@file:Suppress("unused")

package com.github.xs93.core.ktx

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Spanned
import androidx.annotation.*
import androidx.core.content.ContextCompat

/**
 *
 *
 * @author XuShuai
 * @version v1.0
 * @date 2022/8/19 16:52
 * @email 466911254@qq.com
 */

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
fun Context.getColorCompat(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this, id)
}

fun Context.getDrawableCompat(@DrawableRes id: Int): Drawable? {
    return ContextCompat.getDrawable(this, id)
}

fun Context.checkSelfPermissionCompat(permission: String): Int {
    return ContextCompat.checkSelfPermission(this, permission)
}

fun Context.getHtml(@StringRes resId: Int): Spanned {
    return getString(resId).fromHtmlCompat()
}

fun Context.copy(content: String, label: String = "label"): Boolean {
    return try {
        val cm: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText(label, content)
        cm.setPrimaryClip(clipData)
        true
    } catch (e: Exception) {
        false
    }
}

inline val Context.isDebug: Boolean
    get() = (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0

inline val Context.appName: String
    get() {
        var appName = ""
        try {
            val packageManager = packageManager
            val packageInfo = packageManager.getPackageInfoCompat(packageName, 0)
            val labelRes = packageInfo.applicationInfo.labelRes
            appName = resources.getString(labelRes)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return appName
    }

inline val Context.appVersionName: String
    get() {
        var versionName = ""
        try {
            val packageManager = packageManager
            val packageInfo = packageManager.getPackageInfoCompat(packageName, 0)
            versionName = packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return versionName
    }

inline val Context.appVersionCode: Long
    get() {
        var versionCode: Long = -1L
        try {
            val packageManager = packageManager
            val packageInfo = packageManager.getPackageInfoCompat(packageName, 0)
            versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                packageInfo.longVersionCode
            } else {
                @Suppress("DEPRECATION")
                packageInfo.versionCode.toLong()
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return versionCode
    }