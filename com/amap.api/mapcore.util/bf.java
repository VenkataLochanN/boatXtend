package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Bundle;
import com.amap.api.mapcore.util.bu;
import com.amap.api.mapcore.util.ca;
import com.amap.api.maps.AMap;
import java.io.IOException;

/* JADX INFO: compiled from: OfflineMapDownloadTask.java */
/* JADX INFO: loaded from: classes.dex */
public class bf extends js implements bu.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private bu f260a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private bw f261b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private bz f262c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f263d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Bundle f264f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f265g;

    public bf(bz bzVar, Context context) {
        this.f264f = new Bundle();
        this.f265g = false;
        this.f262c = bzVar;
        this.f263d = context;
    }

    public bf(bz bzVar, Context context, AMap aMap) {
        this(bzVar, context);
    }

    @Override // com.amap.api.mapcore.util.js
    public void runTask() {
        if (this.f262c.y()) {
            this.f262c.a(ca.a.file_io_exception);
            return;
        }
        try {
            e();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a() {
        this.f265g = true;
        bu buVar = this.f260a;
        if (buVar != null) {
            buVar.b();
        } else {
            cancelTask();
        }
        bw bwVar = this.f261b;
        if (bwVar != null) {
            bwVar.a();
        }
    }

    private String d() {
        return er.c(this.f263d);
    }

    private void e() throws IOException {
        this.f260a = new bu(new bv(this.f262c.getUrl(), d(), this.f262c.z(), 1, this.f262c.A()), this.f262c.getUrl(), this.f263d, this.f262c);
        this.f260a.a(this);
        bz bzVar = this.f262c;
        this.f261b = new bw(bzVar, bzVar);
        if (this.f265g) {
            return;
        }
        this.f260a.a();
    }

    public void b() {
        Bundle bundle = this.f264f;
        if (bundle != null) {
            bundle.clear();
            this.f264f = null;
        }
    }

    @Override // com.amap.api.mapcore.util.bu.a
    public void c() {
        bw bwVar = this.f261b;
        if (bwVar != null) {
            bwVar.b();
        }
    }
}