@file:Suppress("unused")

package com.github.xs93.core.ktx

import java.text.SimpleDateFormat
import java.util.*

/**
 * 日期相关的扩展函数
 *
 * @author XuShuai
 * @version v1.0
 * @date 2022/8/19 10:13
 * @email 466911254@qq.com
 */

/** 时间戳格式化为指定格式的只出场 */
fun Long.formatTime(
    pattern: String = "yyyy-MM-dd HH:mm:ss",
    locale: Locale = Locale.getDefault(),
    timeZone: TimeZone? = null
): String {
    val simpleFormatter = SimpleDateFormat(pattern, locale)
    if (timeZone != null) {
        simpleFormatter.timeZone = timeZone
    }
    return try {
        simpleFormatter.format(Date(this))
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}

/** 当前时间戳是否是当天 */
fun Long.isToday(): Boolean {
    return try {
        val mDataFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val curDate = Date(this)
        val today = Date()
        mDataFormat.format(curDate) == mDataFormat.format(today)
    } catch (e: Exception) {
        false
    }
}

/** 指定日期字符串转换为时间戳 */
fun String.parseTime(
    pattern: String = "yyyy-MM-dd HH:mm:ss",
    locale: Locale = Locale.getDefault(),
    timeZone: TimeZone? = null
): Long {
    val simpleFormatter = SimpleDateFormat(pattern, locale)
    if (timeZone != null) {
        simpleFormatter.timeZone = timeZone
    }
    try {
        val date = simpleFormatter.parse(this)
        return date?.time ?: 0L
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return 0L
}

