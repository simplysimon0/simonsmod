package mod.simonsmod.core.objects.blocks.tileentities;

import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.handlers.GuiHandler;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.objects.tileEntity.TileEntityPipe;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockObserver;
import net.minecraft.block.BlockRedstoneRepeater;
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
import net.minecraft.init.Blocks;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPipeRedstone extends BlockPipe implements IHasModel {
	
	public static final PropertyBool POWERED = PropertyBool.create("power");
	private boolean canProvidePower = true;
	private final Set<BlockPos> blocksNeedingUpdate = Sets.<BlockPos>newHashSet();
    

	public BlockPipeRedstone(String name) {
		super(name);
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
				.withProperty(EW, false)
				.withProperty(POWERED, false));
		this.setCreativeTab(SimonsMod.SIMONSTAB);
	}
	
	// Registers the Item Model

	@Override
	public void registerModels() {
		SimonsMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	/**
     * Calls World.notifyNeighborsOfStateChange() for all neighboring blocks, but only if the given block is a redstone
     * wire.
     */
    private void notifyWireNeighborsOfStateChange(World worldIn, BlockPos pos)
    {
        if (worldIn.getBlockState(pos).getBlock() == this)
        {
            worldIn.notifyNeighborsOfStateChange(pos, this, false);

            for (EnumFacing enumfacing : EnumFacing.values())
            {
                worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this, false);
            }
        }
    }
	/**
	 * Called after the block is set in the Chunk data, but before the Tile
	 * Entity is set
	 */
    
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote)
        {
            this.updateSurroundingRedstone(worldIn, pos, state);


            for (EnumFacing enumfacing1 : EnumFacing.values())
            {
                this.notifyWireNeighborsOfStateChange(worldIn, pos.offset(enumfacing1));
            }

            /*for (EnumFacing enumfacing2 : EnumFacing.Plane.HORIZONTAL)
            {
                BlockPos blockpos = pos.offset(enumfacing2);

                if (worldIn.getBlockState(blockpos).isNormalCube())
                {
                    this.notifyWireNeighborsOfStateChange(worldIn, blockpos.up());
                }
                else
                {
                    this.notifyWireNeighborsOfStateChange(worldIn, blockpos.down());
                }
            }*/
        }
		//this.updateState(worldIn, pos, state);
	}
	
    private IBlockState updateSurroundingRedstone(World worldIn, BlockPos pos, IBlockState state)
    {
        state = this.calculateCurrentChanges(worldIn, pos, pos, state);
        List<BlockPos> list = Lists.newArrayList(this.blocksNeedingUpdate);
        this.blocksNeedingUpdate.clear();

        for (BlockPos blockpos : list)
        {
            worldIn.notifyNeighborsOfStateChange(blockpos, this, false);
        }

        return state;
    }
    
    
	private IBlockState calculateCurrentChanges(World worldIn, BlockPos pos1, BlockPos pos2, IBlockState state) {
		IBlockState iblockstate = state;
		boolean powered = state.getValue(POWERED);
		int j = 0;
		this.canProvidePower = false;
		int k = worldIn.isBlockIndirectlyGettingPowered(pos1);
		this.canProvidePower = true;
		if(k > 0)
			state = state.withProperty(POWERED, true);
		state = state.withProperty(POWERED, powered);

		if (worldIn.getBlockState(pos1) == iblockstate) {
			worldIn.setBlockState(pos1, state, 2);
		}
			//this.blocksNeedingUpdate.add(pos1);

			//for (EnumFacing enumfacing1 : EnumFacing.values()) {
			//	this.blocksNeedingUpdate.add(pos1.offset(enumfacing1));
		//}

		return state;
	}

	/**
	 * Called when a neighboring block was changed and marks that this state
	 * should perform any checks during a neighbor change. Cases may include
	 * when redstone power is updated, cactus blocks popping off due to a
	 * neighboring solid block, etc.
	 */
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if(!worldIn.isRemote)
			this.updateSurroundingRedstone(worldIn, pos, state);
		//this.updateState(worldIn, pos, state);
	}

	private void updateState(World worldIn, BlockPos pos, IBlockState state) {
		 boolean flag = ((Boolean)state.getValue(POWERED)).booleanValue();
	        boolean flag1 = worldIn.isBlockPowered(pos);

	        if (flag1 != flag)
	        {
	            worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.valueOf(flag1)), 3);
	            worldIn.notifyNeighborsOfStateChange(pos.down(),  this, false);
	            worldIn.notifyNeighborsOfStateChange(pos.up(),    this, false);
	            worldIn.notifyNeighborsOfStateChange(pos.north(), this, false);
	            worldIn.notifyNeighborsOfStateChange(pos.east(), this, false);
	            worldIn.notifyNeighborsOfStateChange(pos.south(), this, false);
	            worldIn.notifyNeighborsOfStateChange(pos.west(), this, false);
	        }
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
		if(!worldIn.isRemote)
			this.updateSurroundingRedstone(worldIn, pos, state);
		super.breakBlock(worldIn, pos, state);
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
				.withProperty(EW, false)
				.withProperty(POWERED, false);
	}

    
    public boolean canProvidePower(IBlockState state)
    {
        return true;
    }

    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
    	if(blockAccess.getBlockState(pos.offset(side)).getBlock() instanceof BlockPipeRedstone)
    	{
    		return 0;
    	}
    	if(blockState.getValue(POWERED))
    		return 15;
        return 0;
    }
    
    
    private boolean canConnectTwoPipe(IBlockAccess worldIn, BlockPos pos, EnumFacing facing1, EnumFacing facing2, EnumFacing facing3, EnumFacing facing4, EnumFacing facing5, EnumFacing facing6) {
    	//World world = worldIn.getWorldType();
    	if((canConnectPipe(worldIn, pos, facing1) && canConnectPipe(worldIn, pos, facing2)) &&
    	   (!canConnectPipe(worldIn, pos, facing3) && !canConnectPipe(worldIn, pos, facing4) && !canConnectPipe(worldIn, pos, facing5) && !canConnectPipe(worldIn, pos, facing6)))
    		return true;
    	return false;
	}
    
    private boolean canConnectPipe(IBlockAccess worldIn, BlockPos pos, EnumFacing facing) {
		// World world = worldIn.getWorldType();
		boolean ret = false;
		BlockPos other = pos.offset(facing);
		Block block = worldIn.getBlockState(other).getBlock();
		IBlockState state = worldIn.getBlockState(other);
		IInventory iinventory = null;
		if (canConnectTo(state, facing, worldIn, other)) {
			ret = true;
		} else if (block.hasTileEntity(state)) {
			TileEntity tileentity = worldIn.getTileEntity(other);

			if (block instanceof BlockPipeRedstone || tileentity instanceof IInventory || block instanceof BlockPipe) {
				ret = true;
			}
		}
		

		return ret;

	}
    
    protected static boolean canConnectTo(IBlockState blockState, @Nullable EnumFacing side, IBlockAccess world, BlockPos pos)
    {
    	//System.out.println("checking");
        Block block = blockState.getBlock();
        
        if (block == Blocks.REDSTONE_WIRE)
        {
            return true;
        }
        else if (Blocks.UNPOWERED_REPEATER.isSameDiode(blockState))
        {
            EnumFacing enumfacing = (EnumFacing)blockState.getValue(BlockRedstoneRepeater.FACING);
            return enumfacing == side || enumfacing.getOpposite() == side;
        }
        else if (Blocks.OBSERVER == blockState.getBlock())
        {
            return side == blockState.getValue(BlockObserver.FACING);
        }
        else
        {
            return blockState.getBlock().canConnectRedstone(blockState, world, pos, side);
        }
    }

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { UP, DOWN, NORTH, EAST, SOUTH, WEST, UD, NS, EW, POWERED });
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