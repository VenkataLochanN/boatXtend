package com.amap.api.location;

import android.content.Context;
import android.text.TextUtils;
import com.loc.ej;
import com.loc.el;
import com.loc.en;
import com.loc.ep;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CoordinateConverter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f86b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static int f87c = 1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static int f88d = 2;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static int f89e = 4;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static int f90f = 8;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static int f91g = 16;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static int f92h = 32;
    private static int i = 64;
    private Context j;
    private CoordType k = null;
    private DPoint l = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    DPoint f93a = null;

    public enum CoordType {
        BAIDU,
        MAPBAR,
        MAPABC,
        SOSOMAP,
        ALIYUN,
        GOOGLE,
        GPS
    }

    public CoordinateConverter(Context context) {
        this.j = context;
    }

    public static float calculateLineDistance(DPoint dPoint, DPoint dPoint2) {
        try {
            return ep.a(dPoint, dPoint2);
        } catch (Throwable unused) {
            return 0.0f;
        }
    }

    public static boolean isAMapDataAvailable(double d2, double d3) {
        return ej.a(d2, d3);
    }

    public synchronized DPoint convert() throws Exception {
        int i2;
        int i3;
        DPoint dPointA;
        if (this.k == null) {
            throw new IllegalArgumentException("转换坐标类型不能为空");
        }
        if (this.l == null) {
            throw new IllegalArgumentException("转换坐标源不能为空");
        }
        if (this.l.getLongitude() > 180.0d || this.l.getLongitude() < -180.0d) {
            throw new IllegalArgumentException("请传入合理经度");
        }
        if (this.l.getLatitude() > 90.0d || this.l.getLatitude() < -90.0d) {
            throw new IllegalArgumentException("请传入合理纬度");
        }
        boolean z = false;
        String str = null;
        switch (this.k) {
            case BAIDU:
                this.f93a = el.a(this.l);
                if ((f86b & f87c) == 0) {
                    str = "baidu";
                    i2 = f86b;
                    i3 = f87c;
                    f86b = i2 | i3;
                    z = true;
                }
                break;
            case MAPBAR:
                this.f93a = el.b(this.j, this.l);
                if ((f86b & f88d) == 0) {
                    str = "mapbar";
                    i2 = f86b;
                    i3 = f88d;
                    f86b = i2 | i3;
                    z = true;
                }
                break;
            case MAPABC:
                if ((f86b & f89e) == 0) {
                    str = "mapabc";
                    f86b |= f89e;
                    z = true;
                }
                dPointA = this.l;
                this.f93a = dPointA;
                break;
            case SOSOMAP:
                if ((f86b & f90f) == 0) {
                    str = "sosomap";
                    f86b |= f90f;
                    z = true;
                }
                dPointA = this.l;
                this.f93a = dPointA;
                break;
            case ALIYUN:
                if ((f86b & f91g) == 0) {
                    str = "aliyun";
                    f86b |= f91g;
                    z = true;
                }
                dPointA = this.l;
                this.f93a = dPointA;
                break;
            case GOOGLE:
                if ((f86b & f92h) == 0) {
                    str = "google";
                    f86b |= f92h;
                    z = true;
                }
                dPointA = this.l;
                this.f93a = dPointA;
                break;
            case GPS:
                if ((f86b & i) == 0) {
                    str = "gps";
                    f86b |= i;
                    z = true;
                }
                dPointA = el.a(this.j, this.l);
                this.f93a = dPointA;
                break;
        }
        if (z) {
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("amap_loc_coordinate", str);
            }
            en.a(this.j, "O021", jSONObject);
        }
        return this.f93a;
    }

    public synchronized CoordinateConverter coord(DPoint dPoint) throws Exception {
        try {
            if (dPoint == null) {
                throw new IllegalArgumentException("传入经纬度对象为空");
            }
            if (dPoint.getLongitude() > 180.0d || dPoint.getLongitude() < -180.0d) {
                throw new IllegalArgumentException("请传入合理经度");
            }
            if (dPoint.getLatitude() > 90.0d || dPoint.getLatitude() < -90.0d) {
                throw new IllegalArgumentException("请传入合理纬度");
            }
            this.l = dPoint;
        } catch (Throwable th) {
            throw th;
        }
        return this;
    }

    public synchronized CoordinateConverter from(CoordType coordType) {
        this.k = coordType;
        return this;
    }
}