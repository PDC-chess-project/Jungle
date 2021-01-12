package com.chess.jungle.utils;

/**
 *
 * @author Chengjie Luo
 * @param <T> Observed data type
 */
public interface LiveData<T> {

    public interface Observer<T> {

        void onChanged(T value);
    }

    public void observe(Observer<T> observer);
}
