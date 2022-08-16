package com.reminder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.reminder.databinding.ActivityEditPersonalInfoBinding;


public class EditPersonalInfoActivity extends AppCompatActivity {
ActivityEditPersonalInfoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityEditPersonalInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.txChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(EditPersonalInfoActivity.this,ChangePasswordActivity.class));
            }
        });
    }
}