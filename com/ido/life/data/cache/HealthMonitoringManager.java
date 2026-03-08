package com.ido.life.data.cache;

import com.boat.Xtend.two.R;
import com.google.android.gms.stats.CodePackage;
import com.ido.life.module.device.activity.BloodOxySettingActivity;
import com.ido.life.module.device.activity.FitnessGuidanceActivity;
import com.ido.life.module.device.activity.HeartRateMonitoringActivity;
import com.ido.life.module.device.activity.MenstrualCycleSettingActivity;
import com.ido.life.module.device.activity.NoiseMonitoringActivity;
import com.ido.life.module.device.activity.PressureMonitoringActivity;
import com.ido.life.module.device.activity.ReminderSelectActivity;
import com.ido.life.module.device.activity.ScientificSleepMonitoringActivity;
import com.ido.life.module.device.activity.WalkReminderActivity;
import com.ido.life.module.device.activity.WaterClockActivity;
import com.ido.life.util.SPHelper;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HealthMonitoringManager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\t\nB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\u000b"}, d2 = {"Lcom/ido/life/data/cache/HealthMonitoringManager;", "Lcom/ido/life/data/cache/AbsDataCacheManager;", "", "()V", "onClear", "", "onUnBind", "macAddress", "", "Companion", "SingleInstanceHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class HealthMonitoringManager extends AbsDataCacheManager<Object> {
    public static final int BODY_TEMPERATURE = 5;
    public static final int DRINK_WATER = 10;
    public static final int FITNESS = 8;
    public static final int HAND_WASH = 11;
    public static final int HEART_RATE = 1;
    public static final int MENSTRUAL_CYCLE = 7;
    public static final int NOISE = 6;
    public static final int PRESSURE = 2;
    public static final int REMIND = 9;
    public static final int SCIENTIFIC_SLEEP = 4;
    public static final int SPO2 = 3;
    public static final int WALK_REMINDER = 12;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final HealthMonitoringManager instance = SingleInstanceHolder.INSTANCE.getINSTANCE();

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onClear() {
    }

    /* JADX INFO: compiled from: HealthMonitoringManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0004J\u001c\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00182\u0006\u0010\u0016\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0019"}, d2 = {"Lcom/ido/life/data/cache/HealthMonitoringManager$Companion;", "", "()V", "BODY_TEMPERATURE", "", "DRINK_WATER", CodePackage.FITNESS, "HAND_WASH", "HEART_RATE", "MENSTRUAL_CYCLE", "NOISE", "PRESSURE", "REMIND", "SCIENTIFIC_SLEEP", "SPO2", "WALK_REMINDER", "instance", "Lcom/ido/life/data/cache/HealthMonitoringManager;", "getInstance", "()Lcom/ido/life/data/cache/HealthMonitoringManager;", "getHealthModuleClazz", "Ljava/lang/Class;", "appUnique", "getHealthModuleInfo", "Lkotlin/Pair;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final HealthMonitoringManager getInstance() {
            return HealthMonitoringManager.instance;
        }

        public final Pair<Integer, Integer> getHealthModuleInfo(int appUnique) {
            switch (appUnique) {
                case 1:
                    return new Pair<>(Integer.valueOf(R.string.public_heart_rate), Integer.valueOf(R.mipmap.icon_heart_rate_detection));
                case 2:
                    return new Pair<>(Integer.valueOf(R.string.public_pressure), Integer.valueOf(R.mipmap.icon_pressure_detection));
                case 3:
                    return new Pair<>(Integer.valueOf(R.string.public_spo2), Integer.valueOf(R.mipmap.icon_blood_detection));
                case 4:
                    return new Pair<>(Integer.valueOf(R.string.device_scientific_sleep), Integer.valueOf(R.mipmap.icon_scientifi_reminder));
                case 5:
                    return new Pair<>(Integer.valueOf(R.string.device_body_temperature), Integer.valueOf(R.mipmap.icon_health_body_temperature));
                case 6:
                    return new Pair<>(Integer.valueOf(R.string.device_noise), Integer.valueOf(R.mipmap.icon_noise_reminder));
                case 7:
                    return new Pair<>(Integer.valueOf(R.string.public_menstrual_cycle_remind), Integer.valueOf(R.mipmap.icon_menstrual_cycle));
                case 8:
                    return new Pair<>(Integer.valueOf(R.string.public_fitness), Integer.valueOf(R.mipmap.icon_health_sanhuan));
                case 9:
                    return new Pair<>(Integer.valueOf(R.string.shortcut_app_reminders), Integer.valueOf(R.mipmap.icon_health_schedule));
                case 10:
                    return new Pair<>(Integer.valueOf(R.string.public_drink_water), Integer.valueOf(R.mipmap.icon_water_clock));
                case 11:
                    return new Pair<>(Integer.valueOf(R.string.public_wash_hand), Integer.valueOf(R.mipmap.icon_health_wash_hand));
                case 12:
                    return new Pair<>(Integer.valueOf(R.string.public_walk), Integer.valueOf(R.mipmap.icon_walk_reminder));
                default:
                    return null;
            }
        }

        public final Class<?> getHealthModuleClazz(int appUnique) {
            switch (appUnique) {
                case 1:
                    return HeartRateMonitoringActivity.class;
                case 2:
                    return PressureMonitoringActivity.class;
                case 3:
                    return BloodOxySettingActivity.class;
                case 4:
                    return ScientificSleepMonitoringActivity.class;
                case 5:
                case 11:
                default:
                    return null;
                case 6:
                    return NoiseMonitoringActivity.class;
                case 7:
                    return MenstrualCycleSettingActivity.class;
                case 8:
                    return FitnessGuidanceActivity.class;
                case 9:
                    return ReminderSelectActivity.class;
                case 10:
                    return WaterClockActivity.class;
                case 12:
                    return WalkReminderActivity.class;
            }
        }
    }

    /* JADX INFO: compiled from: HealthMonitoringManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/ido/life/data/cache/HealthMonitoringManager$SingleInstanceHolder;", "", "()V", "INSTANCE", "Lcom/ido/life/data/cache/HealthMonitoringManager;", "getINSTANCE", "()Lcom/ido/life/data/cache/HealthMonitoringManager;", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final class SingleInstanceHolder {
        public static final SingleInstanceHolder INSTANCE = new SingleInstanceHolder();
        private static final HealthMonitoringManager INSTANCE = new HealthMonitoringManager();

        private SingleInstanceHolder() {
        }

        public final HealthMonitoringManager getINSTANCE() {
            return INSTANCE;
        }
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onUnBind(String macAddress) {
        Intrinsics.checkParameterIsNotNull(macAddress, "macAddress");
        super.onUnBind(macAddress);
        logP("onUnBind，removeSmartHeartRateMode,    removeBodyTemperature,    removeNoiseMode,    removeWashHandReminder");
        SPHelper.removeSmartHeartRateMode(macAddress);
        SPHelper.removeNoiseMode(macAddress);
    }
}