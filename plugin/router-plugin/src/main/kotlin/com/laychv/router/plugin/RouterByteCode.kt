package com.laychv.router.plugin

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Opcodes.*

object RouterByteCode : Opcodes {

    const val CLASS_NAME = "com/laychv/router/mapping/generated/RouterMapping"

    fun get(mappingName: Set<String>): ByteArray {
        val classWriter = ClassWriter(ClassWriter.COMPUTE_MAXS)
        classWriter.visit(
            V1_8,
            ACC_PUBLIC or ACC_FINAL or ACC_SUPER,
            CLASS_NAME,
            null,
            "java/lang/Object",
            null
        )

        classWriter.visitSource(CLASS_NAME, null)

        var annotationVisitor0 = classWriter.visitAnnotation("Lkotlin/Metadata;", true)
        annotationVisitor0.visit("mv", intArrayOf(1, 6, 0))
        annotationVisitor0.visit("k", 1)
        annotationVisitor0.visit("xi", 48)

        val annotationVisitor2 = annotationVisitor0.visitArray("d1")
        annotationVisitor2.visit(
            null,
            "\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004\u00a8\u0006\u0006"
        )
        annotationVisitor2.visitEnd()

        val annotationVisitor1 = annotationVisitor0.visitArray("d2")
        annotationVisitor1.visit(null, CLASS_NAME)
        annotationVisitor1.visit(null, "")
        annotationVisitor1.visit(null, "()V")
        annotationVisitor1.visit(null, "get")
        annotationVisitor1.visit(null, "")
        annotationVisitor1.visit(null, "")
        annotationVisitor1.visit(null, "app_debug")
        annotationVisitor1.visitEnd()
        annotationVisitor0.visitEnd()

        var methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null)
        methodVisitor.visitCode()
        methodVisitor.visitVarInsn(ALOAD, 0)
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false)
        methodVisitor.visitInsn(RETURN)
        methodVisitor.visitMaxs(1, 1)
        methodVisitor.visitEnd()

        methodVisitor = classWriter.visitMethod(
            ACC_PUBLIC or ACC_FINAL,
            "get",
            "()Ljava/util/Map;",
            "()Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;",
            null
        )
        annotationVisitor0 =
            methodVisitor.visitAnnotation("Lorg/jetbrains/annotations/NotNull;", false)
        annotationVisitor0.visitEnd()

        methodVisitor.visitCode()

        methodVisitor.visitTypeInsn(NEW, "java/util/HashMap");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/HashMap", "<init>", "()V", false);
        methodVisitor.visitVarInsn(ASTORE, 1);

        // 向Map中，逐个塞入所有映射表的内容
        mappingName.forEach {
            methodVisitor.visitVarInsn(ALOAD, 1)
            methodVisitor.visitFieldInsn(
                GETSTATIC, CLASS_NAME,
                "INSTANCE", null
            )
            methodVisitor.visitMethodInsn(
                INVOKEVIRTUAL,
                "com/laychv/router/mapping/$it",
                "get", "()Ljava/util/Map;", false
            )
            methodVisitor.visitMethodInsn(
                INVOKEVIRTUAL,
                "java/util/Map",
                "putAll",
                "(Ljava/util/Map;)V", false
            )
        }
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitTypeInsn(CHECKCAST, "java/util/Map");
        methodVisitor.visitInsn(ARETURN);
        methodVisitor.visitLocalVariable("mapping", "Ljava/util/HashMap;", null, null, null, 1);
        methodVisitor.visitLocalVariable(
            "this",
            null,
            null,
            null,
            null,
            0
        )
        methodVisitor.visitInsn(ARETURN)
        methodVisitor.visitMaxs(2, 2)
        methodVisitor.visitEnd()

        classWriter.visitEnd()

        return classWriter.toByteArray()
    }

}