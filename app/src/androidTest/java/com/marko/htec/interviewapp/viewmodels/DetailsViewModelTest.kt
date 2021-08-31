package com.marko.htec.interviewapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asFlow
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.platform.app.InstrumentationRegistry
import com.marko.htec.interviewapp.data.AppDatabase
import com.marko.htec.interviewapp.data.post.PostsRepository
import com.marko.htec.interviewapp.data.user.UserRepository
import com.marko.htec.interviewapp.ui.details.DetailsViewModel
import com.marko.htec.interviewapp.util.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import javax.inject.Inject

/**
 * @author Created by Marko Mihajlovic on 29.8.2021.
 */
@Suppress("BlockingMethodInNonBlockingContext")
@ExperimentalCoroutinesApi
@HiltAndroidTest
class DetailsViewModelTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var viewModel: DetailsViewModel
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val coroutineRule = MainCoroutineRule()

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)
        .around(coroutineRule)

    @Inject
    lateinit var postsRepository: PostsRepository

    @Inject
    lateinit var userRepository: UserRepository


    @Before
    fun setUp() {
        hiltRule.inject()

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

        val savedStateHandle: SavedStateHandle = SavedStateHandle().apply {
            set("postId", testPost.id)
            set("userId", testUser.id)
        }
        viewModel = DetailsViewModel(savedStateHandle, postsRepository, userRepository)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun testPostVM() = coroutineRule.runBlockingTest {
        assertThat(getValue(viewModel.post)!!.id, equalTo(testPost.id))
    }

    @Test
    fun testUserVM() = coroutineRule.runBlockingTest {
        assertThat(getValue(viewModel.user)!!.id, equalTo(testUser.id))
    }

    @Test
    fun testDeletingPost() = runBlocking {
        val flow = viewModel.post.asFlow()

        var hasDeleted = false
        flow.takeWhile { it == null && hasDeleted }.collect {
            if(hasDeleted){
                assertThat(it, equalTo(null))
            }

            if(it!=null){
                viewModel.deletePost()
                hasDeleted = true
            }
        }
    }

}