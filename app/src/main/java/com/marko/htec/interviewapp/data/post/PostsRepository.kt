package com.marko.htec.interviewapp.data.post

import android.content.SharedPreferences
import com.marko.htec.interviewapp.util.logMessage
import com.marko.htec.interviewapp.util.shouldRefreshPosts
import com.marko.htec.interviewapp.util.updateCacheTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject


/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
class PostsRepository @Inject constructor(
    private val postDao: PostDao,
    private val postsService: PostsService,
    private val pref: SharedPreferences,
) {

    /** post */
    suspend fun getPost(postId:Int, forceUpdate: Boolean = false): Flow<Post?>{
        val flow : Flow<Post?> = postDao.getPost(postId)

        if(forceUpdate || flow.firstOrNull() == null)
            refreshPost(postId)

        return flow
    }

    private suspend fun refreshPost(id:Int) {
        try {
            val response = postsService.getPostResponse(id)
            if(response.isSuccessful){
                logMessage("refreshPost().Successful.id: $id")
                response.body()?.let { postDao.insert(it) }
            }else{
                logMessage("refreshPost().Error: ${response.code()}")
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }



    /** posts */
    suspend fun getPosts(): Flow<List<Post>?>{

        if (shouldRefreshPosts(pref))
            refreshPosts()

        return postDao.getPosts()
    }

    suspend fun refreshPosts() {
        try {
            val response = postsService.getPostsResponse()
            if(response.isSuccessful){
                logMessage("refreshPosts().Successful")
                response.body()?.let { postDao.insertAll(it) }
                updateCacheTime(pref)
            }else{
                logMessage("refreshPosts().Error: ${response.code()}")
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }


}