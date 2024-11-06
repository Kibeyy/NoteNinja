package com.example.noteninja.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteninja.ui.theme.NoteNinjaTheme

@Composable
fun TodoButton(onClick:() -> Unit,modifier: Modifier){
   NoteNinjaTheme {
       Button(
           modifier = modifier
               .width(110.dp)
               .height(50.dp),
           onClick = { onClick() },
           shape = RoundedCornerShape(10.dp),
           colors = ButtonDefaults.buttonColors(
               containerColor = MaterialTheme.colorScheme.primary
           )
       ) {
           Text(text = "ADD",
               fontFamily = FontFamily.Monospace,
               color = MaterialTheme.colorScheme.onSurface,
               fontSize = 20.sp,
               letterSpacing = 2.sp,
               fontWeight = FontWeight.Bold)

       }
   }
}