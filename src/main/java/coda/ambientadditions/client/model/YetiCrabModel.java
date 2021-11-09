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
public class YetiCrabModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer leftLeg1;
    public ModelRenderer leftLeg2;
    public ModelRenderer leftLeg3;
    public ModelRenderer rightLeg1;
    public ModelRenderer rightLeg2;
    public ModelRenderer rightLeg3;
    public ModelRenderer furRight;
    public ModelRenderer furLeft;
    public ModelRenderer rostrum;
    public ModelRenderer clawRight;
    public ModelRenderer armRight;
    public ModelRenderer clawLeft;
    public ModelRenderer armLeft;

    public YetiCrabModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.rightLeg3 = new ModelRenderer(this, 0, 22);
        this.rightLeg3.setPos(-2.5F, 1.0F, 1.5F);
        this.rightLeg3.addBox(-4.0F, -0.5F, 0.0F, 4.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightLeg3, 0.0F, 1.1344640137963142F, 0.0F);
        this.rightLeg2 = new ModelRenderer(this, 0, 20);
        this.rightLeg2.setPos(-2.5F, 1.0F, 1.0F);
        this.rightLeg2.addBox(-5.0F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightLeg2, 0.0F, 0.5235987755982988F, 0.0F);
        this.furRight = new ModelRenderer(this, 0, 9);
        this.furRight.mirror = true;
        this.furRight.setPos(-2.0F, 0.5F, -2.0F);
        this.furRight.addBox(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(furRight, 0.0F, 0.2617993877991494F, -0.03490658503988659F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 22.5F, 0.0F);
        this.body.addBox(-2.5F, -1.5F, -3.0F, 5.0F, 3.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.clawRight = new ModelRenderer(this, 0, 16);
        this.clawRight.mirror = true;
        this.clawRight.setPos(-0.5F, 0.0F, -4.2F);
        this.clawRight.addBox(-0.5F, -0.5F, -3.0F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(clawRight, 0.0F, -0.3490658503988659F, 0.0F);
        this.rostrum = new ModelRenderer(this, 0, 0);
        this.rostrum.setPos(0.0F, -1.0F, -3.0F);
        this.rostrum.addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.clawLeft = new ModelRenderer(this, 0, 16);
        this.clawLeft.setPos(0.5F, 0.0F, -4.2F);
        this.clawLeft.addBox(-2.5F, -0.5F, -3.0F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(clawLeft, 0.0F, 0.3490658503988659F, 0.0F);
        this.furLeft = new ModelRenderer(this, 0, 9);
        this.furLeft.setPos(2.0F, 0.5F, -2.0F);
        this.furLeft.addBox(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(furLeft, 0.0F, -0.2617993877991494F, 0.03490658503988659F);
        this.armRight = new ModelRenderer(this, 0, 24);
        this.armRight.setPos(0.0F, 0.0F, 0.0F);
        this.armRight.addBox(-0.5F, -0.5F, -5.0F, 1.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.rightLeg1 = new ModelRenderer(this, 0, 20);
        this.rightLeg1.setPos(-2.5F, 1.0F, 0.5F);
        this.rightLeg1.addBox(-5.0F, -0.5F, -1.0F, 5.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightLeg1, 0.0F, -0.4363323129985824F, 0.0F);
        this.leftLeg1 = new ModelRenderer(this, 0, 20);
        this.leftLeg1.mirror = true;
        this.leftLeg1.setPos(2.5F, 1.0F, 0.5F);
        this.leftLeg1.addBox(0.0F, -0.5F, -1.0F, 5.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftLeg1, 0.0F, 0.4363323129985824F, 0.0F);
        this.leftLeg3 = new ModelRenderer(this, 0, 22);
        this.leftLeg3.setPos(2.5F, 1.0F, 1.5F);
        this.leftLeg3.addBox(0.0F, -0.5F, 0.0F, 4.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftLeg3, 0.0F, -1.1344640137963142F, 0.0F);
        this.armLeft = new ModelRenderer(this, 0, 24);
        this.armLeft.setPos(0.0F, 0.0F, 0.0F);
        this.armLeft.addBox(-0.5F, -0.5F, -5.0F, 1.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.leftLeg2 = new ModelRenderer(this, 0, 20);
        this.leftLeg2.mirror = true;
        this.leftLeg2.setPos(2.5F, 1.0F, 1.0F);
        this.leftLeg2.addBox(0.0F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftLeg2, 0.0F, -0.5235987755982988F, 0.0F);
        this.body.addChild(this.rightLeg3);
        this.body.addChild(this.rightLeg2);
        this.body.addChild(this.furRight);
        this.furRight.addChild(this.clawRight);
        this.body.addChild(this.rostrum);
        this.furLeft.addChild(this.clawLeft);
        this.body.addChild(this.furLeft);
        this.furRight.addChild(this.armRight);
        this.body.addChild(this.rightLeg1);
        this.body.addChild(this.leftLeg1);
        this.body.addChild(this.leftLeg3);
        this.furLeft.addChild(this.armLeft);
        this.body.addChild(this.leftLeg2);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 1.0f;
        this.body.yRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
        this.leftLeg1.yRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.4F;
        this.leftLeg2.yRot = MathHelper.cos(-2.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount - 0.4F;
        this.leftLeg3.yRot = MathHelper.cos(-3.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount - 1.0F;
        this.rightLeg1.yRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount - 0.4F;
        this.rightLeg2.yRot = MathHelper.cos(-2.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.4F;
        this.rightLeg3.yRot = MathHelper.cos(-3.0F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount + 1.0F;
        this.furRight.yRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * -0.35F * limbSwingAmount + 0.25F;
        this.furLeft.yRot = MathHelper.cos(-2.0F + limbSwing * speed * 0.4F) * degree * 0.35F * limbSwingAmount - 0.25F;
        this.clawRight.yRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * -0.35F * limbSwingAmount - 0.25F;
        this.clawLeft.yRot = MathHelper.cos(-2.0F + limbSwing * speed * 0.4F) * degree * 0.35F * limbSwingAmount + 0.25F;
        this.furRight.z = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.1F * limbSwingAmount - 1.99F;
        this.furLeft.z = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * -0.1F * limbSwingAmount - 1.99F;
        this.clawLeft.zRot = 0.1F;
        this.clawRight.zRot = -0.1F;
   }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
