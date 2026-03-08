package com.amap.api.fence;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.DPoint;
import com.loc.ep;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GeoFence implements Parcelable {
    public static final int ADDGEOFENCE_SUCCESS = 0;
    public static final String BUNDLE_KEY_CUSTOMID = "customId";
    public static final String BUNDLE_KEY_FENCE = "fence";
    public static final String BUNDLE_KEY_FENCEID = "fenceid";
    public static final String BUNDLE_KEY_FENCESTATUS = "event";
    public static final String BUNDLE_KEY_LOCERRORCODE = "location_errorcode";
    public static final Parcelable.Creator<GeoFence> CREATOR = new Parcelable.Creator<GeoFence>() { // from class: com.amap.api.fence.GeoFence.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ GeoFence createFromParcel(Parcel parcel) {
            return new GeoFence(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ GeoFence[] newArray(int i) {
            return new GeoFence[i];
        }
    };
    public static final int ERROR_CODE_EXISTS = 17;
    public static final int ERROR_CODE_FAILURE_AUTH = 7;
    public static final int ERROR_CODE_FAILURE_CONNECTION = 4;
    public static final int ERROR_CODE_FAILURE_PARSER = 5;
    public static final int ERROR_CODE_INVALID_PARAMETER = 1;
    public static final int ERROR_CODE_UNKNOWN = 8;
    public static final int ERROR_NO_VALIDFENCE = 16;
    public static final int STATUS_IN = 1;
    public static final int STATUS_LOCFAIL = 4;
    public static final int STATUS_OUT = 2;
    public static final int STATUS_STAYED = 3;
    public static final int STATUS_UNKNOWN = 0;
    public static final int TYPE_AMAPPOI = 2;
    public static final int TYPE_DISTRICT = 3;
    public static final int TYPE_POLYGON = 1;
    public static final int TYPE_ROUND = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f33a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f34b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f35c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private PendingIntent f36d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f37e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private PoiItem f38f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private List<DistrictItem> f39g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private List<List<DPoint>> f40h;
    private float i;
    private long j;
    private int k;
    private float l;
    private float m;
    private DPoint n;
    private int o;
    private long p;
    private boolean q;
    private AMapLocation r;

    public GeoFence() {
        this.f36d = null;
        this.f37e = 0;
        this.f38f = null;
        this.f39g = null;
        this.i = 0.0f;
        this.j = -1L;
        this.k = 1;
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = null;
        this.o = 0;
        this.p = -1L;
        this.q = true;
        this.r = null;
    }

    protected GeoFence(Parcel parcel) {
        this.f36d = null;
        this.f37e = 0;
        this.f38f = null;
        this.f39g = null;
        this.i = 0.0f;
        this.j = -1L;
        this.k = 1;
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = null;
        this.o = 0;
        this.p = -1L;
        this.q = true;
        this.r = null;
        this.f33a = parcel.readString();
        this.f34b = parcel.readString();
        this.f35c = parcel.readString();
        this.f36d = (PendingIntent) parcel.readParcelable(PendingIntent.class.getClassLoader());
        this.f37e = parcel.readInt();
        this.f38f = (PoiItem) parcel.readParcelable(PoiItem.class.getClassLoader());
        this.f39g = parcel.createTypedArrayList(DistrictItem.CREATOR);
        this.i = parcel.readFloat();
        this.j = parcel.readLong();
        this.k = parcel.readInt();
        this.l = parcel.readFloat();
        this.m = parcel.readFloat();
        this.n = (DPoint) parcel.readParcelable(DPoint.class.getClassLoader());
        this.o = parcel.readInt();
        this.p = parcel.readLong();
        int i = parcel.readInt();
        if (i != 0) {
            this.f40h = new ArrayList();
            for (int i2 = 0; i2 < i; i2++) {
                this.f40h.add(parcel.createTypedArrayList(DPoint.CREATOR));
            }
        }
        this.q = parcel.readByte() != 0;
        this.r = (AMapLocation) parcel.readParcelable(AMapLocation.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GeoFence)) {
            return false;
        }
        GeoFence geoFence = (GeoFence) obj;
        if (TextUtils.isEmpty(this.f34b)) {
            if (!TextUtils.isEmpty(geoFence.f34b)) {
                return false;
            }
        } else if (!this.f34b.equals(geoFence.f34b)) {
            return false;
        }
        DPoint dPoint = this.n;
        if (dPoint == null) {
            if (geoFence.n != null) {
                return false;
            }
        } else if (!dPoint.equals(geoFence.n)) {
            return false;
        }
        if (this.i != geoFence.i) {
            return false;
        }
        List<List<DPoint>> list = this.f40h;
        List<List<DPoint>> list2 = geoFence.f40h;
        return list == null ? list2 == null : list.equals(list2);
    }

    public int getActivatesAction() {
        return this.k;
    }

    public DPoint getCenter() {
        return this.n;
    }

    public AMapLocation getCurrentLocation() {
        return this.r;
    }

    public String getCustomId() {
        return this.f34b;
    }

    public List<DistrictItem> getDistrictItemList() {
        return this.f39g;
    }

    public long getEnterTime() {
        return this.p;
    }

    public long getExpiration() {
        return this.j;
    }

    public String getFenceId() {
        return this.f33a;
    }

    public float getMaxDis2Center() {
        return this.m;
    }

    public float getMinDis2Center() {
        return this.l;
    }

    public PendingIntent getPendingIntent() {
        return this.f36d;
    }

    public String getPendingIntentAction() {
        return this.f35c;
    }

    public PoiItem getPoiItem() {
        return this.f38f;
    }

    public List<List<DPoint>> getPointList() {
        return this.f40h;
    }

    public float getRadius() {
        return this.i;
    }

    public int getStatus() {
        return this.o;
    }

    public int getType() {
        return this.f37e;
    }

    public int hashCode() {
        return this.f34b.hashCode() + this.f40h.hashCode() + this.n.hashCode() + ((int) (this.i * 100.0f));
    }

    public boolean isAble() {
        return this.q;
    }

    public void setAble(boolean z) {
        this.q = z;
    }

    public void setActivatesAction(int i) {
        this.k = i;
    }

    public void setCenter(DPoint dPoint) {
        this.n = dPoint;
    }

    public void setCurrentLocation(AMapLocation aMapLocation) {
        this.r = aMapLocation.m7clone();
    }

    public void setCustomId(String str) {
        this.f34b = str;
    }

    public void setDistrictItemList(List<DistrictItem> list) {
        this.f39g = list;
    }

    public void setEnterTime(long j) {
        this.p = j;
    }

    public void setExpiration(long j) {
        this.j = j < 0 ? -1L : j + ep.b();
    }

    public void setFenceId(String str) {
        this.f33a = str;
    }

    public void setMaxDis2Center(float f2) {
        this.m = f2;
    }

    public void setMinDis2Center(float f2) {
        this.l = f2;
    }

    public void setPendingIntent(PendingIntent pendingIntent) {
        this.f36d = pendingIntent;
    }

    public void setPendingIntentAction(String str) {
        this.f35c = str;
    }

    public void setPoiItem(PoiItem poiItem) {
        this.f38f = poiItem;
    }

    public void setPointList(List<List<DPoint>> list) {
        this.f40h = list;
    }

    public void setRadius(float f2) {
        this.i = f2;
    }

    public void setStatus(int i) {
        this.o = i;
    }

    public void setType(int i) {
        this.f37e = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f33a);
        parcel.writeString(this.f34b);
        parcel.writeString(this.f35c);
        parcel.writeParcelable(this.f36d, i);
        parcel.writeInt(this.f37e);
        parcel.writeParcelable(this.f38f, i);
        parcel.writeTypedList(this.f39g);
        parcel.writeFloat(this.i);
        parcel.writeLong(this.j);
        parcel.writeInt(this.k);
        parcel.writeFloat(this.l);
        parcel.writeFloat(this.m);
        parcel.writeParcelable(this.n, i);
        parcel.writeInt(this.o);
        parcel.writeLong(this.p);
        List<List<DPoint>> list = this.f40h;
        if (list != null && !list.isEmpty()) {
            parcel.writeInt(this.f40h.size());
            Iterator<List<DPoint>> it = this.f40h.iterator();
            while (it.hasNext()) {
                parcel.writeTypedList(it.next());
            }
        }
        parcel.writeByte(this.q ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.r, i);
    }
}