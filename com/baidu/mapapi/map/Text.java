package com.baidu.mapapi.map;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import mapsdkvi.com.gdi.bgl.android.java.EnvDrawText;

/* JADX INFO: loaded from: classes.dex */
public final class Text extends Overlay {
    private static final String k = Text.class.getSimpleName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f2972a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    LatLng f2973b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f2974c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f2975d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f2976e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    Typeface f2977f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f2978g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f2979h;
    float i;
    int j;

    Text() {
        this.type = com.baidu.mapsdkplatform.comapi.map.h.text;
    }

    @Override // com.baidu.mapapi.map.Overlay
    Bundle a() {
        Typeface typeface = this.f2977f;
        if (typeface != null) {
            EnvDrawText.removeFontCache(typeface.hashCode());
        }
        return super.a();
    }

    @Override // com.baidu.mapapi.map.Overlay
    Bundle a(Bundle bundle) {
        super.a(bundle);
        if (this.f2973b == null) {
            throw new IllegalStateException("BDMapSDKException: when you add a text overlay, you must provide text and the position info.");
        }
        bundle.putString("text", this.f2972a);
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(this.f2973b);
        bundle.putDouble("location_x", geoPointLl2mc.getLongitudeE6());
        bundle.putDouble("location_y", geoPointLl2mc.getLatitudeE6());
        int i = this.f2975d;
        bundle.putInt("font_color", Color.argb(i >>> 24, i & 255, (i >> 8) & 255, (i >> 16) & 255));
        int i2 = this.f2974c;
        bundle.putInt("bg_color", Color.argb(i2 >>> 24, i2 & 255, (i2 >> 8) & 255, (i2 >> 16) & 255));
        bundle.putInt("font_size", this.f2976e);
        Typeface typeface = this.f2977f;
        if (typeface != null) {
            EnvDrawText.registFontCache(typeface.hashCode(), this.f2977f);
            bundle.putInt("type_face", this.f2977f.hashCode());
        }
        int i3 = this.f2978g;
        float f2 = 0.5f;
        bundle.putFloat("align_x", i3 != 1 ? i3 != 2 ? 0.5f : 1.0f : 0.0f);
        int i4 = this.f2979h;
        if (i4 == 8) {
            f2 = 0.0f;
        } else if (i4 == 16) {
            f2 = 1.0f;
        }
        bundle.putFloat("align_y", f2);
        bundle.putFloat("rotate", this.i);
        bundle.putInt("update", this.j);
        return bundle;
    }

    public float getAlignX() {
        return this.f2978g;
    }

    public float getAlignY() {
        return this.f2979h;
    }

    public int getBgColor() {
        return this.f2974c;
    }

    public int getFontColor() {
        return this.f2975d;
    }

    public int getFontSize() {
        return this.f2976e;
    }

    public LatLng getPosition() {
        return this.f2973b;
    }

    public float getRotate() {
        return this.i;
    }

    public String getText() {
        return this.f2972a;
    }

    public Typeface getTypeface() {
        return this.f2977f;
    }

    public void setAlign(int i, int i2) {
        this.f2978g = i;
        this.f2979h = i2;
        this.j = 1;
        this.listener.b(this);
    }

    public void setBgColor(int i) {
        this.f2974c = i;
        this.j = 1;
        this.listener.b(this);
    }

    public void setFontColor(int i) {
        this.f2975d = i;
        this.j = 1;
        this.listener.b(this);
    }

    public void setFontSize(int i) {
        this.f2976e = i;
        this.j = 1;
        this.listener.b(this);
    }

    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: position can not be null");
        }
        this.f2973b = latLng;
        this.j = 1;
        this.listener.b(this);
    }

    public void setRotate(float f2) {
        this.i = f2;
        this.j = 1;
        this.listener.b(this);
    }

    public void setText(String str) {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException("BDMapSDKException: text can not be null or empty");
        }
        this.f2972a = str;
        this.j = 1;
        this.listener.b(this);
    }

    public void setTypeface(Typeface typeface) {
        this.f2977f = typeface;
        this.j = 1;
        this.listener.b(this);
    }
}