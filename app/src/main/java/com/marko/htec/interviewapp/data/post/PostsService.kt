package com.marko.htec.interviewapp.data.post

import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
interface PostsService {

    @GET("posts")
    suspend fun getPostResponse(): Response<List<Post>>

}