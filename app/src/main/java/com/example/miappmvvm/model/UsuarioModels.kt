package com.example.miappmvvm.model

data class ApiResponse(val results: List<Usuario>)

data class Usuario(
    val name: Nombre,
    val picture: Imagen
)

data class Nombre(
    val first: String,
    val last: String
)

data class Imagen(
    val medium: String
)