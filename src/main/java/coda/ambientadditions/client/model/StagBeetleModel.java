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
public class StagBeetleModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer rightLeg1;
    public ModelRenderer rightLeg2;
    public ModelRenderer rightLeg3;
    public ModelRenderer leftLeg1;
    public ModelRenderer leftLeg2;
    public ModelRenderer leftLeg3;
    public ModelRenderer mandibleLeft;
    public ModelRenderer mandibleRight;
    public ModelRenderer antennaRight;
    public ModelRenderer antennaLeft;

    public StagBeetleModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.mandibleRight = new ModelRenderer(this, -4, 0);
        this.mandibleRight.setPos(-1.5F, 0.5F, -2.5F);
        this.mandibleRight.addBox(-0.5F, 0.0F, -4.0F, 2.0F, 0.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(mandibleRight, 0.0F, 0.17453292519943295F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 22.5F, 1.0F);
        this.body.addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.antennaLeft = new ModelRenderer(this, 0, 9);
        this.antennaLeft.setPos(1.0F, -1.0F, -2.0F);
        this.antennaLeft.addBox(0.0F, -2.0F, -2.5F, 0.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(antennaLeft, -0.4363323129985824F, -0.4363323129985824F, 0.5235987755982988F);
        this.leftLeg3 = new ModelRenderer(this, 0, 5);
        this.leftLeg3.setPos(1.5F, 1.0F, -0.5F);
        this.leftLeg3.addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftLeg3, 0.7853981633974483F, 0.0F, -1.3089969389957472F);
        this.rightLeg3 = new ModelRenderer(this, 0, 5);
        this.rightLeg3.mirror = true;
        this.rightLeg3.setPos(-1.5F, 1.0F, -0.5F);
        this.rightLeg3.addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightLeg3, 0.7853981633974483F, 0.0F, 1.3089969389957472F);
        this.rightLeg1 = new ModelRenderer(this, 0, 5);
        this.rightLeg1.mirror = true;
        this.rightLeg1.setPos(-1.5F, 1.0F, -0.5F);
        this.rightLeg1.addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightLeg1, -0.7853981633974483F, 0.0F, 1.2217304763960306F);
        this.antennaRight = new ModelRenderer(this, 0, 9);
        this.antennaRight.setPos(-1.0F, -1.0F, -2.0F);
        this.antennaRight.addBox(0.0F, -2.0F, -2.5F, 0.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(antennaRight, -0.4363323129985824F, 0.4363323129985824F, -0.5235987755982988F);
        this.head = new ModelRenderer(this, 0, 6);
        this.head.setPos(0.0F, 0.0F, -1.5F);
        this.head.addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.rightLeg2 = new ModelRenderer(this, 0, 5);
        this.rightLeg2.mirror = true;
        this.rightLeg2.setPos(-1.5F, 1.0F, -1.5F);
        this.rightLeg2.addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightLeg2, 0.4363323129985824F, 0.0F, 1.3089969389957472F);
        this.leftLeg1 = new ModelRenderer(this, 0, 5);
        this.leftLeg1.setPos(1.5F, 1.0F, -0.5F);
        this.leftLeg1.addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftLeg1, -0.7853981633974483F, 0.0F, -1.2217304763960306F);
        this.leftLeg2 = new ModelRenderer(this, 0, 5);
        this.leftLeg2.setPos(1.5F, 1.0F, -1.5F);
        this.leftLeg2.addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftLeg2, 0.4363323129985824F, 0.0F, -1.3089969389957472F);
        this.mandibleLeft = new ModelRenderer(this, -4, 0);
        this.mandibleLeft.mirror = true;
        this.mandibleLeft.setPos(1.5F, 0.5F, -2.5F);
        this.mandibleLeft.addBox(-1.5F, 0.0F, -4.0F, 2.0F, 0.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(mandibleLeft, 0.0F, -0.17453292519943295F, 0.0F);
        this.head.addChild(this.mandibleRight);
        this.head.addChild(this.antennaLeft);
        this.body.addChild(this.leftLeg3);
        this.body.addChild(this.rightLeg3);
        this.body.addChild(this.rightLeg1);
        this.head.addChild(this.antennaRight);
        this.body.addChild(this.head);
        this.body.addChild(this.rightLeg2);
        this.body.addChild(this.leftLeg1);
        this.body.addChild(this.leftLeg2);
        this.head.addChild(this.mandibleLeft);
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
        this.mandibleLeft.yRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount - 0.2F;
        this.mandibleRight.yRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * -0.4F * limbSwingAmount + 0.2F;
        this.rightLeg1.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount - 0.8F;
        this.rightLeg1.yRot = 0.1F;
        this.rightLeg2.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.3F;
        this.rightLeg2.yRot = -0.05F;
        this.rightLeg3.xRot = MathHelper.cos(2.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.8F;
        this.leftLeg1.xRot = MathHelper.cos(2.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount - 0.8F;
        this.leftLeg1.yRot = -0.1F;
        this.leftLeg2.xRot = MathHelper.cos(2.0F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount + 0.2F;
        this.leftLeg2.yRot = 0.05F;
        this.leftLeg3.xRot = MathHelper.cos(4.0F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount + 0.8F;
        this.antennaRight.xRot = MathHelper.cos(-0.5F + limbSwing * speed * 0.4F) * degree * 0.6F * limbSwingAmount - 0.4F;
        this.antennaLeft.xRot = MathHelper.cos(-1.5F + limbSwing * speed * 0.4F) * degree * 0.6F * limbSwingAmount - 0.4F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
