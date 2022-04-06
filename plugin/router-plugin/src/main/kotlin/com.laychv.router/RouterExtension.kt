package com.laychv.router

import org.gradle.api.provider.Property

/**
 * abstract
 */
abstract class RouterExtension {

    abstract val wikiDir: Property<String>

    init {}

    /**
     * 三个方法区别
     */
    fun getPath(v: String): String {
//        wikiDir.set("value")
//        wikiDir.convention("laychv-009").get()
        return wikiDir.value(v).get()
    }

}