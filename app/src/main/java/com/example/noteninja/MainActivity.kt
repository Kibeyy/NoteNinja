package com.example.noteninja

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.noteninja.components.SaveChangesButton
import com.example.noteninja.components.TopBar
import com.example.noteninja.model.NoteItem
import com.example.noteninja.room.NoteEntity
import com.example.noteninja.screens.AddNotesScreen
import com.example.noteninja.ui.theme.LinkColor
import com.example.noteninja.ui.theme.NoteNinjaTheme
import com.example.noteninja.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

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
                    composable("addnotescreen") { AddNotesScreen(navController = navController) }
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



    Scaffold(
        topBar = { TopBar() }
    ) { paddingValues ->
        Surface {
            Box(
                modifier = Modifier
                    .padding(paddingValues)
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
                                       note = item,  // Pass each item directly to NoteItem
                                       notesViewModel = notesViewModel // Provide ViewModel for delete functionality
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
}
