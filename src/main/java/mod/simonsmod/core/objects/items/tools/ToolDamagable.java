package mod.simonsmod.core.objects.items.tools;

import java.util.List;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ToolDamagable extends Item implements IHasModel{


    public ToolDamagable(String name, int uses)
    {

        this.maxStackSize = 1;
        this.setMaxDamage(uses);
        setUnlocalizedName(name);
		setRegistryName(name);
		canRepair = false;
		setCreativeTab(SimonsMod.SIMONSTAB);
		
		ItemInit.ITEMS.add(this);
    }
  //ToolTip
    public void func_77624_a(ItemStack stack, World world, List<String> info, ITooltipFlag b) {
		info.add("Uses Left: "+ (stack.getMaxDamage() - stack.getItemDamage()));
	}

	@Override
	public void registerModels() 
	{
		SimonsMod.proxy.registerItemRenderer(this, 0, "inventory");
		
	}
	@Override
	public boolean hasContainerItem(ItemStack stack)
	{
		return true;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		//GameRegistry.addShapelessRecipe(name, group, output, params);
		return itemStack.getItemDamage() < itemStack.getMaxDamage() ? new ItemStack(itemStack.getItem(), 1, itemStack.getItemDamage() + 1) : ItemStack.EMPTY;
	}
	



}
