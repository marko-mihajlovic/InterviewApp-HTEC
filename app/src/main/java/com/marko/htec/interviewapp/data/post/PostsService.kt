package com.marko.htec.interviewapp.data.post

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
interface PostsService {

    @GET("posts")
    suspend fun getPostsResponse(): Response<List<Post>>

    @GET("posts/{id}")
    suspend fun getPostResponse(@Path("id") id:Int): Response<Post>

}