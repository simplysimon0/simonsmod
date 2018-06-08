package mod.simonsmod.core.world.gen;

import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenBush;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenClay;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecoratorMod {
	public boolean decorating;
	public BlockPos chunkPos;
	public ChunkGeneratorSettings chunkProviderSettings;
	public boolean generateLavaFalls = true;

	protected void genDecorations(Biome biomeIn, World worldIn, Random random) {

		if (this.generateLavaFalls) {

			if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, chunkPos,
					net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LAKE_LAVA))
				for (int l5 = 0; l5 < 20; ++l5) {
					int j10 = random.nextInt(16) + 8;
					int i14 = random.nextInt(16) + 8;
					int j17 = random.nextInt(random.nextInt(random.nextInt(240) + 8) + 8);
					BlockPos blockpos3 = this.chunkPos.add(j10, j17, i14);
					(new WorldGenLiquids(Blocks.FLOWING_LAVA)).generate(worldIn, random, blockpos3);
				}
		}
		net.minecraftforge.common.MinecraftForge.EVENT_BUS
				.post(new net.minecraftforge.event.terraingen.DecorateBiomeEvent.Post(worldIn, random, chunkPos));
	}
}
