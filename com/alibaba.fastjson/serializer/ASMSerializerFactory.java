package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.asm.ClassWriter;
import com.alibaba.fastjson.asm.FieldWriter;
import com.alibaba.fastjson.asm.Label;
import com.alibaba.fastjson.asm.MethodVisitor;
import com.alibaba.fastjson.asm.MethodWriter;
import com.alibaba.fastjson.asm.Opcodes;
import com.alibaba.fastjson.asm.Type;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.ASMClassLoader;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.text.Typography;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes.dex */
public class ASMSerializerFactory implements Opcodes {
    protected final ASMClassLoader classLoader = new ASMClassLoader();
    private final AtomicLong seed = new AtomicLong();
    static final String JSONSerializer = ASMUtils.type(JSONSerializer.class);
    static final String ObjectSerializer = ASMUtils.type(ObjectSerializer.class);
    static final String ObjectSerializer_desc = "L" + ObjectSerializer + ";";
    static final String SerializeWriter = ASMUtils.type(SerializeWriter.class);
    static final String SerializeWriter_desc = "L" + SerializeWriter + ";";
    static final String JavaBeanSerializer = ASMUtils.type(JavaBeanSerializer.class);
    static final String JavaBeanSerializer_desc = "L" + ASMUtils.type(JavaBeanSerializer.class) + ";";
    static final String SerialContext_desc = ASMUtils.desc((Class<?>) SerialContext.class);
    static final String SerializeFilterable_desc = ASMUtils.desc((Class<?>) SerializeFilterable.class);

    static class Context {
        static final int features = 5;
        static int fieldName = 6;
        static final int obj = 2;
        static int original = 7;
        static final int paramFieldName = 3;
        static final int paramFieldType = 4;
        static int processValue = 8;
        static final int serializer = 1;
        private final SerializeBeanInfo beanInfo;
        private final String className;
        private final FieldInfo[] getters;
        private final boolean nonContext;
        private final boolean writeDirect;
        private Map<String, Integer> variants = new HashMap();
        private int variantIndex = 9;

        public Context(FieldInfo[] fieldInfoArr, SerializeBeanInfo serializeBeanInfo, String str, boolean z, boolean z2) {
            this.getters = fieldInfoArr;
            this.className = str;
            this.beanInfo = serializeBeanInfo;
            this.writeDirect = z;
            this.nonContext = z2 || serializeBeanInfo.beanType.isEnum();
        }

        public int var(String str) {
            if (this.variants.get(str) == null) {
                Map<String, Integer> map = this.variants;
                int i = this.variantIndex;
                this.variantIndex = i + 1;
                map.put(str, Integer.valueOf(i));
            }
            return this.variants.get(str).intValue();
        }

        public int var(String str, int i) {
            if (this.variants.get(str) == null) {
                this.variants.put(str, Integer.valueOf(this.variantIndex));
                this.variantIndex += i;
            }
            return this.variants.get(str).intValue();
        }

        public int getFieldOrinal(String str) {
            int length = this.getters.length;
            for (int i = 0; i < length; i++) {
                if (this.getters[i].name.equals(str)) {
                    return i;
                }
            }
            return -1;
        }
    }

    public JavaBeanSerializer createJavaBeanSerializer(SerializeBeanInfo serializeBeanInfo) throws Exception {
        boolean z;
        FieldInfo[] fieldInfoArr;
        String str;
        String str2;
        boolean z2;
        boolean z3;
        String str3;
        boolean z4;
        boolean z5;
        String str4;
        JSONType jSONType;
        boolean z6;
        char c2;
        ClassWriter classWriter;
        int i;
        Class<?> cls = serializeBeanInfo.beanType;
        if (cls.isPrimitive()) {
            throw new JSONException("unsupportd class " + cls.getName());
        }
        JSONType jSONType2 = (JSONType) TypeUtils.getAnnotation(cls, JSONType.class);
        FieldInfo[] fieldInfoArr2 = serializeBeanInfo.fields;
        for (FieldInfo fieldInfo : fieldInfoArr2) {
            if (fieldInfo.field == null && fieldInfo.method != null && fieldInfo.method.getDeclaringClass().isInterface()) {
                return new JavaBeanSerializer(cls);
            }
        }
        FieldInfo[] fieldInfoArr3 = serializeBeanInfo.sortedFields;
        boolean z7 = serializeBeanInfo.sortedFields == serializeBeanInfo.fields;
        if (fieldInfoArr3.length > 256) {
            return new JavaBeanSerializer(cls);
        }
        for (FieldInfo fieldInfo2 : fieldInfoArr3) {
            if (!ASMUtils.checkName(fieldInfo2.getMember().getName())) {
                return new JavaBeanSerializer(cls);
            }
        }
        String str5 = "ASMSerializer_" + this.seed.incrementAndGet() + "_" + cls.getSimpleName();
        String name = ASMSerializerFactory.class.getPackage().getName();
        String str6 = name.replace(FilenameUtils.EXTENSION_SEPARATOR, IOUtils.DIR_SEPARATOR_UNIX) + "/" + str5;
        String str7 = name + "." + str5;
        ClassWriter classWriter2 = new ClassWriter();
        classWriter2.visit(49, 33, str6, JavaBeanSerializer, new String[]{ObjectSerializer});
        int length = fieldInfoArr3.length;
        int i2 = 0;
        while (i2 < length) {
            FieldInfo fieldInfo3 = fieldInfoArr3[i2];
            if (fieldInfo3.fieldClass.isPrimitive() || fieldInfo3.fieldClass == String.class) {
                i = length;
            } else {
                i = length;
                new FieldWriter(classWriter2, 1, fieldInfo3.name + "_asm_fieldType", "Ljava/lang/reflect/Type;").visitEnd();
                if (List.class.isAssignableFrom(fieldInfo3.fieldClass)) {
                    new FieldWriter(classWriter2, 1, fieldInfo3.name + "_asm_list_item_ser_", ObjectSerializer_desc).visitEnd();
                }
                new FieldWriter(classWriter2, 1, fieldInfo3.name + "_asm_ser_", ObjectSerializer_desc).visitEnd();
            }
            i2++;
            length = i;
        }
        MethodWriter methodWriter = new MethodWriter(classWriter2, 1, "<init>", "(" + ASMUtils.desc((Class<?>) SerializeBeanInfo.class) + ")V", null, null);
        int i3 = 25;
        methodWriter.visitVarInsn(25, 0);
        methodWriter.visitVarInsn(25, 1);
        methodWriter.visitMethodInsn(183, JavaBeanSerializer, "<init>", "(" + ASMUtils.desc((Class<?>) SerializeBeanInfo.class) + ")V");
        int i4 = 0;
        while (i4 < fieldInfoArr3.length) {
            FieldInfo fieldInfo4 = fieldInfoArr3[i4];
            if (fieldInfo4.fieldClass.isPrimitive() || fieldInfo4.fieldClass == String.class) {
                classWriter = classWriter2;
            } else {
                methodWriter.visitVarInsn(i3, 0);
                if (fieldInfo4.method != null) {
                    methodWriter.visitLdcInsn(Type.getType(ASMUtils.desc(fieldInfo4.declaringClass)));
                    methodWriter.visitLdcInsn(fieldInfo4.method.getName());
                    classWriter = classWriter2;
                    methodWriter.visitMethodInsn(184, ASMUtils.type(ASMUtils.class), "getMethodType", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Type;");
                } else {
                    classWriter = classWriter2;
                    methodWriter.visitVarInsn(i3, 0);
                    methodWriter.visitLdcInsn(Integer.valueOf(i4));
                    methodWriter.visitMethodInsn(183, JavaBeanSerializer, "getFieldType", "(I)Ljava/lang/reflect/Type;");
                }
                methodWriter.visitFieldInsn(181, str6, fieldInfo4.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
            }
            i4++;
            classWriter2 = classWriter;
            i3 = 25;
        }
        ClassWriter classWriter3 = classWriter2;
        methodWriter.visitInsn(177);
        methodWriter.visitMaxs(4, 4);
        methodWriter.visitEnd();
        if (jSONType2 != null) {
            for (SerializerFeature serializerFeature : jSONType2.serialzeFeatures()) {
                if (serializerFeature == SerializerFeature.DisableCircularReferenceDetect) {
                    z = true;
                    break;
                }
            }
            z = false;
        } else {
            z = false;
        }
        int i5 = 0;
        while (true) {
            fieldInfoArr = fieldInfoArr2;
            if (i5 >= 3) {
                break;
            }
            if (i5 == 0) {
                str3 = "write";
                z5 = z;
                z4 = true;
            } else if (i5 == 1) {
                str3 = "writeNormal";
                z5 = z;
                z4 = false;
            } else {
                str3 = "writeDirectNonContext";
                z4 = true;
                z5 = true;
            }
            ClassWriter classWriter4 = classWriter3;
            String str8 = str7;
            String str9 = str6;
            Context context = new Context(fieldInfoArr3, serializeBeanInfo, str6, z4, z5);
            int i6 = i5;
            MethodWriter methodWriter2 = new MethodWriter(classWriter3, 1, str3, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V", null, new String[]{"java/io/IOException"});
            Label label = new Label();
            methodWriter2.visitVarInsn(25, 2);
            methodWriter2.visitJumpInsn(Opcodes.IFNONNULL, label);
            methodWriter2.visitVarInsn(25, 1);
            methodWriter2.visitMethodInsn(182, JSONSerializer, "writeNull", "()V");
            methodWriter2.visitInsn(177);
            methodWriter2.visitLabel(label);
            methodWriter2.visitVarInsn(25, 1);
            methodWriter2.visitFieldInsn(180, JSONSerializer, "out", SerializeWriter_desc);
            methodWriter2.visitVarInsn(58, context.var("out"));
            if (z7 || context.writeDirect || !(jSONType2 == null || jSONType2.alphabetic())) {
                str4 = str9;
            } else {
                Label label2 = new Label();
                methodWriter2.visitVarInsn(25, context.var("out"));
                methodWriter2.visitMethodInsn(182, SerializeWriter, "isSortField", "()Z");
                methodWriter2.visitJumpInsn(154, label2);
                methodWriter2.visitVarInsn(25, 0);
                methodWriter2.visitVarInsn(25, 1);
                methodWriter2.visitVarInsn(25, 2);
                methodWriter2.visitVarInsn(25, 3);
                methodWriter2.visitVarInsn(25, 4);
                methodWriter2.visitVarInsn(21, 5);
                str4 = str9;
                methodWriter2.visitMethodInsn(182, str4, "writeUnsorted", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                methodWriter2.visitInsn(177);
                methodWriter2.visitLabel(label2);
            }
            if (!context.writeDirect || z5) {
                jSONType = jSONType2;
                z6 = z7;
                c2 = 4;
            } else {
                Label label3 = new Label();
                Label label4 = new Label();
                methodWriter2.visitVarInsn(25, 0);
                methodWriter2.visitVarInsn(25, 1);
                String str10 = JavaBeanSerializer;
                StringBuilder sb = new StringBuilder();
                sb.append("(L");
                jSONType = jSONType2;
                sb.append(JSONSerializer);
                sb.append(";)Z");
                z6 = z7;
                methodWriter2.visitMethodInsn(182, str10, "writeDirect", sb.toString());
                methodWriter2.visitJumpInsn(154, label4);
                methodWriter2.visitVarInsn(25, 0);
                methodWriter2.visitVarInsn(25, 1);
                methodWriter2.visitVarInsn(25, 2);
                methodWriter2.visitVarInsn(25, 3);
                methodWriter2.visitVarInsn(25, 4);
                methodWriter2.visitVarInsn(21, 5);
                methodWriter2.visitMethodInsn(182, str4, "writeNormal", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                methodWriter2.visitInsn(177);
                methodWriter2.visitLabel(label4);
                methodWriter2.visitVarInsn(25, context.var("out"));
                methodWriter2.visitLdcInsn(Integer.valueOf(SerializerFeature.DisableCircularReferenceDetect.mask));
                methodWriter2.visitMethodInsn(182, SerializeWriter, "isEnabled", "(I)Z");
                methodWriter2.visitJumpInsn(153, label3);
                methodWriter2.visitVarInsn(25, 0);
                methodWriter2.visitVarInsn(25, 1);
                methodWriter2.visitVarInsn(25, 2);
                methodWriter2.visitVarInsn(25, 3);
                c2 = 4;
                methodWriter2.visitVarInsn(25, 4);
                methodWriter2.visitVarInsn(21, 5);
                methodWriter2.visitMethodInsn(182, str4, "writeDirectNonContext", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                methodWriter2.visitInsn(177);
                methodWriter2.visitLabel(label3);
            }
            methodWriter2.visitVarInsn(25, 2);
            methodWriter2.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(cls));
            methodWriter2.visitVarInsn(58, context.var("entity"));
            generateWriteMethod(cls, methodWriter2, fieldInfoArr3, context);
            methodWriter2.visitInsn(177);
            methodWriter2.visitMaxs(7, context.variantIndex + 2);
            methodWriter2.visitEnd();
            i5 = i6 + 1;
            str6 = str4;
            jSONType2 = jSONType;
            z7 = z6;
            fieldInfoArr2 = fieldInfoArr;
            classWriter3 = classWriter4;
            str7 = str8;
        }
        String str11 = str7;
        String str12 = str6;
        ClassWriter classWriter5 = classWriter3;
        int i7 = 3;
        if (z7) {
            str = str12;
        } else {
            str = str12;
            Context context2 = new Context(fieldInfoArr3, serializeBeanInfo, str12, false, z);
            i7 = 3;
            MethodWriter methodWriter3 = new MethodWriter(classWriter5, 1, "writeUnsorted", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V", null, new String[]{"java/io/IOException"});
            methodWriter3.visitVarInsn(25, 1);
            methodWriter3.visitFieldInsn(180, JSONSerializer, "out", SerializeWriter_desc);
            methodWriter3.visitVarInsn(58, context2.var("out"));
            methodWriter3.visitVarInsn(25, 2);
            methodWriter3.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(cls));
            methodWriter3.visitVarInsn(58, context2.var("entity"));
            generateWriteMethod(cls, methodWriter3, fieldInfoArr, context2);
            methodWriter3.visitInsn(177);
            methodWriter3.visitMaxs(7, context2.variantIndex + 2);
            methodWriter3.visitEnd();
        }
        int i8 = 0;
        while (i8 < i7) {
            if (i8 == 0) {
                str2 = "writeAsArray";
                z3 = z;
                z2 = true;
            } else if (i8 == 1) {
                str2 = "writeAsArrayNormal";
                z3 = z;
                z2 = false;
            } else {
                str2 = "writeAsArrayNonContext";
                z2 = true;
                z3 = true;
            }
            Context context3 = new Context(fieldInfoArr3, serializeBeanInfo, str, z2, z3);
            MethodWriter methodWriter4 = new MethodWriter(classWriter5, 1, str2, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V", null, new String[]{"java/io/IOException"});
            methodWriter4.visitVarInsn(25, 1);
            methodWriter4.visitFieldInsn(180, JSONSerializer, "out", SerializeWriter_desc);
            methodWriter4.visitVarInsn(58, context3.var("out"));
            methodWriter4.visitVarInsn(25, 2);
            methodWriter4.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(cls));
            methodWriter4.visitVarInsn(58, context3.var("entity"));
            generateWriteAsArray(cls, methodWriter4, fieldInfoArr3, context3);
            methodWriter4.visitInsn(177);
            methodWriter4.visitMaxs(7, context3.variantIndex + 2);
            methodWriter4.visitEnd();
            i8++;
            i7 = i7;
        }
        byte[] byteArray = classWriter5.toByteArray();
        return (JavaBeanSerializer) this.classLoader.defineClassPublic(str11, byteArray, 0, byteArray.length).getConstructor(SerializeBeanInfo.class).newInstance(serializeBeanInfo);
    }

    private void generateWriteAsArray(Class<?> cls, MethodVisitor methodVisitor, FieldInfo[] fieldInfoArr, Context context) throws Exception {
        String str;
        int i;
        int i2;
        String str2;
        int i3;
        int i4;
        ASMSerializerFactory aSMSerializerFactory;
        char c2;
        int i5;
        java.lang.reflect.Type type;
        Class<?> cls2;
        int i6;
        Label label;
        Label label2;
        int i7;
        int i8;
        String str3;
        int i9;
        ASMSerializerFactory aSMSerializerFactory2 = this;
        FieldInfo[] fieldInfoArr2 = fieldInfoArr;
        String str4 = "out";
        int i10 = 25;
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(16, 91);
        String str5 = "(I)V";
        int i11 = 182;
        methodVisitor.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
        int length = fieldInfoArr2.length;
        if (length == 0) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(16, 93);
            methodVisitor.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
            return;
        }
        int i12 = 0;
        while (i12 < length) {
            int i13 = i12 == length + (-1) ? 93 : 44;
            FieldInfo fieldInfo = fieldInfoArr2[i12];
            Class<?> cls3 = fieldInfo.fieldClass;
            methodVisitor.visitLdcInsn(fieldInfo.name);
            methodVisitor.visitVarInsn(58, Context.fieldName);
            if (cls3 == Byte.TYPE || cls3 == Short.TYPE || cls3 == Integer.TYPE) {
                str = str4;
                i = i10;
                i2 = length;
                str2 = str5;
                i3 = i11;
                i4 = i12;
                methodVisitor.visitVarInsn(i, context.var(str));
                methodVisitor.visitInsn(89);
                aSMSerializerFactory = this;
                aSMSerializerFactory._get(methodVisitor, context, fieldInfo);
                methodVisitor.visitMethodInsn(i3, SerializeWriter, "writeInt", str2);
                c2 = 16;
                methodVisitor.visitVarInsn(16, i13);
                methodVisitor.visitMethodInsn(i3, SerializeWriter, "write", str2);
            } else {
                if (cls3 == Long.TYPE) {
                    methodVisitor.visitVarInsn(i10, context.var(str4));
                    methodVisitor.visitInsn(89);
                    aSMSerializerFactory2._get(methodVisitor, context, fieldInfo);
                    methodVisitor.visitMethodInsn(i11, SerializeWriter, "writeLong", "(J)V");
                    methodVisitor.visitVarInsn(16, i13);
                    methodVisitor.visitMethodInsn(i11, SerializeWriter, "write", str5);
                } else if (cls3 == Float.TYPE) {
                    methodVisitor.visitVarInsn(i10, context.var(str4));
                    methodVisitor.visitInsn(89);
                    aSMSerializerFactory2._get(methodVisitor, context, fieldInfo);
                    methodVisitor.visitInsn(4);
                    methodVisitor.visitMethodInsn(i11, SerializeWriter, "writeFloat", "(FZ)V");
                    methodVisitor.visitVarInsn(16, i13);
                    methodVisitor.visitMethodInsn(i11, SerializeWriter, "write", str5);
                } else if (cls3 == Double.TYPE) {
                    methodVisitor.visitVarInsn(i10, context.var(str4));
                    methodVisitor.visitInsn(89);
                    aSMSerializerFactory2._get(methodVisitor, context, fieldInfo);
                    methodVisitor.visitInsn(4);
                    methodVisitor.visitMethodInsn(i11, SerializeWriter, "writeDouble", "(DZ)V");
                    methodVisitor.visitVarInsn(16, i13);
                    methodVisitor.visitMethodInsn(i11, SerializeWriter, "write", str5);
                } else if (cls3 == Boolean.TYPE) {
                    methodVisitor.visitVarInsn(i10, context.var(str4));
                    methodVisitor.visitInsn(89);
                    aSMSerializerFactory2._get(methodVisitor, context, fieldInfo);
                    methodVisitor.visitMethodInsn(i11, SerializeWriter, "write", "(Z)V");
                    methodVisitor.visitVarInsn(16, i13);
                    methodVisitor.visitMethodInsn(i11, SerializeWriter, "write", str5);
                } else if (cls3 == Character.TYPE) {
                    methodVisitor.visitVarInsn(i10, context.var(str4));
                    aSMSerializerFactory2._get(methodVisitor, context, fieldInfo);
                    methodVisitor.visitMethodInsn(184, "java/lang/Character", "toString", "(C)Ljava/lang/String;");
                    methodVisitor.visitVarInsn(16, i13);
                    methodVisitor.visitMethodInsn(i11, SerializeWriter, "writeString", "(Ljava/lang/String;C)V");
                    aSMSerializerFactory = aSMSerializerFactory2;
                    str = str4;
                    i = i10;
                    i2 = length;
                    str2 = str5;
                    i3 = i11;
                    i4 = i12;
                    c2 = 16;
                } else if (cls3 == String.class) {
                    methodVisitor.visitVarInsn(i10, context.var(str4));
                    aSMSerializerFactory2._get(methodVisitor, context, fieldInfo);
                    methodVisitor.visitVarInsn(16, i13);
                    methodVisitor.visitMethodInsn(i11, SerializeWriter, "writeString", "(Ljava/lang/String;C)V");
                } else if (cls3.isEnum()) {
                    methodVisitor.visitVarInsn(i10, context.var(str4));
                    methodVisitor.visitInsn(89);
                    aSMSerializerFactory2._get(methodVisitor, context, fieldInfo);
                    methodVisitor.visitMethodInsn(i11, SerializeWriter, "writeEnum", "(Ljava/lang/Enum;)V");
                    methodVisitor.visitVarInsn(16, i13);
                    methodVisitor.visitMethodInsn(i11, SerializeWriter, "write", str5);
                } else if (List.class.isAssignableFrom(cls3)) {
                    java.lang.reflect.Type type2 = fieldInfo.fieldType;
                    if (type2 instanceof Class) {
                        type = Object.class;
                    } else {
                        type = ((ParameterizedType) type2).getActualTypeArguments()[0];
                    }
                    if (!(type instanceof Class) || (cls2 = (Class) type) == Object.class) {
                        cls2 = null;
                    }
                    aSMSerializerFactory2._get(methodVisitor, context, fieldInfo);
                    methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, "java/util/List");
                    methodVisitor.visitVarInsn(58, context.var("list"));
                    if (cls2 == String.class && context.writeDirect) {
                        methodVisitor.visitVarInsn(25, context.var(str4));
                        methodVisitor.visitVarInsn(25, context.var("list"));
                        methodVisitor.visitMethodInsn(182, SerializeWriter, "write", "(Ljava/util/List;)V");
                        str = str4;
                        i2 = length;
                        str3 = str5;
                        i9 = 182;
                        i4 = i12;
                        i6 = i13;
                        i7 = 25;
                        i8 = 16;
                    } else {
                        Label label3 = new Label();
                        Label label4 = new Label();
                        i2 = length;
                        methodVisitor.visitVarInsn(25, context.var("list"));
                        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label4);
                        methodVisitor.visitVarInsn(25, context.var(str4));
                        i4 = i12;
                        i6 = i13;
                        methodVisitor.visitMethodInsn(182, SerializeWriter, "writeNull", "()V");
                        methodVisitor.visitJumpInsn(167, label3);
                        methodVisitor.visitLabel(label4);
                        methodVisitor.visitVarInsn(25, context.var("list"));
                        methodVisitor.visitMethodInsn(185, "java/util/List", "size", "()I");
                        methodVisitor.visitVarInsn(54, context.var("size"));
                        methodVisitor.visitVarInsn(25, context.var(str4));
                        methodVisitor.visitVarInsn(16, 91);
                        methodVisitor.visitMethodInsn(182, SerializeWriter, "write", str5);
                        Label label5 = new Label();
                        Label label6 = new Label();
                        Label label7 = new Label();
                        methodVisitor.visitInsn(3);
                        methodVisitor.visitVarInsn(54, context.var("i"));
                        methodVisitor.visitLabel(label5);
                        methodVisitor.visitVarInsn(21, context.var("i"));
                        methodVisitor.visitVarInsn(21, context.var("size"));
                        methodVisitor.visitJumpInsn(162, label7);
                        methodVisitor.visitVarInsn(21, context.var("i"));
                        methodVisitor.visitJumpInsn(153, label6);
                        methodVisitor.visitVarInsn(25, context.var(str4));
                        methodVisitor.visitVarInsn(16, 44);
                        methodVisitor.visitMethodInsn(182, SerializeWriter, "write", str5);
                        methodVisitor.visitLabel(label6);
                        methodVisitor.visitVarInsn(25, context.var("list"));
                        methodVisitor.visitVarInsn(21, context.var("i"));
                        methodVisitor.visitMethodInsn(185, "java/util/List", "get", "(I)Ljava/lang/Object;");
                        methodVisitor.visitVarInsn(58, context.var("list_item"));
                        Label label8 = new Label();
                        Label label9 = new Label();
                        String str6 = str5;
                        methodVisitor.visitVarInsn(25, context.var("list_item"));
                        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label9);
                        methodVisitor.visitVarInsn(25, context.var(str4));
                        String str7 = str4;
                        methodVisitor.visitMethodInsn(182, SerializeWriter, "writeNull", "()V");
                        methodVisitor.visitJumpInsn(167, label8);
                        methodVisitor.visitLabel(label9);
                        Label label10 = new Label();
                        Label label11 = new Label();
                        if (cls2 == null || !Modifier.isPublic(cls2.getModifiers())) {
                            label = label5;
                            label2 = label8;
                        } else {
                            methodVisitor.visitVarInsn(25, context.var("list_item"));
                            label = label5;
                            methodVisitor.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                            methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls2)));
                            methodVisitor.visitJumpInsn(166, label11);
                            aSMSerializerFactory2._getListFieldItemSer(context, methodVisitor, fieldInfo, cls2);
                            methodVisitor.visitVarInsn(58, context.var("list_item_desc"));
                            Label label12 = new Label();
                            Label label13 = new Label();
                            if (context.writeDirect) {
                                methodVisitor.visitVarInsn(25, context.var("list_item_desc"));
                                methodVisitor.visitTypeInsn(193, JavaBeanSerializer);
                                methodVisitor.visitJumpInsn(153, label12);
                                methodVisitor.visitVarInsn(25, context.var("list_item_desc"));
                                methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, JavaBeanSerializer);
                                methodVisitor.visitVarInsn(25, 1);
                                methodVisitor.visitVarInsn(25, context.var("list_item"));
                                if (context.nonContext) {
                                    methodVisitor.visitInsn(1);
                                    label2 = label8;
                                } else {
                                    methodVisitor.visitVarInsn(21, context.var("i"));
                                    label2 = label8;
                                    methodVisitor.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                                }
                                methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls2)));
                                methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
                                methodVisitor.visitMethodInsn(182, JavaBeanSerializer, "writeAsArrayNonContext", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                                methodVisitor.visitJumpInsn(167, label13);
                                methodVisitor.visitLabel(label12);
                            } else {
                                label2 = label8;
                            }
                            methodVisitor.visitVarInsn(25, context.var("list_item_desc"));
                            methodVisitor.visitVarInsn(25, 1);
                            methodVisitor.visitVarInsn(25, context.var("list_item"));
                            if (context.nonContext) {
                                methodVisitor.visitInsn(1);
                            } else {
                                methodVisitor.visitVarInsn(21, context.var("i"));
                                methodVisitor.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                            }
                            methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls2)));
                            methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
                            methodVisitor.visitMethodInsn(185, ObjectSerializer, "write", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                            methodVisitor.visitLabel(label13);
                            methodVisitor.visitJumpInsn(167, label10);
                        }
                        methodVisitor.visitLabel(label11);
                        methodVisitor.visitVarInsn(25, 1);
                        methodVisitor.visitVarInsn(25, context.var("list_item"));
                        if (context.nonContext) {
                            methodVisitor.visitInsn(1);
                        } else {
                            methodVisitor.visitVarInsn(21, context.var("i"));
                            methodVisitor.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                        }
                        if (cls2 != null && Modifier.isPublic(cls2.getModifiers())) {
                            methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc((Class<?>) type)));
                            methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
                            methodVisitor.visitMethodInsn(182, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                        } else {
                            methodVisitor.visitMethodInsn(182, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
                        }
                        methodVisitor.visitLabel(label10);
                        methodVisitor.visitLabel(label2);
                        methodVisitor.visitIincInsn(context.var("i"), 1);
                        methodVisitor.visitJumpInsn(167, label);
                        methodVisitor.visitLabel(label7);
                        str = str7;
                        i7 = 25;
                        methodVisitor.visitVarInsn(25, context.var(str));
                        i8 = 16;
                        methodVisitor.visitVarInsn(16, 93);
                        str3 = str6;
                        i9 = 182;
                        methodVisitor.visitMethodInsn(182, SerializeWriter, "write", str3);
                        methodVisitor.visitLabel(label3);
                    }
                    methodVisitor.visitVarInsn(i7, context.var(str));
                    methodVisitor.visitVarInsn(i8, i6);
                    methodVisitor.visitMethodInsn(i9, SerializeWriter, "write", str3);
                    i = 25;
                    aSMSerializerFactory = this;
                    str2 = str3;
                    i3 = i9;
                    c2 = 16;
                } else {
                    str = str4;
                    i2 = length;
                    String str8 = str5;
                    i4 = i12;
                    Label label14 = new Label();
                    Label label15 = new Label();
                    _get(methodVisitor, context, fieldInfo);
                    methodVisitor.visitInsn(89);
                    methodVisitor.visitVarInsn(58, context.var("field_" + fieldInfo.fieldClass.getName()));
                    methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label15);
                    methodVisitor.visitVarInsn(25, context.var(str));
                    methodVisitor.visitMethodInsn(182, SerializeWriter, "writeNull", "()V");
                    methodVisitor.visitJumpInsn(167, label14);
                    methodVisitor.visitLabel(label15);
                    Label label16 = new Label();
                    Label label17 = new Label();
                    methodVisitor.visitVarInsn(25, context.var("field_" + fieldInfo.fieldClass.getName()));
                    methodVisitor.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                    methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls3)));
                    methodVisitor.visitJumpInsn(166, label17);
                    _getFieldSer(context, methodVisitor, fieldInfo);
                    methodVisitor.visitVarInsn(58, context.var("fied_ser"));
                    Label label18 = new Label();
                    Label label19 = new Label();
                    if (context.writeDirect && Modifier.isPublic(cls3.getModifiers())) {
                        methodVisitor.visitVarInsn(25, context.var("fied_ser"));
                        methodVisitor.visitTypeInsn(193, JavaBeanSerializer);
                        methodVisitor.visitJumpInsn(153, label18);
                        methodVisitor.visitVarInsn(25, context.var("fied_ser"));
                        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, JavaBeanSerializer);
                        methodVisitor.visitVarInsn(25, 1);
                        methodVisitor.visitVarInsn(25, context.var("field_" + fieldInfo.fieldClass.getName()));
                        methodVisitor.visitVarInsn(25, Context.fieldName);
                        methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls3)));
                        methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
                        i5 = i13;
                        methodVisitor.visitMethodInsn(182, JavaBeanSerializer, "writeAsArrayNonContext", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                        methodVisitor.visitJumpInsn(167, label19);
                        methodVisitor.visitLabel(label18);
                    } else {
                        i5 = i13;
                    }
                    methodVisitor.visitVarInsn(25, context.var("fied_ser"));
                    methodVisitor.visitVarInsn(25, 1);
                    methodVisitor.visitVarInsn(25, context.var("field_" + fieldInfo.fieldClass.getName()));
                    methodVisitor.visitVarInsn(25, Context.fieldName);
                    methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls3)));
                    methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
                    methodVisitor.visitMethodInsn(185, ObjectSerializer, "write", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                    methodVisitor.visitLabel(label19);
                    methodVisitor.visitJumpInsn(167, label16);
                    methodVisitor.visitLabel(label17);
                    String format = fieldInfo.getFormat();
                    methodVisitor.visitVarInsn(25, 1);
                    methodVisitor.visitVarInsn(25, context.var("field_" + fieldInfo.fieldClass.getName()));
                    if (format != null) {
                        methodVisitor.visitLdcInsn(format);
                        methodVisitor.visitMethodInsn(182, JSONSerializer, "writeWithFormat", "(Ljava/lang/Object;Ljava/lang/String;)V");
                        i3 = 182;
                    } else {
                        methodVisitor.visitVarInsn(25, Context.fieldName);
                        if ((fieldInfo.fieldType instanceof Class) && ((Class) fieldInfo.fieldType).isPrimitive()) {
                            i3 = 182;
                            methodVisitor.visitMethodInsn(182, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
                        } else {
                            methodVisitor.visitVarInsn(25, 0);
                            methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
                            methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
                            i3 = 182;
                            methodVisitor.visitMethodInsn(182, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                        }
                    }
                    methodVisitor.visitLabel(label16);
                    methodVisitor.visitLabel(label14);
                    i = 25;
                    methodVisitor.visitVarInsn(25, context.var(str));
                    methodVisitor.visitVarInsn(16, i5);
                    str2 = str8;
                    methodVisitor.visitMethodInsn(i3, SerializeWriter, "write", str2);
                    c2 = 16;
                    aSMSerializerFactory = this;
                }
                aSMSerializerFactory = aSMSerializerFactory2;
                str = str4;
                i = i10;
                i2 = length;
                str2 = str5;
                i3 = i11;
                i4 = i12;
                c2 = 16;
            }
            i12 = i4 + 1;
            i11 = i3;
            length = i2;
            str5 = str2;
            i10 = i;
            str4 = str;
            aSMSerializerFactory2 = aSMSerializerFactory;
            fieldInfoArr2 = fieldInfoArr;
        }
    }

    private void generateWriteMethod(Class<?> cls, MethodVisitor methodVisitor, FieldInfo[] fieldInfoArr, Context context) throws Exception {
        String str;
        int i;
        char c2;
        int i2;
        char c3;
        int i3;
        FieldInfo[] fieldInfoArr2 = fieldInfoArr;
        Label label = new Label();
        int length = fieldInfoArr2.length;
        if (!context.writeDirect) {
            Label label2 = new Label();
            Label label3 = new Label();
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.PrettyFormat.mask));
            methodVisitor.visitMethodInsn(182, SerializeWriter, "isEnabled", "(I)Z");
            methodVisitor.visitJumpInsn(154, label3);
            boolean z = false;
            for (FieldInfo fieldInfo : fieldInfoArr2) {
                if (fieldInfo.method != null) {
                    z = true;
                }
            }
            if (z) {
                methodVisitor.visitVarInsn(25, context.var("out"));
                methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.IgnoreErrorGetter.mask));
                methodVisitor.visitMethodInsn(182, SerializeWriter, "isEnabled", "(I)Z");
                methodVisitor.visitJumpInsn(153, label2);
            } else {
                methodVisitor.visitJumpInsn(167, label2);
            }
            methodVisitor.visitLabel(label3);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitVarInsn(25, 3);
            methodVisitor.visitVarInsn(25, 4);
            methodVisitor.visitVarInsn(21, 5);
            methodVisitor.visitMethodInsn(183, JavaBeanSerializer, "write", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor.visitInsn(177);
            methodVisitor.visitLabel(label2);
        }
        if (!context.nonContext) {
            Label label4 = new Label();
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitVarInsn(21, 5);
            methodVisitor.visitMethodInsn(182, JavaBeanSerializer, "writeReference", "(L" + JSONSerializer + ";Ljava/lang/Object;I)Z");
            methodVisitor.visitJumpInsn(153, label4);
            methodVisitor.visitInsn(177);
            methodVisitor.visitLabel(label4);
        }
        if (context.writeDirect) {
            str = context.nonContext ? "writeAsArrayNonContext" : "writeAsArray";
        } else {
            str = "writeAsArrayNormal";
        }
        if ((context.beanInfo.features & SerializerFeature.BeanToArray.mask) == 0) {
            Label label5 = new Label();
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.BeanToArray.mask));
            methodVisitor.visitMethodInsn(182, SerializeWriter, "isEnabled", "(I)Z");
            methodVisitor.visitJumpInsn(153, label5);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitVarInsn(25, 3);
            methodVisitor.visitVarInsn(25, 4);
            methodVisitor.visitVarInsn(21, 5);
            methodVisitor.visitMethodInsn(182, context.className, str, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor.visitInsn(177);
            methodVisitor.visitLabel(label5);
        } else {
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitVarInsn(25, 3);
            methodVisitor.visitVarInsn(25, 4);
            methodVisitor.visitVarInsn(21, 5);
            methodVisitor.visitMethodInsn(182, context.className, str, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor.visitInsn(177);
        }
        if (!context.nonContext) {
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitMethodInsn(182, JSONSerializer, "getContext", "()" + SerialContext_desc);
            methodVisitor.visitVarInsn(58, context.var("parent"));
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, context.var("parent"));
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitVarInsn(25, 3);
            methodVisitor.visitLdcInsn(Integer.valueOf(context.beanInfo.features));
            methodVisitor.visitMethodInsn(182, JSONSerializer, "setContext", "(" + SerialContext_desc + "Ljava/lang/Object;Ljava/lang/Object;I)V");
        }
        boolean z2 = (context.beanInfo.features & SerializerFeature.WriteClassName.mask) != 0;
        int i4 = 16;
        if (z2 || !context.writeDirect) {
            Label label6 = new Label();
            Label label7 = new Label();
            Label label8 = new Label();
            if (z2) {
                i = 182;
            } else {
                methodVisitor.visitVarInsn(25, 1);
                methodVisitor.visitVarInsn(25, 4);
                methodVisitor.visitVarInsn(25, 2);
                i = 182;
                methodVisitor.visitMethodInsn(182, JSONSerializer, "isWriteClassName", "(Ljava/lang/reflect/Type;Ljava/lang/Object;)Z");
                methodVisitor.visitJumpInsn(153, label7);
            }
            methodVisitor.visitVarInsn(25, 4);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitMethodInsn(i, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
            methodVisitor.visitJumpInsn(165, label7);
            methodVisitor.visitLabel(label8);
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(16, 123);
            methodVisitor.visitMethodInsn(i, SerializeWriter, "write", "(I)V");
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitVarInsn(25, 1);
            if (context.beanInfo.typeKey != null) {
                methodVisitor.visitLdcInsn(context.beanInfo.typeKey);
            } else {
                methodVisitor.visitInsn(1);
            }
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitMethodInsn(182, JavaBeanSerializer, "writeClassName", "(L" + JSONSerializer + ";Ljava/lang/String;Ljava/lang/Object;)V");
            i4 = 16;
            methodVisitor.visitVarInsn(16, 44);
            methodVisitor.visitJumpInsn(167, label6);
            methodVisitor.visitLabel(label7);
            methodVisitor.visitVarInsn(16, 123);
            methodVisitor.visitLabel(label6);
        } else {
            methodVisitor.visitVarInsn(16, 123);
        }
        methodVisitor.visitVarInsn(54, context.var("seperator"));
        if (!context.writeDirect) {
            _before(methodVisitor, context);
        }
        if (context.writeDirect) {
            c2 = 25;
            i2 = 0;
            c3 = Typography.paragraph;
        } else {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitMethodInsn(182, SerializeWriter, "isNotWriteDefaultValue", "()Z");
            methodVisitor.visitVarInsn(54, context.var("notWriteDefaultValue"));
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitMethodInsn(182, JSONSerializer, "checkValue", "(" + SerializeFilterable_desc + ")Z");
            methodVisitor.visitVarInsn(54, context.var("checkValue"));
            c2 = 25;
            methodVisitor.visitVarInsn(25, 1);
            i2 = 0;
            methodVisitor.visitVarInsn(25, 0);
            String str2 = JSONSerializer;
            String str3 = "(" + SerializeFilterable_desc + ")Z";
            c3 = Typography.paragraph;
            methodVisitor.visitMethodInsn(182, str2, "hasNameFilters", str3);
            methodVisitor.visitVarInsn(54, context.var("hasNameFilters"));
        }
        int i5 = i2;
        while (i5 < length) {
            FieldInfo fieldInfo2 = fieldInfoArr2[i5];
            Class<?> cls2 = fieldInfo2.fieldClass;
            methodVisitor.visitLdcInsn(fieldInfo2.name);
            methodVisitor.visitVarInsn(58, Context.fieldName);
            if (cls2 == Byte.TYPE || cls2 == Short.TYPE || cls2 == Integer.TYPE) {
                i3 = i4;
                _int(cls, methodVisitor, fieldInfo2, context, context.var(cls2.getName()), 'I');
            } else {
                if (cls2 == Long.TYPE) {
                    _long(cls, methodVisitor, fieldInfo2, context);
                } else if (cls2 == Float.TYPE) {
                    _float(cls, methodVisitor, fieldInfo2, context);
                } else if (cls2 == Double.TYPE) {
                    _double(cls, methodVisitor, fieldInfo2, context);
                } else if (cls2 == Boolean.TYPE) {
                    i3 = i4;
                    _int(cls, methodVisitor, fieldInfo2, context, context.var("boolean"), 'Z');
                } else {
                    i3 = i4;
                    if (cls2 == Character.TYPE) {
                        _int(cls, methodVisitor, fieldInfo2, context, context.var("char"), 'C');
                    } else if (cls2 == String.class) {
                        _string(cls, methodVisitor, fieldInfo2, context);
                    } else if (cls2 == BigDecimal.class) {
                        _decimal(cls, methodVisitor, fieldInfo2, context);
                    } else if (List.class.isAssignableFrom(cls2)) {
                        _list(cls, methodVisitor, fieldInfo2, context);
                    } else if (cls2.isEnum()) {
                        _enum(cls, methodVisitor, fieldInfo2, context);
                    } else {
                        _object(cls, methodVisitor, fieldInfo2, context);
                    }
                }
                i3 = i4;
            }
            i5++;
            i4 = i3;
            c2 = 25;
            c3 = Typography.paragraph;
            fieldInfoArr2 = fieldInfoArr;
        }
        int i6 = i4;
        if (!context.writeDirect) {
            _after(methodVisitor, context);
        }
        Label label9 = new Label();
        Label label10 = new Label();
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitIntInsn(i6, 123);
        methodVisitor.visitJumpInsn(160, label9);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(i6, 123);
        methodVisitor.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
        methodVisitor.visitLabel(label9);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(i6, 125);
        methodVisitor.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
        methodVisitor.visitLabel(label10);
        methodVisitor.visitLabel(label);
        if (context.nonContext) {
            return;
        }
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, context.var("parent"));
        methodVisitor.visitMethodInsn(182, JSONSerializer, "setContext", "(" + SerialContext_desc + ")V");
    }

    private void _object(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(58, context.var("object"));
        _filters(methodVisitor, fieldInfo, context, label);
        _writeObject(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitLabel(label);
    }

    private void _enum(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label3);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Enum");
        methodVisitor.visitVarInsn(58, context.var("enum"));
        _filters(methodVisitor, fieldInfo, context, label3);
        methodVisitor.visitVarInsn(25, context.var("enum"));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(167, label2);
        methodVisitor.visitLabel(label);
        if (context.writeDirect) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(21, context.var("seperator"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitVarInsn(25, context.var("enum"));
            methodVisitor.visitMethodInsn(182, "java/lang/Enum", AppMeasurementSdk.ConditionalUserProperty.NAME, "()Ljava/lang/String;");
            methodVisitor.visitMethodInsn(182, SerializeWriter, "writeFieldValueStringWithDoubleQuote", "(CLjava/lang/String;Ljava/lang/String;)V");
        } else {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(21, context.var("seperator"));
            methodVisitor.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitInsn(3);
            methodVisitor.visitMethodInsn(182, SerializeWriter, "writeFieldName", "(Ljava/lang/String;Z)V");
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, context.var("enum"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(fieldInfo.fieldClass)));
            methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
            methodVisitor.visitMethodInsn(182, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
        }
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitLabel(label3);
    }

    private void _int(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, int i, char c2) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(54, i);
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(21, i);
        methodVisitor.visitMethodInsn(182, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;" + c2 + ")V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _long(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(55, context.var("long", 2));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(22, context.var("long", 2));
        methodVisitor.visitMethodInsn(182, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;J)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _float(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(56, context.var("float"));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(23, context.var("float"));
        methodVisitor.visitMethodInsn(182, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;F)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _double(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(57, context.var("double", 2));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(24, context.var("double", 2));
        methodVisitor.visitMethodInsn(182, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;D)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _get(MethodVisitor methodVisitor, Context context, FieldInfo fieldInfo) {
        Method method = fieldInfo.method;
        if (method != null) {
            methodVisitor.visitVarInsn(25, context.var("entity"));
            Class<?> declaringClass = method.getDeclaringClass();
            methodVisitor.visitMethodInsn(declaringClass.isInterface() ? 185 : 182, ASMUtils.type(declaringClass), method.getName(), ASMUtils.desc(method));
            if (method.getReturnType().equals(fieldInfo.fieldClass)) {
                return;
            }
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(fieldInfo.fieldClass));
            return;
        }
        methodVisitor.visitVarInsn(25, context.var("entity"));
        Field field = fieldInfo.field;
        methodVisitor.visitFieldInsn(180, ASMUtils.type(fieldInfo.declaringClass), field.getName(), ASMUtils.desc(field.getType()));
        if (field.getType().equals(fieldInfo.fieldClass)) {
            return;
        }
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(fieldInfo.fieldClass));
    }

    private void _decimal(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(58, context.var("decimal"));
        _filters(methodVisitor, fieldInfo, context, label);
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(25, context.var("decimal"));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label3);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(167, label4);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(25, context.var("decimal"));
        methodVisitor.visitMethodInsn(182, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;Ljava/math/BigDecimal;)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitJumpInsn(167, label4);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitLabel(label);
    }

    private void _string(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        if (fieldInfo.name.equals(context.beanInfo.typeKey)) {
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 4);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitMethodInsn(182, JSONSerializer, "isWriteClassName", "(Ljava/lang/reflect/Type;Ljava/lang/Object;)Z");
            methodVisitor.visitJumpInsn(154, label);
        }
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(58, context.var("string"));
        _filters(methodVisitor, fieldInfo, context, label);
        Label label2 = new Label();
        Label label3 = new Label();
        methodVisitor.visitVarInsn(25, context.var("string"));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label2);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(167, label3);
        methodVisitor.visitLabel(label2);
        if ("trim".equals(fieldInfo.format)) {
            methodVisitor.visitVarInsn(25, context.var("string"));
            methodVisitor.visitMethodInsn(182, "java/lang/String", "trim", "()Ljava/lang/String;");
            methodVisitor.visitVarInsn(58, context.var("string"));
        }
        if (context.writeDirect) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(21, context.var("seperator"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitVarInsn(25, context.var("string"));
            methodVisitor.visitMethodInsn(182, SerializeWriter, "writeFieldValueStringWithDoubleQuoteCheck", "(CLjava/lang/String;Ljava/lang/String;)V");
        } else {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(21, context.var("seperator"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitVarInsn(25, context.var("string"));
            methodVisitor.visitMethodInsn(182, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;Ljava/lang/String;)V");
        }
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitLabel(label);
    }

    private void _list(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label;
        Label label2;
        Label label3;
        String str;
        Label label4;
        Label label5;
        int i;
        int i2;
        int i3;
        Label label6;
        String str2;
        java.lang.reflect.Type collectionItemType = TypeUtils.getCollectionItemType(fieldInfo.fieldType);
        Class<?> cls2 = collectionItemType instanceof Class ? (Class) collectionItemType : null;
        if (cls2 == Object.class || cls2 == Serializable.class) {
            cls2 = null;
        }
        Label label7 = new Label();
        Label label8 = new Label();
        Label label9 = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label7);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, "java/util/List");
        methodVisitor.visitVarInsn(58, context.var("list"));
        _filters(methodVisitor, fieldInfo, context, label7);
        methodVisitor.visitVarInsn(25, context.var("list"));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label8);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(167, label9);
        methodVisitor.visitLabel(label8);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
        _writeFieldName(methodVisitor, context);
        methodVisitor.visitVarInsn(25, context.var("list"));
        methodVisitor.visitMethodInsn(185, "java/util/List", "size", "()I");
        methodVisitor.visitVarInsn(54, context.var("size"));
        Label label10 = new Label();
        Label label11 = new Label();
        methodVisitor.visitVarInsn(21, context.var("size"));
        methodVisitor.visitInsn(3);
        methodVisitor.visitJumpInsn(160, label10);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitLdcInsn("[]");
        methodVisitor.visitMethodInsn(182, SerializeWriter, "write", "(Ljava/lang/String;)V");
        methodVisitor.visitJumpInsn(167, label11);
        methodVisitor.visitLabel(label10);
        if (!context.nonContext) {
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, context.var("list"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitMethodInsn(182, JSONSerializer, "setContext", "(Ljava/lang/Object;Ljava/lang/Object;)V");
        }
        if (collectionItemType == String.class && context.writeDirect) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(25, context.var("list"));
            methodVisitor.visitMethodInsn(182, SerializeWriter, "write", "(Ljava/util/List;)V");
            i2 = 25;
            i3 = 182;
            label = label11;
            i = 1;
        } else {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(16, 91);
            methodVisitor.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
            Label label12 = new Label();
            Label label13 = new Label();
            Label label14 = new Label();
            methodVisitor.visitInsn(3);
            label = label11;
            methodVisitor.visitVarInsn(54, context.var("i"));
            methodVisitor.visitLabel(label12);
            methodVisitor.visitVarInsn(21, context.var("i"));
            methodVisitor.visitVarInsn(21, context.var("size"));
            methodVisitor.visitJumpInsn(162, label14);
            methodVisitor.visitVarInsn(21, context.var("i"));
            methodVisitor.visitJumpInsn(153, label13);
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(16, 44);
            methodVisitor.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
            methodVisitor.visitLabel(label13);
            methodVisitor.visitVarInsn(25, context.var("list"));
            methodVisitor.visitVarInsn(21, context.var("i"));
            methodVisitor.visitMethodInsn(185, "java/util/List", "get", "(I)Ljava/lang/Object;");
            methodVisitor.visitVarInsn(58, context.var("list_item"));
            Label label15 = new Label();
            Label label16 = new Label();
            methodVisitor.visitVarInsn(25, context.var("list_item"));
            methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label16);
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitMethodInsn(182, SerializeWriter, "writeNull", "()V");
            methodVisitor.visitJumpInsn(167, label15);
            methodVisitor.visitLabel(label16);
            Label label17 = new Label();
            Label label18 = new Label();
            if (cls2 == null || !Modifier.isPublic(cls2.getModifiers())) {
                label2 = label12;
                label3 = label15;
                str = "out";
                label4 = label14;
                label5 = label18;
            } else {
                methodVisitor.visitVarInsn(25, context.var("list_item"));
                str = "out";
                methodVisitor.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls2)));
                methodVisitor.visitJumpInsn(166, label18);
                _getListFieldItemSer(context, methodVisitor, fieldInfo, cls2);
                methodVisitor.visitVarInsn(58, context.var("list_item_desc"));
                Label label19 = new Label();
                Label label20 = new Label();
                if (context.writeDirect) {
                    if (context.nonContext && context.writeDirect) {
                        label4 = label14;
                        str2 = "writeDirectNonContext";
                    } else {
                        label4 = label14;
                        str2 = "write";
                    }
                    label2 = label12;
                    methodVisitor.visitVarInsn(25, context.var("list_item_desc"));
                    methodVisitor.visitTypeInsn(193, JavaBeanSerializer);
                    methodVisitor.visitJumpInsn(153, label19);
                    methodVisitor.visitVarInsn(25, context.var("list_item_desc"));
                    label3 = label15;
                    methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, JavaBeanSerializer);
                    methodVisitor.visitVarInsn(25, 1);
                    methodVisitor.visitVarInsn(25, context.var("list_item"));
                    if (context.nonContext) {
                        methodVisitor.visitInsn(1);
                        label6 = label18;
                    } else {
                        methodVisitor.visitVarInsn(21, context.var("i"));
                        label6 = label18;
                        methodVisitor.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                    }
                    methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls2)));
                    methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
                    methodVisitor.visitMethodInsn(182, JavaBeanSerializer, str2, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                    methodVisitor.visitJumpInsn(167, label20);
                    methodVisitor.visitLabel(label19);
                } else {
                    label2 = label12;
                    label3 = label15;
                    label4 = label14;
                    label6 = label18;
                }
                methodVisitor.visitVarInsn(25, context.var("list_item_desc"));
                methodVisitor.visitVarInsn(25, 1);
                methodVisitor.visitVarInsn(25, context.var("list_item"));
                if (context.nonContext) {
                    methodVisitor.visitInsn(1);
                } else {
                    methodVisitor.visitVarInsn(21, context.var("i"));
                    methodVisitor.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                }
                methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls2)));
                methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
                methodVisitor.visitMethodInsn(185, ObjectSerializer, "write", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                methodVisitor.visitLabel(label20);
                methodVisitor.visitJumpInsn(167, label17);
                label5 = label6;
            }
            methodVisitor.visitLabel(label5);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, context.var("list_item"));
            if (context.nonContext) {
                methodVisitor.visitInsn(1);
            } else {
                methodVisitor.visitVarInsn(21, context.var("i"));
                methodVisitor.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            }
            if (cls2 != null && Modifier.isPublic(cls2.getModifiers())) {
                methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc((Class<?>) collectionItemType)));
                methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
                methodVisitor.visitMethodInsn(182, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            } else {
                methodVisitor.visitMethodInsn(182, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
            }
            methodVisitor.visitLabel(label17);
            methodVisitor.visitLabel(label3);
            i = 1;
            methodVisitor.visitIincInsn(context.var("i"), 1);
            methodVisitor.visitJumpInsn(167, label2);
            methodVisitor.visitLabel(label4);
            i2 = 25;
            methodVisitor.visitVarInsn(25, context.var(str));
            methodVisitor.visitVarInsn(16, 93);
            i3 = 182;
            methodVisitor.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
        }
        methodVisitor.visitVarInsn(i2, i);
        methodVisitor.visitMethodInsn(i3, JSONSerializer, "popContext", "()V");
        methodVisitor.visitLabel(label);
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label9);
        methodVisitor.visitLabel(label7);
    }

    private void _filters(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        if (fieldInfo.fieldTransient) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.SkipTransientField.mask));
            methodVisitor.visitMethodInsn(182, SerializeWriter, "isEnabled", "(I)Z");
            methodVisitor.visitJumpInsn(154, label);
        }
        _notWriteDefault(methodVisitor, fieldInfo, context, label);
        if (context.writeDirect) {
            return;
        }
        _apply(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(153, label);
        _processKey(methodVisitor, fieldInfo, context);
        _processValue(methodVisitor, fieldInfo, context, label);
    }

    private void _nameApply(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        if (!context.writeDirect) {
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitMethodInsn(182, JavaBeanSerializer, "applyName", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/String;)Z");
            methodVisitor.visitJumpInsn(153, label);
            _labelApply(methodVisitor, fieldInfo, context, label);
        }
        if (fieldInfo.field == null) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.IgnoreNonFieldGetter.mask));
            methodVisitor.visitMethodInsn(182, SerializeWriter, "isEnabled", "(I)Z");
            methodVisitor.visitJumpInsn(154, label);
        }
    }

    private void _labelApply(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitLdcInsn(fieldInfo.label);
        methodVisitor.visitMethodInsn(182, JavaBeanSerializer, "applyLabel", "(L" + JSONSerializer + ";Ljava/lang/String;)Z");
        methodVisitor.visitJumpInsn(153, label);
    }

    private void _writeObject(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        String str;
        Label label2;
        Label label3;
        String format = fieldInfo.getFormat();
        Class<?> cls = fieldInfo.fieldClass;
        Label label4 = new Label();
        if (context.writeDirect) {
            methodVisitor.visitVarInsn(25, context.var("object"));
        } else {
            methodVisitor.visitVarInsn(25, Context.processValue);
        }
        methodVisitor.visitInsn(89);
        methodVisitor.visitVarInsn(58, context.var("object"));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label4);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(167, label);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
        _writeFieldName(methodVisitor, context);
        Label label5 = new Label();
        Label label6 = new Label();
        if (!Modifier.isPublic(cls.getModifiers()) || ParserConfig.isPrimitive2(cls)) {
            str = format;
            label2 = label5;
            label3 = label6;
        } else {
            methodVisitor.visitVarInsn(25, context.var("object"));
            methodVisitor.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
            methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls)));
            methodVisitor.visitJumpInsn(166, label6);
            _getFieldSer(context, methodVisitor, fieldInfo);
            methodVisitor.visitVarInsn(58, context.var("fied_ser"));
            Label label7 = new Label();
            Label label8 = new Label();
            methodVisitor.visitVarInsn(25, context.var("fied_ser"));
            methodVisitor.visitTypeInsn(193, JavaBeanSerializer);
            methodVisitor.visitJumpInsn(153, label7);
            boolean z = (fieldInfo.serialzeFeatures & SerializerFeature.DisableCircularReferenceDetect.mask) != 0;
            boolean z2 = (SerializerFeature.BeanToArray.mask & fieldInfo.serialzeFeatures) != 0;
            String str2 = (z || (context.nonContext && context.writeDirect)) ? z2 ? "writeAsArrayNonContext" : "writeDirectNonContext" : z2 ? "writeAsArray" : "write";
            methodVisitor.visitVarInsn(25, context.var("fied_ser"));
            str = format;
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, JavaBeanSerializer);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, context.var("object"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
            methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
            methodVisitor.visitMethodInsn(182, JavaBeanSerializer, str2, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor.visitJumpInsn(167, label8);
            methodVisitor.visitLabel(label7);
            methodVisitor.visitVarInsn(25, context.var("fied_ser"));
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, context.var("object"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
            methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
            methodVisitor.visitMethodInsn(185, ObjectSerializer, "write", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor.visitLabel(label8);
            label2 = label5;
            methodVisitor.visitJumpInsn(167, label2);
            label3 = label6;
        }
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(25, 1);
        if (context.writeDirect) {
            methodVisitor.visitVarInsn(25, context.var("object"));
        } else {
            methodVisitor.visitVarInsn(25, Context.processValue);
        }
        if (str != null) {
            methodVisitor.visitLdcInsn(str);
            methodVisitor.visitMethodInsn(182, JSONSerializer, "writeWithFormat", "(Ljava/lang/Object;Ljava/lang/String;)V");
        } else {
            methodVisitor.visitVarInsn(25, Context.fieldName);
            if ((fieldInfo.fieldType instanceof Class) && ((Class) fieldInfo.fieldType).isPrimitive()) {
                methodVisitor.visitMethodInsn(182, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
            } else {
                if (fieldInfo.fieldClass == String.class) {
                    methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc((Class<?>) String.class)));
                } else {
                    methodVisitor.visitVarInsn(25, 0);
                    methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
                }
                methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
                methodVisitor.visitMethodInsn(182, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            }
        }
        methodVisitor.visitLabel(label2);
        _seperator(methodVisitor, context);
    }

    private void _before(MethodVisitor methodVisitor, Context context) {
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitMethodInsn(182, JavaBeanSerializer, "writeBefore", "(L" + JSONSerializer + ";Ljava/lang/Object;C)C");
        methodVisitor.visitVarInsn(54, context.var("seperator"));
    }

    private void _after(MethodVisitor methodVisitor, Context context) {
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitMethodInsn(182, JavaBeanSerializer, "writeAfter", "(L" + JSONSerializer + ";Ljava/lang/Object;C)C");
        methodVisitor.visitVarInsn(54, context.var("seperator"));
    }

    private void _notWriteDefault(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        if (context.writeDirect) {
            return;
        }
        Label label2 = new Label();
        methodVisitor.visitVarInsn(21, context.var("notWriteDefaultValue"));
        methodVisitor.visitJumpInsn(153, label2);
        Class<?> cls = fieldInfo.fieldClass;
        if (cls == Boolean.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("boolean"));
            methodVisitor.visitJumpInsn(153, label);
        } else if (cls == Byte.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("byte"));
            methodVisitor.visitJumpInsn(153, label);
        } else if (cls == Short.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("short"));
            methodVisitor.visitJumpInsn(153, label);
        } else if (cls == Integer.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("int"));
            methodVisitor.visitJumpInsn(153, label);
        } else if (cls == Long.TYPE) {
            methodVisitor.visitVarInsn(22, context.var("long"));
            methodVisitor.visitInsn(9);
            methodVisitor.visitInsn(Opcodes.LCMP);
            methodVisitor.visitJumpInsn(153, label);
        } else if (cls == Float.TYPE) {
            methodVisitor.visitVarInsn(23, context.var("float"));
            methodVisitor.visitInsn(11);
            methodVisitor.visitInsn(Opcodes.FCMPL);
            methodVisitor.visitJumpInsn(153, label);
        } else if (cls == Double.TYPE) {
            methodVisitor.visitVarInsn(24, context.var("double"));
            methodVisitor.visitInsn(14);
            methodVisitor.visitInsn(151);
            methodVisitor.visitJumpInsn(153, label);
        }
        methodVisitor.visitLabel(label2);
    }

    private void _apply(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Class<?> cls = fieldInfo.fieldClass;
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(25, Context.fieldName);
        if (cls == Byte.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("byte"));
            methodVisitor.visitMethodInsn(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
        } else if (cls == Short.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("short"));
            methodVisitor.visitMethodInsn(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
        } else if (cls == Integer.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("int"));
            methodVisitor.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        } else if (cls == Character.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("char"));
            methodVisitor.visitMethodInsn(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
        } else if (cls == Long.TYPE) {
            methodVisitor.visitVarInsn(22, context.var("long", 2));
            methodVisitor.visitMethodInsn(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
        } else if (cls == Float.TYPE) {
            methodVisitor.visitVarInsn(23, context.var("float"));
            methodVisitor.visitMethodInsn(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
        } else if (cls == Double.TYPE) {
            methodVisitor.visitVarInsn(24, context.var("double", 2));
            methodVisitor.visitMethodInsn(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
        } else if (cls == Boolean.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("boolean"));
            methodVisitor.visitMethodInsn(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
        } else if (cls == BigDecimal.class) {
            methodVisitor.visitVarInsn(25, context.var("decimal"));
        } else if (cls == String.class) {
            methodVisitor.visitVarInsn(25, context.var("string"));
        } else if (cls.isEnum()) {
            methodVisitor.visitVarInsn(25, context.var("enum"));
        } else if (List.class.isAssignableFrom(cls)) {
            methodVisitor.visitVarInsn(25, context.var("list"));
        } else {
            methodVisitor.visitVarInsn(25, context.var("object"));
        }
        methodVisitor.visitMethodInsn(182, JavaBeanSerializer, "apply", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z");
    }

    private void _processValue(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        Label label2 = new Label();
        Class<?> cls = fieldInfo.fieldClass;
        if (cls.isPrimitive()) {
            Label label3 = new Label();
            methodVisitor.visitVarInsn(21, context.var("checkValue"));
            methodVisitor.visitJumpInsn(154, label3);
            methodVisitor.visitInsn(1);
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(58, Context.original);
            methodVisitor.visitVarInsn(58, Context.processValue);
            methodVisitor.visitJumpInsn(167, label2);
            methodVisitor.visitLabel(label3);
        }
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitLdcInsn(Integer.valueOf(context.getFieldOrinal(fieldInfo.name)));
        methodVisitor.visitMethodInsn(182, JavaBeanSerializer, "getBeanContext", "(I)" + ASMUtils.desc((Class<?>) BeanContext.class));
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(25, Context.fieldName);
        if (cls == Byte.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("byte"));
            methodVisitor.visitMethodInsn(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(58, Context.original);
        } else if (cls == Short.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("short"));
            methodVisitor.visitMethodInsn(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(58, Context.original);
        } else if (cls == Integer.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("int"));
            methodVisitor.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(58, Context.original);
        } else if (cls == Character.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("char"));
            methodVisitor.visitMethodInsn(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(58, Context.original);
        } else if (cls == Long.TYPE) {
            methodVisitor.visitVarInsn(22, context.var("long", 2));
            methodVisitor.visitMethodInsn(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(58, Context.original);
        } else if (cls == Float.TYPE) {
            methodVisitor.visitVarInsn(23, context.var("float"));
            methodVisitor.visitMethodInsn(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(58, Context.original);
        } else if (cls == Double.TYPE) {
            methodVisitor.visitVarInsn(24, context.var("double", 2));
            methodVisitor.visitMethodInsn(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(58, Context.original);
        } else if (cls == Boolean.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("boolean"));
            methodVisitor.visitMethodInsn(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(58, Context.original);
        } else if (cls == BigDecimal.class) {
            methodVisitor.visitVarInsn(25, context.var("decimal"));
            methodVisitor.visitVarInsn(58, Context.original);
            methodVisitor.visitVarInsn(25, Context.original);
        } else if (cls == String.class) {
            methodVisitor.visitVarInsn(25, context.var("string"));
            methodVisitor.visitVarInsn(58, Context.original);
            methodVisitor.visitVarInsn(25, Context.original);
        } else if (cls.isEnum()) {
            methodVisitor.visitVarInsn(25, context.var("enum"));
            methodVisitor.visitVarInsn(58, Context.original);
            methodVisitor.visitVarInsn(25, Context.original);
        } else if (List.class.isAssignableFrom(cls)) {
            methodVisitor.visitVarInsn(25, context.var("list"));
            methodVisitor.visitVarInsn(58, Context.original);
            methodVisitor.visitVarInsn(25, Context.original);
        } else {
            methodVisitor.visitVarInsn(25, context.var("object"));
            methodVisitor.visitVarInsn(58, Context.original);
            methodVisitor.visitVarInsn(25, Context.original);
        }
        methodVisitor.visitMethodInsn(182, JavaBeanSerializer, "processValue", "(L" + JSONSerializer + ";" + ASMUtils.desc((Class<?>) BeanContext.class) + "Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;");
        methodVisitor.visitVarInsn(58, Context.processValue);
        methodVisitor.visitVarInsn(25, Context.original);
        methodVisitor.visitVarInsn(25, Context.processValue);
        methodVisitor.visitJumpInsn(165, label2);
        _writeObject(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitJumpInsn(167, label);
        methodVisitor.visitLabel(label2);
    }

    private void _processKey(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        methodVisitor.visitVarInsn(21, context.var("hasNameFilters"));
        methodVisitor.visitJumpInsn(153, label);
        Class<?> cls = fieldInfo.fieldClass;
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(25, Context.fieldName);
        if (cls == Byte.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("byte"));
            methodVisitor.visitMethodInsn(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
        } else if (cls == Short.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("short"));
            methodVisitor.visitMethodInsn(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
        } else if (cls == Integer.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("int"));
            methodVisitor.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        } else if (cls == Character.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("char"));
            methodVisitor.visitMethodInsn(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
        } else if (cls == Long.TYPE) {
            methodVisitor.visitVarInsn(22, context.var("long", 2));
            methodVisitor.visitMethodInsn(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
        } else if (cls == Float.TYPE) {
            methodVisitor.visitVarInsn(23, context.var("float"));
            methodVisitor.visitMethodInsn(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
        } else if (cls == Double.TYPE) {
            methodVisitor.visitVarInsn(24, context.var("double", 2));
            methodVisitor.visitMethodInsn(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
        } else if (cls == Boolean.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("boolean"));
            methodVisitor.visitMethodInsn(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
        } else if (cls == BigDecimal.class) {
            methodVisitor.visitVarInsn(25, context.var("decimal"));
        } else if (cls == String.class) {
            methodVisitor.visitVarInsn(25, context.var("string"));
        } else if (cls.isEnum()) {
            methodVisitor.visitVarInsn(25, context.var("enum"));
        } else if (List.class.isAssignableFrom(cls)) {
            methodVisitor.visitVarInsn(25, context.var("list"));
        } else {
            methodVisitor.visitVarInsn(25, context.var("object"));
        }
        methodVisitor.visitMethodInsn(182, JavaBeanSerializer, "processKey", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;");
        methodVisitor.visitVarInsn(58, Context.fieldName);
        methodVisitor.visitLabel(label);
    }

    private void _if_write_null(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Class<?> cls = fieldInfo.fieldClass;
        Label label = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        methodVisitor.visitLabel(label);
        JSONField annotation = fieldInfo.getAnnotation();
        int iOf = annotation != null ? SerializerFeature.of(annotation.serialzeFeatures()) : 0;
        if ((SerializerFeature.WRITE_MAP_NULL_FEATURES & iOf) == 0) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.WRITE_MAP_NULL_FEATURES));
            methodVisitor.visitMethodInsn(182, SerializeWriter, "isEnabled", "(I)Z");
            methodVisitor.visitJumpInsn(153, label2);
        }
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
        _writeFieldName(methodVisitor, context);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitLdcInsn(Integer.valueOf(iOf));
        if (cls == String.class || cls == Character.class) {
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.WriteNullStringAsEmpty.mask));
        } else if (Number.class.isAssignableFrom(cls)) {
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.WriteNullNumberAsZero.mask));
        } else if (cls == Boolean.class) {
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.WriteNullBooleanAsFalse.mask));
        } else if (Collection.class.isAssignableFrom(cls) || cls.isArray()) {
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.WriteNullListAsEmpty.mask));
        } else {
            methodVisitor.visitLdcInsn(0);
        }
        methodVisitor.visitMethodInsn(182, SerializeWriter, "writeNull", "(II)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitJumpInsn(167, label4);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitLabel(label4);
    }

    private void _writeFieldName(MethodVisitor methodVisitor, Context context) {
        if (context.writeDirect) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitMethodInsn(182, SerializeWriter, "writeFieldNameDirect", "(Ljava/lang/String;)V");
        } else {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitInsn(3);
            methodVisitor.visitMethodInsn(182, SerializeWriter, "writeFieldName", "(Ljava/lang/String;Z)V");
        }
    }

    private void _seperator(MethodVisitor methodVisitor, Context context) {
        methodVisitor.visitVarInsn(16, 44);
        methodVisitor.visitVarInsn(54, context.var("seperator"));
    }

    private void _getListFieldItemSer(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo, Class<?> cls) {
        Label label = new Label();
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_list_item_ser_", ObjectSerializer_desc);
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls)));
        methodVisitor.visitMethodInsn(182, JSONSerializer, "getObjectWriter", "(Ljava/lang/Class;)" + ObjectSerializer_desc);
        methodVisitor.visitFieldInsn(181, context.className, fieldInfo.name + "_asm_list_item_ser_", ObjectSerializer_desc);
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_list_item_ser_", ObjectSerializer_desc);
    }

    private void _getFieldSer(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo) {
        Label label = new Label();
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_ser_", ObjectSerializer_desc);
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(fieldInfo.fieldClass)));
        methodVisitor.visitMethodInsn(182, JSONSerializer, "getObjectWriter", "(Ljava/lang/Class;)" + ObjectSerializer_desc);
        methodVisitor.visitFieldInsn(181, context.className, fieldInfo.name + "_asm_ser_", ObjectSerializer_desc);
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_ser_", ObjectSerializer_desc);
    }
}