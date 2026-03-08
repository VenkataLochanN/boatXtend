package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.IUiSettingsDelegate;

/* JADX INFO: compiled from: UiSettingsDelegateImp.java */
/* JADX INFO: loaded from: classes.dex */
class ac implements IUiSettingsDelegate {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private IAMapDelegate f122b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f123c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f124d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f125e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f126f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f127g = true;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f128h = true;
    private boolean i = true;
    private boolean j = false;
    private boolean k = true;
    private int l = 0;
    private int m = 1;
    private boolean n = true;
    private boolean o = false;
    private boolean p = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Handler f121a = new Handler(Looper.getMainLooper()) { // from class: com.amap.api.mapcore.util.ac.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null || ac.this.f122b == null) {
                return;
            }
            try {
                switch (message.what) {
                    case 0:
                        ac.this.f122b.showZoomControlsEnabled(ac.this.f128h);
                        break;
                    case 1:
                        ac.this.f122b.showScaleEnabled(ac.this.j);
                        break;
                    case 2:
                        ac.this.f122b.showCompassEnabled(ac.this.i);
                        break;
                    case 3:
                        ac.this.f122b.showMyLocationButtonEnabled(ac.this.f126f);
                        break;
                    case 4:
                        ac.this.f122b.showIndoorSwitchControlsEnabled(ac.this.n);
                        break;
                    case 5:
                        ac.this.f122b.showLogoEnabled(ac.this.k);
                        break;
                    case 6:
                        ac.this.f122b.refreshLogo();
                        break;
                }
            } catch (Throwable th) {
                hn.c(th, "UiSettingsDelegateImp", "handleMessage");
            }
        }
    };

    ac(IAMapDelegate iAMapDelegate) {
        this.f122b = iAMapDelegate;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public boolean isIndoorSwitchEnabled() throws RemoteException {
        return this.n;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public void setIndoorSwitchEnabled(boolean z) throws RemoteException {
        this.n = z;
        this.f121a.obtainMessage(4).sendToTarget();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public void setScaleControlsEnabled(boolean z) throws RemoteException {
        this.j = z;
        this.f121a.obtainMessage(1).sendToTarget();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public void setZoomControlsEnabled(boolean z) throws RemoteException {
        this.f128h = z;
        this.f121a.obtainMessage(0).sendToTarget();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public void setCompassEnabled(boolean z) throws RemoteException {
        this.i = z;
        this.f121a.obtainMessage(2).sendToTarget();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public void setMyLocationButtonEnabled(boolean z) throws RemoteException {
        this.f126f = z;
        this.f121a.obtainMessage(3).sendToTarget();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public void setScrollGesturesEnabled(boolean z) throws RemoteException {
        this.f124d = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public void setZoomGesturesEnabled(boolean z) throws RemoteException {
        this.f127g = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public void setTiltGesturesEnabled(boolean z) throws RemoteException {
        this.f125e = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public void setRotateGesturesEnabled(boolean z) throws RemoteException {
        this.f123c = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public void setAllGesturesEnabled(boolean z) throws RemoteException {
        setRotateGesturesEnabled(z);
        setTiltGesturesEnabled(z);
        setZoomGesturesEnabled(z);
        setScrollGesturesEnabled(z);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public void setLogoPosition(int i) throws RemoteException {
        this.l = i;
        this.f122b.setLogoPosition(i);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public void setZoomPosition(int i) throws RemoteException {
        this.m = i;
        this.f122b.setZoomPosition(i);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public boolean isScaleControlsEnabled() throws RemoteException {
        return this.j;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public boolean isZoomControlsEnabled() throws RemoteException {
        return this.f128h;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public boolean isCompassEnabled() throws RemoteException {
        return this.i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public boolean isMyLocationButtonEnabled() throws RemoteException {
        return this.f126f;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public boolean isScrollGesturesEnabled() throws RemoteException {
        return this.f124d;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public boolean isZoomGesturesEnabled() throws RemoteException {
        return this.f127g;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public boolean isTiltGesturesEnabled() throws RemoteException {
        return this.f125e;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public boolean isRotateGesturesEnabled() throws RemoteException {
        return this.f123c;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public int getLogoPosition() throws RemoteException {
        return this.l;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public int getZoomPosition() throws RemoteException {
        return this.m;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public void setZoomInByScreenCenter(boolean z) {
        this.o = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public boolean isZoomInByScreenCenter() {
        return this.o;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public void setLogoBottomMargin(int i) {
        this.f122b.setLogoBottomMargin(i);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public void setLogoLeftMargin(int i) {
        this.f122b.setLogoLeftMargin(i);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public float getLogoMarginRate(int i) {
        return this.f122b.getLogoMarginRate(i);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public void setLogoMarginRate(int i, float f2) {
        this.f122b.setLogoMarginRate(i, f2);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public void setGestureScaleByMapCenter(boolean z) throws RemoteException {
        this.p = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public boolean isGestureScaleByMapCenter() throws RemoteException {
        return this.p;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public void setLogoEnable(boolean z) {
        this.k = z;
        this.f121a.obtainMessage(5).sendToTarget();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public void requestRefreshLogo() {
        this.f121a.obtainMessage(6).sendToTarget();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IUiSettings
    public boolean isLogoEnable() {
        return this.k;
    }
}