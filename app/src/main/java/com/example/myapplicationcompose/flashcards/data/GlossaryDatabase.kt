package com.example.myapplicationcompose.flashcards.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Glossary::class, GlossaryEntry::class], version = 1)
abstract class GlossaryDatabase: RoomDatabase() {
    abstract fun glossaryDao(): GlossaryDao
}