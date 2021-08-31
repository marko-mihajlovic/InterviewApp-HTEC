package com.marko.htec.interviewapp.ui

import androidx.navigation.Navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.marko.htec.interviewapp.R
import com.marko.htec.interviewapp.ui.posts.PostsFragmentDirections
import com.marko.htec.interviewapp.util.testPost
import com.marko.htec.interviewapp.util.testUser
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

/**
 * @author Created by Marko Mihajlovic on 31.8.2021.
 */
@HiltAndroidTest
class DetailsFragmentTest {

    private val hiltRule = HiltAndroidRule(this)
    private val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(activityTestRule)

    @Before
    fun navigateToDetails() {
        activityTestRule.scenario.onActivity {
            val action = PostsFragmentDirections.actionPostsToDetails(testPost.id, testUser.id)
            findNavController(it, R.id.nav_host).navigate(action)
        }
    }

    @Test
    fun testDetailsFragment() {
        //click on delete icon
        onView(withId(R.id.delete)).perform(click())

        // dismiss the Dialog
        pressBack()

        //return to posts
        pressBack()
    }

}