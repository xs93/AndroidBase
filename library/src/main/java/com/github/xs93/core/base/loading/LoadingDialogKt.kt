package com.github.xs93.core.base.loading

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.github.xs93.core.base.loading.impl.DefaultLoadingDialog

/**
 *
 *
 * @author XuShuai
 * @version v1.0
 * @date 2023/3/29 11:29
 * @email 466911254@qq.com
 */


inline fun <reified T : LoadingDialog> AppCompatActivity.loadingDialog(noinline dialogProducer: ((FragmentManager, LifecycleOwner) -> T)): Lazy<T> {
    return LoadingDialogLazy(supportFragmentManager, this, dialogProducer)
}

fun AppCompatActivity.defaultLoadingDialog(): Lazy<DefaultLoadingDialog> {
    return LoadingDialogLazy(
        supportFragmentManager,
        this
    ) { fragmentManager, lifecycleOwner ->
        DefaultLoadingDialog(fragmentManager, lifecycleOwner)
    }
}

inline fun <reified T : LoadingDialog> Fragment.loadingDialog(noinline dialogProducer: ((FragmentManager, LifecycleOwner) -> T)): Lazy<T> {
    return LoadingDialogLazy(childFragmentManager, this, dialogProducer)
}

fun Fragment.defaultLoadingDialog(): Lazy<DefaultLoadingDialog> {
    return LoadingDialogLazy(
        childFragmentManager,
        this
    ) { fragmentManager, lifecycleOwner ->
        DefaultLoadingDialog(fragmentManager, lifecycleOwner)
    }
}