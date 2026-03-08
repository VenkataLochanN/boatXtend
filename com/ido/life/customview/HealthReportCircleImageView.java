package com.ido.life.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

/* JADX INFO: loaded from: classes2.dex */
public class HealthReportCircleImageView extends AppCompatImageView {
    private Bitmap mBitmap;
    private final Paint mPaint;
    private float mScale;

    public HealthReportCircleImageView(Context context) {
        this(context, null);
    }

    public HealthReportCircleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HealthReportCircleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPaint = new Paint();
        this.mScale = 1.0f;
        this.mPaint.setDither(true);
        this.mPaint.setStyle(Paint.Style.FILL);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        setup();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.mBitmap = bitmap;
        setup();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        this.mBitmap = getBitmapFromDrawable(drawable);
        setup();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        this.mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        this.mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            drawable.draw(new Canvas(bitmapCreateBitmap));
            return bitmapCreateBitmap;
        } catch (Exception unused) {
            return null;
        }
    }

    private void setup() {
        if (this.mBitmap == null) {
            return;
        }
        updateShaderMatrix();
        invalidate();
    }

    private void updateShaderMatrix() {
        this.mScale = Math.max((getWidth() * 1.0f) / this.mBitmap.getWidth(), (getHeight() * 1.0f) / this.mBitmap.getHeight());
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mBitmap == null) {
            return;
        }
        Path path = new Path();
        path.addCircle(getWidth(), getHeight(), getWidth() / 2, Path.Direction.CW);
        canvas.clipPath(path);
        int iSave = canvas.save();
        Matrix matrix = new Matrix();
        float f2 = this.mScale;
        matrix.postScale(f2, f2);
        canvas.drawBitmap(this.mBitmap, matrix, this.mPaint);
        canvas.restoreToCount(iSave);
    }
}