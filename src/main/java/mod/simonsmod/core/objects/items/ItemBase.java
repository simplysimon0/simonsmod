package mod.simonsmod.core.objects.items;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {

	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(SimonsMod.SIMONSTAB);

		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		SimonsMod.proxy.registerItemRenderer(this, 0, "inventory");

	}

}
