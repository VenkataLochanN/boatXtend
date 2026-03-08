package com.ido.life.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.google.android.gms.fitness.FitnessActivities;
import com.ido.life.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AmbientVolumePassedChartView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0011\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001:\u0002VWB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010A\u001a\u00020BH\u0002J\u000e\u0010C\u001a\u00020\u00112\u0006\u0010D\u001a\u00020\u0011J\b\u0010E\u001a\u00020FH\u0002J\u0018\u0010G\u001a\u00020F2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020\u0011H\u0002J\u0018\u0010K\u001a\u00020F2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020\u0011H\u0002J\b\u0010L\u001a\u00020\bH\u0002J\b\u0010M\u001a\u00020FH\u0002J\u0010\u0010N\u001a\u00020F2\u0006\u0010H\u001a\u00020IH\u0014J\u0018\u0010O\u001a\u00020F2\u0006\u0010P\u001a\u00020\n2\u0006\u0010Q\u001a\u00020\nH\u0014J\u000e\u0010R\u001a\u00020F2\u0006\u0010S\u001a\u00020BJ\b\u0010T\u001a\u00020FH\u0002J\b\u0010U\u001a\u00020FH\u0002R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0012\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\r\"\u0004\b\u0014\u0010\u000fR&\u0010\u0015\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\r\"\u0004\b\u0017\u0010\u000fR&\u0010\u0018\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u00118G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\r\"\u0004\b\u001f\u0010\u000fR&\u0010 \u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\r\"\u0004\b\"\u0010\u000fR2\u0010%\u001a\b\u0012\u0004\u0012\u00020$0#2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020$0#8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u000e\u0010*\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010,\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\r\"\u0004\b.\u0010\u000fR2\u00101\u001a\b\u0012\u0004\u0012\u0002000#2\f\u0010/\u001a\b\u0012\u0004\u0012\u0002000#8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010'\"\u0004\b3\u0010)R*\u00105\u001a\u0004\u0018\u0001042\b\u0010\t\u001a\u0004\u0018\u0001048G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u000e\u0010:\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010>\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u00118G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\u001a\"\u0004\b@\u0010\u001c¨\u0006X"}, d2 = {"Lcom/ido/life/customview/AmbientVolumePassedChartView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "mAnimator", "Landroid/animation/ValueAnimator;", "<set-?>", "", "mAnimatorDuration", "getAnimatorDuration", "()I", "setAnimatorDuration", "(I)V", "mAnimatorPercent", "", "mBarColor", "getBarColor", "setBarColor", "mBarRadius", "getBarRadius", "setBarRadius", "mBarWidth", "getBarWidth", "()F", "setBarWidth", "(F)V", "mBottomLabelBarDistance", "getMBottomLabelBarDistance", "setMBottomLabelBarDistance", "mBottomLabelColor", "getBottomLabelColor", "setBottomLabelColor", "", "", "mBottomLabelList", "getBottomLabelList", "()Ljava/util/List;", "setBottomLabelList", "(Ljava/util/List;)V", "mBottomLabelMaxHeight", "mBottomLabelMaxWidth", "mBottomLabelSize", "getBottomLabelSize", "setBottomLabelSize", "it", "Lcom/ido/life/customview/AmbientVolumePassedChartView$ChartBean;", "mChartList", "getChartList", "setChartList", "Lcom/ido/life/customview/AmbientVolumePassedChartView$DrawCallback;", "mDrawCallback", "getDrawCallback", "()Lcom/ido/life/customview/AmbientVolumePassedChartView$DrawCallback;", "setDrawCallback", "(Lcom/ido/life/customview/AmbientVolumePassedChartView$DrawCallback;)V", "mMaxY", "mMinY", "mPaint", "Landroid/graphics/Paint;", "mPassedVolumeAvg", "getPassedVolumeAvg", "setPassedVolumeAvg", "animatorRunning", "", "caluteY", "maxminValue", "caluteYMaxmin", "", "drawBottomLabel", "canvas", "Landroid/graphics/Canvas;", "perWidth", "drawChartBar", "getAnimator", "measureBottomMaxLabelWidth", "onDraw", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "refresh", "animator", "startAnimator", "stopAnimator", "ChartBean", "DrawCallback", "app_release"}, k = 1, mv = {1, 1, 16})
public final class AmbientVolumePassedChartView extends View {
    private HashMap _$_findViewCache;
    private ValueAnimator mAnimator;
    private int mAnimatorDuration;
    private float mAnimatorPercent;
    private int mBarColor;
    private int mBarRadius;
    private float mBarWidth;
    private int mBottomLabelBarDistance;
    private int mBottomLabelColor;
    private List<String> mBottomLabelList;
    private int mBottomLabelMaxHeight;
    private int mBottomLabelMaxWidth;
    private int mBottomLabelSize;
    private List<ChartBean> mChartList;
    private DrawCallback mDrawCallback;
    private float mMaxY;
    private float mMinY;
    private Paint mPaint;
    private float mPassedVolumeAvg;

    /* JADX INFO: compiled from: AmbientVolumePassedChartView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/ido/life/customview/AmbientVolumePassedChartView$DrawCallback;", "", "drawComplete", "", "centerY", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public interface DrawCallback {
        void drawComplete(float centerY);
    }

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
    public AmbientVolumePassedChartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attributeSet");
        this.mBarColor = -3355444;
        this.mBarRadius = 5;
        this.mBottomLabelColor = -3355444;
        this.mBottomLabelSize = 10;
        this.mAnimatorDuration = 2000;
        this.mBottomLabelMaxWidth = 10;
        this.mChartList = new ArrayList();
        this.mBottomLabelList = new ArrayList();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AmbientVolumeRecentChartView);
        Intrinsics.checkExpressionValueIsNotNull(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…entVolumeRecentChartView)");
        this.mBarColor = typedArrayObtainStyledAttributes.getColor(1, this.mBarColor);
        this.mBarRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, this.mBarRadius);
        this.mBarWidth = typedArrayObtainStyledAttributes.getDimension(3, this.mBarWidth);
        this.mBottomLabelColor = typedArrayObtainStyledAttributes.getColor(5, this.mBottomLabelColor);
        this.mBottomLabelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, this.mBottomLabelSize);
        this.mAnimatorDuration = typedArrayObtainStyledAttributes.getInteger(0, this.mAnimatorDuration);
        this.mBottomLabelBarDistance = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, this.mBottomLabelBarDistance);
        typedArrayObtainStyledAttributes.recycle();
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setStyle(Paint.Style.FILL);
    }

    /* JADX INFO: renamed from: getBarColor, reason: from getter */
    public final int getMBarColor() {
        return this.mBarColor;
    }

    public final void setBarColor(int i) {
        this.mBarColor = i;
    }

    /* JADX INFO: renamed from: getBarRadius, reason: from getter */
    public final int getMBarRadius() {
        return this.mBarRadius;
    }

    public final void setBarRadius(int i) {
        this.mBarRadius = i;
    }

    /* JADX INFO: renamed from: getBarWidth, reason: from getter */
    public final float getMBarWidth() {
        return this.mBarWidth;
    }

    public final void setBarWidth(float f2) {
        this.mBarWidth = f2;
    }

    /* JADX INFO: renamed from: getBottomLabelColor, reason: from getter */
    public final int getMBottomLabelColor() {
        return this.mBottomLabelColor;
    }

    public final void setBottomLabelColor(int i) {
        this.mBottomLabelColor = i;
    }

    /* JADX INFO: renamed from: getBottomLabelSize, reason: from getter */
    public final int getMBottomLabelSize() {
        return this.mBottomLabelSize;
    }

    public final void setBottomLabelSize(int i) {
        this.mBottomLabelSize = i;
    }

    /* JADX INFO: renamed from: getAnimatorDuration, reason: from getter */
    public final int getMAnimatorDuration() {
        return this.mAnimatorDuration;
    }

    public final void setAnimatorDuration(int i) {
        this.mAnimatorDuration = i;
    }

    public final int getMBottomLabelBarDistance() {
        return this.mBottomLabelBarDistance;
    }

    public final void setMBottomLabelBarDistance(int i) {
        this.mBottomLabelBarDistance = i;
    }

    /* JADX INFO: renamed from: getPassedVolumeAvg, reason: from getter */
    public final float getMPassedVolumeAvg() {
        return this.mPassedVolumeAvg;
    }

    public final void setPassedVolumeAvg(float f2) {
        this.mPassedVolumeAvg = f2;
    }

    public final List<ChartBean> getChartList() {
        return this.mChartList;
    }

    public final void setChartList(List<ChartBean> it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        float maxValue = 0.0f;
        this.mPassedVolumeAvg = 0.0f;
        if (!it.isEmpty()) {
            int i = 0;
            for (ChartBean chartBean : it) {
                float f2 = 0;
                if (chartBean.getMaxValue() > f2 && chartBean.getMinValue() > f2) {
                    maxValue += (chartBean.getMaxValue() + chartBean.getMinValue()) / 2.0f;
                    i++;
                }
            }
            this.mPassedVolumeAvg = maxValue / i;
        }
        this.mChartList = it;
    }

    public final List<String> getBottomLabelList() {
        return this.mBottomLabelList;
    }

    public final void setBottomLabelList(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.mBottomLabelList = list;
    }

    /* JADX INFO: renamed from: getDrawCallback, reason: from getter */
    public final DrawCallback getMDrawCallback() {
        return this.mDrawCallback;
    }

    public final void setDrawCallback(DrawCallback drawCallback) {
        this.mDrawCallback = drawCallback;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        super.onDraw(canvas);
        if (this.mBottomLabelList.isEmpty() || this.mChartList.isEmpty() || this.mBottomLabelList.size() != this.mChartList.size()) {
            return;
        }
        float width = (getWidth() * 1.0f) / this.mBottomLabelList.size();
        drawChartBar(canvas, width);
        drawBottomLabel(canvas, width);
        DrawCallback drawCallback = this.mDrawCallback;
        if (drawCallback != null) {
            drawCallback.drawComplete(caluteY(this.mPassedVolumeAvg));
        }
    }

    private final void drawChartBar(Canvas canvas, float perWidth) {
        int i;
        if (!animatorRunning() || this.mAnimatorPercent >= 1.0f) {
            i = Integer.MIN_VALUE;
        } else {
            int saveCount = canvas.getSaveCount();
            canvas.clipRect(0.0f, ((getHeight() - this.mBottomLabelMaxHeight) - this.mBottomLabelBarDistance) * (1 - this.mAnimatorPercent), getWidth(), getHeight());
            i = saveCount;
        }
        float fMin = Math.min(this.mBottomLabelMaxWidth, perWidth);
        float f2 = this.mBarWidth;
        if (f2 > 0) {
            fMin = Math.min(f2, fMin);
        }
        float f3 = fMin;
        this.mPaint.setColor(this.mBarColor);
        int size = this.mChartList.size();
        for (int i2 = 0; i2 < size; i2++) {
            ChartBean chartBean = this.mChartList.get(i2);
            float fCaluteY = caluteY(chartBean.getMaxValue());
            float fCaluteY2 = caluteY(chartBean.getMinValue());
            if (fCaluteY < fCaluteY2) {
                float f4 = ((perWidth - f3) / 2.0f) + (i2 * perWidth);
                int i3 = this.mBarRadius;
                canvas.drawRoundRect(f4, fCaluteY, f4 + f3, fCaluteY2, i3, i3, this.mPaint);
            }
        }
        if (i != Integer.MIN_VALUE) {
            canvas.restoreToCount(i);
        }
    }

    private final void drawBottomLabel(Canvas canvas, float perWidth) {
        this.mPaint.setColor(this.mBottomLabelColor);
        this.mPaint.setTextSize(this.mBottomLabelSize);
        int size = this.mBottomLabelList.size();
        Rect rect = new Rect();
        Paint.FontMetrics fontMetrics = this.mPaint.getFontMetrics();
        for (int i = 0; i < size; i++) {
            String str = this.mBottomLabelList.get(i);
            if (!(str.length() == 0)) {
                this.mPaint.getTextBounds(str, 0, str.length(), rect);
                canvas.drawText(str, (i * perWidth) + ((perWidth - rect.width()) / 2.0f), getHeight() - Math.abs(fontMetrics.bottom), this.mPaint);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(View.MeasureSpec.getSize(widthMeasureSpec), View.MeasureSpec.getSize(heightMeasureSpec));
        measureBottomMaxLabelWidth();
        caluteYMaxmin();
    }

    private final void measureBottomMaxLabelWidth() {
        this.mBottomLabelMaxWidth = 0;
        this.mBottomLabelMaxHeight = 0;
        if (this.mBottomLabelList.isEmpty()) {
            return;
        }
        Rect rect = new Rect();
        Paint paint = new Paint();
        paint.setTextSize(this.mBottomLabelSize);
        paint.setStyle(Paint.Style.FILL);
        for (String str : this.mBottomLabelList) {
            paint.getTextBounds(str, 0, str.length(), rect);
            this.mBottomLabelMaxWidth = Math.max(this.mBottomLabelMaxWidth, rect.width());
            this.mBottomLabelMaxHeight = Math.max(this.mBottomLabelMaxHeight, rect.height());
        }
    }

    private final void caluteYMaxmin() {
        float fMax = 0.0f;
        this.mMaxY = 0.0f;
        this.mMinY = 0.0f;
        List<ChartBean> list = this.mChartList;
        if (list == null || list.isEmpty()) {
            return;
        }
        float fMin = Integer.MAX_VALUE;
        for (ChartBean chartBean : this.mChartList) {
            float f2 = 0;
            if (chartBean.getMaxValue() > f2 && chartBean.getMinValue() > f2) {
                fMax = Math.max(fMax, chartBean.getMaxValue());
                fMin = Math.min(fMin, chartBean.getMinValue());
            }
        }
        this.mMaxY = fMax;
        this.mMinY = fMin;
    }

    public final float caluteY(float maxminValue) {
        float height = (getHeight() - this.mBottomLabelMaxHeight) - this.mBottomLabelBarDistance;
        float f2 = this.mMaxY;
        float f3 = this.mMinY;
        return (f2 <= f3 || maxminValue <= f3) ? height : Math.max(0.0f, ((height * (f2 - maxminValue)) * 1.0f) / (f2 - f3));
    }

    private final void stopAnimator() {
        ValueAnimator valueAnimator;
        if (!animatorRunning() || (valueAnimator = this.mAnimator) == null) {
            return;
        }
        valueAnimator.cancel();
    }

    private final void startAnimator() {
        if (this.mAnimator == null) {
            this.mAnimator = getAnimator();
        }
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null) {
            valueAnimator.start();
        }
    }

    private final boolean animatorRunning() {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null) {
            if (valueAnimator == null) {
                Intrinsics.throwNpe();
            }
            if (!valueAnimator.isRunning()) {
                ValueAnimator valueAnimator2 = this.mAnimator;
                if (valueAnimator2 == null) {
                    Intrinsics.throwNpe();
                }
                if (valueAnimator2.isStarted()) {
                }
            }
            return true;
        }
        return false;
    }

    private final ValueAnimator getAnimator() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(this.mAnimatorDuration);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setTarget(this);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.AmbientVolumePassedChartView.getAnimator.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator it) {
                AmbientVolumePassedChartView ambientVolumePassedChartView = AmbientVolumePassedChartView.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Object animatedValue = it.getAnimatedValue();
                if (animatedValue != null) {
                    ambientVolumePassedChartView.mAnimatorPercent = ((Float) animatedValue).floatValue();
                    AmbientVolumePassedChartView.this.invalidate();
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Float");
            }
        });
        return valueAnimator;
    }

    public final void refresh(boolean animator) {
        stopAnimator();
        if (animator) {
            startAnimator();
        } else {
            invalidate();
        }
    }

    /* JADX INFO: compiled from: AmbientVolumePassedChartView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/ido/life/customview/AmbientVolumePassedChartView$ChartBean;", "", "maxValue", "", "minValue", "(FF)V", "getMaxValue", "()F", "setMaxValue", "(F)V", "getMinValue", "setMinValue", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class ChartBean {
        private float maxValue;
        private float minValue;

        public static /* synthetic */ ChartBean copy$default(ChartBean chartBean, float f2, float f3, int i, Object obj) {
            if ((i & 1) != 0) {
                f2 = chartBean.maxValue;
            }
            if ((i & 2) != 0) {
                f3 = chartBean.minValue;
            }
            return chartBean.copy(f2, f3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final float getMaxValue() {
            return this.maxValue;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final float getMinValue() {
            return this.minValue;
        }

        public final ChartBean copy(float maxValue, float minValue) {
            return new ChartBean(maxValue, minValue);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ChartBean)) {
                return false;
            }
            ChartBean chartBean = (ChartBean) other;
            return Float.compare(this.maxValue, chartBean.maxValue) == 0 && Float.compare(this.minValue, chartBean.minValue) == 0;
        }

        public int hashCode() {
            return (Float.valueOf(this.maxValue).hashCode() * 31) + Float.valueOf(this.minValue).hashCode();
        }

        public String toString() {
            return "ChartBean(maxValue=" + this.maxValue + ", minValue=" + this.minValue + ")";
        }

        public ChartBean(float f2, float f3) {
            this.maxValue = f2;
            this.minValue = f3;
        }

        public final float getMaxValue() {
            return this.maxValue;
        }

        public final float getMinValue() {
            return this.minValue;
        }

        public final void setMaxValue(float f2) {
            this.maxValue = f2;
        }

        public final void setMinValue(float f2) {
            this.minValue = f2;
        }
    }
}