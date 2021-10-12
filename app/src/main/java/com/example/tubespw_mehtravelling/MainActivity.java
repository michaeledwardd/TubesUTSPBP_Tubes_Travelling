package com.example.tubespw_mehtravelling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tubespw_mehtravelling.databinding.ActivityDashboardBinding;


public class MainActivity extends AppCompatActivity {

    ActivityDashboardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);
    }

    public View.OnClickListener btnListDestinasi= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivity = new Intent(MainActivity.this, TampilPegawai.class);
            startActivity(mainActivity);
        }
    };

    public View.OnClickListener btnPesanDestinasi = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivity = new Intent(MainActivity.this, InputDataPegawai.class);
            startActivity(mainActivity);
        }
    };
    public View.OnClickListener btnRiwayatDestinasi= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivity = new Intent(MainActivity.this, TampilDataPesertaVaksin.class);
            startActivity(mainActivity);
        }
    };
    public View.OnClickListener btnAboutUs = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivity = new Intent(MainActivity.this, TampilDataPesertaVaksin.class);
            startActivity(mainActivity);
        }
    };

}