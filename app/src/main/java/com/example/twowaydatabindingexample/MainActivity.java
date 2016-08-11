package com.example.twowaydatabindingexample;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.twowaydatabindingexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainVM vm = new MainVM("This is 2way databinding example", true);
        ActivityMainBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_main);
        binding.setItem(vm);
    }
}
