package com.marko.htec.interviewapp.ui.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marko.htec.interviewapp.data.post.Post
import com.marko.htec.interviewapp.data.post.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository : PostsRepository
) : ViewModel() {


    val postList = MutableLiveData<List<Post>?>()
    val toastMsg = repository.toastMsg
    val isRefreshing = repository.isRefreshing

    init { loadPosts() }

    private fun loadPosts(){
        viewModelScope.launch {
            repository.getPosts().collect {
                postList.postValue(it)
            }
        }
    }

    fun refresh(){
        viewModelScope.launch {
            repository.refreshPosts()
        }
    }
}