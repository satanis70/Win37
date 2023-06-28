package com.example.win37

import android.content.Intent
import android.graphics.fonts.FontStyle
import android.os.Bundle
import android.util.Log
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay
import okhttp3.Route


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }

    @Composable
    fun Navigation() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = resources.getString(R.string.splash_screen)
        ) {
            composable(resources.getString(R.string.splash_screen)) {
                SplashScreen(navController = navController)
            }
            composable(resources.getString(R.string.main_screen)) {
                MainScreen()
            }
        }
    }

    @Composable
    fun SplashScreen(navController: NavController) {
        LaunchedEffect(key1 = true) {
            delay(3000L)
            navController.navigate(resources.getString(R.string.main_screen)) {
                popUpTo(0)
            }
        }
        Image(
            painter = rememberAsyncImagePainter("http://49.12.202.175/win37/tennisback.jpg"),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        CustomLinearProgressBar()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentHeight()
                    .graphicsLayer(alpha = 0.99f)
                    .drawWithCache {
                        val brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue))
                        onDrawWithContent {
                            drawContent()
                            drawRect(brush, blendMode = BlendMode.SrcAtop)
                        }
                    },
                text = resources.getString(R.string.app_name),
                textAlign = TextAlign.Center,
                fontSize = 48.sp
            )
        }
    }

    @Composable
    fun MainScreen() {
        MainScreen.LoadBackground(this)
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val context = LocalContext.current
            Button(
                onClick = {
                    val intent = Intent(context, QuizActivity::class.java)
                    context.startActivity(intent)
                },
                shape = RoundedCornerShape(23.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Red,
                    containerColor = Color.White
                )
            ) {
                Text(
                    text = resources.getString(R.string.start_quiz),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 6.dp)
                )
            }
        }
    }

    @Composable
    private fun CustomLinearProgressBar() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp), verticalArrangement = Arrangement.Center
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp),
                color = Color.Red
            )
        }
    }
}
