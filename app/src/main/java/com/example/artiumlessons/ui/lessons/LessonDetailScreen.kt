package com.example.artiumlessons.ui.lessons

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.artiumlessons.data.model.Lesson
import com.example.artiumlessons.ui.core_components.NavBar

@Composable
fun LessonDetailRoute(lesson: Lesson?, navController: NavController) {
    LessonDetailScreen(
        lesson = lesson ?: return,
        onBackPressed = { navController.navigateUp() },
        modifier = Modifier.fillMaxSize()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LessonDetailScreen(
    lesson: Lesson,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {}
) {

    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            lesson.videoUrl?.let { url ->
                val mediaItem = MediaItem.fromUri(Uri.parse(url))
                setMediaItem(mediaItem)
                prepare()
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    var notesExpanded by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                NavBar(
                    title = "Lessons Details",
                    leftSlot = {
                        IconButton(onClick = onBackPressed) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            item {
                AndroidView(
                    factory = { ctx ->
                        PlayerView(ctx).apply {
                            player = exoPlayer
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
            }

            item {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = lesson.lessonTitle ?: "Lesson Title Not Available",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Mentor: ${lesson.mentorName ?: "Unknown"}",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }

            item {
                ListItem(
                    headlineContent = { Text("Lesson Notes", fontWeight = FontWeight.Bold) },
                    supportingContent = { Text("Tap to ${if (notesExpanded) "collapse" else "expand"} notes") },
                    leadingContent = {
                        Icon(
                            imageVector = if (notesExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Notes toggle"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { notesExpanded = !notesExpanded }
                        .padding(16.dp)
                )
            }

            if (notesExpanded) {
                val notes = listOf(
                    "• Practice the alap section slowly with proper breath control",
                    "• Focus on the transition between notes in the gat section",
                    "• Record yourself and compare with the reference track",
                    "• Pay attention to the taans in the final movement"
                )
                items(items = notes, key = null) { note ->
                    Text(
                        text = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        // Submit button
        Button(
            onClick = { showBottomSheet = true },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("Submit", color = Color.White)
        }

        // Bottom sheet
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Submission Form", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("This is where you would add your submission content.")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { showBottomSheet = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Close")
                    }
                }
            }
        }
    }
}
