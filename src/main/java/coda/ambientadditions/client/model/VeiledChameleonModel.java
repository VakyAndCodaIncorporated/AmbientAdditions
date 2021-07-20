package coda.ambientadditions.client.model;

import coda.ambientadditions.common.entities.VeiledChameleonEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class VeiledChameleonModel<T extends Entity> extends AgeableModel<VeiledChameleonEntity> {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer armLeft;
    public ModelRenderer armRight;
    public ModelRenderer legRight;
    public ModelRenderer legLeft;
    public ModelRenderer tail;
    public ModelRenderer casque;

    public VeiledChameleonModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.body = new ModelRenderer(this, 3, 0);
        this.body.setPos(0.0F, 19.5F, 0.0F);
        this.body.addBox(-1.5F, -2.0F, -4.0F, 3.0F, 4.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.legRight = new ModelRenderer(this, 0, 0);
        this.legRight.setPos(-1.5F, 1.5F, 1.5F);
        this.legRight.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.armLeft = new ModelRenderer(this, 0, 0);
        this.armLeft.mirror = true;
        this.armLeft.setPos(1.5F, 1.5F, -2.5F);
        this.armLeft.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.legLeft = new ModelRenderer(this, 0, 0);
        this.legLeft.mirror = true;
        this.legLeft.setPos(1.5F, 1.5F, 1.5F);
        this.legLeft.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 15, 19);
        this.tail.setPos(0.0F, 1.5F, 3.0F);
        this.tail.addBox(-1.0F, -2.5F, 0.0F, 2.0F, 5.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail, 0.19547687289441354F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 20);
        this.head.setPos(0.0F, 0.0F, -4.0F);
        this.head.addBox(-1.5F, -1.0F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.casque = new ModelRenderer(this, 0, 26);
        this.casque.setPos(0.0F, -1.0F, -1.0F);
        this.casque.addBox(-0.5F, -3.0F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.armRight = new ModelRenderer(this, 0, 0);
        this.armRight.setPos(-1.5F, 1.5F, -2.5F);
        this.armRight.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.legRight);
        this.body.addChild(this.armLeft);
        this.body.addChild(this.legLeft);
        this.body.addChild(this.tail);
        this.body.addChild(this.head);
        this.head.addChild(this.casque);
        this.body.addChild(this.armRight);
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
    public void setupAnim(VeiledChameleonEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
