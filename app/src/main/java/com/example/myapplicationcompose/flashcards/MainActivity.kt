package com.example.myapplicationcompose.flashcards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.myapplicationcompose.flashcards.data.AddingFileUiData
import com.example.myapplicationcompose.flashcards.data.Glossary
import com.example.myapplicationcompose.flashcards.data.GlossaryEntry
import com.example.myapplicationcompose.flashcards.navigation.ComposeNavigation
import com.example.myapplicationcompose.flashcards.viewModel.Factory
import com.example.myapplicationcompose.flashcards.viewModel.FlashcardsViewModel
import com.example.myapplicationcompose.ui.theme.MyApplicationComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val addingFileUiData = AddingFileUiData(glossaryEntry = GlossaryEntry("",""), glossary = Glossary("", listOf()))

        val factory = Factory(addingFileUiData)
        val vm: FlashcardsViewModel by viewModels { factory }

        setContent {
            MyApplicationComposeTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ComposeNavigation(vm)
                }
            }
        }
    }
}


