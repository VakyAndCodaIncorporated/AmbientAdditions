package coda.ambientadditions.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChocolateChipStarfishModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer Middle;
    public ModelRenderer MiddleSpike1;
    public ModelRenderer MiddleSpike2;
    public ModelRenderer MiddleSpike3;
    public ModelRenderer MiddleSpike4;
    public ModelRenderer MiddleSpike5;
    public ModelRenderer Arm1;
    public ModelRenderer Arm2;
    public ModelRenderer Arm3;
    public ModelRenderer Arm4;
    public ModelRenderer Arm5;
    public ModelRenderer Spike1;
    public ModelRenderer Spike2;
    public ModelRenderer Spike3;
    public ModelRenderer Spike4;
    public ModelRenderer Spike5;

    public ChocolateChipStarfishModel() {
        this.texWidth = 16;
        this.texHeight = 17;
        this.Middle = new ModelRenderer(this, 0, 10);
        this.Middle.setPos(0.0F, 23.0F, 0.0F);
        this.Middle.addBox(-2.0F, -2.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.MiddleSpike1 = new ModelRenderer(this, 0, 0);
        this.MiddleSpike1.setPos(0.0F, -2.0F, 1.0F);
        this.MiddleSpike1.addBox(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.Arm2 = new ModelRenderer(this, 0, 0);
        this.Arm2.setPos(-1.5F, 0.0F, 1.0F);
        this.Arm2.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Arm2, 0.0F, -1.1344640137963142F, 0.0F);
        this.MiddleSpike4 = new ModelRenderer(this, 0, 0);
        this.MiddleSpike4.setPos(1.0F, -2.0F, -1.0F);
        this.MiddleSpike4.addBox(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(MiddleSpike4, 0.0F, 2.443460952792061F, 0.0F);
        this.Spike5 = new ModelRenderer(this, 0, 4);
        this.Spike5.setPos(0.0F, -1.0F, 0.0F);
        this.Spike5.addBox(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.MiddleSpike2 = new ModelRenderer(this, 0, 0);
        this.MiddleSpike2.setPos(-1.0F, -2.0F, 0.7F);
        this.MiddleSpike2.addBox(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(MiddleSpike2, 0.0F, -1.1344640137963142F, 0.0F);
        this.MiddleSpike3 = new ModelRenderer(this, 0, 0);
        this.MiddleSpike3.setPos(-1.7F, -2.0F, -2.0F);
        this.MiddleSpike3.addBox(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(MiddleSpike3, 0.0F, 0.6981317007977318F, 0.0F);
        this.Arm5 = new ModelRenderer(this, 0, 0);
        this.Arm5.setPos(1.5F, 0.0F, 1.0F);
        this.Arm5.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Arm5, 0.0F, 1.2217304763960306F, 0.0F);
        this.MiddleSpike5 = new ModelRenderer(this, 0, 0);
        this.MiddleSpike5.setPos(1.0F, -2.0F, 0.8F);
        this.MiddleSpike5.addBox(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(MiddleSpike5, 0.0F, 1.2217304763960306F, 0.0F);
        this.Arm3 = new ModelRenderer(this, 0, 0);
        this.Arm3.mirror = true;
        this.Arm3.setPos(-1.0F, 0.0F, -1.0F);
        this.Arm3.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Arm3, 0.0F, -2.443460952792061F, 0.0F);
        this.Arm1 = new ModelRenderer(this, 0, 0);
        this.Arm1.setPos(0.0F, 0.0F, 2.0F);
        this.Arm1.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.Spike3 = new ModelRenderer(this, 0, 4);
        this.Spike3.setPos(0.0F, -1.0F, 0.0F);
        this.Spike3.addBox(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.Spike4 = new ModelRenderer(this, 0, 4);
        this.Spike4.setPos(0.0F, -1.0F, 0.0F);
        this.Spike4.addBox(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.Spike2 = new ModelRenderer(this, 0, 4);
        this.Spike2.setPos(0.0F, -1.0F, 0.0F);
        this.Spike2.addBox(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.Arm4 = new ModelRenderer(this, 0, 0);
        this.Arm4.setPos(1.0F, 0.0F, -1.0F);
        this.Arm4.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Arm4, 0.0F, 2.443460952792061F, 0.0F);
        this.Spike1 = new ModelRenderer(this, 0, 4);
        this.Spike1.setPos(0.0F, -1.0F, 0.0F);
        this.Spike1.addBox(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.Middle.addChild(this.MiddleSpike1);
        this.Middle.addChild(this.Arm2);
        this.Middle.addChild(this.MiddleSpike4);
        this.Arm5.addChild(this.Spike5);
        this.Middle.addChild(this.MiddleSpike2);
        this.Middle.addChild(this.MiddleSpike3);
        this.Middle.addChild(this.Arm5);
        this.Middle.addChild(this.MiddleSpike5);
        this.Middle.addChild(this.Arm3);
        this.Middle.addChild(this.Arm1);
        this.Arm3.addChild(this.Spike3);
        this.Arm4.addChild(this.Spike4);
        this.Arm2.addChild(this.Spike2);
        this.Middle.addChild(this.Arm4);
        this.Arm1.addChild(this.Spike1);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.Middle).forEach((modelRenderer) -> { 
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
