package com.example.win37

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.win37.model.Question
import com.example.win37.model.QuestionModel
import com.example.win37.services.QuizApi
import com.example.win37.ui.theme.Win37Theme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class QuizActivity : ComponentActivity() {

    private val arrayListQuestion = ArrayList<QuestionModel>()

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch(Dispatchers.IO) {
            getQuestions()
            launch(Dispatchers.Main) {
                setContent {
                    MainScreen.loadBackgroundQuiz(context = LocalContext.current)
                    Log.i("arraylistQuestion", arrayListQuestion.toString())
                    Text(
                        text = arrayListQuestion[0].questions[0].question,
                        modifier = Modifier.padding(vertical = 38.dp),
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontSize = 28.sp
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        val context = LocalContext.current
                        Button(
                            onClick = {},
                            shape = RoundedCornerShape(23.dp),
                            colors = ButtonDefaults.buttonColors(contentColor = Color.Red, containerColor = Color.White)
                        ) {
                            Text(
                                text = "Start quiz",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(horizontal = 30.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    private suspend fun getQuestions() {
        val questionList = ArrayList<QuestionModel>()
        val apiQuestions = Retrofit.Builder()
            .baseUrl("http://49.12.202.175/win37/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizApi::class.java)
        questionList.add(apiQuestions.getQuestions().awaitResponse().body()!!)
        arrayListQuestion.addAll(questionList)
    }
}
