package com.example.artiumlessons.ui.lessons.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artiumlessons.data.model.Lesson
import com.example.artiumlessons.data.repository.LessonsRepository
import com.example.artiumlessons.serialize
import com.example.artiumlessons.ui.lessons.LessonsListScreenEvents
import com.example.artiumlessons.ui.lessons.LessonsListScreenStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonsViewModel @Inject constructor(
    private val repository: LessonsRepository
) : ViewModel() {

    private val _screenState =
        MutableStateFlow<LessonsListScreenStates>(LessonsListScreenStates.Loading)
    val screenState: StateFlow<LessonsListScreenStates> = _screenState.asStateFlow()
    private val _events = Channel<LessonsListScreenEvents?>()
    val screenEvents = _events.receiveAsFlow()

    init {
        loadLessons()
    }

    fun navigateToLessonDetail(lesson: Lesson) {
        _events.trySend(LessonsListScreenEvents.NavigateTo("lesson_detail/${lesson.serialize()}"))
    }

    private fun loadLessons() {
        viewModelScope.launch {
            repository.getLessons().onSuccess {
                _screenState.value = LessonsListScreenStates.Success(it)
            }.onFailure {
                _events.trySend(LessonsListScreenEvents.Error(it.message ?: "Something went wrong"))
            }
        }
    }
}
