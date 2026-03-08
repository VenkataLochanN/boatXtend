package com.ido.life.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.ido.life.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PressureAjustProgressView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J\u0018\u0010-\u001a\u00020*2\u0006\u0010.\u001a\u00020\u000b2\u0006\u0010/\u001a\u00020\u000bH\u0014R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001a\u0010\u0013\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\r\"\u0004\b\u001e\u0010\u000fR\u000e\u0010\u001f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\r\"\u0004\b\"\u0010\u000fR\u001a\u0010#\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(¨\u00060"}, d2 = {"Lcom/ido/life/customview/PressureAjustProgressView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attribute", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "TAG", "", "kotlin.jvm.PlatformType", "mColorNormal", "", "getMColorNormal", "()I", "setMColorNormal", "(I)V", "mColorProgress", "getMColorProgress", "setMColorProgress", "mMax", "getMMax", "setMMax", "mPaint", "Landroid/graphics/Paint;", "getMPaint", "()Landroid/graphics/Paint;", "setMPaint", "(Landroid/graphics/Paint;)V", "mProgress", "getMProgress", "setMProgress", "mProgressAngle", "mProgressHeight", "getMProgressHeight", "setMProgressHeight", "mProgressWhiteRadius", "", "getMProgressWhiteRadius", "()F", "setMProgressWhiteRadius", "(F)V", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "app_release"}, k = 1, mv = {1, 1, 16})
public final class PressureAjustProgressView extends View {
    private final String TAG;
    private HashMap _$_findViewCache;
    private int mColorNormal;
    private int mColorProgress;
    private int mMax;
    private Paint mPaint;
    private int mProgress;
    private int mProgressAngle;
    private int mProgressHeight;
    private float mProgressWhiteRadius;

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PressureAjustProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = PressureAjustProgressView.class.getSimpleName();
        this.mColorNormal = Color.parseColor("#F1F1F5");
        this.mColorProgress = Color.parseColor("#AEA8A8");
        this.mMax = 100;
        this.mProgressWhiteRadius = 0.5f;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PressureAjustProgressView);
            Intrinsics.checkExpressionValueIsNotNull(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…ressureAjustProgressView)");
            this.mColorNormal = typedArrayObtainStyledAttributes.getColor(0, this.mColorNormal);
            this.mColorProgress = typedArrayObtainStyledAttributes.getColor(2, this.mColorProgress);
            this.mMax = typedArrayObtainStyledAttributes.getInt(4, this.mMax);
            this.mProgress = typedArrayObtainStyledAttributes.getInt(1, this.mProgress);
            this.mProgressHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, this.mProgressHeight);
            this.mProgressWhiteRadius = typedArrayObtainStyledAttributes.getFloat(5, this.mProgressWhiteRadius);
            typedArrayObtainStyledAttributes.recycle();
        }
        this.mPaint = new Paint(1);
    }

    public final int getMColorNormal() {
        return this.mColorNormal;
    }

    public final void setMColorNormal(int i) {
        this.mColorNormal = i;
    }

    public final int getMColorProgress() {
        return this.mColorProgress;
    }

    public final void setMColorProgress(int i) {
        this.mColorProgress = i;
    }

    public final int getMMax() {
        return this.mMax;
    }

    public final void setMMax(int i) {
        this.mMax = i;
    }

    public final int getMProgress() {
        return this.mProgress;
    }

    public final void setMProgress(int i) {
        this.mProgress = i;
    }

    public final int getMProgressHeight() {
        return this.mProgressHeight;
    }

    public final void setMProgressHeight(int i) {
        this.mProgressHeight = i;
    }

    public final float getMProgressWhiteRadius() {
        return this.mProgressWhiteRadius;
    }

    public final void setMProgressWhiteRadius(float f2) {
        this.mProgressWhiteRadius = f2;
    }

    public final Paint getMPaint() {
        return this.mPaint;
    }

    public final void setMPaint(Paint paint) {
        Intrinsics.checkParameterIsNotNull(paint, "<set-?>");
        this.mPaint = paint;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getWidth() / 2.0f;
        float f2 = 360.0f / this.mMax;
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        int i = this.mMax + 1;
        float f3 = -90.0f;
        for (int i2 = 1; i2 < i; i2++) {
            if (canvas != null) {
                canvas.rotate(f3, width, width);
            }
            if (this.mProgress < i2) {
                this.mPaint.setColor(this.mColorNormal);
            } else {
                this.mPaint.setColor(this.mColorProgress);
            }
            if (canvas != null) {
                canvas.drawLine(width, width, getWidth(), width, this.mPaint);
            }
            if (canvas != null) {
                canvas.rotate(-f3, width, width);
            }
            f3 += f2;
        }
        this.mPaint.setColor(-1);
        if (canvas != null) {
            canvas.drawCircle(width, width, width - this.mProgressHeight, this.mPaint);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(size, size);
    }
}