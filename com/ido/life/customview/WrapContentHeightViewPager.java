package com.ido.life.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.viewpager.widget.ViewPager;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WrapContentHeightViewPager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0014¨\u0006\r"}, d2 = {"Lcom/ido/life/customview/WrapContentHeightViewPager;", "Landroidx/viewpager/widget/ViewPager;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "onMeasure", "", "widthMeasureSpec", "", "heightMeasureSpec", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WrapContentHeightViewPager extends ViewPager {
    private HashMap _$_findViewCache;

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
    public WrapContentHeightViewPager(Context context) {
        super(context);
        if (context == null) {
            Intrinsics.throwNpe();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WrapContentHeightViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (context == null) {
            Intrinsics.throwNpe();
        }
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View child = getChildAt(i2);
            child.measure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, 0));
            Intrinsics.checkExpressionValueIsNotNull(child, "child");
            int measuredHeight = child.getMeasuredHeight();
            if (measuredHeight > i) {
                i = measuredHeight;
            }
        }
        if (i > 0) {
            Log.e("WrapContentHeightViewPager", "height = " + i);
            super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(i, BasicMeasure.EXACTLY));
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}