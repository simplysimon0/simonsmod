package mod.simonsmod.core.world.types;

import mod.simonsmod.core.init.BiomeInit;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;

public class WorldTypeSimon extends WorldType{

	public WorldTypeSimon() {
		super("Simon");
	}
	

	@Override
	public BiomeProvider getBiomeProvider(World world) {
	
		return new BiomeProviderSingle(BiomeInit.FLAT_PLAINS);
	}
}
