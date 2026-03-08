package com.baidu.mapapi.map;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.core.view.ViewCompat;
import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public final class TextOptions extends OverlayOptions {
    public static final int ALIGN_BOTTOM = 16;
    public static final int ALIGN_CENTER_HORIZONTAL = 4;
    public static final int ALIGN_CENTER_VERTICAL = 32;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int ALIGN_TOP = 8;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f2980a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Bundle f2982c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f2983d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private LatLng f2984e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f2985f;
    private Typeface i;
    private float l;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f2986g = ViewCompat.MEASURED_STATE_MASK;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f2987h = 12;
    private int j = 4;
    private int k = 32;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f2981b = true;

    @Override // com.baidu.mapapi.map.OverlayOptions
    Overlay a() {
        Text text = new Text();
        text.A = this.f2981b;
        text.z = this.f2980a;
        text.B = this.f2982c;
        text.f2972a = this.f2983d;
        text.f2973b = this.f2984e;
        text.f2974c = this.f2985f;
        text.f2975d = this.f2986g;
        text.f2976e = this.f2987h;
        text.f2977f = this.i;
        text.f2978g = this.j;
        text.f2979h = this.k;
        text.i = this.l;
        return text;
    }

    public TextOptions align(int i, int i2) {
        this.j = i;
        this.k = i2;
        return this;
    }

    public TextOptions bgColor(int i) {
        this.f2985f = i;
        return this;
    }

    public TextOptions extraInfo(Bundle bundle) {
        this.f2982c = bundle;
        return this;
    }

    public TextOptions fontColor(int i) {
        this.f2986g = i;
        return this;
    }

    public TextOptions fontSize(int i) {
        this.f2987h = i;
        return this;
    }

    public float getAlignX() {
        return this.j;
    }

    public float getAlignY() {
        return this.k;
    }

    public int getBgColor() {
        return this.f2985f;
    }

    public Bundle getExtraInfo() {
        return this.f2982c;
    }

    public int getFontColor() {
        return this.f2986g;
    }

    public int getFontSize() {
        return this.f2987h;
    }

    public LatLng getPosition() {
        return this.f2984e;
    }

    public float getRotate() {
        return this.l;
    }

    public String getText() {
        return this.f2983d;
    }

    public Typeface getTypeface() {
        return this.i;
    }

    public int getZIndex() {
        return this.f2980a;
    }

    public boolean isVisible() {
        return this.f2981b;
    }

    public TextOptions position(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: position can not be null");
        }
        this.f2984e = latLng;
        return this;
    }

    public TextOptions rotate(float f2) {
        this.l = f2;
        return this;
    }

    public TextOptions text(String str) {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException("BDMapSDKException: text can not be null or empty");
        }
        this.f2983d = str;
        return this;
    }

    public TextOptions typeface(Typeface typeface) {
        this.i = typeface;
        return this;
    }

    public TextOptions visible(boolean z) {
        this.f2981b = z;
        return this;
    }

    public TextOptions zIndex(int i) {
        this.f2980a = i;
        return this;
    }
}