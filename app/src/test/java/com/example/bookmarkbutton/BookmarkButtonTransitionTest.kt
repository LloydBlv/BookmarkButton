package com.example.bookmarkbutton

import android.content.Context
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.shadows.ShadowLog
import kotlin.time.Duration.Companion.seconds


@RunWith(AndroidJUnit4::class)
class BookmarkButtonTransitionTest {
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
    fun `when bookmark clicked text is set correctly and progress displayed and hides correctly`() {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        composeTestRule.setContent { HomeContentView() }

        composeTestRule.mainClock.autoAdvance = false //Pause the clock so we can capture all states

        togglingProgressNode.assertIsNotDisplayed() //Initially no progressbar is not visible
        bookmarkTextNode.assertTextEquals(context.getString(R.string.bookmark)) //Initial state is NOT bookmarked

        composeTestRule.onNodeWithTag("bookmark_button_tag")
            .performClick() //Bookmark button is clicked

        togglingProgressNode.isDisplayed() // toggling progressbar is displayed
        bookmarkTextNode.isNotDisplayed() // bookmark button becomes invisible

        composeTestRule.mainClock.advanceTimeBy(1.seconds.inWholeMilliseconds) //Manually advance the clock by 1 second
        togglingProgressNode.assertIsNotDisplayed() //We're not toggling anymore
        bookmarkTextNode.assertTextEquals(context.getString(R.string.bookmarked)) //State is Bookmarked now
    }


}