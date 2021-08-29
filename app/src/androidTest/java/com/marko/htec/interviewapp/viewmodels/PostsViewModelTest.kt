package com.marko.htec.interviewapp.viewmodels


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.platform.app.InstrumentationRegistry
import com.marko.htec.interviewapp.data.AppDatabase
import com.marko.htec.interviewapp.data.post.PostsRepository
import com.marko.htec.interviewapp.ui.posts.PostsViewModel
import com.marko.htec.interviewapp.util.MainCoroutineRule
import com.marko.htec.interviewapp.util.getValue
import com.marko.htec.interviewapp.util.logMessage
import com.marko.htec.interviewapp.util.runBlockingTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import javax.inject.Inject


/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
@Suppress("BlockingMethodInNonBlockingContext")
@ExperimentalCoroutinesApi
@HiltAndroidTest
class PostsViewModelTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var viewModel: PostsViewModel
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

    @Before
    fun setUp() {
        hiltRule.inject()

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

        viewModel = PostsViewModel(postsRepository)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun testPostListSize() = coroutineRule.runBlockingTest {
        val size = getValue(viewModel.postList)?.size
        logMessage("postListSize: $size")

        assertThat(size, equalTo(100))
    }

}
