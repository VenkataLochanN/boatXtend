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
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapsdkplatform.comapi.map.ac;
import com.baidu.mapsdkplatform.comapi.map.ak;
import com.baidu.mapsdkplatform.comapi.util.CustomMapStyleLoader;
import com.ido.life.constants.Constants;
import com.tencent.bugly.BuglyStrategy;
import java.io.File;
import net.sqlcipher.database.SQLiteDatabase;

/* JADX INFO: loaded from: classes.dex */
public final class TextureMapView extends ViewGroup {
    private static String i;
    private int A;
    private int B;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ac f2992b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private BaiduMap f2993c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ImageView f2994d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Bitmap f2995e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ak f2996f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Point f2997g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Point f2998h;
    private RelativeLayout l;
    private TextView m;
    private TextView n;
    private ImageView o;
    private Context p;
    private float r;
    private com.baidu.mapsdkplatform.comapi.map.l s;
    private int t;
    private boolean u;
    private boolean v;
    private int w;
    private int x;
    private int y;
    private int z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f2991a = TextureMapView.class.getSimpleName();
    private static int j = 0;
    private static int k = 0;
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
    }

    public TextureMapView(Context context) {
        super(context);
        this.t = LogoPosition.logoPostionleftBottom.ordinal();
        this.u = true;
        this.v = true;
        a(context, (BaiduMapOptions) null);
    }

    public TextureMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.t = LogoPosition.logoPostionleftBottom.ordinal();
        this.u = true;
        this.v = true;
        a(context, (BaiduMapOptions) null);
    }

    public TextureMapView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.t = LogoPosition.logoPostionleftBottom.ordinal();
        this.u = true;
        this.v = true;
        a(context, (BaiduMapOptions) null);
    }

    public TextureMapView(Context context, BaiduMapOptions baiduMapOptions) {
        super(context);
        this.t = LogoPosition.logoPostionleftBottom.ordinal();
        this.u = true;
        this.v = true;
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
            r9.f2995e = r0
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
            r9.f2995e = r2
        L41:
            android.graphics.Bitmap r0 = r9.f2995e
            if (r0 == 0) goto L58
            android.widget.ImageView r0 = new android.widget.ImageView
            r0.<init>(r10)
            r9.f2994d = r0
            android.widget.ImageView r10 = r9.f2994d
            android.graphics.Bitmap r0 = r9.f2995e
            r10.setImageBitmap(r0)
            android.widget.ImageView r10 = r9.f2994d
            r9.addView(r10)
        L58:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.map.TextureMapView.a(android.content.Context):void");
    }

    private void a(Context context, BaiduMapOptions baiduMapOptions) {
        setBackgroundColor(-1);
        this.p = context;
        com.baidu.mapsdkplatform.comapi.map.i.a();
        BMapManager.init();
        a(context, baiduMapOptions, j == 0 ? i : CustomMapStyleLoader.getCustomStyleFilePath(), k);
        this.f2993c = new BaiduMap(this.f2992b);
        a(context);
        b(context);
        if (baiduMapOptions != null && !baiduMapOptions.f2786h) {
            this.f2996f.setVisibility(4);
        }
        c(context);
        if (baiduMapOptions != null && !baiduMapOptions.i) {
            this.l.setVisibility(4);
        }
        if (baiduMapOptions != null && baiduMapOptions.j != null) {
            this.t = baiduMapOptions.j.ordinal();
        }
        if (baiduMapOptions != null && baiduMapOptions.l != null) {
            this.f2998h = baiduMapOptions.l;
        }
        if (baiduMapOptions == null || baiduMapOptions.k == null) {
            return;
        }
        this.f2997g = baiduMapOptions.k;
    }

    private void a(Context context, BaiduMapOptions baiduMapOptions, String str, int i2) {
        if (baiduMapOptions == null) {
            this.f2992b = new ac(context, null, str, i2);
        } else {
            this.f2992b = new ac(context, baiduMapOptions.a(), str, i2);
        }
        addView(this.f2992b);
        this.s = new t(this);
        this.f2992b.b().a(this.s);
    }

    private void a(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-2, -2);
        }
        int i2 = layoutParams.width;
        int iMakeMeasureSpec = i2 > 0 ? View.MeasureSpec.makeMeasureSpec(i2, BasicMeasure.EXACTLY) : View.MeasureSpec.makeMeasureSpec(0, 0);
        int i3 = layoutParams.height;
        view.measure(iMakeMeasureSpec, i3 > 0 ? View.MeasureSpec.makeMeasureSpec(i3, BasicMeasure.EXACTLY) : View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.f2996f.a()) {
            float f2 = this.f2992b.b().E().f3518a;
            this.f2996f.b(f2 > this.f2992b.b().f3562b);
            this.f2996f.a(f2 < this.f2992b.b().f3561a);
        }
    }

    private void b(Context context) {
        this.f2996f = new ak(context);
        if (this.f2996f.a()) {
            this.f2996f.b(new u(this));
            this.f2996f.a(new v(this));
            addView(this.f2996f);
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
        i = str;
    }

    @Deprecated
    public static void setIconCustom(int i2) {
        k = i2;
    }

    public static void setLoadCustomMapStyleFileMode(int i2) {
        j = i2;
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

    public final LogoPosition getLogoPosition() {
        int i2 = this.t;
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? LogoPosition.logoPostionleftBottom : LogoPosition.logoPostionRightTop : LogoPosition.logoPostionRightBottom : LogoPosition.logoPostionCenterTop : LogoPosition.logoPostionCenterBottom : LogoPosition.logoPostionleftTop;
    }

    public final BaiduMap getMap() {
        BaiduMap baiduMap = this.f2993c;
        baiduMap.f2773b = this;
        return baiduMap;
    }

    public final int getMapLevel() {
        return q.get((int) this.f2992b.b().E().f3518a).intValue();
    }

    public int getScaleControlViewHeight() {
        return this.B;
    }

    public int getScaleControlViewWidth() {
        return this.B;
    }

    public void onCreate(Context context, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        MapStatus mapStatus = (MapStatus) bundle.getParcelable("mapstatus");
        if (this.f2997g != null) {
            this.f2997g = (Point) bundle.getParcelable("scalePosition");
        }
        if (this.f2998h != null) {
            this.f2998h = (Point) bundle.getParcelable("zoomPosition");
        }
        this.u = bundle.getBoolean("mZoomControlEnabled");
        this.v = bundle.getBoolean("mScaleControlEnabled");
        this.t = bundle.getInt("logoPosition");
        setPadding(bundle.getInt("paddingLeft"), bundle.getInt("paddingTop"), bundle.getInt("paddingRight"), bundle.getInt("paddingBottom"));
        a(context, new BaiduMapOptions().mapStatus(mapStatus));
    }

    public final void onDestroy() {
        Context context = this.p;
        if (context != null) {
            this.f2992b.a(context.hashCode());
        }
        Bitmap bitmap = this.f2995e;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.f2995e.recycle();
        }
        this.f2996f.b();
        BMapManager.destroy();
        com.baidu.mapsdkplatform.comapi.map.i.b();
        this.p = null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        float height;
        int measuredHeight;
        int measuredWidth;
        int childCount = getChildCount();
        a(this.f2994d);
        float width = 1.0f;
        if (((getWidth() - this.w) - this.x) - this.f2994d.getMeasuredWidth() <= 0 || ((getHeight() - this.y) - this.z) - this.f2994d.getMeasuredHeight() <= 0) {
            this.w = 0;
            this.x = 0;
            this.z = 0;
            this.y = 0;
            height = 1.0f;
        } else {
            width = ((getWidth() - this.w) - this.x) / getWidth();
            height = ((getHeight() - this.y) - this.z) / getHeight();
        }
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            ac acVar = this.f2992b;
            if (childAt == acVar) {
                acVar.layout(0, 0, getWidth(), getHeight());
            } else {
                ImageView imageView = this.f2994d;
                if (childAt == imageView) {
                    float f2 = width * 5.0f;
                    int width2 = (int) (this.w + f2);
                    int i7 = (int) (this.x + f2);
                    float f3 = 5.0f * height;
                    int measuredHeight2 = (int) (this.y + f3);
                    int i8 = (int) (this.z + f3);
                    int i9 = this.t;
                    if (i9 != 1) {
                        if (i9 == 2) {
                            measuredHeight = getHeight() - i8;
                            measuredHeight2 = measuredHeight - this.f2994d.getMeasuredHeight();
                        } else if (i9 != 3) {
                            if (i9 == 4) {
                                measuredHeight = getHeight() - i8;
                                measuredHeight2 = measuredHeight - this.f2994d.getMeasuredHeight();
                            } else if (i9 != 5) {
                                measuredHeight = getHeight() - i8;
                                measuredWidth = this.f2994d.getMeasuredWidth() + width2;
                                measuredHeight2 = measuredHeight - this.f2994d.getMeasuredHeight();
                            } else {
                                measuredHeight = measuredHeight2 + imageView.getMeasuredHeight();
                            }
                            measuredWidth = getWidth() - i7;
                            width2 = measuredWidth - this.f2994d.getMeasuredWidth();
                        } else {
                            measuredHeight = measuredHeight2 + imageView.getMeasuredHeight();
                        }
                        width2 = (((getWidth() - this.f2994d.getMeasuredWidth()) + this.w) - this.x) / 2;
                        measuredWidth = (((getWidth() + this.f2994d.getMeasuredWidth()) + this.w) - this.x) / 2;
                    } else {
                        measuredHeight = imageView.getMeasuredHeight() + measuredHeight2;
                        measuredWidth = this.f2994d.getMeasuredWidth() + width2;
                    }
                    this.f2994d.layout(width2, measuredHeight2, measuredWidth, measuredHeight);
                } else {
                    ak akVar = this.f2996f;
                    if (childAt != akVar) {
                        RelativeLayout relativeLayout = this.l;
                        if (childAt == relativeLayout) {
                            a(relativeLayout);
                            Point point = this.f2997g;
                            if (point == null) {
                                this.B = this.l.getMeasuredWidth();
                                this.A = this.l.getMeasuredHeight();
                                int i10 = (int) (this.w + (5.0f * width));
                                int height2 = (getHeight() - ((int) ((this.z + (height * 5.0f)) + 56.0f))) - this.f2994d.getMeasuredHeight();
                                this.l.layout(i10, height2, this.B + i10, this.A + height2);
                            } else {
                                this.l.layout(point.x, this.f2997g.y, this.f2997g.x + this.l.getMeasuredWidth(), this.f2997g.y + this.l.getMeasuredHeight());
                            }
                        } else {
                            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                            if (layoutParams instanceof MapViewLayoutParams) {
                                MapViewLayoutParams mapViewLayoutParams = (MapViewLayoutParams) layoutParams;
                                Point pointA = mapViewLayoutParams.f2898c == MapViewLayoutParams.ELayoutMode.absoluteMode ? mapViewLayoutParams.f2897b : this.f2992b.b().a(CoordUtil.ll2mc(mapViewLayoutParams.f2896a));
                                a(childAt);
                                int measuredWidth2 = childAt.getMeasuredWidth();
                                int measuredHeight3 = childAt.getMeasuredHeight();
                                int i11 = (int) (pointA.x - (mapViewLayoutParams.f2899d * measuredWidth2));
                                int i12 = ((int) (pointA.y - (mapViewLayoutParams.f2900e * measuredHeight3))) + mapViewLayoutParams.f2901f;
                                childAt.layout(i11, i12, measuredWidth2 + i11, measuredHeight3 + i12);
                            }
                        }
                    } else if (akVar.a()) {
                        a(this.f2996f);
                        Point point2 = this.f2998h;
                        if (point2 == null) {
                            int height3 = (int) (((getHeight() - 15) * height) + this.y);
                            int width3 = (int) (((getWidth() - 15) * width) + this.w);
                            int measuredWidth3 = width3 - this.f2996f.getMeasuredWidth();
                            int measuredHeight4 = height3 - this.f2996f.getMeasuredHeight();
                            if (this.t == 4) {
                                height3 -= this.f2994d.getMeasuredHeight();
                                measuredHeight4 -= this.f2994d.getMeasuredHeight();
                            }
                            this.f2996f.layout(measuredWidth3, measuredHeight4, width3, height3);
                        } else {
                            this.f2996f.layout(point2.x, this.f2998h.y, this.f2998h.x + this.f2996f.getMeasuredWidth(), this.f2998h.y + this.f2996f.getMeasuredHeight());
                        }
                    }
                }
            }
        }
    }

    public final void onPause() {
        this.f2992b.d();
    }

    public final void onResume() {
        this.f2992b.c();
    }

    public void onSaveInstanceState(Bundle bundle) {
        BaiduMap baiduMap;
        if (bundle == null || (baiduMap = this.f2993c) == null) {
            return;
        }
        bundle.putParcelable("mapstatus", baiduMap.getMapStatus());
        Point point = this.f2997g;
        if (point != null) {
            bundle.putParcelable("scalePosition", point);
        }
        Point point2 = this.f2998h;
        if (point2 != null) {
            bundle.putParcelable("zoomPosition", point2);
        }
        bundle.putBoolean("mZoomControlEnabled", this.u);
        bundle.putBoolean("mScaleControlEnabled", this.v);
        bundle.putInt("logoPosition", this.t);
        bundle.putInt("paddingLeft", this.w);
        bundle.putInt("paddingTop", this.y);
        bundle.putInt("paddingRight", this.x);
        bundle.putInt("paddingBottom", this.z);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (view == this.f2994d) {
            return;
        }
        super.removeView(view);
    }

    public final void setLogoPosition(LogoPosition logoPosition) {
        if (logoPosition == null) {
            logoPosition = LogoPosition.logoPostionleftBottom;
        }
        this.t = logoPosition.ordinal();
        requestLayout();
    }

    @Override // android.view.View
    public void setPadding(int i2, int i3, int i4, int i5) {
        this.w = i2;
        this.y = i3;
        this.x = i4;
        this.z = i5;
    }

    public void setScaleControlPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f2997g = point;
            requestLayout();
        }
    }

    public void setZoomControlsPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f2998h = point;
            requestLayout();
        }
    }

    public void showScaleControl(boolean z) {
        this.l.setVisibility(z ? 0 : 8);
        this.v = z;
    }

    public void showZoomControls(boolean z) {
        if (this.f2996f.a()) {
            this.f2996f.setVisibility(z ? 0 : 8);
            this.u = z;
        }
    }
}