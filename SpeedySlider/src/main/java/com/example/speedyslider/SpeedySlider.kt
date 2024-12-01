package com.example.speedyslider



import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 * SpeedySlider
 *
 * A highly customizable composable function that provides an interactive, animated slider
 * with three distinct positions, each associated with a unique image and label. The slider
 * supports smooth position and rotation animations, making it visually engaging.
 *
 * @param shape The shape of the slider's moving element (e.g., CircleShape).
 * @param shapeColor The color of the slider's moving element.
 * @param sliderBackgroundColor The background color of the slider's container.
 * @param dotsColor The color of the separator dots between the positions.
 * @param dotsShape The shape of the separator dots.
 * @param durationToRotate The duration (in milliseconds) for the rotation animation of the moving element.
 * @param durationToMove The duration (in milliseconds) for the sliding animation when changing positions.
 * @param firsPositionString The label for the first position (e.g., "First").
 * @param middlePositionString The label for the middle position (e.g., "Second").
 * @param lastPositionString The label for the last position (e.g., "Third").
 * @param firstImage The resource ID of the image displayed when the slider is in the first position.
 * @param secondImage The resource ID of the image displayed when the slider is in the middle position.
 * @param thirdImage The resource ID of the image displayed when the slider is in the last position.
 * @param padding The horizontal padding around the slider.
 * @param clicked A callback function triggered when a position is selected, passing the corresponding label.
 *
 * ### Features
 * - A visually appealing slider with three selectable positions.
 * - Smooth rotation animation for the slider's moving element on position changes.
 * - Displays position-dependent images, switching between `firstImage`, `secondImage`, and `thirdImage`.
 * - Fully customizable appearance (shapes, colors, animations, etc.).
 *
 * ### Example Usage
 * ```kotlin
 * SpeedySlider(
 *     shape = CircleShape,
 *     shapeColor = Color.Blue,
 *     sliderBackgroundColor = Color.Gray,
 *     dotsColor = Color.White,
 *     dotsShape = CircleShape,
 *     durationToRotate = 500,
 *     durationToMove = 700,
 *     firsPositionString = "First",
 *     middlePositionString = "Second",
 *     lastPositionString = "Third",
 *     firstImage = R.drawable.turtle,
 *     secondImage = R.drawable.rabbit,
 *     thirdImage = R.drawable.running_man,
 *     padding = 25.dp
 * ) { clickedLabel ->
 *     // Handle position click here, e.g.,
 *     Log.d("SpeedySlider", "Selected: $clickedLabel")
 * }
 * ```
 *
 * ### Notes
 * - Ensure the `firstImage`, `secondImage`, and `thirdImage` resource IDs are valid.
 * - The `clicked` callback provides the label of the selected position (e.g., "First").
 * - Adjust padding, shape, and colors to fit the theme of your application.
 */
@SuppressLint("UseOfNonLambdaOffsetOverload", "UnrememberedMutableInteractionSource")
@Composable
fun SpeedySlider(shape: Shape,
                 shapeColor:Color,
                 sliderBackgroundColor: Color,
                 dotsColor: Color,
                 dotsShape: Shape,
                 durationToRotate: Int,
                 durationToMove: Int,
                 firsPositionString: String,
                 middlePositionString: String,
                 lastPositionString: String,
                 firstImage: Int ,
                 secondImage: Int ,
                 thirdImage: Int ,
                 padding: Dp,
                 clicked: (String) -> Unit) {
    val density = LocalDensity.current
    var containerWidth by remember { mutableIntStateOf(0) }
    var containerWidthDp by remember { mutableStateOf(0.dp) }


    var rotatingAngle by remember { mutableFloatStateOf(0f) }
    var theEnabledOne by remember { mutableStateOf(firsPositionString) }

    LaunchedEffect(Unit) {
        clicked(firsPositionString)
    }

    val rotationAngle by animateFloatAsState(
        targetValue = rotatingAngle,
        animationSpec = tween(durationMillis = durationToRotate),
        label = "Rotation Angle"
    )

    LaunchedEffect(containerWidth) {
        containerWidthDp = with(density) { containerWidth.toDp() }
    }

    val shapePosition by animateDpAsState(
        targetValue = when (theEnabledOne) {
            firsPositionString -> 0.dp
            middlePositionString -> (containerWidthDp / 2) - (55.dp / 2)
            lastPositionString -> containerWidthDp - 60.dp
            else -> 0.dp
        },
        animationSpec = tween(durationMillis = durationToMove),
        label = "Shape Position"
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
                .background(color = sliderBackgroundColor)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    firsPositionString,
                    Modifier
                        .padding(start = 15.dp)
                        .clickable(
                            indication = null,
                            interactionSource = MutableInteractionSource()
                        ) {
                            if (theEnabledOne != firsPositionString) {
                                theEnabledOne = firsPositionString
                                clicked(firsPositionString)
                                rotatingAngle -= 360
                            }
                        }
                )
                Box(
                    modifier = Modifier
                        .clip(dotsShape)
                        .size(10.dp)
                        .background(color = dotsColor)
                )
                Text(
                    middlePositionString,
                    Modifier
                        .clickable(
                            indication = null,
                            interactionSource = MutableInteractionSource()
                        ) {
                        if (theEnabledOne != middlePositionString) {
                            if (theEnabledOne == lastPositionString)
                                rotatingAngle -= 360
                            else
                                rotatingAngle += 360
                            theEnabledOne = middlePositionString
                            clicked(middlePositionString)
                        }
                    }
                )
                Box(
                    modifier = Modifier
                        .clip(dotsShape)
                        .size(10.dp)
                        .background(color = dotsColor)
                )
                Text(
                    lastPositionString,
                    Modifier
                        .padding(end = 15.dp)
                        .clickable(
                            indication = null,
                            interactionSource = MutableInteractionSource()
                        ) {
                            if (theEnabledOne != lastPositionString) {
                                rotatingAngle += 360
                                theEnabledOne = lastPositionString
                                clicked(lastPositionString)
                            }
                        }
                )
            }
        }

        Box(
            modifier = Modifier
                .offset(x = shapePosition, y = (-10).dp)
                .size(60.dp)
                .background(shapeColor, shape = shape)
        ) {
            Image(
                painter = painterResource(
                    when {
                        shapePosition < (containerWidthDp / 4) -> firstImage
                        shapePosition <= (containerWidthDp / 2) -> secondImage
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


@Preview
@Composable
fun SpeedySliderPreview(){
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
    ){

    }
}