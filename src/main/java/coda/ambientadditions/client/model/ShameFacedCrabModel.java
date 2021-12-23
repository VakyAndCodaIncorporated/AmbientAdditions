package coda.ambientadditions.client.model;

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
public class ShameFacedCrabModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer armLeft;
    public ModelRenderer armRight;
    public ModelRenderer eyes;
    public ModelRenderer legLeft1;
    public ModelRenderer legLeft2;
    public ModelRenderer legLeft3;
    public ModelRenderer legLeft4;
    public ModelRenderer legRight1;
    public ModelRenderer legRight2;
    public ModelRenderer legRight3;
    public ModelRenderer legRight4;
    public ModelRenderer clawLeft;
    public ModelRenderer spikesLeft;
    public ModelRenderer clawRight;
    public ModelRenderer spikesRight;

    public ShameFacedCrabModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.armLeft = new ModelRenderer(this, 0, 10);
        this.armLeft.setPos(4.5F, 1.0F, -0.5F);
        this.armLeft.addBox(-4.0F, -2.0F, -2.0F, 4.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(armLeft, 0.2617993877991494F, -0.6108652381980153F, 0.0F);
        this.armRight = new ModelRenderer(this, 0, 10);
        this.armRight.mirror = true;
        this.armRight.setPos(-4.5F, 1.0F, -0.5F);
        this.armRight.addBox(0.0F, -2.0F, -2.0F, 4.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(armRight, 0.2617993877991494F, 0.6108652381980153F, 0.0F);
        this.legLeft1 = new ModelRenderer(this, 0, 0);
        this.legLeft1.setPos(3.5F, 1.5F, -0.5F);
        this.legLeft1.addBox(0.0F, -2.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legLeft1, 0.0F, 0.17453292519943295F, -0.2617993877991494F);
        this.spikesRight = new ModelRenderer(this, 0, 15);
        this.spikesRight.setPos(2.5F, -2.0F, -1.0F);
        this.spikesRight.addBox(-1.5F, -1.0F, 0.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 1, 0);
        this.body.setPos(0.0F, 21.5F, 0.0F);
        this.body.addBox(-3.5F, -2.5F, -2.5F, 7.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.legRight1 = new ModelRenderer(this, 0, 0);
        this.legRight1.mirror = true;
        this.legRight1.setPos(-3.5F, 1.5F, -0.5F);
        this.legRight1.addBox(-3.0F, -2.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legRight1, 0.0F, -0.17453292519943295F, 0.2617993877991494F);
        this.legRight3 = new ModelRenderer(this, 0, 0);
        this.legRight3.mirror = true;
        this.legRight3.setPos(-3.5F, 1.5F, 1.1F);
        this.legRight3.addBox(-3.0F, -2.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legRight3, 0.0F, 0.08726646259971647F, 0.2617993877991494F);
        this.legLeft4 = new ModelRenderer(this, 0, 0);
        this.legLeft4.setPos(3.5F, 1.5F, 1.9F);
        this.legLeft4.addBox(0.0F, -2.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legLeft4, 0.0F, -0.17453292519943295F, -0.2617993877991494F);
        this.legLeft3 = new ModelRenderer(this, 0, 0);
        this.legLeft3.setPos(3.5F, 1.5F, 1.1F);
        this.legLeft3.addBox(0.0F, -2.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legLeft3, 0.0F, -0.08726646259971647F, -0.2617993877991494F);
        this.spikesLeft = new ModelRenderer(this, 0, 15);
        this.spikesLeft.setPos(-2.5F, -2.0F, -1.0F);
        this.spikesLeft.addBox(-1.5F, -1.0F, 0.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.clawRight = new ModelRenderer(this, 12, 10);
        this.clawRight.mirror = true;
        this.clawRight.setPos(4.0F, -0.5F, -1.0F);
        this.clawRight.addBox(0.0F, -1.5F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.legRight4 = new ModelRenderer(this, 0, 0);
        this.legRight4.mirror = true;
        this.legRight4.setPos(-3.5F, 1.5F, 1.9F);
        this.legRight4.addBox(-3.0F, -2.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legRight4, 0.0F, 0.17453292519943295F, 0.2617993877991494F);
        this.legRight2 = new ModelRenderer(this, 0, 0);
        this.legRight2.mirror = true;
        this.legRight2.setPos(-3.5F, 1.5F, 0.3F);
        this.legRight2.addBox(-3.0F, -2.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legRight2, 0.0F, -0.08726646259971647F, 0.2617993877991494F);
        this.eyes = new ModelRenderer(this, 7, 15);
        this.eyes.setPos(0.0F, -1.0F, -2.5F);
        this.eyes.addBox(-1.5F, -2.0F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(eyes, 0.17453292519943295F, 0.0F, 0.0F);
        this.legLeft2 = new ModelRenderer(this, 0, 0);
        this.legLeft2.setPos(3.5F, 1.5F, 0.3F);
        this.legLeft2.addBox(0.0F, -2.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legLeft2, 0.0F, 0.08726646259971647F, -0.2617993877991494F);
        this.clawLeft = new ModelRenderer(this, 12, 10);
        this.clawLeft.setPos(-4.0F, -0.5F, -1.0F);
        this.clawLeft.addBox(-2.0F, -1.5F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.armLeft);
        this.body.addChild(this.armRight);
        this.body.addChild(this.legLeft1);
        this.armRight.addChild(this.spikesRight);
        this.body.addChild(this.legRight1);
        this.body.addChild(this.legRight3);
        this.body.addChild(this.legLeft4);
        this.body.addChild(this.legLeft3);
        this.armLeft.addChild(this.spikesLeft);
        this.armRight.addChild(this.clawRight);
        this.body.addChild(this.legRight4);
        this.body.addChild(this.legRight2);
        this.body.addChild(this.eyes);
        this.body.addChild(this.legLeft2);
        this.armLeft.addChild(this.clawLeft);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 1.0f;
        this.body.zRot = MathHelper.cos(1.0F + f * speed * 0.4F) * degree * 0.2F * f1;
        this.eyes.xRot = MathHelper.cos(-1.0F + f * speed * 0.4F) * degree * 0.4F * f1 + 0.2F;
        this.armLeft.y = MathHelper.cos(-1.0F + f * speed * 0.4F) * degree * 0.1F * f1 + 1.0F;
        this.armRight.y = MathHelper.cos(-3.0F + f * speed * 0.4F) * degree * -0.1F * f1 + 1.0F;
        this.legLeft1.zRot = MathHelper.cos(1.0F + f * speed * 0.4F) * degree * 0.8F * f1 - 0.1F;
        this.legLeft2.zRot = MathHelper.cos(4.0F + f * speed * 0.4F) * degree * 1.0F * f1 - 0.1F;
        this.legLeft3.zRot = MathHelper.cos(1.0F + f * speed * 0.4F) * degree * 0.8F * f1 - 0.1F;
        this.legLeft4.zRot = MathHelper.cos(4.0F + f * speed * 0.4F) * degree * 1.0F * f1 - 0.1F;
        this.legLeft1.y = MathHelper.cos(1.0F + f * speed * 0.4F) * degree * 0.1F * f1 + 1.5F;
        this.legLeft2.y = MathHelper.cos(4.0F + f * speed * 0.4F) * degree * 0.1F * f1 + 1.5F;
        this.legLeft3.y = MathHelper.cos(1.0F + f * speed * 0.4F) * degree * 0.1F * f1 + 1.5F;
        this.legLeft4.y = MathHelper.cos(4.0F + f * speed * 0.4F) * degree * 0.1F * f1 + 1.5F;
        this.legRight1.zRot = MathHelper.cos(1.0F + f * speed * 0.4F) * degree * 0.8F * f1 + 0.1F;
        this.legRight2.zRot = MathHelper.cos(4.0F + f * speed * 0.4F) * degree * 1.0F * f1 + 0.1F;
        this.legRight3.zRot = MathHelper.cos(1.0F + f * speed * 0.4F) * degree * 0.8F * f1 + 0.1F;
        this.legRight4.zRot = MathHelper.cos(4.0F + f * speed * 0.4F) * degree * 1.0F * f1 + 0.1F;
        this.legRight1.y = MathHelper.cos(1.0F + f * speed * 0.4F) * degree * 0.1F * f1 + 1.5F;
        this.legRight2.y = MathHelper.cos(4.0F + f * speed * 0.4F) * degree * 0.1F * f1 + 1.5F;
        this.legRight3.y = MathHelper.cos(1.0F + f * speed * 0.4F) * degree * 0.1F * f1 + 1.5F;
        this.legRight4.y = MathHelper.cos(4.0F + f * speed * 0.4F) * degree * 0.1F * f1 + 1.5F;

    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
