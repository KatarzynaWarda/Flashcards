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
import javax.inject.Inject

class FlashcardsViewModel @Inject constructor(
    private val addingFileUiData: AddingFileUiData,
) : ViewModel() {

    private val _glossaryEntry = mutableStateListOf<GlossaryEntry>()

    private val _glossary = MutableStateFlow(addingFileUiData.glossary)
    val glossary = _glossary.asStateFlow()

    private val _glossaries = mutableStateListOf<Glossary>()
    val glossaries = _glossaries

    private val _glossaryCount = mutableStateOf(0)
    val glossaryCount: State<Int> = _glossaryCount

    fun updateGlossaryName(name: String){
        val currentName = _glossary.value
        _glossary.value = currentName.copy(name = name)
    }

    fun updateGlossaryEntry(
        term: String,
        definition: String
    ) {
        if (term.isNotEmpty() && definition.isNotEmpty()) {
            _glossaryEntry.add(GlossaryEntry(term, definition))
        }
    }

    fun updateGlossary() {
        if (_glossaryEntry.isNotEmpty()) {
            _glossary.value = Glossary(_glossary.value.name, _glossaryEntry)
            _glossaries.add(_glossary.value)
            _glossaryCount.value = _glossaries.size
        }
    }

    fun fromFirstScreenToAddingFileScreen(navigateToAddingFileScreenAction: () -> Unit){
        navigateToAddingFileScreenAction
    }

    fun fromLastToFirst(navigateToMainScreen: () -> Unit){
        navigateToMainScreen
    }

    fun addFileOnClick(navigateToFlashcardsScreenAction: (String) -> Unit){
        navigateToFlashcardsScreenAction(_glossary.value.name)
    }

    fun getGlossaryByName(name: String): Flow<Glossary?> {
        return _glossary
            .filterNotNull()
            .map { glossary ->
                if (glossary.name == name) glossary else null
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