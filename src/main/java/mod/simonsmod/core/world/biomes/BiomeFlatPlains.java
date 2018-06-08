package mod.simonsmod.core.world.biomes;

import java.util.Random;

import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.world.gen.PlantsWorldDecorator;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class BiomeFlatPlains extends Biome {
	PlantsWorldDecorator plantsworlddecorator;

	public BiomeFlatPlains() {
		// Biome.BiomeProperties("Beach")).setBaseHeight(0.0F).setHeightVariation(0.025F).setTemperature(0.8F).setRainfall(0.4F)));
		super(new BiomeProperties("Flat Plains").setBaseHeight(0.125F).setHeightVariation(0.0005F).setTemperature(0.9F)
				.setRainfall(0.4F));
		plantsworlddecorator = new PlantsWorldDecorator();
		//plantsworlddecorator = (PlantsWorldDecorator)decorator;
		this.plantsworlddecorator.cannibusPerChunk = 5;
		
		this.spawnableCreatureList.clear();
	}

}
