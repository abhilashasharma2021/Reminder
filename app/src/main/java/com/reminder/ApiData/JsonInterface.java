package com.reminder.ApiData;
import com.reminder.Model.ForgotPasswordModel;
import com.reminder.Model.LoginModel;
import com.reminder.Model.ShowCountryModel;
import com.reminder.Model.SignUpModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface JsonInterface {


    @POST(API.show_country)
    Call<ShowCountryModel> showCountries();

    @FormUrlEncoded
    @POST(API.signUp)
    Call<SignUpModel> signUp(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(API.login)
    Call<LoginModel> login(@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST(API.forgot)
    Call<ForgotPasswordModel> forgotPassword(@FieldMap Map<String, String> params);
}
