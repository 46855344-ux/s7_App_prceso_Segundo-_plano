package com.example.miappmvvm.model

import com.example.miappmvvm.model.ApiResponse
import com.example.miappmvvm.model.Usuario

class UsuarioRepository {

    private val apiService = RetrofitClient.apiService

    suspend fun getUsers(): List<Usuario> {
        val response = apiService.getUsers()
        return response.results
    }
}