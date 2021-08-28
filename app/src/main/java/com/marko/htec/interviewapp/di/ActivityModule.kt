package com.marko.htec.interviewapp.di

import com.marko.htec.interviewapp.adapter.PostsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun providePostsAdapter(): PostsAdapter {
        return PostsAdapter()
    }

}