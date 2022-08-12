package com.reminder.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.reminder.databinding.ActivityAssigningTaskBinding;

public class AssigningTaskActivity extends AppCompatActivity {
ActivityAssigningTaskBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAssigningTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}