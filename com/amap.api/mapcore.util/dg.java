package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.mapcore.util.df;
import com.amap.api.maps.MapsInitializer;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;

/* JADX INFO: compiled from: CustomStyleTask.java */
/* JADX INFO: loaded from: classes.dex */
public class dg implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f601a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private IAMapDelegate f602b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private df f603c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private a f604d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f605e;

    /* JADX INFO: compiled from: CustomStyleTask.java */
    public interface a {
        void a(byte[] bArr, int i);
    }

    public dg(Context context, a aVar, int i, String str) {
        this.f605e = 0;
        this.f601a = context;
        this.f604d = aVar;
        this.f605e = i;
        if (this.f603c == null) {
            this.f603c = new df(this.f601a, "", i != 0);
        }
        this.f603c.a(str);
    }

    public dg(Context context, IAMapDelegate iAMapDelegate) {
        this.f605e = 0;
        this.f601a = context;
        this.f602b = iAMapDelegate;
        if (this.f603c == null) {
            this.f603c = new df(this.f601a, "");
        }
    }

    public void a(String str) {
        df dfVar = this.f603c;
        if (dfVar != null) {
            dfVar.d(str);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        df.a aVarE;
        try {
            if (MapsInitializer.getNetWorkEnable()) {
                if (this.f603c != null && (aVarE = this.f603c.e()) != null && aVarE.f599a != null) {
                    if (this.f604d != null) {
                        this.f604d.a(aVarE.f599a, this.f605e);
                    } else if (this.f602b != null) {
                        this.f602b.setCustomMapStyle(this.f602b.getMapConfig().isCustomStyleEnable(), aVarE.f599a);
                    }
                }
                hn.a(this.f601a, er.e());
                if (this.f602b != null) {
                    this.f602b.setRunLowFrame(false);
                }
            }
        } catch (Throwable th) {
            hn.c(th, "CustomStyleTask", "download customStyle");
            th.printStackTrace();
        }
    }

    public void a() {
        this.f601a = null;
        if (this.f603c != null) {
            this.f603c = null;
        }
    }

    public void b() {
        eq.a().a(this);
    }
}