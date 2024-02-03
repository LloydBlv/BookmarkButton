package com.example.bookmarkbutton

import android.content.Context
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.bookmarkbutton.components.BookmarkButtonView
import com.example.bookmarkbutton.components.BookmarkState
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.shadows.ShadowLog


@RunWith(AndroidJUnit4::class)
class BookmarkButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Before
    @Throws(Exception::class)
    fun setUp() {
        ShadowLog.stream = System.out // Redirect Logcat to console
    }

    private val bookmarkTextNode: SemanticsNodeInteraction
        get() = composeTestRule.onNodeWithTag("bookmark_text_tag", useUnmergedTree = true)
    private val togglingProgressNode: SemanticsNodeInteraction
        get() = composeTestRule.onNodeWithTag("progress_tag", useUnmergedTree = true)

    @Test
    fun `when bookmarked text is set correctly and progress not shown`() {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        composeTestRule.setContent {
            BookmarkButtonView(
                modifier = Modifier,
                state = BookmarkState.Bookmarked,
                onBookmarkClicked = {}
            )
        }
        togglingProgressNode.assertIsNotDisplayed()
        bookmarkTextNode.assertTextEquals(context.getString(R.string.bookmarked))
    }
    @Test
    fun `when NOT bookmarked text is set correctly and progress not shown`() {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        composeTestRule.setContent {
            BookmarkButtonView(
                modifier = Modifier,
                state = BookmarkState.NotBookmarked,
                onBookmarkClicked = {}
            )
        }
        togglingProgressNode.assertIsNotDisplayed()
        bookmarkTextNode.assertTextEquals(context.getString(R.string.bookmark))
    }

    @Test
    fun `when toggling progress is shown`() {
        composeTestRule.setContent {
            BookmarkButtonView(
                modifier = Modifier,
                state = BookmarkState.Toggling,
                onBookmarkClicked = {}
            )
        }
        togglingProgressNode.assertIsDisplayed()
        bookmarkTextNode.assertIsNotDisplayed()
    }

    @Test
    fun `when toggling bookmark button is not clickable`() {
        composeTestRule.run {
            setContent {
                BookmarkButtonView(
                    modifier = Modifier,
                    state = BookmarkState.Toggling,
                    onBookmarkClicked = {}
                )
            }
            togglingProgressNode.assertIsDisplayed()
            onNodeWithTag("bookmark_button_tag").assertIsNotEnabled()
            bookmarkTextNode.assertIsNotDisplayed()
        }

    }

}