package com.ido.life.customview.charter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.gms.fitness.FitnessActivities;
import com.ido.life.bean.BaseCharBean;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecentSituationWeekChart.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001bB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0014\u001a\u00020\u0015H\u0014J\u0010\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0014J\u0010\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0014J\u0010\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0014R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001c"}, d2 = {"Lcom/ido/life/customview/charter/RecentSituationWeekChart;", "Lcom/ido/life/customview/charter/LineChartBar;", "Lcom/ido/life/customview/charter/RecentSituationWeekChart$ChartBean;", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "mPathEffect", "Landroid/graphics/DashPathEffect;", "getMPathEffect", "()Landroid/graphics/DashPathEffect;", "setMPathEffect", "(Landroid/graphics/DashPathEffect;)V", "mVisualLineColor", "", "getMVisualLineColor", "()I", "setMVisualLineColor", "(I)V", "caluteCirclePosition", "", "drawChat", "canvas", "Landroid/graphics/Canvas;", "drawLeftLabelPer", "drawXGridLineByPer", "ChartBean", "app_release"}, k = 1, mv = {1, 1, 16})
public final class RecentSituationWeekChart extends LineChartBar<ChartBean> {
    private HashMap _$_findViewCache;
    private DashPathEffect mPathEffect;
    private int mVisualLineColor;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Paint.Style.values().length];

        static {
            $EnumSwitchMapping$0[Paint.Style.FILL.ordinal()] = 1;
            $EnumSwitchMapping$0[Paint.Style.FILL_AND_STROKE.ordinal()] = 2;
            $EnumSwitchMapping$0[Paint.Style.STROKE.ordinal()] = 3;
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
    public RecentSituationWeekChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attributeSet");
        this.mPathEffect = new DashPathEffect(new float[]{15.0f, 5.0f}, 5.0f);
        this.mVisualLineColor = Color.parseColor("#CCCDD1");
    }

    public final DashPathEffect getMPathEffect() {
        return this.mPathEffect;
    }

    public final void setMPathEffect(DashPathEffect dashPathEffect) {
        Intrinsics.checkParameterIsNotNull(dashPathEffect, "<set-?>");
        this.mPathEffect = dashPathEffect;
    }

    public final int getMVisualLineColor() {
        return this.mVisualLineColor;
    }

    public final void setMVisualLineColor(int i) {
        this.mVisualLineColor = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0098  */
    /* JADX WARN: Type inference failed for: r1v29, types: [T, android.graphics.RectF] */
    /* JADX WARN: Type inference failed for: r1v41, types: [T, android.graphics.RectF] */
    /* JADX WARN: Type inference failed for: r1v56, types: [T, android.graphics.RectF] */
    /* JADX WARN: Type inference failed for: r3v14, types: [T, android.graphics.RectF] */
    /* JADX WARN: Type inference failed for: r5v12, types: [T, android.graphics.RectF] */
    @Override // com.ido.life.customview.charter.LineChartBar, com.ido.life.customview.charter.CustomChatBar
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void drawChat(android.graphics.Canvas r18) {
        /*
            Method dump skipped, instruction units count: 805
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.charter.RecentSituationWeekChart.drawChat(android.graphics.Canvas):void");
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void drawLeftLabelPer(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        if (this.mLeftLabelEnabled && this.mLabelYLeftList.size() != 0 && this.mYLeftCount == this.mLabelYLeftList.size()) {
            this.mPaint.reset();
            Paint mPaint = this.mPaint;
            Intrinsics.checkExpressionValueIsNotNull(mPaint, "mPaint");
            mPaint.setStyle(Paint.Style.FILL);
            Paint mPaint2 = this.mPaint;
            Intrinsics.checkExpressionValueIsNotNull(mPaint2, "mPaint");
            mPaint2.setTextSize(this.mLeftLabelSize);
            Rect rect = new Rect();
            this.mPaint.getTextBounds("AA", 0, 2, rect);
            int iHeight = rect.height();
            this.mPaint.reset();
            Paint mPaint3 = this.mPaint;
            Intrinsics.checkExpressionValueIsNotNull(mPaint3, "mPaint");
            mPaint3.setStyle(Paint.Style.FILL);
            Paint mPaint4 = this.mPaint;
            Intrinsics.checkExpressionValueIsNotNull(mPaint4, "mPaint");
            mPaint4.setAntiAlias(true);
            Paint mPaint5 = this.mPaint;
            Intrinsics.checkExpressionValueIsNotNull(mPaint5, "mPaint");
            mPaint5.setColor(this.mLeftLabelColor);
            Paint mPaint6 = this.mPaint;
            Intrinsics.checkExpressionValueIsNotNull(mPaint6, "mPaint");
            mPaint6.setTextSize(this.mLeftLabelSize);
            float height = getHeight() - measureBottomLineToBottomDistance();
            if (this.mDrawXGridLine) {
                height -= this.mYGridBottomLineDistance;
            }
            int size = this.mLabelYLeftList.size();
            Rect rect2 = new Rect();
            if (size == 1) {
                String str = this.mLabelYLeftList.get(0);
                Intrinsics.checkExpressionValueIsNotNull(str, "mLabelYLeftList[0]");
                String str2 = str;
                this.mPaint.getTextBounds(str2, 0, str2.length(), rect2);
                canvas.drawText(str2, 0.0f, height / 2, this.mPaint);
                return;
            }
            float f2 = size > 1 ? (float) ((((double) (height - iHeight)) * 1.0d) / ((double) (size - 1))) : height - iHeight;
            for (int i = 0; i < size; i++) {
                String str3 = this.mLabelYLeftList.get(i);
                Intrinsics.checkExpressionValueIsNotNull(str3, "mLabelYLeftList[i]");
                String str4 = str3;
                this.mPaint.getTextBounds(str4, 0, str4.length(), rect2);
                canvas.drawText(str4, 0.0f, height - (i * f2), this.mPaint);
            }
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
                float height = ((getHeight() - iMeasureBottomLineToBottomDistance) - this.mYGridBottomLineDistance) - rect.height();
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
                mPaint5.setPathEffect(new DashPathEffect(new float[]{15.0f, 5.0f}, 5.0f));
                Paint mPaint6 = this.mPaint;
                Intrinsics.checkExpressionValueIsNotNull(mPaint6, "mPaint");
                mPaint6.setColor(this.mGridXColor);
                Paint mPaint7 = this.mPaint;
                Intrinsics.checkExpressionValueIsNotNull(mPaint7, "mPaint");
                mPaint7.setStrokeWidth(this.mGridXHeight);
                float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance();
                float height2 = (getHeight() - iMeasureBottomLineToBottomDistance) - this.mYGridBottomLineDistance;
                for (int i = 0; i < iMax; i++) {
                    float f2 = height2 - (i * height);
                    canvas.drawLine(fMeasureLeftLineToLeftDistance, f2, getWidth(), f2, this.mPaint);
                }
            }
        }
    }

    @Override // com.ido.life.customview.charter.LineChartBar, com.ido.life.customview.charter.CustomChatBar
    protected void caluteCirclePosition() {
        float f2;
        float f3;
        double d2;
        int i;
        this.mCircleRegion.clear();
        if (this.mList.size() == 0) {
            return;
        }
        adjustLabelMaxineValue();
        float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance();
        int circleSize = getCircleSize();
        float chartHeight = getChartHeight();
        float chartBottom = getChartBottom();
        float width = getWidth() - fMeasureLeftLineToLeftDistance;
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
        float f4 = this.mYMaxValue - this.mYMinValue > ((float) 0) ? (float) ((((double) chartHeight) * 1.0d) / ((double) (this.mYMaxValue - this.mYMinValue))) : 0.0f;
        if (this.mList.size() > 0) {
            for (T t : this.mList) {
                if (this.mBottomLabelCenter) {
                    f3 = (float) (((double) fMeasureLeftLineToLeftDistance) + (((double) f2) / 2.0d) + ((double) ((t.x - this.mXMinValue) * f2)));
                } else {
                    f3 = ((t.x - this.mXMinValue) * f2) + fMeasureLeftLineToLeftDistance;
                }
                float f5 = chartBottom - ((t.y - this.mYMinValue) * f4);
                float f6 = circleSize;
                this.mCircleRegion.add(new RectF(f3 - f6, f5 - f6, f3 + f6, f5 + f6));
            }
        }
    }

    /* JADX INFO: compiled from: RecentSituationWeekChart.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J1\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u00052\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u0003HÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015¨\u0006#"}, d2 = {"Lcom/ido/life/customview/charter/RecentSituationWeekChart$ChartBean;", "Lcom/ido/life/bean/BaseCharBean;", "circleColor", "", "effect", "", "locationX", "", "locationY", "(IZFF)V", "getCircleColor", "()I", "setCircleColor", "(I)V", "getEffect", "()Z", "setEffect", "(Z)V", "getLocationX", "()F", "setLocationX", "(F)V", "getLocationY", "setLocationY", "component1", "component2", "component3", "component4", "copy", "equals", FitnessActivities.OTHER, "", "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class ChartBean extends BaseCharBean {
        private int circleColor;
        private boolean effect;
        private float locationX;
        private float locationY;

        public ChartBean() {
            this(0, false, 0.0f, 0.0f, 15, null);
        }

        public static /* synthetic */ ChartBean copy$default(ChartBean chartBean, int i, boolean z, float f2, float f3, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = chartBean.circleColor;
            }
            if ((i2 & 2) != 0) {
                z = chartBean.effect;
            }
            if ((i2 & 4) != 0) {
                f2 = chartBean.locationX;
            }
            if ((i2 & 8) != 0) {
                f3 = chartBean.locationY;
            }
            return chartBean.copy(i, z, f2, f3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCircleColor() {
            return this.circleColor;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getEffect() {
            return this.effect;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final float getLocationX() {
            return this.locationX;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final float getLocationY() {
            return this.locationY;
        }

        public final ChartBean copy(int circleColor, boolean effect, float locationX, float locationY) {
            return new ChartBean(circleColor, effect, locationX, locationY);
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
            return this.circleColor == chartBean.circleColor && this.effect == chartBean.effect && Float.compare(this.locationX, chartBean.locationX) == 0 && Float.compare(this.locationY, chartBean.locationY) == 0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1, types: [int] */
        /* JADX WARN: Type inference failed for: r1v6 */
        /* JADX WARN: Type inference failed for: r1v7 */
        @Override // android.graphics.PointF
        public int hashCode() {
            int iHashCode = Integer.valueOf(this.circleColor).hashCode() * 31;
            boolean z = this.effect;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            return ((((iHashCode + r1) * 31) + Float.valueOf(this.locationX).hashCode()) * 31) + Float.valueOf(this.locationY).hashCode();
        }

        @Override // android.graphics.PointF
        public String toString() {
            return "ChartBean(circleColor=" + this.circleColor + ", effect=" + this.effect + ", locationX=" + this.locationX + ", locationY=" + this.locationY + ")";
        }

        public final int getCircleColor() {
            return this.circleColor;
        }

        public final void setCircleColor(int i) {
            this.circleColor = i;
        }

        public final boolean getEffect() {
            return this.effect;
        }

        public final void setEffect(boolean z) {
            this.effect = z;
        }

        public final float getLocationX() {
            return this.locationX;
        }

        public final void setLocationX(float f2) {
            this.locationX = f2;
        }

        public /* synthetic */ ChartBean(int i, boolean z, float f2, float f3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? -1 : i, (i2 & 2) != 0 ? true : z, (i2 & 4) != 0 ? 0.0f : f2, (i2 & 8) != 0 ? 0.0f : f3);
        }

        public final float getLocationY() {
            return this.locationY;
        }

        public final void setLocationY(float f2) {
            this.locationY = f2;
        }

        public ChartBean(int i, boolean z, float f2, float f3) {
            super(f2, f3, z);
            this.circleColor = i;
            this.effect = z;
            this.locationX = f2;
            this.locationY = f3;
        }
    }
}