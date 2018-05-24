package com.hxqc.business.request

/**
 * Created 胡俊杰
 * 2018/5/23.
 * Todo:
 */
interface HttpResponseHandler {
    fun onStart()

    fun onFailure()

    fun onSuccess(responseString: String)

    fun onFinish()
}
