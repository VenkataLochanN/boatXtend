package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;

/* JADX INFO: compiled from: MoveGestureDetector.java */
/* JADX INFO: loaded from: classes.dex */
public class am extends ak {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final PointF f145a = new PointF();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f146b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private PointF f147c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private PointF f148d;
    private PointF n;
    private PointF o;

    /* JADX INFO: compiled from: MoveGestureDetector.java */
    public interface a {
        boolean a(am amVar);

        boolean b(am amVar);

        void c(am amVar);
    }

    public am(Context context, a aVar) {
        super(context);
        this.n = new PointF();
        this.o = new PointF();
        this.f146b = aVar;
    }

    @Override // com.amap.api.mapcore.util.ak
    protected void a(int i, MotionEvent motionEvent, int i2, int i3) {
        if (i == 0) {
            a();
            this.f143g = MotionEvent.obtain(motionEvent);
            this.k = 0L;
            a(motionEvent);
            return;
        }
        if (i == 2) {
            this.f142f = this.f146b.b(this);
        } else {
            if (i != 5) {
                return;
            }
            if (this.f143g != null) {
                this.f143g.recycle();
            }
            this.f143g = MotionEvent.obtain(motionEvent);
            a(motionEvent);
        }
    }

    @Override // com.amap.api.mapcore.util.ak
    protected void a(int i, MotionEvent motionEvent) {
        if (i != 1) {
            if (i == 2) {
                a(motionEvent);
                if (this.i / this.j <= 0.67f || motionEvent.getPointerCount() > 1 || !this.f146b.a(this)) {
                    return;
                }
                this.f143g.recycle();
                this.f143g = MotionEvent.obtain(motionEvent);
                return;
            }
            if (i != 3) {
                return;
            }
        }
        this.f146b.c(this);
        a();
    }

    @Override // com.amap.api.mapcore.util.ak
    protected void a(MotionEvent motionEvent) {
        super.a(motionEvent);
        MotionEvent motionEvent2 = this.f143g;
        this.f147c = b(motionEvent);
        this.f148d = b(motionEvent2);
        boolean z = this.f143g.getPointerCount() != motionEvent.getPointerCount();
        this.o = z ? f145a : new PointF(this.f147c.x - this.f148d.x, this.f147c.y - this.f148d.y);
        if (z) {
            this.f143g.recycle();
            this.f143g = MotionEvent.obtain(motionEvent);
        }
        this.n.x += this.o.x;
        this.n.y += this.o.y;
    }

    public PointF d() {
        return this.o;
    }
}