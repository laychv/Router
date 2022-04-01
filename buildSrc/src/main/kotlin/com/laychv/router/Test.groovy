package com.laychv.router

import com.android.build.gradle.AppPlugin
import com.laychv.router.RouterExtension
import groovy.json.JsonSlurper
import org.gradle.api.Plugin
import org.gradle.api.Project

class Test implements Plugin<Project> {

    //  Realization apply Method , Inject plug-in logic
    void apply(Project project) {

        //  register  Transform
        if (project.plugins.hasPlugin(AppPlugin)) {

//            AppExtension appExtension = project.extensions.getByType(AppExtension)
//            Transform transform = new RouterMappingTransform()
//            appExtension.registerTransform(transform)
        }

        // 1.  Automatically help users pass path parameters to the annotation processor
        if (project.extensions.findByName("kapt") != null) {
            project.extensions.findByName("kapt").arguments {
                arg("root_project_dir", project.rootProject.projectDir.absolutePath)
            }
        }

        // 2.  Realize the automatic cleaning of old build products
        project.clean.doFirst {

            //  Delete   Generated from the last build  router_mapping Catalog
            File routerMappingDir = new File(project.rootProject.projectDir, "router_mapping")
            if (routerMappingDir.exists()) {

                routerMappingDir.deleteDir()
            }

        }

        if (!project.plugins.hasPlugin(AppPlugin)) {

            return
        }
        println("I am from RouterPlugin, apply from ${project.name}")
        //  To obtain parameters
        project.getExtensions().create("router", RouterExtension)
        project.afterEvaluate {

            RouterExtension extension = project["router"]
            println(" User set WIKI Path is  : ${extension.wikiDir}")
            // 3.  stay javac Mission  (compileDebugJavaWithJavac)  after , Summarize and generate documents
            project.tasks.findAll {
                task ->
                    task.name.startsWith('compile') && task.name.endsWith('JavaWithJavac')
            }.each {
                task ->

                    task.doLast {


                        File routerMappingDir = new File(project.rootProject.projectDir, "router_mapping")

                        if (!routerMappingDir.exists()) {

                            return
                        }

                        File[] allChildFiles = routerMappingDir.listFiles()

                        if (allChildFiles.length < 1) {

                            return
                        }

                        //  Generate page documents
                        StringBuilder markdownBuilder = new StringBuilder()
                        markdownBuilder.append("#  Page document \n\n")
                        allChildFiles.each {
                            child ->
                                if (child.name.endsWith(".json")) {

                                    JsonSlurper jsonSlurper = new JsonSlurper()
                                    def content = jsonSlurper.parse(child)
                                    content.each {
                                        innerContent ->
                                            def url = innerContent['url']
                                            def description = innerContent['description']
                                            def realPath = innerContent['realPath']

                                            markdownBuilder.append("## $description \n")
                                            markdownBuilder.append("- url: $url \n")
                                            markdownBuilder.append("- realPath: $realPath \n\n")
                                    }
                                }
                        }

                        File wikiFileDir = new File(extension.wikiDir)
                        if (!wikiFileDir.exists()) {

                            wikiFileDir.mkdir()
                        }

                        File wikiFile = new File(wikiFileDir, " Page document .md")
                        if (wikiFile.exists()) {

                            wikiFile.delete()
                        }
                        wikiFile.write(markdownBuilder.toString())
                    }
            }
        }
    }

}

