package com.ido.life.customview.charter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.gms.fitness.FitnessActivities;
import com.ido.life.R;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.location.MapHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: FloatLineChartBar.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 P2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003OPQB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010=\u001a\u00020>H\u0002J\b\u0010?\u001a\u00020>H\u0002J\b\u0010@\u001a\u00020>H\u0014J\b\u0010A\u001a\u00020<H\u0014J\b\u0010B\u001a\u00020>H\u0016J\u001a\u0010C\u001a\u00020>2\b\u0010D\u001a\u0004\u0018\u00010E2\u0006\u0010F\u001a\u00020<H\u0002J\u0010\u0010G\u001a\u00020>2\u0006\u0010D\u001a\u00020EH\u0014J\u001a\u0010H\u001a\u00020>2\b\u0010D\u001a\u0004\u0018\u00010E2\u0006\u0010F\u001a\u00020<H\u0002J\u0010\u0010I\u001a\u00020>2\u0006\u0010D\u001a\u00020EH\u0014J\b\u0010J\u001a\u00020\u001eH\u0014J\u0010\u0010K\u001a\u00020>2\u0006\u0010L\u001a\u00020\u000bH\u0014J\u0010\u0010M\u001a\u00020\u000b2\u0006\u0010N\u001a\u00020\u000bH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R&\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R&\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R&\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000e\"\u0004\b\u0019\u0010\u0010R&\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010 \u001a\u00020\u001e2\u0006\u0010\n\u001a\u00020\u001e8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R&\u0010%\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u000e\"\u0004\b'\u0010\u0010R&\u0010(\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u000e\"\u0004\b*\u0010\u0010R&\u0010+\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u000e\"\u0004\b-\u0010\u0010R\u0014\u0010.\u001a\b\u0012\u0004\u0012\u0002000/X\u0082\u0004¢\u0006\u0002\n\u0000R&\u00101\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u000e\"\u0004\b3\u0010\u0010R&\u00104\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u000e\"\u0004\b6\u0010\u0010R&\u00107\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u000e\"\u0004\b9\u0010\u0010R\u000e\u0010:\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020<X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006R"}, d2 = {"Lcom/ido/life/customview/charter/FloatLineChartBar;", "Lcom/ido/life/customview/charter/CustomChatBar;", "Lcom/ido/life/customview/charter/FloatLineChartBar$ChartBean;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "mAnimatorRectf", "Landroid/graphics/RectF;", "<set-?>", "", "mBarHighLightColor", "getBarHighLightColor", "()I", "setBarHighLightColor", "(I)V", "mBarMinHeight", "getBarMinHeight", "setBarMinHeight", "mBarNormalColor", "getBarNormalColor", "setBarNormalColor", "mBarRadius", "getBarRadius", "setBarRadius", "mBarSelectedColor", "getBarSelectedColor", "setBarSelectedColor", "mBarSpace", "", "mBarWidth", "mBarWidthSpaceRadius", "getBarWidthSpaceRadius", "()F", "setBarWidthSpaceRadius", "(F)V", "mChartStyle", "getChartStyle", "setChartStyle", "mCircleSolidHighLightColor", "getCircleSolidHighLightColor", "setCirclrSolidHighLightColor", "mCircleStrokeHighLightColor", "getCircleStrokeHighLightColor", "setCirclrStrokeHighLightColor", "mDrawerList", "", "Lcom/ido/life/customview/charter/FloatLineChartBar$DrawInfo;", "mLineHeight", "getLineHeight", "setLineHeight", "mLineHighLightColor", "getLineHighLightColor", "setLineHighLightColor", "mLineNormalColor", "getLineNormalColor", "setLineNormalColor", "mSelectIndex", "mWarning", "", "ajustLabelMaxinValue", "", "caluteBarWithSpaceWidth", "caluteCirclePosition", "canTouch", "clearList", "drawBar", "canvas", "Landroid/graphics/Canvas;", "highLight", "drawChat", "drawLine", "drawXGridLineByPer", "measureLeftLineToLeftDistance", "onChartClick", "i", "queryNearestTouchIndex", "x", "ChartBean", "Companion", "DrawInfo", "app_release"}, k = 1, mv = {1, 1, 16})
public final class FloatLineChartBar extends CustomChatBar<ChartBean> {
    public static final int TYPE_BAR = 1;
    public static final int TYPE_LINE_BAR = 2;
    private HashMap _$_findViewCache;
    private final RectF mAnimatorRectf;
    private int mBarHighLightColor;
    private int mBarMinHeight;
    private int mBarNormalColor;
    private int mBarRadius;
    private int mBarSelectedColor;
    private float mBarSpace;
    private float mBarWidth;
    private float mBarWidthSpaceRadius;
    private int mChartStyle;
    private int mCircleSolidHighLightColor;
    private int mCircleStrokeHighLightColor;
    private final List<DrawInfo> mDrawerList;
    private int mLineHeight;
    private int mLineHighLightColor;
    private int mLineNormalColor;
    private int mSelectIndex;
    private boolean mWarning;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Paint.Style.values().length];

        static {
            $EnumSwitchMapping$0[Paint.Style.FILL.ordinal()] = 1;
        }
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
    public FloatLineChartBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        this.mBarRadius = 5;
        this.mBarHighLightColor = -16776961;
        this.mBarWidthSpaceRadius = 1.0f;
        this.mBarNormalColor = -3355444;
        this.mBarSelectedColor = MapHelper.Standard_Color;
        this.mLineHeight = 2;
        this.mLineHighLightColor = MapHelper.Standard_Color;
        this.mLineNormalColor = -7829368;
        this.mChartStyle = 1;
        this.mCircleStrokeHighLightColor = MapHelper.Standard_Color;
        this.mCircleSolidHighLightColor = MapHelper.Standard_Color;
        this.mDrawerList = new ArrayList();
        this.mAnimatorRectf = new RectF();
        this.mSelectIndex = -1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.FloatLineChartBar);
        Intrinsics.checkExpressionValueIsNotNull(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…leable.FloatLineChartBar)");
        this.mBarRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, this.mBarRadius);
        this.mBarMinHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, this.mBarMinHeight);
        this.mBarHighLightColor = typedArrayObtainStyledAttributes.getColor(0, this.mBarHighLightColor);
        this.mBarWidthSpaceRadius = typedArrayObtainStyledAttributes.getFloat(5, this.mBarWidthSpaceRadius);
        this.mBarNormalColor = typedArrayObtainStyledAttributes.getColor(2, this.mBarNormalColor);
        this.mBarSelectedColor = typedArrayObtainStyledAttributes.getColor(4, this.mBarSelectedColor);
        this.mLineHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, this.mLineHeight);
        this.mLineHighLightColor = typedArrayObtainStyledAttributes.getColor(10, this.mLineHighLightColor);
        this.mLineNormalColor = typedArrayObtainStyledAttributes.getColor(11, this.mLineNormalColor);
        this.mCircleStrokeHighLightColor = typedArrayObtainStyledAttributes.getColor(8, this.mCircleStrokeHighLightColor);
        this.mCircleSolidHighLightColor = typedArrayObtainStyledAttributes.getColor(7, this.mCircleSolidHighLightColor);
        this.mWarning = typedArrayObtainStyledAttributes.getBoolean(12, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    /* JADX INFO: renamed from: getBarMinHeight, reason: from getter */
    public final int getMBarMinHeight() {
        return this.mBarMinHeight;
    }

    public final void setBarMinHeight(int i) {
        this.mBarMinHeight = i;
    }

    /* JADX INFO: renamed from: getBarRadius, reason: from getter */
    public final int getMBarRadius() {
        return this.mBarRadius;
    }

    public final void setBarRadius(int i) {
        this.mBarRadius = i;
    }

    /* JADX INFO: renamed from: getBarHighLightColor, reason: from getter */
    public final int getMBarHighLightColor() {
        return this.mBarHighLightColor;
    }

    public final void setBarHighLightColor(int i) {
        this.mBarHighLightColor = i;
    }

    /* JADX INFO: renamed from: getBarWidthSpaceRadius, reason: from getter */
    public final float getMBarWidthSpaceRadius() {
        return this.mBarWidthSpaceRadius;
    }

    public final void setBarWidthSpaceRadius(float f2) {
        this.mBarWidthSpaceRadius = f2;
    }

    /* JADX INFO: renamed from: getBarNormalColor, reason: from getter */
    public final int getMBarNormalColor() {
        return this.mBarNormalColor;
    }

    public final void setBarNormalColor(int i) {
        this.mBarNormalColor = i;
    }

    /* JADX INFO: renamed from: getBarSelectedColor, reason: from getter */
    public final int getMBarSelectedColor() {
        return this.mBarSelectedColor;
    }

    public final void setBarSelectedColor(int i) {
        this.mBarSelectedColor = i;
    }

    /* JADX INFO: renamed from: getLineHeight, reason: from getter */
    public final int getMLineHeight() {
        return this.mLineHeight;
    }

    public final void setLineHeight(int i) {
        this.mLineHeight = i;
    }

    /* JADX INFO: renamed from: getLineHighLightColor, reason: from getter */
    public final int getMLineHighLightColor() {
        return this.mLineHighLightColor;
    }

    public final void setLineHighLightColor(int i) {
        this.mLineHighLightColor = i;
    }

    /* JADX INFO: renamed from: getLineNormalColor, reason: from getter */
    public final int getMLineNormalColor() {
        return this.mLineNormalColor;
    }

    public final void setLineNormalColor(int i) {
        this.mLineNormalColor = i;
    }

    /* JADX INFO: renamed from: getChartStyle, reason: from getter */
    public final int getMChartStyle() {
        return this.mChartStyle;
    }

    public final void setChartStyle(int i) {
        this.mChartStyle = i;
    }

    /* JADX INFO: renamed from: getCircleStrokeHighLightColor, reason: from getter */
    public final int getMCircleStrokeHighLightColor() {
        return this.mCircleStrokeHighLightColor;
    }

    public final void setCirclrStrokeHighLightColor(int i) {
        this.mCircleStrokeHighLightColor = i;
    }

    /* JADX INFO: renamed from: getCircleSolidHighLightColor, reason: from getter */
    public final int getMCircleSolidHighLightColor() {
        return this.mCircleSolidHighLightColor;
    }

    public final void setCirclrSolidHighLightColor(int i) {
        this.mCircleSolidHighLightColor = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0097  */
    @Override // com.ido.life.customview.charter.CustomChatBar
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void drawChat(android.graphics.Canvas r9) {
        /*
            r8 = this;
            java.lang.String r0 = "canvas"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            super.drawChat(r9)
            java.util.List<T extends com.ido.life.bean.BaseCharBean> r0 = r8.mList
            java.util.Collection r0 = (java.util.Collection) r0
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L19
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L17
            goto L19
        L17:
            r0 = r1
            goto L1a
        L19:
            r0 = r2
        L1a:
            if (r0 != 0) goto Laf
            java.util.List<com.ido.life.customview.charter.FloatLineChartBar$DrawInfo> r0 = r8.mDrawerList
            java.util.Collection r0 = (java.util.Collection) r0
            if (r0 == 0) goto L2b
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L29
            goto L2b
        L29:
            r0 = r1
            goto L2c
        L2b:
            r0 = r2
        L2c:
            if (r0 != 0) goto Laf
            java.util.List<T extends com.ido.life.bean.BaseCharBean> r0 = r8.mList
            int r0 = r0.size()
            java.util.List<com.ido.life.customview.charter.FloatLineChartBar$DrawInfo> r3 = r8.mDrawerList
            int r3 = r3.size()
            if (r0 == r3) goto L3e
            goto Laf
        L3e:
            android.animation.ValueAnimator r0 = r8.mAnimator
            r3 = 2147483647(0x7fffffff, float:NaN)
            if (r0 == 0) goto L97
            android.animation.ValueAnimator r0 = r8.mAnimator
            if (r0 != 0) goto L4c
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L4c:
            boolean r0 = r0.isRunning()
            if (r0 == 0) goto L97
            int r0 = r8.mAnimatorRadius
            if (r0 < r2) goto L97
            int r0 = r9.save()
            float r4 = r8.measureLeftLineToLeftDistance()
            int r5 = r8.measureBottomLineToBottomDistance()
            int r6 = r8.mYGridBottomLineDistance
            int r5 = r5 + r6
            android.graphics.RectF r6 = r8.mAnimatorRectf
            r6.left = r4
            int r4 = r8.getWidth()
            float r4 = (float) r4
            r6.right = r4
            android.graphics.RectF r4 = r8.mAnimatorRectf
            int r6 = r8.getHeight()
            int r6 = r6 - r5
            float r6 = (float) r6
            r4.bottom = r6
            android.graphics.RectF r4 = r8.mAnimatorRectf
            float r6 = r4.bottom
            int r7 = r8.getHeight()
            int r7 = r7 - r5
            int r5 = r8.mAnimatorRadius
            int r7 = r7 * r5
            float r5 = (float) r7
            r7 = 1065353216(0x3f800000, float:1.0)
            float r5 = r5 * r7
            r7 = 100
            float r7 = (float) r7
            float r5 = r5 / r7
            float r6 = r6 - r5
            r4.top = r6
            android.graphics.RectF r4 = r8.mAnimatorRectf
            r9.clipRect(r4)
            goto L98
        L97:
            r0 = r3
        L98:
            int r4 = r8.mChartStyle
            if (r4 == r2) goto La7
            r5 = 2
            if (r4 == r5) goto La0
            goto Laa
        La0:
            r8.drawBar(r9, r1)
            r8.drawLine(r9, r2)
            goto Laa
        La7:
            r8.drawBar(r9, r2)
        Laa:
            if (r0 == r3) goto Laf
            r9.restoreToCount(r0)
        Laf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.charter.FloatLineChartBar.drawChat(android.graphics.Canvas):void");
    }

    private final void drawBar(Canvas canvas, boolean highLight) {
        Paint mPaint = this.mPaint;
        Intrinsics.checkExpressionValueIsNotNull(mPaint, "mPaint");
        mPaint.setStyle(Paint.Style.FILL);
        if (!this.mDrawerList.isEmpty()) {
            int size = this.mDrawerList.size();
            int i = 0;
            while (i < size) {
                Paint mPaint2 = this.mPaint;
                Intrinsics.checkExpressionValueIsNotNull(mPaint2, "mPaint");
                mPaint2.setColor(i == this.mSelectIndex ? this.mBarSelectedColor : highLight ? this.mBarHighLightColor : this.mBarNormalColor);
                DrawInfo drawInfo = this.mDrawerList.get(i);
                if (canvas != null) {
                    RectF barRect = drawInfo.getBarRect();
                    int i2 = this.mBarRadius;
                    canvas.drawRoundRect(barRect, i2, i2, this.mPaint);
                }
                i++;
            }
        }
        this.mSelectIndex = -1;
    }

    private final void drawLine(Canvas canvas, boolean highLight) {
        if (this.mDrawerList.isEmpty()) {
            return;
        }
        int size = this.mDrawerList.size();
        Path path = new Path();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            DrawInfo drawInfo = this.mDrawerList.get(i2);
            if (i2 == 0) {
                path.moveTo(drawInfo.getCircleX(), drawInfo.getCircleY());
            } else {
                path.lineTo(drawInfo.getCircleX(), drawInfo.getCircleY());
            }
        }
        Paint mPaint = this.mPaint;
        Intrinsics.checkExpressionValueIsNotNull(mPaint, "mPaint");
        mPaint.setColor(highLight ? this.mLineHighLightColor : this.mLineNormalColor);
        Paint mPaint2 = this.mPaint;
        Intrinsics.checkExpressionValueIsNotNull(mPaint2, "mPaint");
        mPaint2.setStyle(Paint.Style.STROKE);
        Paint mPaint3 = this.mPaint;
        Intrinsics.checkExpressionValueIsNotNull(mPaint3, "mPaint");
        mPaint3.setPathEffect((PathEffect) null);
        Paint mPaint4 = this.mPaint;
        Intrinsics.checkExpressionValueIsNotNull(mPaint4, "mPaint");
        mPaint4.setStrokeWidth(this.mLineHeight);
        if (canvas != null) {
            canvas.drawPath(path, this.mPaint);
        }
        Paint.Style style = this.mCircleStyle;
        if (style != null && WhenMappings.$EnumSwitchMapping$0[style.ordinal()] == 1) {
            Paint mPaint5 = this.mPaint;
            Intrinsics.checkExpressionValueIsNotNull(mPaint5, "mPaint");
            mPaint5.setStyle(Paint.Style.FILL);
            while (i < size) {
                DrawInfo drawInfo2 = this.mDrawerList.get(i);
                Paint mPaint6 = this.mPaint;
                Intrinsics.checkExpressionValueIsNotNull(mPaint6, "mPaint");
                mPaint6.setColor(i == this.mSelectIndex ? this.mCircleSelectedColor : highLight ? this.mCircleSolidHighLightColor : this.mCircleColor);
                if (canvas != null) {
                    canvas.drawCircle(drawInfo2.getCircleX(), drawInfo2.getCircleY(), this.mCircleRadius, this.mPaint);
                }
                i++;
            }
            return;
        }
        if (this.mCircleBorderWidth > 0) {
            Paint mPaint7 = this.mPaint;
            Intrinsics.checkExpressionValueIsNotNull(mPaint7, "mPaint");
            mPaint7.setStyle(Paint.Style.STROKE);
            Paint mPaint8 = this.mPaint;
            Intrinsics.checkExpressionValueIsNotNull(mPaint8, "mPaint");
            mPaint8.setStrokeWidth(this.mCircleBorderWidth);
            int i3 = 0;
            while (i3 < size) {
                DrawInfo drawInfo3 = this.mDrawerList.get(i3);
                Paint mPaint9 = this.mPaint;
                Intrinsics.checkExpressionValueIsNotNull(mPaint9, "mPaint");
                mPaint9.setColor(i3 == this.mSelectIndex ? this.mCircleStrokeSelectedColor : highLight ? this.mCircleStrokeHighLightColor : this.mCircleStrokeColor);
                if (canvas != null) {
                    canvas.drawCircle(drawInfo3.getCircleX(), drawInfo3.getCircleY(), this.mCircleRadius, this.mPaint);
                }
                i3++;
            }
        }
        Paint mPaint10 = this.mPaint;
        Intrinsics.checkExpressionValueIsNotNull(mPaint10, "mPaint");
        mPaint10.setStyle(Paint.Style.FILL);
        while (i < size) {
            DrawInfo drawInfo4 = this.mDrawerList.get(i);
            Paint mPaint11 = this.mPaint;
            Intrinsics.checkExpressionValueIsNotNull(mPaint11, "mPaint");
            mPaint11.setColor(i == this.mSelectIndex ? this.mCircleSelectedColor : highLight ? this.mCircleSolidHighLightColor : this.mCircleColor);
            if (canvas != null) {
                canvas.drawCircle(drawInfo4.getCircleX(), drawInfo4.getCircleY(), this.mCircleRadius, this.mPaint);
            }
            i++;
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void drawXGridLineByPer(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        if (this.mDrawXGridLine) {
            if (this.mYLeftCount >= 1 || this.mYRightCount >= 1) {
                this.mPaint.reset();
                Paint mPaint = this.mPaint;
                Intrinsics.checkExpressionValueIsNotNull(mPaint, "mPaint");
                mPaint.setTextSize(this.mLeftLabelSize);
                Paint mPaint2 = this.mPaint;
                Intrinsics.checkExpressionValueIsNotNull(mPaint2, "mPaint");
                mPaint2.setStyle(Paint.Style.FILL);
                Rect rect = new Rect();
                this.mPaint.getTextBounds("AA", 0, 2, rect);
                int iMeasureBottomLineToBottomDistance = measureBottomLineToBottomDistance();
                float height = ((getHeight() - iMeasureBottomLineToBottomDistance) - this.mYGridBottomLineDistance) - (rect.height() / 2);
                int iMax = Math.max(this.mYLeftCount, this.mYRightCount);
                if (iMax > 1) {
                    height = (float) ((((double) height) * 1.0d) / ((double) (iMax - 1)));
                }
                this.mPaint.reset();
                Paint mPaint3 = this.mPaint;
                Intrinsics.checkExpressionValueIsNotNull(mPaint3, "mPaint");
                mPaint3.setAntiAlias(true);
                Paint mPaint4 = this.mPaint;
                Intrinsics.checkExpressionValueIsNotNull(mPaint4, "mPaint");
                mPaint4.setStyle(Paint.Style.STROKE);
                Paint mPaint5 = this.mPaint;
                Intrinsics.checkExpressionValueIsNotNull(mPaint5, "mPaint");
                mPaint5.setPathEffect((PathEffect) null);
                Paint mPaint6 = this.mPaint;
                Intrinsics.checkExpressionValueIsNotNull(mPaint6, "mPaint");
                mPaint6.setColor(this.mGridXColor);
                Paint mPaint7 = this.mPaint;
                Intrinsics.checkExpressionValueIsNotNull(mPaint7, "mPaint");
                mPaint7.setStrokeWidth(this.mGridXHeight);
                float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance();
                float height2 = (getHeight() - iMeasureBottomLineToBottomDistance) - this.mYGridBottomLineDistance;
                for (int i = 0; i < iMax; i++) {
                    if (i != 2) {
                        if (i == 3) {
                            if (this.mWarning) {
                                Paint mPaint8 = this.mPaint;
                                Intrinsics.checkExpressionValueIsNotNull(mPaint8, "mPaint");
                                mPaint8.setColor(Color.parseColor("#FFA3AB"));
                            } else {
                                Paint mPaint9 = this.mPaint;
                                Intrinsics.checkExpressionValueIsNotNull(mPaint9, "mPaint");
                                mPaint9.setColor(this.mGridXColor);
                            }
                        } else {
                            Paint mPaint10 = this.mPaint;
                            Intrinsics.checkExpressionValueIsNotNull(mPaint10, "mPaint");
                            mPaint10.setColor(this.mGridXColor);
                        }
                    } else if (this.mWarning) {
                        Paint mPaint11 = this.mPaint;
                        Intrinsics.checkExpressionValueIsNotNull(mPaint11, "mPaint");
                        mPaint11.setColor(Color.parseColor("#FFD57F"));
                    } else {
                        Paint mPaint12 = this.mPaint;
                        Intrinsics.checkExpressionValueIsNotNull(mPaint12, "mPaint");
                        mPaint12.setColor(this.mGridXColor);
                    }
                    float f2 = height2 - (i * height);
                    canvas.drawLine(fMeasureLeftLineToLeftDistance, f2, getWidth(), f2, this.mPaint);
                }
            }
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void caluteCirclePosition() {
        int i;
        int i2;
        int i3;
        this.mDrawerList.clear();
        Collection collection = this.mList;
        int i4 = 0;
        if (collection == null || collection.isEmpty()) {
            return;
        }
        ajustLabelMaxinValue();
        caluteBarWithSpaceWidth();
        int iMeasureBottomLineToBottomDistance = measureBottomLineToBottomDistance();
        if (this.mDrawXGridLine) {
            iMeasureBottomLineToBottomDistance += this.mYGridBottomLineDistance;
        }
        float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance();
        int height = getHeight() - iMeasureBottomLineToBottomDistance;
        Paint mPaint = this.mPaint;
        Intrinsics.checkExpressionValueIsNotNull(mPaint, "mPaint");
        mPaint.setStyle(Paint.Style.FILL);
        Paint mPaint2 = this.mPaint;
        Intrinsics.checkExpressionValueIsNotNull(mPaint2, "mPaint");
        mPaint2.setTextSize(this.mLeftLabelSize);
        Rect rect = new Rect();
        int i5 = 2;
        this.mPaint.getTextBounds("AA", 0, 2, rect);
        float fHeight = this.mYMaxValue - this.mYMinValue > ((float) 0) ? (float) ((((double) (height - (rect.height() / 2))) * 1.0d) / ((double) (this.mYMaxValue - this.mYMinValue))) : 0.0f;
        float width = this.mXMaxValue - this.mXMinValue > 0 ? (getWidth() - fMeasureLeftLineToLeftDistance) / ((this.mXMaxValue - this.mXMinValue) + 1) : 0.0f;
        int size = this.mList.size();
        while (i4 < size) {
            ChartBean chartBean = (ChartBean) this.mList.get(i4);
            float fCoerceAtLeast = RangesKt.coerceAtLeast(chartBean.getMaxValue(), this.mYMinValue);
            float fCoerceAtLeast2 = RangesKt.coerceAtLeast(chartBean.getMinValue(), this.mYMinValue);
            float f2 = height;
            float f3 = f2 - ((fCoerceAtLeast - this.mYMinValue) * fHeight);
            float f4 = f2 - ((fCoerceAtLeast2 - this.mYMinValue) * fHeight);
            if (f3 == f4 && (i3 = this.mBarMinHeight) > 0) {
                f3 -= i3 / 2;
                f4 += i3 / 2;
            }
            if (this.mBottomLabelCenter) {
                float f5 = i5;
                float f6 = ((chartBean.x - this.mXMinValue) * width) + fMeasureLeftLineToLeftDistance + (width / f5);
                float f7 = this.mBarWidth;
                float f8 = f6 - (f7 / f5);
                i = size;
                this.mDrawerList.add(new DrawInfo(new RectF(f8, f3, f7 + f8, f4), f8 + (this.mBarWidth / f5), f2 - ((RangesKt.coerceAtLeast(chartBean.getAvgValue(), this.mYMinValue) - this.mYMinValue) * fHeight)));
                i2 = 2;
            } else {
                i = size;
                float f9 = ((chartBean.x - this.mXMinValue) * width) + fMeasureLeftLineToLeftDistance;
                List<DrawInfo> list = this.mDrawerList;
                RectF rectF = new RectF(f9, f3, this.mBarWidth + f9, f4);
                i2 = 2;
                list.add(new DrawInfo(rectF, f9 + (this.mBarWidth / 2), f2 - ((RangesKt.coerceAtLeast(chartBean.getAvgValue(), this.mYMinValue) - this.mYMinValue) * fHeight)));
            }
            i4++;
            i5 = i2;
            size = i;
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected float measureLeftLineToLeftDistance() {
        float fMeasureLeftLabelMaxWidth = 0.0f;
        float f2 = this.mLeftLineEnabled ? this.mLeftLineWidth + 0.0f : 0.0f;
        if (this.mLeftLabelEnabled && this.mLabelYLeftList.size() > 0) {
            fMeasureLeftLabelMaxWidth = measureLeftLabelMaxWidth() + this.mLeftLabelLineDistance;
        }
        return f2 + fMeasureLeftLabelMaxWidth;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x001d, code lost:
    
        if (r0.isStarted() != false) goto L14;
     */
    @Override // com.ido.life.customview.charter.CustomChatBar
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean canTouch() {
        /*
            r2 = this;
            android.animation.ValueAnimator r0 = r2.mAnimator
            r1 = 0
            if (r0 == 0) goto L20
            android.animation.ValueAnimator r0 = r2.mAnimator
            if (r0 != 0) goto Lc
            kotlin.jvm.internal.Intrinsics.throwNpe()
        Lc:
            boolean r0 = r0.isRunning()
            if (r0 != 0) goto L1f
            android.animation.ValueAnimator r0 = r2.mAnimator
            if (r0 != 0) goto L19
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L19:
            boolean r0 = r0.isStarted()
            if (r0 == 0) goto L20
        L1f:
            return r1
        L20:
            java.util.List<com.ido.life.customview.charter.FloatLineChartBar$DrawInfo> r0 = r2.mDrawerList
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L29
            return r1
        L29:
            com.ido.life.customview.charter.CustomChatBar$ChartClickListener r0 = r2.mClickListener
            if (r0 == 0) goto L2e
            r1 = 1
        L2e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.charter.FloatLineChartBar.canTouch():boolean");
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    public int queryNearestTouchIndex(int x) {
        int i = -1;
        if (!this.mDrawerList.isEmpty()) {
            int size = this.mDrawerList.size();
            float fAbs = Float.MAX_VALUE;
            for (int i2 = 0; i2 < size; i2++) {
                RectF barRect = this.mDrawerList.get(i2).getBarRect();
                float f2 = x;
                if (Math.abs(f2 - barRect.left) + Math.abs(f2 - barRect.right) < fAbs) {
                    fAbs = Math.abs(f2 - barRect.left) + Math.abs(f2 - barRect.right);
                    i = i2;
                }
            }
        }
        return i;
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void onChartClick(int i) {
        super.onChartClick(i);
        if (this.mDrawerList.size() < i + 1 || i < 0) {
            return;
        }
        this.mClickListener.onChartClick(this, this.mDrawerList.get(i).getBarRect(), i);
        this.mSelectIndex = i;
        refreshChart(false);
    }

    private final void caluteBarWithSpaceWidth() {
        this.mBarWidth = 0.0f;
        this.mBarSpace = 0.0f;
        float width = (getWidth() - measureLeftLineToLeftDistance()) * 1.0f;
        int i = (this.mXMaxValue - this.mXMinValue) + 1;
        if (i < 1) {
            return;
        }
        if (i == 1) {
            float f2 = this.mBarWidthSpaceRadius;
            this.mBarWidth = width / (1 + (1.0f / f2));
            this.mBarSpace = this.mBarWidth / f2;
        } else {
            float f3 = this.mBarWidthSpaceRadius;
            this.mBarWidth = width / (i + (((i - 1) * 1.0f) / f3));
            this.mBarSpace = this.mBarWidth / f3;
        }
    }

    private final void ajustLabelMaxinValue() {
        Collection collection = this.mList;
        if (collection == null || collection.isEmpty()) {
            return;
        }
        if (isAutoXMaxinValue() || isAutoYMaxinValue()) {
            int i = Integer.MAX_VALUE;
            int iMax = Integer.MIN_VALUE;
            int iMin = Integer.MAX_VALUE;
            int i2 = Integer.MIN_VALUE;
            for (T t : this.mList) {
                iMax = (int) Math.max(this.mXMaxValue, t.x);
                int iMin2 = (int) Math.min(this.mXMinValue, t.x);
                int iMax2 = (int) Math.max(this.mYMaxValue, t.getMaxValue());
                iMin = (int) Math.min(this.mYMinValue, t.getMinValue());
                i = iMin2;
                i2 = iMax2;
            }
            if (isAutoXMaxinValue()) {
                this.mXMaxValue = iMax;
                this.mXMinValue = i;
            }
            if (isAutoYMaxinValue()) {
                this.mYMaxValue = i2;
                this.mYMinValue = iMin;
            }
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    public void clearList() {
        super.clearList();
        this.mDrawerList.clear();
    }

    /* JADX INFO: compiled from: FloatLineChartBar.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003JO\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020\u0003HÖ\u0001J\t\u0010'\u001a\u00020(HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0011\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\rR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\r¨\u0006)"}, d2 = {"Lcom/ido/life/customview/charter/FloatLineChartBar$ChartBean;", "Lcom/ido/life/bean/BaseCharBean;", "dataIndex", "", "xIndex", "", "avgValue", "maxValue", "minValue", "durHour", "durMinute", "(IFFFFII)V", "getAvgValue", "()F", "setAvgValue", "(F)V", "getDataIndex", "()I", "getDurHour", "setDurHour", "(I)V", "getDurMinute", "setDurMinute", "getMaxValue", "getMinValue", "getXIndex", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class ChartBean extends BaseCharBean {
        private float avgValue;
        private final int dataIndex;
        private int durHour;
        private int durMinute;
        private final float maxValue;
        private final float minValue;
        private final float xIndex;

        public static /* synthetic */ ChartBean copy$default(ChartBean chartBean, int i, float f2, float f3, float f4, float f5, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = chartBean.dataIndex;
            }
            if ((i4 & 2) != 0) {
                f2 = chartBean.xIndex;
            }
            float f6 = f2;
            if ((i4 & 4) != 0) {
                f3 = chartBean.avgValue;
            }
            float f7 = f3;
            if ((i4 & 8) != 0) {
                f4 = chartBean.maxValue;
            }
            float f8 = f4;
            if ((i4 & 16) != 0) {
                f5 = chartBean.minValue;
            }
            float f9 = f5;
            if ((i4 & 32) != 0) {
                i2 = chartBean.durHour;
            }
            int i5 = i2;
            if ((i4 & 64) != 0) {
                i3 = chartBean.durMinute;
            }
            return chartBean.copy(i, f6, f7, f8, f9, i5, i3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getDataIndex() {
            return this.dataIndex;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final float getXIndex() {
            return this.xIndex;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final float getAvgValue() {
            return this.avgValue;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final float getMaxValue() {
            return this.maxValue;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final float getMinValue() {
            return this.minValue;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getDurHour() {
            return this.durHour;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getDurMinute() {
            return this.durMinute;
        }

        public final ChartBean copy(int dataIndex, float xIndex, float avgValue, float maxValue, float minValue, int durHour, int durMinute) {
            return new ChartBean(dataIndex, xIndex, avgValue, maxValue, minValue, durHour, durMinute);
        }

        @Override // android.graphics.PointF
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ChartBean)) {
                return false;
            }
            ChartBean chartBean = (ChartBean) other;
            return this.dataIndex == chartBean.dataIndex && Float.compare(this.xIndex, chartBean.xIndex) == 0 && Float.compare(this.avgValue, chartBean.avgValue) == 0 && Float.compare(this.maxValue, chartBean.maxValue) == 0 && Float.compare(this.minValue, chartBean.minValue) == 0 && this.durHour == chartBean.durHour && this.durMinute == chartBean.durMinute;
        }

        @Override // android.graphics.PointF
        public int hashCode() {
            return (((((((((((Integer.valueOf(this.dataIndex).hashCode() * 31) + Float.valueOf(this.xIndex).hashCode()) * 31) + Float.valueOf(this.avgValue).hashCode()) * 31) + Float.valueOf(this.maxValue).hashCode()) * 31) + Float.valueOf(this.minValue).hashCode()) * 31) + Integer.valueOf(this.durHour).hashCode()) * 31) + Integer.valueOf(this.durMinute).hashCode();
        }

        @Override // android.graphics.PointF
        public String toString() {
            return "ChartBean(dataIndex=" + this.dataIndex + ", xIndex=" + this.xIndex + ", avgValue=" + this.avgValue + ", maxValue=" + this.maxValue + ", minValue=" + this.minValue + ", durHour=" + this.durHour + ", durMinute=" + this.durMinute + ")";
        }

        public final int getDataIndex() {
            return this.dataIndex;
        }

        public final float getXIndex() {
            return this.xIndex;
        }

        public final float getAvgValue() {
            return this.avgValue;
        }

        public final void setAvgValue(float f2) {
            this.avgValue = f2;
        }

        public final float getMaxValue() {
            return this.maxValue;
        }

        public final float getMinValue() {
            return this.minValue;
        }

        public final int getDurHour() {
            return this.durHour;
        }

        public final void setDurHour(int i) {
            this.durHour = i;
        }

        public final int getDurMinute() {
            return this.durMinute;
        }

        public final void setDurMinute(int i) {
            this.durMinute = i;
        }

        public ChartBean(int i, float f2, float f3, float f4, float f5, int i2, int i3) {
            super(i, f2, f4);
            this.dataIndex = i;
            this.xIndex = f2;
            this.avgValue = f3;
            this.maxValue = f4;
            this.minValue = f5;
            this.durHour = i2;
            this.durMinute = i3;
        }
    }

    /* JADX INFO: compiled from: FloatLineChartBar.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0018"}, d2 = {"Lcom/ido/life/customview/charter/FloatLineChartBar$DrawInfo;", "", "barRect", "Landroid/graphics/RectF;", "circleX", "", "circleY", "(Landroid/graphics/RectF;FF)V", "getBarRect", "()Landroid/graphics/RectF;", "getCircleX", "()F", "getCircleY", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class DrawInfo {
        private final RectF barRect;
        private final float circleX;
        private final float circleY;

        public static /* synthetic */ DrawInfo copy$default(DrawInfo drawInfo, RectF rectF, float f2, float f3, int i, Object obj) {
            if ((i & 1) != 0) {
                rectF = drawInfo.barRect;
            }
            if ((i & 2) != 0) {
                f2 = drawInfo.circleX;
            }
            if ((i & 4) != 0) {
                f3 = drawInfo.circleY;
            }
            return drawInfo.copy(rectF, f2, f3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final RectF getBarRect() {
            return this.barRect;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final float getCircleX() {
            return this.circleX;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final float getCircleY() {
            return this.circleY;
        }

        public final DrawInfo copy(RectF barRect, float circleX, float circleY) {
            Intrinsics.checkParameterIsNotNull(barRect, "barRect");
            return new DrawInfo(barRect, circleX, circleY);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DrawInfo)) {
                return false;
            }
            DrawInfo drawInfo = (DrawInfo) other;
            return Intrinsics.areEqual(this.barRect, drawInfo.barRect) && Float.compare(this.circleX, drawInfo.circleX) == 0 && Float.compare(this.circleY, drawInfo.circleY) == 0;
        }

        public int hashCode() {
            RectF rectF = this.barRect;
            return ((((rectF != null ? rectF.hashCode() : 0) * 31) + Float.valueOf(this.circleX).hashCode()) * 31) + Float.valueOf(this.circleY).hashCode();
        }

        public String toString() {
            return "DrawInfo(barRect=" + this.barRect + ", circleX=" + this.circleX + ", circleY=" + this.circleY + ")";
        }

        public DrawInfo(RectF barRect, float f2, float f3) {
            Intrinsics.checkParameterIsNotNull(barRect, "barRect");
            this.barRect = barRect;
            this.circleX = f2;
            this.circleY = f3;
        }

        public final RectF getBarRect() {
            return this.barRect;
        }

        public final float getCircleX() {
            return this.circleX;
        }

        public final float getCircleY() {
            return this.circleY;
        }
    }
}