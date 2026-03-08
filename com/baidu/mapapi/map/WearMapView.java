package com.baidu.mapapi.map;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapsdkplatform.comapi.map.ak;
import com.baidu.mapsdkplatform.comapi.util.CustomMapStyleLoader;
import com.ido.life.constants.Constants;
import com.tencent.bugly.BuglyStrategy;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;
import net.sqlcipher.database.SQLiteDatabase;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes.dex */
public class WearMapView extends ViewGroup implements View.OnApplyWindowInsetsListener {
    public static final int BT_INVIEW = 1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String f3019c;
    private float A;
    private com.baidu.mapsdkplatform.comapi.map.l B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private int H;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ScreenShape f3022a;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private com.baidu.mapsdkplatform.comapi.map.j f3023f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private BaiduMap f3024g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ImageView f3025h;
    private Bitmap i;
    private ak j;
    private boolean k;
    private Point l;
    private Point m;
    public AnimationTask mTask;
    public Timer mTimer;
    public a mTimerHandler;
    private RelativeLayout n;
    private SwipeDismissView o;
    private TextView p;
    private TextView q;
    private ImageView r;
    private boolean v;
    private Context w;
    private boolean y;
    private boolean z;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3018b = MapView.class.getSimpleName();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static int f3020d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static int f3021e = 0;
    private static int s = 0;
    private static int t = 0;
    private static int u = 10;
    private static final SparseArray<Integer> x = new SparseArray<>();

    public class AnimationTask extends TimerTask {
        public AnimationTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            Message message = new Message();
            message.what = 1;
            WearMapView.this.mTimerHandler.sendMessage(message);
        }
    }

    public interface OnDismissCallback {
        void onDismiss();

        void onNotify();
    }

    public enum ScreenShape {
        ROUND,
        RECTANGLE,
        UNDETECTED
    }

    private class a extends Handler {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final WeakReference<Context> f3029b;

        public a(Context context) {
            this.f3029b = new WeakReference<>(context);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.f3029b.get() == null) {
                return;
            }
            super.handleMessage(message);
            if (message.what == 1 && WearMapView.this.j != null) {
                WearMapView.this.a(true);
            }
        }
    }

    static {
        x.append(3, 2000000);
        x.append(4, 1000000);
        x.append(5, 500000);
        x.append(6, 200000);
        x.append(7, Integer.valueOf(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH));
        x.append(8, Integer.valueOf(SQLiteDatabase.SQLITE_MAX_LIKE_PATTERN_LENGTH));
        x.append(9, Integer.valueOf(Constants.STEP_MAX));
        x.append(10, 20000);
        x.append(11, 10000);
        x.append(12, 5000);
        x.append(13, 2000);
        x.append(14, 1000);
        x.append(15, 500);
        x.append(16, 200);
        x.append(17, 100);
        x.append(18, 50);
        x.append(19, 20);
        x.append(20, 10);
        x.append(21, 5);
        x.append(22, 2);
    }

    public WearMapView(Context context) {
        super(context);
        this.k = true;
        this.v = true;
        this.f3022a = ScreenShape.ROUND;
        this.y = true;
        this.z = true;
        a(context, (BaiduMapOptions) null);
    }

    public WearMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = true;
        this.v = true;
        this.f3022a = ScreenShape.ROUND;
        this.y = true;
        this.z = true;
        a(context, (BaiduMapOptions) null);
    }

    public WearMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = true;
        this.v = true;
        this.f3022a = ScreenShape.ROUND;
        this.y = true;
        this.z = true;
        a(context, (BaiduMapOptions) null);
    }

    public WearMapView(Context context, BaiduMapOptions baiduMapOptions) {
        super(context);
        this.k = true;
        this.v = true;
        this.f3022a = ScreenShape.ROUND;
        this.y = true;
        this.z = true;
        a(context, baiduMapOptions);
    }

    private int a(int i, int i2) {
        return i - ((int) Math.sqrt(Math.pow(i, 2.0d) - Math.pow(i2, 2.0d)));
    }

    private void a(int i) {
        com.baidu.mapsdkplatform.comapi.map.j jVar = this.f3023f;
        if (jVar == null) {
            return;
        }
        if (i == 0) {
            jVar.onPause();
            b();
        } else {
            if (i != 1) {
                return;
            }
            jVar.onResume();
            c();
        }
    }

    private static void a(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        s = point.x;
        t = point.y;
    }

    private void a(Context context, BaiduMapOptions baiduMapOptions) {
        AnimationTask animationTask;
        a(context);
        setOnApplyWindowInsetsListener(this);
        this.w = context;
        this.mTimerHandler = new a(context);
        this.mTimer = new Timer();
        if (this.mTimer != null && (animationTask = this.mTask) != null) {
            animationTask.cancel();
        }
        this.mTask = new AnimationTask();
        this.mTimer.schedule(this.mTask, BootloaderScanner.TIMEOUT);
        com.baidu.mapsdkplatform.comapi.map.i.a();
        BMapManager.init();
        a(context, baiduMapOptions, f3020d == 0 ? f3019c : CustomMapStyleLoader.getCustomStyleFilePath());
        this.f3024g = new BaiduMap(this.f3023f);
        this.f3023f.a().t(false);
        this.f3023f.a().s(false);
        c(context);
        d(context);
        b(context);
        if (baiduMapOptions != null && !baiduMapOptions.f2786h) {
            this.j.setVisibility(4);
        }
        e(context);
        if (baiduMapOptions != null && !baiduMapOptions.i) {
            this.n.setVisibility(4);
        }
        if (baiduMapOptions != null && baiduMapOptions.l != null) {
            this.m = baiduMapOptions.l;
        }
        if (baiduMapOptions == null || baiduMapOptions.k == null) {
            return;
        }
        this.l = baiduMapOptions.k;
    }

    private void a(Context context, BaiduMapOptions baiduMapOptions, String str) {
        if (baiduMapOptions == null) {
            this.f3023f = new com.baidu.mapsdkplatform.comapi.map.j(context, null, str, f3021e);
        } else {
            this.f3023f = new com.baidu.mapsdkplatform.comapi.map.j(context, baiduMapOptions.a(), str, f3021e);
        }
        addView(this.f3023f);
        this.B = new x(this);
        this.f3023f.a().a(this.B);
    }

    private void a(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-2, -2);
        }
        int i = layoutParams.width;
        int iMakeMeasureSpec = i > 0 ? View.MeasureSpec.makeMeasureSpec(i, BasicMeasure.EXACTLY) : View.MeasureSpec.makeMeasureSpec(0, 0);
        int i2 = layoutParams.height;
        view.measure(iMakeMeasureSpec, i2 > 0 ? View.MeasureSpec.makeMeasureSpec(i2, BasicMeasure.EXACTLY) : View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    private void a(View view, boolean z) {
        AnimatorSet animatorSet;
        if (z) {
            animatorSet = new AnimatorSet();
            animatorSet.playTogether(ObjectAnimator.ofFloat(view, "TranslationY", 0.0f, -50.0f), ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f));
            animatorSet.addListener(new aa(this, view));
        } else {
            view.setVisibility(0);
            animatorSet = new AnimatorSet();
            animatorSet.playTogether(ObjectAnimator.ofFloat(view, "TranslationY", -50.0f, 0.0f), ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f));
        }
        animatorSet.setDuration(1200L);
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (this.k) {
            a(this.j, z);
        }
    }

    private void b() {
        if (this.f3023f == null || this.v) {
            return;
        }
        d();
        this.v = true;
    }

    private void b(Context context) {
        this.o = new SwipeDismissView(context, this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams((int) ((context.getResources().getDisplayMetrics().density * 34.0f) + 0.5f), t);
        this.o.setBackgroundColor(Color.argb(0, 0, 0, 0));
        this.o.setLayoutParams(layoutParams);
        addView(this.o);
    }

    private void c() {
        if (this.f3023f != null && this.v) {
            e();
            this.v = false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void c(android.content.Context r10) {
        /*
            r9 = this;
            int r0 = com.baidu.mapapi.common.SysOSUtil.getDensityDpi()
            r1 = 180(0xb4, float:2.52E-43)
            if (r0 >= r1) goto Lb
            java.lang.String r1 = "logo_l.png"
            goto Ld
        Lb:
            java.lang.String r1 = "logo_h.png"
        Ld:
            android.graphics.Bitmap r2 = com.baidu.mapsdkplatform.comapi.commonutils.a.a(r1, r10)
            r1 = 480(0x1e0, float:6.73E-43)
            if (r0 <= r1) goto L31
            android.graphics.Matrix r7 = new android.graphics.Matrix
            r7.<init>()
            r0 = 1073741824(0x40000000, float:2.0)
        L1c:
            r7.postScale(r0, r0)
            r3 = 0
            r4 = 0
            int r5 = r2.getWidth()
            int r6 = r2.getHeight()
            r8 = 1
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r2, r3, r4, r5, r6, r7, r8)
            r9.i = r0
            goto L41
        L31:
            r3 = 320(0x140, float:4.48E-43)
            if (r0 <= r3) goto L3f
            if (r0 > r1) goto L3f
            android.graphics.Matrix r7 = new android.graphics.Matrix
            r7.<init>()
            r0 = 1069547520(0x3fc00000, float:1.5)
            goto L1c
        L3f:
            r9.i = r2
        L41:
            android.graphics.Bitmap r0 = r9.i
            if (r0 == 0) goto L58
            android.widget.ImageView r0 = new android.widget.ImageView
            r0.<init>(r10)
            r9.f3025h = r0
            android.widget.ImageView r10 = r9.f3025h
            android.graphics.Bitmap r0 = r9.i
            r10.setImageBitmap(r0)
            android.widget.ImageView r10 = r9.f3025h
            r9.addView(r10)
        L58:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.map.WearMapView.c(android.content.Context):void");
    }

    private void d() {
        com.baidu.mapsdkplatform.comapi.map.j jVar = this.f3023f;
        if (jVar == null) {
            return;
        }
        jVar.b();
    }

    private void d(Context context) {
        this.j = new ak(context, true);
        if (this.j.a()) {
            this.j.b(new y(this));
            this.j.a(new z(this));
            addView(this.j);
        }
    }

    private void e() {
        com.baidu.mapsdkplatform.comapi.map.j jVar = this.f3023f;
        if (jVar == null) {
            return;
        }
        jVar.c();
    }

    private void e(Context context) {
        this.n = new RelativeLayout(context);
        this.n.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.p = new TextView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(14);
        this.p.setTextColor(Color.parseColor("#FFFFFF"));
        this.p.setTextSize(2, 11.0f);
        TextView textView = this.p;
        textView.setTypeface(textView.getTypeface(), 1);
        this.p.setLayoutParams(layoutParams);
        this.p.setId(Integer.MAX_VALUE);
        this.n.addView(this.p);
        this.q = new TextView(context);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.width = -2;
        layoutParams2.height = -2;
        layoutParams2.addRule(14);
        this.q.setTextColor(Color.parseColor("#000000"));
        this.q.setTextSize(2, 11.0f);
        this.q.setLayoutParams(layoutParams2);
        this.n.addView(this.q);
        this.r = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.width = -2;
        layoutParams3.height = -2;
        layoutParams3.addRule(14);
        layoutParams3.addRule(3, this.p.getId());
        this.r.setLayoutParams(layoutParams3);
        Bitmap bitmapA = com.baidu.mapsdkplatform.comapi.commonutils.a.a("icon_scale.9.png", context);
        byte[] ninePatchChunk = bitmapA.getNinePatchChunk();
        NinePatch.isNinePatchChunk(ninePatchChunk);
        this.r.setBackgroundDrawable(new NinePatchDrawable(bitmapA, ninePatchChunk, new Rect(), null));
        this.n.addView(this.r);
        addView(this.n);
    }

    public static void setCustomMapStylePath(String str) {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("BDMapSDKException: customMapStylePath String is illegal");
        }
        if (!new File(str).exists()) {
            throw new RuntimeException("BDMapSDKException: please check whether the customMapStylePath file exits");
        }
        f3019c = str;
    }

    @Deprecated
    public static void setIconCustom(int i) {
        f3021e = i;
    }

    public static void setLoadCustomMapStyleFileMode(int i) {
        f3020d = i;
    }

    @Deprecated
    public static void setMapCustomEnable(boolean z) {
        com.baidu.mapsdkplatform.comapi.map.i.a(z);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof MapViewLayoutParams) {
            super.addView(view, layoutParams);
        }
    }

    public final BaiduMap getMap() {
        BaiduMap baiduMap = this.f3024g;
        baiduMap.f2774c = this;
        return baiduMap;
    }

    public final int getMapLevel() {
        return x.get((int) this.f3023f.a().E().f3518a).intValue();
    }

    public int getScaleControlViewHeight() {
        return this.G;
    }

    public int getScaleControlViewWidth() {
        return this.H;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        this.f3022a = windowInsets.isRound() ? ScreenShape.ROUND : ScreenShape.RECTANGLE;
        return windowInsets;
    }

    public void onCreate(Context context, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        MapStatus mapStatus = (MapStatus) bundle.getParcelable("mapstatus");
        if (this.l != null) {
            this.l = (Point) bundle.getParcelable("scalePosition");
        }
        if (this.m != null) {
            this.m = (Point) bundle.getParcelable("zoomPosition");
        }
        this.y = bundle.getBoolean("mZoomControlEnabled");
        this.z = bundle.getBoolean("mScaleControlEnabled");
        setPadding(bundle.getInt("paddingLeft"), bundle.getInt("paddingTop"), bundle.getInt("paddingRight"), bundle.getInt("paddingBottom"));
        a(context, new BaiduMapOptions().mapStatus(mapStatus));
    }

    public final void onDestroy() {
        Context context = this.w;
        if (context != null) {
            this.f3023f.b(context.hashCode());
        }
        Bitmap bitmap = this.i;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.i.recycle();
            this.i = null;
        }
        this.j.b();
        BMapManager.destroy();
        com.baidu.mapsdkplatform.comapi.map.i.b();
        AnimationTask animationTask = this.mTask;
        if (animationTask != null) {
            animationTask.cancel();
        }
        this.w = null;
    }

    public final void onDismiss() {
        removeAllViews();
    }

    public final void onEnterAmbient(Bundle bundle) {
        a(0);
    }

    public void onExitAmbient() {
        a(1);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        AnimationTask animationTask;
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1) {
                this.mTimer = new Timer();
                if (this.mTimer != null && (animationTask = this.mTask) != null) {
                    animationTask.cancel();
                }
                this.mTask = new AnimationTask();
                this.mTimer.schedule(this.mTask, BootloaderScanner.TIMEOUT);
            }
        } else if (this.j.getVisibility() == 0) {
            Timer timer = this.mTimer;
            if (timer != null) {
                if (this.mTask != null) {
                    timer.cancel();
                    this.mTask.cancel();
                }
                this.mTimer = null;
                this.mTask = null;
            }
        } else if (this.j.getVisibility() == 4) {
            if (this.mTimer != null) {
                AnimationTask animationTask2 = this.mTask;
                if (animationTask2 != null) {
                    animationTask2.cancel();
                }
                this.mTimer.cancel();
                this.mTask = null;
                this.mTimer = null;
            }
            a(false);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float height;
        int iA;
        int iA2;
        int iA3;
        int iA4;
        int childCount = getChildCount();
        a(this.f3025h);
        float width = 1.0f;
        if (((getWidth() - this.C) - this.D) - this.f3025h.getMeasuredWidth() <= 0 || ((getHeight() - this.E) - this.F) - this.f3025h.getMeasuredHeight() <= 0) {
            this.C = 0;
            this.D = 0;
            this.F = 0;
            this.E = 0;
            height = 1.0f;
        } else {
            width = ((getWidth() - this.C) - this.D) / getWidth();
            height = ((getHeight() - this.E) - this.F) / getHeight();
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            com.baidu.mapsdkplatform.comapi.map.j jVar = this.f3023f;
            if (childAt == jVar) {
                jVar.layout(0, 0, getWidth(), getHeight());
            } else if (childAt == this.f3025h) {
                int i6 = (int) (this.F + (12.0f * height));
                if (this.f3022a == ScreenShape.ROUND) {
                    a(this.j);
                    int i7 = s / 2;
                    iA4 = a(i7, this.j.getMeasuredWidth() / 2);
                    iA3 = u + ((s / 2) - a(i7, i7 - iA4));
                } else {
                    iA3 = 0;
                    iA4 = 0;
                }
                int i8 = (t - iA4) - i6;
                int measuredHeight = i8 - this.f3025h.getMeasuredHeight();
                int i9 = s - iA3;
                this.f3025h.layout(i9 - this.f3025h.getMeasuredWidth(), measuredHeight, i9, i8);
            } else {
                ak akVar = this.j;
                if (childAt == akVar) {
                    if (akVar.a()) {
                        a(this.j);
                        Point point = this.m;
                        if (point == null) {
                            int iA5 = (int) ((12.0f * height) + this.E + (this.f3022a == ScreenShape.ROUND ? a(t / 2, this.j.getMeasuredWidth() / 2) : 0));
                            int measuredWidth = (s - this.j.getMeasuredWidth()) / 2;
                            this.j.layout(measuredWidth, iA5, this.j.getMeasuredWidth() + measuredWidth, this.j.getMeasuredHeight() + iA5);
                        } else {
                            this.j.layout(point.x, this.m.y, this.m.x + this.j.getMeasuredWidth(), this.m.y + this.j.getMeasuredHeight());
                        }
                    }
                } else if (childAt == this.n) {
                    if (this.f3022a == ScreenShape.ROUND) {
                        a(this.j);
                        int i10 = s / 2;
                        iA2 = a(i10, this.j.getMeasuredWidth() / 2);
                        iA = u + ((s / 2) - a(i10, i10 - iA2));
                    } else {
                        iA = 0;
                        iA2 = 0;
                    }
                    a(this.n);
                    Point point2 = this.l;
                    if (point2 == null) {
                        this.H = this.n.getMeasuredWidth();
                        this.G = this.n.getMeasuredHeight();
                        int i11 = (int) (this.C + (5.0f * width) + iA);
                        int i12 = (t - ((int) (this.F + (12.0f * height)))) - iA2;
                        this.n.layout(i11, i12 - this.n.getMeasuredHeight(), this.H + i11, i12);
                    } else {
                        this.n.layout(point2.x, this.l.y, this.l.x + this.n.getMeasuredWidth(), this.l.y + this.n.getMeasuredHeight());
                    }
                } else {
                    SwipeDismissView swipeDismissView = this.o;
                    if (childAt == swipeDismissView) {
                        a(swipeDismissView);
                        this.o.layout(0, 0, this.o.getMeasuredWidth(), t);
                    } else {
                        ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                        if (layoutParams instanceof MapViewLayoutParams) {
                            MapViewLayoutParams mapViewLayoutParams = (MapViewLayoutParams) layoutParams;
                            Point pointA = mapViewLayoutParams.f2898c == MapViewLayoutParams.ELayoutMode.absoluteMode ? mapViewLayoutParams.f2897b : this.f3023f.a().a(CoordUtil.ll2mc(mapViewLayoutParams.f2896a));
                            a(childAt);
                            int measuredWidth2 = childAt.getMeasuredWidth();
                            int measuredHeight2 = childAt.getMeasuredHeight();
                            float f2 = mapViewLayoutParams.f2899d;
                            int i13 = (int) (pointA.x - (f2 * measuredWidth2));
                            int i14 = ((int) (pointA.y - (mapViewLayoutParams.f2900e * measuredHeight2))) + mapViewLayoutParams.f2901f;
                            childAt.layout(i13, i14, measuredWidth2 + i13, measuredHeight2 + i14);
                        }
                    }
                }
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        BaiduMap baiduMap;
        if (bundle == null || (baiduMap = this.f3024g) == null) {
            return;
        }
        bundle.putParcelable("mapstatus", baiduMap.getMapStatus());
        Point point = this.l;
        if (point != null) {
            bundle.putParcelable("scalePosition", point);
        }
        Point point2 = this.m;
        if (point2 != null) {
            bundle.putParcelable("zoomPosition", point2);
        }
        bundle.putBoolean("mZoomControlEnabled", this.y);
        bundle.putBoolean("mScaleControlEnabled", this.z);
        bundle.putInt("paddingLeft", this.C);
        bundle.putInt("paddingTop", this.E);
        bundle.putInt("paddingRight", this.D);
        bundle.putInt("paddingBottom", this.F);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (view == this.f3025h) {
            return;
        }
        super.removeView(view);
    }

    public void setOnDismissCallbackListener(OnDismissCallback onDismissCallback) {
        SwipeDismissView swipeDismissView = this.o;
        if (swipeDismissView == null) {
            return;
        }
        swipeDismissView.setCallback(onDismissCallback);
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        this.C = i;
        this.E = i2;
        this.D = i3;
        this.F = i4;
    }

    public void setScaleControlPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.l = point;
            requestLayout();
        }
    }

    public void setShape(ScreenShape screenShape) {
        this.f3022a = screenShape;
    }

    public void setViewAnimitionEnable(boolean z) {
        this.k = z;
    }

    public void setZoomControlsPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.m = point;
            requestLayout();
        }
    }

    public void showScaleControl(boolean z) {
        this.n.setVisibility(z ? 0 : 8);
        this.z = z;
    }

    public void showZoomControls(boolean z) {
        if (this.j.a()) {
            this.j.setVisibility(z ? 0 : 8);
            this.y = z;
        }
    }
}