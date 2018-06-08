package mod.simonsmod.core.objects.tileEntity.renders;

import java.util.Map;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.primitives.SignedBytes;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;

import mod.simonsmod.core.Reference;
import mod.simonsmod.core.entity.model.ModelPhilosophersCircle;
import mod.simonsmod.core.objects.tileEntity.TileEntityPhilosophersCircle;
import mod.simonsmod.core.objects.tileEntity.TileEntityPipe;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class TileEntityPipeRender extends TileEntitySpecialRenderer<TileEntityPipe> {

	private RenderEntityItem renderItem;

	public TileEntityPipeRender() {
		renderItem = new RenderEntityItem(Minecraft.getMinecraft().getRenderManager(),Minecraft.getMinecraft().getRenderItem()) {
            //@Override
            //public boolean shouldBob() {return false;}
            //@Override
            //public boolean shouldSpreadItems() {return false;}
            
        };
    }
	
	@Override
	public void render(TileEntityPipe te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		ItemStack stack = te.getStackInSlot(0);
		if (stack != null) {
			//if(te.getStackInSlot(i) != null) {
                EntityItem customItem = new EntityItem(te.getWorld());
                customItem.hoverStart = 0.0F;
                customItem.setItem(stack);
                GlStateManager.pushMatrix();
                GlStateManager.translate((float)x,(float)y,(float)z);
                GlStateManager.translate(0.5F,0.17F,0.5F);
                GlStateManager.rotate(0.0F,0.0F,1.0F,0.0F);
                if(stack.getItem() instanceof ItemBlock)
                	GlStateManager.scale(1.0F,1.0F,1.0F);
                else
                	GlStateManager.scale(0.7F,0.7F,0.7F);
                renderItem.doRender(customItem,0,0,0,0,0);
                GlStateManager.popMatrix();
		//}
		}
	}

}
