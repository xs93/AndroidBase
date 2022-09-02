@file:Suppress("DEPRECATION", "unused")

package com.github.xs93.core.ktx

import android.content.pm.PackageInfo
import android.os.Build

/**
 *
 * Package 相关扩展
 *
 * @author xushuai
 * @date   2022/9/2-12:00
 * @email  466911254@qq.com
 */


val PackageInfo.versionCodeCompat: Long
    get() {
        return if (Build.VERSION.SDK_INT >= 28) {
            longVersionCode
        } else {
            versionCode.toLong()
        }
    }