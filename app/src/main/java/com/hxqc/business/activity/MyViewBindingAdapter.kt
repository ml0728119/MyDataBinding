package com.hxqc.business.activity

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.databinding.InverseBindingListener
import android.util.Log

/**
 * Created 胡俊杰
 * 2018/5/24.
 * Todo: 自定义双向绑定  对应的view为MyView,
 * 初始化流程:
 * setRightValueChangeListener
 * setRightValueAA
 * MyView.onTextChanged
 * getRightValueAA
 * setRightValueAA
 * 页面输入框输入流程：
 * onTextChanged
 * getRightValueAA
 * setRightValueAA
 */
object MyViewBindingAdapter {
    /**
     * 设置左边TextView设置数据的方法
     */
    @BindingAdapter(value = ["leftValue"])
    @JvmStatic
    fun setLeftValueAA(view: MyView, value: String) {
        view.setLeftValueBB(value)
    }
    /**
     * 设置右边View设置数据的方法
     */
    @BindingAdapter(value = ["rightValue"])
    @JvmStatic
    fun setRightValueAA(view: MyView, value: String) {
        Log.i("Tag","setRightValueAA")
        //避免循环
        if (value != view.getRightValueBB()) {
            view.setRightValueBB(value)
        }
    }

    /**
     * 获取右边view的数据，反向绑定
     */
    @InverseBindingAdapter(attribute = "rightValue")
    @JvmStatic
    fun getRightValueAA(view: MyView): String {
        Log.i("Tag","getRightValueAA")
        return view.getRightValueBB()
    }

    /**
     * value值格式为 属性名+AttrChanged
     * 例如，修改右边值属性为rightValue，此处监听的value为rightValueAttrChanged
     */
    @BindingAdapter(value = ["rightValueAttrChanged"])
    @JvmStatic
    fun setRightValueChangeListener(view: MyView, attrChange: InverseBindingListener?) {
        Log.i("Tag","setRightValueChangeListener")
        if (attrChange != null) {
            view.onValueChangeListener = object : MyView.OnValueChangeListener {
                override fun onValueChange(view: MyView, valueStr: String) {
                    attrChange.onChange()
                }
            }
        }
    }

}