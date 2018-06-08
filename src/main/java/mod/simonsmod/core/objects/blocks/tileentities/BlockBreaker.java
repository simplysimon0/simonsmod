package mod.simonsmod.core.objects.blocks.tileentities;

import java.util.List;
import java.util.Random;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.handlers.GuiHandler;
import mod.simonsmod.core.handlers.SimonSoundHandler;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.objects.items.tools.ToolPaxel;
import mod.simonsmod.core.objects.tileEntity.TileEntityBreaker;
import mod.simonsmod.core.objects.tileEntity.TileEntityHopperOmni;
import mod.simonsmod.core.objects.tileEntity.TileEntityHopperOmniOld;
import mod.simonsmod.core.objects.tileEntity.TileEntityBreaker;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.PositionImpl;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryDefaulted;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundSetupEvent;

public class BlockBreaker extends BlockRedstoneActivated implements IHasModel, IRedstoneActivated {
	protected Random rand = new Random();

	public BlockBreaker(String name) {
		super(Material.IRON);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(1.5F);
		setResistance(10);
		setCreativeTab(SimonsMod.SIMONSTAB);
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMBLOCKS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TRIGGERED,
				Boolean.valueOf(false)));
	}
	
	@Override
	public void addInformation(ItemStack stack, World world, List<String> info, ITooltipFlag b) {
		if(GuiScreen.isShiftKeyDown())
		{
			info.add("Breaks a block when a redstone signal is applied to it");
			info.add("Items are dropped from the back or placed into an aplicable inventory");
		}
		else
			info.add("-Press Shift-");
	}

	@Override
	public void registerModels() {
		SimonsMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

	/**
	 * Called when the block is right clicked by a player.
	 */
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		} else {
			TileEntity tileentity = worldIn.getTileEntity(pos);

			if (tileentity instanceof TileEntityBreaker) {
				 //playerIn.openGui(SimonsMod.instance, GuiHandler.BREAKER, worldIn, pos.getX(), pos.getY(), pos.getZ());
			}

			return false;
		}
	}

	@Override
	public void onTriggered(World worldIn, BlockPos pos) {
		BlockSourceImpl blocksourceimpl = new BlockSourceImpl(worldIn, pos);
		TileEntityBreaker tileentitybreaker = (TileEntityBreaker) blocksourceimpl.getBlockTileEntity();
		EnumFacing enumfacing = (EnumFacing) worldIn.getBlockState(pos).getValue(FACING);
		BlockPos blockpos = pos.offset(enumfacing);
		IBlockState state = worldIn.getBlockState(blockpos);
		BlockPos blockposdrop = pos.offset(enumfacing.getOpposite());
		IBlockState blockdropstate = worldIn.getBlockState(blockposdrop);
		if(tileentitybreaker != null)
		{
			ItemStack itemstack = new ItemStack(state.getBlock(), 1,state.getBlock().getMetaFromState(state));
			int i = tileentitybreaker.getPlaceSlot(itemstack);
		/*
		 * if(blockdropstate ==
		 * BlockInit.HOPPER_OMNI.getDefaultState().withProperty(
		 * BlockOmniHopperOld.FACING, enumfacing.getOpposite())) { IInventory
		 * iinventory = TileEntityHopperOmni.getInventoryAtPosition(worldIn,
		 * (double)blockposdrop.getX(), (double)blockposdrop.getY(),
		 * (double)blockposdrop.getZ()); BlockSourceImpl blocksourceimplbehind =
		 * new BlockSourceImpl(worldIn, blockposdrop); TileEntityHopperOmniOld
		 * tileentityhopper =
		 * (TileEntityHopperOmniOld)blocksourceimplbehind.getBlockTileEntity();
		 * //if(tileentityhopper.getItems().size() == tileentityhopper.get) if
		 * (iinventory != null) { ItemStack itemstack = new
		 * ItemStack(state.getBlock(), 1,
		 * state.getBlock().getMetaFromState(state));
		 * TileEntityHopperOmni.putStackInInventoryAllSlots(null, iinventory,
		 * itemstack, enumfacing.getOpposite());
		 * worldIn.playSound((EntityPlayer) null, pos.getX(), pos.getY(),
		 * pos.getZ(), state.getBlock().getSoundType(state, worldIn, pos,
		 * null).getBreakSound(), SoundCategory.BLOCKS, 5.0F, 0.8F);
		 * worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState()); } }
		 */
		if (state != Blocks.AIR.getDefaultState()) {
			worldIn.playSound((EntityPlayer) null, pos.getX(), pos.getY(), pos.getZ(),
					state.getBlock().getSoundType(state, worldIn, pos, null).getBreakSound(), SoundCategory.BLOCKS,
					5.0F, 0.8F);
			if (blockdropstate == Blocks.AIR.getDefaultState()) {
				state.getBlock().dropBlockAsItem(worldIn, blockposdrop, state, 0);
				worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState());
			}
			else {
				worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState());
				ItemStack newStack = new ItemStack(state.getBlock().getItemDropped(state, RANDOM, 0), state.getBlock().quantityDropped(RANDOM), state.getBlock().damageDropped(state));
				tileentitybreaker.addItemStack(newStack);
				//tileentitybreaker.setInventorySlotContents(i, itemstack);
			}
		}}
	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing
	 * the block.
	 */
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityBreaker();
	}

	@Override
	public void getName(World worldIn, BlockPos pos, ItemStack stack) {
		if (stack.hasDisplayName()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);

			if (tileentity instanceof TileEntityBreaker) {
				((TileEntityBreaker) tileentity).setCustomName(stack.getDisplayName());
			}
		}
	}

	public static EnumFacing getFacing(int meta) {
		return EnumFacing.getFront(meta & 7);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof IInventory) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
			worldIn.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(worldIn, pos, state);
	}
	
}