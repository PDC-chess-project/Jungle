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
    protected float currentTime;

    public Animation(float minValue, float maxValue, Callback callback) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.callback = callback;
    }

    public void play(boolean isForward, int time) {
        float acceleration = (maxValue - minValue) / time / time;
        float slice = time / 10;
        new Timer(10, e -> {
            if (isForward ? currentValue >= maxValue : currentValue <= minValue) {
                ((Timer) e.getSource()).stop();
                return;
            }
            currentTime += slice;
            currentValue = acceleration * currentTime * currentTime + minValue;
            callback.run(currentValue, this);
        }).start();
    }

    public interface Callback {

        void run(float currentValue, Animation animation);
    }
}
