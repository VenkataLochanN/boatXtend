package com.ido.life.customview.charter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.ido.life.R;
import com.ido.life.bean.GoalLineInfo;
import com.ido.life.bean.GradientBarPoint;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GoalGradientBarChartBar.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0017\u0018B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0012H\u0014J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0014R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/ido/life/customview/charter/GoalGradientBarChartBar;", "Lcom/ido/life/customview/charter/GradientBarChartBar;", "Lcom/ido/life/bean/GradientBarPoint;", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "mCallback", "Lcom/ido/life/customview/charter/GoalGradientBarChartBar$BarSizeCaluteCallback;", "getMCallback", "()Lcom/ido/life/customview/charter/GoalGradientBarChartBar$BarSizeCaluteCallback;", "setMCallback", "(Lcom/ido/life/customview/charter/GoalGradientBarChartBar$BarSizeCaluteCallback;)V", "mLabelGravity", "", "mRightLabelLineDistance", "calculateGoalLinePosition", "", "caluteCirclePosition", "drawGoalLabelLine", "canvas", "Landroid/graphics/Canvas;", "BarSizeCaluteCallback", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class GoalGradientBarChartBar extends GradientBarChartBar<GradientBarPoint> {
    public static final int CENTER = 3;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    private HashMap _$_findViewCache;
    private BarSizeCaluteCallback mCallback;
    private int mLabelGravity;
    private int mRightLabelLineDistance;

    /* JADX INFO: compiled from: GoalGradientBarChartBar.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/ido/life/customview/charter/GoalGradientBarChartBar$BarSizeCaluteCallback;", "", "onBarSizeCaluteComplete", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public interface BarSizeCaluteCallback {
        void onBarSizeCaluteComplete();
    }

    @Override // com.ido.life.customview.charter.GradientBarChartBar
    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.ido.life.customview.charter.GradientBarChartBar
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
    public GoalGradientBarChartBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attributeSet");
        this.mLabelGravity = 1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GoalGradientBarChartBar);
        Intrinsics.checkExpressionValueIsNotNull(typedArrayObtainStyledAttributes, "context.obtainStyledAttr….GoalGradientBarChartBar)");
        this.mLabelGravity = typedArrayObtainStyledAttributes.getInt(0, 1);
        this.mRightLabelLineDistance = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, this.mRightLabelLineDistance);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final BarSizeCaluteCallback getMCallback() {
        return this.mCallback;
    }

    public final void setMCallback(BarSizeCaluteCallback barSizeCaluteCallback) {
        this.mCallback = barSizeCaluteCallback;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0270  */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v5 */
    @Override // com.ido.life.customview.charter.CustomChatBar
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void drawGoalLabelLine(android.graphics.Canvas r24) {
        /*
            Method dump skipped, instruction units count: 722
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.charter.GoalGradientBarChartBar.drawGoalLabelLine(android.graphics.Canvas):void");
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void calculateGoalLinePosition() {
        float f2;
        double d2;
        int i;
        if (this.mGoalLineList == null || this.mGoalLineList.size() <= 0) {
            return;
        }
        adjustLabelMaxineValue();
        float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance();
        this.mPaint.reset();
        Paint mPaint = this.mPaint;
        Intrinsics.checkExpressionValueIsNotNull(mPaint, "mPaint");
        mPaint.setTextSize(this.mGoalXLabelTextSize);
        Paint mPaint2 = this.mPaint;
        Intrinsics.checkExpressionValueIsNotNull(mPaint2, "mPaint");
        mPaint2.setStyle(Paint.Style.FILL);
        Rect rect = new Rect();
        this.mPaint.getTextBounds("AA", 0, 2, rect);
        int height = (getHeight() - measureBottomLineToBottomDistance()) - rect.height();
        float height2 = (getHeight() - measureBottomLineToBottomDistance()) - this.mGoalLineWidth;
        if (this.mDrawXGridLine) {
            height -= this.mGridXHeight + this.mYGridBottomLineDistance;
            height2 -= this.mGridXHeight + this.mYGridBottomLineDistance;
        }
        Paint mPaint3 = this.mPaint;
        Intrinsics.checkExpressionValueIsNotNull(mPaint3, "mPaint");
        mPaint3.setTextSize(this.mGoalXLabelTextSize);
        Rect rect2 = new Rect();
        float width = getWidth() - fMeasureLeftLineToLeftDistance;
        int circleSize = getCircleSize();
        if (!this.mBottomLabelCenter) {
            fMeasureLeftLineToLeftDistance += circleSize;
            width -= circleSize * 2;
        }
        if (this.mXMaxValue - this.mXMinValue > 0) {
            if (this.mBottomLabelCenter) {
                d2 = ((double) width) * 1.0d;
                i = (this.mXMaxValue - this.mXMinValue) + 1;
            } else {
                d2 = ((double) width) * 1.0d;
                i = this.mXMaxValue - this.mXMinValue;
            }
            f2 = (float) (d2 / ((double) i));
        } else {
            f2 = 0.0f;
        }
        this.mPaint.getTextBounds("AA", 0, 2, rect2);
        float fHeight = this.mYMaxValue - this.mYMinValue > ((float) 0) ? (float) ((((double) (height - rect2.height())) * 1.0d) / ((double) (this.mYMaxValue - this.mYMinValue))) : 0.0f;
        for (GoalLineInfo goalLineInfo : this.mGoalLineList) {
            Intrinsics.checkExpressionValueIsNotNull(goalLineInfo, "goalLineInfo");
            if (goalLineInfo.getLineOrientation() == 0) {
                goalLineInfo.setPosition(((this.mGoalLineWidth / 2) + height2) - ((goalLineInfo.getValue() - this.mYMinValue) * fHeight));
            } else {
                goalLineInfo.setPosition(((fMeasureLeftLineToLeftDistance - (this.mGoalLineWidth / 2)) + ((goalLineInfo.getValue() - this.mYMinValue) * f2)) - (this.mGoalLineWidth / 2));
            }
        }
    }

    @Override // com.ido.life.customview.charter.BarChartBar, com.ido.life.customview.charter.CustomChatBar
    protected void caluteCirclePosition() {
        this.mCircleRegion.clear();
        if (this.mList == null || this.mList.size() == 0) {
            return;
        }
        adjustLabelMaxineValue();
        float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance();
        if (this.mLeftLabelLineDistance > 0) {
            fMeasureLeftLineToLeftDistance += this.mLeftLabelLineDistance;
        }
        float chartBottom = getChartBottom();
        float chartHeight = getChartHeight();
        int i = (this.mXMaxValue - this.mXMinValue) + 1;
        float width = (getWidth() - fMeasureLeftLineToLeftDistance) - this.mRightLabelLineDistance;
        if (i > 0) {
            width /= i;
            this.mBarWidth = this.mBarSpaceRadius * width;
        }
        float f2 = this.mYMaxValue - this.mYMinValue > ((float) 0) ? (float) ((((double) chartHeight) * 1.0d) / ((double) (this.mYMaxValue - this.mYMinValue))) : 0.0f;
        int size = this.mList.size();
        for (int i2 = 0; i2 < size; i2++) {
            GradientBarPoint gradientBarPoint = (GradientBarPoint) this.mList.get(i2);
            if (gradientBarPoint != null) {
                float f3 = chartBottom - ((gradientBarPoint.y - this.mYMinValue) * f2);
                if (this.mBottomLabelCenter) {
                    float f4 = ((width - this.mBarWidth) / 2) + fMeasureLeftLineToLeftDistance + ((gradientBarPoint.x - this.mXMinValue) * width);
                    this.mCircleRegion.add(new RectF(f4, f3, (this.mBarWidth * gradientBarPoint.getScaleRadius()) + f4, chartBottom));
                } else {
                    float f5 = ((gradientBarPoint.x - this.mXMinValue) * width) + fMeasureLeftLineToLeftDistance;
                    this.mCircleRegion.add(new RectF(f5, f3, (this.mBarWidth * gradientBarPoint.getScaleRadius()) + f5, chartBottom));
                }
            }
        }
        BarSizeCaluteCallback barSizeCaluteCallback = this.mCallback;
        if (barSizeCaluteCallback != null) {
            barSizeCaluteCallback.onBarSizeCaluteComplete();
        }
    }
}