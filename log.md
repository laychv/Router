gradle :app:assembleDebug

未来遇到的问题AGP8.0Transform彻底移除

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

建错项目，如下，依然有问题

```
plugins {
//    `kotlin-dsl`// 引入外部插件时候才会使用
//  `java-library`// java工程使用，implement(project(""))
    `embedded-kotlin`// kotlin工程使用，implement(project(""))
}
```

## 自定义注解处理器

依赖

```
plugins {
  kotlin("kapt")
}
dependencies {
  val autoService = "1.0-rc7"
  kapt("com.google.auto.service", "auto-service", autoService)
  compileOnly("com.google.auto.service", "auto-service-annotations", autoService)
}
```

## 遇到的问题

错误的添加依赖，错误的使用kapt，导致无法输出控制台日志


## 遗留的问题

- plugin中无法动态获取kapt参数
- 生成jar包失败，不确定是否和kapt有关

https://docs.gradle.org/current/userguide/custom_plugins.html