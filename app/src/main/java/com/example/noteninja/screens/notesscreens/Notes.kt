package com.example.noteninja.screens.notesscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteninja.components.SaveChangesButton
import com.example.noteninja.model.NoteItem
import com.example.noteninja.ui.theme.LinkColor
import com.example.noteninja.viewmodel.NotesViewModel

@Composable
fun Notesscreen(notesViewModel: NotesViewModel = hiltViewModel(),
                navController: NavController
){
    val notes = notesViewModel.notesList.collectAsState().value
    Surface {
        Box(
            modifier = Modifier
              //  .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 30.dp)
            ) {
                if (notes.isNotEmpty()) {
                    LazyColumn {
                        items(notes) { item ->
                            NoteItem(
                                note = item,
                                notesViewModel = notesViewModel,
                                navController = navController // Pass navController to NoteItem
                            )

                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                } else {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "No notes to display!",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 3.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        TextButton(onClick = {
                            navController.navigate("addnotescreen")
                        }) {
                            Text(
                                text = "Click here to add notes.",
                                color = LinkColor,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }
            }

            SaveChangesButton(
                onClick = { navController.navigate("addnotescreen") },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 40.dp, end = 20.dp)
                    .size(70.dp)
            )
        }
    }
}