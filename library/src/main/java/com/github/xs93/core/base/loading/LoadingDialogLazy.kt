package com.github.xs93.core.base.loading

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner

/**
 *
 *
 * @author XuShuai
 * @version v1.0
 * @date 2023/3/29 11:34
 * @email 466911254@qq.com
 */
class LoadingDialogLazy<T : LoadingDialog>(
    private val fm: FragmentManager,
    private val lifecycleOwner: LifecycleOwner,
    private val dialogProducer: ((FragmentManager, LifecycleOwner) -> T),
) : Lazy<T> {

    private var mCached: T? = null

    override val value: T
        get() {
            return mCached ?: dialogProducer(fm, lifecycleOwner).also {
                mCached = it
            }
        }


    override fun isInitialized(): Boolean = mCached != null
}