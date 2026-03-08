package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.VirtualEarthProjection;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.google.android.gms.common.util.GmsVersion;
import com.tencent.bugly.BuglyStrategy;
import net.sqlcipher.database.SQLiteDatabase;

/* JADX INFO: compiled from: ScaleView.java */
/* JADX INFO: loaded from: classes.dex */
public class fl extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f893a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f894b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private IAMapDelegate f895c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Paint f896d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Paint f897e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Rect f898f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private IPoint f899g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f900h;
    private final int[] i;

    public void a() {
        this.f896d = null;
        this.f897e = null;
        this.f898f = null;
        this.f893a = null;
        this.f899g = null;
    }

    public fl(Context context, IAMapDelegate iAMapDelegate) {
        super(context);
        this.f893a = "";
        this.f894b = 0;
        this.f900h = 0.0f;
        this.i = new int[]{10000000, GmsVersion.VERSION_LONGHORN, 2000000, 1000000, 500000, 200000, BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH, SQLiteDatabase.SQLITE_MAX_LIKE_PATTERN_LENGTH, 30000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 25, 10, 5};
        this.f895c = iAMapDelegate;
        this.f896d = new Paint();
        this.f898f = new Rect();
        this.f896d.setAntiAlias(true);
        this.f896d.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.f896d.setStrokeWidth(m.f1683a * 2.0f);
        this.f896d.setStyle(Paint.Style.STROKE);
        this.f897e = new Paint();
        this.f897e.setAntiAlias(true);
        this.f897e.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.f897e.setTextSize(m.f1683a * 20.0f);
        this.f900h = el.a(context, 1.0f);
        this.f899g = new IPoint();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Point waterMarkerPositon;
        String str = this.f893a;
        if (str == null || "".equals(str) || this.f894b == 0 || (waterMarkerPositon = this.f895c.getWaterMarkerPositon()) == null) {
            return;
        }
        Paint paint = this.f897e;
        String str2 = this.f893a;
        paint.getTextBounds(str2, 0, str2.length(), this.f898f);
        int i = waterMarkerPositon.x;
        int iHeight = (waterMarkerPositon.y - this.f898f.height()) + 5;
        canvas.drawText(this.f893a, ((this.f894b - this.f898f.width()) / 2) + i, iHeight, this.f897e);
        float f2 = i;
        float fHeight = iHeight + (this.f898f.height() - 5);
        canvas.drawLine(f2, fHeight - (this.f900h * 2.0f), f2, fHeight + m.f1683a, this.f896d);
        canvas.drawLine(f2, fHeight, this.f894b + i, fHeight, this.f896d);
        int i2 = this.f894b;
        canvas.drawLine(i + i2, fHeight - (this.f900h * 2.0f), i + i2, fHeight + m.f1683a, this.f896d);
    }

    public void a(String str) {
        this.f893a = str;
    }

    public void a(int i) {
        this.f894b = i;
    }

    public void a(boolean z) {
        if (z) {
            setVisibility(0);
            b();
        } else {
            a("");
            a(0);
            setVisibility(8);
        }
    }

    public void b() {
        IAMapDelegate iAMapDelegate = this.f895c;
        if (iAMapDelegate == null) {
            return;
        }
        try {
            float preciseLevel = iAMapDelegate.getPreciseLevel(1);
            this.f895c.getGeoCenter(1, this.f899g);
            if (this.f899g == null) {
                return;
            }
            DPoint dPointPixelsToLatLong = VirtualEarthProjection.pixelsToLatLong(this.f899g.x, this.f899g.y, 20);
            float mapZoomScale = this.f895c.getMapZoomScale();
            double dCos = (float) ((((Math.cos((dPointPixelsToLatLong.y * 3.141592653589793d) / 180.0d) * 2.0d) * 3.141592653589793d) * 6378137.0d) / (Math.pow(2.0d, preciseLevel) * 256.0d));
            int i = (int) preciseLevel;
            int i2 = (int) (((double) this.i[i]) / (dCos * ((double) mapZoomScale)));
            String strA = er.a(this.i[i]);
            a(i2);
            a(strA);
            dPointPixelsToLatLong.recycle();
            invalidate();
        } catch (Throwable th) {
            hn.c(th, "AMapDelegateImpGLSurfaceView", "changeScaleState");
            th.printStackTrace();
        }
    }
}