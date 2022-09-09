package com.reminder.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.reminder.ApiData.APIClient;
import com.reminder.MainActivity;
import com.reminder.Model.ShowCountryModel;
import com.reminder.Model.SignUpModel;
import com.reminder.R;
import com.reminder.databinding.ActivitySignUpBinding;
import com.reminder.utils.AppConstats;
import com.reminder.utils.InternetConnection.InternetConnectionInterface;
import com.reminder.utils.InternetConnection.InternetConnectivity;
import com.reminder.utils.SharedHelper;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    String[] gender;
    String stName = "", stEmail = "", stDob = "", stPassword = "",stNewPass="";
    String selectedGender, selectedCountryId;
    int sp_position;
    ArrayList<String> countryName, countryId;
    DatePickerDialog datePickerDialog;
    private String device_id = "";
    int hourOfDay,minute,second;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rlTerm.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, TermAndConditionActivity.class));
        });

       /* Calendar calendar = Calendar.getInstance();
        calendar.set(0, 0, 0, hourOfDay, minute, second);
        long timeInMillis = calendar.getTimeInMillis();
        java.text.DateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");
        Date date1 = new Date();
        date1.setTime(timeInMillis);*/

    /*    Calendar c1 = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c1.
        ());
        Log.e("Date","DATE : " + strDate);*/



        binding.txContinue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(SignUpActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        Log.e("xcjkxjckxvk", "onTimeSet: "+  selectedHour + ":" + selectedMinute);
                        binding.txContinue.setText( selectedHour + ":" + selectedMinute);

                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });



        device_id = Settings.Secure.getString(SignUpActivity.this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        String text = "Have an account ?<font color=#4F597E>Login</font>";
        binding.txLogin.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);

        String myString = "Gender";

        gender = getResources().getStringArray(R.array.spinner);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(SignUpActivity.this, android.R.layout.simple_spinner_dropdown_item, gender);
        sp_position = ad.getPosition(myString);
        binding.spGender.setAdapter(ad);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(getResources().getColor(R.color.black));
                ((TextView) arg0.getChildAt(0)).setTextSize(13);

                selectedGender = binding.spGender.getSelectedItem().toString();

                Log.e("ssssss", "onItemSelected: " + selectedGender);
                System.out.println(selectedGender);
                setid();

            }

            private void setid() {


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");


        String c = simpleDateFormat.format(date);

        binding.txDate.setText(c);

        binding.rlDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);


                datePickerDialog = new DatePickerDialog(SignUpActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                                 /*25-02-2022*/
                                monthOfYear+=1;
                                String mt,dy;   //local variable
                                if(monthOfYear<10)
                                    mt="0"+monthOfYear; //if month less than 10 then ad 0 before month
                                else mt=String.valueOf(monthOfYear);

                                if(dayOfMonth<10)
                                    dy = "0"+dayOfMonth;
                                else dy = String.valueOf(dayOfMonth);
                                binding.txDate.setText(dy+"-"+mt+"-"+year);

                                binding.txDate.setTextColor(getResources().getColor(R.color.black));
                            }
                        }, year, month, day);

                datePickerDialog.show();
            }

        });


        binding.spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectedCountryId = countryId.get(i);
                Log.e("dsgfdgfdg", selectedCountryId);
                ((TextView) view).setTextColor(
                        getResources().getColor(R.color.black)
                );

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        showCountry();

        binding.txLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stEmail = binding.etEmail.getText().toString();
                stPassword = binding.etPass.getText().toString();
                stName = binding.etName.getText().toString();
                stDob = binding.txDate.getText().toString();

            if (validation()) {

                    if (!validateEmailAddress(stEmail)) {
                        Toast.makeText(SignUpActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    }
                    else if (dobdateValidate(stDob)){
                        Toast.makeText(SignUpActivity.this, "Only 17 years old allowed.", Toast.LENGTH_SHORT).show();
                    }

                    else {
                        InternetConnectionInterface connectivity = new InternetConnectivity();
                        if (connectivity.isConnected(getApplicationContext())) {
                            stNewPass=getMd5(stPassword);

                            signUp(stEmail, stName, selectedCountryId, stDob, selectedGender);

                            Log.e("sdjdkiscids", "onClick: "+stNewPass);
                        } else {
                            Toast.makeText(SignUpActivity.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            else {
                    validation();
                }


            }

        });


    }



    private Boolean validation() {

        Boolean boolen = false;
        if (binding.etEmail.getText().toString().isEmpty()) {
            binding.etEmail.setError("Email Address Must Required");

        } else if (binding.etName.getText().toString().isEmpty()) {
            binding.etName.setError("Name Must Required");

        } else if (binding.etPass.getText().toString().isEmpty()) {
            binding.etPass.setError("Password Must Required");

        }
        else if (stPassword.length()<4) {
            binding.etPass.setError("The Password must be at least 4 characters in length");

        }else if (binding.txDate.getText().toString().isEmpty()) {
            binding.txDate.setError("Dob Must Required");
        } else if (selectedGender.isEmpty()) {

            Toast.makeText(SignUpActivity.this, "Please select gender", Toast.LENGTH_SHORT).show();
        } else if (selectedCountryId.isEmpty()) {

            Toast.makeText(SignUpActivity.this, "Please select country", Toast.LENGTH_SHORT).show();
        } else {
            boolen = true;

        }
        return boolen;
    }


    private Boolean dobdateValidate(String date) {
        boolean result = false;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date parseddate = sdf.parse(date);
            Calendar c2 = Calendar.getInstance();
            c2.add(Calendar.DAY_OF_YEAR, -17);

            Calendar dob = Calendar.getInstance();
            dob.setTime(parseddate);
            Calendar today = Calendar.getInstance();
            int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
            if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
                age--;
            } else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
                    && today.get(Calendar.DAY_OF_MONTH) < dob
                    .get(Calendar.DAY_OF_MONTH)) {
                age--;
            }

            if (age < 17) {
                Toast.makeText(SignUpActivity.this, "Only 17 years old allowed.", Toast.LENGTH_SHORT).show();
            } else {

            }


            Log.e("dsfdsf", getClass().getSimpleName() + ": Age in year= " + age);


            Log.e("dsfdsf", "c2: "+c2);

            if (parseddate.before(c2.getTime())) {

                Log.e("dsfdsf", "dobdateValidate: "+parseddate.before(c2.getTime()));

            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public static boolean validateEmailAddress(String stEmail) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(stEmail);
        return matcher.matches();
    }



    private void showCountry() {


        Call<ShowCountryModel> call = APIClient.getAPIClient().showCountries();
        call.enqueue(new Callback<ShowCountryModel>() {
            @Override
            public void onResponse(@NonNull Call<ShowCountryModel> call, @NonNull Response<ShowCountryModel> response) {

                ShowCountryModel countryData = response.body();
                if (countryData.getServiceName().equals("")) {

                    Toast.makeText(getApplicationContext(), "Something went wrong!!", Toast.LENGTH_SHORT).show();
                } else {

                    countryName = new ArrayList<>();
                    countryId = new ArrayList<>();
                    List<ShowCountryModel.Data.Country> dataList = countryData.getData().getCountryList();
                    for (ShowCountryModel.Data.Country data : dataList) {
                        countryId.add(String.valueOf(data.getCountryId()));
                        countryName.add(data.getCountry_name());
                        Log.e("kskldc", data.getCountry_name());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SignUpActivity.this, android.R.layout.simple_list_item_1, countryName);
                    binding.spCountry.setAdapter(adapter);


                }


            }

            @Override
            public void onFailure(@NonNull Call<ShowCountryModel> call, @NonNull Throwable t) {
                Log.e("fdgdfgfd", t.getMessage());

            }
        });
    }


    public static String getMd5(String input) {
        try {


            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);


            String hashtext = no.toString(16);
            Log.e("kokioi", "getMd5: " + hashtext);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }




    private void signUp(String stEmail, String stName, String selectedCountryId, String stDob, String selectedGender) {


        Log.e("jdhjfh", "stName: " +stName);
        Log.e("jdhjfh", "stEmail:" + stEmail);
        Log.e("jdhjfh", "hashtext: " + stNewPass);
        Log.e("jdhjfh", "stDob: " + stDob);
        Log.e("jdhjfh", "selectedCountryId: " + selectedCountryId);
        Log.e("jdhjfh", "selectedGender: " + selectedGender);
        Log.e("jdhjfh", "device_type");
        Log.e("jdhjfh", "device_id: " + device_id);
        Map<String, String> param = new HashMap<>();
        param.put("first_name",stName);
        param.put("email",stEmail);
        param.put("password",stNewPass);
        param.put("dob",stDob);
        param.put("master_country_id",selectedCountryId);
        param.put("gender",selectedGender);
        param.put("device_type", "2");
        param.put("device_id", device_id);


        Call<SignUpModel> call = APIClient.getAPIClient().signUp(param);

        call.enqueue(new Callback<SignUpModel>() {
            @Override
            public void onResponse(@NonNull Call<SignUpModel> call, @NonNull Response<SignUpModel> response) {
                Log.e("vegdvgv", response.code()+"");

                SignUpModel signupData = response.body();



                if (signupData != null){

                    if (signupData.getServiceName().equals("signup")){

                        SignUpModel.Data userData = signupData.getData();
                        SharedHelper.putKey(getApplicationContext(), AppConstats.USER_NAME, userData.getUserProfile().getFirstName());
                        SharedHelper.putKey(getApplicationContext(), AppConstats.USER_EMAIL, userData.getUserProfile().getEmail());
                        SharedHelper.putKey(getApplicationContext(), AppConstats.USER_ID, String.valueOf(userData.getUserProfile().getUserId()));
                        SharedHelper.putKey(getApplicationContext(), AppConstats.USER_DOB, userData.getUserProfile().getDob());
                        SharedHelper.putKey(getApplicationContext(), AppConstats.USER_GENDER, userData.getUserProfile().getGender());
                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                        finish();

                    }
                    else {

                    }

                }

                else
                {
                    Toast.makeText(SignUpActivity.this, "Only 17 years old allowed.", Toast.LENGTH_SHORT).show();
                    Log.e("rgytrytr", response.toString());

                }



            }

            @Override
            public void onFailure(@NonNull Call<SignUpModel> call, @NonNull Throwable t) {
                Log.e("ferferfef", t.getMessage(), t);
            }
        });

    }



}