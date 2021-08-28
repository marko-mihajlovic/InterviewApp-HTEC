package com.marko.htec.interviewapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marko.htec.interviewapp.model.Post
import com.marko.htec.interviewapp.repository.posts.PostsRepository
import kotlinx.coroutines.launch

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
class PostsViewModel : ViewModel() {

    private val repository by lazy {
        PostsRepository()
    }


    val postList : MutableLiveData<List<Post>> by lazy {
        MutableLiveData<List<Post>>().also {
            loadListAsync(it)
        }
    }

    private fun loadListAsync(it : MutableLiveData<List<Post>>){
        viewModelScope.launch {
            val response = repository.getPosts()
            if(response.isSuccessful){
                it.postValue(response.body())
            }else{
                it.postValue(mutableListOf())
            }
        }
    }

}