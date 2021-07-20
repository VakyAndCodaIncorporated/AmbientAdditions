package coda.ambientadditions.client.model;

import coda.ambientadditions.common.entities.WhiteFruitBatEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WhiteFruitBatModel<T extends Entity> extends EntityModel<WhiteFruitBatEntity> {
    public ModelRenderer body;
    public ModelRenderer earLeft;
    public ModelRenderer earRight;
    public ModelRenderer nose;
    public ModelRenderer wingLeft1;
    public ModelRenderer wingRight1;
    public ModelRenderer legs;
    public ModelRenderer wingLeft2;
    public ModelRenderer wingRight2;

    public WhiteFruitBatModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.wingRight2 = new ModelRenderer(this, 4, 9);
        this.wingRight2.mirror = true;
        this.wingRight2.setPos(-4.0F, 0.0F, -3.0F);
        this.wingRight2.addBox(-8.0F, 0.0F, -2.0F, 8.0F, 0.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.earRight = new ModelRenderer(this, 0, 3);
        this.earRight.setPos(-1.5F, -1.5F, -2.0F);
        this.earRight.addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(earRight, 0.0F, 0.3490658503988659F, -0.4363323129985824F);
        this.wingLeft1 = new ModelRenderer(this, -7, 9);
        this.wingLeft1.setPos(1.5F, 0.5F, 1.5F);
        this.wingLeft1.addBox(0.0F, 0.0F, -4.0F, 4.0F, 0.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.earLeft = new ModelRenderer(this, 0, 3);
        this.earLeft.mirror = true;
        this.earLeft.setPos(1.5F, -1.5F, -2.0F);
        this.earLeft.addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(earLeft, 0.0F, -0.3490658503988659F, 0.4363323129985824F);
        this.wingRight1 = new ModelRenderer(this, -7, 9);
        this.wingRight1.mirror = true;
        this.wingRight1.setPos(-1.5F, 0.5F, 1.5F);
        this.wingRight1.addBox(-4.0F, 0.0F, -4.0F, 4.0F, 0.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.wingLeft2 = new ModelRenderer(this, 4, 9);
        this.wingLeft2.setPos(4.0F, 0.0F, -3.0F);
        this.wingLeft2.addBox(0.0F, 0.0F, -2.0F, 8.0F, 0.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.nose = new ModelRenderer(this, 12, 0);
        this.nose.setPos(0.0F, 0.8F, -2.5F);
        this.nose.addBox(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(nose, 0.5235987755982988F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 1, 1);
        this.body.setPos(0.0F, 22.5F, -0.5F);
        this.body.addBox(-1.5F, -1.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.legs = new ModelRenderer(this, -3, 0);
        this.legs.setPos(0.0F, 0.5F, 2.5F);
        this.legs.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.wingRight1.addChild(this.wingRight2);
        this.body.addChild(this.earRight);
        this.body.addChild(this.wingLeft1);
        this.body.addChild(this.earLeft);
        this.body.addChild(this.wingRight1);
        this.wingLeft1.addChild(this.wingLeft2);
        this.body.addChild(this.nose);
        this.body.addChild(this.legs);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(WhiteFruitBatEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.4f;
        float degree = 1.0f;
        if (!entityIn.isResting()) {
            this.body.xRot = headPitch * ((float)Math.PI / 180F);
            this.body.yRot = netHeadYaw * ((float)Math.PI / 180F);
            this.body.y = MathHelper.cos(-4.0F + limbSwing * speed * 0.3F) * degree * 0.2F * limbSwingAmount;
            this.wingLeft1.zRot = MathHelper.cos(limbSwing * speed * 0.3F) * degree * 1.2F * limbSwingAmount - 0.1F;
            this.wingLeft2.zRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.3F) * degree * 1.4F * limbSwingAmount + 0.2F;
            this.wingRight1.zRot = MathHelper.cos(limbSwing * speed * 0.3F) * degree * -1.2F * limbSwingAmount + 0.1F;
            this.wingRight2.zRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.3F) * degree * -1.4F * limbSwingAmount - 0.2F;
            this.legs.xRot = 0F;
        }
        else if (entityIn.isResting()) {
            this.body.xRot = 0F;
            this.body.yRot = 0F;
            this.body.y = 0F;
            this.wingLeft1.zRot = 0.2618F;
            this.wingLeft2.zRot = 0F;
            this.wingRight1.zRot = -0.2618F;
            this.wingRight2.zRot = 0F;
            this.legs.xRot = -0.32F;
        }
        else {
            this.body.xRot = 0F;
            this.body.yRot = 0F;
            this.body.y = 0F;
            this.wingLeft1.zRot = 0F;
            this.wingLeft2.zRot = 0F;
            this.wingRight1.zRot = 0F;
            this.wingRight2.zRot = 0F;
            this.legs.xRot = 0F;
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
