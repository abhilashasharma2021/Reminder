package com.reminder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.reminder.databinding.ActivityAssigningTaskBinding;

public class AssigningTaskActivity extends AppCompatActivity {
ActivityAssigningTaskBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAssigningTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AssigningTaskActivity.this, InviteTaskActivity.class));

            }
        });
    }
}