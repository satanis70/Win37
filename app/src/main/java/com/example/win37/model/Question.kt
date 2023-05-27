package com.example.win37.model

data class Question(
    val answer1: Answer1,
    val answer2: Answer1,
    val answer3: Answer1,
    val answer4: Answer1,
    val image: String,
    val question: String
)