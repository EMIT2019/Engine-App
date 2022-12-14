package com.example.engine

import android.graphics.ColorSpace.Rgb
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPreview()
        }
    }
}

//UI with Jetpack Compose
@Composable
@Preview
fun DefaultPreview(){
    AnimatedBackground()
}

@Composable
fun AnimatedBackground(){
    var color = remember {Animatable(Color(98, 0, 0))}


    LaunchedEffect(Unit){
        color.animateTo(Color(130,0, 0), animationSpec = infiniteRepeatable(
            animation = tween(3000),
            RepeatMode.Reverse
        ))
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(color.value)
    ){
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {
            Text(text = "ENGINE", style = TextStyle(fontSize = 80.sp, fontWeight = FontWeight.ExtraBold, color = Color.White))
            Image(painter = painterResource(id = R.drawable.start_page_pic), contentDescription = "", colorFilter = ColorFilter.tint(color = Color.White))
            Button(onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(199, 0, 57).copy(alpha = 1f)),
                modifier = Modifier.fillMaxWidth(0.6f).height(50.dp)
            ) {
                Text(text = "GET STARTED", style = TextStyle(fontWeight = FontWeight.ExtraBold, color = Color.White))
            }
        }
    }
}
