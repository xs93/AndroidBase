@file:Suppress("unused")

package com.github.xs93.core.ktx

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter

/**
 * View点击事件单机事件
 *
 * @author XuShuai
 * @version v1.0
 * @date 2022/6/7 10:23
 * @email 466911254@qq.com
 */

@BindingAdapter("singleClickInterval", "onSingleClick", requireAll = false)
fun View.setSingleClickListener(
    singleClickInterval: Long = 800,
    onSingleClick: View.OnClickListener? = null
) {
    setOnClickListener(SingleClickListener(singleClickInterval, onSingleClick))
}

@BindingAdapter("singleClickInterval", "onNavigationIconSingleClick", requireAll = false)
fun Toolbar.setNavigationIconSingleClickListener(
    singleClickInterval: Long = 800,
    onSingleClick: View.OnClickListener? = null
) {
    setNavigationOnClickListener(SingleClickListener(singleClickInterval, onSingleClick))
}


class SingleClickListener(private val interval: Long = 1000, private val singleClick: View.OnClickListener? = null) :
    View.OnClickListener {
    private var mLastClickTime = 0L
    override fun onClick(v: View) {
        if (System.currentTimeMillis() - mLastClickTime > interval) {
            singleClick?.onClick(v)
        }
        mLastClickTime = System.currentTimeMillis()
    }
}