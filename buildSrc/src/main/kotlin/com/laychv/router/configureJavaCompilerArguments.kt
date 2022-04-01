package com.laychv.router

import com.android.build.gradle.AppExtension

fun AppExtension.configureJavaCompilerArguments(arguments: String, arg: String) {
    with(defaultConfig.javaCompileOptions.annotationProcessorOptions) {
        arguments(
            mapOf(
                "arguments" to arguments,
                "arg" to arg
            )
        )
    }
}

//fun ff() {
//    val cl = { project1 ->
//        if (project1.extensions.findByName("kapt") != null) {
//            project1.extensions.findByName("kapt").arguments {
//                arg("ROOT_PROJECT_DIR", project.rootDir.absolutePath)
//            }
//        }
//    }
//    project.rootProject.subprojects.eachWithIndex { subProject, index -> //配置阶段结束之后 设置kapt
//        if (subProject.name == "app") {
//            cl(subProject)
//            return
//        }
//        subProject.afterEvaluate {
//            cl(subProject)
//        }
//    }
//}
