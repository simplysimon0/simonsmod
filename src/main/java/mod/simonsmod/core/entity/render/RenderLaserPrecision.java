package mod.simonsmod.core.entity.render;

import mod.simonsmod.core.Reference;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLaserPrecision extends RenderLaserAbstract
{
	public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":textures/entity/laser.png");

    public RenderLaserPrecision(RenderManager renderManagerIn)
    {
        super(renderManagerIn);

    }
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return TEXTURE;
    }
}
