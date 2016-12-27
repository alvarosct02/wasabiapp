package com.github.alvarosct02.wasabiapp.utils.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

@SuppressWarnings("unused")
public class APIError {

    @SerializedName("error")
    @Expose
    private Error error;

    public APIError() {
    }

    public String getMessage() {
        return error.getMessage();
    }

    public HashMap<String, String> getReasons() {
        return error.getReasons();
    }

    public class Error {
        @SerializedName("message")
        @Expose
        private String message;

        @SerializedName("reasons")
        @Expose
        private HashMap<String, String> reasons;

        public Error() {
        }

        public String getMessage() {
            return message;
        }

        public HashMap<String, String> getReasons() {
            return reasons;
        }
    }

}
