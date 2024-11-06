package com.example.noteninja.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteninja.room.NoteEntity
import com.example.noteninja.room.todo.TodoDao
import com.example.noteninja.room.todo.TodoEntity
import com.example.noteninja.room.todo.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val repository: TodoRepository):ViewModel() {
    private var _todoList = MutableStateFlow<List<TodoEntity>>(emptyList())
    val todoList = _todoList.asStateFlow()//.asLiveData()

    init {
//        checkSelectedIconIndex()

        // Collect notes when ViewModel is initialized
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllTodo()
                .distinctUntilChanged() // Ensures that only distinct updates are sent to the UI
                .collect { listOfTodo ->
                    _todoList.value = listOfTodo // Update the StateFlow whenever notes are fetched
                }
        }


    }

    fun insertTodo(todoEntity: TodoEntity){
        viewModelScope.launch {
            repository.insertTodo(todoEntity)
        }

    }

    fun updateTodo(todoEntity: TodoEntity){
        viewModelScope.launch {
            repository.updateTodo(todoEntity)
        }

    }

    fun deleteTodo(todoEntity: TodoEntity){
        viewModelScope.launch {
            repository.deleteTodo(todoEntity)
        }

    }

    fun getTodoById(todoId: Int): TodoEntity? {
        return _todoList.value.find { it.todoId == todoId } // This assumes notesList is a LiveData or StateFlow
    }


}