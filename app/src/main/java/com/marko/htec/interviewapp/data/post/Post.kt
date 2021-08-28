package com.marko.htec.interviewapp.data.post

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
@Entity(tableName = "posts")
data class Post(
    @PrimaryKey val id: Int,
    val userId: Int,
    val title: String = "",
    val body: String = "",
) {
    fun toStringShort(): String {
        return "Post(id=$id, userId=$userId, title=$title)"
    }
}