package com.ido.life.customview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.ido.ble.event.stat.one.d;
import com.ido.life.R;
import com.realsil.sdk.core.utility.DensityUtils;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CirclePercentView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u0000 ;2\u00020\u0001:\u0001;B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\tH\u0002J\b\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020&H\u0002J\u0010\u0010(\u001a\u00020&2\u0006\u0010)\u001a\u00020*H\u0014J\u0018\u0010+\u001a\u00020&2\u0006\u0010,\u001a\u00020\t2\u0006\u0010-\u001a\u00020\tH\u0014J(\u0010.\u001a\u00020&2\u0006\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u00020\t2\u0006\u00101\u001a\u00020\t2\u0006\u00102\u001a\u00020\tH\u0014J\u000e\u00103\u001a\u00020&2\u0006\u0010\u000b\u001a\u00020\tJ\u000e\u00104\u001a\u00020&2\u0006\u0010\f\u001a\u00020\tJ\u000e\u00105\u001a\u00020&2\u0006\u0010\r\u001a\u00020\u0010J\u000e\u00106\u001a\u00020&2\u0006\u0010$\u001a\u00020\tJ\u000e\u00107\u001a\u00020&2\u0006\u0010\u001a\u001a\u00020\tJ\u000e\u00108\u001a\u00020&2\u0006\u0010\u001c\u001a\u00020\tJ\u000e\u00109\u001a\u00020&2\u0006\u0010\u001f\u001a\u00020\tJ\u0010\u0010:\u001a\u00020&2\b\u0010 \u001a\u0004\u0018\u00010!R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lcom/ido/life/customview/CirclePercentView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "bgColor", "endColor", "gradient", "Landroid/graphics/LinearGradient;", "isGradient", "", "mAnimator", "Landroid/animation/ValueAnimator;", "mPaint", "Landroid/graphics/Paint;", "mTextHeight", "mTextPaint", "Landroid/text/TextPaint;", "mTextSize", "mTextWidth", "progressColor", "progressPercent", "radius", "rectF", "Landroid/graphics/RectF;", "startColor", "text", "", "textColor", "getAnimator", "percentage", d.m, "", "measureTextSize", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onSizeChanged", "w", "h", "oldw", "oldh", "setBgColor", "setEndColor", "setGradient", "setPercentage", "setProgressColor", "setRadius", "setStartColor", "setText", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class CirclePercentView extends View {
    public static final int MAX = 100;
    public static final int WIDTH_RADIUS_RATIO = 8;
    private HashMap _$_findViewCache;
    private int bgColor;
    private int endColor;
    private LinearGradient gradient;
    private boolean isGradient;
    private ValueAnimator mAnimator;
    private Paint mPaint;
    private int mTextHeight;
    private TextPaint mTextPaint;
    private int mTextSize;
    private int mTextWidth;
    private int progressColor;
    private int progressPercent;
    private int radius;
    private RectF rectF;
    private int startColor;
    private String text;
    private int textColor;

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

    public CirclePercentView(Context context) {
        super(context);
        init();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CirclePercentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CirclePercentView);
        Intrinsics.checkExpressionValueIsNotNull(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…leable.CirclePercentView)");
        this.bgColor = typedArrayObtainStyledAttributes.getColor(0, getResources().getColor(com.boat.Xtend.two.R.color.white));
        this.progressColor = typedArrayObtainStyledAttributes.getColor(4, getResources().getColor(com.boat.Xtend.two.R.color.bg_color));
        this.radius = typedArrayObtainStyledAttributes.getDimensionPixelOffset(6, 8);
        this.isGradient = typedArrayObtainStyledAttributes.getBoolean(2, false);
        this.startColor = typedArrayObtainStyledAttributes.getColor(5, getResources().getColor(com.boat.Xtend.two.R.color.bg_color));
        this.endColor = typedArrayObtainStyledAttributes.getColor(1, getResources().getColor(com.boat.Xtend.two.R.color.bg_color));
        this.textColor = typedArrayObtainStyledAttributes.getColor(7, getResources().getColor(com.boat.Xtend.two.R.color.color_82868F));
        this.progressPercent = typedArrayObtainStyledAttributes.getInt(3, 0);
        this.mTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, DensityUtils.dip2px(context, 10.0f));
        typedArrayObtainStyledAttributes.recycle();
        init();
    }

    public CirclePercentView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    private final void measureTextSize() {
        if (TextUtils.isEmpty(this.text)) {
            return;
        }
        Rect rect = new Rect();
        TextPaint textPaint = this.mTextPaint;
        if (textPaint == null) {
            Intrinsics.throwNpe();
        }
        String str = this.text;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        textPaint.getTextBounds(str, 0, str.length(), rect);
        this.mTextWidth = rect.width();
        this.mTextHeight = rect.height();
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h2, int oldw, int oldh) {
        super.onSizeChanged(w, h2, oldw, oldh);
        this.gradient = new LinearGradient(getWidth(), 0.0f, getWidth(), getHeight(), this.startColor, this.endColor, Shader.TileMode.MIRROR);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        super.onDraw(canvas);
        int width = getWidth() / 2;
        int i = this.radius;
        Paint paint = this.mPaint;
        if (paint == null) {
            Intrinsics.throwNpe();
        }
        paint.setShader((Shader) null);
        Paint paint2 = this.mPaint;
        if (paint2 == null) {
            Intrinsics.throwNpe();
        }
        paint2.setStrokeWidth(i);
        Paint paint3 = this.mPaint;
        if (paint3 == null) {
            Intrinsics.throwNpe();
        }
        paint3.setColor(this.bgColor);
        float f2 = width;
        int i2 = i / 2;
        float f3 = width - i2;
        Paint paint4 = this.mPaint;
        if (paint4 == null) {
            Intrinsics.throwNpe();
        }
        canvas.drawCircle(f2, f2, f3, paint4);
        if (this.rectF == null) {
            float f4 = i2;
            float f5 = (width * 2) - i2;
            this.rectF = new RectF(f4, f4, f5, f5);
        }
        if (this.isGradient) {
            Paint paint5 = this.mPaint;
            if (paint5 == null) {
                Intrinsics.throwNpe();
            }
            paint5.setShader(this.gradient);
        } else {
            Paint paint6 = this.mPaint;
            if (paint6 == null) {
                Intrinsics.throwNpe();
            }
            paint6.setColor(this.progressColor);
        }
        RectF rectF = this.rectF;
        if (rectF == null) {
            Intrinsics.throwNpe();
        }
        float f6 = this.progressPercent * 3.6f;
        Paint paint7 = this.mPaint;
        if (paint7 == null) {
            Intrinsics.throwNpe();
        }
        canvas.drawArc(rectF, -90.0f, f6, false, paint7);
        int height = getHeight() / 2;
        if (TextUtils.isEmpty(this.text)) {
            return;
        }
        TextPaint textPaint = this.mTextPaint;
        if (textPaint == null) {
            Intrinsics.throwNpe();
        }
        textPaint.setTextSize(this.mTextSize);
        String str = this.text;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        float f7 = height + (this.mTextHeight / 2.0f);
        TextPaint textPaint2 = this.mTextPaint;
        if (textPaint2 == null) {
            Intrinsics.throwNpe();
        }
        canvas.drawText(str, f2, f7, textPaint2);
    }

    private final void init() {
        this.mPaint = new Paint();
        Paint paint = this.mPaint;
        if (paint == null) {
            Intrinsics.throwNpe();
        }
        paint.setStyle(Paint.Style.STROKE);
        Paint paint2 = this.mPaint;
        if (paint2 == null) {
            Intrinsics.throwNpe();
        }
        paint2.setStrokeCap(Paint.Cap.ROUND);
        Paint paint3 = this.mPaint;
        if (paint3 == null) {
            Intrinsics.throwNpe();
        }
        paint3.setAntiAlias(true);
        this.mTextPaint = new TextPaint();
        TextPaint textPaint = this.mTextPaint;
        if (textPaint == null) {
            Intrinsics.throwNpe();
        }
        textPaint.setAntiAlias(true);
        TextPaint textPaint2 = this.mTextPaint;
        if (textPaint2 == null) {
            Intrinsics.throwNpe();
        }
        textPaint2.setColor(this.textColor);
        TextPaint textPaint3 = this.mTextPaint;
        if (textPaint3 == null) {
            Intrinsics.throwNpe();
        }
        textPaint3.setTextSize(this.mTextSize);
        TextPaint textPaint4 = this.mTextPaint;
        if (textPaint4 == null) {
            Intrinsics.throwNpe();
        }
        textPaint4.setTextAlign(Paint.Align.CENTER);
    }

    public final void setText(String text) {
        this.text = text;
        measureTextSize();
        invalidate();
    }

    public final void setPercentage(int percentage) {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.mAnimator = getAnimator(percentage);
        ValueAnimator valueAnimator2 = this.mAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.start();
        }
    }

    public final void setRadius(int radius) {
        this.radius = radius;
    }

    public final void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public final void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public final void setStartColor(int startColor) {
        this.startColor = startColor;
    }

    public final void setEndColor(int endColor) {
        this.endColor = endColor;
    }

    public final void setGradient(boolean gradient) {
        this.isGradient = gradient;
    }

    private final ValueAnimator getAnimator(int percentage) {
        ValueAnimator animator = ObjectAnimator.ofInt(0, percentage);
        Intrinsics.checkExpressionValueIsNotNull(animator, "animator");
        animator.setDuration(500L);
        animator.setInterpolator(new LinearInterpolator());
        animator.setTarget(this);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.CirclePercentView.getAnimator.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator it) {
                CirclePercentView circlePercentView = CirclePercentView.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Object animatedValue = it.getAnimatedValue();
                if (animatedValue != null) {
                    circlePercentView.progressPercent = ((Integer) animatedValue).intValue();
                    CirclePercentView.this.invalidate();
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        });
        return animator;
    }
}