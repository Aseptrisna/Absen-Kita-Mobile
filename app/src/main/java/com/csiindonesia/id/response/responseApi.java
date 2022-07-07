package com.csiindonesia.id.response;

import com.google.gson.annotations.SerializedName;

public class responseApi {
    @SerializedName("status")
    private boolean status;

    @SerializedName("message")
    private String message;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return
                "{" +
                        "status = '" + status + '\'' +
                        "message = '" + message + '\'' +
                        "}";
    }
}
