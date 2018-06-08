package mod.simonsmod.core.handlers.recipe;

import mod.simonsmod.core.init.ItemInit;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class CraftingHandler {

	public void onCrafting(ItemCraftedEvent event) {
		final IInventory craftMatrix = null;
		for (int i = 0; i < event.craftMatrix.getSizeInventory(); i++) {
			if (event.craftMatrix.getStackInSlot(i) != null) {
				ItemStack itemHammer = event.craftMatrix.getStackInSlot(i);
				if (itemHammer != null && itemHammer.getItem() == ItemInit.TOOL_HAMMER) {
					ItemStack k = new ItemStack(ItemInit.TOOL_HAMMER, 2, (itemHammer.getItemDamage() + 1));

					if (k.getItemDamage() >= k.getMaxDamage()) {
						k.setCount(0);
					}
					event.craftMatrix.setInventorySlotContents(i, k);
				}
			}
		}
	}
}
