package com.marko.htec.interviewapp.util

import android.util.Log

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
private const val showLog = true
fun logMessage(logMsg: String) {
    if (showLog)
        Log.d("com.marko.htec.interviewapp_logMessage", logMsg)
}

