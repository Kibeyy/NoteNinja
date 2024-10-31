package com.example.noteninja.room

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    suspend fun insertNote(note: NoteEntity) = noteDao.insertNote(note)

    suspend fun deleteNote(note: NoteEntity) {
        noteDao.deleteNote(note)
    }

    suspend fun updateNote(note: NoteEntity) = noteDao.updateNote(note)

    suspend fun getAllNotes(): Flow<List<NoteEntity>> = noteDao.getAllNotes()

    suspend fun getNoteById(noteId: Int): NoteEntity? {
        return noteDao.getNoteById(noteId)
    }


}