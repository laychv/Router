package com.laychv.router

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import java.io.File
import java.io.FileOutputStream
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

/**
 * AGP8.0彻底移除Transform，替换为Instrumentation API
 * https://developer.android.com/studio/releases/gradle-plugin-api-updates?hl=zh-cn
 */
@Deprecated("since AGP-8.0 remove")
class RouterTransform : Transform() {

    override fun isIncremental(): Boolean = false
    override fun getName(): String = "RouterTransform"

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> =
        TransformManager.CONTENT_CLASS

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> =
        TransformManager.SCOPE_FULL_PROJECT

    override fun transform(transformInvocation: TransformInvocation?) {
        transformInvocation?.inputs?.forEach { ti ->
            ti.directoryInputs.forEach { di ->
                val path = transformInvocation.outputProvider.getContentLocation(
                    di.name,
                    di.contentTypes,
                    di.scopes,
                    Format.DIRECTORY
                )
                val srcFolder = File(di.file.absolutePath)
                val tarFolder = File(path.absolutePath)
                srcFolder.copyRecursively(tarFolder)
                RouterCollector.collect(di.file)
                println("----di.file----${di.file.name}-------")
            }

            ti.jarInputs.forEach { ji ->
                val target = transformInvocation.outputProvider.getContentLocation(
                    ji.name,
                    ji.contentTypes,
                    ji.scopes,
                    Format.JAR
                )
                ji.file.copyTo(target)
                RouterCollector.collectJarFile(ji.file)
                println("----ji.file-----${ji.file.name}-------")
            }
        }
        println("$name all mapping class name = ${RouterCollector.getMappingClass()}")

        // 将生成的字节码，写入本地文件
        val mappingJarFile = transformInvocation?.outputProvider?.getContentLocation(
            "router-mapping",
            outputTypes,
            scopes,
            Format.JAR
        )
        println("$mappingJarFile--------")
        if (mappingJarFile!!.parentFile!!.exists()) {
            mappingJarFile.mkdir()
        }
        if (mappingJarFile!!.exists()) {
            mappingJarFile.delete()
        }

        val fos = FileOutputStream(mappingJarFile)
        val jos = JarOutputStream(fos)
        val entry = ZipEntry(RouterByteCode.CLASS_NAME + ".class")
        jos.putNextEntry(entry)
        jos.write(RouterByteCode().get(RouterCollector.getMappingClass()))
        jos.closeEntry()
        fos.close()
    }
}