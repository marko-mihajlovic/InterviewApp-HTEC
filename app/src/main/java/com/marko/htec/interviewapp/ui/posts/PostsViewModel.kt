package com.marko.htec.interviewapp.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.marko.htec.interviewapp.data.post.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository : PostsRepository
) : ViewModel() {


    val postList = repository.getPosts(false).asLiveData()

    fun refresh(forceUpdate: Boolean){
        repository.refreshPostsAsync(forceUpdate)
    }
}