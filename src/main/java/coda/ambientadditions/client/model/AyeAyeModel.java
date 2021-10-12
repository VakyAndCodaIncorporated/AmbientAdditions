package coda.ambientadditions.client.model;

import coda.ambientadditions.common.entities.AyeAyeEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class AyeAyeModel<T extends AyeAyeEntity> extends AgeableModel<T> {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer tail;
    public ModelRenderer legRight;
    public ModelRenderer legLeft;
    public ModelRenderer handLeft;
    public ModelRenderer handRight;
    public ModelRenderer mouth;
    public ModelRenderer earLeft;
    public ModelRenderer earRight;
    public ModelRenderer fingerLeft;
    public ModelRenderer fingerRight;

    public AyeAyeModel() {
        this.texWidth = 64;
        this.texHeight = 32;
        this.legRight = new ModelRenderer(this, 48, 25);
        this.legRight.setPos(-1.5F, 0.0F, 2.0F);
        this.legRight.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.fingerLeft = new ModelRenderer(this, -4, 22);
        this.fingerLeft.setPos(0.0F, 3.0F, -1.0F);
        this.fingerLeft.addBox(-1.0F, 0.0F, -4.0F, 3.0F, 0.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(fingerLeft, 0.0F, -0.6108652381980153F, 0.0F);
        this.body = new ModelRenderer(this, 0, 20);
        this.body.setPos(0.0F, 19.0F, 0.0F);
        this.body.addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.mouth = new ModelRenderer(this, 17, 24);
        this.mouth.setPos(0.0F, 0.5F, -2.5F);
        this.mouth.addBox(-1.5F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.earRight = new ModelRenderer(this, 17, 19);
        this.earRight.setPos(-2.5F, -0.5F, -0.5F);
        this.earRight.addBox(-4.0F, -2.0F, -0.5F, 4.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.fingerRight = new ModelRenderer(this, -4, 22);
        this.fingerRight.mirror = true;
        this.fingerRight.setPos(0.0F, 3.0F, -1.0F);
        this.fingerRight.addBox(-2.0F, 0.0F, -4.0F, 3.0F, 0.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(fingerRight, 0.0F, 0.6108652381980153F, 0.0F);
        this.handLeft = new ModelRenderer(this, 1, 8);
        this.handLeft.mirror = true;
        this.handLeft.setPos(1.0F, 2.0F, -3.0F);
        this.handLeft.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 14);
        this.head.setPos(0.0F, -0.5F, -4.0F);
        this.head.addBox(-2.5F, -1.5F, -2.0F, 5.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.handRight = new ModelRenderer(this, 1, 8);
        this.handRight.setPos(-1.0F, 2.0F, -3.0F);
        this.handRight.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.legLeft = new ModelRenderer(this, 48, 25);
        this.legLeft.setPos(1.5F, 0.0F, 2.0F);
        this.legLeft.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.earLeft = new ModelRenderer(this, 17, 19);
        this.earLeft.mirror = true;
        this.earLeft.setPos(2.5F, -0.5F, -0.5F);
        this.earLeft.addBox(0.0F, -2.0F, -0.5F, 4.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 25, 20);
        this.tail.setPos(0.0F, 0.0F, 4.0F);
        this.tail.addBox(-1.5F, -1.0F, 0.0F, 3.0F, 4.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.legRight);
        this.handLeft.addChild(this.fingerLeft);
        this.head.addChild(this.mouth);
        this.head.addChild(this.earRight);
        this.handRight.addChild(this.fingerRight);
        this.body.addChild(this.handLeft);
        this.body.addChild(this.head);
        this.body.addChild(this.handRight);
        this.body.addChild(this.legLeft);
        this.head.addChild(this.earLeft);
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
    public void setupAnim(T entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 0.7f;
        this.tail.xRot = MathHelper.cos(1.0F + f * speed * 0.5F) * degree * 1.0F * f1;
        this.earLeft.zRot = MathHelper.cos(f * speed * 0.5F) * degree * -0.5F * f1 + 0.25F;
        this.earRight.zRot = MathHelper.cos(f * speed * 0.5F) * degree * 0.5F * f1 - 0.25F;
        this.legRight.xRot = MathHelper.cos(-1.0F + f * speed * 0.5F) * degree * 1.0F * f1;
        this.legLeft.xRot = MathHelper.cos(-1.0F + f * speed * 0.5F) * degree * -1.0F * f1;
        this.handLeft.xRot = MathHelper.cos(f * speed * 0.5F) * degree * 2.0F * f1;
        this.handRight.xRot = MathHelper.cos(f * speed * 0.5F) * degree * -2.0F * f1;
        this.body.y = MathHelper.cos(f * speed * 0.5F) * degree * 0.1F * f1 + 19.0F;
        this.body.zRot = MathHelper.cos(f * speed * 0.2F) * degree * 0.3F * f1;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
