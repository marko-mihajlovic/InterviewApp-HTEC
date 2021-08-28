package com.marko.htec.interviewapp.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: Int,
    val name: String,
    val email: String,
)