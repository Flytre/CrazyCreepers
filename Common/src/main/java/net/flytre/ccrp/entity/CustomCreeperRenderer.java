package net.flytre.ccrp.entity;

import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.Identifier;

public class CustomCreeperRenderer extends CreeperEntityRenderer {
    private final Identifier texture;

    public CustomCreeperRenderer(EntityRendererFactory.Context context, Identifier texture) {
        super(context);
        this.texture = texture;
    }

    @Override
    public Identifier getTexture(CreeperEntity entity) {
        return texture;
    }

}
