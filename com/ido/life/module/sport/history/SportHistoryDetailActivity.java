package com.ido.life.module.sport.history;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.bean.LatLngBean;
import com.ido.life.customview.SportDetailScrollView;
import com.ido.life.customview.TrackPointView;
import com.ido.life.customview.WeightSeekBar;
import com.ido.life.customview.charter.CubicSportChartBar;
import com.ido.life.customview.charter.CubicSportStepChartBar;
import com.ido.life.customview.viewgroup.BottomSportView;
import com.ido.life.data.health.remote.HealthManager;
import com.ido.life.database.model.SportHealth;
import com.ido.life.dialog.SportShareDialogFragment;
import com.ido.life.module.sport.bean.HistoryRecordDetailsData;
import com.ido.life.module.sport.bean.PieChartBean;
import com.ido.life.module.sport.explain.SportExplainLocusActivity;
import com.ido.life.module.sport.history.SportHistoryDetailContract;
import com.ido.life.module.sport.map.BaseMap;
import com.ido.life.module.sport.map.MapFactory;
import com.ido.life.module.sport.map.MapScreenShotCallback;
import com.ido.life.module.sport.map.OnMapLoadedListener;
import com.ido.life.module.sport.util.BigDecimalUtil;
import com.ido.life.module.sport.view.SportDeletePopupWindow;
import com.ido.life.module.sport.view.SportDistanceView;
import com.ido.life.module.sport.view.SportPieChart;
import com.ido.life.module.user.feedback.FeedbackActivity;
import com.ido.life.util.DateUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.ShareUtil;
import com.ido.life.util.SportDataUtil;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportHistoryDetailActivity extends BaseCoreActivity implements SportHistoryDetailContract.View {
    private static final String EXTRA_DATE = "extra_date";
    private static final String EXTRA_FROM_TYPE = "from_type";
    private static final String EXTRA_SPORT_LATLNG_LIST = "sport_latlng_list";
    private static final String EXTRA_SPORT_TYPE = "sport_type";
    public static final int REQUEST_CODE = 1001;
    public static final int RESULT_CODE = 1002;
    private static final String TAG = "SportHistoryDetailActivity";
    private Handler handler;

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
    CubicSportStepChartBar mCubicChartStep;
    private String mDateTime;
    private int mDistance;
    private int mDuration;

    @BindView(R.id.ib_delete)
    ImageView mIbDelete;
    private boolean mIsGoogleLoad;

    @BindView(R.id.iv_share)
    ImageView mIvShare;
    private List<LatLngBean> mLatLngBeanList;

    @BindView(R.id.layout_network_error)
    LinearLayout mLayoutNetworkError;

    @BindView(R.id.layout_sport_retry)
    LinearLayout mLayoutSportRetry;

    @BindView(R.id.ll_bottom)
    LinearLayout mLlBottom;

    @BindView(R.id.ll_share_title)
    LinearLayout mLlShareTitle;

    @BindView(R.id.ll_sport_detail)
    LinearLayout mLlSportDetail;

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
    LinearLayout mLlSportSpace;

    @BindView(R.id.ll_sport_detail_speed_item)
    LinearLayout mLlSportSpeedItem;

    @BindView(R.id.ll_sport_suggest)
    LinearLayout mLlSportSuggest;

    @BindView(R.id.pc_sport)
    SportPieChart mPcSport;
    private SportHistoryDetailContract.Presenter mPresenter;

    @BindView(R.id.rl_map_root)
    FrameLayout mRlMapRoot;

    @BindView(R.id.rv_map_loading)
    RelativeLayout mRvMapLoading;

    @BindView(R.id.rv_map_root)
    RelativeLayout mRvMapRoot;

    @BindView(R.id.rv_sport_title)
    RelativeLayout mRvSportTitle;

    @BindView(R.id.scrollView)
    SportDetailScrollView mScrollView;

    @BindView(R.id.seekBar_step_number)
    WeightSeekBar mSeekBarStepNumber;
    private int mSportType;

    @BindView(R.id.title_bar)
    FrameLayout mTitleBar;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @BindView(R.id.tv_aerobic_endurance)
    TextView mTvAerobicEndurance;

    @BindView(R.id.tv_burning_heart_rate)
    TextView mTvBurningHeartRate;

    @BindView(R.id.tv_distance)
    MediumTextView mTvDistance;

    @BindView(R.id.tv_distance_unit)
    RegularTextView mTvDistanceUnit;

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

    @BindView(R.id.tv_sport_pace_unit)
    TextView mTvSportPaceUnit;

    @BindView(R.id.tv_sport_pace_title)
    TextView mTvSportSpaceTitle;

    @BindView(R.id.tv_sport_item_speed_km)
    TextView mTvSportSpeedKm;

    @BindView(R.id.tv_sport_speed_unit)
    TextView mTvSportSpeedUnit;

    @BindView(R.id.tv_time_start)
    RegularTextView mTvSportTimeStart;

    @BindView(R.id.tv_target_diff)
    RegularTextView mTvTargetDiff;

    @BindView(R.id.tv_warm_up_heart)
    TextView mTvWarmUpHeart;
    private int mType;
    protected BaseMap mapModel;
    protected View mapView;
    View mapView_view;

    @BindView(R.id.track_point_view)
    TrackPointView trackPointView;
    private int mFromType = 0;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mIsScreenMap = false;
    private Runnable mapScreenRunable = new Runnable() { // from class: com.ido.life.module.sport.history.SportHistoryDetailActivity.1
        @Override // java.lang.Runnable
        public void run() {
            SportHistoryDetailActivity.this.mapModel.onMapScreenShot(new MapScreenShotCallback() { // from class: com.ido.life.module.sport.history.SportHistoryDetailActivity.1.1
                @Override // com.ido.life.module.sport.map.MapScreenShotCallback
                public void shotComplet(Bitmap bitmap) {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), SportHistoryDetailActivity.TAG, "shotComplet: 截图完成");
                    if (SportHistoryDetailActivity.this.mapModel == null || SportHistoryDetailActivity.this.mLatLngBeanList == null || SportHistoryDetailActivity.this.mLatLngBeanList.size() < 1) {
                        return;
                    }
                    SportHistoryDetailActivity.this.mapModel.addDynamicStartMark((LatLngBean) SportHistoryDetailActivity.this.mLatLngBeanList.get(0), R.mipmap.ic_sport_map_detail_start);
                    SportHistoryDetailActivity.this.mapModel.addDynamicEndMark((LatLngBean) SportHistoryDetailActivity.this.mLatLngBeanList.get(SportHistoryDetailActivity.this.mLatLngBeanList.size() - 1), R.mipmap.ic_sport_map_detail_end);
                    SportHistoryDetailActivity.this.mPresenter.saveSportData(SportHistoryDetailActivity.this.mDateTime, SportHistoryDetailActivity.this.getShotFilePath());
                    SportHistoryDetailActivity.this.mRvMapLoading.setVisibility(8);
                }
            });
        }
    };
    private Runnable mapDrawableRunnable = new Runnable() { // from class: com.ido.life.module.sport.history.SportHistoryDetailActivity.2
        @Override // java.lang.Runnable
        public void run() {
            SportHistoryDetailActivity.this.mIsGoogleLoad = false;
            SportHealth sportHealth = HealthManager.getInstance().getSportHealth(SportHistoryDetailActivity.this.mDateTime);
            if (sportHealth != null && (TextUtils.isEmpty(sportHealth.getIcon()) || !sportHealth.getIcon().contains("http"))) {
                SportHistoryDetailActivity.this.mIsScreenMap = true;
                SportHistoryDetailActivity.this.mRvMapLoading.setVisibility(0);
            }
            if (SportHistoryDetailActivity.this.mapModel == null || SportHistoryDetailActivity.this.mPresenter == null) {
                return;
            }
            SportHistoryDetailActivity.this.mIsGoogleLoad = false;
            List<Point> screenLocation = SportHistoryDetailActivity.this.mapModel.toScreenLocation(SportHistoryDetailActivity.this.mapModel.latLngBeanList);
            if (screenLocation != null) {
                SportHistoryDetailActivity.this.mapModel.setGesturesEnabled(false);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), SportHistoryDetailActivity.TAG, "run: 加载动态轨迹");
                if (SportHistoryDetailActivity.this.mIsScreenMap) {
                    SportHistoryDetailActivity.this.trackPointView.setDetail(false);
                } else {
                    SportHistoryDetailActivity.this.trackPointView.setDetail(true);
                }
                SportHistoryDetailActivity.this.trackPointView.drawSportLine(screenLocation, R.mipmap.ic_sport_map_detail_start, R.mipmap.ic_sport_map_detail_end, new AnonymousClass1());
            }
        }

        /* JADX INFO: renamed from: com.ido.life.module.sport.history.SportHistoryDetailActivity$2$1, reason: invalid class name */
        class AnonymousClass1 implements TrackPointView.OnTrailChangeListener {
            AnonymousClass1() {
            }

            @Override // com.ido.life.customview.TrackPointView.OnTrailChangeListener
            public void onFinish() {
                if (SportHistoryDetailActivity.this.mPresenter == null || SportHistoryDetailActivity.this.mapModel == null) {
                    return;
                }
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), SportHistoryDetailActivity.TAG, "run: 动态轨迹加载完成");
                if (SportHistoryDetailActivity.this.mFromType == 0) {
                    SportHistoryDetailActivity.this.mIvShare.setVisibility(8);
                } else {
                    SportHistoryDetailActivity.this.mIvShare.setVisibility(0);
                }
                if (SportHistoryDetailActivity.this.mapModel == null) {
                    return;
                }
                SportHistoryDetailActivity.this.mapModel.setGesturesEnabled(true);
                SportHistoryDetailActivity.this.mapModel.drawAllAndShot(1L, SportHistoryDetailActivity.this.mType, SportHistoryDetailActivity.this.mDuration, SportHistoryDetailActivity.this.mDistance, SportHistoryDetailActivity.this.mIsScreenMap, new BaseMap.IDrawFinish() { // from class: com.ido.life.module.sport.history.-$$Lambda$SportHistoryDetailActivity$2$1$IRBtgNS8p6rsfbiBIr2BVg1ZVkA
                    @Override // com.ido.life.module.sport.map.BaseMap.IDrawFinish
                    public final void onDrawFinish() {
                        this.f$0.lambda$onFinish$0$SportHistoryDetailActivity$2$1();
                    }
                });
                SportHistoryDetailActivity.this.mapModel.getGoogleMap(new OnMapLoadedListener() { // from class: com.ido.life.module.sport.history.-$$Lambda$SportHistoryDetailActivity$2$1$zu77_P62A1-Xr5z32N7aAjdAimk
                    @Override // com.ido.life.module.sport.map.OnMapLoadedListener
                    public final void onMapLoad(boolean z) {
                        this.f$0.lambda$onFinish$2$SportHistoryDetailActivity$2$1(z);
                    }
                });
            }

            public /* synthetic */ void lambda$onFinish$0$SportHistoryDetailActivity$2$1() {
                SportHistoryDetailActivity.this.mIsGoogleLoad = true;
                if (SportHistoryDetailActivity.this.trackPointView != null) {
                    SportHistoryDetailActivity.this.trackPointView.setVisibility(8);
                }
                if (SportHistoryDetailActivity.this.mIsScreenMap) {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), SportHistoryDetailActivity.TAG, "run: 开始截图");
                    SportHistoryDetailActivity.this.saveAndUploadSportSmallPic(SportHistoryDetailActivity.this.mType, SportHistoryDetailActivity.this.mDuration, SportHistoryDetailActivity.this.mDistance);
                }
            }

            public /* synthetic */ void lambda$onFinish$2$SportHistoryDetailActivity$2$1(boolean z) {
                if (!z || SportHistoryDetailActivity.this.mIsGoogleLoad) {
                    return;
                }
                SportHistoryDetailActivity.this.mapModel.drawAllAndShot(1L, SportHistoryDetailActivity.this.mType, SportHistoryDetailActivity.this.mDuration, SportHistoryDetailActivity.this.mDistance, SportHistoryDetailActivity.this.mIsScreenMap, new BaseMap.IDrawFinish() { // from class: com.ido.life.module.sport.history.-$$Lambda$SportHistoryDetailActivity$2$1$li2J0cdQ3-c9X3qYt3_DDb6d1DY
                    @Override // com.ido.life.module.sport.map.BaseMap.IDrawFinish
                    public final void onDrawFinish() {
                        this.f$0.lambda$null$1$SportHistoryDetailActivity$2$1();
                    }
                });
            }

            public /* synthetic */ void lambda$null$1$SportHistoryDetailActivity$2$1() {
                if (SportHistoryDetailActivity.this.trackPointView != null) {
                    SportHistoryDetailActivity.this.trackPointView.setVisibility(8);
                }
            }
        }
    };

    private void hideStatusBar() {
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_sport_history_detail;
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSportItemPaceFasterUnit(String str) {
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSportItemSpeedTitle(String str) {
    }

    public static void startActivity(Context context, int i, String str, int i2) {
        Intent intent = new Intent(context, (Class<?>) SportHistoryDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("extra_date", str);
        bundle.putInt("sport_type", i);
        bundle.putInt("from_type", i2);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void startActivityResult(Activity activity, int i, String str, int i2) {
        Intent intent = new Intent(activity, (Class<?>) SportHistoryDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("extra_date", str);
        bundle.putInt("sport_type", i);
        bundle.putInt("from_type", i2);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, 1001);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        initMap(bundle);
        initMapData();
        updateData();
    }

    private void initMap(Bundle bundle) {
        this.mapView = MapFactory.getMapView(this);
        this.mapModel = MapFactory.getMap();
        this.mapModel.setActivity(this);
        this.mapModel.initMapView(this.mapView);
        this.mapModel.onCreate(bundle);
        this.mapView_view = findViewById(R.id.rl_map_root);
        ViewGroup.LayoutParams layoutParams = this.mRlMapRoot.getLayoutParams();
        layoutParams.height = DipPixelUtil.dip2px(350.0f);
        this.mapView_view.setLayoutParams(layoutParams);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mapModel.onResume();
        hideStatusBar();
        hideShareView();
    }

    private void hideShareView() {
        this.mLlSportSuggest.setVisibility(0);
        this.mLlShareTitle.setVisibility(8);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mapModel.onPause();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mPcSport.setShowCenterText(LanguageUtil.getLanguageText(R.string.sport_range_heart_rate));
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
    }

    private void updateData() {
        this.mPresenter = new SportHistoryDetailPresenter(this);
        if (this.mFromType == 0) {
            this.mIvShare.setVisibility(8);
            this.mIbDelete.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_share));
        } else {
            this.mIvShare.setVisibility(8);
            this.mIbDelete.setVisibility(0);
        }
        this.mPresenter.getSportNameByType(this.mSportType);
        if (!TextUtils.isEmpty(this.mDateTime)) {
            this.mPresenter.getSportDataByDateTime(this.mDateTime);
        }
        this.mScrollView.smoothScrollTo(0, 0);
        this.mapModel.setIsRunMap(false);
        this.mapModel.setAddCircle(false);
    }

    protected void initMapData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mDateTime = extras.getString("extra_date");
            this.mFromType = extras.getInt("from_type");
            this.mSportType = extras.getInt("sport_type");
        }
    }

    @OnClick({R.id.ib_back})
    public void toBack(View view) {
        SportHistoryDetailContract.Presenter presenter = this.mPresenter;
        if (presenter != null && presenter.needRefreshSportRecordList()) {
            toBack();
        } else {
            ActivityCompat.finishAfterTransition(this);
        }
    }

    @OnClick({R.id.iv_share})
    public void toShare(View view) {
        toShare();
    }

    @OnClick({R.id.tv_retry})
    public void reTry(View view) {
        this.mPresenter.getSportDataByDateTime(this.mDateTime);
    }

    @OnClick({R.id.title_leftBtn})
    public void toLoadingBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @OnClick({R.id.iv_locus_explain})
    public void toExplainLocus(View view) {
        SportExplainLocusActivity.start(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveAndUploadSportSmallPic(int i, int i2, int i3) {
        TrackPointView trackPointView;
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mapScreenRunable);
        }
        List<LatLngBean> list = this.mLatLngBeanList;
        if (list == null || list.size() == 0 || (trackPointView = this.trackPointView) == null) {
            return;
        }
        trackPointView.setVisibility(8);
        this.mapModel.setStartTime(DateUtil.getLongFromDateStr(this.mDateTime));
        Handler handler2 = this.mHandler;
        if (handler2 != null) {
            handler2.postDelayed(this.mapScreenRunable, 2000L);
        }
    }

    protected String getShotFilePath() {
        File file = new File(LogPathImpl.getInstance().getPicPath());
        if (!file.exists()) {
            file.mkdirs();
        }
        return LogPathImpl.getInstance().getPicPath() + DateUtil.getLongFromDateStr(this.mDateTime) + ".png";
    }

    private void toShare() {
        WaitingDialog.showDialog(this);
        this.mLlSportSuggest.setVisibility(8);
        this.mLlShareTitle.setVisibility(8);
        new ShareUtil().shotWithMapView(this.mapModel, this.mRvMapRoot, false, this.mLlBottom, this.mLlShareTitle);
        new Handler().postDelayed(new Runnable() { // from class: com.ido.life.module.sport.history.SportHistoryDetailActivity.3
            @Override // java.lang.Runnable
            public void run() {
                WaitingDialog.hideDialog();
                final SportShareDialogFragment sportShareDialogFragmentNewInstance = SportShareDialogFragment.newInstance();
                sportShareDialogFragmentNewInstance.setCancelable(false);
                sportShareDialogFragmentNewInstance.show(SportHistoryDetailActivity.this.getSupportFragmentManager());
                sportShareDialogFragmentNewInstance.setOnShareResultListener(new SportShareDialogFragment.OnShareChooseListener() { // from class: com.ido.life.module.sport.history.SportHistoryDetailActivity.3.1
                    @Override // com.ido.life.dialog.SportShareDialogFragment.OnShareChooseListener
                    public void onSharePlatChoose(int i) {
                        SportHistoryDetailActivity.this.mLlSportSuggest.setVisibility(0);
                        SportHistoryDetailActivity.this.mLlShareTitle.setVisibility(8);
                        sportShareDialogFragmentNewInstance.dismissAllowingStateLoss();
                    }
                });
            }
        }, 1000L);
    }

    @OnClick({R.id.tv_data_feedback})
    public void toDataFeedback(View view) {
        startActivity(new Intent(this, (Class<?>) FeedbackActivity.class));
    }

    @OnClick({R.id.ib_delete})
    public void toDelete(View view) {
        if (this.mFromType == 0) {
            toShare();
            return;
        }
        SportDeletePopupWindow sportDeletePopupWindow = new SportDeletePopupWindow(this);
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        sportDeletePopupWindow.showAtLocation(view, BadgeDrawable.TOP_END, DipPixelUtil.dip2px(18.0f), iArr[1] + DipPixelUtil.dip2px(30.0f));
        sportDeletePopupWindow.setOnItemClickListener(new SportDeletePopupWindow.OnItemClickListener() { // from class: com.ido.life.module.sport.history.SportHistoryDetailActivity.4
            @Override // com.ido.life.module.sport.view.SportDeletePopupWindow.OnItemClickListener
            public void onDeleteClick(View view2) {
                SportHistoryDetailActivity sportHistoryDetailActivity = SportHistoryDetailActivity.this;
                sportHistoryDetailActivity.showConfirmDelete(sportHistoryDetailActivity.mDateTime);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showConfirmDelete(final String str) {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(ResourceUtil.getString(R.string.sport_detail_confirm_delete_content), ResourceUtil.getString(R.string.sport_detail_confirm_delete_confirm), ResourceUtil.getString(R.string.sport_detail_confirm_delete_cancel), true);
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.history.SportHistoryDetailActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.history.SportHistoryDetailActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                SportHistoryDetailActivity.this.mPresenter.deleteRecord(str);
            }
        });
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void showLoading() {
        WaitingDialog.showDialog(this);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSportName(String str) {
        this.mTvSportName.setText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSportRateMax(String str) {
        this.mBvChartSportRateMax.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSportChartRateAvg(String str) {
        this.mBvChartSportRateAverage.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSportStartTime(String str) {
        this.mTvSportTimeStart.setText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSportDistance(String str) {
        this.mTvDistance.setText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSportDistanceUnit(String str) {
        this.mTvDistanceUnit.setText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setHeartPieChart(List<PieChartBean> list) {
        this.mPcSport.setData(list);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setMaxExerciseTime(String str) {
        this.mTvMaxExerciseTime.setText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setNoEndurance(String str) {
        this.mTvNoEndurance.setText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setAerobicEndurance(String str) {
        this.mTvAerobicEndurance.setText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setBurningHeartRate(String str) {
        this.mTvBurningHeartRate.setText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setWarmUpHeart(String str) {
        this.mTvWarmUpHeart.setText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void toBack() {
        setResult(1002, new Intent());
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setYLabelList(List<String> list) {
        this.mCubicChart.setLabelYRightList(list);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setXLabelList(List<String> list) {
        this.mCubicChart.setLabelXList(list);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setRateList(List<BaseCharBean> list) {
        this.mCubicChart.setList(list);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setRateXMinValue(int i) {
        this.mCubicChart.setXMinValue(i);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setRateXMaxValue(int i) {
        this.mCubicChart.setXMaxValue(i);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setRateYMaxValue(int i) {
        this.mCubicChart.setYMaxValue(i);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setRateYMinValue(int i) {
        this.mCubicChart.setYMinValue(i);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setKmSpace(List<HistoryRecordDetailsData> list, int i, int i2, boolean z) {
        String strComputeTimeMS;
        int iDiv;
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
            int i3 = 0;
            while (i3 < list.size()) {
                HistoryRecordDetailsData historyRecordDetailsData = list.get(i3);
                int paceSpeed2 = historyRecordDetailsData.getPaceSpeed();
                if (list.get(i3).getPaceSpeed() != 0) {
                    iDiv = (int) (BigDecimalUtil.div(list.get(i3).getPaceSpeed(), i2, 2) * d2);
                    strComputeTimeMS = DateUtil.computeTimeMS(paceSpeed2);
                } else {
                    strComputeTimeMS = "0.0";
                    iDiv = 0;
                }
                SportDistanceView sportDistanceView2 = new SportDistanceView(this);
                int i4 = i3 + 1;
                sportDistanceView2.setSportDistanceCount(String.valueOf(i4));
                CommonLogUtil.d(TAG, "setKmSpeed: " + iDiv + AppInfo.DELIM + strComputeTimeMS);
                if (i3 == list.size() - 1 && !z) {
                    this.mLlSportDistance.addView(getTextView(strComputeTimeMS));
                    b2 = -21112;
                } else {
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
                i3 = i4;
                d2 = 100.0d;
            }
        }
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
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
                CommonLogUtil.d(TAG, "setKmSpeed: " + speedMiByPeaceMi + AppInfo.DELIM + i + "" + speedMiByPeaceMi2 + AppInfo.DELIM + Double.valueOf(speedMiByPeaceMi2));
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
        textView.setPadding(DipPixelUtil.dip2px(53.0f), 0, 0, 0);
        textView.setGravity(16);
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void addPolylineAndMove(List<LatLngBean> list, int i, int i2, int i3) {
        TrackPointView trackPointView;
        if (this.mPresenter == null || (trackPointView = this.trackPointView) == null || this.mapModel == null || this.mHandler == null) {
            return;
        }
        this.mType = i;
        this.mDuration = i2;
        this.mDistance = i3;
        trackPointView.setVisibility(0);
        this.mapModel.setMapLoadFinish(false);
        this.mapModel.setLatLngBeanList(list);
        this.mLatLngBeanList = list;
        this.mapModel.ajustMapView();
        this.mHandler.removeCallbacks(this.mapDrawableRunnable);
        this.mHandler.postDelayed(this.mapDrawableRunnable, 1000L);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void addEndMarker(LatLngBean latLngBean) {
        this.mapModel.addEndMarker(latLngBean, R.mipmap.ic_sport_map_detail_end);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSportItemPaceAverage(String str) {
        this.mBvSportItemPaceAvg.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSportItemSpeedAverage(String str) {
        this.mTvSportAvgSpeed.setText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSportItemPaceAverageDesc(String str) {
        this.mBvSportItemPaceAvg.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSportItemPaceFaster(String str) {
        this.mBvSportItemPaceFaster.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSportItemPaceFasterDesc(String str) {
        this.mBvSportItemPaceFaster.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void showSportDataItemOne(boolean z) {
        this.mLlSportDetailOneItem.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void showSportDataItemTwo(boolean z) {
        this.mLlSportDetailTwoItem.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void showSportDataItemThree(boolean z) {
        this.mLlSportDetailThreeItem.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void showSportDataItemFour(boolean z) {
        this.mLlSportDetailFourItem.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setFirstItemValue(String str) {
        this.mBvSportItemOne.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setFirstItemDesc(String str) {
        this.mBvSportItemOne.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setFirstItemUnit(String str) {
        this.mBvSportItemOne.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSecondItemValue(String str) {
        this.mBvSportItemTwo.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSecondItemDesc(String str) {
        this.mBvSportItemTwo.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSecondItemUnit(String str) {
        this.mBvSportItemTwo.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setThreeItemValue(String str) {
        this.mBvSportItemThree.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setThreeItemDesc(String str) {
        this.mBvSportItemThree.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setThreeItemUnit(String str) {
        this.mBvSportItemThree.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setFourItemValue(String str) {
        this.mBvSportItemFour.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setFourItemDesc(String str) {
        this.mBvSportItemFour.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setFourItemUnit(String str) {
        this.mBvSportItemFour.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setFiveItemValue(String str) {
        this.mBvSportItemFive.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setFiveItemDesc(String str) {
        this.mBvSportItemFive.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setFiveItemUnit(String str) {
        this.mBvSportItemFive.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSixItemValue(String str) {
        this.mBvSportItemSix.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSixItemDesc(String str) {
        this.mBvSportItemSix.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSixItemUnit(String str) {
        this.mBvSportItemSix.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSevenItemValue(String str) {
        this.mBvSportItemSeven.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSevenItemDesc(String str) {
        this.mBvSportItemSeven.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSevenItemUnit(String str) {
        this.mBvSportItemSeven.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setEightItemValue(String str) {
        this.mBvSportItemEight.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setEightItemDesc(String str) {
        this.mBvSportItemEight.setDescribeText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setEightItemUnit(String str) {
        this.mBvSportItemEight.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void showSeekBarStepNum(boolean z) {
        this.mSeekBarStepNumber.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void showUserTargetDiff(boolean z) {
        this.mTvTargetDiff.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSeekBarProcess(int i, int i2) {
        this.mSeekBarStepNumber.setCurProgress(i);
        this.mSeekBarStepNumber.setMaxProgress(i2);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setTargetDiff(String str) {
        this.mTvTargetDiff.setText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void showSportItemRate(boolean z) {
        this.mLlSportDetailRate.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void showSportItemSpeed(boolean z) {
        this.mLlSportSpeedItem.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSportSpeedUnit(String str) {
        this.mTvSportSpeedUnit.setText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void showSportNoNet(boolean z) {
        this.mLayoutNetworkError.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void showSportRetryView(boolean z) {
        this.mLayoutSportRetry.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void showSportDataView(boolean z) {
        this.mScrollView.setVisibility(z ? 0 : 8);
        this.mRvSportTitle.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setLoadLoadTitleShow(boolean z) {
        this.mTitleBar.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setLoadTitleText(String str) {
        this.mTitleText.setText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSportItemSpeedTitleUnit(String str) {
        this.mTvSportSpeedUnit.setText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSportItemSpaceTitle(String str) {
        this.mTvSportSpaceTitle.setText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSportItemSpaceTitleUnit(String str) {
        this.mTvSportPaceUnit.setText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSportSpeedItemKm(String str) {
        this.mTvSportSpeedKm.setText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void showSportItemFrequency(boolean z) {
        this.mLlSportFrequencyItem.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setStepFrequencyAvg(String str) {
        this.mBvSportFrequencyAvg.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setStepFrequencyMax(String str) {
        this.mBvSportFrequencyMax.setDataText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setYStepLabelList(List<String> list) {
        this.mCubicChartStep.setLabelYLeftList(list);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setXStepLabelList(List<String> list) {
        this.mCubicChartStep.setLabelXList(list);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setStepFrequencyList(List<BaseCharBean> list) {
        this.mCubicChartStep.setList(list);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setStepXMinValue(int i) {
        this.mCubicChartStep.setXMinValue(i);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setStepXMaxValue(int i) {
        this.mCubicChartStep.setXMaxValue(i);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setStepYMaxValue(int i) {
        this.mCubicChartStep.setYMaxValue(i);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setStepYMinValue(int i) {
        this.mCubicChartStep.setYMinValue(i);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void setSportItemDistanceLabel(String str) {
        this.mTvSportItemDistance.setText(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.View
    public void showSportItemPace(boolean z) {
        this.mLlSportSpace.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(SportHistoryDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        SportHistoryDetailContract.Presenter presenter = this.mPresenter;
        if (presenter != null && presenter.needRefreshSportRecordList()) {
            setResult(1002, new Intent());
        }
        supportFinishAfterTransition();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.ido.life.module.sport.history.SportHistoryDetailActivity$7] */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.trackPointView = null;
        SportHistoryDetailContract.Presenter presenter = this.mPresenter;
        if (presenter != null) {
            presenter.clearSportTarget();
            this.mPresenter.destroy();
        }
        this.mPresenter = null;
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mapScreenRunable);
            this.mHandler.removeCallbacks(this.mapDrawableRunnable);
        }
        this.mHandler = null;
        BaseMap baseMap = this.mapModel;
        if (baseMap != null) {
            baseMap.setMapLoadFinish(true);
            new Thread() { // from class: com.ido.life.module.sport.history.SportHistoryDetailActivity.7
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    super.run();
                    if (SportHistoryDetailActivity.this.mapModel == null) {
                        return;
                    }
                    try {
                        try {
                            SportHistoryDetailActivity.this.mapModel.onDestroy();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } finally {
                        SportHistoryDetailActivity.this.mapModel = null;
                    }
                }
            }.start();
        }
    }
}