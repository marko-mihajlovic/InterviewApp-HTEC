package com.marko.htec.interviewapp.data.user

import com.marko.htec.interviewapp.util.logMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val userService: UserService
) {

    suspend fun getUser(id:Int, forceUpdate: Boolean = false): Flow<User?> {
        val flow : Flow<User?> = userDao.getUser(id)

        if(forceUpdate || flow.firstOrNull() == null)
            refreshUser(id)

        return flow
    }

    private suspend fun refreshUser(id:Int) {
        try {
            val response = userService.getUserResponse(id)
            if(response.isSuccessful){
                logMessage("refreshUser().Successful.id: $id")
                response.body()?.let { userDao.insert(it) }
            }else{
                logMessage("refreshUser().Error: ${response.code()}")
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

}