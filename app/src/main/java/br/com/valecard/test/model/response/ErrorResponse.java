package br.com.valecard.test.model.response;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("timestamp")
    private long timestamp;

    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("error")
    private String error;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
