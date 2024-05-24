package com.example.myapplicationcompose.flashcards.navigation

import com.example.myapplicationcompose.flashcards.data.Glossary

sealed class Screen(val route: String) {

    object FirstScreen : Screen("first_screen/{glossaryName}") {
        fun createRoute(glossaryName: String) = "first_screen/$glossaryName"
    }
    object AddingFileScreen : Screen("adding_file_screen")

    object AddingFileScreenWithId : Screen("adding_file_screen/{glossaryId}") {
        fun createRoute(glossaryId: Int) = "adding_file_screen/$glossaryId"
    }

    object FlashcardsScreen : Screen("flashcards_screen/{glossaryId}") {
        fun createRoute(glossaryId: Int) = "flashcards_screen/$glossaryId"
    }

}