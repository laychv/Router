package com.laychv.retrofit.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author: LayChv
 * @date:   2022/5/1
 * @des:
 */
class RetrofitPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        println("this is standalone plugin from : retrofit-plugin !!!!")
    }
}