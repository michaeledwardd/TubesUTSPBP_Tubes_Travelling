package com.example.tubespw_mehtravelling;

import static androidx.core.content.ContextCompat.startActivity;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tubespw_mehtravelling.Database.DatabaseRegister;
import com.example.tubespw_mehtravelling.Model.User;

public class RegisterActivity {
    private EditText etNama;
    private EditText etAlamat;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnCancel;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                etNama.setText("");
                etAlamat.setText("");
                etUsername.setText("");
                etPassword.setText("");

                Intent movePage = new Intent(RegisterActivity.this,MainActivity.class);
                RegisterActivity.this.startActivity(movePage);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener()
        {
            addUser();
        });

    }

    private void addUser()
    {
        final String nama = etNama.getText().toString();
        final String alamat = etAlamat.getText().toString();
        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();

        class AddUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                User user = new User();


                DatabaseRegister.getInstance(getContext())
                        .getDatabase()
                        .UserDao()
                        .insertUser(user);

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(getContext(), "Berhasil menambahkan data", Toast.LENGTH_SHORT).show();
                etNama.setText("");
                etAlamat.setText("");
                etUsername.setText("");
                etPassword.setText("");

//                getTodos();
            }

        }
        AddUser addUser = new AddUser();
        addUser.execute();
    }
}
