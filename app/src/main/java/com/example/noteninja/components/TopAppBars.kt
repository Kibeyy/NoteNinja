package com.example.noteninja.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.noteninja.ui.theme.NoteNinjaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteTopBar2(navController: NavController){
    CenterAlignedTopAppBar(
        title = { Text(text = "ADD NOTE",
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        ) },
        navigationIcon = {
            IconButton(onClick = { /*go back to home screen*/ navController.navigate("homescreen")}) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back button")

            }

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun updateNoteTopBar(navController: NavController){
    CenterAlignedTopAppBar(
        title = { Text(text = "UPDATE NOTE",
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        ) },
        navigationIcon = {
            IconButton(onClick = { /*go back to home screen*/ navController.navigate("homescreen")}) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back button")

            }

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopBar(navController: NavController){
    NoteNinjaTheme {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            navigationIcon = {
                IconButton(
                    onClick = { /*to navigate back to homepage*/navController.navigate("homescreen") }
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back button")
                }
            },
            title = {
                Text(text = "Settings", fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
            }
        )
    }
}