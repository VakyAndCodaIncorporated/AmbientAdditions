package coda.ambientadditions.client.model;

import coda.ambientadditions.common.entities.SiamangGibbonEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class SiamangGibbonModel<T extends SiamangGibbonEntity> extends AgeableModel<T> {
    public ModelRenderer body;
    public ModelRenderer right_leg;
    public ModelRenderer left_leg;
    public ModelRenderer right_arm;
    public ModelRenderer left_arm;
    public ModelRenderer head;
    public ModelRenderer throat_pouch;
    public ModelRenderer snout;

    public SiamangGibbonModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.right_arm = new ModelRenderer(this, 24, 16);
        this.right_arm.setPos(-3.5F, -2.5F, 0.0F);
        this.right_arm.addBox(-1.0F, -1.5F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 13.0F, 0.0F);
        this.body.addBox(-2.5F, -4.5F, -1.5F, 5.0F, 9.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.throat_pouch = new ModelRenderer(this, 16, 9);
        this.throat_pouch.setPos(0.0F, -4.0F, 0.0F);
        this.throat_pouch.addBox(-2.0F, 0.0F, -3.0F, 4.0F, 4.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.left_arm = new ModelRenderer(this, 24, 16);
        this.left_arm.mirror = true;
        this.left_arm.setPos(3.5F, -2.5F, 0.0F);
        this.left_arm.addBox(-1.0F, -1.5F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.right_leg = new ModelRenderer(this, 0, 13);
        this.right_leg.setPos(-1.5F, 4.0F, 0.0F);
        this.right_leg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.snout = new ModelRenderer(this, 8, 19);
        this.snout.setPos(0.0F, -1.5F, -2.5F);
        this.snout.addBox(-1.5F, -0.5F, -1.0F, 3.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.left_leg = new ModelRenderer(this, 0, 13);
        this.left_leg.mirror = true;
        this.left_leg.setPos(1.5F, 4.0F, 0.0F);
        this.left_leg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 22);
        this.head.setPos(0.0F, -4.5F, 0.0F);
        this.head.addBox(-3.0F, -5.0F, -2.5F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.right_arm);
        this.body.addChild(this.throat_pouch);
        this.body.addChild(this.left_arm);
        this.body.addChild(this.right_leg);
        this.head.addChild(this.snout);
        this.body.addChild(this.left_leg);
        this.body.addChild(this.head);
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
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 1.0f;
        if (entityIn.getDeltaMovement().x != 0 && entityIn.getDeltaMovement().y != 0) {
            this.body.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
            this.right_leg.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.2F * limbSwingAmount;
            this.left_leg.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.2F * limbSwingAmount;
            this.left_leg.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 1.0F * limbSwingAmount;
            this.right_leg.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -1.0F * limbSwingAmount;
            this.head.yRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.05F * limbSwingAmount - 4.45F;
            this.head.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount - 0.05F;
            this.right_arm.xRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount - 0.25F;
            this.right_arm.zRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.1F * limbSwingAmount + 0.35F;
            this.left_arm.xRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * -0.2F * limbSwingAmount - 0.25F;
            this.left_arm.zRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * -0.1F * limbSwingAmount - 0.35F;
            this.right_arm.yRot = limbSwingAmount - 2.45F;
            this.left_arm.yRot = limbSwingAmount - 2.45F;
            this.throat_pouch.yRot = MathHelper.cos(-2.0F + limbSwing * speed * 0.4F) * degree * 0.05F * limbSwingAmount - 3.98F;
            this.throat_pouch.zRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
        }
        else {
            this.right_arm.xRot = 0;
            this.right_arm.zRot = 0;
            this.left_arm.xRot = 0;
            this.left_arm.zRot = 0;
            this.body.zRot = 0F;
            this.right_leg.zRot = 0F;
            this.left_leg.zRot = 0F;
            this.left_leg.xRot = 0F;
            this.right_leg.xRot = 0F;
            this.head.yRot = - 4.5F;
            this.head.xRot = 0F;
            this.right_arm.yRot = - 2.5F;
            this.left_arm.yRot = - 2.5F;
            this.throat_pouch.yRot = - 4F;
            this.throat_pouch.zRot = 0F;
        }
    }
}