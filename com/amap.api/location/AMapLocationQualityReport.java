package com.amap.api.location;

import com.amap.api.location.AMapLocationClientOption;
import com.loc.ej;

/* JADX INFO: loaded from: classes.dex */
public class AMapLocationQualityReport implements Cloneable {
    public static final int GPS_STATUS_MODE_SAVING = 3;
    public static final int GPS_STATUS_NOGPSPERMISSION = 4;
    public static final int GPS_STATUS_NOGPSPROVIDER = 1;
    public static final int GPS_STATUS_OFF = 2;
    public static final int GPS_STATUS_OK = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f76b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f77c = 2;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f78d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f79e = "UNKNOWN";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private long f80f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f81g = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    AMapLocationClientOption.AMapLocationMode f75a = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public AMapLocationQualityReport m9clone() {
        AMapLocationQualityReport aMapLocationQualityReport = new AMapLocationQualityReport();
        try {
            super.clone();
        } catch (Throwable unused) {
        }
        try {
            aMapLocationQualityReport.setGpsStatus(this.f77c);
            aMapLocationQualityReport.setGPSSatellites(this.f78d);
            aMapLocationQualityReport.setWifiAble(this.f76b);
            aMapLocationQualityReport.setNetUseTime(this.f80f);
            aMapLocationQualityReport.setNetworkType(this.f79e);
            aMapLocationQualityReport.setLocationMode(this.f75a);
            aMapLocationQualityReport.setInstallHighDangerMockApp(this.f81g);
        } catch (Throwable th) {
            ej.a(th, "AMapLocationQualityReport", "clone");
        }
        return aMapLocationQualityReport;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getAdviseMessage() {
        /*
            r4 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r1 = r4.f75a
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r2 = com.amap.api.location.AMapLocationClientOption.AMapLocationMode.Battery_Saving
            if (r1 == r2) goto L38
            int r1 = r4.f77c
            r2 = 4
            if (r1 == 0) goto L2b
            r3 = 1
            if (r1 == r3) goto L25
            r3 = 2
            if (r1 == r3) goto L22
            r3 = 3
            if (r1 == r3) goto L1f
            if (r1 == r2) goto L1c
            goto L38
        L1c:
            java.lang.String r1 = "您的设置禁用了GPS定位权限，开启GPS定位权限有助于提高定位的精确度\n"
            goto L27
        L1f:
            java.lang.String r1 = "您的设备当前设置的定位模式不包含GPS定位，选择包含GPS模式的定位模式有助于提高定位的精确度\n"
            goto L27
        L22:
            java.lang.String r1 = "您的设备关闭了GPS定位功能，开启GPS定位功能有助于提高定位的精确度\n"
            goto L27
        L25:
            java.lang.String r1 = "您的设备没有GPS模块或者GPS模块异常，无法进行GPS定位\n"
        L27:
            r0.append(r1)
            goto L38
        L2b:
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r1 = r4.f75a
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r3 = com.amap.api.location.AMapLocationClientOption.AMapLocationMode.Device_Sensors
            if (r1 != r3) goto L38
            int r1 = r4.f78d
            if (r1 >= r2) goto L38
            java.lang.String r1 = "当前GPS信号弱，位置更新可能会延迟\n"
            goto L27
        L38:
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r1 = r4.f75a
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r2 = com.amap.api.location.AMapLocationClientOption.AMapLocationMode.Device_Sensors
            if (r1 == r2) goto L64
            java.lang.String r1 = r4.f79e
            java.lang.String r2 = "DISCONNECTED"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L4e
            java.lang.String r1 = "您的设备未连接到网络，无法进行网络定位\n"
        L4a:
            r0.append(r1)
            goto L5b
        L4e:
            java.lang.String r1 = r4.f79e
            java.lang.String r2 = "2G"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L5b
            java.lang.String r1 = "您的设备网络状态不太好，网络定位可能会有延迟\n"
            goto L4a
        L5b:
            boolean r1 = r4.f76b
            if (r1 != 0) goto L64
            java.lang.String r1 = "您的设备WIFI开关已关闭，打开WIFI开关有助于提高定位的成功率\n"
            r0.append(r1)
        L64:
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.location.AMapLocationQualityReport.getAdviseMessage():java.lang.String");
    }

    public int getGPSSatellites() {
        return this.f78d;
    }

    public int getGPSStatus() {
        return this.f77c;
    }

    public long getNetUseTime() {
        return this.f80f;
    }

    public String getNetworkType() {
        return this.f79e;
    }

    public boolean isInstalledHighDangerMockApp() {
        return this.f81g;
    }

    public boolean isWifiAble() {
        return this.f76b;
    }

    public void setGPSSatellites(int i) {
        this.f78d = i;
    }

    public void setGpsStatus(int i) {
        this.f77c = i;
    }

    public void setInstallHighDangerMockApp(boolean z) {
        this.f81g = z;
    }

    public void setLocationMode(AMapLocationClientOption.AMapLocationMode aMapLocationMode) {
        this.f75a = aMapLocationMode;
    }

    public void setNetUseTime(long j) {
        this.f80f = j;
    }

    public void setNetworkType(String str) {
        this.f79e = str;
    }

    public void setWifiAble(boolean z) {
        this.f76b = z;
    }
}