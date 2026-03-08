package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: NormalStratege.java */
/* JADX INFO: loaded from: classes.dex */
public class fa implements ez {
    @Override // com.amap.api.mapcore.util.ez
    public void a(int i, String str, String str2) {
        if (i == 0) {
            hf.a(er.e()).a(he.a(str, str2));
        } else {
            hf.a(er.e()).a(he.b(str, str2));
        }
    }

    @Override // com.amap.api.mapcore.util.ez
    public void a() {
        try {
            hf.a(er.e()).a();
        } catch (Throwable unused) {
        }
    }
}