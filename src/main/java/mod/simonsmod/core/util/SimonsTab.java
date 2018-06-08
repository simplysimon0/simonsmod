package mod.simonsmod.core.util;

import mod.simonsmod.core.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SimonsTab extends CreativeTabs {

	public SimonsTab(int par1, String par2Str) {
		super(par1, par2Str);

	}

@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem(){
	// Here you make the Icon of the creative Tab
		this.setBackgroundImageName("simonsmod.png");
		return new ItemStack(ItemInit.TOOL_WRENCH, 1, 0);
	}
	public String getTranslatedTabLabel(){
		// Here the Name
		return "Simon's Mod";
	}

}