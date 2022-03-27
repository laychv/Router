package com.laychv.router

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.getValue

/**
 * 插件部分
 */
class RouterPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val extension = target.extensions.create<RouterExtension>("router")
        target.afterEvaluate {
            val wikiDir = extension.wikiDir.get()
            val value = extension.wikiDir.getValue("router", RouterExtension::wikiDir)
            println("用户WIKI路径为：${wikiDir}")
            println("用户WIKI路径为：${value}")
            println(extension.getPath(value))
        }
    }

}
