package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class Overlay {
    boolean A;
    Bundle B;
    protected a listener;
    public com.baidu.mapsdkplatform.comapi.map.h type;
    String y = System.currentTimeMillis() + "_" + hashCode();
    int z;

    interface a {
        void a(Overlay overlay);

        void b(Overlay overlay);
    }

    protected Overlay() {
    }

    static void a(int i, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putFloat("red", ((i >> 16) & 255) / 255.0f);
        bundle2.putFloat("green", ((i >> 8) & 255) / 255.0f);
        bundle2.putFloat("blue", (i & 255) / 255.0f);
        bundle2.putFloat("alpha", (i >>> 24) / 255.0f);
        bundle.putBundle("color", bundle2);
    }

    static void a(List<LatLng> list, Bundle bundle) {
        int size = list.size();
        double[] dArr = new double[size];
        double[] dArr2 = new double[size];
        for (int i = 0; i < size; i++) {
            GeoPoint geoPointLl2mc = CoordUtil.ll2mc(list.get(i));
            dArr[i] = geoPointLl2mc.getLongitudeE6();
            dArr2[i] = geoPointLl2mc.getLatitudeE6();
        }
        bundle.putDoubleArray("x_array", dArr);
        bundle.putDoubleArray("y_array", dArr2);
    }

    Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putString("id", this.y);
        bundle.putInt("type", this.type.ordinal());
        return bundle;
    }

    Bundle a(Bundle bundle) {
        bundle.putString("id", this.y);
        bundle.putInt("type", this.type.ordinal());
        bundle.putInt("visibility", this.A ? 1 : 0);
        bundle.putInt("z_index", this.z);
        return bundle;
    }

    public Bundle getExtraInfo() {
        return this.B;
    }

    public int getZIndex() {
        return this.z;
    }

    public boolean isVisible() {
        return this.A;
    }

    public void remove() {
        this.listener.a(this);
    }

    public void setExtraInfo(Bundle bundle) {
        this.B = bundle;
    }

    public void setVisible(boolean z) {
        this.A = z;
        this.listener.b(this);
    }

    public void setZIndex(int i) {
        this.z = i;
        this.listener.b(this);
    }
}