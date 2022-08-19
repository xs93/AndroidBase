package com.github.xs93.core.base.ui.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.github.xs93.core.R
import com.github.xs93.core.ktx.setOnInsertsChangedListener
import com.github.xs93.core.ui.Surface
import java.lang.reflect.Field

/**
 * 基础dialogFragment 封装
 *
 * @author XuShuai
 * @version v1.0
 * @date 2021/11/4 13:40
 */
abstract class BaseDialogFragment : DialogFragment() {

    protected val surface = Surface()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, getStyle())
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return DialogInterfaceProxyDialog(requireContext(), theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (getContentLayoutId() != 0) {
            return inflater.inflate(getContentLayoutId(), container, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.apply {
            decorView.setOnInsertsChangedListener {
                surface.insets = it
            }
        }
        beforeInitView(view, savedInstanceState)
        initView(view, savedInstanceState)
    }

    protected open fun getStyle(): Int {
        return R.style.BaseDialogStyle
    }

    /**返回布局Layout*/
    @LayoutRes
    abstract fun getContentLayoutId(): Int

    open fun beforeInitView(view: View, savedInstanceState: Bundle?) {}

    /** 初始化View */
    abstract fun initView(view: View, savedInstanceState: Bundle?)

    /**
     * 使用此方法显示弹出框，可以避免生命周期状态错误导致的异常(Can not perform this action after onSaveInstanceState)
     * @param manager FragmentManager
     * @param tag String?
     */
    fun showAllowingStateLoss(manager: FragmentManager, tag: String? = this::class.java.simpleName) {
        try {
            val dismissed: Field = DialogFragment::class.java.getDeclaredField("mDismissed")
            dismissed.isAccessible = true
            dismissed.set(this, false)
            val shown: Field = DialogFragment::class.java.getDeclaredField("mShownByMe")
            shown.isAccessible = true
            shown.set(this, true)
            val ft: FragmentTransaction = manager.beginTransaction()
            ft.add(this, tag)
            ft.commitAllowingStateLoss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}