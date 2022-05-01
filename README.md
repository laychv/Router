# Router

此项目为了学习Gradle而产生，收集注解页面的路由信息，输出日志到项目文件夹。将会用到自定义注解，自定义注解处理器，自定义Plugin，自定义Transform等技术。

## 自定义注解

目前自定义注解作用于Activity页面注解信息。

## 自定义注解处理器

- 收集注解信息；
-
编译期间生成kt文件，文件位置：app/build/temp/kapt3/classes/debug/com.laychv.router.mapping/RouterMapping_xxxx.kt；
- 根目录下创建router-mapping文件夹，将收集到的注解信息写入xxx.json文件。

## 自定义Plugin

自定义Plugin：基于Gradle7.xAPI，这里通过buildSrc的方式，并非独立脚本方式，这个方式有个缺点：无法发布插件，优点：默认文件夹优先加载。
自定义Extension：这里只是在控制台输出项目路径，与Win，Linux略有不同。

## 自定义Transform

主要使用ASM字节码。 依据Google官方文档，Transform API会在AGP8.0中完全移除。

## 自定义shell

适用于linux/unix

- 运行sh脚本
    - 创建run.sh
    - sh run.sh

### 注意

buildSrc/独立插件 区别？ 
- buildSrc：默认文件夹，仅限项目使用  
- 独立插件：通常发布插件使用

独立插件注意事项
```
compileOnly("com.android.tools.build:gradle:7.1.1")// 只能使用compileOnly
```

### 关于Transform废弃问题 

(Gradle方案)[https://docs.gradle.org/current/userguide/artifact_transforms.html]

### 发布插件
(使用 Maven Publish 插件)[https://developer.android.com/studio/build/maven-publish-plugin?hl=zh-cn]

(Maven Publish Plugin)[https://docs.gradle.org/current/userguide/publishing_maven.html]