package com.example.noteninja.model

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.noteninja.room.NoteEntity
import com.example.noteninja.ui.theme.NoteNinjaTheme
import com.example.noteninja.viewmodel.NotesViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@Composable
fun NoteItem(
    note: NoteEntity,
    notesViewModel: NotesViewModel = hiltViewModel()
){
    val delete = SwipeAction(
        onSwipe = {
            Log.d("MainActivity","Item deleted")
            notesViewModel.deleteNote(note)


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


    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .shadow(elevation = 20.dp)

    ) {
        SwipeableActionsBox(
            startActions = listOf(delete),
            endActions = listOf(delete),
            swipeThreshold = 120.dp,
            modifier = Modifier.clip(RoundedCornerShape(20.dp))
        ) {
            Row(

                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    .clip(RoundedCornerShape(20.dp))
                    .padding(all = 20.dp)
                    .fillMaxWidth()



            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = note.noteTitle,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = note.noteDescription,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = FontFamily.Monospace,
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                IconButton(
                    onClick = { /*TO delete note here*/
                        notesViewModel.deleteNote(note)

                    },
                    modifier = Modifier
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "delete note",
                        tint = MaterialTheme.colorScheme.onSurface
                    )

                }


            }
        }
    }
}