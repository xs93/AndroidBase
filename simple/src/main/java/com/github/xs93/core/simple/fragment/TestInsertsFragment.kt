package com.github.xs93.core.simple.fragment

import android.os.Bundle
import android.view.View
import com.github.xs93.core.base.ui.viewbinding.BaseVbFragment
import com.github.xs93.core.simple.R
import com.github.xs93.core.simple.databinding.FragmentTestInsetsBinding

/**
 *
 *
 * @author XuShuai
 * @version v1.0
 * @date 2023/3/1 16:36
 * @email 466911254@qq.com
 */
class TestInsertsFragment : BaseVbFragment<FragmentTestInsetsBinding>(R.layout.fragment_test_insets) {

    companion object {
        fun newInstance(): TestInsertsFragment {
            val args = Bundle()
            val fragment = TestInsertsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            sface = this@TestInsertsFragment.surface
        }
    }
}