package com.ido.life.data.cache;

import android.text.TextUtils;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.SmallQuickModule;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.bean.ShortcutAppData;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShortcutAppManager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u001a\u001bB\u0005¢\u0006\u0002\u0010\u0003J\n\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0002J\b\u0010\t\u001a\u0004\u0018\u00010\u0007J\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\rJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rJ\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u000b2\u0006\u0010\f\u001a\u00020\rJ\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\rH\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u0007R\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/ido/life/data/cache/ShortcutAppManager;", "Lcom/ido/life/data/cache/AbsDataCacheManager;", "Lcom/ido/life/bean/ShortcutAppData;", "()V", "mQueryResponses", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/ido/ble/protocol/model/SmallQuickModule$QueryResponse;", "getMacAddress", "getQueryResponse", "getShortcutAppContent", "Lkotlin/Pair;", "appUnique", "", "getShortcutAppGroupName", "type", "getShortcutAppGroupType", "widgetsType", "getShortcutAppInfo", "getShortcutAppSampleInfo", "language", "strResId", "onClear", "", "setQueryResponse", AeUtil.ROOT_DATA_PATH_OLD_NAME, "Companion", "SingleInstanceHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ShortcutAppManager extends AbsDataCacheManager<ShortcutAppData> {
    public static final int MENU_ALARM = 13;
    public static final int MENU_ALEXA = 9;
    public static final int MENU_BATTERY_POWER = 16;
    public static final int MENU_BLOOD_OXYGEN = 11;
    public static final int MENU_BODY_TEMPERATURE = 10;
    public static final int MENU_DATA_THREE_CIRCLE = 1;
    public static final int MENU_HEART_RATE = 6;
    public static final int MENU_MUSIC = 4;
    public static final int MENU_NOISE = 15;
    public static final int MENU_PHONE = 17;
    public static final int MENU_PRESSURE = 7;
    public static final int MENU_REMINDERS = 14;
    public static final int MENU_SLEEP = 8;
    public static final int MENU_SPORTS = 3;
    public static final int MENU_STEP = 2;
    public static final int MENU_TIME = 18;
    public static final int MENU_TIMER = 12;
    public static final int MENU_WEATHER = 5;
    private ConcurrentHashMap<String, SmallQuickModule.QueryResponse> mQueryResponses = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ShortcutAppManager instance = SingleInstanceHolder.INSTANCE.getINSTANCE();

    public final int getShortcutAppGroupType(int widgetsType) {
        switch (widgetsType) {
            case 1:
            case 2:
            case 3:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 15:
                return 1;
            case 4:
            case 5:
            case 9:
            case 12:
            case 13:
            case 14:
            case 16:
            case 17:
            case 18:
            default:
                return 2;
        }
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onClear() {
    }

    /* JADX INFO: compiled from: ShortcutAppManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/ido/life/data/cache/ShortcutAppManager$Companion;", "", "()V", "MENU_ALARM", "", "MENU_ALEXA", "MENU_BATTERY_POWER", "MENU_BLOOD_OXYGEN", "MENU_BODY_TEMPERATURE", "MENU_DATA_THREE_CIRCLE", "MENU_HEART_RATE", "MENU_MUSIC", "MENU_NOISE", "MENU_PHONE", "MENU_PRESSURE", "MENU_REMINDERS", "MENU_SLEEP", "MENU_SPORTS", "MENU_STEP", "MENU_TIME", "MENU_TIMER", "MENU_WEATHER", "instance", "Lcom/ido/life/data/cache/ShortcutAppManager;", "getInstance", "()Lcom/ido/life/data/cache/ShortcutAppManager;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ShortcutAppManager getInstance() {
            return ShortcutAppManager.instance;
        }
    }

    /* JADX INFO: compiled from: ShortcutAppManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/ido/life/data/cache/ShortcutAppManager$SingleInstanceHolder;", "", "()V", "INSTANCE", "Lcom/ido/life/data/cache/ShortcutAppManager;", "getINSTANCE", "()Lcom/ido/life/data/cache/ShortcutAppManager;", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final class SingleInstanceHolder {
        public static final SingleInstanceHolder INSTANCE = new SingleInstanceHolder();
        private static final ShortcutAppManager INSTANCE = new ShortcutAppManager();

        private SingleInstanceHolder() {
        }

        public final ShortcutAppManager getINSTANCE() {
            return INSTANCE;
        }
    }

    public final void setQueryResponse(SmallQuickModule.QueryResponse data) {
        String macAddress = getMacAddress();
        if (TextUtils.isEmpty(macAddress)) {
            return;
        }
        if (data == null) {
            ConcurrentHashMap<String, SmallQuickModule.QueryResponse> concurrentHashMap = this.mQueryResponses;
            if (concurrentHashMap != null) {
                ConcurrentHashMap<String, SmallQuickModule.QueryResponse> concurrentHashMap2 = concurrentHashMap;
                if (concurrentHashMap2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, V>");
                }
                return;
            }
            return;
        }
        ConcurrentHashMap<String, SmallQuickModule.QueryResponse> concurrentHashMap3 = this.mQueryResponses;
        if (concurrentHashMap3 != null) {
            if (macAddress == null) {
                Intrinsics.throwNpe();
            }
            concurrentHashMap3.put(macAddress, data);
        }
    }

    public final SmallQuickModule.QueryResponse getQueryResponse() {
        ConcurrentHashMap<String, SmallQuickModule.QueryResponse> concurrentHashMap;
        String macAddress = getMacAddress();
        if (TextUtils.isEmpty(macAddress) || (concurrentHashMap = this.mQueryResponses) == null) {
            return null;
        }
        return concurrentHashMap.get(macAddress);
    }

    private final String getMacAddress() {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            return currentDeviceInfo.mDeviceAddress;
        }
        return null;
    }

    public final String getShortcutAppGroupName(int type) {
        String languageText = LanguageUtil.getLanguageText(type != 1 ? R.string.shortcut_app_tools_title : R.string.shortcut_app_health_title);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguageText(res)");
        return languageText;
    }

    public final Pair<Integer, Integer> getShortcutAppInfo(int appUnique) {
        Integer numValueOf = Integer.valueOf(R.mipmap.icon_shortcut_app_tricyclic);
        switch (appUnique) {
            case 1:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_tricyclic), numValueOf);
            case 2:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_steps), Integer.valueOf(R.mipmap.icon_shorcut_app_walk_list));
            case 3:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_sports), Integer.valueOf(R.mipmap.icon_shorcut_app_sport_list));
            case 4:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_music), Integer.valueOf(R.mipmap.icon_shorcut_app_music_list));
            case 5:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_weather), Integer.valueOf(R.mipmap.icon_shorcut_app_weather_list));
            case 6:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_heart_rate), Integer.valueOf(R.mipmap.icon_shorcut_app_heart_list));
            case 7:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_spressure), Integer.valueOf(R.mipmap.icon_shorcut_app_pressure_list));
            case 8:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_sleep), Integer.valueOf(R.mipmap.icon_shorcut_app_sleep_list));
            case 9:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_Alexa), Integer.valueOf(R.mipmap.icon_shorcut_app_alexa_list));
            case 10:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_body_temperature), Integer.valueOf(R.mipmap.icon_tempretrue));
            case 11:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_blood_oxygen), Integer.valueOf(R.mipmap.icon_shorcut_app_oxygen_list));
            case 12:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_timer), Integer.valueOf(R.mipmap.icon_shorcut_app_timer_list));
            case 13:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_alarm_clock), Integer.valueOf(R.mipmap.icon_shorcut_app_clock_list));
            case 14:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_reminders), Integer.valueOf(R.mipmap.icon_shorcut_app_agenda_list));
            case 15:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_noise), Integer.valueOf(R.mipmap.icon_shorcut_app_noise_list));
            case 16:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_battery_power), Integer.valueOf(R.mipmap.icon_shorcut_app_electricity_list));
            case 17:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_phone), Integer.valueOf(R.mipmap.icon_shorcut_app_phone_list));
            case 18:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_world_time), Integer.valueOf(R.mipmap.icon_shorcut_app_world_time_list));
            default:
                return new Pair<>(Integer.valueOf(R.string.home_steps_tittle), numValueOf);
        }
    }

    public final Pair<Integer, Integer> getShortcutAppSampleInfo(int appUnique) {
        Integer numValueOf = Integer.valueOf(R.mipmap.icon_shortcut_app_tricyclic);
        switch (appUnique) {
            case 1:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_tricyclic), numValueOf);
            case 2:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_steps), Integer.valueOf(R.mipmap.icon_shortcut_app_steps));
            case 3:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_sports), Integer.valueOf(R.mipmap.icon_shortcut_app_exercise));
            case 4:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_music), Integer.valueOf(R.mipmap.icon_shortcut_app_music));
            case 5:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_weather), Integer.valueOf(R.mipmap.icon_shortcut_app_weather));
            case 6:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_heart_rate), Integer.valueOf(R.mipmap.icon_shortcut_app_heart));
            case 7:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_spressure), Integer.valueOf(R.mipmap.icon_shortcut_app_stress));
            case 8:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_sleep), Integer.valueOf(R.mipmap.icon_shortcut_app_sleep));
            case 9:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_Alexa), Integer.valueOf(R.mipmap.icon_shortcut_app_alexa));
            case 10:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_body_temperature), Integer.valueOf(R.mipmap.icon_shortcut_app_tempreture));
            case 11:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_blood_oxygen), Integer.valueOf(R.mipmap.icon_shortcut_app_oxygen));
            case 12:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_timer), Integer.valueOf(R.mipmap.icon_shortcut_app_timer));
            case 13:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_alarm_clock), Integer.valueOf(R.mipmap.icon_shortcut_app_clock));
            case 14:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_reminders), Integer.valueOf(R.mipmap.icon_shortcut_app_task));
            case 15:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_noise), Integer.valueOf(R.mipmap.icon_shortcut_app_voice));
            case 16:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_battery_power), Integer.valueOf(R.mipmap.icon_shortcut_app_eletricity));
            case 17:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_phone), Integer.valueOf(R.mipmap.icon_shorcut_app_address_book));
            case 18:
                return new Pair<>(Integer.valueOf(R.string.shortcut_app_world_time), Integer.valueOf(R.mipmap.icon_shortcut_app_world_time));
            default:
                return new Pair<>(Integer.valueOf(R.string.home_steps_tittle), numValueOf);
        }
    }

    public final Pair<String, String> getShortcutAppContent(int appUnique) {
        if (appUnique == 2) {
            return new Pair<>("2000", "");
        }
        if (appUnique != 5) {
            switch (appUnique) {
                case 9:
                    return new Pair<>(language(R.string.shortcut_app_alexa_content), "");
                case 10:
                    return new Pair<>("36", "℃");
                case 11:
                    return new Pair<>("95", "%");
                case 12:
                    return new Pair<>("00:00:00", "");
                case 13:
                    return new Pair<>("08:00 ", "");
                default:
                    switch (appUnique) {
                        case 15:
                            return new Pair<>("75", "dB");
                        case 16:
                            return new Pair<>("98", "%");
                        case 17:
                            return new Pair<>(language(R.string.shortcut_app_contact), "");
                        case 18:
                            return new Pair<>("06:00", "");
                        default:
                            return new Pair<>("", "");
                    }
            }
        }
        return new Pair<>("24", "℃ ");
    }

    private final String language(int strResId) {
        String languageText = LanguageUtil.getLanguageText(strResId);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguageText(strResId)");
        return languageText;
    }
}