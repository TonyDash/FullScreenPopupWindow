package com.cjy.fullscreenpopupwindow.popWinUtil

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow

class PopWindowCompat(contentView: View?, width: Int, height: Int) :
    PopupWindow(contentView, width, height) {

    override fun showAtLocation(parent: View?, gravity: Int, x: Int, y: Int) {
        super.showAtLocation(parent, gravity, x, y)
        when{
            Build.VERSION.SDK_INT < 24->super.showAtLocation(parent,gravity,x,y)
            height == ViewGroup.LayoutParams.WRAP_CONTENT->super.showAtLocation(parent,gravity,x,y)
            else->if (contentView.context is Activity)run {

            }
        }
    }
}