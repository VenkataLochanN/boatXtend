package com.ido.ble.f.a.f.c;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.common.d;
import com.ido.ble.common.e;
import com.ido.ble.common.k;
import com.ido.ble.gps.model.ConfigGPS;
import com.ido.ble.protocol.model.Alarm;
import com.ido.ble.protocol.model.AntiLostMode;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.BloodPressureAdjustPara;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import com.ido.ble.protocol.model.DialPlate;
import com.ido.ble.protocol.model.DisplayMode;
import com.ido.ble.protocol.model.Goal;
import com.ido.ble.protocol.model.HandWearMode;
import com.ido.ble.protocol.model.HeartRateInterval;
import com.ido.ble.protocol.model.HeartRateMeasureMode;
import com.ido.ble.protocol.model.LongSit;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.MenstrualRemind;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.QuickSportMode;
import com.ido.ble.protocol.model.ShortCut;
import com.ido.ble.protocol.model.SleepMonitoringPara;
import com.ido.ble.protocol.model.SportModeSort;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.Units;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.ble.protocol.model.UserInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class b extends d {
    private static final String A = "shortCut";
    private static final String B = "SleepMonitoring";
    private static final String C = "lastSyncHealthDataTime";
    private static final String D = "lastSyncHealthDataDeviceUniqueId";
    private static final String E = "offsetSportIndex";
    private static final String F = "offsetHeartRateIndex";
    private static final String G = "offsetBloodPressuredIndex";
    private static final String H = "setConfigGpsPara";
    private static final String I = "screenBrightness";
    private static final String J = "isNeedCollectRebootLog";
    private static final String K = "menstrual";
    private static final String L = "menstrual_remind";
    private static final String M = "calorie_distance_goal";
    private static final String N = "sport_mode_sort";
    private static b O = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f4462c = "BLE_DEVICE_PARAS";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f4463d = "lastConnectedDeviceInfo";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f4464e = "user_info";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f4465f = "supportFunctionTable";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final String f4466g = "alarms";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final String f4467h = "goal";
    private static final String i = "handWearMode";
    private static final String j = "bloodPressureAdjustPara";
    private static final String k = "units";
    private static final String l = "basicInfo";
    private static final String m = "AntiLostMode";
    private static final String n = "AntiLostPara";
    private static final String o = "LongSit";
    private static final String p = "findPhoneSwitch";
    private static final String q = "heartRateMode";
    private static final String r = "heartRateInterval";
    private static final String s = "upHandGesture";
    private static final String t = "notDisturb";
    private static final String u = "musicSwitch";
    private static final String v = "displayMode";
    private static final String w = "oneKeySos";
    private static final String x = "weatherSwitch";
    private static final String y = "quickSportMode";
    private static final String z = "dialPlateMode";

    class a extends TypeToken<List<Alarm>> {
        a() {
        }
    }

    b() {
    }

    public static synchronized b O() {
        if (O == null) {
            O = new b();
            O.a(e.a());
        }
        return O;
    }

    public boolean A() {
        return a(w, false);
    }

    public QuickSportMode B() {
        String strA = a(y, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (QuickSportMode) new Gson().fromJson(strA, QuickSportMode.class);
    }

    public int C() {
        return a(I, -1);
    }

    public ShortCut D() {
        String strA = a(A, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (ShortCut) new Gson().fromJson(strA, ShortCut.class);
    }

    public SleepMonitoringPara E() {
        String strA = a(B, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (SleepMonitoringPara) new Gson().fromJson(strA, SleepMonitoringPara.class);
    }

    public SportModeSort F() {
        return (SportModeSort) a(N, SportModeSort.class);
    }

    public int G() {
        return a(E, 0);
    }

    public SupportFunctionInfo H() {
        String strA = a(f4465f, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (SupportFunctionInfo) new Gson().fromJson(strA, SupportFunctionInfo.class);
    }

    public Units I() {
        String strA = a(k, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (Units) new Gson().fromJson(strA, Units.class);
    }

    public UpHandGesture J() {
        String strA = a(s, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (UpHandGesture) new Gson().fromJson(strA, UpHandGesture.class);
    }

    public UserInfo K() {
        String strA = a(f4464e, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (UserInfo) new Gson().fromJson(strA, UserInfo.class);
    }

    public boolean L() {
        return a(x, false);
    }

    public boolean M() {
        return a(J, false);
    }

    public boolean N() {
        return a(f4463d);
    }

    public void a(int i2) {
        b(I, i2);
    }

    public void a(long j2) {
        b(C, j2);
    }

    public void a(Context context) {
        super.a(context, f4462c);
    }

    public void a(BLEDevice bLEDevice) {
        b(f4463d, new Gson().toJson(bLEDevice));
    }

    public void a(ConfigGPS configGPS) {
        b(H, new Gson().toJson(configGPS));
    }

    public void a(AntiLostMode antiLostMode) {
        b(m, new Gson().toJson(antiLostMode));
    }

    public void a(BasicInfo basicInfo) {
        b(l, new Gson().toJson(basicInfo));
    }

    public void a(BloodPressureAdjustPara bloodPressureAdjustPara) {
        b(j, new Gson().toJson(bloodPressureAdjustPara));
    }

    public void a(CalorieAndDistanceGoal calorieAndDistanceGoal) {
        b(M, k.a(calorieAndDistanceGoal));
    }

    public void a(DialPlate dialPlate) {
        b(z, new Gson().toJson(dialPlate));
    }

    public void a(DisplayMode displayMode) {
        b(v, new Gson().toJson(displayMode));
    }

    public void a(Goal goal) {
        b(f4467h, new Gson().toJson(goal));
    }

    public void a(HandWearMode handWearMode) {
        b(i, new Gson().toJson(handWearMode));
    }

    public void a(HeartRateInterval heartRateInterval) {
        b(r, new Gson().toJson(heartRateInterval));
    }

    public void a(HeartRateMeasureMode heartRateMeasureMode) {
        b(q, new Gson().toJson(heartRateMeasureMode));
    }

    public void a(LongSit longSit) {
        b(o, new Gson().toJson(longSit));
    }

    public void a(Menstrual menstrual) {
        b("menstrual", new Gson().toJson(menstrual));
    }

    public void a(MenstrualRemind menstrualRemind) {
        b("menstrual_remind", k.a(menstrualRemind));
    }

    public void a(NotDisturbPara notDisturbPara) {
        b(t, new Gson().toJson(notDisturbPara));
    }

    public void a(QuickSportMode quickSportMode) {
        b(y, new Gson().toJson(quickSportMode));
    }

    public void a(ShortCut shortCut) {
        b(A, new Gson().toJson(shortCut));
    }

    public void a(SleepMonitoringPara sleepMonitoringPara) {
        b(B, new Gson().toJson(sleepMonitoringPara));
    }

    public void a(SportModeSort sportModeSort) {
        b(N, k.a(sportModeSort));
    }

    public void a(SupportFunctionInfo supportFunctionInfo) {
        b(f4465f, new Gson().toJson(supportFunctionInfo));
    }

    public void a(Units units) {
        b(k, new Gson().toJson(units));
    }

    public void a(UpHandGesture upHandGesture) {
        b(s, new Gson().toJson(upHandGesture));
    }

    public void a(UserInfo userInfo) {
        b(f4464e, new Gson().toJson(userInfo));
    }

    public void a(List<Alarm> list) {
        b(f4466g, new Gson().toJson(list));
    }

    public void a(boolean z2) {
        b(p, z2);
    }

    public void b() {
        b(f4466g);
        b(f4467h);
        b(i);
        b(j);
        b(k);
        b(l);
        b(m);
        b(n);
        b(o);
        b(p);
        b(q);
        b(r);
        b(s);
        b(t);
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
        b(E);
        b(F);
        b(G);
        b(H);
        b(I);
        b(J);
        b("menstrual");
        b("menstrual_remind");
        b(M);
        b(N);
    }

    public void b(int i2) {
        b(G, i2);
    }

    public void b(boolean z2) {
        b(u, z2);
    }

    public List<Alarm> c() {
        String strA = a(f4466g, "");
        return !TextUtils.isEmpty(strA) ? (List) new Gson().fromJson(strA, new a().getType()) : new ArrayList();
    }

    public void c(int i2) {
        b(F, i2);
    }

    public void c(String str) {
        b(D, str);
    }

    public void c(boolean z2) {
        b(w, z2);
    }

    public Map<Integer, Alarm> d() {
        List<Alarm> listC = c();
        HashMap map = new HashMap();
        for (int i2 = 0; i2 < listC.size(); i2++) {
            map.put(Integer.valueOf(listC.get(i2).getAlarmId()), listC.get(i2));
        }
        return map;
    }

    public void d(int i2) {
        b(E, i2);
    }

    public void d(boolean z2) {
        b(x, z2);
    }

    public AntiLostMode e() {
        return (AntiLostMode) a(m, AntiLostMode.class);
    }

    public void e(boolean z2) {
        b(J, z2);
    }

    public BasicInfo f() {
        return (BasicInfo) a(l, BasicInfo.class);
    }

    public BloodPressureAdjustPara g() {
        return (BloodPressureAdjustPara) a(j, BloodPressureAdjustPara.class);
    }

    public int h() {
        return a(G, 0);
    }

    public CalorieAndDistanceGoal i() {
        return (CalorieAndDistanceGoal) a(M, CalorieAndDistanceGoal.class);
    }

    public DialPlate j() {
        String strA = a(z, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (DialPlate) new Gson().fromJson(strA, DialPlate.class);
    }

    public DisplayMode k() {
        String strA = a(v, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (DisplayMode) new Gson().fromJson(strA, DisplayMode.class);
    }

    public boolean l() {
        return a(p, true);
    }

    public Goal m() {
        String strA = a(f4467h, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (Goal) new Gson().fromJson(strA, Goal.class);
    }

    public ConfigGPS n() {
        ConfigGPS configGPS = (ConfigGPS) a(H, ConfigGPS.class);
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

    public HandWearMode o() {
        return (HandWearMode) a(i, HandWearMode.class);
    }

    public HeartRateInterval p() {
        String strA = a(r, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (HeartRateInterval) new Gson().fromJson(strA, HeartRateInterval.class);
    }

    public HeartRateMeasureMode q() {
        String strA = a(q, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (HeartRateMeasureMode) new Gson().fromJson(strA, HeartRateMeasureMode.class);
    }

    public int r() {
        return a(F, 0);
    }

    public BLEDevice s() {
        return (BLEDevice) a(f4463d, BLEDevice.class);
    }

    public String t() {
        return a(D, "");
    }

    public long u() {
        return a(C, 0L);
    }

    public LongSit v() {
        String strA = a(o, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (LongSit) new Gson().fromJson(strA, LongSit.class);
    }

    public Menstrual w() {
        return (Menstrual) a("menstrual", Menstrual.class);
    }

    public MenstrualRemind x() {
        return (MenstrualRemind) a("menstrual_remind", MenstrualRemind.class);
    }

    public boolean y() {
        return a(u, false);
    }

    public NotDisturbPara z() {
        String strA = a(t, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (NotDisturbPara) new Gson().fromJson(strA, NotDisturbPara.class);
    }
}