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

public class ItemMetals2 extends Item implements IHasModel {
														//0     1		2			3	    	4		

	public static String[] en_USPlateVanilla = new String[] {"Iron","Gold","Lapis","Obsidian"};
	public static String[] en_USGearVanilla = new String[] {"Iron","Gold","Diamond"};
	public static String[] en_USNuggetVanilla = new String[] {"Diamond", "Emerald"};
	public static String[] en_USDustVanilla = new String[] {"Iron","Gold","Diamond","Coal","Lapis","Obsidian"};
	//public static String[] en_USVPTable = new String[] {"Fe","Ag","C","C","",""};
	public static String[] en_USMetals = new String[]{"Copper","Tin","Silver","Lead","Aluminum","Nickel","Zinc","Platinum","Iridium","Osmium","Tungsten","Cobalt","Chromium","Silicon","Uranium","Ardite"};
	public static String[] en_USAlloys = new String[]{"Bronze","Steel","Electrum","Invar","Constantan","Brass","ToolSteel"};
	//public static String[] en_USPTable = new String[]  {"Cu",	 "Sn", "Ag",     "Pb", "Al",	  "Ni",	   "Pt",	  "Ir",		"Os",	 "W",	     "Co"};
	public String name;
	
	public String[] Names()
	{
		String[] arrayName = new String[300];
		//ArrayList<String> name = new ArrayList<String>(300);
		
		//VANILLA DUSTS
		//0-5
		for(int i = 0; i < en_USDustVanilla.length; i++)
			arrayName[i] = "dust_"+en_USDustVanilla[i];
		
		//VANILLA NUGGETS
		//6-7
		for(int i = 0; i < en_USNuggetVanilla.length; i++)
			arrayName[i + 6] = "nugget_"+en_USNuggetVanilla[i];
		
		//VANILLA GEARS
		//8-10
		for(int i = 0; i < en_USGearVanilla.length; i++)
			arrayName[i + 8] = "gear_"+en_USGearVanilla[i];
		
		//VANILLA PLATES
		//11-14
		for(int i = 0; i < en_USPlateVanilla.length; i++)
			arrayName[i + 11]= "plate_"+en_USPlateVanilla[i];
		
		//INGOTS
		//25-49
		for(int i = 0; i < en_USMetals.length; i++)
			arrayName[i + 25]= "ingot_"+en_USMetals[i];
		
		//ALLOY INGOTS
		//50-74
		for(int i = 0; i < en_USAlloys.length; i++)
			arrayName[i + 50]= "ingot_"+en_USAlloys[i];
		
		//DUSTS
		//75-99
		for(int i = 0; i < en_USMetals.length; i++)
			arrayName[i + 75]= "dust_"+en_USMetals[i];
		
		//ALLOY DUSTS
		//100-24
		for(int i = 0; i < en_USAlloys.length; i++)
			arrayName[i + 100]= "dust_"+en_USAlloys[i];
		
		//NUGGETS
		//125-149
		for(int i = 0; i < en_USMetals.length; i++)
			arrayName[i + 125]= "nugget_"+en_USMetals[i];
		
		//ALLOY NUGGETS
		//150-174
		for(int i = 0; i < en_USAlloys.length; i++)
			arrayName[i + 150]= "nugget_"+en_USAlloys[i];
		
		//GEARS
		//175-199
		for(int i = 0; i < en_USMetals.length; i++)
			arrayName[i +175]= "gear_"+en_USMetals[i];
		
		//ALLOY GEARS
		//200-224
		for(int i = 0; i < en_USAlloys.length; i++)
			arrayName[i + 200]= "gear_"+en_USAlloys[i];
		
		//PLATES
		//225-249
		for(int i = 0; i < en_USMetals.length; i++)
			arrayName[i +225]= "plate_"+en_USMetals[i];
		
		//ALLOY PLATES
		//250-274
		for(int i = 0; i < en_USAlloys.length; i++)
			arrayName[i + 250]= "plate_"+en_USAlloys[i];
		
		return arrayName;
	}

	public ItemMetals2(String name) {
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
		return en_USPlateVanilla.length+
				en_USGearVanilla.length+
				en_USNuggetVanilla.length+
				en_USDustVanilla.length+
				en_USMetals.length+
				en_USAlloys.length;
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
			
			for (int i = 0; i < en_USDustVanilla.length; i++) {
				items.add(new ItemStack(this, 1, i));
			}
			for (int i = 0; i < en_USNuggetVanilla.length; i++) {
				items.add(new ItemStack(this, 1, i+6));
			}
			for (int i = 0; i < en_USGearVanilla.length; i++) {
				items.add(new ItemStack(this, 1, i+8));
			}
			for (int i = 0; i < en_USPlateVanilla.length; i++) {
				items.add(new ItemStack(this, 1, i+11));
			}
			for (int i = 0; i < en_USMetals.length; i++) {
				items.add(new ItemStack(this, 1, i+25));
			}
			for (int i = 0; i < en_USAlloys.length; i++) {
				items.add(new ItemStack(this, 1, i+50));
			}
			for (int i = 0; i < en_USMetals.length; i++) {
				items.add(new ItemStack(this, 1, i+75));
			}
			for (int i = 0; i < en_USAlloys.length; i++) {
				items.add(new ItemStack(this, 1, i+100));
			}
			for (int i = 0; i < en_USMetals.length; i++) {
				items.add(new ItemStack(this, 1, i+125));
			}
			for (int i = 0; i < en_USAlloys.length; i++) {
				items.add(new ItemStack(this, 1, i+150));
			}
			for (int i = 0; i < en_USMetals.length; i++) {
				items.add(new ItemStack(this, 1, i+175));
			}
			for (int i = 0; i < en_USAlloys.length; i++) {
				items.add(new ItemStack(this, 1, i+200));
			}
			for (int i = 0; i < en_USMetals.length; i++) {
				items.add(new ItemStack(this, 1, i+225));
			}
			for (int i = 0; i < en_USAlloys.length; i++) {
				items.add(new ItemStack(this, 1, i+250));
			}
		}
	}
	
	
	
	
	
	
	
	//HERE!!!!!!!!!!!!!!!!!!!!!!!!!!


	@Override
	public void registerModels() {
		for (int i = 0; i < en_USDustVanilla.length; i++) {
			SimonsMod.proxy.registerVariantRenderer(this, i, "material/vanilla" + en_USDustVanilla[i], "inventory");
		}
		for (int i = 0; i < en_USNuggetVanilla.length; i++) {
			SimonsMod.proxy.registerVariantRenderer(this, i, "material/vanilla" + en_USNuggetVanilla[i], "inventory");
		}
		for (int i = 0; i < en_USGearVanilla.length; i++) {
			SimonsMod.proxy.registerVariantRenderer(this, i, "material/vanilla" + en_USGearVanilla[i], "inventory");
		}
		for (int i = 0; i < en_USPlateVanilla.length; i++) {
			SimonsMod.proxy.registerVariantRenderer(this, i, "material/vanilla" + en_USPlateVanilla[i], "inventory");
		}
		for (int i = 0; i < en_USMetals.length; i++) {
			SimonsMod.proxy.registerVariantRenderer(this, i, "material/" + en_USMetals[i], "inventory");
		}
		for (int i = 0; i < en_USAlloys.length; i++) {
			SimonsMod.proxy.registerVariantRenderer(this, i, "material/" + en_USAlloys[i], "inventory");
		}
		for (int i = 0; i < en_USMetals.length; i++) {
			SimonsMod.proxy.registerVariantRenderer(this, i, "material/" + en_USMetals[i], "inventory");
		}
		for (int i = 0; i < en_USAlloys.length; i++) {
			SimonsMod.proxy.registerVariantRenderer(this, i, "material/" + en_USAlloys[i], "inventory");
		}
		for (int i = 0; i < en_USMetals.length; i++) {
			SimonsMod.proxy.registerVariantRenderer(this, i, "material/" + en_USMetals[i], "inventory");
		}
		for (int i = 0; i < en_USAlloys.length; i++) {
			SimonsMod.proxy.registerVariantRenderer(this, i, "material/" + en_USAlloys[i], "inventory");
		}
		for (int i = 0; i < en_USMetals.length; i++) {
			SimonsMod.proxy.registerVariantRenderer(this, i, "material/" + en_USMetals[i], "inventory");
		}
		for (int i = 0; i < en_USAlloys.length; i++) {
			SimonsMod.proxy.registerVariantRenderer(this, i, "material/" + en_USAlloys[i], "inventory");
		}
		for (int i = 0; i < en_USMetals.length; i++) {
			SimonsMod.proxy.registerVariantRenderer(this, i, "material/" + en_USMetals[i], "inventory");
		}
		for (int i = 0; i < en_USAlloys.length; i++) {
			SimonsMod.proxy.registerVariantRenderer(this, i, "material/" + en_USAlloys[i], "inventory");
		}
		for (int i = 0; i < Names().length; i++) {
			SimonsMod.proxy.registerVariantRenderer(this, i, "material/" + Names()[i], "inventory");
		}
	}

}
