package com.reminder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.reminder.R;
import com.reminder.databinding.ActivityAcceptDeclineTaskBinding;

public class AcceptDeclineTaskActivity extends AppCompatActivity {
    ActivityAcceptDeclineTaskBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAcceptDeclineTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(AcceptDeclineTaskActivity.this);
                dialog.setContentView(R.layout.dialog_already_planned_layout);
                dialog.setCancelable(true);
                Button btAlways = dialog.findViewById(R.id.btAlways);




                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });  binding=ActivityAcceptDeclineTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(AcceptDeclineTaskActivity.this);
                dialog.setContentView(R.layout.dialog_already_planned_layout);
                dialog.setCancelable(true);
                Button btAlways = dialog.findViewById(R.id.btAlways);




                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });
    }
}