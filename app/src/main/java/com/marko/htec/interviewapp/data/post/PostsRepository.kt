package com.marko.htec.interviewapp.data.post

import android.content.SharedPreferences
import com.marko.htec.interviewapp.util.logMessage
import com.marko.htec.interviewapp.util.shouldRefresh
import com.marko.htec.interviewapp.util.updateCacheTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
class PostsRepository @Inject constructor(
    private val postDao: PostDao,
    private val postsService: PostsService,
    private val pref: SharedPreferences,
) {

    fun getPosts(forceUpdate: Boolean): Flow<List<Post>>{
        refreshPostsAsync(forceUpdate)
        return postDao.getPosts()
    }

    private val ioScope by lazy { CoroutineScope(SupervisorJob() + Dispatchers.Default) }
    fun refreshPostsAsync(forceUpdate: Boolean){
        ioScope.launch {
            refreshPosts(forceUpdate)
        }
    }

    private suspend fun refreshPosts(forceUpdate: Boolean) {
        if (forceUpdate || shouldRefresh(pref)) {

            updateCacheTime(pref)
            val response = postsService.getPostResponse()
            if(response.isSuccessful){
                logMessage("refreshPosts().Successful")
                postDao.insertAll(response.body())
            }else{
                logMessage("refreshPosts().Error: ${response.code()}")
            }
        }
    }


}