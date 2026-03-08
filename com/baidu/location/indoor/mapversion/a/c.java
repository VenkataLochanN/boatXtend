package com.baidu.location.indoor.mapversion.a;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f2631a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2632b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f2633c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f2634d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f2635e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f2636f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f2637g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private double f2638h;
    private double i;

    public c() {
    }

    public c(JSONObject jSONObject) {
        this.f2631a = jSONObject.optString("bldg");
        this.f2632b = jSONObject.optString("guid");
        this.f2633c = jSONObject.optString("building_bid");
        this.f2634d = jSONObject.optString("poi_guid");
        this.f2635e = jSONObject.optString("poi_bid");
        this.f2636f = jSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
        this.f2637g = jSONObject.optString("floor");
        this.f2638h = jSONObject.optDouble("x");
        this.i = jSONObject.optDouble("y");
    }

    public static String a(String str) {
        return str.toLowerCase().replaceAll("[^a-zA-Z0-9]+", "");
    }

    public String a() {
        return this.f2631a;
    }

    public String b() {
        return this.f2633c;
    }

    public String c() {
        return this.f2636f;
    }

    public String d() {
        return this.f2637g;
    }

    public double e() {
        return this.f2638h;
    }

    public double f() {
        return this.i;
    }
}