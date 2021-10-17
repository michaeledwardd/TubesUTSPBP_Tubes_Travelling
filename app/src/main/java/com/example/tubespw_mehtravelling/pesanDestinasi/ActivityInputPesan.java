package com.example.tubespw_mehtravelling.pesanDestinasi;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.tubespw_mehtravelling.Database.DatabasePesanan;
import com.example.tubespw_mehtravelling.Database.DatabaseRegister;
import com.example.tubespw_mehtravelling.MainActivity;
import com.example.tubespw_mehtravelling.Model.User;
import com.example.tubespw_mehtravelling.Preferences.UserPreferences;
import com.example.tubespw_mehtravelling.R;
import com.example.tubespw_mehtravelling.ui.auth.LoginActivity;
import com.example.tubespw_mehtravelling.ui.auth.RegisterActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ActivityInputPesan extends AppCompatActivity {
//    TextInputLayout namaLayout, tanggalLayout, lamaLayout, tipeLayout;
    EditText nama, lama, tipe;
    Button btnCancel,btnCreate;
    private static final String TAG = "ActivityInputPesan";
    private EditText mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);

//        userPreferences = new UserPreferences(RegisterActivity.this);

        nama = findViewById(R.id.twnamaDestinasi);
//        tanggal = findViewById(R.id.tvDate);
        lama = findViewById(R.id.twlamaPesanan);
        tipe = findViewById(R.id.twtipePesanan);

        btnCancel = findViewById(R.id.btnCancel);
        btnCreate = findViewById(R.id.btnCreate);
//        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        mDisplayDate = findViewById(R.id.tvDate);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        ActivityInputPesan.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityInputPesan.this, MainActivity.class));
                finish();
            }
        });


//        View.OnClickListener btnTanggal = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Calendar newCalendar = Calendar.getInstance();
//                datePickerDialog     = new DatePickerDialog(ActivityInputPesan.this, new DatePickerDialog.OnDateSetListener(){
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
//                        Calendar newDate = Calendar.getInstance();
//                        newDate.set(year, monthOfYear, dayOfMonth);
////                        pesan.setTanggal(dateFormatter.format(newDate.getTime()));
//                        tanggal.setText(dateFormatter.format(newDate.getTime()));
//                    }
//                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//                datePickerDialog.show();
//            }
//        };

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validatePesan()){
                    pesan(nama.getText().toString().trim(), mDisplayDate.getText().toString().trim(), Integer.parseInt(lama.getText().toString().trim()), tipe.getText().toString().trim());
                }
            }
        });
    }

    private void pesan(String nama,String tanggal, int lama, String tipe){

        class PesanUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                Pesan pesan = new Pesan();
                pesan.setNama(nama);
                pesan.setTanggal(tanggal);
                pesan.setLama(lama);
                pesan.setTipe(tipe);

                DatabasePesanan.getInstance(ActivityInputPesan.this)
                        .getDatabase()
                        .pesanDao()
                        .insert(pesan);

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(ActivityInputPesan.this, "Berhasil pesan", Toast.LENGTH_SHORT).show();
//                clearField();
                startActivity(new Intent(ActivityInputPesan.this, MainActivity.class));
                finish();
            }

        }

        PesanUser pesanUser = new PesanUser();
        pesanUser.execute();
    }

//    private void clearField(){
//        etNama.setText("");
//        etAlamat.setText("");
//        etUsername.setText("");
//        etPassword.setText("");
//    }


    private boolean validatePesan(){
        /* Check username & password is empty or not */
        if(nama.getText().toString().trim().isEmpty() || mDisplayDate.getText().toString().trim().isEmpty() || lama.getText().toString().trim().isEmpty() || tipe.getText().toString().trim().isEmpty()){
            Toast.makeText(ActivityInputPesan.this,"Field tidak boleh Kosong",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
