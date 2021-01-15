package com.chess.jungle.viewModel;

import com.chess.jungle.utils.Colors;
import com.chess.jungle.utils.LiveData;
import com.chess.jungle.utils.MutableLiveData;

import java.awt.*;

/**
 * @author Chengjie Luo
 */
public class SettingsViewModel {

    private static SettingsViewModel instance;

    private SettingsViewModel() {
    }

    public static SettingsViewModel get() {
        if (instance == null) {
            synchronized (SettingsViewModel.class) {
                if (instance == null) {
                    instance = new SettingsViewModel();
                }
            }
        }
        return instance;
    }

    protected MutableLiveData<Color> themeColor = new MutableLiveData<>(Colors.LIGHT_GREY);

    public LiveData<Color> getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(Color themeColor) {
        this.themeColor.setValue(themeColor);
    }
}
