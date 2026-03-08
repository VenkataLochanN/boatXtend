package com.ido.life.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.ido.life.R;
import com.watch.life.wheelview.view.WheelView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomWheelView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0014R(\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u000e@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0017\"\u0004\b\u001e\u0010\u0019R\u001a\u0010\u001f\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0017\"\u0004\b!\u0010\u0019R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/ido/life/customview/CustomWheelView;", "Lcom/watch/life/wheelview/view/WheelView;", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "value", "", "mSelectUnit", "getMSelectUnit", "()Ljava/lang/String;", "setMSelectUnit", "(Ljava/lang/String;)V", "", "mUnitBold", "getMUnitBold", "()Z", "setUnitBold", "(Z)V", "mUnitLeftPadding", "", "getMUnitLeftPadding", "()I", "setMUnitLeftPadding", "(I)V", "mUnitPaint", "Landroid/graphics/Paint;", "mUnitTextColor", "getMUnitTextColor", "setMUnitTextColor", "mUnitTextSize", "getMUnitTextSize", "setMUnitTextSize", "mUnitXLocation", "", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "app_release"}, k = 1, mv = {1, 1, 16})
public final class CustomWheelView extends WheelView {
    private HashMap _$_findViewCache;
    private String mSelectUnit;
    private boolean mUnitBold;
    private int mUnitLeftPadding;
    private final Paint mUnitPaint;
    private int mUnitTextColor;
    private int mUnitTextSize;
    private float mUnitXLocation;

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
    public CustomWheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attributeSet");
        this.mUnitTextColor = ViewCompat.MEASURED_STATE_MASK;
        this.mUnitXLocation = -1.0f;
        this.mUnitBold = true;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomWheelView);
        Intrinsics.checkExpressionValueIsNotNull(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…tyleable.CustomWheelView)");
        setMSelectUnit(typedArrayObtainStyledAttributes.getString(0));
        this.mUnitLeftPadding = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, this.mUnitLeftPadding);
        this.mUnitTextColor = typedArrayObtainStyledAttributes.getColor(2, this.mUnitTextColor);
        this.mUnitTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, this.mUnitTextSize);
        typedArrayObtainStyledAttributes.recycle();
        this.mUnitPaint = new Paint();
        this.mUnitPaint.setStyle(Paint.Style.FILL);
        this.mUnitPaint.setTextSize(this.mUnitTextSize);
        this.mUnitPaint.setColor(this.mUnitTextColor);
        if (this.mUnitBold) {
            this.mUnitPaint.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            this.mUnitPaint.setTypeface(Typeface.DEFAULT);
        }
    }

    public final String getMSelectUnit() {
        return this.mSelectUnit;
    }

    public final void setMSelectUnit(String str) {
        this.mUnitXLocation = -1.0f;
        this.mSelectUnit = str;
    }

    public final int getMUnitLeftPadding() {
        return this.mUnitLeftPadding;
    }

    public final void setMUnitLeftPadding(int i) {
        this.mUnitLeftPadding = i;
    }

    public final int getMUnitTextColor() {
        return this.mUnitTextColor;
    }

    public final void setMUnitTextColor(int i) {
        this.mUnitTextColor = i;
    }

    public final int getMUnitTextSize() {
        return this.mUnitTextSize;
    }

    public final void setMUnitTextSize(int i) {
        this.mUnitTextSize = i;
    }

    public final boolean getMUnitBold() {
        return this.mUnitBold;
    }

    public final void setUnitBold(boolean z) {
        this.mUnitBold = z;
        if (z) {
            this.mUnitPaint.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            this.mUnitPaint.setTypeface(Typeface.DEFAULT);
        }
    }

    @Override // com.watch.life.wheelview.view.WheelView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (TextUtils.isEmpty(this.mSelectUnit) || !this.isCenterLabel) {
            return;
        }
        this.mUnitXLocation = Math.min((getMeasuredWidth() / 2.0f) + (this.maxTextWidth / 2.0f) + this.mUnitLeftPadding, (getMeasuredWidth() - this.mUnitPaint.measureText(this.mSelectUnit)) - 5);
        if (canvas == null) {
            Intrinsics.throwNpe();
        }
        String str = this.mSelectUnit;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        canvas.drawText(str, this.mUnitXLocation, this.centerY, this.mUnitPaint);
    }
}