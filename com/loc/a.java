package com.loc;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.amap.api.fence.GeoFence;
import com.amap.api.fence.GeoFenceListener;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.DPoint;
import com.amap.api.maps.model.MyLocationStyle;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

/* JADX INFO: compiled from: GeoFenceManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a {
    private static boolean A = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Context f4696b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    en f4695a = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    PendingIntent f4697c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    String f4698d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    GeoFenceListener f4699e = null;
    private Object z = new Object();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    volatile int f4700f = 1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    ArrayList<GeoFence> f4701g = new ArrayList<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    c f4702h = null;
    Object i = new Object();
    Object j = new Object();
    HandlerC0132a k = null;
    b l = null;
    volatile boolean m = false;
    volatile boolean n = false;
    volatile boolean o = false;
    com.loc.b p = null;
    com.loc.c q = null;
    AMapLocationClient r = null;
    volatile AMapLocation s = null;
    long t = 0;
    AMapLocationClientOption u = null;
    int v = 0;
    AMapLocationListener w = new AMapLocationListener() { // from class: com.loc.a.1
        /* JADX WARN: Removed duplicated region for block: B:17:0x005d A[Catch: all -> 0x009f, TryCatch #0 {all -> 0x009f, blocks: (B:2:0x0000, B:5:0x0007, B:8:0x000e, B:10:0x001b, B:12:0x0025, B:17:0x005d, B:19:0x0068, B:21:0x0073, B:22:0x0085, B:24:0x0093, B:13:0x0035), top: B:27:0x0000 }] */
        /* JADX WARN: Removed duplicated region for block: B:19:0x0068 A[Catch: all -> 0x009f, TryCatch #0 {all -> 0x009f, blocks: (B:2:0x0000, B:5:0x0007, B:8:0x000e, B:10:0x001b, B:12:0x0025, B:17:0x005d, B:19:0x0068, B:21:0x0073, B:22:0x0085, B:24:0x0093, B:13:0x0035), top: B:27:0x0000 }] */
        @Override // com.amap.api.location.AMapLocationListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void onLocationChanged(com.amap.api.location.AMapLocation r14) {
            /*
                r13 = this;
                com.loc.a r0 = com.loc.a.this     // Catch: java.lang.Throwable -> L9f
                boolean r0 = r0.y     // Catch: java.lang.Throwable -> L9f
                if (r0 == 0) goto L7
                return
            L7:
                com.loc.a r0 = com.loc.a.this     // Catch: java.lang.Throwable -> L9f
                boolean r0 = r0.o     // Catch: java.lang.Throwable -> L9f
                if (r0 != 0) goto Le
                return
            Le:
                com.loc.a r0 = com.loc.a.this     // Catch: java.lang.Throwable -> L9f
                r0.s = r14     // Catch: java.lang.Throwable -> L9f
                r0 = 0
                r2 = 0
                r3 = 8
                r4 = 1
                r5 = 0
                if (r14 == 0) goto L59
                int r6 = r14.getErrorCode()     // Catch: java.lang.Throwable -> L9f
                int r7 = r14.getErrorCode()     // Catch: java.lang.Throwable -> L9f
                if (r7 != 0) goto L35
                com.loc.a r14 = com.loc.a.this     // Catch: java.lang.Throwable -> L9f
                long r7 = com.loc.ep.b()     // Catch: java.lang.Throwable -> L9f
                r14.t = r7     // Catch: java.lang.Throwable -> L9f
                com.loc.a r14 = com.loc.a.this     // Catch: java.lang.Throwable -> L9f
                r7 = 5
                r14.a(r7, r2, r0)     // Catch: java.lang.Throwable -> L9f
                r14 = r4
                goto L5b
            L35:
                java.lang.String r7 = "定位失败"
                int r8 = r14.getErrorCode()     // Catch: java.lang.Throwable -> L9f
                java.lang.String r9 = r14.getErrorInfo()     // Catch: java.lang.Throwable -> L9f
                java.lang.String[] r10 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L9f
                java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9f
                java.lang.String r12 = "locationDetail:"
                r11.<init>(r12)     // Catch: java.lang.Throwable -> L9f
                java.lang.String r14 = r14.getLocationDetail()     // Catch: java.lang.Throwable -> L9f
                r11.append(r14)     // Catch: java.lang.Throwable -> L9f
                java.lang.String r14 = r11.toString()     // Catch: java.lang.Throwable -> L9f
                r10[r5] = r14     // Catch: java.lang.Throwable -> L9f
                com.loc.a.a(r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L9f
                goto L5a
            L59:
                r6 = r3
            L5a:
                r14 = r5
            L5b:
                if (r14 == 0) goto L68
                com.loc.a r14 = com.loc.a.this     // Catch: java.lang.Throwable -> L9f
                r14.v = r5     // Catch: java.lang.Throwable -> L9f
                com.loc.a r14 = com.loc.a.this     // Catch: java.lang.Throwable -> L9f
                r3 = 6
                r14.a(r3, r2, r0)     // Catch: java.lang.Throwable -> L9f
                return
            L68:
                android.os.Bundle r14 = new android.os.Bundle     // Catch: java.lang.Throwable -> L9f
                r14.<init>()     // Catch: java.lang.Throwable -> L9f
                com.loc.a r0 = com.loc.a.this     // Catch: java.lang.Throwable -> L9f
                boolean r0 = r0.m     // Catch: java.lang.Throwable -> L9f
                if (r0 != 0) goto L85
                com.loc.a r0 = com.loc.a.this     // Catch: java.lang.Throwable -> L9f
                r1 = 7
                r0.b(r1)     // Catch: java.lang.Throwable -> L9f
                java.lang.String r0 = "interval"
                r1 = 2000(0x7d0, double:9.88E-321)
                r14.putLong(r0, r1)     // Catch: java.lang.Throwable -> L9f
                com.loc.a r0 = com.loc.a.this     // Catch: java.lang.Throwable -> L9f
                r0.a(r3, r14, r1)     // Catch: java.lang.Throwable -> L9f
            L85:
                com.loc.a r0 = com.loc.a.this     // Catch: java.lang.Throwable -> L9f
                int r1 = r0.v     // Catch: java.lang.Throwable -> L9f
                int r1 = r1 + r4
                r0.v = r1     // Catch: java.lang.Throwable -> L9f
                com.loc.a r0 = com.loc.a.this     // Catch: java.lang.Throwable -> L9f
                int r0 = r0.v     // Catch: java.lang.Throwable -> L9f
                r1 = 3
                if (r0 < r1) goto L9f
                java.lang.String r0 = "location_errorcode"
                r14.putInt(r0, r6)     // Catch: java.lang.Throwable -> L9f
                com.loc.a r0 = com.loc.a.this     // Catch: java.lang.Throwable -> L9f
                r1 = 1002(0x3ea, float:1.404E-42)
                r0.a(r1, r14)     // Catch: java.lang.Throwable -> L9f
            L9f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.loc.a.AnonymousClass1.onLocationChanged(com.amap.api.location.AMapLocation):void");
        }
    };
    final int x = 3;
    volatile boolean y = false;

    /* JADX INFO: renamed from: com.loc.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: GeoFenceManager.java */
    class HandlerC0132a extends Handler {
        public HandlerC0132a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            String string;
            String string2;
            GeoFence geoFenceA;
            try {
                int iB = 1;
                switch (message.what) {
                    case 0:
                        a aVar = a.this;
                        Bundle data = message.getData();
                        try {
                            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                            if (data == null || data.isEmpty()) {
                                string = "";
                            } else {
                                DPoint dPoint = (DPoint) data.getParcelable("centerPoint");
                                string = data.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                                if (dPoint != null) {
                                    if (dPoint.getLatitude() > 90.0d || dPoint.getLatitude() < -90.0d || dPoint.getLongitude() > 180.0d || dPoint.getLongitude() < -180.0d) {
                                        a.a("添加围栏失败", 1, "经纬度错误，传入的纬度：" + dPoint.getLatitude() + "传入的经度:" + dPoint.getLongitude(), new String[0]);
                                    } else {
                                        GeoFence geoFenceA2 = aVar.a(data, false);
                                        iB = aVar.b(geoFenceA2);
                                        if (iB == 0) {
                                            arrayList.add(geoFenceA2);
                                        }
                                    }
                                }
                            }
                            Bundle bundle = new Bundle();
                            bundle.putInt(MyLocationStyle.ERROR_CODE, iB);
                            bundle.putParcelableArrayList("resultList", arrayList);
                            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string);
                            aVar.a(1000, bundle);
                            return;
                        } catch (Throwable th) {
                            ej.a(th, "GeoFenceManager", "doAddGeoFenceRound");
                            return;
                        }
                    case 1:
                        a aVar2 = a.this;
                        Bundle data2 = message.getData();
                        try {
                            ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>();
                            if (data2 == null || data2.isEmpty()) {
                                string2 = "";
                            } else {
                                ArrayList parcelableArrayList = data2.getParcelableArrayList("pointList");
                                string2 = data2.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                                if (parcelableArrayList != null && parcelableArrayList.size() > 2 && (iB = aVar2.b((geoFenceA = aVar2.a(data2, true)))) == 0) {
                                    arrayList2.add(geoFenceA);
                                }
                            }
                            Bundle bundle2 = new Bundle();
                            bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string2);
                            bundle2.putInt(MyLocationStyle.ERROR_CODE, iB);
                            bundle2.putParcelableArrayList("resultList", arrayList2);
                            aVar2.a(1000, bundle2);
                            return;
                        } catch (Throwable th2) {
                            ej.a(th2, "GeoFenceManager", "doAddGeoFencePolygon");
                            return;
                        }
                    case 2:
                        a.this.c(message.getData());
                        return;
                    case 3:
                        a.this.b(message.getData());
                        return;
                    case 4:
                        a.this.d(message.getData());
                        return;
                    case 5:
                        a.this.f();
                        return;
                    case 6:
                        a.this.a(a.this.s);
                        return;
                    case 7:
                        a aVar3 = a.this;
                        try {
                            if (aVar3.r != null) {
                                aVar3.e();
                                aVar3.u.setOnceLocation(true);
                                aVar3.r.setLocationOption(aVar3.u);
                                aVar3.r.startLocation();
                                return;
                            }
                            return;
                        } catch (Throwable th3) {
                            ej.a(th3, "GeoFenceManager", "doStartOnceLocation");
                            return;
                        }
                    case 8:
                        a aVar4 = a.this;
                        Bundle data3 = message.getData();
                        try {
                            if (aVar4.r != null) {
                                long j = 2000;
                                if (data3 != null && !data3.isEmpty()) {
                                    j = data3.getLong("interval", 2000L);
                                }
                                aVar4.u.setOnceLocation(false);
                                aVar4.u.setInterval(j);
                                aVar4.r.setLocationOption(aVar4.u);
                                if (aVar4.m) {
                                    return;
                                }
                                aVar4.r.stopLocation();
                                aVar4.r.startLocation();
                                aVar4.m = true;
                                return;
                            }
                            return;
                        } catch (Throwable th4) {
                            ej.a(th4, "GeoFenceManager", "doStartContinueLocation");
                            return;
                        }
                    case 9:
                        a.this.a(message.getData());
                        return;
                    case 10:
                        a.this.c();
                        return;
                    case 11:
                        a.this.f(message.getData());
                        return;
                    case 12:
                        a.this.e(message.getData());
                        return;
                    case 13:
                        a.this.h();
                        return;
                    default:
                        return;
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: compiled from: GeoFenceManager.java */
    static class b extends HandlerThread {
        public b(String str) {
            super(str);
        }

        @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                super.run();
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: compiled from: GeoFenceManager.java */
    class c extends Handler {
        public c() {
        }

        public c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            try {
                Bundle data = message.getData();
                switch (message.what) {
                    case 1000:
                        a aVar = a.this;
                        if (data != null) {
                            try {
                                if (data.isEmpty()) {
                                    return;
                                }
                                int i = data.getInt(MyLocationStyle.ERROR_CODE);
                                ArrayList parcelableArrayList = data.getParcelableArrayList("resultList");
                                if (parcelableArrayList == null) {
                                    parcelableArrayList = new ArrayList();
                                }
                                String string = data.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                                if (string == null) {
                                    string = "";
                                }
                                if (aVar.f4699e != null) {
                                    aVar.f4699e.onGeoFenceCreateFinished((ArrayList) parcelableArrayList.clone(), i, string);
                                }
                                if (i == 0) {
                                    aVar.d();
                                    return;
                                }
                                return;
                            } catch (Throwable th) {
                                ej.a(th, "GeoFenceManager", "resultAddGeoFenceFinished");
                                return;
                            }
                        }
                        return;
                    case 1001:
                        try {
                            a.this.c((GeoFence) data.getParcelable("geoFence"));
                            return;
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                            return;
                        }
                    case 1002:
                        try {
                            a.this.c(data.getInt(GeoFence.BUNDLE_KEY_LOCERRORCODE));
                            return;
                        } catch (Throwable th3) {
                            th3.printStackTrace();
                            return;
                        }
                    default:
                        return;
                }
            } catch (Throwable unused) {
            }
        }
    }

    public a(Context context) {
        this.f4696b = null;
        try {
            this.f4696b = context.getApplicationContext();
            k();
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManger", "<init>");
        }
    }

    static float a(DPoint dPoint, List<DPoint> list) {
        float fMin = Float.MAX_VALUE;
        if (dPoint != null && list != null && !list.isEmpty()) {
            Iterator<DPoint> it = list.iterator();
            while (it.hasNext()) {
                fMin = Math.min(fMin, ep.a(dPoint, it.next()));
            }
        }
        return fMin;
    }

    private int a(List<GeoFence> list) {
        try {
            if (this.f4701g == null) {
                this.f4701g = new ArrayList<>();
            }
            Iterator<GeoFence> it = list.iterator();
            while (it.hasNext()) {
                b(it.next());
            }
            return 0;
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "addGeoFenceList");
            a("添加围栏失败", 8, th.getMessage(), new String[0]);
            return 8;
        }
    }

    private static Bundle a(GeoFence geoFence, String str, String str2, int i, int i2) {
        Bundle bundle = new Bundle();
        if (str == null) {
            str = "";
        }
        bundle.putString(GeoFence.BUNDLE_KEY_FENCEID, str);
        bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str2);
        bundle.putInt("event", i);
        bundle.putInt(GeoFence.BUNDLE_KEY_LOCERRORCODE, i2);
        bundle.putParcelable(GeoFence.BUNDLE_KEY_FENCE, geoFence);
        return bundle;
    }

    static void a(String str, int i, String str2, String... strArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("===========================================\n");
        stringBuffer.append("              " + str + "                ");
        stringBuffer.append(IOUtils.LINE_SEPARATOR_UNIX);
        stringBuffer.append("-------------------------------------------\n");
        stringBuffer.append("errorCode:" + i);
        stringBuffer.append(IOUtils.LINE_SEPARATOR_UNIX);
        stringBuffer.append("错误信息:" + str2);
        stringBuffer.append(IOUtils.LINE_SEPARATOR_UNIX);
        if (strArr.length > 0) {
            for (String str3 : strArr) {
                stringBuffer.append(str3);
                stringBuffer.append(IOUtils.LINE_SEPARATOR_UNIX);
            }
        }
        stringBuffer.append("===========================================\n");
        Log.i("fenceErrLog", stringBuffer.toString());
    }

    private static boolean a(GeoFence geoFence, int i) {
        boolean z = false;
        if ((i & 1) == 1) {
            try {
                if (geoFence.getStatus() == 1) {
                    z = true;
                }
            } catch (Throwable th) {
                ej.a(th, "Utils", "remindStatus");
                return z;
            }
        }
        if ((i & 2) == 2 && geoFence.getStatus() == 2) {
            z = true;
        }
        if ((i & 4) != 4) {
            return z;
        }
        if (geoFence.getStatus() == 3) {
            return true;
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0061 A[Catch: all -> 0x0094, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0094, blocks: (B:3:0x0002, B:6:0x000a, B:8:0x0010, B:10:0x001a, B:16:0x002a, B:29:0x0061), top: B:42:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0092  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean a(com.amap.api.location.AMapLocation r10, com.amap.api.fence.GeoFence r11) {
        /*
            r0 = 0
            r1 = 1
            boolean r2 = com.loc.ep.a(r10)     // Catch: java.lang.Throwable -> L94
            if (r2 == 0) goto L92
            if (r11 == 0) goto L92
            java.util.List r2 = r11.getPointList()     // Catch: java.lang.Throwable -> L94
            if (r2 == 0) goto L92
            java.util.List r2 = r11.getPointList()     // Catch: java.lang.Throwable -> L94
            boolean r2 = r2.isEmpty()     // Catch: java.lang.Throwable -> L94
            if (r2 != 0) goto L92
            int r2 = r11.getType()     // Catch: java.lang.Throwable -> L94
            r3 = 2
            r4 = 3
            if (r2 == 0) goto L61
            if (r2 == r1) goto L2a
            if (r2 == r3) goto L61
            if (r2 == r4) goto L2a
            goto L92
        L2a:
            java.util.List r11 = r11.getPointList()     // Catch: java.lang.Throwable -> L94
            java.util.Iterator r11 = r11.iterator()     // Catch: java.lang.Throwable -> L94
            r2 = r0
        L33:
            boolean r3 = r11.hasNext()     // Catch: java.lang.Throwable -> L5e
            if (r3 == 0) goto L5c
            java.lang.Object r3 = r11.next()     // Catch: java.lang.Throwable -> L5e
            java.util.List r3 = (java.util.List) r3     // Catch: java.lang.Throwable -> L5e
            int r5 = r3.size()     // Catch: java.lang.Throwable -> L5e
            if (r5 >= r4) goto L47
            r3 = r0
            goto L58
        L47:
            com.amap.api.location.DPoint r5 = new com.amap.api.location.DPoint     // Catch: java.lang.Throwable -> L5e
            double r6 = r10.getLatitude()     // Catch: java.lang.Throwable -> L5e
            double r8 = r10.getLongitude()     // Catch: java.lang.Throwable -> L5e
            r5.<init>(r6, r8)     // Catch: java.lang.Throwable -> L5e
            boolean r3 = com.loc.ej.a(r5, r3)     // Catch: java.lang.Throwable -> L5e
        L58:
            if (r3 == 0) goto L33
            r2 = r1
            goto L33
        L5c:
            r1 = r2
            goto L9d
        L5e:
            r10 = move-exception
            r1 = r2
            goto L96
        L61:
            com.amap.api.location.DPoint r2 = r11.getCenter()     // Catch: java.lang.Throwable -> L94
            float r11 = r11.getRadius()     // Catch: java.lang.Throwable -> L94
            r5 = 4
            double[] r5 = new double[r5]     // Catch: java.lang.Throwable -> L94
            double r6 = r2.getLatitude()     // Catch: java.lang.Throwable -> L94
            r5[r0] = r6     // Catch: java.lang.Throwable -> L94
            double r6 = r2.getLongitude()     // Catch: java.lang.Throwable -> L94
            r5[r1] = r6     // Catch: java.lang.Throwable -> L94
            double r6 = r10.getLatitude()     // Catch: java.lang.Throwable -> L94
            r5[r3] = r6     // Catch: java.lang.Throwable -> L94
            double r2 = r10.getLongitude()     // Catch: java.lang.Throwable -> L94
            r5[r4] = r2     // Catch: java.lang.Throwable -> L94
            float r10 = com.loc.ep.a(r5)     // Catch: java.lang.Throwable -> L94
            int r10 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r10 > 0) goto L8e
            r10 = r1
            goto L8f
        L8e:
            r10 = r0
        L8f:
            if (r10 == 0) goto L92
            goto L9d
        L92:
            r1 = r0
            goto L9d
        L94:
            r10 = move-exception
            r1 = r0
        L96:
            java.lang.String r11 = "Utils"
            java.lang.String r0 = "isInGeoFence"
            com.loc.ej.a(r10, r11, r0)
        L9d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.a.a(com.amap.api.location.AMapLocation, com.amap.api.fence.GeoFence):boolean");
    }

    static float b(DPoint dPoint, List<DPoint> list) {
        float fMax = Float.MIN_VALUE;
        if (dPoint != null && list != null && !list.isEmpty()) {
            Iterator<DPoint> it = list.iterator();
            while (it.hasNext()) {
                fMax = Math.max(fMax, ep.a(dPoint, it.next()));
            }
        }
        return fMax;
    }

    private static DPoint b(List<DPoint> list) {
        DPoint dPoint = new DPoint();
        if (list == null) {
            return dPoint;
        }
        try {
            double latitude = 0.0d;
            double longitude = 0.0d;
            for (DPoint dPoint2 : list) {
                latitude += dPoint2.getLatitude();
                longitude += dPoint2.getLongitude();
            }
            return new DPoint(ep.b(latitude / ((double) list.size())), ep.b(longitude / ((double) list.size())));
        } catch (Throwable th) {
            ej.a(th, "GeoFenceUtil", "getPolygonCenter");
            return dPoint;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(int r27, android.os.Bundle r28) {
        /*
            Method dump skipped, instruction units count: 604
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.a.b(int, android.os.Bundle):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean b(com.amap.api.location.AMapLocation r7, com.amap.api.fence.GeoFence r8) {
        /*
            r0 = 1
            r1 = 0
            boolean r7 = a(r7, r8)     // Catch: java.lang.Throwable -> L50
            r2 = -1
            if (r7 == 0) goto L3e
            long r4 = r8.getEnterTime()     // Catch: java.lang.Throwable -> L50
            int r7 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r7 != 0) goto L23
            int r7 = r8.getStatus()     // Catch: java.lang.Throwable -> L50
            if (r7 == r0) goto L4e
            long r2 = com.loc.ep.b()     // Catch: java.lang.Throwable -> L50
            r8.setEnterTime(r2)     // Catch: java.lang.Throwable -> L50
            r8.setStatus(r0)     // Catch: java.lang.Throwable -> L50
            goto L59
        L23:
            int r7 = r8.getStatus()     // Catch: java.lang.Throwable -> L50
            r2 = 3
            if (r7 == r2) goto L4e
            long r3 = com.loc.ep.b()     // Catch: java.lang.Throwable -> L50
            long r5 = r8.getEnterTime()     // Catch: java.lang.Throwable -> L50
            long r3 = r3 - r5
            r5 = 600000(0x927c0, double:2.964394E-318)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L4e
            r8.setStatus(r2)     // Catch: java.lang.Throwable -> L50
            goto L59
        L3e:
            int r7 = r8.getStatus()     // Catch: java.lang.Throwable -> L50
            r4 = 2
            if (r7 == r4) goto L4e
            r8.setStatus(r4)     // Catch: java.lang.Throwable -> L4c
            r8.setEnterTime(r2)     // Catch: java.lang.Throwable -> L4c
            goto L59
        L4c:
            r7 = move-exception
            goto L52
        L4e:
            r0 = r1
            goto L59
        L50:
            r7 = move-exception
            r0 = r1
        L52:
            java.lang.String r8 = "Utils"
            java.lang.String r1 = "isFenceStatusChanged"
            com.loc.ej.a(r7, r8, r1)
        L59:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.a.b(com.amap.api.location.AMapLocation, com.amap.api.fence.GeoFence):boolean");
    }

    private void k() {
        if (!this.o) {
            this.o = true;
        }
        if (this.n) {
            return;
        }
        try {
            this.f4702h = Looper.myLooper() == null ? new c(this.f4696b.getMainLooper()) : new c();
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManger", "init 1");
        }
        try {
            this.l = new b("fenceActionThread");
            this.l.setPriority(5);
            this.l.start();
            this.k = new HandlerC0132a(this.l.getLooper());
        } catch (Throwable th2) {
            ej.a(th2, "GeoFenceManger", "init 2");
        }
        try {
            this.p = new com.loc.b(this.f4696b);
            this.q = new com.loc.c();
            this.u = new AMapLocationClientOption();
            this.r = new AMapLocationClient(this.f4696b);
            this.u.setLocationCacheEnable(true);
            this.u.setNeedAddress(false);
            this.r.setLocationListener(this.w);
            if (this.f4695a == null) {
                this.f4695a = new en();
            }
        } catch (Throwable th3) {
            ej.a(th3, "GeoFenceManger", "initBase");
        }
        this.n = true;
        try {
            if (this.f4698d != null && this.f4697c == null) {
                a(this.f4698d);
            }
        } catch (Throwable th4) {
            ej.a(th4, "GeoFenceManger", "init 4");
        }
        if (A) {
            return;
        }
        A = true;
        en.a(this.f4696b, "O020", (JSONObject) null);
    }

    public final PendingIntent a(String str) {
        synchronized (this.z) {
            try {
                Intent intent = new Intent(str);
                intent.setPackage(k.c(this.f4696b));
                this.f4697c = PendingIntent.getBroadcast(this.f4696b, 0, intent, 0);
                this.f4698d = str;
            } finally {
            }
            if (this.f4701g != null && !this.f4701g.isEmpty()) {
                for (GeoFence geoFence : this.f4701g) {
                    geoFence.setPendingIntent(this.f4697c);
                    geoFence.setPendingIntentAction(this.f4698d);
                }
            }
        }
        return this.f4697c;
    }

    final GeoFence a(Bundle bundle, boolean z) {
        GeoFence geoFence = new GeoFence();
        ArrayList arrayList = new ArrayList();
        DPoint dPoint = new DPoint();
        if (z) {
            geoFence.setType(1);
            arrayList = bundle.getParcelableArrayList("pointList");
            if (arrayList != null) {
                dPoint = b(arrayList);
            }
            geoFence.setMaxDis2Center(b(dPoint, arrayList));
            geoFence.setMinDis2Center(a(dPoint, arrayList));
        } else {
            geoFence.setType(0);
            dPoint = (DPoint) bundle.getParcelable("centerPoint");
            if (dPoint != null) {
                arrayList.add(dPoint);
            }
            float f2 = bundle.getFloat("fenceRadius", 1000.0f);
            float f3 = f2 > 0.0f ? f2 : 1000.0f;
            geoFence.setRadius(f3);
            geoFence.setMinDis2Center(f3);
            geoFence.setMaxDis2Center(f3);
        }
        geoFence.setActivatesAction(this.f4700f);
        geoFence.setCustomId(bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(arrayList);
        geoFence.setPointList(arrayList2);
        geoFence.setCenter(dPoint);
        geoFence.setPendingIntentAction(this.f4698d);
        geoFence.setExpiration(-1L);
        geoFence.setPendingIntent(this.f4697c);
        StringBuilder sb = new StringBuilder();
        sb.append(com.loc.c.a());
        geoFence.setFenceId(sb.toString());
        en enVar = this.f4695a;
        if (enVar != null) {
            enVar.a(this.f4696b, 2);
        }
        return geoFence;
    }

    public final void a() {
        try {
            this.o = false;
            a(10, (Bundle) null, 0L);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "removeGeoFence");
        }
    }

    public final void a(int i) {
        try {
            k();
            if (i > 7 || i <= 0) {
                i = 1;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("activatesAction", i);
            a(9, bundle, 0L);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "setActivateAction");
        }
    }

    final void a(int i, Bundle bundle) {
        try {
            synchronized (this.j) {
                if (this.f4702h != null) {
                    Message messageObtainMessage = this.f4702h.obtainMessage();
                    messageObtainMessage.what = i;
                    messageObtainMessage.setData(bundle);
                    this.f4702h.sendMessage(messageObtainMessage);
                }
            }
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "sendResultHandlerMessage");
        }
    }

    final void a(int i, Bundle bundle, long j) {
        try {
            synchronized (this.i) {
                if (this.k != null) {
                    Message messageObtainMessage = this.k.obtainMessage();
                    messageObtainMessage.what = i;
                    messageObtainMessage.setData(bundle);
                    this.k.sendMessageDelayed(messageObtainMessage, j);
                }
            }
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "sendActionHandlerMessage");
        }
    }

    final void a(Bundle bundle) {
        int i = 1;
        if (bundle != null) {
            try {
                i = bundle.getInt("activatesAction", 1);
            } catch (Throwable th) {
                ej.a(th, "GeoFenceManager", "doSetActivatesAction");
                return;
            }
        }
        if (this.f4700f != i) {
            if (this.f4701g != null && !this.f4701g.isEmpty()) {
                for (GeoFence geoFence : this.f4701g) {
                    geoFence.setStatus(0);
                    geoFence.setEnterTime(-1L);
                }
            }
            d();
        }
        this.f4700f = i;
    }

    public final void a(GeoFenceListener geoFenceListener) {
        try {
            this.f4699e = geoFenceListener;
        } catch (Throwable unused) {
        }
    }

    final void a(AMapLocation aMapLocation) {
        try {
            if (this.y || this.f4701g == null || this.f4701g.isEmpty() || aMapLocation == null || aMapLocation.getErrorCode() != 0) {
                return;
            }
            for (GeoFence geoFence : this.f4701g) {
                if (geoFence.isAble() && b(aMapLocation, geoFence) && a(geoFence, this.f4700f)) {
                    geoFence.setCurrentLocation(aMapLocation);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("geoFence", geoFence);
                    a(1001, bundle);
                }
            }
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "doCheckFence");
        }
    }

    public final void a(DPoint dPoint, float f2, String str) {
        try {
            k();
            Bundle bundle = new Bundle();
            bundle.putParcelable("centerPoint", dPoint);
            bundle.putFloat("fenceRadius", f2);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            a(0, bundle, 0L);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "addRoundGeoFence");
        }
    }

    public final void a(String str, String str2) {
        try {
            k();
            Bundle bundle = new Bundle();
            bundle.putString("keyWords", str);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str2);
            a(4, bundle, 0L);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "addDistricetGeoFence");
        }
    }

    public final void a(String str, String str2, DPoint dPoint, float f2, int i, String str3) {
        try {
            k();
            if (f2 <= 0.0f || f2 > 50000.0f) {
                f2 = 3000.0f;
            }
            if (i <= 0) {
                i = 10;
            }
            if (i > 25) {
                i = 25;
            }
            Bundle bundle = new Bundle();
            bundle.putString("keyWords", str);
            bundle.putString("poiType", str2);
            bundle.putParcelable("centerPoint", dPoint);
            bundle.putFloat("aroundRadius", f2);
            bundle.putInt("searchSize", i);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str3);
            a(3, bundle, 0L);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "addNearbyGeoFence");
        }
    }

    public final void a(String str, String str2, String str3, int i, String str4) {
        try {
            k();
            if (i <= 0) {
                i = 10;
            }
            if (i > 25) {
                i = 25;
            }
            Bundle bundle = new Bundle();
            bundle.putString("keyWords", str);
            bundle.putString("poiType", str2);
            bundle.putString("city", str3);
            bundle.putInt("searchSize", i);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str4);
            a(2, bundle, 0L);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "addKeywordGeoFence");
        }
    }

    public final void a(String str, boolean z) {
        try {
            k();
            Bundle bundle = new Bundle();
            bundle.putString("fid", str);
            bundle.putBoolean("ab", z);
            a(12, bundle, 0L);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "setGeoFenceAble");
        }
    }

    public final void a(List<DPoint> list, String str) {
        try {
            k();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("pointList", new ArrayList<>(list));
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            a(1, bundle, 0L);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "addPolygonGeoFence");
        }
    }

    public final boolean a(GeoFence geoFence) {
        try {
            if (this.f4701g != null && !this.f4701g.isEmpty()) {
                if (!this.f4701g.contains(geoFence)) {
                    return false;
                }
                if (this.f4701g.size() == 1) {
                    this.o = false;
                }
                Bundle bundle = new Bundle();
                bundle.putParcelable("fc", geoFence);
                a(11, bundle, 0L);
                return true;
            }
            this.o = false;
            a(10, (Bundle) null, 0L);
            return true;
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "removeGeoFence(GeoFence)");
            return false;
        }
    }

    final int b(GeoFence geoFence) {
        try {
            if (this.f4701g == null) {
                this.f4701g = new ArrayList<>();
            }
            if (this.f4701g.contains(geoFence)) {
                return 17;
            }
            this.f4701g.add(geoFence);
            return 0;
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "addGeoFence2List");
            a("添加围栏失败", 8, th.getMessage(), new String[0]);
            return 8;
        }
    }

    public final List<GeoFence> b() {
        try {
            if (this.f4701g == null) {
                this.f4701g = new ArrayList<>();
            }
            return (ArrayList) this.f4701g.clone();
        } catch (Throwable unused) {
            return new ArrayList();
        }
    }

    final void b(int i) {
        try {
            synchronized (this.i) {
                if (this.k != null) {
                    this.k.removeMessages(i);
                }
            }
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "removeActionHandlerMessage");
        }
    }

    final void b(Bundle bundle) {
        b(2, bundle);
    }

    final void c() {
        try {
            if (!this.n) {
                return;
            }
            if (this.f4701g != null) {
                this.f4701g.clear();
                this.f4701g = null;
            }
            if (this.o) {
                return;
            }
            try {
                synchronized (this.i) {
                    if (this.k != null) {
                        this.k.removeCallbacksAndMessages(null);
                    }
                    this.k = null;
                }
            } catch (Throwable th) {
                ej.a(th, "GeoFenceManager", "destroyActionHandler");
            }
            if (this.r != null) {
                this.r.stopLocation();
                this.r.onDestroy();
            }
            this.r = null;
            if (this.l != null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    this.l.quitSafely();
                } else {
                    this.l.quit();
                }
            }
            this.l = null;
            this.p = null;
            synchronized (this.z) {
                if (this.f4697c != null) {
                    this.f4697c.cancel();
                }
                this.f4697c = null;
            }
            try {
                synchronized (this.j) {
                    if (this.f4702h != null) {
                        this.f4702h.removeCallbacksAndMessages(null);
                    }
                    this.f4702h = null;
                }
            } catch (Throwable th2) {
                ej.a(th2, "GeoFenceManager", "destroyResultHandler");
            }
            if (this.f4695a != null) {
                this.f4695a.b(this.f4696b);
            }
        } catch (Throwable unused) {
        }
        this.m = false;
        this.n = false;
    }

    final void c(int i) {
        try {
            if (this.f4696b != null) {
                synchronized (this.z) {
                    if (this.f4697c == null) {
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtras(a((GeoFence) null, (String) null, (String) null, 4, i));
                    this.f4697c.send(this.f4696b, 0, intent);
                }
            }
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "resultRemindLocationError");
        }
    }

    final void c(Bundle bundle) {
        b(1, bundle);
    }

    final void c(GeoFence geoFence) {
        PendingIntent pendingIntent;
        Context context;
        try {
            synchronized (this.z) {
                if (this.f4696b != null) {
                    if (this.f4697c == null && geoFence.getPendingIntent() == null) {
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtras(a(geoFence, geoFence.getFenceId(), geoFence.getCustomId(), geoFence.getStatus(), 0));
                    if (this.f4698d != null) {
                        intent.setAction(this.f4698d);
                    }
                    intent.setPackage(k.c(this.f4696b));
                    if (geoFence.getPendingIntent() != null) {
                        pendingIntent = geoFence.getPendingIntent();
                        context = this.f4696b;
                    } else {
                        pendingIntent = this.f4697c;
                        context = this.f4696b;
                    }
                    pendingIntent.send(context, 0, intent);
                }
            }
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "resultTriggerGeoFence");
        }
    }

    final void d() {
        if (this.y || this.k == null) {
            return;
        }
        boolean z = false;
        if (this.s != null && ep.a(this.s) && ep.b() - this.t < DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT) {
            z = true;
        }
        if (z) {
            a(6, (Bundle) null, 0L);
            a(5, (Bundle) null, 0L);
        } else {
            b(7);
            a(7, (Bundle) null, 0L);
        }
    }

    final void d(Bundle bundle) {
        b(3, bundle);
    }

    final void e() {
        try {
            if (this.m) {
                b(8);
            }
            if (this.r != null) {
                this.r.stopLocation();
            }
            this.m = false;
        } catch (Throwable unused) {
        }
    }

    final void e(Bundle bundle) {
        if (bundle != null) {
            try {
                if (bundle.isEmpty()) {
                    return;
                }
                String string = bundle.getString("fid");
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                boolean z = true;
                boolean z2 = bundle.getBoolean("ab", true);
                if (this.f4701g != null && !this.f4701g.isEmpty()) {
                    Iterator<GeoFence> it = this.f4701g.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        GeoFence next = it.next();
                        if (next.getFenceId().equals(string)) {
                            next.setAble(z2);
                            break;
                        }
                    }
                }
                if (z2) {
                    d();
                    return;
                }
                if (this.f4701g != null && !this.f4701g.isEmpty()) {
                    Iterator<GeoFence> it2 = this.f4701g.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        } else if (it2.next().isAble()) {
                            z = false;
                            break;
                        }
                    }
                }
                if (z) {
                    h();
                }
            } catch (Throwable th) {
                ej.a(th, "GeoFenceManager", "doSetGeoFenceAble");
            }
        }
    }

    final void f() {
        float f2;
        try {
            if (!this.y && ep.a(this.s)) {
                AMapLocation aMapLocation = this.s;
                ArrayList<GeoFence> arrayList = this.f4701g;
                if (aMapLocation != null && aMapLocation.getErrorCode() == 0 && arrayList != null && !arrayList.isEmpty()) {
                    DPoint dPoint = new DPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    Iterator<GeoFence> it = arrayList.iterator();
                    float fMin = Float.MAX_VALUE;
                    while (true) {
                        if (!it.hasNext()) {
                            f2 = fMin;
                            break;
                        }
                        GeoFence next = it.next();
                        if (next.isAble()) {
                            float fA = ep.a(dPoint, next.getCenter());
                            if (fA > next.getMinDis2Center() && fA < next.getMaxDis2Center()) {
                                f2 = 0.0f;
                                break;
                            }
                            if (fA > next.getMaxDis2Center()) {
                                fMin = Math.min(fMin, fA - next.getMaxDis2Center());
                            }
                            if (fA < next.getMinDis2Center()) {
                                fMin = Math.min(fMin, next.getMinDis2Center() - fA);
                            }
                        }
                    }
                } else {
                    f2 = Float.MAX_VALUE;
                }
                if (f2 == Float.MAX_VALUE) {
                    return;
                }
                if (f2 < 1000.0f) {
                    b(7);
                    Bundle bundle = new Bundle();
                    bundle.putLong("interval", 2000L);
                    a(8, bundle, 500L);
                    return;
                }
                if (f2 < 5000.0f) {
                    e();
                    b(7);
                    a(7, (Bundle) null, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
                } else {
                    e();
                    b(7);
                    a(7, (Bundle) null, (long) (((f2 - 4000.0f) / 100.0f) * 1000.0f));
                }
            }
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "doCheckLocationPolicy");
        }
    }

    final void f(Bundle bundle) {
        try {
            if (this.f4701g != null) {
                GeoFence geoFence = (GeoFence) bundle.getParcelable("fc");
                if (this.f4701g.contains(geoFence)) {
                    this.f4701g.remove(geoFence);
                }
                if (this.f4701g.size() <= 0) {
                    c();
                } else {
                    d();
                }
            }
        } catch (Throwable unused) {
        }
    }

    public final void g() {
        try {
            k();
            this.y = true;
            a(13, (Bundle) null, 0L);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "pauseGeoFence");
        }
    }

    final void h() {
        try {
            b(7);
            b(8);
            if (this.r != null) {
                this.r.stopLocation();
            }
            this.m = false;
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "doPauseGeoFence");
        }
    }

    public final void i() {
        try {
            k();
            if (this.y) {
                this.y = false;
                d();
            }
        } catch (Throwable th) {
            ej.a(th, "GeoFenceManager", "resumeGeoFence");
        }
    }

    public final boolean j() {
        return this.y;
    }
}