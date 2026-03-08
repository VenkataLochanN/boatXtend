package com.baidu.mapapi.map;

import android.R;
import android.animation.ValueAnimator;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class SwipeDismissTouchListener implements View.OnTouchListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f2963a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2964b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2965c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f2966d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private View f2967e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private DismissCallbacks f2968f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f2969g = 1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f2970h;
    private float i;
    private boolean j;
    private int k;
    private Object l;
    private VelocityTracker m;
    private float n;
    private boolean o;
    private boolean p;

    public interface DismissCallbacks {
        boolean canDismiss(Object obj);

        void onDismiss(View view, Object obj);

        void onNotify();
    }

    public SwipeDismissTouchListener(View view, Object obj, DismissCallbacks dismissCallbacks) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(view.getContext());
        this.f2963a = viewConfiguration.getScaledTouchSlop();
        this.f2964b = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f2965c = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f2966d = view.getContext().getResources().getInteger(R.integer.config_shortAnimTime);
        this.f2967e = view;
        this.f2967e.getContext();
        this.l = obj;
        this.f2968f = dismissCallbacks;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        ViewGroup.LayoutParams layoutParams = this.f2967e.getLayoutParams();
        int height = this.f2967e.getHeight();
        ValueAnimator duration = ValueAnimator.ofInt(height, 1).setDuration(this.f2966d);
        duration.addListener(new q(this, layoutParams, height));
        duration.addUpdateListener(new r(this, layoutParams));
        duration.start();
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0027, code lost:
    
        if (r10.m == null) goto L88;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r11, android.view.MotionEvent r12) {
        /*
            Method dump skipped, instruction units count: 434
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.map.SwipeDismissTouchListener.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}