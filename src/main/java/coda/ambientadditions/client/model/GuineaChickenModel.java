package coda.ambientadditions.client.model;

import coda.ambientadditions.common.entities.GuineaChickenEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;

import java.util.Collections;

public class GuineaChickenModel<T extends GuineaChickenEntity> extends AgeableModel<T> {
	private final ModelRenderer chimcken;
	private final ModelRenderer body;
	private final ModelRenderer l_leg;
	private final ModelRenderer r_leg;
	private final ModelRenderer l_wing;
	private final ModelRenderer r_wing;
	private final ModelRenderer tail;
	private final ModelRenderer cube_r1;
	private final ModelRenderer head;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;

	public GuineaChickenModel() {
		texWidth = 64;
		texHeight = 64;

		chimcken = new ModelRenderer(this);
		chimcken.setPos(0.0F, 18.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		chimcken.addChild(body);
		body.texOffs(0, 0).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 7.0F, 8.0F, 0.0F, false);

		l_leg = new ModelRenderer(this);
		l_leg.setPos(1.75F, 4.0F, 1.0F);
		body.addChild(l_leg);
		l_leg.texOffs(9, 16).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);

		r_leg = new ModelRenderer(this);
		r_leg.setPos(-1.75F, 4.0F, 1.0F);
		body.addChild(r_leg);
		r_leg.texOffs(24, 16).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);

		l_wing = new ModelRenderer(this);
		l_wing.setPos(3.0F, -1.0F, -3.0F);
		body.addChild(l_wing);
		l_wing.texOffs(15, 16).addBox(0.0F, -1.0F, 0.0F, 1.0F, 5.0F, 6.0F, 0.0F, false);

		r_wing = new ModelRenderer(this);
		r_wing.setPos(-3.0F, -1.0F, -3.0F);
		body.addChild(r_wing);
		r_wing.texOffs(0, 16).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 5.0F, 6.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setPos(0.0F, -2.0F, 4.0F);
		body.addChild(tail);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.0F, 0.0F, 0.0F);
		tail.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.48F, 0.0F, 0.0F);
		cube_r1.texOffs(21, 0).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -1.0F, -4.0F);
		chimcken.addChild(head);
		head.texOffs(0, 28).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		head.texOffs(0, 0).addBox(-0.5F, -4.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(-0.5F, -3.0F, -1.0F);
		head.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.1309F);
		cube_r2.texOffs(0, 3).addBox(0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(0.5F, -3.0F, -1.0F);
		head.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -0.1309F);
		cube_r3.texOffs(3, 3).addBox(0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(0.0F, -5.0F, -1.0F);
		head.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.2618F, 0.0F, 0.0F);
		cube_r4.texOffs(5, 0).addBox(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.r_wing.zRot = ageInTicks * 0.5F;
		this.l_wing.zRot = -ageInTicks * 0.5F;
	}

	@Override
	protected Iterable<ModelRenderer> headParts() {
		return Collections.emptyList();
	}

	@Override
	protected Iterable<ModelRenderer> bodyParts() {
		return ImmutableList.of(chimcken);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}