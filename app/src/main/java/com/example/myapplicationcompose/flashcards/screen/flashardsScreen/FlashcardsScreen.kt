package com.example.myapplicationcompose.flashcards.screen.flashardsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationcompose.R
import com.example.myapplicationcompose.flashcards.data.Glossary
import com.example.myapplicationcompose.flashcards.screen.flashardsScreen.HorizontalPager

@Composable
fun FlashcardsScreen(
    glossary: Glossary,
    ) {

    Column (modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.background))
        .padding(top = 20.dp, start = 30.dp, end = 30.dp, bottom = 10.dp)
        .testTag("container")
    ) {
        Image(
            painter = painterResource(id = R.drawable.icons8_close_60),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.DarkGray),
            modifier = Modifier.align(Alignment.End)
            )
        Spacer(modifier = Modifier.weight(1f))
        Text(text = glossary.name,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold)
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
        ) {
            Text(text ="0",
                fontSize = 40.sp,
                color = Color.Gray
                )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "0/0",
                fontSize = 40.sp,
                color = Color.Gray)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "0",
                fontSize = 40.sp,
                color = Color.Gray)
        }
        Spacer(modifier = Modifier.weight(1f))
        HorizontalPager()
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    FlashcardsScreen("Animals")
//}