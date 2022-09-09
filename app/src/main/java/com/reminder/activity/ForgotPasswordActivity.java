package com.reminder.activity;

import androidx.annotation.NonNull;
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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.reminder.ApiData.APIClient;
import com.reminder.Model.ForgotPasswordModel;
import com.reminder.R;
import com.reminder.databinding.ActivityForgotPasswordBinding;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ForgotPasswordActivity extends AppCompatActivity {
ActivityForgotPasswordBinding binding;

String strEmail="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btSubmit.setOnClickListener(v -> {
            strEmail = binding.etEmail.getText().toString().trim();
            if (strEmail.equals("")) {
                Toast.makeText(this, "Enter your Email Id", Toast.LENGTH_SHORT).show();
            } else {
                forgotPass(strEmail);
            }
        });

    }

    public void forgotPass(String strEmail) {

        Map<String, String> params = new HashMap<>();
        params.put("email", strEmail);
        Call<ForgotPasswordModel> call = APIClient.getAPIClient().forgotPassword(params);
        call.enqueue(new Callback<ForgotPasswordModel>() {
            @Override
            public void onResponse(@NonNull Call<ForgotPasswordModel> call, @NonNull Response<ForgotPasswordModel> response) {
                Log.e("dsdsfdsfds", response.code()+"");
                ForgotPasswordModel model = response.body();
                if (model != null) {
                    binding.etEmail.setText("");

                    dialog().show();

                } else {

                    }

            }

            @Override
            public void onFailure(@NonNull Call<ForgotPasswordModel> call, @NonNull Throwable t) {

                Log.e("uyuwewd", t.getMessage() + "err");
            }
        });
    }


    public Dialog dialog() {
        Dialog dialog = new Dialog(ForgotPasswordActivity.this);
        dialog.setContentView(R.layout.dialog_reset_password_link_layout);
        dialog.setCancelable(true);
        Button btLogin = dialog.findViewById(R.id.btLogin);


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), ResetPasswordActivity.class));
                finish();
            }
        });


        dialog.show();
        return  dialog;
    }


}