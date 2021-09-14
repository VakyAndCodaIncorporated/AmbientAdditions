package coda.ambientadditions.client.model;

import coda.ambientadditions.common.entities.HawaiianHoneycreeperEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HawaiianHoneycreeperModel<T extends Entity> extends EntityModel<HawaiianHoneycreeperEntity> {
    public ModelRenderer body;
    public ModelRenderer legRight;
    public ModelRenderer legLeft;
    public ModelRenderer beak;
    public ModelRenderer wingLeft;
    public ModelRenderer tail;
    public ModelRenderer wingRight;

    public HawaiianHoneycreeperModel() {
        this.texWidth = 32;
        this.texHeight = 16;
        this.legLeft = new ModelRenderer(this, 6, 0);
        this.legLeft.setPos(1.0F, 2.5F, 0.0F);
        this.legLeft.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.wingLeft = new ModelRenderer(this, 0, 1);
        this.wingLeft.mirror = true;
        this.wingLeft.setPos(1.5F, 0.0F, 1.0F);
        this.wingLeft.addBox(0.0F, 0.0F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 8);
        this.body.setPos(0.0F, 19.5F, 0.0F);
        this.body.addBox(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.1F, 0.0F, 0.0F);
        this.wingRight = new ModelRenderer(this, 0, 1);
        this.wingRight.mirror = true;
        this.wingRight.setPos(-1.5F, 0.0F, 1.0F);
        this.wingRight.addBox(-1.0F, 0.0F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 13, 12);
        this.tail.setPos(0.0F, 1.5F, 1.5F);
        this.tail.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.beak = new ModelRenderer(this, 9, 3);
        this.beak.setPos(0.0F, -0.5F, -1.5F);
        this.beak.addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.legRight = new ModelRenderer(this, 6, 0);
        this.legRight.setPos(-1.0F, 2.5F, 0.0F);
        this.legRight.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.legLeft);
        this.body.addChild(this.wingLeft);
        this.body.addChild(this.wingRight);
        this.body.addChild(this.tail);
        this.body.addChild(this.beak);
        this.body.addChild(this.legRight);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    @Override
    public void setupAnim(HawaiianHoneycreeperEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 1.0f;
        if (!entityIn.isOnGround() || !entityIn.getFeetBlockState().is(BlockTags.LEAVES) && entityIn.getFeetBlockState().isRedstoneConductor(entityIn.getCommandSenderWorld(), entityIn.blockPosition())) {
            this.body.xRot = MathHelper.cos(ageInTicks * speed * 0.4F) * degree * 0.2F * 0.4F + 0.2F;
            this.wingLeft.zRot = MathHelper.cos(ageInTicks * speed * 0.6F) * degree * 1.5F * 0.8F - 1.0F;
            this.wingRight.zRot = MathHelper.cos(ageInTicks * speed * 0.6F) * degree * -1.5F * 0.8F + 1.0F;
            this.tail.xRot = MathHelper.cos(-1.0F + ageInTicks * speed * 0.3F) * degree * 0.2F * 0.4F - 0.45F;
            this.legLeft.xRot = MathHelper.cos(-1.0F + ageInTicks * speed * 0.4F) * degree * 0.2F * 0.4F + 0.15F;
            this.legRight.xRot = MathHelper.cos(-2.5F + ageInTicks * speed * 0.4F) * degree * 0.2F * 0.4F + 0.15F;
        }
        else {
            this.body.xRot = 0.0F;
            this.wingLeft.zRot = 0.0F;
            this.wingRight.zRot = 0.0F;
            this.tail.xRot = 0.0F;
            this.legLeft.xRot = 0.0F;
            this.legRight.xRot = 0.0F;
        }
    }

    public void renderOnShoulder(MatrixStack p_228284_1_, IVertexBuilder p_228284_2_, int p_228284_3_, int p_228284_4_, float p_228284_5_, float p_228284_6_, float p_228284_7_, float p_228284_8_, int p_228284_9_) {
        this.body.render(p_228284_1_, p_228284_2_, p_228284_3_, p_228284_4_);
    }
}
