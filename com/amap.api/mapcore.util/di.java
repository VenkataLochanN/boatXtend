package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.mapcore.util.dh;
import com.amap.api.maps.MapsInitializer;
import com.autonavi.base.amap.mapcore.FileUtil;

/* JADX INFO: compiled from: CustomStyleTextureTask.java */
/* JADX INFO: loaded from: classes.dex */
public class di implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f608a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private dh f609b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Cdo f610c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private a f611d;

    /* JADX INFO: compiled from: CustomStyleTextureTask.java */
    public interface a {
        void a(String str, Cdo cdo);
    }

    public di(Context context) {
        this.f608a = context;
        if (this.f609b == null) {
            this.f609b = new dh(this.f608a, "");
        }
    }

    public void a(String str) {
        dh dhVar = this.f609b;
        if (dhVar != null) {
            dhVar.a(str);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (MapsInitializer.getNetWorkEnable()) {
                if (this.f609b != null) {
                    dh.a aVarE = this.f609b.e();
                    String str = null;
                    if (aVarE != null && aVarE.f606a != null) {
                        str = a(this.f608a) + "/custom_texture_data";
                        a(str, aVarE.f606a);
                    }
                    if (this.f611d != null) {
                        this.f611d.a(str, this.f610c);
                    }
                }
                hn.a(this.f608a, er.e());
            }
        } catch (Throwable th) {
            hn.c(th, "CustomStyleTask", "download customStyle");
            th.printStackTrace();
        }
    }

    private void a(String str, byte[] bArr) {
        FileUtil.writeDatasToFile(str, bArr);
    }

    private String a(Context context) {
        return FileUtil.getMapBaseStorage(context);
    }

    public void a() {
        this.f608a = null;
        if (this.f609b != null) {
            this.f609b = null;
        }
    }

    public void b() {
        eq.a().a(this);
    }

    public void a(a aVar) {
        this.f611d = aVar;
    }

    public void a(Cdo cdo) {
        this.f610c = cdo;
    }
}