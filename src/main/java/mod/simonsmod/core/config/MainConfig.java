package mod.simonsmod.core.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mod.simonsmod.core.Reference;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MainConfig {
	
	private static Configuration config = null;
	
	//public static final String CATEGORY_NAME_BLOCKS = "blocks";
	public static final String CATEGORY_NAME_GEN = "gen";
	public static final String CATEGORY_NAME_ORES = "ores";
	
	public static boolean changeVanillaStones;
	public static boolean marbleSpawn;
	public static boolean basaltSpawn;
	public static boolean gneissSpawn;
	public static boolean getCobbleVanillaOres;
	public static boolean genVolcanoBiome;
	public static boolean genMixedForestBiome;
	public static boolean genClayBeachBiome;
	public static boolean genLab;
	public static boolean enableDrugs;
	public static int clayFromDirtChance;
	//public static int[] copperOre, tinOre, silverOre, leadOre, aluminumOre, nickelOre, zincOre, platinumOre, iridiumOre, osmiumOre, tungstenOre, cobaltOre, arditeOre, siliconOre, mithrilOre, bismuthOre;
	//			Block Count, Chance, Min Height, Max Height
	public static int[] rareOre = 		{35,2,0,35,4};
	public static int[] regularOre = 	{45,2,0,45,6};
	public static int[] plentifulOre = 	{50,2,0,95,8};
	public static int[] insideOre = 	{35,100,0,30,245};
	
	public static int[] chalkOre = 		{15,5,0,64};
	public static int[] rubberOre = 	{15,60,0,256};
	
	public static boolean changeVanillaOres;
	public static boolean copper,tin,silver,lead,aluminum,nickel,zinc,platinum,iridium,osmium,tungsten,cobalt,titanium,lithium,chromium,bismuth,rubberO;


	
	public static void preInit()
	{
		File configFile = new File(Loader.instance().getConfigDir(), "SimonsMod/Main.cfg");
		config = new Configuration(configFile);
		syncFromFiles();
	}
	
	public static Configuration getConfig()
	{
		return config;
	}
	public static void clientPreInit(){
		MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
	}
	
	public static void syncFromFiles()
	{
		syncConfig(true, true);
	}
	
	public static void syncFromGui()
	{
		syncConfig(false, true);
	}
	
	public static void syncFromFields()
	{
		syncConfig(false, false);
	}
	
	private static void syncConfig(boolean loadFromConfigFile, boolean readFieldsFromConfig)
	{
		if(loadFromConfigFile)
			config.load();
		Property propertyChangeVanillaStones	= config.get(CATEGORY_NAME_GEN, "changeVanillaStones", true);
		Property propertyChangeVanillaOres		= config.get(CATEGORY_NAME_GEN, "changeVanillaOres", true);
		Property propertyMarbleOreSpawn 		= config.get(CATEGORY_NAME_GEN, "marbleSpawn", true);
		Property propertyBasaltOreSpawn 		= config.get(CATEGORY_NAME_GEN, "basaltSpawn", true);
		Property propertyGneissOreSpawn 		= config.get(CATEGORY_NAME_GEN, "gneissSpawn", true);
		Property propertyGetCobbleVanillaOres 	= config.get(CATEGORY_NAME_GEN, "getCobbleVanillaOres", false);
		Property propertyGenVolcanoBiome 		= config.get(CATEGORY_NAME_GEN, "genVolcanoBiome", true);
		Property propertyGenmidexBiome 			= config.get(CATEGORY_NAME_GEN, "genMixedForestBiome", true);
		Property propertyGenClayBiome 			= config.get(CATEGORY_NAME_GEN, "genClayBeachBiome", true);
		Property propertyGenLab 				= config.get(CATEGORY_NAME_GEN, "genLab", false);
		Property propertyDrugs 					= config.get(CATEGORY_NAME_GEN, "enableDrugs", true);
		Property propertyClayFromDirt 			= config.get(CATEGORY_NAME_GEN, "clayFromDirtChance", 6);
		//Block Count, Chance, Min Height, Max Height, times
		int[] rare 		= {35,5,0,35,4};
		int[] regular 	= {45,5,0,45,6};
		int[] plentiful = {50,8,0,95,8};
		int[] inside 	= {35,100,0,30,245};
		
		int[] chalk 	= {15,5,0,64};
		int[] rubber    = {15,60,0,256};
		Property propertyInside			= config.get(CATEGORY_NAME_GEN, "insideOre", 		inside);
		propertyInside.setComment("Sets the Worldgen for all ores that spawn inside other ore veins, (Count, Chance, Min Height, Max Height) Chance Value Should be between 0 and 32");
		Property propertyRare			= config.get(CATEGORY_NAME_GEN, "rareOre", 		rare);
		propertyRare.setComment("Sets the Worldgen for all rare ores, (Count, Chance, Min Height, Max Height) Chance Value Should be between 0 and 32");
		Property propertyRegular 		= config.get(CATEGORY_NAME_GEN, "regularOre", 	regular);
		propertyRegular.setComment("Sets the Worldgen for all regular ores, (Count, Chance, Min Height, Max Height) Chance Value Should be between 0 and 32");
		Property propertyPlentiful		= config.get(CATEGORY_NAME_GEN, "plentifulOre", plentiful);		
		propertyPlentiful.setComment("Sets the Worldgen for all plentiful ores, (Count, Chance, Min Height, Max Height) Chance Value Should be between 0 and 32");
		Property propertyChalkOre 		= config.get(CATEGORY_NAME_GEN, "chalkOre", 	chalk);
		propertyChalkOre.setComment("Sets the Worldgen for the chalk ore, (Count, Chance, Min Height, Max Height) The Greater the chance value the greater chance it has to spawn in that chunk");
		
		Property propertyRubberOre = config.get(CATEGORY_NAME_GEN, "rubberOre", 	rubber);
		propertyChalkOre.setComment("Sets the Worldgen for the chalk ore, (Count, Chance, Min Height, Max Height) The Greater the chance value the greater chance it has to spawn in that chunk");

		//tinOre, silverOre, leadOre, aluminumOre, nickelOre, zincOre, platinumOre, iridiumOre, osmiumOre, tungstenOre, cobaltOre, arditeOre, siliconOre, mithrilOre, bismuthOre;
		Property propertyCopper 			= config.get(CATEGORY_NAME_ORES, "copper", 	true);
		Property propertyTin	 			= config.get(CATEGORY_NAME_ORES, "tin", 	true);
		Property propertySilver 			= config.get(CATEGORY_NAME_ORES, "silver", 	true);
		Property propertyLead	 			= config.get(CATEGORY_NAME_ORES, "lead", 	true);
		Property propertyAluminum 			= config.get(CATEGORY_NAME_ORES, "aluminum",true);
		Property propertyNickel 			= config.get(CATEGORY_NAME_ORES, "nickel", 	true);
		Property propertyZinc 	 			= config.get(CATEGORY_NAME_ORES, "zinc", 	true);
		Property propertyPlatinum 			= config.get(CATEGORY_NAME_ORES, "platinum",true);
		Property propertyIridium 			= config.get(CATEGORY_NAME_ORES, "iridium", true);
		Property propertyOsmium 			= config.get(CATEGORY_NAME_ORES, "osmium", 	true);
		Property propertyTungsten 			= config.get(CATEGORY_NAME_ORES, "tungsten",true);
		Property propertyCobalt 			= config.get(CATEGORY_NAME_ORES, "cobalt", 	true);
		Property propertyArdite 			= config.get(CATEGORY_NAME_ORES, "titanium",true);
		Property propertySilicon 			= config.get(CATEGORY_NAME_ORES, "lithium", true);
		Property propertyMithril 			= config.get(CATEGORY_NAME_ORES, "chromium",true);
		Property propertyBismuth 			= config.get(CATEGORY_NAME_ORES, "bismuth", true);
		
		Property propertyRubber 			= config.get(CATEGORY_NAME_ORES, "rubberO", true);
		
		//propertyMachineCooldownBasic.setLanguageKey("gui.config.blocks.machine_cooldown_basic.name");
		//propertyMachineCooldownBasic.setComment(I18n.format("gui.config.blocks.machine_cooldown_basic.comment"));
		//propertyMachineCooldownBasic.setMinValue(10);
		//propertyMachineCooldownBasic.setMaxValue(200);
		
		List<String> propertyOrderBlocks = new ArrayList<String>();
		//propertyOrderBlocks.add(propertyMachineCooldownBasic.getName());
		//config.setCategoryPropertyOrder(CATEGORY_NAME_BLOCKS, propertyOrderBlocks);
		config.setCategoryPropertyOrder(CATEGORY_NAME_ORES, propertyOrderBlocks);
		config.setCategoryPropertyOrder(CATEGORY_NAME_GEN, propertyOrderBlocks);
		
		if(readFieldsFromConfig){
			//machineCooldownBasic = propertyMachineCooldownBasic.getInt();
			changeVanillaStones 	= propertyChangeVanillaStones.getBoolean();
			changeVanillaOres 		= propertyChangeVanillaOres.getBoolean();
			marbleSpawn 			= propertyMarbleOreSpawn.getBoolean();
			basaltSpawn 			= propertyBasaltOreSpawn.getBoolean();
			gneissSpawn 			= propertyGneissOreSpawn.getBoolean();
			getCobbleVanillaOres 	= propertyGetCobbleVanillaOres.getBoolean();
			genVolcanoBiome 		= propertyGenVolcanoBiome.getBoolean();
			genMixedForestBiome 	= propertyGenmidexBiome.getBoolean();
			genClayBeachBiome 		= propertyGenClayBiome.getBoolean();
			genLab 					= propertyGenLab.getBoolean();
			enableDrugs 			= propertyDrugs.getBoolean();
			clayFromDirtChance 		= propertyClayFromDirt.getInt();
			rareOre 				= propertyRare.getIntList();
			regularOre 				= propertyRegular.getIntList();
			plentifulOre 			= propertyPlentiful.getIntList();
			chalkOre 				= propertyChalkOre.getIntList();
			rubberOre 				= propertyRubberOre.getIntList();
			//public static boolean copper,tin,silver,lead,aluminum,nickel,zinc,platinum,iridium,osmium,tungsten,cobalt,titanium,lithium,chromium,bismuth;

			copper 					= propertyCopper.getBoolean();
			tin 					= propertyTin.getBoolean();
			silver 					= propertySilver.getBoolean();
			lead 					= propertyLead.getBoolean();
			aluminum 				= propertyAluminum.getBoolean();
			nickel 					= propertyNickel.getBoolean();
			zinc 					= propertyZinc.getBoolean();
			platinum 				= propertyPlatinum.getBoolean();
			iridium 				= propertyIridium.getBoolean();
			osmium 					= propertyOsmium.getBoolean();
			tungsten 				= propertyTungsten.getBoolean();
			cobalt 					= propertyCobalt.getBoolean();
			titanium 				= propertyArdite.getBoolean();
			lithium 				= propertySilicon.getBoolean();
			chromium 				= propertyMithril.getBoolean();
			bismuth 				= propertyBismuth.getBoolean();
			
			rubberO 					= propertyRubber.getBoolean();
			
		}
		//propertyMachineCooldownBasic.set(machineCooldownBasic);
		if(config.hasChanged())
			config.save();
	}
	
	public static class ConfigEventHandler
	{
		@SubscribeEvent(priority = EventPriority.LOWEST)
		public void onEvent(ConfigChangedEvent.PostConfigChangedEvent event)
		{
			if(event.getModID().equals(Reference.MODID)){
				syncFromGui();
			}
		}
	}
}
