package com.laychv.router.plugin;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Map;
import java.util.Set;

public class RouterMappingDump implements Opcodes {

    public static byte[] get(Set<String> allMappingNames) throws Exception {

        ClassWriter classWriter = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        MethodVisitor methodVisitor;
        AnnotationVisitor annotationVisitor0;

        classWriter.visit(V1_8, ACC_PUBLIC | ACC_FINAL | ACC_SUPER, "com/laychv/router/sample/RouterMapping", null, "java/lang/Object", null);

        classWriter.visitSource("RouterMapping.kt", null);

        {
            annotationVisitor0 = classWriter.visitAnnotation("Lkotlin/Metadata;", true);
            annotationVisitor0.visit("mv", new int[]{1, 6, 0});
            annotationVisitor0.visit("k", new Integer(1));
            annotationVisitor0.visit("xi", new Integer(48));
            {
                AnnotationVisitor annotationVisitor1 = annotationVisitor0.visitArray("d1");
                annotationVisitor1.visit(null, "\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004\u00a8\u0006\u0006");
                annotationVisitor1.visitEnd();
            }
            {
                AnnotationVisitor annotationVisitor1 = annotationVisitor0.visitArray("d2");
                annotationVisitor1.visit(null, "Lcom/laychv/router/sample/RouterMapping;");
                annotationVisitor1.visit(null, "");
                annotationVisitor1.visit(null, "()V");
                annotationVisitor1.visit(null, "get");
                annotationVisitor1.visit(null, "");
                annotationVisitor1.visit(null, "");
                annotationVisitor1.visit(null, "app_debug");
                annotationVisitor1.visitEnd();
            }
            annotationVisitor0.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(6, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lcom/laychv/router/sample/RouterMapping;", null, label0, label1, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_FINAL, "get", "()Ljava/util/Map;", "()Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;", null);
            {
                annotationVisitor0 = methodVisitor.visitAnnotation("Lorg/jetbrains/annotations/NotNull;", false);
                annotationVisitor0.visitEnd();
            }
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(8, label0);
            methodVisitor.visitTypeInsn(NEW, "java/util/HashMap");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/HashMap", "<init>", "()V", false);
            methodVisitor.visitVarInsn(ASTORE, 1);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(9, label1);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitFieldInsn(GETSTATIC, "com/laychv/router/sample/RouterMapping_1", "INSTANCE", "Lcom/laychv/router/sample/RouterMapping_1;");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/laychv/router/sample/RouterMapping_1", "get", "()Ljava/util/Map;", false);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/HashMap", "putAll", "(Ljava/util/Map;)V", false);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(10, label2);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitFieldInsn(GETSTATIC, "com/laychv/router/sample/RouterMapping_2", "INSTANCE", "Lcom/laychv/router/sample/RouterMapping_2;");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/laychv/router/sample/RouterMapping_2", "get", "()Ljava/util/Map;", false);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/HashMap", "putAll", "(Ljava/util/Map;)V", false);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLineNumber(11, label3);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitTypeInsn(CHECKCAST, "java/util/Map");
            methodVisitor.visitInsn(ARETURN);
            Label label4 = new Label();
            methodVisitor.visitLabel(label4);
            methodVisitor.visitLocalVariable("mapping", "Ljava/util/HashMap;", null, label1, label4, 1);
            methodVisitor.visitLocalVariable("this", "Lcom/laychv/router/sample/RouterMapping;", null, label0, label4, 0);
            methodVisitor.visitMaxs(2, 2);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();

        return classWriter.toByteArray();
    }
}
