package com.example.myapplicationcompose.flashcards.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationcompose.flashcards.data.AddingFileUiData
import com.example.myapplicationcompose.flashcards.data.Glossary
import com.example.myapplicationcompose.flashcards.data.GlossaryEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import javax.inject.Inject

class FlashcardsViewModel @Inject constructor(
    private val addingFileUiData: AddingFileUiData,
) : ViewModel() {

    private val _glossaryEntry = mutableStateListOf<GlossaryEntry>()

    private val _glossary = MutableStateFlow(addingFileUiData.glossary)
    val glossary = _glossary.asStateFlow()

    private val _glossaries = mutableStateListOf<Glossary>()
    val glossaries = _glossaries

    private val _glossaryCount = mutableIntStateOf(0)
    val glossaryCount: State<Int> = _glossaryCount

    fun updateGlossaryEntry(
        term: String,
        definition: String,
    ) {
        if (term.isNotEmpty() && definition.isNotEmpty()) {
            _glossaryEntry.add(GlossaryEntry(term, definition))
        }
    }

    fun updateGlossary(name: String) {
        if (_glossaryEntry.isNotEmpty()  && name.isNotBlank()) {
            val newGlossary = Glossary(name, _glossaryEntry.toList()) // Tworzenie nowego obiektu Glossary
            _glossary.value = newGlossary // Aktualizacja wartości _glossary
            _glossaries.add(newGlossary) // Dodanie do listy _glossaries
            _glossaryCount.intValue = _glossaries.size // Aktualizacja liczby glosariuszy
            _glossaryEntry.clear()
        }
    }
}

class Factory @Inject constructor(
    private val addingFileUiData: AddingFileUiData
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(FlashcardsViewModel::class.java))
        return FlashcardsViewModel(
            addingFileUiData = addingFileUiData,
        ) as T
    }
}