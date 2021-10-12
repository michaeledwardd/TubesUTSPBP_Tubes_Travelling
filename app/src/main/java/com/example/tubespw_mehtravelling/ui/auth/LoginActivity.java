package com.example.tubespw_mehtravelling.ui.auth;

import com.example.tubespw_mehtravelling.MainActivity;
import com.example.tubespw_mehtravelling.R;
import com.example.tubespw_mehtravelling.ui.auth.RegisterActivity;
import com.example.tubespw_mehtravelling.Database.DatabaseRegister;
import com.example.tubespw_mehtravelling.Model.User;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tubespw_mehtravelling.Preferences.UserPreferences;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {
    private EditText txtUsername, txtPassword;
    private Button btnClear, btnLogin, btnRegister;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userPreferences = new UserPreferences(LoginActivity.this);

        txtUsername = findViewById(R.id.txtusername);
        txtPassword = findViewById(R.id.txtpassword);

        btnClear = findViewById(R.id.btnClear);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnregister);

        /* Apps will check the login first from shared preferences */
        checkLogin();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });

        /* to clear the field just set text to "" */
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtUsername.setText("");
                txtPassword.setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateForm()){
                    attemptLogin(txtUsername.getText().toString().trim(), txtPassword.getText().toString().trim());
                }
            }
        });

    }


    private void attemptLogin(String username, String password){

        class AttemptLogin extends AsyncTask<Void, Void, User> {
            @Override
            protected User doInBackground(Void... voids) {

                User user = DatabaseRegister.getInstance(LoginActivity.this)
                        .getDatabase()
                        .userDao()
                        .attemptLogin(username,password);

                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                if(user == null){
                    Toast.makeText(LoginActivity.this, "Username atau password salah", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "Berhasil login", Toast.LENGTH_SHORT).show();
                    userPreferences.setUser(user.getId(),user.getNama(), user.getAlamat(),user.getUsername(),user.getPassword());
                }
                checkLogin();

            }

        }

        AttemptLogin attemptLogin = new AttemptLogin();
        attemptLogin.execute();
    }

    private boolean validateForm(){
        /* Check username & password is empty or not */
        if(txtUsername.getText().toString().trim().isEmpty() || txtPassword.getText().toString().trim().isEmpty()){
            Toast.makeText(LoginActivity.this,"Username Atau Password Kosong",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void checkLogin(){
        if(userPreferences.checkLogin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

}