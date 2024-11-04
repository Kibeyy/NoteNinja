package com.example.noteninja.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NoteEntity::class],
    exportSchema = false,
    version = 2

)
abstract class NoteDatabase:RoomDatabase(){
    abstract fun noteDao():NoteDao

}
