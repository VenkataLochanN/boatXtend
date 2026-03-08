package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.amap.api.maps.AMap;
import com.amap.api.maps.InfoWindowParams;
import com.amap.api.maps.model.BasePointOverlay;
import com.amap.api.maps.model.Marker;
import com.autonavi.base.amap.api.mapcore.BaseOverlayImp;
import com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction;

/* JADX INFO: compiled from: InfoWindowDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class ar {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Context f167c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private View f169e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private TextView f170f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private TextView f171g;
    private IInfoWindowAction i;
    private IInfoWindowAction j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    AMap.InfoWindowAdapter f165a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    AMap.CommonInfoWindowAdapter f166b = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f168d = true;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Drawable f172h = null;
    private AMap.InfoWindowAdapter k = new AMap.InfoWindowAdapter() { // from class: com.amap.api.mapcore.util.ar.1
        @Override // com.amap.api.maps.AMap.InfoWindowAdapter
        public View getInfoContents(Marker marker) {
            return null;
        }

        @Override // com.amap.api.maps.AMap.InfoWindowAdapter
        public View getInfoWindow(Marker marker) {
            try {
                if (ar.this.f172h == null) {
                    ar.this.f172h = eg.a(ar.this.f167c, "infowindow_bg.9.png");
                }
                if (ar.this.f169e == null) {
                    ar.this.f169e = new LinearLayout(ar.this.f167c);
                    ar.this.f169e.setBackground(ar.this.f172h);
                    ar.this.f170f = new TextView(ar.this.f167c);
                    ar.this.f170f.setText(marker.getTitle());
                    ar.this.f170f.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    ar.this.f171g = new TextView(ar.this.f167c);
                    ar.this.f171g.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    ar.this.f171g.setText(marker.getSnippet());
                    ((LinearLayout) ar.this.f169e).setOrientation(1);
                    ((LinearLayout) ar.this.f169e).addView(ar.this.f170f);
                    ((LinearLayout) ar.this.f169e).addView(ar.this.f171g);
                }
            } catch (Throwable th) {
                hn.c(th, "InfoWindowDelegate", "showInfoWindow decodeDrawableFromAsset");
                th.printStackTrace();
            }
            return ar.this.f169e;
        }
    };
    private AMap.CommonInfoWindowAdapter l = new AMap.CommonInfoWindowAdapter() { // from class: com.amap.api.mapcore.util.ar.2

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private InfoWindowParams f175b = null;

        @Override // com.amap.api.maps.AMap.CommonInfoWindowAdapter
        public InfoWindowParams getInfoWindowParams(BasePointOverlay basePointOverlay) {
            try {
                if (this.f175b == null) {
                    this.f175b = new InfoWindowParams();
                    if (ar.this.f172h == null) {
                        ar.this.f172h = eg.a(ar.this.f167c, "infowindow_bg.9.png");
                    }
                    ar.this.f169e = new LinearLayout(ar.this.f167c);
                    ar.this.f169e.setBackground(ar.this.f172h);
                    ar.this.f170f = new TextView(ar.this.f167c);
                    ar.this.f170f.setText("标题");
                    ar.this.f170f.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    ar.this.f171g = new TextView(ar.this.f167c);
                    ar.this.f171g.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    ar.this.f171g.setText("内容");
                    ((LinearLayout) ar.this.f169e).setOrientation(1);
                    ((LinearLayout) ar.this.f169e).addView(ar.this.f170f);
                    ((LinearLayout) ar.this.f169e).addView(ar.this.f171g);
                    this.f175b.setInfoWindowType(2);
                    this.f175b.setInfoWindow(ar.this.f169e);
                }
                return this.f175b;
            } catch (Throwable th) {
                hn.c(th, "InfoWindowDelegate", "showInfoWindow decodeDrawableFromAsset");
                th.printStackTrace();
                return null;
            }
        }
    };

    public ar(Context context) {
        this.f167c = context;
    }

    public void a(IInfoWindowAction iInfoWindowAction) {
        synchronized (this) {
            this.i = iInfoWindowAction;
            if (this.i != null) {
                this.i.setInfoWindowAdapterManager(this);
            }
        }
    }

    public void b(IInfoWindowAction iInfoWindowAction) {
        synchronized (this) {
            this.j = iInfoWindowAction;
            if (this.j != null) {
                this.j.setInfoWindowAdapterManager(this);
            }
        }
    }

    public synchronized boolean a() {
        return this.f168d;
    }

    public void a(String str, String str2) {
        TextView textView = this.f170f;
        if (textView != null) {
            textView.requestLayout();
            this.f170f.setText(str);
        }
        TextView textView2 = this.f171g;
        if (textView2 != null) {
            textView2.requestLayout();
            this.f171g.setText(str2);
        }
        View view = this.f169e;
        if (view != null) {
            view.requestLayout();
        }
    }

    public synchronized void a(AMap.InfoWindowAdapter infoWindowAdapter) {
        this.f165a = infoWindowAdapter;
        this.f166b = null;
        if (this.f165a == null) {
            this.f165a = this.k;
            this.f168d = true;
        } else {
            this.f168d = false;
        }
        if (this.j != null) {
            this.j.hideInfoWindow();
        }
        if (this.i != null) {
            this.i.hideInfoWindow();
        }
    }

    public synchronized void a(AMap.CommonInfoWindowAdapter commonInfoWindowAdapter) {
        this.f166b = commonInfoWindowAdapter;
        this.f165a = null;
        if (this.f166b == null) {
            this.f166b = this.l;
            this.f168d = true;
        } else {
            this.f168d = false;
        }
        if (this.j != null) {
            this.j.hideInfoWindow();
        }
        if (this.i != null) {
            this.i.hideInfoWindow();
        }
    }

    public void b() {
        this.f167c = null;
        this.f169e = null;
        this.f170f = null;
        this.f171g = null;
        synchronized (this) {
            er.a(this.f172h);
            this.f172h = null;
            this.k = null;
            this.f165a = null;
        }
        this.f166b = null;
        this.i = null;
        this.j = null;
    }

    private void a(View view, BasePointOverlay basePointOverlay) {
        if (view == null || basePointOverlay == null || basePointOverlay.getPosition() == null || !ea.c()) {
            return;
        }
        String strB = er.b(view);
        if (TextUtils.isEmpty(strB)) {
            return;
        }
        ea.a().a(basePointOverlay.getPosition(), strB, "");
    }

    public View a(BasePointOverlay basePointOverlay) {
        InfoWindowParams infoWindowParams;
        AMap.InfoWindowAdapter infoWindowAdapter = this.f165a;
        if (infoWindowAdapter != null) {
            View infoWindow = infoWindowAdapter.getInfoWindow((Marker) basePointOverlay);
            a(infoWindow, basePointOverlay);
            return infoWindow;
        }
        AMap.CommonInfoWindowAdapter commonInfoWindowAdapter = this.f166b;
        if (commonInfoWindowAdapter != null && (infoWindowParams = commonInfoWindowAdapter.getInfoWindowParams(basePointOverlay)) != null) {
            View infoWindow2 = infoWindowParams.getInfoWindow();
            a(infoWindow2, basePointOverlay);
            return infoWindow2;
        }
        InfoWindowParams infoWindowParams2 = this.l.getInfoWindowParams(basePointOverlay);
        if (infoWindowParams2 != null) {
            return infoWindowParams2.getInfoWindow();
        }
        return null;
    }

    public View b(BasePointOverlay basePointOverlay) {
        InfoWindowParams infoWindowParams;
        AMap.InfoWindowAdapter infoWindowAdapter = this.f165a;
        if (infoWindowAdapter != null) {
            View infoContents = infoWindowAdapter.getInfoContents((Marker) basePointOverlay);
            a(infoContents, basePointOverlay);
            return infoContents;
        }
        AMap.CommonInfoWindowAdapter commonInfoWindowAdapter = this.f166b;
        if (commonInfoWindowAdapter != null && (infoWindowParams = commonInfoWindowAdapter.getInfoWindowParams(basePointOverlay)) != null) {
            View infoContents2 = infoWindowParams.getInfoContents();
            a(infoContents2, basePointOverlay);
            return infoContents2;
        }
        InfoWindowParams infoWindowParams2 = this.l.getInfoWindowParams(basePointOverlay);
        if (infoWindowParams2 != null) {
            return infoWindowParams2.getInfoContents();
        }
        return null;
    }

    public View a(Marker marker) {
        AMap.InfoWindowAdapter infoWindowAdapter = this.f165a;
        if (infoWindowAdapter == null || !(infoWindowAdapter instanceof AMap.MultiPositionInfoWindowAdapter)) {
            return null;
        }
        return ((AMap.MultiPositionInfoWindowAdapter) infoWindowAdapter).getInfoWindowClick(marker);
    }

    public View b(Marker marker) {
        AMap.InfoWindowAdapter infoWindowAdapter = this.f165a;
        if (infoWindowAdapter == null || !(infoWindowAdapter instanceof AMap.MultiPositionInfoWindowAdapter)) {
            return null;
        }
        return ((AMap.MultiPositionInfoWindowAdapter) infoWindowAdapter).getOverturnInfoWindow(marker);
    }

    public View c(Marker marker) {
        AMap.InfoWindowAdapter infoWindowAdapter = this.f165a;
        if (infoWindowAdapter == null || !(infoWindowAdapter instanceof AMap.MultiPositionInfoWindowAdapter)) {
            return null;
        }
        return ((AMap.MultiPositionInfoWindowAdapter) infoWindowAdapter).getOverturnInfoWindowClick(marker);
    }

    public long c(BasePointOverlay basePointOverlay) {
        InfoWindowParams infoWindowParams;
        AMap.InfoWindowAdapter infoWindowAdapter = this.f165a;
        if (infoWindowAdapter != null && (infoWindowAdapter instanceof AMap.ImageInfoWindowAdapter)) {
            return ((AMap.ImageInfoWindowAdapter) infoWindowAdapter).getInfoWindowUpdateTime();
        }
        AMap.CommonInfoWindowAdapter commonInfoWindowAdapter = this.f166b;
        if (commonInfoWindowAdapter == null || (infoWindowParams = commonInfoWindowAdapter.getInfoWindowParams(basePointOverlay)) == null) {
            return 0L;
        }
        return infoWindowParams.getInfoWindowUpdateTime();
    }

    public void c() {
        IInfoWindowAction iInfoWindowActionD = d();
        if (iInfoWindowActionD != null) {
            iInfoWindowActionD.redrawInfoWindow();
        }
    }

    public synchronized IInfoWindowAction d() {
        if (this.f165a != null) {
            if (this.f165a instanceof AMap.ImageInfoWindowAdapter) {
                return this.j;
            }
            if (this.f165a instanceof AMap.MultiPositionInfoWindowAdapter) {
                return this.j;
            }
        }
        if (this.f166b != null && this.f166b.getInfoWindowParams(null).getInfoWindowType() == 1) {
            return this.j;
        }
        return this.i;
    }

    public boolean a(MotionEvent motionEvent) {
        IInfoWindowAction iInfoWindowActionD = d();
        if (iInfoWindowActionD != null) {
            return iInfoWindowActionD.onInfoWindowTap(motionEvent);
        }
        return false;
    }

    public void e() {
        IInfoWindowAction iInfoWindowActionD = d();
        if (iInfoWindowActionD != null) {
            iInfoWindowActionD.hideInfoWindow();
        }
    }

    public void a(BaseOverlayImp baseOverlayImp) throws RemoteException {
        IInfoWindowAction iInfoWindowActionD = d();
        if (iInfoWindowActionD != null) {
            iInfoWindowActionD.showInfoWindow(baseOverlayImp);
        }
    }

    public boolean f() {
        IInfoWindowAction iInfoWindowActionD = d();
        if (iInfoWindowActionD != null) {
            return iInfoWindowActionD.isInfoWindowShown();
        }
        return false;
    }

    public Drawable g() {
        if (this.f172h == null) {
            try {
                this.f172h = eg.a(this.f167c, "infowindow_bg.9.png");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return this.f172h;
    }
}