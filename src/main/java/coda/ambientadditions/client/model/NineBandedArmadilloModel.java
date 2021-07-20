package coda.ambientadditions.client.model;

import coda.ambientadditions.common.entities.NineBandedArmadilloEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class NineBandedArmadilloModel<T extends Entity> extends AgeableModel<NineBandedArmadilloEntity> {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer armRight;
    public ModelRenderer armLeft;
    public ModelRenderer legRight;
    public ModelRenderer legLeft;
    public ModelRenderer tail;
    public ModelRenderer snout;
    public ModelRenderer earLeft;
    public ModelRenderer earRight;

    public NineBandedArmadilloModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 19.0F, 0.0F);
        this.body.addBox(-3.5F, -3.0F, -4.0F, 7.0F, 6.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.armRight = new ModelRenderer(this, 0, 0);
        this.armRight.setPos(-2.0F, 22.0F, -2.5F);
        this.armRight.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.legLeft = new ModelRenderer(this, 0, 0);
        this.legLeft.setPos(2.0F, 22.0F, 2.5F);
        this.legLeft.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.legRight = new ModelRenderer(this, 0, 0);
        this.legRight.setPos(-2.0F, 22.0F, 2.5F);
        this.legRight.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.armLeft = new ModelRenderer(this, 0, 0);
        this.armLeft.setPos(2.0F, 22.0F, -2.5F);
        this.armLeft.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.earLeft = new ModelRenderer(this, 0, 4);
        this.earLeft.mirror = true;
        this.earLeft.setPos(1.0F, -1.5F, -1.5F);
        this.earLeft.addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(earLeft, 0.08726646259971647F, 0.5235987755982988F, 0.5235987755982988F);
        this.earRight = new ModelRenderer(this, 0, 4);
        this.earRight.setPos(-1.0F, -1.5F, -1.5F);
        this.earRight.addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(earRight, 0.08726646259971647F, -0.5235987755982988F, -0.5235987755982988F);
        this.snout = new ModelRenderer(this, 12, 14);
        this.snout.setPos(0.0F, 0.5F, -3.0F);
        this.snout.addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 14);
        this.head.setPos(0.0F, 20.5F, -4.0F);
        this.head.addBox(-1.5F, -1.5F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 14, 14);
        this.tail.setPos(0.0F, 21.0F, 4.0F);
        this.tail.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.head.addChild(this.earLeft);
        this.head.addChild(this.earRight);
        this.head.addChild(this.snout);
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return Collections.emptyList();
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(body, head, tail, legLeft, legRight, armLeft, armRight);
    }

    @Override
    public void setupAnim(NineBandedArmadilloEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 1.0f;
/*
        if (entityIn.isBalled()) {
            this.armLeft.xRot = 1.05F;
            this.armRight.xRot = 1.05F;
            this.legRight.xRot = -0.73F;
            this.legLeft.xRot = -0.73F;
            this.head.xRot = 2.0F;
            this.head.y = 20.5F;
            this.earRight.xRot = 0.09F;
            this.earLeft.xRot = -0.09F;
            this.body.zRot = 0.0F;
            this.tail.visible = false;
        }
        else {
*/
            this.body.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
            this.armRight.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount;
            this.armLeft.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
            this.legRight.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
            this.legLeft.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount;
            this.tail.xRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount - 0.2F;
            this.head.y = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.05F * limbSwingAmount + 20.5F;
            this.head.xRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
            this.earLeft.xRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount;
            this.earRight.xRot = MathHelper.cos(-1.25F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount;
            this.tail.visible = true;
        // }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
