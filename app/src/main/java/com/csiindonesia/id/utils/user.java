package com.csiindonesia.id.utils;

import com.csiindonesia.id.model.modelAbsen;
import com.csiindonesia.id.model.modelUnits;
import com.csiindonesia.id.model.modelUser;

import java.util.List;

public interface user {
    void Berhasil(String message, String Message);
    void Gagal(String Message);
    void No_Internet(String Message);
    void Berhasil(List<modelUser> datauser);
    void Unit(List<modelUnits> data);
    void succes(String message);
    void Succesgetdata(List<modelAbsen> data);
}
