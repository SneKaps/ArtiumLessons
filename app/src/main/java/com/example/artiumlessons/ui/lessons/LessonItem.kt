package com.example.artiumlessons.ui.lessons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.artiumlessons.data.model.Lesson

@Composable
fun LessonItem(
    lesson: Lesson,
    onClick: (Lesson) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(lesson) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(lesson.lessonImageUrl),
                contentDescription = "Lesson Thumbnail",
                modifier = Modifier.size(120.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.padding(horizontal = 16.dp))

            Column {
                Text(
                    text = lesson.lessonTitle ?: "",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = lesson.mentorName ?: "",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
