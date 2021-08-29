package com.marko.htec.interviewapp.util

import com.marko.htec.interviewapp.data.post.Post
import com.marko.htec.interviewapp.data.user.User

/**
 * @author Created by Marko Mihajlovic on 29.8.2021.
 */



val testPosts = arrayListOf(
    Post(3, 2, "eveniet quod temporibus", "reprehenderit quos placeat"),
    Post(7, 3, "ea molestias quasi exercitationem repellat qui ipsa sit aut", "body2"),
    Post(9, 8, "title3", "body3")
)
val testPost = testPosts[0]

val testPostAlone = Post(11, 6, "nesciunt quas odio", "nesciunt quas odio")

val testUsers = arrayListOf(
    User(2,  "Ervin Howell", "Shanna@melissa.tv"),
    User(6,  "name2", "email@test.com2"),
    User(8,  "name3", "email@test.com3")
)
val testUser = testUsers[0]


