package net.flytre.ccrp.entity;

import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.math.MathHelper;

public class GiantCreeperRenderer extends CreeperEntityRenderer {

    private final float scale;

    public GiantCreeperRenderer(EntityRendererFactory.Context context, float scale) {
        super(context);
        this.scale = scale;
    }


    @Override
    protected void scale(CreeperEntity creeperEntity, MatrixStack matrixStack, float f) {
        float g = creeperEntity.getClientFuseTime(f);
        float h = 1.0F + MathHelper.sin(g * 100.0F) * g * 0.01F;
        g = MathHelper.clamp(g, 0.0F, 1.0F);
        g *= g;
        g *= g;
        float i = (1.0F + g * 0.4F) * h;
        float j = (1.0F + g * 0.1F) / h;
        matrixStack.scale(i*scale, j*scale, i*scale);
    }
}
