package dev.vladimir.models.baseComponents.drawable;

import dev.vladimir.models.baseComponents.logic.Point;

import java.awt.*;
import java.util.Map;

public class AnimationState {
    public static final String RUN_RIGHT_ANIMATION_NAME = "runRight";
    public static final String RUN_LEFT_ANIMATION_NAME = "runLeft";
    public static final String STAY_ANIMATION_NAME = "stay";
    private final Map<String, Animation> animationMap;
    private Animation currentAnimation;

    public AnimationState(Map<String, Animation> animationMap) {
        this.animationMap = animationMap;
    }

    public void setAnimation(String animationName) {
        currentAnimation = animationMap.get(animationName);
    }

    public void drawAnimation(Graphics g, Point point) {
        currentAnimation.draw(g, point);
    }
}
