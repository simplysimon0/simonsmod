package mod.simonsmod.core.init;

import java.util.ArrayList;
import java.util.List;

import mod.simonsmod.core.objects.blocks.BlockAlloy;
import mod.simonsmod.core.objects.blocks.BlockChalk;
import mod.simonsmod.core.objects.blocks.BlockColorLight;
import mod.simonsmod.core.objects.blocks.BlockCrop;
import mod.simonsmod.core.objects.blocks.BlockCropPot;
import mod.simonsmod.core.objects.blocks.BlockLab;
import mod.simonsmod.core.objects.blocks.BlockMetal;
import mod.simonsmod.core.objects.blocks.BlockMetalOre;
import mod.simonsmod.core.objects.blocks.BlockMiscOre;
import mod.simonsmod.core.objects.blocks.BlockOmniSlab;
import mod.simonsmod.core.objects.blocks.BlockOmniWall;
import mod.simonsmod.core.objects.blocks.BlockPillar;
import mod.simonsmod.core.objects.blocks.BlockRock;
import mod.simonsmod.core.objects.blocks.BlockSoil;
import mod.simonsmod.core.objects.blocks.BlockSprinkler;
import mod.simonsmod.core.objects.blocks.BlockTile;
import mod.simonsmod.core.objects.blocks.BlockWireRedstone;
import mod.simonsmod.core.objects.blocks.tileentities.BlockAlarm;
import mod.simonsmod.core.objects.blocks.tileentities.BlockBreaker;
import mod.simonsmod.core.objects.blocks.tileentities.BlockEnergyCube;
import mod.simonsmod.core.objects.blocks.tileentities.BlockHopperDuct;
import mod.simonsmod.core.objects.blocks.tileentities.BlockOmniHopperOld;
import mod.simonsmod.core.objects.blocks.tileentities.BlockPedestal;
import mod.simonsmod.core.objects.blocks.tileentities.BlockPipe;
import mod.simonsmod.core.objects.blocks.tileentities.BlockPipeRedstone;
import mod.simonsmod.core.objects.blocks.tileentities.BlockPlacer;
import mod.simonsmod.core.objects.blocks.tileentities.BlockTileEntityTest;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//Machines
	//public static final Block CRUSHER = new BlockCrusher("crusher");
	public static final Block HOPPER_DUCT = new BlockHopperDuct("hopper_duct");
	//public static final Block HOPPER_OMNI = new BlockOmniHopper("hopper_omni");
	public static final Block HOPPER_OMNI = new BlockOmniHopperOld("hopper_omni");
	public static final Block BLOCK_ALARM = new BlockAlarm("alarm");
	public static final Block PIPE = new BlockPipe("pipe");
	//public static final Block PIPE_REDSTONE = new BlockPipeRedstone("pipe_redstone");
	//public static final Block WIRE_REDSTONE = new BlockWireRedstone("wire_redstone");
	public static final Block ENERGY_CUBE = new BlockEnergyCube("energy_cube");
	
	public static final Block PLACER = new BlockPlacer("placer");
	public static final Block BREAKER = new BlockBreaker("breaker");
	
	public static final Block BLOCK_PEDASTAL = new BlockPedestal("pedastal");
	//public static final Block BLOCK_WORK_TABLE = new BlockWorkTable("work_table");
	//public static final Block BLOCK_TILE_ENTITY_TEST = new BlockTileEntityTest("tile_entity_test");
	//Ore
	public static final Block BLOCK_METAL_ORE = new BlockMetalOre("metal_ore");
	public static final Block BLOCK_MISC_ORE = new BlockMiscOre("misc_ore");
	//Blocks
	public static final Block BLOCK_METAL = new BlockMetal("metal");
	public static final Block BLOCK_ALLOY = new BlockAlloy("alloy");

	//Rocks
	public static final Block BLOCK_GRANITE = new BlockRock("granite");
	public static final Block BLOCK_DIORITE = new BlockRock("diorite");
	public static final Block BLOCK_ANDESITE = new BlockRock("andesite");
	public static final Block BLOCK_MARBLE = new BlockRock("marble");
	public static final Block BLOCK_BASALT = new BlockRock("basalt");
	public static final Block BLOCK_GNEISS = new BlockRock("gneiss");
	public static final Block BLOCK_SOIL = new BlockSoil("soil");
	//Decoration Blocks
	public static final Block BLOCK_TILE = new BlockTile("tile");
	public static final Block BLOCK_LAB = new BlockLab("lab"); 
	public static final Block BLOCK_LAB_PILLAR = new BlockPillar("lab_pillar");
	public static final Block LAB_HALF_SLAB = new BlockOmniSlab("lab_half_slab", Material.ROCK, 1.5F, 10);
	public static final Block LAB_WALL = new BlockOmniWall("lab_wall", Material.ROCK, 1.5F, 10);
	//Decoration Models
	//public static final Block BLOCK_CHAIR = new BlockChair("block_chair");
	//Alchemy
	public static final Block BLOCK_CHALK = new BlockChalk("chalk");
	//Machines
	public static final Block CROP_CANNABIS = new BlockCrop("crop_cannabis", ItemInit.CANNABIS, ItemInit.CANNABIS);

	public static final Block BLOCK_CROP_POT = new BlockCropPot("crop_pot");
	public static final Block BLOCK_SPRINKLER = new BlockSprinkler("sprinkler");
	
	public static final Block COLOR_LAMP = new BlockColorLight("color_lamp", false);
	public static final Block LIT_COLOR_LAMP = new BlockColorLight("lit_color_lamp", true);
	//public static final Block BLOCK_FLUID_MERCURY = new FluidMercury.BlockFulidMercury("block_fluid_mercury", FluidInit.FLUID_MERCURY);

}
