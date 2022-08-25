package com.reminder.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.reminder.databinding.ActivityResetPasswordBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResetPasswordActivity extends AppCompatActivity {
ActivityResetPasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}