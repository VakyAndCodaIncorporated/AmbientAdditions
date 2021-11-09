package coda.ambientadditions.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DuckyMaskModel extends BipedModel<LivingEntity> {
    public static final DuckyMaskModel INSTANCE = new DuckyMaskModel();
    public ModelRenderer mask;
    public ModelRenderer band;
    public ModelRenderer bill;

    public DuckyMaskModel() {
        super(0);
        this.texWidth = 128;
        this.texHeight = 64;
        this.mask = new ModelRenderer(this, 64, 8);
        this.mask.setPos(0.0F, 0.0F, 0.0F);
        this.mask.addBox(-4.5F, -8.5F, -4.5F, 9.0F, 9.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.bill = new ModelRenderer(this, 64, 19);
        this.bill.setPos(0.0F, 0.0F, 0.0F);
        this.bill.addBox(-2.5F, -2.5F, -6.5F, 5.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.band = new ModelRenderer(this, 64, 0);
        this.band.setPos(0.0F, 0.0F, 1.0F);
        this.band.addBox(-4.5F, -3.5F, -3.5F, 9.0F, 1.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.head.addChild(this.mask);
        this.mask.addChild(this.bill);
        this.mask.addChild(this.band);
    }

    @Override
    public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        head.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
    }
}
