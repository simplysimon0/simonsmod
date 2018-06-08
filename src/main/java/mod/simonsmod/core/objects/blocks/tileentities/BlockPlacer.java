package mod.simonsmod.core.objects.blocks.tileentities;

import java.util.List;
import java.util.Random;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.handlers.GuiHandler;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.objects.items.ItemCrop;
import mod.simonsmod.core.objects.items.ItemSeed;
import mod.simonsmod.core.objects.tileEntity.TileEntityPlacer;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPlacer extends BlockRedstoneActivated implements IHasModel, IRedstoneActivated 
{
    protected Random rand = new Random();

	public BlockPlacer(String name) {
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
			info.add("Places blocks from its inventory");
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
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityPlacer)
            {
            	playerIn.openGui(SimonsMod.instance, GuiHandler.PLACER, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }

            return true;
        }
    }

    public void onTriggered(World worldIn, BlockPos pos)
    {
    	BlockSourceImpl blocksourceimpl = new BlockSourceImpl(worldIn, pos);
		TileEntityPlacer TileEntityPlacer = (TileEntityPlacer) blocksourceimpl.getBlockTileEntity();

		if (TileEntityPlacer != null) {

			int i = TileEntityPlacer.getPlaceSlot();

			if (i < 0) {
				worldIn.playEvent(1001, pos, 0);
			} else {
				ItemStack itemstack = TileEntityPlacer.getStackInSlot(i);

				if (!itemstack.isEmpty()) {
					EnumFacing enumfacing = (EnumFacing) worldIn.getBlockState(pos).getValue(FACING);
					BlockPos blockpos = pos.offset(enumfacing);
					IBlockState state = worldIn.getBlockState(blockpos);
					if (itemstack.getItem() instanceof ItemBlock && state == Blocks.AIR.getDefaultState()) {
						IBlockState newState = Block.getBlockFromItem(itemstack.getItem()).getStateFromMeta(itemstack.getMetadata());
						worldIn.setBlockState(blockpos, newState);
						itemstack.shrink(1);
						TileEntityPlacer.setInventorySlotContents(i, itemstack);
					}
					else if(itemstack.getItem() instanceof ItemSeed && state == Blocks.AIR.getDefaultState())
					{
						Item item = itemstack.getItem();
						IBlockState crop = ((ItemSeed) item).getPlant(worldIn, blockpos);
						worldIn.setBlockState(blockpos, crop);
						itemstack.shrink(1);
						TileEntityPlacer.setInventorySlotContents(i, itemstack);
					}
					else if(itemstack.getItem() instanceof ItemSeedFood && state == Blocks.AIR.getDefaultState())
					{
						Item item = itemstack.getItem();
						IBlockState crop = ((ItemSeedFood) item).getPlant(worldIn, blockpos);
						worldIn.setBlockState(blockpos, crop);
						itemstack.shrink(1);
						TileEntityPlacer.setInventorySlotContents(i, itemstack);
					}
					else if(itemstack.getItem() instanceof ItemSeeds && state == Blocks.AIR.getDefaultState())
					{
						Item item = itemstack.getItem();
						IBlockState crop = ((ItemSeeds) item).getPlant(worldIn, blockpos);
						worldIn.setBlockState(blockpos, crop);
						itemstack.shrink(1);
						TileEntityPlacer.setInventorySlotContents(i, itemstack);
					}
					else if(itemstack.getItem() instanceof ItemCrop && state == Blocks.AIR.getDefaultState())
					{
						Item item = itemstack.getItem();
						IBlockState crop = ((ItemCrop) item).getPlant(worldIn, blockpos);
						worldIn.setBlockState(blockpos, crop);
						itemstack.shrink(1);
						TileEntityPlacer.setInventorySlotContents(i, itemstack);
					}
				}
			}
		}
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            this.onTriggered(worldIn, pos);
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityPlacer();
    }
    
    @Override
    public void getName(World worldIn, BlockPos pos, ItemStack stack)
    {
    	if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityPlacer)
            {
                ((TileEntityPlacer)tileentity).setCustomName(stack.getDisplayName());
            }
        }
    }

    /**
     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
     */
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEntityPlacer)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityPlacer)tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }

        super.breakBlock(worldIn, pos, state);
    }
}