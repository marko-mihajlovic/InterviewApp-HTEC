package com.marko.htec.interviewapp.repository.user

import com.marko.htec.interviewapp.model.User
import com.marko.htec.interviewapp.repository.RemoteApi
import retrofit2.Response

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
class UserRepository {

    suspend fun getUser(id:Int): Response<User> {
        return RemoteApi.getService(UserService::class.java).getUser(id)
    }

}