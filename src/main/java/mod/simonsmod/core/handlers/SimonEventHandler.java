package mod.simonsmod.core.handlers;

import java.util.List;
import java.util.Random;

import mod.simonsmod.core.config.MainConfig;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.ItemInit;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class SimonEventHandler {
	// In your TutEventHandler class - the name of the method doesn't matter
	// Only the Event type parameter is what's important (see below for
	// explanations of some types)

	@SubscribeEvent
	public void lootLoad(LootTableLoadEvent e)

	{
		if (MainConfig.enableDrugs) {
			if (e.getName().toString().equals("minecraft:chests/simple_dungeon")
					|| e.getName().toString().equals("minecraft:chests/village_blacksmith")
					|| e.getName().toString().equals("minecraft:chests/abandoned_mineshaft")
					|| e.getName().toString().equals("minecraft:chests/desert_pyramid")
					|| e.getName().toString().equals("minecraft:chests/stronghold_crossing"))

			{
				LootEntry entry = new LootEntryItem(ItemInit.PIPE, 15, 60, new LootFunction[0], new LootCondition[0],
						"pipe");
				LootPool pool1 = new LootPool(new LootEntry[] { entry }, new LootCondition[0], new RandomValueRange(1),
						new RandomValueRange(0, 1), "pipePool");
				e.getTable().addPool(pool1);

			}

		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onToolTip(ItemTooltipEvent event) {
		Item item = event.getItemStack().getItem();
		List<String> info = event.getToolTip();
		/*
		 * if(item == Items.IRON_INGOT || item == Items.IRON_NUGGET)
		 * info.add("Fe"); else if(item == Items.GOLD_INGOT || item ==
		 * Items.GOLD_NUGGET) info.add("Ag"); else if(item == Items.DIAMOND)
		 * info.add("C"); else if(item == Items.COAL) info.add("C");
		 */
	}

	@SubscribeEvent
	public void onHarvestBlock(BlockEvent.HarvestDropsEvent event) {
		Random rand = new Random();
		// final int FLINT_CHANCE = 50;
		final EntityPlayer PLAYER = event.getHarvester();
		// null-check before accessing ItemStack (fix #2)
		if (null == PLAYER || null == PLAYER.getHeldItemMainhand())
			return;
		if (MainConfig.changeVanillaStones) {
			if (event.getState() == Blocks.STONE.getBlockState().getBaseState().withProperty(BlockStone.VARIANT,
					BlockStone.EnumType.GRANITE)) {
				event.getDrops().clear();
				event.getDrops().add(new ItemStack(BlockInit.BLOCK_GRANITE, 1, 0));
			} else if (event.getState() == Blocks.STONE.getBlockState().getBaseState().withProperty(BlockStone.VARIANT,
					BlockStone.EnumType.DIORITE)) {
				event.getDrops().clear();
				event.getDrops().add(new ItemStack(BlockInit.BLOCK_DIORITE, 1, 0));
			} else if (event.getState() == Blocks.STONE.getBlockState().getBaseState().withProperty(BlockStone.VARIANT,
					BlockStone.EnumType.ANDESITE)) {
				event.getDrops().clear();
				event.getDrops().add(new ItemStack(BlockInit.BLOCK_ANDESITE, 1, 0));
			}
		}

		if (event.getState() == Blocks.DIRT.getDefaultState() || event.getState() == Blocks.GRASS.getDefaultState()) {
			// event.getDrops().clear();
			if (MainConfig.clayFromDirtChance != 0) {
				if (rand.nextInt(MainConfig.clayFromDirtChance) == 1) {
					event.getDrops().add(new ItemStack(Items.CLAY_BALL, rand.nextInt(2), 0));
				}
			}
		} else if (event.getState() == Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE,
				BlockTallGrass.EnumType.GRASS) && MainConfig.enableDrugs) {
			// event.getDrops().clear();
			if (rand.nextInt(100) == 0) {
				event.getDrops().add(new ItemStack(ItemInit.CANNABIS, 1, 0));
			}
		}

	}

}
