package com.example.letroso.data.networking

import com.example.letroso.data.local.Palavra
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api")
    suspend fun getWords(
        @Query("language") language: String = "pt-br"
    ): Response<List<Palavra>>
}