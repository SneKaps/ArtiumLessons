package com.example.artiumlessons.data.model

import com.google.gson.annotations.SerializedName

data class Lesson(
    @SerializedName("mentor_name")
    val mentorName: String?,
    @SerializedName("lesson_title")
    val lessonTitle: String?,
    @SerializedName("video_thumbnail_url")
    val videoThumbnailUrl: String?,
    @SerializedName("lesson_image_url")
    val lessonImageUrl: String?,
    @SerializedName("video_url")
    val videoUrl: String?
)
