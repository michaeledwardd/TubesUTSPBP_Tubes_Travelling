package com.example.tubespw_mehtravelling;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tubespw_mehtravelling.Database.DatabaseRegister;
import com.example.tubespw_mehtravelling.Model.User;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private EditText etNama;
    private EditText etAlamat;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnCancel;
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        etNama = findViewById(R.id.etNama);
        etAlamat = findViewById(R.id.etAlamat);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnCancel = findViewById(R.id.cancel_button);
        btnSave = findViewById(R.id.save_button);

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                etNama.setText("");
                etAlamat.setText("");
                etUsername.setText("");
                etPassword.setText("");

                Intent pindahHalaman = new Intent(RegisterActivity.this,MainActivity.class);
                RegisterActivity.this.startActivity(pindahHalaman);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                addUser();
            }
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
                user.setNama(nama);
                user.setAlamat(alamat);
                user.setUsername(username);
                user.setPassword(password);

                DatabaseRegister.getInstance(getApplicationContext())
                        .getDatabase()
                        .userDao()
                        .insertUser(user);

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(getApplicationContext(), "Berhasil melakukan registrasi", Toast.LENGTH_SHORT).show();
                etNama.setText("");
                etAlamat.setText("");
                etUsername.setText("");
                etPassword.setText("");

                getRegister();
            }

        }
        AddUser addUser = new AddUser();
        addUser.execute();
    }

    private void getRegister()
    {
        class GetUser extends AsyncTask<Void, Void, List<User>> {

            @Override
            protected List<User> doInBackground(Void... voids) {
                List<User> userList = DatabaseRegister.getInstance(getApplicationContext())
                        .getDatabase()
                        .userDao()
                        .getAll();
                return userList;
            }

            @Override
            protected void onPostExecute(List<User> user) {
                super.onPostExecute(user);
//                todoAdapter = new TodoAdapter(todos, getContext());
//                rv_todoList.setAdapter(todoAdapter);
            }
        }

        GetUser getUsers = new GetUser();
        getUsers.execute();
    }
}
