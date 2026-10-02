package com.example.miappmvvm.model

import retrofit2.http.GET

interface ApiService {
    @GET("api/?results=20")
    suspend fun getUsers(): ApiResponse
}