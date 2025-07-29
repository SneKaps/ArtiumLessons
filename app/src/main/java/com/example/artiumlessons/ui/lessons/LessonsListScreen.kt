package com.example.artiumlessons.ui.lessons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.artiumlessons.data.model.Lesson
import com.example.artiumlessons.ui.core_components.NavBar
import com.example.artiumlessons.ui.lessons.viewmodel.LessonsViewModel
import com.example.artiumlessons.ui.showToast

@Composable
fun LessonsListScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: LessonsViewModel = hiltViewModel()
    val screenState by viewModel.screenState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.screenEvents.collect { event ->
            when (event) {
                is LessonsListScreenEvents.NavigateTo -> navController.navigate(event.route)
                is LessonsListScreenEvents.Error -> context.showToast(event.message)
                else -> Unit
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (val currentState = screenState) {
            LessonsListScreenStates.Loading -> CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onPrimary
            )

            is LessonsListScreenStates.Success -> LessonListScreen(
                lessons = currentState.lessons,
                onClickItem = { lesson ->
                    viewModel.navigateToLessonDetail(lesson)
                },
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
    }
}

@Composable
private fun LessonListScreen(
    lessons: List<Lesson>,
    onClickItem: (Lesson) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        NavBar(
            title = "Lessons List",
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(lessons) { lesson ->
                LessonItem(lesson = lesson, onClick = onClickItem)
            }
        }
    }
}

sealed class LessonsListScreenStates {
    data object Loading : LessonsListScreenStates()
    data class Success(val lessons: List<Lesson>) : LessonsListScreenStates()
}

sealed class LessonsListScreenEvents {
    data class Error(val message: String) : LessonsListScreenEvents()
    data class NavigateTo(val route: String) : LessonsListScreenEvents()
}
