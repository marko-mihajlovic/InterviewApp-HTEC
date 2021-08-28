package com.marko.htec.interviewapp.repository

import com.marko.htec.interviewapp.model.Post
import retrofit2.Response

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
class PostsRepository {

    suspend fun getPosts(): Response<List<Post>>{
        return RemoteApi.getService(PostsService::class.java).getPosts()
    }

}