package com.ido.life.module.home.chartdetail.vertical;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.RectF;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.viewpager.widget.ViewPager;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.common.adapter.FragmentPagerStateAdapter;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.StatusBarUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.CustomChartDetailViewPager;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.RegularRadioButton;
import com.ido.life.customview.charter.CustomChatBar;
import com.ido.life.customview.charter.GradientBarChartBar;
import com.ido.life.module.device.activity.HeartRateMonitoringActivity;
import com.ido.life.module.device.activity.NoiseMonitoringActivity;
import com.ido.life.module.home.HomeHelperKt;
import com.ido.life.module.home.activity.ActivityDetailFragment;
import com.ido.life.module.home.ambientvolume.AmbientVolumeDetailFragment;
import com.ido.life.module.home.calorie.CalorieDetailFragment;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback;
import com.ido.life.module.home.distance.DistanceDetailFragment;
import com.ido.life.module.home.fitness.FitnessDetailFragment;
import com.ido.life.module.home.oxygen.OxygenDetailFragment;
import com.ido.life.module.home.pressure.detail.vertical.PresureDetailFragment;
import com.ido.life.module.home.pressure.prestart.PressureAjustPreStartActivity;
import com.ido.life.module.home.rate.vertical.RateDetailFragment;
import com.ido.life.module.home.step.StepDetailFragment;
import com.ido.life.module.home.weight.WeightDetailFragment;
import com.ido.life.module.user.set.target.SettingTargetActivity;
import com.ido.life.module.user.userdata.weight.WeightSettingActivity;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: ChartDetailActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 s2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b:\u0001sB\u0005¢\u0006\u0002\u0010\tJ\b\u00101\u001a\u00020\u000fH\u0016J\u0012\u00102\u001a\u0004\u0018\u00010-2\u0006\u00103\u001a\u00020\u001fH\u0016J\u0010\u00104\u001a\u00020\u000f2\u0006\u00103\u001a\u00020\u001fH\u0016J\b\u00105\u001a\u00020\u000fH\u0016J\u0010\u00106\u001a\u00020\u000f2\u0006\u00103\u001a\u00020\u001fH\u0016J\b\u00107\u001a\u00020\u000fH\u0014J\u0010\u00108\u001a\u00020\"2\u0006\u00109\u001a\u00020\u000fH\u0016J\u0010\u0010:\u001a\u00020\u000f2\u0006\u00103\u001a\u00020\u001fH\u0016J\b\u0010;\u001a\u00020\u000fH\u0016J\u0010\u0010<\u001a\u00020\u000f2\u0006\u00103\u001a\u00020\u001fH\u0016J\u0012\u0010=\u001a\u0004\u0018\u00010-2\u0006\u00103\u001a\u00020\u001fH\u0016J\b\u0010>\u001a\u00020\u000fH\u0016J\u0012\u0010?\u001a\u0004\u0018\u00010-2\u0006\u00103\u001a\u00020\u001fH\u0016J\u0010\u0010@\u001a\u00020A2\u0006\u00103\u001a\u00020\u001fH\u0016J\b\u0010B\u001a\u00020CH\u0016J\b\u0010D\u001a\u00020CH\u0014J\b\u0010E\u001a\u00020CH\u0014J\b\u0010F\u001a\u00020CH\u0016J\u0010\u0010G\u001a\u00020\u00172\u0006\u00103\u001a\u00020\u001fH\u0016J\u0010\u0010H\u001a\u00020C2\u0006\u0010I\u001a\u00020\u000fH\u0002J\"\u0010J\u001a\u00020C2\u0006\u0010K\u001a\u00020\u000f2\u0006\u0010L\u001a\u00020\u000f2\b\u0010M\u001a\u0004\u0018\u00010NH\u0014J \u0010O\u001a\u00020C2\u0006\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020\u000fH\u0016J\u0018\u0010U\u001a\u00020C2\u0006\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020\u000fH\u0016J\u0010\u0010Y\u001a\u00020C2\u0006\u0010Z\u001a\u00020QH\u0016J\b\u0010[\u001a\u00020CH\u0014J\u0010\u0010\\\u001a\u00020C2\u0006\u0010]\u001a\u00020\u000fH\u0016J \u0010^\u001a\u00020C2\u0006\u0010_\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020\u000fH\u0016J\u0010\u0010c\u001a\u00020C2\u0006\u0010_\u001a\u00020\u000fH\u0016J\u0018\u0010d\u001a\u00020\u00172\u0006\u0010Z\u001a\u00020Q2\u0006\u0010e\u001a\u00020fH\u0016J\b\u0010g\u001a\u00020CH\u0002J\b\u0010h\u001a\u00020CH\u0002J\b\u0010i\u001a\u00020CH\u0002J\b\u0010j\u001a\u00020CH\u0002J\u0018\u0010k\u001a\u00020C2\u0006\u00103\u001a\u00020\u001f2\u0006\u0010l\u001a\u00020\u000fH\u0016J\u0018\u0010m\u001a\u00020C2\u0006\u00103\u001a\u00020\u001f2\u0006\u0010n\u001a\u00020\u000fH\u0016J\u0018\u0010o\u001a\u00020C2\u0006\u00103\u001a\u00020\u001f2\u0006\u0010p\u001a\u00020\u000fH\u0016J\u001a\u0010q\u001a\u00020C2\u0006\u00103\u001a\u00020\u001f2\b\u0010r\u001a\u0004\u0018\u00010\u000bH\u0016R\u0014\u0010\n\u001a\u00020\u000bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n \u0011*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u000bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0014\u0010\u0014\u001a\u00020\u000bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\rR\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\"0!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010+\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010-0,0!X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010.\u001a\u00020%8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b/\u00100¨\u0006t"}, d2 = {"Lcom/ido/life/module/home/chartdetail/vertical/ChartDetailActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/home/chartdetail/vertical/ChartDetailPresenter;", "Landroid/widget/RadioGroup$OnCheckedChangeListener;", "Lcom/ido/life/customview/charter/CustomChatBar$ChartClickListener;", "Landroid/view/View$OnClickListener;", "Lcom/ido/life/module/home/chartdetail/vertical/IChartDetailCallback;", "Landroid/view/View$OnTouchListener;", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "()V", "BOTTOM_VIEW_HOLDER", "", "getBOTTOM_VIEW_HOLDER", "()Ljava/lang/String;", "DEFAULT_LOAD_COUNT", "", "TAG", "kotlin.jvm.PlatformType", "TIP_VIEW_HOLDER", "getTIP_VIEW_HOLDER", "TOP_VIEW_HOLDER", "getTOP_VIEW_HOLDER", "isOpenOxy", "", "mAdapter", "Lcom/ido/common/adapter/FragmentPagerStateAdapter;", "mCalorieType", "mDateOffset", "mDateType", "mFragmentList", "Ljava/util/LinkedList;", "Landroidx/fragment/app/Fragment;", "mPageConfigData", "", "Landroid/os/Bundle;", "mPageType", "mPressureTipDialog", "Lcom/ido/common/dialog/CommBottomConfirmDialog;", "mRateType", "mScreenWidth", "mSelectDate", "mSettingCallback", "Lcom/ido/ble/callback/SettingCallBack$ICallBack;", "mViewHolderMap", "", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailViewHolder;", "pressureTipDialog", "getPressureTipDialog", "()Lcom/ido/common/dialog/CommBottomConfirmDialog;", "getBottomViewHeight", "getBottomViewHolder", "fragment", "getCalorieType", "getContentHeight", "getDateType", "getLayoutResId", "getPageData", "offset", "getPageOffset", "getPagerTop", "getRateType", "getTipViewHolder", "getTopViewHeight", "getTopViewHolder", "getUserId", "", "hideTipUI", "", "initData", "initLabelLanguage", "initViews", "isShow", "loadFragmentList", "pPosition", "onActivityResult", "requestCode", "resultCode", AeUtil.ROOT_DATA_PATH_OLD_NAME, "Landroid/content/Intent;", "onChartClick", "target", "Landroid/view/View;", "rectF", "Landroid/graphics/RectF;", "index", "onCheckedChanged", "group", "Landroid/widget/RadioGroup;", "checkedId", "onClick", "v", "onDestroy", "onPageScrollStateChanged", "state", "onPageScrolled", "position", "positionOffset", "", "positionOffsetPixels", "onPageSelected", "onTouch", "event", "Landroid/view/MotionEvent;", "switchToDay", "switchToMonth", "switchToWeek", "switchToYear", "updateDateSelect", "dateType", "updatePagerHeight", "chartHeight", "updateRateType", "rateType", "updateSelectDate", "selectDate", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ChartDetailActivity extends BaseActivity<ChartDetailPresenter> implements RadioGroup.OnCheckedChangeListener, CustomChatBar.ChartClickListener, View.OnClickListener, IChartDetailCallback, View.OnTouchListener, ViewPager.OnPageChangeListener {
    public static final String PAGE_TYPE = "page_type";
    private HashMap _$_findViewCache;
    private boolean isOpenOxy;
    private FragmentPagerStateAdapter mAdapter;
    private int mPageType;
    private CommBottomConfirmDialog mPressureTipDialog;
    private int mScreenWidth;
    private String mSelectDate;
    private SettingCallBack.ICallBack mSettingCallback;
    private final String TAG = ChartDetailActivity.class.getSimpleName();
    private final String TOP_VIEW_HOLDER = "top_view_holder";
    private final String BOTTOM_VIEW_HOLDER = "bottom_view_holder";
    private final String TIP_VIEW_HOLDER = "tip_view_holder";
    private LinkedList<Fragment> mFragmentList = new LinkedList<>();
    private int mDateType = 1;
    private final int DEFAULT_LOAD_COUNT = 3;
    private int mRateType = 1;
    private int mCalorieType = 2;
    private int mDateOffset = (-this.DEFAULT_LOAD_COUNT) + 1;
    private Map<Integer, Bundle> mPageConfigData = new LinkedHashMap();
    private final Map<String, List<BaseDetailViewHolder>> mViewHolderMap = MapsKt.mutableMapOf(new Pair(this.TOP_VIEW_HOLDER, new ArrayList()), new Pair(this.BOTTOM_VIEW_HOLDER, new ArrayList()), new Pair(this.TIP_VIEW_HOLDER, new ArrayList()));

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

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_chart_detail_layout;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int state) {
    }

    public final String getTOP_VIEW_HOLDER() {
        return this.TOP_VIEW_HOLDER;
    }

    public final String getBOTTOM_VIEW_HOLDER() {
        return this.BOTTOM_VIEW_HOLDER;
    }

    public final String getTIP_VIEW_HOLDER() {
        return this.TIP_VIEW_HOLDER;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        this.mScreenWidth = resources.getDisplayMetrics().widthPixels;
        ChartDetailActivity chartDetailActivity = this;
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top)).setOnTouchListener(chartDetailActivity);
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_bottom)).setOnTouchListener(chartDetailActivity);
        ((RadioGroup) _$_findCachedViewById(com.ido.life.R.id.radio_group)).setOnCheckedChangeListener(this);
        CustomChartDetailViewPager view_pager = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
        Intrinsics.checkExpressionValueIsNotNull(view_pager, "view_pager");
        view_pager.setOffscreenPageLimit(1);
        View divider_1 = _$_findCachedViewById(com.ido.life.R.id.divider_1);
        Intrinsics.checkExpressionValueIsNotNull(divider_1, "divider_1");
        divider_1.setVisibility(8);
        View divider_2 = _$_findCachedViewById(com.ido.life.R.id.divider_2);
        Intrinsics.checkExpressionValueIsNotNull(divider_2, "divider_2");
        divider_2.setVisibility(0);
        View divider_3 = _$_findCachedViewById(com.ido.life.R.id.divider_3);
        Intrinsics.checkExpressionValueIsNotNull(divider_3, "divider_3");
        divider_3.setVisibility(0);
        ((CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager)).addOnPageChangeListener(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0086  */
    @Override // com.ido.common.base.BaseCoreActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void initData() {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.chartdetail.vertical.ChartDetailActivity.initData():void");
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        int i = this.mPageType;
        if (i == 0) {
            setTitle(getLanguageText(R.string.home_steps_tittle));
        } else if (i == 1) {
            setTitle(getLanguageText(R.string.sport_distance));
        } else if (i == 2) {
            setTitle(getLanguageText(R.string.home_calorie_activity));
        } else if (i == 8) {
            setTitle(getLanguageText(R.string.sport_record_heart_rate));
        } else if (i == 9) {
            setTitle(getLanguageText(R.string.home_pressure_title));
        } else if (i == 11) {
            setTitle(getLanguageText(R.string.home_card_weight));
        } else if (i != 19) {
            switch (i) {
                case 15:
                    setTitle(getLanguageText(R.string.home_card_activity_stronger_walk));
                    break;
                case 16:
                    setTitle(getLanguageText(R.string.sport_fitness_android));
                    break;
                case 17:
                    setTitle(getLanguageText(R.string.ambient_volume));
                    break;
            }
        } else {
            setTitle(getLanguageText(R.string.help_max_spo2_title));
        }
        RegularRadioButton rrb_day = (RegularRadioButton) _$_findCachedViewById(com.ido.life.R.id.rrb_day);
        Intrinsics.checkExpressionValueIsNotNull(rrb_day, "rrb_day");
        rrb_day.setText(getLanguageText(R.string.public_time_day));
        RegularRadioButton rrb_week = (RegularRadioButton) _$_findCachedViewById(com.ido.life.R.id.rrb_week);
        Intrinsics.checkExpressionValueIsNotNull(rrb_week, "rrb_week");
        rrb_week.setText(getLanguageText(R.string.public_time_week));
        RegularRadioButton rrb_month = (RegularRadioButton) _$_findCachedViewById(com.ido.life.R.id.rrb_month);
        Intrinsics.checkExpressionValueIsNotNull(rrb_month, "rrb_month");
        rrb_month.setText(getLanguageText(R.string.public_time_month));
        RegularRadioButton rrb_year = (RegularRadioButton) _$_findCachedViewById(com.ido.life.R.id.rrb_year);
        Intrinsics.checkExpressionValueIsNotNull(rrb_year, "rrb_year");
        rrb_year.setText(getLanguageText(R.string.public_time_year));
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Intrinsics.checkParameterIsNotNull(group, "group");
        switch (checkedId) {
            case R.id.rrb_day /* 2131363367 */:
                if (this.mDateType != 1) {
                    switchToDay();
                    break;
                }
                break;
            case R.id.rrb_month /* 2131363368 */:
                if (this.mDateType != 3) {
                    switchToMonth();
                    break;
                }
                break;
            case R.id.rrb_week /* 2131363369 */:
                if (this.mDateType != 2) {
                    switchToWeek();
                    break;
                }
                break;
            case R.id.rrb_year /* 2131363370 */:
                if (this.mDateType != 4) {
                    switchToYear();
                    break;
                }
                break;
        }
    }

    private final void switchToDay() {
        int iCaluteDayOffset;
        int i;
        if (this.mPageType == 11 || TextUtils.isEmpty(this.mSelectDate)) {
            iCaluteDayOffset = (-this.DEFAULT_LOAD_COUNT) + 1;
        } else {
            LinearLayout lay_tip = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_tip);
            Intrinsics.checkExpressionValueIsNotNull(lay_tip, "lay_tip");
            if (lay_tip.getChildCount() > 0) {
                if (this.mDateType == 4) {
                    P p = this.mPresenter;
                    if (p == 0) {
                        Intrinsics.throwNpe();
                    }
                    ChartDetailPresenter chartDetailPresenter = (ChartDetailPresenter) p;
                    String str = this.mSelectDate;
                    if (str == null) {
                        Intrinsics.throwNpe();
                    }
                    iCaluteDayOffset = chartDetailPresenter.caluteDayOffsetFromYear(str);
                } else {
                    P p2 = this.mPresenter;
                    if (p2 == 0) {
                        Intrinsics.throwNpe();
                    }
                    ChartDetailPresenter chartDetailPresenter2 = (ChartDetailPresenter) p2;
                    String str2 = this.mSelectDate;
                    if (str2 == null) {
                        Intrinsics.throwNpe();
                    }
                    iCaluteDayOffset = chartDetailPresenter2.caluteDayOffset(str2);
                }
            } else {
                P p3 = this.mPresenter;
                if (p3 == 0) {
                    Intrinsics.throwNpe();
                }
                ChartDetailPresenter chartDetailPresenter3 = (ChartDetailPresenter) p3;
                P p4 = this.mPresenter;
                if (p4 == 0) {
                    Intrinsics.throwNpe();
                }
                ChartDetailPresenter chartDetailPresenter4 = (ChartDetailPresenter) p4;
                int i2 = this.mDateType;
                String str3 = this.mSelectDate;
                if (str3 == null) {
                    Intrinsics.throwNpe();
                }
                iCaluteDayOffset = chartDetailPresenter3.caluteDayOffset(chartDetailPresenter4.caluteDateByDateTypeChanged(i2, 1, str3));
            }
        }
        this.mDateOffset = iCaluteDayOffset;
        int i3 = this.mDateOffset;
        if (i3 > (-this.DEFAULT_LOAD_COUNT) + 1) {
            i = i3 == 0 ? 2 : 1;
            this.mDateOffset = (-this.DEFAULT_LOAD_COUNT) + 1;
        } else {
            this.mDateOffset = i3 - 1;
            i = 1;
        }
        this.mDateType = 1;
        View divider_1 = _$_findCachedViewById(com.ido.life.R.id.divider_1);
        Intrinsics.checkExpressionValueIsNotNull(divider_1, "divider_1");
        divider_1.setVisibility(8);
        View divider_2 = _$_findCachedViewById(com.ido.life.R.id.divider_2);
        Intrinsics.checkExpressionValueIsNotNull(divider_2, "divider_2");
        divider_2.setVisibility(0);
        View divider_3 = _$_findCachedViewById(com.ido.life.R.id.divider_3);
        Intrinsics.checkExpressionValueIsNotNull(divider_3, "divider_3");
        divider_3.setVisibility(0);
        CommonLogUtil.d(this.TAG, "mDateOffset=" + this.mDateOffset + ",prePosition=" + i);
        loadFragmentList(i);
    }

    private final void switchToWeek() {
        int iCaluteWeekOffset;
        int i;
        if (TextUtils.isEmpty(this.mSelectDate)) {
            iCaluteWeekOffset = (-this.DEFAULT_LOAD_COUNT) + 1;
        } else {
            LinearLayout lay_tip = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_tip);
            Intrinsics.checkExpressionValueIsNotNull(lay_tip, "lay_tip");
            if (lay_tip.getChildCount() > 0) {
                if (this.mDateType == 4) {
                    P p = this.mPresenter;
                    if (p == 0) {
                        Intrinsics.throwNpe();
                    }
                    ChartDetailPresenter chartDetailPresenter = (ChartDetailPresenter) p;
                    String str = this.mSelectDate;
                    if (str == null) {
                        Intrinsics.throwNpe();
                    }
                    iCaluteWeekOffset = chartDetailPresenter.caluteWeekOffsetFromYear(str);
                } else {
                    P p2 = this.mPresenter;
                    if (p2 == 0) {
                        Intrinsics.throwNpe();
                    }
                    ChartDetailPresenter chartDetailPresenter2 = (ChartDetailPresenter) p2;
                    String str2 = this.mSelectDate;
                    if (str2 == null) {
                        Intrinsics.throwNpe();
                    }
                    iCaluteWeekOffset = chartDetailPresenter2.caluteWeekOffset(str2);
                }
            } else {
                P p3 = this.mPresenter;
                if (p3 == 0) {
                    Intrinsics.throwNpe();
                }
                ChartDetailPresenter chartDetailPresenter3 = (ChartDetailPresenter) p3;
                P p4 = this.mPresenter;
                if (p4 == 0) {
                    Intrinsics.throwNpe();
                }
                ChartDetailPresenter chartDetailPresenter4 = (ChartDetailPresenter) p4;
                int i2 = this.mDateType;
                String str3 = this.mSelectDate;
                if (str3 == null) {
                    Intrinsics.throwNpe();
                }
                iCaluteWeekOffset = chartDetailPresenter3.caluteWeekOffset(chartDetailPresenter4.caluteDateByDateTypeChanged(i2, 2, str3));
            }
        }
        this.mDateOffset = iCaluteWeekOffset;
        int i3 = this.mDateOffset;
        if (i3 > (-this.DEFAULT_LOAD_COUNT) + 1) {
            i = i3 == 0 ? 2 : 1;
            this.mDateOffset = (-this.DEFAULT_LOAD_COUNT) + 1;
        } else {
            this.mDateOffset = i3 - 1;
            i = 1;
        }
        this.mDateType = 2;
        View divider_1 = _$_findCachedViewById(com.ido.life.R.id.divider_1);
        Intrinsics.checkExpressionValueIsNotNull(divider_1, "divider_1");
        divider_1.setVisibility(8);
        View divider_2 = _$_findCachedViewById(com.ido.life.R.id.divider_2);
        Intrinsics.checkExpressionValueIsNotNull(divider_2, "divider_2");
        divider_2.setVisibility(8);
        View divider_3 = _$_findCachedViewById(com.ido.life.R.id.divider_3);
        Intrinsics.checkExpressionValueIsNotNull(divider_3, "divider_3");
        divider_3.setVisibility(0);
        CommonLogUtil.d(this.TAG, "mDateOffset=" + this.mDateOffset + ",prePosition=" + i);
        loadFragmentList(i);
    }

    private final void switchToMonth() {
        int iCaluteMonthOffset;
        int i;
        if (TextUtils.isEmpty(this.mSelectDate)) {
            iCaluteMonthOffset = (-this.DEFAULT_LOAD_COUNT) + 1;
        } else {
            LinearLayout lay_tip = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_tip);
            Intrinsics.checkExpressionValueIsNotNull(lay_tip, "lay_tip");
            if (lay_tip.getChildCount() > 0) {
                P p = this.mPresenter;
                if (p == 0) {
                    Intrinsics.throwNpe();
                }
                ChartDetailPresenter chartDetailPresenter = (ChartDetailPresenter) p;
                String str = this.mSelectDate;
                if (str == null) {
                    Intrinsics.throwNpe();
                }
                iCaluteMonthOffset = chartDetailPresenter.caluteMonthOffset(str);
            } else {
                P p2 = this.mPresenter;
                if (p2 == 0) {
                    Intrinsics.throwNpe();
                }
                ChartDetailPresenter chartDetailPresenter2 = (ChartDetailPresenter) p2;
                P p3 = this.mPresenter;
                if (p3 == 0) {
                    Intrinsics.throwNpe();
                }
                ChartDetailPresenter chartDetailPresenter3 = (ChartDetailPresenter) p3;
                int i2 = this.mDateType;
                String str2 = this.mSelectDate;
                if (str2 == null) {
                    Intrinsics.throwNpe();
                }
                iCaluteMonthOffset = chartDetailPresenter2.caluteMonthOffset(chartDetailPresenter3.caluteDateByDateTypeChanged(i2, 3, str2));
            }
        }
        this.mDateOffset = iCaluteMonthOffset;
        int i3 = this.mDateOffset;
        if (i3 > (-this.DEFAULT_LOAD_COUNT) + 1) {
            i = i3 == 0 ? 2 : 1;
            this.mDateOffset = (-this.DEFAULT_LOAD_COUNT) + 1;
        } else {
            this.mDateOffset = i3 - 1;
            i = 1;
        }
        this.mDateType = 3;
        View divider_1 = _$_findCachedViewById(com.ido.life.R.id.divider_1);
        Intrinsics.checkExpressionValueIsNotNull(divider_1, "divider_1");
        divider_1.setVisibility(0);
        View divider_2 = _$_findCachedViewById(com.ido.life.R.id.divider_2);
        Intrinsics.checkExpressionValueIsNotNull(divider_2, "divider_2");
        divider_2.setVisibility(8);
        View divider_3 = _$_findCachedViewById(com.ido.life.R.id.divider_3);
        Intrinsics.checkExpressionValueIsNotNull(divider_3, "divider_3");
        divider_3.setVisibility(8);
        CommonLogUtil.d(this.TAG, "mDateOffset=" + this.mDateOffset + ",prePosition=" + i);
        loadFragmentList(i);
    }

    private final void switchToYear() {
        int iCaluteYearOffset;
        int i;
        if (TextUtils.isEmpty(this.mSelectDate)) {
            iCaluteYearOffset = (-this.DEFAULT_LOAD_COUNT) + 1;
        } else {
            LinearLayout lay_tip = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_tip);
            Intrinsics.checkExpressionValueIsNotNull(lay_tip, "lay_tip");
            if (lay_tip.getChildCount() > 0) {
                P p = this.mPresenter;
                if (p == 0) {
                    Intrinsics.throwNpe();
                }
                ChartDetailPresenter chartDetailPresenter = (ChartDetailPresenter) p;
                String str = this.mSelectDate;
                if (str == null) {
                    Intrinsics.throwNpe();
                }
                iCaluteYearOffset = chartDetailPresenter.caluteYearOffset(str);
            } else {
                P p2 = this.mPresenter;
                if (p2 == 0) {
                    Intrinsics.throwNpe();
                }
                ChartDetailPresenter chartDetailPresenter2 = (ChartDetailPresenter) p2;
                P p3 = this.mPresenter;
                if (p3 == 0) {
                    Intrinsics.throwNpe();
                }
                ChartDetailPresenter chartDetailPresenter3 = (ChartDetailPresenter) p3;
                int i2 = this.mDateType;
                String str2 = this.mSelectDate;
                if (str2 == null) {
                    Intrinsics.throwNpe();
                }
                iCaluteYearOffset = chartDetailPresenter2.caluteYearOffset(chartDetailPresenter3.caluteDateByDateTypeChanged(i2, 4, str2));
            }
        }
        this.mDateOffset = iCaluteYearOffset;
        int i3 = this.mDateOffset;
        if (i3 > (-this.DEFAULT_LOAD_COUNT) + 1) {
            i = i3 == 0 ? 2 : 1;
            this.mDateOffset = (-this.DEFAULT_LOAD_COUNT) + 1;
        } else {
            this.mDateOffset = i3 - 1;
            i = 1;
        }
        this.mDateType = 4;
        View divider_1 = _$_findCachedViewById(com.ido.life.R.id.divider_1);
        Intrinsics.checkExpressionValueIsNotNull(divider_1, "divider_1");
        divider_1.setVisibility(0);
        View divider_2 = _$_findCachedViewById(com.ido.life.R.id.divider_2);
        Intrinsics.checkExpressionValueIsNotNull(divider_2, "divider_2");
        divider_2.setVisibility(0);
        View divider_3 = _$_findCachedViewById(com.ido.life.R.id.divider_3);
        Intrinsics.checkExpressionValueIsNotNull(divider_3, "divider_3");
        divider_3.setVisibility(8);
        CommonLogUtil.d(this.TAG, "mDateOffset=" + this.mDateOffset + ",prePosition=" + i);
        loadFragmentList(i);
    }

    private final void loadFragmentList(int pPosition) {
        HomeHelperKt.printAndSave("进入loadFragmentList-->要显示的类型为-->" + this.mPageType, this.TAG);
        int i = this.DEFAULT_LOAD_COUNT;
        if (this.mDateOffset == Integer.MAX_VALUE) {
            this.mDateOffset = (-i) + 1;
        }
        if (this.mPageType == 11 && this.mDateType == 1) {
            this.mDateOffset = 0;
            i = 1;
            pPosition = 0;
        }
        int size = this.mFragmentList.size();
        if (size > i) {
            for (int i2 = size - i; i2 > 0; i2--) {
                this.mFragmentList.removeFirst();
            }
        } else if (size < i) {
            int i3 = i - size;
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = this.mPageType;
                if (i5 == 0) {
                    this.mFragmentList.addLast(StepDetailFragment.INSTANCE.getInstance(null));
                } else if (i5 == 1) {
                    this.mFragmentList.addLast(DistanceDetailFragment.INSTANCE.getInstance(null));
                } else if (i5 == 2) {
                    this.mFragmentList.addLast(CalorieDetailFragment.INSTANCE.getInstance(null));
                } else if (i5 == 8) {
                    this.mFragmentList.addLast(RateDetailFragment.INSTANCE.getInstance(null));
                } else if (i5 == 9) {
                    this.mFragmentList.addLast(PresureDetailFragment.INSTANCE.getInstance(null));
                } else if (i5 == 11) {
                    this.mFragmentList.addLast(WeightDetailFragment.INSTANCE.getInstance(null));
                } else if (i5 != 19) {
                    switch (i5) {
                        case 15:
                            this.mFragmentList.addLast(ActivityDetailFragment.INSTANCE.getInstance(null));
                            break;
                        case 16:
                            this.mFragmentList.addLast(FitnessDetailFragment.INSTANCE.getInstance(null));
                            break;
                        case 17:
                            this.mFragmentList.addLast(AmbientVolumeDetailFragment.INSTANCE.getInstance(null));
                            break;
                    }
                } else {
                    this.mFragmentList.addLast(OxygenDetailFragment.INSTANCE.getInstance(null));
                }
            }
        }
        int size2 = this.mFragmentList.size();
        BaseDetailViewHolder baseDetailViewHolder = (BaseDetailViewHolder) null;
        List<BaseDetailViewHolder> list = this.mViewHolderMap.get(this.TOP_VIEW_HOLDER);
        if (list == null) {
            Intrinsics.throwNpe();
        }
        List<BaseDetailViewHolder> list2 = list;
        List<BaseDetailViewHolder> list3 = this.mViewHolderMap.get(this.BOTTOM_VIEW_HOLDER);
        if (list3 == null) {
            Intrinsics.throwNpe();
        }
        List<BaseDetailViewHolder> list4 = list3;
        List<BaseDetailViewHolder> list5 = this.mViewHolderMap.get(this.TIP_VIEW_HOLDER);
        if (list5 == null) {
            Intrinsics.throwNpe();
        }
        List<BaseDetailViewHolder> list6 = list5;
        list2.clear();
        list4.clear();
        list6.clear();
        BaseDetailViewHolder topView = baseDetailViewHolder;
        BaseDetailViewHolder bottomView = topView;
        BaseDetailViewHolder tipViewHolder = bottomView;
        for (int i6 = 0; i6 < size2; i6++) {
            LifecycleOwner lifecycleOwner = (Fragment) this.mFragmentList.get(i6);
            if (lifecycleOwner instanceof BaseDetailCallback) {
                BaseDetailCallback baseDetailCallback = (BaseDetailCallback) lifecycleOwner;
                baseDetailCallback.refreshCalorieType(this.mCalorieType);
                baseDetailCallback.refreshRateType(this.mRateType);
                baseDetailCallback.refreshTypeAndOffset(false);
                if (baseDetailCallback.topViewNeedUpdate()) {
                    HomeHelperKt.printAndSave("载入布局--callback.topViewNeedUpdate()", this.TAG);
                    list2.add(baseDetailCallback.getTopView(this));
                } else {
                    if (topView == null) {
                        topView = baseDetailCallback.getTopView(this);
                    }
                    list2.add(topView);
                    HomeHelperKt.printAndSave("载入布局--commonTopViewHolder == null", this.TAG);
                }
                if (baseDetailCallback.bottomViewNeedUpdate()) {
                    list4.add(baseDetailCallback.getBottomView(this));
                } else {
                    if (bottomView == null) {
                        bottomView = baseDetailCallback.getBottomView(this);
                    }
                    list4.add(bottomView);
                }
                if (baseDetailCallback.tipViewNeedUpdate()) {
                    list6.add(baseDetailCallback.getTipViewHolder(this));
                } else {
                    if (tipViewHolder == null) {
                        tipViewHolder = baseDetailCallback.getTipViewHolder(this);
                    }
                    list6.add(tipViewHolder);
                }
            }
        }
        LifecycleOwner lifecycleOwner2 = (Fragment) this.mFragmentList.get(pPosition);
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_tip)).removeAllViews();
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top)).removeAllViews();
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_bottom)).removeAllViews();
        LinearLayout lay_top = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top);
        Intrinsics.checkExpressionValueIsNotNull(lay_top, "lay_top");
        lay_top.setForegroundTintList(ColorStateList.valueOf(0));
        LinearLayout lay_top2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top);
        Intrinsics.checkExpressionValueIsNotNull(lay_top2, "lay_top");
        lay_top2.setAlpha(1.0f);
        if (lifecycleOwner2 instanceof BaseDetailCallback) {
            CustomChartDetailViewPager view_pager = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
            Intrinsics.checkExpressionValueIsNotNull(view_pager, "view_pager");
            view_pager.getLayoutParams().height = ((BaseDetailCallback) lifecycleOwner2).getChartHeight(this);
            List<BaseDetailViewHolder> list7 = this.mViewHolderMap.get(this.TOP_VIEW_HOLDER);
            if (list7 == null) {
                Intrinsics.throwNpe();
            }
            BaseDetailViewHolder baseDetailViewHolder2 = list7.get(pPosition);
            if (baseDetailViewHolder2 == null) {
                List<BaseDetailViewHolder> list8 = this.mViewHolderMap.get(this.TOP_VIEW_HOLDER);
                if (list8 == null) {
                    Intrinsics.throwNpe();
                }
                baseDetailViewHolder2 = list8.get(0);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("显示topView,topViewHolder");
            sb.append(baseDetailViewHolder2 == null ? "为空" : "不为空");
            sb.append(",prePosition=");
            sb.append(pPosition);
            sb.append("-->");
            sb.append(this.mPageType);
            HomeHelperKt.printAndSave(sb.toString(), this.TAG);
            if (baseDetailViewHolder2 != null) {
                ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top)).addView(baseDetailViewHolder2.getItemView(), new LinearLayout.LayoutParams(-1, -2));
            }
            List<BaseDetailViewHolder> list9 = this.mViewHolderMap.get(this.BOTTOM_VIEW_HOLDER);
            if (list9 == null) {
                Intrinsics.throwNpe();
            }
            BaseDetailViewHolder baseDetailViewHolder3 = list9.get(pPosition);
            if (baseDetailViewHolder3 != null) {
                ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_bottom)).addView(baseDetailViewHolder3.getItemView(), new LinearLayout.LayoutParams(-1, -1));
            }
        }
        FragmentPagerStateAdapter fragmentPagerStateAdapter = this.mAdapter;
        if (fragmentPagerStateAdapter == null) {
            CustomChartDetailViewPager view_pager2 = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
            Intrinsics.checkExpressionValueIsNotNull(view_pager2, "view_pager");
            FragmentPagerStateAdapter fragmentPagerStateAdapter2 = new FragmentPagerStateAdapter(getSupportFragmentManager(), this.mFragmentList);
            this.mAdapter = fragmentPagerStateAdapter2;
            view_pager2.setAdapter(fragmentPagerStateAdapter2);
        } else {
            if (fragmentPagerStateAdapter == null) {
                Intrinsics.throwNpe();
            }
            fragmentPagerStateAdapter.notifyDataSetChanged();
        }
        CustomChartDetailViewPager view_pager3 = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
        Intrinsics.checkExpressionValueIsNotNull(view_pager3, "view_pager");
        view_pager3.setCurrentItem(pPosition);
        ChartDetailActivity chartDetailActivity = this;
        ((CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager)).removeOnPageChangeListener(chartDetailActivity);
        ((CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager)).addOnPageChangeListener(chartDetailActivity);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        int id = v.getId();
        if (id == R.id.card_left) {
            int i = this.mPageType;
            if (i != 2) {
                if (i == 8 && this.mRateType != 1) {
                    this.mRateType = 1;
                    CustomChartDetailViewPager view_pager = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
                    Intrinsics.checkExpressionValueIsNotNull(view_pager, "view_pager");
                    loadFragmentList(view_pager.getCurrentItem());
                    return;
                }
                return;
            }
            if (this.mCalorieType == 1) {
                return;
            }
            this.mCalorieType = 1;
            CustomChartDetailViewPager view_pager2 = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
            Intrinsics.checkExpressionValueIsNotNull(view_pager2, "view_pager");
            loadFragmentList(view_pager2.getCurrentItem());
            return;
        }
        if (id == R.id.card_right) {
            int i2 = this.mPageType;
            if (i2 != 2) {
                if (i2 == 8 && this.mRateType != 2) {
                    this.mRateType = 2;
                    CustomChartDetailViewPager view_pager3 = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
                    Intrinsics.checkExpressionValueIsNotNull(view_pager3, "view_pager");
                    loadFragmentList(view_pager3.getCurrentItem());
                    return;
                }
                return;
            }
            if (this.mCalorieType == 2) {
                return;
            }
            this.mCalorieType = 2;
            CustomChartDetailViewPager view_pager4 = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
            Intrinsics.checkExpressionValueIsNotNull(view_pager4, "view_pager");
            loadFragmentList(view_pager4.getCurrentItem());
            return;
        }
        if (id != R.id.layout_right) {
            return;
        }
        int i3 = this.mPageType;
        if (i3 == 0) {
            if (BLEManager.isConnected()) {
                startActivity(new Intent(this, (Class<?>) SettingTargetActivity.class));
                return;
            } else {
                NormalToast.showToast(getLanguageText(R.string.please_connect_device), 2000);
                return;
            }
        }
        if (i3 == 11) {
            startActivityForResult(new Intent(this, (Class<?>) WeightSettingActivity.class), 11);
            return;
        }
        if (i3 == 17) {
            if (BLEManager.isConnected()) {
                startActivity(new Intent(this, (Class<?>) NoiseMonitoringActivity.class));
                return;
            } else {
                NormalToast.showToast(getLanguageText(R.string.please_connect_device), 2000);
                return;
            }
        }
        if (i3 == 8) {
            if (BLEManager.isConnected()) {
                startActivity(new Intent(this, (Class<?>) HeartRateMonitoringActivity.class));
                return;
            } else {
                NormalToast.showToast(getLanguageText(R.string.please_connect_device), 2000);
                return;
            }
        }
        if (i3 != 9) {
            return;
        }
        if (this.mPressureTipDialog == null) {
            this.mPressureTipDialog = getPressureTipDialog();
        }
        CommBottomConfirmDialog commBottomConfirmDialog = this.mPressureTipDialog;
        if (commBottomConfirmDialog == null) {
            Intrinsics.throwNpe();
        }
        commBottomConfirmDialog.show(getSupportFragmentManager());
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback
    public void updateDateSelect(Fragment fragment, int dateType) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        if (this.mDateType == dateType) {
            return;
        }
        ((RadioGroup) _$_findCachedViewById(com.ido.life.R.id.radio_group)).setOnCheckedChangeListener(null);
        this.mDateType = dateType;
        int i = this.mDateType;
        if (i == 1) {
            RegularRadioButton rrb_day = (RegularRadioButton) _$_findCachedViewById(com.ido.life.R.id.rrb_day);
            Intrinsics.checkExpressionValueIsNotNull(rrb_day, "rrb_day");
            rrb_day.setChecked(true);
        } else if (i == 2) {
            RegularRadioButton rrb_week = (RegularRadioButton) _$_findCachedViewById(com.ido.life.R.id.rrb_week);
            Intrinsics.checkExpressionValueIsNotNull(rrb_week, "rrb_week");
            rrb_week.setChecked(true);
        } else if (i == 3) {
            RegularRadioButton rrb_month = (RegularRadioButton) _$_findCachedViewById(com.ido.life.R.id.rrb_month);
            Intrinsics.checkExpressionValueIsNotNull(rrb_month, "rrb_month");
            rrb_month.setChecked(true);
        } else if (i == 4) {
            RegularRadioButton rrb_year = (RegularRadioButton) _$_findCachedViewById(com.ido.life.R.id.rrb_year);
            Intrinsics.checkExpressionValueIsNotNull(rrb_year, "rrb_year");
            rrb_year.setChecked(true);
        }
        ((RadioGroup) _$_findCachedViewById(com.ido.life.R.id.radio_group)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.ido.life.module.home.chartdetail.vertical.ChartDetailActivity.updateDateSelect.1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup group, int i2) {
                Intrinsics.checkParameterIsNotNull(group, "group");
                ChartDetailActivity.this.onCheckedChanged(group, i2);
            }
        });
        CustomChartDetailViewPager view_pager = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
        Intrinsics.checkExpressionValueIsNotNull(view_pager, "view_pager");
        loadFragmentList(view_pager.getCurrentItem());
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback
    public void updateSelectDate(Fragment fragment, String selectDate) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        if (this.mFragmentList.size() > 0) {
            int iIndexOf = this.mFragmentList.indexOf(fragment);
            CustomChartDetailViewPager view_pager = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
            Intrinsics.checkExpressionValueIsNotNull(view_pager, "view_pager");
            if (iIndexOf == view_pager.getCurrentItem()) {
                this.mSelectDate = selectDate;
            }
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        Intrinsics.checkParameterIsNotNull(event, "event");
        int id = v.getId();
        if (id == R.id.lay_bottom || id == R.id.lay_top) {
            LinearLayout lay_tip = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_tip);
            Intrinsics.checkExpressionValueIsNotNull(lay_tip, "lay_tip");
            if (lay_tip.getChildCount() > 0) {
                ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_tip)).removeAllViews();
                LinearLayout lay_top = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top);
                Intrinsics.checkExpressionValueIsNotNull(lay_top, "lay_top");
                lay_top.setForegroundTintList(ColorStateList.valueOf(0));
                LinearLayout lay_top2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top);
                Intrinsics.checkExpressionValueIsNotNull(lay_top2, "lay_top");
                lay_top2.setAlpha(1.0f);
                LinkedList<Fragment> linkedList = this.mFragmentList;
                CustomChartDetailViewPager view_pager = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
                Intrinsics.checkExpressionValueIsNotNull(view_pager, "view_pager");
                LifecycleOwner lifecycleOwner = linkedList.get(view_pager.getCurrentItem());
                Intrinsics.checkExpressionValueIsNotNull(lifecycleOwner, "mFragmentList[view_pager.currentItem]");
                LifecycleOwner lifecycleOwner2 = (Fragment) lifecycleOwner;
                if (lifecycleOwner2 instanceof BaseDetailCallback) {
                    BaseDetailCallback.DefaultImpls.hideSelectedUi$default((BaseDetailCallback) lifecycleOwner2, false, 1, null);
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.customview.charter.CustomChatBar.ChartClickListener
    public void onChartClick(View target, RectF rectF, int index) {
        GradientBarChartBar gradientBarChartBar;
        LinearLayout linearLayout;
        LinearLayout linearLayout2;
        View view;
        int[] iArr;
        char c2;
        ViewGroup.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2;
        ViewGroup.LayoutParams layoutParams3;
        ViewGroup.LayoutParams layoutParams4;
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(rectF, "rectF");
        CommonLogUtil.d(this.TAG, "onChartClick rectF=" + rectF + " index=" + index);
        if (index < 0) {
            LinearLayout lay_tip = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_tip);
            Intrinsics.checkExpressionValueIsNotNull(lay_tip, "lay_tip");
            if (lay_tip.getChildCount() > 0) {
                ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_tip)).removeAllViews();
                LifecycleOwner lifecycleOwner = this.mFragmentList.get(index);
                Intrinsics.checkExpressionValueIsNotNull(lifecycleOwner, "mFragmentList[index]");
                LifecycleOwner lifecycleOwner2 = (Fragment) lifecycleOwner;
                if (lifecycleOwner2 instanceof BaseDetailCallback) {
                    BaseDetailCallback.DefaultImpls.hideSelectedUi$default((BaseDetailCallback) lifecycleOwner2, false, 1, null);
                }
                LinearLayout lay_top = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top);
                Intrinsics.checkExpressionValueIsNotNull(lay_top, "lay_top");
                lay_top.setForegroundTintList(ColorStateList.valueOf(0));
                LinearLayout lay_top2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top);
                Intrinsics.checkExpressionValueIsNotNull(lay_top2, "lay_top");
                lay_top2.setAlpha(1.0f);
                return;
            }
            return;
        }
        if (target.getId() == R.id.chart_activity_calorie || target.getId() == R.id.chart_activity_time) {
            LinkedList<Fragment> linkedList = this.mFragmentList;
            CustomChartDetailViewPager view_pager = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
            Intrinsics.checkExpressionValueIsNotNull(view_pager, "view_pager");
            Fragment fragment = linkedList.get(view_pager.getCurrentItem());
            Intrinsics.checkExpressionValueIsNotNull(fragment, "mFragmentList[view_pager.currentItem]");
            View view2 = fragment.getView();
            if (view2 == null || (gradientBarChartBar = (GradientBarChartBar) view2.findViewById(R.id.chart_walk)) == null) {
                return;
            }
            gradientBarChartBar.onChartClick(index);
            return;
        }
        try {
            LinkedList<Fragment> linkedList2 = this.mFragmentList;
            CustomChartDetailViewPager view_pager2 = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
            Intrinsics.checkExpressionValueIsNotNull(view_pager2, "view_pager");
            Fragment fragment2 = linkedList2.get(view_pager2.getCurrentItem());
            Intrinsics.checkExpressionValueIsNotNull(fragment2, "mFragmentList[view_pager.currentItem]");
            Fragment fragment3 = fragment2;
            if (!(fragment3 instanceof BaseDetailCallback)) {
                return;
            }
            int[] iArr2 = new int[2];
            ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_tip)).getLocationOnScreen(new int[2]);
            target.getLocationOnScreen(iArr2);
            BaseDetailCallback baseDetailCallback = (BaseDetailCallback) fragment3;
            BaseDetailViewHolder tipViewHolder = getTipViewHolder(fragment3);
            if (tipViewHolder == null) {
                Intrinsics.throwNpe();
            }
            View itemView = tipViewHolder.getItemView();
            if (itemView == null) {
                ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_tip)).removeAllViews();
                LinearLayout lay_top3 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top);
                Intrinsics.checkExpressionValueIsNotNull(lay_top3, "lay_top");
                lay_top3.setForegroundTintList(ColorStateList.valueOf(0));
                LinearLayout lay_top4 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top);
                Intrinsics.checkExpressionValueIsNotNull(lay_top4, "lay_top");
                lay_top4.setAlpha(1.0f);
                return;
            }
            LinearLayout lay_tip2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_tip);
            Intrinsics.checkExpressionValueIsNotNull(lay_tip2, "lay_tip");
            if (lay_tip2.getChildCount() == 0) {
                ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_tip)).addView(itemView, new ViewGroup.LayoutParams(-1, -2));
            }
            LinearLayout lay_top5 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top);
            Intrinsics.checkExpressionValueIsNotNull(lay_top5, "lay_top");
            lay_top5.setForegroundTintList(ColorStateList.valueOf(0));
            LinearLayout lay_top6 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top);
            Intrinsics.checkExpressionValueIsNotNull(lay_top6, "lay_top");
            lay_top6.setAlpha(0.1f);
            baseDetailCallback.refreshChartTipView(index);
            View tipLayContent = baseDetailCallback.getTipLayContent();
            try {
                if (tipLayContent == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout");
                }
                LinearLayout linearLayout3 = (LinearLayout) tipLayContent;
                itemView.measure(0, 0);
                ViewGroup.LayoutParams layoutParams5 = linearLayout3.getLayoutParams();
                layoutParams5.height = (int) ((iArr2[1] - r15[1]) + rectF.top);
                linearLayout3.setLayoutParams(layoutParams5);
                LinearLayout linearLayout4 = (LinearLayout) itemView.findViewById(R.id.lay_tip_bottom);
                if (fragment3 instanceof FitnessDetailFragment) {
                    try {
                        View view3 = ((FitnessDetailFragment) fragment3).getView();
                        if (view3 == null) {
                            Intrinsics.throwNpe();
                        }
                        ViewGroup viewGroup = (ViewGroup) view3.findViewById(R.id.lay_calorie_all);
                        View view4 = ((FitnessDetailFragment) fragment3).getView();
                        if (view4 == null) {
                            Intrinsics.throwNpe();
                        }
                        ViewGroup lay_active_time_all = (ViewGroup) view4.findViewById(R.id.lay_active_time_all);
                        View view5 = ((FitnessDetailFragment) fragment3).getView();
                        if (view5 == null) {
                            Intrinsics.throwNpe();
                        }
                        ViewGroup lay_walk_all = (ViewGroup) view5.findViewById(R.id.lay_walk_all);
                        viewGroup.getLocationOnScreen(new int[2]);
                        lay_active_time_all.getLocationOnScreen(new int[2]);
                        lay_walk_all.getLocationOnScreen(new int[2]);
                        View viewFindViewById = linearLayout4.findViewById(R.id.line_two);
                        View viewFindViewById2 = linearLayout4.findViewById(R.id.line_three);
                        View viewFindViewById3 = linearLayout4.findViewById(R.id.line_four);
                        View viewFindViewById4 = linearLayout4.findViewById(R.id.line_five);
                        View view6 = ((FitnessDetailFragment) fragment3).getView();
                        if (view6 == null) {
                            Intrinsics.throwNpe();
                        }
                        GradientBarChartBar chartActivityCalorie = (GradientBarChartBar) view6.findViewById(R.id.chart_activity_calorie);
                        View view7 = ((FitnessDetailFragment) fragment3).getView();
                        if (view7 == null) {
                            Intrinsics.throwNpe();
                        }
                        linearLayout = linearLayout4;
                        GradientBarChartBar chartActivityTime = (GradientBarChartBar) view7.findViewById(R.id.chart_activity_time);
                        View view8 = ((FitnessDetailFragment) fragment3).getView();
                        if (view8 == null) {
                            Intrinsics.throwNpe();
                        }
                        GradientBarChartBar chartWalk = (GradientBarChartBar) view8.findViewById(R.id.chart_walk);
                        linearLayout2 = linearLayout3;
                        view = itemView;
                        Intrinsics.checkExpressionValueIsNotNull(chartWalk, "chartWalk");
                        RectF rectF2 = chartWalk.getCircleRegion().get(index);
                        iArr = iArr2;
                        Intrinsics.checkExpressionValueIsNotNull(chartActivityCalorie, "chartActivityCalorie");
                        RectF rectF3 = chartActivityCalorie.getCircleRegion().get(index);
                        chartWalk.getLocationOnScreen(new int[2]);
                        chartActivityTime.getLocationOnScreen(new int[2]);
                        chartActivityCalorie.getLocationOnScreen(new int[2]);
                        if (viewFindViewById4 != null && (layoutParams4 = viewFindViewById4.getLayoutParams()) != null) {
                            Intrinsics.checkExpressionValueIsNotNull(lay_walk_all, "lay_walk_all");
                            float height = lay_walk_all.getHeight() - (chartWalk.getHeight() - rectF.top);
                            Intrinsics.checkExpressionValueIsNotNull(chartActivityTime, "chartActivityTime");
                            layoutParams4.height = MathKt.roundToInt((height + chartActivityTime.getHeight()) - rectF2.bottom);
                        }
                        if (viewFindViewById3 != null && (layoutParams3 = viewFindViewById3.getLayoutParams()) != null) {
                            layoutParams3.height = MathKt.roundToInt(rectF2.height());
                        }
                        if (viewFindViewById2 != null && (layoutParams2 = viewFindViewById2.getLayoutParams()) != null) {
                            Intrinsics.checkExpressionValueIsNotNull(lay_active_time_all, "lay_active_time_all");
                            float height2 = lay_active_time_all.getHeight();
                            Intrinsics.checkExpressionValueIsNotNull(chartActivityTime, "chartActivityTime");
                            layoutParams2.height = MathKt.roundToInt(((height2 - (chartActivityTime.getHeight() - rectF2.top)) + chartActivityCalorie.getHeight()) - rectF3.bottom);
                        }
                        if (viewFindViewById != null && (layoutParams = viewFindViewById.getLayoutParams()) != null) {
                            layoutParams.height = MathKt.roundToInt(rectF3.height());
                        }
                        c2 = 0;
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        CommonLogUtil.printAndSave(e.getLocalizedMessage());
                        return;
                    }
                } else {
                    linearLayout2 = linearLayout3;
                    linearLayout = linearLayout4;
                    view = itemView;
                    iArr = iArr2;
                    c2 = 0;
                }
                float fCenterX = (iArr[c2] + rectF.centerX()) - (view.getMeasuredWidth() / 2.0f);
                if (view.getMeasuredWidth() + fCenterX > this.mScreenWidth) {
                    LinearLayout linearLayout5 = linearLayout2;
                    linearLayout5.setGravity(3);
                    linearLayout5.setTranslationX(this.mScreenWidth - view.getMeasuredWidth());
                    if (linearLayout != null) {
                        LinearLayout linearLayout6 = linearLayout;
                        linearLayout6.measure(0, 0);
                        linearLayout6.setTranslationX(((iArr[0] + rectF.centerX()) - linearLayout5.getTranslationX()) - linearLayout6.getMeasuredWidth());
                        return;
                    }
                    return;
                }
                LinearLayout linearLayout7 = linearLayout;
                LinearLayout linearLayout8 = linearLayout2;
                if (fCenterX < 0) {
                    linearLayout8.setGravity(3);
                    linearLayout8.setTranslationX(0.0f);
                    if (linearLayout7 != null) {
                        linearLayout7.measure(0, 0);
                        linearLayout7.setTranslationX(iArr[0] + rectF.centerX());
                        return;
                    }
                    return;
                }
                if (linearLayout7 != null) {
                    linearLayout7.setTranslationX(0.0f);
                }
                linearLayout8.setGravity(17);
                linearLayout8.setTranslationX(fCenterX);
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            e = e4;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == -1) {
            LinkedList<Fragment> linkedList = this.mFragmentList;
            CustomChartDetailViewPager view_pager = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
            Intrinsics.checkExpressionValueIsNotNull(view_pager, "view_pager");
            LifecycleOwner lifecycleOwner = linkedList.get(view_pager.getCurrentItem());
            Intrinsics.checkExpressionValueIsNotNull(lifecycleOwner, "mFragmentList[view_pager.currentItem]");
            LifecycleOwner lifecycleOwner2 = (Fragment) lifecycleOwner;
            if (lifecycleOwner2 instanceof BaseDetailCallback) {
                ((BaseDetailCallback) lifecycleOwner2).refreshTypeAndOffset(true);
            }
        }
    }

    private final CommBottomConfirmDialog getPressureTipDialog() {
        CommBottomConfirmDialog dialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.tips), getLanguageText(R.string.first_pressure_ajust_message), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true);
        dialog.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.home.chartdetail.vertical.ChartDetailActivity$pressureTipDialog$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CommBottomConfirmDialog commBottomConfirmDialog = this.this$0.mPressureTipDialog;
                if (commBottomConfirmDialog == null) {
                    Intrinsics.throwNpe();
                }
                commBottomConfirmDialog.dismiss();
                ChartDetailActivity chartDetailActivity = this.this$0;
                chartDetailActivity.startActivity(new Intent(chartDetailActivity, (Class<?>) PressureAjustPreStartActivity.class));
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog");
        return dialog;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        LinearLayout lay_tip = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_tip);
        Intrinsics.checkExpressionValueIsNotNull(lay_tip, "lay_tip");
        if (lay_tip.getChildCount() > 0) {
            ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_tip)).removeAllViews();
            LifecycleOwner lifecycleOwner = this.mFragmentList.get(position);
            Intrinsics.checkExpressionValueIsNotNull(lifecycleOwner, "mFragmentList[position]");
            LifecycleOwner lifecycleOwner2 = (Fragment) lifecycleOwner;
            if (lifecycleOwner2 instanceof BaseDetailCallback) {
                BaseDetailCallback.DefaultImpls.hideSelectedUi$default((BaseDetailCallback) lifecycleOwner2, false, 1, null);
            }
            LinearLayout lay_top = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top);
            Intrinsics.checkExpressionValueIsNotNull(lay_top, "lay_top");
            lay_top.setForegroundTintList(ColorStateList.valueOf(0));
            LinearLayout lay_top2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top);
            Intrinsics.checkExpressionValueIsNotNull(lay_top2, "lay_top");
            lay_top2.setAlpha(1.0f);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int position) {
        if (this.mPageType == 11 && this.mDateType == 1) {
            return;
        }
        Fragment fragment = this.mFragmentList.get(position);
        Intrinsics.checkExpressionValueIsNotNull(fragment, "mFragmentList[position]");
        Fragment fragment2 = fragment;
        if (fragment2 instanceof BaseDetailCallback) {
            BaseDetailCallback baseDetailCallback = (BaseDetailCallback) fragment2;
            BaseDetailCallback.DefaultImpls.hideSelectedUi$default(baseDetailCallback, false, 1, null);
            if (baseDetailCallback.tipViewNeedUpdate()) {
                ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_tip)).removeAllViews();
            }
        }
        LinearLayout lay_top = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top);
        Intrinsics.checkExpressionValueIsNotNull(lay_top, "lay_top");
        lay_top.setForegroundTintList(ColorStateList.valueOf(0));
        LinearLayout lay_top2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top);
        Intrinsics.checkExpressionValueIsNotNull(lay_top2, "lay_top");
        lay_top2.setAlpha(1.0f);
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top)).removeAllViews();
        BaseDetailViewHolder topViewHolder = getTopViewHolder(fragment2);
        if (topViewHolder != null) {
            ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top)).addView(topViewHolder.getItemView(), new LinearLayout.LayoutParams(-1, -2));
        }
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_bottom)).removeAllViews();
        BaseDetailViewHolder bottomViewHolder = getBottomViewHolder(fragment2);
        if (bottomViewHolder != null) {
            ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_bottom)).addView(bottomViewHolder.getItemView(), new LinearLayout.LayoutParams(-1, -1));
        }
        int size = this.mFragmentList.size();
        if (this.mPageType == 11 && this.mDateType == 1) {
            if (size > 1) {
                return;
            }
        } else if (size > this.DEFAULT_LOAD_COUNT) {
            return;
        }
        if (position != this.mFragmentList.size() - 1) {
            if (position == 0) {
                this.mDateOffset--;
                LifecycleOwner lifecycleOwner = (Fragment) this.mFragmentList.pollLast();
                if (lifecycleOwner instanceof BaseDetailCallback) {
                    ((BaseDetailCallback) lifecycleOwner).refreshTypeAndOffset(false);
                }
                this.mFragmentList.addFirst((Fragment) lifecycleOwner);
                List<BaseDetailViewHolder> list = this.mViewHolderMap.get(this.TOP_VIEW_HOLDER);
                if (list == null) {
                    Intrinsics.throwNpe();
                }
                List<BaseDetailViewHolder> list2 = list;
                List<BaseDetailViewHolder> list3 = this.mViewHolderMap.get(this.BOTTOM_VIEW_HOLDER);
                if (list3 == null) {
                    Intrinsics.throwNpe();
                }
                List<BaseDetailViewHolder> list4 = list3;
                List<BaseDetailViewHolder> list5 = this.mViewHolderMap.get(this.TIP_VIEW_HOLDER);
                if (list5 == null) {
                    Intrinsics.throwNpe();
                }
                List<BaseDetailViewHolder> list6 = list5;
                BaseDetailViewHolder baseDetailViewHolder = (BaseDetailViewHolder) CollectionsKt.last((List) list2);
                BaseDetailViewHolder baseDetailViewHolder2 = (BaseDetailViewHolder) CollectionsKt.last((List) list4);
                BaseDetailViewHolder baseDetailViewHolder3 = (BaseDetailViewHolder) CollectionsKt.last((List) list6);
                list2.remove(list2.size() - 1);
                list4.remove(list4.size() - 1);
                list6.remove(list6.size() - 1);
                list2.add(0, baseDetailViewHolder);
                list4.add(0, baseDetailViewHolder2);
                list6.add(0, baseDetailViewHolder3);
                FragmentPagerStateAdapter fragmentPagerStateAdapter = this.mAdapter;
                if (fragmentPagerStateAdapter == null) {
                    Intrinsics.throwNpe();
                }
                fragmentPagerStateAdapter.notifyDataSetChanged();
                CustomChartDetailViewPager view_pager = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
                Intrinsics.checkExpressionValueIsNotNull(view_pager, "view_pager");
                view_pager.setCurrentItem(1);
                return;
            }
            return;
        }
        if ((this.mDateOffset + this.mFragmentList.size()) - 1 >= 0) {
            return;
        }
        this.mDateOffset++;
        if (this.mDateOffset > 0) {
            return;
        }
        LifecycleOwner lifecycleOwner2 = (Fragment) this.mFragmentList.pollFirst();
        this.mFragmentList.addLast((Fragment) lifecycleOwner2);
        List<BaseDetailViewHolder> list7 = this.mViewHolderMap.get(this.TOP_VIEW_HOLDER);
        if (list7 == null) {
            Intrinsics.throwNpe();
        }
        List<BaseDetailViewHolder> list8 = list7;
        List<BaseDetailViewHolder> list9 = this.mViewHolderMap.get(this.BOTTOM_VIEW_HOLDER);
        if (list9 == null) {
            Intrinsics.throwNpe();
        }
        List<BaseDetailViewHolder> list10 = list9;
        List<BaseDetailViewHolder> list11 = this.mViewHolderMap.get(this.TIP_VIEW_HOLDER);
        if (list11 == null) {
            Intrinsics.throwNpe();
        }
        List<BaseDetailViewHolder> list12 = list11;
        BaseDetailViewHolder baseDetailViewHolder4 = (BaseDetailViewHolder) CollectionsKt.first((List) list8);
        BaseDetailViewHolder baseDetailViewHolder5 = (BaseDetailViewHolder) CollectionsKt.first((List) list10);
        BaseDetailViewHolder baseDetailViewHolder6 = (BaseDetailViewHolder) CollectionsKt.first((List) list12);
        list8.remove(0);
        list10.remove(0);
        list12.remove(0);
        list8.add(baseDetailViewHolder4);
        list10.add(baseDetailViewHolder5);
        list12.add(baseDetailViewHolder6);
        if (lifecycleOwner2 instanceof BaseDetailCallback) {
            ((BaseDetailCallback) lifecycleOwner2).refreshTypeAndOffset(false);
        }
        FragmentPagerStateAdapter fragmentPagerStateAdapter2 = this.mAdapter;
        if (fragmentPagerStateAdapter2 == null) {
            Intrinsics.throwNpe();
        }
        fragmentPagerStateAdapter2.notifyDataSetChanged();
        CustomChartDetailViewPager view_pager2 = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
        Intrinsics.checkExpressionValueIsNotNull(view_pager2, "view_pager");
        view_pager2.setCurrentItem(1);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback
    public int getDateType(Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        return this.mDateType;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback
    public int getPageOffset(Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        if (this.isOpenOxy) {
            this.isOpenOxy = false;
            this.mDateOffset = 0;
            return this.mDateOffset;
        }
        int iIndexOf = this.mFragmentList.indexOf(fragment);
        if (this.isOpenOxy) {
            this.isOpenOxy = false;
            this.mDateOffset = 0;
            return this.mDateOffset;
        }
        if (iIndexOf > -1) {
            return this.mDateOffset + iIndexOf;
        }
        return this.mDateOffset + this.mFragmentList.size();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback
    public int getRateType(Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        return this.mRateType;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback
    public void updateRateType(Fragment fragment, int rateType) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        this.mRateType = rateType;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback
    public int getCalorieType(Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        return this.mCalorieType;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback
    public long getUserId(Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        return runTimeUtil.getUserId();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback
    public void hideTipUI() {
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_tip)).removeAllViews();
        LinearLayout lay_top = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top);
        Intrinsics.checkExpressionValueIsNotNull(lay_top, "lay_top");
        lay_top.setForegroundTintList(ColorStateList.valueOf(0));
        LinearLayout lay_top2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top);
        Intrinsics.checkExpressionValueIsNotNull(lay_top2, "lay_top");
        lay_top2.setAlpha(1.0f);
        LinkedList<Fragment> linkedList = this.mFragmentList;
        CustomChartDetailViewPager view_pager = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
        Intrinsics.checkExpressionValueIsNotNull(view_pager, "view_pager");
        LifecycleOwner lifecycleOwner = linkedList.get(view_pager.getCurrentItem());
        Intrinsics.checkExpressionValueIsNotNull(lifecycleOwner, "mFragmentList[view_pager.currentItem]");
        LifecycleOwner lifecycleOwner2 = (Fragment) lifecycleOwner;
        if (lifecycleOwner2 instanceof BaseDetailCallback) {
            BaseDetailCallback.DefaultImpls.hideSelectedUi$default((BaseDetailCallback) lifecycleOwner2, false, 1, null);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback
    public BaseDetailViewHolder getTopViewHolder(Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        if (!isShow(fragment)) {
            return null;
        }
        int iIndexOf = this.mFragmentList.indexOf(fragment);
        List<BaseDetailViewHolder> list = this.mViewHolderMap.get(this.TOP_VIEW_HOLDER);
        List<BaseDetailViewHolder> list2 = list;
        if (list2 == null || list2.isEmpty()) {
            return null;
        }
        int size = list2.size();
        if (iIndexOf >= 0 && size > iIndexOf) {
            return list.get(iIndexOf);
        }
        return null;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback
    public BaseDetailViewHolder getBottomViewHolder(Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        if (!isShow(fragment)) {
            return null;
        }
        int iIndexOf = this.mFragmentList.indexOf(fragment);
        List<BaseDetailViewHolder> list = this.mViewHolderMap.get(this.BOTTOM_VIEW_HOLDER);
        List<BaseDetailViewHolder> list2 = list;
        if (list2 == null || list2.isEmpty()) {
            return null;
        }
        int size = list2.size();
        if (iIndexOf >= 0 && size > iIndexOf) {
            return list.get(iIndexOf);
        }
        return null;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback
    public BaseDetailViewHolder getTipViewHolder(Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        if (!isShow(fragment)) {
            return null;
        }
        int iIndexOf = this.mFragmentList.indexOf(fragment);
        List<BaseDetailViewHolder> list = this.mViewHolderMap.get(this.TIP_VIEW_HOLDER);
        List<BaseDetailViewHolder> list2 = list;
        if (list2 == null || list2.isEmpty()) {
            return null;
        }
        int size = list2.size();
        if (iIndexOf >= 0 && size > iIndexOf) {
            return list.get(iIndexOf);
        }
        return null;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback
    public int getPagerTop() {
        ((RadioGroup) _$_findCachedViewById(com.ido.life.R.id.radio_group)).measure(0, 0);
        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this) + getResources().getDimensionPixelSize(R.dimen.common_tittle_height);
        RadioGroup radio_group = (RadioGroup) _$_findCachedViewById(com.ido.life.R.id.radio_group);
        Intrinsics.checkExpressionValueIsNotNull(radio_group, "radio_group");
        return statusBarHeight + radio_group.getMeasuredHeight();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback
    public int getTopViewHeight() {
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top)).measure(0, 0);
        LinearLayout lay_top = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_top);
        Intrinsics.checkExpressionValueIsNotNull(lay_top, "lay_top");
        return lay_top.getMeasuredHeight();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback
    public int getBottomViewHeight() {
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_bottom)).measure(0, 0);
        LinearLayout lay_bottom = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_bottom);
        Intrinsics.checkExpressionValueIsNotNull(lay_bottom, "lay_bottom");
        return lay_bottom.getMeasuredHeight();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback
    public int getContentHeight() {
        return getResources().getDimensionPixelSize(R.dimen.sw_dp_300);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback
    public boolean isShow(Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        int iIndexOf = this.mFragmentList.indexOf(fragment);
        CustomChartDetailViewPager view_pager = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
        Intrinsics.checkExpressionValueIsNotNull(view_pager, "view_pager");
        return iIndexOf == view_pager.getCurrentItem();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback
    public void updatePagerHeight(Fragment fragment, int chartHeight) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        if (isShow(fragment)) {
            CustomChartDetailViewPager view_pager = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
            Intrinsics.checkExpressionValueIsNotNull(view_pager, "view_pager");
            ViewGroup.LayoutParams layoutParams = view_pager.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.height = chartHeight;
                CustomChartDetailViewPager view_pager2 = (CustomChartDetailViewPager) _$_findCachedViewById(com.ido.life.R.id.view_pager);
                Intrinsics.checkExpressionValueIsNotNull(view_pager2, "view_pager");
                view_pager2.setLayoutParams(layoutParams);
            }
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback
    public Bundle getPageData(int offset) {
        Bundle bundle = this.mPageConfigData.get(Integer.valueOf(offset));
        if (bundle != null) {
            return bundle;
        }
        Bundle bundleBundleOf = BundleKt.bundleOf(new Pair[0]);
        this.mPageConfigData.put(Integer.valueOf(offset), bundleBundleOf);
        return bundleBundleOf;
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        SettingCallBack.ICallBack iCallBack = this.mSettingCallback;
        if (iCallBack != null) {
            BLEManager.unregisterSettingCallBack(iCallBack);
        }
    }
}