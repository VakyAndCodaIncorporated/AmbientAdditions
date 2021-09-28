package coda.ambientadditions.client.model;

import coda.ambientadditions.common.entities.PinocchioAnoleEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PinocchioAnoleModel<T extends PinocchioAnoleEntity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer tail;
    public ModelRenderer armLeft;
    public ModelRenderer armRight;
    public ModelRenderer legRight;
    public ModelRenderer legLeft;
    public ModelRenderer head;
    public ModelRenderer proboscis;

    public PinocchioAnoleModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.armLeft = new ModelRenderer(this, -3, 0);
        this.armLeft.mirror = true;
        this.armLeft.setPos(1.5F, 1.0F, -1.0F);
        this.armLeft.addBox(0.0F, 0.0F, -3.0F, 2.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(armLeft, 0.0F, 0.4363323129985824F, 0.0F);
        this.armRight = new ModelRenderer(this, -3, 0);
        this.armRight.setPos(-1.5F, 1.0F, -1.0F);
        this.armRight.addBox(-2.0F, 0.0F, -3.0F, 2.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(armRight, 0.0F, -0.4363323129985824F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 23.0F, 0.0F);
        this.body.addBox(-1.5F, -1.0F, -2.0F, 3.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.proboscis = new ModelRenderer(this, 10, 0);
        this.proboscis.setPos(0.0F, 0.0F, -3.0F);
        this.proboscis.addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.legRight = new ModelRenderer(this, -2, 6);
        this.legRight.setPos(-1.5F, 1.0F, 1.0F);
        this.legRight.addBox(-2.0F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legRight, 0.0F, 0.3490658503988659F, 0.0F);
        this.legLeft = new ModelRenderer(this, -2, 6);
        this.legLeft.mirror = true;
        this.legLeft.setPos(1.5F, 1.0F, 1.0F);
        this.legLeft.addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(legLeft, 0.0F, -0.3490658503988659F, 0.0F);
        this.head = new ModelRenderer(this, 0, 12);
        this.head.setPos(0.0F, 0.0F, -1.5F);
        this.head.addBox(-1.0F, -1.5F, -3.0F, 2.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 0, 6);
        this.tail.setPos(0.0F, 0.5F, 2.0F);
        this.tail.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.armLeft);
        this.body.addChild(this.armRight);
        this.head.addChild(this.proboscis);
        this.body.addChild(this.legRight);
        this.body.addChild(this.legLeft);
        this.body.addChild(this.head);
        this.body.addChild(this.tail);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
