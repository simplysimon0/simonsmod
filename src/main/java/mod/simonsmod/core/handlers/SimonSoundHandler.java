package mod.simonsmod.core.handlers;

import mod.simonsmod.core.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SimonSoundHandler {

	private static int size = 0;

	public static SoundEvent ALARM, CLICK, WRENCH, LASER;

	public static void registerSounds() {
		ALARM = registerSound("alarm");
		CLICK = registerSound("click");
		WRENCH = registerSound("wrench");
		LASER = registerSound("laser");
	}

	public static SoundEvent registerSound(String name) {
		ResourceLocation location = new ResourceLocation(Reference.MODID, name);
		SoundEvent e = new SoundEvent(location);
		e.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(e);
		// SoundEvent.REGISTRY.register(size, location, e);
		// size++;
		return e;
	}
}
