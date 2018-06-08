package mod.simonsmod.core.objects.blocks.tileentities;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.handlers.GuiHandler;
import mod.simonsmod.core.handlers.RegistryHandler;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.objects.simonsEnums.EnumColor;
import mod.simonsmod.core.objects.simonsEnums.EnumMetal;
import mod.simonsmod.core.objects.tileEntity.TileEntityHopperDuct;
import mod.simonsmod.core.objects.tileEntity.TileEntityHopperOmni;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOmniHopper extends BlockContainer implements IHasModel {
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final PropertyDirection FACINGE = PropertyDirection.create("facing_extend");

	// 																x1, y1, z1, x2, y2, z2
	//protected static final AxisAlignedBB FULL = new AxisAlignedBB(0.2F, 0.2F, 0.2F, 0.8F, 0.8F, 0.8F);
	protected static final AxisAlignedBB DOWN = new AxisAlignedBB(0.3F, -0.3F, 0.3F, 0.7F, 0.7F, 0.7F);
	protected static final AxisAlignedBB UP = new AxisAlignedBB(0.3F, 0.3F, 0.3F, 0.7F, 1.3F, 0.7F);
	protected static final AxisAlignedBB NORTH = new AxisAlignedBB(0.3F, 0.3F, -0.3F, 0.7F, 0.7F, 0.7F);
	protected static final AxisAlignedBB SOUTH = new AxisAlignedBB(0.3F, 0.3F, 0.3F, 0.7F, 0.7F, 1.3F);
	protected static final AxisAlignedBB WEST = new AxisAlignedBB(-0.3F, 0.3F, 0.3F, 0.7F, 0.7F, 0.7F);
	protected static final AxisAlignedBB EAST = new AxisAlignedBB(0.3F, 0.3F, 0.3F, 1.3F, 0.7F, 0.7F);
	//protected static final AxisAlignedBB Duct = new AxisAlignedBB(0.35D, 0.35D, 0.6D, 0.65D, 0.65D, 0.65D);
	// protected static final AxisAlignedBB UP_AABB = new AxisAlignedBB(0.0D,
	// 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
	// protected static final AxisAlignedBB DOWN_AABB = new AxisAlignedBB(0.0D,
	// 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);

	public BlockOmniHopper(String name) {
		super(Material.IRON, MapColor.STONE);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(1.5F);
		setResistance(10);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.DOWN).withProperty(FACINGE, EnumFacing.DOWN));
		this.setCreativeTab(SimonsMod.SIMONSTAB);
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(BlockInit.HOPPER_OMNI);
    }
	
	// Registers the Item Model

	@Override
	public void registerModels() {
		SimonsMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

	// Sets the bounding box
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}

	// sets the collision box
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, FULL_BLOCK_AABB);

		// addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
		// addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
		// addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
		// addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
		// addCollisionBoxToList(pos, entityBox, collidingBoxes, UP_AABB);
		// addCollisionBoxToList(pos, entityBox, collidingBoxes, DOWN_AABB);
	}

	/**
	 * Called by ItemBlocks just before a block is actually set in the world, to
	 * allow for adjustments to the IBlockstate
	 */
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		EnumFacing enumfacing = facing;
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof TileEntityHopperOmni) {
			//((TileEntityHopperOmni) tileentity).setExtendedFacing(EnumFacing.getDirectionFromEntityLiving(pos, placer));
			//((TileEntityHopperOmni) tileentity).setRegularFacing(enumfacing);
			
		}
		//return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
		
		return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(FACINGE, EnumFacing.getDirectionFromEntityLiving(pos, placer));
	}
	

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing
	 * the block.
	 */
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityHopperOmni();
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

			if (tileentity instanceof TileEntityHopperOmni) {
				((TileEntityHopperOmni) tileentity).setCustomName(stack.getDisplayName());
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
		
		if (!worldIn.isRemote) {
			playerIn.openGui(SimonsMod.instance, GuiHandler.HOPPER_OMNI, worldIn, pos.getX(), pos.getY(),
					pos.getZ());
		}
		return true;
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
		int meta2 = 0;
		IBlockState state;
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				if(meta2 == meta)
					return combineProperties(i, j);
				meta2++;
			}
		}
		return this.getDefaultState().withProperty(FACING, EnumFacing.DOWN).withProperty(FACINGE, EnumFacing.DOWN);
	}
	
	public static EnumFacing getFacing(int meta, boolean face) {
		int meta2 = 0;
		IBlockState state;
		for(int Facing = 0; Facing < 5; Facing++)
		{
			for(int Extended = 0; Extended < 5; Extended++)
			{
				if(meta2 == meta)
				{
					if(face)
						return intToEnum(Facing);
					return intToEnum(Extended);
				}
					
				meta2++;
			}
		}
		return EnumFacing.DOWN;
	}
	
	public static EnumFacing intToEnum(int num)
	{
		if(num == 0)
			return EnumFacing.DOWN;
		else if(num == 1)
			return EnumFacing.UP;
		else if(num == 2)
			return EnumFacing.NORTH;
		else if(num == 3)
			return EnumFacing.EAST;
		else if(num == 4)
			return EnumFacing.SOUTH;
		else 
			return EnumFacing.WEST;
	}
	
	public IBlockState combineProperties(int one, int two)
	{
		return this.getDefaultState().withProperty(FACING, intToEnum(one)).withProperty(FACINGE, intToEnum(two));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = 0;
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				if(state == combineProperties(i, j))
					return meta;
				meta++;
			}
		}
		return 0;
	}
	
	/**
     * Get the actual Block state of this Block at the given position. This applies properties not visible in the
     * metadata, such as fence connections.
     */
	/*@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(FACING, getNbtFacing(worldIn, pos, false))
					.withProperty(FACINGE, getNbtFacing(worldIn, pos, true));
		
	}*/
	/**
     * Get the facing extended from the tile entity nbt
     */
	private EnumFacing getNbtFacing(IBlockAccess worldIn, BlockPos pos, boolean extended)
	{
		//BlockPos other = pos.offset(facing);
		Block block = worldIn.getBlockState(pos).getBlock();
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = (TileEntityHopperOmni) worldIn.getTileEntity(pos);
		if(extended)
			return((TileEntityHopperOmni) tileentity).getExtendedFacing();
		return((TileEntityHopperOmni) tileentity).getRegularFacing();
	}
	

	/**
	 * Returns the blockstate with the given rotation from the passed
	 * blockstate. If inapplicable, returns the passed blockstate.
	 */
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING))).withProperty(FACINGE, rot.rotate((EnumFacing) state.getValue(FACINGE)));
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 */
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING))).withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACINGE)));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, FACINGE });
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