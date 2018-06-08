package mod.simonsmod.core.containers.slots;

import mod.simonsmod.core.objects.tileEntity.TileEntityTest;
import mod.simonsmod.core.objects.tileEntity.TileEntityWorkTable;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotWorkTableTool extends Slot
{
	public SlotWorkTableTool(IInventory inventory, int index, int x, int y) 
	{
		super(inventory, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return TileEntityWorkTable.isItemTool(stack);
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) 
	{
		return super.getItemStackLimit(stack);
	}
}