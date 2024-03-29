package com.github.xs93.core.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import java.security.MessageDigest
import java.util.*


/**
 * 获取唯一标识设备id
 *
 * @author XuShuai
 * @version v1.0
 * @date 2022/7/13 9:39
 * @email 466911254@qq.com
 */
class DeviceIdUtils {

    companion object {
        private const val KEY_UNIQUE_IDENTIFICATION_CODE = "ID_UniqueIdentificationCode"
        private const val KEY_ANDROID = "ID_ANDROID"
        private const val KEY_UUID = "ID_UUID"
        private const val KEY_RANDOM = "564289"
        private const val DEFAULT_RANDOM = "564289"

        @JvmStatic
        fun getDeviceId(context: Context): String {
            val sharedPreferences = context.getSharedPreferences("UniqueIdentificationCode", Context.MODE_PRIVATE)
            var uniqueIdentificationCode = sharedPreferences.getString(KEY_UNIQUE_IDENTIFICATION_CODE, null)
            if (uniqueIdentificationCode.isNullOrBlank()) {
                val editor = sharedPreferences.edit()
                var androidId = sharedPreferences.getString(KEY_ANDROID, null) ?: getAndroidId(context)
                if (androidId.isNullOrBlank() || androidId == "9774d56d682e549c") {
                    var uuid = sharedPreferences.getString(KEY_UUID, null)
                    if (uuid.isNullOrBlank()) {
                        uuid = UUID.randomUUID().toString()
                        editor.putString(KEY_UUID, uuid)
                    }
                    androidId = uuid
                    editor.putString(KEY_ANDROID, androidId)
                }


                val imei = getImei(context)
                val serial = getSerial()
                val random = sharedPreferences.getString(KEY_RANDOM, DEFAULT_RANDOM) ?: DEFAULT_RANDOM
                val deviceId = getDeviceUUID(random).replace("-", "")

                val builder = StringBuilder()
                if (imei.isNotBlank()) {
                    builder.append("$imei|")
                }
                if (serial.isNotBlank()) {
                    builder.append("$serial|")
                }
                if (androidId.isNotBlank()) {
                    builder.append("$androidId|")
                }
                if (deviceId.isNotBlank()) {
                    builder.append(deviceId)
                }

                uniqueIdentificationCode = (if (builder.isNotEmpty()) {
                    val hash = getHashByString(builder.toString())
                    UUID.nameUUIDFromBytes(hash).toString().replace("-", "")
                } else {
                    UUID.randomUUID().toString().replace("-", "")
                }).lowercase(Locale.getDefault())
                editor.putString(KEY_UNIQUE_IDENTIFICATION_CODE, uniqueIdentificationCode)
                editor.apply()
                return uniqueIdentificationCode
            }
            return uniqueIdentificationCode
        }


        @SuppressLint("ApplySharedPref")
        fun changeDeviceId(context: Context) {
            val sharedPreferences = context.getSharedPreferences("UniqueIdentificationCode", Context.MODE_PRIVATE)
            val newRandom = "$DEFAULT_RANDOM${Random().nextInt(10000)}"
            sharedPreferences.edit().remove(KEY_UNIQUE_IDENTIFICATION_CODE)
                .remove(KEY_UUID)
                .remove(KEY_ANDROID)
                .putString(KEY_RANDOM, newRandom)
                .commit()
        }

        @SuppressLint("ApplySharedPref")
        fun resetDeviceId(context: Context) {
            val sharedPreferences = context.getSharedPreferences("UniqueIdentificationCode", Context.MODE_PRIVATE)
            sharedPreferences.edit().remove(KEY_UNIQUE_IDENTIFICATION_CODE)
                .remove(KEY_UUID)
                .remove(KEY_ANDROID)
                .remove(KEY_RANDOM)
                .commit()
        }

        @Suppress("DEPRECATION")
        private fun getAndroidId(context: Context): String? {
            return Settings.System.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }

        @Suppress("DEPRECATION")
        @SuppressLint("MissingPermission", "HardwareIds")
        private fun getImei(context: Context): String {
            try {
                val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    tm.imei
                } else {
                    tm.deviceId
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            return ""
        }

        @Suppress("DEPRECATION")
        @SuppressLint("MissingPermission", "HardwareIds")
        private fun getSerial(): String {
            try {
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Build.getSerial()
                } else {
                    Build.SERIAL
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            return ""
        }

        @SuppressLint("HardwareIds")
        private fun getDeviceUUID(random: String): String {
            return try {
                val builder = StringBuilder()
                builder.append("${random}|")
                builder.append("${Build.BOARD}|")
                builder.append("${Build.BRAND}|")
                builder.append("${Build.DEVICE}|")
                builder.append("${Build.HARDWARE}|")
                builder.append("${Build.FINGERPRINT}|")
                builder.append("${Build.MANUFACTURER}|")
                builder.append("${Build.TAGS}|")
                builder.append("${Build.ID}|")
                builder.append("${Build.MODEL}|")
                builder.append("${Build.PRODUCT}|")
                return UUID(builder.toString().hashCode().toLong(), Build.TAGS.hashCode().toLong()).toString()
            } catch (ex: Exception) {
                ex.printStackTrace()
                ""
            }
        }


        /**
         * 取SHA1
         * @param data 数据
         * @return 对应的hash值
         */
        private fun getHashByString(data: String): ByteArray {
            return try {
                val messageDigest: MessageDigest = MessageDigest.getInstance("SHA1")
                messageDigest.reset()
                messageDigest.update(data.toByteArray(charset("UTF-8")))
                messageDigest.digest()
            } catch (e: Exception) {
                "".toByteArray()
            }
        }

        /**
         * 转16进制字符串
         * @param data 数据
         * @return 16进制字符串
         */
        private fun bytesToHex(data: ByteArray): String {
            val sb = StringBuilder()
            var stmp: String
            for (n in data.indices) {
                stmp = Integer.toHexString((data[n].toInt() and 0xFF))
                if (stmp.length == 1) sb.append("0")
                sb.append(stmp)
            }
            return sb.toString().uppercase(Locale.getDefault())
        }
    }


}