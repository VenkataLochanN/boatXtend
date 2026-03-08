package com.baidu.mapsdkplatform.comapi.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.baidu.mapapi.common.SysOSUtil;

/* JADX INFO: loaded from: classes.dex */
public class ak extends LinearLayout implements View.OnTouchListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ImageView f3548a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ImageView f3549b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f3550c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Bitmap f3551d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Bitmap f3552e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Bitmap f3553f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Bitmap f3554g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Bitmap f3555h;
    private Bitmap i;
    private Bitmap j;
    private Bitmap k;
    private int l;
    private boolean m;
    private boolean n;

    @Deprecated
    public ak(Context context) {
        super(context);
        this.m = false;
        this.n = false;
        this.f3550c = context;
        c();
        if (this.f3551d == null || this.f3552e == null || this.f3553f == null || this.f3554g == null) {
            return;
        }
        this.f3548a = new ImageView(this.f3550c);
        this.f3549b = new ImageView(this.f3550c);
        this.f3548a.setImageBitmap(this.f3551d);
        this.f3549b.setImageBitmap(this.f3553f);
        this.l = a(this.f3553f.getHeight() / 6);
        a(this.f3548a, "main_topbtn_up.9.png");
        a(this.f3549b, "main_bottombtn_up.9.png");
        this.f3548a.setId(0);
        this.f3549b.setId(1);
        this.f3548a.setClickable(true);
        this.f3549b.setClickable(true);
        this.f3548a.setOnTouchListener(this);
        this.f3549b.setOnTouchListener(this);
        setOrientation(1);
        setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        addView(this.f3548a);
        addView(this.f3549b);
        this.n = true;
    }

    public ak(Context context, boolean z) {
        super(context);
        this.m = false;
        this.n = false;
        this.f3550c = context;
        this.m = z;
        this.f3548a = new ImageView(this.f3550c);
        this.f3549b = new ImageView(this.f3550c);
        if (z) {
            d();
            if (this.f3555h == null || this.i == null || this.j == null || this.k == null) {
                return;
            }
            this.f3548a.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            this.f3549b.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            this.f3548a.setImageBitmap(this.f3555h);
            this.f3549b.setImageBitmap(this.j);
            setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            setOrientation(0);
        } else {
            c();
            Bitmap bitmap = this.f3551d;
            if (bitmap == null || this.f3552e == null || this.f3553f == null || this.f3554g == null) {
                return;
            }
            this.f3548a.setImageBitmap(bitmap);
            this.f3549b.setImageBitmap(this.f3553f);
            this.l = a(this.f3553f.getHeight() / 6);
            a(this.f3548a, "main_topbtn_up.9.png");
            a(this.f3549b, "main_bottombtn_up.9.png");
            setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            setOrientation(1);
        }
        this.f3548a.setId(0);
        this.f3549b.setId(1);
        this.f3548a.setClickable(true);
        this.f3549b.setClickable(true);
        this.f3548a.setOnTouchListener(this);
        this.f3549b.setOnTouchListener(this);
        addView(this.f3548a);
        addView(this.f3549b);
        this.n = true;
    }

    private int a(int i) {
        return (int) ((this.f3550c.getResources().getDisplayMetrics().density * i) + 0.5f);
    }

    private Bitmap a(String str) {
        Matrix matrix = new Matrix();
        int densityDpi = SysOSUtil.getDensityDpi();
        float f2 = densityDpi > 480 ? 1.8f : (densityDpi <= 320 || densityDpi > 480) ? 1.2f : 1.5f;
        matrix.postScale(f2, f2);
        Bitmap bitmapA = com.baidu.mapsdkplatform.comapi.commonutils.a.a(str, this.f3550c);
        return Bitmap.createBitmap(bitmapA, 0, 0, bitmapA.getWidth(), bitmapA.getHeight(), matrix, true);
    }

    private void a(View view, String str) {
        Bitmap bitmapA = com.baidu.mapsdkplatform.comapi.commonutils.a.a(str, this.f3550c);
        byte[] ninePatchChunk = bitmapA.getNinePatchChunk();
        NinePatch.isNinePatchChunk(ninePatchChunk);
        view.setBackgroundDrawable(new NinePatchDrawable(bitmapA, ninePatchChunk, new Rect(), null));
        int i = this.l;
        view.setPadding(i, i, i, i);
    }

    private void c() {
        this.f3551d = a("main_icon_zoomin.png");
        this.f3552e = a("main_icon_zoomin_dis.png");
        this.f3553f = a("main_icon_zoomout.png");
        this.f3554g = a("main_icon_zoomout_dis.png");
    }

    private void d() {
        this.f3555h = a("wear_zoom_in.png");
        this.i = a("wear_zoom_in_pressed.png");
        this.j = a("wear_zoon_out.png");
        this.k = a("wear_zoom_out_pressed.png");
    }

    public void a(View.OnClickListener onClickListener) {
        this.f3548a.setOnClickListener(onClickListener);
    }

    public void a(boolean z) {
        ImageView imageView;
        Bitmap bitmap;
        this.f3548a.setEnabled(z);
        if (z) {
            imageView = this.f3548a;
            bitmap = this.f3551d;
        } else {
            imageView = this.f3548a;
            bitmap = this.f3552e;
        }
        imageView.setImageBitmap(bitmap);
    }

    public boolean a() {
        return this.n;
    }

    public void b() {
        Bitmap bitmap = this.f3551d;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.f3551d.recycle();
            this.f3551d = null;
        }
        Bitmap bitmap2 = this.f3552e;
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            this.f3552e.recycle();
            this.f3552e = null;
        }
        Bitmap bitmap3 = this.f3553f;
        if (bitmap3 != null && !bitmap3.isRecycled()) {
            this.f3553f.recycle();
            this.f3553f = null;
        }
        Bitmap bitmap4 = this.f3554g;
        if (bitmap4 != null && !bitmap4.isRecycled()) {
            this.f3554g.recycle();
            this.f3554g = null;
        }
        Bitmap bitmap5 = this.f3555h;
        if (bitmap5 != null && !bitmap5.isRecycled()) {
            this.f3555h.recycle();
            this.f3555h = null;
        }
        Bitmap bitmap6 = this.i;
        if (bitmap6 != null && !bitmap6.isRecycled()) {
            this.i.recycle();
            this.i = null;
        }
        Bitmap bitmap7 = this.j;
        if (bitmap7 != null && !bitmap7.isRecycled()) {
            this.j.recycle();
            this.j = null;
        }
        Bitmap bitmap8 = this.k;
        if (bitmap8 == null || bitmap8.isRecycled()) {
            return;
        }
        this.k.recycle();
        this.k = null;
    }

    public void b(View.OnClickListener onClickListener) {
        this.f3549b.setOnClickListener(onClickListener);
    }

    public void b(boolean z) {
        ImageView imageView;
        Bitmap bitmap;
        this.f3549b.setEnabled(z);
        if (z) {
            imageView = this.f3549b;
            bitmap = this.f3553f;
        } else {
            imageView = this.f3549b;
            bitmap = this.f3554g;
        }
        imageView.setImageBitmap(bitmap);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ImageView imageView;
        Bitmap bitmap;
        ImageView imageView2;
        String str;
        if (!(view instanceof ImageView)) {
            return false;
        }
        int id = ((ImageView) view).getId();
        if (id == 0) {
            if (motionEvent.getAction() == 0) {
                if (this.m) {
                    imageView = this.f3548a;
                    bitmap = this.i;
                    imageView.setImageBitmap(bitmap);
                    return false;
                }
                imageView2 = this.f3548a;
                str = "main_topbtn_down.9.png";
                a(imageView2, str);
                return false;
            }
            if (motionEvent.getAction() != 1) {
                return false;
            }
            if (this.m) {
                imageView = this.f3548a;
                bitmap = this.f3555h;
                imageView.setImageBitmap(bitmap);
                return false;
            }
            imageView2 = this.f3548a;
            str = "main_topbtn_up.9.png";
            a(imageView2, str);
            return false;
        }
        if (id != 1) {
            return false;
        }
        if (motionEvent.getAction() == 0) {
            if (this.m) {
                imageView = this.f3549b;
                bitmap = this.k;
                imageView.setImageBitmap(bitmap);
                return false;
            }
            imageView2 = this.f3549b;
            str = "main_bottombtn_down.9.png";
            a(imageView2, str);
            return false;
        }
        if (motionEvent.getAction() != 1) {
            return false;
        }
        if (this.m) {
            imageView = this.f3549b;
            bitmap = this.j;
            imageView.setImageBitmap(bitmap);
            return false;
        }
        imageView2 = this.f3549b;
        str = "main_bottombtn_up.9.png";
        a(imageView2, str);
        return false;
    }
}