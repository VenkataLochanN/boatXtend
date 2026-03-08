package com.amap.api.maps.offlinemap;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import com.amap.api.mapcore.util.a;

/* JADX INFO: loaded from: classes.dex */
public class DownloadProgressView extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1885a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1886b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1887c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f1888d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f1889e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private TextPaint f1890f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private TextPaint f1891g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f1892h;
    private float i;

    public DownloadProgressView(Context context) {
        super(context);
        this.f1886b = -65536;
        this.f1887c = -65536;
        this.f1888d = 0.0f;
        this.f1889e = 0.6f;
        a(null, 0);
    }

    public DownloadProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1886b = -65536;
        this.f1887c = -65536;
        this.f1888d = 0.0f;
        this.f1889e = 0.6f;
        a(attributeSet, 0);
    }

    public DownloadProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1886b = -65536;
        this.f1887c = -65536;
        this.f1888d = 0.0f;
        this.f1889e = 0.6f;
        a(attributeSet, i);
    }

    private void a(AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, a.C0006a.f103a, i, 0);
        this.f1885a = typedArrayObtainStyledAttributes.getString(0);
        this.f1886b = typedArrayObtainStyledAttributes.getColor(3, this.f1886b);
        this.f1888d = typedArrayObtainStyledAttributes.getDimension(1, this.f1888d);
        this.f1887c = typedArrayObtainStyledAttributes.getColor(2, this.f1887c);
        typedArrayObtainStyledAttributes.recycle();
        this.f1890f = new TextPaint();
        this.f1890f.setFlags(1);
        this.f1890f.setTextAlign(Paint.Align.RIGHT);
        this.f1891g = new TextPaint();
        this.f1891g.setStyle(Paint.Style.FILL);
        a();
    }

    private void a() {
        this.f1890f.setTextSize(this.f1888d);
        this.f1890f.setColor(this.f1886b);
        this.f1891g.setColor(this.f1887c);
        this.f1892h = this.f1890f.measureText(this.f1885a);
        this.i = this.f1890f.getFontMetrics().bottom;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int width = (getWidth() - paddingLeft) - paddingRight;
        int height = (getHeight() - paddingTop) - paddingBottom;
        String str = String.valueOf((int) (this.f1889e * 100.0f)) + "%";
        canvas.drawRect(new Rect(0, (int) (height * 0.8f), (int) (width * this.f1889e), height), this.f1891g);
        canvas.drawText(str, (int) (this.f1889e * r3), (int) (r1 - 3.0d), this.f1890f);
    }

    public void setProgress(int i) {
        if (i > 100 || i < 0) {
            return;
        }
        this.f1889e = i / 100.0f;
        invalidate();
    }
}