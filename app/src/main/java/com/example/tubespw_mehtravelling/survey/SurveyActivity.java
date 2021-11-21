package com.example.tubespw_mehtravelling.survey;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.tubespw_mehtravelling.R;
import com.example.tubespw_mehtravelling.survey.adapters.SurveyAdapter;
import com.example.tubespw_mehtravelling.survey.api.ApiSurvey;
import com.example.tubespw_mehtravelling.survey.api.SurveyInterface;
import com.example.tubespw_mehtravelling.survey.models.SurveyResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurveyActivity extends AppCompatActivity {

    public static final int LAUNCH_ADD_ACTIVITY = 123;
    private SwipeRefreshLayout srMahasiswa;
    private SurveyAdapter adapter;
    private SurveyInterface surveyService;
    private SearchView svMahasiswa;
    private LinearLayout layoutLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surveyService = ApiSurvey.getSurvey().create(SurveyInterface.class);
        layoutLoading = findViewById(R.id.layout_loading);
        srMahasiswa = findViewById(R.id.sr_mahasiswa);
        svMahasiswa = findViewById(R.id.sv_mahasiswa);
        srMahasiswa.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllSurvey();
            }
        });
        svMahasiswa.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SurveyActivity.this, AddEditActivity.class);
                startActivityForResult(i, LAUNCH_ADD_ACTIVITY);
            }
        });
        RecyclerView rvMahasiswa = findViewById(R.id.rv_mahasiswa);
        adapter = new SurveyAdapter(new ArrayList<>(), this);
        rvMahasiswa.setLayoutManager(new LinearLayoutManager(SurveyActivity.this,
                LinearLayoutManager.VERTICAL, false));
        rvMahasiswa.setAdapter(adapter);
        getAllSurvey();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent
            data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_ADD_ACTIVITY && resultCode == Activity.RESULT_OK)
            getAllSurvey();
    }

    private void getAllSurvey() {
        Call<SurveyResponse> call = surveyService.getAllSurvey();
        srMahasiswa.setRefreshing(true);
        call.enqueue(new Callback<SurveyResponse>() {
            @Override
            public void onResponse(Call<SurveyResponse> call,
                                   Response<SurveyResponse> response) {
                if (response.isSuccessful()) {
                    adapter.setMahasiswaList(response.body().getSurveyList());
                    adapter.getFilter().filter(svMahasiswa.getQuery());
                } else {
                    try {
                        JSONObject jObjError = new
                                JSONObject(response.errorBody().string());
                        Toast.makeText(SurveyActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(SurveyActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                srMahasiswa.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<SurveyResponse> call, Throwable t) {
                Toast.makeText(SurveyActivity.this, "Network error",
                        Toast.LENGTH_SHORT).show();
                srMahasiswa.setRefreshing(false);
            }
        });
    }

    public void deleteSurvey(long id) {
        Call<SurveyResponse> call = surveyService.deleteSurvey(id);
        setLoading(true);
        call.enqueue(new Callback<SurveyResponse>() {
            @Override
            public void onResponse(Call<SurveyResponse> call,
                                   Response<SurveyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(SurveyActivity.this,
                            response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    getAllSurvey();
                } else {
                    try {
                        JSONObject jObjError = new
                                JSONObject(response.errorBody().string());
                        Toast.makeText(SurveyActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(SurveyActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                setLoading(false);
            }

            @Override
            public void onFailure(Call<SurveyResponse> call, Throwable t) {
                Toast.makeText(SurveyActivity.this, "Network error",
                        Toast.LENGTH_SHORT).show();
                setLoading(false);
            }
        });
    }

    // Fungsi untuk menampilkan layout loading
    private void setLoading(boolean isLoading) {
        if (isLoading) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.VISIBLE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.GONE);
        }
    }
}