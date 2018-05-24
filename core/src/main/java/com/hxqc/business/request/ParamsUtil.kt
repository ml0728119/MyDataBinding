package com.hxqc.business.request

import android.os.Build
import android.text.TextUtils
import com.hxqc.business.BuildConfig
import com.hxqc.business.core.httprequest.Fetch
import com.hxqc.business.user.CoreUser
import com.hxqc.util.DebugLog
import com.hxqc.util.JSONUtils
import java.net.URLEncoder
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import kotlin.collections.HashMap

/**
 * Created 胡俊杰
 * 2017/8/10.
 * Todo:
 */

object ParamsUtil {
    const val APITAG = "ApiClient"

    fun buildRequestParams2Native(dataMap: Map<String, Any>?, fetch: Fetch ,
                                  md5Key: String, adapter_no: String = ""): MutableMap<String, Any> {
        val des3K = arrayOf(Hehedabbbbbb.abc(fetch.desKey), fetch.IV)
        return buildRequestParams(dataMap, des3K, md5Key, adapter_no, false,false)
    }

    /**
     *dataMap 业务参数
     * des3k  加密key
     * md5key md5的key
     * adapter_no 接口编号
     */
    private fun buildRequestParams(
            tDataMap: Map<String, Any>?,
            des3K: Array<String>,
            md5Key: String,
            adapter_no: String = "",
            isRN: Boolean = true,
            isNeedURLEncoder:Boolean = true): MutableMap<String, Any> {
        val requestParams = HashMap<String, Any>()
        val time = (System.currentTimeMillis() / 1000).toString() + getRandomString(8)
        val dataMap = HashMap<String, Any>()
        try {
            if (tDataMap != null) dataMap.putAll(tDataMap)
            dataMap.put("deviceType", "Android")
            dataMap.put("appVersion", BuildConfig.VERSION_NAME)
            dataMap.put("deviceMode", Build.MODEL)
            dataMap.put("deviceVersion", Build.VERSION.SDK_INT)
            dataMap.put("release", Build.VERSION.RELEASE)
            if(!isRN){
                if (!TextUtils.isEmpty(CoreUser.entityCode)) {
                    dataMap.put("entityCode", CoreUser.entityCode)
                    dataMap.put("token", CoreUser.token)
                }
            }

            DebugLog.i(APITAG,"dataMap   "+ JSONUtils.toJson(dataMap))

            val dataString = DES3.encodeA(des3K[0], des3K[1], JSONUtils.toJson(dataMap))

            val identity_code = getMD5(md5Key + dataString + time)
            DebugLog.i(APITAG, "md5Key   $md5Key" )
            DebugLog.i(APITAG, "dataString   $dataString" )
            DebugLog.i(APITAG, "time   $time")
            DebugLog.i(APITAG, "identity_code   $identity_code")
            requestParams.put("adapter_no", adapter_no)
            if (isRN) {

                if(isNeedURLEncoder){
                    requestParams.put("identity_code", URLEncoder.encode(identity_code, "UTF-8"))
                    requestParams.put("data", URLEncoder.encode(dataString, "UTF-8"))
                }else{
                    requestParams.put("identity_code", identity_code)
                    requestParams.put("data", dataString)
                }


                val entityCode = tDataMap?.get("entityCode")
                if (TextUtils.isEmpty(entityCode as CharSequence?)){
                    requestParams.put("entityCode", CoreUser.entityCode)
                }else{
                    requestParams.put("entityCode", entityCode as Any)
                }
            } else {
                requestParams.put("identity_code", identity_code)
                requestParams.put("data", dataString)
                requestParams.put("entityCode", CoreUser.entityCode)
            }
            requestParams.put("time", time)
            requestParams.put("system_id", "100080")


            DebugLog.d(APITAG, requestParams.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return requestParams
    }

    private fun getRandomString(length: Int): String {
        val str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        val random = Random()
        val sb = StringBuilder()
        for (i in 0..length - 1) {
            val number = random.nextInt(62)
            sb.append(str[number])
        }
        return sb.toString()
    }

    @Throws(NoSuchAlgorithmException::class)
    private fun getMD5(`val`: String): String {
        try {
            val bmd5 = MessageDigest.getInstance("MD5")
            bmd5.update(`val`.toByteArray())
            var i: Int
            val buf = StringBuilder()
            val b = bmd5.digest()// 加密
            for (offset in b.indices) {
                i = b[offset].toInt()
                if (i < 0)
                    i += 256
                if (i < 16)
                    buf.append("0")
                buf.append(Integer.toHexString(i))
            }
            return buf.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }
}
