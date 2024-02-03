package com.example.bookmarkbutton.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookmarkbutton.components.BookmarkState.*

typealias OnClick = () -> Unit

sealed interface BookmarkState {
    data object Bookmarked : BookmarkState
    data object NotBookmarked : BookmarkState
    data object Toggling : BookmarkState
}

@Composable
fun BookmarkButtonView(
    state: BookmarkState,
    modifier: Modifier = Modifier,
    onBookmarkClicked: OnClick
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onBookmarkClicked,
        enabled = state != Toggling,
        content = {
            when(state) {
                is Toggling -> CircularProgressIndicator(modifier = Modifier.size(24.dp), strokeWidth = 2.dp)
                is Bookmarked -> Text(text = "Bookmarked", fontSize = 33.sp)
                is NotBookmarked -> Text(text = "Bookmark", fontSize = 33.sp)
            }
        })
}