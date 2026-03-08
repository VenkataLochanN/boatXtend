package com.tencent.bugly.proguard;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class d extends c {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static HashMap<String, byte[]> f5678f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static HashMap<String, HashMap<String, byte[]>> f5679g;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private f f5680e = new f();

    public d() {
        this.f5680e.f5682a = (short) 2;
    }

    @Override // com.tencent.bugly.proguard.c, com.tencent.bugly.proguard.a
    public final <T> void a(String str, T t) {
        if (str.startsWith(".")) {
            throw new IllegalArgumentException("put name can not startwith . , now is " + str);
        }
        super.a(str, t);
    }

    @Override // com.tencent.bugly.proguard.c
    public final void c() {
        super.c();
        this.f5680e.f5682a = (short) 3;
    }

    @Override // com.tencent.bugly.proguard.c, com.tencent.bugly.proguard.a
    public final byte[] a() {
        if (this.f5680e.f5682a == 2) {
            if (this.f5680e.f5684c.equals("")) {
                throw new IllegalArgumentException("servantName can not is null");
            }
            if (this.f5680e.f5685d.equals("")) {
                throw new IllegalArgumentException("funcName can not is null");
            }
        } else {
            if (this.f5680e.f5684c == null) {
                this.f5680e.f5684c = "";
            }
            if (this.f5680e.f5685d == null) {
                this.f5680e.f5685d = "";
            }
        }
        i iVar = new i(0);
        iVar.a(this.f5579b);
        if (this.f5680e.f5682a == 2) {
            iVar.a((Map) this.f5578a, 0);
        } else {
            iVar.a((Map) this.f5675d, 0);
        }
        this.f5680e.f5686e = k.a(iVar.a());
        i iVar2 = new i(0);
        iVar2.a(this.f5579b);
        this.f5680e.a(iVar2);
        byte[] bArrA = k.a(iVar2.a());
        int length = bArrA.length + 4;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length);
        byteBufferAllocate.putInt(length).put(bArrA).flip();
        return byteBufferAllocate.array();
    }

    @Override // com.tencent.bugly.proguard.c, com.tencent.bugly.proguard.a
    public final void a(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        try {
            h hVar = new h(bArr, 4);
            hVar.a(this.f5579b);
            this.f5680e.a(hVar);
            if (this.f5680e.f5682a == 3) {
                h hVar2 = new h(this.f5680e.f5686e);
                hVar2.a(this.f5579b);
                if (f5678f == null) {
                    HashMap<String, byte[]> map = new HashMap<>();
                    f5678f = map;
                    map.put("", new byte[0]);
                }
                this.f5675d = hVar2.a((Map) f5678f, 0, false);
                return;
            }
            h hVar3 = new h(this.f5680e.f5686e);
            hVar3.a(this.f5579b);
            if (f5679g == null) {
                f5679g = new HashMap<>();
                HashMap<String, byte[]> map2 = new HashMap<>();
                map2.put("", new byte[0]);
                f5679g.put("", map2);
            }
            this.f5578a = hVar3.a((Map) f5679g, 0, false);
            new HashMap();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public final void b(String str) {
        this.f5680e.f5684c = str;
    }

    public final void c(String str) {
        this.f5680e.f5685d = str;
    }

    public final void a(int i) {
        this.f5680e.f5683b = 1;
    }
}