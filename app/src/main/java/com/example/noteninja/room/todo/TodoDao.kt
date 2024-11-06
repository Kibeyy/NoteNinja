package com.example.noteninja.room.todo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {


        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertTodo(todoEntity: TodoEntity)

        @Delete
        suspend fun deleteTodo(todoEntity: TodoEntity)

        @Update
        suspend fun updateTodo(todoEntity: TodoEntity)

        @Query("SELECT * FROM todo_table")
        fun getAllTodo(): kotlinx.coroutines.flow.Flow<List<TodoEntity>>

        @Query("SELECT * FROM todo_table WHERE todoId = :id LIMIT 1")
        suspend fun getTodoById(id:Int):TodoEntity

}