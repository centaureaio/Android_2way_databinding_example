package com.example.twowaydatabindingexample;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class MainVM extends BaseObservable {

    public MainVM(String title, boolean checked) {
        this.title = title;
        this.checked = checked;
    }

    private String title;
    private boolean checked;

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyChange();
    }

    @Bindable
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        notifyChange();
        System.out.println("MainVM's 'checked' field was changed");
    }

}
