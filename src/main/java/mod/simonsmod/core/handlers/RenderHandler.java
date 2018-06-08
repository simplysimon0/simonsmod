package mod.simonsmod.core.handlers;

import mod.simonsmod.core.entity.laser.EntityLaser;
import mod.simonsmod.core.entity.laser.EntityLaserEntity;
import mod.simonsmod.core.entity.laser.EntityLaserExplosive;
import mod.simonsmod.core.entity.laser.EntityLaserMining;
import mod.simonsmod.core.entity.laser.EntityLaserPrecision;
import mod.simonsmod.core.entity.render.RenderLaser;
import mod.simonsmod.core.entity.render.RenderLaserPrecision;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderHandler 
{
	@SideOnly(Side.CLIENT)
	public static void registerEntityRenders()
	{
		//RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderLaser(Minecraft.getMinecraft().getRenderManager()));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityLaserMining.class, new IRenderFactory<EntityLaserMining>()
		{
			@Override
			public Render<? super EntityLaserMining> createRenderFor(RenderManager manager) 
			{
				return new RenderLaser(manager);
			}
		});
		
		
		
	}
	
	/*	@Override
	public void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityGhost.class, new RenderGhost(Minecraft.getMinecraft().getRenderManager(), new ModelGhost(), 0.4f)); //use depricated method
	}*/
}
