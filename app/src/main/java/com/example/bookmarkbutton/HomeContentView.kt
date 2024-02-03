package com.example.bookmarkbutton

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookmarkbutton.components.BookmarkButtonView
import com.example.bookmarkbutton.components.BookmarkState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@Composable
internal fun HomeContentView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        var bookmarked by rememberSaveable { mutableStateOf(false) }
        var toggling by rememberSaveable { mutableStateOf(false) }
        val scope = rememberCoroutineScope()
        BookmarkButtonView(
            modifier = Modifier
                .width(300.dp)
                .height(200.dp),
            state = when {
                toggling -> BookmarkState.Toggling
                bookmarked -> BookmarkState.Bookmarked
                else -> BookmarkState.NotBookmarked
            },
            onBookmarkClicked = {
                //Simulate toggling behaviour
                scope.launch {
                    toggling = true
                    delay(1.seconds)
                    toggling = false
                    bookmarked = !bookmarked
                }
            })
    }
}

