package com.example.myapplicationcompose.flashcards.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationcompose.flashcards.data.AddingFileUiData
import com.example.myapplicationcompose.flashcards.data.Glossary
import com.example.myapplicationcompose.flashcards.data.GlossaryEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import javax.inject.Inject

class FlashcardsViewModel @Inject constructor(
        addingFileUiData: AddingFileUiData,
) : ViewModel() {

    private val _glossaryEntry = mutableStateListOf<GlossaryEntry>()

    private val _glossary = MutableStateFlow(addingFileUiData.glossary)
    val glossary = _glossary.asStateFlow()

    private val _glossaries = mutableStateListOf<Glossary>()
    val glossaries = _glossaries

    private val _glossaryCount = mutableIntStateOf(0)
    val glossaryCount: State<Int> = _glossaryCount

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

    fun updateGlossaryEntry(
            term: String,
            definition: String,
    ) {
        if (term.isNotEmpty() && definition.isNotEmpty()) {
            _glossaryEntry.add(GlossaryEntry(term, definition))
        }
    }

    fun updateGlossary(name: String) {
        if (_glossaryEntry.isNotEmpty() && name.isNotBlank()) {
            val newGlossary = Glossary(name, _glossaryEntry.toList())
            _glossary.value = newGlossary
            _glossaries.add(newGlossary)
            _glossaryCount.intValue = _glossaries.size
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