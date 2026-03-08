package com.ido.life.module.home.detail;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.ble.data.manage.database.HealthSleep;
import com.ido.ble.data.manage.database.HealthSleepItem;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.NumUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.bean.ValueRangePair;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.RegularRadioButton;
import com.ido.life.customview.charter.CharterBar;
import com.ido.life.customview.charter.SleepBarChart;
import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.database.model.MultiDaysWalkTotalData;
import com.ido.life.database.model.ServerBloodOxyDayData;
import com.ido.life.database.model.ServerDaysBloodOxyData;
import com.ido.life.database.model.ServerDaysSleepData;
import com.ido.life.database.model.ServerMultiMonthBloodOxyTotalData;
import com.ido.life.database.model.ServerMultiMonthSleepTotalData;
import com.ido.life.database.model.ServerSleepDayData;
import com.ido.life.database.model.SleepDetailModel;
import com.ido.life.database.model.StepDayData;
import com.ido.life.database.model.WalkDayData;
import com.ido.life.database.model.WalkYearTotalData;
import com.ido.life.module.device.activity.BloodOxySettingActivity;
import com.ido.life.module.device.activity.HeartRateMonitoringActivity;
import com.ido.life.module.device.activity.ScientificSleepMonitoringActivity;
import com.ido.life.module.device.activity.WalkReminderActivity;
import com.ido.life.module.sport.bean.PieChartBean;
import com.ido.life.module.sport.view.SportPieChart;
import com.ido.life.module.user.set.target.SettingTargetActivity;
import com.ido.life.util.DateUtil;
import com.ido.life.util.HealthDataUtil;
import com.ido.life.util.RunTimeUtil;
import com.veryfit.multi.nativeprotocol.b;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* JADX INFO: loaded from: classes2.dex */
public class DetailActivity extends BaseActivity<DetailPresenter> implements RadioGroup.OnCheckedChangeListener, IDetailView, View.OnClickListener, CharterBar.OnItemBarTouchListener, SleepBarChart.OnItemSelectListener {
    public static final String DATA_TYPE = "data_type";
    private static final int REQUEST_CODE = 291;
    private static final String TAG = "DetailActivity";
    private int dateType;
    private Date firstDayOfSelectedMonth;
    private CharterBar mCharterBar;

    @BindView(R.id.divider_1)
    View mDivider1;

    @BindView(R.id.divider_2)
    View mDivider2;

    @BindView(R.id.divider_3)
    View mDivider3;

    @BindView(R.id.img_data_load_failed)
    ImageView mImgLoadFailed;

    @BindView(R.id.img_data_loading)
    ImageView mImgLoading;

    @BindView(R.id.iv_next)
    ImageView mIvNext;

    @BindView(R.id.iv_previous)
    ImageView mIvPrevious;
    private Date mLastDataDate;

    @BindView(R.id.lay_loading)
    LinearLayout mLayLoading;

    @BindView(R.id.layout_total_1)
    LinearLayout mLayTotalOne;
    private LinearLayout mLayoutHeartRateOnTip;
    private LinearLayout mLayoutSleep;
    private LinearLayout mLayoutSleepData;
    private LinearLayout mLayoutSleepRatioCard;

    @BindView(R.id.layout_total_data)
    LinearLayout mLayoutTotalData;

    @BindView(R.id.layout_total_2)
    LinearLayout mLayoutTotalTwo;
    private ValueAnimator mLoadingAnimator;

    @BindView(R.id.mtv_avg_data)
    MediumTextView mMtvAvgData;
    private TextView mMtvRespiratoryRate;
    private TextView mMtvRespiratoryRateTitle;
    private TextView mMtvScoreTitle;
    private TextView mMtvSleepScore;
    private MediumTextView mMtvSleepScoreDesc;
    private TextView mMtvSleepTime;
    private TextView mMtvSleepTimeTitle;

    @BindView(R.id.mtv_total_data)
    MediumTextView mMtvTotalData;

    @BindView(R.id.mtv_total_data2)
    MediumTextView mMtvTotalData2;
    private TextView mMtvWakeTime;
    private TextView mMtvWakeTimeTitle;
    private PagerAdapter mPagerAdapter;

    @BindView(R.id.rrb_day)
    RegularRadioButton mRadioDay;

    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;

    @BindView(R.id.radio_group_ground)
    RadioGroup mRadioGroupGround;

    @BindView(R.id.radio_group_top)
    RadioGroup mRadioGroupTop;

    @BindView(R.id.rrb_month)
    RegularRadioButton mRadioMonth;

    @BindView(R.id.rrb_week)
    RegularRadioButton mRadioWeek;

    @BindView(R.id.rrb_year)
    RegularRadioButton mRadioYear;

    @BindView(R.id.rb_ground_time_1)
    RadioButton mRbGroundTime1;

    @BindView(R.id.rb_ground_time_2)
    RadioButton mRbGroundTime2;

    @BindView(R.id.rb_ground_time_3)
    RadioButton mRbGroundTime3;

    @BindView(R.id.rb_top_time_1)
    RadioButton mRbTopTime1;

    @BindView(R.id.rb_top_time_2)
    RadioButton mRbTopTime2;

    @BindView(R.id.rb_top_time_3)
    RadioButton mRbTopTime3;
    private TableRow mRowRem;

    @BindView(R.id.rtv_avg_unit)
    RegularTextView mRtvAvgUnit;

    @BindView(R.id.rtv_date)
    RegularTextView mRtvDate;

    @BindView(R.id.rtv_total_title_1)
    RegularTextView mRtvTotalTitle1;

    @BindView(R.id.rtv_total_title_2)
    RegularTextView mRtvTotalTitle2;

    @BindView(R.id.rtv_total_unit)
    RegularTextView mRtvTotalUnit;

    @BindView(R.id.rtv_total_unit2)
    RegularTextView mRtvTotalUnit2;
    private SleepBarChart mSleepBarChart;
    private int[] mSleepColors;
    private SportPieChart mSpcPressure;
    private SportPieChart mSpcSleep;
    private TableRow mTrRespiratoryRate;
    private TextView mTvAwakeDuration;
    private TextView mTvAwakeRatio;

    @BindView(R.id.tv_data_loading_state)
    TextView mTvDataLoadState;
    private TextView mTvDeepSleepDuration;
    private TextView mTvDeepSleepRatio;
    private MediumTextView mTvHeartRateMonitoringOnTip;
    private TextView mTvLightSleepDuration;
    private TextView mTvLightSleepRatio;
    private TextView mTvRemDuration;
    private TextView mTvRemMark;
    private TextView mTvRemRatio;
    private int mType;

    @BindView(R.id.view_bottom)
    RelativeLayout mViewBottom;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private ViewStub mVsCommGraph;

    @BindView(R.id.vs_sleep)
    ViewStub mVsSleep;
    private ViewStub mVsSleepGraph;
    private Date selectedDate;
    private int selectedMonth;
    private int selectedYear;
    private Date startDateOfSelectedWeek;
    private int mViewPagerIndex = 1073741822;
    private int mCount = LockFreeTaskQueueCore.MAX_CAPACITY_MASK;
    private Map<Integer, View> mViewMap = new HashMap();

    private int getCriticalValue(int i) {
        if (i <= 80) {
            return 60;
        }
        return i <= 90 ? 80 : 90;
    }

    private void updateBreathTrainingView() {
    }

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_detail;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.dateType = 0;
        this.mType = getIntent().getIntExtra(DATA_TYPE, 0);
        CommonLogUtil.d(TAG, "initData: " + this.mType);
        ((DetailPresenter) this.mPresenter).setDataType(this.mType);
        this.selectedDate = DateUtil.getTodayDate();
        initLastDataDate();
    }

    private void initLastDataDate() {
        this.mLastDataDate = DateUtil.getTodayDate();
        int i = this.mType;
        if (i == 7) {
            ServerSleepDayData lastSleepData = ((DetailPresenter) this.mPresenter).getLastSleepData();
            if (lastSleepData != null) {
                this.selectedDate = DateUtil.string2Date(lastSleepData.getDate(), DateUtil.DATE_FORMAT_YMD);
            }
            this.mViewPagerIndex = (this.mCount - DateUtil.daysBetween(this.selectedDate, this.mLastDataDate)) - 1;
        } else if (i == 10) {
            ServerBloodOxyDayData lastBloodOxyDailyData = ((DetailPresenter) this.mPresenter).getLastBloodOxyDailyData();
            if (lastBloodOxyDailyData != null) {
                this.selectedDate = DateUtil.string2Date(lastBloodOxyDailyData.getDate(), DateUtil.DATE_FORMAT_YMD);
            }
            this.mViewPagerIndex = (this.mCount - DateUtil.daysBetween(this.selectedDate, this.mLastDataDate)) - 1;
        }
        int[] yearMonthDay = DateUtil.getYearMonthDay(this.selectedDate);
        this.selectedYear = yearMonthDay[0];
        this.selectedMonth = yearMonthDay[1];
        this.startDateOfSelectedWeek = ((DetailPresenter) this.mPresenter).getStartDateOfWeek(this.selectedDate);
        this.firstDayOfSelectedMonth = DateUtil.getFirstDayOfMonth(this.selectedDate);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        initTitleBarStyle();
        this.mRadioGroup.setOnCheckedChangeListener(this);
        updateDividerStatus();
        initViewPager();
        initLayout();
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mRadioDay.setText(getLanguageText(R.string.public_time_day));
        this.mRadioWeek.setText(getLanguageText(R.string.public_time_week));
        this.mRadioMonth.setText(getLanguageText(R.string.public_time_month));
        this.mRadioYear.setText(getLanguageText(R.string.public_time_year));
        initTitle();
        initUnit();
    }

    private void initLayout() {
        int i = this.mType;
        if (i != 7) {
            if (i == 10) {
                View viewInflate = getLayoutInflater().inflate(R.layout.bottom_instruction_for_blood_oxygen, (ViewGroup) null);
                this.mViewBottom.addView(viewInflate);
                this.mViewBottom.setVisibility(0);
                ((ImageView) viewInflate.findViewById(R.id.iv_to_detail_instruction)).setOnClickListener(this);
                return;
            }
            this.mRadioGroupGround.setVisibility(8);
            this.mRadioGroupTop.setVisibility(8);
            return;
        }
        initSleepTimeLayout();
        this.mSleepColors = new int[]{getColor(R.color.color_alive_sleep), getColor(R.color.color_sleep), getColor(R.color.color_deep_sleep), getColor(R.color.color_fast_sleep)};
        ViewStub viewStub = this.mVsSleep;
        if (viewStub != null) {
            viewStub.inflate();
        }
        this.mLayoutSleep = (LinearLayout) findViewById(R.id.layout_sleep_reference);
        this.mMtvSleepScore = (TextView) findViewById(R.id.mtv_sleep_score);
        this.mMtvSleepTime = (TextView) findViewById(R.id.mtv_sleep_time);
        this.mMtvWakeTime = (TextView) findViewById(R.id.mtv_wake_time);
        this.mMtvRespiratoryRate = (TextView) findViewById(R.id.mtv_respiratory_rate);
        this.mSpcSleep = (SportPieChart) findViewById(R.id.spc_sleep);
        this.mLayoutSleepData = (LinearLayout) findViewById(R.id.layout_sleep_data);
        this.mMtvScoreTitle = (TextView) findViewById(R.id.mtv_score_title);
        this.mMtvSleepTimeTitle = (TextView) findViewById(R.id.mtv_sleep_time_title);
        this.mMtvWakeTimeTitle = (TextView) findViewById(R.id.mtv_wake_time_title);
        this.mMtvRespiratoryRateTitle = (TextView) findViewById(R.id.mtv_respiratory_rate_title);
        this.mLayoutSleepRatioCard = (LinearLayout) findViewById(R.id.layout_sleep_ratio_card);
        this.mTvDeepSleepDuration = (TextView) findViewById(R.id.tv_deep_sleep_duration);
        this.mTvDeepSleepRatio = (TextView) findViewById(R.id.tv_deep_sleep_ratio);
        this.mTvLightSleepDuration = (TextView) findViewById(R.id.tv_light_sleep_duration);
        this.mTvLightSleepRatio = (TextView) findViewById(R.id.tv_light_sleep_ratio);
        this.mTvRemDuration = (TextView) findViewById(R.id.tv_rem_duration);
        this.mTvRemRatio = (TextView) findViewById(R.id.tv_rem_ratio);
        this.mTvAwakeDuration = (TextView) findViewById(R.id.tv_awake_duration);
        this.mTvAwakeRatio = (TextView) findViewById(R.id.tv_awake_ratio);
        this.mMtvTotalData2.setVisibility(0);
        this.mRtvTotalUnit2.setVisibility(0);
        findViewById(R.id.iv_breathing_quality).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.home.detail.-$$Lambda$DetailActivity$tq4Rqe_oQ6eyWcErD0IbMZ_ICds
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initLayout$0$DetailActivity(view);
            }
        });
        this.mMtvSleepScoreDesc = (MediumTextView) findViewById(R.id.mtv_sleep_score_desc);
        this.mLayoutHeartRateOnTip = (LinearLayout) findViewById(R.id.layout_heart_rate_on_tip);
        this.mTvHeartRateMonitoringOnTip = (MediumTextView) findViewById(R.id.mtv_heart_rate_monitoring_on_tip);
        setTipStatus();
        TextView textView = (TextView) findViewById(R.id.tv_deep_sleep_mark);
        TextView textView2 = (TextView) findViewById(R.id.tv_light_sleep_mark);
        this.mTvRemMark = (TextView) findViewById(R.id.tv_rem_mark);
        this.mRowRem = (TableRow) findViewById(R.id.row_rem);
        TextView textView3 = (TextView) findViewById(R.id.tv_awake_mark);
        int screenW = (int) (((ScreenUtil.getScreenW() - (getResources().getDimension(R.dimen.sw_dp_20) * 2.0f)) - (getResources().getDimension(R.dimen.sw_dp_16) * 3.0f)) / 4.0f);
        textView.setMaxWidth(screenW);
        textView2.setMaxWidth(screenW);
        this.mTvRemMark.setMaxWidth(screenW);
        textView3.setMaxWidth(screenW);
        this.mTrRespiratoryRate = (TableRow) findViewById(R.id.tr_respiratory_rate);
        ((DetailPresenter) this.mPresenter).getHeartRateMode();
    }

    public /* synthetic */ void lambda$initLayout$0$DetailActivity(View view) {
        startActivity(new Intent(this, (Class<?>) BreathingQualityActivity.class));
    }

    private void setTipStatus() {
        if (this.mType == 7) {
            final boolean zIsSupportScienceSleep = ((DetailPresenter) this.mPresenter).isSupportScienceSleep();
            this.mLayoutHeartRateOnTip.setVisibility((!zIsSupportScienceSleep || ((DetailPresenter) this.mPresenter).isScienceSleepOpen()) ? 8 : 0);
            this.mTvHeartRateMonitoringOnTip.setText(getLanguageText(zIsSupportScienceSleep ? R.string.health_science_sleep_switch_tip : R.string.detail_heart_rate_monitoring_on_tip));
            findViewById(R.id.mtv_to_open).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.home.detail.-$$Lambda$DetailActivity$UuyTgIVblt-fqeg78F0SgedDRws
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$setTipStatus$1$DetailActivity(zIsSupportScienceSleep, view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$setTipStatus$1$DetailActivity(boolean z, View view) {
        if (!((DetailPresenter) this.mPresenter).isConnected()) {
            showToast(getLanguageText(R.string.device_pls_connect_device));
        } else {
            startActivityForResult(new SingleTopIntent(this, (Class<?>) (z ? ScientificSleepMonitoringActivity.class : HeartRateMonitoringActivity.class)), REQUEST_CODE);
        }
    }

    private void initSleepTimeLayout() {
        this.mRadioGroupTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.ido.life.module.home.detail.-$$Lambda$DetailActivity$C0xEUPG0DOM16Wx6GpHB9VhDROE
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.lambda$initSleepTimeLayout$2$DetailActivity(radioGroup, i);
            }
        });
    }

    public /* synthetic */ void lambda$initSleepTimeLayout$2$DetailActivity(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_top_time_1 /* 2131363282 */:
                this.mRbGroundTime1.setChecked(true);
                ((DetailPresenter) this.mPresenter).switchSleepTime(0);
                break;
            case R.id.rb_top_time_2 /* 2131363283 */:
                this.mRbGroundTime2.setChecked(true);
                ((DetailPresenter) this.mPresenter).switchSleepTime(1);
                break;
            case R.id.rb_top_time_3 /* 2131363284 */:
                this.mRbGroundTime3.setChecked(true);
                ((DetailPresenter) this.mPresenter).switchSleepTime(2);
                break;
        }
    }

    private void initUnit() {
        int i = this.mType;
        if (i == 4) {
            this.mRtvTotalUnit.setText(getLanguageText(R.string.public_time_hour));
            this.mRtvAvgUnit.setText(getLanguageText(R.string.public_time_hour));
        }
        if (i != 13) {
            switch (i) {
                case 7:
                    this.mRtvTotalUnit.setText(getLanguageText(R.string.public_time_hour));
                    this.mRtvTotalUnit2.setText(getLanguageText(R.string.unit_min));
                    break;
                case 8:
                    this.mRtvTotalUnit.setText(getLanguageText(R.string.public_heartbeat_unit));
                    break;
                case 10:
                    this.mRtvTotalUnit.setText("%");
                    this.mRtvAvgUnit.setText("%");
                    break;
            }
        }
    }

    private void initViewPager() {
        this.mPagerAdapter = new PagerAdapter() { // from class: com.ido.life.module.home.detail.DetailActivity.1
            @Override // androidx.viewpager.widget.PagerAdapter
            public int getItemPosition(Object obj) {
                return -2;
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public boolean isViewFromObject(View view, Object obj) {
                return view == obj;
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public int getCount() {
                return DetailActivity.this.mCount;
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public Object instantiateItem(ViewGroup viewGroup, int i) {
                CommonLogUtil.d("initViewPager instantiateItem: " + i);
                View viewInflate = View.inflate(DetailActivity.this, R.layout.item_pager_bar_data, null);
                viewInflate.setTag(Integer.valueOf(i));
                if (i == DetailActivity.this.mViewPagerIndex) {
                    DetailActivity.this.initItemView(viewInflate);
                    DetailActivity.this.updateDataView();
                } else {
                    DetailActivity.this.initDefaultView(viewInflate);
                }
                viewGroup.addView(viewInflate);
                DetailActivity.this.mViewMap.put(Integer.valueOf(i), viewInflate);
                return viewInflate;
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
                viewGroup.removeView((View) obj);
                DetailActivity.this.mViewMap.remove(Integer.valueOf(i));
            }
        };
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mViewPager.setOffscreenPageLimit(1);
        this.mViewPager.setCurrentItem(this.mViewPagerIndex);
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.ido.life.module.home.detail.DetailActivity.2
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f2, int i2) {
                CommonLogUtil.d("onPageScrolled position = " + i);
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                CommonLogUtil.d(DetailActivity.TAG, "initViewPager onPageSelected: " + DetailActivity.this.mViewPagerIndex + AppInfo.DELIM + i);
                View view = (View) DetailActivity.this.mViewMap.get(Integer.valueOf(i));
                if (view != null) {
                    DetailActivity.this.initItemView(view);
                }
                if (DetailActivity.this.mViewPagerIndex < i) {
                    DetailActivity.this.check2Next();
                } else {
                    DetailActivity.this.check2Previous();
                }
                DetailActivity.this.mViewPagerIndex = i;
            }
        });
        if (this.mType == 7) {
            ViewGroup.LayoutParams layoutParams = this.mViewPager.getLayoutParams();
            layoutParams.height = (int) getResources().getDimension(R.dimen.sw_dp_370);
            this.mViewPager.setLayoutParams(layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDefaultView(View view) {
        CharterBar charterBar;
        ViewStub viewStub = (ViewStub) view.findViewById(R.id.vs_comm_graph);
        ViewStub viewStub2 = (ViewStub) view.findViewById(R.id.vs_sleep_graph);
        SleepBarChart sleepBarChart = null;
        if (this.mType == 7) {
            if (viewStub2 != null) {
                viewStub2.inflate();
            }
            sleepBarChart = (SleepBarChart) view.findViewById(R.id.charter_sleep);
            charterBar = null;
        } else {
            if (viewStub != null) {
                viewStub.inflate();
            }
            charterBar = (CharterBar) view.findViewById(R.id.charter_bar);
        }
        initDataLabel(charterBar);
        updateGraphStyleByDateType(charterBar, sleepBarChart);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initItemView(View view) {
        this.mVsCommGraph = (ViewStub) view.findViewById(R.id.vs_comm_graph);
        this.mVsSleepGraph = (ViewStub) view.findViewById(R.id.vs_sleep_graph);
        initLayoutInflate(view);
        initDataLabel(this.mCharterBar);
        updateGraphStyleByDateType(this.mCharterBar, this.mSleepBarChart);
    }

    private void updateGraphStyleByDateType(CharterBar charterBar, SleepBarChart sleepBarChart) {
        int i = this.dateType;
        if (i == 0) {
            if (charterBar != null) {
                charterBar.setBottomLabelAlignBar(false);
                charterBar.setTimeType(0);
                charterBar.setBottomLabels(Constants.LABELS_DAY);
                charterBar.setValues(HealthDataUtil.getDefaultValues(24));
            }
            if (sleepBarChart != null) {
                sleepBarChart.setDataTimeType(0);
                return;
            }
            return;
        }
        if (i == 1) {
            if (charterBar != null) {
                charterBar.setBottomLabelAlignBar(true);
                charterBar.setTimeType(1);
                charterBar.setBottomLabels(convertWeekStrByWeekStartDay());
                charterBar.setValues(HealthDataUtil.getDefaultValues(7));
            }
            if (sleepBarChart != null) {
                sleepBarChart.setDataTimeType(1);
                sleepBarChart.setBottomLabels(convertWeekStrByWeekStartDay());
                return;
            }
            return;
        }
        if (i == 2) {
            if (charterBar != null) {
                charterBar.setBottomLabelAlignBar(true);
                charterBar.setTimeType(2);
                charterBar.setBottomLabels(((DetailPresenter) this.mPresenter).getMonthLabels(this.firstDayOfSelectedMonth));
                charterBar.setValues(HealthDataUtil.getDefaultValues(DateUtil.getDaysOfMonth(this.firstDayOfSelectedMonth)));
            }
            if (sleepBarChart != null) {
                sleepBarChart.setDataTimeType(2);
                sleepBarChart.setBottomLabels(((DetailPresenter) this.mPresenter).getMonthLabels(this.firstDayOfSelectedMonth));
                return;
            }
            return;
        }
        if (i != 3) {
            return;
        }
        if (charterBar != null) {
            charterBar.setSelectedYear(this.selectedYear);
            charterBar.setBottomLabelAlignBar(true);
            charterBar.setTimeType(3);
            charterBar.setBottomLabels(Constants.LABELS_YEAR);
            charterBar.setValues(HealthDataUtil.getDefaultValues(12));
        }
        if (sleepBarChart != null) {
            sleepBarChart.setDataTimeType(3);
            sleepBarChart.setBottomLabels(Constants.LABELS_YEAR);
            sleepBarChart.setSelectedYear(this.selectedYear);
            sleepBarChart.setOverlay(true);
            sleepBarChart.invalidate();
        }
    }

    private void initTitleBarStyle() {
        int i = this.mType;
        if (i == 4) {
            if (((DetailPresenter) this.mPresenter).isSupportWalkReminder()) {
                this.mTitleBar.initLayout(1).setRightImg(R.mipmap.icon_setting).setRightOnClick(this);
                return;
            } else {
                this.mTitleBar.initLayout(0);
                return;
            }
        }
        if (i != 10) {
            if (i == 11) {
                this.mTitleBar.initLayout(1).setRightImg(R.mipmap.icon_setting).setRightOnClick(this);
                return;
            } else {
                this.mTitleBar.initLayout(0);
                return;
            }
        }
        if (((DetailPresenter) this.mPresenter).supportBloodDetection()) {
            this.mTitleBar.initLayout(1).setRightImg(R.mipmap.icon_setting).setRightOnClick(this);
        } else {
            this.mTitleBar.initLayout(0);
        }
    }

    private void initLayoutInflate(View view) {
        int i = this.mType;
        if (i == 7) {
            ViewStub viewStub = this.mVsSleepGraph;
            if (viewStub != null) {
                viewStub.inflate();
            }
            this.mSleepBarChart = (SleepBarChart) view.findViewById(R.id.charter_sleep);
        } else if (i == 9) {
            ViewStub viewStub2 = this.mVsCommGraph;
            if (viewStub2 != null) {
                viewStub2.inflate();
            }
            this.mCharterBar = (CharterBar) view.findViewById(R.id.charter_bar);
            this.mSpcPressure = (SportPieChart) findViewById(R.id.spc_pressure);
            initPressurePieView();
        } else {
            ViewStub viewStub3 = this.mVsCommGraph;
            if (viewStub3 != null) {
                viewStub3.inflate();
            }
            this.mCharterBar = (CharterBar) view.findViewById(R.id.charter_bar);
        }
        CharterBar charterBar = this.mCharterBar;
        if (charterBar != null) {
            charterBar.isAddClickZone(true);
            this.mCharterBar.setOnItemBarTouchListener(this);
        }
        SleepBarChart sleepBarChart = this.mSleepBarChart;
        if (sleepBarChart != null) {
            sleepBarChart.setOnItemSelectListener(this);
        }
    }

    private void initPressurePieView() {
        if (this.mSpcPressure == null) {
            return;
        }
        int[] iArr = {getColor(R.color.color_4A9AEF), getColor(R.color.color_FE9C5E), getColor(R.color.color_FC5D68), getColor(R.color.color_67DAEC)};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < iArr.length; i++) {
            PieChartBean pieChartBean = new PieChartBean();
            pieChartBean.setColor(iArr[i]);
            if (i < 2) {
                pieChartBean.setValue(30.0f);
            } else {
                pieChartBean.setValue(20.0f);
            }
            arrayList.add(pieChartBean);
        }
        this.mSpcPressure.setData(arrayList);
    }

    private void updateChart() {
        CharterBar charterBar = this.mCharterBar;
        if (charterBar == null) {
            return;
        }
        int i = this.dateType;
        if (i == 1) {
            charterBar.setBottomLabels(convertWeekStrByWeekStartDay());
        } else if (i == 2) {
            charterBar.setBottomLabels(((DetailPresenter) this.mPresenter).getMonthLabels(this.firstDayOfSelectedMonth));
        } else {
            if (i != 3) {
                return;
            }
            charterBar.setBottomLabels(Constants.LABELS_YEAR);
        }
    }

    private void initDataLabel(CharterBar charterBar) {
        int i = this.mType;
        if (i == 4) {
            if (charterBar != null) {
                charterBar.setBarColor(R.color.color_991BBADE);
                charterBar.setType(4);
                charterBar.setTargetBarColor(R.color.color_1BBADE);
                charterBar.setDefaultBarColor(R.color.color_ECECF2);
            }
            return;
        }
        if (i == 13) {
            if (charterBar != null) {
                charterBar.setUnit(getLanguageText(R.string.unit_min));
                charterBar.setHasLeftLabel(false);
                charterBar.setDefaultMaxValue(5);
                charterBar.setIncreaseAmount(5);
                charterBar.setBarColor(R.color.color_5AC8FA);
                return;
            }
            return;
        }
        switch (i) {
            case 8:
                if (charterBar != null) {
                    charterBar.setIsValuePair(true);
                    charterBar.setBarDefaultHeight(0);
                    charterBar.setUnit(getLanguageText(R.string.public_heartbeat_unit));
                    charterBar.setDefaultMaxValue(b.Y);
                    charterBar.setIncreaseAmount(50);
                    charterBar.setDividerCount(3);
                    charterBar.setAutoIncrementDividerCount(true);
                    charterBar.setBarColor(R.color.color_F44452);
                }
                break;
            case 9:
                if (charterBar != null) {
                    charterBar.setIsValuePair(true);
                    charterBar.setBarDefaultHeight(0);
                    charterBar.setDividerCount(2);
                    charterBar.setTopDividerVisible(false);
                    charterBar.setBarColor(R.color.color_F44452);
                }
                break;
            case 10:
                if (charterBar != null) {
                    charterBar.setUnit("%");
                    charterBar.setIsValuePair(true);
                    charterBar.setBarDefaultHeight(0);
                    charterBar.setDefaultMaxValue(100);
                    charterBar.setIncreaseAmount(10);
                    charterBar.setCriticalValue(90);
                    charterBar.setBarColor(R.color.color_33E9F2);
                    charterBar.setType(10);
                    charterBar.setDividerCount(2);
                }
                break;
        }
    }

    private void initTitle() {
        int i = this.mType;
        if (i == 3) {
            this.mTitleBar.setTitle(getLanguageText(R.string.home_card_activity_stronger_walk));
            return;
        }
        if (i == 4) {
            this.mTitleBar.setTitle(getLanguageText(R.string.detail_walk_hour));
            return;
        }
        if (i != 13) {
            switch (i) {
                case 7:
                    this.mTitleBar.setTitle(getLanguageText(R.string.home_card_sleep_title));
                    break;
                case 8:
                    this.mTitleBar.setTitle(getLanguageText(R.string.home_card_heartbeat_title));
                    this.mRtvTotalTitle1.setText(getLanguageText(R.string.home_detail_ave_ios));
                    break;
                case 9:
                    this.mTitleBar.setTitle(getLanguageText(R.string.home_pressure_title));
                    break;
                case 10:
                    this.mTitleBar.setTitle(getLanguageText(R.string.home_card_blood_oxygen));
                    this.mLayoutTotalTwo.setVisibility(0);
                    this.mRtvTotalTitle1.setText(getLanguageText(R.string.detail_lowest));
                    this.mRtvTotalTitle2.setText(getLanguageText(R.string.detail_highest));
                    break;
            }
            return;
        }
        this.mTitleBar.setTitle(getLanguageText(R.string.home_breath_title));
    }

    private void updateLayoutDisplay() {
        int i = this.mType;
        if (i == 4) {
            updateWalkHourView();
            return;
        }
        if (i != 13) {
            switch (i) {
                case 7:
                    updateSleepView();
                    break;
                case 8:
                    updateHeartRateView();
                    break;
                case 9:
                    updatePressureView();
                    break;
                case 10:
                    updateBloodOxygenView();
                    break;
            }
            return;
        }
        updateBreathTrainingView();
    }

    private void updateWalkHourView() {
        CharterBar charterBar;
        int i = this.dateType;
        if (i == 0) {
            CharterBar charterBar2 = this.mCharterBar;
            if (charterBar2 != null) {
                charterBar2.setHasLeftLabel(false);
                this.mCharterBar.setDividerCount(0);
                this.mCharterBar.setDefaultMaxValue(2);
                this.mCharterBar.setBarAndMarginWidthWeight(3, 1);
                return;
            }
            return;
        }
        if ((i == 1 || i == 2 || i == 3) && (charterBar = this.mCharterBar) != null) {
            charterBar.setHasLeftLabel(true);
            this.mCharterBar.setDividerCount(2);
            this.mCharterBar.setDefaultMaxValue(24);
            this.mCharterBar.setTargetValue(((DetailPresenter) this.mPresenter).getWalkTargetHour(), false);
            this.mCharterBar.setUnit(getLanguageText(R.string.public_time_hour));
        }
    }

    private void updateBloodOxygenView() {
        CharterBar charterBar;
        int i = this.dateType;
        if (i == 0) {
            CharterBar charterBar2 = this.mCharterBar;
            if (charterBar2 != null) {
                charterBar2.setBarAndMarginWidthWeight(1, 1);
                return;
            }
            return;
        }
        if (i == 1) {
            CharterBar charterBar3 = this.mCharterBar;
            if (charterBar3 != null) {
                charterBar3.setBarAndMarginWidthWeight(1, 4);
                return;
            }
            return;
        }
        if ((i == 2 || i == 3) && (charterBar = this.mCharterBar) != null) {
            charterBar.setBarAndMarginWidthWeight(1, 2);
        }
    }

    private void updatePressureView() {
        int i = this.dateType;
        if (i != 0) {
            if (i == 1) {
                CharterBar charterBar = this.mCharterBar;
                if (charterBar != null) {
                    charterBar.setBarAndMarginWidthWeight(1, 4);
                    return;
                }
                return;
            }
            if (i != 2 && i != 3) {
                return;
            }
        }
        CharterBar charterBar2 = this.mCharterBar;
        if (charterBar2 != null) {
            charterBar2.setBarAndMarginWidthWeight(1, 2);
        }
    }

    private void updateSleepView() {
        this.mRbTopTime1.setChecked(true);
        this.mRbGroundTime1.setChecked(true);
        int i = this.dateType;
        if (i == 0) {
            SleepBarChart sleepBarChart = this.mSleepBarChart;
            if (sleepBarChart != null) {
                sleepBarChart.setOverlay(false);
            }
            this.mMtvScoreTitle.setText(getLanguageText(R.string.detail_sleep_score));
            this.mMtvSleepTimeTitle.setText(getLanguageText(R.string.detail_sleep_time));
            this.mMtvWakeTimeTitle.setText(getLanguageText(R.string.detail_wake_time));
            this.mMtvRespiratoryRateTitle.setText(getLanguageText(R.string.detail_breathing_quality));
            return;
        }
        if (i != 1 && i != 2) {
            if (i != 3) {
                return;
            }
            SleepBarChart sleepBarChart2 = this.mSleepBarChart;
            if (sleepBarChart2 != null) {
                sleepBarChart2.setOverlay(true);
            }
            this.mLayoutSleepData.setVisibility(8);
            return;
        }
        SleepBarChart sleepBarChart3 = this.mSleepBarChart;
        if (sleepBarChart3 != null) {
            sleepBarChart3.setOverlay(true);
        }
        this.mMtvScoreTitle.setText(getLanguageText(R.string.detail_avg_sleep_score));
        this.mMtvSleepTimeTitle.setText(getLanguageText(R.string.detail_avg_sleep_time));
        this.mMtvWakeTimeTitle.setText(getLanguageText(R.string.detail_avg_wake_time));
        this.mMtvRespiratoryRateTitle.setText(getLanguageText(R.string.detail_avg_breathing_quality));
    }

    private void updateHeartRateView() {
        int i = this.dateType;
        if (i != 0) {
            if (i == 1) {
                CharterBar charterBar = this.mCharterBar;
                if (charterBar != null) {
                    charterBar.setBarAndMarginWidthWeight(1, 4);
                    return;
                }
                return;
            }
            if (i != 2 && i != 3) {
                return;
            }
        }
        CharterBar charterBar2 = this.mCharterBar;
        if (charterBar2 != null) {
            charterBar2.setBarAndMarginWidthWeight(1, 2);
        }
    }

    private String[] convertWeekStrByWeekStartDay() {
        int[] iArr;
        int weekStart = RunTimeUtil.getInstance().getWeekStart();
        if (weekStart == 2) {
            iArr = Constants.WEEK_START_MONDAY;
        } else if (weekStart == 7) {
            iArr = Constants.WEEK_START_SATURDAY;
        } else {
            iArr = Constants.WEEK_START_SUNDAY;
        }
        int length = iArr.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = LanguageUtil.getLanguageText(iArr[i]);
        }
        return strArr;
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int iDaysBetween = 0;
        switch (i) {
            case R.id.rrb_day /* 2131363367 */:
                this.dateType = 0;
                iDaysBetween = DateUtil.daysBetween(this.selectedDate, this.mLastDataDate);
                break;
            case R.id.rrb_month /* 2131363368 */:
                this.dateType = 2;
                this.mRadioGroupGround.setVisibility(8);
                this.mRadioGroupTop.setVisibility(8);
                int[] yearMonthDay = DateUtil.getYearMonthDay(this.mLastDataDate);
                int[] yearMonthDay2 = DateUtil.getYearMonthDay(this.firstDayOfSelectedMonth);
                iDaysBetween = (((yearMonthDay[0] - yearMonthDay2[0]) * 12) + yearMonthDay[1]) - yearMonthDay2[1];
                break;
            case R.id.rrb_week /* 2131363369 */:
                this.dateType = 1;
                this.mRadioGroupGround.setVisibility(8);
                this.mRadioGroupTop.setVisibility(8);
                iDaysBetween = DateUtil.daysBetween(this.startDateOfSelectedWeek, this.mLastDataDate) / 7;
                break;
            case R.id.rrb_year /* 2131363370 */:
                this.dateType = 3;
                this.mRadioGroupGround.setVisibility(8);
                this.mRadioGroupTop.setVisibility(8);
                iDaysBetween = DateUtil.getYearMonthDay(this.mLastDataDate)[0] - this.selectedYear;
                break;
        }
        updateTotalDataLayoutStatus(-1);
        updateDividerStatus();
        ((DetailPresenter) this.mPresenter).setTimeType(this.dateType);
        this.mCount = this.mViewPagerIndex + 1 + iDaysBetween;
        this.mPagerAdapter.notifyDataSetChanged();
    }

    private void updateDividerStatus() {
        this.mDivider1.setVisibility(0);
        this.mDivider2.setVisibility(0);
        this.mDivider3.setVisibility(0);
        int i = this.dateType;
        if (i == 0) {
            this.mDivider1.setVisibility(4);
            return;
        }
        if (i == 1) {
            this.mDivider1.setVisibility(4);
            this.mDivider2.setVisibility(4);
        } else if (i == 2) {
            this.mDivider2.setVisibility(4);
            this.mDivider3.setVisibility(4);
        } else {
            if (i != 3) {
                return;
            }
            this.mDivider3.setVisibility(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDataView() {
        updateTotalDataLayout();
        updateLayoutDisplay();
        requestData();
    }

    private void updateTotalDataLayout() {
        updateDisplayStatus();
        updateDataTitle();
        updateDate();
    }

    private void updateDisplayStatus() {
        if (this.mType != 4) {
            return;
        }
        if (this.dateType == 0) {
            this.mLayoutTotalTwo.setVisibility(8);
        } else {
            this.mLayoutTotalTwo.setVisibility(0);
        }
    }

    private void updateDataTitle() {
        if (this.mType != 7) {
            return;
        }
        if (this.dateType == 0) {
            this.mRtvTotalTitle1.setText(R.string.total);
        } else {
            this.mRtvTotalTitle1.setText(R.string.home_detail_ave_ios);
        }
    }

    private void updateDate() {
        int i = this.dateType;
        if (i == 0) {
            this.mRtvDate.setText(DateUtil.format(this.selectedDate, DateUtil.DATE_FORMAT_DMY_1));
            return;
        }
        if (i == 1) {
            this.mRtvDate.setText(String.format("%s-%s", DateUtil.format(this.startDateOfSelectedWeek, DateUtil.DATE_FORMAT_DMY_1), DateUtil.format(DateUtil.getSpecifiedDayBefore(this.startDateOfSelectedWeek, -6), DateUtil.DATE_FORMAT_DM_1)));
        } else if (i == 2) {
            this.mRtvDate.setText(String.format("%s-%s", DateUtil.format(this.firstDayOfSelectedMonth, DateUtil.DATE_FORMAT_DMY_1), DateUtil.format(DateUtil.getLastDayOfMonth(this.firstDayOfSelectedMonth), DateUtil.DATE_FORMAT_DM_1)));
        } else {
            if (i != 3) {
                return;
            }
            this.mRtvDate.setText(String.format("%s-%s", DateUtil.getFirstMonthStr(this.selectedYear), DateUtil.getLastMonthStr(this.selectedYear)));
        }
    }

    private void requestData() {
        CommonLogUtil.d(TAG, "开始请求数据");
        ((DetailPresenter) this.mPresenter).removeListener();
        showLoadSuccessUI();
        int i = this.dateType;
        if (i == 0) {
            ((DetailPresenter) this.mPresenter).requestDayData(this.selectedDate);
            return;
        }
        if (i == 1) {
            ((DetailPresenter) this.mPresenter).requestWeekData(this.startDateOfSelectedWeek);
        } else if (i == 2) {
            ((DetailPresenter) this.mPresenter).requestMonthData(this.firstDayOfSelectedMonth);
        } else {
            if (i != 3) {
                return;
            }
            ((DetailPresenter) this.mPresenter).requestYearData(this.selectedYear);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void check2Next() {
        int i = this.dateType;
        if (i == 0) {
            this.selectedDate = DateUtil.getSpecifiedDayBefore(this.selectedDate, -1);
        } else if (i == 1) {
            this.startDateOfSelectedWeek = DateUtil.getSpecifiedDayBefore(this.startDateOfSelectedWeek, -7);
        } else if (i == 2) {
            this.selectedMonth++;
            this.firstDayOfSelectedMonth = DateUtil.getFirstDayOfNextMonth(this.firstDayOfSelectedMonth);
            CharterBar charterBar = this.mCharterBar;
            if (charterBar != null) {
                charterBar.setBottomLabels(((DetailPresenter) this.mPresenter).getMonthLabels(this.firstDayOfSelectedMonth));
            }
            SleepBarChart sleepBarChart = this.mSleepBarChart;
            if (sleepBarChart != null) {
                sleepBarChart.setBottomLabels(((DetailPresenter) this.mPresenter).getMonthLabels(this.firstDayOfSelectedMonth));
            }
        } else if (i == 3) {
            this.selectedYear++;
            CharterBar charterBar2 = this.mCharterBar;
            if (charterBar2 != null) {
                charterBar2.setSelectedYear(this.selectedYear);
                this.mCharterBar.invalidate();
            }
            SleepBarChart sleepBarChart2 = this.mSleepBarChart;
            if (sleepBarChart2 != null) {
                sleepBarChart2.setSelectedYear(this.selectedYear);
                this.mSleepBarChart.invalidate();
            }
        }
        updateDataView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void check2Previous() {
        int i = this.dateType;
        if (i == 0) {
            this.selectedDate = DateUtil.getSpecifiedDayBefore(this.selectedDate, 1);
        } else if (i == 1) {
            this.startDateOfSelectedWeek = DateUtil.getSpecifiedDayBefore(this.startDateOfSelectedWeek, 7);
        } else if (i == 2) {
            this.selectedMonth--;
            this.firstDayOfSelectedMonth = DateUtil.getFirstDayOfLastMonth(this.firstDayOfSelectedMonth);
            CharterBar charterBar = this.mCharterBar;
            if (charterBar != null) {
                charterBar.setBottomLabels(((DetailPresenter) this.mPresenter).getMonthLabels(this.firstDayOfSelectedMonth));
            }
            SleepBarChart sleepBarChart = this.mSleepBarChart;
            if (sleepBarChart != null) {
                sleepBarChart.setBottomLabels(((DetailPresenter) this.mPresenter).getMonthLabels(this.firstDayOfSelectedMonth));
            }
        } else if (i == 3) {
            this.selectedYear--;
            CharterBar charterBar2 = this.mCharterBar;
            if (charterBar2 != null) {
                charterBar2.setSelectedYear(this.selectedYear);
            }
            SleepBarChart sleepBarChart2 = this.mSleepBarChart;
            if (sleepBarChart2 != null) {
                sleepBarChart2.setSelectedYear(this.selectedYear);
            }
        }
        updateDataView();
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onNeedTurnedOnHeartRateMeasurement(boolean z) {
        this.mLayoutHeartRateOnTip.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetDayStep(StepDayData stepDayData, float[] fArr) {
        dismissLoadingDialog();
        CharterBar charterBar = this.mCharterBar;
        if (charterBar == null) {
            return;
        }
        charterBar.setValues(fArr);
        if (stepDayData == null) {
            this.mMtvTotalData.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        } else {
            this.mMtvTotalData.setText(String.valueOf(stepDayData.getNumSteps()));
        }
        this.mLayoutTotalTwo.setVisibility(8);
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetMultiDaysStep(int i, int i2, float[] fArr) {
        setMultiDaysData2CharterBar(fArr);
        this.mMtvTotalData.setText(String.valueOf(i));
        this.mLayoutTotalTwo.setVisibility(0);
        this.mMtvAvgData.setText(String.valueOf(i2));
        this.mRtvAvgUnit.setText(getLanguageText(R.string.public_sport_step));
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetYearStepData(int i, int i2, float[] fArr) {
        setYearData2CharterBar(fArr);
        this.mMtvTotalData.setText(String.valueOf(i));
        this.mLayoutTotalTwo.setVisibility(0);
        this.mMtvAvgData.setText(String.valueOf(i2));
        this.mRtvAvgUnit.setText(getLanguageText(R.string.public_sport_step));
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetDaySleepData(HealthSleep healthSleep, List<HealthSleepItem> list, ServerSleepDayData serverSleepDayData) {
        dismissLoadingDialog();
        SleepBarChart sleepBarChart = this.mSleepBarChart;
        if (sleepBarChart != null) {
            sleepBarChart.setDatas(healthSleep, list);
        }
        if (serverSleepDayData == null || serverSleepDayData.getTotalSeconds() <= 0) {
            this.mMtvTotalData.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            this.mRtvTotalUnit.setVisibility(8);
            this.mRtvTotalUnit2.setVisibility(8);
            this.mMtvTotalData2.setVisibility(8);
            this.mMtvSleepScore.setText("--");
            this.mMtvSleepTime.setText("--");
            this.mMtvWakeTime.setText("--");
            this.mMtvRespiratoryRate.setText("--");
            this.mLayoutSleepRatioCard.setVisibility(8);
            this.mLayoutSleepData.setVisibility(8);
            this.mTvRemMark.setVisibility(8);
            return;
        }
        this.mRtvTotalUnit.setVisibility(0);
        this.mRtvTotalUnit2.setVisibility(0);
        this.mMtvTotalData2.setVisibility(0);
        int totalSeconds = serverSleepDayData.getTotalSeconds() / 60;
        this.mMtvTotalData.setText(String.valueOf(totalSeconds / 60));
        this.mMtvTotalData2.setText(String.valueOf(totalSeconds % 60));
        this.mMtvSleepScore.setText(serverSleepDayData.getScore() == 0 ? "--" : String.valueOf(serverSleepDayData.getScore()));
        this.mMtvSleepTime.setText(HealthDataUtil.getSleepTime(healthSleep));
        this.mMtvWakeTime.setText(String.format("%s:%s", NumUtil.numberFormat(healthSleep.sleepEndedTimeH), NumUtil.numberFormat(healthSleep.sleepEndedTimeM)));
        this.mMtvRespiratoryRate.setText(String.valueOf(serverSleepDayData.getBreathRate() != 0 ? Integer.valueOf(serverSleepDayData.getBreathRate()) : "--"));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PieChartBean(this.mSleepColors[2], serverSleepDayData.getDeeplyRatio()));
        arrayList.add(new PieChartBean(this.mSleepColors[3], serverSleepDayData.getEyeMovementRatio()));
        arrayList.add(new PieChartBean(this.mSleepColors[0], serverSleepDayData.getAwakeRatio()));
        arrayList.add(new PieChartBean(this.mSleepColors[1], serverSleepDayData.getLightlyRatio()));
        int deeplySeconds = serverSleepDayData.getDeeplySeconds() / 60;
        int lightlySeconds = serverSleepDayData.getLightlySeconds() / 60;
        int awakeSeconds = serverSleepDayData.getAwakeSeconds() / 60;
        int eyeMovementSeconds = serverSleepDayData.getEyeMovementSeconds() / 60;
        bindSleepData2View(this.mTvDeepSleepDuration, this.mTvDeepSleepRatio, deeplySeconds, serverSleepDayData.getDeeplyRatio());
        bindSleepData2View(this.mTvLightSleepDuration, this.mTvLightSleepRatio, lightlySeconds, serverSleepDayData.getLightlyRatio());
        bindSleepData2View(this.mTvRemDuration, this.mTvRemRatio, eyeMovementSeconds, serverSleepDayData.getEyeMovementRatio());
        bindSleepData2View(this.mTvAwakeDuration, this.mTvAwakeRatio, awakeSeconds, serverSleepDayData.getAwakeRatio());
        this.mSpcSleep.setData(arrayList);
        this.mLayoutSleepRatioCard.setVisibility(0);
        this.mLayoutSleepData.setVisibility(0);
        setSleepScoreDesc(serverSleepDayData.getScore());
        if (serverSleepDayData.supportEyeMovement()) {
            setScienceSleepLayoutVisible(true);
        } else {
            setScienceSleepLayoutVisible(false);
        }
    }

    private void setScienceSleepLayoutVisible(boolean z) {
        if (z) {
            this.mTvRemMark.setVisibility(0);
            this.mRowRem.setVisibility(0);
            this.mTrRespiratoryRate.setVisibility(0);
        } else {
            this.mTvRemMark.setVisibility(8);
            this.mRowRem.setVisibility(8);
            this.mTrRespiratoryRate.setVisibility(8);
        }
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetDaySleepList(List<ServerSleepDayData> list) {
        if (list == null || list.size() <= 1) {
            this.mRadioGroupTop.setVisibility(8);
            this.mRadioGroupGround.setVisibility(8);
            this.mRbGroundTime1.setVisibility(8);
            this.mRbGroundTime2.setVisibility(8);
            this.mRbGroundTime3.setVisibility(8);
            this.mRbTopTime1.setVisibility(8);
            this.mRbTopTime2.setVisibility(8);
            this.mRbTopTime3.setVisibility(8);
            return;
        }
        this.mRadioGroupTop.setVisibility(0);
        this.mRadioGroupGround.setVisibility(0);
        if (list.size() == 2) {
            this.mRbGroundTime3.setVisibility(4);
            this.mRbTopTime3.setVisibility(4);
            this.mRbGroundTime1.setVisibility(0);
            this.mRbGroundTime2.setVisibility(0);
            this.mRbTopTime1.setVisibility(0);
            this.mRbTopTime2.setVisibility(0);
        } else {
            this.mRbGroundTime1.setVisibility(0);
            this.mRbGroundTime2.setVisibility(0);
            this.mRbGroundTime3.setVisibility(0);
            this.mRbTopTime1.setVisibility(0);
            this.mRbTopTime2.setVisibility(0);
            this.mRbTopTime3.setVisibility(0);
        }
        for (int i = 0; i < list.size(); i++) {
            ServerSleepDayData serverSleepDayData = list.get(i);
            String str = DateUtil.format(DateUtil.string2Date(serverSleepDayData.getStartTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_Hm);
            String str2 = DateUtil.format(DateUtil.string2Date(serverSleepDayData.getEndTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_Hm);
            if (i == 0) {
                this.mRbGroundTime1.setText(String.format("%1$s-%2$S", str, str2));
                this.mRbTopTime1.setText(String.format("%1$s-%2$S", str, str2));
            } else if (i == 1) {
                this.mRbGroundTime2.setText(String.format("%1$s-%2$S", str, str2));
                this.mRbTopTime2.setText(String.format("%1$s-%2$S", str, str2));
            } else {
                this.mRbGroundTime3.setText(String.format("%1$s-%2$S", str, str2));
                this.mRbTopTime3.setText(String.format("%1$s-%2$S", str, str2));
                return;
            }
        }
    }

    private void bindSleepData2View(TextView textView, TextView textView2, int i, int i2) {
        int i3 = i / 60;
        int i4 = i % 60;
        String strConcat = i3 > 0 ? "".concat(String.valueOf(i3)).concat(getLanguageText(R.string.public_time_hour)) : "";
        if (i4 > 0) {
            strConcat = strConcat.concat(String.valueOf(i4)).concat(getLanguageText(R.string.unit_min));
        }
        if (TextUtils.isEmpty(strConcat)) {
            strConcat = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
        }
        textView.setText(strConcat);
        textView2.setText(String.format(getString(R.string.device_x_percent), Integer.valueOf(i2)));
    }

    private void setSleepScoreDesc(int i) {
        if (i >= 86) {
            this.mMtvSleepScoreDesc.setText(getLanguageText(R.string.detail_sleep_tip_1));
            return;
        }
        if (i >= 76) {
            this.mMtvSleepScoreDesc.setText(getLanguageText(R.string.detail_sleep_tip_2));
        } else if (i >= 66) {
            this.mMtvSleepScoreDesc.setText(getLanguageText(R.string.detail_sleep_tip_3));
        } else {
            this.mMtvSleepScoreDesc.setText(getLanguageText(R.string.detail_sleep_tip_4));
        }
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetMultiDaysSleepTotalData(ServerDaysSleepData serverDaysSleepData, List<SleepDetailModel> list, boolean z) {
        dismissLoadingDialog();
        SleepBarChart sleepBarChart = this.mSleepBarChart;
        if (sleepBarChart != null) {
            int i = this.dateType;
            if (i == 1) {
                sleepBarChart.setFirstDateOfSelected(this.startDateOfSelectedWeek);
            } else if (i == 2) {
                sleepBarChart.setFirstDateOfSelected(this.firstDayOfSelectedMonth);
            }
            if (z) {
                this.mSleepBarChart.setDatas(list);
            } else {
                this.mSleepBarChart.setDatasNoAnimator(list);
            }
        }
        if (serverDaysSleepData == null || serverDaysSleepData.getAvgTotalSeconds() == 0) {
            this.mRtvTotalUnit.setVisibility(8);
            this.mRtvTotalUnit2.setVisibility(8);
            this.mMtvTotalData2.setVisibility(8);
            this.mMtvTotalData.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            this.mMtvTotalData2.setText("--");
            this.mMtvSleepScore.setText("--");
            this.mMtvSleepTime.setText("--");
            this.mMtvWakeTime.setText("--");
            this.mMtvRespiratoryRate.setText("--");
            this.mLayoutSleepRatioCard.setVisibility(8);
            this.mLayoutSleepData.setVisibility(8);
            this.mTvRemMark.setVisibility(8);
            return;
        }
        this.mRtvTotalUnit.setVisibility(0);
        this.mRtvTotalUnit2.setVisibility(0);
        this.mMtvTotalData2.setVisibility(0);
        this.mMtvSleepScore.setText(serverDaysSleepData.getAvgScore() == 0 ? "--" : String.valueOf(serverDaysSleepData.getAvgScore()));
        this.mMtvRespiratoryRate.setText(serverDaysSleepData.getAvgBreathRate() != 0 ? String.valueOf(serverDaysSleepData.getAvgBreathRate()) : "--");
        this.mMtvSleepTime.setText(String.format("%s:%s", NumUtil.numberFormat(serverDaysSleepData.getAvgStartTimeMinute() / 60), NumUtil.numberFormat(serverDaysSleepData.getAvgStartTimeMinute() % 60)));
        this.mMtvWakeTime.setText(String.format("%s:%s", NumUtil.numberFormat(serverDaysSleepData.getAvgEndTimeMinute() / 60), NumUtil.numberFormat(serverDaysSleepData.getAvgEndTimeMinute() % 60)));
        ArrayList arrayList = new ArrayList();
        int avgTotalSeconds = serverDaysSleepData.getAvgTotalSeconds() / 60;
        this.mMtvTotalData.setText(String.valueOf(avgTotalSeconds / 60));
        this.mMtvTotalData2.setText(String.valueOf(avgTotalSeconds % 60));
        int avgDeeplySeconds = serverDaysSleepData.getAvgDeeplySeconds() / 60;
        arrayList.add(new PieChartBean(this.mSleepColors[2], serverDaysSleepData.getDeeplyRatio()));
        int avgEyeMovementSeconds = serverDaysSleepData.getAvgEyeMovementSeconds() / 60;
        arrayList.add(new PieChartBean(this.mSleepColors[3], serverDaysSleepData.getEyeMovementRatio()));
        int avgAwakeSeconds = serverDaysSleepData.getAvgAwakeSeconds() / 60;
        arrayList.add(new PieChartBean(this.mSleepColors[0], serverDaysSleepData.getAwakeRatio()));
        int avgLightlySeconds = serverDaysSleepData.getAvgLightlySeconds() / 60;
        arrayList.add(new PieChartBean(this.mSleepColors[1], serverDaysSleepData.getLightlyRatio()));
        this.mSpcSleep.setData(arrayList);
        this.mLayoutSleepRatioCard.setVisibility(0);
        this.mLayoutSleepData.setVisibility(0);
        setSleepScoreDesc(serverDaysSleepData.getAvgScore());
        setScienceSleepLayoutVisible(hasRemTime(list));
        bindSleepData2View(this.mTvDeepSleepDuration, this.mTvDeepSleepRatio, avgDeeplySeconds, serverDaysSleepData.getDeeplyRatio());
        bindSleepData2View(this.mTvLightSleepDuration, this.mTvLightSleepRatio, avgLightlySeconds, serverDaysSleepData.getLightlyRatio());
        bindSleepData2View(this.mTvRemDuration, this.mTvRemRatio, avgEyeMovementSeconds, serverDaysSleepData.getEyeMovementRatio());
        bindSleepData2View(this.mTvAwakeDuration, this.mTvAwakeRatio, avgAwakeSeconds, serverDaysSleepData.getAwakeRatio());
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetYearSleepData(ServerMultiMonthSleepTotalData serverMultiMonthSleepTotalData, List<SleepDetailModel> list, boolean z) {
        dismissLoadingDialog();
        SleepBarChart sleepBarChart = this.mSleepBarChart;
        if (sleepBarChart != null) {
            sleepBarChart.setSelectedYear(this.selectedYear);
            if (z) {
                this.mSleepBarChart.setDatas(list);
            } else {
                this.mSleepBarChart.setDatasNoAnimator(list);
            }
        }
        if (serverMultiMonthSleepTotalData == null || serverMultiMonthSleepTotalData.getAvgTotalSeconds() == 0) {
            this.mRtvTotalUnit.setVisibility(8);
            this.mRtvTotalUnit2.setVisibility(8);
            this.mMtvTotalData2.setVisibility(8);
            this.mMtvTotalData.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            this.mMtvTotalData2.setText("--");
            this.mLayoutSleepRatioCard.setVisibility(8);
            this.mTvRemMark.setVisibility(8);
            return;
        }
        this.mRtvTotalUnit.setVisibility(0);
        this.mRtvTotalUnit2.setVisibility(0);
        this.mMtvTotalData2.setVisibility(0);
        ArrayList arrayList = new ArrayList();
        int avgTotalSeconds = serverMultiMonthSleepTotalData.getAvgTotalSeconds() / 60;
        this.mMtvTotalData.setText(String.valueOf(avgTotalSeconds / 60));
        this.mMtvTotalData2.setText(String.valueOf(avgTotalSeconds % 60));
        int avgDeeplySeconds = serverMultiMonthSleepTotalData.getAvgDeeplySeconds() / 60;
        arrayList.add(new PieChartBean(this.mSleepColors[2], serverMultiMonthSleepTotalData.getDeeplyRatio()));
        int avgEyeMovementSeconds = serverMultiMonthSleepTotalData.getAvgEyeMovementSeconds() / 60;
        arrayList.add(new PieChartBean(this.mSleepColors[3], serverMultiMonthSleepTotalData.getEyeMovementRatio()));
        int avgAwakeSeconds = serverMultiMonthSleepTotalData.getAvgAwakeSeconds() / 60;
        arrayList.add(new PieChartBean(this.mSleepColors[0], serverMultiMonthSleepTotalData.getAwakeRatio()));
        int avgLightlySeconds = serverMultiMonthSleepTotalData.getAvgLightlySeconds() / 60;
        arrayList.add(new PieChartBean(this.mSleepColors[1], serverMultiMonthSleepTotalData.getLightlyRatio()));
        this.mSpcSleep.setData(arrayList);
        this.mLayoutSleepRatioCard.setVisibility(0);
        setScienceSleepLayoutVisible(hasRemTime(list));
        bindSleepData2View(this.mTvDeepSleepDuration, this.mTvDeepSleepRatio, avgDeeplySeconds, serverMultiMonthSleepTotalData.getDeeplyRatio());
        bindSleepData2View(this.mTvLightSleepDuration, this.mTvLightSleepRatio, avgLightlySeconds, serverMultiMonthSleepTotalData.getLightlyRatio());
        bindSleepData2View(this.mTvRemDuration, this.mTvRemRatio, avgEyeMovementSeconds, serverMultiMonthSleepTotalData.getEyeMovementRatio());
        bindSleepData2View(this.mTvAwakeDuration, this.mTvAwakeRatio, avgAwakeSeconds, serverMultiMonthSleepTotalData.getAwakeRatio());
    }

    private boolean hasRemTime(List<SleepDetailModel> list) {
        if (list != null && !list.isEmpty()) {
            for (SleepDetailModel sleepDetailModel : list) {
                if (sleepDetailModel != null && sleepDetailModel.eyeMovementSleepMinutes > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetDayActiveTimeData(ActiveTimeDayData activeTimeDayData, float[] fArr) {
        dismissLoadingDialog();
        String strValueOf = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
        if (activeTimeDayData != null) {
            CharterBar charterBar = this.mCharterBar;
            if (charterBar != null) {
                charterBar.setValues(fArr);
            }
            MediumTextView mediumTextView = this.mMtvTotalData;
            if (activeTimeDayData != null) {
                strValueOf = String.valueOf(Math.round(activeTimeDayData.getTotalSeconds() / 60.0f));
            }
            mediumTextView.setText(strValueOf);
        } else {
            this.mMtvTotalData.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        }
        this.mLayoutTotalTwo.setVisibility(8);
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetMultiDaysActiveTime(int i, int i2, float[] fArr) {
        setMultiDaysData2CharterBar(fArr);
        this.mMtvTotalData.setText(String.valueOf(i));
        this.mLayoutTotalTwo.setVisibility(0);
        this.mMtvAvgData.setText(String.valueOf(i2));
        this.mRtvAvgUnit.setText(getLanguageText(R.string.min_unit_short));
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetYearActiveTime(int i, int i2, float[] fArr) {
        setYearData2CharterBar(fArr);
        this.mMtvTotalData.setText(String.valueOf(i));
        this.mLayoutTotalTwo.setVisibility(0);
        this.mMtvAvgData.setText(String.valueOf(i2));
        this.mRtvAvgUnit.setText(getLanguageText(R.string.min_unit_short));
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetDayBloodOxygen(ServerBloodOxyDayData serverBloodOxyDayData, List<ValueRangePair> list) {
        int criticalValue;
        dismissLoadingDialog();
        if (serverBloodOxyDayData == null || (serverBloodOxyDayData.getMinValue() == 0 && serverBloodOxyDayData.getMaxValue() == 0)) {
            this.mMtvTotalData.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            this.mMtvAvgData.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            this.mRtvTotalUnit.setVisibility(4);
            this.mRtvAvgUnit.setVisibility(4);
            criticalValue = 90;
        } else {
            this.mRtvTotalUnit.setVisibility(0);
            this.mRtvAvgUnit.setVisibility(0);
            int minValue = serverBloodOxyDayData.getMinValue();
            this.mMtvTotalData.setText(String.valueOf(minValue));
            this.mMtvAvgData.setText(String.valueOf(serverBloodOxyDayData.getMaxValue()));
            criticalValue = getCriticalValue(minValue);
        }
        CharterBar charterBar = this.mCharterBar;
        if (charterBar != null) {
            charterBar.setCriticalValue(criticalValue);
            this.mCharterBar.setValues(list);
        }
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetMultiDaysBloodOxygen(ServerDaysBloodOxyData serverDaysBloodOxyData, List<ValueRangePair> list, boolean z) {
        int criticalValue;
        dismissLoadingDialog();
        if (serverDaysBloodOxyData == null || (serverDaysBloodOxyData.getMaxValue() == 0 && serverDaysBloodOxyData.getMinValue() == 0)) {
            this.mMtvTotalData.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            this.mMtvAvgData.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            this.mRtvTotalUnit.setVisibility(4);
            this.mRtvAvgUnit.setVisibility(4);
            criticalValue = 90;
        } else {
            this.mRtvTotalUnit.setVisibility(0);
            this.mRtvAvgUnit.setVisibility(0);
            int minValue = serverDaysBloodOxyData.getMinValue();
            this.mMtvTotalData.setText(String.valueOf(minValue));
            this.mMtvAvgData.setText(String.valueOf(serverDaysBloodOxyData.getMaxValue()));
            criticalValue = getCriticalValue(minValue);
        }
        if (this.mCharterBar != null) {
            if (list.size() == 7) {
                this.mCharterBar.setFirstDateOfSelected(this.startDateOfSelectedWeek);
            } else {
                this.mCharterBar.setFirstDateOfSelected(this.firstDayOfSelectedMonth);
            }
            this.mCharterBar.setCriticalValue(criticalValue);
            if (z) {
                this.mCharterBar.setValues(list);
            } else {
                this.mCharterBar.setValuesNoAnimator(list);
            }
        }
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetYearBloodOxy(ServerMultiMonthBloodOxyTotalData serverMultiMonthBloodOxyTotalData, List<ValueRangePair> list, boolean z) {
        int criticalValue;
        dismissLoadingDialog();
        if (serverMultiMonthBloodOxyTotalData == null || (serverMultiMonthBloodOxyTotalData.getMaxValue() == 0 && serverMultiMonthBloodOxyTotalData.getMinValue() == 0)) {
            this.mMtvTotalData.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            this.mMtvAvgData.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            this.mRtvTotalUnit.setVisibility(4);
            this.mRtvAvgUnit.setVisibility(4);
            criticalValue = 90;
        } else {
            this.mRtvTotalUnit.setVisibility(0);
            this.mRtvAvgUnit.setVisibility(0);
            int minValue = serverMultiMonthBloodOxyTotalData.getMinValue();
            this.mMtvTotalData.setText(String.valueOf(minValue));
            this.mMtvAvgData.setText(String.valueOf(serverMultiMonthBloodOxyTotalData.getMaxValue()));
            criticalValue = getCriticalValue(minValue);
        }
        CharterBar charterBar = this.mCharterBar;
        if (charterBar != null) {
            charterBar.setCriticalValue(criticalValue);
            this.mCharterBar.setSelectedYear(this.selectedYear);
            if (z) {
                this.mCharterBar.setValues(list);
            } else {
                this.mCharterBar.setValuesNoAnimator(list);
            }
        }
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetDayWalkData(WalkDayData walkDayData, float[] fArr) {
        dismissLoadingDialog();
        CharterBar charterBar = this.mCharterBar;
        if (charterBar != null) {
            charterBar.setValues(fArr);
        }
        if (walkDayData == null) {
            this.mMtvTotalData.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        } else {
            this.mMtvTotalData.setText(String.valueOf((walkDayData.getReachSeconds() / 60) / 60));
        }
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetMultiDaysWalkData(MultiDaysWalkTotalData multiDaysWalkTotalData, float[] fArr, boolean z) {
        dismissLoadingDialog();
        CharterBar charterBar = this.mCharterBar;
        if (charterBar != null) {
            if (fArr.length == 7) {
                charterBar.setFirstDateOfSelected(this.startDateOfSelectedWeek);
            } else {
                charterBar.setFirstDateOfSelected(this.firstDayOfSelectedMonth);
            }
            if (z) {
                this.mCharterBar.setValues(fArr);
            } else {
                this.mCharterBar.setValuesNoAnimator(fArr);
            }
        }
        if (multiDaysWalkTotalData == null) {
            this.mMtvTotalData.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            this.mMtvAvgData.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        } else {
            this.mMtvTotalData.setText(String.valueOf(multiDaysWalkTotalData.getTotalHour()));
            this.mMtvAvgData.setText(NumUtil.float2String(multiDaysWalkTotalData.getAvgHour(), !NumUtil.isInteger(multiDaysWalkTotalData.getAvgHour()) ? 1 : 0));
        }
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetYearWalkData(WalkYearTotalData walkYearTotalData, float[] fArr, boolean z) {
        dismissLoadingDialog();
        CharterBar charterBar = this.mCharterBar;
        if (charterBar != null) {
            charterBar.setSelectedYear(this.selectedYear);
            if (z) {
                this.mCharterBar.setValues(fArr);
            } else {
                this.mCharterBar.setValuesNoAnimator(fArr);
            }
        }
        if (walkYearTotalData == null) {
            this.mMtvTotalData.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            this.mMtvAvgData.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        } else {
            this.mMtvTotalData.setText(String.valueOf(walkYearTotalData.getTotalHour()));
            this.mMtvAvgData.setText(NumUtil.float2String(walkYearTotalData.getAvgDayHour(), !NumUtil.isInteger(walkYearTotalData.getAvgDayHour()) ? 1 : 0));
        }
    }

    private void setYearData2CharterBar(float[] fArr) {
        dismissLoadingDialog();
        CharterBar charterBar = this.mCharterBar;
        if (charterBar != null) {
            charterBar.setSelectedYear(this.selectedYear);
            this.mCharterBar.setValues(fArr);
        }
    }

    private void setMultiDaysData2CharterBar(float[] fArr) {
        dismissLoadingDialog();
        CharterBar charterBar = this.mCharterBar;
        if (charterBar != null) {
            if (fArr.length == 7) {
                charterBar.setFirstDateOfSelected(this.startDateOfSelectedWeek);
            } else {
                charterBar.setFirstDateOfSelected(this.firstDayOfSelectedMonth);
            }
            this.mCharterBar.setValues(fArr);
        }
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetDayData(int i, float[] fArr) {
        dismissLoadingDialog();
        CharterBar charterBar = this.mCharterBar;
        if (charterBar != null) {
            charterBar.setValues(fArr);
        }
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetDayData(int i, List<ValueRangePair> list) {
        dismissLoadingDialog();
        CharterBar charterBar = this.mCharterBar;
        if (charterBar != null) {
            charterBar.setValues(list);
        }
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetWeekData(int i, float[] fArr) {
        dismissLoadingDialog();
        CharterBar charterBar = this.mCharterBar;
        if (charterBar != null) {
            charterBar.setFirstDateOfSelected(this.startDateOfSelectedWeek);
            this.mCharterBar.setValues(fArr);
        }
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetWeekData(int i, List<ValueRangePair> list) {
        dismissLoadingDialog();
        CharterBar charterBar = this.mCharterBar;
        if (charterBar != null) {
            charterBar.setFirstDateOfSelected(this.startDateOfSelectedWeek);
            this.mCharterBar.setValues(list);
        }
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetMonthData(int i, float[] fArr) {
        dismissLoadingDialog();
        CharterBar charterBar = this.mCharterBar;
        if (charterBar != null) {
            charterBar.setFirstDateOfSelected(this.firstDayOfSelectedMonth);
            this.mCharterBar.setValues(fArr);
        }
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetMonthData(int i, List<ValueRangePair> list) {
        dismissLoadingDialog();
        CharterBar charterBar = this.mCharterBar;
        if (charterBar != null) {
            charterBar.setFirstDateOfSelected(this.firstDayOfSelectedMonth);
            this.mCharterBar.setValues(list);
        }
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetYearData(int i, float[] fArr) {
        dismissLoadingDialog();
        setYearData2CharterBar(fArr);
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void onGetYearData(int i, List<ValueRangePair> list) {
        dismissLoadingDialog();
        CharterBar charterBar = this.mCharterBar;
        if (charterBar != null) {
            charterBar.setSelectedYear(this.selectedYear);
            this.mCharterBar.setValues(list);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.lay_loading) {
            if (NetworkUtil.isConnected(this)) {
                ((DetailPresenter) this.mPresenter).retryPullData();
                return;
            } else {
                NormalToast.showToast(getLanguageText(R.string.mine_manual_sync_no_network));
                return;
            }
        }
        if (view.getId() == R.id.iv_to_detail_instruction) {
            startActivity(new Intent(this, (Class<?>) ActivityForBloodOxy.class));
            return;
        }
        if (!((DetailPresenter) this.mPresenter).isConnected()) {
            showToast(getLanguageText(R.string.device_pls_connect_device));
            return;
        }
        int i = this.mType;
        if (i == 0) {
            startActivityForResult(new Intent(this, (Class<?>) SettingTargetActivity.class), 100);
        } else if (i == 4) {
            startActivityForResult(new Intent(this, (Class<?>) WalkReminderActivity.class), 99);
        } else {
            if (i != 10) {
                return;
            }
            startActivity(new SingleTopIntent(this, (Class<?>) BloodOxySettingActivity.class));
        }
    }

    @Override // com.ido.life.customview.charter.CharterBar.OnItemBarTouchListener
    public void onItemBarTouch(int i) {
        updateTotalDataLayoutStatus(i);
    }

    @Override // com.ido.life.customview.charter.SleepBarChart.OnItemSelectListener
    public void onItemSelected(int i) {
        updateTotalDataLayoutStatus(i);
        updateSleepTimeLayout(i);
    }

    private void updateSleepTimeLayout(int i) {
        if (this.dateType != 0) {
            return;
        }
        if (i == -1) {
            this.mRadioGroupTop.setVisibility(0);
        } else {
            this.mRadioGroupTop.setVisibility(8);
        }
    }

    private void updateTotalDataLayoutStatus(int i) {
        if (i == -1) {
            this.mLayoutTotalData.setAlpha(1.0f);
        } else {
            this.mLayoutTotalData.setAlpha(0.06f);
        }
        resetSelectedDate(i);
    }

    private void resetSelectedDate(int i) {
        Date todayDate = DateUtil.getTodayDate();
        if (i == -1) {
            int i2 = this.dateType;
            if (i2 == 1) {
                if (!todayDate.before(DateUtil.getSpecifiedDayBefore(this.startDateOfSelectedWeek, -6))) {
                    todayDate = DateUtil.getSpecifiedDayBefore(this.startDateOfSelectedWeek, -6);
                }
                this.selectedDate = todayDate;
            } else if (i2 == 2) {
                this.selectedDate = DateUtil.getLastDayOfYearMonth(DateUtil.getLastDayOfMonth(this.firstDayOfSelectedMonth));
            } else if (i2 == 3) {
                this.selectedDate = DateUtil.getLastDateOfYear(this.selectedYear);
            }
        } else {
            int i3 = this.dateType;
            if (i3 == 1) {
                Date specifiedDayBefore = DateUtil.getSpecifiedDayBefore(this.startDateOfSelectedWeek, -i);
                if (todayDate.before(specifiedDayBefore)) {
                    specifiedDayBefore = todayDate;
                }
                this.selectedDate = specifiedDayBefore;
            } else if (i3 == 2) {
                Date specifiedDayBefore2 = DateUtil.getSpecifiedDayBefore(this.firstDayOfSelectedMonth, -i);
                if (todayDate.before(specifiedDayBefore2)) {
                    specifiedDayBefore2 = todayDate;
                }
                this.selectedDate = specifiedDayBefore2;
            } else if (i3 == 3) {
                Date date = DateUtil.getDate(this.selectedYear, i + 1, 16);
                if (todayDate.before(date)) {
                    date = todayDate;
                }
                this.selectedDate = date;
            }
        }
        int[] yearMonthDay = DateUtil.getYearMonthDay(this.selectedDate);
        this.selectedYear = yearMonthDay[0];
        this.selectedMonth = yearMonthDay[1];
        if (this.dateType == 3) {
            Date lastDayOfYearMonth = DateUtil.getLastDayOfYearMonth(this.selectedDate);
            this.startDateOfSelectedWeek = ((DetailPresenter) this.mPresenter).getStartDateOfWeek(lastDayOfYearMonth);
            this.firstDayOfSelectedMonth = DateUtil.getFirstDayOfMonth(lastDayOfYearMonth);
        } else {
            this.startDateOfSelectedWeek = ((DetailPresenter) this.mPresenter).getStartDateOfWeek(this.selectedDate);
            this.firstDayOfSelectedMonth = DateUtil.getFirstDayOfMonth(this.selectedDate);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 99) {
            CharterBar charterBar = this.mCharterBar;
            if (charterBar != null) {
                charterBar.setTargetValue(((DetailPresenter) this.mPresenter).getWalkTargetHour(), true);
            }
        } else if (i2 == 100) {
            CharterBar charterBar2 = this.mCharterBar;
        }
        if (REQUEST_CODE == i) {
            setTipStatus();
        }
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        super.handleMessage(baseMessage);
        if (this.mPresenter != 0) {
            ((DetailPresenter) this.mPresenter).processEventMessage(baseMessage);
        }
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void showLoadSuccessUI() {
        CommonLogUtil.d(TAG, "显示数据加载成功UI");
        stopLoadingAnimator();
        this.mLayLoading.setVisibility(8);
        this.mLayLoading.setOnClickListener(null);
        this.mLayoutTotalData.setVisibility(0);
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void showLoadFailedUI() {
        CommonLogUtil.d(TAG, "显示数据加载失败UI");
        stopLoadingAnimator();
        this.mLayLoading.setVisibility(0);
        this.mImgLoadFailed.setVisibility(0);
        this.mTvDataLoadState.setText(getLanguageText(R.string.chart_detail_data_load_failed));
        this.mLayLoading.setOnClickListener(this);
        this.mImgLoading.setVisibility(8);
        this.mLayoutTotalData.setVisibility(4);
    }

    @Override // com.ido.life.module.home.detail.IDetailView
    public void showLoadingUI() {
        CommonLogUtil.d(TAG, "显示数据正在加载中UI");
        this.mLayLoading.setVisibility(0);
        this.mImgLoading.setVisibility(0);
        this.mLayLoading.setOnClickListener(null);
        this.mTvDataLoadState.setText(getLanguageText(R.string.chart_detail_data_loading));
        this.mImgLoadFailed.setVisibility(8);
        this.mLayoutTotalData.setVisibility(4);
        startLoadingAnimator();
    }

    private ValueAnimator getLoadingAnimator() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setTarget(this.mImgLoading);
        valueAnimator.setDuration(500L);
        valueAnimator.setIntValues(0, SpatialRelationUtil.A_CIRCLE_DEGREE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setRepeatMode(1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.module.home.detail.-$$Lambda$DetailActivity$PcTz0Vz_KTuvQwwmY0as2dr-uzU
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                this.f$0.lambda$getLoadingAnimator$3$DetailActivity(valueAnimator2);
            }
        });
        return valueAnimator;
    }

    public /* synthetic */ void lambda$getLoadingAnimator$3$DetailActivity(ValueAnimator valueAnimator) {
        ImageView imageView = this.mImgLoading;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        this.mImgLoading.setRotation(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    private void startLoadingAnimator() {
        if (this.mLoadingAnimator == null) {
            this.mLoadingAnimator = getLoadingAnimator();
        }
        if (this.mLoadingAnimator.isStarted() || this.mLoadingAnimator.isRunning()) {
            return;
        }
        this.mLoadingAnimator.start();
    }

    private void stopLoadingAnimator() {
        ValueAnimator valueAnimator = this.mLoadingAnimator;
        if (valueAnimator == null) {
            return;
        }
        if (valueAnimator.isStarted() || this.mLoadingAnimator.isRunning()) {
            this.mLoadingAnimator.cancel();
        }
    }
}