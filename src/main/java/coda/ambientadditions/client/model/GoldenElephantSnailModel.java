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
public class GoldenElephantSnailModel<T extends GoldenElephantSnailEntity> extends EntityModel<T> {
    public ModelRenderer shell1;
    public ModelRenderer shell2;
    public ModelRenderer head;
    public ModelRenderer mouth;
    public ModelRenderer antenna;

    public GoldenElephantSnailModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.head = new ModelRenderer(this, 0, 16);
        this.head.setPos(0.0F, 0.0F, -1.0F);
        this.head.addBox(-1.0F, 0.0F, -3.0F, 2.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.shell1 = new ModelRenderer(this, 0, 7);
        this.shell1.setPos(0.0F, 22.0F, -1.0F);
        this.shell1.addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.antenna = new ModelRenderer(this, 0, 21);
        this.antenna.setPos(0.0F, 0.0F, -1.0F);
        this.antenna.addBox(-1.0F, -3.0F, -2.0F, 2.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.shell2 = new ModelRenderer(this, 0, 0);
        this.shell2.setPos(0.0F, 0.0F, 4.0F);
        this.shell2.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.mouth = new ModelRenderer(this, 10, 16);
        this.mouth.setPos(0.0F, 1.0F, -4.5F);
        this.mouth.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.shell1.addChild(this.head);
        this.head.addChild(this.antenna);
        this.shell1.addChild(this.shell2);
        this.head.addChild(this.mouth);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.shell1).forEach((modelRenderer) -> { 
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
