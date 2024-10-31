package com.example.noteninja.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.noteninja.room.NoteEntity
import com.example.noteninja.room.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val noteRepository: NoteRepository):ViewModel() {

    private var _notesList = MutableStateFlow<List<NoteEntity>>(emptyList())
    val notesList = _notesList.asStateFlow()//.asLiveData()

    val note:NoteEntity = NoteEntity(
        noteTitle = "",
        noteDescription = ""
    )

    init {
        // Collect notes when ViewModel is initialized
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.getAllNotes()
                .distinctUntilChanged() // Ensures that only distinct updates are sent to the UI
                .collect { listOfNotes ->
                    _notesList.value = listOfNotes // Update the StateFlow whenever notes are fetched
                }
        }


    }

    fun insertNote(note:NoteEntity){
        viewModelScope.launch {
            noteRepository.insertNote(note)

        }
    }

    fun deleteNote(note: NoteEntity){
        viewModelScope.launch {
            noteRepository.deleteNote(note)

        }
    }

    fun updateNote(note:NoteEntity){
        viewModelScope.launch {
            noteRepository.updateNote(note)

        }
    }

    //suspend fun getAllNotes(): LiveData<List<NoteEntity>> = noteRepository.getAllNotes().asLiveData()

}