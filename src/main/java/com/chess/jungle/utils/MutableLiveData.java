package com.chess.jungle.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * An simplified implementation of a observer pattern
 * @author Chengjie Luo
 */
public class MutableLiveData<T> implements LiveData<T> {

    private T value;

    protected List<LiveData.Observer<T>> observerList = new ArrayList<>();

    public MutableLiveData() {
    }

    public MutableLiveData(T value) {
        this.value = value;
    }

    @Override
    public void observe(LiveData.Observer<T> observer) {
        this.observerList.add(observer);
    }

    @Override
    public void stickyObserve(Observer<T> observer) {
        observer.onChanged(value);
        observe(observer);
    }

    public void setValue(T value) {
        this.value = value;
        inform();
    }

    public void inform() {
        this.observerList.forEach(observer -> observer.onChanged(value));
    }

    public T get() {
        return value;
    }

    public void clear() {
        observerList.clear();
    }
}
