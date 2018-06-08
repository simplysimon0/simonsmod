package mod.simonsmod.core.objects.items.tools;

import java.util.ArrayList;
import java.util.List;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.handlers.SimonSoundHandler;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class ToolWrench extends Item implements IHasModel {

	public World world;

	public ToolWrench(String name, int uses) {
		this.maxStackSize = 1;
		this.setMaxDamage(uses);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(SimonsMod.SIMONSTAB);

		ItemInit.ITEMS.add(this);
	}

	// ToolTip
	public void func_77624_a(ItemStack stack, World world, List<String> info, ITooltipFlag b) {
		if (GuiScreen.isShiftKeyDown())
			info.add("Rotates and breaks certain blocks");
		else
			info.add("-Press Shift-");
		info.add("Uses Left: " + (stack.getMaxDamage() - stack.getItemDamage()));
	}

	@Override
	public void registerModels() {
		SimonsMod.proxy.registerItemRenderer(this, 0, "inventory");

	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		NBTTagCompound nbt = itemstack.getTagCompound();

		boolean rotated = false;
		IBlockState iblockstate = world.getBlockState(pos);
		Block block = iblockstate.getBlock();
		if (!world.isRemote) {
			if (!player.isSneaking()) {
				if (block.rotateBlock(world, pos, side)) {
					world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ,
							SimonSoundHandler.WRENCH, SoundCategory.PLAYERS, 1.0F, 1.0F);
					return EnumActionResult.SUCCESS;
				}
			} else if (getWrenchable(block, itemstack, player, world, pos, iblockstate, side)) {
				world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SimonSoundHandler.WRENCH,
						SoundCategory.PLAYERS, 1.0F, 1.0F);
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.PASS;
	}

	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		return EnumActionResult.PASS;
	}

	public boolean getWrenchable(Block block, ItemStack itemstack, EntityPlayer player, World worldIn, BlockPos pos,
			IBlockState iblockstate, EnumFacing facing) {
		ArrayList<Block> blockList = Wrenchable.getWrenchableList();
		IBlockState air = Blocks.AIR.getDefaultState();
		for (int i = 0; i < blockList.size(); i++) {
			if (block == blockList.get(i)) {
				ToolWrench.setBlock(itemstack, player, worldIn, pos, air);
				blockList.get(i).dropBlockAsItem(worldIn, pos, iblockstate, 0);
				return true;
			}
		}
		return false;
	}

	public boolean getWrench(Block block, ItemStack itemstack, EntityPlayer player, World worldIn, BlockPos pos,
			IBlockState iblockstate, EnumFacing facing) {
		ArrayList<Block> blockList = Wrenchable.getWrenchableList();
		IBlockState air = Blocks.AIR.getDefaultState();
		for (int i = 0; i < blockList.size(); i++) {
			if (block == blockList.get(i)) {
				return true;
			}
		}
		return false;
	}

	protected static void setBlock(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos,
			IBlockState state) {
		if (!worldIn.isRemote) {
			worldIn.setBlockState(pos, state);
			stack.damageItem(1, player);
		}
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		return itemStack.getItemDamage() < itemStack.getMaxDamage()
				? new ItemStack(itemStack.getItem(), 1, itemStack.getItemDamage() + 1) : ItemStack.EMPTY;
	}
}
