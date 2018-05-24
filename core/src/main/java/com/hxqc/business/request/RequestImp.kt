package com.hxqc.business.request

import com.hxqc.business.core.httprequest.SSOFetch

/**
 * Created 胡俊杰
 * 2018/5/22.
 * Todo:
 */
object RequestImp {

    fun postSSO(urlControl: String, adapterNo: String="", data: MutableMap<String, Any>,responseHandler:HttpResponseHandler?) {
        val url = "http://10.0.14.185:7001"
        basePost(url, urlControl, SSOFetch.Sys, SSOFetch.Sys, SSOFetch, adapterNo,paramMap = data, responseHandler = responseHandler)
    }
    fun getSSO(urlControl: String, adapterNo: String="", data: MutableMap<String, Any>,responseHandler:HttpResponseHandler?) {
        val url = "http://10.0.14.185:7001"
        basePost(url, urlControl, SSOFetch.Sys, SSOFetch.Sys, SSOFetch, adapterNo,paramMap = data,responseHandler = responseHandler)
    }

}