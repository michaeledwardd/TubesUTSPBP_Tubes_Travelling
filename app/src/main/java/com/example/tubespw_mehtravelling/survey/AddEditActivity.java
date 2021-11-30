package com.example.tubespw_mehtravelling.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tubespw_mehtravelling.R;
import com.example.tubespw_mehtravelling.survey.api.ApiSurvey;
import com.example.tubespw_mehtravelling.survey.api.SurveyInterface;
import com.example.tubespw_mehtravelling.survey.models.Survey;
import com.example.tubespw_mehtravelling.survey.models.SurveyResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditActivity extends AppCompatActivity {

//    private static final String[] DESTINASI_LIST = new String[]{"Malioboro", "kraton solo", "kuta", "monas",
//            "raja ampat", "labuan bajo"};

//    private static final String[] PENILAIAN_LIST = new String[]{"1",
//            "2", "3", "4", "5", "6"};

    private SurveyInterface surveyService;
//    private AutoCompleteTextView ednamadestinasi, edpenilaian ;
    private EditText etnamadestinasi, etpenilaian, etnamapengguna, etalasan;
    private LinearLayout layoutLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        surveyService = ApiSurvey.getSurvey().create(SurveyInterface.class);
        etnamadestinasi = findViewById(R.id.et_namadestinasi);
//        etdestinasi = findViewById(R.id.etdestinasi);
        etnamapengguna = findViewById(R.id.et_namapengguna);
        etpenilaian = findViewById(R.id.et_penilaian);
        etalasan = findViewById(R.id.et_alasan);
        layoutLoading = findViewById(R.id.layout_loading);

//        ArrayAdapter<String> adapterNamaDestinasi =
//                new ArrayAdapter<>(this, R.layout.item_list, DESTINASI_LIST);
//        ednamadestinasi.setAdapter(adapterNamaDestinasi);
//        ArrayAdapter<String> adapterPenilaian =
//                new ArrayAdapter<>(this, R.layout.item_list, PENILAIAN_LIST);
//        edpenilaian.setAdapter(adapterPenilaian);


        Button btnCancel = findViewById(R.id.btn_cancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
//        edpenilaian.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
//                String selection = (String)parent.getItemAtPosition(position);
//                //TODO Do something with the selected text
//                Toast.makeText(AddEditActivity.this,
//                        "Penilaian gagal", Toast.LENGTH_SHORT).show();
//            }
//        });

        Button btnSave = findViewById(R.id.btn_save);
        TextView tvTitle = findViewById(R.id.tv_title);
        long id = getIntent().getLongExtra("id", -1);
        if (id == -1) {
            tvTitle.setText(R.string.tambah_survey);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createSuvey();
                }
            });
        } else {
            tvTitle.setText(R.string.edit_survey);
            getSurveyById(id);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateSurvey(id);
                }
            });
        }
    }
    private void getSurveyById(long id) {
        setLoading(true);
        Call<SurveyResponse> call = surveyService.getSurveyById(id);
        call.enqueue(new Callback<SurveyResponse>() {
            @Override
            public void onResponse(Call<SurveyResponse> call,
                                   Response<SurveyResponse> response) {
                if (response.isSuccessful()) {
                    Survey survey = response.body().getSurveyList().get(0);
                    etnamadestinasi.setText(survey.getNamaDestinasi());
                    etnamapengguna.setText(survey.getNamaPengguna());
                    etalasan.setText(survey.getAlasan());
                    etpenilaian.setText(survey.getPenilaian());

                } else {
                    try {
                        JSONObject jObjError = new
                                JSONObject(response.errorBody().string());
                        Toast.makeText(AddEditActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AddEditActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                setLoading(false);
            }
            @Override
            public void onFailure(Call<SurveyResponse> call, Throwable t) {
                Toast.makeText(AddEditActivity.this,
                        "Network error", Toast.LENGTH_SHORT).show();
                setLoading(false);
            }
        });
    }
    private void createSuvey() {
        setLoading(true);
        Survey survey = new Survey(
                etnamadestinasi.getText().toString(),
                etnamapengguna.getText().toString(),
                etalasan.getText().toString(),
                etpenilaian.getText().toString());
        Call<SurveyResponse> call = surveyService.createSurvey(survey);
        call.enqueue(new Callback<SurveyResponse>() {
            @Override
            public void onResponse(Call<SurveyResponse> call,
                                   Response<SurveyResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddEditActivity.this,
                            response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    try {
                        JSONObject jObjError = new
                                JSONObject(response.errorBody().string());
                        Toast.makeText(AddEditActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AddEditActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                setLoading(false);
            }
            @Override
            public void onFailure(Call<SurveyResponse> call, Throwable t) {
                Toast.makeText(AddEditActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
                setLoading(false);
            }
        });
    }
    private void updateSurvey(long id) {
        setLoading(true);
        Survey survey = new Survey(
                etnamadestinasi.getText().toString(),
                etnamapengguna.getText().toString(),
                etalasan.getText().toString(),
                etpenilaian.getText().toString());
        Call<SurveyResponse> call = surveyService.updateSurvey(id, survey);
        call.enqueue(new Callback<SurveyResponse>() {
            @Override
            public void onResponse(Call<SurveyResponse> call,
                                   Response<SurveyResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddEditActivity.this,
                            response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    try {
                        JSONObject jObjError = new
                                JSONObject(response.errorBody().string());
                        Toast.makeText(AddEditActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AddEditActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                setLoading(false);
            }
            @Override
            public void onFailure(Call<SurveyResponse> call, Throwable t) {
                Toast.makeText(AddEditActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
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
            layoutLoading.setVisibility(View.INVISIBLE);
        }
    }
}