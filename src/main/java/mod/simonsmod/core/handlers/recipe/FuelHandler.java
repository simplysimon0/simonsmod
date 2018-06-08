package mod.simonsmod.core.handlers.recipe;

import mod.simonsmod.core.init.ItemInit;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler{

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.getItem() == ItemInit.TOOL_MORTARPESTLE)
			return 200;
		if(fuel.getItem() == ItemInit.TOOL_HAMMER)
			return 400;
		return 0;
	}

}
