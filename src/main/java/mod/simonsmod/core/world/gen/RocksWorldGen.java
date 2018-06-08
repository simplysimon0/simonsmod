package mod.simonsmod.core.world.gen;

import java.util.Random;

import mod.simonsmod.core.config.MainConfig;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.objects.blocks.BlockRock;
import mod.simonsmod.core.objects.simonsEnums.EnumRock;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class RocksWorldGen implements IWorldGenerator {
	private WorldGenerator rock_marble, rock_basalt, rock_gneiss, lava, rock_granite, rock_andesite, rock_diorite, cannabis;
	// private WorldGenerator ore_nether_aluminium, ore_overworld_aluminium,
	// ore_end_aluminium;
	
	
	public RocksWorldGen() {
		lava = new WorldGenMinable(Blocks.LAVA.getDefaultState(), 1, BlockMatcher.forBlock(Blocks.STONE));

		rock_marble = new WorldGenMinable(
				BlockInit.BLOCK_MARBLE.getDefaultState().withProperty(BlockRock.VARIANT, EnumRock.STONE), 40,
				BlockMatcher.forBlock(Blocks.STONE));
		//
		rock_basalt = new WorldGenMinable(
				BlockInit.BLOCK_BASALT.getDefaultState().withProperty(BlockRock.VARIANT, EnumRock.STONE), 40,
				BlockMatcher.forBlock(Blocks.STONE));
		//
		rock_gneiss = new WorldGenMinable(
				BlockInit.BLOCK_GNEISS.getDefaultState().withProperty(BlockRock.VARIANT, EnumRock.STONE), 40,
				BlockMatcher.forBlock(Blocks.STONE));
		rock_diorite = new WorldGenMinable(
				BlockInit.BLOCK_DIORITE.getDefaultState().withProperty(BlockRock.VARIANT, EnumRock.STONE), 40,
				BlockMatcher.forBlock(Blocks.STONE));
		rock_andesite = new WorldGenMinable(
				BlockInit.BLOCK_ANDESITE.getDefaultState().withProperty(BlockRock.VARIANT, EnumRock.STONE), 40,
				BlockMatcher.forBlock(Blocks.STONE));
		rock_granite = new WorldGenMinable(
				BlockInit.BLOCK_GRANITE.getDefaultState().withProperty(BlockRock.VARIANT, EnumRock.STONE), 40,
				BlockMatcher.forBlock(Blocks.STONE));
		cannabis = new WorldGenMinable(
				BlockInit.BLOCK_GRANITE.getDefaultState().withProperty(BlockRock.VARIANT, EnumRock.STONE), 1,
				BlockMatcher.forBlock(Blocks.STONE));

	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
		case -1:

			// runGenerator(ore_nether_aluminium, world, random, chunkX, chunkZ,
			// 50, 0, 100);
			// runGenerator(ore_nether_copper, world, random, chunkX, chunkZ,
			// 50, 0, 100);

			break;

		case 0:
			// Chance, min height, max height
			runGenerator(lava, world, random, chunkX, chunkZ, 10, 64, 256);
			if (MainConfig.marbleSpawn)
				runGenerator(rock_marble, world, random, chunkX, chunkZ, 5, 0, 256);
			if (MainConfig.basaltSpawn)
				runGenerator(rock_basalt, world, random, chunkX, chunkZ, 5, 0, 256);
			if (MainConfig.gneissSpawn)
				runGenerator(rock_gneiss, world, random, chunkX, chunkZ, 4, 0, 256);
			if (MainConfig.changeVanillaStones)
			{
				runGenerator(rock_diorite, world, random, chunkX, chunkZ, 5, 0, 256);
				runGenerator(rock_andesite, world, random, chunkX, chunkZ, 5, 0, 256);
				runGenerator(rock_granite, world, random, chunkX, chunkZ, 5, 0, 256);
			}
				
			break;

		case 1:

			// runGenerator(ore_end_aluminium, world, random, chunkX, chunkZ,
			// 50, 0, 256);
			// runGenerator(ore_end_copper, world, random, chunkX, chunkZ, 50,
			// 0, 256);
		}
	}

	private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance,
			int minHeight, int maxHeight) {
		if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256)
			throw new IllegalArgumentException("Rock generated out of bounds");

		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chance; i++) {
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunkZ * 16 + rand.nextInt(16);

			gen.generate(world, rand, new BlockPos(x, y, z));
		}
	}
}