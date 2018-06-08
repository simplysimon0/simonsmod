package mod.simonsmod.core.world.gen;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;

public class PlantsWorldDecorator extends BiomeDecorator {
	public int cannibusPerChunk;

	 public WorldGenerator cannibusGen = new WorldGenCannabis();
	
	@Override
	public void decorate(World worldIn, Random random, Biome biome, BlockPos pos) {
		super.decorate(worldIn, random, biome, pos);

		for (int j3 = 0; j3 < this.cannibusPerChunk; ++j3)
		{
			int l9 = random.nextInt(16) + 8;
			int k13 = random.nextInt(16) + 8;
			int l16 = worldIn.getHeight(this.chunkPos.add(l9, 0, k13)).getY() * 2;

			if (l16 > 0) {
				int j19 = random.nextInt(l16);
				this.cannibusGen.generate(worldIn, random, this.chunkPos.add(l9, j19, k13));
			}
		}
	}
}
