package com.marko.htec.interviewapp

import com.marko.htec.interviewapp.data.post.PostsService
import com.marko.htec.interviewapp.repository.TestRemoteApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
class FetchPostsTest{

    private val postsService = TestRemoteApi.getService(PostsService::class.java)

    @Test
    fun fetchPosts() = runBlocking{
        val posts = postsService.getPostsResponse().body()
        assertNotNull(posts)

        for (task in posts!!) {
            println("task: ${task.toStringShort()}")
        }
    }

}