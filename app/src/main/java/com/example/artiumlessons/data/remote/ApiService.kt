package com.example.artiumlessons.data.remote

import com.example.artiumlessons.data.model.LessonsResponse
import retrofit2.http.GET

interface ApiService {
    @GET("b/7JF5")
    suspend fun getLessons(): LessonsResponse
}
