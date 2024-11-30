package com.example.speedyslider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.speedyslider.ui.theme.SpeedySliderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpeedySliderTheme {
                SpeedySlider(
                    shape = TODO(),
                    durationToRotate = TODO(),
                    durationToMove = TODO(),
                    shapeColor = TODO(),
                    firstImage = R.drawable.rabb,
                    secondImage = R.drawable.turtle,
                    thirdImage = R.drawable.running_man,
                    padding = TODO()
                )
            }
        }
    }
}

