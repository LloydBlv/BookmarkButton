package com.example.bookmarkbutton

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.dp
import com.example.bookmarkbutton.components.BookmarkButtonView
import com.example.bookmarkbutton.components.BookmarkState
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode

@GraphicsMode(GraphicsMode.Mode.NATIVE)
@RunWith(RobolectricTestRunner::class)
class BookmarkButtonScreenshotTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun bookmarkedStateSnapshot() {
        composeRule.setContent {
            ScreenshotBox {
                BookmarkButtonView(
                    state = BookmarkState.Bookmarked,
                    onBookmarkClicked = {}
                )
            }
        }

        composeRule
            .onRoot()
            .captureRoboImage()
    }


    @Test
    fun notBookmarkedStateSnapshot() {
        composeRule.setContent {
            ScreenshotBox {
                BookmarkButtonView(
                    state = BookmarkState.NotBookmarked,
                    onBookmarkClicked = {}
                )
            }
        }

        composeRule
            .onRoot()
            .captureRoboImage()
    }

    @Test
    fun togglingStateSnapshot() {
        composeRule.setContent {
            ScreenshotBox {

                BookmarkButtonView(
                    state = BookmarkState.Toggling,
                    onBookmarkClicked = {}
                )
            }
        }
        composeRule
            .onRoot()
            .captureRoboImage()
    }

    @Composable
    private fun ScreenshotBox(
        modifier: Modifier = Modifier,
        content: @Composable BoxScope.() -> Unit
    ) {
        Box(
            modifier = modifier.padding(10.dp),
            content = content
        )
    }
}