package com.example.myapplicationcompose.flashcards.dagger

import com.example.myapplicationcompose.flashcards.MainActivity
import dagger.Component
import javax.inject.Singleton

// odpowiedzialna za wstrzykiwanie zależności
// musi przyjmować aktywność jako argument - do niej wstrzykujemy zależności
//@Singleton
//@Component
//interface AppComponent {
//    fun inject(activity: MainActivity)
//}