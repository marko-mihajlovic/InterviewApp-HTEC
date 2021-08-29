package com.marko.htec.interviewapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.marko.htec.interviewapp.data.user.UserDao
import com.marko.htec.interviewapp.util.testUser
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Created by Marko Mihajlovic on 29.8.2021.
 */
@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var userDao: UserDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        userDao = database.userDao()
        insertUser()
    }

    private fun insertUser() = runBlocking {
        userDao.insert(testUser)
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetUser() = runBlocking {
        val testUserA = userDao.getUser(testUser.id).first()

        assertThat(testUserA, equalTo(testUser))
    }


}