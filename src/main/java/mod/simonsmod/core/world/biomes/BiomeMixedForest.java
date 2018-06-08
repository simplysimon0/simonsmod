package mod.simonsmod.core.world.biomes;

import java.util.Random;

import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.objects.blocks.BlockTile;
import mod.simonsmod.core.objects.simonsEnums.EnumColor;
import mod.simonsmod.core.world.gen.PlantsWorldDecorator;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBirchTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;

public class BiomeMixedForest extends Biome {
	PlantsWorldDecorator plantsworlddecorator;
	private static final WorldGenTaiga1 PINE_GENERATOR = new WorldGenTaiga1();
	private static final WorldGenTaiga2 SPRUCE_GENERATOR = new WorldGenTaiga2(true);
	protected static final WorldGenBirchTree SUPER_BIRCH_TREE = new WorldGenBirchTree(true, true);
	protected static final WorldGenBirchTree BIRCH_TREE = new WorldGenBirchTree(true, true);
	protected static final WorldGenCanopyTree ROOF_TREE = new WorldGenCanopyTree(true);

	// private final BiomeForest.Type type;
	public BiomeMixedForest() {
		super(new BiomeProperties("mixed_forest").setTemperature(0.4F).setRainfall(0.8F).setHeightVariation(0.3F));
		// this.type = typeIn;
		// topBlock =
		// BlockInit.BLOCK_DIRT.getDefaultState().withProperty(BlockRockVanilla.VARIANT,
		// EnumRockVanilla.COBBLE);
		// fillerBlock =
		// BlockInit.BLOCK_DIRT.getDefaultState().withProperty(BlockRockVanilla.VARIANT,
		// EnumRockVanilla.PANNEL);
		// this.decorator.grassPerChunk = 10;
		this.decorator.dirtGen = new WorldGenMinable(
				BlockInit.BLOCK_TILE.getDefaultState().withProperty(BlockTile.COLOR, EnumColor.BLACK), 10);
		this.decorator.generateFalls = false;
		this.spawnableCreatureList.clear();
		this.decorator.treesPerChunk = 12;
		this.decorator.grassPerChunk = 2;
		
		plantsworlddecorator = new PlantsWorldDecorator();
		//plantsworlddecorator = (PlantsWorldDecorator)decorator;
		this.plantsworlddecorator.cannibusPerChunk = 5;
		// this.decorator.treesPerChunk = 2;
	}

	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		if (rand.nextInt(2) > 0) {
			return TREE_FEATURE;
		} else if (rand.nextInt(2) > 0) {
			return SPRUCE_GENERATOR;
		} else if (rand.nextInt(2) > 0) {
			return PINE_GENERATOR;
		} else if (rand.nextInt(9) > 0) {
			return ROOF_TREE;
		} else {
			return BIRCH_TREE;
		}

	}

}
