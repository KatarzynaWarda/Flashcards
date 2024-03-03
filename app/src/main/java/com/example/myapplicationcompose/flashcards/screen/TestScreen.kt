package com.example.myapplicationcompose.flashcards.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplicationcompose.flashcards.viewModel.FlashcardsViewModel

@Composable
fun TestScreen(
    glossaryName: String,
    viewModel: FlashcardsViewModel,
) {

    val glossary by viewModel.getGlossaryByName(glossaryName).collectAsState()

    glossary?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
                .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
        ) {
            Column {
                Text(
                    text = it.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(20.dp)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = it.entries[0].term,
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(20.dp)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = it.entries[0].definition,
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(20.dp)
                )


            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewTestScreen(){
//    TestScreen(glossary = Glossary("Animals",
//        listOf(
//        GlossaryEntry("kot", "cat")
//    )))
//}