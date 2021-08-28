package com.marko.htec.interviewapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marko.htec.interviewapp.data.post.Post
import com.marko.htec.interviewapp.data.post.PostDao
import com.marko.htec.interviewapp.data.user.User
import com.marko.htec.interviewapp.data.user.UserDao
import com.marko.htec.interviewapp.util.DATABASE_NAME

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
@Database(entities = [Post::class, User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
    abstract fun userDao(): UserDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }
}