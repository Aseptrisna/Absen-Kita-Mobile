package com.csiindonesia.id.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class modelUser {
    private String id;
    private String guid;
    private String name;
    private String email;
    private String password;
    private Number telp;
    private String address;
    private String role;
    private String instansi;
    private String unit;
    private Integer otp;
    private Boolean isActive;
    private List<Object> units = new ArrayList<Object>();
    private List<Object> aplication = new ArrayList<Object>();
    private String createAt;
    private Integer v;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getGuid() {
        return guid;
    }
    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Number getTelp() {
        return telp;
    }
    public void setTelp(Integer telp) {
        this.telp = telp;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public Integer getOtp() {
        return otp;
    }
    public void setOtp(Integer otp) {
        this.otp = otp;
    }
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    public List<Object> getUnits() {
        return units;
    }
    public void setUnit(List<Object> unit) {
        this.units = units;
    }
    public List<Object> getAplication() {
        return aplication;
    }
    public void setAplication(List<Object> aplication) {
        this.aplication = aplication;
    }
    public String getCreateAt() {
        return createAt;
    }
    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
    public Integer getV() {
        return v;
    }
    public void setV(Integer v) {
        this.v = v;
    }

    public String getInstansi() {
        return instansi;
    }
    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
}
