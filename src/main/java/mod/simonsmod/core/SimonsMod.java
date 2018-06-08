package mod.simonsmod.core;

import mod.simonsmod.core.handlers.RegistryHandler;
import mod.simonsmod.core.objects.tileEntity.TileEntityPhilosophersCircle;
import mod.simonsmod.core.objects.tileEntity.TileEntityPipe;
import mod.simonsmod.core.objects.tileEntity.renders.TileEntityPhilosophersCircleRenderer;
import mod.simonsmod.core.objects.tileEntity.renders.TileEntityPipeRender;
import mod.simonsmod.core.proxy.CommonProxy;
import mod.simonsmod.core.util.SimonsTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY)
public class SimonsMod {
	@Instance
	public static SimonsMod instance;

	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
	public static CommonProxy proxy;

	public static final CreativeTabs SIMONSTAB = new SimonsTab(CreativeTabs.getNextID(), "Simon's Mod");

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		RegistryHandler.otherPreRegistries();
	}

	@SideOnly(Side.CLIENT)
	@EventHandler
	public static void clientInit(FMLInitializationEvent event) {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPhilosophersCircle.class, new TileEntityPhilosophersCircleRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPipe.class, new TileEntityPipeRender());
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {

		RegistryHandler.otherInitRegistries();
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		RegistryHandler.otherPostInitRegistries();
	}
}
