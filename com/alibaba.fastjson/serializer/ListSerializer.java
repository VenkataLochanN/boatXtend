package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ListSerializer implements ObjectSerializer {
    public static final ListSerializer instance = new ListSerializer();

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        char c2;
        int i2;
        Object obj3;
        boolean zIsEnabled = jSONSerializer.out.isEnabled(SerializerFeature.WriteClassName);
        SerializeWriter serializeWriter = jSONSerializer.out;
        Type collectionItemType = zIsEnabled ? TypeUtils.getCollectionItemType(type) : null;
        if (obj == null) {
            serializeWriter.writeNull(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        List list = (List) obj;
        if (list.size() == 0) {
            serializeWriter.append((CharSequence) "[]");
            return;
        }
        SerialContext serialContext = jSONSerializer.context;
        jSONSerializer.setContext(serialContext, obj, obj2, 0);
        try {
            char c3 = ',';
            char c4 = ']';
            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                serializeWriter.append('[');
                jSONSerializer.incrementIndent();
                int i3 = 0;
                for (Object obj4 : list) {
                    if (i3 != 0) {
                        serializeWriter.append(c3);
                    }
                    jSONSerializer.println();
                    if (obj4 != null) {
                        if (jSONSerializer.containsReference(obj4)) {
                            jSONSerializer.writeReference(obj4);
                        } else {
                            ObjectSerializer objectWriter = jSONSerializer.getObjectWriter(obj4.getClass());
                            jSONSerializer.context = new SerialContext(serialContext, obj, obj2, 0, 0);
                            objectWriter.write(jSONSerializer, obj4, Integer.valueOf(i3), collectionItemType, 0);
                        }
                    } else {
                        jSONSerializer.out.writeNull();
                    }
                    i3++;
                    c3 = ',';
                }
                jSONSerializer.decrementIdent();
                jSONSerializer.println();
                serializeWriter.append(']');
                return;
            }
            serializeWriter.append('[');
            int size = list.size();
            int i4 = 0;
            while (i4 < size) {
                Object obj5 = list.get(i4);
                if (i4 != 0) {
                    c2 = ',';
                    serializeWriter.append(',');
                } else {
                    c2 = ',';
                }
                if (obj5 == null) {
                    serializeWriter.append((CharSequence) "null");
                } else {
                    Class<?> cls = obj5.getClass();
                    if (cls == Integer.class) {
                        serializeWriter.writeInt(((Integer) obj5).intValue());
                    } else if (cls == Long.class) {
                        long jLongValue = ((Long) obj5).longValue();
                        if (zIsEnabled) {
                            serializeWriter.writeLong(jLongValue);
                            serializeWriter.write(76);
                        } else {
                            serializeWriter.writeLong(jLongValue);
                        }
                    } else {
                        if ((SerializerFeature.DisableCircularReferenceDetect.mask & i) != 0) {
                            i2 = i4;
                            jSONSerializer.getObjectWriter(obj5.getClass()).write(jSONSerializer, obj5, Integer.valueOf(i4), collectionItemType, i);
                        } else {
                            i2 = i4;
                            if (serializeWriter.disableCircularReferenceDetect) {
                                obj3 = obj5;
                            } else {
                                obj3 = obj5;
                                jSONSerializer.context = new SerialContext(serialContext, obj, obj2, 0, 0);
                            }
                            if (jSONSerializer.containsReference(obj3)) {
                                jSONSerializer.writeReference(obj3);
                            } else {
                                jSONSerializer.getObjectWriter(obj3.getClass()).write(jSONSerializer, obj3, Integer.valueOf(i2), collectionItemType, 0);
                            }
                        }
                        i4 = i2 + 1;
                        c4 = ']';
                    }
                }
                i2 = i4;
                i4 = i2 + 1;
                c4 = ']';
            }
            serializeWriter.append(c4);
        } finally {
            jSONSerializer.context = serialContext;
        }
    }
}