package coda.ambientadditions.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NapoleonWrasseModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer mouth;
    public ModelRenderer bottomFins;
    public ModelRenderer tail;
    public ModelRenderer topFin;
    public ModelRenderer rightFin;
    public ModelRenderer leftFin;
    public ModelRenderer forehead;
    public ModelRenderer caudulFin;

    public NapoleonWrasseModel() {
        this.texWidth = 64;
        this.texHeight = 32;
        this.leftFin = new ModelRenderer(this, 0, 17);
        this.leftFin.setPos(2.5F, 3.0F, 0.5F);
        this.leftFin.addBox(0.0F, -5.0F, 0.0F, 0.0F, 6.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftFin, 0.0F, 0.5235987755982988F, 0.0F);
        this.topFin = new ModelRenderer(this, 13, 13);
        this.topFin.setPos(0.0F, -5.0F, 7.5F);
        this.topFin.addBox(0.0F, -2.0F, -8.0F, 0.0F, 4.0F, 11.0F, 0.0F, 0.0F, 0.0F);
        this.rightFin = new ModelRenderer(this, 0, 17);
        this.rightFin.setPos(-2.5F, 3.0F, 0.5F);
        this.rightFin.addBox(0.0F, -5.0F, 0.0F, 0.0F, 6.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightFin, 0.0F, -0.5235987755982988F, 0.0F);
        this.bottomFins = new ModelRenderer(this, 0, 16);
        this.bottomFins.setPos(0.0F, 5.0F, 2.0F);
        this.bottomFins.addBox(0.0F, -1.0F, -6.5F, 0.0F, 3.0F, 13.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 19.0F, 1.0F);
        this.body.addBox(-2.5F, -5.0F, -6.5F, 5.0F, 10.0F, 13.0F, 0.0F, 0.0F, 0.0F);
        this.mouth = new ModelRenderer(this, 0, 0);
        this.mouth.setPos(0.0F, 3.0F, -6.5F);
        this.mouth.addBox(-1.5F, -2.0F, -3.0F, 3.0F, 4.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.forehead = new ModelRenderer(this, 0, 7);
        this.forehead.setPos(0.0F, -6.5F, 0.0F);
        this.forehead.addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.caudulFin = new ModelRenderer(this, 24, -5);
        this.caudulFin.setPos(0.0F, 0.0F, 3.0F);
        this.caudulFin.addBox(0.0F, -3.0F, -1.0F, 0.0F, 6.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 25, 6);
        this.tail.setPos(0.0F, 1.0F, 6.5F);
        this.tail.addBox(-1.0F, -2.0F, 0.0F, 2.0F, 4.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.leftFin);
        this.body.addChild(this.topFin);
        this.body.addChild(this.rightFin);
        this.body.addChild(this.bottomFins);
        this.body.addChild(this.mouth);
        this.mouth.addChild(this.forehead);
        this.tail.addChild(this.caudulFin);
        this.body.addChild(this.tail);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 1.0f;
        limbSwingAmount = MathHelper.clamp(limbSwingAmount, -0.35F, 0.35F);
        this.body.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
        this.body.yRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
        this.tail.yRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 1.2F * limbSwingAmount;
        this.caudulFin.yRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 1.2F * limbSwingAmount;
        this.rightFin.yRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount - 0.4F;
        this.leftFin.yRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount + 0.4F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
