package com.marko.htec.interviewapp

import com.marko.htec.interviewapp.data.user.UserService
import com.marko.htec.interviewapp.repository.TestRemoteApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
class FetchUserTest {

    private val userService = TestRemoteApi.getService(UserService::class.java)

    @Test
    fun fetchExistingUserA() = runBlocking{
        val user = userService.getUserResponse(2).body()
        assertNotNull(user)

        println("user: $user")
    }

    @Test
    fun fetchExistingUserB() = runBlocking{
        val user = userService.getUserResponse(5).body()
        assertNotNull(user)

        println("user: $user")
    }

    @Test
    fun fetchNonExistingUser() = runBlocking{
        val user = userService.getUserResponse(-2).body()
        assertNull(user)

        println("NonExistingUser: $user")
    }


}