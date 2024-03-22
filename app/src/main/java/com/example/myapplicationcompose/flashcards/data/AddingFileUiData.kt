package com.example.myapplicationcompose.flashcards.data

data class AddingFileUiData(
    val glossaryEntry : GlossaryEntry,
    val glossary: Glossary,
)

data class GlossaryEntry(val term: String, val definition: String)

data class Glossary(val name: String, val entries: List<GlossaryEntry>)