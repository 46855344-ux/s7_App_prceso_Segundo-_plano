package com.example.miappmvvm.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.miappmvvm.model.ProcesoSegundoPlano
import com.example.miappmvvm.model.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsuarioViewModel : ViewModel() {

    private val repository = UsuarioRepository()

    // Estado de la lista de usuarios
    private val _uiState = MutableStateFlow<UsuarioUiState>(UsuarioUiState.Loading)
    val uiState: StateFlow<UsuarioUiState> = _uiState

    // Estado de la tarea en background
    private val _estadoBackground = MutableStateFlow("Esperando para ejecutar")
    val estadoBackground: StateFlow<String> = _estadoBackground

    init {
        fetchUsers()
    }

    // Consume la API
    fun fetchUsers() {
        viewModelScope.launch {
            _uiState.value = UsuarioUiState.Loading
            try {
                val usuarios = withContext(Dispatchers.IO) {
                    repository.getUsers()
                }
                _uiState.value = UsuarioUiState.Success(usuarios)
            } catch (e: Exception) {
                _uiState.value = UsuarioUiState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    // Encola el WorkManager
    fun iniciarTareaBackground(context: Context) {
        _estadoBackground.value = "Ejecutando..."
        viewModelScope.launch {
            val request = OneTimeWorkRequestBuilder<ProcesoSegundoPlano>().build()
            WorkManager.getInstance(context).enqueue(request)

            withContext(Dispatchers.IO) { Thread.sleep(3000) }
            _estadoBackground.value = "Completado ✅"
        }
    }
}