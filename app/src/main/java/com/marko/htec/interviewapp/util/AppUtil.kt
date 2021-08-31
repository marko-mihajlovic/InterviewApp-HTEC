package com.marko.htec.interviewapp.util

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Created by Marko Mihajlovic on 31.8.2021.
 */
fun showToast(context: Context?, msg : String?, duration:Int = Toast.LENGTH_SHORT){
    if(context == null || msg.isNullOrBlank())
        return

    Toast.makeText(context, msg, duration).show()
}

fun confStyle(activity : Activity?, backBtnVisible: Boolean, @StringRes titleRes : Int){
    toggleBackBtn(activity, backBtnVisible)
    changeBarTitle(activity, titleRes)
}

private fun toggleBackBtn(activity : Activity?, visible: Boolean){
    if(activity is AppCompatActivity) {
        activity.supportActionBar?.setDisplayShowHomeEnabled(visible)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(visible)
    }
}

private fun changeBarTitle(activity : Activity?, @StringRes titleRes : Int){
    if(activity is AppCompatActivity) {
        activity.supportActionBar?.title = activity.getString(titleRes)
    }
}