
package com.hxqc.business.request

import android.text.TextUtils

import com.hxqc.security.Base64Util

import java.io.UnsupportedEncodingException
import java.security.Key

import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESedeKeySpec
import javax.crypto.spec.IvParameterSpec

/**
 * 3DES加密工具类
 */
object DES3 {
    // 密钥 com.hxqc.crmkey
//    private val secretKey = Hehedabbbbbb.eGet12Sth()

    // 向量
//    private val iv = "01234567"
    // 加解密统一使用的编码方式
    private val encoding = "utf-8"

    @Throws(Exception::class)
    fun encodeA(secretKey: String, iv: String, plainText: String): String {
        if (TextUtils.isEmpty(plainText)) return ""
        val deskey: Key
        val spec = DESedeKeySpec(secretKey.toByteArray())
        val keyfactory = SecretKeyFactory.getInstance("desede")
        deskey = keyfactory.generateSecret(spec)

        val cipher = Cipher.getInstance("desede/CBC/PKCS5Padding")
        val ips = IvParameterSpec(iv.toByteArray())
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips)
        val encryptData = cipher.doFinal(plainText.toByteArray(charset(encoding)))
        return Base64Util.encode(encryptData).replace(" ", "")
    }

    @Throws(Exception::class)
    fun decodeA(secretKey: String, iv: String, encryptText: String): java.lang.String {
        val deskey: Key
        val spec = DESedeKeySpec(secretKey.toByteArray())
        val keyfactory = SecretKeyFactory.getInstance("desede")
        deskey = keyfactory.generateSecret(spec)
        val cipher = Cipher.getInstance("desede/CBC/PKCS5Padding")
        val ips = IvParameterSpec(iv.toByteArray())
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips)

        val decryptData = cipher.doFinal(Base64Util.decode(encryptText)) as ByteArray

        return java.lang.String(decryptData , encoding)
    }


    fun padding(str: String): java.lang.String? {
        val oldByteArray: ByteArray
        try {
            oldByteArray = str.toByteArray(charset("UTF8"))
            val numberToPad = 8 - oldByteArray.size % 8
            val newByteArray = ByteArray(oldByteArray.size + numberToPad)
            System.arraycopy(oldByteArray, 0, newByteArray, 0,
                    oldByteArray.size)
            for (i in oldByteArray.size..newByteArray.size - 1) {
                newByteArray[i] = 0
            }
            return java.lang.String(newByteArray, encoding)
        } catch (e: UnsupportedEncodingException) {
        }

        return null
    }


}
