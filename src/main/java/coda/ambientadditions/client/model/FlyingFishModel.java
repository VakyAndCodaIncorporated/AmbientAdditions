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
public class FlyingFishModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer tail;
    public ModelRenderer wingLeft;
    public ModelRenderer wingRight;
    public ModelRenderer backWingLeft;
    public ModelRenderer backWingRight;
    public ModelRenderer dorsalFin;

    public FlyingFishModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.backWingRight = new ModelRenderer(this, -2, 0);
        this.backWingRight.setPos(-1.0F, 0.5F, 2.5F);
        this.backWingRight.addBox(-3.0F, 0.0F, -1.0F, 3.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.wingLeft = new ModelRenderer(this, -3, 12);
        this.wingLeft.mirror = true;
        this.wingLeft.setPos(1.0F, -0.5F, -0.5F);
        this.wingLeft.addBox(0.0F, 0.0F, -1.5F, 8.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.wingRight = new ModelRenderer(this, -3, 12);
        this.wingRight.setPos(-1.0F, -0.5F, -0.5F);
        this.wingRight.addBox(-8.0F, 0.0F, -1.5F, 8.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 22.5F, 0.0F);
        this.body.addBox(-1.0F, -1.5F, -4.5F, 2.0F, 3.0F, 9.0F, 0.0F, 0.0F, 0.0F);
        this.backWingLeft = new ModelRenderer(this, -2, 0);
        this.backWingLeft.mirror = true;
        this.backWingLeft.setPos(1.0F, 0.5F, 2.5F);
        this.backWingLeft.addBox(0.0F, 0.0F, -1.0F, 3.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 0, 0);
        this.tail.setPos(0.0F, 0.0F, 4.5F);
        this.tail.addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.dorsalFin = new ModelRenderer(this, 0, 4);
        this.dorsalFin.setPos(0.0F, -1.5F, 2.0F);
        this.dorsalFin.addBox(0.0F, -1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.backWingRight);
        this.body.addChild(this.wingLeft);
        this.body.addChild(this.wingRight);
        this.body.addChild(this.backWingLeft);
        this.body.addChild(this.tail);
        this.body.addChild(this.dorsalFin);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.body.xRot = headPitch * ((float)Math.PI / 180F);
        this.body.yRot = netHeadYaw * ((float)Math.PI / 180F);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
