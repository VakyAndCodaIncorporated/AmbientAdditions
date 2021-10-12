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
public class PineMartenModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer armLeft;
    public ModelRenderer armRight;
    public ModelRenderer legRight;
    public ModelRenderer legLeft;
    public ModelRenderer tail;
    public ModelRenderer neck;
    public ModelRenderer tail2;
    public ModelRenderer head;
    public ModelRenderer mouth;
    public ModelRenderer ears;

    public PineMartenModel() {
        this.texWidth = 64;
        this.texHeight = 32;
        this.tail = new ModelRenderer(this, 0, 18);
        this.tail.setPos(0.0F, -1.5F, 2.0F);
        this.tail.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail, -0.4363323129985824F, 0.0F, 0.0F);
        this.armLeft = new ModelRenderer(this, 0, 8);
        this.armLeft.setPos(1.0F, 0.5F, -2.5F);
        this.armLeft.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.legRight = new ModelRenderer(this, 0, 8);
        this.legRight.setPos(-1.0F, 0.5F, 1.5F);
        this.legRight.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.tail2 = new ModelRenderer(this, 0, 20);
        this.tail2.setPos(0.0F, 2.0F, 4.0F);
        this.tail2.addBox(0.0F, -3.0F, 0.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail2, 0.46896998465826234F, 0.0F, 0.0F);
        this.legLeft = new ModelRenderer(this, 0, 8);
        this.legLeft.setPos(1.0F, 0.5F, 1.5F);
        this.legLeft.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.armRight = new ModelRenderer(this, 0, 8);
        this.armRight.setPos(-1.0F, 0.5F, -2.5F);
        this.armRight.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setPos(0.0F, -2.0F, 0.5F);
        this.head.addBox(-1.5F, -0.5F, -2.0F, 3.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.mouth = new ModelRenderer(this, 0, 6);
        this.mouth.setPos(0.0F, 1.0F, -2.0F);
        this.mouth.addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.ears = new ModelRenderer(this, 0, 4);
        this.ears.setPos(0.0F, -0.5F, -0.5F);
        this.ears.addBox(-2.0F, -1.0F, 0.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 1, 11);
        this.body.setPos(0.0F, 21.5F, 0.0F);
        this.body.addBox(-1.5F, -1.5F, -3.0F, 3.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.neck = new ModelRenderer(this, 6, 4);
        this.neck.setPos(0.0F, -1.0F, -2.5F);
        this.neck.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.tail);
        this.body.addChild(this.armLeft);
        this.body.addChild(this.legRight);
        this.tail.addChild(this.tail2);
        this.body.addChild(this.legLeft);
        this.body.addChild(this.armRight);
        this.neck.addChild(this.head);
        this.head.addChild(this.mouth);
        this.head.addChild(this.ears);
        this.body.addChild(this.neck);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 1.0f;
        this.body.xRot = MathHelper.cos(-1.0F + f * speed * 0.4F) * degree * 0.25F * f1;
        this.body.y = MathHelper.cos(f * speed * 0.4F) * degree * 0.1F * f1 + 21.5F;
        this.armLeft.xRot = MathHelper.cos(-2.0F + f * speed * 0.4F) * degree * 1.8F * f1 - 0.1F;
        this.armRight.xRot = MathHelper.cos(-3.3F + f * speed * 0.4F) * degree * 1.8F * f1 - 0.1F;
        this.legLeft.xRot = MathHelper.cos(-1.0F + f * speed * 0.4F) * degree * 1.8F * f1;
        this.legRight.xRot = MathHelper.cos(-2.3F + f * speed * 0.4F) * degree * 1.8F * f1;
        this.neck.xRot = MathHelper.cos(-1.0F + f * speed * 0.4F) * degree * 0.4F * f1 + 1.1F;
        this.head.xRot = MathHelper.cos(1.0F + f * speed * 0.4F) * degree * 0.4F * f1 - 1.0F;
        this.tail.xRot = MathHelper.cos(-1.0F + f * speed * 0.4F) * degree * 0.3F * f1 - 0.4F;
        this.tail2.xRot = MathHelper.cos(-1.0F + f * speed * 0.4F) * degree * 0.2F * f1 + 0.45F;
        this.ears.xRot = MathHelper.cos(-1.0F + f * speed * 0.4F) * degree * 0.4F * f1 + 0.1F;

    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
