package com.amap.api.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.loc.ej;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes.dex */
public class AMapLocationClientOption implements Parcelable, Cloneable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static int f62d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static int f63e = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static int f64f = 2;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static int f65g = 4;
    private float A;
    private AMapLocationPurpose B;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f66b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    String f67c;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private long f68h;
    private long i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private AMapLocationMode o;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private long x;
    private long y;
    private GeoLanguage z;
    private static AMapLocationProtocol p = AMapLocationProtocol.HTTP;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static String f61a = "";
    public static final Parcelable.Creator<AMapLocationClientOption> CREATOR = new Parcelable.Creator<AMapLocationClientOption>() { // from class: com.amap.api.location.AMapLocationClientOption.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ AMapLocationClientOption createFromParcel(Parcel parcel) {
            return new AMapLocationClientOption(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ AMapLocationClientOption[] newArray(int i) {
            return new AMapLocationClientOption[i];
        }
    };
    public static boolean OPEN_ALWAYS_SCAN_WIFI = true;
    public static long SCAN_WIFI_INTERVAL = 30000;

    /* JADX INFO: renamed from: com.amap.api.location.AMapLocationClientOption$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f69a = new int[AMapLocationPurpose.values().length];

        static {
            try {
                f69a[AMapLocationPurpose.SignIn.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f69a[AMapLocationPurpose.Transport.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f69a[AMapLocationPurpose.Sport.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public enum AMapLocationMode {
        Battery_Saving,
        Device_Sensors,
        Hight_Accuracy
    }

    public enum AMapLocationProtocol {
        HTTP(0),
        HTTPS(1);


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f72a;

        AMapLocationProtocol(int i) {
            this.f72a = i;
        }

        public final int getValue() {
            return this.f72a;
        }
    }

    public enum AMapLocationPurpose {
        SignIn,
        Transport,
        Sport
    }

    public enum GeoLanguage {
        DEFAULT,
        ZH,
        EN
    }

    public AMapLocationClientOption() {
        this.f68h = 2000L;
        this.i = ej.f5165g;
        this.j = false;
        this.k = true;
        this.l = true;
        this.m = true;
        this.n = true;
        this.o = AMapLocationMode.Hight_Accuracy;
        this.q = false;
        this.r = false;
        this.s = true;
        this.t = true;
        this.u = false;
        this.v = false;
        this.w = true;
        this.x = 30000L;
        this.y = 30000L;
        this.z = GeoLanguage.DEFAULT;
        this.A = 0.0f;
        this.B = null;
        this.f66b = false;
        this.f67c = null;
    }

    protected AMapLocationClientOption(Parcel parcel) {
        this.f68h = 2000L;
        this.i = ej.f5165g;
        this.j = false;
        this.k = true;
        this.l = true;
        this.m = true;
        this.n = true;
        this.o = AMapLocationMode.Hight_Accuracy;
        this.q = false;
        this.r = false;
        this.s = true;
        this.t = true;
        this.u = false;
        this.v = false;
        this.w = true;
        this.x = 30000L;
        this.y = 30000L;
        this.z = GeoLanguage.DEFAULT;
        this.A = 0.0f;
        this.B = null;
        this.f66b = false;
        this.f67c = null;
        this.f68h = parcel.readLong();
        this.i = parcel.readLong();
        this.j = parcel.readByte() != 0;
        this.k = parcel.readByte() != 0;
        this.l = parcel.readByte() != 0;
        this.m = parcel.readByte() != 0;
        this.n = parcel.readByte() != 0;
        int i = parcel.readInt();
        this.o = i == -1 ? AMapLocationMode.Hight_Accuracy : AMapLocationMode.values()[i];
        this.q = parcel.readByte() != 0;
        this.r = parcel.readByte() != 0;
        this.s = parcel.readByte() != 0;
        this.t = parcel.readByte() != 0;
        this.u = parcel.readByte() != 0;
        this.v = parcel.readByte() != 0;
        this.w = parcel.readByte() != 0;
        this.x = parcel.readLong();
        int i2 = parcel.readInt();
        p = i2 == -1 ? AMapLocationProtocol.HTTP : AMapLocationProtocol.values()[i2];
        int i3 = parcel.readInt();
        this.z = i3 == -1 ? GeoLanguage.DEFAULT : GeoLanguage.values()[i3];
        this.A = parcel.readFloat();
        int i4 = parcel.readInt();
        this.B = i4 != -1 ? AMapLocationPurpose.values()[i4] : null;
        OPEN_ALWAYS_SCAN_WIFI = parcel.readByte() != 0;
        this.y = parcel.readLong();
    }

    public static String getAPIKEY() {
        return f61a;
    }

    public static boolean isDownloadCoordinateConvertLibrary() {
        return false;
    }

    public static boolean isOpenAlwaysScanWifi() {
        return OPEN_ALWAYS_SCAN_WIFI;
    }

    public static void setDownloadCoordinateConvertLibrary(boolean z) {
    }

    public static void setLocationProtocol(AMapLocationProtocol aMapLocationProtocol) {
        p = aMapLocationProtocol;
    }

    public static void setOpenAlwaysScanWifi(boolean z) {
        OPEN_ALWAYS_SCAN_WIFI = z;
    }

    public static void setScanWifiInterval(long j) {
        SCAN_WIFI_INTERVAL = j;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public AMapLocationClientOption m8clone() {
        try {
            super.clone();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.f68h = this.f68h;
        aMapLocationClientOption.j = this.j;
        aMapLocationClientOption.o = this.o;
        aMapLocationClientOption.k = this.k;
        aMapLocationClientOption.q = this.q;
        aMapLocationClientOption.r = this.r;
        aMapLocationClientOption.l = this.l;
        aMapLocationClientOption.m = this.m;
        aMapLocationClientOption.i = this.i;
        aMapLocationClientOption.s = this.s;
        aMapLocationClientOption.t = this.t;
        aMapLocationClientOption.u = this.u;
        aMapLocationClientOption.v = isSensorEnable();
        aMapLocationClientOption.w = isWifiScan();
        aMapLocationClientOption.x = this.x;
        setLocationProtocol(getLocationProtocol());
        aMapLocationClientOption.z = this.z;
        setDownloadCoordinateConvertLibrary(isDownloadCoordinateConvertLibrary());
        aMapLocationClientOption.A = this.A;
        aMapLocationClientOption.B = this.B;
        setOpenAlwaysScanWifi(isOpenAlwaysScanWifi());
        setScanWifiInterval(getScanWifiInterval());
        aMapLocationClientOption.y = this.y;
        return aMapLocationClientOption;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public float getDeviceModeDistanceFilter() {
        return this.A;
    }

    public GeoLanguage getGeoLanguage() {
        return this.z;
    }

    public long getGpsFirstTimeout() {
        return this.y;
    }

    public long getHttpTimeOut() {
        return this.i;
    }

    public long getInterval() {
        return this.f68h;
    }

    public long getLastLocationLifeCycle() {
        return this.x;
    }

    public AMapLocationMode getLocationMode() {
        return this.o;
    }

    public AMapLocationProtocol getLocationProtocol() {
        return p;
    }

    public AMapLocationPurpose getLocationPurpose() {
        return this.B;
    }

    public long getScanWifiInterval() {
        return SCAN_WIFI_INTERVAL;
    }

    public boolean isGpsFirst() {
        return this.r;
    }

    public boolean isKillProcess() {
        return this.q;
    }

    public boolean isLocationCacheEnable() {
        return this.t;
    }

    public boolean isMockEnable() {
        return this.k;
    }

    public boolean isNeedAddress() {
        return this.l;
    }

    public boolean isOffset() {
        return this.s;
    }

    public boolean isOnceLocation() {
        return this.j;
    }

    public boolean isOnceLocationLatest() {
        return this.u;
    }

    public boolean isSensorEnable() {
        return this.v;
    }

    public boolean isWifiActiveScan() {
        return this.m;
    }

    public boolean isWifiScan() {
        return this.w;
    }

    public AMapLocationClientOption setDeviceModeDistanceFilter(float f2) {
        this.A = f2;
        return this;
    }

    public AMapLocationClientOption setGeoLanguage(GeoLanguage geoLanguage) {
        this.z = geoLanguage;
        return this;
    }

    public AMapLocationClientOption setGpsFirst(boolean z) {
        this.r = z;
        return this;
    }

    public AMapLocationClientOption setGpsFirstTimeout(long j) {
        if (j < BootloaderScanner.TIMEOUT) {
            j = 5000;
        }
        if (j > 30000) {
            j = 30000;
        }
        this.y = j;
        return this;
    }

    public AMapLocationClientOption setHttpTimeOut(long j) {
        this.i = j;
        return this;
    }

    public AMapLocationClientOption setInterval(long j) {
        if (j <= 800) {
            j = 800;
        }
        this.f68h = j;
        return this;
    }

    public AMapLocationClientOption setKillProcess(boolean z) {
        this.q = z;
        return this;
    }

    public AMapLocationClientOption setLastLocationLifeCycle(long j) {
        this.x = j;
        return this;
    }

    public AMapLocationClientOption setLocationCacheEnable(boolean z) {
        this.t = z;
        return this;
    }

    public AMapLocationClientOption setLocationMode(AMapLocationMode aMapLocationMode) {
        this.o = aMapLocationMode;
        return this;
    }

    public AMapLocationClientOption setLocationPurpose(AMapLocationPurpose aMapLocationPurpose) {
        String str;
        this.B = aMapLocationPurpose;
        if (aMapLocationPurpose != null) {
            int i = AnonymousClass2.f69a[aMapLocationPurpose.ordinal()];
            if (i == 1) {
                this.o = AMapLocationMode.Hight_Accuracy;
                this.j = true;
                this.u = true;
                this.r = false;
                this.k = false;
                this.w = true;
                int i2 = f62d;
                int i3 = f63e;
                if ((i2 & i3) == 0) {
                    this.f66b = true;
                    f62d = i2 | i3;
                    this.f67c = "signin";
                }
            } else if (i == 2) {
                int i4 = f62d;
                int i5 = f64f;
                if ((i4 & i5) == 0) {
                    this.f66b = true;
                    f62d = i4 | i5;
                    str = NotificationCompat.CATEGORY_TRANSPORT;
                    this.f67c = str;
                }
                this.o = AMapLocationMode.Hight_Accuracy;
                this.j = false;
                this.u = false;
                this.r = true;
                this.k = false;
                this.w = true;
            } else if (i == 3) {
                int i6 = f62d;
                int i7 = f65g;
                if ((i6 & i7) == 0) {
                    this.f66b = true;
                    f62d = i6 | i7;
                    str = "sport";
                    this.f67c = str;
                }
                this.o = AMapLocationMode.Hight_Accuracy;
                this.j = false;
                this.u = false;
                this.r = true;
                this.k = false;
                this.w = true;
            }
        }
        return this;
    }

    public AMapLocationClientOption setMockEnable(boolean z) {
        this.k = z;
        return this;
    }

    public AMapLocationClientOption setNeedAddress(boolean z) {
        this.l = z;
        return this;
    }

    public AMapLocationClientOption setOffset(boolean z) {
        this.s = z;
        return this;
    }

    public AMapLocationClientOption setOnceLocation(boolean z) {
        this.j = z;
        return this;
    }

    public AMapLocationClientOption setOnceLocationLatest(boolean z) {
        this.u = z;
        return this;
    }

    public AMapLocationClientOption setSensorEnable(boolean z) {
        this.v = z;
        return this;
    }

    public AMapLocationClientOption setWifiActiveScan(boolean z) {
        this.m = z;
        this.n = z;
        return this;
    }

    public AMapLocationClientOption setWifiScan(boolean z) {
        this.w = z;
        this.m = this.w ? this.n : false;
        return this;
    }

    public String toString() {
        return "interval:" + String.valueOf(this.f68h) + "#isOnceLocation:" + String.valueOf(this.j) + "#locationMode:" + String.valueOf(this.o) + "#locationProtocol:" + String.valueOf(p) + "#isMockEnable:" + String.valueOf(this.k) + "#isKillProcess:" + String.valueOf(this.q) + "#isGpsFirst:" + String.valueOf(this.r) + "#isNeedAddress:" + String.valueOf(this.l) + "#isWifiActiveScan:" + String.valueOf(this.m) + "#wifiScan:" + String.valueOf(this.w) + "#httpTimeOut:" + String.valueOf(this.i) + "#isLocationCacheEnable:" + String.valueOf(this.t) + "#isOnceLocationLatest:" + String.valueOf(this.u) + "#sensorEnable:" + String.valueOf(this.v) + "#geoLanguage:" + String.valueOf(this.z) + "#locationPurpose:" + String.valueOf(this.B) + "#";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f68h);
        parcel.writeLong(this.i);
        parcel.writeByte(this.j ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.k ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.l ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.m ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.n ? (byte) 1 : (byte) 0);
        AMapLocationMode aMapLocationMode = this.o;
        parcel.writeInt(aMapLocationMode == null ? -1 : aMapLocationMode.ordinal());
        parcel.writeByte(this.q ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.r ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.s ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.t ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.u ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.v ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.w ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.x);
        parcel.writeInt(p == null ? -1 : getLocationProtocol().ordinal());
        GeoLanguage geoLanguage = this.z;
        parcel.writeInt(geoLanguage == null ? -1 : geoLanguage.ordinal());
        parcel.writeFloat(this.A);
        AMapLocationPurpose aMapLocationPurpose = this.B;
        parcel.writeInt(aMapLocationPurpose != null ? aMapLocationPurpose.ordinal() : -1);
        parcel.writeInt(OPEN_ALWAYS_SCAN_WIFI ? 1 : 0);
        parcel.writeLong(this.y);
    }
}