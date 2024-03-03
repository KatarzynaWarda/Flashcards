package com.example.myapplicationcompose.flashcards.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
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
import com.example.myapplicationcompose.R
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
            .padding(top = 10.dp, start = 30.dp, end = 30.dp, bottom = 10.dp)
    ) {
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
