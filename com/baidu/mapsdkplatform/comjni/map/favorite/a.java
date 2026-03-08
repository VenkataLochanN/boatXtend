package com.baidu.mapsdkplatform.comjni.map.favorite;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f3867a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private JNIFavorite f3868b;

    /* JADX INFO: renamed from: com.baidu.mapsdkplatform.comjni.map.favorite.a$a, reason: collision with other inner class name */
    public static class C0036a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static boolean f3869a = false;

        /* JADX INFO: Access modifiers changed from: private */
        public static void b() {
            f3869a = true;
        }
    }

    public a() {
        this.f3868b = null;
        this.f3868b = new JNIFavorite();
    }

    public int a(Bundle bundle) {
        try {
            return this.f3868b.GetAll(this.f3867a, bundle);
        } catch (Throwable unused) {
            return 0;
        }
    }

    public long a() {
        this.f3867a = this.f3868b.Create();
        return this.f3867a;
    }

    public boolean a(int i) {
        return this.f3868b.SetType(this.f3867a, i);
    }

    public boolean a(String str) {
        return this.f3868b.Remove(this.f3867a, str);
    }

    public boolean a(String str, String str2) {
        C0036a.b();
        return this.f3868b.Add(this.f3867a, str, str2);
    }

    public boolean a(String str, String str2, String str3, int i, int i2, int i3) {
        return this.f3868b.Load(this.f3867a, str, str2, str3, i, i2, i3);
    }

    public int b() {
        return this.f3868b.Release(this.f3867a);
    }

    public String b(String str) {
        try {
            return this.f3868b.GetValue(this.f3867a, str);
        } catch (Exception unused) {
            return null;
        }
    }

    public boolean b(String str, String str2) {
        C0036a.b();
        return this.f3868b.Update(this.f3867a, str, str2);
    }

    public boolean c() {
        return this.f3868b.Clear(this.f3867a);
    }

    public boolean c(String str) {
        try {
            return this.f3868b.IsExist(this.f3867a, str);
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean d() {
        return this.f3868b.SaveCache(this.f3867a);
    }
}