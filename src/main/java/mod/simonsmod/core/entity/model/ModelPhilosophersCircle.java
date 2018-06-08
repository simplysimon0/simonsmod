package mod.simonsmod.core.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPhilosophersCircle extends ModelBase
{
    /** The chest lid in the chest's model. */
    public ModelRenderer main = (new ModelRenderer(this, 0, 0)).setTextureSize(368, 368);


    public ModelPhilosophersCircle()
    {
        this.main.addBox(0.0F, 0.05F, 0.0F, 368, 0, 368, 0.0F);
        //this.chestLid.rotationPointX = 1.0F;
        //this.chestLid.rotationPointY = 7.0F;
        //this.chestLid.rotationPointZ = 15.0F;
        //System.out.println("Working Maybe?");
    }

    /**
     * This method renders out all parts of the chest model.
     */
    public void renderAll()
    {
        //this.chestKnob.rotateAngleX = this.chestLid.rotateAngleX;
        this.main.render(0.0625F);
    }
}