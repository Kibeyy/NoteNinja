package com.example.noteninja.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteninja.R
import com.example.noteninja.ui.theme.NoteNinjaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SearchNote(){
   NoteNinjaTheme {
       Row(
           verticalAlignment = Alignment.CenterVertically,
           modifier = Modifier
               .clip(RoundedCornerShape(10.dp))
               .background(color = MaterialTheme.colorScheme.secondary)
               .height(50.dp)
               .width(150.dp)
       ) {
           IconButton(
               onClick = { /*TODO*/ }) {
               Icon(
                   imageVector = Icons.Default.Search ,
                   contentDescription = "Search icon" ,
                   tint = MaterialTheme.colorScheme.onSurface)
           }

           Text(text = stringResource(id = R.string.search_note),
               color = MaterialTheme.colorScheme.onSurface)

       }
   }
}
