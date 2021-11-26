package com.example.tubespw_mehtravelling;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ThemeActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences, app_preferences;
    SharedPreferences.Editor editor;
    Button button;
    Colors colors;

    int appTheme;
    int themeColor;
    int appColor;
    Constant constant;

    String selectedWarna="Default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
        appColor = app_preferences.getInt("color", 0);
        appTheme = app_preferences.getInt("theme", 0);
        themeColor = appColor;
        constant.color = appColor;

        if (themeColor == 0){
            setTheme(Constant.theme);
        }else if (appTheme == 0){
            setTheme(Constant.theme);
        }else{
            setTheme(appTheme);
        }
        setContentView(R.layout.activity_theme);

        if(appColor == 3)
            appColor = 0;

        colors = new Colors();
        button = (Button) findViewById(R.id.button_color);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                optionsDialog();
            }
        });
    }

    private void optionsDialog(){
        final String[] warna = {"Default", "Red-Gold", "Blue-Purple"};
        AlertDialog.Builder builder = new AlertDialog.Builder(ThemeActivity.this);
        builder.setTitle("Pick One");
        builder.setSingleChoiceItems(warna, appColor, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedWarna = warna[which];
            }
        });
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int value = 0;

                if (selectedWarna.equals("Red-Gold"))
                    value = 1;
                else if (selectedWarna.equals("Blue-Purple"))
                    value = 2;
                else if (selectedWarna.equals("Default"))
                    value = 3;

                Constant.color = value;

                colors.setColorTheme();
                editor.putInt("color", value);
                editor.putInt("theme",Constant.theme);
                editor.commit();

                Intent intent = new Intent(ThemeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

}