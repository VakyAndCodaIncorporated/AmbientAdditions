package coda.ambientadditions.client.model;

import coda.ambientadditions.common.entities.LeafFrogEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class LeafFrogModel<T extends LeafFrogEntity> extends SegmentedModel<T> {
    public ModelRenderer body;
    public ModelRenderer armLeft;
    public ModelRenderer armRight;
    public ModelRenderer head;
    public ModelRenderer legLeft;
    public ModelRenderer legRight;
    public ModelRenderer hornLeft;
    public ModelRenderer hornRight;
    public ModelRenderer part6;
    public ModelRenderer footLeft;
    public ModelRenderer footRight;
    public ModelRenderer tailTadpole;
    public ModelRenderer bodyTadpole;

    public LeafFrogModel() {
        setAngles();
    }

    protected abstract void setAngles();

    @Override
    public Iterable<ModelRenderer> parts() {
        return young ? ImmutableList.of(bodyTadpole) : ImmutableList.of(body);
    }

    @Override
    public void setupAnim(T entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 3.0f;
        float degree = 1.0f;

        f1 = MathHelper.clamp(f1, -0.45F, -0.45F);

        if (entityIn.isBaby()) {
            this.tailTadpole.yRot = MathHelper.cos(2.0F + ageInTicks * 0.3F) * 0.7F;
        }
        else {
            if (entityIn.getDeltaMovement().x() == 0.0D && entityIn.getDeltaMovement().z() == 0.0D) {
                this.body.y = 21.5F;
                this.body.xRot = f1 + 0.45F;
                this.legLeft.xRot = f1 + 0.45F;
                this.legRight.xRot = f1 + 0.45F;
                this.armLeft.xRot = f1 + 0.45F;
                this.armRight.xRot = f1 + 0.45F;
                this.head.y = -0.5F;
                this.body.zRot = f1 + 0.45F;
            }
            else {
                this.body.y = MathHelper.cos(-1.0F + f * speed * 0.4F) * degree * 4.0F * f1 + 19.5F;
                this.body.xRot = MathHelper.cos(-3.0F + f * speed * 0.4F) * degree * 0.8F * f1;
                this.legLeft.xRot = MathHelper.cos(-2.0F + f * speed * 0.4F) * degree * 2.0F * f1;
                this.legRight.xRot = MathHelper.cos(-2.0F + f * speed * 0.4F) * degree * 1.5F * f1;
                this.armLeft.xRot = MathHelper.cos(-1.0F + f * speed * 0.4F) * degree * 1.5F * f1 - 0.4F;
                this.armRight.xRot = MathHelper.cos(-1.0F + f * speed * 0.4F) * degree * 2.0F * f1 - 0.4F;
                this.head.y = MathHelper.cos(1.0F + f * speed * 0.4F) * degree * 1.0F * f1 - 0.5F;
                this.body.zRot = MathHelper.cos(f * speed * 0.4F) * degree * 0.2F * f1;
            }
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    public static class Adult extends LeafFrogModel<LeafFrogEntity> {
        @Override
        protected void setAngles() {
            this.texWidth = 64;
            this.texHeight = 32;
            this.part6 = new ModelRenderer(this, -1, 4);
            this.part6.setPos(0.0F, -1.0F, -2.0F);
            this.part6.addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.armRight = new ModelRenderer(this, 0, 0);
            this.armRight.setPos(-1.0F, 1.0F, -1.0F);
            this.armRight.addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(armRight, -0.4363323129985824F, 0.0F, 0.4363323129985824F);
            this.body = new ModelRenderer(this, 0, 0);
            this.body.setPos(0.0F, 21.5F, 0.0F);
            this.body.addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F);
            this.hornLeft = new ModelRenderer(this, 0, 15);
            this.hornLeft.mirror = true;
            this.hornLeft.setPos(1.5F, -1.0F, 0.0F);
            this.hornLeft.addBox(0.0F, -2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(hornLeft, 0.4363323129985824F, 0.0F, 0.5235987755982988F);
            this.head = new ModelRenderer(this, 8, 8);
            this.head.setPos(0.0F, -0.5F, -2.0F);
            this.head.addBox(-1.5F, -1.0F, -2.0F, 3.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.footRight = new ModelRenderer(this, -2, 14);
            this.footRight.setPos(0.5F, 1.0F, -2.0F);
            this.footRight.addBox(-2.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.armLeft = new ModelRenderer(this, 0, 0);
            this.armLeft.setPos(1.0F, 1.0F, -1.0F);
            this.armLeft.addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(armLeft, -0.4363323129985824F, 0.0F, -0.4363323129985824F);
            this.legLeft = new ModelRenderer(this, 0, 8);
            this.legLeft.setPos(1.5F, 1.5F, 2.5F);
            this.legLeft.addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(legLeft, 0.0F, -0.2617993877991494F, 0.0F);
            this.footLeft = new ModelRenderer(this, -2, 14);
            this.footLeft.mirror = true;
            this.footLeft.setPos(-0.5F, 1.0F, -2.0F);
            this.footLeft.addBox(0.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.legRight = new ModelRenderer(this, 0, 8);
            this.legRight.setPos(-1.5F, 1.5F, 2.5F);
            this.legRight.addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(legRight, 0.0F, 0.2617993877991494F, 0.0F);
            this.hornRight = new ModelRenderer(this, 0, 15);
            this.hornRight.setPos(-1.5F, -1.0F, 0.0F);
            this.hornRight.addBox(0.0F, -2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(hornRight, 0.4363323129985824F, 0.0F, -0.5235987755982988F);
            this.head.addChild(this.part6);
            this.body.addChild(this.armRight);
            this.head.addChild(this.hornLeft);
            this.body.addChild(this.head);
            this.legRight.addChild(this.footRight);
            this.body.addChild(this.armLeft);
            this.body.addChild(this.legLeft);
            this.legLeft.addChild(this.footLeft);
            this.body.addChild(this.legRight);
            this.head.addChild(this.hornRight);
        }
    }

    public static class Child extends LeafFrogModel<LeafFrogEntity> {
        @Override
        protected void setAngles() {
            this.texWidth = 16;
            this.texHeight = 16;
            this.bodyTadpole = new ModelRenderer(this, 0, 0);
            this.bodyTadpole.setPos(0.0F, 22.5F, -2.0F);
            this.bodyTadpole.addBox(-2.0F, -1.5F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F);
            this.tailTadpole = new ModelRenderer(this, 0, 2);
            this.tailTadpole.setPos(0.0F, 0.0F, 2.0F);
            this.tailTadpole.addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F);
            this.bodyTadpole.addChild(this.tailTadpole);
        }
    }
}
