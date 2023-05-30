package com.example.win37.services

import com.example.win37.model.QuestionModel
import retrofit2.Call
import retrofit2.http.GET

interface QuizApi {
    @GET("questions.json")
    fun getQuestions():Call<QuestionModel>
}