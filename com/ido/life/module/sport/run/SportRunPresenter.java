package com.ido.life.module.sport.run;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NumUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.life.bean.LatLngBean;
import com.ido.life.ble.BleSdkWrapper;
import com.ido.life.data.health.HealthRepository;
import com.ido.life.database.model.SportHealth;
import com.ido.life.log.SportLogHelper;
import com.ido.life.module.sport.SportRunManager;
import com.ido.life.module.sport.run.SportRunContract;
import com.ido.life.module.sport.setting.SportSetting;
import com.ido.life.module.sport.setting.SportSettingPreference;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.SportUnitUtil;
import com.ido.life.util.UnitUtil;
import java.text.DecimalFormat;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportRunPresenter implements SportRunContract.Presenter {
    private static final int MAX_SAVE_TIME = 60;
    private static final String TAG = "SportRunPresenter";
    private boolean isConnectedDevice;
    private float kmDistance;
    private boolean mIsAlready;
    private boolean mIsAlreadyLoadGps;
    private boolean mIsOut;
    private MediaPlayer mMediaPlayer;
    private int mSportType;
    private int mTotalSeconds;
    private long mUserId;
    private SportRunContract.View mView;
    private float mileDistance;
    private int tempDistance;
    private Handler handler = new Handler();
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private SportRunManager mSportRunManager = SportRunManager.getInstance();

    @Override // com.ido.life.module.sport.run.SportRunContract.Presenter
    public void clearListener() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public SportRunPresenter(SportRunContract.View view, long j) {
        this.mView = view;
        this.mSportRunManager.setUserId(j);
        this.mIsAlreadyLoadGps = false;
        this.mUserId = j;
        this.isConnectedDevice = BLEManager.isConnected();
        if (this.isConnectedDevice) {
            view.setSportCalorie("--");
            view.setSportCalorieDesc(LanguageUtil.getLanguageText(R.string.sport_record_heart_rate));
        } else {
            view.setSportCalorie("--");
            view.setSportCalorieDesc(LanguageUtil.getLanguageText(R.string.sport_run_category));
        }
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.Presenter
    public void pause() {
        this.mSportRunManager.pauseRun();
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.Presenter
    public void showMap(View view, View view2) {
        SportRunContract.View view3 = this.mView;
        if (view3 == null) {
            return;
        }
        view3.showBack(true);
        this.mView.showMiddleDistanceView(false);
        this.mView.showSportMapIcon(false);
        this.mView.showBottomDistanceView(false);
        Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(view2, view.getRight(), view.getBottom(), ScreenUtil.getScreenH(), 0.0f);
        animatorCreateCircularReveal.setInterpolator(new AccelerateInterpolator());
        animatorCreateCircularReveal.setDuration(500L);
        animatorCreateCircularReveal.addListener(new AnimatorListenerAdapter() { // from class: com.ido.life.module.sport.run.SportRunPresenter.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                SportRunPresenter.this.mView.showMapView(false);
                SportRunPresenter.this.mView.showBack(true);
            }
        });
        animatorCreateCircularReveal.start();
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.Presenter
    public void reStart() {
        this.mSportRunManager.resumeRun();
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.Presenter
    public void end() {
        if (this.mTotalSeconds < 60) {
            SportLogHelper.saveSportLog(TAG, "停止运动，不足60秒，弹框提示。");
            SportRunContract.View view = this.mView;
            if (view == null) {
                return;
            }
            view.showEndConfirmDialog();
            return;
        }
        SportLogHelper.saveSportLog(TAG, "停止运动，运动时长不小于60秒。");
        this.mSportRunManager.stopRun(true);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.Presenter
    public void hideMap(View view, View view2) {
        if (this.mView == null) {
            return;
        }
        Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(view2, view.getRight(), view.getBottom(), 0.0f, ScreenUtil.getScreenH());
        animatorCreateCircularReveal.setInterpolator(new AccelerateInterpolator());
        animatorCreateCircularReveal.setDuration(500L);
        animatorCreateCircularReveal.start();
        this.mView.showMapView(true);
        this.mView.showMiddleDistanceView(true);
        this.mView.showBottomDistanceView(true);
        this.mView.showSportMapIcon(true);
        this.mView.showBack(false);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.Presenter
    public void isSportOutDoor(boolean z) {
        SportRunContract.View view = this.mView;
        if (view == null) {
            return;
        }
        view.showSportMapIcon(z);
        this.mIsOut = z;
        this.mSportRunManager.setIsOutDoor(this.mIsOut);
        if (z) {
            getLocation();
        }
    }

    private void getLocation() {
        this.mSportRunManager.startLocation();
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.Presenter
    public void stopLocation() {
        CommonLogUtil.d(TAG, "stopLocation: stopLocation");
        this.mSportRunManager.stopLocation();
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.Presenter
    public void setSportRunListener() {
        this.mSportRunManager.setSportRunCallback(new SportRunManager.ISportRunCallBack() { // from class: com.ido.life.module.sport.run.SportRunPresenter.2
            @Override // com.ido.life.module.sport.SportRunManager.ISportRunCallBack
            public void sportPause(boolean z) {
                CommonLogUtil.d(SportRunPresenter.TAG, "sportPause: " + z);
                if (!z || SportRunPresenter.this.mView == null) {
                    return;
                }
                SportRunPresenter.this.mView.setSportStatus(false);
            }

            @Override // com.ido.life.module.sport.SportRunManager.ISportRunCallBack
            public void sportResume(boolean z) {
                CommonLogUtil.d(SportRunPresenter.TAG, "sportResume: " + z);
                if (!z || SportRunPresenter.this.mView == null) {
                    return;
                }
                SportRunPresenter.this.mView.setSportStatus(true);
            }

            @Override // com.ido.life.module.sport.SportRunManager.ISportRunCallBack
            public void sportStop(boolean z, SportHealth sportHealth, List<LatLngBean> list) {
                if (z) {
                    SportRunPresenter.this.goDetailFromSportType(sportHealth);
                } else {
                    if (SportRunPresenter.this.mView == null) {
                        return;
                    }
                    SportRunPresenter.this.mView.toBack();
                }
            }

            @Override // com.ido.life.module.sport.SportRunManager.ISportRunCallBack
            public void sportRunning(SportHealth sportHealth, int i, LatLngBean latLngBean, int i2, boolean z) {
                if (latLngBean != null) {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), SportRunPresenter.TAG, "gps: " + latLngBean.toString() + AppInfo.DELIM + i2 + AppInfo.DELIM + System.currentTimeMillis() + AppInfo.DELIM + z);
                    if (SportRunPresenter.this.mView == null) {
                        return;
                    }
                    if (z) {
                        SportRunPresenter.this.mIsAlreadyLoadGps = false;
                        SportRunPresenter.this.mView.loadMap(latLngBean);
                        SportRunPresenter.this.mView.addFirstCurrentMarker(latLngBean);
                    } else {
                        SportRunPresenter.this.mIsAlreadyLoadGps = true;
                        SportRunPresenter.this.mView.addPolylineAndMove(latLngBean, false);
                        SportRunPresenter.this.mView.addCurrentMarker(latLngBean);
                    }
                }
                SportRunPresenter.this.showGpsStatus(i2);
                SportRunPresenter.this.showRunData(sportHealth, i);
                SportRunPresenter.this.showRunDataTarget(sportHealth);
            }

            @Override // com.ido.life.module.sport.SportRunManager.ISportRunCallBack
            public void sportFivePeaceAndSpeed(String str, String str2) {
                SportLogHelper.saveSportLog(SportRunPresenter.TAG, "sportFivePeaceAndSpeed: " + str + AppInfo.DELIM + str2);
                if (SportRunPresenter.this.mSportType == 50 || SportRunPresenter.this.mSportType == 3) {
                    SportRunPresenter.this.mView.setSportSpeed(str2);
                    SportRunPresenter.this.mView.setSportSpeedTitle(LanguageUtil.getLanguageText(R.string.sport_detail_speed));
                    SportRunPresenter.this.mView.setSportSpeedMap(str2);
                    SportRunPresenter.this.mView.setSportSpeedMapTitle(LanguageUtil.getLanguageText(R.string.sport_detail_speed));
                    return;
                }
                if (str.contains("'")) {
                    try {
                        if (Integer.parseInt(str.split("'")[0]) > 99) {
                            str = DateUtil.computeTimePace("99.99");
                        }
                    } catch (Exception e2) {
                        SportLogHelper.saveSportLog(SportRunPresenter.TAG, "getAvgPace: " + e2.toString());
                    }
                }
                SportRunPresenter.this.mView.setSportSpeedTitle(LanguageUtil.getLanguageText(R.string.sport_speed_distribution));
                SportRunPresenter.this.mView.setSportSpeed(str);
                SportRunPresenter.this.mView.setSportSpeedMap(str);
                SportRunPresenter.this.mView.setSportSpeedMapTitle(LanguageUtil.getLanguageText(R.string.sport_speed_distribution));
            }
        });
        this.mSportRunManager.setConnectCallBack(new SportRunManager.ISportConnectCallBack() { // from class: com.ido.life.module.sport.run.SportRunPresenter.3
            @Override // com.ido.life.module.sport.SportRunManager.ISportConnectCallBack
            public void connectTimeOut() {
                SportLogHelper.saveSportLog(SportRunPresenter.TAG, "connectTimeOut: ");
                if (SportRunPresenter.this.mView == null) {
                    return;
                }
                SportRunPresenter.this.mSportRunManager.stopRun(false);
                SportRunPresenter.this.mView.showMessage(LanguageUtil.getLanguageText(R.string.sport_device_connect_stop));
            }

            @Override // com.ido.life.module.sport.SportRunManager.ISportConnectCallBack
            public void bleDisconnect() {
                SportLogHelper.saveSportLog(SportRunPresenter.TAG, "bleDisconnect: ");
                if (SportRunPresenter.this.mView == null) {
                    return;
                }
                SportRunPresenter.this.mView.showLoading(LanguageUtil.getLanguageText(R.string.sport_device_connect_ing));
                SportRunPresenter.this.mSportRunManager.pauseRun();
            }

            @Override // com.ido.life.module.sport.SportRunManager.ISportConnectCallBack
            public void bleConnect() {
                if (SportRunPresenter.this.mView == null) {
                    return;
                }
                SportLogHelper.saveSportLog(SportRunPresenter.TAG, "bleConnect: ");
                SportRunPresenter.this.mView.hideLoading();
                SportRunPresenter.this.mSportRunManager.resumeRun();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showGpsStatus(int i) {
        SportRunContract.View view = this.mView;
        if (view == null) {
            return;
        }
        view.setGPSSingleStrength(i);
        this.mView.setGPSSingleStrengthMap(i);
        if (this.mIsOut) {
            this.mView.showGpsSingle(true);
            if (i == 0) {
                this.mView.showGpsStatusDesc(true);
                this.mView.setGpsStatusDesc(LanguageUtil.getLanguageText(R.string.sport_run_gps_invalid));
                return;
            } else if (i == 1) {
                this.mView.showGpsStatusDesc(false);
                return;
            } else {
                if (i != 2) {
                    return;
                }
                this.mView.showGpsStatusDesc(true);
                this.mView.setGpsStatusDesc(LanguageUtil.getLanguageText(R.string.sport_run_gps_bad));
                return;
            }
        }
        this.mView.showGpsStatusDesc(false);
        this.mView.showGpsSingle(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRunDataTarget(SportHealth sportHealth) {
        boolean zIsRideKm;
        int i = this.mSportType;
        if (i == 50 || i == 3) {
            zIsRideKm = SportUnitUtil.isRideKm();
        } else {
            zIsRideKm = SportUnitUtil.isWalkOrRunKm();
        }
        SportSetting sportSetting = SportSettingPreference.getSportSetting();
        if (sportHealth != null) {
            SportLogHelper.saveSportLog(TAG, "showRunDataTarget: 目标信息" + sportSetting.toString() + "运动信息:距离" + sportHealth.getDistance() + "步数" + sportHealth.getNumSteps() + "卡路里" + sportHealth.getNumCalories() + "时间" + sportHealth.getTotalSeconds());
        }
        if (sportSetting.isSportRemindOff()) {
            if (sportSetting.isDistanceVoice()) {
                int distanceInterval = zIsRideKm ? sportSetting.getDistanceInterval() * 1000 : (int) UnitUtil.mile2km(r2 * 1000);
                int distance = sportHealth == null ? 0 : sportHealth.getDistance();
                if (this.tempDistance < distanceInterval) {
                    this.tempDistance = distanceInterval;
                }
                if (distance >= distanceInterval && distance / this.tempDistance > 0) {
                    playTipsMusic();
                    this.tempDistance += distanceInterval;
                }
            }
            if (sportSetting.isSportTarget()) {
                int step = sportSetting.getStep();
                int distance2 = sportSetting.getDistance();
                int category = sportSetting.getCategory();
                int time = sportSetting.getTime();
                if (step != 0 && sportHealth != null && sportHealth.getNumSteps() >= step && !this.mIsAlready) {
                    playTipsMusic();
                    this.mIsAlready = true;
                }
                if (distance2 != 0 && sportHealth != null && sportHealth.getDistance() >= distance2 && !this.mIsAlready) {
                    playTipsMusic();
                    this.mIsAlready = true;
                }
                if (category != 0 && sportHealth != null && sportHealth.getNumCalories() >= category && !this.mIsAlready) {
                    playTipsMusic();
                    this.mIsAlready = true;
                }
                if (time == 0 || sportHealth == null || sportHealth.getTotalSeconds() <= time || this.mIsAlready) {
                    return;
                }
                playTipsMusic();
                this.mIsAlready = true;
            }
        }
    }

    public void playTipsMusic() {
        SportLogHelper.saveSportLog(TAG, "playTipsMusic: ");
        if (this.mMediaPlayer == null) {
            this.mMediaPlayer = MediaPlayer.create(IdoApp.getAppContext(), R.raw.target_tips_music);
            this.mMediaPlayer.setAudioStreamType(3);
            this.mMediaPlayer.start();
            this.handler.postDelayed(new Runnable() { // from class: com.ido.life.module.sport.run.SportRunPresenter.4
                @Override // java.lang.Runnable
                public void run() {
                    if (SportRunPresenter.this.mMediaPlayer != null) {
                        SportRunPresenter.this.mMediaPlayer.stop();
                        SportRunPresenter.this.mMediaPlayer.release();
                        SportRunPresenter.this.mMediaPlayer = null;
                    }
                }
            }, 2000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goDetailFromSportType(SportHealth sportHealth) {
        if (this.mView == null || sportHealth == null) {
            return;
        }
        sportHealth.setUserId(this.mUserId);
        int i = this.mSportType;
        if (i != 1 && i != 2 && i != 3) {
            if (i == 4) {
                if (this.mIsAlreadyLoadGps) {
                    this.mView.toSportHistory(4, sportHealth);
                    return;
                }
                sportHealth.setIsLocus(0);
                sportHealth.setGpsSourceType(0);
                GreenDaoUtil.saveActivityData(sportHealth);
                this.mView.toIndoorRun(4, sportHealth.getDateTime());
                return;
            }
            if (i != 52) {
                if (i != 53) {
                    switch (i) {
                        case 49:
                            this.mView.toIndoorRun(49, sportHealth.getDateTime());
                            break;
                    }
                }
                this.mView.toIndoorRun(53, sportHealth.getDateTime());
                return;
            }
        }
        if (this.mIsAlreadyLoadGps) {
            this.mView.toSportHistory(this.mSportType, sportHealth);
            return;
        }
        sportHealth.setIsLocus(0);
        sportHealth.setGpsSourceType(0);
        GreenDaoUtil.saveActivityData(sportHealth);
        this.mView.toIndoorRun(this.mSportType, sportHealth.getDateTime());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRunData(SportHealth sportHealth, int i) {
        boolean zIsWalkOrRunKm;
        String str;
        if (sportHealth == null) {
            return;
        }
        sportHealth.setUserId(this.mUserId);
        SportLogHelper.saveSportLog(TAG, "showRunData: 时间," + sportHealth.getTotalSeconds() + "距离," + sportHealth.getDistance() + "配速," + sportHealth.getPace());
        this.kmDistance = ((float) sportHealth.getDistance()) / 1000.0f;
        this.mileDistance = UnitUtil.getKm2mile(this.kmDistance);
        if (this.mView == null) {
            return;
        }
        if (this.mSportType == 50) {
            zIsWalkOrRunKm = SportUnitUtil.isRideKm();
        } else {
            zIsWalkOrRunKm = SportUnitUtil.isWalkOrRunKm();
        }
        if (zIsWalkOrRunKm) {
            str = this.decimalFormat.format(Float.parseFloat(NumUtil.float2String(this.kmDistance, 2)));
            this.mView.setSportDistanceUnit(LanguageUtil.getLanguageText(R.string.sport_run_distance_unit));
            this.mView.setSportDistanceUnitMap(LanguageUtil.getLanguageText(R.string.sport_run_distance_unit));
        } else {
            str = this.decimalFormat.format(Float.parseFloat(NumUtil.float2String(this.mileDistance, 2)));
            this.mView.setSportDistanceUnit(LanguageUtil.getLanguageText(R.string.sport_run_distance_unit_mi));
            this.mView.setSportDistanceUnitMap(LanguageUtil.getLanguageText(R.string.sport_run_distance_unit_mi));
        }
        this.mView.setSportTime(DateUtil.computeTimeHMS(sportHealth.getTotalSeconds()));
        if (this.isConnectedDevice) {
            this.mView.setSportCalorie(String.valueOf(i));
            this.mView.setSportCalorieDesc(LanguageUtil.getLanguageText(R.string.sport_record_heart_rate));
        } else {
            this.mView.setSportCalorie(String.valueOf(sportHealth.getNumCalories()));
            this.mView.setSportCalorieDesc(LanguageUtil.getLanguageText(R.string.sport_run_category));
        }
        this.mTotalSeconds = sportHealth.getTotalSeconds();
        this.mView.setSportDistance(str);
        this.mView.setSportDistanceMap(str);
        this.mView.setSportDistanceUnitMap(LanguageUtil.getLanguageText(R.string.sport_run_distance_desc));
        this.mView.setSportTimeMap(DateUtil.computeTimeHMS(sportHealth.getTotalSeconds()));
        if (BleSdkWrapper.isConnected()) {
            this.mView.setDeviceStatus(LanguageUtil.getLanguageText(R.string.device_connected_already));
            this.mView.setDeviceStatusMap(LanguageUtil.getLanguageText(R.string.device_connected_already));
        } else {
            this.mView.setDeviceStatus(LanguageUtil.getLanguageText(R.string.device_pls_connect_device));
            this.mView.setDeviceStatusMap(LanguageUtil.getLanguageText(R.string.device_pls_connect_device));
        }
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.Presenter
    public void stopRun(boolean z) {
        this.mSportRunManager.stopRun(z);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.Presenter
    public void close() {
        this.mSportRunManager.close();
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.Presenter
    public boolean back() {
        if (this.mSportRunManager.isCompleteRun()) {
            return true;
        }
        SportRunContract.View view = this.mView;
        if (view == null) {
            return false;
        }
        view.showMessage(ResourceUtil.getString(R.string.sport_end_switch_stop_first));
        return false;
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.Presenter
    public void getSportNameByType(int i) {
        SportRunContract.View view = this.mView;
        if (view == null) {
            return;
        }
        if (i == 1) {
            this.mSportType = i;
            view.setSportTypeName(LanguageUtil.getLanguageText(R.string.sport_tab_walk));
            return;
        }
        if (i == 2) {
            this.mSportType = i;
            view.setSportTypeName(LanguageUtil.getLanguageText(R.string.sport_tab_run));
            return;
        }
        if (i == 3) {
            this.mSportType = i;
            view.setSportTypeName(LanguageUtil.getLanguageText(R.string.sport_tab_ride));
            return;
        }
        if (i == 4) {
            this.mSportType = i;
            view.setSportTypeName(LanguageUtil.getLanguageText(R.string.sport_record_walk_onfoot));
            return;
        }
        if (i == 52) {
            this.mSportType = i;
            view.setSportTypeName(LanguageUtil.getLanguageText(R.string.sport_walk_outdoor));
            return;
        }
        if (i != 53) {
            switch (i) {
                case 48:
                    this.mSportType = i;
                    view.setSportTypeName(LanguageUtil.getLanguageText(R.string.sport_record_run_outdoor));
                    break;
                case 49:
                    this.mSportType = i;
                    view.setSportTypeName(LanguageUtil.getLanguageText(R.string.sport_record_run_indoor));
                    break;
                case 50:
                    this.mSportType = i;
                    view.setSportTypeName(LanguageUtil.getLanguageText(R.string.sport_record_ride_outdoor));
                    break;
            }
            return;
        }
        this.mSportType = i;
        view.setSportTypeName(LanguageUtil.getLanguageText(R.string.sport_walk_indoor));
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.Presenter
    public void initUserTarget() {
        if (this.mView == null) {
            return;
        }
        SportSetting sportSetting = SportSettingPreference.getSportSetting();
        if (sportSetting.isDistanceVoice() || sportSetting.isSportTarget()) {
            this.mView.setSoundSrc(R.mipmap.ic_sport_sound_on);
            this.mView.setSoundEnable(true);
            this.mView.setSoundAlpha(1.0f);
        } else {
            this.mView.setSoundEnable(false);
            this.mView.setSoundAlpha(0.5f);
        }
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.Presenter
    public void toSoundOffOrOn() {
        if (this.mView == null) {
            return;
        }
        SportSetting sportSetting = SportSettingPreference.getSportSetting();
        sportSetting.setSportRemindOff(!sportSetting.isSportRemindOff());
        if (sportSetting.isSportRemindOff()) {
            this.mView.setSoundSrc(R.mipmap.ic_sport_sound_on);
        } else {
            this.mView.setSoundSrc(R.mipmap.ic_sport_sound_off);
        }
        SportSettingPreference.saveSportSetting(sportSetting);
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.Presenter
    public boolean getInit() {
        SportRunManager sportRunManager = this.mSportRunManager;
        if (sportRunManager != null) {
            return sportRunManager.getSportInit();
        }
        return false;
    }

    @Override // com.ido.life.module.sport.run.SportRunContract.Presenter
    public void onRestoreInstanceState() {
        setSportRunListener();
        this.mSportRunManager.onRestoreInstanceState();
        List<LatLngBean> latLngByDateTime = HealthRepository.getInstance().getLatLngByDateTime(DateUtil.getLongFromDateStr(this.mSportRunManager.getDateTime()));
        latLngByDateTime.addAll(this.mSportRunManager.getmLatLngDomainList());
        SportLogHelper.saveSportLog(TAG, "onRestoreInstanceState: " + latLngByDateTime.size());
        if (latLngByDateTime.size() > 0) {
            SportLogHelper.saveSportLog(TAG, "onRestoreInstanceState: " + latLngByDateTime.size());
            for (LatLngBean latLngBean : latLngByDateTime) {
                SportRunContract.View view = this.mView;
                if (view == null) {
                    return;
                } else {
                    view.addPolylineAndMove(latLngBean, false);
                }
            }
        }
    }

    @Override // com.ido.common.base.BasePresenter
    public void release() {
        this.mView = null;
    }
}