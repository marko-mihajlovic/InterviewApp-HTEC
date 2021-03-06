package com.marko.htec.interviewapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.marko.htec.interviewapp.data.post.PostDao
import com.marko.htec.interviewapp.util.testPost
import com.marko.htec.interviewapp.util.testPosts
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Created by Marko Mihajlovic on 30.8.2021.
 */
@RunWith(AndroidJUnit4::class)
class DeletePostDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var postDao: PostDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        postDao = database.postDao()
        insertAll()
    }

    private fun insertAll() = runBlocking {
        postDao.insertAll(testPosts)
        postDao.delete(testPost)
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testDeletedPost() = runBlocking {
        val postList = postDao.getPosts().first()
        assertThat(postList.size, equalTo(testPosts.size - 1)) // - 1 because we deleted testPost
    }

}