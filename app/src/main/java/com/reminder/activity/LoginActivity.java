package com.reminder.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.reminder.ApiData.APIClient;
import com.reminder.MainActivity;
import com.reminder.Model.LoginModel;
import com.reminder.databinding.ActivityLoginBinding;
import com.reminder.utils.AppConstats;
import com.reminder.utils.InternetConnection.InternetConnectionInterface;
import com.reminder.utils.InternetConnection.InternetConnectivity;
import com.reminder.utils.SharedHelper;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    String stEmail = "",stPassword="",stNewPass="",device_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        device_id = Settings.Secure.getString(LoginActivity.this.getContentResolver(),
                Settings.Secure.ANDROID_ID);


        String text = "Newbie?<font color=#4F597E> Create Account</font>";
        binding.txSignUp.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);


        binding.txSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
        binding.txForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stEmail = binding.etEmail.getText().toString().trim();
                stPassword = binding.etPass.getText().toString().trim();
                Log.e("trhtrjhyt", "onClick: "+stEmail );
                Log.e("trhtrjhyt", "onClick: "+stPassword );


                if (validation()) {

                    if (!validateEmailAddress(stEmail)) {
                        Toast.makeText(LoginActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    } else {
                        InternetConnectionInterface connectivity = new InternetConnectivity();
                        if (connectivity.isConnected(getApplicationContext())) {
                            stNewPass=getMd5(stPassword);

                            login(stEmail);

                            Log.e("sdjdkiscids", "onClick: "+stNewPass);
                        } else {
                            Toast.makeText(LoginActivity.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {
                    validation();
                }



            }
        });

        }
    private Boolean validation() {

        Boolean boolen = false;
        if (binding.etEmail.getText().toString().isEmpty()) {
            binding.etEmail.setError("Email Address Must Required");


        } else if (binding.etPass.getText().toString().isEmpty()) {
            binding.etPass.setError("Password Must Required");

        }
        else if (stPassword.length()<8) {
            binding.etPass.setError("The Password must be at least 8 characters in length");

        }else {
            boolen = true;

        }
        return boolen;
    }

    public static boolean validateEmailAddress(String stEmail) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(stEmail);
        return matcher.matches();
    }




    public static String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    private void login(String stEmail) {


        Log.e("dskjfkdj", "getEmail: "+stEmail );
        Log.e("dskjfkdj", "stNewPass: "+stNewPass );
        Log.e("dskjfkdj", "android_id: "+device_id );

        Map<String, String> param = new HashMap<>();
        param.put("email", stEmail);
        param.put("password", stNewPass);
        param.put("device_type", "2");
        param.put("device_id", device_id);

        Call<LoginModel> call = APIClient.getAPIClient().login(param);

        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                LoginModel loginData = response.body();
                Log.e("jrhfhrhgfrjh",response.toString());



                if (loginData != null){

                    if (loginData.getServiceName().equals("login")){

                        LoginModel.Data userData = loginData.getData();
                        SharedHelper.putKey(getApplicationContext(), AppConstats.USER_NAME, userData.getUserProfile().getFirstName());
                        SharedHelper.putKey(getApplicationContext(), AppConstats.USER_ID, String.valueOf(userData.getUserProfile().getUserId()));
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();

                    }

                }

                else
                {
                    Log.e("rgytrytr", response.toString());
                    Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                }



                startActivity(new Intent(LoginActivity.this, MainActivity.class));

            }

            @Override
            public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
                Log.e("sjduwoiej", t.getMessage(), t);
            }
        });
    }
}