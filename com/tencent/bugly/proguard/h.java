package com.tencent.bugly.proguard;

import com.realsil.sdk.dfu.DfuConstants;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ByteBuffer f5690a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5691b = "GBK";

    /* JADX INFO: compiled from: BUGLY */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public byte f5692a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5693b;
    }

    public h() {
    }

    public h(byte[] bArr) {
        this.f5690a = ByteBuffer.wrap(bArr);
    }

    public h(byte[] bArr, int i) {
        this.f5690a = ByteBuffer.wrap(bArr);
        this.f5690a.position(4);
    }

    public final void a(byte[] bArr) {
        ByteBuffer byteBuffer = this.f5690a;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
        this.f5690a = ByteBuffer.wrap(bArr);
    }

    private static int a(a aVar, ByteBuffer byteBuffer) {
        byte b2 = byteBuffer.get();
        aVar.f5692a = (byte) (b2 & DfuConstants.BANK_INFO_NOT_SUPPORTED);
        aVar.f5693b = (b2 & 240) >> 4;
        if (aVar.f5693b != 15) {
            return 1;
        }
        aVar.f5693b = byteBuffer.get();
        return 2;
    }

    private boolean a(int i) {
        a aVar;
        try {
            aVar = new a();
            while (true) {
                int iA = a(aVar, this.f5690a.duplicate());
                if (i <= aVar.f5693b || aVar.f5692a == 11) {
                    break;
                }
                this.f5690a.position(this.f5690a.position() + iA);
                a(aVar.f5692a);
            }
        } catch (g | BufferUnderflowException unused) {
        }
        return i == aVar.f5693b;
    }

    private void a() {
        a aVar = new a();
        do {
            a(aVar, this.f5690a);
            a(aVar.f5692a);
        } while (aVar.f5692a != 11);
    }

    private void a(byte b2) {
        int i = 0;
        switch (b2) {
            case 0:
                ByteBuffer byteBuffer = this.f5690a;
                byteBuffer.position(byteBuffer.position() + 1);
                return;
            case 1:
                ByteBuffer byteBuffer2 = this.f5690a;
                byteBuffer2.position(byteBuffer2.position() + 2);
                return;
            case 2:
                ByteBuffer byteBuffer3 = this.f5690a;
                byteBuffer3.position(byteBuffer3.position() + 4);
                return;
            case 3:
                ByteBuffer byteBuffer4 = this.f5690a;
                byteBuffer4.position(byteBuffer4.position() + 8);
                return;
            case 4:
                ByteBuffer byteBuffer5 = this.f5690a;
                byteBuffer5.position(byteBuffer5.position() + 4);
                return;
            case 5:
                ByteBuffer byteBuffer6 = this.f5690a;
                byteBuffer6.position(byteBuffer6.position() + 8);
                return;
            case 6:
                int i2 = this.f5690a.get();
                if (i2 < 0) {
                    i2 += 256;
                }
                ByteBuffer byteBuffer7 = this.f5690a;
                byteBuffer7.position(byteBuffer7.position() + i2);
                return;
            case 7:
                int i3 = this.f5690a.getInt();
                ByteBuffer byteBuffer8 = this.f5690a;
                byteBuffer8.position(byteBuffer8.position() + i3);
                return;
            case 8:
                int iA = a(0, 0, true);
                while (i < (iA << 1)) {
                    a aVar = new a();
                    a(aVar, this.f5690a);
                    a(aVar.f5692a);
                    i++;
                }
                return;
            case 9:
                int iA2 = a(0, 0, true);
                while (i < iA2) {
                    a aVar2 = new a();
                    a(aVar2, this.f5690a);
                    a(aVar2.f5692a);
                    i++;
                }
                return;
            case 10:
                a();
                return;
            case 11:
            case 12:
                return;
            case 13:
                a aVar3 = new a();
                a(aVar3, this.f5690a);
                if (aVar3.f5692a != 0) {
                    throw new g("skipField with invalid type, type value: " + ((int) b2) + ", " + ((int) aVar3.f5692a));
                }
                int iA3 = a(0, 0, true);
                ByteBuffer byteBuffer9 = this.f5690a;
                byteBuffer9.position(byteBuffer9.position() + iA3);
                return;
            default:
                throw new g("invalid type.");
        }
    }

    public final boolean a(int i, boolean z) {
        return a((byte) 0, i, z) != 0;
    }

    public final byte a(byte b2, int i, boolean z) {
        if (!a(i)) {
            if (z) {
                throw new g("require field not exist.");
            }
            return b2;
        }
        a aVar = new a();
        a(aVar, this.f5690a);
        byte b3 = aVar.f5692a;
        if (b3 == 0) {
            return this.f5690a.get();
        }
        if (b3 == 12) {
            return (byte) 0;
        }
        throw new g("type mismatch.");
    }

    public final short a(short s, int i, boolean z) {
        if (!a(i)) {
            if (z) {
                throw new g("require field not exist.");
            }
            return s;
        }
        a aVar = new a();
        a(aVar, this.f5690a);
        byte b2 = aVar.f5692a;
        if (b2 == 0) {
            return this.f5690a.get();
        }
        if (b2 == 1) {
            return this.f5690a.getShort();
        }
        if (b2 == 12) {
            return (short) 0;
        }
        throw new g("type mismatch.");
    }

    public final int a(int i, int i2, boolean z) {
        if (!a(i2)) {
            if (z) {
                throw new g("require field not exist.");
            }
            return i;
        }
        a aVar = new a();
        a(aVar, this.f5690a);
        byte b2 = aVar.f5692a;
        if (b2 == 0) {
            return this.f5690a.get();
        }
        if (b2 == 1) {
            return this.f5690a.getShort();
        }
        if (b2 == 2) {
            return this.f5690a.getInt();
        }
        if (b2 == 12) {
            return 0;
        }
        throw new g("type mismatch.");
    }

    public final long a(long j, int i, boolean z) {
        int i2;
        if (!a(i)) {
            if (z) {
                throw new g("require field not exist.");
            }
            return j;
        }
        a aVar = new a();
        a(aVar, this.f5690a);
        byte b2 = aVar.f5692a;
        if (b2 == 0) {
            i2 = this.f5690a.get();
        } else if (b2 == 1) {
            i2 = this.f5690a.getShort();
        } else {
            if (b2 != 2) {
                if (b2 == 3) {
                    return this.f5690a.getLong();
                }
                if (b2 == 12) {
                    return 0L;
                }
                throw new g("type mismatch.");
            }
            i2 = this.f5690a.getInt();
        }
        return i2;
    }

    private float a(float f2, int i, boolean z) {
        if (!a(i)) {
            if (z) {
                throw new g("require field not exist.");
            }
            return f2;
        }
        a aVar = new a();
        a(aVar, this.f5690a);
        byte b2 = aVar.f5692a;
        if (b2 == 4) {
            return this.f5690a.getFloat();
        }
        if (b2 == 12) {
            return 0.0f;
        }
        throw new g("type mismatch.");
    }

    private double a(double d2, int i, boolean z) {
        if (!a(i)) {
            if (z) {
                throw new g("require field not exist.");
            }
            return d2;
        }
        a aVar = new a();
        a(aVar, this.f5690a);
        byte b2 = aVar.f5692a;
        if (b2 == 4) {
            return this.f5690a.getFloat();
        }
        if (b2 == 5) {
            return this.f5690a.getDouble();
        }
        if (b2 == 12) {
            return 0.0d;
        }
        throw new g("type mismatch.");
    }

    public final String b(int i, boolean z) {
        if (!a(i)) {
            if (z) {
                throw new g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        a(aVar, this.f5690a);
        byte b2 = aVar.f5692a;
        if (b2 == 6) {
            int i2 = this.f5690a.get();
            if (i2 < 0) {
                i2 += 256;
            }
            byte[] bArr = new byte[i2];
            this.f5690a.get(bArr);
            try {
                return new String(bArr, this.f5691b);
            } catch (UnsupportedEncodingException unused) {
                return new String(bArr);
            }
        }
        if (b2 == 7) {
            int i3 = this.f5690a.getInt();
            if (i3 > 104857600 || i3 < 0) {
                throw new g("String too long: " + i3);
            }
            byte[] bArr2 = new byte[i3];
            this.f5690a.get(bArr2);
            try {
                return new String(bArr2, this.f5691b);
            } catch (UnsupportedEncodingException unused2) {
                return new String(bArr2);
            }
        }
        throw new g("type mismatch.");
    }

    public final <K, V> HashMap<K, V> a(Map<K, V> map, int i, boolean z) {
        return (HashMap) a(new HashMap(), map, i, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <K, V> Map<K, V> a(Map<K, V> map, Map<K, V> map2, int i, boolean z) {
        if (map2 == null || map2.isEmpty()) {
            return new HashMap();
        }
        Map.Entry<K, V> next = map2.entrySet().iterator().next();
        K key = next.getKey();
        V value = next.getValue();
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.f5690a);
            if (aVar.f5692a == 8) {
                int iA = a(0, 0, true);
                if (iA < 0) {
                    throw new g("size invalid: " + iA);
                }
                for (int i2 = 0; i2 < iA; i2++) {
                    map.put(a(key, 0, true), a(value, 1, true));
                }
            } else {
                throw new g("type mismatch.");
            }
        } else if (z) {
            throw new g("require field not exist.");
        }
        return map;
    }

    private boolean[] d(int i, boolean z) {
        if (!a(i)) {
            if (z) {
                throw new g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        a(aVar, this.f5690a);
        if (aVar.f5692a == 9) {
            int iA = a(0, 0, true);
            if (iA < 0) {
                throw new g("size invalid: " + iA);
            }
            boolean[] zArr = new boolean[iA];
            for (int i2 = 0; i2 < iA; i2++) {
                zArr[i2] = a((byte) 0, 0, true) != 0;
            }
            return zArr;
        }
        throw new g("type mismatch.");
    }

    public final byte[] c(int i, boolean z) {
        if (!a(i)) {
            if (z) {
                throw new g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        a(aVar, this.f5690a);
        byte b2 = aVar.f5692a;
        if (b2 == 9) {
            int iA = a(0, 0, true);
            if (iA < 0) {
                throw new g("size invalid: " + iA);
            }
            byte[] bArr = new byte[iA];
            for (int i2 = 0; i2 < iA; i2++) {
                bArr[i2] = a(bArr[0], 0, true);
            }
            return bArr;
        }
        if (b2 == 13) {
            a aVar2 = new a();
            a(aVar2, this.f5690a);
            if (aVar2.f5692a != 0) {
                throw new g("type mismatch, tag: " + i + ", type: " + ((int) aVar.f5692a) + ", " + ((int) aVar2.f5692a));
            }
            int iA2 = a(0, 0, true);
            if (iA2 < 0) {
                throw new g("invalid size, tag: " + i + ", type: " + ((int) aVar.f5692a) + ", " + ((int) aVar2.f5692a) + ", size: " + iA2);
            }
            byte[] bArr2 = new byte[iA2];
            this.f5690a.get(bArr2);
            return bArr2;
        }
        throw new g("type mismatch.");
    }

    private short[] e(int i, boolean z) {
        if (!a(i)) {
            if (z) {
                throw new g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        a(aVar, this.f5690a);
        if (aVar.f5692a == 9) {
            int iA = a(0, 0, true);
            if (iA < 0) {
                throw new g("size invalid: " + iA);
            }
            short[] sArr = new short[iA];
            for (int i2 = 0; i2 < iA; i2++) {
                sArr[i2] = a(sArr[0], 0, true);
            }
            return sArr;
        }
        throw new g("type mismatch.");
    }

    private int[] f(int i, boolean z) {
        if (!a(i)) {
            if (z) {
                throw new g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        a(aVar, this.f5690a);
        if (aVar.f5692a == 9) {
            int iA = a(0, 0, true);
            if (iA < 0) {
                throw new g("size invalid: " + iA);
            }
            int[] iArr = new int[iA];
            for (int i2 = 0; i2 < iA; i2++) {
                iArr[i2] = a(iArr[0], 0, true);
            }
            return iArr;
        }
        throw new g("type mismatch.");
    }

    private long[] g(int i, boolean z) {
        if (!a(i)) {
            if (z) {
                throw new g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        a(aVar, this.f5690a);
        if (aVar.f5692a == 9) {
            int iA = a(0, 0, true);
            if (iA < 0) {
                throw new g("size invalid: " + iA);
            }
            long[] jArr = new long[iA];
            for (int i2 = 0; i2 < iA; i2++) {
                jArr[i2] = a(jArr[0], 0, true);
            }
            return jArr;
        }
        throw new g("type mismatch.");
    }

    private float[] h(int i, boolean z) {
        if (!a(i)) {
            if (z) {
                throw new g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        a(aVar, this.f5690a);
        if (aVar.f5692a == 9) {
            int iA = a(0, 0, true);
            if (iA < 0) {
                throw new g("size invalid: " + iA);
            }
            float[] fArr = new float[iA];
            for (int i2 = 0; i2 < iA; i2++) {
                fArr[i2] = a(fArr[0], 0, true);
            }
            return fArr;
        }
        throw new g("type mismatch.");
    }

    private double[] i(int i, boolean z) {
        if (!a(i)) {
            if (z) {
                throw new g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        a(aVar, this.f5690a);
        if (aVar.f5692a == 9) {
            int iA = a(0, 0, true);
            if (iA < 0) {
                throw new g("size invalid: " + iA);
            }
            double[] dArr = new double[iA];
            for (int i2 = 0; i2 < iA; i2++) {
                dArr[i2] = a(dArr[0], 0, true);
            }
            return dArr;
        }
        throw new g("type mismatch.");
    }

    private <T> T[] a(T[] tArr, int i, boolean z) {
        if (tArr == null || tArr.length == 0) {
            throw new g("unable to get type of key and value.");
        }
        return (T[]) b(tArr[0], i, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> T[] b(T t, int i, boolean z) {
        if (!a(i)) {
            if (z) {
                throw new g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        a(aVar, this.f5690a);
        if (aVar.f5692a == 9) {
            int iA = a(0, 0, true);
            if (iA < 0) {
                throw new g("size invalid: " + iA);
            }
            T[] tArr = (T[]) ((Object[]) Array.newInstance(t.getClass(), iA));
            for (int i2 = 0; i2 < iA; i2++) {
                tArr[i2] = a((Object) t, 0, true);
            }
            return tArr;
        }
        throw new g("type mismatch.");
    }

    public final j a(j jVar, int i, boolean z) {
        if (!a(i)) {
            if (z) {
                throw new g("require field not exist.");
            }
            return null;
        }
        try {
            j jVar2 = (j) jVar.getClass().newInstance();
            a aVar = new a();
            a(aVar, this.f5690a);
            if (aVar.f5692a != 10) {
                throw new g("type mismatch.");
            }
            jVar2.a(this);
            a();
            return jVar2;
        } catch (Exception e2) {
            throw new g(e2.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> Object a(T t, int i, boolean z) {
        if (t instanceof Byte) {
            return Byte.valueOf(a((byte) 0, i, z));
        }
        if (t instanceof Boolean) {
            return Boolean.valueOf(a((byte) 0, i, z) != 0);
        }
        if (t instanceof Short) {
            return Short.valueOf(a((short) 0, i, z));
        }
        if (t instanceof Integer) {
            return Integer.valueOf(a(0, i, z));
        }
        if (t instanceof Long) {
            return Long.valueOf(a(0L, i, z));
        }
        if (t instanceof Float) {
            return Float.valueOf(a(0.0f, i, z));
        }
        if (t instanceof Double) {
            return Double.valueOf(a(0.0d, i, z));
        }
        if (t instanceof String) {
            return String.valueOf(b(i, z));
        }
        if (t instanceof Map) {
            return (HashMap) a(new HashMap(), (Map) t, i, z);
        }
        if (t instanceof List) {
            List list = (List) t;
            if (list == null || list.isEmpty()) {
                return new ArrayList();
            }
            Object[] objArrB = b(list.get(0), i, z);
            if (objArrB == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj : objArrB) {
                arrayList.add(obj);
            }
            return arrayList;
        }
        if (t instanceof j) {
            return a((j) t, i, z);
        }
        if (t.getClass().isArray()) {
            if ((t instanceof byte[]) || (t instanceof Byte[])) {
                return c(i, z);
            }
            if (t instanceof boolean[]) {
                return d(i, z);
            }
            if (t instanceof short[]) {
                return e(i, z);
            }
            if (t instanceof int[]) {
                return f(i, z);
            }
            if (t instanceof long[]) {
                return g(i, z);
            }
            if (t instanceof float[]) {
                return h(i, z);
            }
            if (t instanceof double[]) {
                return i(i, z);
            }
            return a((Object[]) t, i, z);
        }
        throw new g("read object error: unsupport type.");
    }

    public final int a(String str) {
        this.f5691b = str;
        return 0;
    }
}