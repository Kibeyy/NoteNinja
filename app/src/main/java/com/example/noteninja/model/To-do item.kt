package com.example.noteninja.model

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.noteninja.room.todo.TodoEntity
import com.example.noteninja.viewmodel.TodoViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun Todo_item(
    todoEntity: TodoEntity,
    todoViewModel: TodoViewModel = hiltViewModel()){
    val checkedState = remember {
        mutableStateOf(false)
    }
    val isTextCrosed = remember {
        mutableStateOf(checkedState.value)
    }
    val context = LocalContext.current.applicationContext
    val delete = SwipeAction(
        onSwipe = {
            Log.d("MainActivity","Task deleted")
            todoViewModel.deleteTodo(todoEntity)
            Toast.makeText(context,"Task deleted!", Toast.LENGTH_SHORT).show()


        },
        icon = {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null )
        },
        background = Color.Red
    )
    val edit = SwipeAction(
        onSwipe = {
            Log.d("MainActivity","Item edited")


        },
        icon = {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null )
        },
        background = Color.Green
    )
    MaterialTheme {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))

        ) {
            SwipeableActionsBox(
                startActions = listOf(delete),
                endActions = listOf(edit),
                swipeThreshold = 120.dp,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))

            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.tertiary)
                        .fillMaxWidth()
                        .height(100.dp)

                ) {
                    Text(
                        text = todoEntity.todoContent,
                        textDecoration = if (checkedState.value) TextDecoration.LineThrough else TextDecoration.None,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.width(130.dp))
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = {checkedState.value = !checkedState.value},
                        colors = CheckboxDefaults.colors(
                            checkmarkColor = MaterialTheme.colorScheme.onSurface,


                        )
                        )

                }
            }
        }
    }
}