package com.example.tubespw_mehtravelling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tubespw_mehtravelling.databinding.ActivityMainBinding;
import com.example.tubespw_mehtravelling.listDestinasi.TampilDataDestinasi;
import com.example.tubespw_mehtravelling.pesanDestinasi.ActivityInputPesan;
import com.example.tubespw_mehtravelling.pesanDestinasi.PesanActivity;
import com.example.tubespw_mehtravelling.pesanDestinasi.PesanFragment;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);
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

}