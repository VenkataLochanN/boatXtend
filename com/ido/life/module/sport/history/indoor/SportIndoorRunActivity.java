package com.ido.life.module.sport.history.indoor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.google.android.material.badge.BadgeDrawable;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.WeightSeekBar;
import com.ido.life.customview.charter.CubicChartBar;
import com.ido.life.customview.charter.CubicSportChartBar;
import com.ido.life.customview.viewgroup.BottomSportView;
import com.ido.life.customview.viewgroup.UnitView;
import com.ido.life.database.model.SportHealth;
import com.ido.life.dialog.SportShareDialogFragment;
import com.ido.life.module.sport.bean.HistoryRecordDetailsData;
import com.ido.life.module.sport.bean.PieChartBean;
import com.ido.life.module.sport.history.indoor.SportIndoorRunContract;
import com.ido.life.module.sport.util.BigDecimalUtil;
import com.ido.life.module.sport.view.SportDeletePopupWindow;
import com.ido.life.module.sport.view.SportDistanceView;
import com.ido.life.module.sport.view.SportPieChart;
import com.ido.life.module.user.feedback.FeedbackActivity;
import com.ido.life.util.DateUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.ShareUtil;
import com.ido.life.util.SportDataUtil;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportIndoorRunActivity extends BaseCoreActivity implements SportIndoorRunContract.View {
    private static final String EXTRA_DATE = "extra_date";
    private static final String EXTRA_FROM_TYPE = "from_type";
    private static final String EXTRA_SPORT_HEALTH = "sport_health";
    private static final String EXTRA_SPORT_TYPE = "sport_type";
    public static final int REQUEST_CODE = 1001;
    public static final int RESULT_CODE = 1002;
    private static final String TAG = "SportIndoorRunActivity";

    @BindView(R.id.bv_chart_sport_rate_average)
    BottomSportView mBvChartSportRateAverage;

    @BindView(R.id.bv_chart_sport_rate_max)
    BottomSportView mBvChartSportRateMax;

    @BindView(R.id.bv_sport_frequency_avg)
    BottomSportView mBvSportFrequencyAvg;

    @BindView(R.id.bv_sport_frequency_max)
    BottomSportView mBvSportFrequencyMax;

    @BindView(R.id.bv_sport_item_eight)
    BottomSportView mBvSportItemEight;

    @BindView(R.id.bv_sport_item_five)
    BottomSportView mBvSportItemFive;

    @BindView(R.id.bv_sport_item_four)
    BottomSportView mBvSportItemFour;

    @BindView(R.id.bv_sport_item_one)
    BottomSportView mBvSportItemOne;

    @BindView(R.id.bv_sport_item_pace_avg)
    BottomSportView mBvSportItemPaceAvg;

    @BindView(R.id.bv_sport_item_pace_faster)
    BottomSportView mBvSportItemPaceFaster;

    @BindView(R.id.bv_sport_item_seven)
    BottomSportView mBvSportItemSeven;

    @BindView(R.id.bv_sport_item_six)
    BottomSportView mBvSportItemSix;

    @BindView(R.id.bv_sport_item_three)
    BottomSportView mBvSportItemThree;

    @BindView(R.id.bv_sport_item_two)
    BottomSportView mBvSportItemTwo;

    @BindView(R.id.cubic_chart)
    CubicSportChartBar mCubicChart;

    @BindView(R.id.cubic_chart_step)
    CubicChartBar mCubicChartStep;
    private String mDateTime;
    private int mFromType = 0;

    @BindView(R.id.iv_share)
    ImageView mIvShare;

    @BindView(R.id.layout_network_error)
    LinearLayout mLayoutNetworkError;

    @BindView(R.id.layout_sport_retry)
    LinearLayout mLayoutSportRetry;

    @BindView(R.id.ll_content)
    LinearLayout mLlContent;

    @BindView(R.id.ll_share_title)
    LinearLayout mLlShareTitle;

    @BindView(R.id.ll_sport_detail_four_item)
    LinearLayout mLlSportDetailFourItem;

    @BindView(R.id.ll_sport_detail_one_item)
    LinearLayout mLlSportDetailOneItem;

    @BindView(R.id.ll_sport_detail_rate_item)
    LinearLayout mLlSportDetailRate;

    @BindView(R.id.ll_sport_detail_three_item)
    LinearLayout mLlSportDetailThreeItem;

    @BindView(R.id.ll_sport_detail_two_item)
    LinearLayout mLlSportDetailTwoItem;

    @BindView(R.id.ll_sport_distance)
    LinearLayout mLlSportDistance;

    @BindView(R.id.ll_sport_detail_frequency_item)
    LinearLayout mLlSportFrequencyItem;

    @BindView(R.id.ll_sport_km_speed)
    LinearLayout mLlSportKmSpeed;

    @BindView(R.id.ll_sport_detail_peace_item)
    LinearLayout mLlSportPaceItem;

    @BindView(R.id.ll_sport_root)
    LinearLayout mLlSportRoot;

    @BindView(R.id.ll_sport_detail_speed_item)
    LinearLayout mLlSportSpeedItem;

    @BindView(R.id.ll_sport_suggest)
    LinearLayout mLlSportSuggest;

    @BindView(R.id.pc_sport)
    SportPieChart mPcSport;
    private SportIndoorRunContract.Presenter mPresenter;

    @BindView(R.id.seekBar_step_number)
    WeightSeekBar mSeekBarStepNumber;

    @BindView(R.id.ll_sport_content)
    LinearLayout mSportContent;
    private int mSportType;

    @BindView(R.id.sv_content)
    ScrollView mSvContent;

    @BindView(R.id.title_leftBtn)
    ImageButton mTitleLeftBtn;

    @BindView(R.id.title_rightBtn)
    ImageButton mTitleRightBtn;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @BindView(R.id.tv_aerobic_endurance)
    TextView mTvAerobicEndurance;

    @BindView(R.id.tv_burning_heart_rate)
    TextView mTvBurningHeartRate;

    @BindView(R.id.tv_data_doubt)
    RegularTextView mTvDataDoubt;

    @BindView(R.id.tv_data_feedback)
    RegularTextView mTvDataFeedback;

    @BindView(R.id.tv_heart_rate)
    RegularTextView mTvHeartRate;

    @BindView(R.id.tv_heart_rate_unit)
    RegularTextView mTvHeartRateUnit;

    @BindView(R.id.tv_max_exercise_time)
    TextView mTvMaxExerciseTime;

    @BindView(R.id.tv_no_endurance)
    TextView mTvNoEndurance;

    @BindView(R.id.tv_retry)
    TextView mTvRetry;

    @BindView(R.id.tv_sport_avg_speed)
    TextView mTvSportAvgSpeed;

    @BindView(R.id.tv_sport_frequency_title)
    TextView mTvSportFrequencyTitle;

    @BindView(R.id.tv_sport_frequency_unit)
    TextView mTvSportFrequencyUnit;

    @BindView(R.id.tv_sport_item_distance)
    TextView mTvSportItemDistance;

    @BindView(R.id.tv_sport_name)
    RegularTextView mTvSportName;

    @BindView(R.id.tv_sport_item_pace)
    TextView mTvSportPace;

    @BindView(R.id.tv_sport_pace_unit)
    TextView mTvSportPaceUnit;

    @BindView(R.id.tv_sport_item_speed_km)
    TextView mTvSportSpeedKm;

    @BindView(R.id.tv_sport_pace_title)
    TextView mTvSportSpeedTitle;

    @BindView(R.id.tv_sport_speed_unit)
    TextView mTvSportSpeedUnit;

    @BindView(R.id.tv_sport_time_start)
    RegularTextView mTvSportTimeStart;

    @BindView(R.id.tv_target_diff)
    RegularTextView mTvTargetDiff;

    @BindView(R.id.tv_warm_up_heart)
    TextView mTvWarmUpHeart;

    @BindView(R.id.uv_category)
    UnitView mUvDistance;

    @BindView(R.id.title_bar)
    FrameLayout mfLTitleBar;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_sport_indoor_run;
    }

    public static void start(Context context, int i, String str, int i2, SportHealth sportHealth) {
        Intent intent = new Intent(context, (Class<?>) SportIndoorRunActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("extra_date", str);
        bundle.putInt("sport_type", i);
        bundle.putInt("from_type", i2);
        bundle.putSerializable(EXTRA_SPORT_HEALTH, sportHealth);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void startActivityResult(Activity activity, int i, String str, int i2, SportHealth sportHealth) {
        Intent intent = new Intent(activity, (Class<?>) SportIndoorRunActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("extra_date", str);
        bundle.putInt("sport_type", i);
        bundle.putInt("from_type", i2);
        bundle.putSerializable(EXTRA_SPORT_HEALTH, sportHealth);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, 1001);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mDateTime = extras.getString("extra_date");
            this.mFromType = extras.getInt("from_type");
            this.mSportType = extras.getInt("sport_type");
        }
        this.mPresenter = new SportIndoorRunPresenter(this);
        this.mPresenter.getSportNameByType(this.mSportType);
        SportIndoorRunContract.Presenter presenter = this.mPresenter;
        int i = this.mFromType;
        presenter.getSportDataByDate(i, this.mDateTime, i);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initViews() {
        super.initViews();
        initChartData();
    }

    protected void initChartData() {
        this.mCubicChart.setBottomLabelCenter(false);
        this.mCubicChart.setRateLineColor(getResources().getColor(R.color.color_FC4B69));
        this.mCubicChart.setGradientStartColor(getResources().getColor(R.color.color_sport_red));
        this.mCubicChart.setGradientEndColor(getResources().getColor(R.color.white));
        this.mCubicChart.setGoalLineList(null);
        this.mCubicChart.invalidate();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mPcSport.setShowCenterText(LanguageUtil.getLanguageText(R.string.sport_range_heart_rate));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        hideShareView();
    }

    private void hideShareView() {
        this.mLlSportSuggest.setVisibility(0);
        this.mLlShareTitle.setVisibility(8);
        this.mfLTitleBar.setVisibility(0);
    }

    @OnClick({R.id.title_rightBtn})
    public void toDelete(View view) {
        if (this.mFromType == 0) {
            toShare();
            return;
        }
        SportDeletePopupWindow sportDeletePopupWindow = new SportDeletePopupWindow(this);
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        sportDeletePopupWindow.showAtLocation(view, BadgeDrawable.TOP_END, DipPixelUtil.dip2px(18.0f), iArr[1] + DipPixelUtil.dip2px(30.0f));
        sportDeletePopupWindow.setOnItemClickListener(new SportDeletePopupWindow.OnItemClickListener() { // from class: com.ido.life.module.sport.history.indoor.SportIndoorRunActivity.1
            @Override // com.ido.life.module.sport.view.SportDeletePopupWindow.OnItemClickListener
            public void onDeleteClick(View view2) {
                SportIndoorRunActivity sportIndoorRunActivity = SportIndoorRunActivity.this;
                sportIndoorRunActivity.showConfirmDelete(sportIndoorRunActivity.mDateTime);
            }
        });
    }

    @OnClick({R.id.iv_share})
    public void toShare(View view) {
        toShare();
    }

    @OnClick({R.id.tv_retry})
    public void toRetry(View view) {
        this.mPresenter.getSportDataByDate(this.mSportType, this.mDateTime, this.mFromType);
    }

    private void toShare() {
        WaitingDialog.showDialog(this);
        this.mfLTitleBar.setVisibility(8);
        this.mLlShareTitle.setVisibility(8);
        new Handler().postDelayed(new Runnable() { // from class: com.ido.life.module.sport.history.indoor.SportIndoorRunActivity.2
            @Override // java.lang.Runnable
            public void run() {
                WaitingDialog.hideDialog();
                new ShareUtil().shotLongScreen(SportIndoorRunActivity.this.mLlContent, SportIndoorRunActivity.this.mLlContent);
                final SportShareDialogFragment sportShareDialogFragmentNewInstance = SportShareDialogFragment.newInstance();
                sportShareDialogFragmentNewInstance.setCancelable(false);
                sportShareDialogFragmentNewInstance.show(SportIndoorRunActivity.this.getSupportFragmentManager());
                sportShareDialogFragmentNewInstance.setOnShareResultListener(new SportShareDialogFragment.OnShareChooseListener() { // from class: com.ido.life.module.sport.history.indoor.SportIndoorRunActivity.2.1
                    @Override // com.ido.life.dialog.SportShareDialogFragment.OnShareChooseListener
                    public void onSharePlatChoose(int i) {
                        SportIndoorRunActivity.this.mLlSportSuggest.setVisibility(0);
                        SportIndoorRunActivity.this.mLlShareTitle.setVisibility(8);
                        SportIndoorRunActivity.this.mfLTitleBar.setVisibility(0);
                        sportShareDialogFragmentNewInstance.dismissAllowingStateLoss();
                    }
                });
            }
        }, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showConfirmDelete(final String str) {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(ResourceUtil.getString(R.string.sport_detail_confirm_delete_content), ResourceUtil.getString(R.string.sport_detail_confirm_delete_confirm), ResourceUtil.getString(R.string.sport_detail_confirm_delete_cancel), true);
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.history.indoor.SportIndoorRunActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.history.indoor.SportIndoorRunActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                SportIndoorRunActivity.this.mPresenter.deleteRecord(str);
            }
        });
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void showLoading() {
        WaitingDialog.showDialog(this);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void showMessage(String str) {
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSportName(String str) {
        this.mTvSportName.setText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSportStartTime(String str) {
        this.mTvSportTimeStart.setText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSportDistance(String str, int i) {
        if (i == 2) {
            this.mLlSportDetailTwoItem.setVisibility(8);
            this.mLlSportDetailThreeItem.setVisibility(8);
            this.mBvSportItemSeven.setVisibility(8);
            return;
        }
        this.mUvDistance.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSportDistanceUnit(String str) {
        this.mUvDistance.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSportRateMax(String str) {
        this.mBvChartSportRateMax.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSportChartRateAvg(String str) {
        this.mBvChartSportRateAverage.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setHeartPieChart(List<PieChartBean> list) {
        this.mPcSport.setData(list);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setMaxExerciseTime(String str) {
        this.mTvMaxExerciseTime.setText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setNoEndurance(String str) {
        this.mTvNoEndurance.setText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setAerobicEndurance(String str) {
        this.mTvAerobicEndurance.setText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setBurningHeartRate(String str) {
        this.mTvBurningHeartRate.setText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setWarmUpHeart(String str) {
        this.mTvWarmUpHeart.setText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setKmSpace(List<HistoryRecordDetailsData> list, int i, int i2, boolean z) {
        String str;
        String strComputeTimeMS;
        int iDiv;
        int i3;
        boolean z2;
        byte b2;
        if (list != null) {
            double d2 = 100.0d;
            if (list.size() == 1) {
                if (z) {
                    int paceSpeed = list.get(0).getPaceSpeed();
                    if (i2 == 0) {
                        return;
                    }
                    int iDiv2 = (int) (BigDecimalUtil.div(list.get(0).getPaceSpeed(), i2, 2) * 100.0d);
                    String strComputeTimeMS2 = DateUtil.computeTimeMS(paceSpeed);
                    SportDistanceView sportDistanceView = new SportDistanceView(this);
                    sportDistanceView.setSportStatusProgressRechColor(-21112, -21112);
                    sportDistanceView.setSportStatusProgress(iDiv2, strComputeTimeMS2, "");
                    this.mLlSportDistance.addView(sportDistanceView);
                    return;
                }
                this.mLlSportDistance.addView(getTextView(DateUtil.computeTimeMS(list.get(0).getPaceSpeed())));
                this.mBvSportItemPaceFaster.setVisibility(8);
                return;
            }
            int i4 = 0;
            while (i4 < list.size()) {
                HistoryRecordDetailsData historyRecordDetailsData = list.get(i4);
                CommonLogUtil.d(TAG, "setKmSpeed: " + historyRecordDetailsData.toString() + AppInfo.DELIM + i + AppInfo.DELIM + i2);
                int paceSpeed2 = historyRecordDetailsData.getPaceSpeed();
                if (list.get(i4).getPaceSpeed() != 0) {
                    double paceSpeed3 = list.get(i4).getPaceSpeed();
                    str = AppInfo.DELIM;
                    iDiv = (int) (BigDecimalUtil.div(paceSpeed3, i2, 2) * d2);
                    strComputeTimeMS = DateUtil.computeTimeMS(paceSpeed2);
                } else {
                    str = AppInfo.DELIM;
                    strComputeTimeMS = "0.0";
                    iDiv = 0;
                }
                SportDistanceView sportDistanceView2 = new SportDistanceView(this);
                int i5 = i4 + 1;
                sportDistanceView2.setSportDistanceCount(String.valueOf(i5));
                CommonLogUtil.d(TAG, "setKmSpeed: " + iDiv + str + strComputeTimeMS);
                if (i4 == list.size() - 1 && !z) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, DipPixelUtil.dip2px(25.0f));
                    TextView textView = new TextView(this);
                    textView.setTextColor(getResources().getColor(R.color.white, null));
                    if (RunTimeUtil.getInstance().getUnitSet() == 1) {
                        textView.setText(String.format(LanguageUtil.getLanguageText(R.string.sport_not_reach), LanguageUtil.getLanguageText(R.string.sport_run_distance_unit)) + strComputeTimeMS);
                        i3 = 0;
                        z2 = true;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        z2 = true;
                        i3 = 0;
                        sb.append(String.format(LanguageUtil.getLanguageText(R.string.sport_not_reach), LanguageUtil.getLanguageText(R.string.sport_run_distance_unit_mi)));
                        sb.append(strComputeTimeMS);
                        textView.setText(sb.toString());
                    }
                    textView.setPadding(DipPixelUtil.dip2px(5.0f), i3, i3, i3);
                    textView.setGravity(16);
                    textView.setLayoutParams(layoutParams);
                    this.mLlSportDistance.addView(textView);
                    b2 = -21112;
                } else {
                    i3 = 0;
                    z2 = true;
                    if (historyRecordDetailsData.isFaster()) {
                        sportDistanceView2.setSportStatusProgressRechColor(-367616, -367616);
                        sportDistanceView2.setSportStatusProgress(iDiv, strComputeTimeMS, LanguageUtil.getLanguageText(R.string.sport_fastest));
                        b2 = -21112;
                    } else {
                        b2 = -21112;
                        sportDistanceView2.setSportStatusProgressRechColor(-21112, -21112);
                        sportDistanceView2.setSportStatusProgress(iDiv, strComputeTimeMS, "");
                    }
                    this.mLlSportDistance.addView(sportDistanceView2);
                }
                i4 = i5;
                d2 = 100.0d;
            }
        }
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setKmSpeed(List<HistoryRecordDetailsData> list, int i, int i2, boolean z) {
        int iDiv;
        if (list != null) {
            if (list.size() == 1) {
                if (z) {
                    this.mLlSportKmSpeed.addView(getSportDistanceView(list.get(0), i));
                    return;
                } else {
                    this.mLlSportKmSpeed.addView(getTextView(SportDataUtil.getSpeedMiByPeaceMi(list.get(0).getPaceSpeed())));
                    return;
                }
            }
            int i3 = 0;
            while (i3 < list.size()) {
                HistoryRecordDetailsData historyRecordDetailsData = list.get(i3);
                String speedMiByPeaceMi = SportDataUtil.getSpeedMiByPeaceMi(historyRecordDetailsData.getPaceSpeed());
                String speedMiByPeaceMi2 = SportDataUtil.getSpeedMiByPeaceMi(i);
                if (i <= 0) {
                    return;
                }
                if (historyRecordDetailsData.getPaceSpeed() != 0) {
                    iDiv = (int) (BigDecimalUtil.div(Double.valueOf(speedMiByPeaceMi).doubleValue(), Double.valueOf(speedMiByPeaceMi2).doubleValue(), 2) * 100.0d);
                } else {
                    speedMiByPeaceMi = "0.0";
                    iDiv = 0;
                }
                SportDistanceView sportDistanceView = new SportDistanceView(this);
                int i4 = i3 + 1;
                sportDistanceView.setSportDistanceCount(String.valueOf(i4));
                CommonLogUtil.d(TAG, "setKmSpeed: " + iDiv + AppInfo.DELIM + speedMiByPeaceMi);
                if (i3 == list.size() - 1 && !z) {
                    this.mLlSportKmSpeed.addView(getTextView(speedMiByPeaceMi));
                } else {
                    if (historyRecordDetailsData.isFaster()) {
                        sportDistanceView.setSportStatusProgressRechColor(-367616, -367616);
                        sportDistanceView.setSportStatusEndText(ResourceUtil.getColor(R.color.com_color_white));
                        sportDistanceView.setSportStatusProgress(iDiv, speedMiByPeaceMi, LanguageUtil.getLanguageText(R.string.sport_fastest));
                    } else {
                        sportDistanceView.setSportStatusProgressRechColor(-21112, -21112);
                        sportDistanceView.setSportStatusProgress(iDiv, speedMiByPeaceMi, "");
                    }
                    this.mLlSportKmSpeed.addView(sportDistanceView);
                }
                i3 = i4;
            }
        }
    }

    private SportDistanceView getSportDistanceView(HistoryRecordDetailsData historyRecordDetailsData, int i) {
        String speedMiByPeaceMi = SportDataUtil.getSpeedMiByPeaceMi(historyRecordDetailsData.getPaceSpeed());
        int iDiv = (int) (BigDecimalUtil.div(Double.valueOf(speedMiByPeaceMi).doubleValue(), Double.valueOf(SportDataUtil.getSpeedMiByPeaceMi(i)).doubleValue(), 2) * 100.0d);
        SportDistanceView sportDistanceView = new SportDistanceView(this);
        sportDistanceView.setSportStatusProgressRechColor(-21112, -21112);
        sportDistanceView.setSportStatusProgress(iDiv, speedMiByPeaceMi, "");
        return sportDistanceView;
    }

    private TextView getTextView(String str) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, DipPixelUtil.dip2px(25.0f));
        TextView textView = new TextView(this);
        textView.setTextColor(getResources().getColor(R.color.white, null));
        if (RunTimeUtil.getInstance().getUnitSet() == 1) {
            textView.setText(String.format(LanguageUtil.getLanguageText(R.string.sport_not_reach), LanguageUtil.getLanguageText(R.string.sport_run_distance_unit)) + str);
        } else {
            textView.setText(String.format(LanguageUtil.getLanguageText(R.string.sport_not_reach), LanguageUtil.getLanguageText(R.string.sport_run_distance_unit_mi)) + str);
        }
        textView.setPadding(DipPixelUtil.dip2px(5.0f), 0, 0, 0);
        textView.setGravity(16);
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setYLabelList(List<String> list) {
        this.mCubicChart.setLabelYRightList(list);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setXLabelList(List<String> list) {
        this.mCubicChart.setLabelXList(list);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setRateList(List<BaseCharBean> list) {
        this.mCubicChart.setList(list);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setRateXMinValue(int i) {
        this.mCubicChart.setXMinValue(i);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setRateXMaxValue(int i) {
        this.mCubicChart.setXMaxValue(i);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setRateYMaxValue(int i) {
        this.mCubicChart.setYMaxValue(i);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setRateYMinValue(int i) {
        this.mCubicChart.setYMinValue(i);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void showSportDataItemOne(boolean z) {
        this.mLlSportDetailOneItem.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void showSportDataItemTwo(boolean z) {
        this.mLlSportDetailTwoItem.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void showSportDataItemThree(boolean z) {
        this.mLlSportDetailThreeItem.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void showSportDataItemFour(boolean z) {
        this.mLlSportDetailFourItem.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setFirstItemValue(String str) {
        this.mBvSportItemOne.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setFirstItemDesc(String str) {
        this.mBvSportItemOne.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setFirstItemUnit(String str) {
        this.mBvSportItemOne.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSecondItemValue(String str) {
        this.mBvSportItemTwo.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSecondItemDesc(String str) {
        this.mBvSportItemTwo.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSecondItemUnit(String str) {
        this.mBvSportItemTwo.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setThreeItemValue(String str) {
        this.mBvSportItemThree.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setThreeItemDesc(String str) {
        this.mBvSportItemThree.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setThreeItemUnit(String str) {
        this.mBvSportItemThree.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setFourItemValue(String str) {
        this.mBvSportItemFour.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setFourItemDesc(String str) {
        this.mBvSportItemFour.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setFourItemUnit(String str) {
        this.mBvSportItemFour.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void showFourItem(boolean z) {
        this.mBvSportItemFour.setVisibility(z ? 0 : 4);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setFiveItemValue(String str) {
        this.mBvSportItemFive.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setFiveItemDesc(String str) {
        this.mBvSportItemFive.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setFiveItemUnit(String str) {
        this.mBvSportItemFive.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSixItemValue(String str) {
        this.mBvSportItemSix.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSixItemDesc(String str) {
        this.mBvSportItemSix.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSixItemUnit(String str) {
        this.mBvSportItemSix.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSevenItemValue(String str) {
        this.mBvSportItemSeven.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSevenItemDesc(String str) {
        this.mBvSportItemSeven.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSevenItemUnit(String str) {
        this.mBvSportItemSeven.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setEightItemValue(String str) {
        this.mBvSportItemEight.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setEightItemDesc(String str) {
        this.mBvSportItemEight.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setEightItemUnit(String str) {
        this.mBvSportItemEight.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void showSportItemRate(boolean z) {
        this.mLlSportDetailRate.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void showRightBtn(boolean z) {
        this.mTitleRightBtn.setVisibility(z ? 0 : 8);
        this.mIvShare.setVisibility(0);
        if (z) {
            return;
        }
        this.mIvShare.setVisibility(8);
        this.mTitleRightBtn.setVisibility(0);
        this.mTitleRightBtn.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_share));
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSportItemSpeedTitle(String str) {
        this.mTvSportSpeedTitle.setText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSportItemPaceTitleUnit(String str) {
        this.mTvSportPaceUnit.setText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSportItemSpeedTitleUnit(String str) {
        this.mTvSportSpeedUnit.setText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSportSpeedItemKm(String str) {
        this.mTvSportSpeedKm.setText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSportItemSpeedType(String str) {
        this.mTvSportPace.setText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSportItemPaceFasterUnit(String str) {
        this.mBvSportItemPaceFaster.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSportItemPaceAverageDesc(String str) {
        this.mBvSportItemPaceAvg.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSportItemPaceFasterDesc(String str) {
        this.mBvSportItemPaceFaster.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void toBack() {
        setResult(1002, new Intent());
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(SportIndoorRunContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void showSeekBarStepNum(boolean z) {
        this.mSeekBarStepNumber.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void showUserTargetDiff(boolean z) {
        this.mTvTargetDiff.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSeekBarProcess(int i, int i2) {
        this.mSeekBarStepNumber.setCurProgress(i);
        this.mSeekBarStepNumber.setMaxProgress(i2);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setTargetDiff(String str) {
        this.mTvTargetDiff.setText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSportItemPaceFaster(String str) {
        this.mBvSportItemPaceFaster.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void showSportItemPace(boolean z) {
        this.mLlSportPaceItem.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSportItemPaceAverage(String str) {
        this.mBvSportItemPaceAvg.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void showSportItemFrequency(boolean z) {
        this.mLlSportFrequencyItem.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setStepFrequencyAvg(String str) {
        this.mBvSportFrequencyAvg.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setStepFrequencyMax(String str) {
        this.mBvSportFrequencyMax.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setYStepLabelList(List<String> list) {
        this.mCubicChartStep.setLabelYLeftList(list);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setXStepLabelList(List<String> list) {
        this.mCubicChartStep.setLabelXList(list);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setStepFrequencyList(List<BaseCharBean> list) {
        this.mCubicChartStep.setList(list);
        this.mCubicChartStep.refreshChart(true);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setStepXMinValue(int i) {
        this.mCubicChartStep.setXMinValue(i);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setStepXMaxValue(int i) {
        this.mCubicChartStep.setXMaxValue(i);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setStepYMaxValue(int i) {
        this.mCubicChartStep.setYMaxValue(i);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setStepYMinValue(int i) {
        this.mCubicChartStep.setYMinValue(i);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setSportItemDistanceLabel(String str) {
        this.mTvSportItemDistance.setText(str);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void showSportItemSpeed(boolean z) {
        this.mLlSportSpeedItem.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void showSportNoNet(boolean z) {
        this.mLayoutNetworkError.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void showSportRetryView(boolean z) {
        this.mLayoutSportRetry.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void showSportDataView(boolean z) {
        this.mSportContent.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setLoadLoadTitleShow(boolean z) {
        this.mTitleText.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.View
    public void setLoadTitleText(String str) {
        this.mTitleText.setText(str);
    }

    @OnClick({R.id.title_leftBtn})
    public void toBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @OnClick({R.id.tv_data_feedback})
    public void toFeedback(View view) {
        startActivity(new Intent(this, (Class<?>) FeedbackActivity.class));
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mPresenter.clearSportTarget();
    }
}