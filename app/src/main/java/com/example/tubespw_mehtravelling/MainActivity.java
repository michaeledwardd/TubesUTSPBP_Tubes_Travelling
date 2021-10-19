package com.example.tubespw_mehtravelling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tubespw_mehtravelling.Preferences.UserPreferences;
import com.example.tubespw_mehtravelling.databinding.ActivityMainBinding;
import com.example.tubespw_mehtravelling.listDestinasi.TampilDataDestinasi;
import com.example.tubespw_mehtravelling.pesanDestinasi.ActivityInputPesan;
import com.example.tubespw_mehtravelling.pesanDestinasi.PesanActivity;
import com.example.tubespw_mehtravelling.pesanDestinasi.PesanFragment;
import com.example.tubespw_mehtravelling.ui.auth.LoginActivity;
import com.example.tubespw_mehtravelling.ui.auth.RegisterActivity;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    UserPreferences userPreferences=null;
//    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);

//        btnLogout=findViewById(R.id.btnLogout);
//
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                userPreferences.logout();
//                Toast.makeText(MainActivity.this, "Baiii baiii", Toast.LENGTH_SHORT).show();
//                checkLogin();
//            }
//        });
    }

    public View.OnClickListener btnListDestinasi= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivity = new Intent(MainActivity.this, TampilDataDestinasi.class);
            startActivity(mainActivity);
        }
    };

    public View.OnClickListener btnPesanDestinasi = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivity = new Intent(MainActivity.this, ActivityInputPesan.class);
            startActivity(mainActivity);
        }
    };
    public View.OnClickListener btnRiwayatDestinasi= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivity = new Intent(MainActivity.this, PesanActivity.class);
            startActivity(mainActivity);
        }
    };
    public View.OnClickListener btnAboutUs = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent mainActivity = new Intent(MainActivity.this, .class);
//            startActivity(mainActivity);
        }
    };
    public View.OnClickListener btnLokasi= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivity = new Intent(MainActivity.this, LokasiActivity.class);
            startActivity(mainActivity);
        }
    };

    public View.OnClickListener btnLogout= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivity = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(mainActivity);
        }
    };

//    public View.OnClickListener btnLogout= new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
////            Intent mainActivity = new Intent(MainActivity.this, RegisterActivity.class);
////            startActivity(mainActivity);
////            finish();
//            userPreferences.logout();
//            Toast.makeText(MainActivity.this, "Baiii baiii", Toast.LENGTH_SHORT).show();
//            checkLogin();
//        }
//    };
//    private void checkLogin(){
//        /* this function will check if user login , akan memunculkan toast jika tidak redirect ke login activity */
//        if(!userPreferences.checkLogin()){
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            finish();
//        }else {
//            Toast.makeText(MainActivity.this, "Welcome back !", Toast.LENGTH_SHORT).show();
//        }
//    }
}