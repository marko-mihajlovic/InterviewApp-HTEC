package com.marko.htec.interviewapp.di

import android.content.SharedPreferences
import com.marko.htec.interviewapp.data.AppDatabase
import com.marko.htec.interviewapp.data.post.PostDao
import com.marko.htec.interviewapp.data.post.PostsRepository
import com.marko.htec.interviewapp.data.post.PostsService
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
object PostModule {

    @Provides
    fun providePostsRepository(postDao: PostDao, postsService: PostsService, pref: SharedPreferences): PostsRepository {
        return PostsRepository(postDao, postsService, pref)
    }

    @Provides
    fun providePostsService(retrofit: Retrofit): PostsService {
        return retrofit.create(PostsService::class.java)
    }


    @Provides
    fun providePostDao(appDatabase: AppDatabase): PostDao {
        return appDatabase.postDao()
    }



}