package com.cjy.fullscreenpopupwindow

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

class UIUtils {
    companion object {
        fun scanForActivity(context: Context): Activity? {
            return when (context is Activity) {
                true -> context
                false -> {
                    if (context is ContextWrapper)
                        scanForActivity(context.baseContext)
                    else
                        null
                }
            }
        }
    }
}