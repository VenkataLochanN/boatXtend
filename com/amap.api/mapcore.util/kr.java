package com.amap.api.mapcore.util;

import android.text.TextUtils;
import com.autonavi.amap.mapcore.Inner_3dMap_location;
import org.json.JSONObject;

/* JADX INFO: compiled from: MapLocationModel.java */
/* JADX INFO: loaded from: classes.dex */
public final class kr extends Inner_3dMap_location {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f1584a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f1585b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f1586c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1587d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f1588e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f1589f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private JSONObject f1590g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f1591h;
    private String i;
    private long j;
    private String k;

    public kr(String str) {
        super(str);
        this.f1585b = null;
        this.f1586c = "";
        this.f1588e = "";
        this.f1589f = "new";
        this.f1590g = null;
        this.f1591h = "";
        this.f1584a = true;
        this.i = "";
        this.j = 0L;
        this.k = null;
    }

    public final String a() {
        return this.f1585b;
    }

    public final void a(String str) {
        this.f1585b = str;
    }

    public final String b() {
        return this.f1586c;
    }

    public final void b(String str) {
        this.f1586c = str;
    }

    public final int c() {
        return this.f1587d;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(java.lang.String r3) {
        /*
            r2 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto L2d
            java.lang.String r0 = r2.getProvider()
            java.lang.String r1 = "gps"
            boolean r0 = r0.equals(r1)
            r1 = 0
            if (r0 == 0) goto L16
            r2.f1587d = r1
            return
        L16:
            java.lang.String r0 = "0"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L21
            r2.f1587d = r1
            return
        L21:
            java.lang.String r0 = "1"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L2d
            r3 = 1
        L2a:
            r2.f1587d = r3
            return
        L2d:
            r3 = -1
            goto L2a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.kr.c(java.lang.String):void");
    }

    public final String d() {
        return this.f1588e;
    }

    public final void d(String str) {
        this.f1588e = str;
    }

    public final JSONObject e() {
        return this.f1590g;
    }

    public final void e(String str) {
        this.desc = str;
    }

    @Override // com.autonavi.amap.mapcore.Inner_3dMap_location
    public final void setFloor(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = str.replace("F", "");
            try {
                Integer.parseInt(str);
            } catch (Throwable th) {
                kg.a(th, "MapLocationModel", "setFloor");
                str = null;
            }
        }
        this.floor = str;
    }

    @Override // com.autonavi.amap.mapcore.Inner_3dMap_location
    public final JSONObject toJson(int i) {
        try {
            JSONObject json = super.toJson(i);
            if (i == 1) {
                json.put("retype", this.f1588e);
                json.put("cens", this.i);
                json.put("poiid", this.buildingId);
                json.put("floor", this.floor);
                json.put("coord", this.f1587d);
                json.put("mcell", this.f1591h);
                json.put("desc", this.desc);
                json.put("address", getAddress());
                if (this.f1590g != null && kk.a(json, "offpct")) {
                    json.put("offpct", this.f1590g.getString("offpct"));
                }
            } else if (i != 2 && i != 3) {
                return json;
            }
            json.put("type", this.f1589f);
            json.put("isReversegeo", this.f1584a);
            return json;
        } catch (Throwable th) {
            kg.a(th, "MapLocationModel", "toStr");
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.Inner_3dMap_location
    public final String toStr(int i) {
        JSONObject json;
        try {
            json = super.toJson(i);
            json.put("nb", this.k);
        } catch (Throwable th) {
            kg.a(th, "MapLocationModel", "toStr part2");
            json = null;
        }
        if (json == null) {
            return null;
        }
        return json.toString();
    }
}