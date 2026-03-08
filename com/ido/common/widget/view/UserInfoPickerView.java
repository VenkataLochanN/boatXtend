package com.ido.common.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.ido.common.R;
import com.ido.common.widget.textview.RegularTextView;
import com.watch.life.wheelview.adapter.ArrayWheelAdapter;
import com.watch.life.wheelview.view.WheelView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserInfoPickerView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0015\u0018\u0000 &2\u00020\u0001:\u0001&B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\r\u001a\u00020\tJ\u0006\u0010\u000e\u001a\u00020\fJ\u0006\u0010\u000f\u001a\u00020\tJ\u0006\u0010\u0010\u001a\u00020\fJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0014J\u000e\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\fJ\u0014\u0010\u0016\u001a\u00020\u00122\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\u000e\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\tJ\u000e\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\fJ\u000e\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\fJ\u0014\u0010\u001c\u001a\u00020\u00122\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\u000e\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\tJ\u000e\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\fJ\u000e\u0010 \u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\fJ\u000e\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\tJ\u000e\u0010#\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\fJ\u000e\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\fR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/ido/common/widget/view/UserInfoPickerView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "mLeftList", "", "", "mRightList", "mViewStyle", "", "getLeftSelectData", "getLeftSelectPosition", "getRightSelectData", "getRightSelectPosition", "initView", "", "onAttachedToWindow", "setCenterTextColor", "color", "setLeftData", "leftList", "setLeftSelect", "value", "setLeftWeelCenterTextColor", "setLeftWeelOutTextColor", "setRightData", "rightList", "setRightSelect", "setRightWheelCenterTextColor", "setRightWheelOutTextColor", "setUnitText", "unit", "setUnitTextColor", "setViewStyle", "viewStyle", "Companion", "common_release"}, k = 1, mv = {1, 1, 16})
public final class UserInfoPickerView extends LinearLayout {
    public static final int STYLE_FLOAT = 2;
    public static final int STYLE_NORMAL = 1;
    private HashMap _$_findViewCache;
    private List<String> mLeftList;
    private List<String> mRightList;
    private int mViewStyle;

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
    public UserInfoPickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mViewStyle = 1;
        this.mLeftList = new ArrayList();
        this.mRightList = new ArrayList();
        initView(context);
    }

    private final void initView(Context context) {
        setGravity(16);
        View.inflate(context, R.layout.widget_userinfo_picker, this);
    }

    public final void setViewStyle(int viewStyle) {
        this.mViewStyle = viewStyle;
        if (viewStyle == 1) {
            WheelView wheel_left = (WheelView) _$_findCachedViewById(R.id.wheel_left);
            Intrinsics.checkExpressionValueIsNotNull(wheel_left, "wheel_left");
            wheel_left.setVisibility(0);
            RegularTextView tv_dot = (RegularTextView) _$_findCachedViewById(R.id.tv_dot);
            Intrinsics.checkExpressionValueIsNotNull(tv_dot, "tv_dot");
            tv_dot.setVisibility(8);
            WheelView wheel_right = (WheelView) _$_findCachedViewById(R.id.wheel_right);
            Intrinsics.checkExpressionValueIsNotNull(wheel_right, "wheel_right");
            wheel_right.setVisibility(8);
            RegularTextView tv_unit = (RegularTextView) _$_findCachedViewById(R.id.tv_unit);
            Intrinsics.checkExpressionValueIsNotNull(tv_unit, "tv_unit");
            tv_unit.setVisibility(0);
            return;
        }
        WheelView wheel_left2 = (WheelView) _$_findCachedViewById(R.id.wheel_left);
        Intrinsics.checkExpressionValueIsNotNull(wheel_left2, "wheel_left");
        wheel_left2.setVisibility(0);
        RegularTextView tv_dot2 = (RegularTextView) _$_findCachedViewById(R.id.tv_dot);
        Intrinsics.checkExpressionValueIsNotNull(tv_dot2, "tv_dot");
        tv_dot2.setVisibility(0);
        WheelView wheel_right2 = (WheelView) _$_findCachedViewById(R.id.wheel_right);
        Intrinsics.checkExpressionValueIsNotNull(wheel_right2, "wheel_right");
        wheel_right2.setVisibility(0);
        RegularTextView tv_unit2 = (RegularTextView) _$_findCachedViewById(R.id.tv_unit);
        Intrinsics.checkExpressionValueIsNotNull(tv_unit2, "tv_unit");
        tv_unit2.setVisibility(0);
    }

    public final void setUnitTextColor(int color) {
        ((RegularTextView) _$_findCachedViewById(R.id.tv_unit)).setTextColor(color);
    }

    public final void setUnitText(String unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        RegularTextView tv_unit = (RegularTextView) _$_findCachedViewById(R.id.tv_unit);
        Intrinsics.checkExpressionValueIsNotNull(tv_unit, "tv_unit");
        tv_unit.setText(unit);
    }

    public final void setLeftSelect(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        boolean z = true;
        if (value.length() == 0) {
            return;
        }
        List<String> list = this.mLeftList;
        if (list != null && !list.isEmpty()) {
            z = false;
        }
        if (z) {
            return;
        }
        int size = this.mLeftList.size();
        for (int i = 0; i < size; i++) {
            if (value.contentEquals(this.mLeftList.get(i))) {
                WheelView wheel_left = (WheelView) _$_findCachedViewById(R.id.wheel_left);
                Intrinsics.checkExpressionValueIsNotNull(wheel_left, "wheel_left");
                wheel_left.setCurrentItem(i);
                return;
            }
        }
    }

    public final void setRightSelect(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        boolean z = true;
        if (value.length() == 0) {
            return;
        }
        List<String> list = this.mRightList;
        if (list != null && !list.isEmpty()) {
            z = false;
        }
        if (z) {
            return;
        }
        int size = this.mRightList.size();
        for (int i = 0; i < size; i++) {
            if (value.contentEquals(this.mRightList.get(i))) {
                WheelView wheel_right = (WheelView) _$_findCachedViewById(R.id.wheel_right);
                Intrinsics.checkExpressionValueIsNotNull(wheel_right, "wheel_right");
                wheel_right.setCurrentItem(i);
                return;
            }
        }
    }

    public final void setLeftData(List<String> leftList) {
        Intrinsics.checkParameterIsNotNull(leftList, "leftList");
        this.mLeftList = leftList;
        WheelView wheel_left = (WheelView) _$_findCachedViewById(R.id.wheel_left);
        Intrinsics.checkExpressionValueIsNotNull(wheel_left, "wheel_left");
        wheel_left.setAdapter(new ArrayWheelAdapter(this.mLeftList));
        WheelView wheel_left2 = (WheelView) _$_findCachedViewById(R.id.wheel_left);
        Intrinsics.checkExpressionValueIsNotNull(wheel_left2, "wheel_left");
        wheel_left2.setCurrentItem(0);
    }

    public final void setRightData(List<String> rightList) {
        Intrinsics.checkParameterIsNotNull(rightList, "rightList");
        this.mRightList = rightList;
        WheelView wheel_right = (WheelView) _$_findCachedViewById(R.id.wheel_right);
        Intrinsics.checkExpressionValueIsNotNull(wheel_right, "wheel_right");
        wheel_right.setAdapter(new ArrayWheelAdapter(this.mRightList));
        WheelView wheel_right2 = (WheelView) _$_findCachedViewById(R.id.wheel_right);
        Intrinsics.checkExpressionValueIsNotNull(wheel_right2, "wheel_right");
        wheel_right2.setCurrentItem(0);
    }

    public final void setCenterTextColor(int color) {
        ((RegularTextView) _$_findCachedViewById(R.id.tv_dot)).setTextColor(color);
    }

    public final String getLeftSelectData() {
        List<String> list = this.mLeftList;
        if (list == null || list.isEmpty()) {
            return "";
        }
        WheelView wheel_left = (WheelView) _$_findCachedViewById(R.id.wheel_left);
        Intrinsics.checkExpressionValueIsNotNull(wheel_left, "wheel_left");
        int currentItem = wheel_left.getCurrentItem();
        return this.mLeftList.size() > currentItem ? this.mLeftList.get(currentItem) : "";
    }

    public final String getRightSelectData() {
        List<String> list = this.mRightList;
        if (list == null || list.isEmpty()) {
            return "";
        }
        WheelView wheel_right = (WheelView) _$_findCachedViewById(R.id.wheel_right);
        Intrinsics.checkExpressionValueIsNotNull(wheel_right, "wheel_right");
        int currentItem = wheel_right.getCurrentItem();
        return this.mRightList.size() > currentItem ? this.mRightList.get(currentItem) : "";
    }

    public final int getLeftSelectPosition() {
        WheelView wheel_left = (WheelView) _$_findCachedViewById(R.id.wheel_left);
        Intrinsics.checkExpressionValueIsNotNull(wheel_left, "wheel_left");
        return wheel_left.getCurrentItem();
    }

    public final int getRightSelectPosition() {
        WheelView wheel_right = (WheelView) _$_findCachedViewById(R.id.wheel_right);
        Intrinsics.checkExpressionValueIsNotNull(wheel_right, "wheel_right");
        return wheel_right.getCurrentItem();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((WheelView) _$_findCachedViewById(R.id.wheel_left)).setCyclic(false);
        ((WheelView) _$_findCachedViewById(R.id.wheel_left)).setItemsVisibleCount(5);
        ((WheelView) _$_findCachedViewById(R.id.wheel_left)).setPadding(0, 8, 0, 8);
        ((WheelView) _$_findCachedViewById(R.id.wheel_right)).setCyclic(false);
        ((WheelView) _$_findCachedViewById(R.id.wheel_right)).setItemsVisibleCount(5);
        ((WheelView) _$_findCachedViewById(R.id.wheel_right)).setPadding(0, 8, 0, 8);
    }

    public final void setLeftWeelCenterTextColor(int color) {
        ((WheelView) _$_findCachedViewById(R.id.wheel_left)).setTextColorCenter(color);
    }

    public final void setRightWheelCenterTextColor(int color) {
        ((WheelView) _$_findCachedViewById(R.id.wheel_right)).setTextColorCenter(color);
    }

    public final void setLeftWeelOutTextColor(int color) {
        ((WheelView) _$_findCachedViewById(R.id.wheel_left)).setTextColorOut(color);
    }

    public final void setRightWheelOutTextColor(int color) {
        ((WheelView) _$_findCachedViewById(R.id.wheel_right)).setTextColorOut(color);
    }
}