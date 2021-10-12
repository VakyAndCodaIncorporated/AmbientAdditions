package coda.ambientadditions.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GiantLandSnailModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer mouth;
    public ModelRenderer eyes;
    public ModelRenderer shell1;
    public ModelRenderer shell2;

    public GiantLandSnailModel() {
        this.texWidth = 64;
        this.texHeight = 32;
        this.eyes = new ModelRenderer(this, 0, 0);
        this.eyes.setPos(0.0F, -1.0F, -3.5F);
        this.eyes.addBox(-1.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.shell1 = new ModelRenderer(this, 0, 10);
        this.shell1.setPos(0.0F, -1.0F, 0.0F);
        this.shell1.addBox(-2.0F, -3.0F, -1.0F, 4.0F, 4.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.mouth = new ModelRenderer(this, 0, 4);
        this.mouth.setPos(0.0F, 0.5F, -4.0F);
        this.mouth.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.shell2 = new ModelRenderer(this, 0, 20);
        this.shell2.setPos(0.0F, -0.5F, 5.0F);
        this.shell2.addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 23.0F, 0.0F);
        this.body.addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.eyes);
        this.body.addChild(this.shell1);
        this.body.addChild(this.mouth);
        this.shell1.addChild(this.shell2);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
