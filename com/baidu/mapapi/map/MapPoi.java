package com.baidu.mapapi.map;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class MapPoi {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f2865d = MapPoi.class.getSimpleName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f2866a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    LatLng f2867b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    String f2868c;

    void a(JSONObject jSONObject) {
        this.f2866a = jSONObject.optString("tx");
        String str = this.f2866a;
        if (str != null && !str.equals("")) {
            this.f2866a = this.f2866a.replaceAll("\\\\", "").replaceAll("/?[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "").replaceAll("[(/>)<]", "");
        }
        this.f2867b = CoordUtil.decodeNodeLocation(jSONObject.optString("geo"));
        this.f2868c = jSONObject.optString("ud");
    }

    public String getName() {
        return this.f2866a;
    }

    public LatLng getPosition() {
        return this.f2867b;
    }

    public String getUid() {
        return this.f2868c;
    }
}