package com.example.noteninja.constants

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.vector.ImageVector

enum class Hometabs(
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
    val Text:String

) {
    Notes(
        Icons.Filled.Book,
        Icons.Outlined.Book,
        "Notes"
    ),
    Todo(
        Icons.Filled.Work,
        Icons.Outlined.Work,
        "TO-DO"

    )
}