package com.example.noteninja.screens.todoscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteninja.components.SaveChangesButton
import com.example.noteninja.components.TodoButton
import com.example.noteninja.model.NoteItem
import com.example.noteninja.model.Todo_item
import com.example.noteninja.room.todo.TodoEntity
import com.example.noteninja.ui.theme.LinkColor
import com.example.noteninja.viewmodel.NotesViewModel
import com.example.noteninja.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    todoViewModel: TodoViewModel = hiltViewModel(),
    notesViewModel: NotesViewModel = hiltViewModel(),
    navController: NavController,
    modifier: Modifier

){
    val todoItem = remember {
        mutableStateOf("")
    }
    val todoList = todoViewModel.todoList.collectAsState().value
    val showBottomSheet = remember {
        mutableStateOf(false)
    }
    MaterialTheme {
        Surface(
            modifier = modifier
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 30.dp)
                ) {
                    if (todoList.isNotEmpty()) {
                        LazyColumn {
                            items(todoList) { item ->
                                Todo_item(
                                    item
                                )

                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }
                    } else {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "No tasks to display!",
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 20.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 3.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            TextButton(onClick = {
                                showBottomSheet.value = true
                            }) {
                                Text(
                                    text = "Click here to add tasks.",
                                    color = LinkColor,
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }
                    }

                }
                SaveChangesButton(
                    onClick = { showBottomSheet.value = true },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 40.dp, end = 20.dp)
                        .size(70.dp)
                )
            }
        }
    }

    if(showBottomSheet.value){
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet.value = !showBottomSheet.value}) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(start = 20.dp, end = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = todoItem.value,
                    onValueChange = {todoItem.value = it},
                    placeholder = {
                        Text(text = "Task...",
                            fontFamily = FontFamily.Monospace,
                            color = MaterialTheme.colorScheme.onSurface)
                    }

                )
                Spacer(modifier = Modifier.height(10.dp))
                TodoButton (
                    modifier = Modifier.padding(start = 250.dp),
                    onClick = {
                        if(
                            todoItem.value.isNotEmpty()
                        ){
                            val todo = TodoEntity(
                                todoContent = todoItem.value
                            )
                            todoViewModel.insertTodo(todo)
                            todoItem.value = ""
                            showBottomSheet.value = false
                        }
                    }
                )
            }

        }
    }
}