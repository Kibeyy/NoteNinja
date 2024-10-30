package com.example.noteninja.screens

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.noteninja.R
import com.example.noteninja.components.AddNoteTopBar
import com.example.noteninja.components.SaveChangesButton
import com.example.noteninja.room.NoteEntity
import com.example.noteninja.ui.theme.PrimaryColor
import com.example.noteninja.viewmodel.NotesViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddNotesScreen(
    navController: NavController,
    notesViewModel: NotesViewModel = hiltViewModel()
){
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") // Customize format as needed
    val formattedDateTime = currentDateTime.format(formatter)
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current.applicationContext
    val noteTitle = remember{
        mutableStateOf("")
    }

    val noteDescription = remember{
        mutableStateOf("")
    }

    Scaffold(
        topBar = { AddNoteTopBar(navController = navController)}
    ) {paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 50.dp)
                    .fillMaxSize()
            ) {
                TextField(
                    value = noteTitle.value,
                    onValueChange = {noteTitle.value = it},
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    placeholder = {
                        Text(text = stringResource(R.string.notetitle_placeholder),
                            fontFamily = FontFamily.Monospace,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                            )
                                  },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent, 
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = PrimaryColor


                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                //show current date and time here
                Text(text = formattedDateTime,
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .fillMaxWidth())
                Spacer(modifier = Modifier.height(10.dp))
                //text field for adding notes
                TextField(
                    value = noteDescription.value,
                    onValueChange = {noteDescription.value = it},
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                                            ),
                    placeholder = {
                        Text(text = stringResource(R.string.note_content_placeholder)+"...",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent

                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(400.dp))
                //button to save the changes
                SaveChangesButton(
                    onClick = {
                        if(
                            noteDescription.value.isNotEmpty()
                        ){
                            val note = NoteEntity(
                                noteTitle = noteTitle.value,
                                noteDescription = noteDescription.value
                            )
                            notesViewModel.insertNote(note)
                            noteTitle.value = ""
                            noteDescription.value = ""
                            navController.navigate("homescreen")
                        }else{
                            Toast.makeText(context,"Note content cannot be empty!",Toast.LENGTH_SHORT).show()

                        }

                })






            }
        }
    }
}