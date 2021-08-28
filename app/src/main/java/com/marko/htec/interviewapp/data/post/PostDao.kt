package com.marko.htec.interviewapp.data.post

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
@Dao
interface PostDao {

    @Transaction
    @Query("SELECT * FROM posts")
    fun getPosts(): Flow<List<Post>>

    @Query("SELECT * FROM posts WHERE id = :postId LIMIT 1")
    fun getPost(postId : Int): Flow<Post>

 /*   @Transaction
    @Query("SELECT * FROM posts")
    fun getPostsAndUsers(): Flow<List<PostAndUser>>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(post: List<Post>?): List<Long>

}