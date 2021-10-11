package com.bbbond.playground

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private val Yellow200 = Color(0XFFFFEB46)
private val Blue200 = Color(0XFF91A4FC)

private val Yellow500 = Color(0XFFFFC107)
private val Yellow400 = Color(0XFFFFCA28)
private val Blue700 = Color(0XFF1976D2)


val DarkColors = darkColors(
    primary = Blue200,
    primaryVariant = Blue700
)

val LightColors = lightColors(
    primary = Yellow400,
    primaryVariant = Yellow500
)