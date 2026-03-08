package com.ido.life.module.sport;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.google.gson.JsonObject;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.AppExchangeDataIngDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataIngPara;
import com.ido.ble.protocol.model.AppExchangeDataPauseDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataResumeDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataStartDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataStartPara;
import com.ido.ble.protocol.model.AppExchangeDataStopDeviceReplyData;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataPauseAppReplyData;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataPausePara;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataResumeAppReplyData;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataResumePara;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataStopAppReplyData;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataStopPara;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.V3AppExchangeDataDeviceReplayEndData;
import com.ido.ble.protocol.model.V3AppExchangeDataHeartRate;
import com.ido.ble.protocol.model.V3AppExchangeDataIngDeviceReplyData;
import com.ido.ble.protocol.model.V3AppExchangeDataIngPara;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.StringUtil;
import com.ido.common.utils.TimeUtil;
import com.ido.life.bean.LatLngBean;
import com.ido.life.ble.AppExchangeDataCallBackWrapper;
import com.ido.life.ble.AppExchangeV3DataCallBackWrapper;
import com.ido.life.ble.BaseConnCallback;
import com.ido.life.ble.BleSdkWrapper;
import com.ido.life.ble.SportDataHelper;
import com.ido.life.boatservice.GDLocationManager;
import com.ido.life.data.SportHealthDataManager;
import com.ido.life.database.model.GpsData;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.SportItemPace;
import com.ido.life.log.SportLogHelper;
import com.ido.life.module.sport.bean.LocationMessage;
import com.ido.life.module.sport.map.MapHelper;
import com.ido.life.module.sport.setting.SportSetting;
import com.ido.life.module.sport.setting.SportSettingPreference;
import com.ido.life.util.DateUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SportDataUtil;
import com.ido.life.util.SportUnitUtil;
import com.ido.life.util.ThreadUtil;
import com.veryfit.multi.nativeprotocol.Protocol;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SportRunManager {
    static final int BLE_START_CHARGE_POWER = 3;
    static final int BLE_START_IN_CALLING = 5;
    static final int BLE_START_LOW_POWER = 2;
    static final int BLE_START_SPORT_FAIL = 1;
    static final int BLE_START_SPORT_IN_ALEXA = 4;
    static final int BLE_START_SUCCESS = 0;
    private static final String TAG = "SportRunManager";
    private static final int TIME_FIVE_MINUTE = 5;
    private static final int TIME_OUT_CONNECT = 15000;
    private static volatile SportRunManager instance;
    private long changeIndex;
    private ISportConnectCallBack connectCallBack;
    private GDLocationManager gdLocationManager;
    private LatLngBean mCurrentLatLng;
    private int mHeartRate;
    private boolean mIsAppComplete;
    private boolean mIsLoadMap;
    private boolean mIsOut;
    private boolean mIsRemoveFirstPoint;
    private boolean mIsSupportRealPace;
    private double mOneMinuteDistances;
    private Protocol mProtocol;
    private SportHealth mSportHealth;
    private String mSportName;
    private ISportRunCallBack mSportRunCallBack;
    private ISportStartCallBack mSportStart;
    private int mType;
    private long mUserId;
    int paceByphone;
    int speedByphone;
    public List<LatLngBean> mLatLngDomainList = new ArrayList();
    private final int SPORT_STATE_NONE = 0;
    private final int SPORT_STATE_RUNNING = 1;
    private final int SPORT_STATE_STOP = 2;
    private final int SPORT_STATE_PAUSE = 3;
    private AppExchangeDataStartPara switchDataAppStart = new AppExchangeDataStartPara();
    private AppExchangeDataIngPara switchDataAppIng = new AppExchangeDataIngPara();
    private AppExchangeDataIngPara mSwitchDataAppIng = new AppExchangeDataIngPara();
    private V3AppExchangeDataIngPara mV3SwitchDataAppIng = new V3AppExchangeDataIngPara();
    private int maxHeartRate = 0;
    private long lastCurrentTimeMillis = 0;
    RunTimerTask runTimerTask = new RunTimerTask();
    private final int UPDATE_DATA_INTERVAL = 1000;
    private Handler updateHandler = new Handler(Looper.getMainLooper());
    private int mGpsSignValue = 0;
    private double mSendDistance = 0.0d;
    private int sportState = 0;
    private LinkedHashMap<Integer, int[]> serialHeartRate = new LinkedHashMap<>();
    private List<Integer> mHeartRateList = new ArrayList();
    private List<Integer> mRateList = new ArrayList();
    private List<Integer> mMetricItemsList = new ArrayList();
    private List<Integer> mBritishItemsList = new ArrayList();
    private boolean mIsSaveData = true;
    protected boolean mIsCompleteRun = true;
    int NO_SAVE_END = 0;
    int SAVE_END = 1;
    private int mSourceType = 1;
    protected LatLngBean lastLatlng = null;
    protected double mTotalDistance = 0.0d;
    int FORCE_START_INVALID = 0;
    int FORCE_START_VALID = 1;
    private boolean mIsV3Exchange = false;
    private boolean mIsEnd = false;
    private List<Double> mFiveMinuteDistances = new ArrayList();
    GDLocationManager.LocationStringListener locationStringListener = new GDLocationManager.LocationStringListener() { // from class: com.ido.life.module.sport.SportRunManager.1
        @Override // com.ido.life.boatservice.GDLocationManager.LocationStringListener
        public void onReceiveLocation(LocationMessage locationMessage) {
            if (locationMessage == null) {
                return;
            }
            SportRunManager.this.mGpsSignValue = locationMessage.gpsAccuracyStatus;
            if (SportRunManager.this.mGpsSignValue != 1 && (locationMessage.getLongitude() != 0.0d || locationMessage.getLatitude() != 0.0d)) {
                if (!SportRunManager.this.mIsLoadMap) {
                    LatLngBean latLngBean = new LatLngBean();
                    latLngBean.setLatitude(locationMessage.getLatitude());
                    latLngBean.setLongitude(locationMessage.getLongitude());
                    SportRunManager.this.mCurrentLatLng = latLngBean;
                    SportRunManager.this.mCurrentLatLng.setCurrentTimeMillis(TimeUtil.convTimeDetail(System.currentTimeMillis()));
                    if (SportRunManager.this.mLatLngDomainList == null) {
                        SportRunManager.this.mLatLngDomainList = new ArrayList();
                    }
                    SportRunManager.this.mIsRemoveFirstPoint = true;
                }
                SportRunManager.this.mIsLoadMap = true;
                return;
            }
            SportLogHelper.saveSportLog(SportRunManager.TAG, "定位的经纬度 onReceiveLocation: " + locationMessage.toString() + "LocationMessage.speed=" + locationMessage.speed);
            GpsData gpsData = new GpsData();
            gpsData.setAccuracy(locationMessage.accurac);
            gpsData.setAltitude(locationMessage.altitude);
            gpsData.setCurrentTimeMillis(TimeUtil.convTimeYmdhmsToLong(locationMessage.currentTimeMillis));
            gpsData.setDateTime(locationMessage.getCurrentTimeMillis());
            gpsData.setGpsAccuracyStatus(locationMessage.gpsAccuracyStatus);
            gpsData.setLatitude(locationMessage.getLatitude());
            gpsData.setLongitude(locationMessage.getLongitude());
            SportLogHelper.saveGpsLog(SportRunManager.TAG, GsonUtil.toJson(gpsData));
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("lat", Double.valueOf(gpsData.getLatitude()));
            jsonObject.addProperty("lon", Double.valueOf(gpsData.getLongitude()));
            jsonObject.addProperty("accuracy", Float.valueOf(gpsData.getAccuracy()));
            jsonObject.addProperty("gpsaccuracystatus", Integer.valueOf(gpsData.gpsAccuracyStatus));
            jsonObject.addProperty("timestamp", Long.valueOf(gpsData.getCurrentTimeMillis() / 1000));
            String string = jsonObject.toString();
            SportLogHelper.saveSportLog(SportRunManager.TAG, "转换之前的点" + string);
            String strAppGpsAlgProcessRealtime = SportRunManager.this.mProtocol.appGpsAlgProcessRealtime(string);
            SportLogHelper.saveSportLog(SportRunManager.TAG, "转换之后的点" + strAppGpsAlgProcessRealtime);
            try {
                JSONObject jSONObject = new JSONObject(strAppGpsAlgProcessRealtime);
                if (Math.abs(Double.valueOf(jSONObject.getDouble("lat")).doubleValue() - 180.0d) < 0.1d) {
                    return;
                }
                locationMessage.setLatitude(jSONObject.getDouble("lat"));
                locationMessage.setLongitude(jSONObject.getDouble("lon"));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            LatLngBean latLngBean2 = new LatLngBean();
            latLngBean2.setLatitude(locationMessage.getLatitude());
            latLngBean2.setLongitude(locationMessage.getLongitude());
            locationMessage.totalDistance = (int) SportRunManager.this.getDistanceByGps(latLngBean2);
            if (SportRunManager.this.mIsAppComplete || !BleSdkWrapper.isConnected()) {
                SportRunManager.this.mSportHealth.setDistance(locationMessage.totalDistance);
            } else {
                SportRunManager.this.mSendDistance = locationMessage.totalDistance;
            }
            SportRunManager.this.mCurrentLatLng = latLngBean2;
            SportRunManager.this.mCurrentLatLng.setCurrentTimeMillis(TimeUtil.convTimeDetail(System.currentTimeMillis()));
            SportRunManager.this.mLatLngDomainList.add(SportRunManager.this.mCurrentLatLng);
            SportRunManager.this.mIsLoadMap = true;
            SportRunManager.this.mIsRemoveFirstPoint = false;
            SportLogHelper.saveSportLog(SportRunManager.TAG, "onReceiveLocation: " + SportRunManager.this.mTotalDistance + AppInfo.DELIM + SportRunManager.this.mSendDistance);
        }
    };
    private AppExchangeV3DataCallBackWrapper changeV3CallBack = new AppExchangeV3DataCallBackWrapper() { // from class: com.ido.life.module.sport.SportRunManager.2
        @Override // com.ido.life.ble.AppExchangeV3DataCallBackWrapper, com.ido.ble.callback.V3AppExchangeDataCallBack.ICallBack
        public void onReplyExchangeDateIng(V3AppExchangeDataIngDeviceReplyData v3AppExchangeDataIngDeviceReplyData) {
            super.onReplyExchangeDateIng(v3AppExchangeDataIngDeviceReplyData);
            SportLogHelper.saveSportLog(SportRunManager.TAG, "onReplyExchangeDateIng: v3" + v3AppExchangeDataIngDeviceReplyData.toString());
            CommonLogUtil.d("v3--", "onReplyExchangeDateIng: v3 " + v3AppExchangeDataIngDeviceReplyData.toString());
            if (v3AppExchangeDataIngDeviceReplyData.status == 1) {
                if (v3AppExchangeDataIngDeviceReplyData.real_time_calories != 0) {
                    SportRunManager.this.mSportHealth.setNumCalories(v3AppExchangeDataIngDeviceReplyData.real_time_calories);
                }
                if (v3AppExchangeDataIngDeviceReplyData.steps != 0) {
                    SportRunManager.this.mSportHealth.setNumSteps(v3AppExchangeDataIngDeviceReplyData.steps);
                }
                if (v3AppExchangeDataIngDeviceReplyData.distance != 0) {
                    SportRunManager.this.mOneMinuteDistances = v3AppExchangeDataIngDeviceReplyData.distance - SportRunManager.this.mSportHealth.getDistance();
                    SportRunManager.this.mSportHealth.setDistance(v3AppExchangeDataIngDeviceReplyData.distance);
                }
                SportRunManager.this.deviceSportTime = v3AppExchangeDataIngDeviceReplyData.duration;
                String strComputeTimeMS = DateUtil.computeTimeMS(v3AppExchangeDataIngDeviceReplyData.real_time_speed_pace);
                if (!SportUnitUtil.isWalkOrRunKm()) {
                    strComputeTimeMS = SportDataUtil.changePeace2mile(v3AppExchangeDataIngDeviceReplyData.real_time_speed_pace);
                }
                String avgSpeed = SportDataUtil.formatAvgSpeed(v3AppExchangeDataIngDeviceReplyData.real_time_speed / 100.0f);
                if (!SportUnitUtil.isRideKm()) {
                    avgSpeed = SportDataUtil.changeSpeed2mile(v3AppExchangeDataIngDeviceReplyData.real_time_speed);
                }
                if (SportRunManager.this.mSportRunCallBack != null && SportRunManager.this.mIsSupportRealPace) {
                    SportLogHelper.saveSportLog(SportRunManager.TAG, "onReplyExchangeDateIng:配速 " + strComputeTimeMS + AppInfo.DELIM + avgSpeed + AppInfo.DELIM + SportRunManager.this.mIsSupportRealPace);
                    SportRunManager.this.mSportRunCallBack.sportFivePeaceAndSpeed(strComputeTimeMS, avgSpeed);
                }
                SportRunManager.this.mHeartRate = v3AppExchangeDataIngDeviceReplyData.heart_rate;
            }
        }

        @Override // com.ido.life.ble.AppExchangeV3DataCallBackWrapper, com.ido.ble.callback.V3AppExchangeDataCallBack.ICallBack
        public void onReplyExchangeDataEndData(V3AppExchangeDataDeviceReplayEndData v3AppExchangeDataDeviceReplayEndData) {
            super.onReplyExchangeDataEndData(v3AppExchangeDataDeviceReplayEndData);
            CommonLogUtil.d("v3--", "onReplyExchangeDataEndData: v3 " + GsonUtil.toJson(v3AppExchangeDataDeviceReplayEndData));
            SportLogHelper.saveSportLog(SportRunManager.TAG, "onReplyExchangeDataEndData: " + v3AppExchangeDataDeviceReplayEndData.toString() + SportRunManager.this.mIsEnd);
            SportRunManager.this.setSportHealth(v3AppExchangeDataDeviceReplayEndData);
            if (!SportRunManager.this.mIsEnd || SportRunManager.this.mSportRunCallBack == null) {
                return;
            }
            SportRunManager.this.mSportRunCallBack.sportStop(SportRunManager.this.mIsSaveData, SportRunManager.this.mSportHealth, SportRunManager.this.mLatLngDomainList);
        }

        @Override // com.ido.life.ble.AppExchangeV3DataCallBackWrapper, com.ido.ble.callback.V3AppExchangeDataCallBack.ICallBack
        public void onReplyExchangeHeartRateData(V3AppExchangeDataHeartRate v3AppExchangeDataHeartRate) {
            super.onReplyExchangeHeartRateData(v3AppExchangeDataHeartRate);
            CommonLogUtil.d("v3--", "onReplyExchangeHeartRateData: v3 " + v3AppExchangeDataHeartRate.toString());
            SportLogHelper.saveSportLog(SportRunManager.TAG, "心率数据 onReplyExchangeHeartRateData: " + v3AppExchangeDataHeartRate.toString());
            if (v3AppExchangeDataHeartRate.heart_rate_history == null) {
                return;
            }
            SportRunManager.this.mHeartRateList.addAll(v3AppExchangeDataHeartRate.heart_rate_history);
        }
    };
    private AppExchangeDataCallBackWrapper changCallBack = new AppExchangeDataCallBackWrapper() { // from class: com.ido.life.module.sport.SportRunManager.3
        @Override // com.ido.life.ble.AppExchangeDataCallBackWrapper, com.ido.ble.callback.AppExchangeDataCallBack.ICallBack
        public void onReplyExchangeDataStart(AppExchangeDataStartDeviceReplyData appExchangeDataStartDeviceReplyData) {
            SportLogHelper.saveSportLog(SportRunManager.TAG, "onReplyExchangeDataStart: ");
            SportRunManager.this.handlerReplay(appExchangeDataStartDeviceReplyData.ret_code);
        }

        @Override // com.ido.life.ble.AppExchangeDataCallBackWrapper, com.ido.ble.callback.AppExchangeDataCallBack.ICallBack
        public void onReplyExchangeDateIng(AppExchangeDataIngDeviceReplyData appExchangeDataIngDeviceReplyData) {
            SportLogHelper.saveSportLog(SportRunManager.TAG, "onReplyExchangeDateIng----code=" + appExchangeDataIngDeviceReplyData.toString());
            if (appExchangeDataIngDeviceReplyData.status == 1) {
                int[] iArr = appExchangeDataIngDeviceReplyData.hr_value;
                if (iArr != null && iArr.length > 0 && appExchangeDataIngDeviceReplyData.interval_second > 0) {
                    SportRunManager.this.serialHeartRate.put(Integer.valueOf(appExchangeDataIngDeviceReplyData.hr_value_serial), iArr);
                    for (int i = 0; i < iArr.length; i++) {
                        CommonLogUtil.d("有效心率", String.valueOf(iArr[i]));
                        SportRunManager.this.mHeartRateList.add(Integer.valueOf(iArr[i]));
                    }
                    int size = SportRunManager.this.mHeartRateList.size();
                    int iIntValue = 0;
                    for (int i2 = 0; i2 < size; i2++) {
                        iIntValue += ((Integer) SportRunManager.this.mHeartRateList.get(i2)).intValue();
                        SportRunManager sportRunManager = SportRunManager.this;
                        sportRunManager.maxHeartRate = Math.max(sportRunManager.maxHeartRate, ((Integer) SportRunManager.this.mHeartRateList.get(i2)).intValue());
                    }
                    SportRunManager.this.mSportHealth.setAvgHrValue(iIntValue / size);
                    SportRunManager.this.mSportHealth.setMaxHrValue(SportRunManager.this.maxHeartRate);
                }
                SportRunManager.this.mSportHealth.setNumCalories(appExchangeDataIngDeviceReplyData.calories);
                SportRunManager.this.mSportHealth.setNumSteps(appExchangeDataIngDeviceReplyData.step);
                SportRunManager.this.mSportHealth.setDistance(appExchangeDataIngDeviceReplyData.distance);
                SportRunManager.this.mHeartRate = appExchangeDataIngDeviceReplyData.cur_hr_value;
            }
        }

        @Override // com.ido.life.ble.AppExchangeDataCallBackWrapper, com.ido.ble.callback.AppExchangeDataCallBack.ICallBack
        public void onReplyExchangeDateStop(AppExchangeDataStopDeviceReplyData appExchangeDataStopDeviceReplyData) {
            SportLogHelper.saveSportLog(SportRunManager.TAG, "onReplyExchangeDateStop--设备回复结束运动指令的回调: " + appExchangeDataStopDeviceReplyData);
            if (appExchangeDataStopDeviceReplyData.errCode == 0) {
                SportRunManager.this.mIsEnd = true;
                if (SportRunManager.this.mIsV3Exchange) {
                    SportRunManager sportRunManager = SportRunManager.this;
                    sportRunManager.stopSuccess(sportRunManager.mIsSaveData, false);
                    SportLogHelper.saveSportLog(SportRunManager.TAG, "onReplyExchangeDateStop--设备回复结束运动指令的回调,调用endV3Cmd获取最新的运动记录");
                    SportRunManager.this.endV3Cmd();
                    return;
                }
                SportRunManager sportRunManager2 = SportRunManager.this;
                sportRunManager2.stopSuccess(sportRunManager2.mIsSaveData, true);
                return;
            }
            if (SportRunManager.this.mSportRunCallBack != null) {
                SportRunManager.this.mSportRunCallBack.sportStop(SportRunManager.this.mIsSaveData, SportRunManager.this.mSportHealth, SportRunManager.this.mLatLngDomainList);
            }
        }

        @Override // com.ido.life.ble.AppExchangeDataCallBackWrapper, com.ido.ble.callback.AppExchangeDataCallBack.ICallBack
        public void onReplyExchangeDatePause(AppExchangeDataPauseDeviceReplyData appExchangeDataPauseDeviceReplyData) {
            SportLogHelper.saveSportLog(SportRunManager.TAG, "onReplyExchangeDatePause: ");
            if (appExchangeDataPauseDeviceReplyData.err_code == 0) {
                SportRunManager.this.pauseSuccess();
                return;
            }
            CommonLogUtil.d("onSysEvt_SWITCH_APP_PAUSE");
            if (SportRunManager.this.mSportRunCallBack != null) {
                SportRunManager.this.mSportRunCallBack.sportPause(false);
            }
        }

        @Override // com.ido.life.ble.AppExchangeDataCallBackWrapper, com.ido.ble.callback.AppExchangeDataCallBack.ICallBack
        public void onReplyExchangeDateResume(AppExchangeDataResumeDeviceReplyData appExchangeDataResumeDeviceReplyData) {
            SportLogHelper.saveSportLog(SportRunManager.TAG, "onReplyExchangeDateResume:  " + appExchangeDataResumeDeviceReplyData.toString());
            if (appExchangeDataResumeDeviceReplyData.err_code == 0) {
                SportRunManager.this.resumeSuccess(false);
            } else if (SportRunManager.this.mSportRunCallBack != null) {
                SportRunManager.this.mSportRunCallBack.sportResume(false);
            }
        }

        @Override // com.ido.life.ble.AppExchangeDataCallBackWrapper, com.ido.ble.callback.AppExchangeDataCallBack.ICallBack
        public void onDeviceNoticeAppStop(DeviceNoticeAppExchangeDataStopPara deviceNoticeAppExchangeDataStopPara) {
            SportLogHelper.saveSportLog(SportRunManager.TAG, "onDeviceNoticeAppStop--设备通知APP结束: " + deviceNoticeAppExchangeDataStopPara.toString());
            DeviceNoticeAppExchangeDataStopAppReplyData deviceNoticeAppExchangeDataStopAppReplyData = new DeviceNoticeAppExchangeDataStopAppReplyData();
            deviceNoticeAppExchangeDataStopAppReplyData.err_code = 0;
            if (SportRunManager.this.mSportHealth != null) {
                deviceNoticeAppExchangeDataStopAppReplyData.calories = SportRunManager.this.mSportHealth.getNumCalories();
                deviceNoticeAppExchangeDataStopAppReplyData.duration = SportRunManager.this.mSportHealth.getTotalSeconds();
                deviceNoticeAppExchangeDataStopAppReplyData.distance = SportRunManager.this.mSportHealth.getDistance();
                BleSdkWrapper.replyDeviceNoticeAppExchangeDataStop(deviceNoticeAppExchangeDataStopAppReplyData);
                SportRunManager.this.mIsSaveData = deviceNoticeAppExchangeDataStopPara.is_save == 1;
                CommonLogUtil.d(SportRunManager.TAG, "run: " + SportRunManager.this.mIsSaveData + AppInfo.DELIM + SportRunManager.this.mIsEnd);
                SportRunManager.this.mIsEnd = true;
                if (SportRunManager.this.mIsV3Exchange) {
                    SportLogHelper.saveSportLog(SportRunManager.TAG, "onDeviceNoticeAppStop--设备通知APP结束,调用endV3Cmd获取最新的运动记录");
                    SportRunManager.this.endV3Cmd();
                    return;
                } else {
                    SportRunManager sportRunManager = SportRunManager.this;
                    sportRunManager.stopSuccess(sportRunManager.mIsSaveData, true);
                    return;
                }
            }
            SportLogHelper.saveSportLog(SportRunManager.TAG, "onDeviceNoticeAppStop: 对象为空");
        }

        @Override // com.ido.life.ble.AppExchangeDataCallBackWrapper, com.ido.ble.callback.AppExchangeDataCallBack.ICallBack
        public void onDeviceNoticeAppPause(DeviceNoticeAppExchangeDataPausePara deviceNoticeAppExchangeDataPausePara) {
            StringBuilder sb = new StringBuilder();
            sb.append("onDeviceNoticeAppPause: ");
            sb.append(deviceNoticeAppExchangeDataPausePara == null ? "null" : deviceNoticeAppExchangeDataPausePara.toString());
            SportLogHelper.saveSportLog(SportRunManager.TAG, sb.toString());
            DeviceNoticeAppExchangeDataPauseAppReplyData deviceNoticeAppExchangeDataPauseAppReplyData = new DeviceNoticeAppExchangeDataPauseAppReplyData();
            deviceNoticeAppExchangeDataPauseAppReplyData.err_code = 0;
            BleSdkWrapper.replyDeviceNoticeAppExchangeDataPause(deviceNoticeAppExchangeDataPauseAppReplyData);
            SportRunManager.this.pauseSuccess();
        }

        @Override // com.ido.life.ble.AppExchangeDataCallBackWrapper, com.ido.ble.callback.AppExchangeDataCallBack.ICallBack
        public void onDeviceNoticeAppResume(DeviceNoticeAppExchangeDataResumePara deviceNoticeAppExchangeDataResumePara) {
            SportLogHelper.saveSportLog(SportRunManager.TAG, "run: " + ("onDeviceNoticeAppResume----code=" + deviceNoticeAppExchangeDataResumePara.toString()));
            DeviceNoticeAppExchangeDataResumeAppReplyData deviceNoticeAppExchangeDataResumeAppReplyData = new DeviceNoticeAppExchangeDataResumeAppReplyData();
            deviceNoticeAppExchangeDataResumeAppReplyData.err_code = 0;
            BleSdkWrapper.replyDeviceNoticeAppExchangeDataResume(deviceNoticeAppExchangeDataResumeAppReplyData);
            SportRunManager.this.resumeSuccess(false);
        }
    };
    private Runnable disConnRun = new Runnable() { // from class: com.ido.life.module.sport.SportRunManager.4
        @Override // java.lang.Runnable
        public void run() {
            SportLogHelper.saveSportLog(SportRunManager.TAG, "run: 超时了.......");
            SportRunManager.this.mIsAppComplete = true;
            if (SportRunManager.this.connectCallBack != null) {
                SportRunManager.this.connectCallBack.connectTimeOut();
            }
        }
    };
    private BaseConnCallback connCallBack = new BaseConnCallback() { // from class: com.ido.life.module.sport.SportRunManager.5
        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInitCompleted(String str) {
            super.onInitCompleted(str);
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
            SportLogHelper.saveSportLog(SportRunManager.TAG, "onConnectBreak: macAddress=" + str);
            if (SportRunManager.this.connectCallBack != null) {
                SportRunManager.this.connectCallBack.bleDisconnect();
            }
            SportRunManager.this.updateHandler.postDelayed(SportRunManager.this.disConnRun, 15000L);
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectSuccess(String str) {
            super.onConnectSuccess(str);
            SportLogHelper.saveSportLog(SportRunManager.TAG, "onConnectSuccess: " + str);
            SportRunManager.this.mIsAppComplete = false;
            SportRunManager.this.updateHandler.removeCallbacks(SportRunManager.this.disConnRun);
            if (SportRunManager.this.connectCallBack != null) {
                SportRunManager.this.connectCallBack.bleConnect();
            }
        }
    };
    private int deviceSportTime = -1;

    public interface ISportConnectCallBack {
        void bleConnect();

        void bleDisconnect();

        void connectTimeOut();
    }

    public interface ISportRunCallBack {
        void sportFivePeaceAndSpeed(String str, String str2);

        void sportPause(boolean z);

        void sportResume(boolean z);

        void sportRunning(SportHealth sportHealth, int i, LatLngBean latLngBean, int i2, boolean z);

        void sportStop(boolean z, SportHealth sportHealth, List<LatLngBean> list);
    }

    public interface ISportStartCallBack {
        void sportChargePower();

        void sportRunInAlexa();

        void sportStartFailed();

        void sportStartFailedByLowPower();

        void sportStartInCalling();

        void sportStartSuccess();
    }

    static /* synthetic */ long access$3108(SportRunManager sportRunManager) {
        long j = sportRunManager.changeIndex;
        sportRunManager.changeIndex = 1 + j;
        return j;
    }

    public void setSportRunCallback(ISportRunCallBack iSportRunCallBack) {
        this.mSportRunCallBack = iSportRunCallBack;
    }

    public void setSportStartCallback(ISportStartCallBack iSportStartCallBack) {
        this.mSportStart = iSportStartCallBack;
    }

    public void setConnectCallBack(ISportConnectCallBack iSportConnectCallBack) {
        this.connectCallBack = iSportConnectCallBack;
    }

    public long getUserId() {
        return this.mUserId;
    }

    public void setUserId(long j) {
        this.mUserId = j;
    }

    public void setIsOutDoor(boolean z) {
        this.mIsOut = z;
    }

    public boolean isCompleteRun() {
        return this.mIsCompleteRun;
    }

    public void startLocation() {
        if (this.mIsOut) {
            this.gdLocationManager = GDLocationManager.getInstance(IdoApp.getAppContext());
            this.gdLocationManager.startLocation(this.locationStringListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double getDistanceByGps(LatLngBean latLngBean) {
        LatLngBean latLngBean2 = this.lastLatlng;
        if (latLngBean2 == null) {
            this.lastLatlng = latLngBean;
        } else {
            double distance = MapHelper.getDistance(latLngBean, latLngBean2);
            this.mOneMinuteDistances = distance;
            SportLogHelper.saveSportLog(TAG, "getDistanceByGps: " + latLngBean.toString() + AppInfo.DELIM + this.lastLatlng.toString() + "两点之间的距离," + distance + ",总距离," + this.mTotalDistance);
            this.lastLatlng = latLngBean;
            this.mTotalDistance = this.mTotalDistance + distance;
        }
        return this.mTotalDistance;
    }

    public void stopLocation() {
        GDLocationManager gDLocationManager = this.gdLocationManager;
        if (gDLocationManager != null) {
            gDLocationManager.stopLocation(this.locationStringListener);
        }
    }

    private SportRunManager() {
    }

    public void setNameByType(int i) {
        if (i == 4) {
            this.mSportName = LanguageUtil.getLanguageText(R.string.sport_record_walk_onfoot);
            return;
        }
        if (i == 52) {
            this.mSportName = LanguageUtil.getLanguageText(R.string.sport_walk_outdoor);
            return;
        }
        if (i != 53) {
            switch (i) {
                case 48:
                    this.mSportName = LanguageUtil.getLanguageText(R.string.sport_record_run_outdoor);
                    break;
                case 49:
                    this.mSportName = LanguageUtil.getLanguageText(R.string.sport_record_run_indoor);
                    break;
                case 50:
                    this.mSportName = LanguageUtil.getLanguageText(R.string.sport_record_ride_outdoor);
                    break;
            }
            return;
        }
        this.mSportName = LanguageUtil.getLanguageText(R.string.sport_walk_indoor);
    }

    public static SportRunManager getInstance() {
        if (instance == null) {
            synchronized (SportRunManager.class) {
                if (instance == null) {
                    instance = new SportRunManager();
                }
            }
        }
        return instance;
    }

    public void startRun(int i, boolean z) {
        SportLogHelper.saveSportLog(TAG, "startRun: " + i + ",isApp" + z);
        clearCache();
        this.deviceSportTime = -1;
        getFunction();
        this.mIsAppComplete = z;
        this.mType = i;
        setNameByType(this.mType);
        this.mTotalDistance = 0.0d;
        initSwitchData(i, this.FORCE_START_INVALID);
        this.mHeartRate = 0;
        this.maxHeartRate = 0;
        if (!z) {
            this.mSourceType = 3;
            startCmd(this.FORCE_START_INVALID);
            if (this.mIsV3Exchange) {
                BleSdkWrapper.registerAppExchangeDataCallBack(this.changCallBack);
                BleSdkWrapper.registerV3AppExchangeDataCallBack(this.changeV3CallBack);
            } else {
                BleSdkWrapper.registerAppExchangeDataCallBack(this.changCallBack);
            }
            BleSdkWrapper.registerConnectCallBack(this.connCallBack);
            return;
        }
        this.mSourceType = 1;
        sportStartSuccess();
    }

    private void getFunction() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo == null || !BleSdkWrapper.isConnected()) {
            return;
        }
        this.mIsV3Exchange = supportFunctionInfo.ex_table_main9_v3_activity_exchange_data;
        this.mIsSupportRealPace = supportFunctionInfo.V3_support_v3_exchange_data_reply_add_real_time_speed_pace;
    }

    public void forceStartRun() {
        startCmd(this.FORCE_START_VALID);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSportHealth(V3AppExchangeDataDeviceReplayEndData v3AppExchangeDataDeviceReplayEndData) {
        if (this.mSportHealth == null) {
            SportLogHelper.saveSportLog(TAG, "设置40s数据sportHealth为空");
            return;
        }
        if (v3AppExchangeDataDeviceReplayEndData.distance != 0) {
            this.mSportHealth.setDistance(v3AppExchangeDataDeviceReplayEndData.distance);
        }
        if (v3AppExchangeDataDeviceReplayEndData.avg_speed != 0) {
            this.mSportHealth.setAvgSpeed(v3AppExchangeDataDeviceReplayEndData.avg_speed);
        }
        this.mSportHealth.setMaxSpeed(v3AppExchangeDataDeviceReplayEndData.max_speed);
        this.mSportHealth.setAvgPace(v3AppExchangeDataDeviceReplayEndData.km_speed);
        this.mSportHealth.setMinPace(v3AppExchangeDataDeviceReplayEndData.fast_km_speed);
        this.mSportHealth.setStepRate(v3AppExchangeDataDeviceReplayEndData.avg_step_frequency);
        this.mSportHealth.setStepRateMax(v3AppExchangeDataDeviceReplayEndData.max_step_frequency);
        this.mSportHealth.setStepRange(v3AppExchangeDataDeviceReplayEndData.avg_step_stride);
        this.mSportHealth.setStepRangeMax(v3AppExchangeDataDeviceReplayEndData.max_step_stride);
        this.mSportHealth.setNumSteps(v3AppExchangeDataDeviceReplayEndData.step);
        this.mSportHealth.setTotalSeconds(v3AppExchangeDataDeviceReplayEndData.durations);
        this.mSportHealth.setNumCalories(v3AppExchangeDataDeviceReplayEndData.calories);
        this.mSportHealth.setAvgHrValue(v3AppExchangeDataDeviceReplayEndData.avg_hr_value);
        this.mSportHealth.setMaxHrValue(v3AppExchangeDataDeviceReplayEndData.max_hr_value);
        this.mSportHealth.setExtremeSecond(v3AppExchangeDataDeviceReplayEndData.extreme_exercise_time);
        this.mSportHealth.setAnaerobicSecond(v3AppExchangeDataDeviceReplayEndData.anaerobic_exercise_time);
        this.mSportHealth.setAerobicSeconds(v3AppExchangeDataDeviceReplayEndData.aerobic_exercise_time);
        this.mSportHealth.setBurnFatSeconds(v3AppExchangeDataDeviceReplayEndData.fat_burning_time);
        this.mSportHealth.setWarmupSeconds(v3AppExchangeDataDeviceReplayEndData.warm_up_time);
        this.mSportHealth.setIntervalSecond(v3AppExchangeDataDeviceReplayEndData.hr_data_interval_minute);
        this.mSportHealth.setHrDataIntervalMinute(v3AppExchangeDataDeviceReplayEndData.hr_data_interval_minute);
        if (v3AppExchangeDataDeviceReplayEndData.steps_frequency != null) {
            this.mRateList.addAll(v3AppExchangeDataDeviceReplayEndData.steps_frequency);
        }
        if (v3AppExchangeDataDeviceReplayEndData.km_speed_s != null) {
            this.mMetricItemsList.addAll(v3AppExchangeDataDeviceReplayEndData.km_speed_s);
        }
        if (v3AppExchangeDataDeviceReplayEndData.items_mi_speed != null) {
            this.mBritishItemsList.addAll(v3AppExchangeDataDeviceReplayEndData.items_mi_speed);
        }
        if (v3AppExchangeDataDeviceReplayEndData.recover_time > 0) {
            this.mSportHealth.setRecoverTime(v3AppExchangeDataDeviceReplayEndData.recover_time);
            Calendar calendar = Calendar.getInstance();
            calendar.add(11, v3AppExchangeDataDeviceReplayEndData.recover_time);
            this.mSportHealth.setDiscoverDateTime(DateUtil.format(calendar, "yyyy-MM-dd HH:mm:ss"));
        }
        if (v3AppExchangeDataDeviceReplayEndData.training_effect != 0) {
            this.mSportHealth.setTrainingEffectScore(v3AppExchangeDataDeviceReplayEndData.training_effect / 10.0f);
        }
        this.mSportHealth.setVo2max(v3AppExchangeDataDeviceReplayEndData.vo2max);
        saveSportRunData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopSuccess(boolean z, boolean z2) {
        ISportRunCallBack iSportRunCallBack;
        SportLogHelper.saveSportLog(TAG, "运动结束保存运动的数据---isSave: " + z);
        this.mIsCompleteRun = true;
        this.sportState = 2;
        this.mIsSaveData = z;
        stopUpdateTimer();
        stopLocation();
        BleSdkWrapper.unregisterConnectCallBack(this.connCallBack);
        BleSdkWrapper.unregisterAppExchangeDataCallBack(this.changCallBack);
        saveSportRunData();
        if (!z2 || (iSportRunCallBack = this.mSportRunCallBack) == null) {
            return;
        }
        iSportRunCallBack.sportStop(this.mIsSaveData, this.mSportHealth, this.mLatLngDomainList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void saveSportRunData() {
        if (this.mSportHealth == null) {
            SportLogHelper.saveSportLog(TAG, "saveSportRunData: 保存运动信息为空");
            return;
        }
        SportLogHelper.saveSportLog(TAG, "saveSportRunData: 时间，" + this.mSportHealth.getTotalSeconds() + "距离," + this.mSportHealth.getDistance());
        this.mSportHealth.setEndTime(TimeUtil.convTimeDetail(System.currentTimeMillis()));
        this.mSportHealth.setHr_data_vlaue_json(GsonUtil.toJson(this.mHeartRateList));
        this.mSportHealth.setStepItem(GsonUtil.toJson(this.mRateList));
        this.mSportHealth.setSourceType(this.mSourceType);
        SportItemPace pace = this.mSportHealth.getPace();
        if (pace == null) {
            pace = new SportItemPace();
        }
        pace.setMetricItems(GsonUtil.toJson(this.mMetricItemsList));
        pace.setBritishItems(GsonUtil.toJson(this.mBritishItemsList));
        this.mSportHealth.setPace(pace);
        if (this.mIsSaveData) {
            List<LatLngBean> list = this.mLatLngDomainList;
            if (list != null && list.size() > 0) {
                CommonLogUtil.d("----经纬度---" + this.mLatLngDomainList.size());
                SportHealthDataManager.addSportGpsData(this.mLatLngDomainList, TimeUtil.convTimeYmdhmsToLong(this.mSportHealth.getStartTime()), RunTimeUtil.getInstance().getUserId());
                List<LatLngBean> list2 = this.mLatLngDomainList;
                if (list2 != null) {
                    list2.clear();
                }
                this.mSportHealth.setIsLocus(1);
                this.mSportHealth.setGpsSourceType(1);
            }
            SportHealth sportHealth = this.mSportHealth;
            sportHealth.setDateTime(sportHealth.getStartTime());
            this.mSportHealth.setUploaded(false);
            this.mSportHealth.setType(this.mType);
            setTarget(this.mSportHealth);
            this.mSportHealth.setLoadDetail(true);
            SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
            if (supportFunctionInfo != null) {
                this.mSportHealth.setIsSupportTrainingEffect(supportFunctionInfo.V3_sync_v3_activity_add_param ? 1 : 0);
            }
            if (this.mSportHealth.getTotalSeconds() > 60) {
                this.mSportHealth.setUserId(this.mUserId);
                SportHealthDataManager.addDataFromApp(this.mSportHealth);
            }
        }
    }

    private void setTarget(SportHealth sportHealth) {
        SportSetting sportSetting = SportSettingPreference.getSportSetting();
        if (sportSetting == null || !sportSetting.isSportTarget()) {
            sportHealth.setTargetType(0);
            return;
        }
        if (sportSetting.isSportTarget()) {
            if (sportSetting.getStep() != 0) {
                sportHealth.setTargetType(1);
                sportHealth.setTargetValue(sportSetting.getStep());
                return;
            }
            if (sportSetting.getDistance() != 0) {
                sportHealth.setTargetType(2);
                sportHealth.setTargetValue(sportSetting.getDistance());
            } else if (sportSetting.getCategory() != 0) {
                sportHealth.setTargetType(3);
                sportHealth.setTargetValue(sportSetting.getCategory());
            } else if (sportSetting.getTime() != 0) {
                sportHealth.setTargetType(4);
                sportHealth.setTargetValue(sportSetting.getTime());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resumeSuccess(boolean z) {
        this.sportState = 1;
        startUpdateTimer(z);
        startLocation();
        ISportRunCallBack iSportRunCallBack = this.mSportRunCallBack;
        if (iSportRunCallBack != null) {
            iSportRunCallBack.sportResume(true);
        }
    }

    private void startCmd(int i) {
        int vo2max;
        this.switchDataAppStart.force_start = i;
        int avgWeekActivityTime = SportHealthDataManager.getAvgWeekActivityTime();
        SportHealth lastSportVo = SportHealthDataManager.getLastSportVo();
        int sportRecoverTime = SportHealthDataManager.getSportRecoverTime();
        if (lastSportVo != null) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "startCmd: " + lastSportVo.getVo2max() + AppInfo.DELIM + lastSportVo.getDateTime());
            vo2max = lastSportVo.getVo2max();
        } else {
            vo2max = 0;
        }
        AppExchangeDataStartPara appExchangeDataStartPara = this.switchDataAppStart;
        appExchangeDataStartPara.vo2max = vo2max;
        appExchangeDataStartPara.recover_time = sportRecoverTime;
        appExchangeDataStartPara.avg_week_activity_time = avgWeekActivityTime;
        SportLogHelper.saveSportLog(TAG, "startCmd: 发送开始命令:" + this.switchDataAppStart.toString());
        BleSdkWrapper.appSwitchDataStart(this.switchDataAppStart);
    }

    public void initSwitchData(int i, int i2) {
        this.switchDataAppStart.day = com.ido.life.util.TimeUtil.getDay();
        this.switchDataAppStart.hour = com.ido.life.util.TimeUtil.getHour();
        this.switchDataAppStart.minute = com.ido.life.util.TimeUtil.getMinute();
        this.switchDataAppStart.second = com.ido.life.util.TimeUtil.getSecond();
        AppExchangeDataStartPara appExchangeDataStartPara = this.switchDataAppStart;
        appExchangeDataStartPara.sportType = i;
        appExchangeDataStartPara.force_start = i2;
        this.switchDataAppIng.day = appExchangeDataStartPara.day;
        this.switchDataAppIng.hour = this.switchDataAppStart.hour;
        this.switchDataAppIng.minute = this.switchDataAppStart.minute;
        this.switchDataAppIng.second = this.switchDataAppStart.second;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerReplay(int i) {
        ISportStartCallBack iSportStartCallBack;
        SportLogHelper.saveSportLog(TAG, "handlerReplay: startRelayCode=" + i);
        if (i == 0) {
            sportStartSuccess();
            return;
        }
        if (i == 1) {
            ISportStartCallBack iSportStartCallBack2 = this.mSportStart;
            if (iSportStartCallBack2 != null) {
                iSportStartCallBack2.sportStartFailed();
                return;
            }
            return;
        }
        if (i == 2) {
            ISportStartCallBack iSportStartCallBack3 = this.mSportStart;
            if (iSportStartCallBack3 != null) {
                iSportStartCallBack3.sportStartFailedByLowPower();
                return;
            }
            return;
        }
        if (i == 3) {
            ISportStartCallBack iSportStartCallBack4 = this.mSportStart;
            if (iSportStartCallBack4 != null) {
                iSportStartCallBack4.sportChargePower();
                return;
            }
            return;
        }
        if (i != 4) {
            if (i == 5 && (iSportStartCallBack = this.mSportStart) != null) {
                iSportStartCallBack.sportStartInCalling();
                return;
            }
            return;
        }
        ISportStartCallBack iSportStartCallBack5 = this.mSportStart;
        if (iSportStartCallBack5 != null) {
            iSportStartCallBack5.sportRunInAlexa();
        }
    }

    private void sportStartSuccess() {
        this.mIsCompleteRun = false;
        initTrainDomain();
        SportLogHelper.saveSportLog(TAG, "sportStartSuccess: ");
        stopUpdateTimer();
        startUpdateTimer(true);
        ISportStartCallBack iSportStartCallBack = this.mSportStart;
        if (iSportStartCallBack != null) {
            iSportStartCallBack.sportStartSuccess();
        }
    }

    private void initTrainDomain() {
        this.mSportHealth = new SportHealth();
        this.mSendDistance = 0.0d;
        this.mIsRemoveFirstPoint = true;
        Date dateByHMS = DateUtil.getDateByHMS(this.switchDataAppStart.hour, this.switchDataAppStart.minute, this.switchDataAppStart.second);
        this.mBritishItemsList = new ArrayList();
        this.mMetricItemsList = new ArrayList();
        this.mRateList = new ArrayList();
        this.mSportHealth.setStartTime(TimeUtil.convTimeDetail(dateByHMS.getTime()));
        this.mSportHealth.setDateTime(TimeUtil.convTimeDetail(dateByHMS.getTime()));
        SportLogHelper.saveSportLog(TAG, "initTrainDomain: " + this.mSportHealth.toString());
        this.mSportHealth.setSourceMac(BleSdkWrapper.getBindMac());
        this.mProtocol = Protocol.getInstance();
        this.mProtocol.initType(this.mType);
        this.mProtocol.initParameter();
        SportLogHelper.saveGpsLog(TAG, this.mSportName);
    }

    public void startUpdateTimer(boolean z) {
        this.lastCurrentTimeMillis = System.currentTimeMillis() / 1000;
        this.updateHandler.removeCallbacks(this.runTimerTask);
        if (z) {
            this.updateHandler.postDelayed(this.runTimerTask, 1000L);
        } else {
            this.updateHandler.post(this.runTimerTask);
        }
    }

    private void stopUpdateTimer() {
        SportLogHelper.saveSportLog(TAG, "stopUpdateTimer: ");
        this.updateHandler.removeCallbacks(this.runTimerTask);
    }

    public void pauseRun() {
        if (this.mIsAppComplete || !BleSdkWrapper.isConnected()) {
            pauseSuccess();
            return;
        }
        CommonLogUtil.d("*************************pauserRun");
        SportLogHelper.saveSportLog(TAG, "pauseRun: ");
        BleSdkWrapper.appSwitchPause(this.switchDataAppStart.day, this.switchDataAppStart.hour, this.switchDataAppStart.minute, this.switchDataAppStart.second);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pauseSuccess() {
        this.sportState = 3;
        SportLogHelper.saveSportLog(TAG, "pauseSuccess: ");
        stopUpdateTimer();
        CommonLogUtil.d(TAG, "pauseSuccess: stopLocation");
        stopLocation();
        ISportRunCallBack iSportRunCallBack = this.mSportRunCallBack;
        if (iSportRunCallBack != null) {
            iSportRunCallBack.sportPause(true);
        }
    }

    public void resumeRun() {
        if (this.mIsAppComplete || !BleSdkWrapper.isConnected()) {
            resumeSuccess(true);
            CommonLogUtil.d("resumeRun---1");
        } else {
            CommonLogUtil.d("resumeRun---2");
            BleSdkWrapper.appSwitchRestore(this.switchDataAppStart.day, this.switchDataAppStart.hour, this.switchDataAppStart.minute, this.switchDataAppStart.second);
        }
    }

    public void onRestoreInstanceState() {
        ISportRunCallBack iSportRunCallBack = this.mSportRunCallBack;
        if (iSportRunCallBack != null) {
            iSportRunCallBack.sportRunning(this.mSportHealth, this.mHeartRate, this.mCurrentLatLng, this.mGpsSignValue, this.mIsRemoveFirstPoint);
        }
        if (this.mIsV3Exchange) {
            BleSdkWrapper.unregisterV3AppExchangeDataCallBack(this.changeV3CallBack);
            BleSdkWrapper.registerV3AppExchangeDataCallBack(this.changeV3CallBack);
        }
        BleSdkWrapper.unregisterAppExchangeDataCallBack(this.changCallBack);
        BleSdkWrapper.registerAppExchangeDataCallBack(this.changCallBack);
        BleSdkWrapper.registerConnectCallBack(this.connCallBack);
        if (this.sportState == 3) {
            pauseSuccess();
        } else {
            startUpdateTimer(false);
        }
    }

    public void stopRun(final boolean z) {
        this.mIsSaveData = z;
        ThreadUtil.runOnMainThread(new Runnable() { // from class: com.ido.life.module.sport.SportRunManager.6
            @Override // java.lang.Runnable
            public void run() {
                SportLogHelper.saveSportLog(SportRunManager.TAG, "结束运动------isAppComplete:" + SportRunManager.this.mIsAppComplete + ",isConnected:" + BleSdkWrapper.isConnected());
                if (SportRunManager.this.mIsAppComplete || !BleSdkWrapper.isConnected()) {
                    SportRunManager.this.mIsEnd = true;
                    SportLogHelper.saveSportLog(SportRunManager.TAG, "app单独发起运动或者断连结束运动---run: stopRun :mIsEnd" + SportRunManager.this.mIsEnd);
                    SportRunManager.this.stopSuccess(z, true);
                    return;
                }
                SportRunManager sportRunManager = SportRunManager.this;
                sportRunManager.endCmd(z ? sportRunManager.SAVE_END : sportRunManager.NO_SAVE_END);
            }
        });
    }

    public void endCmd(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("endCmd: ");
        sb.append(i);
        sb.append(",mSportHealth=");
        SportHealth sportHealth = this.mSportHealth;
        sb.append(sportHealth == null ? "null" : sportHealth.toString());
        SportLogHelper.saveSportLog(TAG, sb.toString());
        SportHealth sportHealth2 = this.mSportHealth;
        if (sportHealth2 == null) {
            return;
        }
        BleSdkWrapper.appSwitchDataEnd(this.switchDataAppStart.day, this.switchDataAppStart.hour, this.switchDataAppStart.minute, this.switchDataAppStart.second, this.mType, sportHealth2.getTotalSeconds(), this.mSportHealth.getNumCalories(), this.mSportHealth.getDistance(), i);
    }

    public void endV3Cmd() {
        SportLogHelper.saveSportLog(TAG, "endV3Cmd: " + this.mIsV3Exchange);
        BleSdkWrapper.v3AppSwitchDataEnd();
    }

    public void close() {
        this.mSportRunCallBack = null;
        unRegisterConnectCallBcak();
        SportLogHelper.saveSportLog(TAG, "close: ");
        stopUpdateTimer();
        BleSdkWrapper.unregisterAppExchangeDataCallBack(this.changCallBack);
        BleSdkWrapper.unregisterV3AppExchangeDataCallBack(this.changeV3CallBack);
    }

    public void unRegisterConnectCallBcak() {
        BleSdkWrapper.unregisterConnectCallBack(this.connCallBack);
    }

    public boolean getSportInit() {
        SportHealth sportHealth = this.mSportHealth;
        return sportHealth == null || TextUtils.isEmpty(sportHealth.getStartTime());
    }

    public String getDateTime() {
        SportHealth sportHealth = this.mSportHealth;
        return sportHealth != null ? sportHealth.getStartTime() : "";
    }

    public List<LatLngBean> getmLatLngDomainList() {
        return this.mLatLngDomainList;
    }

    private class RunTimerTask extends TimerTask {
        private RunTimerTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
            int totalSeconds = SportRunManager.this.mSportHealth.getTotalSeconds() + Math.max(1, (int) (jCurrentTimeMillis - SportRunManager.this.lastCurrentTimeMillis));
            if (SportRunManager.this.deviceSportTime - totalSeconds > 2) {
                totalSeconds = SportRunManager.this.deviceSportTime;
            }
            SportRunManager.this.mSportHealth.setTotalSeconds(totalSeconds);
            SportRunManager.this.lastCurrentTimeMillis = jCurrentTimeMillis;
            CommonLogUtil.d(SportRunManager.TAG, "duration:" + SportRunManager.this.mSportHealth.getTotalSeconds());
            SportRunManager.this.completeCalorie();
            ThreadUtil.runOnMainThread(new Runnable() { // from class: com.ido.life.module.sport.SportRunManager.RunTimerTask.1
                @Override // java.lang.Runnable
                public void run() {
                    if (SportRunManager.this.mSportRunCallBack != null) {
                        SportRunManager.this.mSportRunCallBack.sportRunning(SportRunManager.this.mSportHealth, SportRunManager.this.mHeartRate, SportRunManager.this.mCurrentLatLng, SportRunManager.this.mGpsSignValue, SportRunManager.this.mIsRemoveFirstPoint);
                    }
                }
            });
            SportRunManager.access$3108(SportRunManager.this);
            SportLogHelper.saveSportLog(SportRunManager.TAG, "mIsV3Exchange: " + SportRunManager.this.mIsV3Exchange + ",changeIndex=" + SportRunManager.this.changeIndex + ",mIsAppComplete=" + SportRunManager.this.mIsAppComplete + ",BleSdkWrapper.isConnected()=" + BleSdkWrapper.isConnected());
            SportRunManager.this.getFiveMinutePace();
            if (!SportRunManager.this.mIsAppComplete && BleSdkWrapper.isConnected()) {
                if (SportRunManager.this.mIsV3Exchange) {
                    if (SportRunManager.this.changeIndex % 2 == 0) {
                        SportRunManager.this.changeV3Cmd();
                    }
                    if (SportRunManager.this.changeIndex % 30 == 0) {
                        SportRunManager.this.changeV3Rate();
                    }
                    if (SportRunManager.this.changeIndex % 40 == 0) {
                        SportRunManager.this.endV3Cmd();
                    }
                } else if (SportRunManager.this.changeIndex % 2 == 0) {
                    SportRunManager.this.changeCmd();
                }
            }
            if (SportRunManager.this.mIsAppComplete && SportRunManager.this.changeIndex % 40 == 0) {
                SportRunManager.this.saveSportRunData();
            }
            SportRunManager.this.updateHandler.postDelayed(this, 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getFiveMinutePace() {
        String strComputeTimeSpeed;
        String strComputeTimePace = StringUtil.format("%.2f", Float.valueOf(0.0f));
        List<Double> list = this.mFiveMinuteDistances;
        if (list != null && list.size() >= 5) {
            this.mFiveMinuteDistances.remove(0);
        }
        this.mFiveMinuteDistances.add(Double.valueOf(this.mOneMinuteDistances));
        this.mOneMinuteDistances = 0.0d;
        Double dValueOf = Double.valueOf(0.0d);
        if (this.mFiveMinuteDistances.size() == 5) {
            for (int i = 0; i < this.mFiveMinuteDistances.size(); i++) {
                dValueOf = Double.valueOf(dValueOf.doubleValue() + this.mFiveMinuteDistances.get(i).doubleValue());
            }
            strComputeTimePace = SportDataUtil.computeTimePace(dValueOf.intValue(), 5);
            strComputeTimeSpeed = SportDataUtil.computeTimeSpeed(dValueOf.intValue(), 5);
        } else {
            strComputeTimeSpeed = "";
        }
        completeFastPaceAndFastSpeedByPhone(dValueOf.intValue(), 5);
        if (this.mSportRunCallBack == null || this.mIsSupportRealPace) {
            return;
        }
        SportLogHelper.saveSportLog(TAG, "自己计算的配速和速度: " + strComputeTimePace + AppInfo.DELIM + strComputeTimeSpeed + AppInfo.DELIM + this.mIsSupportRealPace + AppInfo.DELIM + dValueOf);
        this.mSportRunCallBack.sportFivePeaceAndSpeed(strComputeTimePace, strComputeTimeSpeed);
    }

    private void completeFastPaceAndFastSpeedByPhone(int i, int i2) {
        if (this.mIsAppComplete) {
            float f2 = i / 1000.0f;
            float f3 = i2;
            this.paceByphone = (int) ((f3 / 60.0f) / f2);
            this.speedByphone = (int) ((f2 / (f3 / 3600.0f)) * 100.0f);
            if (this.mSportHealth.getMinPace() == 0) {
                this.mSportHealth.setMinPace(this.paceByphone);
            }
            int i3 = this.paceByphone;
            if (i3 != 0) {
                SportHealth sportHealth = this.mSportHealth;
                sportHealth.setMinPace(Math.min(i3, sportHealth.getMinPace()));
            }
            SportHealth sportHealth2 = this.mSportHealth;
            sportHealth2.setMaxPace(Math.min(this.paceByphone, sportHealth2.getMaxPace()));
            if (this.mSportHealth.getMinSpeed() == 0) {
                this.mSportHealth.setMinSpeed(this.paceByphone);
            }
            int i4 = this.speedByphone;
            if (i4 != 0) {
                SportHealth sportHealth3 = this.mSportHealth;
                sportHealth3.setMinSpeed(Math.min(i4, sportHealth3.getMinSpeed()));
            }
            SportHealth sportHealth4 = this.mSportHealth;
            sportHealth4.setMaxSpeed(Math.max(this.speedByphone, sportHealth4.getMaxSpeed()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeV3Rate() {
        BleSdkWrapper.getExChangeV3DataHeartRateInterval();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeV3Cmd() {
        SportLogHelper.saveSportLog(TAG, "changeV3Cmd: " + this.mGpsSignValue + AppInfo.DELIM + this.mSendDistance);
        V3AppExchangeDataIngPara v3AppExchangeDataIngPara = this.mV3SwitchDataAppIng;
        v3AppExchangeDataIngPara.signalFlag = this.mGpsSignValue;
        v3AppExchangeDataIngPara.distance = (int) this.mSendDistance;
        BleSdkWrapper.v3AppSwitchDataIng(v3AppExchangeDataIngPara);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeCmd() {
        int totalSeconds = this.mSportHealth.getTotalSeconds();
        int numCalories = this.mSportHealth.getNumCalories();
        AppExchangeDataIngPara appExchangeDataIngPara = this.mSwitchDataAppIng;
        appExchangeDataIngPara.status = this.mGpsSignValue;
        appExchangeDataIngPara.duration = totalSeconds;
        appExchangeDataIngPara.calories = numCalories;
        appExchangeDataIngPara.distance = (int) this.mSendDistance;
        BleSdkWrapper.appSwitchDataIng(appExchangeDataIngPara);
        SportLogHelper.saveSportLog(TAG, "v2 2s changeCmd: " + this.mSwitchDataAppIng.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void completeCalorie() {
        int numCalories = this.mSportHealth.getNumCalories();
        if (this.mIsAppComplete || !BleSdkWrapper.isConnected()) {
            numCalories = SportDataHelper.completCarloy(this.mType, this.mSportHealth.getDistance(), this.mSportHealth.getNumCalories());
        }
        this.mSportHealth.setNumCalories(numCalories);
    }

    private void clearCache() {
        this.mLatLngDomainList.clear();
        this.serialHeartRate.clear();
        this.mHeartRateList.clear();
        this.mMetricItemsList.clear();
        this.mBritishItemsList.clear();
        this.mIsEnd = false;
        this.maxHeartRate = 0;
        this.lastCurrentTimeMillis = 0L;
        this.lastLatlng = null;
        this.mCurrentLatLng = null;
        this.mIsLoadMap = false;
        this.mIsV3Exchange = false;
        this.mIsSupportRealPace = false;
        this.changeIndex = 0L;
    }

    private void printLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLocationInfoPath(), TAG, str);
    }
}