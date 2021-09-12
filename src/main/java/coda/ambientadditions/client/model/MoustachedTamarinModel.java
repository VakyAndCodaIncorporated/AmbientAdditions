package coda.ambientadditions.client.model;

import coda.ambientadditions.common.entities.MoustachedTamarinEntity;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class MoustachedTamarinModel<T extends Entity> extends AgeableModel<MoustachedTamarinEntity> {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer tail;
    public ModelRenderer leftArm;
    public ModelRenderer leftLeg;
    public ModelRenderer rightArm;
    public ModelRenderer rightLeg;
    public ModelRenderer moustacheLeft;
    public ModelRenderer moustacheRight;
    public ModelRenderer ears;

    public MoustachedTamarinModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 20.0F, -0.5F);
        this.body.addBox(-2.0F, -1.0F, -2.0F, 4.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.rightArm = new ModelRenderer(this, 0, 2);
        this.rightArm.setPos(-1.5F, 2.0F, -1.0F);
        this.rightArm.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.leftArm = new ModelRenderer(this, 0, 2);
        this.leftArm.setPos(1.5F, 2.0F, -1.0F);
        this.leftArm.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 0, 14);
        this.tail.setPos(0.0F, -1.0F, 3.0F);
        this.tail.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail, -0.6108652381980153F, 0.0F, 0.0F);
        this.rightLeg = new ModelRenderer(this, 0, 2);
        this.rightLeg.setPos(-1.5F, 2.0F, 2.5F);
        this.rightLeg.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 8);
        this.head.setPos(0.0F, -1.0F, -1.5F);
        this.head.addBox(-1.5F, -1.5F, -2.5F, 3.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.moustacheLeft = new ModelRenderer(this, 0, 0);
        this.moustacheLeft.setPos(-0.5F, 1.0F, -2.5F);
        this.moustacheLeft.addBox(-2.0F, -0.5F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(moustacheLeft, 0.0F, -0.03490658503988659F, 0.0F);
        this.leftLeg = new ModelRenderer(this, 0, 2);
        this.leftLeg.setPos(1.5F, 2.0F, 2.5F);
        this.leftLeg.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.moustacheRight = new ModelRenderer(this, 0, 0);
        this.moustacheRight.mirror = true;
        this.moustacheRight.setPos(0.5F, 1.0F, -2.5F);
        this.moustacheRight.addBox(0.0F, -0.5F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(moustacheRight, 0.0F, 0.03490658503988659F, 0.0F);
        this.ears = new ModelRenderer(this, 0, 20);
        this.ears.setPos(0.0F, 0.0F, -1.0F);
        this.ears.addBox(-2.5F, -1.0F, 0.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.rightArm);
        this.body.addChild(this.leftArm);
        this.body.addChild(this.tail);
        this.body.addChild(this.rightLeg);
        this.body.addChild(this.head);
        this.head.addChild(this.moustacheLeft);
        this.body.addChild(this.leftLeg);
        this.head.addChild(this.moustacheRight);
        this.head.addChild(this.ears);
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
    public void setupAnim(MoustachedTamarinEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.8f;
        float degree = 1.0f;
        this.body.xRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.1F * limbSwingAmount;
        this.body.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.05F * limbSwingAmount;
        this.tail.xRot = MathHelper.cos(-2.0F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount - 0.2F;
        this.leftArm.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
        this.leftArm.y = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.03F * limbSwingAmount + 2.0F;
        this.rightArm.xRot = MathHelper.cos(-3.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
        this.rightArm.y = MathHelper.cos(-2.0F + limbSwing * speed * 0.4F) * degree * 0.03F * limbSwingAmount + 2.0F;;
        this.rightLeg.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
        this.rightLeg.y = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.03F * limbSwingAmount + 2.0F;;
        this.leftLeg.xRot = MathHelper.cos(-3.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
        this.leftLeg.y = MathHelper.cos(-2.0F + limbSwing * speed * 0.4F) * degree * 0.03F * limbSwingAmount + 2.0F;;
        this.head.y = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.1F * limbSwingAmount - 0.5F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
