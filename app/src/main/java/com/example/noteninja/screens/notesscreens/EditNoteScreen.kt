package com.example.noteninja.screens.notesscreens


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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteninja.R
import com.example.noteninja.components.SaveChangesButton
import com.example.noteninja.components.updateNoteTopBar
import com.example.noteninja.room.NoteEntity
import com.example.noteninja.ui.theme.PrimaryColor
import com.example.noteninja.viewmodel.NotesViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditNotesScreen(
    navController: NavController,
    notesViewModel: NotesViewModel = hiltViewModel(),
    noteToEdit:NoteEntity
){
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") // Customize format as needed
    val formattedDateTime = currentDateTime.format(formatter)
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current.applicationContext
    val editNoteTitle = remember{
        mutableStateOf(noteToEdit.noteTitle)
    }

    val editedNoteDescription = remember{
        mutableStateOf(noteToEdit.noteDescription)
    }

    Scaffold(
        topBar = { updateNoteTopBar(navController = navController)}
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
                    value = editNoteTitle.value,
                    onValueChange = {editNoteTitle.value = it},
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Sentences
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    placeholder = {
                        Text(text = stringResource(R.string.notetitle_placeholder),
                            fontFamily = FontFamily.Monospace,
                            fontSize = 24.sp,
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
                    value = editedNoteDescription.value,
                    onValueChange = {editedNoteDescription.value = it},
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        capitalization = KeyboardCapitalization.Sentences

                    ),
                    placeholder = {
                        Text(text = stringResource(R.string.note_content_placeholder)+"...",
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
                Spacer(modifier = Modifier.height(400.dp))
                //button to save the changes
                SaveChangesButton(
                    label = stringResource(R.string.update_note),
                    onClick = {
                        if (editedNoteDescription.value.isNotEmpty()) {
                            val updatedNote = noteToEdit.copy( // Use copy to create an updated note
                                noteTitle = editNoteTitle.value,
                                noteDescription = editedNoteDescription.value,
                                timeStamp = formattedDateTime.toString() // Update timestamp if needed
                            )
                            notesViewModel.updateNote(updatedNote) // Update the note in your ViewModel
                            navController.navigate("homescreen")
                        } else {
                            Toast.makeText(context, "Note content cannot be empty!", Toast.LENGTH_SHORT).show()
                        }
                    }
                )






            }
        }
    }
}