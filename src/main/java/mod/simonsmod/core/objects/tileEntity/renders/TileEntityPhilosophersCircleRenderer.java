package mod.simonsmod.core.objects.tileEntity.renders;

import java.util.Map;
import java.util.UUID;

import javax.annotation.Nullable;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;

import mod.simonsmod.core.Reference;
import mod.simonsmod.core.entity.model.ModelPhilosophersCircle;
import mod.simonsmod.core.objects.tileEntity.TileEntityPhilosophersCircle;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class TileEntityPhilosophersCircleRenderer extends TileEntitySpecialRenderer<TileEntityPhilosophersCircle> {

	private static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation(Reference.MODID + ":textures/blocks/phil_circle.png");

	private final ModelPhilosophersCircle philcircle = new ModelPhilosophersCircle();
	
	
	
	@Override
	public void render(TileEntityPhilosophersCircle te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		ModelPhilosophersCircle modelbase = this.philcircle;
		this.bindTexture(TEXTURE_NORMAL);
        int i = 0;
        GlStateManager.enableDepth();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        GlStateManager.disableCull();
        GlStateManager.disableLighting();
        
        if (te.hasWorld())
        {
            Block block = te.getBlockType();
            i = te.getBlockMetadata();
        }
        int j = 0;
        int xOffSet = 0;
        int zOffSet = 0;
        if (i == 3)
        {
            j = 180;
            xOffSet = 23;
            zOffSet = 23;
        }

        if (i == 4)
        {
            j = -90;
            xOffSet = 23;
        }

        if (i == 5)
        {
        	zOffSet = 23;
            j = 90;
        }

        if (i == 2)
        {
            j = 0;
            
        }
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        
		if(i >= 2 && i <= 5)
        {
			GlStateManager.translate((float)x - 11.0F + (float)xOffSet, (float)y + 0.05F, (float)z - 11.0F + (float)zOffSet);
	        GlStateManager.rotate((float)j, 0.0F, 1.0F, 0.0F);
			GlStateManager.pushMatrix();
        	modelbase.renderAll();
        	GlStateManager.popMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        }
		//GlStateManager.enableLighting();
		GlStateManager.enableCull();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		
		
	}
	
}
