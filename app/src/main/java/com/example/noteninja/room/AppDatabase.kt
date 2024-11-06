package com.example.noteninja.room


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteninja.room.todo.TodoDao
import com.example.noteninja.room.todo.TodoEntity

@Database(
    entities = [NoteEntity::class,TodoEntity::class],

    version = 3

)
abstract class AppDatabase:RoomDatabase(){
    abstract fun noteDao():NoteDao
    abstract fun todoDao():TodoDao

}
