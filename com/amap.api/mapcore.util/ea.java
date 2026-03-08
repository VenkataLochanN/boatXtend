package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.maps.model.LatLng;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import java.lang.ref.WeakReference;
import java.util.Hashtable;
import java.util.Iterator;

/* JADX INFO: compiled from: InfoCollectUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class ea {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f708a = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static volatile ea f709d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Hashtable<String, String> f710b = new Hashtable<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private WeakReference<Context> f711c = null;

    private ea() {
    }

    public static ea a() {
        if (f709d == null) {
            synchronized (ea.class) {
                if (f709d == null) {
                    f709d = new ea();
                }
            }
        }
        return f709d;
    }

    public static void b() {
        if (f709d != null) {
            if (f709d.f710b != null && f709d.f710b.size() > 0) {
                synchronized (f709d.f710b) {
                    f709d.d();
                    if (f709d.f711c != null) {
                        f709d.f711c.clear();
                    }
                }
            }
            f709d = null;
        }
        a(false);
    }

    public static void a(boolean z) {
        f708a = z;
    }

    public static boolean c() {
        return f708a;
    }

    public static void a(int i) {
        if (f708a) {
            a(i < 1000);
        }
    }

    public void a(Context context) {
        if (context != null) {
            this.f711c = new WeakReference<>(context);
        }
    }

    public void a(LatLng latLng, String str, String str2) {
        if (!f708a) {
            this.f710b.clear();
            return;
        }
        if (latLng == null || TextUtils.isEmpty(str)) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append("\"lon\":");
        stringBuffer.append(latLng.longitude);
        stringBuffer.append(AppInfo.DELIM);
        stringBuffer.append("\"lat\":");
        stringBuffer.append(latLng.latitude);
        stringBuffer.append(AppInfo.DELIM);
        stringBuffer.append("\"title\":");
        stringBuffer.append("\"");
        stringBuffer.append(str);
        stringBuffer.append("\"");
        stringBuffer.append(AppInfo.DELIM);
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        stringBuffer.append("\"snippet\":");
        stringBuffer.append("\"");
        stringBuffer.append(str2);
        stringBuffer.append("\"");
        stringBuffer.append("}");
        a(stringBuffer.toString());
    }

    private void a(String str) {
        Hashtable<String, String> hashtable;
        if (str == null || (hashtable = this.f710b) == null) {
            return;
        }
        synchronized (hashtable) {
            String strB = gq.b(str);
            if (this.f710b != null && !this.f710b.contains(strB)) {
                this.f710b.put(strB, str);
            }
            if (e()) {
                d();
            }
        }
    }

    private void d() {
        WeakReference<Context> weakReference;
        if (!f708a) {
            this.f710b.clear();
            return;
        }
        if (this.f710b != null) {
            StringBuffer stringBuffer = new StringBuffer();
            int i = 0;
            int size = this.f710b.size();
            if (size > 0) {
                stringBuffer.append("[");
                Iterator<String> it = this.f710b.values().iterator();
                while (it.hasNext()) {
                    i++;
                    stringBuffer.append(it.next());
                    if (i < size) {
                        stringBuffer.append(AppInfo.DELIM);
                    }
                }
                stringBuffer.append("]");
                String string = stringBuffer.toString();
                if (!TextUtils.isEmpty(string) && (weakReference = this.f711c) != null && weakReference.get() != null) {
                    ix.a(string, this.f711c.get());
                }
            }
            this.f710b.clear();
        }
    }

    private boolean e() {
        Hashtable<String, String> hashtable = this.f710b;
        return hashtable != null && hashtable.size() > 20;
    }
}