package codyhuh.ambientadditions.common.entities.util;

import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;

public class AAAnimations {
    public static final AnimationBuilder WALK = new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP);
    public static final AnimationBuilder FLY = new AnimationBuilder().addAnimation("fly", ILoopType.EDefaultLoopTypes.LOOP);
    public static final AnimationBuilder SWIM = new AnimationBuilder().addAnimation("swim", ILoopType.EDefaultLoopTypes.LOOP);
    public static final AnimationBuilder IDLE = new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP);
    public static final AnimationBuilder SIT = new AnimationBuilder().addAnimation("sit", ILoopType.EDefaultLoopTypes.LOOP);
    public static final AnimationBuilder ACTION = new AnimationBuilder().addAnimation("action", ILoopType.EDefaultLoopTypes.LOOP);
}
