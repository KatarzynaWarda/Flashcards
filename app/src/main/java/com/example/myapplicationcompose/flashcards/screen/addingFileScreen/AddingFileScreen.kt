package com.example.myapplicationcompose.flashcards.screen.addingFileScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplicationcompose.R
import com.example.myapplicationcompose.flashcards.data.GlossaryEntry
import com.example.myapplicationcompose.flashcards.viewModel.FlashcardsViewModel

@Composable
fun AddingFileScreen(
    viewModel: FlashcardsViewModel,
    navigateToMainScreen: () -> Unit,
    glossaryId: Int?,
) {
    val entriesDraft = remember { mutableStateListOf<GlossaryEntry>() }
    val itemsCount = remember { mutableStateOf(1) }
    var name by remember { mutableStateOf("") }

    while (entriesDraft.size < itemsCount.value) {
        entriesDraft.add(GlossaryEntry(term = "", definition = "", glossaryId = glossaryId ?: 0))
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
    ) {
        Column {
            TextField(
                value = name,
                onValueChange = { name = it},
                label = { Text("Nazwa") },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(20.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn {
                itemsIndexed(entriesDraft) { index, draft ->
                    GlossaryScreen(
                        onEntryChange = { term, definition ->
                            entriesDraft[index] = draft.copy(term = term, definition = definition)
                        }
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
        IconButton(
            onClick = {
                if (name.isNotBlank()) {
                    viewModel.updateGlossary(name, entriesDraft) {
                        navigateToMainScreen()
                        entriesDraft.clear()
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(80.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icons8_done_52),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp),
            )
        }
    }
    LaunchedEffect(entriesDraft.last()) {
        if (entriesDraft.last().term.isNotBlank() && entriesDraft.last().definition.isNotBlank()) {
            itemsCount.value = entriesDraft.size + 1
        }
    }
}