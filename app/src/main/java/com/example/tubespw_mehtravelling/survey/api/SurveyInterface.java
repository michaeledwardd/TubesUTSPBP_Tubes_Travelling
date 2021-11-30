package com.example.tubespw_mehtravelling.survey.api;
import com.example.tubespw_mehtravelling.survey.models.Survey;
import com.example.tubespw_mehtravelling.survey.models.SurveyResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
public interface SurveyInterface {
    @Headers({"Accept: application/json"})
    @GET("travelling")
    Call<SurveyResponse> getAllSurvey();

    @Headers({"Accept: application/json"})
    @GET("travelling/{id}")
    Call<SurveyResponse> getSurveyById(@Path("id") long id);

    @Headers({"Accept: application/json"})
    @POST("travelling")
    Call<SurveyResponse> createSurvey(@Body Survey travelling);

    @Headers({"Accept: application/json"})
    @PUT("travelling/{id}")
    Call<SurveyResponse> updateSurvey(@Path("id") long id,
                                      @Body Survey travelling);

    @Headers({"Accept: application/json"})
    @DELETE("travelling/{id}")
    Call<SurveyResponse> deleteSurvey(@Path("id") long id);
}