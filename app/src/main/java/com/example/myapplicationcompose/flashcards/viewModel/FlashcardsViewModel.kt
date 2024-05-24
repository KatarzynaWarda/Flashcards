package com.example.myapplicationcompose.flashcards.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationcompose.flashcards.data.Glossary
import com.example.myapplicationcompose.flashcards.data.GlossaryEntry
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.myapplicationcompose.flashcards.data.GlossaryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.pow
import kotlin.math.sqrt

class FlashcardsViewModel @Inject constructor(
    private val glossaryDao: GlossaryDao
) : ViewModel() {

    private val _glossaries = mutableStateOf<List<Glossary>>(emptyList())
    val glossaries: State<List<Glossary>> = _glossaries

    init {
        viewModelScope.launch {
            glossaryDao.getAllGlossaries().collect { glossaries ->
                _glossaries.value = glossaries
            }
        }
    }

    private val _good = mutableIntStateOf(0)
    fun good(): Int = _good.intValue
    private val _bad = mutableIntStateOf(0)
    fun bad(): Int = _bad.intValue

    fun incrementGood() {
        _good.intValue++
    }

    fun incrementBad() {
        _bad.intValue++
    }

    fun summary(): Int = _good.intValue + _bad.intValue

    fun deleteGlossary(glossary: Glossary) {
        viewModelScope.launch {
            glossaryDao.deleteGlossary(glossary)
        }
    }

    fun updateGlossaryEntry(glossaryId: Int, term: String, definition: String) {
        if (term.isNotEmpty() && definition.isNotEmpty()) {
            viewModelScope.launch {
                glossaryDao.insertGlossaryEntry(GlossaryEntry(
                    term = term,
                    definition = definition,
                    glossaryId = glossaryId
                ))
            }
        }
    }

    fun updateGlossary(name: String, entries: List<GlossaryEntry>, onGlossaryCreated: (Int) -> Unit) {
        if (entries.isNotEmpty() && name.isNotBlank()) {
            viewModelScope.launch {
                val glossaryId = glossaryDao.insertGlossary(Glossary(name = name))
                entries.filter {it.term.isNotBlank() && it.definition.isNotBlank()}
                    .forEach { entry ->
                        entry.glossaryId = glossaryId.toInt()
                        glossaryDao.insertGlossaryEntry(entry)
                    }
                onGlossaryCreated(glossaryId.toInt())
            }
        }
    }

    private val _entriesForGlossary = MutableStateFlow<List<GlossaryEntry>>(emptyList())
    val entriesForGlossary: StateFlow<List<GlossaryEntry>> = _entriesForGlossary

    fun loadEntriesForGlossary(glossaryId: Int) {
        viewModelScope.launch {
            glossaryDao.getEntriesForGlossary(glossaryId).collect { entries ->
                Log.d("FlashcardsViewModel", "Loaded entries: $entries")
                _entriesForGlossary.value = entries
            }
        }
    }

    fun getGlossaryById(glossaryId: Int): Glossary? {
        return glossaries.value.find { it.id == glossaryId }
    }
}

class Factory @Inject constructor(
    private val glossaryDao: GlossaryDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(FlashcardsViewModel::class.java))
        return FlashcardsViewModel(
           glossaryDao = glossaryDao
        ) as T
    }
}