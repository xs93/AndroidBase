@file:Suppress("unused")

package com.github.xs93.core.ktx

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle

/**
 *
 *
 * @author XuShuai
 * @version v1.0
 * @date 2022/8/19 16:31
 * @email 466911254@qq.com
 */


fun Context.getAppMateData(): Bundle {
    val info = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
    return info.metaData
}

fun Context.getActivityMateData(clazz: String): Bundle {
    val componentName = ComponentName(this, clazz)
    val info = packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA)
    return info.metaData
}

fun Context.getActivityMateData(clazz: Class<*>): Bundle {
    val componentName = ComponentName(this, clazz)
    val info = packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA)
    return info.metaData
}

fun Context.getServiceMateData(clazz: String): Bundle {
    val componentName = ComponentName(this, clazz)
    val info = packageManager.getServiceInfo(componentName, PackageManager.GET_META_DATA)
    return info.metaData
}

fun Context.getServiceMateData(clazz: Class<*>): Bundle {
    val componentName = ComponentName(this, clazz)
    val info = packageManager.getServiceInfo(componentName, PackageManager.GET_META_DATA)
    return info.metaData
}

fun Context.getReceiverMateData(clazz: String): Bundle {
    val componentName = ComponentName(this, clazz)
    val info = packageManager.getReceiverInfo(componentName, PackageManager.GET_META_DATA)
    return info.metaData
}

fun Context.getReceiverMateData(clazz: Class<*>): Bundle {
    val componentName = ComponentName(this, clazz)
    val info = packageManager.getReceiverInfo(componentName, PackageManager.GET_META_DATA)
    return info.metaData
}