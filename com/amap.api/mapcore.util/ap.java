package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;

/* JADX INFO: compiled from: ZoomOutGestureDetectorAbstract.java */
/* JADX INFO: loaded from: classes.dex */
public class ap extends aj {
    private static final PointF p = new PointF();
    private final a n;
    private boolean o;
    private PointF q;
    private PointF r;
    private PointF s;
    private PointF t;

    /* JADX INFO: compiled from: ZoomOutGestureDetectorAbstract.java */
    public interface a {
        void a(ap apVar);

        boolean b(ap apVar);
    }

    /* JADX INFO: compiled from: ZoomOutGestureDetectorAbstract.java */
    public static class b implements a {
        @Override // com.amap.api.mapcore.util.ap.a
        public void a(ap apVar) {
        }

        @Override // com.amap.api.mapcore.util.ap.a
        public boolean b(ap apVar) {
            return true;
        }
    }

    public ap(Context context, a aVar) {
        super(context);
        this.s = new PointF();
        this.t = new PointF();
        this.n = aVar;
    }

    @Override // com.amap.api.mapcore.util.ak
    protected void a(int i, MotionEvent motionEvent, int i2, int i3) {
        if (i != 5) {
            return;
        }
        a();
        this.f143g = MotionEvent.obtain(motionEvent);
        this.k = 0L;
        a(motionEvent);
        this.o = c(motionEvent, i2, i3);
        if (this.o) {
            return;
        }
        this.f142f = this.n.b(this);
    }

    @Override // com.amap.api.mapcore.util.ak
    protected void a(int i, MotionEvent motionEvent) {
        if (i == 3) {
            a();
        } else {
            if (i != 6) {
                return;
            }
            a(motionEvent);
            if (!this.o) {
                this.n.a(this);
            }
            a();
        }
    }

    @Override // com.amap.api.mapcore.util.ak
    protected void a() {
        super.a();
        this.o = false;
        PointF pointF = this.s;
        pointF.x = 0.0f;
        PointF pointF2 = this.t;
        pointF2.x = 0.0f;
        pointF.y = 0.0f;
        pointF2.y = 0.0f;
    }

    @Override // com.amap.api.mapcore.util.aj, com.amap.api.mapcore.util.ak
    protected void a(MotionEvent motionEvent) {
        super.a(motionEvent);
        MotionEvent motionEvent2 = this.f143g;
        this.q = b(motionEvent);
        this.r = b(motionEvent2);
        this.t = this.f143g.getPointerCount() != motionEvent.getPointerCount() ? p : new PointF(this.q.x - this.r.x, this.q.y - this.r.y);
        this.s.x += this.t.x;
        this.s.y += this.t.y;
    }

    public float d() {
        return this.s.x;
    }

    public float e() {
        return this.s.y;
    }
}