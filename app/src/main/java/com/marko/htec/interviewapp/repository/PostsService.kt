package com.marko.htec.interviewapp.repository

import com.marko.htec.interviewapp.model.Post
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
interface PostsService {

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

}