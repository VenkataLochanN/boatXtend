package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;

/* JADX INFO: compiled from: HoverGestureDetectorAbstract.java */
/* JADX INFO: loaded from: classes.dex */
public class al extends aj {
    private static final PointF n = new PointF();
    private final a o;
    private boolean p;
    private PointF q;
    private PointF r;
    private PointF s;
    private PointF t;

    /* JADX INFO: compiled from: HoverGestureDetectorAbstract.java */
    public interface a {
        boolean a(al alVar);

        boolean b(al alVar);

        void c(al alVar);
    }

    public al(Context context, a aVar) {
        super(context);
        this.s = new PointF();
        this.t = new PointF();
        this.o = aVar;
    }

    @Override // com.amap.api.mapcore.util.ak
    protected void a(int i, MotionEvent motionEvent, int i2, int i3) {
        if (i == 2) {
            if (this.p) {
                this.p = c(motionEvent, i2, i3);
                if (this.p) {
                    return;
                }
                this.f142f = this.o.b(this);
                return;
            }
            return;
        }
        if (i != 5) {
            if (i != 6) {
                return;
            }
            boolean z = this.p;
            return;
        }
        a();
        this.f143g = MotionEvent.obtain(motionEvent);
        this.k = 0L;
        a(motionEvent);
        this.p = c(motionEvent, i2, i3);
        if (this.p) {
            return;
        }
        this.f142f = this.o.b(this);
    }

    @Override // com.amap.api.mapcore.util.ak
    protected void a(int i, MotionEvent motionEvent) {
        if (i == 2) {
            a(motionEvent);
            if (this.i / this.j <= 0.67f || !this.o.a(this)) {
                return;
            }
            this.f143g.recycle();
            this.f143g = MotionEvent.obtain(motionEvent);
            return;
        }
        if (i == 3) {
            if (!this.p) {
                this.o.c(this);
            }
            a();
        } else {
            if (i != 6) {
                return;
            }
            a(motionEvent);
            if (!this.p) {
                this.o.c(this);
            }
            a();
        }
    }

    @Override // com.amap.api.mapcore.util.ak
    protected void a() {
        super.a();
        this.p = false;
    }

    @Override // com.amap.api.mapcore.util.aj, com.amap.api.mapcore.util.ak
    protected void a(MotionEvent motionEvent) {
        super.a(motionEvent);
        MotionEvent motionEvent2 = this.f143g;
        this.q = b(motionEvent);
        this.r = b(motionEvent2);
        this.t = this.f143g.getPointerCount() != motionEvent.getPointerCount() ? n : new PointF(this.q.x - this.r.x, this.q.y - this.r.y);
        this.s.x += this.t.x;
        this.s.y += this.t.y;
    }

    public PointF d() {
        return this.t;
    }
}