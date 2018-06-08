package mod.simonsmod.core.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelArmorStand - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelLaser extends ModelBase {
    public ModelRenderer field_178737_d;
    public ModelRenderer field_178737_d_1;

    public ModelLaser() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.field_178737_d = new ModelRenderer(this, 0, 0);
        this.field_178737_d.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_178737_d.addBox(0.0F, -1.5F, -8.0F, 0, 3, 16, 0.0F);
        this.field_178737_d_1 = new ModelRenderer(this, 0, 0);
        this.field_178737_d_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_178737_d_1.addBox(0.0F, -1.5F, -8.0F, 0, 3, 16, 0.0F);
        this.setRotateAngle(field_178737_d_1, 0.0F, 0.0F, 1.5707963267948966F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.field_178737_d.render(f5);
        this.field_178737_d_1.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
