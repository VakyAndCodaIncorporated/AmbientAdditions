package coda.ambientadditions.client.model;

import coda.ambientadditions.common.entities.PinkFairyArmadilloEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class PinkFairyArmadilloModel<T extends Entity> extends AgeableModel<PinkFairyArmadilloEntity> {
    public ModelRenderer body;
    public ModelRenderer armLeft;
    public ModelRenderer armRight;
    public ModelRenderer legLeft;
    public ModelRenderer legRight;
    public ModelRenderer head;
    public ModelRenderer tail;
    public ModelRenderer snout;

    public PinkFairyArmadilloModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.armLeft = new ModelRenderer(this, 0, 22);
        this.armLeft.setPos(2.5F, 2.0F, -2.5F);
        this.armLeft.addBox(-1.0F, 0.0F, -4.0F, 2.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(armLeft, 0.0F, -0.3490658503988659F, 0.0F);
        this.tail = new ModelRenderer(this, 14, 14);
        this.tail.setPos(0.0F, 0.0F, 4.0F);
        this.tail.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail, -0.3490658503988659F, 0.0F, 0.0F);
        this.legLeft = new ModelRenderer(this, 14, 22);
        this.legLeft.setPos(2.0F, 2.0F, 3.5F);
        this.legLeft.addBox(-1.0F, 0.0F, -3.0F, 2.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legLeft, 0.0F, -0.6108652381980153F, 0.0F);
        this.snout = new ModelRenderer(this, 0, 0);
        this.snout.setPos(0.0F, 1.0F, -2.0F);
        this.snout.addBox(-1.0F, -1.5F, -2.0F, 2.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 21.0F, 1.0F);
        this.body.addBox(-3.0F, -2.5F, -4.5F, 6.0F, 5.0F, 9.0F, 0.0F, 0.0F, 0.0F);
        this.legRight = new ModelRenderer(this, 14, 22);
        this.legRight.mirror = true;
        this.legRight.setPos(-2.0F, 2.0F, 3.5F);
        this.legRight.addBox(-1.0F, 0.0F, -3.0F, 2.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legRight, 0.0F, 0.6108652381980153F, 0.0F);
        this.armRight = new ModelRenderer(this, 0, 22);
        this.armRight.mirror = true;
        this.armRight.setPos(-2.5F, 2.0F, -2.5F);
        this.armRight.addBox(-1.0F, 0.0F, -4.0F, 2.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(armRight, 0.0F, 0.3490658503988659F, 0.0F);
        this.head = new ModelRenderer(this, 0, 15);
        this.head.setPos(0.0F, -1.0F, -4.5F);
        this.head.addBox(-2.1F, -1.5F, -3.0F, 4.0F, 4.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.armLeft);
        this.body.addChild(this.tail);
        this.body.addChild(this.legLeft);
        this.head.addChild(this.snout);
        this.body.addChild(this.legRight);
        this.body.addChild(this.armRight);
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
    public void setupAnim(PinkFairyArmadilloEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.9f;
        float degree = 0.75f;
//        limbSwing = ageInTicks;
//        limbSwingAmount = 0.5F;
        this.body.zRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
        this.body.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
        this.head.y = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.25F * limbSwingAmount + 0.01F;
        this.armLeft.xRot = MathHelper.cos(5.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
        this.armLeft.yRot = MathHelper.cos(2.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount - 0.1F;
        this.armRight.xRot = MathHelper.cos(5.0F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount;
        this.armRight.yRot = MathHelper.cos(2.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.1F;
        this.legLeft.xRot = MathHelper.cos(4.0F + limbSwing * speed * 0.4F) * degree * -0.6F * limbSwingAmount;
        this.legLeft.yRot = MathHelper.cos(2.0F + limbSwing * speed * 0.4F) * degree * 0.6F * limbSwingAmount - 0.7F;
        this.legRight.xRot = MathHelper.cos(4.0F + limbSwing * speed * 0.4F) * degree * 0.6F * limbSwingAmount;
        this.legRight.yRot = MathHelper.cos(2.0F + limbSwing * speed * 0.4F) * degree * 0.6F * limbSwingAmount + 0.7F;
        this.tail.xRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount - 0.5F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
