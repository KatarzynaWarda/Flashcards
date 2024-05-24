package com.example.myapplicationcompose.flashcards.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GlossaryDao {
    @Insert
    suspend fun insertGlossary(glossary: Glossary): Long

    @Insert
    suspend fun insertGlossaryEntry(entry: GlossaryEntry)

    @Delete
    suspend fun deleteGlossary(glossary: Glossary)

    @Query("SELECT * FROM glossary")
    fun getAllGlossaries(): Flow<List<Glossary>>

    @Query("SELECT * FROM glossary_entry WHERE glossaryId = :glossaryId")
    fun getEntriesForGlossary(glossaryId: Int): Flow<List<GlossaryEntry>>
}
