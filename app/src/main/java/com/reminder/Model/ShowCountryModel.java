package com.reminder.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShowCountryModel {
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

    public class Data {

        @SerializedName("country_list")
        @Expose
        private List<Country> countryList = null;

        public List<Country> getCountryList() {
            return countryList;
        }

        public void setCountryList(List<Country> countryList) {
            this.countryList = countryList;
        }


        public class Country{
            @SerializedName("master_country_id")
            @Expose
            private Integer countryId;

            @SerializedName("country_name")
            @Expose
            private String country_name;

            public Integer getCountryId() {
                return countryId;
            }

            public void setCountryId(Integer countryId) {
                this.countryId = countryId;
            }

            public String getCountry_name() {
                return country_name;
            }

            public void setCountry_name(String country_name) {
                this.country_name = country_name;
            }

            public String getPhonecode() {
                return phonecode;
            }

            public void setPhonecode(String phonecode) {
                this.phonecode = phonecode;
            }

            @SerializedName("phonecode")
            @Expose
            private String phonecode;

        }
    }

    }
