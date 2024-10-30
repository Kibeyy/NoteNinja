package com.example.noteninja.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun SaveChangesButton(modifier: Modifier = Modifier, onClick:() -> Unit){
    FloatingActionButton(
        onClick = { /*navigate to add note*/onClick() },
        containerColor =  MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(50.dp),
        elevation = FloatingActionButtonDefaults.elevation(20.dp),
        modifier = modifier

        ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(imageVector = Icons.Default.Add,
                modifier = Modifier.size(30.dp),
                tint = Color.White,
                contentDescription = "add note",
                )

        }

    }
}