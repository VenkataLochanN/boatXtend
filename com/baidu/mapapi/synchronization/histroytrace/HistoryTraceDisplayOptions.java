package com.baidu.mapapi.synchronization.histroytrace;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;

/* JADX INFO: loaded from: classes.dex */
public class HistoryTraceDisplayOptions {
    private boolean j = true;
    private int k = 30;
    private int l = 30;
    private int m = 30;
    private int n = 30;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BitmapDescriptor f3342a = BitmapDescriptorFactory.fromAssetWithDpi("SDK_Default_Icon_Start.png");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private BitmapDescriptor f3343b = BitmapDescriptorFactory.fromAssetWithDpi("SDK_Default_Icon_End.png");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private BitmapDescriptor f3344c = BitmapDescriptorFactory.fromAssetWithDpi("SDK_Default_Icon_Car.png");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private BitmapDescriptor f3345d = BitmapDescriptorFactory.fromAsset("SDK_Default_Route_Texture_Gray_Arrow.png");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3346e = 22;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f3347f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f3348g = true;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f3349h = false;
    private boolean i = true;

    public BitmapDescriptor getCarIcon() {
        return this.f3344c;
    }

    public BitmapDescriptor getEndPositionIcon() {
        return this.f3343b;
    }

    public int getPaddingBottom() {
        return this.n;
    }

    public int getPaddingLeft() {
        return this.k;
    }

    public int getPaddingRight() {
        return this.l;
    }

    public int getPaddingTop() {
        return this.m;
    }

    public BitmapDescriptor getRouteLineTexture() {
        return this.f3345d;
    }

    public int getRouteLineWidth() {
        return this.f3346e;
    }

    public BitmapDescriptor getStartPositionIcon() {
        return this.f3342a;
    }

    public boolean isRouteLineRenderBySubSection() {
        return this.j;
    }

    public boolean isShowCarIcon() {
        return this.f3349h;
    }

    public boolean isShowEndPositionIcon() {
        return this.f3348g;
    }

    public boolean isShowRoutePlan() {
        return this.i;
    }

    public boolean isShowStartPositionIcon() {
        return this.f3347f;
    }

    public HistoryTraceDisplayOptions setCarIcon(BitmapDescriptor bitmapDescriptor) {
        this.f3344c = bitmapDescriptor;
        return this;
    }

    public HistoryTraceDisplayOptions setEndPositionIcon(BitmapDescriptor bitmapDescriptor) {
        this.f3343b = bitmapDescriptor;
        return this;
    }

    public HistoryTraceDisplayOptions setPaddingBottom(int i) {
        this.n = i;
        return this;
    }

    public HistoryTraceDisplayOptions setPaddingLeft(int i) {
        this.k = i;
        return this;
    }

    public HistoryTraceDisplayOptions setPaddingRight(int i) {
        this.l = i;
        return this;
    }

    public HistoryTraceDisplayOptions setPaddingTop(int i) {
        this.m = i;
        return this;
    }

    public void setRouteLineRenderBySubSection(boolean z) {
        this.j = z;
    }

    public HistoryTraceDisplayOptions setRouteLineTexture(BitmapDescriptor bitmapDescriptor) {
        this.f3345d = bitmapDescriptor;
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0003 A[PHI: r0
  0x0003: PHI (r0v2 int) = (r0v0 int), (r0v1 int) binds: [B:3:0x0001, B:6:0x0008] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.baidu.mapapi.synchronization.histroytrace.HistoryTraceDisplayOptions setRouteLineWidth(int r2) {
        /*
            r1 = this;
            r0 = 5
            if (r2 >= r0) goto L6
        L3:
            r1.f3346e = r0
            goto Ld
        L6:
            r0 = 40
            if (r2 <= r0) goto Lb
            goto L3
        Lb:
            r1.f3346e = r2
        Ld:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.synchronization.histroytrace.HistoryTraceDisplayOptions.setRouteLineWidth(int):com.baidu.mapapi.synchronization.histroytrace.HistoryTraceDisplayOptions");
    }

    public HistoryTraceDisplayOptions setShowCarIcon(boolean z) {
        this.f3349h = z;
        return this;
    }

    public HistoryTraceDisplayOptions setShowEndPositionIcon(boolean z) {
        this.f3348g = z;
        return this;
    }

    public HistoryTraceDisplayOptions setShowRoutePlan(boolean z) {
        this.i = z;
        return this;
    }

    public HistoryTraceDisplayOptions setShowStartPositionIcon(boolean z) {
        this.f3347f = z;
        return this;
    }

    public HistoryTraceDisplayOptions setStartPositionIcon(BitmapDescriptor bitmapDescriptor) {
        this.f3342a = bitmapDescriptor;
        return this;
    }
}