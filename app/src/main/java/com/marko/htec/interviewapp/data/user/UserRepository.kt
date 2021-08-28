package com.marko.htec.interviewapp.data.user

import retrofit2.Response
import javax.inject.Inject

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val userService: UserService
) {

    suspend fun getUser(id:Int): Response<User> {
        return userService.getUser(id)
    }

}