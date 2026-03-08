package com.baidu.location.e;

import android.os.Build;
import android.os.Handler;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.baidu.location.g.k;
import com.ido.life.util.DateUtil;
import com.realsil.sdk.dfu.DfuException;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f2393a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f2394b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static b f2395c;
    private static Class<?> k;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private TelephonyManager f2396d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.baidu.location.e.a f2397e = new com.baidu.location.e.a();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private com.baidu.location.e.a f2398f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private List<com.baidu.location.e.a> f2399g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private a f2400h = null;
    private boolean i = false;
    private boolean j = false;
    private Handler l = new Handler();

    /* JADX INFO: Access modifiers changed from: private */
    class a extends PhoneStateListener {
        public a() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onCellLocationChanged(CellLocation cellLocation) {
            if (cellLocation == null) {
                return;
            }
            b.this.l.post(new c(this));
        }

        @Override // android.telephony.PhoneStateListener
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            com.baidu.location.e.a aVar;
            int cdmaDbm;
            if (b.this.f2397e != null) {
                if (b.this.f2397e.i == 'g') {
                    aVar = b.this.f2397e;
                    cdmaDbm = signalStrength.getGsmSignalStrength();
                } else {
                    if (b.this.f2397e.i != 'c') {
                        return;
                    }
                    aVar = b.this.f2397e;
                    cdmaDbm = signalStrength.getCdmaDbm();
                }
                aVar.f2392h = cdmaDbm;
            }
        }
    }

    private b() {
    }

    private int a(int i) {
        if (i == Integer.MAX_VALUE) {
            return -1;
        }
        return i;
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
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.b.a(android.telephony.CellInfo):com.baidu.location.e.a");
    }

    private com.baidu.location.e.a a(CellLocation cellLocation) {
        return a(cellLocation, false);
    }

    private com.baidu.location.e.a a(CellLocation cellLocation, boolean z) {
        if (cellLocation == null || this.f2396d == null) {
            return null;
        }
        com.baidu.location.e.a aVar = new com.baidu.location.e.a();
        if (z) {
            aVar.f();
        }
        aVar.f2391g = System.currentTimeMillis();
        try {
            String networkOperator = this.f2396d.getNetworkOperator();
            if (networkOperator != null && networkOperator.length() > 0) {
                int iIntValue = -1;
                if (networkOperator.length() >= 3) {
                    iIntValue = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
                    aVar.f2387c = iIntValue < 0 ? this.f2397e.f2387c : iIntValue;
                }
                String strSubstring = networkOperator.substring(3);
                if (strSubstring != null) {
                    char[] charArray = strSubstring.toCharArray();
                    int i = 0;
                    while (i < charArray.length && Character.isDigit(charArray[i])) {
                        i++;
                    }
                    iIntValue = Integer.valueOf(strSubstring.substring(0, i)).intValue();
                }
                if (iIntValue < 0) {
                    iIntValue = this.f2397e.f2388d;
                }
                aVar.f2388d = iIntValue;
            }
            f2393a = this.f2396d.getSimState();
        } catch (Exception unused) {
            f2394b = 1;
        }
        if (cellLocation instanceof GsmCellLocation) {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            aVar.f2385a = gsmCellLocation.getLac();
            aVar.f2386b = gsmCellLocation.getCid();
            aVar.i = 'g';
        } else if (cellLocation instanceof CdmaCellLocation) {
            aVar.i = 'c';
            if (k == null) {
                try {
                    k = Class.forName("android.telephony.cdma.CdmaCellLocation");
                } catch (Exception unused2) {
                    k = null;
                    return aVar;
                }
            }
            Class<?> cls = k;
            if (cls != null && cls.isInstance(cellLocation)) {
                try {
                    int systemId = ((CdmaCellLocation) cellLocation).getSystemId();
                    if (systemId < 0) {
                        systemId = this.f2397e.f2388d;
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
                    f2394b = 3;
                    return aVar;
                }
            }
        }
        c(aVar);
        return aVar;
    }

    public static synchronized b a() {
        if (f2395c == null) {
            f2395c = new b();
        }
        return f2395c;
    }

    private void c(com.baidu.location.e.a aVar) {
        if (aVar.b()) {
            com.baidu.location.e.a aVar2 = this.f2397e;
            if (aVar2 == null || !aVar2.a(aVar)) {
                this.f2397e = aVar;
                if (!aVar.b()) {
                    List<com.baidu.location.e.a> list = this.f2399g;
                    if (list != null) {
                        list.clear();
                        return;
                    }
                    return;
                }
                int size = this.f2399g.size();
                com.baidu.location.e.a aVar3 = size == 0 ? null : this.f2399g.get(size - 1);
                if (aVar3 != null && aVar3.f2386b == this.f2397e.f2386b && aVar3.f2385a == this.f2397e.f2385a) {
                    return;
                }
                this.f2399g.add(this.f2397e);
                if (this.f2399g.size() > 3) {
                    this.f2399g.remove(0);
                }
                j();
                this.j = false;
            }
        }
    }

    private String d(com.baidu.location.e.a aVar) {
        com.baidu.location.e.a aVarA;
        StringBuilder sb;
        StringBuilder sb2 = new StringBuilder();
        if (Integer.valueOf(Build.VERSION.SDK_INT).intValue() >= 17) {
            try {
                List<CellInfo> allCellInfo = this.f2396d.getAllCellInfo();
                if (allCellInfo != null && allCellInfo.size() > 0) {
                    sb2.append("&nc=");
                    for (CellInfo cellInfo : allCellInfo) {
                        if (!cellInfo.isRegistered() && (aVarA = a(cellInfo)) != null && aVarA.f2385a != -1 && aVarA.f2386b != -1) {
                            if (aVar.f2385a != aVarA.f2385a) {
                                sb = new StringBuilder();
                                sb.append(aVarA.f2385a);
                                sb.append("|");
                                sb.append(aVarA.f2386b);
                                sb.append("|");
                                sb.append(aVarA.f2392h);
                                sb.append(";");
                            } else {
                                sb = new StringBuilder();
                                sb.append("|");
                                sb.append(aVarA.f2386b);
                                sb.append("|");
                                sb.append(aVarA.f2392h);
                                sb.append(";");
                            }
                            sb2.append(sb.toString());
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return sb2.toString();
    }

    private void i() {
        String strI = k.i();
        if (strI == null) {
            return;
        }
        File file = new File(strI + File.separator + "lcvif.dat");
        if (file.exists()) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                long j = 0;
                randomAccessFile.seek(0L);
                if (System.currentTimeMillis() - randomAccessFile.readLong() > DateUtil.MINUTE) {
                    randomAccessFile.close();
                    file.delete();
                    return;
                }
                randomAccessFile.readInt();
                int i = 0;
                while (i < 3) {
                    long j2 = randomAccessFile.readLong();
                    int i2 = randomAccessFile.readInt();
                    int i3 = randomAccessFile.readInt();
                    int i4 = randomAccessFile.readInt();
                    int i5 = randomAccessFile.readInt();
                    int i6 = randomAccessFile.readInt();
                    char c2 = i6 == 2 ? 'c' : i6 == 1 ? 'g' : (char) 0;
                    if (j2 != j) {
                        com.baidu.location.e.a aVar = new com.baidu.location.e.a(i4, i5, i2, i3, 0, c2);
                        aVar.f2391g = j2;
                        if (aVar.b()) {
                            this.j = true;
                            this.f2399g.add(aVar);
                        }
                    }
                    i++;
                    j = 0;
                }
                randomAccessFile.close();
            } catch (Exception unused) {
                file.delete();
            }
        }
    }

    private void j() {
        if (this.f2399g == null && this.f2398f == null) {
            return;
        }
        if (this.f2399g == null && this.f2398f != null) {
            this.f2399g = new LinkedList();
            this.f2399g.add(this.f2398f);
        }
        String strI = k.i();
        if (strI == null || this.f2399g == null) {
            return;
        }
        File file = new File(strI + File.separator + "lcvif.dat");
        int size = this.f2399g.size();
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(0L);
            randomAccessFile.writeLong(this.f2399g.get(size - 1).f2391g);
            randomAccessFile.writeInt(size);
            for (int i = 0; i < 3 - size; i++) {
                randomAccessFile.writeLong(0L);
                randomAccessFile.writeInt(-1);
                randomAccessFile.writeInt(-1);
                randomAccessFile.writeInt(-1);
                randomAccessFile.writeInt(-1);
                randomAccessFile.writeInt(2);
            }
            for (int i2 = 0; i2 < size; i2++) {
                randomAccessFile.writeLong(this.f2399g.get(i2).f2391g);
                randomAccessFile.writeInt(this.f2399g.get(i2).f2387c);
                randomAccessFile.writeInt(this.f2399g.get(i2).f2388d);
                randomAccessFile.writeInt(this.f2399g.get(i2).f2385a);
                randomAccessFile.writeInt(this.f2399g.get(i2).f2386b);
                if (this.f2399g.get(i2).i == 'g') {
                    randomAccessFile.writeInt(1);
                } else if (this.f2399g.get(i2).i == 'c') {
                    randomAccessFile.writeInt(2);
                } else {
                    randomAccessFile.writeInt(3);
                }
            }
            randomAccessFile.close();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        CellLocation cellLocation;
        com.baidu.location.e.a aVarL = l();
        if (aVarL != null) {
            c(aVarL);
        }
        if (aVarL == null || !aVarL.b()) {
            try {
                cellLocation = this.f2396d.getCellLocation();
            } catch (Throwable unused) {
                cellLocation = null;
            }
            if (cellLocation != null) {
                a(cellLocation);
            }
        }
    }

    private com.baidu.location.e.a l() {
        if (Integer.valueOf(Build.VERSION.SDK_INT).intValue() < 17) {
            return null;
        }
        try {
            f2393a = this.f2396d.getSimState();
            List<CellInfo> allCellInfo = this.f2396d.getAllCellInfo();
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

    public String a(com.baidu.location.e.a aVar) {
        String strD;
        try {
            strD = d(aVar);
            int iIntValue = Integer.valueOf(Build.VERSION.SDK_INT).intValue();
            if (strD != null && !strD.equals("")) {
                if (!strD.equals("&nc=")) {
                    return strD;
                }
            }
            if (iIntValue >= 17) {
                return strD;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            strD = "";
        }
        if (strD == null || !strD.equals("&nc=")) {
            return strD;
        }
        return null;
    }

    public String b(com.baidu.location.e.a aVar) {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("&nw=");
        stringBuffer.append(aVar.i);
        stringBuffer.append(String.format(Locale.CHINA, "&cl=%d|%d|%d|%d&cl_s=%d", Integer.valueOf(aVar.f2387c), Integer.valueOf(aVar.f2388d), Integer.valueOf(aVar.f2385a), Integer.valueOf(aVar.f2386b), Integer.valueOf(aVar.f2392h)));
        if (aVar.f2389e < Integer.MAX_VALUE && aVar.f2390f < Integer.MAX_VALUE) {
            stringBuffer.append(String.format(Locale.CHINA, "&cdmall=%.6f|%.6f", Double.valueOf(((double) aVar.f2390f) / 14400.0d), Double.valueOf(((double) aVar.f2389e) / 14400.0d)));
        }
        stringBuffer.append("&cl_t=");
        stringBuffer.append(aVar.f2391g);
        try {
            if (this.f2399g != null && this.f2399g.size() > 0) {
                int size = this.f2399g.size();
                stringBuffer.append("&clt=");
                for (int i = 0; i < size; i++) {
                    com.baidu.location.e.a aVar2 = this.f2399g.get(i);
                    if (aVar2 != null) {
                        if (aVar2.f2387c != aVar.f2387c) {
                            stringBuffer.append(aVar2.f2387c);
                        }
                        stringBuffer.append("|");
                        if (aVar2.f2388d != aVar.f2388d) {
                            stringBuffer.append(aVar2.f2388d);
                        }
                        stringBuffer.append("|");
                        if (aVar2.f2385a != aVar.f2385a) {
                            stringBuffer.append(aVar2.f2385a);
                        }
                        stringBuffer.append("|");
                        if (aVar2.f2386b != aVar.f2386b) {
                            stringBuffer.append(aVar2.f2386b);
                        }
                        stringBuffer.append("|");
                        stringBuffer.append((System.currentTimeMillis() - aVar2.f2391g) / 1000);
                        stringBuffer.append(";");
                    }
                }
            }
        } catch (Exception unused) {
        }
        if (f2393a > 100) {
            f2393a = 0;
        }
        stringBuffer.append("&cs=" + (f2393a + (f2394b << 8)));
        if (aVar.j != null) {
            stringBuffer.append(aVar.j);
        }
        return stringBuffer.toString();
    }

    public synchronized void b() {
        if (this.i) {
            return;
        }
        if (com.baidu.location.f.isServing) {
            this.f2396d = (TelephonyManager) com.baidu.location.f.getServiceContext().getSystemService("phone");
            this.f2399g = new LinkedList();
            this.f2400h = new a();
            i();
            if (this.f2396d == null || this.f2400h == null) {
                return;
            }
            try {
                this.f2396d.listen(this.f2400h, DfuException.ERROR_READ_PATCH_INFO_ERROR);
            } catch (Exception unused) {
            }
            this.i = true;
        }
    }

    public synchronized void c() {
        if (this.i) {
            if (this.f2400h != null && this.f2396d != null) {
                this.f2396d.listen(this.f2400h, 0);
            }
            this.f2400h = null;
            this.f2396d = null;
            this.f2399g.clear();
            this.f2399g = null;
            j();
            this.i = false;
        }
    }

    public boolean d() {
        return this.j;
    }

    public int e() {
        TelephonyManager telephonyManager = this.f2396d;
        if (telephonyManager == null) {
            return 0;
        }
        try {
            return telephonyManager.getNetworkType();
        } catch (Exception unused) {
            return 0;
        }
    }

    public com.baidu.location.e.a f() {
        com.baidu.location.e.a aVar = this.f2397e;
        if ((aVar == null || !aVar.a() || !this.f2397e.b()) && this.f2396d != null) {
            try {
                k();
            } catch (Exception unused) {
            }
        }
        com.baidu.location.e.a aVar2 = this.f2397e;
        if (aVar2 != null && aVar2.e()) {
            this.f2398f = null;
            this.f2398f = new com.baidu.location.e.a(this.f2397e.f2385a, this.f2397e.f2386b, this.f2397e.f2387c, this.f2397e.f2388d, this.f2397e.f2392h, this.f2397e.i);
        }
        com.baidu.location.e.a aVar3 = this.f2397e;
        if (aVar3 != null && aVar3.d() && this.f2398f != null && this.f2397e.i == 'g') {
            this.f2397e.f2388d = this.f2398f.f2388d;
            this.f2397e.f2387c = this.f2398f.f2387c;
        }
        return this.f2397e;
    }

    public String g() {
        int simState = -1;
        try {
            if (this.f2396d != null) {
                simState = this.f2396d.getSimState();
            }
        } catch (Exception unused) {
        }
        return "&sim=" + simState;
    }

    public int h() {
        String subscriberId;
        try {
            subscriberId = this.f2396d.getSubscriberId();
        } catch (Exception unused) {
            subscriberId = null;
        }
        if (subscriberId == null) {
            return 0;
        }
        if (subscriberId.startsWith("46000") || subscriberId.startsWith("46002") || subscriberId.startsWith("46007")) {
            return 1;
        }
        if (subscriberId.startsWith("46001")) {
            return 2;
        }
        return subscriberId.startsWith("46003") ? 3 : 0;
    }
}