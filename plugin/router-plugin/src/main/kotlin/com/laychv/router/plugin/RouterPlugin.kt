package com.laychv.router.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import groovy.json.JsonSlurper
import groovy.lang.Closure
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import java.io.File

/**
 * 插件部分
 * 接收kapt参数，失败
 * 删除注解处理器生成的router-mapping文件夹
 * org.jetbrains.kotlin.gradle.plugin.KaptExtension
 */
class RouterPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        // transform
        if (target.plugins.hasPlugin(AppPlugin::class.java)) {
            val appExtension = target.extensions.getByType(AppExtension::class.java)
            appExtension.registerTransform(RouterTransform())
        }

        // 动态获取kapt的参数，由于未实现，导致app-lib需要手动配置，导致无法获取app-lib中的路由信息
        // kotlin 遍历整个项目工程，找到subprojects
//        kapt {
//            arguments {
//                arg("root_project_dir", rootProject.projectDir.absolutePath)
//            }
//        }

//        target.afterEvaluate {
//            target.extensions.getByType(AppExtension::class.java).run {
//                configureJavaCompilerArguments(
//                    "router_project_plugin",
//                    rootProject.projectDir.absolutePath
//                )
//            }
//        }

//        target.rootProject.subprojects.forEachIndexed { _, subProject -> //配置阶段结束之后 设置kapt
//            if (subProject.name == "app") {
//                cl(subProject)
//                return
//            }
//            subProject.afterEvaluate {
//                cl(subProject)
//            }
//        }

//        target.extensions.getByType(KaptExtension::class.java)

//        val ss = target.extensions.getByType(KaptExtension::class.java)
//        println("$ss  <<<< router:为啥什么都没有呢！！！！")

        if (target.plugins.hasPlugin(AppPlugin::class.java).not()) {
            return
        }

        // 清理生成的文件
        val routerMappingDir = File(target.rootProject.projectDir, DIR)
        if (routerMappingDir.exists()) {
            routerMappingDir.deleteRecursively()
        }
        println("显示生成的路径：$routerMappingDir===========")

//        target.extensions.create(PLUGIN_NAME, RouterExtension::class.java)

        val extension = target.extensions.create<RouterExtension>(PLUGIN_NAME)
        target.afterEvaluate {
            val wikiDir = extension.wikiDir.get()
            println("用户WIKI路径为：${wikiDir}")

            target.tasks.findAll(Closure.IDENTITY).forEach { task ->
                task.doLast {
                    val file = File(rootProject.projectDir, DIR)
                    if (file.exists().not()) {
                        return@doLast
                    }
                    val sbMarkDown = StringBuilder()
                    sbMarkDown.append("# 页面文档 \n\n")
                    file.listFiles()?.forEach { child ->
                        println("内部文件夹：$child")
                        // 解析router-mapping文件夹中的.json文件，写入.md中
                        if (child.name.endsWith(".json")) {
                            // groovy/each + kotlin = 🤦‍
                            val js = JsonSlurper()
                            val content = js.parse(child) as ArrayList<Map<String, String>>
                            content.forEach {
                                val url = it["url"]
                                val description = it["description"]
                                val realPath = it["realPath"]
                                sbMarkDown.append("## $description\n")
                                sbMarkDown.append("- $url\n")
                                sbMarkDown.append("- $realPath\n\n")
                            }
                        }
                    }

                    val wikiFileDir = File(wikiDir)
                    if (!wikiFileDir.exists()) {
                        wikiFileDir.mkdir()
                    }

                    val wikiFile = File(wikiFileDir, MD_NAME)
                    if (wikiFile.exists()) {
                        wikiFile.delete()
                    }
                    wikiFile.writeText(sbMarkDown.toString())
                }
            }
        }
    }
}
