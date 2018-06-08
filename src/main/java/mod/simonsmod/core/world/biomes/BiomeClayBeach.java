package mod.simonsmod.core.world.biomes;

import mod.simonsmod.core.init.BlockInit;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;

public class BiomeClayBeach extends Biome {
	public BiomeClayBeach() {
		// Biome.BiomeProperties("Beach")).setBaseHeight(0.0F).setHeightVariation(0.025F).setTemperature(0.8F).setRainfall(0.4F)));
		super(new BiomeProperties("ClayBeach").setBaseHeight(-0.5F).setHeightVariation(0.025F).setTemperature(0.8F)
				.setRainfall(0.4F));
		this.spawnableCreatureList.clear();
		this.topBlock = BlockInit.BLOCK_SOIL.getDefaultState();
		this.fillerBlock = Blocks.CLAY.getDefaultState();
		this.decorator.treesPerChunk = -999;
		this.decorator.deadBushPerChunk = 0;
		this.decorator.reedsPerChunk = 0;
		this.decorator.cactiPerChunk = 0;
	}
}
