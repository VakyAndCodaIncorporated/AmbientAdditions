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
public class HawaiianHoneyCreeperModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer legRight;
    public ModelRenderer legLeft;
    public ModelRenderer beak;
    public ModelRenderer wingLeft;
    public ModelRenderer tail;
    public ModelRenderer wingRight;

    public HawaiianHoneyCreeperModel() {
        this.texWidth = 32;
        this.texHeight = 16;
        this.legLeft = new ModelRenderer(this, 6, 0);
        this.legLeft.setPos(1.0F, 2.5F, 0.0F);
        this.legLeft.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.wingLeft = new ModelRenderer(this, 0, 1);
        this.wingLeft.mirror = true;
        this.wingLeft.setPos(1.5F, 0.0F, 1.0F);
        this.wingLeft.addBox(0.0F, 0.0F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 8);
        this.body.setPos(0.0F, 19.5F, 0.0F);
        this.body.addBox(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.1F, 0.0F, 0.0F);
        this.wingRight = new ModelRenderer(this, 0, 1);
        this.wingRight.mirror = true;
        this.wingRight.setPos(-1.5F, 0.0F, 1.0F);
        this.wingRight.addBox(-1.0F, 0.0F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 13, 12);
        this.tail.setPos(0.0F, 1.5F, 1.5F);
        this.tail.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.beak = new ModelRenderer(this, 9, 3);
        this.beak.setPos(0.0F, -0.5F, -1.5F);
        this.beak.addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.legRight = new ModelRenderer(this, 6, 0);
        this.legRight.setPos(-1.0F, 2.5F, 0.0F);
        this.legRight.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.legLeft);
        this.body.addChild(this.wingLeft);
        this.body.addChild(this.wingRight);
        this.body.addChild(this.tail);
        this.body.addChild(this.beak);
        this.body.addChild(this.legRight);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
