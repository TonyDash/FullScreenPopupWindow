package com.cjy.fullscreenpopupwindow.popWinUtil

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.PopupWindow
import androidx.core.content.ContextCompat
import com.cjy.fullscreenpopupwindow.R
import com.cjy.fullscreenpopupwindow.UIUtils
import java.util.*

abstract class PopwinAbstract(context: Context) {
    protected var mInflater:LayoutInflater = LayoutInflater.from(context)
    private lateinit var mPop:PopWindowCompat
    protected var mContext: Context = context
    private lateinit var mParent: View
    private var mContentView: View
    protected lateinit var mSelect:IPopSelect
    private var mAnimStyle:Int=0

    init {
        mContentView = getView()
        initPopWindow(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT)
    }

    abstract fun getView():View

    fun setParent(parent:View){
        mParent = parent
    }

    protected fun initPopWindow(width:Int,height:Int){
        mPop = PopWindowCompat(mContentView,width, height)
        mPop.animationStyle = R.style.PopWindowAnimation
        mPop.isClippingEnabled = false
        mPop.isFocusable = true
        mPop.isOutsideTouchable = true
        mPop.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(mContext,R.color.release_half_transparent_bg)))
        mPop.inputMethodMode = PopupWindow.INPUT_METHOD_NEEDED
        mPop.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        mPop.update()
    }

    fun setInputMethodMode(inputMode:Int){
        mPop.inputMethodMode = inputMode
        mPop.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
    }

    fun popupInputMethodWindow(delay:Long){
        var timer = Timer()
        timer.schedule(object :TimerTask(){
            override fun run() {
                val inputMethodManager:InputMethodManager = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS)
            }
        },delay)
    }

    fun show(){
        val activity = UIUtils.scanForActivity(mContext)
        activity?.run {
            if (!this@PopwinAbstract::mParent.isInitialized) {
                mParent = activity.window.decorView
            }
            if (this@PopwinAbstract::mParent.isInitialized && !mPop.isShowing) {
                mPop.showAtLocation(mParent, Gravity.LEFT or Gravity.TOP, 0, 0)
            }
        }
    }

    fun setFocusable(focusable: Boolean) {
        if (this::mPop.isInitialized) {
            mPop.isFocusable = focusable
        }
    }

    fun setTouchable(touchable: Boolean) {
        if (this::mPop.isInitialized) {
            mPop.isTouchable = touchable
        }
    }

    open fun afterShow(
        anchor: View?,
        xOff: Int,
        yOff: Int,
        gravity: Int
    ) {
    }

    open fun dismiss() {
        if (this::mPop.isInitialized && mPop.isShowing) {
            mPop.dismiss()
        }
    }

    protected open fun isShowing(): Boolean {
        return (this::mPop.isInitialized && mPop.isShowing)
    }
}