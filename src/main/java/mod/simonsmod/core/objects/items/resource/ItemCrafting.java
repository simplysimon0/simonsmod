package mod.simonsmod.core.objects.items.resource;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.handlers.IMetaName;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCrafting extends Item implements IHasModel {
														//0     1		2			3	    	4		

	public static String[] en_USVanilla = new String[] {"Chalk_Dust","Rubber"};
	//public static String[] en_USPTable = new String[]  {"Cu",	 "Sn", "Ag",     "Pb", "Al",	  "Ni",	   "Pt",	  "Ir",		"Os",	 "W",	     "Co"};
	public String name;
	
	public String[] Names()
	{
		String[] arrayName = new String[300];
		//ArrayList<String> name = new ArrayList<String>(300);
		
		//VANILLA DUSTS
		//0-5
		for(int i = 0; i < en_USVanilla.length; i++)
			arrayName[i] = en_USVanilla[i];
		return arrayName;
	}

	public ItemCrafting(String name) {
		this.setHasSubtypes(true);
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(SimonsMod.SIMONSTAB);
		this.name = name;
		ItemInit.ITEMS.add(this);
	}
	
	public void addInformation(ItemStack stack, World world, List<String> info, ITooltipFlag b) {
		super.addInformation(stack, world, info, b);
		int meta = stack.getMetadata();
		//info.add(en_USPTable[meta]);
	}
	public int getVariants() {
		return en_USVanilla.length;
	}

	public String getUnlocalizedName(ItemStack item) {
		if(Names()[item.getItemDamage()] == null)
			return null;
		return "item." + Names()[item.getItemDamage()].toLowerCase();
	}

	/*
	 * @Override public void getSubItems(CreativeTabs tab,
	 * NonNullList<ItemStack> items) { for(int i = 0; i < en_USNames.length;
	 * i++) { items.add(new ItemStack(this, 1, i)); } }
	 */
	//HERE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			
			for (int i = 0; i < en_USVanilla.length; i++) {
				items.add(new ItemStack(this, 1, i));
			}
		}
	}
	
	
	
	//HERE!!!!!!!!!!!!!!!!!!!!!!!!!!


	@Override
	public void registerModels() {
		for (int i = 0; i < Names().length; i++) {
			SimonsMod.proxy.registerVariantRenderer(this, i, "crafting/" + Names()[i], "inventory");
		}
	}

}
