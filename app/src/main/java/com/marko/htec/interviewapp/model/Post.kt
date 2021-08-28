package com.marko.htec.interviewapp.model

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
) {
    fun toStringShort(): String {
        return "Post(id=$id, userId=$userId, title=$title)"
    }
}