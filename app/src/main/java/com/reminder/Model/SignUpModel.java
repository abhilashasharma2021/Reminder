package com.reminder.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignUpModel {
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("global_error")
    @Expose
    private String globalError;
    @SerializedName("error")
    @Expose
    private List<Object> error = null;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("last_task_id")
    @Expose
    private Integer lastTaskId;
    @SerializedName("app_data_version")
    @Expose
    private String appDataVersion;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGlobalError() {
        return globalError;
    }

    public void setGlobalError(String globalError) {
        this.globalError = globalError;
    }

    public List<Object> getError() {
        return error;
    }

    public void setError(List<Object> error) {
        this.error = error;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Integer getLastTaskId() {
        return lastTaskId;
    }

    public void setLastTaskId(Integer lastTaskId) {
        this.lastTaskId = lastTaskId;
    }

    public String getAppDataVersion() {
        return appDataVersion;
    }

    public void setAppDataVersion(String appDataVersion) {
        this.appDataVersion = appDataVersion;
    }


    public class UserProfile {

        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("user_unique_id")
        @Expose
        private String userUniqueId;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("user_name")
        @Expose
        private String userName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("master_country_id")
        @Expose
        private String masterCountryId;
        @SerializedName("phone_no")
        @Expose
        private String phoneNo;
        @SerializedName("referral_code")
        @Expose
        private String referralCode;
        @SerializedName("balance")
        @Expose
        private Integer balance;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("currency")
        @Expose
        private String currency;
        @SerializedName("require_otp")
        @Expose
        private Boolean requireOtp;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserUniqueId() {
            return userUniqueId;
        }

        public void setUserUniqueId(String userUniqueId) {
            this.userUniqueId = userUniqueId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getMasterCountryId() {
            return masterCountryId;
        }

        public void setMasterCountryId(String masterCountryId) {
            this.masterCountryId = masterCountryId;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getReferralCode() {
            return referralCode;
        }

        public void setReferralCode(String referralCode) {
            this.referralCode = referralCode;
        }

        public Integer getBalance() {
            return balance;
        }

        public void setBalance(Integer balance) {
            this.balance = balance;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public Boolean getRequireOtp() {
            return requireOtp;
        }

        public void setRequireOtp(Boolean requireOtp) {
            this.requireOtp = requireOtp;
        }
    }
    public class Data {

        @SerializedName("session_key")
        @Expose
        private String sessionKey;
        @SerializedName("user_profile")
        @Expose
        private UserProfile userProfile;
        @SerializedName("send_email_otp")
        @Expose
        private Boolean sendEmailOtp;
        @SerializedName("send_phone_otp")
        @Expose
        private Boolean sendPhoneOtp;

        public String getSessionKey() {
            return sessionKey;
        }

        public void setSessionKey(String sessionKey) {
            this.sessionKey = sessionKey;
        }

        public UserProfile getUserProfile() {
            return userProfile;
        }

        public void setUserProfile(UserProfile userProfile) {
            this.userProfile = userProfile;
        }

        public Boolean getSendEmailOtp() {
            return sendEmailOtp;
        }

        public void setSendEmailOtp(Boolean sendEmailOtp) {
            this.sendEmailOtp = sendEmailOtp;
        }

        public Boolean getSendPhoneOtp() {
            return sendPhoneOtp;
        }

        public void setSendPhoneOtp(Boolean sendPhoneOtp) {
            this.sendPhoneOtp = sendPhoneOtp;
        }
    }

}