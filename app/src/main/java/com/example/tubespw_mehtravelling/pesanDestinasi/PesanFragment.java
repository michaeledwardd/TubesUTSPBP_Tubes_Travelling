package com.example.tubespw_mehtravelling.pesanDestinasi;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.tubespw_mehtravelling.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.example.tubespw_mehtravelling.Database.DatabasePesanan;
import com.example.tubespw_mehtravelling.pesanDestinasi.Pesan;

public class PesanFragment extends Fragment {

    TextInputLayout namaLayout, tanggalLayout, lamaLayout, tipeLayout;
    TextInputEditText nama,tanggal, lama, tipe;
    Button btnCancel,btnCreate;
    FloatingActionButton fab;

    public PesanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,    ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_pesan, container, false);
        nama = view.findViewById(R.id.twnamaDestinasi);
        tanggal = view.findViewById(R.id.twtanggalPemesanan);
        lama = view.findViewById(R.id.twlamaPesanan);
        tipe= view.findViewById(R.id.twtipePesanan);
        btnCreate= view.findViewById(R.id.btnCreate);
        btnCancel = view.findViewById(R.id.btnCancel);

        namaLayout = view.findViewById(R.id.input_nama_layout);
        tanggalLayout =  view.findViewById(R.id.input_tanggal_layout);
        lamaLayout = view.findViewById(R.id.input_lama_layout);
        tipeLayout = view.findViewById(R.id.input_tipe_layout);

        fab = getActivity().findViewById(R.id.fab);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPesan();
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.show();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(PesanFragment.this).commit();
            }
        });
    }

    private void addPesan(){
        final int lamaAdd;

        final String nama = nama.getText().toString();
        final String lamaTemp = lama.getText().toString();
        final String tipe = tipe.getText().toString();
        final String tanggal = tanggal.getText().toString();


        if(nama.isEmpty()) {
            namaLayout.setError("Please fill nama correctly");;
        } else {
            namaLayout.setError(null);
        }

        if (tipe.isEmpty()) {
            tipeLayout.setError("Please fill tipe pemesanan correctly");
        } else {
            tipeLayout.setError(null);
        }
        if (tanggal.isEmpty()) {
            tanggalLayout.setError("Please fill tanggal pemesanan correctly");
        } else {
            tanggalLayout.setError(null);
        }

        if (lamaTemp.equals("")) {
            lamaAdd =0;
            lamaLayout.setError("Please fill lama pemesanan correctly");
        } else {
            lamaLayout.setError(null);
            lamaAdd = Integer.parseInt(lama.getText().toString());
        }

        if(!nama.isEmpty() && !tipe.isEmpty() && !lamaTemp.isEmpty() && !tanggal.isEmpty()) {

            class AddPesan extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... voids) {
                    Pesan pesan = new Pesan();
                    pesan.setNama(nama);
                    pesan.setLama(lamaAdd);
                    pesan.setTipe(tipe);
                    pesan.setTanggal(tanggal);

                    DatabasePesanan.getInstance(getActivity().getApplicationContext()).getDatabase()
                            .pesanDao()
                            .insert(pesan);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    fab.show();
                    Toast.makeText(getActivity().getApplicationContext(), "Data Pesanan saved", Toast.LENGTH_SHORT).show();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.hide(PesanFragment.this).commit();
                }
            }
            AddPesan add = new AddPesan();
            add.execute();
        }
    }


}