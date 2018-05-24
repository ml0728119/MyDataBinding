package com.hxqc.business.request


import com.hxqc.business.request.ParamsUtil.APITAG
import com.hxqc.util.DebugLog
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created 胡俊杰
 * 2018/5/21.
 * Todo:
 */
class ApiClient private constructor() {
    var retrofit: Retrofit

    init {
        val builder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor { message ->   DebugLog.i(APITAG,message) }

        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(loggingInterceptor)
        val client = builder.build()

        retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.14.41:8361/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    }


    companion object {
        fun get(): ApiClient {
            return Inner.client
        }

        private object Inner {
            val client = ApiClient()
        }
    }


}
