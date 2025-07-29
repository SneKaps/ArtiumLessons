package com.example.artiumlessons.data.repository

import com.example.artiumlessons.data.model.Lesson

interface LessonsRepository {
    suspend fun getLessons(): Result<List<Lesson>>
}
