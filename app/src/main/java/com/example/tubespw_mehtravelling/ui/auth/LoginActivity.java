package com.example.tubespw_mehtravelling.ui.auth;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tubespw_mehtravelling.API.ApiClient;
import com.example.tubespw_mehtravelling.API.ApiInterface;
import com.example.tubespw_mehtravelling.API.User.UserDAO;
import com.example.tubespw_mehtravelling.API.User.UserResponse;
import com.example.tubespw_mehtravelling.Constant;
import com.example.tubespw_mehtravelling.MainActivity;
import com.example.tubespw_mehtravelling.Model.User;
import com.example.tubespw_mehtravelling.R;
import com.example.tubespw_mehtravelling.survey.AddEditActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    MaterialTextView clickHere;
    MaterialButton login, btn_clearfield;
    TextInputLayout emailLayout, passLayout;
    TextInputEditText email, pass;
    List<User> userList;
    int idUser, currentIdUser = -1;
    private ProgressDialog progressDialog;

    private static String token;


    public static final int mode = Activity.MODE_PRIVATE;
    private SharedPreferences shared;

    Constant constant;
    SharedPreferences.Editor editor;
    SharedPreferences app_preferences;
    int appTheme;
    int themeColor;
    int appColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
        appColor = app_preferences.getInt("color", 0);
        appTheme = app_preferences.getInt("theme", 0);
        themeColor = appColor;
        constant.color = appColor;

        progressDialog = new ProgressDialog(this);

        if (themeColor == 0) {
            setTheme(Constant.theme);
        } else if (appTheme == 0) {
            setTheme(Constant.theme);
        } else {
            setTheme(appTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        shared = getSharedPreferences("getId", mode);
        idUser = shared.getInt("idUser", -1);

        Log.d("ID USER BEFORE LOGIN:", String.valueOf(idUser));
        //ID
        clickHere = findViewById(R.id.clickHere);
        login = findViewById(R.id.btn_login);
        email = findViewById(R.id.ti_login_email);
        pass = findViewById(R.id.ti_login_pass);
        btn_clearfield = findViewById(R.id.btn_clearfield);

        emailLayout = findViewById(R.id.til_login_email);
        passLayout = findViewById(R.id.til_login_pass);

        //Click here to sign up text link
        clickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToSignUp = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(moveToSignUp);
            }
        });

        btn_clearfield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email.setText("");
                pass.setText("");
            }
        });
        //Button Login clicked
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        final String emailLogin = email.getText().toString();
        final String passLogin = pass.getText().toString();

        if (TextUtils.isEmpty(emailLogin)) {
            email.setError("Email field is empty!");
            return;
        } else if (TextUtils.isEmpty(passLogin)) {
            pass.setError("Password field is empty");
            return;
        } else {
            progressDialog.setMessage("Logging In....");
            progressDialog.setProgressStyle(android.app.ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<UserResponse> req = apiService.login(
                    email.getText().toString(),
                    pass.getText().toString());

            req.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    //If response's code is 200
                    if (response.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        System.out.println("TEST SAMPE SINI");
                        UserDAO user = response.body().getUsers();

                        //get token
                        token = response.body().getToken();

                        SharedPreferences.Editor editor = shared.edit();
                        editor.putInt("idUser", Integer.parseInt(user.getId()));
                        System.out.println("ID user on response " + user.getId());

                        //save token
                        editor.putString("token", token);
                        editor.apply();
//                        Toast.makeText(LoginActivity.this,
//                                token, Toast.LENGTH_SHORT).show();
                        System.out.println("Test setelah editor apply");

                        if (user.getEmail().equalsIgnoreCase("admin@admin.com")) {
                            System.out.println("admin areaaa");
//                            Intent i = new Intent(LoginActivity.this, AdminActivity.class);
//                            startActivity(i);
                        } else {
                            System.out.println("non admin areaaa");
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            System.out.println("non admin area setelah put extra");
                            i.putExtra("id",user.getId());
                            i.putExtra("name",user.getName());
                            i.putExtra("email",user.getEmail());
                            i.putExtra("country",user.getCountry());
                            i.putExtra("phone",user.getPhone());
                            i.putExtra("city",user.getCity());
                            i.putExtra("image",user.getPhoto());
                            startActivity(i);
                            System.out.println("non admin area setelah start activity");
                            finish();
                            System.out.println("non admin area setelah finish");
                        }
                    } else { //If response's code is 4xx (error)
                        try {
                            JSONObject error = new JSONObject(response.errorBody().string());
                            Toast.makeText(LoginActivity.this, error.optString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Gagal Login", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Log.i("LOGIN", "Msg: " + t.getMessage());
                }
            });
        }
    }
}