package com.ido.life.customview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import com.ido.ble.event.stat.one.d;
import com.ido.life.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MultiplTextView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010C\u001a\u00020\u001eH\u0002J\u0010\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020GH\u0002J\b\u0010H\u001a\u00020EH\u0002J\u001a\u0010I\u001a\u00020E2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010J\u001a\u00020E2\u0006\u0010F\u001a\u00020GH\u0014J\u0018\u0010K\u001a\u00020E2\u0006\u0010L\u001a\u00020\t2\u0006\u0010M\u001a\u00020\tH\u0014J\u0010\u0010N\u001a\u00020E2\u0006\u0010F\u001a\u00020GH\u0002J\u0006\u0010O\u001a\u00020ER\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u001a\u0010\u0014\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010%\"\u0004\b*\u0010'R\u001a\u0010+\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010%\"\u0004\b-\u0010'R\u000e\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00102\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010%\"\u0004\b4\u0010'R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010:\u001a\u0004\u0018\u00010;X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001a\u0010@\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010%\"\u0004\bB\u0010'¨\u0006P"}, d2 = {"Lcom/ido/life/customview/MultiplTextView;", "Landroidx/appcompat/widget/AppCompatTextView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "bGradient", "", "getBGradient", "()Z", "setBGradient", "(Z)V", "bRemoveDefaultPadding", "getBRemoveDefaultPadding", "setBRemoveDefaultPadding", "bRunText", "getBRunText", "setBRunText", "fontMetricsInt", "Landroid/graphics/Paint$FontMetricsInt;", "getFontMetricsInt", "()Landroid/graphics/Paint$FontMetricsInt;", "setFontMetricsInt", "(Landroid/graphics/Paint$FontMetricsInt;)V", "fontPath", "", "getFontPath", "()Ljava/lang/String;", "setFontPath", "(Ljava/lang/String;)V", "gradientCenterColor", "getGradientCenterColor", "()I", "setGradientCenterColor", "(I)V", "gradientStartColor", "getGradientStartColor", "setGradientStartColor", "gradietendColor", "getGradietendColor", "setGradietendColor", "mBounds", "Landroid/graphics/Rect;", "mGradientMatrix", "Landroid/graphics/Matrix;", "mHeight", "getMHeight", "setMHeight", "mLinearGradient", "Landroid/graphics/LinearGradient;", "mPaint", "Landroid/graphics/Paint;", "mTranslate", "mTypeface", "Landroid/graphics/Typeface;", "getMTypeface", "()Landroid/graphics/Typeface;", "setMTypeface", "(Landroid/graphics/Typeface;)V", "mWidth", "getMWidth", "setMWidth", "calculateTextParams", "drawText", "", "canvas", "Landroid/graphics/Canvas;", d.m, "initAttributes", "onDraw", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "option", "runText", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MultiplTextView extends AppCompatTextView {
    private HashMap _$_findViewCache;
    private boolean bGradient;
    private boolean bRemoveDefaultPadding;
    private boolean bRunText;
    private Paint.FontMetricsInt fontMetricsInt;
    private String fontPath;
    private int gradientCenterColor;
    private int gradientStartColor;
    private int gradietendColor;
    private final Rect mBounds;
    private Matrix mGradientMatrix;
    private int mHeight;
    private LinearGradient mLinearGradient;
    private Paint mPaint;
    private int mTranslate;
    private Typeface mTypeface;
    private int mWidth;

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

    public final int getMWidth() {
        return this.mWidth;
    }

    public final void setMWidth(int i) {
        this.mWidth = i;
    }

    public final int getMHeight() {
        return this.mHeight;
    }

    public final void setMHeight(int i) {
        this.mHeight = i;
    }

    public final Paint.FontMetricsInt getFontMetricsInt() {
        return this.fontMetricsInt;
    }

    public final void setFontMetricsInt(Paint.FontMetricsInt fontMetricsInt) {
        this.fontMetricsInt = fontMetricsInt;
    }

    public final boolean getBRunText() {
        return this.bRunText;
    }

    public final void setBRunText(boolean z) {
        this.bRunText = z;
    }

    public final boolean getBRemoveDefaultPadding() {
        return this.bRemoveDefaultPadding;
    }

    public final void setBRemoveDefaultPadding(boolean z) {
        this.bRemoveDefaultPadding = z;
    }

    public final String getFontPath() {
        return this.fontPath;
    }

    public final void setFontPath(String str) {
        this.fontPath = str;
    }

    public final boolean getBGradient() {
        return this.bGradient;
    }

    public final void setBGradient(boolean z) {
        this.bGradient = z;
    }

    public final int getGradientStartColor() {
        return this.gradientStartColor;
    }

    public final void setGradientStartColor(int i) {
        this.gradientStartColor = i;
    }

    public final int getGradientCenterColor() {
        return this.gradientCenterColor;
    }

    public final void setGradientCenterColor(int i) {
        this.gradientCenterColor = i;
    }

    public final int getGradietendColor() {
        return this.gradietendColor;
    }

    public final void setGradietendColor(int i) {
        this.gradietendColor = i;
    }

    public final Typeface getMTypeface() {
        return this.mTypeface;
    }

    public final void setMTypeface(Typeface typeface) {
        this.mTypeface = typeface;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiplTextView(Context context) {
        super(context);
        if (context == null) {
            Intrinsics.throwNpe();
        }
        this.mBounds = new Rect();
        this.mPaint = getPaint();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiplTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mBounds = new Rect();
        this.mPaint = getPaint();
        initAttributes(context, attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiplTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mBounds = new Rect();
        this.mPaint = getPaint();
        initAttributes(context, attributeSet);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        init();
        option(canvas);
        drawText(canvas);
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        this.mHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        if (this.bRemoveDefaultPadding) {
            calculateTextParams();
            setMeasuredDimension(this.mBounds.right - this.mBounds.left, (-this.mBounds.top) + this.mBounds.bottom);
        }
    }

    private final void init() {
        this.mLinearGradient = new LinearGradient(0.0f, 0.0f, getMeasuredWidth(), 0.0f, new int[]{this.gradientStartColor, this.gradientCenterColor, this.gradietendColor}, (float[]) null, Shader.TileMode.MIRROR);
        this.mGradientMatrix = new Matrix();
    }

    private final void initAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.MultiplTextView);
        Intrinsics.checkExpressionValueIsNotNull(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…tyleable.MultiplTextView)");
        this.bRunText = typedArrayObtainStyledAttributes.getBoolean(5, false);
        this.bGradient = typedArrayObtainStyledAttributes.getBoolean(0, false);
        this.bRemoveDefaultPadding = typedArrayObtainStyledAttributes.getBoolean(4, false);
        this.gradientStartColor = typedArrayObtainStyledAttributes.getColor(3, getCurrentTextColor());
        this.gradientCenterColor = typedArrayObtainStyledAttributes.getColor(1, getCurrentTextColor());
        this.gradietendColor = typedArrayObtainStyledAttributes.getColor(2, ViewCompat.MEASURED_STATE_MASK);
        this.fontPath = typedArrayObtainStyledAttributes.getString(6);
        if (!TextUtils.isEmpty(this.fontPath)) {
            Resources resources = getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
            this.mTypeface = Typeface.createFromAsset(resources.getAssets(), this.fontPath);
            Paint paint = this.mPaint;
            if (paint == null) {
                Intrinsics.throwNpe();
            }
            paint.setTypeface(this.mTypeface);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private final void option(Canvas canvas) {
        if (this.bGradient) {
            Paint paint = this.mPaint;
            if (paint == null) {
                Intrinsics.throwNpe();
            }
            paint.setShader(this.mLinearGradient);
        }
        if (this.bRunText) {
            runText();
        }
    }

    public final void runText() {
        if (this.mGradientMatrix != null) {
            int i = this.mTranslate;
            int i2 = this.mWidth;
            this.mTranslate = i + (i2 / 5);
            if (this.mTranslate > i2 * 2) {
                this.mTranslate = -i2;
            }
            Matrix matrix = this.mGradientMatrix;
            if (matrix == null) {
                Intrinsics.throwNpe();
            }
            matrix.setTranslate(this.mTranslate, 0.0f);
            LinearGradient linearGradient = this.mLinearGradient;
            if (linearGradient == null) {
                Intrinsics.throwNpe();
            }
            linearGradient.setLocalMatrix(this.mGradientMatrix);
            postInvalidateDelayed(100L);
        }
    }

    private final String calculateTextParams() {
        String string = getText().toString();
        int length = string.length();
        Paint paint = this.mPaint;
        if (paint == null) {
            Intrinsics.throwNpe();
        }
        paint.getTextBounds(string, 0, length, this.mBounds);
        if (length == 0) {
            Rect rect = this.mBounds;
            rect.right = rect.left;
        }
        return string;
    }

    private final void drawText(Canvas canvas) {
        String strCalculateTextParams = calculateTextParams();
        int i = this.mBounds.left;
        int i2 = this.mBounds.bottom;
        Rect rect = this.mBounds;
        rect.offset(-rect.left, -this.mBounds.top);
        Paint paint = this.mPaint;
        if (paint == null) {
            Intrinsics.throwNpe();
        }
        paint.setAntiAlias(true);
        Paint paint2 = this.mPaint;
        if (paint2 == null) {
            Intrinsics.throwNpe();
        }
        paint2.setColor(getCurrentTextColor());
        float f2 = -i;
        float f3 = this.mBounds.bottom - i2;
        Paint paint3 = this.mPaint;
        if (paint3 == null) {
            Intrinsics.throwNpe();
        }
        canvas.drawText(strCalculateTextParams, f2, f3, paint3);
    }
}