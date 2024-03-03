package com.example.myapplicationcompose.flashcards.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationcompose.flashcards.screen.AddingFileScreen
import com.example.myapplicationcompose.flashcards.screen.FirstScreen
import com.example.myapplicationcompose.flashcards.screen.FlashcardsScreen
import com.example.myapplicationcompose.flashcards.screen.TestScreen
import com.example.myapplicationcompose.flashcards.viewModel.FlashcardsViewModel

@Composable
fun ComposeNavigation(
    viewModel: FlashcardsViewModel,
) {

    val navController = rememberNavController()
    //val vm = viewModel<FlashcardsViewModel>()

    NavHost(navController = navController, startDestination = Screen.FirstScreen.route)
    {
        composable(Screen.FirstScreen.route) {
         //   val glossaryName = remember { it.arguments?.getString(NavArgument.GlossaryName.name) }
            FirstScreen(
                viewModel = viewModel,
                navigateToAddingFileScreenAction = {
                    navController.navigate(Screen.AddingFileScreen.route) }
            )
        }
        composable(Screen.AddingFileScreen.route) {
            AddingFileScreen(
                viewModel = viewModel,
                addFileOnClick = { glossaryId ->
                    navController.navigate(Screen.TestScreen.createRoute(glossaryId)) }
                    )
        }
//        composable(Screen.FlashcardsScreen.route) {
//            FlashcardsScreen()
//        }
        composable(Screen.TestScreen.route) {
            val glossaryId = it.arguments?.getString(NavArgument.GlossaryId.name)
            TestScreen(glossaryName = glossaryId.orEmpty(), viewModel = viewModel)
        }
    }
}