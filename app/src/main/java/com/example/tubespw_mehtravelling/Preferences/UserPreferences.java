package com.example.tubespw_mehtravelling.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tubespw_mehtravelling.Model.User;


public class UserPreferences {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "nama";
    public static final String KEY_ALAMAT = "alamat";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    public UserPreferences(Context context) {
        this.context = context;
        /* penamaan bebas namun disini digunakan "userPreferences" */
        sharedPreferences = context.getSharedPreferences("userPreferences",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setUser(int id,String nama, String alamat, String username, String password){

        /* Menyimpan data login ke sharedPreferences dengan key dan value  */
        editor.putBoolean(IS_LOGIN, true);
        editor.putInt(KEY_ID,id);
        editor.putString(KEY_NAME,nama);
        editor.putString(KEY_ALAMAT,alamat);
        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_PASSWORD,password);

        /* Jangan lupa commit karena kalo hanya set editonya saja tidak commit akan sia-sia */
        editor.commit();
    }

    public User getUserLogin(){
        /* Mengembalikan object User untuk menampilkan data user jika user sudah login */
        String nama,alamat, username,password;
        int id;

        id = sharedPreferences.getInt(KEY_ID,0);
        nama = sharedPreferences.getString(KEY_NAME,null);
        alamat = sharedPreferences.getString(KEY_ALAMAT,null);
        username = sharedPreferences.getString(KEY_USERNAME,null);
        password = sharedPreferences.getString(KEY_PASSWORD,null);

        return new User(id,nama,alamat,username,password);
    }

    public boolean checkLogin(){
        /* Mengembalikan nilai is_login, jika sudah login otomatis nilai true jika tidak akan return false */
        return sharedPreferences.getBoolean(IS_LOGIN,false);
    }

    public void logout(){
        /* Melakukan clear data yang ada pada sharedPreferences  , jangan lupa di commit agar data terubah*/
        editor.clear();
        editor.commit();
    }
}