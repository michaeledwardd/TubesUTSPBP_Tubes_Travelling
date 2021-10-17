package com.example.tubespw_mehtravelling;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tubespw_mehtravelling.Database.DatabasePesanan;
import com.example.tubespw_mehtravelling.Database.DatabaseRegister;
import com.example.tubespw_mehtravelling.Model.User;
import com.example.tubespw_mehtravelling.pesanDestinasi.Pesan;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class UpdateFragment extends Fragment {

//    TextInputLayout  nameLayout, tipeLayout;
    EditText nama, tipe;
    Button saveBtn, deleteBtn, cancelBtn;
    Pesan pesan;

    public UpdateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update, container, false);

        pesan = (Pesan) getArguments().getSerializable("pesan");
        nama = view.findViewById(R.id.etnamaDestinasi);
        tipe = view.findViewById(R.id.etTipe);

        saveBtn = view.findViewById(R.id.btnSave);
        deleteBtn = view.findViewById(R.id.btnDelete);
        cancelBtn = view.findViewById(R.id.btnCancel);
//
//        nameLayout = view.findViewById(R.id.input_name_layout);
//        tipeLayout =  view.findViewById(R.id.input_tipe_layout);

        try {
            if (pesan.getNama() != null) {
                nama.setText(pesan.getNama());
                tipe.setText(pesan.getTipe());
            } else {
                nama.setText("-");
                tipe.setText("-");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nama.getText().toString().equals("")) {
                    nama.setError(null);
                    pesan.setNama(nama.getText().toString());
                } else {
                    nama.setError("Please fill name correctly");
                }
                if(!tipe.getText().toString().equals(""))
                {
                    tipe.setError(null);
                    pesan.setTipe(tipe.getText().toString());
                } else {

                    tipe.setError("Please fill age correctly");
                }

            }
        });

//        deleteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setMessage("Are you sure to delete?")
//                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                // User cancelled the dialog
//                            }
//                        })
//                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                delete(pesan);
//                            }
//                        });
//
//                // Create the AlertDialog object and return it
//                builder.create().show();
////                fab.show();
//
//            }
//        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fab.show();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(UpdateFragment.this).commit();
            }
        });
    }

    private void update(final Pesan pesan){
        class UpdatePesan extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabasePesanan.getInstance(getActivity().getApplicationContext()).getDatabase()
                        .pesanDao()
                        .update(pesan);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity().getApplicationContext(), "User updated", Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(UpdateFragment.this).commit();
            }
        }

        UpdatePesan update = new UpdatePesan();
        update.execute();
    }

    private void delete(final Pesan pesan){
        class DeletePesan extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabasePesanan.getInstance(getActivity().getApplicationContext()).getDatabase()
                        .pesanDao()
                        .delete(pesan);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity().getApplicationContext(), "Employee deleted", Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(UpdateFragment.this).commit();
            }
        }

        DeletePesan delete = new DeletePesan();
        delete.execute();
    }
}