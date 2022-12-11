package com.example.engine

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

data class Vehicle(
    val picture: Painter,
    val brand: String,
    val top_speed: Int,
    val power: Int,
    val model: String
)