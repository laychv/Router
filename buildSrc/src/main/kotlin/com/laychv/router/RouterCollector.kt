package com.laychv.router

import java.io.File
import java.util.jar.JarEntry
import java.util.jar.JarFile

object RouterCollector {
    private const val PACKAGE_NAME = "com/laychv/router/mapping"
    private const val CLASS_NAME_PREFIX = "RouterMapping_"
    private const val CLASS_FILE_SUFFIX = ".kt"// kotlin for .kt/java for .class

    private val clazz = HashSet<String>()

    // 获取收集好的映射表类名
    fun getMappingClass(): Set<String> {
        println("内部收集信息：$clazz")
        return clazz
    }

    // 收集class文件或者class文件目录中的映射表类。
    fun collect(file: File) {
        if (!file.exists()) {
            return
        }
        if (file.isFile) {
            if (file.absolutePath.contains(PACKAGE_NAME)
                && file.name.startsWith(CLASS_NAME_PREFIX)
                && file.name.endsWith(CLASS_FILE_SUFFIX)
            ) {
                val name = file.name.replace(CLASS_FILE_SUFFIX, "")
                println("---内部name222---$name ----")
                clazz.add(name)
            }
        } else {
            file.listFiles().forEach {
                collect(it)
            }
        }
    }

    // 收集JAR包中的目标类
    fun collectJarFile(jarFile: File) {
        val jFile = JarFile(jarFile).entries()
        while (jFile.hasMoreElements()) {
            val next = jFile.nextElement() as JarEntry
            val entry = next.name
            if (entry.contains(PACKAGE_NAME)
                && entry.contains(CLASS_NAME_PREFIX)
                && entry.contains(CLASS_FILE_SUFFIX)
            ) {
                val name = entry
                    .replace(PACKAGE_NAME, "")
                    .replace("/", "")
                    .replace(CLASS_FILE_SUFFIX, "")
                println("---内部name333---$name ----")
                clazz.add(name)
            }
        }
    }

}