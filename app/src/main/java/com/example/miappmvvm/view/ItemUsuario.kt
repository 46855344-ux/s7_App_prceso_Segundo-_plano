package com.example.miappmvvm.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.miappmvvm.model.Usuario

@Composable
fun ItemUsuario(user: Usuario) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = user.picture.medium,
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Column {
            Text(text = user.name.first)
            Text(text = user.name.last)
        }
    }
}