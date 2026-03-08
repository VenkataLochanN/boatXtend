package com.tencent.bugly.proguard;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ByteBuffer f5694a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5695b;

    public i(int i) {
        this.f5695b = "GBK";
        this.f5694a = ByteBuffer.allocate(i);
    }

    public i() {
        this(128);
    }

    public final ByteBuffer a() {
        return this.f5694a;
    }

    public final byte[] b() {
        byte[] bArr = new byte[this.f5694a.position()];
        System.arraycopy(this.f5694a.array(), 0, bArr, 0, this.f5694a.position());
        return bArr;
    }

    private void a(int i) {
        if (this.f5694a.remaining() < i) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate((this.f5694a.capacity() + i) << 1);
            byteBufferAllocate.put(this.f5694a.array(), 0, this.f5694a.position());
            this.f5694a = byteBufferAllocate;
        }
    }

    private void b(byte b2, int i) {
        if (i < 15) {
            this.f5694a.put((byte) (b2 | (i << 4)));
        } else if (i < 256) {
            this.f5694a.put((byte) (b2 | 240));
            this.f5694a.put((byte) i);
        } else {
            throw new b("tag is too large: " + i);
        }
    }

    public final void a(boolean z, int i) {
        a(z ? (byte) 1 : (byte) 0, i);
    }

    public final void a(byte b2, int i) {
        a(3);
        if (b2 == 0) {
            b((byte) 12, i);
        } else {
            b((byte) 0, i);
            this.f5694a.put(b2);
        }
    }

    public final void a(short s, int i) {
        a(4);
        if (s >= -128 && s <= 127) {
            a((byte) s, i);
        } else {
            b((byte) 1, i);
            this.f5694a.putShort(s);
        }
    }

    public final void a(int i, int i2) {
        a(6);
        if (i >= -32768 && i <= 32767) {
            a((short) i, i2);
        } else {
            b((byte) 2, i2);
            this.f5694a.putInt(i);
        }
    }

    public final void a(long j, int i) {
        a(10);
        if (j >= -2147483648L && j <= 2147483647L) {
            a((int) j, i);
        } else {
            b((byte) 3, i);
            this.f5694a.putLong(j);
        }
    }

    public final void a(String str, int i) {
        byte[] bytes;
        try {
            bytes = str.getBytes(this.f5695b);
        } catch (UnsupportedEncodingException unused) {
            bytes = str.getBytes();
        }
        a(bytes.length + 10);
        if (bytes.length > 255) {
            b((byte) 7, i);
            this.f5694a.putInt(bytes.length);
            this.f5694a.put(bytes);
        } else {
            b((byte) 6, i);
            this.f5694a.put((byte) bytes.length);
            this.f5694a.put(bytes);
        }
    }

    public final <K, V> void a(Map<K, V> map, int i) {
        a(8);
        b((byte) 8, i);
        a(map == null ? 0 : map.size(), 0);
        if (map != null) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                a(entry.getKey(), 0);
                a(entry.getValue(), 1);
            }
        }
    }

    public final void a(byte[] bArr, int i) {
        a(bArr.length + 8);
        b((byte) 13, i);
        b((byte) 0, 0);
        a(bArr.length, 0);
        this.f5694a.put(bArr);
    }

    public final <T> void a(Collection<T> collection, int i) {
        a(8);
        b((byte) 9, i);
        a(collection == null ? 0 : collection.size(), 0);
        if (collection != null) {
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                a(it.next(), 0);
            }
        }
    }

    public final void a(j jVar, int i) {
        a(2);
        b((byte) 10, i);
        jVar.a(this);
        a(2);
        b((byte) 11, 0);
    }

    public final void a(Object obj, int i) {
        if (obj instanceof Byte) {
            a(((Byte) obj).byteValue(), i);
            return;
        }
        if (obj instanceof Boolean) {
            a(((Boolean) obj).booleanValue() ? (byte) 1 : (byte) 0, i);
            return;
        }
        if (obj instanceof Short) {
            a(((Short) obj).shortValue(), i);
            return;
        }
        if (obj instanceof Integer) {
            a(((Integer) obj).intValue(), i);
            return;
        }
        if (obj instanceof Long) {
            a(((Long) obj).longValue(), i);
            return;
        }
        if (obj instanceof Float) {
            float fFloatValue = ((Float) obj).floatValue();
            a(6);
            b((byte) 4, i);
            this.f5694a.putFloat(fFloatValue);
            return;
        }
        if (obj instanceof Double) {
            double dDoubleValue = ((Double) obj).doubleValue();
            a(10);
            b((byte) 5, i);
            this.f5694a.putDouble(dDoubleValue);
            return;
        }
        if (obj instanceof String) {
            a((String) obj, i);
            return;
        }
        if (obj instanceof Map) {
            a((Map) obj, i);
            return;
        }
        if (obj instanceof List) {
            a((Collection) obj, i);
            return;
        }
        if (obj instanceof j) {
            a(2);
            b((byte) 10, i);
            ((j) obj).a(this);
            a(2);
            b((byte) 11, 0);
            return;
        }
        if (obj instanceof byte[]) {
            a((byte[]) obj, i);
            return;
        }
        if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            a(8);
            b((byte) 9, i);
            a(zArr.length, 0);
            for (boolean z : zArr) {
                a(z ? (byte) 1 : (byte) 0, 0);
            }
            return;
        }
        if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            a(8);
            b((byte) 9, i);
            a(sArr.length, 0);
            for (short s : sArr) {
                a(s, 0);
            }
            return;
        }
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            a(8);
            b((byte) 9, i);
            a(iArr.length, 0);
            for (int i2 : iArr) {
                a(i2, 0);
            }
            return;
        }
        if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            a(8);
            b((byte) 9, i);
            a(jArr.length, 0);
            for (long j : jArr) {
                a(j, 0);
            }
            return;
        }
        if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            a(8);
            b((byte) 9, i);
            a(fArr.length, 0);
            for (float f2 : fArr) {
                a(6);
                b((byte) 4, 0);
                this.f5694a.putFloat(f2);
            }
            return;
        }
        if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            a(8);
            b((byte) 9, i);
            a(dArr.length, 0);
            for (double d2 : dArr) {
                a(10);
                b((byte) 5, 0);
                this.f5694a.putDouble(d2);
            }
            return;
        }
        if (obj.getClass().isArray()) {
            Object[] objArr = (Object[]) obj;
            a(8);
            b((byte) 9, i);
            a(objArr.length, 0);
            for (Object obj2 : objArr) {
                a(obj2, 0);
            }
            return;
        }
        if (obj instanceof Collection) {
            a((Collection) obj, i);
        } else {
            throw new b("write object error: unsupport type. " + obj.getClass());
        }
    }

    public final int a(String str) {
        this.f5695b = str;
        return 0;
    }
}