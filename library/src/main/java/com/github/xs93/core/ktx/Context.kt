@file:Suppress("unused")

package com.github.xs93.core.ktx

import android.content.Context
import android.text.Spanned
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

/**
 *
 *
 * @author XuShuai
 * @version v1.0
 * @date 2022/8/19 16:52
 * @email 466911254@qq.com
 */


fun Context.dpToPx(dpValue: Float): Int {
    val scale = resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

fun Context.dpToPx(dpValue: Int): Int {
    val scale = resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}


fun Context.pxToDp(pxValue: Int): Float {
    val scale = resources.displayMetrics.density
    return pxValue / scale
}

fun Context.pxToDp(pxValue: Float): Float {
    val scale = resources.displayMetrics.density
    return pxValue / scale
}

fun Context.getPixel(@DimenRes id: Int): Float {
    return resources.getDimension(id)
}

fun Context.getPixelSize(@DimenRes id: Int): Int {
    return resources.getDimensionPixelSize(id)
}

fun Context.getPixelOffset(@DimenRes id: Int): Int {
    return resources.getDimensionPixelOffset(id)
}

@ColorInt
fun Context.getCompatColor(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this, id)
}

fun Context.getHtml(@StringRes resId: Int): Spanned {
    return fromHtmlCompat(getString(resId))
}
