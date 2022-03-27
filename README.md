# Router

Gradle-Router-KTS版，实现页面路由。

## 自定义Gradle插件

## 自定义Extension

## 自定义独立插件

## 发布独立插件

## 自定义注解

问题

```
> Configure project :router-annotations
WARNING: Unsupported Kotlin plugin version.
The `embedded-kotlin` and `kotlin-dsl` plugins rely on features of Kotlin `1.5.21` that might work differently than in the requested version `1.6.10`.

FAILURE: Build failed with an exception.

* What went wrong:
Don't know how to compute maven coordinate for artifact 'Gradle API' with component identifier of type 'class org.gradle.internal.component.local.model.OpaqueComponentIdentifier'.
```

原因：引入plugins的问题

```
plugins {
//    `kotlin-dsl`// 引入外部插件时候才会使用
//  `java-library`// java工程使用，implement(project(""))
    `embedded-kotlin`// kotlin工程使用，implement(project(""))
}
```

## 自定义注解处理器


https://docs.gradle.org/current/userguide/custom_plugins.html