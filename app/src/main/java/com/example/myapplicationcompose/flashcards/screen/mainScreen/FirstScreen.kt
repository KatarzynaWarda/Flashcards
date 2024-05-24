package com.example.myapplicationcompose.flashcards.screen.mainScreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationcompose.R
import com.example.myapplicationcompose.flashcards.viewModel.FlashcardsViewModel
import kotlinx.coroutines.coroutineScope

@Composable
fun FirstScreen(
    viewModel: FlashcardsViewModel,
    navigateToAddingFileScreenAction: () -> Unit,
    navigateToFlashcards: (Int) -> Unit,
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
            items(viewModel.glossaries.value.size) { index ->
                Box(modifier = Modifier
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = {
                                viewModel.deleteGlossary(glossary = viewModel.glossaries.value[index])
                            },
                            onPress = {
                                tryAwaitRelease()
                                navigateToFlashcards(viewModel.glossaries.value[index].id)
                            },
                        )
                    }
                )
                {
                    Text(
                        text = viewModel.glossaries.value[index].name,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        IconButton(
            onClick = navigateToAddingFileScreenAction,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(80.dp)
                .background(Color.DarkGray, RoundedCornerShape(100.dp))
                .border(
                    BorderStroke(2.dp, colorResource(id = R.color.blue)),
                    RoundedCornerShape(100.dp)
                ),
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
