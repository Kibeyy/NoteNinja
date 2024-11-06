package com.example.noteninja.room.todo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val todoId:Int = 0,
    val todoContent:String,
    )