package com.github.xs93.core.ktx

import android.os.Build
import android.os.Bundle

/**
 *
 * Bundle 扩展
 * @author XuShuai
 * @version v1.0
 * @date 2023/2/28 17:03
 * @email 466911254@qq.com
 */


fun <T> Bundle.getParcelableCompat(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, clazz)
    } else {
        @Suppress("DEPRECATION")
        getParcelable(key)
    }
}