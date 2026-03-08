package com.ido.life.module.sport.run;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.StatusBarUtil;
import com.ido.life.bean.LatLngBean;
import com.ido.life.ble.BleSdkWrapper;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.viewgroup.SportControlView;
import com.ido.life.database.model.SportHealth;
import com.ido.life.log.SportLogHelper;
import com.ido.life.module.sport.history.detail.SportHistoryDetailNewActivity;
import com.ido.life.module.sport.map.BaseMap;
import com.ido.life.module.sport.map.MapFactory;
import com.ido.life.module.sport.run.SportRunContract;
import com.ido.life.module.sport.view.CustomGPSView;
import com.ido.life.module.sport.view.RunBottomView;
import com.ido.life.util.RunTimeUtil;

/* JADX INFO: loaded from: classes2.dex */
public class SportRunActivity extends BaseCoreActivity implements SportRunContract.View {
    private static final String EXTRA_SPORT_OUTDOOR = "sport_out_door";
    private static final String EXTRA_TYPE = "sport_type";
    private static final String TAG = "SportRunActivity";

    @BindView(R.id.gps_sport_view)
    CustomGPSView mGpsSportView;

    @BindView(R.id.gps_view_map)
    CustomGPSView mGpsViewMap;
    private boolean mIsOut;

    @BindView(R.id.iv_show_map)
    ImageView mIvShowMap;

    @BindView(R.id.iv_show_more)
    ImageView mIvShowMore;

    @BindView(R.id.iv_sport_sound)
    ImageView mIvSportSound;

    @BindView(R.id.ll_distance)
    LinearLayout mLlDistance;

    @BindView(R.id.ll_run_bottom)
    RelativeLayout mLlRunBottom;
    private SportRunContract.Presenter mPresenter;

    @BindView(R.id.rv_distance)
    RelativeLayout mRvDistance;

    @BindView(R.id.tv_sport_distance_map)
    RunBottomView mSportDistanceMap;

    @BindView(R.id.tv_sport_speed_map)
    RunBottomView mSportSpeedMap;

    @BindView(R.id.tv_sport_time_map)
    RunBottomView mSportTimeMap;
    private int mSportType;

    @BindView(R.id.tv_device_status)
    TextView mTvDeviceStatus;

    @BindView(R.id.tv_device_status_map)
    TextView mTvDeviceStatusMap;

    @BindView(R.id.tv_gps_status_desc)
    TextView mTvGpsStatusDesc;

    @BindView(R.id.tv_sport_calorie)
    RunBottomView mTvSportCalorie;

    @BindView(R.id.tv_sport_run_distance)
    TextView mTvSportRunDistance;

    @BindView(R.id.tv_sport_run_distance_unit)
    TextView mTvSportRunDistanceUnit;

    @BindView(R.id.tv_sport_speed)
    RunBottomView mTvSportSpeed;

    @BindView(R.id.tv_sport_status)
    TextView mTvSportStatus;

    @BindView(R.id.tv_sport_time)
    RunBottomView mTvSportTime;

    @BindView(R.id.tv_sport_type_name)
    TextView mTvSportTypeName;
    protected BaseMap mapModel;
    protected View mapView;

    @BindView(R.id.sportControlView)
    SportControlView sportControlView;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_sport_run;
    }

    public static void startActivity(Activity activity, int i, boolean z) {
        Intent intent = new Intent(activity, (Class<?>) SportRunActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("sport_type", i);
        bundle.putBoolean(EXTRA_SPORT_OUTDOOR, z);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        initView();
        initMap(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
    }

    private void initMap(Bundle bundle) {
        this.mapModel = MapFactory.getMap();
        this.mapModel.setActivity(this);
        this.mapModel.initMapView(this.mapView);
        this.mapModel.onCreate(bundle);
        this.mapModel.setIsHideMapView(false);
        this.mapModel.setIsRunMap(true);
    }

    private void initView() {
        this.mapView = MapFactory.getMapView(this);
    }

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode((Activity) this, false);
    }

    private void initListener() {
        this.mPresenter.setSportRunListener();
        this.sportControlView.setOnSportPauseListener(new SportControlView.OnSportPauseListener() { // from class: com.ido.life.module.sport.run.SportRunActivity.1
            @Override // com.ido.life.customview.viewgroup.SportControlView.OnSportPauseListener
            public void onRestart() {
                SportRunActivity.this.mPresenter.reStart();
            }

            @Override // com.ido.life.customview.viewgroup.SportControlView.OnSportPauseListener
            public void onPause() {
                SportRunActivity.this.mPresenter.pause();
            }

            @Override // com.ido.life.customview.viewgroup.SportControlView.OnSportPauseListener
            public void onStop() {
                SportLogHelper.saveSportLog(SportRunActivity.TAG, "停止运动。onStop");
                SportRunActivity.this.mPresenter.end();
            }

            @Override // com.ido.life.customview.viewgroup.SportControlView.OnSportPauseListener
            public void onSoundOnOff() {
                SportRunActivity.this.mPresenter.toSoundOffOrOn();
            }
        });
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mSportType = extras.getInt("sport_type", -1);
            this.mIsOut = extras.getBoolean(EXTRA_SPORT_OUTDOOR, true);
        }
        this.mPresenter = new SportRunPresenter(this, RunTimeUtil.getInstance().getUserId());
        this.mPresenter.start();
        this.mPresenter.isSportOutDoor(this.mIsOut);
        this.mPresenter.getSportNameByType(this.mSportType);
        this.mPresenter.initUserTarget();
        initListener();
        if (this.mPresenter.getInit()) {
            toBack();
        }
        this.mTvSportSpeed.setUnitText(LanguageUtil.getLanguageText(R.string.sport_speed_distribution));
        this.mTvSportTime.setUnitText(LanguageUtil.getLanguageText(R.string.sport_run_time_used));
        this.mSportTimeMap.setUnitText(LanguageUtil.getLanguageText(R.string.sport_run_time_used));
        this.mSportSpeedMap.setUnitText(LanguageUtil.getLanguageText(R.string.sport_speed_distribution));
        if (RunTimeUtil.getInstance().getUnitSet() == 1) {
            this.mTvSportRunDistanceUnit.setText(LanguageUtil.getLanguageText(R.string.sport_run_distance_unit));
        } else {
            this.mTvSportRunDistanceUnit.setText(LanguageUtil.getLanguageText(R.string.sport_run_distance_unit_mi));
        }
        if (BleSdkWrapper.isConnected()) {
            this.mTvSportCalorie.setUnitText(LanguageUtil.getLanguageText(R.string.sport_record_heart_rate));
            this.mTvSportCalorie.setDataText("--");
        } else {
            this.mTvSportCalorie.setUnitText(LanguageUtil.getLanguageText(R.string.sport_run_category));
            this.mTvSportCalorie.setDataText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mapModel.onSaveInstanceState(bundle);
        SportLogHelper.saveSportLog(TAG, "onSaveInstanceState: ");
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        SportLogHelper.saveSportLog(TAG, "onRestoreInstanceState: ");
        this.mPresenter.onRestoreInstanceState();
    }

    @OnClick({R.id.iv_show_map})
    public void toShowMap(View view) {
        showMap();
        setStatusBarColor(getColor(R.color.white), true);
    }

    @OnClick({R.id.iv_show_more})
    public void toHideMap(View view) {
        hideMap();
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
    }

    @OnClick({R.id.iv_location})
    public void toLocation(View view) {
        this.mapModel.move2CurrentLocation();
    }

    private void showMap() {
        this.mPresenter.showMap(this.mapView, this.mLlRunBottom);
    }

    private void hideMap() {
        this.mPresenter.hideMap(this.mapView, this.mLlRunBottom);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void showMapView(boolean z) {
        this.mLlRunBottom.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void showBack(boolean z) {
        this.mIvShowMore.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setSoundAlpha(float f2) {
        this.mIvSportSound.setAlpha(f2);
        this.sportControlView.setSoundAlpha(f2);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setSoundEnable(boolean z) {
        this.mIvSportSound.setClickable(z);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setSoundSrc(int i) {
        this.mIvSportSound.setImageDrawable(ResourceUtil.getDrawable(i));
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setDeviceStatus(String str) {
        this.mTvDeviceStatus.setText(str);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setGPSSingleStrength(int i) {
        this.mGpsSportView.setGPSSignalStrength(i);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setSportDistance(String str) {
        this.mTvSportRunDistance.setText(str);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setSportDistanceUnit(String str) {
        this.mTvSportRunDistanceUnit.setText(str);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setSportSpeed(String str) {
        this.mTvSportSpeed.setDataText(str);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setSportSpeedTitle(String str) {
        this.mTvSportSpeed.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setSportTime(String str) {
        this.mTvSportTime.setDataText(str);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setSportCalorie(String str) {
        this.mTvSportCalorie.setDataText(str);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setSportCalorieDesc(String str) {
        this.mTvSportCalorie.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void showSportMapIcon(boolean z) {
        this.mIvShowMap.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setDeviceStatusMap(String str) {
        this.mTvDeviceStatusMap.setText(str);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setGPSSingleStrengthMap(int i) {
        this.mGpsViewMap.setGPSSignalStrength(i);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setSportDistanceMap(String str) {
        this.mSportDistanceMap.setDataText(str);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setSportDistanceUnitMap(String str) {
        this.mSportDistanceMap.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setSportSpeedMap(String str) {
        this.mSportSpeedMap.setDataText(str);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setSportSpeedMapTitle(String str) {
        this.mSportSpeedMap.setUnitText(str);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setSportTimeMap(String str) {
        this.mSportTimeMap.setDataText(str);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void showEndConfirmDialog() {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(ResourceUtil.getString(R.string.sport_time_short_remind), ResourceUtil.getString(R.string.sport_time_short_on), ResourceUtil.getString(R.string.sport_time_short_off), true);
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.run.SportRunActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SportLogHelper.saveSportLog(SportRunActivity.TAG, "不足60秒运动，用户确定停止。");
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                SportRunActivity.this.mPresenter.stopRun(false);
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.run.SportRunActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                SportRunActivity.this.mPresenter.reStart();
            }
        });
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(SportRunContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void toIndoorRun(int i, String str) {
        SportHistoryDetailNewActivity.INSTANCE.startActivityResultHideMap(this, i, str, 0);
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void toSportHistory(int i, SportHealth sportHealth) {
        SportHistoryDetailNewActivity.INSTANCE.startActivityResult(this, i, sportHealth.getDateTime(), 0);
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void addPolylineAndMove(LatLngBean latLngBean, boolean z) {
        this.mapModel.addPolylineAndMove(latLngBean, z);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void loadMap(LatLngBean latLngBean) {
        BaseMap baseMap = this.mapModel;
        if (baseMap != null) {
            baseMap.animateCamera(latLngBean);
        }
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void addCurrentMarker(LatLngBean latLngBean) {
        this.mapModel.addCurrentMark(latLngBean);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void addFirstCurrentMarker(LatLngBean latLngBean) {
        this.mapModel.addCurrentMarker(latLngBean, R.mipmap.ic_sport_map_end);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void showMessage(String str) {
        NormalToast.showToast(str, 0);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void showLoading(String str) {
        WaitingDialog.showDialogBack(this, str);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setSportTypeName(String str) {
        this.mTvSportTypeName.setText(str);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setSportStatus(boolean z) {
        if (z) {
            this.sportControlView.setSportStatus(1);
            this.mTvSportStatus.setText(LanguageUtil.getLanguageText(R.string.sport_status_resume));
        } else {
            this.sportControlView.setSportStatus(0);
            this.mTvSportStatus.setText(LanguageUtil.getLanguageText(R.string.sport_status_pause));
        }
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void setGpsStatusDesc(String str) {
        this.mTvGpsStatusDesc.setText(str);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void showGpsStatusDesc(boolean z) {
        this.mTvGpsStatusDesc.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void showGpsSingle(boolean z) {
        this.mGpsSportView.setVisibility(z ? 0 : 4);
        this.mGpsViewMap.setVisibility(z ? 0 : 4);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void toBack() {
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void showMiddleDistanceView(boolean z) {
        this.mRvDistance.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void showBottomDistanceView(boolean z) {
        this.mLlDistance.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.View
    public void clearPoint() {
        BaseMap baseMap = this.mapModel;
        if (baseMap != null) {
            baseMap.clearMarker();
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.ido.life.module.sport.run.SportRunActivity$4] */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mPresenter.stopLocation();
        this.mPresenter.close();
        new Thread() { // from class: com.ido.life.module.sport.run.SportRunActivity.4
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                if (SportRunActivity.this.mapModel == null) {
                    return;
                }
                try {
                    try {
                        SportRunActivity.this.mapModel.onDestroy();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } finally {
                    SportRunActivity.this.mapModel = null;
                }
            }
        }.start();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.mPresenter.back()) {
            super.onBackPressed();
        }
    }
}