package com.scouljobs.hunter.scouljobs;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class CustomToast {
    private AppCompatTextView message;
    private AppCompatImageView icon;
    private Context context;

    private SharedPreferences sharedPreferences;

    public CustomToast(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Config.MYPREFERENCES, Context.MODE_PRIVATE);
    }

    public void setToast(String _message){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View customToast = layoutInflater.inflate(R.layout.custom_toast, null);

        message = customToast.findViewById(R.id.message);
        icon = customToast.findViewById(R.id.icon);

        message.setText(_message);

        Toast toast = new Toast(context);
        toast.setView(customToast);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    public void setPrefs(int _id, String _firstname, String _lastname, String _email, int _access, String dob, String nationality, String phone, String education, String address){


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("session_id", _id);
        editor.putString("session_firstname", _firstname);
        editor.putString("session_lastname", _lastname);
        editor.putString("session_email", _email);
        editor.putString("session_nationality", nationality);
        editor.putInt("access_level", _access);
        editor.putString("session_dob", dob);
        editor.putString("session_phone", phone);
        editor.putString("session_education", education);
        editor.putString("session_address", address);
        editor.apply();

    }

    public void deletePrefs(){

        sharedPreferences.edit().clear().commit();
    }

    public int getAccessLevel(){
        int access_level = sharedPreferences.getInt("access_level", 0);

        return access_level;
    }

    public String getSessionFirstname(){
        return sharedPreferences.getString("session_education", null);
    }

    public String getEducation(){
        return sharedPreferences.getString("session_firstname", null);
    }

    public String getPhone(){
        return sharedPreferences.getString("session_phone", null);
    }

    public String getNationality(){
        return sharedPreferences.getString("session_nationality", null);
    }

    public String getAddress(){
        return sharedPreferences.getString("session_address", null);
    }

    public String getDOB(){
        return sharedPreferences.getString("session_dob", null);
    }


    public String getSessionLastname(){
        return sharedPreferences.getString("session_lastname", null);
    }

    public String getSessionEmail(){
        return sharedPreferences.getString("session_email", null);
    }

    public int getUserId(){
        int id = sharedPreferences.getInt("session_id", 0);

        return id;
    }

}
