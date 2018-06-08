package mod.simonsmod.core.init;

import mod.simonsmod.core.config.MainConfig;
import mod.simonsmod.core.world.biomes.BiomeCanyon;
import mod.simonsmod.core.world.biomes.BiomeClayBeach;
import mod.simonsmod.core.world.biomes.BiomeFlatPlains;
import mod.simonsmod.core.world.biomes.BiomeMixedForest;
import mod.simonsmod.core.world.biomes.BiomeVolcano;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeInit {

	public static final Biome VOLCANO = new BiomeVolcano();
	public static final Biome CANYON = new BiomeCanyon();
	public static final Biome MIXED_FOREST = new BiomeMixedForest();
	public static final Biome CLAY_BEACH = new BiomeClayBeach();
	public static final Biome FLAT_PLAINS = new BiomeFlatPlains();

	public static void registerBiomes()
	{
		if(MainConfig.genVolcanoBiome)
			initBiome(VOLCANO, "volcano", BiomeType.WARM, Type.HILLS, Type.MOUNTAIN, Type.DRY);
		if(MainConfig.genMixedForestBiome)
			initBiome(MIXED_FOREST, "mixed_forest", BiomeType.WARM, Type.HILLS, Type.MOUNTAIN, Type.DRY);
		if(MainConfig.genClayBeachBiome)
			initBiome(CLAY_BEACH, "clay_beach", BiomeType.WARM, Type.BEACH);
		
		initBiome(FLAT_PLAINS, "flat_plains", BiomeType.WARM, Type.PLAINS);
	}
	private static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... types)
	{
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10));
		BiomeManager.addSpawnBiome(biome);
		return biome;
	}
}
