package mod.simonsmod.core.objects.tileEntity;

import java.util.List;

import javax.annotation.Nullable;

import mod.simonsmod.core.containers.ContainerHopperDuct;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.objects.blocks.tileentities.BlockPipe;
import mod.simonsmod.core.objects.blocks.tileentities.BlockPipe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class TileEntityPipe extends TileEntityLockableLoot implements IHopper, ITickable
{
    private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
    private int transferCooldown = -1;
    private long tickedGameTime;
    private boolean up;
    private static final int[] slots_Pipe = new int[]{0};
    
    
    public static void registerFixesHopper(DataFixer fixer)
    {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityPipe.class, new String[] {"Items"}));
    }

    
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);

        if (!this.checkLootAndRead(compound))
        {
            ItemStackHelper.loadAllItems(compound, this.inventory);
        }

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
        //this.up = compound.getBoolean("Up");
        this.transferCooldown = compound.getInteger("TransferCooldown");
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        if (!this.checkLootAndWrite(compound))
        {
            ItemStackHelper.saveAllItems(compound, this.inventory);
        }

        compound.setInteger("TransferCooldown", this.transferCooldown);
        //compound.setBoolean("Up", isConnected("up"));
        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.customName);
        }

        return compound;
    }


    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.inventory.size();
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    public ItemStack decrStackSize(int index, int count)
    {
        this.fillWithLoot((EntityPlayer)null);
        ItemStack itemstack = ItemStackHelper.getAndSplit(this.getItems(), index, count);
        return itemstack;
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.fillWithLoot((EntityPlayer)null);
        this.getItems().set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.hopper_duct";
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    public void update()
    {
        if (this.world != null && !this.world.isRemote)
        {
            --this.transferCooldown;
            this.tickedGameTime = this.world.getTotalWorldTime();
            
            if(this.up)
            	System.out.println("up");
            
            if (!this.isOnTransferCooldown())
            {
                this.setTransferCooldown(0);
                //this.updatePipe();
            }
        }
    }
    public boolean isDirectional(EnumFacing facing){
    	IBlockState state = BlockInit.PIPE.getDefaultState();
    	if(facing == EnumFacing.DOWN)
    		if(this.getBlockState() == 
    			state.withProperty(BlockPipe.DOWN, true).withProperty(BlockPipe.UP, false).withProperty(BlockPipe.NORTH, false)
    			.withProperty(BlockPipe.EAST, false).withProperty(BlockPipe.SOUTH, false).withProperty(BlockPipe.WEST, false) ||
    			this.getBlockState() == 
    			state.withProperty(BlockPipe.DOWN, true).withProperty(BlockPipe.UP, true).withProperty(BlockPipe.NORTH, false)
    			.withProperty(BlockPipe.EAST, false).withProperty(BlockPipe.SOUTH, false).withProperty(BlockPipe.WEST, false))
    			return true;
    	if(facing == EnumFacing.UP)
    		if(this.getBlockState() == state.withProperty(BlockPipe.UP, true))
    			return true;
    	if(facing == EnumFacing.NORTH)
    		if(this.getBlockState() == state.withProperty(BlockPipe.NORTH, true))
    			return true;
    	if(facing == EnumFacing.EAST)
    		if(this.getBlockState() == state.withProperty(BlockPipe.EAST, true))
    			return true;
    	if(facing == EnumFacing.SOUTH)
    		if(this.getBlockState() == state.withProperty(BlockPipe.SOUTH, true))
    			return true;
    	if(facing == EnumFacing.WEST)
    		if(this.getBlockState() == state.withProperty(BlockPipe.WEST, true))
    			return true;
    	
    	return false;
    }
    
    
    protected boolean updatePipe()
    {
        if (this.world != null && !this.world.isRemote)
        {
            if (!this.isOnTransferCooldown() )
            {
                boolean flag = false;

                if (!this.isInventoryEmpty())
                {
                	if(isDirectional(EnumFacing.UP))
                		flag = this.transferItemsOut(EnumFacing.UP);
                	if(isDirectional(EnumFacing.DOWN))
                		flag = this.transferItemsOut(EnumFacing.DOWN);
                	if(isDirectional(EnumFacing.NORTH))
                		flag = this.transferItemsOut(EnumFacing.NORTH);
                	if(isDirectional(EnumFacing.EAST))
                		flag = this.transferItemsOut(EnumFacing.EAST);
                	if(isDirectional(EnumFacing.SOUTH))
                		flag = this.transferItemsOut(EnumFacing.SOUTH);
                	if(isDirectional(EnumFacing.WEST))
                		flag = this.transferItemsOut(EnumFacing.WEST);
                }

                
                if (flag)
                {
                    this.setTransferCooldown(8);
                    this.markDirty();
                    return true;
                }
            }

            return false;
        }
        else
        {
            return false;
        }
    }
    public IBlockState getBlockState()
    {
    	return this.world.getBlockState(this.pos);
    }
    
    private boolean transferItemsOut(EnumFacing facing)
    {
    	IBlockState state = BlockInit.PIPE.getDefaultState();
//        if(this.getBlockState() == state.withProperty(BlockPipe.UP, true))

    	
    	
    	IInventory iinventory = this.getInventoryForPipeTransfer(facing);
        
        if (iinventory == null)
        {
            return false;
        }
        else
        {
            EnumFacing enumfacing = BlockPipe.getFacing(this.getBlockMetadata()).getOpposite();

            if (this.isInventoryFull(iinventory, enumfacing))
            {
                return false;
            }
            else
            {
                for (int i = 0; i < this.getSizeInventory(); ++i)
                {
                    if (!this.getStackInSlot(i).isEmpty())
                    {
                        ItemStack itemstack = this.getStackInSlot(i).copy();
                        ItemStack itemstack1 = putStackInInventoryAllSlots(this, iinventory, this.decrStackSize(i, 1), enumfacing);

                        if (itemstack1.isEmpty())
                        {
                            iinventory.markDirty();
                            return true;
                        }

                        this.setInventorySlotContents(i, itemstack);
                    }
                }

                return false;
            }
        }
    }
    /**
     * Attempts to place the passed stack in the inventory, using as many slots as required. Returns leftover items
     */
    public static ItemStack putStackInInventoryAllSlots(IInventory source, IInventory destination, ItemStack stack, @Nullable EnumFacing direction)
    {
        if (destination instanceof ISidedInventory && direction != null)
        {
            ISidedInventory isidedinventory = (ISidedInventory)destination;
            int[] aint = isidedinventory.getSlotsForFace(direction);

            for (int k = 0; k < aint.length && !stack.isEmpty(); ++k)
            {
                stack = insertStack(source, destination, stack, aint[k], direction);
            }
        }
        else
        {
            int i = destination.getSizeInventory();

            for (int j = 0; j < i && !stack.isEmpty(); ++j)
            {
                stack = insertStack(source, destination, stack, j, direction);
            }
        }

        return stack;
    }
    

    /**
     * Insert the specified stack to the specified inventory and return any leftover items
     */
    private static ItemStack insertStack(IInventory source, IInventory destination, ItemStack stack, int index, EnumFacing direction)
    {
        ItemStack itemstack = destination.getStackInSlot(index);

        if (canInsertItemInSlot(destination, stack, index, direction))
        {
            boolean flag = false;
            boolean flag1 = destination.isEmpty();

            if (itemstack.isEmpty())
            {
                destination.setInventorySlotContents(index, stack);
                stack = ItemStack.EMPTY;
                flag = true;
            }
            else if (canCombine(itemstack, stack))
            {
                int i = stack.getMaxStackSize() - itemstack.getCount();
                int j = Math.min(stack.getCount(), i);
                stack.shrink(j);
                itemstack.grow(j);
                flag = j > 0;
            }

            if (flag)
            {
                if (flag1 && destination instanceof TileEntityPipe)
                {
                    TileEntityPipe tileentityhopper1 = (TileEntityPipe)destination;

                    if (!tileentityhopper1.mayTransfer())
                    {
                        int k = 0;

                        if (source != null && source instanceof TileEntityPipe)
                        {
                            TileEntityPipe tileentityhopper = (TileEntityPipe)source;

                            if (tileentityhopper1.tickedGameTime >= tileentityhopper.tickedGameTime)
                            {
                                k = 1;
                            }
                        }

                        tileentityhopper1.setTransferCooldown(8 - k);
                    }
                }

                destination.markDirty();
            }
        }

        return stack;
    }
    
    private static boolean canCombine(ItemStack stack1, ItemStack stack2)
    {
        if (stack1.getItem() != stack2.getItem())
        {
            return false;
        }
        else if (stack1.getMetadata() != stack2.getMetadata())
        {
            return false;
        }
        else if (stack1.getCount() > stack1.getMaxStackSize())
        {
            return false;
        }
        else
        {
            return ItemStack.areItemStackTagsEqual(stack1, stack2);
        }
    }
    
    /**
     * Can this hopper insert the specified item from the specified slot on the specified side?
     */
    private static boolean canInsertItemInSlot(IInventory inventoryIn, ItemStack stack, int index, EnumFacing side)
    {
        if (!inventoryIn.isItemValidForSlot(index, stack))
        {
            return false;
        }
        else
        {
            return !(inventoryIn instanceof ISidedInventory) || ((ISidedInventory)inventoryIn).canInsertItem(index, stack, side);
        }
    }
    
    private IInventory getInventoryForPipeTransfer(EnumFacing facing)
    {
        if(facing == EnumFacing.UP)
        	return getInventoryAtPosition(this.getWorld(), this.getXPos(), this.getYPos() + 1, this.getZPos());
        else if(facing == EnumFacing.DOWN)
        	return getInventoryAtPosition(this.getWorld(), this.getXPos(), this.getYPos() - 1, this.getZPos());
        else if(facing == EnumFacing.NORTH)
        	return getInventoryAtPosition(this.getWorld(), this.getXPos(), this.getYPos(), this.getZPos() - 1);
        else if(facing == EnumFacing.SOUTH)
        	return getInventoryAtPosition(this.getWorld(), this.getXPos(), this.getYPos(), this.getZPos() + 1);
        else if(facing == EnumFacing.EAST)
        	return getInventoryAtPosition(this.getWorld(), this.getXPos() + 1, this.getYPos(), this.getZPos());
        else
        	return getInventoryAtPosition(this.getWorld(), this.getXPos() - 1, this.getYPos() + 1, this.getZPos());
        
    }
    
    /**
     * Returns false if the inventory has any room to place items in
     */
    private boolean isInventoryFull(IInventory inventoryIn, EnumFacing side)
    {
        if (inventoryIn instanceof ISidedInventory)
        {
            ISidedInventory isidedinventory = (ISidedInventory)inventoryIn;
            int[] aint = isidedinventory.getSlotsForFace(side);

            for (int k : aint)
            {
                ItemStack itemstack1 = isidedinventory.getStackInSlot(k);

                if (itemstack1.isEmpty() || itemstack1.getCount() != itemstack1.getMaxStackSize())
                {
                    return false;
                }
            }
        }
        else
        {
            int i = inventoryIn.getSizeInventory();

            for (int j = 0; j < i; ++j)
            {
                ItemStack itemstack = inventoryIn.getStackInSlot(j);

                if (itemstack.isEmpty() || itemstack.getCount() != itemstack.getMaxStackSize())
                {
                    return false;
                }
            }
        }

        return true;
    }
    
    
    /**
     * Returns the IInventory (if applicable) of the TileEntity at the specified position
     */
    public static IInventory getInventoryAtPosition(World worldIn, double x, double y, double z)
    {
        IInventory iinventory = null;
        int i = MathHelper.floor(x);
        int j = MathHelper.floor(y);
        int k = MathHelper.floor(z);
        BlockPos blockpos = new BlockPos(i, j, k);
        net.minecraft.block.state.IBlockState state = worldIn.getBlockState(blockpos);
        Block block = state.getBlock();

        if (block.hasTileEntity(state))
        {
            TileEntity tileentity = worldIn.getTileEntity(blockpos);

            if (tileentity instanceof IInventory)
            {
                iinventory = (IInventory)tileentity;

                if (iinventory instanceof TileEntityChest && block instanceof BlockChest)
                {
                    iinventory = ((BlockChest)block).getContainer(worldIn, blockpos, true);
                }
            }
        }

        if (iinventory == null)
        {
            List<Entity> list = worldIn.getEntitiesInAABBexcluding((Entity)null, new AxisAlignedBB(x - 0.5D, y - 0.5D, z - 0.5D, x + 0.5D, y + 0.5D, z + 0.5D), EntitySelectors.HAS_INVENTORY);

            if (!list.isEmpty())
            {
                iinventory = (IInventory)list.get(worldIn.rand.nextInt(list.size()));
            }
        }

        return iinventory;
    }
    
    
    
    
    private boolean isInventoryEmpty()
    {
        for (ItemStack itemstack : this.inventory)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

    public boolean isEmpty()
    {
        return this.isInventoryEmpty();
    }

    private boolean isFull()
    {
        for (ItemStack itemstack : this.inventory)
        {
            if (itemstack.isEmpty() || itemstack.getCount() != itemstack.getMaxStackSize())
            {
                return false;
            }
        }

        return true;
    }


    /**
     * Gets the world X position for this hopper entity.
     */
    public double getXPos()
    {
        return (double)this.pos.getX() + 0.5D;
    }

    /**
     * Gets the world Y position for this hopper entity.
     */
    public double getYPos()
    {
        return (double)this.pos.getY() + 0.5D;
    }

    /**
     * Gets the world Z position for this hopper entity.
     */
    public double getZPos()
    {
        return (double)this.pos.getZ() + 0.5D;
    }

    public void setTransferCooldown(int ticks)
    {
        this.transferCooldown = ticks;
    }

    private boolean isOnTransferCooldown()
    {
        return this.transferCooldown > 0;
    }

    public boolean mayTransfer()
    {
        return this.transferCooldown > 8;
    }

    public String getGuiID()
    {
        return "minecraft:hopper";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        this.fillWithLoot(playerIn);
        return new ContainerHopperDuct(playerInventory, this, playerIn);
    }

    protected NonNullList<ItemStack> getItems()
    {
        return this.inventory;
    }

    public long getLastUpdateTime() { return tickedGameTime; } // Forge
}