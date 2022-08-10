package com.reminder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.reminder.R;
import com.reminder.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
ActivitySignUpBinding binding;
    String[] gender;
    String[] country;
    String selected,selectedCountry;
    int sp_position,sp_position_country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String text = "Have an account ?<font color=#4F597E>Login</font>";
        binding.txLogin.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);

        String myString = "Gender";

        gender = getResources().getStringArray(R.array.spinner);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(SignUpActivity.this, android.R.layout.simple_spinner_dropdown_item,gender);
        sp_position = ad.getPosition(myString);
        binding.spGender.setAdapter(ad);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(getResources().getColor(R.color.grey_text));
                ((TextView) arg0.getChildAt(0)).setTextSize(12);

                selected = binding.spGender.getSelectedItem().toString();
                System.out.println(selected);
                setid();

            }

            private void setid() {
                binding.spGender.setSelection(sp_position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });



        String myString1 = "Country";

        country = getResources().getStringArray(R.array.spinnercountry);
        ArrayAdapter<String> ad1 = new ArrayAdapter<String>(SignUpActivity.this, android.R.layout.simple_spinner_dropdown_item,country);
        sp_position_country = ad1.getPosition(myString1);
        binding.spCountry.setAdapter(ad1);
        ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(getResources().getColor(R.color.grey_text));
                ((TextView) arg0.getChildAt(0)).setTextSize(12);

                selectedCountry = binding.spCountry.getSelectedItem().toString();
                System.out.println(selectedCountry);
                setid();

            }

            private void setid() {
                binding.spGender.setSelection(sp_position_country);


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });






        binding.txLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });
    }
}