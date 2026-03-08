package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amap.api.maps.model.CameraPosition;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;

/* JADX INFO: compiled from: CompassView.java */
/* JADX INFO: loaded from: classes.dex */
public class fg extends LinearLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Bitmap f834a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Bitmap f835b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Bitmap f836c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ImageView f837d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    IAMapDelegate f838e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    Matrix f839f;

    public void a() {
        try {
            removeAllViews();
            if (this.f834a != null) {
                er.b(this.f834a);
            }
            if (this.f835b != null) {
                er.b(this.f835b);
            }
            if (this.f836c != null) {
                er.b(this.f836c);
            }
            if (this.f839f != null) {
                this.f839f.reset();
                this.f839f = null;
            }
            this.f836c = null;
            this.f834a = null;
            this.f835b = null;
        } catch (Throwable th) {
            hn.c(th, "CompassView", "destroy");
            th.printStackTrace();
        }
    }

    public fg(Context context, IAMapDelegate iAMapDelegate) {
        super(context);
        this.f839f = new Matrix();
        this.f838e = iAMapDelegate;
        try {
            this.f836c = er.a(context, "maps_dav_compass_needle_large.png");
            this.f835b = er.a(this.f836c, m.f1683a * 0.8f);
            this.f836c = er.a(this.f836c, m.f1683a * 0.7f);
            if (this.f835b != null && this.f836c != null) {
                this.f834a = Bitmap.createBitmap(this.f835b.getWidth(), this.f835b.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(this.f834a);
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setFilterBitmap(true);
                canvas.drawBitmap(this.f836c, (this.f835b.getWidth() - this.f836c.getWidth()) / 2.0f, (this.f835b.getHeight() - this.f836c.getHeight()) / 2.0f, paint);
                this.f837d = new ImageView(context);
                this.f837d.setScaleType(ImageView.ScaleType.MATRIX);
                this.f837d.setImageBitmap(this.f834a);
                this.f837d.setClickable(true);
                b();
                this.f837d.setOnTouchListener(new View.OnTouchListener() { // from class: com.amap.api.mapcore.util.fg.1
                    @Override // android.view.View.OnTouchListener
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        try {
                            if (!fg.this.f838e.isMaploaded()) {
                                return false;
                            }
                            if (motionEvent.getAction() == 0) {
                                fg.this.f837d.setImageBitmap(fg.this.f835b);
                            } else if (motionEvent.getAction() == 1) {
                                fg.this.f837d.setImageBitmap(fg.this.f834a);
                                CameraPosition cameraPosition = fg.this.f838e.getCameraPosition();
                                fg.this.f838e.animateCamera(ah.a(new CameraPosition(cameraPosition.target, cameraPosition.zoom, 0.0f, 0.0f)));
                            }
                        } catch (Throwable th) {
                            hn.c(th, "CompassView", "onTouch");
                            th.printStackTrace();
                        }
                        return false;
                    }
                });
                addView(this.f837d);
            }
        } catch (Throwable th) {
            hn.c(th, "CompassView", "create");
            th.printStackTrace();
        }
    }

    public void b() {
        try {
            if (this.f838e == null || this.f837d == null) {
                return;
            }
            float cameraDegree = this.f838e.getCameraDegree(1);
            float mapAngle = this.f838e.getMapAngle(1);
            if (this.f839f == null) {
                this.f839f = new Matrix();
            }
            this.f839f.reset();
            this.f839f.postRotate(-mapAngle, this.f837d.getDrawable().getBounds().width() / 2.0f, this.f837d.getDrawable().getBounds().height() / 2.0f);
            this.f839f.postScale(1.0f, (float) Math.cos((((double) cameraDegree) * 3.141592653589793d) / 180.0d), this.f837d.getDrawable().getBounds().width() / 2.0f, this.f837d.getDrawable().getBounds().height() / 2.0f);
            this.f837d.setImageMatrix(this.f839f);
        } catch (Throwable th) {
            hn.c(th, "CompassView", "invalidateAngle");
            th.printStackTrace();
        }
    }

    public void a(boolean z) {
        if (z) {
            setVisibility(0);
            b();
        } else {
            setVisibility(8);
        }
    }
}