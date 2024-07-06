package com.example.myapplicationcompose.flashcards

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.myapplicationcompose.flashcards.data.GlossaryDao
import com.example.myapplicationcompose.flashcards.data.GlossaryDatabase
import com.example.myapplicationcompose.flashcards.navigation.ComposeNavigation
import com.example.myapplicationcompose.flashcards.viewModel.Factory
import com.example.myapplicationcompose.flashcards.viewModel.FlashcardsViewModel
import com.example.myapplicationcompose.ui.theme.MyApplicationComposeTheme


class MainActivity : ComponentActivity() {

    private lateinit var database: GlossaryDatabase
    private lateinit var glossaryDao: GlossaryDao
    private val startTime = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("LaunchTime", "App start time: $startTime")
        super.onCreate(savedInstanceState)
        database = Room.databaseBuilder(applicationContext, GlossaryDatabase::class.java, "my_database")
            .build()

        glossaryDao = database.glossaryDao()

        val factory = Factory(glossaryDao)
        val vm: FlashcardsViewModel by viewModels { factory }

        setContent {
            MyApplicationComposeTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ComposeNavigation(vm)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val endTime = System.currentTimeMillis()
        Log.d("LaunchTime", "App ready time: $endTime")
        Log.d("LaunchTime", ("Launch time: " + (endTime - startTime)) + " ms")
    }
}


