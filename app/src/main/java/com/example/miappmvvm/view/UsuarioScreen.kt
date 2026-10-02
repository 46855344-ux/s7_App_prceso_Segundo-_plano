package com.example.miappmvvm.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.miappmvvm.viewmodel.UsuarioUiState
import com.example.miappmvvm.viewmodel.UsuarioViewModel

@Composable
fun UsuarioScreen(viewModel: UsuarioViewModel = viewModel()) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val estadoBackground by viewModel.estadoBackground.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Sección WorkManager
        Text(text = "Estado Background: $estadoBackground")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { viewModel.iniciarTareaBackground(context) }) {
            Text("Iniciar tarea en segundo plano")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sección Retrofit
        when (val state = uiState) {
            is UsuarioUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Cargando...")
                }
            }
            is UsuarioUiState.Success -> {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(state.usuarios) { user ->
                        ItemUsuario(user)
                    }
                }
            }
            is UsuarioUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error: ${state.message}")
                }
            }
        }
    }
}