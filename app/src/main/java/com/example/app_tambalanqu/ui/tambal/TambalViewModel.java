package com.example.app_tambalanqu.ui.tambal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TambalViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TambalViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}