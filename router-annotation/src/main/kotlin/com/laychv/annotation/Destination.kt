package com.laychv.annotation

@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class Destination(val url: String, val description: String)