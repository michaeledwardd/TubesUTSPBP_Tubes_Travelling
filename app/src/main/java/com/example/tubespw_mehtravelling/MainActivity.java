package com.example.tubespw_mehtravelling;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tubespw_mehtravelling.Database.DatabaseRegister;


public class MainActivity extends AppCompatActivity  {

    private Button btnLogin;
    private Button btnClear;
    private TextView txtusername;
    private TextView txtpassword;
    private TextView register;
    private String Username,Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtusername = findViewById(R.id.txtusername);
        txtpassword = findViewById(R.id.txtpassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnClear = findViewById(R.id.btnClear);

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                checkLoginStatus(Username,Password);
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                txtusername.setText("");
                txtpassword.setText("");
            }
        });
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent movePage = new Intent(MainActivity.this,RegisterActivity.class);
                MainActivity.this.startActivity(movePage);
            }
        });
    }

    private boolean checkLoginStatus(String username, String password)
    {
        return DatabaseRegister.getInstance(getApplicationContext()).getDatabase().userDao().checkLogin(username,password);
    }

    public void check()
    {
        String user = txtusername.getText().toString();
        Intent intent = new Intent(MainActivity.this,DashboardActivity.class);

            if(txtusername.getText().toString().equals("") || txtpassword.getText().toString().equals("") )
            {
                Toast.makeText(this, "Inputan tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }

            else
            {
            //    startActivity(new Intent(MainActivity.this, LoginActivity.class));
            Toast.makeText(this, "Berhasil Login", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            this.finish();
            }
    }


}