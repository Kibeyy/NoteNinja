package com.example.noteninja.screens

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
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
import com.example.noteninja.components.AddNoteTopBar2
import com.example.noteninja.components.SaveChangesButton
import com.example.noteninja.room.NoteEntity
import com.example.noteninja.ui.theme.Pink
import com.example.noteninja.ui.theme.PrimaryColor
import com.example.noteninja.ui.theme.Purple
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
        topBar = { AddNoteTopBar2(navController = navController) }
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
                    value = noteDescription.value,
                    onValueChange = {noteDescription.value = it},
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        capitalization = KeyboardCapitalization.Sentences

                                            ),
                    placeholder = {
                        Text(text = stringResource(R.string.note_content_placeholder)+"...",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 18.sp,
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
                Spacer(modifier = Modifier.height(250.dp))
                Text(text = "Note color",
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.fillMaxWidth().padding(start = 10.dp))
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Purple,
                        Pink)
                    colors.forEach { color ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .clip(RoundedCornerShape(100))
                                .background(color)

                                .width(40.dp)
                                .height(40.dp)
                                .clickable {
                                    notesViewModel.updateNoteColor(color) // Set selected color in ViewModel
                                }
                        ){
                            if (notesViewModel.selectedColor == color) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Selected",
                                    tint = Color.Black, // Choose a color that contrasts with the background
                                    modifier = Modifier.size(24.dp) // Adjust size as needed
                                )
                            }

                        }
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
                Spacer(modifier = Modifier.height(130.dp))
                //button to save the changes
                SaveChangesButton(
                    label = stringResource(R.string.add_note),
                    onClick = {
                        if(
                            noteDescription.value.isNotEmpty()
                        ){
                            val note = NoteEntity(
                                noteTitle = noteTitle.value,
                                noteDescription = noteDescription.value,
                                timeStamp = formattedDateTime.toString(),
                                noteColor = notesViewModel.selectedColor.toArgb() // Ensure this conversion is done


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