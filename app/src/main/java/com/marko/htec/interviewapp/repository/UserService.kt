package com.marko.htec.interviewapp.repository

import com.marko.htec.interviewapp.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
interface UserService {

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id:Int): Response<User>

}