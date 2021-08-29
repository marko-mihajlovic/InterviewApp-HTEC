package com.marko.htec.interviewapp

import com.marko.htec.interviewapp.util.CACHE_TIMEOUT
import com.marko.htec.interviewapp.util.shouldRefreshPosts
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Created by Marko Mihajlovic on 29.8.2021.
 */
class RefreshTest {

    private val curTime = 1630273132879
    private val validCacheTime = curTime - CACHE_TIMEOUT + 1
    private val invalidCacheTime = curTime - CACHE_TIMEOUT - 1

    @Test
    fun testRefresh1(){
        val b1 = shouldRefreshPosts(validCacheTime, curTime)
        // cache is valid, no need for refresh, $b1 must be false
        assertEquals(b1, false)
    }

    @Test
    fun testRefresh2(){
        val b2 = shouldRefreshPosts(invalidCacheTime, curTime)
        // cache is invalid, must refresh, $b2 must be true
        assertEquals(b2, true)
    }

}