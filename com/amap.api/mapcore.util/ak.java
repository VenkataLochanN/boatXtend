package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.PointF;
import android.os.Build;
import android.view.MotionEvent;

/* JADX INFO: compiled from: BaseGestureDetector.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class ak {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected final Context f141e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected boolean f142f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected MotionEvent f143g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected MotionEvent f144h;
    protected float i;
    protected float j;
    protected long k;
    protected int l = 0;
    protected int m = 0;

    protected abstract void a(int i, MotionEvent motionEvent);

    protected abstract void a(int i, MotionEvent motionEvent, int i2, int i3);

    public void a(int i, int i2) {
        this.l = i;
        this.m = i2;
    }

    public ak(Context context) {
        this.f141e = context;
    }

    public boolean d(MotionEvent motionEvent, int i, int i2) {
        int action = motionEvent.getAction() & 255;
        if (!this.f142f) {
            a(action, motionEvent, i, i2);
            return true;
        }
        a(action, motionEvent);
        return true;
    }

    protected void a(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = this.f143g;
        MotionEvent motionEvent3 = this.f144h;
        if (motionEvent3 != null) {
            motionEvent3.recycle();
            this.f144h = null;
        }
        this.f144h = MotionEvent.obtain(motionEvent);
        this.k = motionEvent.getEventTime() - motionEvent2.getEventTime();
        if (Build.VERSION.SDK_INT >= 8) {
            this.i = motionEvent.getPressure(motionEvent.getActionIndex());
            this.j = motionEvent2.getPressure(motionEvent2.getActionIndex());
        } else {
            this.i = motionEvent.getPressure(0);
            this.j = motionEvent2.getPressure(0);
        }
    }

    protected void a() {
        MotionEvent motionEvent = this.f143g;
        if (motionEvent != null) {
            motionEvent.recycle();
            this.f143g = null;
        }
        MotionEvent motionEvent2 = this.f144h;
        if (motionEvent2 != null) {
            motionEvent2.recycle();
            this.f144h = null;
        }
        this.f142f = false;
    }

    public long b() {
        return this.k;
    }

    public static PointF b(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        float x = 0.0f;
        float y = 0.0f;
        for (int i = 0; i < pointerCount; i++) {
            x += motionEvent.getX(i);
            y += motionEvent.getY(i);
        }
        float f2 = pointerCount;
        return new PointF(x / f2, y / f2);
    }

    public MotionEvent c() {
        return this.f144h;
    }
}