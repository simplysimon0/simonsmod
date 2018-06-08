package mod.simonsmod.core.init;

import mod.simonsmod.core.Reference;
import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.entity.laser.EntityLaser;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {

	public static final int ENTITY_LASER = 70;
	public static final int ENTITY_LASER_PERCISION = 70;
	public static final int ENTITY_LASER_EXPLOSIVE = 70;

	public static void registerEntities() {
		registerEntity("laser", EntityLaser.class, ENTITY_LASER, 500);
		registerEntity("laser_percision", EntityLaser.class, ENTITY_LASER_PERCISION, 500);
		registerEntity("laser_explosive", EntityLaser.class, ENTITY_LASER_EXPLOSIVE, 500);
	}

	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id,
				SimonsMod.instance, range, 1, true);
	}

	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1,
			int color2) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id,
				SimonsMod.instance, range, 1, true, color1, color2);
	}
}
