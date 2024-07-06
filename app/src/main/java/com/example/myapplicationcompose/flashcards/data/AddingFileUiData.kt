package com.example.myapplicationcompose.flashcards.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(tableName = "glossary")
data class Glossary(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val name: String
)

@Entity(
        tableName = "glossary_entry",
        foreignKeys = [ForeignKey(
                entity = Glossary::class,
                parentColumns = ["id"],
                childColumns = ["glossaryId"],
                onDelete = ForeignKey.CASCADE
        )],
        indices = [Index(value = ["glossaryId"])]
)
data class GlossaryEntry(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        var term: String,
        var definition: String,
        var glossaryId: Int
)