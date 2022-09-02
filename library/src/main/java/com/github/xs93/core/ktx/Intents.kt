package com.github.xs93.core.ktx

import android.app.PendingIntent
import android.os.Build

/**
 *
 *  Intent相关扩展
 *
 * @author xushuai
 * @date   2022/9/2-12:02
 * @email  466911254@qq.com
 */

fun pendingIntentFlags(flags: Int, mutable: Boolean): Int {
    return if (Build.VERSION.SDK_INT >= 24) {
        if (Build.VERSION.SDK_INT > 30 && mutable) {
            flags or PendingIntent.FLAG_MUTABLE
        } else {
            flags or PendingIntent.FLAG_IMMUTABLE
        }
    } else {
        flags
    }
}