@file:Suppress("unused")

package com.github.xs93.core.ktx

import android.content.Context
import android.content.res.Resources

/**
 * 单位尺寸扩展函数
 *
 * @author XuShuai
 * @version v1.0
 * @date 2022/11/1 10:36
 * @email 466911254@qq.com
 */
fun Float.dp(context: Context? = null): Int {
    val resource = context?.resources ?: Resources.getSystem()
    val scale = resource.displayMetrics.density
    return (this * scale + 0.5f).toInt()
}

fun Float.toDp(context: Context? = null): Float {
    val resource = context?.resources ?: Resources.getSystem()
    val scale = resource.displayMetrics.density
    return this / scale
}

fun Float.sp(context: Context? = null): Int {
    val resource = context?.resources ?: Resources.getSystem()
    val scale = resource.displayMetrics.scaledDensity
    return (this * scale + 0.5f).toInt()
}

fun Float.toSp(context: Context? = null): Float {
    val resource = context?.resources ?: Resources.getSystem()
    val scale = resource.displayMetrics.scaledDensity
    return this / scale
}


fun Int.dp(context: Context? = null): Int {
    val resource = context?.resources ?: Resources.getSystem()
    val scale = resource.displayMetrics.density
    return (this * scale + 0.5f).toInt()
}

fun Int.toDp(context: Context? = null): Float {
    val resource = context?.resources ?: Resources.getSystem()
    val scale = resource.displayMetrics.density
    return this / scale
}

fun Int.sp(context: Context? = null): Int {
    val resource = context?.resources ?: Resources.getSystem()
    val scale = resource.displayMetrics.scaledDensity
    return (this * scale + 0.5f).toInt()
}


fun Int.toSp(context: Context? = null): Float {
    val resource = context?.resources ?: Resources.getSystem()
    val scale = resource.displayMetrics.scaledDensity
    return this / scale
}
