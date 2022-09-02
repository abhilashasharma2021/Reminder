package com.reminder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.reminder.R;
import com.reminder.databinding.ActivityForgotPasswordBinding;

import java.util.Calendar;


public class ForgotPasswordActivity extends AppCompatActivity {
ActivityForgotPasswordBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               final Dialog dialog = new Dialog(ForgotPasswordActivity.this);
                dialog.setContentView(R.layout.dialog_reset_password_link_layout);
                dialog.setCancelable(true);
                Button btLogin = dialog.findViewById(R.id.btLogin);


                btLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }
                });


                dialog.show();










            }
        });

    }


}