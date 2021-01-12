package com.chess.jungle.utils;

import javax.swing.Timer;

/**
 * @author Chengjie Luo
 */
public class Animation {

    protected float minValue;
    protected float maxValue;
    protected Callback callback;

    protected float currentValue;
    protected float currentTime;

    public Animation(float minValue, float maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public Animation(float minValue, float maxValue, Callback callback) {
        this(minValue, maxValue);
        this.callback = callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void play(boolean isForward, int time) {
        if (callback == null) {
            return;
        }
        currentTime = 0;
        currentValue = isForward ? minValue : maxValue;
        float acceleration = 2 * (maxValue - minValue) / time / time * (isForward ? 1 : -1);
        new Timer(18, e -> {
            if (isForward ? currentValue >= maxValue : currentValue <= minValue) {
                ((Timer) e.getSource()).stop();
                return;
            }
            currentTime += 18f;
            if (currentTime > time) currentTime = time;
            currentValue = 1 / 2f * acceleration * currentTime * currentTime + (isForward ? minValue : maxValue);
            callback.run(currentValue, this);
        }).start();
    }

    public interface Callback {

        void run(float currentValue, Animation animation);
    }
}
