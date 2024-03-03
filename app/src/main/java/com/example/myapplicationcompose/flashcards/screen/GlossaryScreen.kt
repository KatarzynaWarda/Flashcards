package com.example.myapplicationcompose.flashcards.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.myapplicationcompose.R
import com.example.myapplicationcompose.flashcards.viewModel.FlashcardsViewModel

@Composable
fun GlossaryScreen(
    viewModel: FlashcardsViewModel,
){

    val term by viewModel.term.collectAsState()
    val definition by viewModel.definition.collectAsState()

    Box(modifier = Modifier
        .background(colorResource(id = R.color.box))
        .padding(20.dp)) {
        Column {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = term,
                onValueChange = { newTerm ->
                    viewModel.updateTerm(newTerm)
                },
                label = { Text("pojęcie") },
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = definition,
                onValueChange = { newDefinition ->
                    viewModel.updateDefinition(newDefinition)
                },
                label = { Text("definicja") },
            )
        }
    }
}