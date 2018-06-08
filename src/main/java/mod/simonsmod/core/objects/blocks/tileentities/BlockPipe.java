package mod.simonsmod.core.objects.blocks.tileentities;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.handlers.GuiHandler;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.objects.tileEntity.TileEntityPipe;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPipe extends BlockContainer implements IHasModel {
	//public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final PropertyBool UP = PropertyBool.create("up");
	public static final PropertyBool DOWN = PropertyBool.create("down");
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");
	public static final PropertyBool UD = PropertyBool.create("ud");
	public static final PropertyBool NS = PropertyBool.create("ns");
	public static final PropertyBool EW = PropertyBool.create("ew");
	
    

	
	//private static final EnumMap<EnumFacing, List<AxisAlignedBB>> bounds;
	// 																x1, y1, z1, x2, y2, z2
	/*
	protected static final AxisAlignedBB FULL = new AxisAlignedBB(0.2F, 0.2F, 0.2F, 0.6F, 0.6F, 0.6F);
	protected static final AxisAlignedBB DOWN 	= new AxisAlignedBB(0.3F, 0.0F, 0.3F, 0.7F, 0.7F, 0.7F);
	protected static final AxisAlignedBB UP 	= new AxisAlignedBB(0.3F, 0.3F, 0.3F, 0.7F, 1.0F, 0.7F);
	protected static final AxisAlignedBB NORTH 	= new AxisAlignedBB(0.3F, 0.3F, 0.0F, 0.7F, 0.7F, 0.7F);
	protected static final AxisAlignedBB SOUTH 	= new AxisAlignedBB(0.3F, 0.3F, 0.3F, 0.7F, 0.7F, 1.0F);
	protected static final AxisAlignedBB WEST 	= new AxisAlignedBB(0.0F, 0.3F, 0.3F, 0.7F, 0.7F, 0.7F);
	protected static final AxisAlignedBB EAST 	= new AxisAlignedBB(0.3F, 0.3F, 0.3F, 1.0F, 0.7F, 0.7F);
	protected static final AxisAlignedBB Duct = new AxisAlignedBB(0.35D, 0.35D, 0.6D, 0.65D, 0.65D, 0.65D);
	// protected static final AxisAlignedBB UP_AABB = new AxisAlignedBB(0.0D,
	// 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
	// protected static final AxisAlignedBB DOWN_AABB = new AxisAlignedBB(0.0D,
	// 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
*/
	public BlockPipe(String name) {
		super(Material.IRON, MapColor.STONE);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(1.5F);
		setResistance(10);
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(UP, false)
				.withProperty(DOWN, false)
				.withProperty(NORTH, false)
				.withProperty(EAST, false)
				.withProperty(SOUTH, false)
				.withProperty(WEST, false)
				.withProperty(UD, false)
				.withProperty(NS, false)
				.withProperty(EW, false));
		this.setCreativeTab(SimonsMod.SIMONSTAB);
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


    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(BlockInit.HOPPER_DUCT);
    }
	
	// Registers the Item Model

	@Override
	public void registerModels() {
		SimonsMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

	// Sets the bounding box
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = this.getActualState(state, source, pos);
        int id = getBoundingBoxIdx(state);
        
        if(AllBoundingBoxes()[id] != null)
        	return AllBoundingBoxes()[id];
        return FULL_BLOCK_AABB;
    }
	
	
	private static int getBoundingBoxIdx(IBlockState state)
    {
        int i = 0;

        if (((Boolean)state.getValue(NORTH)).booleanValue())
        {
            i |= 1 << EnumFacing.NORTH.getIndex();
        }

        if (((Boolean)state.getValue(EAST)).booleanValue())
        {
            i |= 1 << EnumFacing.EAST.getIndex();
        }

        if (((Boolean)state.getValue(SOUTH)).booleanValue())
        {
            i |= 1 << EnumFacing.SOUTH.getIndex();
        }

        if (((Boolean)state.getValue(WEST)).booleanValue())
        {
            i |= 1 << EnumFacing.WEST.getIndex();
        }
        
        if (((Boolean)state.getValue(UP)).booleanValue())
        {
            i |= 1 << EnumFacing.UP.getIndex();
        }
        
        if (((Boolean)state.getValue(DOWN)).booleanValue())
        {
            i |= 1 << EnumFacing.DOWN.getIndex();
        }
        //System.out.println(i);
        return i;
    }
	
	
	private static AxisAlignedBB makeAABB(int fromX, int fromY, int fromZ, int toX, int toY, int toZ) {
		return new AxisAlignedBB((double) ((float) fromX / 16.0F), (double) ((float) fromY / 16.0F),
				(double) ((float) fromZ / 16.0F), (double) ((float) toX / 16.0F), (double) ((float) toY / 16.0F),
				(double) ((float) toZ / 16.0F));
	}
	private AxisAlignedBB[] AllBoundingBoxes(){
		AxisAlignedBB[] bounding = new AxisAlignedBB[64];
		//makeAABB(fromX, fromY, fromZ, toX, toY, toZ)
		bounding[0] = makeAABB(4, 4, 4, 12, 12, 12);//Cube
		bounding[1] = makeAABB(4, 0, 4, 12, 12, 12);//Down
		bounding[2] = makeAABB(4, 4, 4, 12, 16, 12);//Up
		bounding[3] = makeAABB(4, 0, 4, 12, 16, 12);//Up Down
		bounding[4] = makeAABB(4, 4, 0, 12, 12, 12);//North
		bounding[5] = makeAABB(4, 0, 0, 12, 12, 12);//North Down
		bounding[6] = makeAABB(4, 4, 0, 12, 16, 12);//North Up
		bounding[7] = makeAABB(4, 0, 0, 12, 16, 12);//North Down Up
		bounding[8] = makeAABB(4, 4, 4, 12, 12, 16);//South
		bounding[9] = makeAABB(4, 0, 4, 12, 12, 16);//South Down
		bounding[10] = makeAABB(4, 4, 4, 12, 16, 16);//South Up
		bounding[11] = makeAABB(4, 0, 4, 12, 16, 16);//South Down Up
		bounding[12] = makeAABB(4, 4, 0, 12, 12, 16);//North South
		bounding[13] = makeAABB(4, 0, 0, 12, 12, 16);//North South Down
		bounding[14] = makeAABB(4, 4, 0, 12, 16, 16);//North South Up
		bounding[15] = makeAABB(4, 0, 0, 12, 16, 16);//North South Down Up
		bounding[16] = makeAABB(0, 4, 4, 12, 12, 12);//West
		bounding[17] = makeAABB(0, 0, 4, 12, 12, 12);//West Down
		bounding[18] = makeAABB(0, 4, 4, 12, 16, 12);//West Up
		bounding[19] = makeAABB(0, 0, 4, 12, 16, 12);//West Down Up
		bounding[20] = makeAABB(0, 4, 0, 12, 12, 12);//North West
		bounding[21] = makeAABB(0, 0, 0, 12, 12, 12);//North West Down
		bounding[22] = makeAABB(0, 4, 0, 12, 16, 12);//North West Up
		bounding[23] = makeAABB(0, 0, 0, 12, 16, 12);//North West Down Up
		bounding[24] = makeAABB(0, 4, 4, 12, 12, 16);//South West
		bounding[25] = makeAABB(0, 0, 4, 12, 12, 16);//South West Down
		bounding[26] = makeAABB(0, 4, 4, 12, 16, 16);//South West Up
		bounding[27] = makeAABB(0, 0, 4, 12, 16, 16);//South West Down Up 
		bounding[28] = makeAABB(0, 4, 0, 12, 12, 16);//North South West 
		bounding[29] = makeAABB(0, 0, 0, 12, 12, 16);//North South West Down
		bounding[30] = makeAABB(0, 4, 0, 12, 16, 16);//North South West Up
		bounding[31] = makeAABB(0, 0, 0, 12, 16, 16);//North South West Down Up
		bounding[32] = makeAABB(4, 4, 4, 16, 12, 12);//East
		bounding[33] = makeAABB(4, 0, 4, 16, 12, 12);//East Down
		bounding[34] = makeAABB(4, 4, 4, 16, 16, 12);//East Up
		bounding[35] = makeAABB(4, 0, 4, 16, 16, 12);//East Down Up
		bounding[36] = makeAABB(4, 4, 0, 16, 12, 12);//North East
		bounding[37] = makeAABB(4, 0, 0, 16, 12, 12);//North East Down
		bounding[38] = makeAABB(4, 4, 0, 16, 16, 12);//North East Up
		bounding[39] = makeAABB(4, 0, 0, 16, 16, 12);//North East Down Up
		bounding[40] = makeAABB(4, 4, 4, 16, 12, 16);//South East
		bounding[41] = makeAABB(4, 0, 4, 16, 12, 16);//South East Down
		bounding[42] = makeAABB(4, 4, 4, 16, 16, 16);//South East Up
		bounding[43] = makeAABB(4, 0, 4, 16, 16, 16);//South East Up Down
		bounding[44] = makeAABB(4, 4, 0, 16, 12, 16);//South East North
		bounding[45] = makeAABB(4, 0, 0, 16, 12, 16);//South East North Down
		bounding[46] = makeAABB(4, 4, 0, 16, 16, 16);//South East North Up
		bounding[47] = makeAABB(4, 0, 0, 16, 16, 16);//South East North Up Down
		bounding[48] = makeAABB(0, 4, 4, 16, 12, 12);//East West
		bounding[49] = makeAABB(0, 0, 4, 16, 12, 12);//East West Down
		bounding[50] = makeAABB(0, 4, 4, 16, 16, 12);//East West Up
		bounding[51] = makeAABB(0, 0, 4, 16, 16, 12);//East West Down Up
		bounding[52] = makeAABB(0, 4, 0, 16, 12, 12);//East West North
		bounding[53] = makeAABB(0, 0, 0, 16, 12, 12);//East West North Down
		bounding[54] = makeAABB(0, 4, 0, 16, 16, 12);//East West North Up
		bounding[55] = makeAABB(0, 0, 0, 16, 16, 12);//East West North Down Up
		bounding[56] = makeAABB(0, 4, 4, 16, 12, 16);//East West South
		bounding[57] = makeAABB(0, 0, 4, 16, 12, 16);//East West South Down
		bounding[58] = makeAABB(0, 4, 4, 16, 16, 16);//East West South Up
		bounding[59] = makeAABB(0, 0, 4, 16, 16, 16);//East West South Down Up
		bounding[60] = makeAABB(0, 4, 0, 16, 12, 16);//North East West South
		bounding[61] = makeAABB(0, 0, 0, 16, 12, 16);//North East West South Down
		bounding[62] = makeAABB(0, 4, 0, 16, 16, 16);//North East West South Up
		return bounding;
		
	}

	// sets the collision box
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, makeAABB(4, 4, 4, 12, 12, 12));
		
		// addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
		// addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
		// addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
		// addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
		// addCollisionBoxToList(pos, entityBox, collidingBoxes, UP_AABB);
		// addCollisionBoxToList(pos, entityBox, collidingBoxes, DOWN_AABB);
	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing
	 * the block.
	 */
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityPipe();
	}

	/**
	 * Called by ItemBlocks after a block is set in the world, to allow
	 * post-place logic
	 */
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

		if (stack.hasDisplayName()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);

			if (tileentity instanceof TileEntityPipe) {
				((TileEntityPipe) tileentity).setCustomName(stack.getDisplayName());
			}
		}
	}

	/**
	 * Determines if the block is solid enough on the top side to support other
	 * blocks, like redstone components.
	 */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	/**
	 * Called after the block is set in the Chunk data, but before the Tile
	 * Entity is set
	 */
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		this.updateState(worldIn, pos, state);
	}

	/**
	 * Called when the block is right clicked by a player.
	 */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		} else {
			
			TileEntity tileentity = worldIn.getTileEntity(pos);

			if (tileentity instanceof TileEntityPipe) {
				
					playerIn.openGui(SimonsMod.instance, GuiHandler.PIPE, worldIn, pos.getX(), pos.getY(), pos.getZ());
					return true;
				
				
			}
			 
			return false;
		}
		
	}

	/**
	 * Called when a neighboring block was changed and marks that this state
	 * should perform any checks during a neighbor change. Cases may include
	 * when redstone power is updated, cactus blocks popping off due to a
	 * neighboring solid block, etc.
	 */
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		this.updateState(worldIn, pos, state);
	}

	private void updateState(World worldIn, BlockPos pos, IBlockState state) {

	}

	/**
	 * Called serverside after this block is replaced with another in Chunk, but
	 * before the Tile Entity is updated
	 */
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof IInventory) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
			worldIn.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(worldIn, pos, state);
	}

	// sets the render type to model
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	// tells minecraft that this block isnt a full cube, i.e. flower, button
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks
	 * for render
	 */
	@Override
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
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState()
				.withProperty(UP, false)
				.withProperty(DOWN, false)
				.withProperty(NORTH, false)
				.withProperty(EAST, false)
				.withProperty(SOUTH, false)
				.withProperty(WEST, false)
				.withProperty(UD, false)
				.withProperty(NS, false)
				.withProperty(EW, false);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
	
	/**
     * Get the actual Block state of this Block at the given position. This applies properties not visible in the
     * metadata, such as fence connections.
     */
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {		
        return state.withProperty(NORTH, canConnectPipe(worldIn, pos, EnumFacing.NORTH))
                    .withProperty(EAST,  canConnectPipe(worldIn, pos, EnumFacing.EAST))
                    .withProperty(SOUTH, canConnectPipe(worldIn, pos, EnumFacing.SOUTH))
                    .withProperty(WEST,  canConnectPipe(worldIn, pos, EnumFacing.WEST))
                    .withProperty(UP,  	 canConnectPipe(worldIn, pos, EnumFacing.UP))
                    .withProperty(DOWN,  canConnectPipe(worldIn, pos, EnumFacing.DOWN))
                    .withProperty(UD,  	 canConnectTwoPipe(worldIn, pos, EnumFacing.UP, EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST))
                    .withProperty(NS,  	 canConnectTwoPipe(worldIn, pos, EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.UP, EnumFacing.DOWN, EnumFacing.EAST, EnumFacing.WEST))
                    .withProperty(EW,  	 canConnectTwoPipe(worldIn, pos, EnumFacing.EAST, EnumFacing.WEST, EnumFacing.UP, EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.DOWN));
    }
    
    
    private boolean canConnectTwoPipe(IBlockAccess worldIn, BlockPos pos, EnumFacing facing1, EnumFacing facing2, EnumFacing facing3, EnumFacing facing4, EnumFacing facing5, EnumFacing facing6) {
    	//World world = worldIn.getWorldType();
    	if((canConnectPipe(worldIn, pos, facing1) && canConnectPipe(worldIn, pos, facing2)) &&
    	   (!canConnectPipe(worldIn, pos, facing3) && !canConnectPipe(worldIn, pos, facing4) && !canConnectPipe(worldIn, pos, facing5) && !canConnectPipe(worldIn, pos, facing6)))
    		return true;
    	return false;
	}
    
    private boolean canConnectPipe(IBlockAccess worldIn, BlockPos pos, EnumFacing facing) {
    	//World world = worldIn.getWorldType();
    	BlockPos other = pos.offset(facing);
        Block block = worldIn.getBlockState(other).getBlock();
        IBlockState state = worldIn.getBlockState(other);
        IInventory iinventory = null;
        if (block.hasTileEntity(state))
        {
            TileEntity tileentity = worldIn.getTileEntity(other);

            if (block instanceof BlockPipe || tileentity instanceof IInventory)
            {
                return true;
            }
        }
        
    	return false;

	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { UP, DOWN, NORTH, EAST, SOUTH, WEST, UD, NS, EW });
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
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return face == EnumFacing.UP ? BlockFaceShape.BOWL : BlockFaceShape.UNDEFINED;
	}

}