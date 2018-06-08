package mod.simonsmod.core.world.biomes;

import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.objects.blocks.BlockRock;
import mod.simonsmod.core.objects.blocks.BlockTile;
import mod.simonsmod.core.objects.simonsEnums.EnumColor;
import mod.simonsmod.core.objects.simonsEnums.EnumRock;
import mod.simonsmod.core.world.gen.BiomeDecoratorMod;
import mod.simonsmod.core.world.gen.generators.WorldGenLava;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeCanyon extends Biome {
	// protected static final WorldGenerator LAVA = new WorldGenLava();
	public BiomeCanyon() {
		super(new BiomeProperties("Canyon").setBaseHeight(-1.6F).setHeightVariation(0.5F).setTemperature(2.0F));

		topBlock = BlockInit.BLOCK_MARBLE.getDefaultState().withProperty(BlockRock.VARIANT, EnumRock.STONE);
		fillerBlock = BlockInit.BLOCK_MARBLE.getDefaultState().withProperty(BlockRock.VARIANT, EnumRock.STONE);
		// this.decorator.grassPerChunk = 10;
		// this.decorator.dirtGen = new
		// WorldGenMinable(BlockInit.BLOCK_TILE.getDefaultState().withProperty(BlockTile.COLOR,
		// EnumColor.BLACK), 10);
		this.decorator.generateFalls = false;
		this.spawnableCreatureList.clear();

		// this.decorator.treesPerChunk = 2;
	}

}
