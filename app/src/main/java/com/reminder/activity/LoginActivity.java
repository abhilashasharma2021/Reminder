package com.reminder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.reminder.MainActivity;
import com.reminder.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {
ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String text = "Newbie?<font color=#4F597E> Create Account</font>";
        binding.txSignUp.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);

        binding.txSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });
        binding.txForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
            }
        });
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });

    }
}