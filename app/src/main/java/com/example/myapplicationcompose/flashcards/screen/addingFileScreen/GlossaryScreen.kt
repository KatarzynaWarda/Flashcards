package com.example.myapplicationcompose.flashcards.screen.addingFileScreen

import  androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GlossaryScreen(
    onEntryChange: (String, String) -> Unit
){
    var term by remember { mutableStateOf("") }
    var definition by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .background(Color.Gray)
        .padding(20.dp)) {
        Column {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = term,
                onValueChange = { term = it },
                label = { Text("pojęcie") },
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = definition,
                onValueChange = { definition = it},
                label = { Text("definicja") },
            )
        }
    }
    LaunchedEffect(term, definition) {
        if (term.isNotBlank() && definition.isNotBlank()) {
            onEntryChange(term, definition)
        }
    }
}