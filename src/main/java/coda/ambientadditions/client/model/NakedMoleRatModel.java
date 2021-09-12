package coda.ambientadditions.client.model;

import coda.ambientadditions.common.entities.NakedMoleRatEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class NakedMoleRatModel<T extends Entity> extends AgeableModel<NakedMoleRatEntity> {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer legLeft;
    public ModelRenderer legRight;
    public ModelRenderer backLegRight;
    public ModelRenderer backLegLeft;
    public ModelRenderer tail;

    public NakedMoleRatModel() {
        this.texHeight = 32;
        this.texWidth = 32;
        this.backLegRight = new ModelRenderer(this, 0, 0);
        this.backLegRight.setPos(-1.0F, 2.0F, 6.5F);
        this.backLegRight.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.backLegLeft = new ModelRenderer(this, 0, 0);
        this.backLegLeft.setPos(1.0F, 2.0F, 6.5F);
        this.backLegLeft.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 1, 6);
        this.body.setPos(0.0F, 20.0F, -3.0F);
        this.body.addBox(-1.5F, -2.0F, 0.0F, 3.0F, 4.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.legRight = new ModelRenderer(this, 0, 0);
        this.legRight.setPos(-1.0F, 2.0F, 1.5F);
        this.legRight.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 0, 17);
        this.tail.setPos(0.0F, 0.0F, 7.0F);
        this.tail.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail, -0.5235987755982988F, 0.0F, 0.0F);
        this.legLeft = new ModelRenderer(this, 0, 0);
        this.legLeft.setPos(1.0F, 2.0F, 1.5F);
        this.legLeft.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setPos(0.0F, 0.5F, 0.0F);
        this.head.texOffs(1, 0).addBox(-2.0F, -1.5F, -3.0F, 4.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.head.texOffs(0, 6).addBox(-1.5F, -0.5F, -4.0F, 3.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.head.texOffs(1, 10).addBox(-0.5F, 1.5F, -3.5F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.backLegRight);
        this.body.addChild(this.backLegLeft);
        this.body.addChild(this.legRight);
        this.body.addChild(this.tail);
        this.body.addChild(this.legLeft);
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
    public void setupAnim(NakedMoleRatEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.8f;
        float degree = 1.0f;
        this.body.xRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.1F * limbSwingAmount;
        this.body.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.05F * limbSwingAmount;
        this.tail.xRot = MathHelper.cos(-2.0F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount - 0.2F;
        this.legLeft.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
        this.legLeft.y = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.03F * limbSwingAmount + 2.0F;
        this.legRight.xRot = MathHelper.cos(-3.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
        this.legRight.y = MathHelper.cos(-2.0F + limbSwing * speed * 0.4F) * degree * 0.03F * limbSwingAmount + 2.0F;;
        this.backLegRight.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
        this.backLegRight.y = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.03F * limbSwingAmount + 2.0F;;
        this.backLegLeft.xRot = MathHelper.cos(-3.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
        this.backLegLeft.y = MathHelper.cos(-2.0F + limbSwing * speed * 0.4F) * degree * 0.03F * limbSwingAmount + 2.0F;;
        this.head.y = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.1F * limbSwingAmount + 0.5F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
