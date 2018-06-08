package mod.simonsmod.core.handlers;

import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.objects.blocks.BlockMetal;
import mod.simonsmod.core.objects.blocks.BlockTile;
import mod.simonsmod.core.objects.items.resource.ItemResource;
import mod.simonsmod.core.objects.simonsEnums.EnumColor;
import mod.simonsmod.core.objects.simonsEnums.EnumMetal;
import mod.simonsmod.core.objects.simonsEnums.EnumMetal;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHandler {

	
	public static void registerOreDictionary()
	{
		
		
		//OreDictionary.registerOre("plateMachineMetal", new ItemStack(ItemInit.MATERIAL, 1, 11));
		//OreDictionary.registerOre("plateMachineMetal", new ItemStack(ItemInit.MATERIAL, 1, 229));
		String[] metal = ItemResource.en_USMetals;
		for(int i = 0; i < metal.length; i++)
		{
			OreDictionary.registerOre("ingot"	+ metal[i], new ItemStack(ItemInit.MATERIAL, 1, i+25));
			OreDictionary.registerOre("dust" 	+ metal[i], new ItemStack(ItemInit.MATERIAL, 1, i+75));
			OreDictionary.registerOre("nugget"	+metal[i], new ItemStack(ItemInit.MATERIAL, 1, i+125));
			OreDictionary.registerOre("gear"	+  metal[i], new ItemStack(ItemInit.MATERIAL, 1, i+175));
			OreDictionary.registerOre("plate"	+ metal[i], new ItemStack(ItemInit.MATERIAL, 1, i+225));
			OreDictionary.registerOre("ore"		+metal[i], new ItemStack(BlockInit.BLOCK_METAL_ORE, 1, i));
			OreDictionary.registerOre("block"	+metal[i], new ItemStack(BlockInit.BLOCK_METAL, 1, i));
			
		}
		String[] alloy = ItemResource.en_USAlloys;
		for(int i = 0; i < alloy.length; i++)
		{
			OreDictionary.registerOre("ingot"	+ alloy[i], new ItemStack(ItemInit.MATERIAL, 1, i+50));
			OreDictionary.registerOre("dust" 	+ alloy[i], new ItemStack(ItemInit.MATERIAL, 1, i+100));
			OreDictionary.registerOre("nugget"	+ alloy[i], new ItemStack(ItemInit.MATERIAL, 1, i+150));
			OreDictionary.registerOre("gear"	+ alloy[i], new ItemStack(ItemInit.MATERIAL, 1, i+200));
			OreDictionary.registerOre("plate"	+ alloy[i], new ItemStack(ItemInit.MATERIAL, 1, i+250));
			OreDictionary.registerOre("block	"+alloy[i], new ItemStack(BlockInit.BLOCK_ALLOY, 1, i));
		}
		
		OreDictionary.registerOre("dustIron", new ItemStack(ItemInit.MATERIAL, 1, 0));
		OreDictionary.registerOre("dustGold", new ItemStack(ItemInit.MATERIAL, 1, 1));
		OreDictionary.registerOre("dustDiamond", new ItemStack(ItemInit.MATERIAL, 1, 2));
		OreDictionary.registerOre("dustCoal", new ItemStack(ItemInit.MATERIAL, 1, 3));
		OreDictionary.registerOre("dustLapis", new ItemStack(ItemInit.MATERIAL, 1, 4));
		OreDictionary.registerOre("dustObsidian", new ItemStack(ItemInit.MATERIAL, 1, 5));
		
		OreDictionary.registerOre("nuggetDiamond", new ItemStack(ItemInit.MATERIAL, 1, 6));
		OreDictionary.registerOre("nuggetEmerald", new ItemStack(ItemInit.MATERIAL, 1, 7));
		
		OreDictionary.registerOre("gearIron", new ItemStack(ItemInit.MATERIAL, 1, 8));
		OreDictionary.registerOre("gearGold", new ItemStack(ItemInit.MATERIAL, 1, 9));
		OreDictionary.registerOre("gearDiamond", new ItemStack(ItemInit.MATERIAL, 1, 10));
		
		OreDictionary.registerOre("plateIron", new ItemStack(ItemInit.MATERIAL, 1, 11));
		OreDictionary.registerOre("plateGold", new ItemStack(ItemInit.MATERIAL, 1, 12));
		OreDictionary.registerOre("plateLapis", new ItemStack(ItemInit.MATERIAL, 1, 13));
		OreDictionary.registerOre("plateObsidian", new ItemStack(ItemInit.MATERIAL, 1, 14));

		OreDictionary.registerOre("stoneMarble", new ItemStack(BlockInit.BLOCK_MARBLE, 1, 0));
		OreDictionary.registerOre("stoneBasalt", new ItemStack(BlockInit.BLOCK_BASALT, 1, 0));
		OreDictionary.registerOre("stoneGneiss", new ItemStack(BlockInit.BLOCK_GNEISS, 1, 0));
		
		//OreDictionary.registerOre("craftingHammer", new ItemStack(ItemInit.TOOL_HAMMER, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("craftingToolForgeHammer", new ItemStack(ItemInit.TOOL_HAMMER, 1, OreDictionary.WILDCARD_VALUE));
		//OreDictionary.registerOre("craftingToolForgeHammer", new ItemStack(ItemInit.TOOL_HAMMER, 1, 1));
		OreDictionary.registerOre("craftingToolMortarPestle", new ItemStack(ItemInit.TOOL_MORTARPESTLE, 1, OreDictionary.WILDCARD_VALUE));
		
		OreDictionary.registerOre("cobblestone", new ItemStack(BlockInit.BLOCK_MARBLE, 1, 1));
		OreDictionary.registerOre("cobblestone", new ItemStack(BlockInit.BLOCK_BASALT, 1, 1));
		OreDictionary.registerOre("cobblestone", new ItemStack(BlockInit.BLOCK_GNEISS, 1, 1));
		OreDictionary.registerOre("cobblestone", new ItemStack(BlockInit.BLOCK_ANDESITE, 1, 1));
		OreDictionary.registerOre("cobblestone", new ItemStack(BlockInit.BLOCK_GRANITE, 1, 1));
		OreDictionary.registerOre("cobblestone", new ItemStack(BlockInit.BLOCK_DIORITE, 1, 1));
		
		OreDictionary.registerOre("stone", new ItemStack(BlockInit.BLOCK_MARBLE, 1, 0));
		OreDictionary.registerOre("stone", new ItemStack(BlockInit.BLOCK_BASALT, 1, 0));
		OreDictionary.registerOre("stone", new ItemStack(BlockInit.BLOCK_GNEISS, 1, 0));
		OreDictionary.registerOre("stone", new ItemStack(BlockInit.BLOCK_ANDESITE, 1, 0));
		OreDictionary.registerOre("stone", new ItemStack(BlockInit.BLOCK_GRANITE, 1, 0));
		OreDictionary.registerOre("stone", new ItemStack(BlockInit.BLOCK_DIORITE, 1, 0));
		
		OreDictionary.registerOre("sand", new ItemStack(BlockInit.BLOCK_SOIL, 1, 0));
		
		OreDictionary.registerOre("rubber", new ItemStack(ItemInit.CRAFTING, 1, 1));
	}
	
	
	
}
