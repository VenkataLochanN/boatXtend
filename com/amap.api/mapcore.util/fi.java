package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amap.api.maps.model.LatLng;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;

/* JADX INFO: compiled from: LocationView.java */
/* JADX INFO: loaded from: classes.dex */
public class fi extends LinearLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Bitmap f859a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Bitmap f860b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Bitmap f861c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    Bitmap f862d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    Bitmap f863e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    Bitmap f864f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    ImageView f865g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    IAMapDelegate f866h;
    boolean i;

    public void a() {
        try {
            removeAllViews();
            if (this.f859a != null) {
                er.b(this.f859a);
            }
            if (this.f860b != null) {
                er.b(this.f860b);
            }
            if (this.f860b != null) {
                er.b(this.f861c);
            }
            this.f859a = null;
            this.f860b = null;
            this.f861c = null;
            if (this.f862d != null) {
                er.b(this.f862d);
                this.f862d = null;
            }
            if (this.f863e != null) {
                er.b(this.f863e);
                this.f863e = null;
            }
            if (this.f864f != null) {
                er.b(this.f864f);
                this.f864f = null;
            }
        } catch (Throwable th) {
            hn.c(th, "LocationView", "destroy");
            th.printStackTrace();
        }
    }

    public fi(Context context, IAMapDelegate iAMapDelegate) {
        super(context);
        this.i = false;
        this.f866h = iAMapDelegate;
        try {
            this.f862d = er.a(context, "location_selected.png");
            this.f859a = er.a(this.f862d, m.f1683a);
            this.f863e = er.a(context, "location_pressed.png");
            this.f860b = er.a(this.f863e, m.f1683a);
            this.f864f = er.a(context, "location_unselected.png");
            this.f861c = er.a(this.f864f, m.f1683a);
            this.f865g = new ImageView(context);
            this.f865g.setImageBitmap(this.f859a);
            this.f865g.setClickable(true);
            this.f865g.setPadding(0, 20, 20, 0);
            this.f865g.setOnTouchListener(new View.OnTouchListener() { // from class: com.amap.api.mapcore.util.fi.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (!fi.this.i) {
                        return false;
                    }
                    if (motionEvent.getAction() == 0) {
                        fi.this.f865g.setImageBitmap(fi.this.f860b);
                    } else if (motionEvent.getAction() == 1) {
                        try {
                            fi.this.f865g.setImageBitmap(fi.this.f859a);
                            fi.this.f866h.setMyLocationEnabled(true);
                            Location myLocation = fi.this.f866h.getMyLocation();
                            if (myLocation == null) {
                                return false;
                            }
                            LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                            fi.this.f866h.showMyLocationOverlay(myLocation);
                            fi.this.f866h.moveCamera(ah.a(latLng, fi.this.f866h.getZoomLevel()));
                        } catch (Throwable th) {
                            hn.c(th, "LocationView", "onTouch");
                            th.printStackTrace();
                        }
                    }
                    return false;
                }
            });
            addView(this.f865g);
        } catch (Throwable th) {
            hn.c(th, "LocationView", "create");
            th.printStackTrace();
        }
    }

    public void a(boolean z) {
        this.i = z;
        try {
            if (z) {
                this.f865g.setImageBitmap(this.f859a);
            } else {
                this.f865g.setImageBitmap(this.f861c);
            }
            this.f865g.invalidate();
        } catch (Throwable th) {
            hn.c(th, "LocationView", "showSelect");
            th.printStackTrace();
        }
    }
}