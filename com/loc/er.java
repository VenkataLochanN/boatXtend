package com.loc;

import com.bumptech.glide.load.Key;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.Arrays;

/* JADX INFO: compiled from: FlatBufferBuilder.java */
/* JADX INFO: loaded from: classes3.dex */
public class er {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ByteBuffer f5186a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f5187b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f5188d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int[] f5189e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f5190f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    boolean f5191g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    boolean f5192h;
    int i;
    int[] j;
    int k;
    int l;
    boolean m;
    CharsetEncoder n;
    ByteBuffer o;
    static final /* synthetic */ boolean p = !er.class.desiredAssertionStatus();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final Charset f5185c = Charset.forName(Key.STRING_CHARSET_NAME);

    private er() {
        this.f5188d = 1;
        this.f5189e = null;
        this.f5190f = 0;
        this.f5191g = false;
        this.f5192h = false;
        this.j = new int[16];
        this.k = 0;
        this.l = 0;
        this.m = false;
        this.n = f5185c.newEncoder();
        this.f5187b = 1024;
        this.f5186a = d(1024);
    }

    public er(ByteBuffer byteBuffer) {
        this.f5188d = 1;
        this.f5189e = null;
        this.f5190f = 0;
        this.f5191g = false;
        this.f5192h = false;
        this.j = new int[16];
        this.k = 0;
        this.l = 0;
        this.m = false;
        this.n = f5185c.newEncoder();
        a(byteBuffer);
    }

    private void a(short s) {
        c(2, 0);
        ByteBuffer byteBuffer = this.f5186a;
        int i = this.f5187b - 2;
        this.f5187b = i;
        byteBuffer.putShort(i, s);
    }

    private void c(int i, int i2) {
        if (i > this.f5188d) {
            this.f5188d = i;
        }
        int i3 = ((~((this.f5186a.capacity() - this.f5187b) + i2)) + 1) & (i - 1);
        while (this.f5187b < i3 + i + i2) {
            int iCapacity = this.f5186a.capacity();
            ByteBuffer byteBuffer = this.f5186a;
            int iCapacity2 = byteBuffer.capacity();
            if (((-1073741824) & iCapacity2) != 0) {
                throw new AssertionError("FlatBuffers: cannot grow buffer beyond 2 gigabytes.");
            }
            int i4 = iCapacity2 << 1;
            byteBuffer.position(0);
            ByteBuffer byteBufferD = d(i4);
            byteBufferD.position(i4 - iCapacity2);
            byteBufferD.put(byteBuffer);
            this.f5186a = byteBufferD;
            this.f5187b += this.f5186a.capacity() - iCapacity;
        }
        e(i3);
    }

    private int d() {
        return this.f5186a.capacity() - this.f5187b;
    }

    private static ByteBuffer d(int i) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        return byteBufferAllocate;
    }

    private void e() {
        if (this.f5191g) {
            throw new AssertionError("FlatBuffers: object serialization must not be nested.");
        }
    }

    private void e(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            ByteBuffer byteBuffer = this.f5186a;
            int i3 = this.f5187b - 1;
            this.f5187b = i3;
            byteBuffer.put(i3, (byte) 0);
        }
    }

    private void f(int i) {
        ByteBuffer byteBuffer = this.f5186a;
        int i2 = this.f5187b - 4;
        this.f5187b = i2;
        byteBuffer.putInt(i2, i);
    }

    private void g(int i) {
        c(4, 0);
        f(i);
    }

    private void h(int i) {
        this.f5189e[i] = d();
    }

    public final int a() {
        if (!this.f5191g) {
            throw new AssertionError("FlatBuffers: endVector called without startVector");
        }
        this.f5191g = false;
        f(this.l);
        return d();
    }

    public int a(CharSequence charSequence) {
        int length = (int) (charSequence.length() * this.n.maxBytesPerChar());
        ByteBuffer byteBuffer = this.o;
        if (byteBuffer == null || byteBuffer.capacity() < length) {
            this.o = ByteBuffer.allocate(Math.max(128, length));
        }
        this.o.clear();
        CoderResult coderResultEncode = this.n.encode(charSequence instanceof CharBuffer ? (CharBuffer) charSequence : CharBuffer.wrap(charSequence), this.o, true);
        if (coderResultEncode.isError()) {
            try {
                coderResultEncode.throwException();
            } catch (CharacterCodingException e2) {
                throw new Error(e2);
            }
        }
        this.o.flip();
        ByteBuffer byteBuffer2 = this.o;
        int iRemaining = byteBuffer2.remaining();
        a((byte) 0);
        a(1, iRemaining, 1);
        ByteBuffer byteBuffer3 = this.f5186a;
        int i = this.f5187b - iRemaining;
        this.f5187b = i;
        byteBuffer3.position(i);
        this.f5186a.put(byteBuffer2);
        return a();
    }

    public final er a(ByteBuffer byteBuffer) {
        this.f5186a = byteBuffer;
        this.f5186a.clear();
        this.f5186a.order(ByteOrder.LITTLE_ENDIAN);
        this.f5188d = 1;
        this.f5187b = this.f5186a.capacity();
        this.f5190f = 0;
        this.f5191g = false;
        this.f5192h = false;
        this.i = 0;
        this.k = 0;
        this.l = 0;
        return this;
    }

    public final void a(byte b2) {
        c(1, 0);
        ByteBuffer byteBuffer = this.f5186a;
        int i = this.f5187b - 1;
        this.f5187b = i;
        byteBuffer.put(i, b2);
    }

    public final void a(int i) {
        c(4, 0);
        if (!p && i > d()) {
            throw new AssertionError();
        }
        f((d() - i) + 4);
    }

    public final void a(int i, byte b2) {
        if (this.m || b2 != 0) {
            a(b2);
            h(i);
        }
    }

    public final void a(int i, int i2) {
        if (this.m || i2 != 0) {
            g(i2);
            h(i);
        }
    }

    public final void a(int i, int i2, int i3) {
        e();
        this.l = i2;
        int i4 = i * i2;
        c(4, i4);
        c(i3, i4);
        this.f5191g = true;
    }

    public final void a(int i, long j) {
        if (this.m || j != 0) {
            c(8, 0);
            ByteBuffer byteBuffer = this.f5186a;
            int i2 = this.f5187b - 8;
            this.f5187b = i2;
            byteBuffer.putLong(i2, j);
            h(i);
        }
    }

    public final void a(int i, short s) {
        if (this.m || s != 0) {
            a(s);
            h(i);
        }
    }

    public final void a(boolean z) {
        if (this.m || z) {
            c(1, 0);
            ByteBuffer byteBuffer = this.f5186a;
            int i = this.f5187b - 1;
            this.f5187b = i;
            byteBuffer.put(i, z ? (byte) 1 : (byte) 0);
            h(0);
        }
    }

    public final int b() {
        int i;
        int i2;
        if (this.f5189e == null || !this.f5191g) {
            throw new AssertionError("FlatBuffers: endObject called without startObject");
        }
        g(0);
        int iD = d();
        for (int i3 = this.f5190f - 1; i3 >= 0; i3--) {
            int[] iArr = this.f5189e;
            a((short) (iArr[i3] != 0 ? iD - iArr[i3] : 0));
        }
        a((short) (iD - this.i));
        a((short) ((this.f5190f + 2) * 2));
        int i4 = 0;
        loop1: while (true) {
            if (i4 >= this.k) {
                i = 0;
                break;
            }
            int iCapacity = this.f5186a.capacity() - this.j[i4];
            int i5 = this.f5187b;
            short s = this.f5186a.getShort(iCapacity);
            if (s == this.f5186a.getShort(i5)) {
                for (2; i2 < s; i2 + 2) {
                    i2 = this.f5186a.getShort(iCapacity + i2) == this.f5186a.getShort(i5 + i2) ? i2 + 2 : 2;
                }
                i = this.j[i4];
                break loop1;
            }
            i4++;
        }
        if (i != 0) {
            this.f5187b = this.f5186a.capacity() - iD;
            this.f5186a.putInt(this.f5187b, i - iD);
        } else {
            int i6 = this.k;
            int[] iArr2 = this.j;
            if (i6 == iArr2.length) {
                this.j = Arrays.copyOf(iArr2, i6 * 2);
            }
            int[] iArr3 = this.j;
            int i7 = this.k;
            this.k = i7 + 1;
            iArr3[i7] = d();
            ByteBuffer byteBuffer = this.f5186a;
            byteBuffer.putInt(byteBuffer.capacity() - iD, d() - iD);
        }
        this.f5191g = false;
        return iD;
    }

    public final void b(int i) {
        e();
        int[] iArr = this.f5189e;
        if (iArr == null || iArr.length < i) {
            this.f5189e = new int[i];
        }
        this.f5190f = i;
        Arrays.fill(this.f5189e, 0, this.f5190f, 0);
        this.f5191g = true;
        this.i = d();
    }

    public final void b(int i, int i2) {
        if (this.m || i2 != 0) {
            a(i2);
            h(i);
        }
    }

    public final void c(int i) {
        c(this.f5188d, 4);
        a(i);
        this.f5186a.position(this.f5187b);
        this.f5192h = true;
    }

    public final byte[] c() {
        int i = this.f5187b;
        int iCapacity = this.f5186a.capacity() - this.f5187b;
        if (!this.f5192h) {
            throw new AssertionError("FlatBuffers: you can only access the serialized buffer after it has been finished by FlatBufferBuilder.finish().");
        }
        byte[] bArr = new byte[iCapacity];
        this.f5186a.position(i);
        this.f5186a.get(bArr);
        return bArr;
    }
}