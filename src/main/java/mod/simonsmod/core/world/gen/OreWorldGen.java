package mod.simonsmod.core.world.gen;

import java.util.Random;
import java.util.function.Predicate;

import mod.simonsmod.core.config.MainConfig;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.objects.blocks.BlockMetalOre;
import mod.simonsmod.core.objects.blocks.BlockMiscOre;
import mod.simonsmod.core.objects.blocks.BlockRock;
import mod.simonsmod.core.objects.simonsEnums.EnumMetal;
import mod.simonsmod.core.objects.simonsEnums.EnumMiscOre;
import mod.simonsmod.core.objects.simonsEnums.EnumRock;
import mod.simonsmod.core.util.StateMatcher;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OreWorldGen implements IWorldGenerator {
	// "Copper","Tin","Silver","Lead","Aluminum","Nickel","Zinc","Platinum","Iridium","Osmium","Tungsten","Cobalt","Ardite","lithium","chromium","Bismuth"};
	private WorldGenerator copper, tin, silver, lead, aluminum, nickel, zinc, platinum, iridium, osmium, tungsten,
			cobalt, titanium, lithium, chromium, bismuth, chalk, iron, coal, diamond, lapis,gold, redstone, rubber;
	// private WorldGenerator ore_nether_aluminium, ore_overworld_aluminium,
	// ore_end_aluminium;


	
	
	public OreWorldGen() {
		copper = new WorldGenMinable(
				BlockInit.BLOCK_METAL_ORE.getDefaultState().withProperty(BlockMetalOre.VARIANT, EnumMetal.COPPER),
				MainConfig.plentifulOre[0], BlockMatcher.forBlock(Blocks.STONE));
		tin = new WorldGenMinable(
				BlockInit.BLOCK_METAL_ORE.getDefaultState().withProperty(BlockMetalOre.VARIANT, EnumMetal.TIN),
				MainConfig.plentifulOre[0], BlockMatcher.forBlock(Blocks.STONE));
		silver = new WorldGenMinableSilver(
				BlockInit.BLOCK_METAL_ORE.getDefaultState().withProperty(BlockMetalOre.VARIANT, EnumMetal.SILVER),
				MainConfig.insideOre[0]);
		lead = new WorldGenMinable(
				BlockInit.BLOCK_METAL_ORE.getDefaultState().withProperty(BlockMetalOre.VARIANT, EnumMetal.LEAD),
				45, BlockMatcher.forBlock(Blocks.STONE));
		aluminum = new WorldGenMinable(
				BlockInit.BLOCK_METAL_ORE.getDefaultState().withProperty(BlockMetalOre.VARIANT, EnumMetal.ALUMINUM),
				MainConfig.plentifulOre[0], BlockMatcher.forBlock(Blocks.STONE));
		nickel = new WorldGenMinable(
				BlockInit.BLOCK_METAL_ORE.getDefaultState().withProperty(BlockMetalOre.VARIANT, EnumMetal.NICKEL),
				MainConfig.regularOre[0], BlockMatcher.forBlock(Blocks.STONE));
		zinc = new WorldGenMinable(
				BlockInit.BLOCK_METAL_ORE.getDefaultState().withProperty(BlockMetalOre.VARIANT, EnumMetal.ZINC),
				MainConfig.regularOre[0], BlockMatcher.forBlock(Blocks.STONE));
		platinum = new WorldGenMinable(
				BlockInit.BLOCK_METAL_ORE.getDefaultState().withProperty(BlockMetalOre.VARIANT, EnumMetal.PLATINUM),
				MainConfig.rareOre[0], BlockMatcher.forBlock(Blocks.STONE));
		iridium = new WorldGenMinable(
				BlockInit.BLOCK_METAL_ORE.getDefaultState().withProperty(BlockMetalOre.VARIANT, EnumMetal.IRIDIUM),
				MainConfig.rareOre[0], BlockMatcher.forBlock(Blocks.STONE));
		osmium = new WorldGenMinable(
				BlockInit.BLOCK_METAL_ORE.getDefaultState().withProperty(BlockMetalOre.VARIANT, EnumMetal.OSMIUM),
				MainConfig.regularOre[0], BlockMatcher.forBlock(Blocks.STONE));
		tungsten = new WorldGenMinable(
				BlockInit.BLOCK_METAL_ORE.getDefaultState().withProperty(BlockMetalOre.VARIANT, EnumMetal.TUNGSTEN),
				MainConfig.rareOre[0], BlockMatcher.forBlock(Blocks.STONE));
		cobalt = new WorldGenMinable(
				BlockInit.BLOCK_METAL_ORE.getDefaultState().withProperty(BlockMetalOre.VARIANT, EnumMetal.COBALT),
				MainConfig.rareOre[0], BlockMatcher.forBlock(Blocks.STONE));
		titanium = new WorldGenMinable(
				BlockInit.BLOCK_METAL_ORE.getDefaultState().withProperty(BlockMetalOre.VARIANT, EnumMetal.TITANIUM),
				MainConfig.rareOre[0], BlockMatcher.forBlock(Blocks.STONE));
		lithium = new WorldGenMinable(
				BlockInit.BLOCK_METAL_ORE.getDefaultState().withProperty(BlockMetalOre.VARIANT, EnumMetal.LITHIUM),
				MainConfig.rareOre[0], BlockMatcher.forBlock(Blocks.STONE));
		chromium = new WorldGenMinable(
				BlockInit.BLOCK_METAL_ORE.getDefaultState().withProperty(BlockMetalOre.VARIANT, EnumMetal.CHROMIUM),
				MainConfig.rareOre[0], BlockMatcher.forBlock(Blocks.STONE));
		bismuth = new WorldGenMinable(
				BlockInit.BLOCK_METAL_ORE.getDefaultState().withProperty(BlockMetalOre.VARIANT, EnumMetal.BISMUTH),
				MainConfig.rareOre[0], BlockMatcher.forBlock(Blocks.STONE));
		chalk = new WorldGenMinable(
				BlockInit.BLOCK_MISC_ORE.getDefaultState().withProperty(BlockMiscOre.VARIANT, EnumMiscOre.CHALK),
				MainConfig.chalkOre[0], BlockMatcher.forBlock(Blocks.STONE));
		iron = new WorldGenMinable(
				Blocks.IRON_ORE.getDefaultState(),
				MainConfig.plentifulOre[0], BlockMatcher.forBlock(Blocks.STONE));
		coal = new WorldGenMinable(
				Blocks.COAL_ORE.getDefaultState(),
				MainConfig.plentifulOre[0], BlockMatcher.forBlock(Blocks.STONE));
		diamond = new WorldGenMinable(
				Blocks.DIAMOND_ORE.getDefaultState(),
				MainConfig.insideOre[0], BlockMatcher.forBlock(Blocks.COAL_ORE));
		gold = new WorldGenMinable(
				Blocks.GOLD_ORE.getDefaultState(),
				MainConfig.rareOre[0], BlockMatcher.forBlock(Blocks.STONE));
		lapis = new WorldGenMinable(
				Blocks.LAPIS_ORE.getDefaultState(),
				MainConfig.regularOre[0], BlockMatcher.forBlock(Blocks.STONE));
		redstone = new WorldGenMinable(
				Blocks.GOLD_ORE.getDefaultState(),
				MainConfig.regularOre[0], BlockMatcher.forBlock(Blocks.STONE));
		rubber = new WorldGenMinable(
				BlockInit.BLOCK_MISC_ORE.getDefaultState().withProperty(BlockMiscOre.VARIANT, EnumMiscOre.RUBBER),
				MainConfig.rubberOre[0], StateMatcher.forState(Blocks.LOG.getDefaultState()));

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
			if(MainConfig.copper)
				runGenerator(copper, 	world, random, chunkX, chunkZ, MainConfig.plentifulOre[1], 		MainConfig.plentifulOre[2], MainConfig.plentifulOre[3], MainConfig.plentifulOre[4]);
			if(MainConfig.tin)
				runGenerator(tin,   	world, random, chunkX, chunkZ, MainConfig.plentifulOre[1], 		MainConfig.plentifulOre[2], MainConfig.plentifulOre[3], MainConfig.plentifulOre[4]);
			if(MainConfig.silver)
			{
				runGenerator(silver, 	world, random, chunkX, chunkZ, MainConfig.insideOre[1], 		MainConfig.insideOre[2], 	MainConfig.insideOre[3], 	MainConfig.insideOre[4]);
				runGenerator(silver, 	world, random, chunkX, chunkZ, MainConfig.insideOre[1], 		MainConfig.insideOre[2], 	MainConfig.insideOre[3], 	MainConfig.insideOre[4]);
				runGenerator(silver, 	world, random, chunkX, chunkZ, MainConfig.insideOre[1], 		MainConfig.insideOre[2], 	MainConfig.insideOre[3], 	MainConfig.insideOre[4]);
				//runGenerator(silver, 	world, random, chunkX, chunkZ, MainConfig.insideOre[1], 		MainConfig.insideOre[2], 	MainConfig.insideOre[3], 	MainConfig.insideOre[4]);
			}
				//runGenerator(diamond, 	world, random, chunkX, chunkZ, MainConfig.insideOre[1], 	MainConfig.insideOre[2], 	MainConfig.insideOre[3], 	MainConfig.insideOre[4]);
			if(MainConfig.lead)
				runGenerator(lead,    	world, random, chunkX, chunkZ, MainConfig.regularOre[1],		MainConfig.regularOre[2], MainConfig.regularOre[3], MainConfig.regularOre[4]);
			if(MainConfig.aluminum)
				runGenerator(aluminum,	world, random, chunkX, chunkZ, MainConfig.plentifulOre[1], 		MainConfig.plentifulOre[2], MainConfig.plentifulOre[3], MainConfig.plentifulOre[4]);
			if(MainConfig.nickel)
				runGenerator(nickel,	world, random, chunkX, chunkZ, MainConfig.regularOre[1],   		MainConfig.regularOre[2], 	MainConfig.regularOre[3],	MainConfig.regularOre[4]);
			if(MainConfig.zinc)
				runGenerator(zinc,	 	world, random, chunkX, chunkZ, MainConfig.regularOre[1], 		MainConfig.regularOre[2], 	MainConfig.regularOre[3], 	MainConfig.regularOre[4]);
			if(MainConfig.platinum)
				runGenerator(platinum, 	world, random, chunkX, chunkZ, MainConfig.rareOre[1], 			MainConfig.rareOre[2], 		MainConfig.rareOre[3], 		MainConfig.rareOre[4]);
			if(MainConfig.iridium)
				runGenerator(iridium, 	world, random, chunkX, chunkZ, MainConfig.rareOre[1], 			MainConfig.rareOre[2], 		MainConfig.rareOre[3], 		MainConfig.rareOre[4]);
			if(MainConfig.osmium)
				runGenerator(osmium, 	world, random, chunkX, chunkZ, MainConfig.regularOre[1], 		MainConfig.regularOre[2], 	MainConfig.regularOre[3], 	MainConfig.regularOre[4]);
			if(MainConfig.tungsten)
				runGenerator(tungsten, 	world, random, chunkX, chunkZ, MainConfig.rareOre[1], 			MainConfig.rareOre[2],	 	MainConfig.rareOre[3], 		MainConfig.rareOre[4]);
			if(MainConfig.cobalt)
				runGenerator(cobalt, 	world, random, chunkX, chunkZ, MainConfig.rareOre[1], 			MainConfig.rareOre[2], 		MainConfig.rareOre[3], 		MainConfig.rareOre[4]);
			if(MainConfig.titanium)
				runGenerator(titanium, 	world, random, chunkX, chunkZ, MainConfig.rareOre[1], 			MainConfig.rareOre[2], 		MainConfig.rareOre[3], 		MainConfig.rareOre[4]);
			if(MainConfig.lithium)
				runGenerator(lithium, 	world, random, chunkX, chunkZ, MainConfig.rareOre[1], 			MainConfig.rareOre[2], 		MainConfig.rareOre[3], 		MainConfig.rareOre[4]);
			if(MainConfig.chromium)
				runGenerator(chromium, 	world, random, chunkX, chunkZ, MainConfig.rareOre[1], 			MainConfig.rareOre[2], 		MainConfig.rareOre[3], 		MainConfig.rareOre[4]);
			if(MainConfig.bismuth)
				runGenerator(bismuth, 	world, random, chunkX, chunkZ,  MainConfig.rareOre[1], 			MainConfig.rareOre[2], 		MainConfig.rareOre[3], 		MainConfig.rareOre[4]);
			
			runRGenerator(chalk, 	world, random, chunkX, chunkZ, MainConfig.chalkOre[1], 	MainConfig.chalkOre[2], 	MainConfig.chalkOre[3]);
			if(MainConfig.rubberO)
				runRGenerator(rubber, 	world, random, chunkX, chunkZ, MainConfig.rubberOre[1], MainConfig.rubberOre[2], 	MainConfig.rubberOre[3]);
			if(MainConfig.changeVanillaOres)
			{
				runGenerator(iron, 		world, random, chunkX, chunkZ, MainConfig.plentifulOre[1], 	MainConfig.plentifulOre[2], MainConfig.plentifulOre[3], MainConfig.plentifulOre[4]);
				runGenerator(coal, 		world, random, chunkX, chunkZ, MainConfig.plentifulOre[1], 	MainConfig.plentifulOre[2], MainConfig.plentifulOre[3],	MainConfig.plentifulOre[4]);
				runGenerator(diamond, 	world, random, chunkX, chunkZ, MainConfig.insideOre[1], 	MainConfig.insideOre[2], 	MainConfig.insideOre[3], 	MainConfig.insideOre[4]);
				runGenerator(gold, 		world, random, chunkX, chunkZ, MainConfig.rareOre[1], 		MainConfig.rareOre[2], 		MainConfig.rareOre[3], 		MainConfig.plentifulOre[4]);
				runGenerator(lapis, 	world, random, chunkX, chunkZ, MainConfig.rareOre[1], 		MainConfig.rareOre[2], 		MainConfig.rareOre[3], 		MainConfig.plentifulOre[4]);
				runGenerator(redstone, 	world, random, chunkX, chunkZ, MainConfig.regularOre[1], 	MainConfig.regularOre[2], 	MainConfig.regularOre[3], 	MainConfig.plentifulOre[4]);
			}
			break;

		case 1:

			// runGenerator(ore_end_aluminium, world, random, chunkX, chunkZ,
			// 50, 0, 256);
			// runGenerator(ore_end_copper, world, random, chunkX, chunkZ, 50,
			// 0, 256);
		}
	}

	private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight,
			int maxHeight, int times) {
		if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256)
			throw new IllegalArgumentException("Ore generated out of bounds");

		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < times; i++) {
			if(rand.nextInt(5000) <= chance)
			{
				int x = chunkX * 16 + rand.nextInt(16);
				int y = minHeight + rand.nextInt(heightDiff);
				int z = chunkZ * 16 + rand.nextInt(16);
				gen.generate(world, rand, new BlockPos(x, y, z));
			}
		}
	}
	private void runRGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance,
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