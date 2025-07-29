package com.example.artiumlessons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.artiumlessons.data.model.Lesson
import com.example.artiumlessons.ui.Routes
import com.example.artiumlessons.ui.core_components.ArtiumLessonsSurface
import com.example.artiumlessons.ui.lessons.LessonDetailRoute
import com.example.artiumlessons.ui.lessons.LessonsListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtiumLessonsSurface {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.LessonsList.route,
                ) {
                    composable(Routes.LessonsList.route) {
                        LessonsListScreen(navController = navController)
                    }
                    composable(
                        route = Routes.LessonDetail.route,
                        arguments = Routes.LessonDetail.args ?: emptyList(),
                    ) { backStackEntry ->
                        LessonDetailRoute(
                            lesson = backStackEntry.arguments?.getString("serialized_lesson")
                                .deserializeToClass(Lesson::class.java),
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}