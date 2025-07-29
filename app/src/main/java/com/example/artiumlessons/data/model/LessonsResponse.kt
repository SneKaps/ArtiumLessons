package com.example.artiumlessons.data.model

import com.google.gson.annotations.SerializedName

data class LessonsResponse(
    @SerializedName("lessons")
    val lessons: List<Lesson>?
)
