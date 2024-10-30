package com.example.noteninja.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_tbl")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    val noteId:Int = 0,
    val noteTitle:String,
    val noteDescription:String
)