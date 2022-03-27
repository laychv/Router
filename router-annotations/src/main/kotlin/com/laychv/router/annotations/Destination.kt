package com.laychv.router.annotations

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class Destination(
    val url: String,
    val description: String
)
