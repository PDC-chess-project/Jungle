package com.chess.jungle.utils;

/**
 *
 * @author Chengjie Luo
 * @param <T> Observed data type
 */
public interface LiveData<T> {

     interface Observer<T> {

        void onChanged(T value);
    }

     void observe(Observer<T> observer);
    
     void stickyObserve(Observer<T> observer);
    
     T get();
}
