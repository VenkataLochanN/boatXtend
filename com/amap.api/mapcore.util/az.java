package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.amap.api.mapcore.util.br;
import com.amap.api.mapcore.util.ca;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.ido.common.utils.FileDialDefinedUtil;
import java.io.File;

/* JADX INFO: compiled from: CityObject.java */
/* JADX INFO: loaded from: classes.dex */
public class az extends OfflineMapCity implements bi, bz {
    public static final Parcelable.Creator<az> o = new Parcelable.Creator<az>() { // from class: com.amap.api.mapcore.util.az.2
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public az createFromParcel(Parcel parcel) {
            return new az(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public az[] newArray(int i) {
            return new az[i];
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final cd f217a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final cd f218b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final cd f219c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final cd f220d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final cd f221e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final cd f222f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final cd f223g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final cd f224h;
    public final cd i;
    public final cd j;
    public final cd k;
    cd l;
    Context m;
    boolean n;
    private String p;
    private String q;
    private long r;

    @Override // com.amap.api.maps.offlinemap.OfflineMapCity, com.amap.api.maps.offlinemap.City, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void a(String str) {
        this.q = str;
    }

    public String a() {
        return this.q;
    }

    @Override // com.amap.api.mapcore.util.bi
    public String b() {
        return getUrl();
    }

    public az(Context context, OfflineMapCity offlineMapCity) {
        this(context, offlineMapCity.getState());
        setCity(offlineMapCity.getCity());
        setUrl(offlineMapCity.getUrl());
        setState(offlineMapCity.getState());
        setCompleteCode(offlineMapCity.getcompleteCode());
        setAdcode(offlineMapCity.getAdcode());
        setVersion(offlineMapCity.getVersion());
        setSize(offlineMapCity.getSize());
        setCode(offlineMapCity.getCode());
        setJianpin(offlineMapCity.getJianpin());
        setPinyin(offlineMapCity.getPinyin());
        t();
    }

    public az(Context context, int i) {
        this.f217a = new cf(6, this);
        this.f218b = new cm(2, this);
        this.f219c = new ci(0, this);
        this.f220d = new ck(3, this);
        this.f221e = new cl(1, this);
        this.f222f = new ce(4, this);
        this.f223g = new cj(7, this);
        this.f224h = new cg(-1, this);
        this.i = new cg(101, this);
        this.j = new cg(102, this);
        this.k = new cg(103, this);
        this.p = null;
        this.q = "";
        this.n = false;
        this.r = 0L;
        this.m = context;
        a(i);
    }

    public void a(int i) {
        if (i != -1) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i != 6) {
                                    if (i == 7) {
                                        this.l = this.f223g;
                                    } else {
                                        switch (i) {
                                            case 101:
                                                this.l = this.i;
                                                break;
                                            case 102:
                                                this.l = this.j;
                                                break;
                                            case 103:
                                                this.l = this.k;
                                                break;
                                            default:
                                                if (i < 0) {
                                                    this.l = this.f224h;
                                                }
                                                break;
                                        }
                                    }
                                } else {
                                    this.l = this.f217a;
                                }
                            } else {
                                this.l = this.f222f;
                            }
                        } else {
                            this.l = this.f220d;
                        }
                    } else {
                        this.l = this.f218b;
                    }
                } else {
                    this.l = this.f221e;
                }
            } else {
                this.l = this.f219c;
            }
        } else {
            this.l = this.f224h;
        }
        setState(i);
    }

    public void a(cd cdVar) {
        this.l = cdVar;
        setState(cdVar.b());
    }

    public cd c() {
        return this.l;
    }

    public void d() {
        ba baVarA = ba.a(this.m);
        if (baVarA != null) {
            baVarA.c(this);
        }
    }

    public void e() {
        ba baVarA = ba.a(this.m);
        if (baVarA != null) {
            baVarA.e(this);
            d();
        }
    }

    public void f() {
        bx.a("CityOperation current State==>" + c().b());
        if (this.l.equals(this.f220d)) {
            this.l.d();
            return;
        }
        if (this.l.equals(this.f219c)) {
            this.l.e();
            return;
        }
        if (this.l.equals(this.f223g) || this.l.equals(this.f224h)) {
            k();
            this.n = true;
        } else if (this.l.equals(this.j) || this.l.equals(this.i) || this.l.a(this.k)) {
            this.l.c();
        } else {
            c().h();
        }
    }

    public void g() {
        this.l.e();
    }

    public void h() {
        this.l.a(this.k.b());
    }

    public void i() {
        this.l.a();
        if (this.n) {
            this.l.h();
        }
        this.n = false;
    }

    public void j() {
        this.l.equals(this.f222f);
        this.l.f();
    }

    public void k() {
        ba baVarA = ba.a(this.m);
        if (baVarA != null) {
            baVarA.a(this);
        }
    }

    public void l() {
        ba baVarA = ba.a(this.m);
        if (baVarA != null) {
            baVarA.b(this);
        }
    }

    public void m() {
        ba baVarA = ba.a(this.m);
        if (baVarA != null) {
            baVarA.d(this);
        }
    }

    @Override // com.amap.api.mapcore.util.ca
    public void n() {
        this.r = 0L;
        if (!this.l.equals(this.f218b)) {
            bx.a("state must be waiting when download onStart");
        }
        this.l.c();
    }

    @Override // com.amap.api.mapcore.util.ca
    public void a(long j, long j2) {
        int i = (int) ((j2 * 100) / j);
        if (i != getcompleteCode()) {
            setCompleteCode(i);
            d();
        }
    }

    @Override // com.amap.api.mapcore.util.ca
    public void o() {
        if (!this.l.equals(this.f219c)) {
            bx.a("state must be Loading when download onFinish");
        }
        this.l.g();
    }

    /* JADX INFO: renamed from: com.amap.api.mapcore.util.az$3, reason: invalid class name */
    /* JADX INFO: compiled from: CityObject.java */
    static /* synthetic */ class AnonymousClass3 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f228a = new int[ca.a.values().length];

        static {
            try {
                f228a[ca.a.amap_exception.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f228a[ca.a.file_io_exception.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f228a[ca.a.network_exception.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.amap.api.mapcore.util.ca
    public void a(ca.a aVar) {
        int iB;
        int i = AnonymousClass3.f228a[aVar.ordinal()];
        if (i == 1) {
            iB = this.j.b();
        } else if (i == 2) {
            iB = this.k.b();
        } else {
            iB = i != 3 ? 6 : this.i.b();
        }
        if (this.l.equals(this.f219c) || this.l.equals(this.f218b)) {
            this.l.a(iB);
        }
    }

    @Override // com.amap.api.mapcore.util.ca
    public void p() {
        e();
    }

    @Override // com.amap.api.mapcore.util.bs
    public void q() {
        this.r = 0L;
        setCompleteCode(0);
        this.l.equals(this.f221e);
        this.l.c();
    }

    @Override // com.amap.api.mapcore.util.bs
    public void r() {
        this.l.equals(this.f221e);
        this.l.a(this.f224h.b());
    }

    @Override // com.amap.api.mapcore.util.bs
    public void a(long j) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.r > 500) {
            int i = (int) j;
            if (i > getcompleteCode()) {
                setCompleteCode(i);
                d();
            }
            this.r = jCurrentTimeMillis;
        }
    }

    @Override // com.amap.api.mapcore.util.bs
    public void b(String str) {
        this.l.equals(this.f221e);
        this.q = str;
        String strU = u();
        String strV = v();
        if (TextUtils.isEmpty(strU) || TextUtils.isEmpty(strV)) {
            r();
            return;
        }
        File file = new File(strV + "/");
        File file2 = new File(er.a(this.m) + File.separator + "map/");
        File file3 = new File(er.a(this.m));
        if (file3.exists() || file3.mkdir()) {
            if (file2.exists() || file2.mkdir()) {
                a(file, file2, strU);
            }
        }
    }

    @Override // com.amap.api.mapcore.util.bs
    public void s() {
        e();
    }

    protected void t() {
        String str = ba.f237a;
        String strC = bx.c(getUrl());
        if (strC != null) {
            this.p = str + strC + ".zip.tmp";
            return;
        }
        this.p = str + getPinyin() + ".zip.tmp";
    }

    public String u() {
        if (TextUtils.isEmpty(this.p)) {
            return null;
        }
        String str = this.p;
        return str.substring(0, str.lastIndexOf("."));
    }

    public String v() {
        if (TextUtils.isEmpty(this.p)) {
            return null;
        }
        String strU = u();
        return strU.substring(0, strU.lastIndexOf(46));
    }

    private void a(final File file, File file2, final String str) {
        new br().a(file, file2, -1L, bx.a(file), new br.a() { // from class: com.amap.api.mapcore.util.az.1
            @Override // com.amap.api.mapcore.util.br.a
            public void a(String str2, String str3) {
            }

            @Override // com.amap.api.mapcore.util.br.a
            public void a(String str2, String str3, float f2) {
                int i = (int) ((((double) f2) * 0.39d) + 60.0d);
                if (i - az.this.getcompleteCode() <= 0 || System.currentTimeMillis() - az.this.r <= 1000) {
                    return;
                }
                az.this.setCompleteCode(i);
                az.this.r = System.currentTimeMillis();
            }

            @Override // com.amap.api.mapcore.util.br.a
            public void b(String str2, String str3) {
                try {
                    if (new File(str).delete()) {
                        bx.b(file);
                        az.this.setCompleteCode(100);
                        az.this.l.g();
                    }
                } catch (Exception unused) {
                    az.this.l.a(az.this.k.b());
                }
            }

            @Override // com.amap.api.mapcore.util.br.a
            public void a(String str2, String str3, int i) {
                az.this.l.a(az.this.k.b());
            }
        });
    }

    public boolean w() {
        if (bx.a() < (getSize() * 2.5d) - (((long) getcompleteCode()) * getSize())) {
        }
        return false;
    }

    public bk x() {
        setState(this.l.b());
        bk bkVar = new bk(this, this.m);
        bkVar.a(a());
        bx.a("vMapFileNames: " + a());
        return bkVar;
    }

    @Override // com.amap.api.maps.offlinemap.OfflineMapCity, com.amap.api.maps.offlinemap.City, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.q);
    }

    public az(Parcel parcel) {
        super(parcel);
        this.f217a = new cf(6, this);
        this.f218b = new cm(2, this);
        this.f219c = new ci(0, this);
        this.f220d = new ck(3, this);
        this.f221e = new cl(1, this);
        this.f222f = new ce(4, this);
        this.f223g = new cj(7, this);
        this.f224h = new cg(-1, this);
        this.i = new cg(101, this);
        this.j = new cg(102, this);
        this.k = new cg(103, this);
        this.p = null;
        this.q = "";
        this.n = false;
        this.r = 0L;
        this.q = parcel.readString();
    }

    @Override // com.amap.api.mapcore.util.bz
    public boolean y() {
        return w();
    }

    @Override // com.amap.api.mapcore.util.bz
    public String z() {
        StringBuffer stringBuffer = new StringBuffer();
        String strC = bx.c(getUrl());
        if (strC != null) {
            stringBuffer.append(strC);
        } else {
            stringBuffer.append(getPinyin());
        }
        stringBuffer.append(FileDialDefinedUtil.FILE_ZIP);
        return stringBuffer.toString();
    }

    @Override // com.amap.api.mapcore.util.bz
    public String A() {
        return getAdcode();
    }

    @Override // com.amap.api.mapcore.util.bt
    public String B() {
        return u();
    }

    @Override // com.amap.api.mapcore.util.bt
    public String C() {
        return v();
    }

    public cd b(int i) {
        switch (i) {
            case 101:
                return this.i;
            case 102:
                return this.j;
            case 103:
                return this.k;
            default:
                return this.f224h;
        }
    }
}