package com.example.tubespw_mehtravelling.ui.auth;

import static com.example.tubespw_mehtravelling.PushNotif.MyApplication.CHANNEL_1_ID;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.core.app.NotificationManagerCompat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.tubespw_mehtravelling.Database.DatabaseRegister;
import com.example.tubespw_mehtravelling.MainActivity;
import com.example.tubespw_mehtravelling.Model.User;
import com.example.tubespw_mehtravelling.Preferences.UserPreferences;
import com.example.tubespw_mehtravelling.R;
import com.google.android.material.button.MaterialButton;

public class RegisterActivity extends AppCompatActivity {
    private EditText etNama,etAlamat, etUsername, etPassword;
    private Button btnCancel, btnSave;
    private UserPreferences userPreferences;
    private NotificationManagerCompat notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        userPreferences = new UserPreferences(RegisterActivity.this);

        etNama = findViewById(R.id.etNama);
        etAlamat = findViewById(R.id.etAlamat);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnCancel = findViewById(R.id.cancel_button);
        btnSave = findViewById(R.id.save_button);
        notificationManager = NotificationManagerCompat.from(this);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    register(etNama.getText().toString(), etAlamat.getText().toString(), etUsername.getText().toString().trim(), etPassword.getText().toString().trim());
                    Intent activityIntent = new Intent(RegisterActivity.this, RegisterActivity.class);
                    PendingIntent contentIntent = PendingIntent.getActivity(RegisterActivity.this, 0, activityIntent, 0);
                    Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
                    Notification notification = new NotificationCompat.Builder(RegisterActivity.this, CHANNEL_1_ID)
                            .setSmallIcon(R.drawable.ic_baseline_looks_one_24)
                            .setContentTitle("MEH TRAVELLING")
                            .setLargeIcon(picture)
                            .setContentText("REGISTER SUKSES")
                            .setStyle(new NotificationCompat.BigPictureStyle()
                                    .bigPicture(picture)
                                    .bigLargeIcon(null))
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .setColor(Color.RED)
                            .setContentIntent(contentIntent)
                            .setAutoCancel(true)
                            .setOnlyAlertOnce(true)
                            .build();

                    notificationManager.notify(1, notification);
                }

            }
        });
    }


    private void register(String nama,String alamat, String username, String password){

        class RegisterUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                User user = new User();
                user.setNama(nama);
                user.setAlamat(alamat);
                user.setUsername(username);
                user.setPassword(password);

                DatabaseRegister.getInstance(RegisterActivity.this)
                        .getDatabase()
                        .userDao()
                        .registerUser(user);

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(RegisterActivity.this, "Berhasil mendaftar", Toast.LENGTH_SHORT).show();
                clearField();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }

        }

        RegisterUser registerUser = new RegisterUser();
        registerUser.execute();
    }

    private void clearField(){
        etNama.setText("");
        etAlamat.setText("");
        etUsername.setText("");
        etPassword.setText("");
    }


    private boolean validateForm(){
        /* Check username & password is empty or not */
        if(etUsername.getText().toString().trim().isEmpty() || etPassword.getText().toString().trim().isEmpty() || etNama.getText().toString().trim().isEmpty() || etAlamat.getText().toString().trim().isEmpty()){
            Toast.makeText(RegisterActivity.this,"Field tidak boleh Kosong",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}