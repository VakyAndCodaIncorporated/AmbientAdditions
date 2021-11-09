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
public class RubberDuckyIsopodModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer bill;
    public ModelRenderer spikes;
    public ModelRenderer tail;
    public ModelRenderer legs1;
    public ModelRenderer legs2;
    public ModelRenderer legs3;
    public ModelRenderer legs4;
    public ModelRenderer legs5;
    public ModelRenderer legs6;
    public ModelRenderer antennaLeft;
    public ModelRenderer antennaRight;
    public ModelRenderer spikes_1;

    public RubberDuckyIsopodModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.spikes_1 = new ModelRenderer(this, -3, 26);
        this.spikes_1.setPos(0.0F, 0.5F, -1.0F);
        this.spikes_1.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.legs2 = new ModelRenderer(this, 0, 0);
        this.legs2.setPos(0.0F, 2.0F, -1.5F);
        this.legs2.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.legs3 = new ModelRenderer(this, 0, 0);
        this.legs3.setPos(0.0F, 2.0F, -0.5F);
        this.legs3.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.bill = new ModelRenderer(this, 0, 11);
        this.bill.setPos(0.0F, 1.5F, -3.5F);
        this.bill.addBox(-1.5F, -0.5F, -1.0F, 3.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.legs1 = new ModelRenderer(this, 0, 0);
        this.legs1.setPos(0.0F, 2.0F, -2.5F);
        this.legs1.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.legs5 = new ModelRenderer(this, 0, 0);
        this.legs5.setPos(0.0F, 2.0F, 1.5F);
        this.legs5.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.antennaLeft = new ModelRenderer(this, -3, 1);
        this.antennaLeft.setPos(1.5F, 2.0F, -3.5F);
        this.antennaLeft.addBox(0.0F, 0.0F, -3.0F, 3.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(antennaLeft, 0.0F, 0.3490658503988659F, 0.0F);
        this.body = new ModelRenderer(this, 3, 0);
        this.body.setPos(0.0F, 21.0F, 0.0F);
        this.body.addBox(-2.5F, -2.0F, -3.5F, 5.0F, 4.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.legs4 = new ModelRenderer(this, 0, 0);
        this.legs4.setPos(0.0F, 2.0F, 0.5F);
        this.legs4.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.spikes = new ModelRenderer(this, -8, 13);
        this.spikes.setPos(0.0F, 1.5F, 0.5F);
        this.spikes.addBox(-3.5F, 0.0F, -5.0F, 7.0F, 0.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 0, 22);
        this.tail.setPos(0.0F, 1.0F, 4.5F);
        this.tail.addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.antennaRight = new ModelRenderer(this, -3, 1);
        this.antennaRight.mirror = true;
        this.antennaRight.setPos(-1.5F, 2.0F, -3.5F);
        this.antennaRight.addBox(-3.0F, 0.0F, -3.0F, 3.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(antennaRight, 0.0F, -0.3490658503988659F, 0.0F);
        this.legs6 = new ModelRenderer(this, 0, 0);
        this.legs6.setPos(0.0F, 2.0F, 2.5F);
        this.legs6.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.tail.addChild(this.spikes_1);
        this.body.addChild(this.legs2);
        this.body.addChild(this.legs3);
        this.body.addChild(this.bill);
        this.body.addChild(this.legs1);
        this.body.addChild(this.legs5);
        this.body.addChild(this.antennaLeft);
        this.body.addChild(this.legs4);
        this.body.addChild(this.spikes);
        this.body.addChild(this.tail);
        this.body.addChild(this.antennaRight);
        this.body.addChild(this.legs6);
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
        this.body.yRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
        this.antennaLeft.xRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.3F * limbSwingAmount - 0.1F;
        this.antennaLeft.yRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.1F * limbSwingAmount - 0.4F;
        this.antennaRight.xRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * -0.3F * limbSwingAmount - 0.1F;
        this.antennaRight.yRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * -0.1F * limbSwingAmount + 0.4F;
        this.legs1.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 2.0F * limbSwingAmount;
        this.legs2.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -2.0F * limbSwingAmount;
        this.legs3.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 2.0F * limbSwingAmount;
        this.legs4.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -2.0F * limbSwingAmount;
        this.legs5.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 2.0F * limbSwingAmount;
        this.legs6.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -2.0F * limbSwingAmount;

    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
