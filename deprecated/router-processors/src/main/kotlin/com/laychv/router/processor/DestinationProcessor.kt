package com.laychv.router.processor

import com.google.auto.service.AutoService
import com.laychv.router.annotations.Destination
import java.util.*
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement

/**
 * 自定义注解处理器
 */
@AutoService(Process::class)
class DestinationProcessor : AbstractProcessor() {

    private val TAG = "DestinationProcessor"

    /**
     * 告诉注解处理器，处理的目标注解
     * 使用的是集合，输入多个注解
     * 这里就一个注解Destination，
     */
    override fun getSupportedAnnotationTypes(): Set<String> {
        return Collections.singleton(Destination::class.java.canonicalName)
//        return setOf(Destination::class.java.canonicalName)
    }

    /**
     * 获取注解及其属性
     */
    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
        println("$TAG >>>> processor start...")
        if (p1!!.processingOver()) {
            return false
        }
        // 获取注解的所有信息，即Destination的所有信息
        val elements = p1?.getElementsAnnotatedWith(Destination::class.java) as Set<Element>
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
        println("$TAG >>>> processor end...")

        return false
    }
}