package com.example.miappmvvm.viewmodel


import com.example.miappmvvm.model.Usuario

sealed class UsuarioUiState {
    object Loading : UsuarioUiState()
    data class Success(val usuarios: List<Usuario>) : UsuarioUiState()
    data class Error(val message: String) : UsuarioUiState()
}