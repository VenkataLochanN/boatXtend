package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/* JADX INFO: compiled from: BlankView.java */
/* JADX INFO: loaded from: classes.dex */
public class ff extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f831a = Color.argb(255, 235, 235, 235);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f832b = Color.argb(255, 21, 21, 21);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Paint f833c;

    public ff(Context context) {
        super(context);
        this.f833c = new Paint();
        this.f833c.setAntiAlias(true);
        this.f833c.setColor(f831a);
    }

    public void a(int i) {
        Paint paint = this.f833c;
        if (paint != null) {
            paint.setColor(i);
            try {
                invalidate();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.f833c);
    }

    public void a(boolean z) {
        if (z) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }
}