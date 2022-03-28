package com.laychv.processor

import com.google.auto.service.AutoService
import com.laychv.annotation.Destination
import java.util.*
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
class DestinationProcessor : AbstractProcessor() {

    private val TAG = "DestinationProcessor"

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
        // 获取注解的所有信息，即Destination的所有信息
        val elements = p1.getElementsAnnotatedWith(Destination::class.java) as Set<Element>
        if (elements.isEmpty()) {
            return false
        }
        println("$TAG >>>> all element size : ${elements.size}")
        for (element in elements) {
            val typeElement = element as TypeElement
            val destination = typeElement.getAnnotation(Destination::class.java) ?: continue
            val realPath = typeElement.qualifiedName.toString()// 获取真实的路径
            val url = destination.url
            val description = destination.description
            println("$TAG >> processor-url: $url")
            println("$TAG >> processor-description: $description")
            println("$TAG >> processor-realPath: $realPath")
        }
        println("$TAG >>>> processor end... <<<<")

        return false

    }
}