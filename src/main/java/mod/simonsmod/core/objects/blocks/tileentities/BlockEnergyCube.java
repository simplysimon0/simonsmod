package mod.simonsmod.core.objects.blocks.tileentities;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.handlers.GuiHandler;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.objects.tileEntity.TileEntityEnergyCube;
import mod.simonsmod.core.objects.tileEntity.TileEntityPlacer;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEnergyCube extends BlockContainer implements ITileEntityProvider, IHasModel{

	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	
	public BlockEnergyCube(String name) {
		super(Material.IRON);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(1.5F);
		setResistance(10);
		setCreativeTab(SimonsMod.SIMONSTAB);
        //this.setMaxDamage(10000);
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMBLOCKS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	@Override
	public void registerModels() {
		SimonsMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> info, ITooltipFlag b) {
		if(GuiScreen.isShiftKeyDown())
		{
			NBTTagCompound nbt = getOrCreateNbtData(stack);
			info.add(nbt.getInteger("Energy")+"/"+nbt.getInteger("Capacity"));
		}
		else
			info.add(TextFormatting.BLUE + "-Press Shift-");
	}
	
	public static NBTTagCompound getOrCreateNbtData(ItemStack stack) {
		NBTTagCompound ret = stack.getTagCompound();
		if (ret == null) {
			ret = new NBTTagCompound();
			stack.setTagCompound(ret);
		}

		return ret;
	}
	
	
	/**
     * Called when the block is right clicked by a player.
     */
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityEnergyCube)
            {
            	playerIn.openGui(SimonsMod.instance, GuiHandler.ENERGYCUBE, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
            return true;
        }
    }
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityEnergyCube();
	}
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof TileEntityEnergyCube)
        {
			//System.out.println("Placed");
			TileEntityEnergyCube tile = (TileEntityEnergyCube) te;
			int energy = tile.storage.getEnergyStored();
			int energyMax = tile.storage.getMaxEnergyStored();
			if(stack.hasTagCompound())
			{
				if (stack.getTagCompound().hasKey("Energy"))
					energy = stack.getTagCompound().getInteger("Energy");
				if (stack.getTagCompound().hasKey("Energy"))
					energyMax = stack.getTagCompound().getInteger("Capacity");
			}
			tile.storage.setEnergyStored(energy);
			tile.storage.setCapacity(energyMax);
        }
	}
	
	
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
        
		TileEntity te = world.getTileEntity(pos);

        if (te instanceof TileEntityEnergyCube)
        {
        	TileEntityEnergyCube tile = (TileEntityEnergyCube) te;
    		int energy = tile.storage.getEnergyStored();
    		int energyMax = tile.storage.getMaxEnergyStored();
    		ItemStack stack = new ItemStack(world.getBlockState(pos).getBlock(), 1, 0);
    		if (!stack.hasTagCompound()) {
    			stack.setTagCompound(new NBTTagCompound());
    		}
    		stack.getTagCompound().setInteger("Energy", energy);
    		stack.getTagCompound().setInteger("Capacity", energyMax);
    		drops.add(stack);
        }
        else
        {
            drops.add(new ItemStack(BlockInit.ENERGY_CUBE, 1, 0));
        }
        
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		TileEntity te = world.getTileEntity(pos);

        if (te instanceof TileEntityEnergyCube)
        {
        	TileEntityEnergyCube tile = (TileEntityEnergyCube) te;
    		int energy = tile.storage.getEnergyStored();
    		int energyMax = tile.storage.getMaxEnergyStored();
    		ItemStack stack = new ItemStack(world.getBlockState(pos).getBlock(), 1, 0);
    		if (!stack.hasTagCompound()) {
    			stack.setTagCompound(new NBTTagCompound());
    			//System.out.println("None");
    		}
    		stack.getTagCompound().setInteger("Energy", energy);
    		stack.getTagCompound().setInteger("Capacity", energyMax);
    		return stack;
        }
        else
        {
            return new ItemStack(BlockInit.ENERGY_CUBE, 1, 0);
        }
	}
	
	@Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {	
        if (willHarvest) return true; //If it will harvest, delay deletion of the block until after getDrops
        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }
    /**
     * Spawns the block's drops in the world. By the time this is called the Block has possibly been set to air via
     * Block.removedByPlayer
     */
    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack tool)
    {
        super.harvestBlock(world, player, pos, state, te, tool);
        world.setBlockToAir(pos);
    }
	
	
	
	/**
	 * Called by ItemBlocks just before a block is actually set in the world, to
	 * allow for adjustments to the IBlockstate
	 */
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		EnumFacing enumfacing = facing.getOpposite();
		if (placer.isSneaking())
			return this.getDefaultState().withProperty(FACING, enumfacing);
		return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
	}
	
	public static EnumFacing getFacing(int meta) {
		return EnumFacing.getFront(meta & 7);
	}
	
	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, getFacing(meta));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumFacing) state.getValue(FACING)).getIndex();
		i |= 8;
		return i;
	}

	/**
	 * Returns the blockstate with the given rotation from the passed
	 * blockstate. If inapplicable, returns the passed blockstate.
	 */
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 */
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	// tells minecraft that this block isnt a full cube, i.e. flower, button
	@Override
	public boolean isFullCube(IBlockState state) {
		return true;
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks
	 * for render
	 */
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

}
