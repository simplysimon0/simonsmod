package mod.simonsmod.core.world.gen;

import java.util.Random;

import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.objects.blocks.BlockCrop;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCannabis extends WorldGenerator {

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		for (int i = 0; i < 10; ++i) {
			BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4),
					rand.nextInt(8) - rand.nextInt(8));

			if (worldIn.isAirBlock(blockpos)) {
				int j = 1 + rand.nextInt(rand.nextInt(3) + 1);

				for (int k = 0; k < j; ++k) {

					worldIn.setBlockState(blockpos.up(k),
							BlockInit.CROP_CANNABIS.getDefaultState().withProperty(BlockCrop.AGE, 6), 2);

				}
			}
		}
		System.out.println("gay");
		return true;
	}
}
