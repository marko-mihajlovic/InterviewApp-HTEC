package com.marko.htec.interviewapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.marko.htec.interviewapp.data.AppDatabase
import com.marko.htec.interviewapp.data.post.Post
import com.marko.htec.interviewapp.data.post.PostDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
@RunWith(AndroidJUnit4::class)
class PostDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var postDao: PostDao
    private val postA = Post(1, 2, "title1", "body1")
    private val postB = Post(2, 2, "title2", "body2")
    private val postC = Post(3, 4, "title3", "body3")


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
        postDao.insertAll(listOf(postA, postB, postC))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test fun testGetPlants() = runBlocking {
        val plantList = postDao.getPosts().first()
        assertThat(plantList.size, equalTo(3))

        assertThat(plantList[0], equalTo(postA))
        assertThat(plantList[1], equalTo(postB))
        assertThat(plantList[2], equalTo(postC))
    }

    @Test fun testGetPlant() = runBlocking {
        assertThat(postDao.getPost(postA.id).first(), equalTo(postA))
    }

}