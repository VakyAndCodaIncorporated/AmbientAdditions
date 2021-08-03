package coda.ambientadditions.client.model;

import coda.ambientadditions.common.entities.MoleEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class MoleModel<T extends Entity> extends AgeableModel<MoleEntity> {
    public ModelRenderer body;
    public ModelRenderer nose;
    public ModelRenderer armLeft;
    public ModelRenderer armRight;
    public ModelRenderer legLeft;
    public ModelRenderer legRight;
    public ModelRenderer tail;
    public ModelRenderer star;
    private float armYRot;
    private float bodyXRot;

    public MoleModel() {
        this.texWidth = 64;
        this.texHeight = 32;
        this.armLeft = new ModelRenderer(this, 0, 11);
        this.armLeft.mirror = true;
        this.armLeft.setPos(2.5F, 1.5F, -0.5F);
        this.armLeft.addBox(0.0F, -1.0F, -1.5F, 6.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.legRight = new ModelRenderer(this, 0, 6);
        this.legRight.setPos(-2.5F, 2.0F, 4.5F);
        this.legRight.addBox(-5.0F, -0.5F, -1.5F, 5.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.star = new ModelRenderer(this, 19, 11);
        this.star.setPos(0.0F, 0.0F, -2.0F);
        this.star.addBox(-2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 17);
        this.body.setPos(0.0F, 21.5F, -1.0F);
        this.body.addBox(-2.5F, -2.5F, -4.0F, 5.0F, 5.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.armRight = new ModelRenderer(this, 0, 11);
        this.armRight.setPos(-2.5F, 1.5F, -0.5F);
        this.armRight.addBox(-6.0F, -1.0F, -1.5F, 6.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.nose = new ModelRenderer(this, 0, 21);
        this.nose.setPos(0.0F, 0.5F, -4.0F);
        this.nose.addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.legLeft = new ModelRenderer(this, 0, 6);
        this.legLeft.mirror = true;
        this.legLeft.setPos(2.5F, 2.0F, 4.5F);
        this.legLeft.addBox(0.0F, -0.5F, -1.5F, 5.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 21, 17);
        this.tail.setPos(0.0F, 1.5F, 6.0F);
        this.tail.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.armLeft);
        this.body.addChild(this.legRight);
        this.nose.addChild(this.star);
        this.body.addChild(this.armRight);
        this.body.addChild(this.nose);
        this.body.addChild(this.legLeft);
        this.body.addChild(this.tail);
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
    public void setupAnim(MoleEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 1.0f;
        limbSwingAmount = MathHelper.clamp(limbSwingAmount, -0.4F, 0.4F);
        this.armLeft.yRot = MathHelper.cos(-0.75F + limbSwing * speed * -0.35F) * degree * 1.5F * limbSwingAmount + 0.2F;
        this.body.yRot = MathHelper.cos(limbSwing * speed * 0.35F) * degree * 0.4F * limbSwingAmount;
        this.armRight.yRot = MathHelper.cos(-0.75F + limbSwing * speed * -0.35F) * degree * 1.5F * limbSwingAmount - 0.2F;
        this.legLeft.yRot = MathHelper.cos(0.75F + limbSwing * speed * 0.35F) * degree * -1.0F * limbSwingAmount - 1.2F;
        this.legRight.yRot = MathHelper.cos(0.75F + limbSwing * speed * 0.35F) * degree * -1.0F * limbSwingAmount + 1.2F;
        this.tail.yRot = MathHelper.cos(4.0F + limbSwing * speed * 0.35F) * degree * 0.5F * limbSwingAmount;
        this.legLeft.zRot = MathHelper.cos(limbSwing * speed * 0.35F) * degree * -0.2F * limbSwingAmount;
        this.legRight.zRot = MathHelper.cos(limbSwing * speed * 0.35F) * degree * -0.2F * limbSwingAmount;

        if (entityIn.eatAnimationTick > 4 && entityIn.eatAnimationTick <= 36) {
            this.armLeft.yRot = this.armYRot;
            this.armRight.yRot = -this.armYRot;
            this.body.xRot = this.bodyXRot;
        }
    }

    @Override
    public void prepareMobModel(MoleEntity p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
        super.prepareMobModel(p_212843_1_, p_212843_2_, p_212843_3_, p_212843_4_);
        this.armYRot = p_212843_1_.getHeadEatAngleScale(p_212843_4_) + 0.5F;
        this.bodyXRot = p_212843_1_.getHeadEatAngleScale(p_212843_4_) + 0.5F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
