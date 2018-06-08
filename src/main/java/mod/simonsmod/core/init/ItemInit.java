package mod.simonsmod.core.init;

import java.util.ArrayList;
import java.util.List;

import mod.simonsmod.core.objects.items.ItemBase;
import mod.simonsmod.core.objects.items.ItemChalk;
import mod.simonsmod.core.objects.items.ItemCrop;
import mod.simonsmod.core.objects.items.ItemJoint;
import mod.simonsmod.core.objects.items.ItemPipe;
import mod.simonsmod.core.objects.items.resource.ItemResource;
import mod.simonsmod.core.objects.items.resource.ItemCrafting;
import mod.simonsmod.core.objects.items.tools.ToolAxe;
import mod.simonsmod.core.objects.items.tools.ToolDamagable;
import mod.simonsmod.core.objects.items.tools.ToolLaser;
import mod.simonsmod.core.objects.items.tools.ToolPaxel;
import mod.simonsmod.core.objects.items.tools.ToolPickaxe;
import mod.simonsmod.core.objects.items.tools.ToolShovel;
import mod.simonsmod.core.objects.items.tools.ToolWrench;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemInit {

	public static final List<Item> ITEMBLOCKS = new ArrayList<Item>();
	public static final List<Item> ITEMS = new ArrayList<Item>();
	public static final ToolMaterial TOOL_BRONZE = EnumHelper.addToolMaterial("tool_bronze", 2, 150, 6.0F, 2.0F, 14);
	
	
	//public static final Item BLOCK_ = new ToolPickaxe("pickaxe_bronze", TOOL_BRONZE);
	
	public static final Item MATERIAL = new ItemResource("material");
	public static final Item CRAFTING = new ItemCrafting("crafting");

	public static final Item PICKAXE_BRONZE = new ToolPickaxe("pickaxe_bronze", TOOL_BRONZE);
	public static final Item SHOVEL_BRONZE = new ToolShovel("shovel_bronze", TOOL_BRONZE);
	public static final Item AXE_BRONZE = new ToolAxe("axe_bronze", TOOL_BRONZE);
	public static final Item PAXEL_BRONZE = new ToolPaxel("paxel_bronze", TOOL_BRONZE);
	public static final Item PAXEL_IRON = new ToolPaxel("paxel_iron", ToolMaterial.IRON);
	public static final Item PAXEL_DIAMOND = new ToolPaxel("paxel_diamond", ToolMaterial.DIAMOND);
	public static final Item TOOL_HAMMER = new ToolDamagable("tool_hammer", 150);
	public static final Item TOOL_MORTARPESTLE = new ToolDamagable("tool_mortarpestle", 150);
	public static final Item TOOL_WRENCH = new ToolWrench("tool_wrench", 250);
	public static final Item LASER = new ToolLaser("laser", 250);
	
	public static final Item CANNABIS = new ItemCrop("cannabis", 1, false, BlockInit.CROP_CANNABIS);
	public static final Item PIPE = new ItemPipe("classic_pipe");
	public static final Item JOINT = new ItemJoint("joint");
	public static final Item CHALK = new ItemChalk("chalk");
	public static final Item CLAY_CERAMIC = new ItemBase("clay_ceramic");
	public static final Item TILE_CERAMIC = new ItemBase("tile_ceramic");
	//public static final Item SEEDS_CANNABUS = new ItemSeed("seeds_cannabus", BlockInit.CROPS_CANNABUS, Blocks.FARMLAND);

	
}
