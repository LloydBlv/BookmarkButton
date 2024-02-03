package com.example.bookmarkbutton.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookmarkbutton.R
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
        modifier = modifier.testTag("bookmark_button_tag"),
        onClick = onBookmarkClicked,
        enabled = state != Toggling,
        content = {
            when(state) {
                is Toggling -> CircularProgressIndicator(modifier = Modifier
                    .size(24.dp)
                    .testTag("progress_tag"), strokeWidth = 2.dp)
                is Bookmarked -> BookmarkText(modifier = Modifier, text = stringResource(R.string.bookmarked))
                is NotBookmarked -> BookmarkText(modifier = Modifier, text = stringResource(R.string.bookmark))
            }
        })
}

@Composable
private fun BookmarkText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, fontSize = 33.sp, modifier = modifier.testTag("bookmark_text_tag"))
}