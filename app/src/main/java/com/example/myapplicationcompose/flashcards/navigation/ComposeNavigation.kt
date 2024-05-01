package com.example.myapplicationcompose.flashcards.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplicationcompose.flashcards.screen.addingFileScreen.AddingFileScreen
import com.example.myapplicationcompose.flashcards.screen.mainScreen.FirstScreen
import com.example.myapplicationcompose.flashcards.screen.flashardsScreen.FlashCardsScreen
import com.example.myapplicationcompose.flashcards.viewModel.FlashcardsViewModel

@Composable
fun ComposeNavigation(
    viewModel: FlashcardsViewModel,
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.FirstScreen.route)
    {
        composable(Screen.FirstScreen.route) {
            FirstScreen(
                viewModel = viewModel,
                navigateToAddingFileScreenAction = {
                    navController.navigate(Screen.AddingFileScreen.route) },
                navigateToFlashcards = { index ->
                    navController.navigate(Screen.TestScreen.createRoute(index))
                }
            )
        }
        composable(Screen.AddingFileScreen.route) {
            AddingFileScreen(
                viewModel = viewModel,
                navigateToMainScreen = {
                    navController.navigate(Screen.FirstScreen.route) }
            )
        }
//        composable(Screen.FlashcardsScreen.route) {
//            FlashcardsScreen()
//        }
        composable(
            Screen.TestScreen.route,
            arguments = listOf(navArgument("index") { type = NavType.IntType }) ) {
                backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: 0 // Domyślna wartość na wypadek braku argumentu

            FlashCardsScreen(
                viewModel = viewModel,
                navigateToMainScreen = {
                    navController.navigate(Screen.FirstScreen.route) },
                index = index
            )
        }
    }
}