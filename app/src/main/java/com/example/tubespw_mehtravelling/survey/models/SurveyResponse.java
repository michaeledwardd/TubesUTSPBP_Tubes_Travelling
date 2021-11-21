package com.example.tubespw_mehtravelling.survey.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SurveyResponse {
    private String message;

    @SerializedName("survey")
    private List<Survey> surveyList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Survey> getSurveyList() {
        return surveyList;
    }

    public void setSurveyList(List<Survey> surveyList) {
        this.surveyList = surveyList;
    }
}
