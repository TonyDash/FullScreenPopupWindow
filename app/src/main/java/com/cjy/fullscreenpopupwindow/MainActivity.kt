package com.cjy.fullscreenpopupwindow

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnNormal.setOnClickListener(this)
        btnFullScreen.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnFullScreen -> {
                val rewordPopWin = RewordPopWin(this)
                rewordPopWin.show()
            }
            R.id.btnNormal -> {
                val view = LayoutInflater.from(this).inflate(R.layout.view_popwin,null)
                val normalPop = PopupWindow(view,WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT)
                normalPop.isClippingEnabled = false
                normalPop.isFocusable = true
                normalPop.isOutsideTouchable = true
                normalPop.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this,R.color.release_half_transparent_bg)))
                normalPop.inputMethodMode = PopupWindow.INPUT_METHOD_NEEDED
                normalPop.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                val ivIcon = view.findViewById<ImageView>(R.id.ivIcon)
                val cslOutside = view.findViewById<ConstraintLayout>(R.id.cslOutside)
                ivIcon.setOnClickListener{ normalPop.dismiss() }
                cslOutside.setOnClickListener { normalPop.dismiss() }
                normalPop.showAtLocation(window.decorView,Gravity.LEFT or Gravity.TOP, 0, 0)
            }
        }
    }
}
