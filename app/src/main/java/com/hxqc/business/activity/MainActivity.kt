package com.hxqc.business.activity

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.View
import com.hxqc.business.R
import com.hxqc.business.databinding.AppActivityMainBinding
import kotlinx.android.synthetic.main.app_activity_main.*

/**
 * Created 胡俊杰
 * 2018/5/23.
 * Todo:
 */
class MainActivity : Activity() {
    val user = User("kkkkk", "AAAAA")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<AppActivityMainBinding>(this, R.layout.app_activity_main)

        binding.user = user

        app_button2.setOnClickListener {
            user.firstName = "1234"
            user.aaName = "skdfl"
        }
        app_button3.setOnClickListener { Log.i("Tag", "111   " + user.toString()) }
    }

    fun clickAA(view: View) {

    }
}
