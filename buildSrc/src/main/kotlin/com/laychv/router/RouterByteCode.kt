package com.laychv.router

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Opcodes.*


class RouterByteCode : Opcodes {

    companion object {
        const val CLASS_NAME = "com/laychv/router/mapping/generated/RouterMapping"
    }

    fun get(mappingName: Set<String>): ByteArray {
        val cw = ClassWriter(ClassWriter.COMPUTE_MAXS)
        cw.visit(V1_8, ACC_PUBLIC + ACC_SUPER, CLASS_NAME, null, "java/lang/Object", null)
        var vm = cw.visitMethod(ACC_PUBLIC, CLASS_NAME, null, null, null)
        vm.visitCode()
        vm.visitVarInsn(ALOAD, 0)
        vm.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false)
        vm.visitInsn(RETURN)
        vm.visitMaxs(1, 1)
        vm.visitEnd()

        // 创建get方法
        vm = cw.visitMethod(
            ACC_PUBLIC + ACC_STATIC,
            "get",
            "()Ljava/util/Map;",
            "()Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;",
            null
        )

        vm.visitCode()

        vm.visitTypeInsn(NEW, "java/util/HashMap")
        vm.visitInsn(DUP)
        vm.visitMethodInsn(
            INVOKESPECIAL,
            "java/util/HashMap", "<init>", "()V", false
        )
        vm.visitVarInsn(ASTORE, 0)

        // 向Map中，逐个塞入所有映射表的内容
        mappingName.forEach {
            vm.visitVarInsn(ALOAD, 0)
            vm.visitMethodInsn(
                INVOKESTATIC,
                "com/imooc/router/mapping/$it",
                "get", "()Ljava/util/Map;", false
            )
            vm.visitMethodInsn(
                INVOKEINTERFACE,
                "java/util/Map",
                "putAll",
                "(Ljava/util/Map;)V", true
            )
        }

        // 返回map
        vm.visitVarInsn(ALOAD, 0)
        vm.visitInsn(ARETURN)
        vm.visitMaxs(2, 2)

        vm.visitEnd()
        return cw.toByteArray()
    }

}