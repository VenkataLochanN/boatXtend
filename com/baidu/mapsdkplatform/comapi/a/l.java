package com.baidu.mapsdkplatform.comapi.a;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.graphics.Point;
import android.view.animation.Interpolator;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public class l extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Animator f3454a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f3455b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Interpolator f3456c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Animation.AnimationListener f3457d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3458e = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3459f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Object[] f3460g;

    public class a implements TypeEvaluator {
        public a() {
        }

        @Override // android.animation.TypeEvaluator
        public Object evaluate(float f2, Object obj, Object obj2) {
            LatLng latLng = (LatLng) obj;
            LatLng latLng2 = (LatLng) obj2;
            double d2 = f2;
            return new LatLng(latLng.latitude + (d2 * (latLng2.latitude - latLng.latitude)), latLng.longitude + ((latLng2.longitude - latLng.longitude) * d2));
        }
    }

    public class b implements TypeEvaluator {
        public b() {
        }

        @Override // android.animation.TypeEvaluator
        public Object evaluate(float f2, Object obj, Object obj2) {
            Point point = (Point) obj;
            Point point2 = (Point) obj2;
            return new Point((int) (point.x + ((point2.x - point.x) * f2)), (int) (point.y + (f2 * (point2.y - point.y))));
        }
    }

    public l(Point... pointArr) {
        this.f3460g = pointArr;
    }

    public l(LatLng... latLngArr) {
        this.f3460g = latLngArr;
    }

    ObjectAnimator a(Marker marker) {
        ObjectAnimator objectAnimatorOfObject;
        if (marker.isFixed()) {
            if (!(this.f3460g[0] instanceof Point)) {
                throw new ClassCastException("BDMapSDKException: if the marker is fixed on screen, the parameters of Transformation must be android.graphics.Point");
            }
            objectAnimatorOfObject = ObjectAnimator.ofObject(marker, "fixedScreenPosition", new b(), this.f3460g);
        } else {
            if (!(this.f3460g[0] instanceof LatLng)) {
                throw new ClassCastException("BDMapSDKException: if the marker isn't fixed on screen, the parameters of Transformation must be Latlng");
            }
            objectAnimatorOfObject = ObjectAnimator.ofObject(marker, "position", new a(), this.f3460g);
        }
        if (objectAnimatorOfObject != null) {
            objectAnimatorOfObject.setRepeatCount(this.f3459f);
            objectAnimatorOfObject.setRepeatMode(c());
            objectAnimatorOfObject.setDuration(this.f3455b);
            Interpolator interpolator = this.f3456c;
            if (interpolator != null) {
                objectAnimatorOfObject.setInterpolator(interpolator);
            }
        }
        return objectAnimatorOfObject;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a() {
        Animator animator = this.f3454a;
        if (animator == null) {
            return;
        }
        animator.start();
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(int i) {
        this.f3458e = i;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(long j) {
        if (j < 0) {
            j = 0;
        }
        this.f3455b = j;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    protected void a(Animator animator) {
        if (animator == null) {
            return;
        }
        animator.addListener(new m(this));
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(Interpolator interpolator) {
        this.f3456c = interpolator;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(Animation.AnimationListener animationListener) {
        this.f3457d = animationListener;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(Marker marker, Animation animation) {
        this.f3454a = a(marker);
        a(this.f3454a);
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void b() {
        Animator animator = this.f3454a;
        if (animator != null) {
            animator.cancel();
            this.f3454a = null;
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void b(int i) {
        if (i > 0 || i == -1) {
            this.f3459f = i;
        }
    }

    public int c() {
        return this.f3458e;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void c(int i) {
    }
}