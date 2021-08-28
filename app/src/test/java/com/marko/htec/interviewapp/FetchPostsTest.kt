package com.marko.htec.interviewapp

import com.marko.htec.interviewapp.repository.PostsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
class FetchPostsTest {

    private val repository by lazy {
        PostsRepository()
    }


    @Test
    fun fetchPosts() = runBlocking{
        val posts = repository.getPosts().body()
        assertNotNull(posts)

        for (task in posts!!) {
            println("task: ${task.toStringShort()}")
        }
    }

}