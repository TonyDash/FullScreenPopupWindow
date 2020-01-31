package com.cjy.fullscreenpopupwindow

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.cjy.fullscreenpopupwindow.popWinUtil.PopwinAbstract

class RewordPopWin(context: Context) : PopwinAbstract(context), View.OnClickListener {

    private lateinit var ivIcon: ImageView
    private lateinit var cslOutSide: ConstraintLayout
    private lateinit var rewordListener: RewordListener

    override fun getView(): View {
        val view: View = mInflater.inflate(R.layout.view_popwin, null)
        ivIcon = view.findViewById(R.id.ivIcon)
        cslOutSide = view.findViewById(R.id.cslOutside)
        ivIcon.setOnClickListener(this)
        cslOutSide.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        v?.run {
            when (v.id) {
                R.id.ivIcon -> {
                    this@RewordPopWin.dismiss()
                }
                R.id.cslOutside->{
                    this@RewordPopWin.dismiss()
                }
            }
        }
    }

    override fun dismiss() {
        super.dismiss()
        if (this::rewordListener.isInitialized)
            rewordListener.click()
    }

    fun setListener(rewordListener: RewordListener){
        this.rewordListener = rewordListener
    }

    interface RewordListener{
        fun click()
    }
}