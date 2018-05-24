package com.hxqc.business.activity

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.hxqc.business.R
import kotlinx.android.synthetic.main.app_my_view.view.*

/**
 * Created 胡俊杰
 * 2018/5/23.
 * Todo:
 */
class MyView : LinearLayout {


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        LayoutInflater.from(context).inflate(R.layout.app_my_view, this)
        app_editview.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("Tag","onTextChanged  ")
                onValueChangeListener.onValueChange(this@MyView, s.toString())
            }

        })
    }

    fun setLeftValueBB(value: String) {
        app_textview.text = value
    }

    fun setRightValueBB(value: String) {
        app_editview.setText(value)
    }

    fun getRightValueBB(): String {
        return app_editview.text.toString()
    }

    lateinit var onValueChangeListener: OnValueChangeListener


    interface OnValueChangeListener {
        fun onValueChange(view: MyView, valueStr: String)
    }


}
