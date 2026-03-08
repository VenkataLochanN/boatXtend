package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amap.api.mapcore.util.fj;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;

/* JADX INFO: compiled from: ZoomControllerView.java */
/* JADX INFO: loaded from: classes.dex */
class fn extends LinearLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Bitmap f910a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Bitmap f911b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Bitmap f912c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Bitmap f913d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Bitmap f914e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Bitmap f915f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Bitmap f916g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Bitmap f917h;
    private Bitmap i;
    private Bitmap j;
    private Bitmap k;
    private Bitmap l;
    private ImageView m;
    private ImageView n;
    private IAMapDelegate o;

    public void a() {
        try {
            removeAllViews();
            er.b(this.f910a);
            er.b(this.f911b);
            er.b(this.f912c);
            er.b(this.f913d);
            er.b(this.f914e);
            er.b(this.f915f);
            this.f910a = null;
            this.f911b = null;
            this.f912c = null;
            this.f913d = null;
            this.f914e = null;
            this.f915f = null;
            if (this.f916g != null) {
                er.b(this.f916g);
                this.f916g = null;
            }
            if (this.f917h != null) {
                er.b(this.f917h);
                this.f917h = null;
            }
            if (this.i != null) {
                er.b(this.i);
                this.i = null;
            }
            if (this.j != null) {
                er.b(this.j);
                this.f916g = null;
            }
            if (this.k != null) {
                er.b(this.k);
                this.k = null;
            }
            if (this.l != null) {
                er.b(this.l);
                this.l = null;
            }
            this.m = null;
            this.n = null;
        } catch (Throwable th) {
            hn.c(th, "ZoomControllerView", "destory");
            th.printStackTrace();
        }
    }

    public fn(Context context, IAMapDelegate iAMapDelegate) {
        super(context);
        this.o = iAMapDelegate;
        try {
            this.f916g = er.a(context, "zoomin_selected.png");
            this.f910a = er.a(this.f916g, m.f1683a);
            this.f917h = er.a(context, "zoomin_unselected.png");
            this.f911b = er.a(this.f917h, m.f1683a);
            this.i = er.a(context, "zoomout_selected.png");
            this.f912c = er.a(this.i, m.f1683a);
            this.j = er.a(context, "zoomout_unselected.png");
            this.f913d = er.a(this.j, m.f1683a);
            this.k = er.a(context, "zoomin_pressed.png");
            this.f914e = er.a(this.k, m.f1683a);
            this.l = er.a(context, "zoomout_pressed.png");
            this.f915f = er.a(this.l, m.f1683a);
            this.m = new ImageView(context);
            this.m.setImageBitmap(this.f910a);
            this.m.setClickable(true);
            this.n = new ImageView(context);
            this.n.setImageBitmap(this.f912c);
            this.n.setClickable(true);
            this.m.setOnTouchListener(new View.OnTouchListener() { // from class: com.amap.api.mapcore.util.fn.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    try {
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    if (fn.this.o.getZoomLevel() < fn.this.o.getMaxZoomLevel() && fn.this.o.isMaploaded()) {
                        if (motionEvent.getAction() == 0) {
                            fn.this.m.setImageBitmap(fn.this.f914e);
                        } else if (motionEvent.getAction() == 1) {
                            fn.this.m.setImageBitmap(fn.this.f910a);
                            try {
                                fn.this.o.animateCamera(ah.a());
                            } catch (RemoteException e2) {
                                hn.c(e2, "ZoomControllerView", "zoomin ontouch");
                                e2.printStackTrace();
                            }
                        }
                        return false;
                    }
                    return false;
                }
            });
            this.n.setOnTouchListener(new View.OnTouchListener() { // from class: com.amap.api.mapcore.util.fn.2
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    try {
                    } catch (Throwable th) {
                        hn.c(th, "ZoomControllerView", "zoomout ontouch");
                        th.printStackTrace();
                    }
                    if (fn.this.o.getZoomLevel() > fn.this.o.getMinZoomLevel() && fn.this.o.isMaploaded()) {
                        if (motionEvent.getAction() == 0) {
                            fn.this.n.setImageBitmap(fn.this.f915f);
                        } else if (motionEvent.getAction() == 1) {
                            fn.this.n.setImageBitmap(fn.this.f912c);
                            fn.this.o.animateCamera(ah.b());
                        }
                        return false;
                    }
                    return false;
                }
            });
            this.m.setPadding(0, 0, 20, -2);
            this.n.setPadding(0, 0, 20, 20);
            setOrientation(1);
            addView(this.m);
            addView(this.n);
        } catch (Throwable th) {
            hn.c(th, "ZoomControllerView", "create");
            th.printStackTrace();
        }
    }

    public void a(float f2) {
        try {
            if (f2 < this.o.getMaxZoomLevel() && f2 > this.o.getMinZoomLevel()) {
                this.m.setImageBitmap(this.f910a);
                this.n.setImageBitmap(this.f912c);
            } else if (f2 == this.o.getMinZoomLevel()) {
                this.n.setImageBitmap(this.f913d);
                this.m.setImageBitmap(this.f910a);
            } else if (f2 == this.o.getMaxZoomLevel()) {
                this.m.setImageBitmap(this.f911b);
                this.n.setImageBitmap(this.f912c);
            }
        } catch (Throwable th) {
            hn.c(th, "ZoomControllerView", "setZoomBitmap");
            th.printStackTrace();
        }
    }

    public void a(int i) {
        try {
            fj.a aVar = (fj.a) getLayoutParams();
            if (i == 1) {
                aVar.f886e = 16;
            } else if (i == 2) {
                aVar.f886e = 80;
            }
            setLayoutParams(aVar);
        } catch (Throwable th) {
            hn.c(th, "ZoomControllerView", "setZoomPosition");
            th.printStackTrace();
        }
    }

    public void a(boolean z) {
        if (z) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }
}