package com.example.tubespw_mehtravelling;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tubespw_mehtravelling.Preferences.UserPreferences;
import com.example.tubespw_mehtravelling.databinding.ActivityMainBinding;
import com.example.tubespw_mehtravelling.listDestinasi.TampilDataDestinasi;
import com.example.tubespw_mehtravelling.pesanDestinasi.ActivityInputPesan;
import com.example.tubespw_mehtravelling.pesanDestinasi.PesanActivity;
import com.example.tubespw_mehtravelling.pesanDestinasi.PesanFragment;
import com.example.tubespw_mehtravelling.survey.SurveyActivity;
import com.example.tubespw_mehtravelling.ui.auth.LoginActivity;
import com.example.tubespw_mehtravelling.ui.auth.RegisterActivity;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    UserPreferences userPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Disini kita menghubungkan menu yang telah kita buat dengan activity ini
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.home_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_exit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure want to exit?")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //  Keluar dari aplikasi
                           finishAndRemoveTask();
                        }
                    })
                    .show();
        }
        return super.onOptionsItemSelected(item);
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
            Intent mainActivity = new Intent(MainActivity.this, AboutUs.class);
           startActivity(mainActivity);
        }
    };
    public View.OnClickListener btnLokasi= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivity = new Intent(MainActivity.this, LokasiActivity.class);
            startActivity(mainActivity);
        }
    };
    public View.OnClickListener btnSurvey= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivity = new Intent(MainActivity.this, SurveyActivity.class);
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