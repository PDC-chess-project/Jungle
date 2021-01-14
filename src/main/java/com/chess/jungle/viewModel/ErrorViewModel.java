package com.chess.jungle.viewModel;

import com.chess.jungle.utils.LiveData;
import com.chess.jungle.utils.MutableLiveData;

/**
 * @author Chengjie Luo
 */
public class ErrorViewModel {

    private static ErrorViewModel instance;

    private ErrorViewModel() {
    }

    public static ErrorViewModel get() {
        if (instance == null) {
            synchronized (ErrorViewModel.class) {
                if (instance == null) {
                    instance = new ErrorViewModel();
                }
            }
        }
        return instance;
    }

    protected MutableLiveData<Exception> error = new MutableLiveData<>();

    public LiveData<Exception> getError() {
        return error;
    }

    public void setError(Exception error) {
        this.error.setValue(error);
    }
}
