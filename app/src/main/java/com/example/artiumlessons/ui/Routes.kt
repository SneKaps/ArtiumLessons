package com.example.artiumlessons.ui

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Routes(val route: String, val args: List<NamedNavArgument>? = null) {
    data object LessonsList : Routes(route = "lessons_list")
    data object LessonDetail : Routes(
        route = "lesson_detail/{serialized_lesson}",
        args = listOf(
            navArgument("serialized_lesson") {
                type = NavType.StringType
            }
        )
    )
}