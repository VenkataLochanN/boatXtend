package com.loc;

import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import org.json.JSONObject;

/* JADX INFO: compiled from: AMapLocationServer.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ds extends AMapLocation {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected String f5045d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f5046e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    String f5047f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f5048g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f5049h;
    private int i;
    private String j;
    private String k;
    private JSONObject l;
    private String m;
    private String n;
    private String o;

    public ds(String str) {
        super(str);
        this.f5045d = "";
        this.f5048g = null;
        this.f5049h = "";
        this.j = "";
        this.k = "new";
        this.l = null;
        this.m = "";
        this.f5046e = true;
        this.f5047f = String.valueOf(AMapLocationClientOption.GeoLanguage.DEFAULT);
        this.n = "";
        this.o = null;
    }

    public final String a() {
        return this.f5048g;
    }

    public final void a(String str) {
        this.f5048g = str;
    }

    public final void a(JSONObject jSONObject) {
        this.l = jSONObject;
    }

    public final void a(boolean z) {
        this.f5046e = z;
    }

    public final String b() {
        return this.f5049h;
    }

    public final void b(String str) {
        this.f5049h = str;
    }

    public final void b(JSONObject jSONObject) {
        try {
            ej.a(this, jSONObject);
            this.k = jSONObject.optString("type", this.k);
            this.j = jSONObject.optString("retype", this.j);
            String strOptString = jSONObject.optString("cens", this.n);
            if (!TextUtils.isEmpty(strOptString)) {
                String[] strArrSplit = strOptString.split("\\*");
                int length = strArrSplit.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    String str = strArrSplit[i];
                    if (!TextUtils.isEmpty(str)) {
                        String[] strArrSplit2 = str.split(AppInfo.DELIM);
                        setLongitude(ep.e(strArrSplit2[0]));
                        setLatitude(ep.e(strArrSplit2[1]));
                        setAccuracy(ep.g(strArrSplit2[2]));
                        break;
                    }
                    i++;
                }
                this.n = strOptString;
            }
            this.f5045d = jSONObject.optString("desc", this.f5045d);
            c(jSONObject.optString("coord", String.valueOf(this.i)));
            this.m = jSONObject.optString("mcell", this.m);
            this.f5046e = jSONObject.optBoolean("isReversegeo", this.f5046e);
            this.f5047f = jSONObject.optString("geoLanguage", this.f5047f);
            if (ep.a(jSONObject, "poiid")) {
                setBuildingId(jSONObject.optString("poiid"));
            }
            if (ep.a(jSONObject, "pid")) {
                setBuildingId(jSONObject.optString("pid"));
            }
            if (ep.a(jSONObject, "floor")) {
                setFloor(jSONObject.optString("floor"));
            }
            if (ep.a(jSONObject, "flr")) {
                setFloor(jSONObject.optString("flr"));
            }
        } catch (Throwable th) {
            ej.a(th, "AmapLoc", "AmapLoc");
        }
    }

    public final int c() {
        return this.i;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(java.lang.String r2) {
        /*
            r1 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L1a
            java.lang.String r0 = "0"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L10
            r2 = 0
            goto L1b
        L10:
            java.lang.String r0 = "1"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L1a
            r2 = 1
            goto L1b
        L1a:
            r2 = -1
        L1b:
            r1.i = r2
            int r2 = r1.i
            if (r2 != 0) goto L27
            java.lang.String r2 = "WGS84"
        L23:
            super.setCoordType(r2)
            return
        L27:
            java.lang.String r2 = "GCJ02"
            goto L23
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ds.c(java.lang.String):void");
    }

    public final String d() {
        return this.j;
    }

    public final void d(String str) {
        this.j = str;
    }

    public final String e() {
        return this.k;
    }

    public final void e(String str) {
        this.k = str;
    }

    public final JSONObject f() {
        return this.l;
    }

    public final void f(String str) {
        this.f5047f = str;
    }

    public final String g() {
        return this.m;
    }

    public final void g(String str) {
        this.f5045d = str;
    }

    public final ds h() {
        String str = this.m;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] strArrSplit = str.split(AppInfo.DELIM);
        if (strArrSplit.length != 3) {
            return null;
        }
        ds dsVar = new ds("");
        dsVar.setProvider(getProvider());
        dsVar.setLongitude(ep.e(strArrSplit[0]));
        dsVar.setLatitude(ep.e(strArrSplit[1]));
        dsVar.setAccuracy(ep.f(strArrSplit[2]));
        dsVar.setCityCode(getCityCode());
        dsVar.setAdCode(getAdCode());
        dsVar.setCountry(getCountry());
        dsVar.setProvince(getProvince());
        dsVar.setCity(getCity());
        dsVar.setTime(getTime());
        dsVar.k = this.k;
        dsVar.c(String.valueOf(this.i));
        if (ep.a(dsVar)) {
            return dsVar;
        }
        return null;
    }

    public final void h(String str) {
        this.o = str;
    }

    public final boolean i() {
        return this.f5046e;
    }

    public final String j() {
        return this.f5047f;
    }

    public final String k() {
        return this.o;
    }

    @Override // com.amap.api.location.AMapLocation
    public final JSONObject toJson(int i) {
        try {
            JSONObject json = super.toJson(i);
            if (i == 1) {
                json.put("retype", this.j);
                json.put("cens", this.n);
                json.put("coord", this.i);
                json.put("mcell", this.m);
                json.put("desc", this.f5045d);
                json.put("address", getAddress());
                if (this.l != null && ep.a(json, "offpct")) {
                    json.put("offpct", this.l.getString("offpct"));
                }
            } else if (i != 2 && i != 3) {
                return json;
            }
            json.put("type", this.k);
            json.put("isReversegeo", this.f5046e);
            json.put("geoLanguage", this.f5047f);
            return json;
        } catch (Throwable th) {
            ej.a(th, "AmapLoc", "toStr");
            return null;
        }
    }

    @Override // com.amap.api.location.AMapLocation
    public final String toStr() {
        return toStr(1);
    }

    @Override // com.amap.api.location.AMapLocation
    public final String toStr(int i) {
        JSONObject json;
        try {
            json = toJson(i);
            json.put("nb", this.o);
        } catch (Throwable th) {
            ej.a(th, "AMapLocation", "toStr part2");
            json = null;
        }
        if (json == null) {
            return null;
        }
        return json.toString();
    }
}