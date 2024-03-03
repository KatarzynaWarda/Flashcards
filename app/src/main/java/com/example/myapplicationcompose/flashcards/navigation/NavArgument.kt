package com.example.myapplicationcompose.flashcards.navigation

sealed class NavArgument(val name: String) {
    object GlossaryName : NavArgument("glossaryName")
    object GlossaryId : NavArgument("glossaryId")
}