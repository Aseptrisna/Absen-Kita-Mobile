package com.csiindonesia.id.localstorage;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    public static final String SP_LOGIN_APP = "Apps";
    public static final String SP_Email= "spUsername";
    public static final String SP_Guid= "spGuid";
    public static final String SP_Nama= "spNama";
    public static final String SP_Telp= "spTelp";
    public static final String SP_Password = "spPassword";
    public static final String SP_Status= "spStatus";
    public static final String SP_Instansi= "spInstansi";
    public static final String SP_Units= "SPUnits";
    public static final String SP_SUDAH_LOGIN = "SudahLogin";
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_LOGIN_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }
    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }
    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }
    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }
    public String getSP_Email(){
        return sp.getString(SP_Email, "");
    }
    public String getSP_Password(){
        return sp.getString(SP_Password, "");
    }
    public String getSP_Status(){
        return sp.getString(SP_Status, "");
    }
    public Boolean getSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
    public String getSP_Nama(){
        return sp.getString(SP_Nama, "");
    }
    public String getSP_Telp(){
        return sp.getString(SP_Telp, "");
    }
    public String getSP_Instansi(){
        return sp.getString(SP_Instansi, "");
    }
    public String getSP_Units(){
        return sp.getString(SP_Units, "");
    }
    public String getSP_Guid(){
        return sp.getString(SP_Guid, "");
    }

}
