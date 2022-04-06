package com.laychv.processor

import com.google.auto.service.AutoService
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.laychv.annotation.Destination
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.tools.StandardLocation

/**
 * 生成kt文件，json文件
 */
@AutoService(Processor::class)
class DestinationProcessor : AbstractProcessor() {

    private val TAG = "DestinationProcessor"

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        println("为啥没有日志输出呢！！！！")
        return Collections.singleton(Destination::class.java.canonicalName)
    }

    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
        println("$TAG >>>> processor start... <<<<")
        // 多次输出，无效！！！
        if (p1!!.processingOver()) {
            return false
        }

        // 拿到kapt的传参路径
        val rootProjectDir = processingEnv.options["root_project_dir"]
        println("------看到表示获取到路径：$rootProjectDir")

        val className = "RouterMapping_" + System.currentTimeMillis()

        val sb = StringBuilder()
        sb.append("package com.laychv.router.mapping\n\n")
        sb.append("object $className {\n\n")
        sb.append("    fun get(): Map<String, String> {\n")
        sb.append("        val mapping = HashMap<String, String>()\n")

        // 获取注解的所有信息，即Destination的所有信息
        val elements = p1.getElementsAnnotatedWith(Destination::class.java) as Set<Element>
        if (elements.isEmpty()) {
            return false
        }
        println("$TAG >>>> all element size : ${elements.size}")
        val jsonArray = JsonArray()
        for (element in elements) {
            val typeElement = element as TypeElement
            val destination = typeElement.getAnnotation(Destination::class.java) ?: continue
            val realPath = typeElement.qualifiedName.toString()// 获取真实的路径
            val url = destination.url
            val description = destination.description
            println("$TAG >> processor-url: $url")
            println("$TAG >> processor-description: $description")
            println("$TAG >> processor-realPath: $realPath")

            sb.append("        mapping[\"${url}\"]" + " = " + "\"${realPath}\"\n")
            val item = JsonObject()
            item.addProperty("url", url)
            item.addProperty("description", description)
            item.addProperty("realPath", realPath)
            jsonArray.add(item)
        }
        sb.append("        return mapping\n")
        sb.append("    }\n")
        sb.append("}\n")

        val packageName = "com.laychv.router.mapping"
        val fullPath = "com.laychv.router.mapping.$className"
        println("$TAG > $fullPath")
        println("$TAG > $sb\n")

        // 写入build文件
        try {
//            val file = processingEnv.filer.createSourceFile(fullPath)// java 💁🏻‍️
            // createResource(输出类型，包名，文件名)
            val file = processingEnv.filer.createResource(
                StandardLocation.CLASS_OUTPUT,
                packageName,
                "${className}.kt"
            )
            val writer = file.openWriter()
            writer.write(sb.toString())
            writer.flush()
            writer.close()
            println(">>>>>>> show file name: ${file.name}")
        } catch (e: Exception) {
            println("$TAG >>>>>>>>>> Error: $e")
        }

        // 写入json文件
        if (rootProjectDir == null) {
            throw RuntimeException("获取项目根目录失败！！！")
        }
        val file = File(rootProjectDir)
        if (!file.exists()) {
            throw RuntimeException("根目录不存在！！！")
        }
        // 创建子目录
        val fileDir = File(file, "router-mapping")
        if (!fileDir.exists()) {
            fileDir.mkdir()
        }
        // 创建json文件
        val jsonFile = File(fileDir, "mapping_" + System.currentTimeMillis() + ".json")
        try {
            val writer = BufferedWriter(FileWriter(jsonFile))
            writer.write(jsonArray.toString())
            writer.flush()
            writer.close()
        } catch (e: Exception) {
            println("$TAG    >>>>>> json 文件错误：$e")
        }

        println("$TAG >>>> processor end... <<<<")

        return false
    }
}