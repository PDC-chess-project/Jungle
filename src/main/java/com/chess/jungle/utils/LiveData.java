package com.chess.jungle.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CommA
 */
public class LiveData<T> {

    public interface Observer<T> {

        void onChanged(T value);
    }

    private T value;

    protected List<Observer<T>> observerList = new ArrayList<>();

    public LiveData() {
    }

    public LiveData(T value) {
        this.value = value;
    }

    public void observe(Observer<T> observer) {
        this.observerList.add(observer);
    }

    public void setValue(T value) {
        this.value = value;
        inform();
    }

    public void inform() {
        for (Observer observer : this.observerList) {
            observer.onChanged(value);
        }
    }

    public T get() {
        return value;
    }

    public void clear() {
        observerList.clear();
    }
}
