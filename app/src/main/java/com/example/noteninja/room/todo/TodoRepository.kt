package com.example.noteninja.room.todo


import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepository @Inject constructor(private val TodoDao: TodoDao) {

    suspend fun insertTodo(todoEntity: TodoEntity) = TodoDao.insertTodo(todoEntity)

    suspend fun deleteTodo(todoEntity: TodoEntity) = TodoDao.deleteTodo(todoEntity)

    suspend fun updateTodo(todoEntity: TodoEntity) = TodoDao.updateTodo(todoEntity)

    fun getAllTodo():Flow<List<TodoEntity>>  =TodoDao.getAllTodo()

    suspend fun getTodoById(todoId:Int):TodoEntity = TodoDao.getTodoById(todoId)


}