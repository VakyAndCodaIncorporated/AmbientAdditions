package codyhuh.ambientadditions.common.entities.util;

import software.bernie.geckolib.core.animation.RawAnimation;

public class AAAnimations {
    public static final RawAnimation WALK = RawAnimation.begin().thenLoop("walk");
    public static final RawAnimation FLY = RawAnimation.begin().thenLoop("fly");
    public static final RawAnimation SWIM = RawAnimation.begin().thenLoop("swim");
    public static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
    public static final RawAnimation SIT = RawAnimation.begin().thenLoop("sit");
    public static final RawAnimation ACTION = RawAnimation.begin().thenLoop("action");
}
