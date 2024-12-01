![SpeedySliderLogo](https://github.com/user-attachments/assets/ffce7cf8-7b54-490c-b847-f39c774f1e5f)

A highly customizable composable function that provides an interactive, animated slider with three distinct positions, each associated with a unique image and label. The slider supports smooth position and rotation animations, making it visually engaging.

## ScreenShot


![Speedy](https://github.com/user-attachments/assets/130a631f-1fb7-4353-ac5c-eec761f2c24a)

## Example

```bash

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
){ clickedLabel ->
    Log.d("SpeedySlider", "Selected: $clickedLabel")
}

```


## Installation
SpeedySlider is available through jitpack. To install it, simply add the maven { url = uri("https://jitpack.io") } to your settings.gradle:

```bash
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
```
and in dependencies add this line implementation (libs.speedyslider)
```bash
dependencies{
      implementation (libs.speedyslider)
}
```

## Author
Muath,Muath.Omarieh@gmail.com

