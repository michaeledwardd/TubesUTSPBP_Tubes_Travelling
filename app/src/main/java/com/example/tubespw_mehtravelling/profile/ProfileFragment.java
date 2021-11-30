package com.example.tubespw_mehtravelling.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tubespw_mehtravelling.API.ApiClient;
import com.example.tubespw_mehtravelling.API.ApiInterface;
import com.example.tubespw_mehtravelling.API.User.UserResponse;
import com.example.tubespw_mehtravelling.Constant;
import com.example.tubespw_mehtravelling.MainActivity;
import com.example.tubespw_mehtravelling.R;
import com.example.tubespw_mehtravelling.databinding.FragmentProfileBinding;
import com.example.tubespw_mehtravelling.profile.model.User;
import com.google.android.material.textview.MaterialTextView;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private com.example.tubespw_mehtravelling.profile.ProfileViewModel profileViewModel;
    private MaterialTextView email, name, username, phone, city, country;
    private CircleImageView image;
    private List<User> userList;
    private String sIdUser, sName, sEmail, sCountry, sCity, sPhone, sImage;
    Bitmap[] array;

    SharedPreferences shared;
    int idUser;
    String token;

    Constant constant;
    SharedPreferences.Editor editor;
    SharedPreferences app_preferences;
    int appTheme;
    int themeColor;
    int appColor;

    FragmentProfileBinding profileBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        profileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        View root = profileBinding.getRoot();

        MainActivity main = (MainActivity)getActivity();

        //Get Theme
        app_preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        appColor = app_preferences.getInt("color", 0);
        appTheme = app_preferences.getInt("theme", 0);
        themeColor = appColor;
        constant.color = appColor;

        if (themeColor == 0){
            main.setTheme(Constant.theme);
        }else if (appTheme == 0){
            main.setTheme(Constant.theme);
        }else{
            main.setTheme(appTheme);
        }

        //Get sharepreferences for ID user
        shared = getActivity().getSharedPreferences("getId", Context.MODE_PRIVATE);
        idUser = shared.getInt("idUser", -1);
        token = shared.getString("token", null);
        Log.d("ID USER Profile", String.valueOf(idUser));

        name = root.findViewById(R.id.tv_nama);
        email = root.findViewById(R.id.et_email);
        phone = root.findViewById(R.id.et_phone);
        country = root.findViewById(R.id.et_country);
        city = root.findViewById(R.id.et_city);
        image = root.findViewById(R.id.profile_image_profile);

        Intent i = getActivity().getIntent();
        sIdUser = i.getStringExtra("id");
        sName = i.getStringExtra("name");
        sEmail = i.getStringExtra("email");
        sCountry = i.getStringExtra("country");
        sCity = i.getStringExtra("city");
        sPhone = i.getStringExtra("phone");
        sImage = i.getStringExtra("image");

        name.setText(sName);
        email.setText(sEmail);
        country.setText(sCountry);
        city.setText(sCity);
        phone.setText(sPhone);

        loadUser();

        Button btnEdit = root.findViewById(R.id.btn_editProfile);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Navigation.findNavController(view).navigate(
//                        R.id.action_navigation_notifications_to_editProfileFragment);
            }
        });
        return root;
    }

    private void loadUser() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> load = apiService.getUser("Bearer " + token);
        load.enqueue(new Callback<UserResponse>()
        {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                sName = response.body().getUsers().getName();
                sEmail = response.body().getUsers().getEmail();
                sPhone = response.body().getUsers().getPhone();
                sCity = response.body().getUsers().getCity();
                sCountry = response.body().getUsers().getCountry();
                sImage = response.body().getUsers().getPhoto();
                name.setText(sName);
                email.setText(sEmail);
                phone.setText(sPhone);
                city.setText(sCity);
                country.setText(sCountry);

                String url = "url = \"https://www.mehtravellingtubes.xyz/public/api/" + sImage;
                System.out.println("url gambar " + url);

//                Glide.with(getContext())
//                        .load("http://nugaskuy.site/storage/" + sImage)
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .skipMemoryCache(true)
//                        .into(image);

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}