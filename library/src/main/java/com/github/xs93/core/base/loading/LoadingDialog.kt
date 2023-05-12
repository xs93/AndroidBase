package com.github.xs93.core.base.loading

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.xs93.core.ktx.isShowing
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 *
 *
 * @author XuShuai
 * @version v1.0
 * @date 2023/3/29 11:19
 * @email 466911254@qq.com
 */
abstract class LoadingDialog(private val fm: FragmentManager, private val lifecycleOwner: LifecycleOwner) {

    private val mChannel = Channel<Boolean>(Channel.UNLIMITED)
    private val mFlow = mChannel.receiveAsFlow()

    private var mLoadingDialog: DialogFragment? = null

    init {
        lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mFlow.collect {
                    if (it) {
                        if (mLoadingDialog.isShowing) {
                            return@collect
                        }
                        mLoadingDialog = createDialog().apply {
                            show(fm, "mLoadingDialog")
                        }
                    } else {
                        mLoadingDialog?.dismissAllowingStateLoss()
                    }
                }
            }
        }
    }


    fun show() {
        lifecycleOwner.lifecycleScope.launch {
            mChannel.send(true)
        }
    }

    fun dismiss() {
        lifecycleOwner.lifecycleScope.launch {
            mChannel.send(false)
        }
    }

    fun isShowing(): Boolean {
        return mLoadingDialog.isShowing
    }

    abstract fun createDialog(): DialogFragment

    /**
     * 返回当前Dialog对象
     * @return DialogFragment? DialogFragment对象
     */
    fun getDialog(): DialogFragment? {
        return mLoadingDialog
    }
}