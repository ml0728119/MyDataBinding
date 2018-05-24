package com.hxqc.business.request

/**
 * Author:  wh
 * Date:  2016/11/17
 * FIXME
 * Todo
 */

object Hehedabbbbbb {

    fun abc(kk: IntArray): String {
        val str = StringBuilder()
        for (aKk in kk) {
            val i1 = aKk - 21 shr 2
            val e = i1.toChar()
            str.append(e)
        }
        return str.toString()
    }
}
