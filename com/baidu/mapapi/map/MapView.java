package com.baidu.mapapi.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import net.sqlcipher.database.SQLiteDatabase;

/* JADX INFO: loaded from: classes.dex */
public final class MapView extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f2889b;
    private int A;
    private int B;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.baidu.mapsdkplatform.comapi.map.j f2892e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private BaiduMap f2893f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ImageView f2894g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Bitmap f2895h;
    private ak i;
    private Point j;
    private Point k;
    private RelativeLayout l;
    private TextView m;
    private TextView n;
    private ImageView o;
    private Context p;
    private int r;
    private boolean s;
    private boolean t;
    private float u;
    private com.baidu.mapsdkplatform.comapi.map.l v;
    private int w;
    private int x;
    private int y;
    private int z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f2888a = MapView.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static int f2890c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static int f2891d = 0;
    private static final SparseArray<Integer> q = new SparseArray<>();

    static {
        q.append(3, 2000000);
        q.append(4, 1000000);
        q.append(5, 500000);
        q.append(6, 200000);
        q.append(7, Integer.valueOf(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH));
        q.append(8, Integer.valueOf(SQLiteDatabase.SQLITE_MAX_LIKE_PATTERN_LENGTH));
        q.append(9, Integer.valueOf(Constants.STEP_MAX));
        q.append(10, 20000);
        q.append(11, 10000);
        q.append(12, 5000);
        q.append(13, 2000);
        q.append(14, 1000);
        q.append(15, 500);
        q.append(16, 200);
        q.append(17, 100);
        q.append(18, 50);
        q.append(19, 20);
        q.append(20, 10);
        q.append(21, 5);
        q.append(22, 2);
        q.append(23, 2);
        q.append(24, 2);
        q.append(25, 2);
        q.append(26, 2);
    }

    public MapView(Context context) {
        super(context);
        this.r = LogoPosition.logoPostionleftBottom.ordinal();
        this.s = true;
        this.t = true;
        a(context, (BaiduMapOptions) null);
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.r = LogoPosition.logoPostionleftBottom.ordinal();
        this.s = true;
        this.t = true;
        a(context, (BaiduMapOptions) null);
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.r = LogoPosition.logoPostionleftBottom.ordinal();
        this.s = true;
        this.t = true;
        a(context, (BaiduMapOptions) null);
    }

    public MapView(Context context, BaiduMapOptions baiduMapOptions) {
        super(context);
        this.r = LogoPosition.logoPostionleftBottom.ordinal();
        this.s = true;
        this.t = true;
        a(context, baiduMapOptions);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(android.content.Context r10) {
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
            r9.f2895h = r0
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
            r9.f2895h = r2
        L41:
            android.graphics.Bitmap r0 = r9.f2895h
            if (r0 == 0) goto L58
            android.widget.ImageView r0 = new android.widget.ImageView
            r0.<init>(r10)
            r9.f2894g = r0
            android.widget.ImageView r10 = r9.f2894g
            android.graphics.Bitmap r0 = r9.f2895h
            r10.setImageBitmap(r0)
            android.widget.ImageView r10 = r9.f2894g
            r9.addView(r10)
        L58:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.map.MapView.a(android.content.Context):void");
    }

    private void a(Context context, BaiduMapOptions baiduMapOptions) {
        this.p = context;
        com.baidu.mapsdkplatform.comapi.map.i.a();
        BMapManager.init();
        a(context, baiduMapOptions, f2890c == 0 ? f2889b : CustomMapStyleLoader.getCustomStyleFilePath(), f2890c);
        this.f2893f = new BaiduMap(this.f2892e);
        a(context);
        b(context);
        if (baiduMapOptions != null && !baiduMapOptions.f2786h) {
            this.i.setVisibility(4);
        }
        c(context);
        if (baiduMapOptions != null && !baiduMapOptions.i) {
            this.l.setVisibility(4);
        }
        if (baiduMapOptions != null && baiduMapOptions.j != null) {
            this.r = baiduMapOptions.j.ordinal();
        }
        if (baiduMapOptions != null && baiduMapOptions.l != null) {
            this.k = baiduMapOptions.l;
        }
        if (baiduMapOptions == null || baiduMapOptions.k == null) {
            return;
        }
        this.j = baiduMapOptions.k;
    }

    private void a(Context context, BaiduMapOptions baiduMapOptions, String str, int i) {
        if (baiduMapOptions == null) {
            this.f2892e = new com.baidu.mapsdkplatform.comapi.map.j(context, null, str, i);
        } else {
            this.f2892e = new com.baidu.mapsdkplatform.comapi.map.j(context, baiduMapOptions.a(), str, i);
        }
        addView(this.f2892e);
        this.v = new l(this);
        this.f2892e.a().a(this.v);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.i.a()) {
            float f2 = this.f2892e.a().E().f3518a;
            this.i.b(f2 > this.f2892e.a().f3562b);
            this.i.a(f2 < this.f2892e.a().f3561a);
        }
    }

    private void b(Context context) {
        this.i = new ak(context, false);
        if (this.i.a()) {
            this.i.b(new m(this));
            this.i.a(new n(this));
            addView(this.i);
        }
    }

    private void c(Context context) {
        this.l = new RelativeLayout(context);
        this.l.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.m = new TextView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(14);
        this.m.setTextColor(Color.parseColor("#FFFFFF"));
        this.m.setTextSize(2, 11.0f);
        TextView textView = this.m;
        textView.setTypeface(textView.getTypeface(), 1);
        this.m.setLayoutParams(layoutParams);
        this.m.setId(Integer.MAX_VALUE);
        this.l.addView(this.m);
        this.n = new TextView(context);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.width = -2;
        layoutParams2.height = -2;
        layoutParams2.addRule(14);
        this.n.setTextColor(Color.parseColor("#000000"));
        this.n.setTextSize(2, 11.0f);
        this.n.setLayoutParams(layoutParams2);
        this.l.addView(this.n);
        this.o = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.width = -2;
        layoutParams3.height = -2;
        layoutParams3.addRule(14);
        layoutParams3.addRule(3, this.m.getId());
        this.o.setLayoutParams(layoutParams3);
        Bitmap bitmapA = com.baidu.mapsdkplatform.comapi.commonutils.a.a("icon_scale.9.png", context);
        byte[] ninePatchChunk = bitmapA.getNinePatchChunk();
        NinePatch.isNinePatchChunk(ninePatchChunk);
        this.o.setBackgroundDrawable(new NinePatchDrawable(bitmapA, ninePatchChunk, new Rect(), null));
        this.l.addView(this.o);
        addView(this.l);
    }

    public static void setCustomMapStylePath(String str) {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("BDMapSDKException: customMapStylePath String is illegal");
        }
        if (!new File(str).exists()) {
            throw new RuntimeException("BDMapSDKException: please check whether the customMapStylePath file exits");
        }
        f2889b = str;
    }

    @Deprecated
    public static void setIconCustom(int i) {
        f2891d = i;
    }

    public static void setLoadCustomMapStyleFileMode(int i) {
        f2890c = i;
    }

    public static void setMapCustomEnable(boolean z) {
        com.baidu.mapsdkplatform.comapi.map.i.a(z);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof MapViewLayoutParams) {
            super.addView(view, layoutParams);
        }
    }

    public void cancelRenderMap() {
        this.f2892e.a().w(false);
        this.f2892e.a().P().clear();
    }

    public final LogoPosition getLogoPosition() {
        int i = this.r;
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? LogoPosition.logoPostionleftBottom : LogoPosition.logoPostionRightTop : LogoPosition.logoPostionRightBottom : LogoPosition.logoPostionCenterTop : LogoPosition.logoPostionCenterBottom : LogoPosition.logoPostionleftTop;
    }

    public final BaiduMap getMap() {
        BaiduMap baiduMap = this.f2893f;
        baiduMap.f2772a = this;
        return baiduMap;
    }

    public final int getMapLevel() {
        return q.get((int) this.f2892e.a().E().f3518a).intValue();
    }

    public int getScaleControlViewHeight() {
        return this.A;
    }

    public int getScaleControlViewWidth() {
        return this.B;
    }

    public boolean handleMultiTouch(float f2, float f3, float f4, float f5) {
        com.baidu.mapsdkplatform.comapi.map.j jVar = this.f2892e;
        return jVar != null && jVar.a(f2, f3, f4, f5);
    }

    public void handleTouchDown(float f2, float f3) {
        com.baidu.mapsdkplatform.comapi.map.j jVar = this.f2892e;
        if (jVar == null) {
            return;
        }
        jVar.a(f2, f3);
    }

    public boolean handleTouchMove(float f2, float f3) {
        com.baidu.mapsdkplatform.comapi.map.j jVar = this.f2892e;
        return jVar != null && jVar.c(f2, f3);
    }

    public boolean handleTouchUp(float f2, float f3) {
        com.baidu.mapsdkplatform.comapi.map.j jVar = this.f2892e;
        if (jVar == null) {
            return false;
        }
        return jVar.b(f2, f3);
    }

    public boolean inRangeOfView(float f2, float f3) {
        com.baidu.mapsdkplatform.comapi.map.j jVar = this.f2892e;
        return jVar != null && jVar.d(f2, f3);
    }

    public void onCreate(Context context, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        MapStatus mapStatus = (MapStatus) bundle.getParcelable("mapstatus");
        if (this.j != null) {
            this.j = (Point) bundle.getParcelable("scalePosition");
        }
        if (this.k != null) {
            this.k = (Point) bundle.getParcelable("zoomPosition");
        }
        this.s = bundle.getBoolean("mZoomControlEnabled");
        this.t = bundle.getBoolean("mScaleControlEnabled");
        this.r = bundle.getInt("logoPosition");
        setPadding(bundle.getInt("paddingLeft"), bundle.getInt("paddingTop"), bundle.getInt("paddingRight"), bundle.getInt("paddingBottom"));
        a(context, new BaiduMapOptions().mapStatus(mapStatus));
    }

    public final void onDestroy() {
        Context context = this.p;
        if (context != null) {
            this.f2892e.b(context.hashCode());
        }
        Bitmap bitmap = this.f2895h;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.f2895h.recycle();
            this.f2895h = null;
        }
        if (f2889b != null) {
            f2889b = null;
        }
        this.i.b();
        BMapManager.destroy();
        com.baidu.mapsdkplatform.comapi.map.i.b();
        this.p = null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float height;
        int measuredHeight;
        int measuredWidth;
        int childCount = getChildCount();
        a(this.f2894g);
        float width = 1.0f;
        if (((getWidth() - this.w) - this.x) - this.f2894g.getMeasuredWidth() <= 0 || ((getHeight() - this.y) - this.z) - this.f2894g.getMeasuredHeight() <= 0) {
            this.w = 0;
            this.x = 0;
            this.z = 0;
            this.y = 0;
            height = 1.0f;
        } else {
            width = ((getWidth() - this.w) - this.x) / getWidth();
            height = ((getHeight() - this.y) - this.z) / getHeight();
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt != null) {
                com.baidu.mapsdkplatform.comapi.map.j jVar = this.f2892e;
                if (childAt == jVar) {
                    jVar.layout(0, 0, getWidth(), getHeight());
                } else {
                    ImageView imageView = this.f2894g;
                    if (childAt == imageView) {
                        float f2 = width * 5.0f;
                        int width2 = (int) (this.w + f2);
                        int i6 = (int) (this.x + f2);
                        float f3 = 5.0f * height;
                        int measuredHeight2 = (int) (this.y + f3);
                        int i7 = (int) (this.z + f3);
                        int i8 = this.r;
                        if (i8 != 1) {
                            if (i8 == 2) {
                                measuredHeight = getHeight() - i7;
                                measuredHeight2 = measuredHeight - this.f2894g.getMeasuredHeight();
                            } else if (i8 != 3) {
                                if (i8 == 4) {
                                    measuredHeight = getHeight() - i7;
                                    measuredHeight2 = measuredHeight - this.f2894g.getMeasuredHeight();
                                } else if (i8 != 5) {
                                    measuredHeight = getHeight() - i7;
                                    measuredWidth = this.f2894g.getMeasuredWidth() + width2;
                                    measuredHeight2 = measuredHeight - this.f2894g.getMeasuredHeight();
                                } else {
                                    measuredHeight = measuredHeight2 + imageView.getMeasuredHeight();
                                }
                                measuredWidth = getWidth() - i6;
                                width2 = measuredWidth - this.f2894g.getMeasuredWidth();
                            } else {
                                measuredHeight = measuredHeight2 + imageView.getMeasuredHeight();
                            }
                            width2 = (((getWidth() - this.f2894g.getMeasuredWidth()) + this.w) - this.x) / 2;
                            measuredWidth = (((getWidth() + this.f2894g.getMeasuredWidth()) + this.w) - this.x) / 2;
                        } else {
                            measuredHeight = imageView.getMeasuredHeight() + measuredHeight2;
                            measuredWidth = this.f2894g.getMeasuredWidth() + width2;
                        }
                        this.f2894g.layout(width2, measuredHeight2, measuredWidth, measuredHeight);
                    } else {
                        ak akVar = this.i;
                        if (childAt != akVar) {
                            RelativeLayout relativeLayout = this.l;
                            if (childAt == relativeLayout) {
                                a(relativeLayout);
                                Point point = this.j;
                                if (point == null) {
                                    this.B = this.l.getMeasuredWidth();
                                    this.A = this.l.getMeasuredHeight();
                                    int i9 = (int) (this.w + (5.0f * width));
                                    int height2 = (getHeight() - ((int) ((this.z + (height * 5.0f)) + 56.0f))) - this.f2894g.getMeasuredHeight();
                                    this.l.layout(i9, height2, this.B + i9, this.A + height2);
                                } else {
                                    this.l.layout(point.x, this.j.y, this.j.x + this.l.getMeasuredWidth(), this.j.y + this.l.getMeasuredHeight());
                                }
                            } else {
                                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                                if (layoutParams == null) {
                                    Log.e("test", "lp == null");
                                }
                                if (layoutParams instanceof MapViewLayoutParams) {
                                    MapViewLayoutParams mapViewLayoutParams = (MapViewLayoutParams) layoutParams;
                                    Point pointA = mapViewLayoutParams.f2898c == MapViewLayoutParams.ELayoutMode.absoluteMode ? mapViewLayoutParams.f2897b : this.f2892e.a().a(CoordUtil.ll2mc(mapViewLayoutParams.f2896a));
                                    a(childAt);
                                    int measuredWidth2 = childAt.getMeasuredWidth();
                                    int measuredHeight3 = childAt.getMeasuredHeight();
                                    int i10 = (int) (pointA.x - (mapViewLayoutParams.f2899d * measuredWidth2));
                                    int i11 = ((int) (pointA.y - (mapViewLayoutParams.f2900e * measuredHeight3))) + mapViewLayoutParams.f2901f;
                                    childAt.layout(i10, i11, measuredWidth2 + i10, measuredHeight3 + i11);
                                }
                            }
                        } else if (akVar.a()) {
                            a(this.i);
                            Point point2 = this.k;
                            if (point2 == null) {
                                int height3 = (int) (((getHeight() - 15) * height) + this.y);
                                int width3 = (int) (((getWidth() - 15) * width) + this.w);
                                int measuredWidth3 = width3 - this.i.getMeasuredWidth();
                                int measuredHeight4 = height3 - this.i.getMeasuredHeight();
                                if (this.r == 4) {
                                    height3 -= this.f2894g.getMeasuredHeight();
                                    measuredHeight4 -= this.f2894g.getMeasuredHeight();
                                }
                                this.i.layout(measuredWidth3, measuredHeight4, width3, height3);
                            } else {
                                this.i.layout(point2.x, this.k.y, this.k.x + this.i.getMeasuredWidth(), this.k.y + this.i.getMeasuredHeight());
                            }
                        }
                    }
                }
            }
        }
    }

    public final void onPause() {
        this.f2892e.onPause();
    }

    public final void onResume() {
        this.f2892e.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        BaiduMap baiduMap;
        if (bundle == null || (baiduMap = this.f2893f) == null) {
            return;
        }
        bundle.putParcelable("mapstatus", baiduMap.getMapStatus());
        Point point = this.j;
        if (point != null) {
            bundle.putParcelable("scalePosition", point);
        }
        Point point2 = this.k;
        if (point2 != null) {
            bundle.putParcelable("zoomPosition", point2);
        }
        bundle.putBoolean("mZoomControlEnabled", this.s);
        bundle.putBoolean("mScaleControlEnabled", this.t);
        bundle.putInt("logoPosition", this.r);
        bundle.putInt("paddingLeft", this.w);
        bundle.putInt("paddingTop", this.y);
        bundle.putInt("paddingRight", this.x);
        bundle.putInt("paddingBottom", this.z);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (view == this.f2894g) {
            return;
        }
        super.removeView(view);
    }

    public void renderMap() {
        com.baidu.mapsdkplatform.comapi.map.e eVarA = this.f2892e.a();
        eVarA.w(true);
        eVarA.Q();
    }

    public final void setLogoPosition(LogoPosition logoPosition) {
        if (logoPosition == null) {
            logoPosition = LogoPosition.logoPostionleftBottom;
        }
        this.r = logoPosition.ordinal();
        requestLayout();
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        this.w = i;
        this.y = i2;
        this.x = i3;
        this.z = i4;
    }

    public void setScaleControlPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.j = point;
            requestLayout();
        }
    }

    public void setUpViewEventToMapView(MotionEvent motionEvent) {
        this.f2892e.onTouchEvent(motionEvent);
    }

    public final void setZOrderMediaOverlay(boolean z) {
        com.baidu.mapsdkplatform.comapi.map.j jVar = this.f2892e;
        if (jVar == null) {
            return;
        }
        jVar.setZOrderMediaOverlay(z);
    }

    public void setZoomControlsPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.k = point;
            requestLayout();
        }
    }

    public void showScaleControl(boolean z) {
        this.l.setVisibility(z ? 0 : 8);
        this.t = z;
    }

    public void showZoomControls(boolean z) {
        if (this.i.a()) {
            this.i.setVisibility(z ? 0 : 8);
            this.s = z;
        }
    }
}