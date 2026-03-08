package com.ido.life.module.home;

import android.graphics.Point;
import android.util.Pair;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.life.base.IBaseView;
import com.ido.life.bean.BarChartPoint;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.bean.MainData;
import com.ido.life.database.model.HealthVolumeData;
import com.ido.life.database.model.ServerBloodOxyDayData;
import com.ido.life.database.model.ServerHeartRateDayData;
import com.ido.life.database.model.ServerSleepDayData;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.WeightItemBean;
import com.ido.life.enums.UserModelEnum;
import com.ido.life.module.sport.map.BaseMap;
import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: IHomeView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J:\u0010\r\u001a4\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000e\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u000f\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010\u0018\u00010\u000e\u0018\u00010\u000eH&J \u0010\u0012\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010\u0018\u00010\u000eH&J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0016H&J\u0014\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0002\b\u0003\u0018\u00010\u0018H&J\n\u0010\u001a\u001a\u0004\u0018\u00010\u001bH&J\n\u0010\u001c\u001a\u0004\u0018\u00010\u001dH&J\u0016\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000eH&J\n\u0010\u001f\u001a\u0004\u0018\u00010 H&J\"\u0010!\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\f0\u000e\u0012\u0004\u0012\u00020\f\u0018\u00010\u000eH&J \u0010\"\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\f0\u000e\u0012\u0004\u0012\u00020\f0\u000eH&J(\u0010#\u001a\"\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u0010\u0018\u00010\u000eH&J\"\u0010%\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\f0\u000e\u0012\u0004\u0012\u00020\f\u0018\u00010\u000eH&J\b\u0010&\u001a\u00020'H&J\u0012\u0010(\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010)\u0018\u00010\u0010H&J\b\u0010*\u001a\u00020\u000fH&J\b\u0010+\u001a\u00020\u000fH&J\b\u0010,\u001a\u00020\u000fH&J\b\u0010-\u001a\u00020\u000fH&J\b\u0010.\u001a\u00020\u000fH&J\b\u0010/\u001a\u00020\u000fH&J\b\u00100\u001a\u00020\u000fH&J\b\u00101\u001a\u00020\u000fH&J\b\u00102\u001a\u00020\u000fH&J\b\u00103\u001a\u00020\u000fH&J\b\u00104\u001a\u00020\u000fH&J\b\u00105\u001a\u00020\u000fH&J\b\u00106\u001a\u00020\u0004H&J\b\u00107\u001a\u00020\u0004H&J\b\u00108\u001a\u00020\u0004H&J\b\u00109\u001a\u00020\u0004H&J\b\u0010:\u001a\u00020\u0004H&J\b\u0010;\u001a\u00020\u0004H&J\b\u0010<\u001a\u00020\u0004H&J\b\u0010=\u001a\u00020\u0004H&J\b\u0010>\u001a\u00020\u0004H&J\u0018\u0010?\u001a\u00020\u00042\u000e\u0010@\u001a\n\u0012\u0004\u0012\u00020B\u0018\u00010AH&J\b\u0010C\u001a\u00020\u0004H&J\u0012\u0010D\u001a\u00020\u00042\b\u0010E\u001a\u0004\u0018\u00010FH&J\b\u0010G\u001a\u00020\u0004H&J\b\u0010H\u001a\u00020\u0004H&J\b\u0010I\u001a\u00020\u0004H&J\b\u0010J\u001a\u00020\u0004H&J\u0010\u0010K\u001a\u00020\u00042\u0006\u0010L\u001a\u00020\fH&J\b\u0010M\u001a\u00020\u0004H&J\b\u0010N\u001a\u00020\u0004H&J\b\u0010O\u001a\u00020\u0004H&J\b\u0010P\u001a\u00020\u0004H&J\b\u0010Q\u001a\u00020\u0004H&J\b\u0010R\u001a\u00020\u0004H&J\b\u0010S\u001a\u00020\u000fH&J\b\u0010T\u001a\u00020\u0004H&J\b\u0010U\u001a\u00020\u000fH&J\b\u0010V\u001a\u00020\u0004H&J\b\u0010W\u001a\u00020\u0004H&J\b\u0010X\u001a\u00020\u0004H&J\b\u0010Y\u001a\u00020\u000fH&J\b\u0010Z\u001a\u00020\u000fH&J\b\u0010[\u001a\u00020\u0004H&J\b\u0010\\\u001a\u00020\u000fH&J\b\u0010]\u001a\u00020\u0004H&J\b\u0010^\u001a\u00020\u0004H&J\b\u0010_\u001a\u00020\u0004H&J\b\u0010`\u001a\u00020\u0004H&J\b\u0010a\u001a\u00020\u000fH&J\u0012\u0010b\u001a\u00020\u00042\b\u0010c\u001a\u0004\u0018\u00010dH&J\b\u0010e\u001a\u00020\u0004H&J\b\u0010f\u001a\u00020\u0004H&J\b\u0010g\u001a\u00020\u0004H&J\b\u0010h\u001a\u00020\u0004H&J\u0010\u0010i\u001a\u00020\u00042\u0006\u0010j\u001a\u00020\u000fH&¨\u0006k"}, d2 = {"Lcom/ido/life/module/home/IHomeView;", "Lcom/ido/life/base/IBaseView;", "Landroid/view/View$OnClickListener;", "backFromBackground", "", "getAmbientNoiseData", "Lcom/ido/life/database/model/HealthVolumeData;", "getDateShowByTimeStamp", "", "timeStamp", "", "getHeaderCount", "", "getHealthPressure", "Landroid/util/Pair;", "", "", "Lcom/ido/life/bean/BarChartPoint;", "getHeartRateData", "Lcom/ido/life/database/model/ServerHeartRateDayData;", "Lcom/ido/life/bean/BaseCharBean;", "getLastestSportRecord", "Lcom/ido/life/database/model/SportHealth;", "getMap", "Lcom/ido/life/module/sport/map/BaseMap;", "Landroid/view/View;", "getMenstrual", "Lcom/ido/life/module/home/WholeLifeCycleInfo;", "getNearOxyData", "Lcom/ido/life/database/model/ServerBloodOxyDayData;", "getOxygenUptakeData", "getSleepData", "Lcom/ido/life/database/model/ServerSleepDayData;", "getTodayActive", "getTodayActiveTime", "getTodayStepData", "Landroid/graphics/Point;", "getTodayWalk", "getTotalDistance", "", "getWeightList", "Lcom/ido/life/database/model/WeightItemBean;", "hasActivityData", "hasBloodOxyData", "hasCalorie", "hasDistance", "hasHeartRate", "hasLifeCycle", "hasLogin", "hasPressure", "hasSleepData", "hasSportRecord", "hasStepData", "hasWalkData", "historyDataLoadFailed", "historyDataLoadSuccess", "onBlueToothConnect", "onBlueToothDisconnect", "onConnectFailed", "onDeviceBindCrossDay", "onDeviceBindSuccess", "onDeviceRestarted", "onDeviceUnBindSuccess", "onGetCardDataList", "mainDataList", "Ljava/util/LinkedList;", "Lcom/ido/life/bean/MainData;", "onGetUserEmailBindStateFailed", "onInDfuMode", "device", "Lcom/ido/ble/bluetooth/device/BLEDevice;", "onNeedLocationPermission", "onNeedOpenBle", "onNeedOpenGps", "onSyncFailed", "onSyncProgress", NotificationCompat.CATEGORY_PROGRESS, "onSyncSuccess", "onTargetChanged", "onUserInfoChanged", "refreshAllCard", "refreshBloodOxyCard", "refreshHeartRateCard", "refreshHeartRateTime", "refreshMenstrualCard", "refreshOxyTime", "refreshOxygenUptakeCard", "refreshPanelCard", "refreshPressureCard", "refreshPressureTime", "refreshRecordTime", "refreshSleepCard", "refreshSleepTime", "refreshSportCard", "refreshStepCard", "refreshVolumeCard", "refreshWeightCard", "refreshWeightTime", "showUserMedalDialog", "modelEnum", "Lcom/ido/life/enums/UserModelEnum;", "startLoadHistoryData", "startRefresh", "startUpdateTime", "updateHistoryPullProgress", "updateUserEmailBindState", "hasBind", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IHomeView extends IBaseView, View.OnClickListener {
    void backFromBackground();

    HealthVolumeData getAmbientNoiseData();

    String getDateShowByTimeStamp(long timeStamp);

    int getHeaderCount();

    Pair<Pair<Long, Integer>, Pair<Boolean, List<BarChartPoint>>> getHealthPressure();

    Pair<ServerHeartRateDayData, List<BaseCharBean>> getHeartRateData();

    SportHealth getLastestSportRecord();

    BaseMap<View, ?> getMap();

    WholeLifeCycleInfo getMenstrual();

    ServerBloodOxyDayData getNearOxyData();

    Pair<Long, Integer> getOxygenUptakeData();

    ServerSleepDayData getSleepData();

    Pair<Pair<Boolean, Integer>, Integer> getTodayActive();

    Pair<Pair<Boolean, Integer>, Integer> getTodayActiveTime();

    Pair<Pair<Integer, Boolean>, List<Point>> getTodayStepData();

    Pair<Pair<Boolean, Integer>, Integer> getTodayWalk();

    float getTotalDistance();

    List<WeightItemBean> getWeightList();

    boolean hasActivityData();

    boolean hasBloodOxyData();

    boolean hasCalorie();

    boolean hasDistance();

    boolean hasHeartRate();

    boolean hasLifeCycle();

    boolean hasLogin();

    boolean hasPressure();

    boolean hasSleepData();

    boolean hasSportRecord();

    boolean hasStepData();

    boolean hasWalkData();

    void historyDataLoadFailed();

    void historyDataLoadSuccess();

    void onBlueToothConnect();

    void onBlueToothDisconnect();

    void onConnectFailed();

    void onDeviceBindCrossDay();

    void onDeviceBindSuccess();

    void onDeviceRestarted();

    void onDeviceUnBindSuccess();

    void onGetCardDataList(LinkedList<MainData> mainDataList);

    void onGetUserEmailBindStateFailed();

    void onInDfuMode(BLEDevice device);

    void onNeedLocationPermission();

    void onNeedOpenBle();

    void onNeedOpenGps();

    void onSyncFailed();

    void onSyncProgress(int progress);

    void onSyncSuccess();

    void onTargetChanged();

    void onUserInfoChanged();

    void refreshAllCard();

    void refreshBloodOxyCard();

    void refreshHeartRateCard();

    boolean refreshHeartRateTime();

    void refreshMenstrualCard();

    boolean refreshOxyTime();

    void refreshOxygenUptakeCard();

    void refreshPanelCard();

    void refreshPressureCard();

    boolean refreshPressureTime();

    boolean refreshRecordTime();

    void refreshSleepCard();

    boolean refreshSleepTime();

    void refreshSportCard();

    void refreshStepCard();

    void refreshVolumeCard();

    void refreshWeightCard();

    boolean refreshWeightTime();

    void showUserMedalDialog(UserModelEnum modelEnum);

    void startLoadHistoryData();

    void startRefresh();

    void startUpdateTime();

    void updateHistoryPullProgress();

    void updateUserEmailBindState(boolean hasBind);
}