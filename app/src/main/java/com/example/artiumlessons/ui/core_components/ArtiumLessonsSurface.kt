package com.example.artiumlessons.ui.core_components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.artiumlessons.ui.theme.ArtiumLessonsTheme

@Composable
fun ArtiumLessonsSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    ArtiumLessonsTheme {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.surface,
            content = content
        )
    }
}