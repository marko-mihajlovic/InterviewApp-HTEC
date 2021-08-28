package com.marko.htec.interviewapp.util

import android.content.Context
import android.content.SharedPreferences

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
const val prefKey = "appPref"
const val timeWhenCached = "timeWhenCached"
const val CACHE_TIMEOUT = 5 * 60 * 1000

fun getSharedPref(context : Context): SharedPreferences {
    return context.getSharedPreferences(prefKey, Context.MODE_PRIVATE)
}

fun updateCacheTime(pref : SharedPreferences){
    pref.edit().putLong(timeWhenCached, System.currentTimeMillis()).apply()
}

fun shouldRefresh(pref : SharedPreferences): Boolean {
    val timeWhenCached = pref.getLong(timeWhenCached, 0)
    val time: Long = System.currentTimeMillis() - timeWhenCached
    return time > CACHE_TIMEOUT
}