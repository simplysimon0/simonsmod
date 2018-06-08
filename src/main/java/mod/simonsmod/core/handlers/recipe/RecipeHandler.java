package mod.simonsmod.core.handlers.recipe;

import mod.simonsmod.core.Reference;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.objects.items.resource.ItemResource;
import mod.simonsmod.core.objects.simonsEnums.EnumMetal;
import mod.simonsmod.core.objects.simonsEnums.EnumAlloy;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeHandler extends Ingredient {

	public static void registerFurnaceRecipes() {
		/*
		Example Smelting Recipe
		
		GameRegistry.addSmelting(new ItemStack(BlockInit.EXAMPLE_INPUT, [How Many], [MetaData]),
				new ItemStack(BlockInit.EXAMPLE_OUTPUT, [How Many], [MetaData]), [XP]);
		*/
		GameRegistry.addSmelting(new ItemStack(BlockInit.BLOCK_MARBLE, 1, 1),
				new ItemStack(BlockInit.BLOCK_MARBLE, 1, 0), 0.1f);
		GameRegistry.addSmelting(new ItemStack(BlockInit.BLOCK_BASALT, 1, 1),
				new ItemStack(BlockInit.BLOCK_BASALT, 1, 0), 0.1f);
		GameRegistry.addSmelting(new ItemStack(BlockInit.BLOCK_GNEISS, 1, 1),
				new ItemStack(BlockInit.BLOCK_GNEISS, 1, 0), 0.1f);
		GameRegistry.addSmelting(new ItemStack(BlockInit.BLOCK_ANDESITE, 1, 1),
				new ItemStack(BlockInit.BLOCK_ANDESITE, 1, 0), 0.1f);
		GameRegistry.addSmelting(new ItemStack(BlockInit.BLOCK_DIORITE, 1, 1),
				new ItemStack(BlockInit.BLOCK_DIORITE, 1, 0), 0.1f);
		GameRegistry.addSmelting(new ItemStack(BlockInit.BLOCK_GRANITE, 1, 1),
				new ItemStack(BlockInit.BLOCK_GRANITE, 1, 0), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemInit.CLAY_CERAMIC, 1, 0), new ItemStack(ItemInit.TILE_CERAMIC, 1, 0),
				0.1f);
		

		GameRegistry.addSmelting(new ItemStack(ItemInit.MATERIAL, 1, 0), new ItemStack(Items.IRON_INGOT, 1, 0), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemInit.MATERIAL, 1, 1), new ItemStack(Items.GOLD_INGOT, 1, 0), 0.1f);
		//GameRegistry.addSmelting(new ItemStack(ItemInit.MATERIAL, 1, 2), new ItemStack(Items.DIAMOND, 1, 0), 0.1f);
		
		//Adds all the smelting recipes for ores and dusts
		for(int i = 0; i != ItemResource.en_USMetals.length; i++)
		{
			GameRegistry.addSmelting(new ItemStack(ItemInit.MATERIAL, 1, i+75), new ItemStack(ItemInit.MATERIAL, 1, i+25), 0.1f);
		}
		
		for(int i = 0; i != EnumMetal.values().length; i++)
		{
			GameRegistry.addSmelting(new ItemStack(BlockInit.BLOCK_METAL_ORE, 1, i), new ItemStack(ItemInit.MATERIAL, 1, i+25),
				0.1f);
		}
		
		for(int i = 0; i != ItemResource.en_USAlloys.length; i++)
		{
			GameRegistry.addSmelting(new ItemStack(ItemInit.MATERIAL, 1, i+100), new ItemStack(ItemInit.MATERIAL, 1, i+50), 0.1f);
		}
		String[] ingots = ItemResource.en_USMetals;
		
		
		GameRegistry.addSmelting(new ItemStack(BlockInit.BLOCK_SOIL, 1, 0), new ItemStack(Blocks.GLASS, 1, 0), 0.1f);
	}

	public static void registerCraftingRecipes() {
		String[] ingots = ItemResource.en_USMetals;
		String[] alloys = ItemResource.en_USAlloys;
		/*
		SHAPELESS RECIPE EXAMPLE
		
		AddShapelessRecipeNew("recipeName", new ItemStack(ItemInit.OUTPUT_ITEM, [NUMBER OF ITEMS], [META DATA]),
				new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.INPUT_ITEM_1, 1, [META DATA])),
						Ingredient.fromStacks(new ItemStack(ItemInit.INPUT_ITEM_2, 1, [META DATA])) });
		
		SHAPED RECIPE EXAMPLE
		
		AddShapedRecipeNew("recipeName", new ItemStack(ItemInit.OUTPUT_ITEM, [NUMBER OF ITEMS], [META DATA]), new Object[] { 
		"AAA",  THIS SIMULATES A CRAFTING GRID, IT CAN BE SHRUNK ACCORDINGLY 
		"A#A",
		"AAA",
		'#', new ItemStack(ItemInit.TOOL_MORTARPESTLE, 1, OreDictionary.WILDCARD_VALUE),
		'A', "oreDictonary values are also accepted" });
		*/
		
		//All the alloy Recipes
		AddShapedRecipeNew("craftdustAlloyBronze", new ItemStack(ItemInit.MATERIAL, 4, 100),
				new Object[] { 
						"AA",
						"AB",
						'A',
						"dustCopper",
						'B',
						"dustTin"
						});
		AddShapedRecipeNew("craftdustAlloySteel", new ItemStack(ItemInit.MATERIAL, 2, 101),
				new Object[] { 
						"AB",
						'A',
						"dustIron",
						'B',
						"dustCoal"
						});
		AddShapedRecipeNew("craftdustAlloyElectrum", new ItemStack(ItemInit.MATERIAL, 2, 102),
				new Object[] { 
						"AB",
						'A',
						"dustGold",
						'B',
						"dustSilver"
						});
		AddShapedRecipeNew("craftdustAlloyInvar", new ItemStack(ItemInit.MATERIAL, 3, 103),
				new Object[] { 
						"AA",
						"B ",
						'A',
						"dustIron",
						'B',
						"dustNickel"
						});
		AddShapedRecipeNew("craftdustAlloyConstantan", new ItemStack(ItemInit.MATERIAL, 2, 104),
				new Object[] { 
						"AB",
						'A',
						"dustCopper",
						'B',
						"dustNickel"
						});
		AddShapedRecipeNew("craftdustAlloyBrass", new ItemStack(ItemInit.MATERIAL, 2, 105),
				new Object[] { 
						"AB",
						'A',
						"dustCopper",
						'B',
						"dustZinc"
						});
		AddShapedRecipeNew("craftdustAlloyToolSteel", new ItemStack(ItemInit.MATERIAL, 2, 106),
				new Object[] { 
						"AB",
						'A',
						"dustSteel",
						'B',
						"dustTungsten"
						});
		AddShapedRecipeNew("craftdustAlloyManyullyn", new ItemStack(ItemInit.MATERIAL, 2, 107),
				new Object[] { 
						"AB",
						'A',
						"dustCobalt",
						'B',
						"dustArdite"
						});
		
		AddShapedRecipeNew("gravelMP", new ItemStack(Blocks.GRAVEL, 1, 0), new Object[] { "A#", '#',
				new ItemStack(ItemInit.TOOL_MORTARPESTLE, 1, OreDictionary.WILDCARD_VALUE), 'A', "cobblestone" });
		
		AddShapedRecipeNew("sandMP", new ItemStack(Blocks.SAND, 1, 0), new Object[] { "A#", '#',
				new ItemStack(ItemInit.TOOL_MORTARPESTLE, 1, OreDictionary.WILDCARD_VALUE), 'A', "gravel" });
		
		AddShapelessRecipeNew("bonemealMP", new ItemStack(Items.DYE, 4, 15),
				new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.BONE, 1, 0)),
						Ingredient.fromStacks(new ItemStack(ItemInit.TOOL_MORTARPESTLE, 1, OreDictionary.WILDCARD_VALUE)) });
		
		
		//Adds all the Vanilla Plate and Dust recipes
		AddShapelessRecipeNew("plateiron", new ItemStack(ItemInit.MATERIAL, 1, 11),
				new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.IRON_INGOT, 1, 0)),
						Ingredient.fromStacks(new ItemStack(ItemInit.TOOL_HAMMER, 1, OreDictionary.WILDCARD_VALUE)) });
		
		AddShapelessRecipeNew("plategold", new ItemStack(ItemInit.MATERIAL, 1, 12),
				new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.GOLD_INGOT, 1, 0)),
						Ingredient.fromStacks(new ItemStack(ItemInit.TOOL_HAMMER, 1, OreDictionary.WILDCARD_VALUE)) });
		
		AddShapelessRecipeNew("platelapis", new ItemStack(ItemInit.MATERIAL, 1, 13),
				new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.DYE, 1, 4)),
						Ingredient.fromStacks(new ItemStack(ItemInit.TOOL_HAMMER, 1, OreDictionary.WILDCARD_VALUE)) });
		
		AddShapelessRecipeNew("plateobsidian", new ItemStack(ItemInit.MATERIAL, 1, 14),
				new Ingredient[] { Ingredient.fromStacks(new ItemStack(Blocks.OBSIDIAN, 1, 0)),
						Ingredient.fromStacks(new ItemStack(ItemInit.TOOL_HAMMER, 1, OreDictionary.WILDCARD_VALUE)) });
		
		AddShapedRecipeNew("oreDustIron", new ItemStack(ItemInit.MATERIAL, 2, 0), new Object[] { "A#", '#',
				new ItemStack(ItemInit.TOOL_MORTARPESTLE, 1, OreDictionary.WILDCARD_VALUE), 'A', "oreIron" });
		AddShapedRecipeNew("oreDustGold", new ItemStack(ItemInit.MATERIAL, 2, 1), new Object[] { "A#", '#',
				new ItemStack(ItemInit.TOOL_MORTARPESTLE, 1, OreDictionary.WILDCARD_VALUE), 'A', "oreGold" });
		
		AddShapedRecipeNew("dustIron", new ItemStack(ItemInit.MATERIAL, 1, 0), new Object[] { "A#", '#',
				new ItemStack(ItemInit.TOOL_MORTARPESTLE, 1, OreDictionary.WILDCARD_VALUE), 'A', "ingotIron" });
		AddShapedRecipeNew("dustGold", new ItemStack(ItemInit.MATERIAL, 1, 1), new Object[] { "A#", '#',
				new ItemStack(ItemInit.TOOL_MORTARPESTLE, 1, OreDictionary.WILDCARD_VALUE), 'A', "ingotGold" });
		AddShapedRecipeNew("dustDiamond", new ItemStack(ItemInit.MATERIAL, 1, 2), new Object[] { "A#", '#',
				new ItemStack(ItemInit.TOOL_MORTARPESTLE, 1, OreDictionary.WILDCARD_VALUE), 'A', "gemDiamond" });
		AddShapedRecipeNew("dustCoal", new ItemStack(ItemInit.MATERIAL, 1, 3),
				new Object[] { "A#", '#', new ItemStack(ItemInit.TOOL_MORTARPESTLE, 1, OreDictionary.WILDCARD_VALUE),
						'A', new ItemStack(Items.COAL, 1, 0) });
		AddShapedRecipeNew("dustLapis", new ItemStack(ItemInit.MATERIAL, 1, 4),
				new Object[] { "A#", '#', new ItemStack(ItemInit.TOOL_MORTARPESTLE, 1, OreDictionary.WILDCARD_VALUE),
						'A', new ItemStack(Items.DYE, 1, 4) });
		AddShapedRecipeNew("dustObsidian", new ItemStack(ItemInit.MATERIAL, 1, 5),
				new Object[] { "A#", '#', new ItemStack(ItemInit.TOOL_MORTARPESTLE, 1, OreDictionary.WILDCARD_VALUE),
						'A', new ItemStack(Blocks.OBSIDIAN, 1, 0) });
		
		AddShapedRecipeNew("nuggetDiamondA", new ItemStack(ItemInit.MATERIAL, 9, 6),
				new Object[] { "A",
						'A',
						"gemDiamond"});
		System.out.println("Diamond Nugget Recipe Loaded");
		
		AddShapedRecipeNew("nuggetDiamond", new ItemStack(ItemInit.MATERIAL, 9, 7),
				new Object[] { "A",
						'A',
						"gemEmerald"});
		System.out.println("Diamond Nugget Recipe Loaded");
		
		AddShapedRecipeNew("diamondGemNugget", new ItemStack(Items.DIAMOND, 1, 0),
				new Object[] { 
						"AAA",
						"AAA",
						"AAA",
						'A',
						"nuggetDiamond"  });
		System.out.println("Diamond Gem From Nugget Recipe Loaded");
		
		AddShapedRecipeNew("emeraldGemNugget", new ItemStack(Items.EMERALD, 1, 0),
				new Object[] { 
						"AAA",
						"AAA",
						"AAA",
						'A',
						"nuggetEmerald"  });
		System.out.println("Emerald Gem From Nugget  Recipe Loaded");
		
		AddShapedRecipeNew("gearIron", new ItemStack(ItemInit.MATERIAL, 1, 8),
				new Object[] { 
						"#A#",
						"A A",
						"#A#",
						'#',
						"nuggetIron",
						'A',
						"ingotIron"});
		System.out.println("Iron Gear Recipe Loaded");
		
		AddShapedRecipeNew("gearGold", new ItemStack(ItemInit.MATERIAL, 1, 9),
				new Object[] { 
						"#A#",
						"A A",
						"#A#",
						'#',
						"nuggetGold",
						'A',
						"ingotGold"});
		System.out.println("Gold Gear Recipe Loaded");
		
		AddShapedRecipeNew("gearDiamond", new ItemStack(ItemInit.MATERIAL, 1, 10),
				new Object[] { 
						"#A#",
						"A A",
						"#A#",
						'#',
						"nuggetDiamond",
						'A',
						"gemDiamond"});
		System.out.println("Diamond Gear Recipe Loaded");
		
		
		
		int j = 0;
		
		
		for (int i = 0; i != ingots.length; i++) {
			//Adds all the Mod Plate Recipes
			AddShapedRecipeNew("plate" + ingots[i], new ItemStack(ItemInit.MATERIAL, 1, i+225),
					new Object[] { "A#", '#',
							new ItemStack(ItemInit.TOOL_HAMMER, 1, OreDictionary.WILDCARD_VALUE), 'A',
							"ingot" + ingots[i] });
			System.out.println(ingots[i] + " Plate Recipe Loaded");
			
			AddShapedRecipeNew("nugget" + ingots[i], new ItemStack(ItemInit.MATERIAL, 9, i+125),
					new Object[] { "A",
							'A',
							"ingot" + ingots[i] });
			System.out.println(ingots[i] + " Nugget Recipe Loaded");
			
			AddShapedRecipeNew("ingotNugget" + ingots[i], new ItemStack(ItemInit.MATERIAL, 1, i+25),
					new Object[] { 
							"AAA",
							"AAA",
							"AAA",
							'A',
							"nugget" + ingots[i] });
			System.out.println(ingots[i] + " Nugget to Ingot Recipe Loaded");
			
			AddShapedRecipeNew("gear" + ingots[i], new ItemStack(ItemInit.MATERIAL, 1, i+175),
					new Object[] { 
							"#A#",
							"A A",
							"#A#",
							'#',
							"nugget"+ingots[i],
							'A',
							"ingot" + ingots[i] });
			System.out.println(ingots[i] + " Gear Recipe Loaded");
			
			//Adds all the Mod Ore Dust Recipes
			AddShapedRecipeNew("dust" + ingots[i], new ItemStack(ItemInit.MATERIAL, 1, i+75),
					new Object[] { "A#", '#',
							new ItemStack(ItemInit.TOOL_MORTARPESTLE, 1, OreDictionary.WILDCARD_VALUE), 'A',
							"ingot" + ingots[i] });
			System.out.println(ingots[i] + " Ingot Dust Recipe Loaded");
			

			
			//Adds all the Mod Ingot Plate Recipes
			AddShapedRecipeNew("oreDust" + ingots[i], new ItemStack(ItemInit.MATERIAL, 2, i+75),
					new Object[] { "A#", '#',
							new ItemStack(ItemInit.TOOL_MORTARPESTLE, 1, OreDictionary.WILDCARD_VALUE), 'A',
							"ore" + ingots[i] });
			System.out.println(ingots[i] + " Ore Dust Recipe Loaded");
			
		}
		
		for (int i = 0; i != alloys.length; i++)
		{
			AddShapedRecipeNew("plateAlloy" + alloys[i], new ItemStack(ItemInit.MATERIAL, 1, i+250),
					new Object[] { "A#", '#',
							new ItemStack(ItemInit.TOOL_HAMMER, 1, OreDictionary.WILDCARD_VALUE), 'A',
							"ingot" + alloys[i] });
			System.out.println(alloys[i] + " Alloy Plate Recipe Loaded");
			
			AddShapedRecipeNew("nugget" + alloys[i], new ItemStack(ItemInit.MATERIAL, 9, i+150),
					new Object[] { "A",
							'A',
							"ingot" + alloys[i] });
			System.out.println(alloys[i] + " Nugget Recipe Loaded");
			
			AddShapedRecipeNew("ingotNugget" + alloys[i], new ItemStack(ItemInit.MATERIAL, 1, i+50),
					new Object[] { 
							"AAA",
							"AAA",
							"AAA",
							'A',
							"nugget" + alloys[i] });
			System.out.println(alloys[i] + " Nugget to Ingot Recipe Loaded");
			
			
			AddShapedRecipeNew("gear" + alloys[i], new ItemStack(ItemInit.MATERIAL, 1, i+200),
					new Object[] { 
							"#A#",
							"A A",
							"#A#",
							'#',
							"nugget"+alloys[i],
							'A',
							"ingot" + alloys[i] });
			System.out.println(alloys[i] + "Alloy Gear Recipe Loaded");
			
			//Adds all the Mod Ore Dust Recipes
			AddShapedRecipeNew("dustAlloy" + alloys[i], new ItemStack(ItemInit.MATERIAL, 1, i+100),
					new Object[] { "A#", '#',
							new ItemStack(ItemInit.TOOL_MORTARPESTLE, 1, OreDictionary.WILDCARD_VALUE), 'A',
							"ingot" + alloys[i] });
			System.out.println(alloys[i] + "Alloy Ingot Dust Recipe Loaded");
		}
		
		AddShapedRecipeNew("ironPaxel", new ItemStack(ItemInit.PAXEL_IRON, 1, 0),
				new Object[] {  
						" # ",
						"ABC",
						'A',
						new ItemStack(Items.IRON_AXE, 1, OreDictionary.WILDCARD_VALUE),
						'B',
						new ItemStack(Items.IRON_SHOVEL, 1, OreDictionary.WILDCARD_VALUE),
						'C',
						new ItemStack(Items.IRON_PICKAXE, 1, OreDictionary.WILDCARD_VALUE),
						'#',
						new ItemStack(ItemInit.TOOL_HAMMER, 1, OreDictionary.WILDCARD_VALUE)
						});
		AddShapedRecipeNew("diamondPaxel", new ItemStack(ItemInit.PAXEL_DIAMOND, 1, 0),
				new Object[] {  
						" # ",
						"ABC",
						'A',
						new ItemStack(Items.DIAMOND_AXE, 1, OreDictionary.WILDCARD_VALUE),
						'B',
						new ItemStack(Items.DIAMOND_SHOVEL, 1, OreDictionary.WILDCARD_VALUE),
						'C',
						new ItemStack(Items.DIAMOND_PICKAXE, 1, OreDictionary.WILDCARD_VALUE),
						'#',
						new ItemStack(ItemInit.TOOL_HAMMER, 1, OreDictionary.WILDCARD_VALUE)
						});
		AddShapedRecipeNew("bronzePaxel", new ItemStack(ItemInit.PAXEL_BRONZE, 1, 0),
				new Object[] {  
						" # ",
						"ABC",
						'A',
						new ItemStack(ItemInit.AXE_BRONZE, 1, OreDictionary.WILDCARD_VALUE),
						'B',
						new ItemStack(ItemInit.SHOVEL_BRONZE, 1, OreDictionary.WILDCARD_VALUE),
						'C',
						new ItemStack(ItemInit.PICKAXE_BRONZE, 1, OreDictionary.WILDCARD_VALUE),
						'#',
						new ItemStack(ItemInit.TOOL_HAMMER, 1, OreDictionary.WILDCARD_VALUE)
						});
		AddShapedRecipeNew("bronzePickaxe", new ItemStack(ItemInit.PICKAXE_BRONZE, 1, 0),
				new Object[] {  
						"AAA",
						" B ",
						" B ",
						'A',
						"ingotBronze",
						'B',
						new ItemStack(Items.STICK, 1, 0)
						});
		AddShapedRecipeNew("bronzeShovel", new ItemStack(ItemInit.SHOVEL_BRONZE, 1, 0),
				new Object[] {  
						"A",
						"B",
						"B",
						'A',
						"ingotBronze",
						'B',
						new ItemStack(Items.STICK, 1, 0)
						});
		AddShapedRecipeNew("bronzeAxe", new ItemStack(ItemInit.AXE_BRONZE, 1, 0),
				new Object[] {  
						"AA",
						"AB",
						" B",
						'A',
						"ingotBronze",
						'B',
						new ItemStack(Items.STICK, 1, 0)
						});
		AddShapedRecipeNew("bronzeAxe2", new ItemStack(ItemInit.AXE_BRONZE, 1, 0),
				new Object[] {  
						"AA",
						"BA",
						"B ",
						'A',
						"ingotBronze",
						'B',
						new ItemStack(Items.STICK, 1, 0)
						});
		
		for (int i = 0; i != EnumMetal.values().length; i++) {
			AddShapedRecipeNew("blockFromIngot" + i, new ItemStack(BlockInit.BLOCK_METAL, 1, i),
					new Object[] { 
							"AAA", 
							"AAA",
							"AAA",
							'A',
							"ingot" + ingots[i] });
			Ingredient metal = Ingredient.fromStacks(new ItemStack(BlockInit.BLOCK_METAL, 1, i));
			AddShapelessRecipeNew("ingotFromBlock"+i, new ItemStack(ItemInit.MATERIAL, 9, i+25),
					new Ingredient[] { 
							metal});
		}
		
		for (int i = 0; i != EnumAlloy.values().length; i++) {
			AddShapedRecipeNew("blockFromAlloy" + i, new ItemStack(BlockInit.BLOCK_ALLOY, 1, i),
					new Object[] { 
							"AAA", 
							"AAA",
							"AAA",
							'A',
							"ingot" + alloys[i] });
			Ingredient metal = Ingredient.fromStacks(new ItemStack(BlockInit.BLOCK_ALLOY, 1, i));
			AddShapelessRecipeNew("alloyFromBlock"+i, new ItemStack(ItemInit.MATERIAL, 9, i+50),
					new Ingredient[] { 
							metal});
		}
		
		j = 0;
		String[] dyes = { "dyeBlack", "dyeRed", "dyeGreen", "dyeBrown", "dyeBlue", "dyePurple", "dyeCyan", "dyeLightGray",
				"dyeGray", "dyePink", "dyeLime", "dyeYellow", "dyeLightBlue", "dyeMagenta", "dyeOrange", "dyeWhite" };

		for (int i = 0; i != 16; i++) {
			//Adds all the Lamp Recipes
			AddShapedRecipeNew("lamp" + dyes[i], new ItemStack(BlockInit.COLOR_LAMP, 8, i),
					new Object[] { "BBB", "BAB", "BBB", 'B', new ItemStack(Blocks.REDSTONE_LAMP), 'A', dyes[i] });
		}

		AddShapedRecipeNew("tile", new ItemStack(BlockInit.BLOCK_TILE, 1, 15),
				new Object[] { "BB", "BB", 'B', new ItemStack(ItemInit.TILE_CERAMIC)});
		for(int i = 0; i != 15; i++) {
			AddShapedRecipeNew("tile" + dyes[i], new ItemStack(BlockInit.BLOCK_TILE, 8, i),
					new Object[] { "BBB", "BAB", "BBB", 'B', new ItemStack(BlockInit.BLOCK_TILE, 1, OreDictionary.WILDCARD_VALUE), 'A', dyes[i] });
		}
		for(int i = 1; i != 4; i++) {
			AddShapedRecipeNew("lab" + i, new ItemStack(BlockInit.BLOCK_LAB, 1, 0),
					new Object[] {"B", 'B', new ItemStack(BlockInit.BLOCK_LAB, 1, i)});
		}
		//Adds the bronze dust recipe
		//AddShapedRecipeNew("dustBronze", new ItemStack(ItemInit.ALLOY_DUST, 4, 0),
		//		new Object[] { "BB", "BA", 'B', "dustCopper", 'A', "dustTin" });

		System.out.println("Sucsessfully Did those recipes yall");

	}

	private static void AddShapedRecipeNew(String type, ItemStack output, Object... input) {
		ResourceLocation name = new ResourceLocation(Reference.MODID + ":" + type);
		ResourceLocation group = null;
		GameRegistry.addShapedRecipe(name, group, output, input);
	}

	private static void AddShapelessRecipeNew(String type, ItemStack output, Ingredient... input) {
		// String type =
		ResourceLocation name = new ResourceLocation(Reference.MODID + ":" + type);
		ResourceLocation group = null;
		GameRegistry.addShapelessRecipe(name, group, output, input);
	}

}
