package com.hxqc.business.user

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.hxqc.business.application.SampleApplicationContext

/**
 * Created 胡俊杰
 * 2017/8/8.
 * Todo:
 */

open class CoreUser {
    companion object {

        var monitorToken: String
            get() = CoreUserSP().monitorToken
            set(monitorToken) {
                CoreUserSP().monitorToken = monitorToken
            }

        var name: String
            get() = CoreUserSP().name
            set(name) {
                CoreUserSP().name = name
            }

        var shopName: String
            get() = CoreUserSP().shopName
            set(shopName) {
                CoreUserSP().shopName = shopName
            }

        var position: String
            get() = CoreUserSP().position
            set(position) {
                CoreUserSP().position = position
            }

        var idNumber: String
            get() = CoreUserSP().idNumber
            set(idNumber) {
                CoreUserSP().idNumber = idNumber
            }

        var entityCode: String
            get() = CoreUserSP().entityCode
            set(entityCode) {
                CoreUserSP().entityCode = entityCode
            }

        var userName: String
            get() = CoreUserSP().userName
            set(userName) {
                CoreUserSP().userName = userName
            }

        var token: String
            get() = CoreUserSP().token
            set(token) {
                val coreUserSP = CoreUserSP()
                coreUserSP.token = token
                coreUserSP.loginTime = System.currentTimeMillis()
            }

        var loginTime: Long?
            get() = CoreUserSP().loginTime
            set(loginTime) {
                CoreUserSP().loginTime = loginTime
            }

        fun exit() {
            CoreUserSP().exit()
        }
    }

    internal class CoreUserSP {
        private val sharedPreferences: SharedPreferences

        init {
            sharedPreferences = SampleApplicationContext.context!!.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
            sharedPreferences.edit {

            }
        }

        /**
         * 获取token
         */
        var token: String
            get() = sharedPreferences.getString(TOKEN_VALUE, "")
            set(token) = sharedPreferences.edit {
                putString(TOKEN_VALUE, token).apply()
            }

        var userName: String
            get() = sharedPreferences.getString(USER_NAME, "")
            set(userName) = sharedPreferences.edit().putString(USER_NAME, userName).apply()

        var entityCode: String
            get() = sharedPreferences.getString(ENTITY_CODE, "")
            set(entityCode) = sharedPreferences.edit().putString(ENTITY_CODE, entityCode).apply()

        var idNumber: String
            get() = sharedPreferences.getString(ID_NUMBER, "")
            set(idNumber) = sharedPreferences.edit().putString(ID_NUMBER, idNumber).apply()

        var monitorToken: String
            get() = sharedPreferences.getString(MONITOR_TOKEN, "")
            set(monitorToken) = sharedPreferences.edit().putString(MONITOR_TOKEN, monitorToken).apply()

        var name: String
            get() = sharedPreferences.getString(REAL_NAME, "")
            set(name) = sharedPreferences.edit().putString(REAL_NAME, name).apply()

        var shopName: String
            get() = sharedPreferences.getString(SHOP_NAME, "")
            set(shopName) = sharedPreferences.edit().putString(SHOP_NAME, shopName).apply()

        var position: String
            get() = sharedPreferences.getString(POSITION, "")
            set(position) = sharedPreferences.edit().putString(POSITION, position).apply()

        var loginTime: Long?
            get() = sharedPreferences.getLong(LOGIN_TIME, 0)
            set(loginTime) = sharedPreferences.edit().putLong(LOGIN_TIME, loginTime!!).apply()

        fun exit(): SharedPreferences {
            sharedPreferences.edit().remove(TOKEN_VALUE).apply()
            sharedPreferences.edit().remove(USER_NAME).apply()
            sharedPreferences.edit().remove(ENTITY_CODE).apply()
            sharedPreferences.edit().remove(ID_NUMBER).apply()
            sharedPreferences.edit().remove(MONITOR_TOKEN).apply()
            sharedPreferences.edit().remove(LOGIN_TIME).apply()
            sharedPreferences.edit().remove(REAL_NAME).apply()
            sharedPreferences.edit().remove(SHOP_NAME).apply()
            sharedPreferences.edit().remove(POSITION).apply()
            return sharedPreferences
        }

        companion object {
            private val SHARED_PREFERENCES_NAME = "CoreUser"
            private val TOKEN_VALUE = "Token"//token
            private val USER_NAME = "userName"//用户账号
            private val ENTITY_CODE = "entityCode"//公司编码
            private val LOGIN_TIME = "login_time"//登陆时间
            private val ID_NUMBER = "id_number"//身份证
            private val REAL_NAME = "real_name"//真实姓名
            private val SHOP_NAME = "shop_name"//店铺名称
            private val POSITION = "user_position"//职位


            private val MONITOR_TOKEN = "m_token"//监控测试token
        }
    }
}


