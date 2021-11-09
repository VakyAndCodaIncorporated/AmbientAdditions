package coda.ambientadditions.client.model;

import coda.ambientadditions.common.entities.HarlequinShrimpEntity;
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
public class HarlequinShrimpModel<T extends HarlequinShrimpEntity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer antennaLeft;
    public ModelRenderer antennaRight;
    public ModelRenderer rostrum;
    public ModelRenderer tail1;
    public ModelRenderer clawLeft;
    public ModelRenderer clawRight;
    public ModelRenderer legLeft1;
    public ModelRenderer legLeft2;
    public ModelRenderer legLeft3;
    public ModelRenderer legLeft4;
    public ModelRenderer legRight1;
    public ModelRenderer legRight2;
    public ModelRenderer legRight3;
    public ModelRenderer legRight4;
    public ModelRenderer tail2;
    public ModelRenderer tailFin;

    public HarlequinShrimpModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.legLeft1 = new ModelRenderer(this, 0, 0);
        this.legLeft1.mirror = true;
        this.legLeft1.setPos(1.0F, 1.0F, -0.5F);
        this.legLeft1.addBox(0.0F, 0.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legLeft1, 0.08726646259971647F, 0.2617993877991494F, -0.5235987755982988F);
        this.legLeft2 = new ModelRenderer(this, 0, 0);
        this.legLeft2.mirror = true;
        this.legLeft2.setPos(1.0F, 1.0F, 0.0F);
        this.legLeft2.addBox(0.0F, 0.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legLeft2, 0.3490658503988659F, 0.17453292519943295F, -0.6108652381980153F);
        this.antennaRight = new ModelRenderer(this, 0, 26);
        this.antennaRight.setPos(-1.0F, 0.0F, -2.5F);
        this.antennaRight.addBox(0.0F, -4.0F, 0.0F, 0.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(antennaRight, 0.8726646259971648F, 0.3490658503988659F, 0.0F);
        this.body = new ModelRenderer(this, 3, 0);
        this.body.setPos(0.0F, 21.5F, 0.0F);
        this.body.addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(body, -0.3490658503988659F, 0.0F, 0.0F);
        this.tail1 = new ModelRenderer(this, 0, 7);
        this.tail1.setPos(0.0F, 0.5F, 2.0F);
        this.tail1.addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail1, 0.593411945678072F, 0.0F, 0.0F);
        this.legLeft3 = new ModelRenderer(this, 0, 0);
        this.legLeft3.mirror = true;
        this.legLeft3.setPos(1.0F, 0.5F, 0.5F);
        this.legLeft3.addBox(0.0F, 0.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legLeft3, 0.5235987755982988F, 0.0F, -0.6108652381980153F);
        this.rostrum = new ModelRenderer(this, 0, 23);
        this.rostrum.setPos(0.0F, -1.5F, -2.0F);
        this.rostrum.addBox(-0.5F, 0.0F, -3.0F, 1.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.antennaLeft = new ModelRenderer(this, 0, 26);
        this.antennaLeft.mirror = true;
        this.antennaLeft.setPos(1.0F, 0.0F, -2.5F);
        this.antennaLeft.addBox(0.0F, -4.0F, 0.0F, 0.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(antennaLeft, 0.8726646259971648F, -0.3490658503988659F, 0.0F);
        this.legLeft4 = new ModelRenderer(this, 0, 0);
        this.legLeft4.mirror = true;
        this.legLeft4.setPos(1.0F, 0.5F, 1.0F);
        this.legLeft4.addBox(0.0F, 0.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legLeft4, 0.7853981633974483F, -0.3490658503988659F, -0.6981317007977318F);
        this.tail2 = new ModelRenderer(this, 0, 12);
        this.tail2.setPos(0.0F, -1.5F, 2.0F);
        this.tail2.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail2, 0.5235987755982988F, 0.0F, 0.0F);
        this.legRight2 = new ModelRenderer(this, 0, 0);
        this.legRight2.setPos(-1.0F, 1.0F, 0.0F);
        this.legRight2.addBox(-3.0F, 0.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legRight2, 0.3490658503988659F, -0.17453292519943295F, 0.6108652381980153F);
        this.tailFin = new ModelRenderer(this, 0, 16);
        this.tailFin.setPos(0.0F, 2.5F, 0.5F);
        this.tailFin.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tailFin, -0.6981317007977318F, 0.0F, 0.0F);
        this.clawLeft = new ModelRenderer(this, 0, 18);
        this.clawLeft.setPos(1.0F, 1.0F, -1.5F);
        this.clawLeft.addBox(0.0F, -0.5F, -3.0F, 1.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(clawLeft, 0.2617993877991494F, -0.4363323129985824F, 0.0F);
        this.clawRight = new ModelRenderer(this, 0, 18);
        this.clawRight.setPos(-1.0F, 1.0F, -1.5F);
        this.clawRight.addBox(-1.0F, -0.5F, -3.0F, 1.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(clawRight, 0.2617993877991494F, 0.4363323129985824F, 0.0F);
        this.legRight1 = new ModelRenderer(this, 0, 0);
        this.legRight1.setPos(-1.0F, 1.0F, -0.5F);
        this.legRight1.addBox(-3.0F, 0.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legRight1, 0.08726646259971647F, -0.2617993877991494F, 0.5235987755982988F);
        this.legRight3 = new ModelRenderer(this, 0, 0);
        this.legRight3.setPos(-1.0F, 0.5F, 0.5F);
        this.legRight3.addBox(-3.0F, 0.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legRight3, 0.5235987755982988F, 0.0F, 0.6108652381980153F);
        this.legRight4 = new ModelRenderer(this, 0, 0);
        this.legRight4.setPos(-1.0F, 0.5F, 1.0F);
        this.legRight4.addBox(-3.0F, 0.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legRight4, 0.7853981633974483F, 0.3490658503988659F, 0.6981317007977318F);
        this.body.addChild(this.legLeft1);
        this.body.addChild(this.legLeft2);
        this.body.addChild(this.antennaRight);
        this.body.addChild(this.tail1);
        this.body.addChild(this.legLeft3);
        this.body.addChild(this.rostrum);
        this.body.addChild(this.antennaLeft);
        this.body.addChild(this.legLeft4);
        this.tail1.addChild(this.tail2);
        this.body.addChild(this.legRight2);
        this.tail2.addChild(this.tailFin);
        this.body.addChild(this.clawLeft);
        this.body.addChild(this.clawRight);
        this.body.addChild(this.legRight1);
        this.body.addChild(this.legRight3);
        this.body.addChild(this.legRight4);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 3.0f;
        float degree = 2.0f;
        limbSwingAmount = MathHelper.clamp(limbSwingAmount, -0.5F, 0.5F);
        this.body.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.175F * limbSwingAmount;
        this.legLeft1.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount - 0.6F;
        this.legLeft1.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount - 0.1F;
        this.legLeft2.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount - 0.6F;
        this.legLeft2.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.2F;
        this.legLeft3.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount - 0.6F;
        this.legLeft3.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.5F;
        this.legLeft4.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount - 0.6F;
        this.legLeft4.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.8F;
        this.legRight1.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.6F;
        this.legRight1.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount - 0.1F;
        this.legRight2.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount + 0.6F;
        this.legRight2.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount + 0.2F;
        this.legRight3.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.6F;
        this.legRight3.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount + 0.5F;
        this.legRight4.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount + 0.6F;
        this.legRight4.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount + 0.8F;
        this.antennaLeft.xRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.9F;
        this.antennaRight.xRot = MathHelper.cos(-1.5F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.9F;
        this.clawLeft.y = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.1F * limbSwingAmount + 1.0F;
        this.clawRight.y = MathHelper.cos(-1.5F + limbSwing * speed * 0.4F) * degree * 0.1F * limbSwingAmount + 1.0F;
        this.tail1.xRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.3F * limbSwingAmount + 0.65F;
        this.tail2.xRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.3F * limbSwingAmount + 0.4F;
        this.body.y = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.05F * limbSwingAmount + 21.5F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
