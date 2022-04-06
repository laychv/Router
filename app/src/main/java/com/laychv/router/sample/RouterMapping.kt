package com.laychv.router.sample

/**
 * 生成字节码的模版
 */
class RouterMapping {
    fun get(): Map<String, String> {
        val mapping = HashMap<String, String>()
        mapping.putAll(RouterMapping_1.get())
        mapping.putAll(RouterMapping_2.get())
        return mapping
    }
}