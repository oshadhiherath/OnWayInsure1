package com.example.onwayinsure1.ui.home;

import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onwayinsure1.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}