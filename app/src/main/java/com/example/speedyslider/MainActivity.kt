package com.example.speedyslider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.example.speedyslider.ui.theme.SpeedySliderTheme

class MainActivity : ComponentActivity() {
    private var clickedString by mutableStateOf("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            SpeedySliderTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Choose player speed:",Modifier.padding(bottom = 20.dp))
                    SpeedySlider(
                        shape = CircleShape,
                        shapeColor = Color(color = 0xffe86a10),
                        sliderBackgroundColor = Color(color = 0xffc9d1d4),
                        dotsColor = White,
                        dotsShape = CircleShape,
                        durationToRotate = 500,
                        durationToMove = 700,
                        firsPositionString = "0",
                        middlePositionString = "1",
                        lastPositionString = "2",
                        firstImage = R.drawable.turtle,
                        secondImage = R.drawable.rabb,
                        thirdImage = R.drawable.running_man,
                        padding = 50.dp
                    ){ clicked ->
                        clickedString = clicked
                    }

                    Text("value: $clickedString")
                }
            }
        }
    }
}

