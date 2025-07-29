package com.example.artiumlessons.data.repository

import com.example.artiumlessons.data.model.Lesson
import com.example.artiumlessons.data.remote.ApiService
import com.example.artiumlessons.safeCall
import javax.inject.Inject

class LessonsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : LessonsRepository {
    override suspend fun getLessons(): Result<List<Lesson>> {
        return safeCall {
            apiService.getLessons().lessons ?: emptyList()
        }
    }
}
