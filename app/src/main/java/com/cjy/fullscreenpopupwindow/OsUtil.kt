package com.cjy.fullscreenpopupwindow

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
import android.graphics.Rect
import android.os.Build
import android.util.DisplayMetrics
import android.view.Display
import android.view.View
import android.view.Window

class OsUtil {

    companion object{
        private fun getScreenHeight(activity: Activity):Int{
            val display = activity.windowManager.defaultDisplay
            var realHeight:Int
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1) {
                val metrics = DisplayMetrics()
                display.getRealMetrics(metrics)
                realHeight = metrics.heightPixels
            }else{
                val mGetRawH = Display::class.java.getMethod("getRawHeight")
                realHeight = mGetRawH.invoke(display) as Int
            }
            return realHeight
        }

        private fun getScreenWidth(activity: Activity):Int{
            val display = activity.windowManager.defaultDisplay
            var realWidth:Int
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1) {
                val metrics = DisplayMetrics()
                display.getRealMetrics(metrics)
                realWidth = metrics.widthPixels
            }else{
                val mGetRawH = Display::class.java.getMethod("getRawWidth")
                realWidth = mGetRawH.invoke(display) as Int
            }
            return realWidth
        }

        /**
         * 状态栏高度
         */
        fun getStatusBarHeight(activity: Activity):Int{
            val resourceId = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
            return activity.resources.getDimensionPixelSize(resourceId)
        }

        /**
         * 获取虚拟键高度（无论是否隐藏）
         */
        fun getNavigationBarHeight(context:Context):Int{
            var result:Int
            val resources = context.resources
            val resourceId = resources.getIdentifier(if (isPortrait(context))"navigation_bar_height" else "navigation_bar_height_landscape", "dimen", "android")
            if (resourceId>0)
                result = context.resources.getDimensionPixelSize(resourceId)
            else result = 0
            return result
        }

        /**
         * 手机具有底部导航栏时，底部导航栏是否可见
         */
        private fun isNavigationBarVisible(activity: Activity):Boolean{
            var show = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
                val display = activity.window.windowManager.defaultDisplay
                val point = Point()
                display.getRealSize(point)
                val decorView = activity.window.decorView
                val conf = activity.resources.configuration
                if (Configuration.ORIENTATION_LANDSCAPE == conf.orientation){
                    val contentView:View = decorView.findViewById(R.id.content)
                    show = (point.x!=contentView.width)
                }else{
                    val rect = Rect()
                    decorView.getWindowVisibleDisplayFrame(rect)
                    show = (rect.bottom!=point.y)
                }
            }
            return show
        }

        /**
         * 检测是否具有底部导航栏
         */
        private fun checkDeviceHasNavigationBar(activity: Activity):Boolean{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                val windowManager = activity.windowManager
                val display = windowManager.defaultDisplay
                val realDisplayMetrics = DisplayMetrics()
                display.getRealMetrics(realDisplayMetrics)
                val realHeight = realDisplayMetrics.heightPixels
                val realWidth = realDisplayMetrics.widthPixels
                val displayMetrics = DisplayMetrics()
                display.getMetrics(displayMetrics)
                val displayHeight = displayMetrics.heightPixels
                val displayWidth = displayMetrics.widthPixels
                return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0
            }else{
                var hasNavigationBar:Boolean
                val resources = activity.resources
                val id = resources.getIdentifier("config_showNavigationBar", "bool", "android")
                if (id>0){
                    hasNavigationBar = resources.getBoolean(id)
                }
                val systemPropertiesClass = Class.forName("android.os.SystemProperties")
                val m = systemPropertiesClass.getMethod("get",String.javaClass)
                val navBarOverride:String = m.invoke(systemPropertiesClass,"qemu.hw.mainkeys") as String
                hasNavigationBar = !"1".equals(navBarOverride)
                return hasNavigationBar
            }
        }

        /**
         * 获取当前底部栏高度（隐藏后高度为0）
         */
        private fun getCurrentNavigationBarHeight(activity: Activity):Int{
            var navigationBarHeight = 0
            val resources = activity.resources
            val resourceId = resources.getIdentifier(if (isPortrait(activity))"navigation_bar_height" else "navigation_bar_height_landscape", "dimen", "android")
            if (resourceId>0&& checkDeviceHasNavigationBar(activity)&& isNavigationBarVisible(activity)){
                navigationBarHeight = resources.getDimensionPixelSize(resourceId)
            }
            return navigationBarHeight

        }
        private fun isPortrait(context: Context):Boolean{
            return context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        }

        /**
         * 获取可用屏幕高度，排除虚拟键
         */
        fun getContentHeight(activity: Activity):Int{
            return getScreenHeight(activity) - getCurrentNavigationBarHeight(activity)
        }

        /**
         * 标题栏高度，如果隐藏了标题栏则返回0
         */
        fun getTitleHeight(activity: Activity):Int{
            return activity.window.findViewById<View>(Window.ID_ANDROID_CONTENT).top
        }
    }
}