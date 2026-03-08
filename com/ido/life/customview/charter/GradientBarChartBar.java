package com.ido.life.customview.charter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.ido.life.R;
import com.ido.life.bean.GradientBarPoint;
import com.ido.life.customview.charter.CustomChatBar;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GradientBarChartBar.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0014J\u0012\u0010\u0017\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0007H\u0002J\b\u0010\u0019\u001a\u00020\nH\u0014J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\nH\u0016R&\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR&\u0010\u0010\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/ido/life/customview/charter/GradientBarChartBar;", "T", "Lcom/ido/life/bean/GradientBarPoint;", "Lcom/ido/life/customview/charter/BarChartBar;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "<set-?>", "", "mBarEndColor", "getBarEndColor", "()I", "setBarEndColor", "(I)V", "mBarStartColor", "getBarStartColor", "setBarStartColor", "drawChat", "", "canvas", "Landroid/graphics/Canvas;", "initAttrs", "attributeSet", "measureBottomLineToBottomDistance", "onChartClick", "index", "app_release"}, k = 1, mv = {1, 1, 16})
public class GradientBarChartBar<T extends GradientBarPoint> extends BarChartBar<T> {
    private HashMap _$_findViewCache;
    private int mBarEndColor;
    private int mBarStartColor;

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

    public /* synthetic */ GradientBarChartBar(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? (AttributeSet) null : attributeSet);
    }

    public GradientBarChartBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBarStartColor = -1;
        this.mBarEndColor = -1;
        initAttrs(attributeSet);
    }

    /* JADX INFO: renamed from: getBarStartColor, reason: from getter */
    public final int getMBarStartColor() {
        return this.mBarStartColor;
    }

    public final void setBarStartColor(int i) {
        this.mBarStartColor = i;
    }

    /* JADX INFO: renamed from: getBarEndColor, reason: from getter */
    public final int getMBarEndColor() {
        return this.mBarEndColor;
    }

    public final void setBarEndColor(int i) {
        this.mBarEndColor = i;
    }

    private final void initAttrs(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.GradientBarChartBar);
        Intrinsics.checkExpressionValueIsNotNull(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…able.GradientBarChartBar)");
        this.mBarStartColor = typedArrayObtainStyledAttributes.getColor(1, this.mBarStartColor);
        this.mBarEndColor = typedArrayObtainStyledAttributes.getColor(0, this.mBarEndColor);
        typedArrayObtainStyledAttributes.recycle();
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00a1  */
    @Override // com.ido.life.customview.charter.BarChartBar, com.ido.life.customview.charter.CustomChatBar
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void drawChat(android.graphics.Canvas r24) {
        /*
            Method dump skipped, instruction units count: 516
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.charter.GradientBarChartBar.drawChat(android.graphics.Canvas):void");
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected int measureBottomLineToBottomDistance() {
        int iAbs = this.mBottomLineEnabled ? 0 + this.mBottomLineHeight : 0;
        if (this.mBottomLabelEnabled) {
            int i = iAbs + this.mBottomLabelLineDistance;
            Paint mPaint = this.mPaint;
            Intrinsics.checkExpressionValueIsNotNull(mPaint, "mPaint");
            mPaint.setTextSize(this.mBottomLabelSize);
            Paint mPaint2 = this.mPaint;
            Intrinsics.checkExpressionValueIsNotNull(mPaint2, "mPaint");
            Paint.FontMetricsInt fontMetricsInt = mPaint2.getFontMetricsInt();
            iAbs = i + Math.abs(fontMetricsInt.top) + Math.abs(fontMetricsInt.bottom);
        }
        return iAbs + getPaddingBottom();
    }

    @Override // com.ido.life.customview.charter.BarChartBar, com.ido.life.customview.charter.CustomChatBar
    public void onChartClick(int index) {
        if (this.mClickListener != null && this.mCircleRegion.size() >= index + 1 && index >= 0) {
            RectF rectF = this.mCircleRegion.get(index);
            CustomChatBar.ChartClickListener chartClickListener = this.mClickListener;
            if (chartClickListener == null) {
                Intrinsics.throwNpe();
            }
            chartClickListener.onChartClick(this, rectF, index);
            this.mSelectedIndex = index;
            refreshChart(false);
        }
    }
}