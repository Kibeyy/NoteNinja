package com.example.noteninja.room

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_tbl")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    val noteId:Int = 0,
    val noteTitle:String,
    val noteDescription:String,
    val timeStamp:String,
    val noteColor:Int
)

fun Int.toColorr(): Color {
    return Color(this)
}

