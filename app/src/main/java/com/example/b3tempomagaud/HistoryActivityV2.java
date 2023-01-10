package com.example.b3tempomagaud;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b3tempomagaud.databinding.ActivityHistoryV2Binding;

public class HistoryActivityV2 extends AppCompatActivity {
    private static final String LOG_TAG = HistoryActivityV2.class.getSimpleName();

    ActivityHistoryV2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryV2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
