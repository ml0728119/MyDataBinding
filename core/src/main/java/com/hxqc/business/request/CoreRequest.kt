package com.hxqc.business.request

import com.hxqc.business.core.httprequest.Fetch
import com.hxqc.business.request.ParamsUtil.APITAG
import com.hxqc.business.request.ParamsUtil.buildRequestParams2Native
import com.hxqc.util.DebugLog
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*
import java.io.File
import java.io.IOException

/**
 * Created 胡俊杰
 * 2018/5/22.
 * Todo:
 */

interface BlogService {
    @GET
    fun getBlog(@Url url: String, @QueryMap id: Map<String, Any>): Call<ResponseBody>

    @POST
    fun postBlog(@Url url: String, @Body Body: RequestBody): Call<ResponseBody>
}

var businessEnvironment = "debug"
@Throws(IOException::class)
fun basePost(url: String, urlControl: String, confSystem: String, encodeType: String,
             fetch: Fetch, adapterNo: String = "", paramMap: MutableMap<String, Any>, responseHandler: HttpResponseHandler?) {

//将文件加入requestbody
    val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
    builder.addFormDataPart("urlControl", urlControl)
    builder.addFormDataPart("confSystem", confSystem)
    builder.addFormDataPart("encodeType", encodeType)
    builder.addFormDataPart("businessEnvironment", businessEnvironment)
    builder.addFormDataPart("erpHostURL", "")
    for ((key, value) in paramMap) {
        if (value is File) {
            builder.addFormDataPart(key, value.name, RequestBody.create(MediaType.parse("image/*"), value))
        }
    }

//加密后的参数加入requestbody
    val data = buildRequestParams2Native(paramMap, fetch, fetch.MD5String, adapterNo)
    for ((key, value) in data) {
        if (value !is File) {
            builder.addFormDataPart(key, value as String)
        }
    }
    val requestBody = builder.build()

    val service = ApiClient.get().retrofit.create(BlogService::class.java)
    val call = service.postBlog(url, requestBody)
    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

            responseHandler?.onSuccess(response.body().toString())

            // 已经转换为想要的类型了
            val body1 = response.body()
            try {
                DebugLog.i(APITAG, "----------" + body1!!.string())
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            t.printStackTrace()
            responseHandler?.onFailure()
        }
    })
}


@Throws(IOException::class)
fun baseGet(url: String, urlControl: String, confSystem: String, encodeType: String,
            fetch: Fetch, adapterNo: String = "", paramMap: MutableMap<String, Any>, responseHandler: HttpResponseHandler?) {

//加密后的参数加入requestbody
    val data = buildRequestParams2Native(paramMap, fetch, fetch.MD5String, adapterNo)
    data["urlControl"] = urlControl
    data["confSystem"] = confSystem
    data["encodeType"] = encodeType

    val service = ApiClient.get().retrofit.create(BlogService::class.java)
    val call = service.getBlog(url, data)
    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            responseHandler?.onSuccess(response.body().toString())
            // 已经转换为想要的类型了
            val body1 = response.body()
            try {
                DebugLog.i(APITAG, "----------" + body1!!.string())
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            t.printStackTrace()
        }
    })
}
