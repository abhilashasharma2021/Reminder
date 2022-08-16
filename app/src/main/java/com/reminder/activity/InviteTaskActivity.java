package com.reminder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
        binding.btInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InviteTaskActivity.this, AllCompletedTaskActivity.class));

            }
        });

    }
}