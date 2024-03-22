package com.example.myapplicationcompose.flashcards.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplicationcompose.flashcards.data.AddingFileUiData
import com.example.myapplicationcompose.flashcards.data.Glossary
import com.example.myapplicationcompose.flashcards.data.GlossaryEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class FlashcardsViewModel @Inject constructor(
    private val addingFileUiData: AddingFileUiData,
) : ViewModel() {

    private val _glossaryEntry = MutableStateFlow(addingFileUiData.glossary.entries)
    val glossaryEntries: StateFlow<List<GlossaryEntry>> = _glossaryEntry.asStateFlow()

    private val _glossary = MutableStateFlow(addingFileUiData.glossary)
    val glossary = _glossary.asStateFlow()

    fun updateGlossaryName(name: String){
        val currentName = _glossary.value
        _glossary.value = currentName.copy(name = name)
    }

    fun updateGlossaryEntry(
        term: String,
        definition: String,
        index: Int,
        updatedEntry: GlossaryEntry
    ) {
        val updatedList = _glossaryEntry.value.toMutableList().apply {
            this[index] = updatedEntry
        }

        if (index == _glossaryEntry.value.size - 1 && updatedEntry.definition.isNotBlank()) {
            updatedList.add(GlossaryEntry(term, definition))
        }

        _glossaryEntry.value = updatedList
    }

    fun updateGlossary() {
        _glossary.value = Glossary(_glossary.value.name, _glossaryEntry.value)
    }

    fun fromFirstScreenToAddingFileScreen(navigateToAddingFileScreenAction: () -> Unit){
        navigateToAddingFileScreenAction
    }

    fun addFileOnClick(navigateToFlashcardsScreenAction: (String) -> Unit){
        navigateToFlashcardsScreenAction(_glossary.value.name)
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