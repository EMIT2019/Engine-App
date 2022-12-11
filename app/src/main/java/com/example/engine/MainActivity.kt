package com.example.engine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
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
    var vehicleList: ArrayList<Vehicle> = arrayListOf()
    
    val ferrari: Vehicle = Vehicle(painterResource(id = R.drawable.ferrari), "Ferrari", 245, 290, "F-50")
    val jeep: Vehicle = Vehicle(painterResource(id = R.drawable.jeep), "Jeep", 205, 340, "Cherokee")
    val toyota: Vehicle = Vehicle(painterResource(id = R.drawable.toyota), "Toyota", 200, 300, "Hi lux")
    val ford: Vehicle = Vehicle(painterResource(id = R.drawable.ford), "Ford", 220, 360, "RAM")
    val maserati: Vehicle = Vehicle(painterResource(id = R.drawable.maserati), "Maserati", 230, 280, "MS-22")
    
    vehicleList.add(ferrari)
    vehicleList.add(jeep)
    vehicleList.add(toyota)
    vehicleList.add(ford)
    vehicleList.add(maserati)
    
    RecyclerView(vehicleList = vehicleList)
}

@Composable
fun ImageCardItem(
    vehicle: Vehicle,
    modifier: Modifier = Modifier
){
    val expanded = remember { mutableStateOf(false)}
    val extraPadding by animateDpAsState(
        if(expanded.value) 60.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp
    ) {
       Column(
           modifier = Modifier
               .fillMaxWidth()
       ) {
            Box(
                modifier = Modifier.height(200.dp)
            ){
                Image(
                    painter = vehicle.picture,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                ),
                                startY = 300f
                            )
                        )
                )
                Row(){
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(6.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(text = vehicle.brand, style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold))
                    }
                    Button(onClick = { expanded.value = !expanded.value }, modifier = Modifier.padding(6.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black.copy(alpha = 0.85f))) {
                        Text(
                            if(expanded.value) "Less" else "More", style =  TextStyle(color = Color.White)
                        )
                    }
                }
            }
           if(expanded.value){
               Column(modifier = Modifier
                   .height(
                       extraPadding.coerceAtLeast(0.dp)
                   )
                   .fillMaxWidth()
                   .background(color = Color.Black)
                   .padding(
                       start = 6.dp,
                       top = 5.dp,
                       end = 6.dp
                   )
               ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.spacedBy(25.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(text = "Top Speed: ${vehicle.top_speed} k/h", fontWeight = FontWeight.Bold, style = TextStyle(color = Color.White))
                            Text(text = "Power ${vehicle.power} cv", fontWeight = FontWeight.Bold, style = TextStyle(color = Color.White))
                        }
                        Text(text = "Model: ${vehicle.model}", fontWeight = FontWeight.Bold, style = TextStyle(color = Color.White))
                    }
               }
           }
       }
    }
}

@Composable
fun RecyclerView(vehicleList: List<Vehicle>){
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)){
        items(items = vehicleList){ item ->
            ImageCardItem(vehicle = item)
        }
    }
}