package com.baidu.location.a;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.baidu.android.bbalbs.common.util.CommonParam;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.LocationClientOption;
import com.baidu.mapsdkplatform.comapi.location.CoordinateType;
import com.google.android.material.timepicker.TimeModel;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class c {
    private static Class<?> i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f2065a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    String f2066b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f2068d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TelephonyManager f2069e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private WifiManager f2071g;
    private String j;
    private LocationClientOption k;
    private a l;
    private String n;
    private String o;
    private boolean p;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private com.baidu.location.e.a f2070f = new com.baidu.location.e.a();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private C0016c f2072h = null;
    private String m = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    b f2067c = new b();
    private long q = 0;
    private boolean r = false;

    public interface a {
        void onReceiveLocation(BDLocation bDLocation);
    }

    class b extends com.baidu.location.g.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f2073a = null;

        b() {
            this.k = new HashMap();
        }

        @Override // com.baidu.location.g.e
        public void a() {
            this.f2489h = com.baidu.location.g.k.e();
            if (c.this.n != null && c.this.o != null) {
                this.f2073a += String.format(Locale.CHINA, "&ki=%s&sn=%s", c.this.n, c.this.o);
            }
            String strEncodeTp4 = Jni.encodeTp4(this.f2073a);
            this.f2073a = null;
            this.k.put("bloc", strEncodeTp4);
            this.k.put("trtm", String.format(Locale.CHINA, TimeModel.NUMBER_FORMAT, Long.valueOf(System.currentTimeMillis())));
        }

        public void a(String str) {
            this.f2073a = str;
            c(com.baidu.location.g.k.f2505f);
        }

        @Override // com.baidu.location.g.e
        public void a(boolean z) {
            BDLocation bDLocation;
            if (z && this.j != null) {
                try {
                    try {
                        bDLocation = new BDLocation(this.j);
                    } catch (Exception unused) {
                        bDLocation = new BDLocation();
                        bDLocation.setLocType(63);
                    }
                    if (bDLocation.getLocType() == 161) {
                        bDLocation.setCoorType(c.this.k.coorType);
                        bDLocation.setLocationID(Jni.en1(c.this.f2065a + ";" + c.this.f2066b + ";" + bDLocation.getTime()));
                        bDLocation.setRoadLocString(0.0f, 0.0f);
                        c.this.r = true;
                        c.this.l.onReceiveLocation(bDLocation);
                    }
                } catch (Exception unused2) {
                }
            }
            if (this.k != null) {
                this.k.clear();
            }
        }
    }

    /* JADX INFO: renamed from: com.baidu.location.a.c$c, reason: collision with other inner class name */
    protected class C0016c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public List<ScanResult> f2075a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private long f2077c;

        public C0016c(List<ScanResult> list) {
            this.f2075a = null;
            this.f2077c = 0L;
            this.f2075a = list;
            this.f2077c = System.currentTimeMillis();
            try {
                c();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        private String b() {
            WifiInfo connectionInfo;
            if (c.this.f2071g == null || (connectionInfo = c.this.f2071g.getConnectionInfo()) == null) {
                return null;
            }
            try {
                String bssid = connectionInfo.getBSSID();
                String strReplace = bssid != null ? bssid.replace(":", "") : null;
                if (strReplace == null || strReplace.length() == 12) {
                    return new String(strReplace);
                }
                return null;
            } catch (Exception unused) {
                return null;
            }
        }

        /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
            jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        private void c() {
            /*
                r7 = this;
                int r0 = r7.a()
                r1 = 1
                if (r0 >= r1) goto L8
                return
            L8:
                java.util.List<android.net.wifi.ScanResult> r0 = r7.f2075a
                int r0 = r0.size()
                int r0 = r0 - r1
                r2 = r1
            L10:
                if (r0 < r1) goto L5e
                if (r2 == 0) goto L5e
                r2 = 0
                r3 = r2
            L16:
                if (r2 >= r0) goto L5a
                java.util.List<android.net.wifi.ScanResult> r4 = r7.f2075a
                java.lang.Object r4 = r4.get(r2)
                if (r4 == 0) goto L57
                java.util.List<android.net.wifi.ScanResult> r4 = r7.f2075a
                int r5 = r2 + 1
                java.lang.Object r4 = r4.get(r5)
                if (r4 == 0) goto L57
                java.util.List<android.net.wifi.ScanResult> r4 = r7.f2075a
                java.lang.Object r4 = r4.get(r2)
                android.net.wifi.ScanResult r4 = (android.net.wifi.ScanResult) r4
                int r4 = r4.level
                java.util.List<android.net.wifi.ScanResult> r6 = r7.f2075a
                java.lang.Object r6 = r6.get(r5)
                android.net.wifi.ScanResult r6 = (android.net.wifi.ScanResult) r6
                int r6 = r6.level
                if (r4 >= r6) goto L57
                java.util.List<android.net.wifi.ScanResult> r3 = r7.f2075a
                java.lang.Object r3 = r3.get(r5)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                java.util.List<android.net.wifi.ScanResult> r4 = r7.f2075a
                java.lang.Object r6 = r4.get(r2)
                r4.set(r5, r6)
                java.util.List<android.net.wifi.ScanResult> r4 = r7.f2075a
                r4.set(r2, r3)
                r3 = r1
            L57:
                int r2 = r2 + 1
                goto L16
            L5a:
                int r0 = r0 + (-1)
                r2 = r3
                goto L10
            L5e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.c.C0016c.c():void");
        }

        public int a() {
            List<ScanResult> list = this.f2075a;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x004d  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x00ef  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x00f9 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:58:0x00fb  */
        /* JADX WARN: Removed duplicated region for block: B:85:0x00e9 A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.String a(int r25) {
            /*
                Method dump skipped, instruction units count: 382
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.c.C0016c.a(int):java.lang.String");
        }
    }

    public c(Context context, LocationClientOption locationClientOption, a aVar) {
        String deviceId;
        String str;
        this.f2068d = null;
        this.f2069e = null;
        this.f2071g = null;
        this.j = null;
        this.n = null;
        this.o = null;
        this.f2065a = null;
        this.f2066b = null;
        this.p = false;
        this.f2068d = context.getApplicationContext();
        try {
            com.baidu.location.g.k.ax = this.f2068d.getPackageName();
        } catch (Exception unused) {
        }
        this.p = true;
        if (this.p) {
            this.k = new LocationClientOption(locationClientOption);
            this.l = aVar;
            this.f2065a = this.f2068d.getPackageName();
            this.f2066b = null;
            try {
                this.f2069e = (TelephonyManager) this.f2068d.getSystemService("phone");
                this.f2071g = (WifiManager) this.f2068d.getApplicationContext().getSystemService("wifi");
                deviceId = this.f2069e.getDeviceId();
            } catch (Exception unused2) {
                deviceId = null;
            }
            try {
                this.f2066b = CommonParam.a(this.f2068d);
            } catch (Throwable unused3) {
                this.f2066b = null;
                this.f2069e = null;
                this.f2071g = null;
            }
            if (this.f2066b != null) {
                com.baidu.location.g.k.o = "" + this.f2066b;
                str = "&prod=" + this.k.prodName + ":" + this.f2065a + "|&cu=" + this.f2066b + "&coor=" + locationClientOption.getCoorType();
            } else {
                str = "&prod=" + this.k.prodName + ":" + this.f2065a + "|&im=" + deviceId + "&coor=" + locationClientOption.getCoorType();
            }
            this.j = str;
            StringBuffer stringBuffer = new StringBuffer(256);
            stringBuffer.append("&fw=");
            stringBuffer.append("7.72");
            stringBuffer.append("&sdk=");
            stringBuffer.append("7.72");
            stringBuffer.append("&lt=1");
            stringBuffer.append("&mb=");
            stringBuffer.append(Build.MODEL);
            stringBuffer.append("&resid=");
            stringBuffer.append("12");
            locationClientOption.getAddrType();
            if (locationClientOption.getAddrType() != null && locationClientOption.getAddrType().equals("all")) {
                this.j += "&addr=allj";
                if (locationClientOption.isNeedNewVersionRgc) {
                    stringBuffer.append("&adtp=n2");
                }
            }
            if (locationClientOption.isNeedAptag || locationClientOption.isNeedAptagd) {
                this.j += "&sema=";
                if (locationClientOption.isNeedAptag) {
                    this.j += "aptag|";
                }
                if (locationClientOption.isNeedAptagd) {
                    this.j += "aptagd|";
                }
                this.n = j.b(this.f2068d);
                this.o = j.c(this.f2068d);
            }
            stringBuffer.append("&first=1");
            stringBuffer.append("&os=A");
            stringBuffer.append(Build.VERSION.SDK);
            this.j += stringBuffer.toString();
            String strA = a();
            strA = TextUtils.isEmpty(strA) ? strA : strA.replace(":", "");
            if (!TextUtils.isEmpty(strA) && !strA.equals("020000000000")) {
                this.j += "&mac=" + strA;
            }
            b();
        }
    }

    private int a(int i2) {
        if (i2 == Integer.MAX_VALUE) {
            return -1;
        }
        return i2;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:6|(1:8)(7:10|(3:12|(5:18|49|19|(2:25|(1:28))|(0))(1:16)|17)(2:31|(1:33)(1:34))|35|(3:47|38|(1:40))|51|41|44)|9|35|(3:47|38|(0))|51|41|44) */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0172, code lost:
    
        r1.f2391g = java.lang.System.currentTimeMillis();
     */
    /* JADX WARN: Removed duplicated region for block: B:40:0x011f A[Catch: Exception -> 0x015d, TRY_LEAVE, TryCatch #0 {Exception -> 0x015d, blocks: (B:38:0x011b, B:40:0x011f), top: B:47:0x011b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.baidu.location.e.a a(android.telephony.CellInfo r10) {
        /*
            Method dump skipped, instruction units count: 377
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.c.a(android.telephony.CellInfo):com.baidu.location.e.a");
    }

    private void a(CellLocation cellLocation) {
        if (cellLocation == null || this.f2069e == null) {
            return;
        }
        com.baidu.location.e.a aVar = new com.baidu.location.e.a();
        String networkOperator = this.f2069e.getNetworkOperator();
        if (networkOperator != null && networkOperator.length() > 0) {
            try {
                if (networkOperator.length() >= 3) {
                    int iIntValue = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
                    if (iIntValue < 0) {
                        iIntValue = this.f2070f.f2387c;
                    }
                    aVar.f2387c = iIntValue;
                }
                String strSubstring = networkOperator.substring(3);
                if (strSubstring != null) {
                    char[] charArray = strSubstring.toCharArray();
                    int i2 = 0;
                    while (i2 < charArray.length && Character.isDigit(charArray[i2])) {
                        i2++;
                    }
                    int iIntValue2 = Integer.valueOf(strSubstring.substring(0, i2)).intValue();
                    if (iIntValue2 < 0) {
                        iIntValue2 = this.f2070f.f2388d;
                    }
                    aVar.f2388d = iIntValue2;
                }
            } catch (Exception unused) {
            }
        }
        if (cellLocation instanceof GsmCellLocation) {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            aVar.f2385a = gsmCellLocation.getLac();
            aVar.f2386b = gsmCellLocation.getCid();
            aVar.i = 'g';
        } else if (cellLocation instanceof CdmaCellLocation) {
            aVar.i = 'c';
            if (i == null) {
                try {
                    i = Class.forName("android.telephony.cdma.CdmaCellLocation");
                } catch (Exception unused2) {
                    i = null;
                    return;
                }
            }
            Class<?> cls = i;
            if (cls != null && cls.isInstance(cellLocation)) {
                try {
                    int systemId = ((CdmaCellLocation) cellLocation).getSystemId();
                    if (systemId < 0) {
                        systemId = -1;
                    }
                    aVar.f2388d = systemId;
                    aVar.f2386b = ((CdmaCellLocation) cellLocation).getBaseStationId();
                    aVar.f2385a = ((CdmaCellLocation) cellLocation).getNetworkId();
                    int baseStationLatitude = ((CdmaCellLocation) cellLocation).getBaseStationLatitude();
                    if (baseStationLatitude < Integer.MAX_VALUE) {
                        aVar.f2389e = baseStationLatitude;
                    }
                    int baseStationLongitude = ((CdmaCellLocation) cellLocation).getBaseStationLongitude();
                    if (baseStationLongitude < Integer.MAX_VALUE) {
                        aVar.f2390f = baseStationLongitude;
                    }
                } catch (Exception unused3) {
                }
            }
        }
        if (aVar.b()) {
            this.f2070f = aVar;
        } else {
            this.f2070f = null;
        }
    }

    private String b(int i2) {
        String strH;
        String strA;
        try {
            com.baidu.location.e.a aVarD = d();
            if (aVarD == null || !aVarD.b()) {
                a(this.f2069e.getCellLocation());
            } else {
                this.f2070f = aVarD;
            }
            strH = (this.f2070f == null || !this.f2070f.b()) ? null : this.f2070f.h();
            try {
                if (!TextUtils.isEmpty(strH) && this.f2070f != null && this.f2070f.j != null) {
                    strH = strH + this.f2070f.j;
                }
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            strH = null;
        }
        try {
            this.f2072h = null;
            this.f2072h = new C0016c(this.f2071g.getScanResults());
            strA = this.f2072h.a(i2);
        } catch (Exception unused3) {
            strA = null;
        }
        if (strH == null && strA == null) {
            this.m = null;
            return null;
        }
        if (strA != null) {
            if (strH == null) {
                strH = strA;
            } else {
                strH = strH + strA;
            }
        }
        if (strH == null) {
            return null;
        }
        this.m = strH;
        if (this.j != null) {
            this.m += this.j;
        }
        return strH + this.j;
    }

    private com.baidu.location.e.a d() {
        if (Integer.valueOf(Build.VERSION.SDK_INT).intValue() < 17) {
            return null;
        }
        try {
            List<CellInfo> allCellInfo = this.f2069e.getAllCellInfo();
            if (allCellInfo == null || allCellInfo.size() <= 0) {
                return null;
            }
            com.baidu.location.e.a aVar = null;
            for (CellInfo cellInfo : allCellInfo) {
                if (cellInfo.isRegistered()) {
                    boolean z = aVar != null;
                    com.baidu.location.e.a aVarA = a(cellInfo);
                    if (aVarA != null) {
                        if (!aVarA.b()) {
                            aVarA = null;
                        } else if (z && aVar != null) {
                            aVar.j = aVarA.i();
                            return aVar;
                        }
                        if (aVar == null) {
                            aVar = aVarA;
                        }
                    }
                }
            }
            return aVar;
        } catch (Throwable unused) {
            return null;
        }
    }

    public String a() {
        try {
            WifiInfo connectionInfo = this.f2071g.getConnectionInfo();
            if (connectionInfo != null) {
                return connectionInfo.getMacAddress();
            }
            return null;
        } catch (Error | Exception unused) {
            return null;
        }
    }

    public String b() {
        try {
            return b(15);
        } catch (Exception unused) {
            return null;
        }
    }

    public void c() {
        BDLocation bDLocationA;
        if (this.m != null && this.p) {
            BDLocation bDLocation = null;
            if (this.f2071g != null && this.k.scanSpan >= 1000 && !this.k.getAddrType().equals("all") && !this.k.isNeedAptag && !this.k.isNeedAptagd) {
                try {
                    String strG = this.f2070f != null ? this.f2070f.g() : null;
                    if (this.f2071g != null) {
                        bDLocationA = com.baidu.location.d.a.a().a(strG, this.f2071g.getScanResults(), false);
                        if (bDLocationA != null && bDLocationA.getLocType() == 66 && Math.abs(bDLocationA.getLatitude()) < 0.10000000149011612d && Math.abs(bDLocationA.getLongitude()) < 0.10000000149011612d) {
                            bDLocationA.setLocType(67);
                        }
                    } else {
                        bDLocationA = null;
                    }
                    if (bDLocationA != null) {
                        bDLocationA.getLocType();
                    }
                    if (bDLocationA != null) {
                        bDLocationA.getLocType();
                    }
                    if (!this.k.coorType.equals(CoordinateType.GCJ02) && bDLocationA != null && bDLocationA.getLocType() == 66) {
                        double longitude = bDLocationA.getLongitude();
                        double latitude = bDLocationA.getLatitude();
                        if (Math.abs(longitude) > 0.10000000149011612d && Math.abs(latitude) > 0.10000000149011612d) {
                            double[] dArrCoorEncrypt = Jni.coorEncrypt(longitude, latitude, this.k.coorType);
                            bDLocationA.setLongitude(dArrCoorEncrypt[0]);
                            bDLocationA.setLatitude(dArrCoorEncrypt[1]);
                            bDLocationA.setCoorType(this.k.coorType);
                        }
                    }
                    if (bDLocationA != null && bDLocationA.getLocType() == 66 && Math.abs(bDLocationA.getLatitude()) > 0.10000000149011612d && Math.abs(bDLocationA.getLongitude()) > 0.10000000149011612d) {
                        if (!this.r) {
                            this.l.onReceiveLocation(bDLocationA);
                        }
                        bDLocation = bDLocationA;
                    }
                } catch (Exception unused) {
                }
            }
            if (bDLocation == null) {
                this.f2067c.a(this.m);
            }
        }
    }
}