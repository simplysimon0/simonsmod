package mod.simonsmod.core.objects.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.mojang.realmsclient.dto.PlayerInfo;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.objects.simonsEnums.EnumLab;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOmniSlab extends Block implements IHasModel {
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	protected static final AxisAlignedBB AABB_BOTTOM_FACING = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
	protected static final AxisAlignedBB AABB_TOP_FACING = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_NORTH_FACING = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D);
	protected static final AxisAlignedBB AABB_WEST_FACING = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_SOUTH_FACING = new AxisAlignedBB(0.0D, 0.0D, 0.5D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_EAST_FACING = new AxisAlignedBB(0.5D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

	public BlockOmniSlab(String name, Material material, float hardness, float resistance) {
		super(material);
		IBlockState iblockstate = this.blockState.getBaseState();
		iblockstate = iblockstate.withProperty(FACING, EnumFacing.DOWN);
		this.setDefaultState(iblockstate);
		setUnlocalizedName(name);
		setRegistryName(name);
		this.setLightOpacity(255);
		setHardness(hardness);
		setResistance(resistance);
		setCreativeTab(SimonsMod.SIMONSTAB);
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMBLOCKS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	public void addInformation(ItemStack stack, World world, List<String> info, ITooltipFlag b) {
		super.addInformation(stack, world, info, b);
		info.add(TextFormatting.BLUE + "Try placing it on a wall");

	}

	@Override
	public void registerModels() {
		SimonsMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

	public static EnumFacing getFacing(int meta) {
		return EnumFacing.getFront(meta & 7);
	}

	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = this.getDefaultState();
		iblockstate = iblockstate.withProperty(FACING, getFacing(meta));
		return iblockstate;
	}

	public int getMetaFromState(IBlockState state) {
		int j = 0;
		j = j | ((EnumFacing) state.getValue(FACING)).getIndex();
		j |= 8;

		return j;
	}

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
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	protected boolean canSilkHarvest() {
		return false;
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (state.getValue(FACING) == EnumFacing.UP) {
			return AABB_TOP_FACING;
		} else if (state.getValue(FACING) == EnumFacing.DOWN) {
			return AABB_BOTTOM_FACING;
		} else if (state.getValue(FACING) == EnumFacing.NORTH) {
			return AABB_NORTH_FACING;
		} else if (state.getValue(FACING) == EnumFacing.EAST) {
			return AABB_EAST_FACING;
		} else if (state.getValue(FACING) == EnumFacing.SOUTH) {
			return AABB_SOUTH_FACING;
		} else {
			return AABB_WEST_FACING;
		}

	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {

		if (state.getValue(FACING) == EnumFacing.UP) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_TOP_FACING);
		} else if (state.getValue(FACING) == EnumFacing.DOWN) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BOTTOM_FACING);
		} else if (state.getValue(FACING) == EnumFacing.NORTH) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NORTH_FACING);
		} else if (state.getValue(FACING) == EnumFacing.EAST) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_EAST_FACING);
		} else if (state.getValue(FACING) == EnumFacing.SOUTH) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SOUTH_FACING);
		} else {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WEST_FACING);
		}
	}

	/**
	 * Determines if the block is solid enough on the top side to support other
	 * blocks, like redstone components.
	 */
	public boolean isTopSolid(IBlockState state) {
		return state.getValue(FACING) == EnumFacing.UP;
	}

	/**
	 * Get the geometry of the queried face at the given position and state.
	 * This is used to decide whether things like buttons are allowed to be
	 * placed on the face, or how glass panes connect to the face, among other
	 * things.
	 * <p>
	 * Common values are {@code SOLID}, which is the default, and
	 * {@code UNDEFINED}, which represents something that does not fit the other
	 * descriptions and will generally cause other things not to connect to the
	 * face.
	 * 
	 * @return an approximation of the form of the given face
	 */
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		if (face == EnumFacing.UP && state.getValue(FACING) == EnumFacing.UP) {
			return BlockFaceShape.SOLID;
		} else {
			return face == EnumFacing.DOWN && state.getValue(FACING) == EnumFacing.DOWN ? BlockFaceShape.SOLID
					: BlockFaceShape.UNDEFINED;
		}
	}

	/**
	 * Called by ItemBlocks just before a block is actually set in the world, to
	 * allow for adjustments to the IBlockstate
	 */
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		EnumFacing enumfacing = facing.getOpposite();
		if (!placer.isSneaking())
			return this.getDefaultState().withProperty(FACING, enumfacing);
		IBlockState iblockstate = super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer)
				.withProperty(FACING, EnumFacing.DOWN);
		BlockPos checkfor = pos;
		if (placer.getHorizontalFacing() == EnumFacing.WEST)
			checkfor = pos.west();
		else if (placer.getHorizontalFacing() == EnumFacing.NORTH)
			checkfor = pos.north();
		else if (placer.getHorizontalFacing() == EnumFacing.EAST)
			checkfor = pos.east();
		else if (placer.getHorizontalFacing() == EnumFacing.SOUTH)
			checkfor = pos.south();
		IBlockState blockstate = this.getDefaultState();
		if (worldIn.getBlockState(checkfor) == blockstate.withProperty(FACING, EnumFacing.NORTH)
				|| worldIn.getBlockState(checkfor) == blockstate.withProperty(FACING, EnumFacing.EAST)
				|| worldIn.getBlockState(checkfor) == blockstate.withProperty(FACING, EnumFacing.SOUTH)
				|| worldIn.getBlockState(checkfor) == blockstate.withProperty(FACING, EnumFacing.WEST))
			return worldIn.getBlockState(checkfor);
		return facing != EnumFacing.DOWN && (facing == EnumFacing.UP || (double) hitY <= 0.5D) ? iblockstate
				: iblockstate.withProperty(FACING, EnumFacing.UP);

	}

}