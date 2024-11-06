package com.example.noteninja.screens.notesscreens

import androidx.compose.foundation.border
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
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noteninja.components.SettingsTopBar
import com.example.noteninja.ui.theme.NoteNinjaTheme


@Composable
fun SettingsScreen(navController: NavController){
   Scaffold(
       topBar = { SettingsTopBar(navController = navController)}
   ) {paddingValues ->
       NoteNinjaTheme {
           Surface(
               modifier = Modifier.padding(paddingValues)
           ) {
               Column(
                   horizontalAlignment = Alignment.CenterHorizontally,
                   modifier = Modifier
                       .padding(start = 20.dp, end = 20.dp, top = 30.dp)
                       .fillMaxSize()
               ) {

                   Column(
                       modifier = Modifier
                           .fillMaxWidth()

                           .border(
                               2.dp,
                               MaterialTheme.colorScheme.onSurface.copy(alpha = .4f),
                               RoundedCornerShape(10.dp)
                           )
                   ) {
                       Row(modifier = Modifier.padding(top = 10.dp, start = 10.dp)) {
                           Icon(imageVector = Icons.Outlined.Delete, contentDescription = null, tint = Color.Red.copy(alpha = .5f))
                           Spacer(modifier = Modifier.width(10.dp))
                           Text(text = "Delete", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, color = Color.Gray)

                       }
                       Text(text = "Swipe right on an item to delete it ",
                           fontFamily = FontFamily.Monospace,
                           fontSize = 14.sp,
                           modifier = Modifier.padding(start = 10.dp, bottom = 10.dp))


                   }
                   Spacer(modifier = Modifier.height(40.dp))

                   Column(
                       modifier = Modifier
                           .fillMaxWidth()

                           .border(
                               2.dp,
                               MaterialTheme.colorScheme.onSurface.copy(alpha = .4f),
                               RoundedCornerShape(10.dp)
                           )
                   ) {
                       Row(modifier = Modifier.padding(top = 10.dp, start = 10.dp)) {
                           Icon(imageVector = Icons.Outlined.Edit, contentDescription = null, tint = Color.Green.copy(alpha = .5f))
                           Spacer(modifier = Modifier.width(10.dp))
                           Text(text = "Edit", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold,color = Color.Gray)

                       }
                       Text(text = "Swipe left on an item to edit it ",
                           fontFamily = FontFamily.Monospace,
                           fontSize = 14.sp,
                           modifier = Modifier.padding(start = 10.dp, bottom = 10.dp))


                   }

               }
           }
       }

   }

}