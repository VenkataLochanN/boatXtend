package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.mapcore.util.gj;
import com.amap.api.maps.AMapException;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: compiled from: AbstractProtocalHandler.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class by<T, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected T f314a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected int f315b = 3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected Context f316c;

    protected abstract String a();

    protected abstract JSONObject a(gj.b bVar);

    protected abstract V b(JSONObject jSONObject) throws AMapException;

    protected abstract Map<String, String> b();

    public by(Context context, T t) {
        a(context, t);
    }

    private void a(Context context, T t) {
        this.f316c = context;
        this.f314a = t;
    }

    public V c() throws AMapException {
        if (this.f314a != null) {
            return d();
        }
        return null;
    }

    protected V d() throws AMapException {
        int i;
        String str;
        AMapException aMapException;
        int i2 = 0;
        V vB = null;
        gj.b bVarA = null;
        while (i2 < this.f315b) {
            try {
                bVarA = gj.a(this.f316c, er.e(), a(), b());
                vB = b(a(bVarA));
                i2 = this.f315b;
            } finally {
                if (i2 < i) {
                    continue;
                }
            }
        }
        return vB;
    }
}