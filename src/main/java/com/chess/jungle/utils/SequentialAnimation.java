package com.chess.jungle.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Coordinate multiple animations to avoid conflict.
 *
 * @author Chengjie Luo
 */
public class SequentialAnimation {

    protected Map<String, Animation> animationMap = new HashMap<>();

    public void play(String name, boolean isForward, int time) {
        Animation animationToPlay = animationMap.get(name);
        boolean isAnyAnimationRunning = false;
        for (Animation animation : animationMap.values()) {
            if (animation.isRunning()) {
                // Check if the animation is already playing
                if (animation == animationToPlay) return;
                // The animation will be executed after another running animation.
                animation.setEndCallback(() -> animationToPlay.play(isForward, time));
                isAnyAnimationRunning = true;
                break;
            }
        }
        if (!isAnyAnimationRunning) {
            animationToPlay.play(isForward, time);
        }
    }

    public void add(String name, Animation animation) {
        animationMap.put(name, animation);
    }
}
