@file:Suppress("DEPRECATION")

package com.github.xs93.core.ktx

import android.os.Build
import android.text.Html
import android.text.Spanned

/**
 * Html格式化方法
 *
 * @author XuShuai
 * @version v1.0
 * @date 2022/8/19 17:06
 * @email 466911254@qq.com
 */

fun fromHtmlCompat(content: String): Spanned {
    return if (Build.VERSION.SDK_INT >= 24) {
        Html.fromHtml(content, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(content)
    }
}