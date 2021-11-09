package coda.ambientadditions.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class YetiArmWarmersModel extends BipedModel<LivingEntity> {
    public static final YetiArmWarmersModel INSTANCE = new YetiArmWarmersModel();
    public ModelRenderer rightArmBand;
    public ModelRenderer rightArmString;
    public ModelRenderer leftArmBand;
    public ModelRenderer leftArmString;

    public YetiArmWarmersModel() {
        super(0);
        this.texWidth = 128;
        this.texHeight = 64;
        this.leftArmBand = new ModelRenderer(this, 64, 0);
        this.leftArmBand.setPos(0.0F, 0.0F, 0.0F);
        this.leftArmBand.addBox(1.0F, 1.0F, -2.5F, 3.0F, 7.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.rightArmString = new ModelRenderer(this, 64, 12);
        this.rightArmString.mirror = true;
        this.rightArmString.setPos(0.0F, 0.0F, 0.0F);
        this.rightArmString.addBox(-1.0F, 1.0F, -2.5F, 3.0F, 7.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.rightArmBand = new ModelRenderer(this, 64, 0);
        this.rightArmBand.mirror = true;
        this.rightArmBand.setPos(0.0F, 0.0F, 0.0F);
        this.rightArmBand.addBox(-4.0F, 1.0F, -2.5F, 3.0F, 7.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.leftArmString = new ModelRenderer(this, 64, 12);
        this.leftArmString.setPos(0.0F, 0.0F, 0.0F);
        this.leftArmString.addBox(-2.0F, 1.0F, -2.5F, 3.0F, 7.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.leftArm.addChild(this.leftArmBand);
        this.rightArmBand.addChild(this.rightArmString);
        this.rightArm.addChild(this.rightArmBand);
        this.leftArmBand.addChild(this.leftArmString);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.rightArm, this.leftArm).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }
}
