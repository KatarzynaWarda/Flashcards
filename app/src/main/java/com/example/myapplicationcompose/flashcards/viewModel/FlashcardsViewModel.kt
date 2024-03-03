package com.example.myapplicationcompose.flashcards.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplicationcompose.flashcards.data.AddingFileUiData
import com.example.myapplicationcompose.flashcards.data.Glossary
import com.example.myapplicationcompose.flashcards.data.GlossaryEntry
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Named

class FlashcardsViewModel @Inject constructor(
    private val addingFileUiData: AddingFileUiData,
) : ViewModel() {

    private val _term = MutableStateFlow(addingFileUiData.newTerm)
    val term = _term.asStateFlow()

    private val _definition = MutableStateFlow(addingFileUiData.newDefinition)
    val definition = _definition.asStateFlow()

    private val _glossaryName = MutableStateFlow(addingFileUiData.glossaryName)
    val glossaryName = _glossaryName.asStateFlow()

    private val _glossaryEntry = MutableStateFlow(addingFileUiData.glossaryEntry)
    val glossaryEntry = _glossaryEntry.asStateFlow()

    private val _glossary = MutableStateFlow(addingFileUiData.glossary)
    val glossary = _glossary.asStateFlow()

    private val _temporaryEntries = MutableStateFlow<List<GlossaryEntry>>(listOf())

    fun updateTerm(term: String){
        _term.value = term
    }

    fun updateDefinition(definition: String){
        _definition.value = definition
    }

    fun updateGlossaryName(name: String){
        _glossaryName.value = name
    }

    fun updateGlossaryEntry(term: String, definition: String) {
        val newEntry = GlossaryEntry(term, definition)
        _temporaryEntries.value = _temporaryEntries.value + newEntry
        updateGlossary()
    }

    fun updateGlossary() {
        _glossary.value = Glossary(_glossaryName.value, _temporaryEntries.value)
    }

    fun fromFirstScreenToAddingFileScreen(navigateToAddingFileScreenAction: () -> Unit){
        navigateToAddingFileScreenAction
    }

    fun addFileOnClick(navigateToFlashcardsScreenAction: (String) -> Unit){
        navigateToFlashcardsScreenAction(_glossaryName.value)
    }

    fun getGlossaryByName(name: String): StateFlow<Glossary?> {
        return _glossary.map { glossary -> if (glossary.name == name) glossary else null }.stateIn(viewModelScope, SharingStarted.Lazily, null)
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