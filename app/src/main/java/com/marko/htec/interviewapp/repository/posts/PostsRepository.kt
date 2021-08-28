package com.marko.htec.interviewapp.repository.posts

import com.marko.htec.interviewapp.model.Post
import com.marko.htec.interviewapp.repository.RemoteApi
import retrofit2.Response

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
class PostsRepository {

    suspend fun getPosts(): Response<List<Post>>{
        return RemoteApi.getService(PostsService::class.java).getPosts()
    }

}