package com.ido.life.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.view.ViewCompat;
import com.ido.life.R;
import com.watch.life.wheelview.adapter.ArrayWheelAdapter;
import com.watch.life.wheelview.listener.OnItemSelectedListener;
import com.watch.life.wheelview.view.WheelView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DateSelectWheelView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010D\u001a\u00020\u000bJ\u0006\u0010E\u001a\u00020\u000bJ\u0006\u0010F\u001a\u00020\u000bJT\u0010G\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020I0H2\u0006\u0010J\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020\u00122\u0006\u0010L\u001a\u00020\u00122\u0006\u0010M\u001a\u00020\u000b2\u0006\u0010N\u001a\u00020\u000b2\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020\u000b2\u0006\u0010R\u001a\u00020\u000bH\u0002J\u0010\u0010S\u001a\u00020T2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\b\u0010U\u001a\u00020TH\u0002J\b\u0010V\u001a\u00020TH\u0002J\b\u0010W\u001a\u00020TH\u0002J\b\u0010X\u001a\u00020TH\u0002J\b\u0010Y\u001a\u00020TH\u0002R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00100\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010-\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R&\u00103\u001a\u00020\u00122\u0006\u00102\u001a\u00020\u00128G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001a\u00108\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u00105\"\u0004\b:\u00107R\u0014\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00100\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Z"}, d2 = {"Lcom/ido/life/customview/DateSelectWheelView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "mCalendar", "Ljava/util/Calendar;", "kotlin.jvm.PlatformType", "mCurrentDay", "", "mCurrentMonth", "mCurrentYear", "mDayAdapter", "Lcom/watch/life/wheelview/adapter/ArrayWheelAdapter;", "", "mDayCenterTextBold", "", "mDayCenterTextColor", "mDayDividerColor", "mDayIndex", "mDayList", "", "mDayOutTextColor", "mDaySelector", "Lcom/watch/life/wheelview/view/WheelView;", "getMDaySelector", "()Lcom/watch/life/wheelview/view/WheelView;", "setMDaySelector", "(Lcom/watch/life/wheelview/view/WheelView;)V", "mDayTextSize", "mDividerSize", "mMonthAdapter", "mMonthCenterTextBold", "mMonthCenterTextColor", "mMonthDividerColor", "mMonthIndex", "mMonthList", "mMonthOutTextColor", "mMonthSelector", "mMonthTextSize", "mSelectedDay", "mSelectedMonth", "mSelectedYear", "mSelectorVisibleCount", "getMSelectorVisibleCount", "()I", "setMSelectorVisibleCount", "(I)V", "<set-?>", "mShowDaySelector", "getShowDaySelector", "()Z", "setShowDaySelector", "(Z)V", "mSupportCyclic", "getMSupportCyclic", "setMSupportCyclic", "mYearAdapter", "mYearCenterTextBold", "mYearCenterTextColor", "mYearDividerColor", "mYearIndex", "mYearList", "mYearOutTextColor", "mYearSelector", "mYearTextSize", "getSelectedDay", "getSelectedMonth", "getSelectedYear", "getSelector", "Lkotlin/Pair;", "Landroid/widget/LinearLayout$LayoutParams;", "visibleCount", "cyclic", "centerBold", "centerTextColor", "outTextColor", "textSize", "", "dividerColor", "dividerSize", "initConfig", "", "initDayList", "initMonthList", "initSelectedDate", "initView", "initYearList", "app_release"}, k = 1, mv = {1, 1, 16})
public final class DateSelectWheelView extends LinearLayout {
    private HashMap _$_findViewCache;
    private final Calendar mCalendar;
    private final int mCurrentDay;
    private final int mCurrentMonth;
    private final int mCurrentYear;
    private ArrayWheelAdapter<String> mDayAdapter;
    private boolean mDayCenterTextBold;
    private int mDayCenterTextColor;
    private int mDayDividerColor;
    private int mDayIndex;
    private List<String> mDayList;
    private int mDayOutTextColor;
    private WheelView mDaySelector;
    private int mDayTextSize;
    private int mDividerSize;
    private final ArrayWheelAdapter<String> mMonthAdapter;
    private boolean mMonthCenterTextBold;
    private int mMonthCenterTextColor;
    private int mMonthDividerColor;
    private int mMonthIndex;
    private final List<String> mMonthList;
    private int mMonthOutTextColor;
    private WheelView mMonthSelector;
    private int mMonthTextSize;
    private int mSelectedDay;
    private int mSelectedMonth;
    private int mSelectedYear;
    private int mSelectorVisibleCount;
    private boolean mShowDaySelector;
    private boolean mSupportCyclic;
    private final ArrayWheelAdapter<String> mYearAdapter;
    private boolean mYearCenterTextBold;
    private int mYearCenterTextColor;
    private int mYearDividerColor;
    private int mYearIndex;
    private final List<String> mYearList;
    private int mYearOutTextColor;
    private WheelView mYearSelector;
    private int mYearTextSize;

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
    public DateSelectWheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attributeSet");
        this.mShowDaySelector = true;
        this.mSelectorVisibleCount = 5;
        this.mYearCenterTextColor = ViewCompat.MEASURED_STATE_MASK;
        this.mYearOutTextColor = -3355444;
        this.mYearTextSize = 12;
        this.mYearCenterTextBold = true;
        this.mYearDividerColor = -3355444;
        this.mMonthCenterTextColor = ViewCompat.MEASURED_STATE_MASK;
        this.mMonthOutTextColor = -3355444;
        this.mMonthTextSize = 12;
        this.mMonthCenterTextBold = true;
        this.mMonthDividerColor = -3355444;
        this.mDayCenterTextColor = ViewCompat.MEASURED_STATE_MASK;
        this.mDayOutTextColor = -3355444;
        this.mDayTextSize = 12;
        this.mDayCenterTextBold = true;
        this.mDayDividerColor = -3355444;
        this.mDividerSize = 1;
        this.mYearList = new ArrayList();
        this.mYearAdapter = new ArrayWheelAdapter<>(this.mYearList);
        this.mMonthList = new ArrayList();
        this.mMonthAdapter = new ArrayWheelAdapter<>(this.mMonthList);
        this.mSelectedYear = -1;
        this.mSelectedMonth = -1;
        this.mSelectedDay = -1;
        this.mYearIndex = -1;
        this.mMonthIndex = -1;
        this.mDayIndex = -1;
        this.mCalendar = Calendar.getInstance();
        setOrientation(0);
        initConfig(attributeSet);
        if (this.mSelectedYear == -1 || this.mSelectedMonth == -1 || this.mSelectedDay == -1) {
            initSelectedDate();
        }
        this.mCurrentYear = this.mCalendar.get(1);
        this.mCurrentDay = this.mCalendar.get(5);
        this.mCurrentMonth = this.mCalendar.get(2) + 1;
        initYearList();
        initMonthList();
        initDayList();
        initView();
    }

    /* JADX INFO: renamed from: getShowDaySelector, reason: from getter */
    public final boolean getMShowDaySelector() {
        return this.mShowDaySelector;
    }

    public final void setShowDaySelector(boolean z) {
        this.mShowDaySelector = z;
    }

    public final int getMSelectorVisibleCount() {
        return this.mSelectorVisibleCount;
    }

    public final void setMSelectorVisibleCount(int i) {
        this.mSelectorVisibleCount = i;
    }

    public final boolean getMSupportCyclic() {
        return this.mSupportCyclic;
    }

    public final void setMSupportCyclic(boolean z) {
        this.mSupportCyclic = z;
    }

    public final WheelView getMDaySelector() {
        return this.mDaySelector;
    }

    public final void setMDaySelector(WheelView wheelView) {
        this.mDaySelector = wheelView;
    }

    private final void initConfig(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.DateSelectWheelView);
        this.mShowDaySelector = typedArrayObtainStyledAttributes.getBoolean(17, this.mShowDaySelector);
        int color = typedArrayObtainStyledAttributes.getColor(9, -1);
        if (color != -1) {
            this.mYearDividerColor = color;
            this.mMonthDividerColor = color;
            this.mDayDividerColor = color;
        }
        this.mDividerSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(10, this.mDividerSize);
        this.mYearDividerColor = typedArrayObtainStyledAttributes.getColor(22, this.mYearDividerColor);
        this.mMonthDividerColor = typedArrayObtainStyledAttributes.getColor(13, this.mMonthDividerColor);
        this.mDayDividerColor = typedArrayObtainStyledAttributes.getColor(6, this.mDayDividerColor);
        this.mSupportCyclic = typedArrayObtainStyledAttributes.getBoolean(2, this.mSupportCyclic);
        this.mSelectorVisibleCount = typedArrayObtainStyledAttributes.getInt(19, this.mSelectorVisibleCount);
        int color2 = typedArrayObtainStyledAttributes.getColor(1, -1);
        if (color2 != -1) {
            this.mYearCenterTextColor = color2;
            this.mMonthCenterTextColor = color2;
            this.mDayCenterTextColor = color2;
        }
        this.mYearCenterTextColor = typedArrayObtainStyledAttributes.getColor(21, this.mYearCenterTextColor);
        this.mMonthCenterTextColor = typedArrayObtainStyledAttributes.getColor(12, this.mMonthCenterTextColor);
        this.mDayCenterTextColor = typedArrayObtainStyledAttributes.getColor(5, this.mDayCenterTextColor);
        int color3 = typedArrayObtainStyledAttributes.getColor(16, -1);
        if (color3 != -1) {
            this.mYearOutTextColor = color3;
            this.mMonthOutTextColor = color3;
            this.mDayOutTextColor = color3;
        }
        this.mYearOutTextColor = typedArrayObtainStyledAttributes.getColor(23, this.mYearOutTextColor);
        this.mMonthOutTextColor = typedArrayObtainStyledAttributes.getColor(14, this.mMonthOutTextColor);
        this.mDayOutTextColor = typedArrayObtainStyledAttributes.getColor(7, this.mDayOutTextColor);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(18, -1);
        if (dimensionPixelSize != -1) {
            this.mYearTextSize = dimensionPixelSize;
            this.mMonthTextSize = dimensionPixelSize;
            this.mDayTextSize = dimensionPixelSize;
        }
        this.mYearTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(24, this.mYearTextSize);
        this.mMonthTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(15, this.mMonthTextSize);
        this.mDayTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, this.mDayTextSize);
        if (typedArrayObtainStyledAttributes.hasValue(0)) {
            boolean z = typedArrayObtainStyledAttributes.getBoolean(0, this.mYearCenterTextBold);
            this.mYearCenterTextBold = z;
            this.mMonthCenterTextBold = z;
            this.mDayCenterTextBold = z;
        }
        this.mYearCenterTextBold = typedArrayObtainStyledAttributes.getBoolean(20, this.mYearCenterTextBold);
        this.mMonthCenterTextBold = typedArrayObtainStyledAttributes.getBoolean(11, this.mMonthCenterTextBold);
        this.mDayCenterTextBold = typedArrayObtainStyledAttributes.getBoolean(4, this.mDayCenterTextBold);
        typedArrayObtainStyledAttributes.recycle();
    }

    private final void initView() {
        Pair<WheelView, LinearLayout.LayoutParams> selector = getSelector(this.mSelectorVisibleCount, this.mSupportCyclic, this.mYearCenterTextBold, this.mYearCenterTextColor, this.mYearOutTextColor, this.mYearTextSize, this.mYearDividerColor, this.mDividerSize);
        Pair<WheelView, LinearLayout.LayoutParams> selector2 = getSelector(this.mSelectorVisibleCount, this.mSupportCyclic, this.mMonthCenterTextBold, this.mMonthCenterTextColor, this.mMonthOutTextColor, this.mMonthTextSize, this.mMonthDividerColor, this.mDividerSize);
        Pair<WheelView, LinearLayout.LayoutParams> selector3 = this.mShowDaySelector ? getSelector(this.mSelectorVisibleCount, this.mSupportCyclic, this.mDayCenterTextBold, this.mDayCenterTextColor, this.mDayOutTextColor, this.mDayTextSize, this.mDayDividerColor, this.mDividerSize) : null;
        addView(selector.getFirst(), selector.getSecond());
        addView(selector2.getFirst(), selector2.getSecond());
        if (selector3 != null) {
            addView(selector3.getFirst(), selector3.getSecond());
        }
        this.mYearSelector = selector.getFirst();
        this.mMonthSelector = selector2.getFirst();
        if (selector3 != null) {
            this.mDaySelector = selector3.getFirst();
        }
        WheelView wheelView = this.mYearSelector;
        if (wheelView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mYearSelector");
        }
        wheelView.setAdapter(this.mYearAdapter);
        WheelView wheelView2 = this.mMonthSelector;
        if (wheelView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mMonthSelector");
        }
        wheelView2.setAdapter(this.mMonthAdapter);
        WheelView wheelView3 = this.mYearSelector;
        if (wheelView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mYearSelector");
        }
        wheelView3.setCurrentItem(this.mYearIndex);
        WheelView wheelView4 = this.mMonthSelector;
        if (wheelView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mMonthSelector");
        }
        wheelView4.setCurrentItem(this.mMonthIndex);
        WheelView wheelView5 = this.mYearSelector;
        if (wheelView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mYearSelector");
        }
        wheelView5.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.ido.life.customview.DateSelectWheelView.initView.1
            @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
            public final void onItemSelected(int i) {
                DateSelectWheelView.this.mYearIndex = i;
                DateSelectWheelView dateSelectWheelView = DateSelectWheelView.this;
                dateSelectWheelView.mSelectedYear = Integer.parseInt((String) dateSelectWheelView.mYearList.get(i));
                if (DateSelectWheelView.this.getMDaySelector() != null) {
                    DateSelectWheelView.this.initDayList();
                    WheelView mDaySelector = DateSelectWheelView.this.getMDaySelector();
                    if (mDaySelector == null) {
                        Intrinsics.throwNpe();
                    }
                    mDaySelector.setAdapter(DateSelectWheelView.this.mDayAdapter);
                    List list = DateSelectWheelView.this.mDayList;
                    if (list == null) {
                        Intrinsics.throwNpe();
                    }
                    int size = list.size();
                    int i2 = DateSelectWheelView.this.mDayIndex;
                    if (i2 < 0 || size <= i2) {
                        DateSelectWheelView dateSelectWheelView2 = DateSelectWheelView.this;
                        if (dateSelectWheelView2.mDayList == null) {
                            Intrinsics.throwNpe();
                        }
                        dateSelectWheelView2.mDayIndex = r0.size() - 1;
                    }
                    WheelView mDaySelector2 = DateSelectWheelView.this.getMDaySelector();
                    if (mDaySelector2 == null) {
                        Intrinsics.throwNpe();
                    }
                    mDaySelector2.setCurrentItem(DateSelectWheelView.this.mDayIndex);
                }
            }
        });
        WheelView wheelView6 = this.mMonthSelector;
        if (wheelView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mMonthSelector");
        }
        wheelView6.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.ido.life.customview.DateSelectWheelView.initView.2
            @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
            public final void onItemSelected(int i) {
                DateSelectWheelView.this.mMonthIndex = i;
                DateSelectWheelView dateSelectWheelView = DateSelectWheelView.this;
                dateSelectWheelView.mSelectedMonth = Integer.parseInt((String) dateSelectWheelView.mMonthList.get(i));
                if (DateSelectWheelView.this.getMDaySelector() != null) {
                    DateSelectWheelView.this.initDayList();
                    WheelView mDaySelector = DateSelectWheelView.this.getMDaySelector();
                    if (mDaySelector == null) {
                        Intrinsics.throwNpe();
                    }
                    mDaySelector.setAdapter(DateSelectWheelView.this.mDayAdapter);
                    List list = DateSelectWheelView.this.mDayList;
                    if (list == null) {
                        Intrinsics.throwNpe();
                    }
                    int size = list.size();
                    int i2 = DateSelectWheelView.this.mDayIndex;
                    if (i2 < 0 || size <= i2) {
                        DateSelectWheelView dateSelectWheelView2 = DateSelectWheelView.this;
                        if (dateSelectWheelView2.mDayList == null) {
                            Intrinsics.throwNpe();
                        }
                        dateSelectWheelView2.mDayIndex = r0.size() - 1;
                    }
                    WheelView mDaySelector2 = DateSelectWheelView.this.getMDaySelector();
                    if (mDaySelector2 == null) {
                        Intrinsics.throwNpe();
                    }
                    mDaySelector2.setCurrentItem(DateSelectWheelView.this.mDayIndex);
                }
            }
        });
        if (this.mDaySelector != null) {
            if (this.mDayAdapter == null) {
                this.mDayAdapter = new ArrayWheelAdapter<>(this.mDayList);
            }
            WheelView wheelView7 = this.mDaySelector;
            if (wheelView7 == null) {
                Intrinsics.throwNpe();
            }
            wheelView7.setAdapter(this.mDayAdapter);
            WheelView wheelView8 = this.mDaySelector;
            if (wheelView8 == null) {
                Intrinsics.throwNpe();
            }
            wheelView8.setCurrentItem(this.mDayIndex);
            WheelView wheelView9 = this.mDaySelector;
            if (wheelView9 == null) {
                Intrinsics.throwNpe();
            }
            wheelView9.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.ido.life.customview.DateSelectWheelView.initView.3
                @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
                public final void onItemSelected(int i) {
                    DateSelectWheelView.this.mDayIndex = i;
                    DateSelectWheelView dateSelectWheelView = DateSelectWheelView.this;
                    List list = dateSelectWheelView.mDayList;
                    if (list == null) {
                        Intrinsics.throwNpe();
                    }
                    dateSelectWheelView.mSelectedDay = Integer.parseInt((String) list.get(i));
                }
            });
        }
    }

    private final void initSelectedDate() {
        Calendar calendar = Calendar.getInstance();
        this.mSelectedYear = calendar.get(1);
        this.mSelectedDay = calendar.get(5);
        this.mSelectedMonth = calendar.get(2) + 1;
    }

    private final void initYearList() {
        this.mYearList.clear();
        int i = Calendar.getInstance().get(1);
        int i2 = i - 100;
        if (i2 > i) {
            return;
        }
        int i3 = i2;
        while (true) {
            this.mYearList.add(String.valueOf(i3));
            if (i3 == this.mSelectedYear) {
                this.mYearIndex = i3 - i2;
            }
            if (i3 == i) {
                return;
            } else {
                i3++;
            }
        }
    }

    private final void initMonthList() {
        this.mMonthList.clear();
        for (int i = 1; i <= 12; i++) {
            this.mMonthList.add(String.valueOf(i));
            if (i == this.mSelectedMonth) {
                this.mMonthIndex = i - 1;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initDayList() {
        List<String> list = this.mDayList;
        if (list == null) {
            this.mDayList = new ArrayList();
        } else {
            if (list == null) {
                Intrinsics.throwNpe();
            }
            list.clear();
        }
        int i = 1;
        this.mCalendar.set(1, this.mSelectedYear);
        this.mCalendar.set(2, this.mSelectedMonth);
        this.mCalendar.add(2, -1);
        int actualMaximum = (this.mCurrentYear == this.mSelectedYear && this.mCurrentMonth == this.mSelectedMonth) ? this.mCurrentDay : this.mCalendar.getActualMaximum(5);
        if (1 > actualMaximum) {
            return;
        }
        while (true) {
            if (i == this.mSelectedDay) {
                this.mDayIndex = i - 1;
            }
            List<String> list2 = this.mDayList;
            if (list2 == null) {
                Intrinsics.throwNpe();
            }
            list2.add(String.valueOf(i));
            if (i == actualMaximum) {
                return;
            } else {
                i++;
            }
        }
    }

    /* JADX INFO: renamed from: getSelectedYear, reason: from getter */
    public final int getMSelectedYear() {
        return this.mSelectedYear;
    }

    /* JADX INFO: renamed from: getSelectedMonth, reason: from getter */
    public final int getMSelectedMonth() {
        return this.mSelectedMonth;
    }

    /* JADX INFO: renamed from: getSelectedDay, reason: from getter */
    public final int getMSelectedDay() {
        return this.mSelectedDay;
    }

    private final Pair<WheelView, LinearLayout.LayoutParams> getSelector(int visibleCount, boolean cyclic, boolean centerBold, int centerTextColor, int outTextColor, float textSize, int dividerColor, int dividerSize) {
        WheelView wheelView = new WheelView(getContext());
        wheelView.setItemsVisibleCount(visibleCount);
        wheelView.setCyclic(cyclic);
        wheelView.setCenterBold(centerBold);
        wheelView.setTextColorCenter(centerTextColor);
        wheelView.setTextColorOut(outTextColor);
        wheelView.setTextSize(textSize);
        wheelView.setDividerWidth(dividerSize);
        wheelView.setDividerColor(dividerColor);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        return new Pair<>(wheelView, layoutParams);
    }
}