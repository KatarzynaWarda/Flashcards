package com.example.myapplicationcompose

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplicationcompose.flashcards.data.Glossary
import com.example.myapplicationcompose.flashcards.data.GlossaryDao
import com.example.myapplicationcompose.flashcards.data.GlossaryDatabase
import com.example.myapplicationcompose.flashcards.data.GlossaryEntry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class GlossaryDatabaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: GlossaryDatabase
    private lateinit var glossaryDao: GlossaryDao

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GlossaryDatabase::class.java
        )
            .allowMainThreadQueries() // For simplicity in testing; avoid in production
            .build()
        glossaryDao = database.glossaryDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testAddGlossaryAndEntries() = runBlocking {
        val glossary = Glossary(name = "Animals")
        val entries = listOf(
            GlossaryEntry(term = "Cat", definition = "Kot", glossaryId = 0),
            GlossaryEntry(term = "Dog", definition = "Pies", glossaryId = 0),
            GlossaryEntry(term = "Hamster", definition = "Chomik", glossaryId = 0)
        )

        // Measure insert time
        val insertStartTime = System.nanoTime()
        val glossaryId = glossaryDao.insertGlossary(glossary)
        entries.forEach { entry ->
            val entryWithId = entry.copy(glossaryId = glossaryId.toInt())
            glossaryDao.insertGlossaryEntry(entryWithId)
        }
        val insertEndTime = System.nanoTime()
        val insertDuration = TimeUnit.NANOSECONDS.toMillis(insertEndTime - insertStartTime)

        println("Insert duration: $insertDuration ms")
        Assert.assertNotEquals(glossaryId, -1)
    }

    @Test
    fun testReadGlossariesAndEntries() = runBlocking {
        // Add a glossary and entries first
        val glossary = Glossary(name = "Animals")
        val entries = listOf(
            GlossaryEntry(term = "Cat", definition = "Kot", glossaryId = 0),
            GlossaryEntry(term = "Dog", definition = "Pies", glossaryId = 0),
            GlossaryEntry(term = "Hamster", definition = "Chomik", glossaryId = 0)
        )
        val glossaryId = glossaryDao.insertGlossary(glossary)
        entries.forEach { entry ->
            val entryWithId = entry.copy(glossaryId = glossaryId.toInt())
            glossaryDao.insertGlossaryEntry(entryWithId)
        }

        // Measure read time
        val readStartTime = System.nanoTime()
        val glossaries = glossaryDao.getAllGlossaries().first()
        Assert.assertTrue(glossaries.isNotEmpty())

        val glossaryToRead = glossaries.last()
        val entriesForGlossary = glossaryDao.getEntriesForGlossary(glossaryToRead.id).first()
        Assert.assertTrue(entriesForGlossary.isNotEmpty())
        val readEndTime = System.nanoTime()
        val readDuration = TimeUnit.NANOSECONDS.toMillis(readEndTime - readStartTime)

        println("Read duration: $readDuration ms")

        entriesForGlossary.forEach { entry ->
            println("Term: ${entry.term}, Definition: ${entry.definition}")
        }
    }

    @Test
    fun testDeleteGlossaryAndEntries() = runBlocking {
        // Add a glossary and entries first
        val glossary = Glossary(name = "Animals")
        val entries = listOf(
            GlossaryEntry(term = "Cat", definition = "Kot", glossaryId = 0),
            GlossaryEntry(term = "Dog", definition = "Pies", glossaryId = 0),
            GlossaryEntry(term = "Hamster", definition = "Chomik", glossaryId = 0)
        )
        val glossaryId = glossaryDao.insertGlossary(glossary)
        entries.forEach { entry ->
            val entryWithId = entry.copy(glossaryId = glossaryId.toInt())
            glossaryDao.insertGlossaryEntry(entryWithId)
        }

        // Measure delete time
        val deleteStartTime = System.nanoTime()
        val glossaries = glossaryDao.getAllGlossaries().first()
        val glossaryToDelete = glossaries.last()

        // Delete glossary
        glossaryDao.deleteGlossary(glossaryToDelete)
        val deleteEndTime = System.nanoTime()
        val deleteDuration = TimeUnit.NANOSECONDS.toMillis(deleteEndTime - deleteStartTime)

        println("Delete duration: $deleteDuration ms")

        val remainingGlossaries = glossaryDao.getAllGlossaries().first()
        val remainingEntries = glossaryDao.getEntriesForGlossary(glossaryToDelete.id).first()

        println("Remaining glossaries: $remainingGlossaries")
        println("Remaining entries for glossary ${glossaryToDelete.id}: $remainingEntries")

        Assert.assertTrue(remainingGlossaries.none { it.id == glossaryToDelete.id })
        Assert.assertTrue(remainingEntries.isEmpty())
    }
}