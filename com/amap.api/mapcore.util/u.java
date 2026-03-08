package com.amap.api.mapcore.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.autonavi.amap.mapcore.interfaces.IAMap;
import com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate;

/* JADX INFO: compiled from: MapFragmentDelegateImp.java */
/* JADX INFO: loaded from: classes.dex */
public class u implements IMapFragmentDelegate {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile Context f1791a;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static String f1792f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1793b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f1794c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private IAMap f1795d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1796e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private AMapOptions f1797g;

    @Override // com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate
    public boolean isReady() throws RemoteException {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate
    public void onCreate(Bundle bundle) throws RemoteException {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate
    public void onDestroyView() throws RemoteException {
    }

    public u(int i) {
        this.f1796e = 0;
        this.f1796e = i % 3;
        b();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate
    public void setContext(Context context) {
        a(context);
    }

    private void a(Context context) {
        if (context != null) {
            f1791a = context.getApplicationContext();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate
    public void setOptions(AMapOptions aMapOptions) {
        this.f1797g = aMapOptions;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate
    public IAMap getMap() throws RemoteException {
        if (this.f1795d == null) {
            if (f1791a == null) {
                Log.w("MapFragmentDelegateImp", "Context 为 null 请在地图调用之前 使用 MapsInitializer.initialize(Context paramContext) 来设置Context");
                return null;
            }
            int i = f1791a.getResources().getDisplayMetrics().densityDpi;
            if (i <= 120) {
                m.f1683a = 0.5f;
            } else if (i <= 160) {
                m.f1683a = 0.8f;
            } else if (i <= 240) {
                m.f1683a = 0.87f;
            } else if (i <= 320) {
                m.f1683a = 1.0f;
            } else if (i <= 480) {
                m.f1683a = 1.5f;
            } else if (i <= 640) {
                m.f1683a = 1.8f;
            } else {
                m.f1683a = 0.9f;
            }
            int i2 = this.f1796e;
            if (i2 == 0) {
                this.f1795d = new e(f1791a, this.f1794c).a();
            } else if (i2 == 1) {
                this.f1795d = new f(f1791a, this.f1794c).a();
            } else {
                this.f1795d = new d(f1791a).a();
            }
        }
        return this.f1795d;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate
    public void onInflate(Activity activity, AMapOptions aMapOptions, Bundle bundle) throws RemoteException {
        setContext(activity.getApplicationContext());
        this.f1797g = aMapOptions;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) throws RemoteException {
        byte[] byteArray;
        if (f1791a == null && layoutInflater != null) {
            setContext(layoutInflater.getContext().getApplicationContext());
        }
        try {
            this.f1795d = getMap();
            this.f1795d.setVisibilityEx(this.f1793b);
            if (this.f1797g == null && bundle != null && (byteArray = bundle.getByteArray("MAP_OPTIONS")) != null) {
                Parcel parcelObtain = Parcel.obtain();
                parcelObtain.unmarshall(byteArray, 0, byteArray.length);
                parcelObtain.setDataPosition(0);
                this.f1797g = AMapOptions.CREATOR.createFromParcel(parcelObtain);
            }
            a(this.f1797g);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.f1795d.getView();
    }

    void a(AMapOptions aMapOptions) throws RemoteException {
        if (aMapOptions == null || this.f1795d == null) {
            return;
        }
        CameraPosition camera = aMapOptions.getCamera();
        if (camera != null) {
            this.f1795d.moveCamera(CameraUpdateFactory.newCameraPosition(camera));
        }
        UiSettings aMapUiSettings = this.f1795d.getAMapUiSettings();
        aMapUiSettings.setRotateGesturesEnabled(aMapOptions.getRotateGesturesEnabled());
        aMapUiSettings.setScrollGesturesEnabled(aMapOptions.getScrollGesturesEnabled());
        aMapUiSettings.setTiltGesturesEnabled(aMapOptions.getTiltGesturesEnabled());
        aMapUiSettings.setZoomControlsEnabled(aMapOptions.getZoomControlsEnabled());
        aMapUiSettings.setZoomGesturesEnabled(aMapOptions.getZoomGesturesEnabled());
        aMapUiSettings.setCompassEnabled(aMapOptions.getCompassEnabled());
        aMapUiSettings.setScaleControlsEnabled(aMapOptions.getScaleControlsEnabled());
        aMapUiSettings.setLogoPosition(aMapOptions.getLogoPosition());
        this.f1795d.setMapType(aMapOptions.getMapType());
        this.f1795d.setZOrderOnTop(aMapOptions.getZOrderOnTop());
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate
    public void onResume() throws RemoteException {
        IAMap iAMap = this.f1795d;
        if (iAMap != null) {
            iAMap.onActivityResume();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate
    public void onPause() throws RemoteException {
        IAMap iAMap = this.f1795d;
        if (iAMap != null) {
            iAMap.onActivityPause();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate
    public void onDestroy() throws RemoteException {
        a();
        IAMap iAMap = this.f1795d;
        if (iAMap != null) {
            iAMap.clear();
            this.f1795d.destroy();
            this.f1795d = null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate
    public void onLowMemory() throws RemoteException {
        Log.d("onLowMemory", "onLowMemory run");
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate
    public void onSaveInstanceState(Bundle bundle) throws RemoteException {
        if (this.f1795d != null) {
            if (this.f1797g == null) {
                this.f1797g = new AMapOptions();
            }
            try {
                Parcel parcelObtain = Parcel.obtain();
                this.f1797g = this.f1797g.camera(getMap().getCameraPosition());
                this.f1797g.writeToParcel(parcelObtain, 0);
                bundle.putByteArray("MAP_OPTIONS", parcelObtain.marshall());
            } catch (Throwable unused) {
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate
    public void setVisibility(int i) {
        this.f1793b = i;
        IAMap iAMap = this.f1795d;
        if (iAMap != null) {
            iAMap.setVisibilityEx(i);
        }
    }

    private void a() {
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            boolean z = false;
            boolean z2 = false;
            boolean z3 = false;
            for (int i = 0; i < stackTrace.length; i++) {
                if (stackTrace[i].getClassName() != null && stackTrace[i].getClassName().endsWith("TextureMapView")) {
                    z2 = true;
                }
                if (stackTrace[i].getClassName() != null && stackTrace[i].getClassName().endsWith("Fragment")) {
                    z = true;
                }
                if ("OnDestroyView".equalsIgnoreCase(stackTrace[i].getMethodName())) {
                    z3 = true;
                }
            }
            if (z && z2 && !z3) {
                c();
            }
        } catch (Throwable unused) {
        }
    }

    private void b() {
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 80; i++) {
                sb.append("=");
            }
            f1792f = sb.toString();
        } catch (Throwable unused) {
        }
    }

    private void c() {
        Log.i("errorLog", f1792f);
        Log.i("errorLog", "OnDestroy 方法需要在OnDestroyView中调用");
        Log.i("errorLog", f1792f);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate
    public void loadWorldVectorMap(boolean z) {
        this.f1794c = z;
        IAMap iAMap = this.f1795d;
        if (iAMap != null) {
            iAMap.loadWorldVectorMap(z);
        }
    }
}