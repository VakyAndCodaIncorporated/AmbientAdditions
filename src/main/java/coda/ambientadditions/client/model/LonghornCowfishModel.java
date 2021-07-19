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
public class LonghornCowfishModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer horns;
    public ModelRenderer dorsalFin;
    public ModelRenderer analFin;
    public ModelRenderer hornsBack;
    public ModelRenderer pectoralFinLeft;
    public ModelRenderer pectoralFinRight;
    public ModelRenderer caudalFin;

    public LonghornCowfishModel() {
        this.texWidth = 16;
        this.texHeight = 16;
        this.analFin = new ModelRenderer(this, 2, -1);
        this.analFin.setPos(0.0F, 1.0F, 2.0F);
        this.analFin.addBox(0.0F, 0.0F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.pectoralFinRight = new ModelRenderer(this, 0, -1);
        this.pectoralFinRight.setPos(-1.5F, 0.0F, -0.5F);
        this.pectoralFinRight.addBox(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(pectoralFinRight, 0.0F, -0.5235987755982988F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 22.5F, -0.5F);
        this.body.addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.horns = new ModelRenderer(this, 8, 7);
        this.horns.setPos(0.0F, -0.5F, -2.0F);
        this.horns.addBox(-1.0F, -1.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.hornsBack = new ModelRenderer(this, 0, 10);
        this.hornsBack.setPos(0.0F, 0.5F, 2.0F);
        this.hornsBack.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.dorsalFin = new ModelRenderer(this, 0, 0);
        this.dorsalFin.setPos(0.0F, -1.0F, 0.5F);
        this.dorsalFin.addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.pectoralFinLeft = new ModelRenderer(this, 0, -1);
        this.pectoralFinLeft.mirror = true;
        this.pectoralFinLeft.setPos(1.5F, 0.0F, -0.5F);
        this.pectoralFinLeft.addBox(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(pectoralFinLeft, 0.0F, 0.5235987755982988F, 0.0F);
        this.caudalFin = new ModelRenderer(this, 0, 3);
        this.caudalFin.setPos(0.0F, 0.5F, 2.0F);
        this.caudalFin.addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.analFin);
        this.body.addChild(this.pectoralFinRight);
        this.body.addChild(this.horns);
        this.body.addChild(this.hornsBack);
        this.body.addChild(this.dorsalFin);
        this.body.addChild(this.pectoralFinLeft);
        this.body.addChild(this.caudalFin);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        float f = 1.0F;
        if (!p_225597_1_.isInWater()) {
            f = 1.5F;
        }

        this.caudalFin.yRot = -f * 0.45F * MathHelper.sin(0.6F * p_225597_4_);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
