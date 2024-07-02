package com.example.myapplicationcompose.flashcards.screen.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationcompose.R
import com.example.myapplicationcompose.flashcards.viewModel.FlashcardsViewModel

@Composable
fun FirstScreen(
    viewModel: FlashcardsViewModel,
    navigateToAddingFileScreenAction: () -> Unit,
    navigateToFlashcards: (Int) -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(top = 50.dp, start = 30.dp, end = 30.dp, bottom = 10.dp)

    ) {
        LazyColumn(
            modifier = Modifier.align(Alignment.Center)
        ) {
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
        ) {
            Image(
                painter = painterResource(id = R.drawable.icons8_close_60),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .rotate(45.0f),
            )
        }
    }
}
