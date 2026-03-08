package com.ido.life.module.sport.history.swim;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.google.android.material.badge.BadgeDrawable;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NumUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.customview.charter.SportSpeedChart;
import com.ido.life.customview.viewgroup.BottomSportView;
import com.ido.life.customview.viewgroup.UnitView;
import com.ido.life.data.cache.MotionTypeManager;
import com.ido.life.database.model.SportHealth;
import com.ido.life.dialog.SportShareDialogFragment;
import com.ido.life.dialog.SwimNameExplainDialogFragment;
import com.ido.life.module.sport.explain.SportExplainActivity;
import com.ido.life.module.sport.history.swim.SportSwimContract;
import com.ido.life.module.sport.view.SportDeletePopupWindow;
import com.ido.life.module.user.feedback.FeedbackActivity;
import com.ido.life.util.DateUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.ShareUtil;
import com.ido.life.util.SportUnitUtil;
import com.ido.life.util.TimeUtil;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportSwimActivity extends BaseCoreActivity implements SportSwimContract.View {
    private static final String EXTRA_SPORT_DATA = "sport_data";
    private static final String EXTRA_SPORT_TYPE = "sport_type";
    private static final String EXTRA_USER_ID = "user_Id";
    public static final int REQUEST_CODE = 1001;
    public static final int RESULT_CODE = 1002;

    @BindView(R.id.bv_swim_item_five)
    BottomSportView bvSwimItemFive;

    @BindView(R.id.bv_swim_item_four)
    BottomSportView bvSwimItemFour;

    @BindView(R.id.bv_swim_item_one)
    BottomSportView bvSwimItemOne;

    @BindView(R.id.bv_swim_item_six)
    BottomSportView bvSwimItemSix;

    @BindView(R.id.bv_swim_item_three)
    BottomSportView bvSwimItemThree;

    @BindView(R.id.bv_swim_item_two)
    BottomSportView bvSwimItemTwo;

    @BindView(R.id.chart_frequency)
    SportSpeedChart chartFrequency;

    @BindView(R.id.chart_item_avg_frequency)
    BottomSportView chartItemAvgFrequency;

    @BindView(R.id.chart_item_avg_pace)
    BottomSportView chartItemAvgPace;

    @BindView(R.id.chart_item_avg_swolf)
    BottomSportView chartItemAvgSwolf;

    @BindView(R.id.chart_item_max_frequency)
    BottomSportView chartItemMaxFrequency;

    @BindView(R.id.chart_item_max_pace)
    BottomSportView chartItemMaxPace;

    @BindView(R.id.chart_item_max_swolf)
    BottomSportView chartItemMaxSwolf;

    @BindView(R.id.chart_pace)
    SportSpeedChart chartPace;

    @BindView(R.id.chart_swolf)
    SportSpeedChart chartSwolf;

    @BindView(R.id.iv_sport)
    ImageView ivSport;

    @BindView(R.id.iv_sport_about)
    ImageView ivSportAbout;
    private String mDateTime;

    @BindView(R.id.durationLayout)
    View mDurationLayout;

    @BindView(R.id.title_bar)
    FrameLayout mFlTitleBar;

    @BindView(R.id.iv_share)
    ImageView mIvShare;

    @BindView(R.id.layout_network_error)
    LinearLayout mLayoutNetworkError;

    @BindView(R.id.layout_sport_retry)
    LinearLayout mLayoutSportRetry;

    @BindView(R.id.ll_share_title)
    LinearLayout mLlShareTitle;

    @BindView(R.id.ll_sport_content)
    LinearLayout mLlSportContent;

    @BindView(R.id.ll_sport_root)
    LinearLayout mLlSportRoot;

    @BindView(R.id.ll_sport_suggest)
    LinearLayout mLlSportSuggest;
    private SportSwimContract.Presenter mPresenter;

    @BindView(R.id.rv_qr_code)
    RelativeLayout mRvQrCode;

    @BindView(R.id.sportNameTitleLayout)
    View mSportNameTitleLayout;
    private int mSportType;

    @BindView(R.id.title_rightBtn)
    ImageButton mTitleRightBtn;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @BindView(R.id.tv_retry)
    TextView mTvRetry;
    private long mUserId = RunTimeUtil.getInstance().getUserId();

    @BindView(R.id.rl_chart_frequency)
    RelativeLayout rlChartFrequency;

    @BindView(R.id.rl_chart_pace)
    RelativeLayout rlChartPace;

    @BindView(R.id.rl_chart_swolf)
    RelativeLayout rlChartSwolf;

    @BindView(R.id.rl_pool_performance)
    RelativeLayout rlPoolPerformance;

    @BindView(R.id.ll_swim_indoor_item)
    LinearLayout rlSwimIndoorItem;

    @BindView(R.id.tv_frequency_value)
    TextView tvFrequencyValue;

    @BindView(R.id.tv_rate)
    TextView tvHr;

    @BindView(R.id.tv_more)
    TextView tvMore;

    @BindView(R.id.tv_pace_value)
    TextView tvPaceValue;

    @BindView(R.id.tv_sport_indoor_name)
    RegularTextView tvSportName;

    @BindView(R.id.tv_start_time)
    RegularTextView tvSportTimeStart;

    @BindView(R.id.tv_swolf_value)
    TextView tvSwolfValue;

    @BindView(R.id.tv_total_second)
    TextView tvTotalTime;

    @BindView(R.id.tv_calorie)
    TextView tvcalory;

    @BindView(R.id.uv_distance)
    UnitView uvCategory;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_sport_swim;
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void hideOpenWaterSwimView() {
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void showSportDataView(boolean z) {
    }

    public static void start(Context context, int i, String str, long j) {
        Intent intent = new Intent(context, (Class<?>) SportSwimActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("sport_type", i);
        bundle.putString(EXTRA_SPORT_DATA, str);
        bundle.putLong(EXTRA_USER_ID, j);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void startActivityResult(Activity activity, int i, String str, long j) {
        Intent intent = new Intent(activity, (Class<?>) SportSwimActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("sport_type", i);
        bundle.putString(EXTRA_SPORT_DATA, str);
        bundle.putLong(EXTRA_USER_ID, j);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, 1001);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), true);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initViews() {
        super.initViews();
        this.mFlTitleBar.setBackgroundColor(ResourceUtil.getColor(R.color.color_1E1E1E));
        this.tvHr.setVisibility(8);
        this.mSportNameTitleLayout.setBackgroundColor(ResourceUtil.getColor(R.color.color_1E1E1E));
        this.mDurationLayout.setBackgroundColor(ResourceUtil.getColor(R.color.color_1E1E1E));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        hideShareView();
    }

    private void hideShareView() {
        this.mLlSportSuggest.setVisibility(0);
        this.mRvQrCode.setVisibility(8);
        this.mLlShareTitle.setVisibility(8);
        this.mFlTitleBar.setVisibility(0);
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void hidePoolSwimView() {
        this.rlSwimIndoorItem.setVisibility(8);
        this.rlPoolPerformance.setVisibility(8);
        this.rlChartPace.setVisibility(8);
        this.rlChartFrequency.setVisibility(8);
        this.rlChartSwolf.setVisibility(8);
    }

    @OnClick({R.id.iv_share})
    public void toShare(View view) {
        showLoading();
        this.mLlSportSuggest.setVisibility(8);
        this.mRvQrCode.setVisibility(0);
        this.mLlShareTitle.setVisibility(0);
        this.mFlTitleBar.setVisibility(8);
        new Handler().postDelayed(new Runnable() { // from class: com.ido.life.module.sport.history.swim.SportSwimActivity.1
            @Override // java.lang.Runnable
            public void run() {
                SportSwimActivity.this.hideLoading();
                new ShareUtil().shotLongScreen(SportSwimActivity.this.mLlSportContent, SportSwimActivity.this.mLlSportContent);
                final SportShareDialogFragment sportShareDialogFragmentNewInstance = SportShareDialogFragment.newInstance();
                sportShareDialogFragmentNewInstance.setCancelable(false);
                sportShareDialogFragmentNewInstance.show(SportSwimActivity.this.getSupportFragmentManager());
                sportShareDialogFragmentNewInstance.setOnShareResultListener(new SportShareDialogFragment.OnShareChooseListener() { // from class: com.ido.life.module.sport.history.swim.SportSwimActivity.1.1
                    @Override // com.ido.life.dialog.SportShareDialogFragment.OnShareChooseListener
                    public void onSharePlatChoose(int i) {
                        SportSwimActivity.this.mLlSportSuggest.setVisibility(0);
                        SportSwimActivity.this.mRvQrCode.setVisibility(8);
                        SportSwimActivity.this.mLlShareTitle.setVisibility(8);
                        SportSwimActivity.this.mFlTitleBar.setVisibility(0);
                        sportShareDialogFragmentNewInstance.dismissAllowingStateLoss();
                    }
                });
            }
        }, 1000L);
    }

    @OnClick({R.id.title_leftBtn, R.id.tv_more, R.id.tv_data_feedback, R.id.iv_sport_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_sport_about /* 2131362616 */:
                SwimNameExplainDialogFragment.newInstance().show(getSupportFragmentManager());
                break;
            case R.id.title_leftBtn /* 2131363702 */:
                ActivityCompat.finishAfterTransition(this);
                break;
            case R.id.tv_data_feedback /* 2131363873 */:
                startActivity(new Intent(this, (Class<?>) FeedbackActivity.class));
                break;
            case R.id.tv_more /* 2131364036 */:
                SportExplainActivity.start(this, LanguageUtil.getLanguageText(R.string.get_more), LanguageUtil.getLanguageText(R.string.sport_type_swolf), LanguageUtil.getLanguageText(R.string.swolf_introduce));
                break;
        }
    }

    @OnClick({R.id.title_rightBtn})
    public void toDelete(View view) {
        SportDeletePopupWindow sportDeletePopupWindow = new SportDeletePopupWindow(this);
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        sportDeletePopupWindow.showAtLocation(view, BadgeDrawable.TOP_END, DipPixelUtil.dip2px(18.0f), iArr[1] + DipPixelUtil.dip2px(30.0f));
        sportDeletePopupWindow.setOnItemClickListener(new SportDeletePopupWindow.OnItemClickListener() { // from class: com.ido.life.module.sport.history.swim.SportSwimActivity.2
            @Override // com.ido.life.module.sport.view.SportDeletePopupWindow.OnItemClickListener
            public void onDeleteClick(View view2) {
                SportSwimActivity sportSwimActivity = SportSwimActivity.this;
                sportSwimActivity.showConfirmDelete(sportSwimActivity.mDateTime);
            }
        });
    }

    @OnClick({R.id.tv_retry})
    public void toRetry(View view) {
        this.mPresenter.getSwimSportData(this.mDateTime);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showConfirmDelete(final String str) {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(ResourceUtil.getString(R.string.sport_detail_confirm_delete_content), ResourceUtil.getString(R.string.sport_detail_confirm_delete_confirm), ResourceUtil.getString(R.string.sport_detail_confirm_delete_cancel), true);
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.history.swim.SportSwimActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.history.swim.SportSwimActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                SportSwimActivity.this.mPresenter.deleteRecord(str, "");
            }
        });
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.ivSportAbout.setVisibility(8);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mDateTime = extras.getString(EXTRA_SPORT_DATA);
            this.mSportType = extras.getInt("sport_type");
            this.mUserId = extras.getLong(EXTRA_USER_ID);
        }
        this.mPresenter = new SportSwimPresenter(this, this.mUserId);
        this.mPresenter.getSwimNameByType(this.mSportType);
        this.mPresenter.getSwimSportData(this.mDateTime);
        this.ivSport.setImageDrawable(ResourceUtil.getDrawable(MotionTypeManager.INSTANCE.getSportDetailTypeIcon(this.mSportType)));
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(SportSwimContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void setSwimName(String str) {
        this.tvSportName.setText(str);
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void setOpenWaterData(String str, int i, int i2) {
        this.tvSportTimeStart.setText(TimeUtil.formatTime(str));
        this.tvTotalTime.setText(DateUtil.computeTimeHMS(i2));
        this.tvTotalTime.setVisibility(0);
        this.uvCategory.setUnitText(SportUnitUtil.getCalorieUnit());
        this.uvCategory.setDataText(SportUnitUtil.getCarieValue(i) + "");
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void setIndoorSwimNormalData(SportHealth sportHealth) {
        this.ivSportAbout.setVisibility(0);
        try {
            this.tvSportTimeStart.setText(TimeUtil.formatTime(sportHealth.getStartTime()));
            this.uvCategory.setDataText(SportUnitUtil.getShowDistance(SportUnitUtil.isWalkOrRunKm(), sportHealth.getDistance()));
            this.uvCategory.setUnitText(SportUnitUtil.getPaceUnit(SportUnitUtil.isWalkOrRunKm()));
            this.tvTotalTime.setText(DateUtil.computeTimeHMS(sportHealth.getTotalSeconds()));
            this.tvTotalTime.setVisibility(0);
            this.tvcalory.setText(SportUnitUtil.getCarieValue(sportHealth.getNumCalories()) + SportUnitUtil.getCalorieUnit());
            this.tvcalory.setVisibility(0);
            this.bvSwimItemOne.setDataText(sportHealth.getSwimmingPostureName());
            this.bvSwimItemTwo.setDataText(sportHealth.getTotalStrokesNumber() + "");
            this.bvSwimItemThree.setDataText(sportHealth.getAvgFrequency() + "");
            this.bvSwimItemFour.setDataText(sportHealth.getTrips() + "");
            this.bvSwimItemFive.setDataText(DateUtil.computeTimePace(NumUtil.save2Point((float) (((double) sportHealth.getAvgPace()) / 60.0d))));
            this.bvSwimItemFive.setUnitText("100" + LanguageUtil.getLanguageText(R.string.unit_mi));
            this.bvSwimItemSix.setDataText(sportHealth.getAverageSWOLF() + "");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void setPaceChartView(List<String> list, List<String> list2, List<BaseCharBean> list3, int i, int i2) {
        if (list != null && list.size() != 0 && list2 != null && list2.size() != 0) {
            this.chartPace.setLabelXList(list);
        }
        this.chartPace.setLabelYLeftList(list2);
        this.chartPace.setXMaxValue(i);
        this.chartPace.setYMaxValue(i2);
        this.chartPace.setList(list3);
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void setPeaceVisible(boolean z) {
        this.rlChartPace.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void setFrequencyChartView(List<String> list, List<String> list2, List<BaseCharBean> list3, int i, int i2) {
        this.chartFrequency.setLabelXList(list);
        this.chartFrequency.setLabelYLeftList(list2);
        this.chartFrequency.setXMaxValue(i);
        this.chartFrequency.setYMaxValue(i2);
        this.chartFrequency.setList(list3);
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void setFrequencyChartVisible(boolean z) {
        this.rlChartFrequency.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void setSwolfChartView(List<String> list, List<String> list2, List<BaseCharBean> list3, int i, int i2) {
        this.chartSwolf.setLabelXList(list);
        this.chartSwolf.setLabelYLeftList(list2);
        this.chartSwolf.setXMaxValue(i);
        this.chartSwolf.setYMaxValue(i2);
        this.chartSwolf.setList(list3);
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void setSwolfChartVisible(boolean z) {
        this.rlChartSwolf.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void toBack() {
        setResult(1002, new Intent());
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void showLoading() {
        WaitingDialog.showDialog(this);
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void showSportNoNet(boolean z) {
        this.mLayoutNetworkError.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void showSportRetryView(boolean z) {
        this.mLayoutSportRetry.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void setLoadLoadTitleShow(boolean z) {
        this.mTitleText.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void setLoadTitleText(String str) {
        this.mTitleText.setText(str);
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void showRightBtn(boolean z) {
        this.mTitleRightBtn.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void setShareView(boolean z) {
        this.mIvShare.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.View
    public void setRightButtonView(boolean z) {
        this.mTitleRightBtn.setVisibility(z ? 0 : 8);
    }
}