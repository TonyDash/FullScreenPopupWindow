package com.cjy.fullscreenpopupwindow.popWinUtil

import android.view.View

interface IPopSelect {
    fun onItemSelect(pop:PopwinAbstract, v: View,position:Int)
}