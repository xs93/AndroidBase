package com.github.xs93.core.simple.activity

import android.hardware.SensorEvent
import android.os.Bundle
import com.github.xs93.core.base.ui.vbvm.BaseVbVmActivity
import com.github.xs93.core.simple.R
import com.github.xs93.core.simple.databinding.ActivitySecondBinding
import com.github.xs93.core.simple.state.SecondViewEvent
import com.github.xs93.core.simple.state.SecondViewState
import com.github.xs93.core.simple.viewmodel.SecondViewModel

/**
 *
 *
 * @author XuShuai
 * @version v1.0
 * @date 2023/2/27 10:20
 * @email 466911254@qq.com
 */
class SecondActivity : BaseVbVmActivity<ActivitySecondBinding, SecondViewModel>(R.layout.activity_second) {

    override fun initView(savedInstanceState: Bundle?) {

    }
}