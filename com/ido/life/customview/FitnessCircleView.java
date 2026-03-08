package com.ido.life.customview;

import android.animation.Animator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.ido.life.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FitnessCircleView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\bQ\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010v\u001a\u00020w2\u0006\u0010x\u001a\u00020yH\u0002J\u0010\u0010z\u001a\u00020w2\u0006\u0010x\u001a\u00020yH\u0002J\u0010\u0010{\u001a\u00020w2\u0006\u0010x\u001a\u00020yH\u0002J\u0010\u0010|\u001a\u00020w2\u0006\u0010x\u001a\u00020yH\u0002J\u0010\u0010}\u001a\u00020w2\u0006\u0010x\u001a\u00020yH\u0002J\u0010\u0010~\u001a\u00020w2\u0006\u0010x\u001a\u00020yH\u0002J\u0010\u0010\u007f\u001a\u00020w2\u0006\u0010x\u001a\u00020yH\u0002J\u0011\u0010\u0080\u0001\u001a\u00020w2\u0006\u0010x\u001a\u00020yH\u0002J\t\u0010\u0081\u0001\u001a\u00020\u000fH\u0002J\t\u0010\u0082\u0001\u001a\u00020wH\u0002J\t\u0010\u0083\u0001\u001a\u00020wH\u0002J\t\u0010\u0084\u0001\u001a\u00020wH\u0002J\t\u0010\u0085\u0001\u001a\u00020wH\u0002J\t\u0010\u0086\u0001\u001a\u00020wH\u0002J\t\u0010\u0087\u0001\u001a\u00020wH\u0002J\u0011\u0010\u0088\u0001\u001a\u00020w2\u0006\u0010x\u001a\u00020yH\u0014J\u001b\u0010\u0089\u0001\u001a\u00020w2\u0007\u0010\u008a\u0001\u001a\u00020\b2\u0007\u0010\u008b\u0001\u001a\u00020\bH\u0014J\u0011\u0010\u008c\u0001\u001a\u00020w2\b\u0010\u008d\u0001\u001a\u00030\u008e\u0001J\t\u0010\u008f\u0001\u001a\u00020wH\u0002J\t\u0010\u0090\u0001\u001a\u00020wH\u0002R\u0014\u0010\u0007\u001a\u00020\bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u00118G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R*\u0010\u0017\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u00118G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082D¢\u0006\u0002\n\u0000R&\u0010\u001e\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\n\"\u0004\b \u0010!R*\u0010\"\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u00118G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0014\"\u0004\b$\u0010\u0016R&\u0010%\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\n\"\u0004\b'\u0010!R&\u0010(\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\n\"\u0004\b*\u0010!R&\u0010+\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\n\"\u0004\b-\u0010!R&\u0010.\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\n\"\u0004\b0\u0010!R&\u00101\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\n\"\u0004\b3\u0010!R*\u00104\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u00118G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0014\"\u0004\b6\u0010\u0016R&\u00107\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\n\"\u0004\b9\u0010!R&\u0010:\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\n\"\u0004\b<\u0010!R\u000e\u0010=\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u001dX\u0082D¢\u0006\u0002\n\u0000R&\u0010?\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\n\"\u0004\bA\u0010!R&\u0010B\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\n\"\u0004\bD\u0010!R&\u0010E\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\n\"\u0004\bG\u0010!R&\u0010H\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\n\"\u0004\bJ\u0010!R&\u0010K\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\n\"\u0004\bM\u0010!R&\u0010N\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010\n\"\u0004\bP\u0010!R&\u0010Q\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010\n\"\u0004\bS\u0010!R&\u0010T\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010\n\"\u0004\bV\u0010!R\u000e\u0010W\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020\u001dX\u0082D¢\u0006\u0002\n\u0000R&\u0010Y\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010\n\"\u0004\b[\u0010!R&\u0010\\\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010\n\"\u0004\b^\u0010!R&\u0010_\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010\n\"\u0004\ba\u0010!R&\u0010b\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010\n\"\u0004\bd\u0010!R&\u0010e\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010\n\"\u0004\bg\u0010!R&\u0010h\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010\n\"\u0004\bj\u0010!R&\u0010k\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010\n\"\u0004\bm\u0010!R\u000e\u0010n\u001a\u00020oX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010p\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u00118G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010\u0014\"\u0004\br\u0010\u0016R*\u0010s\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u00118G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bt\u0010\u0014\"\u0004\bu\u0010\u0016¨\u0006\u0091\u0001"}, d2 = {"Lcom/ido/life/customview/FitnessCircleView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "COLOR_NONE", "", "getCOLOR_NONE", "()I", "INNER_ANIMATION_MAX_DURATION", "OUT_BOTTOM_ANIMATION_MAX_DURATION", "OUT_TOP_ANIMATION_MAX_DURATION", "mAnimator", "Landroid/animation/ValueAnimator;", "<set-?>", "Landroid/graphics/Shader;", "mBottomBgShader", "getBottomBgShader", "()Landroid/graphics/Shader;", "setBottomBgShader", "(Landroid/graphics/Shader;)V", "mBottomShader", "getBottomShader", "setBottomShader", "mInnerAnimatorPercent", "", "mInnerAnimatorPropertyName", "", "mInnerBgEndColor", "getInnerBgEndColor", "setInnerBgEndColor", "(I)V", "mInnerBgShader", "getInnerBgShader", "setInnerBgShader", "mInnerBgStartColor", "getInnerBgStartColor", "setInnerBgStartColor", "mInnerCircleRadius", "getInnerCircleRadius", "setInnerCircleRadius", "mInnerEndColor", "getInnerEndColor", "setInnerEndColor", "mInnerMaxProgress", "getInnerMaxProgress", "setInnerMaxProgress", "mInnerProgress", "getInnerProgress", "setInnerProgress", "mInnerShader", "getInnerShader", "setInnerShader", "mInnerStartColor", "getInnerStartColor", "setInnerStartColor", "mInnerStrokeWidth", "getInnerStrokeWidth", "setInnerStrokeWidth", "mOutBottomAnimatorPercent", "mOutBottomAnimatorPropertyName", "mOutBottomBgEndColor", "getOutBottomBgEndColor", "setOutBottomBgEndColor", "mOutBottomBgStartColor", "getOutBottomBgStartColor", "setOutBottomBgStartColor", "mOutBottomEndColor", "getOutBottomEndColor", "setOutBottomEndColor", "mOutBottomMaxProgress", "getOutBottomMaxProgress", "setOutBottomMaxProgress", "mOutBottomProgress", "getOutBottomProgress", "setOutBottomProgress", "mOutBottomStartColor", "getOutBottomStartColor", "setOutBottomStartColor", "mOutCircleRadius", "getOutCircleRadius", "setOutCircleRadius", "mOutStrokeWidth", "getOutStrokeWidth", "setOutStrokeWidth", "mOutTopAnimatorPercent", "mOutTopAnimatorPropertyName", "mOutTopBgEndColor", "getOutTopBgEndColor", "setOutTopBgEndColor", "mOutTopBgStartColor", "getOutTopBgStartColor", "setOutTopBgStartColor", "mOutTopBottomSpaceAngle", "getOutTopBottomSpaceAngle", "setOutTopBottomSpaceAngle", "mOutTopEndColor", "getOutTopEndColor", "setOutTopEndColor", "mOutTopMaxProgress", "getOutTopMaxProgress", "setOutTopMaxProgress", "mOutTopProgress", "getOutTopProgress", "setOutTopProgress", "mOutTopStartColor", "getOutTopStartColor", "setOutTopStartColor", "mPaint", "Landroid/graphics/Paint;", "mTopBgShader", "getTopBgShader", "setTopBgShader", "mTopShader", "getTopShader", "setTopShader", "drawCircleBg", "", "canvas", "Landroid/graphics/Canvas;", "drawCircleProgress", "drawInnerBg", "drawInnerProgress", "drawOutBottomBg", "drawOutBottomProgress", "drawOutTopBg", "drawOutTopProgress", "initAnimator", "initInnerBgShader", "initInnerShader", "initOutBottomBgShader", "initOutBottomShader", "initOutTopBgShader", "initOutTopShader", "onDraw", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "refreshView", "animator", "", "startAnimator", "stopAnimator", "app_release"}, k = 1, mv = {1, 1, 16})
public final class FitnessCircleView extends View {
    private final int COLOR_NONE;
    private int INNER_ANIMATION_MAX_DURATION;
    private int OUT_BOTTOM_ANIMATION_MAX_DURATION;
    private int OUT_TOP_ANIMATION_MAX_DURATION;
    private HashMap _$_findViewCache;
    private ValueAnimator mAnimator;
    private Shader mBottomBgShader;
    private Shader mBottomShader;
    private float mInnerAnimatorPercent;
    private final String mInnerAnimatorPropertyName;
    private int mInnerBgEndColor;
    private Shader mInnerBgShader;
    private int mInnerBgStartColor;
    private int mInnerCircleRadius;
    private int mInnerEndColor;
    private int mInnerMaxProgress;
    private int mInnerProgress;
    private Shader mInnerShader;
    private int mInnerStartColor;
    private int mInnerStrokeWidth;
    private float mOutBottomAnimatorPercent;
    private final String mOutBottomAnimatorPropertyName;
    private int mOutBottomBgEndColor;
    private int mOutBottomBgStartColor;
    private int mOutBottomEndColor;
    private int mOutBottomMaxProgress;
    private int mOutBottomProgress;
    private int mOutBottomStartColor;
    private int mOutCircleRadius;
    private int mOutStrokeWidth;
    private float mOutTopAnimatorPercent;
    private final String mOutTopAnimatorPropertyName;
    private int mOutTopBgEndColor;
    private int mOutTopBgStartColor;
    private int mOutTopBottomSpaceAngle;
    private int mOutTopEndColor;
    private int mOutTopMaxProgress;
    private int mOutTopProgress;
    private int mOutTopStartColor;
    private final Paint mPaint;
    private Shader mTopBgShader;
    private Shader mTopShader;

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
    public FitnessCircleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.COLOR_NONE = -1;
        this.mOutTopAnimatorPropertyName = "out_top";
        this.mOutBottomAnimatorPropertyName = "out_bottom";
        this.mInnerAnimatorPropertyName = "inner";
        this.mOutCircleRadius = 200;
        this.mOutStrokeWidth = 12;
        this.mInnerCircleRadius = 100;
        this.mInnerStrokeWidth = 12;
        int i = this.COLOR_NONE;
        this.mOutTopBgStartColor = i;
        this.mOutTopBgEndColor = i;
        this.mOutBottomBgStartColor = i;
        this.mOutBottomBgEndColor = i;
        this.mOutTopStartColor = i;
        this.mOutTopEndColor = i;
        this.mOutBottomStartColor = i;
        this.mOutBottomEndColor = i;
        this.mInnerBgStartColor = i;
        this.mInnerBgEndColor = i;
        this.mInnerStartColor = i;
        this.mInnerEndColor = i;
        this.mOutTopBottomSpaceAngle = 5;
        this.mOutTopMaxProgress = 100;
        this.mOutBottomMaxProgress = 100;
        this.mInnerMaxProgress = 100;
        this.OUT_TOP_ANIMATION_MAX_DURATION = 3000;
        this.OUT_BOTTOM_ANIMATION_MAX_DURATION = 3000;
        this.INNER_ANIMATION_MAX_DURATION = 3000;
        this.mPaint = new Paint();
        this.mOutTopAnimatorPercent = 1.0f;
        this.mOutBottomAnimatorPercent = 1.0f;
        this.mInnerAnimatorPercent = 1.0f;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FitnessCircleView);
        Intrinsics.checkExpressionValueIsNotNull(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…leable.FitnessCircleView)");
        this.mOutCircleRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(16, this.mOutCircleRadius);
        this.mOutStrokeWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(18, this.mOutStrokeWidth);
        this.mInnerCircleRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, this.mInnerCircleRadius);
        this.mInnerStrokeWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, this.mInnerStrokeWidth);
        this.mOutTopBgStartColor = typedArrayObtainStyledAttributes.getColor(21, this.mOutTopBgStartColor);
        this.mOutTopBgEndColor = typedArrayObtainStyledAttributes.getColor(20, this.mOutTopBgEndColor);
        this.mOutBottomBgStartColor = typedArrayObtainStyledAttributes.getColor(11, this.mOutBottomBgStartColor);
        this.mOutBottomBgEndColor = typedArrayObtainStyledAttributes.getColor(10, this.mOutBottomBgEndColor);
        this.mOutTopStartColor = typedArrayObtainStyledAttributes.getColor(23, this.mOutTopStartColor);
        this.mOutTopEndColor = typedArrayObtainStyledAttributes.getColor(22, this.mOutTopEndColor);
        this.mOutBottomStartColor = typedArrayObtainStyledAttributes.getColor(13, this.mOutBottomStartColor);
        this.mOutBottomEndColor = typedArrayObtainStyledAttributes.getColor(12, this.mOutBottomEndColor);
        this.mInnerBgStartColor = typedArrayObtainStyledAttributes.getColor(2, this.mInnerBgStartColor);
        this.mInnerBgEndColor = typedArrayObtainStyledAttributes.getColor(1, this.mInnerBgEndColor);
        this.mInnerStartColor = typedArrayObtainStyledAttributes.getColor(8, this.mInnerStartColor);
        this.mInnerEndColor = typedArrayObtainStyledAttributes.getColor(5, this.mInnerEndColor);
        this.mOutTopBottomSpaceAngle = typedArrayObtainStyledAttributes.getInt(17, this.mOutTopBottomSpaceAngle);
        this.mOutTopMaxProgress = typedArrayObtainStyledAttributes.getInt(24, this.mOutTopMaxProgress);
        this.mOutBottomMaxProgress = typedArrayObtainStyledAttributes.getInt(14, this.mOutBottomMaxProgress);
        this.mInnerMaxProgress = typedArrayObtainStyledAttributes.getInt(6, this.mInnerMaxProgress);
        this.mOutTopProgress = typedArrayObtainStyledAttributes.getInt(25, this.mOutTopProgress);
        this.mOutBottomProgress = typedArrayObtainStyledAttributes.getInt(15, this.mOutBottomProgress);
        this.mInnerProgress = typedArrayObtainStyledAttributes.getInt(7, this.mInnerProgress);
        this.OUT_TOP_ANIMATION_MAX_DURATION = typedArrayObtainStyledAttributes.getInt(19, this.OUT_TOP_ANIMATION_MAX_DURATION);
        this.OUT_BOTTOM_ANIMATION_MAX_DURATION = typedArrayObtainStyledAttributes.getInt(9, this.OUT_BOTTOM_ANIMATION_MAX_DURATION);
        this.INNER_ANIMATION_MAX_DURATION = typedArrayObtainStyledAttributes.getInt(0, this.INNER_ANIMATION_MAX_DURATION);
        typedArrayObtainStyledAttributes.recycle();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        setLayerType(1, this.mPaint);
    }

    public final int getCOLOR_NONE() {
        return this.COLOR_NONE;
    }

    /* JADX INFO: renamed from: getOutCircleRadius, reason: from getter */
    public final int getMOutCircleRadius() {
        return this.mOutCircleRadius;
    }

    public final void setOutCircleRadius(int i) {
        this.mOutCircleRadius = i;
    }

    /* JADX INFO: renamed from: getOutStrokeWidth, reason: from getter */
    public final int getMOutStrokeWidth() {
        return this.mOutStrokeWidth;
    }

    public final void setOutStrokeWidth(int i) {
        this.mOutStrokeWidth = i;
    }

    /* JADX INFO: renamed from: getInnerCircleRadius, reason: from getter */
    public final int getMInnerCircleRadius() {
        return this.mInnerCircleRadius;
    }

    public final void setInnerCircleRadius(int i) {
        this.mInnerCircleRadius = i;
    }

    /* JADX INFO: renamed from: getInnerStrokeWidth, reason: from getter */
    public final int getMInnerStrokeWidth() {
        return this.mInnerStrokeWidth;
    }

    public final void setInnerStrokeWidth(int i) {
        this.mInnerStrokeWidth = i;
    }

    /* JADX INFO: renamed from: getOutTopBgStartColor, reason: from getter */
    public final int getMOutTopBgStartColor() {
        return this.mOutTopBgStartColor;
    }

    public final void setOutTopBgStartColor(int i) {
        this.mOutTopBgStartColor = i;
    }

    /* JADX INFO: renamed from: getOutTopBgEndColor, reason: from getter */
    public final int getMOutTopBgEndColor() {
        return this.mOutTopBgEndColor;
    }

    public final void setOutTopBgEndColor(int i) {
        this.mOutTopBgEndColor = i;
    }

    /* JADX INFO: renamed from: getOutBottomBgStartColor, reason: from getter */
    public final int getMOutBottomBgStartColor() {
        return this.mOutBottomBgStartColor;
    }

    public final void setOutBottomBgStartColor(int i) {
        this.mOutBottomBgStartColor = i;
    }

    /* JADX INFO: renamed from: getOutBottomBgEndColor, reason: from getter */
    public final int getMOutBottomBgEndColor() {
        return this.mOutBottomBgEndColor;
    }

    public final void setOutBottomBgEndColor(int i) {
        this.mOutBottomBgEndColor = i;
    }

    /* JADX INFO: renamed from: getOutTopStartColor, reason: from getter */
    public final int getMOutTopStartColor() {
        return this.mOutTopStartColor;
    }

    public final void setOutTopStartColor(int i) {
        this.mOutTopStartColor = i;
    }

    /* JADX INFO: renamed from: getOutTopEndColor, reason: from getter */
    public final int getMOutTopEndColor() {
        return this.mOutTopEndColor;
    }

    public final void setOutTopEndColor(int i) {
        this.mOutTopEndColor = i;
    }

    /* JADX INFO: renamed from: getOutBottomStartColor, reason: from getter */
    public final int getMOutBottomStartColor() {
        return this.mOutBottomStartColor;
    }

    public final void setOutBottomStartColor(int i) {
        this.mOutBottomStartColor = i;
    }

    /* JADX INFO: renamed from: getOutBottomEndColor, reason: from getter */
    public final int getMOutBottomEndColor() {
        return this.mOutBottomEndColor;
    }

    public final void setOutBottomEndColor(int i) {
        this.mOutBottomEndColor = i;
    }

    /* JADX INFO: renamed from: getInnerBgStartColor, reason: from getter */
    public final int getMInnerBgStartColor() {
        return this.mInnerBgStartColor;
    }

    public final void setInnerBgStartColor(int i) {
        this.mInnerBgStartColor = i;
    }

    /* JADX INFO: renamed from: getInnerBgEndColor, reason: from getter */
    public final int getMInnerBgEndColor() {
        return this.mInnerBgEndColor;
    }

    public final void setInnerBgEndColor(int i) {
        this.mInnerBgEndColor = i;
    }

    /* JADX INFO: renamed from: getInnerStartColor, reason: from getter */
    public final int getMInnerStartColor() {
        return this.mInnerStartColor;
    }

    public final void setInnerStartColor(int i) {
        this.mInnerStartColor = i;
    }

    /* JADX INFO: renamed from: getInnerEndColor, reason: from getter */
    public final int getMInnerEndColor() {
        return this.mInnerEndColor;
    }

    public final void setInnerEndColor(int i) {
        this.mInnerEndColor = i;
    }

    /* JADX INFO: renamed from: getOutTopBottomSpaceAngle, reason: from getter */
    public final int getMOutTopBottomSpaceAngle() {
        return this.mOutTopBottomSpaceAngle;
    }

    public final void setOutTopBottomSpaceAngle(int i) {
        this.mOutTopBottomSpaceAngle = i;
    }

    /* JADX INFO: renamed from: getOutTopMaxProgress, reason: from getter */
    public final int getMOutTopMaxProgress() {
        return this.mOutTopMaxProgress;
    }

    public final void setOutTopMaxProgress(int i) {
        this.mOutTopMaxProgress = i;
    }

    /* JADX INFO: renamed from: getOutBottomMaxProgress, reason: from getter */
    public final int getMOutBottomMaxProgress() {
        return this.mOutBottomMaxProgress;
    }

    public final void setOutBottomMaxProgress(int i) {
        this.mOutBottomMaxProgress = i;
    }

    /* JADX INFO: renamed from: getInnerMaxProgress, reason: from getter */
    public final int getMInnerMaxProgress() {
        return this.mInnerMaxProgress;
    }

    public final void setInnerMaxProgress(int i) {
        this.mInnerMaxProgress = i;
    }

    /* JADX INFO: renamed from: getOutTopProgress, reason: from getter */
    public final int getMOutTopProgress() {
        return this.mOutTopProgress;
    }

    public final void setOutTopProgress(int i) {
        this.mOutTopProgress = i;
    }

    /* JADX INFO: renamed from: getOutBottomProgress, reason: from getter */
    public final int getMOutBottomProgress() {
        return this.mOutBottomProgress;
    }

    public final void setOutBottomProgress(int i) {
        this.mOutBottomProgress = i;
    }

    /* JADX INFO: renamed from: getInnerProgress, reason: from getter */
    public final int getMInnerProgress() {
        return this.mInnerProgress;
    }

    public final void setInnerProgress(int i) {
        this.mInnerProgress = i;
    }

    /* JADX INFO: renamed from: getTopBgShader, reason: from getter */
    public final Shader getMTopBgShader() {
        return this.mTopBgShader;
    }

    public final void setTopBgShader(Shader shader) {
        this.mTopBgShader = shader;
    }

    /* JADX INFO: renamed from: getTopShader, reason: from getter */
    public final Shader getMTopShader() {
        return this.mTopShader;
    }

    public final void setTopShader(Shader shader) {
        this.mTopShader = shader;
    }

    /* JADX INFO: renamed from: getBottomBgShader, reason: from getter */
    public final Shader getMBottomBgShader() {
        return this.mBottomBgShader;
    }

    public final void setBottomBgShader(Shader shader) {
        this.mBottomBgShader = shader;
    }

    /* JADX INFO: renamed from: getBottomShader, reason: from getter */
    public final Shader getMBottomShader() {
        return this.mBottomShader;
    }

    public final void setBottomShader(Shader shader) {
        this.mBottomShader = shader;
    }

    /* JADX INFO: renamed from: getInnerBgShader, reason: from getter */
    public final Shader getMInnerBgShader() {
        return this.mInnerBgShader;
    }

    public final void setInnerBgShader(Shader shader) {
        this.mInnerBgShader = shader;
    }

    /* JADX INFO: renamed from: getInnerShader, reason: from getter */
    public final Shader getMInnerShader() {
        return this.mInnerShader;
    }

    public final void setInnerShader(Shader shader) {
        this.mInnerShader = shader;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        super.onDraw(canvas);
        this.mInnerProgress = Math.min(this.mInnerProgress, this.mInnerMaxProgress);
        this.mOutTopProgress = Math.min(this.mOutTopProgress, this.mOutTopMaxProgress);
        this.mOutBottomProgress = Math.min(this.mOutBottomProgress, this.mOutBottomMaxProgress);
        initOutTopBgShader();
        initOutBottomBgShader();
        initInnerBgShader();
        initOutTopShader();
        initOutBottomShader();
        initInnerShader();
        drawCircleBg(canvas);
        drawCircleProgress(canvas);
    }

    private final void drawCircleBg(Canvas canvas) {
        drawOutTopBg(canvas);
        drawOutBottomBg(canvas);
        drawInnerBg(canvas);
    }

    private final void drawCircleProgress(Canvas canvas) {
        drawOutTopProgress(canvas);
        drawOutBottomProgress(canvas);
        drawInnerProgress(canvas);
    }

    private final void drawOutTopBg(Canvas canvas) {
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mOutStrokeWidth);
        this.mPaint.setShader(this.mTopBgShader);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        int i = this.mOutTopBgStartColor;
        int i2 = this.COLOR_NONE;
        if (i != i2) {
            this.mPaint.setColor(i);
        } else {
            int i3 = this.mOutTopBgEndColor;
            if (i3 != i2) {
                this.mPaint.setColor(i3);
            }
        }
        float fAtan = ((float) (((double) (((float) Math.atan((this.mOutStrokeWidth / 2.0f) / this.mOutCircleRadius)) * 180)) / 3.141592653589793d)) + 5;
        int i4 = this.mOutStrokeWidth;
        canvas.drawArc(i4 / 2.0f, i4 / 2.0f, getWidth() - (this.mOutStrokeWidth / 2.0f), getHeight() - (this.mOutStrokeWidth / 2.0f), (r1 / 2) + 180.0f + fAtan, (180.0f - this.mOutTopBottomSpaceAngle) - (2 * fAtan), false, this.mPaint);
    }

    private final void drawOutBottomBg(Canvas canvas) {
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mOutStrokeWidth);
        this.mPaint.setShader(this.mBottomBgShader);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        int i = this.mOutBottomBgStartColor;
        int i2 = this.COLOR_NONE;
        if (i != i2) {
            this.mPaint.setColor(i);
        } else {
            int i3 = this.mOutBottomBgEndColor;
            if (i3 != i2) {
                this.mPaint.setColor(i3);
            }
        }
        float fAtan = ((float) (((double) (((float) Math.atan((this.mOutStrokeWidth / 2.0f) / this.mOutCircleRadius)) * 180)) / 3.141592653589793d)) + 5;
        int i4 = this.mOutStrokeWidth;
        canvas.drawArc(i4 / 2.0f, i4 / 2.0f, getWidth() - (this.mOutStrokeWidth / 2.0f), getHeight() - (this.mOutStrokeWidth / 2.0f), (180.0f - (r2 / 2)) - fAtan, (this.mOutTopBottomSpaceAngle - 180.0f) + (2 * fAtan), false, this.mPaint);
    }

    private final void drawInnerBg(Canvas canvas) {
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mInnerStrokeWidth);
        this.mPaint.setShader(this.mInnerBgShader);
        this.mPaint.setStrokeCap(Paint.Cap.BUTT);
        int i = this.mInnerBgStartColor;
        int i2 = this.COLOR_NONE;
        if (i != i2) {
            this.mPaint.setColor(i);
        } else {
            int i3 = this.mInnerBgEndColor;
            if (i3 != i2) {
                this.mPaint.setColor(i3);
            }
        }
        canvas.drawArc((getWidth() / 2.0f) - this.mInnerCircleRadius, (getHeight() / 2.0f) - this.mInnerCircleRadius, (getWidth() / 2.0f) + this.mInnerCircleRadius, (getHeight() / 2.0f) + this.mInnerCircleRadius, 270.0f, 360.0f, false, this.mPaint);
    }

    private final void drawOutTopProgress(Canvas canvas) {
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mOutStrokeWidth);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        int i = this.mOutTopStartColor;
        int i2 = this.COLOR_NONE;
        if (i != i2) {
            this.mPaint.setColor(i);
        } else {
            int i3 = this.mOutTopEndColor;
            if (i3 != i2) {
                this.mPaint.setColor(i3);
            }
        }
        float fAtan = ((float) (((double) (((float) Math.atan((this.mOutStrokeWidth / 2.0f) / this.mOutCircleRadius)) * 180)) / 3.141592653589793d)) + 5;
        float f2 = ((180.0f - this.mOutTopBottomSpaceAngle) - (2 * fAtan)) * this.mOutTopProgress * (this.mOutTopAnimatorPercent / this.mOutTopMaxProgress);
        if (f2 != 0.0f) {
            this.mPaint.setShader(this.mTopShader);
            int i4 = this.mOutStrokeWidth;
            canvas.drawArc(i4 / 2.0f, i4 / 2.0f, getWidth() - (this.mOutStrokeWidth / 2.0f), getHeight() - (this.mOutStrokeWidth / 2.0f), (this.mOutTopBottomSpaceAngle / 2.0f) + 180.0f + fAtan, f2, false, this.mPaint);
        }
    }

    private final void drawOutBottomProgress(Canvas canvas) {
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mOutStrokeWidth);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        int i = this.mOutBottomStartColor;
        int i2 = this.COLOR_NONE;
        if (i != i2) {
            this.mPaint.setColor(i);
        } else {
            int i3 = this.mOutBottomEndColor;
            if (i3 != i2) {
                this.mPaint.setColor(i3);
            }
        }
        float fAtan = ((float) (((double) (((float) Math.atan((this.mOutStrokeWidth / 2.0f) / this.mOutCircleRadius)) * 180)) / 3.141592653589793d)) + 5;
        float f2 = ((((this.mOutTopBottomSpaceAngle - 180.0f) + (2 * fAtan)) * this.mOutBottomProgress) * this.mOutBottomAnimatorPercent) / this.mOutBottomMaxProgress;
        if (f2 != 0.0f) {
            this.mPaint.setShader(this.mBottomShader);
            int i4 = this.mOutStrokeWidth;
            canvas.drawArc(i4 / 2.0f, i4 / 2.0f, getWidth() - (this.mOutStrokeWidth / 2.0f), getHeight() - (this.mOutStrokeWidth / 2.0f), (180.0f - (this.mOutTopBottomSpaceAngle / 2)) - fAtan, f2, false, this.mPaint);
        }
    }

    private final void drawInnerProgress(Canvas canvas) {
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mInnerStrokeWidth);
        int i = this.mInnerStartColor;
        int i2 = this.COLOR_NONE;
        if (i != i2) {
            this.mPaint.setColor(i);
        } else {
            int i3 = this.mInnerEndColor;
            if (i3 != i2) {
                this.mPaint.setColor(i3);
            }
        }
        if ((this.mInnerProgress * this.mInnerAnimatorPercent) / this.mInnerMaxProgress < 1.0f) {
            this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        } else {
            this.mPaint.setStrokeCap(Paint.Cap.BUTT);
        }
        float fAtan = ((float) (((double) (((float) Math.atan((this.mOutStrokeWidth / 2.0f) / this.mOutCircleRadius)) * 180)) / 3.141592653589793d)) + 5;
        float f2 = this.mInnerAnimatorPercent;
        float f3 = f2 >= 1.0f ? (this.mInnerProgress * 360.0f) / this.mInnerMaxProgress : (((this.mInnerProgress * 360.0f) * f2) / this.mInnerMaxProgress) - fAtan;
        if (f3 != 0.0f) {
            this.mPaint.setShader(this.mInnerShader);
            int i4 = this.mOutCircleRadius;
            int i5 = this.mInnerCircleRadius;
            int i6 = this.mOutStrokeWidth;
            canvas.drawArc((i4 - i5) + i6, (i4 - i5) + i6, (getWidth() - (this.mOutCircleRadius - this.mInnerCircleRadius)) - this.mOutStrokeWidth, (getHeight() - (this.mOutCircleRadius - this.mInnerCircleRadius)) - this.mOutStrokeWidth, fAtan + 270.0f, f3, false, this.mPaint);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int i = (this.mOutCircleRadius + this.mOutStrokeWidth) * 2;
        setMeasuredDimension(i, i);
    }

    private final void initOutTopBgShader() {
        int i;
        int i2 = this.mOutTopBgStartColor;
        int i3 = this.COLOR_NONE;
        this.mTopBgShader = (i2 == i3 || (i = this.mOutTopBgEndColor) == i3 || i2 == i) ? null : new LinearGradient(0.0f, 0.0f, getWidth(), 0.0f, this.mOutTopBgStartColor, this.mOutTopBgEndColor, Shader.TileMode.REPEAT);
    }

    private final void initOutBottomBgShader() {
        int i;
        int i2 = this.mOutBottomBgStartColor;
        int i3 = this.COLOR_NONE;
        this.mBottomBgShader = (i2 == i3 || (i = this.mOutBottomBgEndColor) == i3 || i2 == i) ? null : new LinearGradient(0.0f, 0.0f, getWidth(), 0.0f, this.mOutBottomBgStartColor, this.mOutBottomBgEndColor, Shader.TileMode.REPEAT);
    }

    private final void initOutTopShader() {
        int i;
        int i2;
        int i3;
        if (this.mOutTopProgress > 0 && this.mOutTopAnimatorPercent > 0 && (i = this.mOutTopStartColor) != (i2 = this.COLOR_NONE) && (i3 = this.mOutTopEndColor) != i2 && i != i3) {
            this.mTopShader = new LinearGradient(0.0f, 0.0f, Math.max(getWidth() / 4.0f, Math.min(getWidth(), (((getWidth() * this.mOutTopAnimatorPercent) * this.mOutTopProgress) / this.mOutTopMaxProgress) + this.mOutStrokeWidth)), 0.0f, this.mOutTopStartColor, this.mOutTopEndColor, Shader.TileMode.REPEAT);
        } else {
            this.mTopShader = (Shader) null;
        }
    }

    private final void initOutBottomShader() {
        int i;
        int i2;
        int i3;
        if (this.mOutBottomProgress > 0 && this.mOutBottomAnimatorPercent > 0 && (i = this.mOutBottomStartColor) != (i2 = this.COLOR_NONE) && (i3 = this.mOutBottomEndColor) != i2 && i != i3) {
            this.mBottomShader = new LinearGradient(0.0f, 0.0f, Math.max(getWidth() / 4.0f, Math.min(getWidth(), (((getWidth() * this.mOutBottomAnimatorPercent) * this.mOutBottomProgress) / this.mOutBottomMaxProgress) + this.mOutStrokeWidth)), 0.0f, this.mOutBottomStartColor, this.mOutBottomEndColor, Shader.TileMode.REPEAT);
        } else {
            this.mBottomShader = (Shader) null;
        }
    }

    private final void initInnerBgShader() {
        int i;
        int i2;
        int i3;
        this.mInnerBgShader = (this.mInnerProgress <= 0 || (i = this.mInnerBgStartColor) == (i2 = this.COLOR_NONE) || (i3 = this.mInnerBgEndColor) == i2 || i == i3) ? null : new LinearGradient(0.0f, 0.0f, getWidth(), 0.0f, this.mInnerBgStartColor, this.mInnerBgEndColor, Shader.TileMode.REPEAT);
    }

    private final void initInnerShader() {
        int i;
        int i2 = this.mInnerStartColor;
        int i3 = this.COLOR_NONE;
        this.mInnerShader = (i2 == i3 || (i = this.mInnerEndColor) == i3 || i2 == i) ? null : new LinearGradient(0.0f, ((getHeight() / 2.0f) - this.mInnerCircleRadius) - this.mInnerStrokeWidth, 0.0f, (getHeight() / 2.0f) + this.mInnerCircleRadius + this.mInnerStrokeWidth, this.mInnerStartColor, this.mInnerEndColor, Shader.TileMode.REPEAT);
    }

    public final void refreshView(boolean animator) {
        if (animator && (this.mOutTopProgress > 0 || this.mOutBottomProgress > 0 || this.mInnerProgress > 0)) {
            startAnimator();
        } else {
            stopAnimator();
            invalidate();
        }
    }

    private final void startAnimator() {
        stopAnimator();
        this.mInnerProgress = Math.min(this.mInnerProgress, this.mInnerMaxProgress);
        this.mOutTopProgress = Math.min(this.mOutTopProgress, this.mOutTopMaxProgress);
        this.mOutBottomProgress = Math.min(this.mOutBottomProgress, this.mOutBottomMaxProgress);
        if (this.mAnimator == null) {
            this.mAnimator = initAnimator();
        }
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void stopAnimator() {
        /*
            r1 = this;
            android.animation.ValueAnimator r0 = r1.mAnimator
            if (r0 == 0) goto L26
            if (r0 != 0) goto L9
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L9:
            boolean r0 = r0.isStarted()
            if (r0 != 0) goto L1c
            android.animation.ValueAnimator r0 = r1.mAnimator
            if (r0 != 0) goto L16
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L16:
            boolean r0 = r0.isRunning()
            if (r0 == 0) goto L26
        L1c:
            android.animation.ValueAnimator r0 = r1.mAnimator
            if (r0 != 0) goto L23
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L23:
            r0.cancel()
        L26:
            r0 = 1065353216(0x3f800000, float:1.0)
            r1.mOutTopAnimatorPercent = r0
            r1.mOutBottomAnimatorPercent = r0
            r1.mInnerAnimatorPercent = r0
            r0 = 0
            android.animation.ValueAnimator r0 = (android.animation.ValueAnimator) r0
            r1.mAnimator = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.FitnessCircleView.stopAnimator():void");
    }

    private final ValueAnimator initAnimator() {
        ValueAnimator valueAnimator = new ValueAnimator();
        PropertyValuesHolder propertyValuesHolderOfFloat = PropertyValuesHolder.ofFloat(this.mOutTopAnimatorPropertyName, 0.0f, this.mOutTopMaxProgress);
        PropertyValuesHolder propertyValuesHolderOfFloat2 = PropertyValuesHolder.ofFloat(this.mOutBottomAnimatorPropertyName, 0.0f, this.mOutBottomMaxProgress);
        PropertyValuesHolder propertyValuesHolderOfFloat3 = PropertyValuesHolder.ofFloat(this.mInnerAnimatorPropertyName, 0.0f, this.mInnerMaxProgress);
        valueAnimator.setDuration(Math.max(this.INNER_ANIMATION_MAX_DURATION, Math.max(this.OUT_TOP_ANIMATION_MAX_DURATION, this.OUT_BOTTOM_ANIMATION_MAX_DURATION)));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setValues(propertyValuesHolderOfFloat, propertyValuesHolderOfFloat2, propertyValuesHolderOfFloat3);
        valueAnimator.setTarget(this);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.FitnessCircleView.initAnimator.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                Object animatedValue = valueAnimator2.getAnimatedValue(FitnessCircleView.this.mOutTopAnimatorPropertyName);
                if (animatedValue != null) {
                    float fFloatValue = ((Float) animatedValue).floatValue();
                    Object animatedValue2 = valueAnimator2.getAnimatedValue(FitnessCircleView.this.mOutBottomAnimatorPropertyName);
                    if (animatedValue2 != null) {
                        float fFloatValue2 = ((Float) animatedValue2).floatValue();
                        Object animatedValue3 = valueAnimator2.getAnimatedValue(FitnessCircleView.this.mInnerAnimatorPropertyName);
                        if (animatedValue3 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Float");
                        }
                        float fFloatValue3 = ((Float) animatedValue3).floatValue();
                        FitnessCircleView.this.mOutTopAnimatorPercent = Math.min(1.0f, fFloatValue / r1.getMOutTopProgress());
                        FitnessCircleView.this.mOutBottomAnimatorPercent = Math.min(1.0f, fFloatValue2 / r0.getMOutBottomProgress());
                        FitnessCircleView.this.mInnerAnimatorPercent = Math.min(1.0f, fFloatValue3 / r0.getMInnerProgress());
                        FitnessCircleView.this.invalidate();
                        if (FitnessCircleView.this.mOutTopAnimatorPercent == 1.0f && FitnessCircleView.this.mOutBottomAnimatorPercent == 1.0f && FitnessCircleView.this.mInnerAnimatorPercent == 1.0f) {
                            FitnessCircleView.this.stopAnimator();
                            return;
                        }
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Float");
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Float");
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() { // from class: com.ido.life.customview.FitnessCircleView.initAnimator.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animation) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                FitnessCircleView.this.mOutTopAnimatorPercent = 1.0f;
                FitnessCircleView.this.mOutBottomAnimatorPercent = 1.0f;
                FitnessCircleView.this.mInnerAnimatorPercent = 1.0f;
                FitnessCircleView.this.invalidate();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                FitnessCircleView.this.mOutTopAnimatorPercent = 1.0f;
                FitnessCircleView.this.mOutBottomAnimatorPercent = 1.0f;
                FitnessCircleView.this.mInnerAnimatorPercent = 1.0f;
                FitnessCircleView.this.invalidate();
            }
        });
        return valueAnimator;
    }
}