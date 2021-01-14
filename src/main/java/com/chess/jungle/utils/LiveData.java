package com.chess.jungle.utils;

/**
 * Represent a data which is being observed.
 *
 * @param <T> Observed data type
 * @author Chengjie Luo
 */
public interface LiveData<T> {

    interface Observer<T> {

        void onChanged(T value);
    }

    void observe(Observer<T> observer);

    void stickyObserve(Observer<T> observer);

    T get();
}
