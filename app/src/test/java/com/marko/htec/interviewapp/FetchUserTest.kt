package com.marko.htec.interviewapp

import com.marko.htec.interviewapp.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
class FetchUserTest {

    private val repository by lazy {
        UserRepository()
    }


    @Test
    fun fetchExistingUserA() = runBlocking{
        val user = repository.getUser(2).body()
        assertNotNull(user)

        println("user: $user")
    }

    @Test
    fun fetchExistingUserB() = runBlocking{
        val user = repository.getUser(5).body()
        assertNotNull(user)

        println("user: $user")
    }

    @Test
    fun fetchNonExistingUser() = runBlocking{
        val user = repository.getUser(-2).body()
        assertNull(user)

        println("NonExistingUser: $user")
    }


}