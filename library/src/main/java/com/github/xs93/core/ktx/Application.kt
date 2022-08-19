@file:Suppress("unused")

package com.github.xs93.core.ktx

import android.app.Application
import android.os.Build
import android.os.Process
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

/**
 *
 * Application扩展
 *
 * @author XuShuai
 * @version v1.0
 * @date 2022/8/19 15:37
 * @email 466911254@qq.com
 */

/** 当前进程名称 */
val Application.currentProcessName: String
    get() {
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName()
        }
        return try {
            val cmdline = File("/proc/${Process.myPid()}/cmdline")
            BufferedReader(FileReader(cmdline)).use { reader -> return reader.readLine() }
        } catch (throwable: Throwable) {
            packageName
        }
    }

/** 当前进程是否是主进程 */
val Application.isMainProcess: Boolean
    get() {
        return currentProcessName == packageName
    }