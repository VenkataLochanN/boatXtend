package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import java.io.File;
import java.io.InputStream;

/* JADX INFO: compiled from: WaterMarkerView.java */
/* JADX INFO: loaded from: classes.dex */
public class fm extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Bitmap f901a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Bitmap f902b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Bitmap f903c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Bitmap f904d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Bitmap f905e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Bitmap f906f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Bitmap f907g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Paint f908h;
    private boolean i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private boolean r;
    private boolean s;
    private Context t;
    private boolean u;
    private float v;
    private float w;
    private boolean x;
    private boolean y;

    public void a() {
        try {
            if (this.f901a != null) {
                er.b(this.f901a);
                this.f901a = null;
            }
            if (this.f902b != null) {
                er.b(this.f902b);
                this.f902b = null;
            }
            this.f901a = null;
            this.f902b = null;
            if (this.f906f != null) {
                er.b(this.f906f);
                this.f906f = null;
            }
            if (this.f907g != null) {
                er.b(this.f907g);
                this.f907g = null;
            }
            if (this.f903c != null) {
                er.b(this.f903c);
            }
            this.f903c = null;
            if (this.f904d != null) {
                er.b(this.f904d);
            }
            this.f904d = null;
            if (this.f905e != null) {
                this.f905e.recycle();
            }
            this.f908h = null;
        } catch (Throwable th) {
            hn.c(th, "WaterMarkerView", "destory");
            th.printStackTrace();
        }
    }

    public fm(Context context, IAMapDelegate iAMapDelegate) {
        InputStream inputStreamOpen;
        super(context);
        this.f908h = new Paint();
        this.i = false;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 10;
        this.n = 0;
        this.o = 0;
        this.p = 10;
        this.q = 8;
        this.r = false;
        this.s = false;
        this.u = true;
        this.v = 0.0f;
        this.w = 0.0f;
        this.x = true;
        this.y = false;
        if (this.u) {
            InputStream inputStreamOpen2 = null;
            try {
                try {
                    this.t = context.getApplicationContext();
                    inputStreamOpen = el.a(context).open("ap.data");
                } catch (Throwable th) {
                    th = th;
                    inputStreamOpen = null;
                }
                try {
                    this.f906f = BitmapFactory.decodeStream(inputStreamOpen);
                    this.f901a = er.a(this.f906f, m.f1683a);
                    inputStreamOpen.close();
                    inputStreamOpen2 = el.a(context).open("ap1.data");
                    this.f907g = BitmapFactory.decodeStream(inputStreamOpen2);
                    this.f902b = er.a(this.f907g, m.f1683a);
                    inputStreamOpen2.close();
                    this.k = this.f902b.getWidth();
                    this.j = this.f902b.getHeight();
                    this.f908h.setAntiAlias(true);
                    this.f908h.setColor(ViewCompat.MEASURED_STATE_MASK);
                    this.f908h.setStyle(Paint.Style.STROKE);
                    AMapEngineUtils.LOGO_CUSTOM_ICON_DAY_NAME = context.getFilesDir() + "/icon_web_day.data";
                    AMapEngineUtils.LOGO_CUSTOM_ICON_NIGHT_NAME = context.getFilesDir() + "/icon_web_night.data";
                    eq.a().a(new Runnable() { // from class: com.amap.api.mapcore.util.fm.1
                        @Override // java.lang.Runnable
                        public void run() {
                            fm.this.a(AMapEngineUtils.LOGO_CUSTOM_ICON_DAY_NAME, 0);
                            fm.this.a(AMapEngineUtils.LOGO_CUSTOM_ICON_NIGHT_NAME, 1);
                            if ("".equals(eh.a(fm.this.t, "amap_web_logo", "md5_day", ""))) {
                                if (fm.this.f903c == null || fm.this.f904d == null) {
                                    eh.a(fm.this.t, "amap_web_logo", "md5_day", (Object) "f3a1627fe912c49ecdcd4ab92a5d6bc8");
                                    eh.a(fm.this.t, "amap_web_logo", "md5_night", (Object) "61733cf36c9727db08c2ef727490c036");
                                    return;
                                }
                                eh.a(fm.this.t, "amap_web_logo", "md5_day", (Object) gq.a(AMapEngineUtils.LOGO_CUSTOM_ICON_DAY_NAME));
                                String strA = gq.a(AMapEngineUtils.LOGO_CUSTOM_ICON_NIGHT_NAME);
                                if (!"".equals(strA)) {
                                    eh.a(fm.this.t, "amap_web_logo", "md5_night", (Object) strA);
                                }
                                fm.this.d(true);
                            }
                        }
                    });
                    if (inputStreamOpen != null) {
                        try {
                            inputStreamOpen.close();
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        hn.c(th, "WaterMarkerView", "create");
                        if (inputStreamOpen != null) {
                            try {
                                inputStreamOpen.close();
                            } catch (Throwable th4) {
                                th4.printStackTrace();
                            }
                        }
                        if (inputStreamOpen2 == null) {
                            return;
                        } else {
                            inputStreamOpen2.close();
                        }
                    } finally {
                    }
                }
                if (inputStreamOpen2 != null) {
                    inputStreamOpen2.close();
                }
            } catch (Throwable th5) {
                th5.printStackTrace();
            }
        }
    }

    public Bitmap b() {
        Bitmap bitmap;
        Bitmap bitmap2;
        Bitmap bitmap3;
        return (!this.y || (bitmap3 = this.f905e) == null) ? this.i ? (!this.s || (bitmap2 = this.f904d) == null) ? this.f902b : bitmap2 : (!this.s || (bitmap = this.f903c) == null) ? this.f901a : bitmap : bitmap3;
    }

    public void a(boolean z) {
        if (this.u) {
            try {
                this.i = z;
                if (z) {
                    this.f908h.setColor(-1);
                } else {
                    this.f908h.setColor(ViewCompat.MEASURED_STATE_MASK);
                }
            } catch (Throwable th) {
                hn.c(th, "WaterMarkerView", "changeBitmap");
                th.printStackTrace();
            }
        }
    }

    public Point c() {
        return new Point(this.m, this.n - 2);
    }

    public void a(int i) {
        this.o = 0;
        this.l = i;
        d();
    }

    public void b(int i) {
        this.o = 1;
        this.q = i;
        d();
    }

    public void c(int i) {
        this.o = 1;
        this.p = i;
        d();
    }

    public float d(int i) {
        float f2;
        if (!this.u) {
            return 0.0f;
        }
        if (i == 0) {
            return this.v;
        }
        if (i == 1) {
            f2 = this.v;
        } else {
            if (i != 2) {
                return 0.0f;
            }
            f2 = this.w;
        }
        return 1.0f - f2;
    }

    public void a(int i, float f2) {
        if (this.u) {
            this.o = 2;
            float fMax = Math.max(0.0f, Math.min(f2, 1.0f));
            if (i == 0) {
                this.v = fMax;
                this.x = true;
            } else if (i == 1) {
                this.v = 1.0f - fMax;
                this.x = false;
            } else if (i == 2) {
                this.w = 1.0f - fMax;
            }
            d();
        }
    }

    public void d() {
        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        f();
        postInvalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        try {
            if (!this.u || getWidth() == 0 || getHeight() == 0 || this.f902b == null) {
                return;
            }
            if (!this.r) {
                f();
                this.r = true;
            }
            canvas.drawBitmap(b(), this.m, this.n, this.f908h);
        } catch (Throwable th) {
            hn.c(th, "WaterMarkerView", "onDraw");
            th.printStackTrace();
        }
    }

    private void f() {
        int i = this.o;
        if (i == 0) {
            h();
        } else if (i != 1 && i == 2) {
            g();
        }
        this.m = this.p;
        this.n = (getHeight() - this.q) - this.j;
        if (this.m < 0) {
            this.m = 0;
        }
        if (this.n < 0) {
            this.n = 0;
        }
    }

    private void g() {
        if (this.x) {
            this.p = (int) (getWidth() * this.v);
        } else {
            this.p = (int) ((getWidth() * this.v) - this.k);
        }
        this.q = (int) (getHeight() * this.w);
    }

    private void h() {
        int i = this.l;
        if (i == 1) {
            this.p = (getWidth() - this.k) / 2;
        } else if (i == 2) {
            this.p = (getWidth() - this.k) - 10;
        } else {
            this.p = 10;
        }
        this.q = 8;
    }

    public void a(String str, int i) {
        try {
            if (this.u && new File(str).exists()) {
                if (i == 0) {
                    Bitmap bitmap = this.f903c;
                    this.f906f = BitmapFactory.decodeFile(str);
                    this.f903c = er.a(this.f906f, m.f1683a);
                    if (bitmap != null && !bitmap.isRecycled()) {
                        er.b(bitmap);
                    }
                } else if (i == 1) {
                    Bitmap bitmap2 = this.f904d;
                    this.f906f = BitmapFactory.decodeFile(str);
                    this.f904d = er.a(this.f906f, m.f1683a);
                    if (bitmap2 != null && !bitmap2.isRecycled()) {
                        er.b(bitmap2);
                    }
                }
            }
        } catch (Throwable th) {
            hn.c(th, "WaterMarkerView", "create");
            th.printStackTrace();
        }
    }

    public void b(boolean z) {
        if (this.u) {
            this.y = z;
            if (z) {
                Bitmap bitmap = this.f905e;
                if (bitmap != null) {
                    this.k = bitmap.getWidth();
                    this.j = this.f905e.getHeight();
                    return;
                }
                return;
            }
            this.k = this.f901a.getWidth();
            this.j = this.f901a.getHeight();
        }
    }

    public void c(boolean z) {
        this.u = z;
    }

    public void d(boolean z) {
        if (this.u && this.s != z) {
            this.s = z;
            if (z) {
                if (this.i) {
                    Bitmap bitmap = this.f904d;
                    if (bitmap != null) {
                        this.k = bitmap.getWidth();
                        this.j = this.f904d.getHeight();
                        return;
                    }
                    return;
                }
                Bitmap bitmap2 = this.f903c;
                if (bitmap2 != null) {
                    this.k = bitmap2.getWidth();
                    this.j = this.f903c.getHeight();
                    return;
                }
                return;
            }
            this.k = this.f901a.getWidth();
            this.j = this.f901a.getHeight();
        }
    }

    public boolean e() {
        return this.i;
    }
}