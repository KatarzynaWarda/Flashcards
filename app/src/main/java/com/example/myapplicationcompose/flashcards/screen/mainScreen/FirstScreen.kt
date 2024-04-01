package com.example.myapplicationcompose.flashcards.screen.mainScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationcompose.R
import com.example.myapplicationcompose.flashcards.data.Glossary
import com.example.myapplicationcompose.flashcards.data.GlossaryEntry
import com.example.myapplicationcompose.flashcards.viewModel.FlashcardsViewModel

@Composable
fun FirstScreen(
    viewModel: FlashcardsViewModel,
    navigateToAddingFileScreenAction: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
            .padding(top = 50.dp, start = 30.dp, end = 30.dp, bottom = 10.dp)

    ) {
        LazyColumn (
            modifier = Modifier.align(Alignment.Center)
        ){
            items(viewModel.glossaryCount.value) { index ->
                Button(
                    onClick = { /*TODO*/ },) {
                    Text(
                        text = viewModel.glossaries[index].name,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }

            }
        }
        IconButton(
            onClick = navigateToAddingFileScreenAction,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(80.dp)
                .background(Color.DarkGray, RoundedCornerShape(100.dp))
                .border(BorderStroke(2.dp, colorResource(id = R.color.blue)), RoundedCornerShape(100.dp)),
        ) {
            Image(
                painter = painterResource(id = R.drawable.icons8_close_60),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .rotate(45.0f),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.blue))
            )
        }
    }
}

//@Preview
//@Composable
//fun PreviewFirstScreen(){
//    FirstScreen()
//}
