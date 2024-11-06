package com.example.noteninja

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.noteninja.components.BottomNavigation
import com.example.noteninja.components.SaveChangesButton
import com.example.noteninja.components.TopBar
import com.example.noteninja.constants.Hometabs
import com.example.noteninja.model.NoteItem
import com.example.noteninja.screens.notesscreens.AddNotesScreen
import com.example.noteninja.screens.notesscreens.EditNotesScreen
import com.example.noteninja.screens.notesscreens.Notesscreen
import com.example.noteninja.screens.notesscreens.SettingsScreen
import com.example.noteninja.screens.todoscreens.NotesScreen
import com.example.noteninja.screens.todoscreens.TodoScreen
import com.example.noteninja.ui.theme.LinkColor
import com.example.noteninja.ui.theme.NoteNinjaTheme
import com.example.noteninja.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        setContent {
            NoteNinjaTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "homescreen") {
                    composable("homescreen") { HomeScreen(navController = navController) }
                    //composable("todoscreen") { TodoScreen(navController = navController) }
                    composable("addnotescreen") { AddNotesScreen(navController = navController) }
                    composable("settingsscreen") { SettingsScreen(navController = navController) }
                    composable("editNote/{noteId}") { backStackEntry ->
                        val noteId = backStackEntry.arguments?.getString("noteId")?.toInt() ?: -1
                        val notesViewModel: NotesViewModel = hiltViewModel()
                        val noteToEdit = notesViewModel.getNoteById(noteId)

                        // Check if noteToEdit is null before navigating
                        if (noteToEdit != null) {
                            EditNotesScreen(navController, noteToEdit = noteToEdit)
                        } else {
                            Log.e("MainActivity", "Note not found with id: $noteId")
                            // Handle the case where the note is not found
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    notesViewModel: NotesViewModel = hiltViewModel(),
    navController: NavController
) {
    val notes = notesViewModel.notesList.collectAsState().value
    val selectedTab = notesViewModel.selectedTab.value

    Scaffold(
        topBar = { TopBar(navController = navController) },
        bottomBar = { BottomNavigation( notesViewModel =  notesViewModel)}
    ) { paddingValues ->

        when (selectedTab) {
            0 -> NotesScreen(modifier = Modifier.padding(paddingValues).fillMaxSize(), navController = navController)
            1 -> TodoScreen(modifier = Modifier.padding(paddingValues),navController = navController)
        }

    }
}

