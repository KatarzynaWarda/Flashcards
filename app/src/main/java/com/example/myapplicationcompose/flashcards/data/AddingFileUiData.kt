package com.example.myapplicationcompose.flashcards.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "glossary")
data class Glossary(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val name: String
)

@Entity(tableName = "glossary_entry")
data class GlossaryEntry (
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        var term: String,
        var definition: String,
        var glossaryId: Int
)