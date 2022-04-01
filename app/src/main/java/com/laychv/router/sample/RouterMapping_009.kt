package com.laychv.router.sample

class RouterMapping_009 {

    fun get(): Map<String, String> {
        val mapping = HashMap<String, String>()
        mapping["router://page-home"] = "com.laychv.router.MainActivity"
        mapping["router://page-test"] = "com.laychv.router.TestActivity"
        return mapping
    }
}
