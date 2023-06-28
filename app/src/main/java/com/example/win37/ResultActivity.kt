package com.example.win37

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.win37.ui.theme.Win37Theme

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen.loadBackgroundQuiz()
            val data = intent.getIntExtra("result", 0)
            val all = intent.getIntExtra("all", 0)
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = resources.getString(R.string.result) + "$data/$all",
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center
                )
                val context = this@ResultActivity
                Button(onClick = {
                    val intent = Intent(context, QuizActivity::class.java)
                    startActivity(intent)
                    finish()
                }) {
                    Text(
                        text = resources.getString(R.string.again),
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}