package com.ido.life.boatservice;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaApi;
import com.ido.alexa.AlexaApp;
import com.ido.alexa.callbacks.AlexaSettingCallBack;
import com.ido.alexa.callbacks.AudioResultCallback;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.alexa.service.AlexaService;
import com.ido.alexa.util.ComUtil;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.FirmwareAndBt3Version;
import com.ido.ble.protocol.model.HornVoice;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.VoiceToText;
import com.ido.common.IdoApp;
import com.ido.life.base.BaseDeviceParaCallBack;
import com.ido.life.base.BaseDeviceVoiceChangedCallBack;
import com.ido.life.base.BaseMessage;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.ble.BaseConnCallback;
import com.ido.life.ble.BaseDeviceInfoCallback;
import com.ido.life.ble.DeviceConfigHelper;
import com.ido.life.constants.Constants;
import com.ido.life.data.health.remote.HealthManager;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.ServerBloodOxyDayData;
import com.ido.life.database.model.ServerHeartRateDayData;
import com.ido.life.database.model.ServerSleepDayData;
import com.ido.life.database.model.SportDistanceBean;
import com.ido.life.database.model.StepDayData;
import com.ido.life.module.alexa.AlexaHelpActivity;
import com.ido.life.module.alexa.YearHeartRateItem;
import com.ido.life.module.home.HomeFragmentPresenter;
import com.ido.life.util.DateUtil;
import com.ido.life.util.DeviceUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.util.eventbus.EventBusHelper;
import com.ido.life.util.eventbus.EventBusWrapper;
import com.ido.life.util.eventbus.IHandlerEventBus;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaDataService extends Service implements AudioResultCallback, IHandlerEventBus {
    static final int MAX_BRIGHTNESS = 100;
    boolean isApollo;
    boolean isOTA;
    boolean isStart;
    EventBusWrapper mEventBus;
    int mPerValue;
    AlexaSettingCallBack.ICallBack mAlexaSettingCallBack = new AlexaSettingCallBack.ICallBack() { // from class: com.ido.life.boatservice.-$$Lambda$AlexaDataService$_rc5PI7iO3cRdKoJGuYelGPVA6M
        @Override // com.ido.alexa.callbacks.AlexaSettingCallBack.ICallBack
        public final void onSet(AlexaSettingCallBack.AlexaSettingType alexaSettingType, Object obj) {
            this.f$0.lambda$new$0$AlexaDataService(alexaSettingType, obj);
        }
    };
    BaseConnCallback baseConnCallback = new BaseConnCallback() { // from class: com.ido.life.boatservice.AlexaDataService.3
        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectSuccess(String str) {
            super.onConnectSuccess(str);
            AlexaApi.connectSuccess(str);
            AlexaDataService.this.getDeviceThirdVersion();
            if (AlexaDataService.this.isOTA) {
                AlexaDataService.this.isOTA = false;
                BLEManager.getFunctionTables();
                BLEManager.registerGetDeviceInfoCallBack(new BaseDeviceInfoCallback() { // from class: com.ido.life.boatservice.AlexaDataService.3.1
                    @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
                    public void onGetFunctionTable(SupportFunctionInfo supportFunctionInfo) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(" AlexaDataService OTA_SUCCESS onGetFunctionTable");
                        sb.append(supportFunctionInfo == null ? "null" : supportFunctionInfo.toString());
                        AlexaLogUtil.printAndSave(sb.toString());
                        BLEManager.unregisterGetDeviceInfoCallBack(this);
                        if (supportFunctionInfo == null || !supportFunctionInfo.V3_alexa_set_jump_ui) {
                            return;
                        }
                        AlexaApi.uploadSkill();
                    }
                });
            }
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectFailed(ConnectFailedReason connectFailedReason, String str) {
            super.onConnectFailed(connectFailedReason, str);
            AlexaApi.connectFailed();
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
            super.onConnectBreak(str);
            AlexaApi.connectBreak();
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInDfuMode(BLEDevice bLEDevice) {
            super.onInDfuMode(bLEDevice);
            StringBuilder sb = new StringBuilder();
            sb.append("AlexaDataService onInDfuMode=");
            sb.append(bLEDevice == null ? "null" : bLEDevice.toString());
            AlexaLogUtil.printAndSave(sb.toString());
        }
    };
    BaseDeviceInfoCallback mDeviceInfoCallback = new BaseDeviceInfoCallback() { // from class: com.ido.life.boatservice.AlexaDataService.4
        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetBasicInfo(BasicInfo basicInfo) {
            super.onGetBasicInfo(basicInfo);
            if (basicInfo == null || basicInfo.reboot != 1) {
                return;
            }
            AlexaApi.reboot();
        }
    };
    private final BaseDeviceParaCallBack mSystemConstituentCallback = new BaseDeviceParaCallBack() { // from class: com.ido.life.boatservice.AlexaDataService.5
        @Override // com.ido.life.base.BaseDeviceParaCallBack, com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
        public void onGetFirmwareAndBt3Version(FirmwareAndBt3Version firmwareAndBt3Version) {
            super.onGetFirmwareAndBt3Version(firmwareAndBt3Version);
            BLEManager.unregisterGetDeviceParaCallBack(AlexaDataService.this.mSystemConstituentCallback);
            String deviceThirdVersion = DeviceUtil.getDeviceThirdVersion(firmwareAndBt3Version);
            AlexaLogUtil.printAndSave("设备版本号：" + deviceThirdVersion);
            SPHelper.saveDeviceThirdVersion(deviceThirdVersion);
            AlexaApi.setDeviceFirmwareVersion(AlexaDataService.this.parseLong(deviceThirdVersion.replaceAll("\\.", "")));
        }
    };
    private final BaseDeviceVoiceChangedCallBack deviceVoiceChangedCallBack = new BaseDeviceVoiceChangedCallBack() { // from class: com.ido.life.boatservice.AlexaDataService.6
        @Override // com.ido.life.base.BaseDeviceVoiceChangedCallBack, com.ido.ble.callback.DeviceVoiceChangedCallBack.ICallBack
        public void onHornVoiceChanged(HornVoice hornVoice) {
            super.onHornVoiceChanged(hornVoice);
            AlexaLogUtil.printAndSave("设备音量发送改变：" + hornVoice);
            AlexaApi.hornVoiceChanged(hornVoice.value);
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        AlexaLogUtil.printAndSave("AlexaDataService onCreate");
        AlexaApi.registerAudioResultCallback(this);
        BLEManager.registerConnectCallBack(this.baseConnCallback);
        BLEManager.registerGetDeviceInfoCallBack(this.mDeviceInfoCallback);
        AlexaApi.registerAlexaSettingCallBack(this.mAlexaSettingCallBack);
        BLEManager.registerDeviceVoiceChangedCallBack(this.deviceVoiceChangedCallBack);
        createNotificationChannel();
        this.mEventBus = new EventBusWrapper();
        this.mEventBus.register(this);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, getPackageName());
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            if (notificationManager == null) {
                return;
            }
            NotificationChannel notificationChannel = new NotificationChannel(getPackageName(), getString(R.string.app_name), 3);
            notificationChannel.setSound(null, null);
            notificationManager.createNotificationChannel(notificationChannel);
            builder.setChannelId(getPackageName());
            startForeground(1, builder.build());
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        createNotificationChannel();
        ComUtil.startService(this, AlexaService.class);
        return super.onStartCommand(intent, i, i2);
    }

    public /* synthetic */ void lambda$new$0$AlexaDataService(AlexaSettingCallBack.AlexaSettingType alexaSettingType, Object obj) {
        if (alexaSettingType == AlexaSettingCallBack.AlexaSettingType.ALEXA_HELP) {
            SingleTopIntent singleTopIntent = new SingleTopIntent(IdoApp.getAppContext(), (Class<?>) AlexaHelpActivity.class);
            singleTopIntent.addFlags(268435456);
            startActivity(singleTopIntent);
            return;
        }
        if (alexaSettingType == AlexaSettingCallBack.AlexaSettingType.FIND_PHONE) {
            AlexaApp.getAppContext().startService(new Intent(AlexaApp.getAppContext(), (Class<?>) DeviceAssistService.class));
            BLEManager.setFindPhoneSwitch(true);
            return;
        }
        if (alexaSettingType == AlexaSettingCallBack.AlexaSettingType.STOP_FIND_PHONE) {
            EventBusHelper.post(500);
            return;
        }
        if (alexaSettingType == AlexaSettingCallBack.AlexaSettingType.BRIGHTNESS) {
            handlerBrightness();
            return;
        }
        long nearlySevenDasySportRecordCount = 0;
        if (alexaSettingType == AlexaSettingCallBack.AlexaSettingType.LAST_WEEKLY_AVG_HR) {
            long userId = RunTimeUtil.getInstance().getUserId();
            int weekStart = RunTimeUtil.getInstance().getWeekStart();
            String startdayThisWeek = ComUtil.getStartdayThisWeek(-1, weekStart);
            String enddayThisWeek = ComUtil.getEnddayThisWeek(-1, weekStart);
            AlexaLogUtil.printAndSave("handlerHeartrate week " + startdayThisWeek + "  " + enddayThisWeek);
            int[] iArrCalHr = calHr(GreenDaoUtil.queryHeartRateColumnByDateArea(userId, startdayThisWeek, enddayThisWeek));
            nearlySevenDasySportRecordCount = (long) iArrCalHr[0];
            replyDevice(iArrCalHr[0] + " bpm");
        } else if (alexaSettingType == AlexaSettingCallBack.AlexaSettingType.LAST_MONTHLY_AVG_HR) {
            long userId2 = RunTimeUtil.getInstance().getUserId();
            Calendar calendar = Calendar.getInstance();
            calendar.add(2, -1);
            int i = calendar.get(1);
            int i2 = calendar.get(2) + 1;
            AlexaLogUtil.printAndSave("handlerHeartrate month " + i + "-" + i2);
            int[] iArrCalHr2 = calHr(GreenDaoUtil.queryHeartRateMonthData(userId2, i, i2));
            nearlySevenDasySportRecordCount = (long) iArrCalHr2[0];
            replyDevice(iArrCalHr2[0] + " bpm");
        } else if (alexaSettingType == AlexaSettingCallBack.AlexaSettingType.LAST_YEARLY_AVG_HR) {
            long userId3 = RunTimeUtil.getInstance().getUserId();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(1, -1);
            int i3 = calendar2.get(1);
            AlexaLogUtil.printAndSave("handlerHeartrate year" + i3);
            int[] iArrCalHr3 = calHr(caluteYearHr(GreenDaoUtil.queryHeartRateYearData(userId3, i3)));
            nearlySevenDasySportRecordCount = (long) iArrCalHr3[0];
            replyDevice(iArrCalHr3[0] + " bpm");
        } else if (alexaSettingType == AlexaSettingCallBack.AlexaSettingType.HEARTRATE) {
            ServerHeartRateDayData dayHeartRateByDate = GreenDaoUtil.getDayHeartRateByDate(RunTimeUtil.getInstance().getUserId(), DateUtil.getDay());
            if (dayHeartRateByDate != null) {
                nearlySevenDasySportRecordCount = dayHeartRateByDate.getLatestValue();
                AlexaLogUtil.printAndSave("query heartrate= " + dayHeartRateByDate.toString());
            }
        } else if (alexaSettingType == AlexaSettingCallBack.AlexaSettingType.BLOOD_OXYGEN) {
            ServerBloodOxyDayData bloodOxyDailyDataByDate = GreenDaoUtil.getBloodOxyDailyDataByDate(getUserId(), DateUtil.getDay());
            if (bloodOxyDailyDataByDate != null) {
                nearlySevenDasySportRecordCount = bloodOxyDailyDataByDate.getLatestValue();
                AlexaLogUtil.printAndSave("query blood oxygen= " + bloodOxyDailyDataByDate.toString());
            }
        } else if (alexaSettingType == AlexaSettingCallBack.AlexaSettingType.SLEEP_SCORE) {
            ServerSleepDayData sleepDailyDataByDate = GreenDaoUtil.getSleepDailyDataByDate(getUserId(), DateUtil.getDay());
            if (sleepDailyDataByDate != null) {
                nearlySevenDasySportRecordCount = sleepDailyDataByDate.getScore();
                AlexaLogUtil.printAndSave("query sleep= " + sleepDailyDataByDate.toString());
            }
        } else if (alexaSettingType == AlexaSettingCallBack.AlexaSettingType.STEP_COUNT) {
            StepDayData stepDailyDataByDate = GreenDaoUtil.getStepDailyDataByDate(getUserId(), DateUtil.getDay());
            if (stepDailyDataByDate != null) {
                nearlySevenDasySportRecordCount = stepDailyDataByDate.getNumSteps();
                AlexaLogUtil.printAndSave("query steps= " + stepDailyDataByDate.toString());
            }
        } else if (alexaSettingType == AlexaSettingCallBack.AlexaSettingType.TODAY_CALORIE_STATISTICS) {
            CalorieDayData calorieDailyDataByDate = GreenDaoUtil.getCalorieDailyDataByDate(RunTimeUtil.getInstance().getUserId(), DateUtil.format(Calendar.getInstance(Locale.CHINA), DateUtil.DATE_FORMAT_YMD));
            if (calorieDailyDataByDate != null && calorieDailyDataByDate.getActivityCalorie() > 0) {
                nearlySevenDasySportRecordCount = calorieDailyDataByDate.getActivityCalorie();
                AlexaLogUtil.printAndSave("query calories= " + nearlySevenDasySportRecordCount);
            }
        } else if (alexaSettingType == AlexaSettingCallBack.AlexaSettingType.TODAY_KILOMETER_STATISTICST) {
            SportDistanceBean sportDistanceBeanQuerySportDistanceByDate = GreenDaoUtil.querySportDistanceByDate(RunTimeUtil.getInstance().getUserId(), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate()));
            if (sportDistanceBeanQuerySportDistanceByDate != null) {
                nearlySevenDasySportRecordCount = (long) sportDistanceBeanQuerySportDistanceByDate.getTotalDistance();
                AlexaLogUtil.printAndSave("query distance= " + nearlySevenDasySportRecordCount);
            }
        } else if (alexaSettingType == AlexaSettingCallBack.AlexaSettingType.TODAY_SWIMMING_STATISTICS) {
            nearlySevenDasySportRecordCount = HealthManager.getSportHealthDistanceList(54, DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate()));
            AlexaLogUtil.printAndSave("query swimming data= " + nearlySevenDasySportRecordCount);
        } else if (alexaSettingType == AlexaSettingCallBack.AlexaSettingType.TODAY_WORKOUT_COUNT) {
            nearlySevenDasySportRecordCount = GreenDaoUtil.getTodaySportRecordCount(getUserId());
            AlexaLogUtil.printAndSave("query TodaySportRecordCount= " + nearlySevenDasySportRecordCount);
        } else if (alexaSettingType == AlexaSettingCallBack.AlexaSettingType.TODAY_RUNNING_COUNT) {
            long todaySportRecordCountByType = GreenDaoUtil.getTodaySportRecordCountByType(getUserId(), 49);
            long todaySportRecordCountByType2 = GreenDaoUtil.getTodaySportRecordCountByType(getUserId(), 48);
            AlexaLogUtil.printAndSave("query runningCount= indoorRunCount:" + todaySportRecordCountByType + " ,outdoorRunCount: " + todaySportRecordCountByType2);
            nearlySevenDasySportRecordCount = todaySportRecordCountByType + todaySportRecordCountByType2;
        } else if (alexaSettingType == AlexaSettingCallBack.AlexaSettingType.TODAY_SWIMMING_COUNT) {
            long todaySportRecordCountByType3 = GreenDaoUtil.getTodaySportRecordCountByType(getUserId(), 54);
            long todaySportRecordCountByType4 = GreenDaoUtil.getTodaySportRecordCountByType(getUserId(), 55);
            AlexaLogUtil.printAndSave("query swimmingCount= poolSwimCount:" + todaySportRecordCountByType3 + " ,openWaterSwimCount; " + todaySportRecordCountByType4);
            nearlySevenDasySportRecordCount = todaySportRecordCountByType3 + todaySportRecordCountByType4;
        } else if (alexaSettingType == AlexaSettingCallBack.AlexaSettingType.WORKOUT_HISTORY) {
            nearlySevenDasySportRecordCount = GreenDaoUtil.getNearlySevenDasySportRecordCount(getUserId());
            AlexaLogUtil.printAndSave("query nearly seven Days SportRecordCount= " + nearlySevenDasySportRecordCount);
        }
        AlexaApi.handlerQueryResult(nearlySevenDasySportRecordCount);
    }

    private static void replyDevice(String str) {
        VoiceToText voiceToText = new VoiceToText();
        voiceToText.title = "";
        voiceToText.text_content = str;
        BLEManager.setVoiceToText(voiceToText);
        AlexaLogUtil.d("AlexaDataService replyDevice=" + voiceToText.toString());
    }

    private long getUserId() {
        return RunTimeUtil.getInstance().getUserId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int formatValue2Level(int i) {
        if (i > 100) {
            i = 100;
        }
        int i2 = this.mPerValue;
        return Math.max((i / i2) + ((i % i2) * 2 >= i2 ? 1 : 0), 1);
    }

    private void handlerBrightness() {
        SupportFunctionInfo supportFunctionInfo = DeviceConfigHelper.getSupportFunctionInfo();
        this.mPerValue = 100 / (supportFunctionInfo.ex_table_main8_screen_brightness_3_level ? 3 : 5);
        if (supportFunctionInfo.ex_table_main9_get_screen_brightness) {
            BLEManager.registerGetDeviceParaCallBack(new BaseDeviceParaCallBack() { // from class: com.ido.life.boatservice.AlexaDataService.1
                @Override // com.ido.life.base.BaseDeviceParaCallBack, com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
                public void onGetScreenBrightness(ScreenBrightness screenBrightness) {
                    super.onGetScreenBrightness(screenBrightness);
                    BLEManager.unregisterGetDeviceParaCallBack(this);
                    if (screenBrightness == null) {
                        screenBrightness = new ScreenBrightness();
                        screenBrightness.level = 100;
                    }
                    int i = screenBrightness.level;
                    if (i > 100) {
                        i = 100;
                    }
                    long value2Level = AlexaDataService.this.formatValue2Level(i);
                    AlexaApi.handlerQueryResult(value2Level);
                    AlexaLogUtil.printAndSave("query brightness " + value2Level);
                }
            });
            BLEManager.getScreenBrightness();
            return;
        }
        ScreenBrightness screenBrigthnessConfig = LocalDataManager.getScreenBrigthnessConfig();
        if (screenBrigthnessConfig == null) {
            screenBrigthnessConfig = new ScreenBrightness();
            screenBrigthnessConfig.level = 100;
        }
        long value2Level = formatValue2Level(screenBrigthnessConfig.level);
        AlexaApi.handlerQueryResult(value2Level);
        AlexaLogUtil.printAndSave("query brightness " + value2Level);
    }

    private List<ServerHeartRateDayData> caluteYearHr(List<ServerHeartRateDayData> list) {
        ArrayList arrayList = new ArrayList();
        HashMap map = new HashMap();
        if (list != null) {
            for (ServerHeartRateDayData serverHeartRateDayData : list) {
                if (serverHeartRateDayData != null && !TextUtils.isEmpty(serverHeartRateDayData.getDate()) && serverHeartRateDayData.getDate().contains("-") && (serverHeartRateDayData.getSilentValue() != 0 || serverHeartRateDayData.getAvgValue() != 0)) {
                    String str = serverHeartRateDayData.getDate().substring(0, serverHeartRateDayData.getDate().length() - 2) + "01";
                    if (map.containsKey(str)) {
                        YearHeartRateItem yearHeartRateItem = (YearHeartRateItem) map.get(str);
                        if (yearHeartRateItem != null) {
                            if (serverHeartRateDayData.getAvgValue() > 0) {
                                yearHeartRateItem.totalAvgValue += serverHeartRateDayData.getAvgValue();
                                yearHeartRateItem.count++;
                            }
                            yearHeartRateItem.maxRate = Math.max(yearHeartRateItem.maxRate, Math.min(220, serverHeartRateDayData.getMaxValue()));
                            yearHeartRateItem.minRate = Math.min(yearHeartRateItem.minRate, Math.max(20, serverHeartRateDayData.getMinValue()));
                        }
                    } else {
                        YearHeartRateItem yearHeartRateItem2 = new YearHeartRateItem();
                        yearHeartRateItem2.totalAvgValue = serverHeartRateDayData.getAvgValue();
                        yearHeartRateItem2.count = serverHeartRateDayData.getAvgValue() <= 0 ? 0 : 1;
                        yearHeartRateItem2.minRate = Math.min(220, serverHeartRateDayData.getMaxValue());
                        yearHeartRateItem2.maxRate = Math.max(20, serverHeartRateDayData.getMinValue());
                        map.put(str, yearHeartRateItem2);
                    }
                }
            }
        }
        if (map.size() > 0) {
            for (String str2 : map.keySet()) {
                YearHeartRateItem yearHeartRateItem3 = (YearHeartRateItem) map.get(str2);
                ServerHeartRateDayData serverHeartRateDayData2 = new ServerHeartRateDayData();
                serverHeartRateDayData2.setDate(str2);
                serverHeartRateDayData2.setMaxValue(((YearHeartRateItem) Objects.requireNonNull(yearHeartRateItem3)).maxRate);
                serverHeartRateDayData2.setMinValue(yearHeartRateItem3.minRate);
                if (yearHeartRateItem3.count > 0) {
                    serverHeartRateDayData2.setAvgValue(yearHeartRateItem3.totalAvgValue / yearHeartRateItem3.count);
                }
                arrayList.add(serverHeartRateDayData2);
            }
        }
        return arrayList;
    }

    private int[] calHr(List<ServerHeartRateDayData> list) {
        int iRound;
        int iMax;
        int iMin;
        if (list != null) {
            int size = list.size();
            int i = 0;
            int avgValue = 0;
            iMax = 0;
            iMin = 0;
            for (int i2 = 0; i2 < size; i2++) {
                ServerHeartRateDayData serverHeartRateDayData = list.get(i2);
                if (serverHeartRateDayData != null && serverHeartRateDayData.getAvgValue() >= 20 && serverHeartRateDayData.getAvgValue() <= 220) {
                    i++;
                    avgValue += serverHeartRateDayData.getAvgValue();
                    iMax = Math.max(serverHeartRateDayData.getMaxValue(), iMax);
                    if (iMin == 0) {
                        iMin = serverHeartRateDayData.getMinValue();
                    } else {
                        iMin = Math.min(serverHeartRateDayData.getMinValue(), iMin);
                    }
                }
            }
            iRound = i > 0 ? Math.round((avgValue * 1.0f) / i) : 0;
        } else {
            iRound = 0;
            iMax = 0;
            iMin = 0;
        }
        AlexaLogUtil.printAndSave("avgHr= " + iRound + " ,maxHr= " + iMax + " ,minHr= " + iMin);
        return new int[]{iRound, iMax, iMin};
    }

    boolean isApollo() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null && basicInfo.platform == 30) {
            this.isApollo = true;
        } else {
            this.isApollo = false;
        }
        return this.isApollo;
    }

    @Override // com.ido.alexa.callbacks.AudioResultCallback
    public void onAlexaStart() {
        if (!isApollo() || this.isStart) {
            return;
        }
        this.isStart = true;
        EventBusHelper.post(Constants.EventConstants.EVENT_ALEXA_START);
        AlexaLogUtil.printAndSave("isApollo:=" + this.isApollo + ", Alexa start,isSyning=" + HomeFragmentPresenter.mIsSyncing);
    }

    @Override // com.ido.alexa.callbacks.AudioResultCallback
    public void onAlexaEnd() {
        if (this.isApollo && this.isStart) {
            this.isStart = false;
            EventBusHelper.post(Constants.EventConstants.EVENT_ALEXA_END);
            AlexaLogUtil.printAndSave("isApollo:=" + this.isApollo + ", Alexa end");
        }
    }

    @Override // com.ido.alexa.callbacks.AudioResultCallback
    public void onStartPrase() {
        EventBusHelper.post(Constants.EventConstants.EVENT_ALEXA_PRASE_START);
    }

    @Override // com.ido.alexa.callbacks.AudioResultCallback
    public void onResponse(String str) {
        EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_ALEXA_PRASE_END, str));
    }

    @Override // com.ido.alexa.callbacks.AudioResultCallback
    public void onAlexaLogout() {
        EventBusHelper.post(Constants.EventConstants.EVENT_LOGOUT_ALEXA);
    }

    @Override // com.ido.alexa.callbacks.AudioResultCallback
    public void onPcmFileName(String str) {
        EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_ALEXA_VOICE_FILE_FINISH, str));
    }

    @Override // com.ido.alexa.callbacks.AudioResultCallback
    public void onMP3FileName(final String str) {
        if (DeviceConfigHelper.getSupportFunctionInfo().V3_support_app_send_voice_to_ble) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ido.life.boatservice.AlexaDataService.2
                @Override // java.lang.Runnable
                public void run() {
                    BLEManager.playAlexaVoice(str);
                    AlexaLogUtil.printAndSave("AlexaDataService 下发语音文件：" + str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDeviceThirdVersion() {
        if (DeviceConfigHelper.getSupportFunctionInfo().V3_v2_02_EB_firmware_bt_version_01_create) {
            String deviceThirdVersion = SPHelper.getDeviceThirdVersion();
            AlexaLogUtil.printAndSave("设备版本号：" + deviceThirdVersion);
            if (TextUtils.isEmpty(deviceThirdVersion)) {
                BLEManager.unregisterGetDeviceParaCallBack(this.mSystemConstituentCallback);
                BLEManager.registerGetDeviceParaCallBack(this.mSystemConstituentCallback);
                BLEManager.getFirmwareAndBt3Version();
                return;
            }
            AlexaApi.setDeviceFirmwareVersion(parseLong(deviceThirdVersion.replaceAll("\\.", "")));
            return;
        }
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo == null) {
            currentDeviceInfo = new BLEDevice();
        }
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            currentDeviceInfo.mDeviceId = basicInfo.deivceId;
            currentDeviceInfo.version = basicInfo.firmwareVersion;
        }
        AlexaLogUtil.printAndSave("设备版本号：" + currentDeviceInfo.version);
        AlexaApi.setDeviceFirmwareVersion((long) currentDeviceInfo.version);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long parseLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return 0L;
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        AlexaApi.unregisterAudioResultCallback();
        AlexaApi.unregisterAlexaSettingCallBack(this.mAlexaSettingCallBack);
        BLEManager.unregisterConnectCallBack(this.baseConnCallback);
        BLEManager.unregisterGetDeviceInfoCallBack(this.mDeviceInfoCallback);
        BLEManager.unregisterDeviceVoiceChangedCallBack(this.deviceVoiceChangedCallBack);
        EventBusWrapper eventBusWrapper = this.mEventBus;
        if (eventBusWrapper != null) {
            eventBusWrapper.unregister();
        }
        AlexaLogUtil.printAndSave("  AlexaDataService onDestroy");
    }

    @Override // com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        if (baseMessage == null) {
            return;
        }
        if (baseMessage.getType() == 824) {
            AlexaLogUtil.printAndSave(" AlexaDataService OTA_SUCCESS");
            this.isOTA = true;
        } else if (baseMessage.getType() == 852) {
            AlexaLogUtil.printAndSave(" AlexaDataService 收到设备重启过的通知");
            AlexaApi.reboot();
        }
    }
}