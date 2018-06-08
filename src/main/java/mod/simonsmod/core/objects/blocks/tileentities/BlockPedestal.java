package mod.simonsmod.core.objects.blocks.tileentities;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.objects.simonsEnums.EnumDusts;
import mod.simonsmod.core.objects.tileEntity.TileEntityHopperDuct;
import mod.simonsmod.core.objects.tileEntity.TileEntityPhilosophersCircle;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.command.CommandException;
import net.minecraft.command.EntityNotFoundException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPedestal extends BlockContainer implements IHasModel {

	protected static final AxisAlignedBB[] REDSTONE = new AxisAlignedBB[] {
			new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 0.8125D),
			new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 0.8125D),
			new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 1.0D),
			new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 0.8125D, 0.0625D, 0.8125D),
			new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 0.8125D, 0.0625D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.8125D, 0.0625D, 0.8125D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.8125D, 0.0625D, 1.0D),
			new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 1.0D, 0.0625D, 0.8125D),
			new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 1.0D, 0.0625D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 1.0D, 0.0625D, 0.8125D),
			new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 1.0D, 0.0625D, 1.0D),
			new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 1.0D, 0.0625D, 0.8125D),
			new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 0.8125D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D) };
	// public static final PropertyBool POWERED =
	// PropertyBool.create("powered");
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static boolean Active = false;
	public static String Cardinal = "";

	public BlockPedestal(String name) {
		super(Material.ROCK);
		setUnlocalizedName(name);
		setRegistryName(name);
		this.setCreativeTab(SimonsMod.SIMONSTAB);
		this.setHardness(2.0F);
		// this.setTickRandomly(true);
		this.setSoundType(SoundType.STONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.DOWN));
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMBLOCKS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void addInformation(ItemStack stack, World world, List<String> info, ITooltipFlag b) {
		if(GuiScreen.isShiftKeyDown())
		{
			info.add("Not fully implemented");
		}
		else
			info.add("-Press Shift-");
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityPhilosophersCircle();
	}

	

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		// Entity item = ;
		// Entity entity = entity(EntityItem.class);
		NBTTagCompound nbttagcompound = null;
		if (!worldIn.isRemote) {

			// playerIn.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ()+
			// 0.5);
			double x = (double) pos.getX();
			double y = (double) pos.getY();
			double z = (double) pos.getZ();
			AxisAlignedBB scanInside = new AxisAlignedBB(x, y, z, x, y, z);
			// List entities = worldIn.getEntitiesWithinAABB(EntityItem.class,
			// scanInside);
			if (isMultiBlockStructure(worldIn, pos.getX(), pos.getY(), pos.getZ())) {
				ITextComponent working = new TextComponentTranslation("Working");
				playerIn.sendMessage(working);
				return true;
				// playerIn.attemptTeleport(pos.getX() + 0.5, pos.getY(),
				// pos.getZ() + 0.5);
			} else {
				ITextComponent notworking = new TextComponentTranslation("Not Working");
				playerIn.sendMessage(notworking);
				return false;

			}

		}
		return true;
	}

	@Override
	public void registerModels() {
		SimonsMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

	public boolean isMultiBlockStructureB(World world, int x, int y, int z) {
		// if (checkNorth(world, x, y, z))
		// return true;
		/*
		 * if(checkEast(world,x,y,z)) return true; if(checkSouth(world,x,y,z))
		 * return true; if(checkWest(world,x,y,z)) return true;
		 */
		return false;

	}

	private static boolean isCardinal(World world, int x, int y, int z, int xOffSet, int zOffSet,
			IBlockState[][] cardinal) {
		int count = 0;
		for (int i = 0; i < 22; i++) {
			for (int j = 0; j < 22; j++) {
				if (world.getBlockState(new BlockPos(x - xOffSet + i, y, z - zOffSet + j)).getBlock() == cardinal[i][j]
						.getBlock())
					count++;
				else if (cardinal[i][j] == Blocks.AIR.getBlockState().getBaseState())
					count++;
			}
		}
		// System.out.println(count);
		if (count == 484)
			return true;
		return false;
	}

	static IBlockState[][] rotateCW(IBlockState[][] matrix) {
		int rowNum = matrix.length;
		int colNum = matrix[0].length;

		IBlockState[][] temp = new IBlockState[rowNum][colNum];
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {

				temp[i][j] = matrix[rowNum - j - 1][i];
			}
		}
		return temp;
	}

	public static boolean isMultiBlockStructure(World world, int x, int y, int z) {
		IBlockState o = Blocks.AIR.getBlockState().getBaseState();
		IBlockState X = BlockInit.BLOCK_CHALK.getBlockState().getBaseState();
		IBlockState F = BlockInit.BLOCK_PEDASTAL.getBlockState().getBaseState();
		boolean working = false;

		IBlockState[][] west = new IBlockState[][] {
				// 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22
				/* 0 */ { o, o, o, o, o, o, o, X, X, X, X, X, X, X, X, X, o, o, o, o, o, o, o },
				/* 1 */ { o, o, o, o, o, X, X, o, o, o, o, X, o, o, o, o, X, X, o, o, o, o, o },
				/* 2 */ { o, o, o, o, X, o, o, o, o, o, X, o, X, o, o, o, o, o, X, o, o, o, o },
				/* 3 */ { o, o, o, X, o, o, o, o, o, X, o, o, o, X, o, o, o, o, o, X, o, o, o },
				/* 4 */ { o, o, X, o, o, o, o, o, o, X, o, o, o, X, o, o, o, o, o, o, X, o, o },
				/* 5 */ { o, X, o, o, o, o, o, o, X, o, o, o, o, o, X, o, o, o, o, o, o, X, o },
				/* 6 */ { o, X, o, o, o, o, o, X, o, o, o, o, o, o, o, X, o, o, o, o, o, X, o },
				/* 7 */ { X, o, o, o, o, o, o, X, o, o, o, o, o, o, o, X, o, o, o, o, o, o, X },
				/* 8 */ { X, o, o, o, o, o, X, X, X, X, X, X, X, X, X, X, X, o, o, o, o, o, X },
				/* 9 */ { X, o, o, o, o, X, o, X, o, o, X, X, X, o, o, X, o, X, o, o, o, o, X },
				/* 10 */{ X, o, o, o, o, X, o, X, o, X, o, o, o, X, o, X, o, X, o, o, o, o, X },
				/* 11 */{ X, o, o, o, X, o, o, X, X, o, o, F, o, o, X, X, o, o, X, o, o, o, X },
				/* 12 */{ X, o, o, X, o, o, o, X, X, o, o, o, o, o, X, X, o, o, o, X, o, o, X },
				/* 13 */{ X, o, o, X, o, o, o, X, X, o, o, o, o, o, X, X, o, o, o, X, o, o, X },
				/* 14 */{ X, o, X, o, o, o, o, X, o, X, o, o, o, X, o, X, o, o, o, o, X, o, X },
				/* 15 */{ X, X, o, o, o, o, o, X, o, o, X, X, X, o, o, X, o, o, o, o, o, X, X },
				/* 16 */{ o, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, o },
				/* 17 */{ o, X, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, X, o },
				/* 18 */{ o, o, X, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, X, o, o },
				/* 19 */{ o, o, o, X, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, X, o, o, o },
				/* 20 */{ o, o, o, o, X, o, o, o, o, o, o, o, o, o, o, o, o, o, X, o, o, o, o },
				/* 21 */{ o, o, o, o, o, X, X, o, o, o, o, o, o, o, o, o, X, X, o, o, o, o, o },
				/* 22 */{ o, o, o, o, o, o, o, X, X, X, X, X, X, X, X, X, o, o, o, o, o, o, o } };

		IBlockState[][] north = rotateCW(west);
		IBlockState[][] east = rotateCW(north);
		IBlockState[][] south = rotateCW(east);

		int xOffSet = 11;
		int zOffSet = 11;
		if (isCardinal(world, x, y, z, xOffSet, zOffSet, north)) {
			world.setBlockState(new BlockPos(x, y, z),
					BlockInit.BLOCK_PEDASTAL.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
			return true;
		} else if (isCardinal(world, x, y, z, xOffSet, zOffSet, east)) {
			world.setBlockState(new BlockPos(x, y, z),
					BlockInit.BLOCK_PEDASTAL.getDefaultState().withProperty(FACING, EnumFacing.EAST));
			return true;
		} else if (isCardinal(world, x, y, z, xOffSet, zOffSet, south)) {
			world.setBlockState(new BlockPos(x, y, z),
					BlockInit.BLOCK_PEDASTAL.getDefaultState().withProperty(FACING, EnumFacing.SOUTH));
			return true;
		} else if (isCardinal(world, x, y, z, xOffSet, zOffSet, west)) {
			world.setBlockState(new BlockPos(x, y, z),
					BlockInit.BLOCK_PEDASTAL.getDefaultState().withProperty(FACING, EnumFacing.WEST));
			return true;
		} else {
			world.setBlockState(new BlockPos(x, y, z),
					BlockInit.BLOCK_PEDASTAL.getDefaultState().withProperty(FACING, EnumFacing.DOWN));
		}
		return false;

	}

	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		boolean flag = worldIn.isBlockPowered(pos);
		return this.getDefaultState().withProperty(FACING, EnumFacing.DOWN);
	}

	/**
	 * Determines if the block is solid enough on the top side to support other
	 * blocks, like redstone components.
	 */
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	/**
	 * The type of render function called. MODEL for mixed tesr and static
	 * model, MODELBLOCK_ANIMATED for TESR-only, LIQUID for vanilla liquids,
	 * INVISIBLE to skip all rendering
	 */
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks
	 * for render
	 */
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public static EnumFacing getFacing(int meta) {
		return EnumFacing.getFront(meta & 7);
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
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	/**
	 * Returns the blockstate with the given rotation from the passed
	 * blockstate. If inapplicable, returns the passed blockstate.
	 */
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}
}