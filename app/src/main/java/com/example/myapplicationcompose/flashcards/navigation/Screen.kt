package com.example.myapplicationcompose.flashcards.navigation

import com.example.myapplicationcompose.flashcards.data.Glossary

sealed class Screen(val route: String) {

    object FirstScreen : Screen("first_screen/{glossaryName}") {
        fun createRoute(glossaryName: String) = "first_screen/$glossaryName"
    }

    object AddingFileScreen : Screen("adding_file_screen")

    object FlashcardsScreen : Screen("flashcards_screen/{glossaryId}") {
        fun createRoute(glossaryId: String) = "flashcards_screen/$glossaryId"
    }

    object TestScreen : Screen("test_screen/{index}") {
        fun createRoute(index: Int) = "test_screen/$index"
    }

}