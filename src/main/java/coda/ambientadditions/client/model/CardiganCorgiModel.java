package coda.ambientadditions.client.model;

import coda.ambientadditions.common.entities.CardiganCorgiEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class CardiganCorgiModel<T extends Entity> extends AgeableModel<CardiganCorgiEntity> {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer legLeft;
    public ModelRenderer armRight;
    public ModelRenderer armLeft;
    public ModelRenderer legRight;
    public ModelRenderer tail;
    public ModelRenderer snout;
    public ModelRenderer earLeft;
    public ModelRenderer earRight;

    public CardiganCorgiModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.legRight = new ModelRenderer(this, 22, 21);
        this.legRight.setPos(-1.5F, 2.5F, 3.5F);
        this.legRight.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.snout = new ModelRenderer(this, 0, 17);
        this.snout.setPos(0.0F, -2.0F, -2.0F);
        this.snout.addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 19, 8);
        this.tail.setPos(0.0F, -0.5F, 4.5F);
        this.tail.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.legLeft = new ModelRenderer(this, 22, 21);
        this.legLeft.setPos(1.5F, 2.5F, 3.5F);
        this.legLeft.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.earRight = new ModelRenderer(this, 2, 22);
        this.earRight.mirror = true;
        this.earRight.setPos(-1.5F, -4.0F, 0.5F);
        this.earRight.addBox(-1.0F, -3.0F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(earRight, 0.0F, 0.0F, -0.5235987755982988F);
        this.armRight = new ModelRenderer(this, 22, 16);
        this.armRight.setPos(-1.5F, 2.5F, -3.5F);
        this.armRight.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.armLeft = new ModelRenderer(this, 22, 16);
        this.armLeft.setPos(1.5F, 2.5F, -3.5F);
        this.armLeft.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 18);
        this.body.setPos(0.0F, 19.5F, 0.0F);
        this.body.addBox(-3.0F, -2.5F, -4.5F, 6.0F, 5.0F, 9.0F, 0.0F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 4);
        this.head.setPos(0.0F, -0.5F, -4.5F);
        this.head.addBox(-2.5F, -5.0F, -2.0F, 5.0F, 8.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.earLeft = new ModelRenderer(this, 2, 22);
        this.earLeft.setPos(1.5F, -4.0F, 0.5F);
        this.earLeft.addBox(-1.0F, -3.0F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(earLeft, 0.0F, 0.0F, 0.5235987755982988F);
        this.body.addChild(this.legRight);
        this.head.addChild(this.snout);
        this.body.addChild(this.tail);
        this.body.addChild(this.legLeft);
        this.head.addChild(this.earRight);
        this.body.addChild(this.armRight);
        this.body.addChild(this.armLeft);
        this.body.addChild(this.head);
        this.head.addChild(this.earLeft);
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return Collections.emptyList();
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(body);
    }

    @Override
    public void setupAnim(CardiganCorgiEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.tail.xRot = ageInTicks;

        float speed = 1.0F;
        float degree = 1.0F;
        if (entityIn.isInSittingPose()) {
            this.legRight.x = -2.0F;
            this.legRight.y = 1.5F;
            this.legRight.z = 4.0F;
            this.legRight.xRot = 1.5078F;
            this.legRight.yRot = -0.7853F;

            this.legLeft.x = 2.0F;
            this.legLeft.y = 1.5F;
            this.legLeft.z = 4.0F;
            this.legLeft.xRot = 1.5078F;
            this.legLeft.yRot = 0.7853F;


            this.armRight.x = -2.0F;
            this.armRight.y = 1.5F;
            this.armRight.z = -4.0F;
            this.armRight.xRot = -1.5708F;
            this.armRight.yRot = 1.05F;

            this.armLeft.x = 2.0F;
            this.armLeft.y = 1.5F;
            this.armLeft.z = -4.0F;
            this.armLeft.xRot = -1.5708F;
            this.armLeft.yRot = -1.05F;

            this.body.y = 21.5F;
            this.tail.zRot = 0.0F;
            this.legLeft.zRot = 0.0F;
            this.legRight.zRot = 0.0F;
            this.armLeft.zRot = 0.0F;
            this.armRight.zRot = 0.0F;
            this.earRight.zRot = -0.7F;
            this.earLeft.zRot = 0.5F;
            this.body.xRot = 0.0F;

        }
        else {
            this.legRight.x = -1.5F;
            this.legRight.y = 2.5F;
            this.legRight.z = 3.5F;
            this.legRight.yRot = 0.0F;

            this.legLeft.x = 1.5F;
            this.legLeft.y = 2.5F;
            this.legLeft.z = 3.5F;
            this.legLeft.yRot = 0.0F;

            this.armRight.x = -1.5F;
            this.armRight.y = 2.5F;
            this.armRight.z = -3.5F;
            this.armRight.yRot = 0.0F;

            this.armLeft.x = 1.5F;
            this.armLeft.y = 2.5F;
            this.armLeft.z = -3.5F;
            this.armLeft.yRot = 0.0F;

            this.body.y = 19.5F;

            this.legLeft.xRot = MathHelper.cos(limbSwing * speed * 0.5F) * degree * -1.0F * limbSwingAmount;
            this.legRight.xRot = MathHelper.cos(limbSwing * speed * 0.5F) * degree * 1.0F * limbSwingAmount;
            this.armRight.xRot = MathHelper.cos(limbSwing * speed * 0.5F) * degree * -1.0F * limbSwingAmount;
            this.armLeft.xRot = MathHelper.cos(limbSwing * speed * 0.5F) * degree * 1.0F * limbSwingAmount;
            this.tail.zRot = MathHelper.cos(limbSwing * speed * 0.5F) * degree * 1.0F * limbSwingAmount;
            this.legLeft.zRot = MathHelper.cos(limbSwing * speed * 0.5F) * degree * -0.2F * limbSwingAmount;
            this.legRight.zRot = MathHelper.cos(limbSwing * speed * 0.5F) * degree * -0.2F * limbSwingAmount;
            this.armLeft.zRot = MathHelper.cos(limbSwing * speed * 0.5F) * degree * -0.2F * limbSwingAmount;
            this.armRight.zRot = MathHelper.cos(limbSwing * speed * 0.5F) * degree * -0.2F * limbSwingAmount;
            this.earRight.zRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.5F) * degree * 0.5F * limbSwingAmount - 1.0F;
            this.earLeft.zRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.5F) * degree * -0.5F * limbSwingAmount + 1.0F;
            this.body.y = MathHelper.cos(-1.0F + limbSwing * speed * 0.5F) * degree * 0.25F * limbSwingAmount + 19.5F;
            this.body.xRot = MathHelper.cos(-2.0F + limbSwing * speed * 0.5F) * degree * 0.1F * limbSwingAmount;
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
