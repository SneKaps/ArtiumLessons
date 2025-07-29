package com.example.artiumlessons.ui.core_components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artiumlessons.ui.theme.ArtiumLessonsTheme

@Composable
fun NavBar(
    title: String,
    modifier: Modifier = Modifier,
    topBarHeight: Dp = 56.dp,
    titleTextStyle: TextStyle = TextStyle(
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
    ),
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    paddingValues: PaddingValues = PaddingValues(),
    leftSlot: (@Composable RowScope.() -> Unit)? = null,
    rightSlot: (@Composable RowScope.() -> Unit)? = null
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(topBarHeight),
        color = backgroundColor,
        contentColor = contentColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            leftSlot?.invoke(this)

            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary,
                style = titleTextStyle,
                modifier = Modifier.weight(1f)
            )

            rightSlot?.invoke(this)
        }
    }
}

@Preview
@Composable
private fun NavBar_Preview() {
    ArtiumLessonsTheme {
        NavBar(title = "Title")
    }
}

@Preview(name = "Top bar with left icon")
@Composable
private fun NavBar_Preview_Left_And_Right_Slot() {
    ArtiumLessonsTheme {
        NavBar(
            title = "Title",
            leftSlot = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            rightSlot = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        )
    }
}