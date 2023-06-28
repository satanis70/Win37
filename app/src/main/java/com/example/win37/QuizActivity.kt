package com.example.win37

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
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
    private var score = 0
    private var position = 0

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch(Dispatchers.IO) {
            getQuestions()
            launch(Dispatchers.Main) {
                setContent {
                    MainScreen.loadBackgroundQuiz()
                    ShowData()
                }
            }
        }
    }

    @Composable
    private fun ShowData() {

        val currentButtonColor1 = remember {
            mutableStateOf(Color.White)
        }
        val currentButtonColor2 = remember {
            mutableStateOf(Color.White)
        }
        val currentButtonColor3 = remember {
            mutableStateOf(Color.White)
        }
        val currentButtonColor4 = remember {
            mutableStateOf(Color.White)
        }
        val clickable = remember {
            mutableStateOf(false)
        }
        val showQuestion = remember {
            mutableStateOf(false)
        }
        if (clickable.value==true){
            if (showQuestion.value==true){
                ShowData()
                showQuestion.value = false
            }
        }
        Text(
            text = arrayListQuestion[0].questions[position].question,
            modifier = Modifier.padding(vertical = 38.dp),
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 28.sp
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val context = LocalContext.current
            Button(
                onClick = {
                    if (clickable.value == false) {
                        if (arrayListQuestion[0].questions[position].answer1.trueorfalse == "true") {
                            score += 1
                            currentButtonColor1.value = Color.Green
                            clickable.value = true
                        } else {
                            currentButtonColor1.value = Color.Red
                            clickable.value = true
                        }
                    }
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(23.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = currentButtonColor1.value
                )
            ) {
                Text(
                    text = arrayListQuestion[0].questions[position].answer1.name,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 6.dp)
                )
            }
            Button(
                onClick = {
                    if (clickable.value == false) {
                        if (arrayListQuestion[0].questions[position].answer2.trueorfalse == "true") {
                            score += 1
                            currentButtonColor2.value = Color.Green
                            clickable.value = true
                        } else {
                            currentButtonColor2.value = Color.Red
                            clickable.value = true
                        }
                    }
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .layoutId("button2"),
                shape = RoundedCornerShape(23.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = currentButtonColor2.value
                )
            ) {
                Text(
                    text = arrayListQuestion[0].questions[position].answer2.name,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 6.dp)
                )
            }
            Button(
                onClick = {
                    if (clickable.value == false) {
                        if (arrayListQuestion[0].questions[position].answer3.trueorfalse == "true") {
                            score += 1
                            currentButtonColor3.value = Color.Green
                            clickable.value = true
                        } else {
                            currentButtonColor3.value = Color.Red
                            clickable.value = true
                        }
                    }
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(23.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = currentButtonColor3.value
                )
            ) {
                Text(
                    text = arrayListQuestion[0].questions[position].answer3.name,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 6.dp)
                )
            }
            Button(
                onClick = {
                    if (clickable.value == false) {
                        if (arrayListQuestion[0].questions[position].answer4.trueorfalse == "true") {
                            score += 1
                            currentButtonColor4.value = Color.Green
                            clickable.value = true
                        } else {
                            currentButtonColor4.value = Color.Red
                            clickable.value = true
                        }
                    }
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(23.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = currentButtonColor4.value
                )
            ) {
                Text(
                    text = arrayListQuestion[0].questions[position].answer4.name,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 6.dp)
                )
            }
            Button(
                onClick = {
                    if (arrayListQuestion[0].questions.size==position+1){
                        val intent = Intent(this@QuizActivity, ResultActivity::class.java)
                        intent.putExtra("result", score)
                        intent.putExtra("all", arrayListQuestion[0].questions.size)
                        startActivity(intent)
                        finish()
                    } else{
                        if (clickable.value==true){
                            showQuestion.value = true
                            position++
                            currentButtonColor1.value = Color.White
                            currentButtonColor2.value = Color.White
                            currentButtonColor3.value = Color.White
                            currentButtonColor4.value = Color.White
                            clickable.value = false
                        }
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .padding(top = 58.dp),
                shape = RoundedCornerShape(23.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Red,
                    containerColor = Color.White
                )
            ) {
                Text(
                    text = context.resources.getString(R.string.next_button),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 6.dp)
                )
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
