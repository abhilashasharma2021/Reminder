package com.reminder.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.reminder.databinding.ActivityAllCompletedTaskBinding;


public class AllCompletedTaskActivity extends AppCompatActivity {
ActivityAllCompletedTaskBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAllCompletedTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}