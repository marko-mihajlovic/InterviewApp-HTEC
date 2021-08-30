package com.marko.htec.interviewapp.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marko.htec.interviewapp.data.post.Post
import com.marko.htec.interviewapp.data.post.PostsRepository
import com.marko.htec.interviewapp.data.user.User
import com.marko.htec.interviewapp.data.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Created by Marko Mihajlovic on 29.8.2021.
 */
@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val postsRepository: PostsRepository,
    private val userRepository: UserRepository,
) : ViewModel() {


    private val postId: Int = savedStateHandle.get<Int>("postId")!!
    private val userId: Int = savedStateHandle.get<Int>("userId")!!

    val post = MutableLiveData<Post?>()
    val user = MutableLiveData<User?>()

    init {
        loadPost()
        loadUser()
    }

    private fun loadPost(){
        viewModelScope.launch {
            postsRepository.getPost(postId, ).collect {
                post.postValue(it)
            }
        }
    }


    private fun loadUser(){
        viewModelScope.launch {
            userRepository.getUser(userId, ).collect {
                user.postValue(it)
            }
        }
    }

    fun deletePost(){
        viewModelScope.launch {
            post.value?.let { postsRepository.deletePost(it) }
        }
    }

}