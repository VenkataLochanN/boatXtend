package com.baidu.mapsdkplatform.comapi.synchronization.a;

import android.content.Context;
import android.view.View;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.synchronization.DisplayOptions;
import com.baidu.mapapi.synchronization.RoleOptions;
import com.baidu.mapapi.synchronization.SyncCoordinateConverter;
import com.baidu.mapapi.synchronization.SynchronizationConstants;
import com.baidu.mapapi.synchronization.SynchronizationDisplayListener;
import com.baidu.mapsdkplatform.comapi.synchronization.c.c;
import com.baidu.mapsdkplatform.comapi.synchronization.data.k;
import com.baidu.mapsdkplatform.comapi.synchronization.data.l;
import com.baidu.mapsdkplatform.comapi.synchronization.render.d;
import com.baidu.mapsdkplatform.comapi.synchronization.render.e;

/* JADX INFO: loaded from: classes.dex */
public class a implements k, d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3641a = a.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private l f3642b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private e f3643c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private SynchronizationDisplayListener f3644d;

    public a(Context context, BaiduMap baiduMap, RoleOptions roleOptions, DisplayOptions displayOptions) {
        this.f3642b = null;
        this.f3643c = null;
        if (context == null) {
            throw new IllegalArgumentException("BDMapSDKException: Context invalid, please check!");
        }
        if (baiduMap == null || !(baiduMap instanceof BaiduMap)) {
            throw new IllegalArgumentException("BDMapSDKException: BaiduMap is null or invalid, please check!");
        }
        if (!b(roleOptions)) {
            throw new IllegalArgumentException("BDMapSDKException: RoleOptions is invalid, please check!");
        }
        this.f3642b = new l(roleOptions, displayOptions);
        this.f3642b.a(this);
        this.f3643c = new e(context, baiduMap);
        this.f3643c.a(this);
    }

    private boolean a(LatLng latLng, RoleOptions roleOptions) {
        double d2;
        if (latLng == null) {
            return false;
        }
        double d3 = -180.0d;
        double d4 = 180.0d;
        double d5 = -90.0d;
        double d6 = 90.0d;
        double d7 = 0.0d;
        if (SyncCoordinateConverter.CoordType.COMMON == roleOptions.getCoordType()) {
            SyncCoordinateConverter syncCoordinateConverter = new SyncCoordinateConverter();
            LatLng latLngConvert = syncCoordinateConverter.from(roleOptions.getCoordType()).coord(new LatLng(-90.0d, -180.0d)).convert();
            double d8 = latLngConvert.longitude;
            double d9 = latLngConvert.latitude;
            LatLng latLngConvert2 = syncCoordinateConverter.from(roleOptions.getCoordType()).coord(new LatLng(90.0d, 180.0d)).convert();
            double d10 = latLngConvert2.longitude;
            double d11 = latLngConvert2.latitude;
            LatLng latLngConvert3 = syncCoordinateConverter.from(roleOptions.getCoordType()).coord(new LatLng(0.0d, 0.0d)).convert();
            d7 = latLngConvert3.longitude;
            d2 = latLngConvert3.latitude;
            d4 = d10;
            d6 = d11;
            d3 = d8;
            d5 = d9;
        } else {
            d2 = 0.0d;
        }
        return !(Double.valueOf(d7).compareTo(Double.valueOf(latLng.longitude)) == 0 && Double.valueOf(d2).compareTo(Double.valueOf(latLng.latitude)) == 0) && latLng.longitude >= d3 && latLng.longitude <= d4 && latLng.latitude >= d5 && latLng.latitude <= d6;
    }

    private boolean a(SyncCoordinateConverter.CoordType coordType) {
        return SyncCoordinateConverter.CoordType.BD09LL == coordType || SyncCoordinateConverter.CoordType.COMMON == coordType;
    }

    private boolean b(RoleOptions roleOptions) {
        if (roleOptions != null && roleOptions.getOrderId() != null && !roleOptions.getOrderId().equals("") && roleOptions.getRoleType() == 0 && roleOptions.getDriverId() != null && !roleOptions.getDriverId().equals("") && roleOptions.getUserId() != null && !roleOptions.getUserId().equals("") && a(roleOptions.getCoordType()) && a(roleOptions.getStartPosition(), roleOptions)) {
            return true;
        }
        if (roleOptions == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3641a, "The roleOptions is null");
            return false;
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3641a, "The roleOptions content is: OrderId = " + roleOptions.getOrderId() + "; DriverId = " + roleOptions.getDriverId() + "; UserId = " + roleOptions.getUserId() + "; StartPosition = " + roleOptions.getStartPosition() + "; EndPosition = " + roleOptions.getEndPosition() + "; DriverPosition = " + roleOptions.getDriverPosition() + "; CoordType = " + roleOptions.getCoordType());
        return false;
    }

    private boolean e(int i) {
        return i >= 0 && i <= 5;
    }

    public void a() {
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3641a, "onResume");
        l lVar = this.f3642b;
        if (lVar != null) {
            lVar.a();
        }
        e eVar = this.f3643c;
        if (eVar != null) {
            eVar.a();
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.synchronization.data.k
    public void a(float f2, long j) {
        SynchronizationDisplayListener synchronizationDisplayListener = this.f3644d;
        if (synchronizationDisplayListener != null) {
            synchronizationDisplayListener.onRoutePlanInfoFreshFinished(f2, j);
        }
    }

    public void a(int i) {
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.c(f3641a, "The order state = " + i);
        if (!e(i)) {
            SynchronizationDisplayListener synchronizationDisplayListener = this.f3644d;
            if (synchronizationDisplayListener != null) {
                synchronizationDisplayListener.onSynchronizationProcessResult(1002, SynchronizationConstants.LBS_STATUS_MESSAGE_ORDER_STATE_INVALID);
            }
            i = 0;
        }
        e eVar = this.f3643c;
        if (eVar != null) {
            eVar.a(i);
        }
        l lVar = this.f3642b;
        if (lVar != null) {
            lVar.a(i);
        }
    }

    public void a(int i, int i2, int i3, int i4) {
        e eVar = this.f3643c;
        if (eVar != null) {
            eVar.a(i, i2, i3, i4);
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.synchronization.render.d
    public void a(int i, String str) {
        SynchronizationDisplayListener synchronizationDisplayListener = this.f3644d;
        if (synchronizationDisplayListener != null) {
            synchronizationDisplayListener.onSynchronizationProcessResult(i, str);
        }
    }

    public void a(View view) {
        l lVar = this.f3642b;
        if (lVar != null) {
            lVar.a(view);
        }
    }

    public void a(DisplayOptions displayOptions) {
        l lVar = this.f3642b;
        if (lVar == null || displayOptions == null) {
            return;
        }
        lVar.a(displayOptions);
    }

    public void a(RoleOptions roleOptions) {
        if (roleOptions == null || !b(roleOptions)) {
            SynchronizationDisplayListener synchronizationDisplayListener = this.f3644d;
            if (synchronizationDisplayListener != null) {
                synchronizationDisplayListener.onSynchronizationProcessResult(1003, SynchronizationConstants.LBS_STATUS_MESSAGE_ORDER_PARAM_INVALID);
                return;
            }
            return;
        }
        l lVar = this.f3642b;
        if (lVar != null) {
            lVar.a(roleOptions);
        }
    }

    public void a(SynchronizationDisplayListener synchronizationDisplayListener) {
        if (synchronizationDisplayListener != null) {
            this.f3644d = synchronizationDisplayListener;
        } else {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3641a, "SynchronizationDisplayListener is null, must be applied.");
            throw new IllegalArgumentException("BDMapSDKException: synchronizationDisplayListener is null");
        }
    }

    public void a(boolean z) {
        c.f3684b = z;
    }

    public void b() {
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3641a, "onPause");
        l lVar = this.f3642b;
        if (lVar != null) {
            lVar.b();
        }
        e eVar = this.f3643c;
        if (eVar != null) {
            eVar.b();
        }
    }

    public void b(int i) {
        if (i < 2) {
            i = 2;
        }
        if (i > 30) {
            i = 30;
        }
        l lVar = this.f3642b;
        if (lVar != null) {
            lVar.b(i);
        }
        e eVar = this.f3643c;
        if (eVar != null) {
            eVar.b(i);
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.synchronization.data.k
    public void b(int i, String str) {
        SynchronizationDisplayListener synchronizationDisplayListener = this.f3644d;
        if (synchronizationDisplayListener != null) {
            synchronizationDisplayListener.onSynchronizationProcessResult(i, str);
        }
    }

    public void b(View view) {
        l lVar = this.f3642b;
        if (lVar != null) {
            lVar.b(view);
        }
    }

    public void b(SynchronizationDisplayListener synchronizationDisplayListener) {
        if (this.f3644d != null) {
            this.f3644d = null;
        }
    }

    public void c() {
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3641a, "release");
        l lVar = this.f3642b;
        if (lVar != null) {
            lVar.c();
        }
        e eVar = this.f3643c;
        if (eVar != null) {
            eVar.f();
        }
        if (this.f3644d != null) {
            this.f3644d = null;
        }
    }

    public void c(int i) {
        if (i < 10) {
            i = 10;
        }
        if (i > 30) {
            i = 30;
        }
        e eVar = this.f3643c;
        if (eVar != null) {
            eVar.c(i);
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.synchronization.data.k
    public void c(int i, String str) {
        SynchronizationDisplayListener synchronizationDisplayListener = this.f3644d;
        if (synchronizationDisplayListener != null) {
            synchronizationDisplayListener.onSynchronizationProcessResult(i, str);
        }
    }

    public void c(View view) {
        l lVar = this.f3642b;
        if (lVar != null) {
            lVar.c(view);
        }
    }

    public Marker d() {
        e eVar = this.f3643c;
        if (eVar != null) {
            return eVar.c();
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3641a, "Data manager instance is null");
        return null;
    }

    public void d(int i) {
        if (i < 5) {
            i = 5;
        }
        if (i > 30) {
            i = 30;
        }
        e eVar = this.f3643c;
        if (eVar != null) {
            eVar.d(i);
        }
    }

    public Marker e() {
        e eVar = this.f3643c;
        if (eVar != null) {
            return eVar.d();
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3641a, "Data manager instance is null");
        return null;
    }

    public Marker f() {
        e eVar = this.f3643c;
        if (eVar != null) {
            return eVar.e();
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3641a, "Data manager instance is null");
        return null;
    }

    public void g() {
        e eVar = this.f3643c;
        if (eVar != null) {
            eVar.g();
        }
    }

    public boolean h() {
        return c.f3684b;
    }
}