package com.ido.ble.f.a.f;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.common.d;
import com.ido.ble.common.e;
import com.ido.ble.common.k;
import com.ido.ble.gps.model.ConfigGPS;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.ActivitySwitch;
import com.ido.ble.protocol.model.Alarm;
import com.ido.ble.protocol.model.AntiLostMode;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.BloodPressureAdjustPara;
import com.ido.ble.protocol.model.BreatheTrain;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import com.ido.ble.protocol.model.DialPlate;
import com.ido.ble.protocol.model.DisplayMode;
import com.ido.ble.protocol.model.DrinkWaterReminder;
import com.ido.ble.protocol.model.FitnessGuidance;
import com.ido.ble.protocol.model.Goal;
import com.ido.ble.protocol.model.HandWearMode;
import com.ido.ble.protocol.model.HeartRateInterval;
import com.ido.ble.protocol.model.HeartRateMeasureMode;
import com.ido.ble.protocol.model.HeartRateMeasureModeV3;
import com.ido.ble.protocol.model.LongSit;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.MenstrualRemind;
import com.ido.ble.protocol.model.MenuList;
import com.ido.ble.protocol.model.NightTemperatureMonitoringPara;
import com.ido.ble.protocol.model.NoisePara;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.NoticeReminderSwitchStatus;
import com.ido.ble.protocol.model.PressureParam;
import com.ido.ble.protocol.model.QuickSportMode;
import com.ido.ble.protocol.model.SPO2Param;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.ShortCut;
import com.ido.ble.protocol.model.SleepMonitoringPara;
import com.ido.ble.protocol.model.SportModeSort;
import com.ido.ble.protocol.model.SportModeSortV3;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.Units;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.ble.protocol.model.UserInfo;
import com.ido.ble.protocol.model.WalkReminder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class a extends d {
    private static final String A = "quickSportMode";
    private static final String B = "dialPlateMode";
    private static final String C = "shortCut";
    private static final String D = "SleepMonitoring";
    private static final String E = "NightTemperatureMonitoring";
    private static final String F = "NoisePara";
    private static final String G = "FitnessGuidance";
    private static final String H = "lastSyncHealthDataTime";
    private static final String I = "lastSyncHealthDataDeviceUniqueId";
    private static final String J = "offsetSportIndex";
    private static final String K = "offsetHeartRateIndex";
    private static final String L = "offsetBloodPressuredIndex";
    private static final String M = "setConfigGpsPara";

    @Deprecated
    private static final String N = "screenBrightness";
    private static final String O = "screenBrightness_config";
    private static final String P = "isNeedCollectRebootLog";
    private static final String Q = "menstrual";
    private static final String R = "menstrual_remind";
    private static final String S = "calorie_distance_goal";
    private static final String T = "sport_mode_sort";
    private static final String U = "sport_mode_sort_v3";
    private static final String V = "breatheTrain";
    private static final String W = "walkReminder";
    private static final String X = "spo2Param";
    private static final String Y = "activitySwitch";
    private static final String Z = "drinkWaterReminder";
    private static final String a0 = "menuList";
    private static final String b0 = "pressurePara";
    private static final String c0 = "device_info";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f4445d = "device_";
    private static final String d0 = "bt_mac_address";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f4446e = "user_info";
    private static a e0 = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f4447f = "supportFunctionTable";
    private static final String f0 = "default";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final String f4448g = "alarms";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final String f4449h = "goal";
    private static final String i = "handWearMode";
    private static final String j = "bloodPressureAdjustPara";
    private static final String k = "units";
    private static final String l = "basicInfo";
    private static final String m = "AntiLostMode";
    private static final String n = "AntiLostPara";
    private static final String o = "LongSit";
    private static final String p = "findPhoneSwitch";
    private static final String q = "heartRateMode";
    private static final String r = "heartRateModeV3";
    private static final String s = "heartRateInterval";
    private static final String t = "noticeReminderSwitchStatus";
    private static final String u = "upHandGesture";
    private static final String v = "notDisturb";
    private static final String w = "musicSwitch";
    private static final String x = "displayMode";
    private static final String y = "oneKeySos";
    private static final String z = "weatherSwitch";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f4450c;

    /* JADX INFO: renamed from: com.ido.ble.f.a.f.a$a, reason: collision with other inner class name */
    class C0080a extends TypeToken<List<Alarm>> {
        C0080a() {
        }
    }

    private void b(BLEDevice bLEDevice) {
        BasicInfo basicInfoH;
        if (bLEDevice == null || bLEDevice.mDeviceId > 0 || (basicInfoH = h()) == null) {
            return;
        }
        bLEDevice.mDeviceId = basicInfoH.deivceId;
    }

    public static a c0() {
        if (e0 == null) {
            BLEDevice bLEDeviceC = b.e().c();
            e0 = new a();
            if (bLEDeviceC == null || TextUtils.isEmpty(bLEDeviceC.mDeviceAddress)) {
                e0.a(e.a(), f0);
            } else {
                e0.a(e.a(), bLEDeviceC.mDeviceAddress);
            }
            LogTool.d("DeviceSharedPreferences", "sp_name = " + e0.f4450c);
        }
        return e0;
    }

    @Deprecated
    private int d0() {
        return a(N, -1);
    }

    public static a f(String str) {
        a aVar = new a();
        aVar.a(e.a(), str);
        LogTool.d("MultiDeviceSPDataUtils", "[createInstanceWithMacAddress] sp_name = " + aVar.f4450c);
        return aVar;
    }

    public String A() {
        return a(I, "");
    }

    public long B() {
        return a(H, 0L);
    }

    public LongSit C() {
        String strA = a(o, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (LongSit) new Gson().fromJson(strA, LongSit.class);
    }

    public Menstrual D() {
        return (Menstrual) a("menstrual", Menstrual.class);
    }

    public MenstrualRemind E() {
        return (MenstrualRemind) a("menstrual_remind", MenstrualRemind.class);
    }

    public MenuList F() {
        return (MenuList) a(a0, MenuList.class);
    }

    public boolean G() {
        return a(w, false);
    }

    public NightTemperatureMonitoringPara H() {
        String strA = a(E, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (NightTemperatureMonitoringPara) new Gson().fromJson(strA, NightTemperatureMonitoringPara.class);
    }

    public NoisePara I() {
        String strA = a(F, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (NoisePara) new Gson().fromJson(strA, NoisePara.class);
    }

    public NotDisturbPara J() {
        String strA = a(v, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (NotDisturbPara) new Gson().fromJson(strA, NotDisturbPara.class);
    }

    public boolean K() {
        return a(y, false);
    }

    public PressureParam L() {
        return (PressureParam) a(b0, PressureParam.class);
    }

    public QuickSportMode M() {
        String strA = a(A, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (QuickSportMode) new Gson().fromJson(strA, QuickSportMode.class);
    }

    public boolean N() {
        ScreenBrightness screenBrightnessO = O();
        return screenBrightnessO != null && screenBrightnessO.mode == 1;
    }

    public ScreenBrightness O() {
        int iD0;
        ScreenBrightness screenBrightness = (ScreenBrightness) a(O, ScreenBrightness.class);
        if (screenBrightness != null || (iD0 = d0()) <= 0) {
            return screenBrightness;
        }
        ScreenBrightness screenBrightness2 = new ScreenBrightness();
        screenBrightness2.level = iD0;
        return screenBrightness2;
    }

    public ShortCut P() {
        String strA = a(C, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (ShortCut) new Gson().fromJson(strA, ShortCut.class);
    }

    public SleepMonitoringPara Q() {
        String strA = a(D, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (SleepMonitoringPara) new Gson().fromJson(strA, SleepMonitoringPara.class);
    }

    public SPO2Param R() {
        return (SPO2Param) a(X, SPO2Param.class);
    }

    public SportModeSort S() {
        return (SportModeSort) a(T, SportModeSort.class);
    }

    public SportModeSortV3 T() {
        return (SportModeSortV3) a(U, SportModeSortV3.class);
    }

    public int U() {
        return a(J, 0);
    }

    public SupportFunctionInfo V() {
        String strA = a(f4447f, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (SupportFunctionInfo) new Gson().fromJson(strA, SupportFunctionInfo.class);
    }

    public Units W() {
        String strA = a(k, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (Units) new Gson().fromJson(strA, Units.class);
    }

    public UpHandGesture X() {
        String strA = a(u, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (UpHandGesture) new Gson().fromJson(strA, UpHandGesture.class);
    }

    public UserInfo Y() {
        String strA = a(f4446e, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (UserInfo) new Gson().fromJson(strA, UserInfo.class);
    }

    public WalkReminder Z() {
        return (WalkReminder) a(W, WalkReminder.class);
    }

    public void a(int i2) {
        b(L, i2);
    }

    public void a(long j2) {
        b(H, j2);
    }

    @Override // com.ido.ble.common.d
    public void a(Context context, String str) {
        this.f4450c = f4445d + str;
        super.a(context, this.f4450c);
    }

    public void a(BLEDevice bLEDevice) {
        if (bLEDevice == null) {
            return;
        }
        b(bLEDevice);
        b("device_info", k.a(bLEDevice));
    }

    public void a(ConfigGPS configGPS) {
        if (configGPS == null) {
            return;
        }
        b(M, new Gson().toJson(configGPS));
    }

    public void a(ActivitySwitch activitySwitch) {
        if (activitySwitch == null) {
            return;
        }
        b(Y, k.a(activitySwitch));
    }

    public void a(AntiLostMode antiLostMode) {
        if (antiLostMode == null) {
            return;
        }
        b(m, new Gson().toJson(antiLostMode));
    }

    public void a(BasicInfo basicInfo) {
        if (basicInfo == null) {
            return;
        }
        b(l, new Gson().toJson(basicInfo));
    }

    public void a(BloodPressureAdjustPara bloodPressureAdjustPara) {
        if (bloodPressureAdjustPara == null) {
            return;
        }
        b(j, new Gson().toJson(bloodPressureAdjustPara));
    }

    public void a(BreatheTrain breatheTrain) {
        if (breatheTrain == null) {
            return;
        }
        b(V, k.a(breatheTrain));
    }

    public void a(CalorieAndDistanceGoal calorieAndDistanceGoal) {
        if (calorieAndDistanceGoal == null) {
            return;
        }
        b(S, k.a(calorieAndDistanceGoal));
    }

    public void a(DialPlate dialPlate) {
        if (dialPlate == null) {
            return;
        }
        b(B, new Gson().toJson(dialPlate));
    }

    public void a(DisplayMode displayMode) {
        if (displayMode == null) {
            return;
        }
        b(x, new Gson().toJson(displayMode));
    }

    public void a(DrinkWaterReminder drinkWaterReminder) {
        if (drinkWaterReminder == null) {
            return;
        }
        b(Z, k.a(drinkWaterReminder));
    }

    public void a(FitnessGuidance fitnessGuidance) {
        if (fitnessGuidance == null) {
            return;
        }
        b(G, new Gson().toJson(fitnessGuidance));
    }

    public void a(Goal goal) {
        if (goal == null) {
            return;
        }
        b(f4449h, new Gson().toJson(goal));
    }

    public void a(HandWearMode handWearMode) {
        if (handWearMode == null) {
            return;
        }
        b(i, new Gson().toJson(handWearMode));
    }

    public void a(HeartRateInterval heartRateInterval) {
        if (heartRateInterval == null) {
            return;
        }
        b(s, new Gson().toJson(heartRateInterval));
    }

    public void a(HeartRateMeasureMode heartRateMeasureMode) {
        if (heartRateMeasureMode == null) {
            return;
        }
        b(q, new Gson().toJson(heartRateMeasureMode));
    }

    public void a(HeartRateMeasureModeV3 heartRateMeasureModeV3) {
        if (heartRateMeasureModeV3 == null) {
            return;
        }
        b(r, new Gson().toJson(heartRateMeasureModeV3));
    }

    public void a(LongSit longSit) {
        if (longSit == null) {
            return;
        }
        b(o, new Gson().toJson(longSit));
    }

    public void a(Menstrual menstrual) {
        if (menstrual == null) {
            return;
        }
        b("menstrual", new Gson().toJson(menstrual));
    }

    public void a(MenstrualRemind menstrualRemind) {
        if (menstrualRemind == null) {
            return;
        }
        b("menstrual_remind", k.a(menstrualRemind));
    }

    public void a(MenuList menuList) {
        if (menuList == null) {
            return;
        }
        b(a0, k.a(menuList));
    }

    public void a(NightTemperatureMonitoringPara nightTemperatureMonitoringPara) {
        if (nightTemperatureMonitoringPara == null) {
            return;
        }
        b(E, new Gson().toJson(nightTemperatureMonitoringPara));
    }

    public void a(NoisePara noisePara) {
        if (noisePara == null) {
            return;
        }
        b(F, new Gson().toJson(noisePara));
    }

    public void a(NotDisturbPara notDisturbPara) {
        if (notDisturbPara == null) {
            return;
        }
        b(v, new Gson().toJson(notDisturbPara));
    }

    public void a(NoticeReminderSwitchStatus noticeReminderSwitchStatus) {
        if (noticeReminderSwitchStatus == null) {
            return;
        }
        b(t, new Gson().toJson(noticeReminderSwitchStatus));
    }

    public void a(PressureParam pressureParam) {
        if (pressureParam == null) {
            return;
        }
        b(b0, k.a(pressureParam));
    }

    public void a(QuickSportMode quickSportMode) {
        if (quickSportMode == null) {
            return;
        }
        b(A, new Gson().toJson(quickSportMode));
    }

    public void a(SPO2Param sPO2Param) {
        if (sPO2Param == null) {
            return;
        }
        b(X, k.a(sPO2Param));
    }

    public void a(ScreenBrightness screenBrightness) {
        b(O, k.a(screenBrightness));
    }

    public void a(ShortCut shortCut) {
        if (shortCut == null) {
            return;
        }
        b(C, new Gson().toJson(shortCut));
    }

    public void a(SleepMonitoringPara sleepMonitoringPara) {
        if (sleepMonitoringPara == null) {
            return;
        }
        b(D, new Gson().toJson(sleepMonitoringPara));
    }

    public void a(SportModeSort sportModeSort) {
        if (sportModeSort == null) {
            return;
        }
        b(T, k.a(sportModeSort));
    }

    public void a(SportModeSortV3 sportModeSortV3) {
        if (sportModeSortV3 == null) {
            return;
        }
        b(U, k.a(sportModeSortV3));
    }

    public void a(SupportFunctionInfo supportFunctionInfo) {
        if (supportFunctionInfo == null) {
            return;
        }
        b(f4447f, new Gson().toJson(supportFunctionInfo));
    }

    public void a(Units units) {
        if (units == null) {
            return;
        }
        b(k, new Gson().toJson(units));
    }

    public void a(UpHandGesture upHandGesture) {
        if (upHandGesture == null) {
            return;
        }
        b(u, new Gson().toJson(upHandGesture));
    }

    public void a(UserInfo userInfo) {
        if (userInfo == null) {
            return;
        }
        b(f4446e, new Gson().toJson(userInfo));
    }

    public void a(WalkReminder walkReminder) {
        if (walkReminder == null) {
            return;
        }
        b(W, k.a(walkReminder));
    }

    public void a(List<Alarm> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        b(f4448g, new Gson().toJson(list));
    }

    public void a(boolean z2) {
        b(p, z2);
    }

    public boolean a0() {
        return a(z, false);
    }

    public void b() {
        b(d0, "");
    }

    public void b(int i2) {
        b(K, i2);
    }

    public void b(boolean z2) {
        b(w, z2);
    }

    public boolean b0() {
        return a(P, false);
    }

    public void c() {
        b(f4448g);
        b(f4449h);
        b(i);
        b(j);
        b(k);
        b(l);
        b(m);
        b(n);
        b(o);
        b(p);
        b(q);
        b(s);
        b(u);
        b(v);
        b(w);
        b(x);
        b(y);
        b(z);
        b(A);
        b(B);
        b(C);
        b(D);
        b(H);
        b(I);
        b(J);
        b(K);
        b(L);
        b(M);
        b(N);
        b(P);
        b("menstrual");
        b("menstrual_remind");
        b(S);
        b(T);
        b(W);
        b(V);
        b(X);
        b(Y);
    }

    public void c(int i2) {
        ScreenBrightness screenBrightnessO = O();
        if (screenBrightnessO == null) {
            screenBrightnessO = new ScreenBrightness();
        }
        screenBrightnessO.level = i2;
        screenBrightnessO.opera = 1;
        a(screenBrightnessO);
    }

    public void c(String str) {
        b(d0, str);
    }

    public void c(boolean z2) {
        b(y, z2);
    }

    public ActivitySwitch d() {
        return (ActivitySwitch) a(Y, ActivitySwitch.class);
    }

    public void d(int i2) {
        b(J, i2);
    }

    public void d(String str) {
        b(I, str);
    }

    public void d(boolean z2) {
        b(z, z2);
    }

    public List<Alarm> e() {
        String strA = a(f4448g, "");
        return !TextUtils.isEmpty(strA) ? (List) new Gson().fromJson(strA, new C0080a().getType()) : new ArrayList();
    }

    public void e(String str) {
        e0.a(e.a(), str);
        LogTool.d("MultiDeviceSPDataUtils", "[switchToDevice] sp_name = " + e0.f4450c);
    }

    public void e(boolean z2) {
        b(P, z2);
    }

    public Map<Integer, Alarm> f() {
        List<Alarm> listE = e();
        HashMap map = new HashMap();
        for (int i2 = 0; i2 < listE.size(); i2++) {
            map.put(Integer.valueOf(listE.get(i2).getAlarmId()), listE.get(i2));
        }
        return map;
    }

    public AntiLostMode g() {
        return (AntiLostMode) a(m, AntiLostMode.class);
    }

    public BasicInfo h() {
        return (BasicInfo) a(l, BasicInfo.class);
    }

    public BloodPressureAdjustPara i() {
        return (BloodPressureAdjustPara) a(j, BloodPressureAdjustPara.class);
    }

    public int j() {
        return a(L, 0);
    }

    public BreatheTrain k() {
        return (BreatheTrain) a(V, BreatheTrain.class);
    }

    public String l() {
        return a(d0, "");
    }

    public CalorieAndDistanceGoal m() {
        return (CalorieAndDistanceGoal) a(S, CalorieAndDistanceGoal.class);
    }

    public BLEDevice n() {
        BLEDevice bLEDevice = (BLEDevice) a("device_info", BLEDevice.class);
        b(bLEDevice);
        return bLEDevice;
    }

    public DialPlate o() {
        String strA = a(B, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (DialPlate) new Gson().fromJson(strA, DialPlate.class);
    }

    public DisplayMode p() {
        String strA = a(x, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (DisplayMode) new Gson().fromJson(strA, DisplayMode.class);
    }

    public DrinkWaterReminder q() {
        return (DrinkWaterReminder) a(Z, DrinkWaterReminder.class);
    }

    public boolean r() {
        return a(p, true);
    }

    public FitnessGuidance s() {
        String strA = a(G, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (FitnessGuidance) new Gson().fromJson(strA, FitnessGuidance.class);
    }

    public Goal t() {
        String strA = a(f4449h, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (Goal) new Gson().fromJson(strA, Goal.class);
    }

    public ConfigGPS u() {
        ConfigGPS configGPS = (ConfigGPS) a(M, ConfigGPS.class);
        if (configGPS == null) {
            return null;
        }
        ConfigGPS configGPS2 = new ConfigGPS();
        configGPS2.gnsValue = configGPS.gnsValue;
        configGPS2.cycleMS = configGPS.cycleMS;
        configGPS2.operationMode = configGPS.operationMode;
        configGPS2.startMode = configGPS.startMode;
        return configGPS2;
    }

    public HandWearMode v() {
        return (HandWearMode) a(i, HandWearMode.class);
    }

    public HeartRateInterval w() {
        String strA = a(s, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (HeartRateInterval) new Gson().fromJson(strA, HeartRateInterval.class);
    }

    public HeartRateMeasureMode x() {
        String strA = a(q, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (HeartRateMeasureMode) new Gson().fromJson(strA, HeartRateMeasureMode.class);
    }

    public HeartRateMeasureModeV3 y() {
        String strA = a(r, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (HeartRateMeasureModeV3) new Gson().fromJson(strA, HeartRateMeasureModeV3.class);
    }

    public int z() {
        return a(K, 0);
    }
}