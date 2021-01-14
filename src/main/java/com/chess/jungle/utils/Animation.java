package com.chess.jungle.utils;

import javax.swing.Timer;

/**
 * An interpolator to execute a uniformly variable motion animation.
 *
 * @author Chengjie Luo
 */
public class Animation {

    protected float minValue;
    protected float maxValue;
    protected Callback callback;
    protected Runnable endCallback;

    protected boolean isRunning;
    protected boolean isForward;
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

    public void setEndCallback(Runnable endCallback) {
        this.endCallback = endCallback;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isForward() {
        return isForward;
    }

    public void play(boolean isForward, int time) {
        if (callback == null) {
            return;
        }
        isRunning = true;
        this.isForward = isForward;
        currentTime = 0;
        currentValue = isForward ? minValue : maxValue;
        float acceleration = 2 * (maxValue - minValue) / time / time * (isForward ? 1 : -1);
        new Timer(18, e -> {
            if (isForward ? currentValue >= maxValue : currentValue <= minValue) {
                ((Timer) e.getSource()).stop();
                isRunning = false;
                if (endCallback != null) {
                    endCallback.run();
                    endCallback = null;
                }
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
