package com.chess.jungle.utils;

import javax.swing.Timer;

/**
 *
 * @author Chengjie Luo
 */
public class Animation {

    protected float minValue;
    protected float maxValue;
    protected final Callback callback;

    protected float currentValue;

    public Animation(float minValue, float maxValue, Callback callback) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.callback = callback;
    }

    public void play(boolean isForward, int time) {
        new Timer(20, e -> {
            if (isForward ? currentValue >= maxValue : currentValue <= minValue) {
                ((Timer) e.getSource()).stop();
                return;
            }
            float increment = 12f / (time / 20f);
            currentValue += increment;
            callback.run(currentValue, this);
        }).start();
    }

    public interface Callback {

        void run(float currentValue, Animation animation);
    }
}
