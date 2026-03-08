package com.tencent.bugly.proguard;

import androidx.core.app.NotificationCompat;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class f extends j {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f5686e;
    private Map<String, String> i;
    private Map<String, String> j;
    private static /* synthetic */ boolean m = !f.class.desiredAssertionStatus();
    private static byte[] k = null;
    private static Map<String, String> l = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public short f5682a = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private byte f5687f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f5688g = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5683b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f5684c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f5685d = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f5689h = 0;

    public final boolean equals(Object obj) {
        f fVar = (f) obj;
        return k.a(1, (int) fVar.f5682a) && k.a(1, (int) fVar.f5687f) && k.a(1, fVar.f5688g) && k.a(1, fVar.f5683b) && k.a((Object) 1, (Object) fVar.f5684c) && k.a((Object) 1, (Object) fVar.f5685d) && k.a((Object) 1, (Object) fVar.f5686e) && k.a(1, fVar.f5689h) && k.a((Object) 1, (Object) fVar.i) && k.a((Object) 1, (Object) fVar.j);
    }

    public final Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            if (m) {
                return null;
            }
            throw new AssertionError();
        }
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(i iVar) {
        iVar.a(this.f5682a, 1);
        iVar.a(this.f5687f, 2);
        iVar.a(this.f5688g, 3);
        iVar.a(this.f5683b, 4);
        iVar.a(this.f5684c, 5);
        iVar.a(this.f5685d, 6);
        iVar.a(this.f5686e, 7);
        iVar.a(this.f5689h, 8);
        iVar.a((Map) this.i, 9);
        iVar.a((Map) this.j, 10);
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(h hVar) {
        try {
            this.f5682a = hVar.a(this.f5682a, 1, true);
            this.f5687f = hVar.a(this.f5687f, 2, true);
            this.f5688g = hVar.a(this.f5688g, 3, true);
            this.f5683b = hVar.a(this.f5683b, 4, true);
            this.f5684c = hVar.b(5, true);
            this.f5685d = hVar.b(6, true);
            if (k == null) {
                k = new byte[]{0};
            }
            byte[] bArr = k;
            this.f5686e = hVar.c(7, true);
            this.f5689h = hVar.a(this.f5689h, 8, true);
            if (l == null) {
                HashMap map = new HashMap();
                l = map;
                map.put("", "");
            }
            this.i = (Map) hVar.a(l, 9, true);
            if (l == null) {
                HashMap map2 = new HashMap();
                l = map2;
                map2.put("", "");
            }
            this.j = (Map) hVar.a(l, 10, true);
        } catch (Exception e2) {
            e2.printStackTrace();
            System.out.println("RequestPacket decode error " + e.a(this.f5686e));
            throw new RuntimeException(e2);
        }
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(StringBuilder sb, int i) {
        z zVar = new z(sb, i);
        zVar.a(this.f5682a, "iVersion");
        zVar.a(this.f5687f, "cPacketType");
        zVar.a(this.f5688g, "iMessageType");
        zVar.a(this.f5683b, "iRequestId");
        zVar.a(this.f5684c, "sServantName");
        zVar.a(this.f5685d, "sFuncName");
        zVar.a(this.f5686e, "sBuffer");
        zVar.a(this.f5689h, "iTimeout");
        zVar.a((Map) this.i, "context");
        zVar.a((Map) this.j, NotificationCompat.CATEGORY_STATUS);
    }
}