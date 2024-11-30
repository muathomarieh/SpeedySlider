package com.example.speedyslider


import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp



@Composable
fun SpeedySlider(shape: Shape = CircleShape,
                 durationToRotate: Int = 500,
                 durationToMove: Int = 700,
                 shapeColor:Color = Color(0xFFF57C00),
                 firstImage: Int ,
                 secondImage: Int ,
                 thirdImage: Int ,
                 padding: Dp = 25.dp) {//documentation comment
    val density = LocalDensity.current
    var containerWidth by remember { mutableIntStateOf(0) }
    var containerWidthDp by remember { mutableStateOf(0.dp) }

    var rotatingAngle by remember { mutableFloatStateOf(0f) }
    var theEnabledOne by remember { mutableStateOf("0.5X") }

    val rotationAngle by animateFloatAsState(
        targetValue = rotatingAngle,
        animationSpec = tween(durationMillis = durationToRotate)
    )

    LaunchedEffect(containerWidth) {
        containerWidthDp = with(density) { containerWidth.toDp() }
    }

    val turtlePosition by animateDpAsState(
        targetValue = when (theEnabledOne) {
            "0.5X" -> 0.dp
            "1X" -> (containerWidthDp / 2) - (55.dp / 2)
            "2X" -> containerWidthDp - 60.dp
            else -> 0.dp
        },
        animationSpec = tween(durationMillis = durationToMove)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = padding)
            .onSizeChanged { size ->
                containerWidth = size.width
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(shape)
                .background(color = Color(0xffd6d6d6))
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "0.5X",
                    Modifier
                        .padding(start = 15.dp)
                        .clickable {
                            if (theEnabledOne != "0.5X") {
                                theEnabledOne = "0.5X"
                                rotatingAngle -= 360
                            }
                        }
                )
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(10.dp)
                        .background(color = Color(0xFFFFFFFF))
                )
                Text(
                    "1X",
                    Modifier.clickable {
                        if (theEnabledOne != "1X") {
                            if (theEnabledOne == "2X")
                                rotatingAngle -= 360
                            else
                                rotatingAngle += 360
                            theEnabledOne = "1X"
                        }
                    }
                )
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(10.dp)
                        .background(color = Color(0xFFFFFFFF))
                )
                Text(
                    "2X",
                    Modifier
                        .padding(end = 15.dp)
                        .clickable {
                            if (theEnabledOne != "2X") {
                                rotatingAngle += 360
                                theEnabledOne = "2X"
                            }
                        }
                )
            }
        }

        Box(
            modifier = Modifier
                .offset(x = turtlePosition, y = (-10).dp)
                .size(60.dp)
                .background(shapeColor, shape = shape)
        ) {
            Image(
                painter = painterResource(
                    when {
                        turtlePosition < (containerWidthDp / 4) -> firstImage
                        turtlePosition <= (containerWidthDp / 2) -> secondImage
                        else -> thirdImage
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .rotate(rotationAngle)
                    .padding(10.dp)
            )
        }
    }
}
