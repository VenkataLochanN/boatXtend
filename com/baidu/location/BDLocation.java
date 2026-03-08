package com.baidu.location;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.location.Address;
import com.baidu.location.g.k;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class BDLocation implements Parcelable {
    public static final String BDLOCATION_BD09LL_TO_GCJ02 = "bd09ll2gcj";
    public static final String BDLOCATION_BD09_TO_GCJ02 = "bd092gcj";
    public static final String BDLOCATION_GCJ02_TO_BD09 = "bd09";
    public static final String BDLOCATION_GCJ02_TO_BD09LL = "bd09ll";
    public static final String BDLOCATION_WGS84_TO_GCJ02 = "gps2gcj";
    public static final Parcelable.Creator<BDLocation> CREATOR = new a();
    public static final int GPS_ACCURACY_BAD = 3;
    public static final int GPS_ACCURACY_GOOD = 1;
    public static final int GPS_ACCURACY_MID = 2;
    public static final int GPS_ACCURACY_UNKNOWN = 0;
    public static final int GPS_RECTIFY_INDOOR = 1;
    public static final int GPS_RECTIFY_NONE = 0;
    public static final int GPS_RECTIFY_OUTDOOR = 2;
    public static final int INDOOR_LOCATION_NEARBY_SURPPORT_TRUE = 2;
    public static final int INDOOR_LOCATION_SOURCE_BLUETOOTH = 4;
    public static final int INDOOR_LOCATION_SOURCE_MAGNETIC = 2;
    public static final int INDOOR_LOCATION_SOURCE_SMALLCELLSTATION = 8;
    public static final int INDOOR_LOCATION_SOURCE_UNKNOWN = 0;
    public static final int INDOOR_LOCATION_SOURCE_WIFI = 1;
    public static final int INDOOR_LOCATION_SURPPORT_FALSE = 0;
    public static final int INDOOR_LOCATION_SURPPORT_TRUE = 1;
    public static final int INDOOR_NETWORK_STATE_HIGH = 2;
    public static final int INDOOR_NETWORK_STATE_LOW = 0;
    public static final int INDOOR_NETWORK_STATE_MIDDLE = 1;
    public static final int LOCATION_WHERE_IN_CN = 1;
    public static final int LOCATION_WHERE_OUT_CN = 0;
    public static final int LOCATION_WHERE_UNKNOW = 2;
    public static final int OPERATORS_TYPE_MOBILE = 1;
    public static final int OPERATORS_TYPE_TELECOMU = 3;
    public static final int OPERATORS_TYPE_UNICOM = 2;
    public static final int OPERATORS_TYPE_UNKONW = 0;
    public static final int TypeCacheLocation = 65;
    public static final int TypeCriteriaException = 62;
    public static final int TypeGpsLocation = 61;
    public static final int TypeNetWorkException = 63;
    public static final int TypeNetWorkLocation = 161;
    public static final int TypeNone = 0;
    public static final int TypeOffLineLocation = 66;
    public static final int TypeOffLineLocationFail = 67;
    public static final int TypeOffLineLocationNetworkFail = 68;
    public static final int TypeServerCheckKeyError = 505;
    public static final int TypeServerDecryptError = 162;
    public static final int TypeServerError = 167;
    public static final int USER_INDDOR_TRUE = 1;
    public static final int USER_INDOOR_FALSE = 0;
    public static final int USER_INDOOR_UNKNOW = -1;
    private int A;
    private String B;
    private int C;
    private String D;
    private int E;
    private int F;
    private int G;
    private int H;
    private String I;
    private String J;
    private String K;
    private List<Poi> L;
    private String M;
    private String N;
    private String O;
    private Bundle P;
    private int Q;
    private int R;
    private long S;
    private String T;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f2018a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2019b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private double f2020c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private double f2021d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2022e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private double f2023f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f2024g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f2025h;
    private boolean i;
    private float j;
    private boolean k;
    private int l;
    private float m;
    private String n;
    private boolean o;
    private String p;
    private String q;
    private String r;
    private String s;
    private boolean t;
    private Address u;
    private String v;
    private String w;
    private String x;
    private boolean y;
    private int z;

    public BDLocation() {
        this.f2018a = 0;
        this.f2019b = null;
        this.f2020c = Double.MIN_VALUE;
        this.f2021d = Double.MIN_VALUE;
        this.f2022e = false;
        this.f2023f = Double.MIN_VALUE;
        this.f2024g = false;
        this.f2025h = 0.0f;
        this.i = false;
        this.j = 0.0f;
        this.k = false;
        this.l = -1;
        this.m = -1.0f;
        this.n = null;
        this.o = false;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = false;
        this.u = new Address.Builder().build();
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = false;
        this.z = 0;
        this.A = 1;
        this.B = null;
        this.D = "";
        this.E = -1;
        this.F = 0;
        this.G = 2;
        this.H = 0;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        this.O = null;
        this.P = new Bundle();
        this.Q = 0;
        this.R = 0;
        this.S = 0L;
        this.T = null;
    }

    private BDLocation(Parcel parcel) {
        this.f2018a = 0;
        this.f2019b = null;
        this.f2020c = Double.MIN_VALUE;
        this.f2021d = Double.MIN_VALUE;
        this.f2022e = false;
        this.f2023f = Double.MIN_VALUE;
        this.f2024g = false;
        this.f2025h = 0.0f;
        this.i = false;
        this.j = 0.0f;
        this.k = false;
        this.l = -1;
        this.m = -1.0f;
        this.n = null;
        this.o = false;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = false;
        this.u = new Address.Builder().build();
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = false;
        this.z = 0;
        this.A = 1;
        this.B = null;
        this.D = "";
        this.E = -1;
        this.F = 0;
        this.G = 2;
        this.H = 0;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        this.O = null;
        this.P = new Bundle();
        this.Q = 0;
        this.R = 0;
        this.S = 0L;
        this.T = null;
        this.f2018a = parcel.readInt();
        this.f2019b = parcel.readString();
        this.f2020c = parcel.readDouble();
        this.f2021d = parcel.readDouble();
        this.f2023f = parcel.readDouble();
        this.f2025h = parcel.readFloat();
        this.j = parcel.readFloat();
        this.l = parcel.readInt();
        this.m = parcel.readFloat();
        this.v = parcel.readString();
        this.z = parcel.readInt();
        this.w = parcel.readString();
        this.x = parcel.readString();
        this.B = parcel.readString();
        String string = parcel.readString();
        String string2 = parcel.readString();
        String string3 = parcel.readString();
        String string4 = parcel.readString();
        String string5 = parcel.readString();
        String string6 = parcel.readString();
        parcel.readString();
        String string7 = parcel.readString();
        String string8 = parcel.readString();
        this.u = new Address.Builder().country(string7).countryCode(string8).province(string).city(string2).cityCode(string6).district(string3).street(string4).streetNumber(string5).adcode(parcel.readString()).build();
        boolean[] zArr = new boolean[7];
        this.C = parcel.readInt();
        this.D = parcel.readString();
        this.q = parcel.readString();
        this.r = parcel.readString();
        this.s = parcel.readString();
        this.A = parcel.readInt();
        this.M = parcel.readString();
        this.E = parcel.readInt();
        this.F = parcel.readInt();
        this.G = parcel.readInt();
        this.H = parcel.readInt();
        this.I = parcel.readString();
        this.J = parcel.readString();
        this.K = parcel.readString();
        this.Q = parcel.readInt();
        this.N = parcel.readString();
        this.R = parcel.readInt();
        this.O = parcel.readString();
        this.T = parcel.readString();
        this.S = parcel.readLong();
        try {
            parcel.readBooleanArray(zArr);
            this.f2022e = zArr[0];
            this.f2024g = zArr[1];
            this.i = zArr[2];
            this.k = zArr[3];
            this.o = zArr[4];
            this.t = zArr[5];
            this.y = zArr[6];
        } catch (Exception unused) {
        }
        ArrayList arrayList = new ArrayList();
        parcel.readList(arrayList, Poi.class.getClassLoader());
        if (arrayList.size() == 0) {
            this.L = null;
        } else {
            this.L = arrayList;
        }
        this.P = parcel.readBundle();
    }

    /* synthetic */ BDLocation(Parcel parcel, a aVar) {
        this(parcel);
    }

    public BDLocation(BDLocation bDLocation) {
        this.f2018a = 0;
        ArrayList arrayList = null;
        this.f2019b = null;
        this.f2020c = Double.MIN_VALUE;
        this.f2021d = Double.MIN_VALUE;
        this.f2022e = false;
        this.f2023f = Double.MIN_VALUE;
        this.f2024g = false;
        this.f2025h = 0.0f;
        this.i = false;
        this.j = 0.0f;
        this.k = false;
        this.l = -1;
        this.m = -1.0f;
        this.n = null;
        this.o = false;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = false;
        this.u = new Address.Builder().build();
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = false;
        this.z = 0;
        this.A = 1;
        this.B = null;
        this.D = "";
        this.E = -1;
        this.F = 0;
        this.G = 2;
        this.H = 0;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        this.O = null;
        this.P = new Bundle();
        this.Q = 0;
        this.R = 0;
        this.S = 0L;
        this.T = null;
        this.f2018a = bDLocation.f2018a;
        this.f2019b = bDLocation.f2019b;
        this.f2020c = bDLocation.f2020c;
        this.f2021d = bDLocation.f2021d;
        this.f2022e = bDLocation.f2022e;
        this.f2023f = bDLocation.f2023f;
        this.f2024g = bDLocation.f2024g;
        this.f2025h = bDLocation.f2025h;
        this.i = bDLocation.i;
        this.j = bDLocation.j;
        this.k = bDLocation.k;
        this.l = bDLocation.l;
        this.m = bDLocation.m;
        this.n = bDLocation.n;
        this.o = bDLocation.o;
        this.p = bDLocation.p;
        this.t = bDLocation.t;
        this.u = new Address.Builder().country(bDLocation.u.country).countryCode(bDLocation.u.countryCode).province(bDLocation.u.province).city(bDLocation.u.city).cityCode(bDLocation.u.cityCode).district(bDLocation.u.district).street(bDLocation.u.street).streetNumber(bDLocation.u.streetNumber).adcode(bDLocation.u.adcode).build();
        this.v = bDLocation.v;
        this.w = bDLocation.w;
        this.x = bDLocation.x;
        this.A = bDLocation.A;
        this.z = bDLocation.z;
        this.y = bDLocation.y;
        this.B = bDLocation.B;
        this.C = bDLocation.C;
        this.D = bDLocation.D;
        this.q = bDLocation.q;
        this.r = bDLocation.r;
        this.s = bDLocation.s;
        this.E = bDLocation.E;
        this.F = bDLocation.F;
        this.G = bDLocation.F;
        this.H = bDLocation.H;
        this.I = bDLocation.I;
        this.J = bDLocation.J;
        this.K = bDLocation.K;
        this.Q = bDLocation.Q;
        this.O = bDLocation.O;
        this.S = bDLocation.S;
        this.N = bDLocation.N;
        if (bDLocation.L != null) {
            arrayList = new ArrayList();
            for (int i = 0; i < bDLocation.L.size(); i++) {
                Poi poi = bDLocation.L.get(i);
                arrayList.add(new Poi(poi.getId(), poi.getName(), poi.getRank()));
            }
        }
        this.L = arrayList;
        this.M = bDLocation.M;
        this.P = bDLocation.P;
        this.R = bDLocation.R;
    }

    /* JADX WARN: Removed duplicated region for block: B:171:0x037a A[Catch: Exception -> 0x05bc, Error -> 0x05c4, TRY_LEAVE, TryCatch #8 {Error -> 0x05c4, blocks: (B:7:0x008a, B:8:0x008c, B:11:0x00bd, B:13:0x0115, B:14:0x011e, B:16:0x0124, B:17:0x0130, B:18:0x0133, B:20:0x0137, B:21:0x013e, B:24:0x0147, B:26:0x0178, B:28:0x0186, B:30:0x0192, B:31:0x0195, B:32:0x0197, B:34:0x019f, B:35:0x01b1, B:37:0x01b7, B:39:0x01cb, B:40:0x01de, B:42:0x01e5, B:44:0x01ef, B:46:0x01fb, B:47:0x01fd, B:49:0x0205, B:51:0x0211, B:52:0x0213, B:54:0x0219, B:58:0x0223, B:60:0x022b, B:62:0x0233, B:64:0x023b, B:66:0x0243, B:68:0x024b, B:70:0x0253, B:72:0x025b, B:74:0x0263, B:76:0x026b, B:78:0x0273, B:80:0x027b, B:82:0x0283, B:84:0x028b, B:86:0x0293, B:88:0x029b, B:90:0x02a7, B:92:0x02af, B:171:0x037a, B:177:0x03b4, B:179:0x03bc, B:181:0x03cc, B:182:0x03cf, B:184:0x03d7, B:186:0x03e3, B:187:0x03ee, B:189:0x03f6, B:191:0x0406, B:192:0x0409, B:194:0x0411, B:196:0x0421, B:197:0x0424, B:199:0x042c, B:201:0x043c, B:202:0x043f, B:204:0x0447, B:207:0x0454, B:209:0x0457, B:212:0x0460, B:213:0x046a, B:215:0x0472, B:217:0x0480, B:219:0x0490, B:222:0x0498, B:223:0x049b, B:225:0x04a3, B:226:0x04b4, B:228:0x04bc, B:229:0x04c4, B:231:0x04cc, B:232:0x04d4, B:234:0x04dc, B:235:0x04e5, B:237:0x04ed, B:239:0x04fd, B:243:0x0515, B:248:0x051e, B:250:0x0526, B:254:0x0534, B:256:0x053a, B:257:0x0546, B:258:0x054a, B:260:0x054e, B:262:0x0557, B:264:0x055f, B:261:0x0554, B:253:0x0531, B:247:0x051b, B:95:0x02bf, B:97:0x02cc, B:102:0x02d5, B:109:0x02e6, B:117:0x02f3, B:125:0x0304, B:133:0x0316, B:141:0x0329, B:149:0x033b, B:158:0x0352, B:169:0x0373, B:174:0x03ae, B:176:0x03b1, B:274:0x0579, B:275:0x057e), top: B:294:0x008a }] */
    /* JADX WARN: Removed duplicated region for block: B:179:0x03bc A[Catch: Exception -> 0x05bc, Error -> 0x05c4, TryCatch #8 {Error -> 0x05c4, blocks: (B:7:0x008a, B:8:0x008c, B:11:0x00bd, B:13:0x0115, B:14:0x011e, B:16:0x0124, B:17:0x0130, B:18:0x0133, B:20:0x0137, B:21:0x013e, B:24:0x0147, B:26:0x0178, B:28:0x0186, B:30:0x0192, B:31:0x0195, B:32:0x0197, B:34:0x019f, B:35:0x01b1, B:37:0x01b7, B:39:0x01cb, B:40:0x01de, B:42:0x01e5, B:44:0x01ef, B:46:0x01fb, B:47:0x01fd, B:49:0x0205, B:51:0x0211, B:52:0x0213, B:54:0x0219, B:58:0x0223, B:60:0x022b, B:62:0x0233, B:64:0x023b, B:66:0x0243, B:68:0x024b, B:70:0x0253, B:72:0x025b, B:74:0x0263, B:76:0x026b, B:78:0x0273, B:80:0x027b, B:82:0x0283, B:84:0x028b, B:86:0x0293, B:88:0x029b, B:90:0x02a7, B:92:0x02af, B:171:0x037a, B:177:0x03b4, B:179:0x03bc, B:181:0x03cc, B:182:0x03cf, B:184:0x03d7, B:186:0x03e3, B:187:0x03ee, B:189:0x03f6, B:191:0x0406, B:192:0x0409, B:194:0x0411, B:196:0x0421, B:197:0x0424, B:199:0x042c, B:201:0x043c, B:202:0x043f, B:204:0x0447, B:207:0x0454, B:209:0x0457, B:212:0x0460, B:213:0x046a, B:215:0x0472, B:217:0x0480, B:219:0x0490, B:222:0x0498, B:223:0x049b, B:225:0x04a3, B:226:0x04b4, B:228:0x04bc, B:229:0x04c4, B:231:0x04cc, B:232:0x04d4, B:234:0x04dc, B:235:0x04e5, B:237:0x04ed, B:239:0x04fd, B:243:0x0515, B:248:0x051e, B:250:0x0526, B:254:0x0534, B:256:0x053a, B:257:0x0546, B:258:0x054a, B:260:0x054e, B:262:0x0557, B:264:0x055f, B:261:0x0554, B:253:0x0531, B:247:0x051b, B:95:0x02bf, B:97:0x02cc, B:102:0x02d5, B:109:0x02e6, B:117:0x02f3, B:125:0x0304, B:133:0x0316, B:141:0x0329, B:149:0x033b, B:158:0x0352, B:169:0x0373, B:174:0x03ae, B:176:0x03b1, B:274:0x0579, B:275:0x057e), top: B:294:0x008a }] */
    /* JADX WARN: Removed duplicated region for block: B:184:0x03d7 A[Catch: Exception -> 0x05bc, Error -> 0x05c4, TryCatch #8 {Error -> 0x05c4, blocks: (B:7:0x008a, B:8:0x008c, B:11:0x00bd, B:13:0x0115, B:14:0x011e, B:16:0x0124, B:17:0x0130, B:18:0x0133, B:20:0x0137, B:21:0x013e, B:24:0x0147, B:26:0x0178, B:28:0x0186, B:30:0x0192, B:31:0x0195, B:32:0x0197, B:34:0x019f, B:35:0x01b1, B:37:0x01b7, B:39:0x01cb, B:40:0x01de, B:42:0x01e5, B:44:0x01ef, B:46:0x01fb, B:47:0x01fd, B:49:0x0205, B:51:0x0211, B:52:0x0213, B:54:0x0219, B:58:0x0223, B:60:0x022b, B:62:0x0233, B:64:0x023b, B:66:0x0243, B:68:0x024b, B:70:0x0253, B:72:0x025b, B:74:0x0263, B:76:0x026b, B:78:0x0273, B:80:0x027b, B:82:0x0283, B:84:0x028b, B:86:0x0293, B:88:0x029b, B:90:0x02a7, B:92:0x02af, B:171:0x037a, B:177:0x03b4, B:179:0x03bc, B:181:0x03cc, B:182:0x03cf, B:184:0x03d7, B:186:0x03e3, B:187:0x03ee, B:189:0x03f6, B:191:0x0406, B:192:0x0409, B:194:0x0411, B:196:0x0421, B:197:0x0424, B:199:0x042c, B:201:0x043c, B:202:0x043f, B:204:0x0447, B:207:0x0454, B:209:0x0457, B:212:0x0460, B:213:0x046a, B:215:0x0472, B:217:0x0480, B:219:0x0490, B:222:0x0498, B:223:0x049b, B:225:0x04a3, B:226:0x04b4, B:228:0x04bc, B:229:0x04c4, B:231:0x04cc, B:232:0x04d4, B:234:0x04dc, B:235:0x04e5, B:237:0x04ed, B:239:0x04fd, B:243:0x0515, B:248:0x051e, B:250:0x0526, B:254:0x0534, B:256:0x053a, B:257:0x0546, B:258:0x054a, B:260:0x054e, B:262:0x0557, B:264:0x055f, B:261:0x0554, B:253:0x0531, B:247:0x051b, B:95:0x02bf, B:97:0x02cc, B:102:0x02d5, B:109:0x02e6, B:117:0x02f3, B:125:0x0304, B:133:0x0316, B:141:0x0329, B:149:0x033b, B:158:0x0352, B:169:0x0373, B:174:0x03ae, B:176:0x03b1, B:274:0x0579, B:275:0x057e), top: B:294:0x008a }] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x03f6 A[Catch: Exception -> 0x05bc, Error -> 0x05c4, TryCatch #8 {Error -> 0x05c4, blocks: (B:7:0x008a, B:8:0x008c, B:11:0x00bd, B:13:0x0115, B:14:0x011e, B:16:0x0124, B:17:0x0130, B:18:0x0133, B:20:0x0137, B:21:0x013e, B:24:0x0147, B:26:0x0178, B:28:0x0186, B:30:0x0192, B:31:0x0195, B:32:0x0197, B:34:0x019f, B:35:0x01b1, B:37:0x01b7, B:39:0x01cb, B:40:0x01de, B:42:0x01e5, B:44:0x01ef, B:46:0x01fb, B:47:0x01fd, B:49:0x0205, B:51:0x0211, B:52:0x0213, B:54:0x0219, B:58:0x0223, B:60:0x022b, B:62:0x0233, B:64:0x023b, B:66:0x0243, B:68:0x024b, B:70:0x0253, B:72:0x025b, B:74:0x0263, B:76:0x026b, B:78:0x0273, B:80:0x027b, B:82:0x0283, B:84:0x028b, B:86:0x0293, B:88:0x029b, B:90:0x02a7, B:92:0x02af, B:171:0x037a, B:177:0x03b4, B:179:0x03bc, B:181:0x03cc, B:182:0x03cf, B:184:0x03d7, B:186:0x03e3, B:187:0x03ee, B:189:0x03f6, B:191:0x0406, B:192:0x0409, B:194:0x0411, B:196:0x0421, B:197:0x0424, B:199:0x042c, B:201:0x043c, B:202:0x043f, B:204:0x0447, B:207:0x0454, B:209:0x0457, B:212:0x0460, B:213:0x046a, B:215:0x0472, B:217:0x0480, B:219:0x0490, B:222:0x0498, B:223:0x049b, B:225:0x04a3, B:226:0x04b4, B:228:0x04bc, B:229:0x04c4, B:231:0x04cc, B:232:0x04d4, B:234:0x04dc, B:235:0x04e5, B:237:0x04ed, B:239:0x04fd, B:243:0x0515, B:248:0x051e, B:250:0x0526, B:254:0x0534, B:256:0x053a, B:257:0x0546, B:258:0x054a, B:260:0x054e, B:262:0x0557, B:264:0x055f, B:261:0x0554, B:253:0x0531, B:247:0x051b, B:95:0x02bf, B:97:0x02cc, B:102:0x02d5, B:109:0x02e6, B:117:0x02f3, B:125:0x0304, B:133:0x0316, B:141:0x0329, B:149:0x033b, B:158:0x0352, B:169:0x0373, B:174:0x03ae, B:176:0x03b1, B:274:0x0579, B:275:0x057e), top: B:294:0x008a }] */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0411 A[Catch: Exception -> 0x05bc, Error -> 0x05c4, TryCatch #8 {Error -> 0x05c4, blocks: (B:7:0x008a, B:8:0x008c, B:11:0x00bd, B:13:0x0115, B:14:0x011e, B:16:0x0124, B:17:0x0130, B:18:0x0133, B:20:0x0137, B:21:0x013e, B:24:0x0147, B:26:0x0178, B:28:0x0186, B:30:0x0192, B:31:0x0195, B:32:0x0197, B:34:0x019f, B:35:0x01b1, B:37:0x01b7, B:39:0x01cb, B:40:0x01de, B:42:0x01e5, B:44:0x01ef, B:46:0x01fb, B:47:0x01fd, B:49:0x0205, B:51:0x0211, B:52:0x0213, B:54:0x0219, B:58:0x0223, B:60:0x022b, B:62:0x0233, B:64:0x023b, B:66:0x0243, B:68:0x024b, B:70:0x0253, B:72:0x025b, B:74:0x0263, B:76:0x026b, B:78:0x0273, B:80:0x027b, B:82:0x0283, B:84:0x028b, B:86:0x0293, B:88:0x029b, B:90:0x02a7, B:92:0x02af, B:171:0x037a, B:177:0x03b4, B:179:0x03bc, B:181:0x03cc, B:182:0x03cf, B:184:0x03d7, B:186:0x03e3, B:187:0x03ee, B:189:0x03f6, B:191:0x0406, B:192:0x0409, B:194:0x0411, B:196:0x0421, B:197:0x0424, B:199:0x042c, B:201:0x043c, B:202:0x043f, B:204:0x0447, B:207:0x0454, B:209:0x0457, B:212:0x0460, B:213:0x046a, B:215:0x0472, B:217:0x0480, B:219:0x0490, B:222:0x0498, B:223:0x049b, B:225:0x04a3, B:226:0x04b4, B:228:0x04bc, B:229:0x04c4, B:231:0x04cc, B:232:0x04d4, B:234:0x04dc, B:235:0x04e5, B:237:0x04ed, B:239:0x04fd, B:243:0x0515, B:248:0x051e, B:250:0x0526, B:254:0x0534, B:256:0x053a, B:257:0x0546, B:258:0x054a, B:260:0x054e, B:262:0x0557, B:264:0x055f, B:261:0x0554, B:253:0x0531, B:247:0x051b, B:95:0x02bf, B:97:0x02cc, B:102:0x02d5, B:109:0x02e6, B:117:0x02f3, B:125:0x0304, B:133:0x0316, B:141:0x0329, B:149:0x033b, B:158:0x0352, B:169:0x0373, B:174:0x03ae, B:176:0x03b1, B:274:0x0579, B:275:0x057e), top: B:294:0x008a }] */
    /* JADX WARN: Removed duplicated region for block: B:199:0x042c A[Catch: Exception -> 0x05bc, Error -> 0x05c4, TryCatch #8 {Error -> 0x05c4, blocks: (B:7:0x008a, B:8:0x008c, B:11:0x00bd, B:13:0x0115, B:14:0x011e, B:16:0x0124, B:17:0x0130, B:18:0x0133, B:20:0x0137, B:21:0x013e, B:24:0x0147, B:26:0x0178, B:28:0x0186, B:30:0x0192, B:31:0x0195, B:32:0x0197, B:34:0x019f, B:35:0x01b1, B:37:0x01b7, B:39:0x01cb, B:40:0x01de, B:42:0x01e5, B:44:0x01ef, B:46:0x01fb, B:47:0x01fd, B:49:0x0205, B:51:0x0211, B:52:0x0213, B:54:0x0219, B:58:0x0223, B:60:0x022b, B:62:0x0233, B:64:0x023b, B:66:0x0243, B:68:0x024b, B:70:0x0253, B:72:0x025b, B:74:0x0263, B:76:0x026b, B:78:0x0273, B:80:0x027b, B:82:0x0283, B:84:0x028b, B:86:0x0293, B:88:0x029b, B:90:0x02a7, B:92:0x02af, B:171:0x037a, B:177:0x03b4, B:179:0x03bc, B:181:0x03cc, B:182:0x03cf, B:184:0x03d7, B:186:0x03e3, B:187:0x03ee, B:189:0x03f6, B:191:0x0406, B:192:0x0409, B:194:0x0411, B:196:0x0421, B:197:0x0424, B:199:0x042c, B:201:0x043c, B:202:0x043f, B:204:0x0447, B:207:0x0454, B:209:0x0457, B:212:0x0460, B:213:0x046a, B:215:0x0472, B:217:0x0480, B:219:0x0490, B:222:0x0498, B:223:0x049b, B:225:0x04a3, B:226:0x04b4, B:228:0x04bc, B:229:0x04c4, B:231:0x04cc, B:232:0x04d4, B:234:0x04dc, B:235:0x04e5, B:237:0x04ed, B:239:0x04fd, B:243:0x0515, B:248:0x051e, B:250:0x0526, B:254:0x0534, B:256:0x053a, B:257:0x0546, B:258:0x054a, B:260:0x054e, B:262:0x0557, B:264:0x055f, B:261:0x0554, B:253:0x0531, B:247:0x051b, B:95:0x02bf, B:97:0x02cc, B:102:0x02d5, B:109:0x02e6, B:117:0x02f3, B:125:0x0304, B:133:0x0316, B:141:0x0329, B:149:0x033b, B:158:0x0352, B:169:0x0373, B:174:0x03ae, B:176:0x03b1, B:274:0x0579, B:275:0x057e), top: B:294:0x008a }] */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0447 A[Catch: Exception -> 0x05bc, Error -> 0x05c4, TRY_LEAVE, TryCatch #8 {Error -> 0x05c4, blocks: (B:7:0x008a, B:8:0x008c, B:11:0x00bd, B:13:0x0115, B:14:0x011e, B:16:0x0124, B:17:0x0130, B:18:0x0133, B:20:0x0137, B:21:0x013e, B:24:0x0147, B:26:0x0178, B:28:0x0186, B:30:0x0192, B:31:0x0195, B:32:0x0197, B:34:0x019f, B:35:0x01b1, B:37:0x01b7, B:39:0x01cb, B:40:0x01de, B:42:0x01e5, B:44:0x01ef, B:46:0x01fb, B:47:0x01fd, B:49:0x0205, B:51:0x0211, B:52:0x0213, B:54:0x0219, B:58:0x0223, B:60:0x022b, B:62:0x0233, B:64:0x023b, B:66:0x0243, B:68:0x024b, B:70:0x0253, B:72:0x025b, B:74:0x0263, B:76:0x026b, B:78:0x0273, B:80:0x027b, B:82:0x0283, B:84:0x028b, B:86:0x0293, B:88:0x029b, B:90:0x02a7, B:92:0x02af, B:171:0x037a, B:177:0x03b4, B:179:0x03bc, B:181:0x03cc, B:182:0x03cf, B:184:0x03d7, B:186:0x03e3, B:187:0x03ee, B:189:0x03f6, B:191:0x0406, B:192:0x0409, B:194:0x0411, B:196:0x0421, B:197:0x0424, B:199:0x042c, B:201:0x043c, B:202:0x043f, B:204:0x0447, B:207:0x0454, B:209:0x0457, B:212:0x0460, B:213:0x046a, B:215:0x0472, B:217:0x0480, B:219:0x0490, B:222:0x0498, B:223:0x049b, B:225:0x04a3, B:226:0x04b4, B:228:0x04bc, B:229:0x04c4, B:231:0x04cc, B:232:0x04d4, B:234:0x04dc, B:235:0x04e5, B:237:0x04ed, B:239:0x04fd, B:243:0x0515, B:248:0x051e, B:250:0x0526, B:254:0x0534, B:256:0x053a, B:257:0x0546, B:258:0x054a, B:260:0x054e, B:262:0x0557, B:264:0x055f, B:261:0x0554, B:253:0x0531, B:247:0x051b, B:95:0x02bf, B:97:0x02cc, B:102:0x02d5, B:109:0x02e6, B:117:0x02f3, B:125:0x0304, B:133:0x0316, B:141:0x0329, B:149:0x033b, B:158:0x0352, B:169:0x0373, B:174:0x03ae, B:176:0x03b1, B:274:0x0579, B:275:0x057e), top: B:294:0x008a }] */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0526 A[Catch: Exception -> 0x05bc, Error -> 0x05c4, TRY_LEAVE, TryCatch #8 {Error -> 0x05c4, blocks: (B:7:0x008a, B:8:0x008c, B:11:0x00bd, B:13:0x0115, B:14:0x011e, B:16:0x0124, B:17:0x0130, B:18:0x0133, B:20:0x0137, B:21:0x013e, B:24:0x0147, B:26:0x0178, B:28:0x0186, B:30:0x0192, B:31:0x0195, B:32:0x0197, B:34:0x019f, B:35:0x01b1, B:37:0x01b7, B:39:0x01cb, B:40:0x01de, B:42:0x01e5, B:44:0x01ef, B:46:0x01fb, B:47:0x01fd, B:49:0x0205, B:51:0x0211, B:52:0x0213, B:54:0x0219, B:58:0x0223, B:60:0x022b, B:62:0x0233, B:64:0x023b, B:66:0x0243, B:68:0x024b, B:70:0x0253, B:72:0x025b, B:74:0x0263, B:76:0x026b, B:78:0x0273, B:80:0x027b, B:82:0x0283, B:84:0x028b, B:86:0x0293, B:88:0x029b, B:90:0x02a7, B:92:0x02af, B:171:0x037a, B:177:0x03b4, B:179:0x03bc, B:181:0x03cc, B:182:0x03cf, B:184:0x03d7, B:186:0x03e3, B:187:0x03ee, B:189:0x03f6, B:191:0x0406, B:192:0x0409, B:194:0x0411, B:196:0x0421, B:197:0x0424, B:199:0x042c, B:201:0x043c, B:202:0x043f, B:204:0x0447, B:207:0x0454, B:209:0x0457, B:212:0x0460, B:213:0x046a, B:215:0x0472, B:217:0x0480, B:219:0x0490, B:222:0x0498, B:223:0x049b, B:225:0x04a3, B:226:0x04b4, B:228:0x04bc, B:229:0x04c4, B:231:0x04cc, B:232:0x04d4, B:234:0x04dc, B:235:0x04e5, B:237:0x04ed, B:239:0x04fd, B:243:0x0515, B:248:0x051e, B:250:0x0526, B:254:0x0534, B:256:0x053a, B:257:0x0546, B:258:0x054a, B:260:0x054e, B:262:0x0557, B:264:0x055f, B:261:0x0554, B:253:0x0531, B:247:0x051b, B:95:0x02bf, B:97:0x02cc, B:102:0x02d5, B:109:0x02e6, B:117:0x02f3, B:125:0x0304, B:133:0x0316, B:141:0x0329, B:149:0x033b, B:158:0x0352, B:169:0x0373, B:174:0x03ae, B:176:0x03b1, B:274:0x0579, B:275:0x057e), top: B:294:0x008a }] */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0530  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x053a A[Catch: Exception -> 0x054a, Error -> 0x05c4, TryCatch #2 {Exception -> 0x054a, blocks: (B:254:0x0534, B:256:0x053a, B:257:0x0546), top: B:296:0x0534 }] */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0546 A[Catch: Exception -> 0x054a, Error -> 0x05c4, TRY_LEAVE, TryCatch #2 {Exception -> 0x054a, blocks: (B:254:0x0534, B:256:0x053a, B:257:0x0546), top: B:296:0x0534 }] */
    /* JADX WARN: Removed duplicated region for block: B:260:0x054e A[Catch: Exception -> 0x05bc, Error -> 0x05c4, TryCatch #8 {Error -> 0x05c4, blocks: (B:7:0x008a, B:8:0x008c, B:11:0x00bd, B:13:0x0115, B:14:0x011e, B:16:0x0124, B:17:0x0130, B:18:0x0133, B:20:0x0137, B:21:0x013e, B:24:0x0147, B:26:0x0178, B:28:0x0186, B:30:0x0192, B:31:0x0195, B:32:0x0197, B:34:0x019f, B:35:0x01b1, B:37:0x01b7, B:39:0x01cb, B:40:0x01de, B:42:0x01e5, B:44:0x01ef, B:46:0x01fb, B:47:0x01fd, B:49:0x0205, B:51:0x0211, B:52:0x0213, B:54:0x0219, B:58:0x0223, B:60:0x022b, B:62:0x0233, B:64:0x023b, B:66:0x0243, B:68:0x024b, B:70:0x0253, B:72:0x025b, B:74:0x0263, B:76:0x026b, B:78:0x0273, B:80:0x027b, B:82:0x0283, B:84:0x028b, B:86:0x0293, B:88:0x029b, B:90:0x02a7, B:92:0x02af, B:171:0x037a, B:177:0x03b4, B:179:0x03bc, B:181:0x03cc, B:182:0x03cf, B:184:0x03d7, B:186:0x03e3, B:187:0x03ee, B:189:0x03f6, B:191:0x0406, B:192:0x0409, B:194:0x0411, B:196:0x0421, B:197:0x0424, B:199:0x042c, B:201:0x043c, B:202:0x043f, B:204:0x0447, B:207:0x0454, B:209:0x0457, B:212:0x0460, B:213:0x046a, B:215:0x0472, B:217:0x0480, B:219:0x0490, B:222:0x0498, B:223:0x049b, B:225:0x04a3, B:226:0x04b4, B:228:0x04bc, B:229:0x04c4, B:231:0x04cc, B:232:0x04d4, B:234:0x04dc, B:235:0x04e5, B:237:0x04ed, B:239:0x04fd, B:243:0x0515, B:248:0x051e, B:250:0x0526, B:254:0x0534, B:256:0x053a, B:257:0x0546, B:258:0x054a, B:260:0x054e, B:262:0x0557, B:264:0x055f, B:261:0x0554, B:253:0x0531, B:247:0x051b, B:95:0x02bf, B:97:0x02cc, B:102:0x02d5, B:109:0x02e6, B:117:0x02f3, B:125:0x0304, B:133:0x0316, B:141:0x0329, B:149:0x033b, B:158:0x0352, B:169:0x0373, B:174:0x03ae, B:176:0x03b1, B:274:0x0579, B:275:0x057e), top: B:294:0x008a }] */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0554 A[Catch: Exception -> 0x05bc, Error -> 0x05c4, TryCatch #8 {Error -> 0x05c4, blocks: (B:7:0x008a, B:8:0x008c, B:11:0x00bd, B:13:0x0115, B:14:0x011e, B:16:0x0124, B:17:0x0130, B:18:0x0133, B:20:0x0137, B:21:0x013e, B:24:0x0147, B:26:0x0178, B:28:0x0186, B:30:0x0192, B:31:0x0195, B:32:0x0197, B:34:0x019f, B:35:0x01b1, B:37:0x01b7, B:39:0x01cb, B:40:0x01de, B:42:0x01e5, B:44:0x01ef, B:46:0x01fb, B:47:0x01fd, B:49:0x0205, B:51:0x0211, B:52:0x0213, B:54:0x0219, B:58:0x0223, B:60:0x022b, B:62:0x0233, B:64:0x023b, B:66:0x0243, B:68:0x024b, B:70:0x0253, B:72:0x025b, B:74:0x0263, B:76:0x026b, B:78:0x0273, B:80:0x027b, B:82:0x0283, B:84:0x028b, B:86:0x0293, B:88:0x029b, B:90:0x02a7, B:92:0x02af, B:171:0x037a, B:177:0x03b4, B:179:0x03bc, B:181:0x03cc, B:182:0x03cf, B:184:0x03d7, B:186:0x03e3, B:187:0x03ee, B:189:0x03f6, B:191:0x0406, B:192:0x0409, B:194:0x0411, B:196:0x0421, B:197:0x0424, B:199:0x042c, B:201:0x043c, B:202:0x043f, B:204:0x0447, B:207:0x0454, B:209:0x0457, B:212:0x0460, B:213:0x046a, B:215:0x0472, B:217:0x0480, B:219:0x0490, B:222:0x0498, B:223:0x049b, B:225:0x04a3, B:226:0x04b4, B:228:0x04bc, B:229:0x04c4, B:231:0x04cc, B:232:0x04d4, B:234:0x04dc, B:235:0x04e5, B:237:0x04ed, B:239:0x04fd, B:243:0x0515, B:248:0x051e, B:250:0x0526, B:254:0x0534, B:256:0x053a, B:257:0x0546, B:258:0x054a, B:260:0x054e, B:262:0x0557, B:264:0x055f, B:261:0x0554, B:253:0x0531, B:247:0x051b, B:95:0x02bf, B:97:0x02cc, B:102:0x02d5, B:109:0x02e6, B:117:0x02f3, B:125:0x0304, B:133:0x0316, B:141:0x0329, B:149:0x033b, B:158:0x0352, B:169:0x0373, B:174:0x03ae, B:176:0x03b1, B:274:0x0579, B:275:0x057e), top: B:294:0x008a }] */
    /* JADX WARN: Removed duplicated region for block: B:264:0x055f A[Catch: Exception -> 0x05bc, Error -> 0x05c4, TryCatch #8 {Error -> 0x05c4, blocks: (B:7:0x008a, B:8:0x008c, B:11:0x00bd, B:13:0x0115, B:14:0x011e, B:16:0x0124, B:17:0x0130, B:18:0x0133, B:20:0x0137, B:21:0x013e, B:24:0x0147, B:26:0x0178, B:28:0x0186, B:30:0x0192, B:31:0x0195, B:32:0x0197, B:34:0x019f, B:35:0x01b1, B:37:0x01b7, B:39:0x01cb, B:40:0x01de, B:42:0x01e5, B:44:0x01ef, B:46:0x01fb, B:47:0x01fd, B:49:0x0205, B:51:0x0211, B:52:0x0213, B:54:0x0219, B:58:0x0223, B:60:0x022b, B:62:0x0233, B:64:0x023b, B:66:0x0243, B:68:0x024b, B:70:0x0253, B:72:0x025b, B:74:0x0263, B:76:0x026b, B:78:0x0273, B:80:0x027b, B:82:0x0283, B:84:0x028b, B:86:0x0293, B:88:0x029b, B:90:0x02a7, B:92:0x02af, B:171:0x037a, B:177:0x03b4, B:179:0x03bc, B:181:0x03cc, B:182:0x03cf, B:184:0x03d7, B:186:0x03e3, B:187:0x03ee, B:189:0x03f6, B:191:0x0406, B:192:0x0409, B:194:0x0411, B:196:0x0421, B:197:0x0424, B:199:0x042c, B:201:0x043c, B:202:0x043f, B:204:0x0447, B:207:0x0454, B:209:0x0457, B:212:0x0460, B:213:0x046a, B:215:0x0472, B:217:0x0480, B:219:0x0490, B:222:0x0498, B:223:0x049b, B:225:0x04a3, B:226:0x04b4, B:228:0x04bc, B:229:0x04c4, B:231:0x04cc, B:232:0x04d4, B:234:0x04dc, B:235:0x04e5, B:237:0x04ed, B:239:0x04fd, B:243:0x0515, B:248:0x051e, B:250:0x0526, B:254:0x0534, B:256:0x053a, B:257:0x0546, B:258:0x054a, B:260:0x054e, B:262:0x0557, B:264:0x055f, B:261:0x0554, B:253:0x0531, B:247:0x051b, B:95:0x02bf, B:97:0x02cc, B:102:0x02d5, B:109:0x02e6, B:117:0x02f3, B:125:0x0304, B:133:0x0316, B:141:0x0329, B:149:0x033b, B:158:0x0352, B:169:0x0373, B:174:0x03ae, B:176:0x03b1, B:274:0x0579, B:275:0x057e), top: B:294:0x008a }] */
    /* JADX WARN: Removed duplicated region for block: B:322:0x0472 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:331:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v4, types: [boolean, int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public BDLocation(java.lang.String r18) throws org.json.JSONException {
        /*
            Method dump skipped, instruction units count: 1494
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.BDLocation.<init>(java.lang.String):void");
    }

    private void a(Boolean bool) {
        this.t = bool.booleanValue();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAdCode() {
        return this.u.adcode;
    }

    public String getAddrStr() {
        return this.u.address;
    }

    public Address getAddress() {
        return this.u;
    }

    public double getAltitude() {
        return this.f2023f;
    }

    public String getBuildingID() {
        return this.w;
    }

    public String getBuildingName() {
        return this.x;
    }

    public String getCity() {
        return this.u.city;
    }

    public String getCityCode() {
        return this.u.cityCode;
    }

    public String getCoorType() {
        return this.n;
    }

    public String getCountry() {
        return this.u.country;
    }

    public String getCountryCode() {
        return this.u.countryCode;
    }

    public long getDelayTime() {
        return this.S;
    }

    @Deprecated
    public float getDerect() {
        return this.m;
    }

    public float getDirection() {
        return this.m;
    }

    public String getDistrict() {
        return this.u.district;
    }

    public Location getExtraLocation(String str) {
        Bundle bundle = this.P;
        if (bundle == null) {
            return null;
        }
        Parcelable parcelable = bundle.getParcelable(str);
        if (parcelable instanceof Location) {
            return (Location) parcelable;
        }
        return null;
    }

    public String getFloor() {
        return this.v;
    }

    public double[] getFusionLocInfo(String str) {
        return this.P.getDoubleArray(str);
    }

    public int getGpsAccuracyStatus() {
        return this.Q;
    }

    public int getGpsCheckStatus() {
        return this.R;
    }

    public int getIndoorLocationSource() {
        return this.H;
    }

    public int getIndoorLocationSurpport() {
        return this.F;
    }

    public String getIndoorLocationSurpportBuidlingID() {
        return this.J;
    }

    public String getIndoorLocationSurpportBuidlingName() {
        return this.I;
    }

    public int getIndoorNetworkState() {
        return this.G;
    }

    public String getIndoorSurpportPolygon() {
        return this.K;
    }

    public double getLatitude() {
        return this.f2020c;
    }

    public int getLocType() {
        return this.f2018a;
    }

    public String getLocTypeDescription() {
        return this.M;
    }

    public String getLocationDescribe() {
        return this.q;
    }

    public String getLocationID() {
        return this.N;
    }

    public int getLocationWhere() {
        return this.A;
    }

    public double getLongitude() {
        return this.f2021d;
    }

    public String getNetworkLocationType() {
        return this.B;
    }

    public String getNrlResult() {
        return this.T;
    }

    public int getOperators() {
        return this.C;
    }

    public List<Poi> getPoiList() {
        return this.L;
    }

    public String getProvince() {
        return this.u.province;
    }

    public float getRadius() {
        return this.j;
    }

    public String getRetFields(String str) {
        return this.P.getString(str);
    }

    public String getRoadLocString() {
        return this.O;
    }

    public int getSatelliteNumber() {
        this.k = true;
        return this.l;
    }

    @Deprecated
    public String getSemaAptag() {
        return this.q;
    }

    public float getSpeed() {
        return this.f2025h;
    }

    public String getStreet() {
        return this.u.street;
    }

    public String getStreetNumber() {
        return this.u.streetNumber;
    }

    public String getTime() {
        return this.f2019b;
    }

    public int getUserIndoorState() {
        return this.E;
    }

    public boolean hasAddr() {
        return this.o;
    }

    public boolean hasAltitude() {
        return this.f2022e;
    }

    public boolean hasRadius() {
        return this.i;
    }

    public boolean hasSateNumber() {
        return this.k;
    }

    public boolean hasSpeed() {
        return this.f2024g;
    }

    public boolean isCellChangeFlag() {
        return this.t;
    }

    public boolean isIndoorLocMode() {
        return this.y;
    }

    public int isParkAvailable() {
        return this.z;
    }

    public void setAddr(Address address) {
        if (address != null) {
            this.u = address;
            this.o = true;
        }
    }

    public void setAddrStr(String str) {
        this.p = str;
        this.o = str != null;
    }

    public void setAltitude(double d2) {
        if (d2 < 9999.0d) {
            this.f2023f = d2;
            this.f2022e = true;
        }
    }

    public void setBuildingID(String str) {
        this.w = str;
    }

    public void setBuildingName(String str) {
        this.x = str;
    }

    public void setCoorType(String str) {
        this.n = str;
    }

    public void setDelayTime(long j) {
        this.S = j;
    }

    public void setDirection(float f2) {
        this.m = f2;
    }

    public void setExtraLocation(String str, Location location) {
        if (this.P == null) {
            this.P = new Bundle();
        }
        this.P.putParcelable(str, location);
    }

    public void setFloor(String str) {
        this.v = str;
    }

    public void setFusionLocInfo(String str, double[] dArr) {
        if (this.P == null) {
            this.P = new Bundle();
        }
        this.P.putDoubleArray(str, dArr);
    }

    public void setGpsAccuracyStatus(int i) {
        this.Q = i;
    }

    public void setGpsCheckStatus(int i) {
        this.R = i;
    }

    public void setIndoorLocMode(boolean z) {
        this.y = z;
    }

    public void setIndoorLocationSource(int i) {
        this.H = i;
    }

    public void setIndoorLocationSurpport(int i) {
        this.F = i;
    }

    public void setIndoorNetworkState(int i) {
        this.G = i;
    }

    public void setIndoorSurpportPolygon(String str) {
        this.K = str;
    }

    public void setLatitude(double d2) {
        this.f2020c = d2;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setLocType(int r2) {
        /*
            r1 = this;
            r1.f2018a = r2
            r0 = 66
            if (r2 == r0) goto L3f
            r0 = 67
            if (r2 == r0) goto L3c
            r0 = 161(0xa1, float:2.26E-43)
            if (r2 == r0) goto L39
            r0 = 162(0xa2, float:2.27E-43)
            if (r2 == r0) goto L36
            r0 = 167(0xa7, float:2.34E-43)
            if (r2 == r0) goto L33
            r0 = 505(0x1f9, float:7.08E-43)
            if (r2 == r0) goto L30
            switch(r2) {
                case 61: goto L26;
                case 62: goto L23;
                case 63: goto L3c;
                default: goto L1d;
            }
        L1d:
            java.lang.String r2 = "UnKnown!"
        L1f:
            r1.setLocTypeDescription(r2)
            goto L42
        L23:
            java.lang.String r2 = "Location failed beacuse we can not get any loc information!"
            goto L1f
        L26:
            java.lang.String r2 = "GPS location successful!"
            r1.setLocTypeDescription(r2)
            r2 = 0
            r1.setUserIndoorState(r2)
            goto L42
        L30:
            java.lang.String r2 = "NetWork location failed because baidu location service check the key is unlegal, please check the key in AndroidManifest.xml !"
            goto L1f
        L33:
            java.lang.String r2 = "NetWork location failed because baidu location service can not caculate the location!"
            goto L1f
        L36:
            java.lang.String r2 = "NetWork location failed because baidu location service can not decrypt the request query, please check the so file !"
            goto L1f
        L39:
            java.lang.String r2 = "NetWork location successful!"
            goto L1f
        L3c:
            java.lang.String r2 = "Offline location failed, please check the net (wifi/cell)!"
            goto L1f
        L3f:
            java.lang.String r2 = "Offline location successful!"
            goto L1f
        L42:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.BDLocation.setLocType(int):void");
    }

    public void setLocTypeDescription(String str) {
        this.M = str;
    }

    public void setLocationDescribe(String str) {
        this.q = str;
    }

    public void setLocationID(String str) {
        this.N = str;
    }

    public void setLocationWhere(int i) {
        this.A = i;
    }

    public void setLongitude(double d2) {
        this.f2021d = d2;
    }

    public void setNetworkLocationType(String str) {
        this.B = str;
    }

    public void setNrlData(String str) {
        this.T = str;
    }

    public void setOperators(int i) {
        this.C = i;
    }

    public void setParkAvailable(int i) {
        this.z = i;
    }

    public void setPoiList(List<Poi> list) {
        this.L = list;
    }

    public void setRadius(float f2) {
        this.j = f2;
        this.i = true;
    }

    public void setRetFields(String str, String str2) {
        if (this.P == null) {
            this.P = new Bundle();
        }
        this.P.putString(str, str2);
    }

    public void setRoadLocString(float f2, float f3) {
        String str = ((double) f2) > 0.001d ? String.format("%.2f", Float.valueOf(f2)) : "";
        String str2 = ((double) f3) > 0.001d ? String.format("%.2f", Float.valueOf(f3)) : "";
        if (this.T != null) {
            this.O = String.format(Locale.US, "%s,%s,%s", this.T, str, str2);
        }
    }

    public void setSatelliteNumber(int i) {
        this.l = i;
    }

    public void setSpeed(float f2) {
        this.f2025h = f2;
        this.f2024g = true;
    }

    public void setTime(String str) {
        this.f2019b = str;
        setLocationID(k.a(str));
    }

    public void setUserIndoorState(int i) {
        this.E = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2018a);
        parcel.writeString(this.f2019b);
        parcel.writeDouble(this.f2020c);
        parcel.writeDouble(this.f2021d);
        parcel.writeDouble(this.f2023f);
        parcel.writeFloat(this.f2025h);
        parcel.writeFloat(this.j);
        parcel.writeInt(this.l);
        parcel.writeFloat(this.m);
        parcel.writeString(this.v);
        parcel.writeInt(this.z);
        parcel.writeString(this.w);
        parcel.writeString(this.x);
        parcel.writeString(this.B);
        parcel.writeString(this.u.province);
        parcel.writeString(this.u.city);
        parcel.writeString(this.u.district);
        parcel.writeString(this.u.street);
        parcel.writeString(this.u.streetNumber);
        parcel.writeString(this.u.cityCode);
        parcel.writeString(this.u.address);
        parcel.writeString(this.u.country);
        parcel.writeString(this.u.countryCode);
        parcel.writeString(this.u.adcode);
        parcel.writeInt(this.C);
        parcel.writeString(this.D);
        parcel.writeString(this.q);
        parcel.writeString(this.r);
        parcel.writeString(this.s);
        parcel.writeInt(this.A);
        parcel.writeString(this.M);
        parcel.writeInt(this.E);
        parcel.writeInt(this.F);
        parcel.writeInt(this.G);
        parcel.writeInt(this.H);
        parcel.writeString(this.I);
        parcel.writeString(this.J);
        parcel.writeString(this.K);
        parcel.writeInt(this.Q);
        parcel.writeString(this.N);
        parcel.writeInt(this.R);
        parcel.writeString(this.O);
        parcel.writeString(this.T);
        parcel.writeLong(this.S);
        parcel.writeBooleanArray(new boolean[]{this.f2022e, this.f2024g, this.i, this.k, this.o, this.t, this.y});
        parcel.writeList(this.L);
        parcel.writeBundle(this.P);
    }
}