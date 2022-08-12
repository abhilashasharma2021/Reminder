package com.reminder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.reminder.R;
import com.reminder.databinding.ActivityAssigningTaskBinding;
import com.reminder.databinding.ActivityInviteTaskBinding;

public class InviteTaskActivity extends AppCompatActivity {
ActivityInviteTaskBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityInviteTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}