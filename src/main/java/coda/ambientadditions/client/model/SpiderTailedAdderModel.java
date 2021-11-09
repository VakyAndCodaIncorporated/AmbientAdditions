package coda.ambientadditions.client.model;

import coda.ambientadditions.common.entities.SpiderTailedAdderEntity;
import com.google.common.collect.ImmutableList;
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
public class SpiderTailedAdderModel<T extends SpiderTailedAdderEntity> extends AgeableModel<T> {
    public ModelRenderer neck;
    public ModelRenderer head;
    public ModelRenderer body1;
    public ModelRenderer tongue;
    public ModelRenderer body2;
    public ModelRenderer body3;
    public ModelRenderer tail;

    public SpiderTailedAdderModel() {
        this.texWidth = 64;
        this.texHeight = 32;
        this.tongue = new ModelRenderer(this, -3, 5);
        this.tongue.setPos(0.0F, 0.0F, -3.0F);
        this.tongue.addBox(-1.5F, 0.0F, -3.0F, 3.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.body3 = new ModelRenderer(this, 20, 0);
        this.body3.setPos(0.0F, 0.0F, 11.0F);
        this.body3.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 12, 0);
        this.head.setPos(0.0F, 0.0F, 0.0F);
        this.head.addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.body2 = new ModelRenderer(this, 0, 10);
        this.body2.setPos(0.0F, 0.0F, 8.0F);
        this.body2.addBox(-1.5F, -2.0F, 0.0F, 3.0F, 3.0F, 11.0F, 0.0F, 0.0F, 0.0F);
        this.body1 = new ModelRenderer(this, 0, 0);
        this.body1.setPos(0.0F, 0.0F, 0.0F);
        this.body1.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, -5, 0);
        this.tail.setPos(0.0F, 0.0F, 8.0F);
        this.tail.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.neck = new ModelRenderer(this, 0, 0);
        this.neck.setPos(0.0F, 23.0F, -10.0F);
        this.neck.addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.head.addChild(this.tongue);
        this.body2.addChild(this.body3);
        this.neck.addChild(this.head);
        this.body1.addChild(this.body2);
        this.neck.addChild(this.body1);
        this.body3.addChild(this.tail);
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return Collections.emptyList();
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(neck);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 0.65f;
        float degree = 1.0f;
        this.head.yRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.5F * limbSwingAmount;
        this.neck.yRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount;
        this.body1.yRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.6F * limbSwingAmount;
        this.body2.yRot = MathHelper.cos(-4.0F + limbSwing * speed * 0.4F) * degree * 1.0F * limbSwingAmount;
        this.body3.yRot = MathHelper.cos(-4.0F + limbSwing * speed * 0.4F) * degree * 1.0F * limbSwingAmount;
        this.tongue.visible = false;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
