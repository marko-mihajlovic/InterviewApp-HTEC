package com.marko.htec.interviewapp.di

import com.marko.htec.interviewapp.data.AppDatabase
import com.marko.htec.interviewapp.data.user.UserDao
import com.marko.htec.interviewapp.data.user.UserRepository
import com.marko.htec.interviewapp.data.user.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    fun provideUserRepository(userDao: UserDao, userService: UserService): UserRepository {
        return UserRepository(userDao, userService)
    }


    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

}