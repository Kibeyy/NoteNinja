package com.example.noteninja.components


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.noteninja.viewmodel.NotesViewModel

@Preview
@Composable
fun BottomNavigation( notesViewModel: NotesViewModel = hiltViewModel()){
    val selectedIconIndex = notesViewModel.selectedIcon.value
    MaterialTheme {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            NavigationBarItem(
                selected = if(selectedIconIndex == 0) true else false
                ,
                onClick = {
                    notesViewModel.selectedIcon.value = 0
                    notesViewModel.selectedTab.value = 0
                                    },
                icon = { /*TODO*/
                    Icon(
                        imageVector =if(selectedIconIndex == 1) Icons.Outlined.Book else Icons.Filled.Book,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = "Notes")
                }
            )

            NavigationBarItem(
                selected = if(selectedIconIndex == 1) true else false,
                onClick = {
                    notesViewModel.selectedIcon.value = 1
                    notesViewModel.selectedTab.value = 1
                },
                icon = { /*TODO*/
                    Icon(
                        imageVector = if(selectedIconIndex == 1) Icons.Outlined.Work else Icons.Filled.Work,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = "To-Do")
                }
            )


        }
    }
}