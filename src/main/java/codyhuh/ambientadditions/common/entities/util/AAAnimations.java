package codyhuh.ambientadditions.common.entities.util;

import software.bernie.geckolib.core.animation.RawAnimation;

public class AAAnimations {
    public static final RawAnimation WALK = RawAnimation.begin().thenPlay("walk");
    public static final RawAnimation FLY = RawAnimation.begin().thenPlay("fly");
    public static final RawAnimation SWIM = RawAnimation.begin().thenPlay("swim");
    public static final RawAnimation IDLE = RawAnimation.begin().thenPlay("idle");
    public static final RawAnimation SIT = RawAnimation.begin().thenPlay("sit");
    public static final RawAnimation ACTION = RawAnimation.begin().thenLoop("action");
}
