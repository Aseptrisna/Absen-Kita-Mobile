package com.csiindonesia.id.response;

import com.csiindonesia.id.model.modelUser;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class responseLogin {
    @SerializedName("status")
    private Boolean status;
    @SerializedName("message")
    private String message;
    @SerializedName("user")
    private List<modelUser> user = new ArrayList<modelUser>();
    private String token;
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<modelUser> getUser() {
        return user;
    }
    public void setUser(List<modelUser> user) {
        this.user = user;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
