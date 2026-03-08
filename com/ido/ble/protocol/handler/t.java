package com.ido.ble.protocol.handler;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.logs.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Handler f4645a = new Handler(Looper.getMainLooper());

    static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f4646a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f4647b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f4648c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f4649d;

        a(int i, int i2, int i3, int i4) {
            this.f4646a = i;
            this.f4647b = i2;
            this.f4648c = i3;
            this.f4649d = i4;
        }

        @Override // java.lang.Runnable
        public void run() {
            t.c(this.f4646a, this.f4647b, this.f4648c, this.f4649d);
        }
    }

    static class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ byte[] f4650a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f4651b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f4652c;

        b(byte[] bArr, int i, int i2) {
            this.f4650a = bArr;
            this.f4651b = i;
            this.f4652c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            t.c(this.f4650a, this.f4651b, this.f4652c);
        }
    }

    public static int a(byte[] bArr) {
        com.ido.ble.bluetooth.a.a(bArr);
        return 0;
    }

    public static int b(byte[] bArr) {
        com.ido.ble.bluetooth.bt.c.a(bArr);
        return 0;
    }

    public static void b(int i, int i2, int i3, int i4) {
        f4645a.post(new a(i, i2, i3, i4));
    }

    public static void b(byte[] bArr, int i, int i2) {
        f4645a.post(new b(bArr, i, i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(int i, int i2, int i3, int i4) {
        if (i == 9728) {
            s.a(i2);
            return;
        }
        if (i2 == 403) {
            q.a(i2, i3, i4);
            return;
        }
        if (j.a(i2)) {
            j.a(i2, i3, i4);
            return;
        }
        if (l.a(i2)) {
            l.a(i2, i3, i4);
            return;
        }
        if (r.a(i2)) {
            r.a(i2, i3, i4);
            return;
        }
        if (c.a(i2)) {
            c.a(i2, i3, i4);
            return;
        }
        if (v.b(i2)) {
            v.a(i2, i3, i4);
            return;
        }
        if (p.a(i2)) {
            p.a(i2, i3, i4);
            return;
        }
        if (com.ido.ble.protocol.handler.a.a(i2)) {
            com.ido.ble.protocol.handler.a.a(i2, i3, i4);
            return;
        }
        if (e.a(i2)) {
            e.a(i2, i3, i4);
            return;
        }
        if (SyncHandler.a(i2)) {
            SyncHandler.a(i2, i3, i4);
            return;
        }
        if (com.ido.ble.protocol.handler.b.a(i2)) {
            com.ido.ble.protocol.handler.b.a(i2, i3, i4);
            return;
        }
        if (d.a(i2)) {
            d.a(i2, i3, i4);
            return;
        }
        if (q.a(i2)) {
            q.a(i2, i3, i4);
            return;
        }
        if (com.ido.ble.h.d.c(i2)) {
            com.ido.ble.h.d.a(i2, i3, i4);
            return;
        }
        if (o.a(i2)) {
            o.a(i2, i3, i4);
            return;
        }
        if (SyncV3Handler.isSyncV3Type(i2)) {
            SyncV3Handler.handleIntResult(i2, i3, i4);
            return;
        }
        if (m.a(i2)) {
            m.a(i2, i3, i4);
            return;
        }
        if (g.a(i2)) {
            g.a(i2, i3, i4);
            return;
        }
        if (com.ido.ble.watch.custom.c.a(i2)) {
            com.ido.ble.watch.custom.c.a(i2, i3, i4);
            return;
        }
        if (x.a(i2)) {
            x.a(i2, i3, i4);
            return;
        }
        if (f.a(i2)) {
            f.a(i2, i3, i4);
            return;
        }
        if (w.a(i2)) {
            w.a(i2, i3, i4);
            return;
        }
        if (i.a(i2)) {
            i.a(i2, i3, i4);
            return;
        }
        if (n.a(i2)) {
            n.a(i2, i3, i4);
            return;
        }
        LogTool.b("SoLibCallBackDispatcher", "[handleCallBackSysEvt] need handle type = " + i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(byte[] bArr, int i, int i2) {
        if (j.a(i)) {
            j.a(i, bArr, i2);
            return;
        }
        if (l.a(i)) {
            l.a(i, bArr, i2);
            return;
        }
        if (r.a(i)) {
            r.a(i, bArr, i2);
            return;
        }
        if (c.a(i)) {
            c.a(i, bArr, i2);
            return;
        }
        if (v.b(i)) {
            v.a(i, bArr, i2);
            return;
        }
        if (p.a(i)) {
            p.a(i, bArr, i2);
            return;
        }
        if (com.ido.ble.protocol.handler.a.a(i)) {
            com.ido.ble.protocol.handler.a.a(i, bArr, i2);
            return;
        }
        if (e.a(i)) {
            e.a(i, bArr, i2);
            return;
        }
        if (SyncHandler.a(i)) {
            SyncHandler.a(i, bArr, i2);
            return;
        }
        if (com.ido.ble.protocol.handler.b.a(i)) {
            com.ido.ble.protocol.handler.b.a(i, bArr, i2);
            return;
        }
        if (d.a(i)) {
            d.a(i, bArr, i2);
            return;
        }
        if (q.a(i)) {
            q.a(i, bArr, i2);
            return;
        }
        if (com.ido.ble.h.d.c(i)) {
            com.ido.ble.h.d.a(i, bArr, i2);
            return;
        }
        if (o.a(i)) {
            o.a(i, bArr, i2);
            return;
        }
        if (SyncV3Handler.isSyncV3Type(i)) {
            SyncV3Handler.handleJsonResult(i, bArr, i2);
            return;
        }
        if (m.a(i)) {
            m.a(i, bArr, i2);
            return;
        }
        if (g.a(i)) {
            g.a(i, bArr, i2);
            return;
        }
        if (com.ido.ble.watch.custom.c.a(i)) {
            com.ido.ble.watch.custom.c.a(i, bArr, i2);
            return;
        }
        if (x.a(i)) {
            x.a(i, bArr, i2);
            return;
        }
        if (f.a(i)) {
            f.a(i, bArr, i2);
            return;
        }
        if (w.a(i)) {
            w.a(i, bArr, i2);
            return;
        }
        if (i.a(i)) {
            i.a(i, bArr, i2);
            return;
        }
        if (n.a(i)) {
            n.a(i, bArr, i2);
            return;
        }
        h.a(bArr, i, i2);
        LogTool.b("SoLibCallBackDispatcher", "[handleCallBackJsonData] need handle type = " + i);
    }
}