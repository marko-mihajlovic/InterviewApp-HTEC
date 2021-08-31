package com.marko.htec.interviewapp.ui

import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.marko.htec.interviewapp.R
import com.marko.htec.interviewapp.adapter.PostsAdapter
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain


/**
 * @author Created by Marko Mihajlovic on 31.8.2021.
 */
@HiltAndroidTest
class PostsFragmentTest {

    private val hiltRule = HiltAndroidRule(this)
    private val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(activityTestRule)

    @Before
    fun navigateToPosts() {
        activityTestRule.scenario.onActivity {
            findNavController(it, R.id.nav_host).navigate(R.id.posts_fragment)
        }
    }

    @Test
    fun testPostsFragment() {
        //test refreshing
        onView(withId(R.id.listView)).perform(swipeDown())

        //wait for data to load
        Thread.sleep(5000)

        var size = 0
        onView(withId(R.id.listView)).check(matches(object : TypeSafeMatcher<View>(){
            override fun matchesSafely(view: View): Boolean {
                size = (view as RecyclerView).adapter!!.itemCount
                return true
            }
            override fun describeTo(description: Description?) {}
        }))

        onView(withId(R.id.listView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<PostsAdapter.RowPost>(size-1, click()))
    }



}