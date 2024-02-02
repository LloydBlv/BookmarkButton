package com.example.bookmarkbutton.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

typealias OnClick = () -> Unit

@Composable
fun BookmarkButtonView(
    isBookmarked: Boolean,
    isToggling: Boolean,
    modifier: Modifier = Modifier,
    onBookmarkClicked: OnClick
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onBookmarkClicked,
        enabled = !isToggling,
        content = {
            when {
                isToggling -> CircularProgressIndicator(modifier = Modifier.size(24.dp), strokeWidth = 2.dp)
                isBookmarked -> Text(text = "Bookmarked")
                else -> Text(text = "Bookmark")
            }
        })
}