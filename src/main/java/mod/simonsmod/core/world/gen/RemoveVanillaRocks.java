package mod.simonsmod.core.world.gen;

import java.util.HashMap;
import java.util.Map;

import mod.simonsmod.core.config.MainConfig;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RemoveVanillaRocks {
	private Map<EventType, Boolean> disabledMinables = new HashMap();

	@SubscribeEvent
	public void onOreGenMinable(GenerateMinable event) {
		EventType type = event.getType();
		if (MainConfig.changeVanillaStones) {
			//if the event of the vanilla stones spawning it will tell it nah dont do that
			if (event.getType() == (EventType.GRANITE)) {
				event.setResult(Result.DENY);
			} else if (event.getType().equals(EventType.ANDESITE)) {
				event.setResult(Result.DENY);
			} else if (event.getType().equals(EventType.DIORITE)) {
				event.setResult(Result.DENY);
			}
		}
		
		if (MainConfig.changeVanillaOres) {
			//if the event of the vanilla stones spawning it will tell it nah dont do that
			if (event.getType() == (EventType.IRON)) {
				event.setResult(Result.DENY);
			} else if (event.getType().equals(EventType.COAL)) {
				event.setResult(Result.DENY);
			} else if (event.getType().equals(EventType.DIAMOND)) {
				event.setResult(Result.DENY);
			} else if (event.getType().equals(EventType.GOLD)) {
				event.setResult(Result.DENY);
			} else if (event.getType().equals(EventType.LAPIS)) {
				event.setResult(Result.DENY);
			} else if (event.getType().equals(EventType.REDSTONE)) {
				event.setResult(Result.DENY);
			}
		}

	}
}
