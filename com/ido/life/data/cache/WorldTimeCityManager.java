package com.ido.life.data.cache;

import android.text.TextUtils;
import com.alibaba.fastjson.asm.Opcodes;
import com.boat.Xtend.two.R;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.WorldTime;
import com.ido.common.IdoApp;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.VeryFitApp;
import com.ido.life.base.BaseMessage;
import com.ido.life.bean.WorldTimeCity;
import com.ido.life.customview.NormalToast;
import com.ido.life.data.ExecutorDispatcher;
import com.ido.life.data.cache.listener.OnWorldTimeRefreshListener;
import com.ido.life.module.home.HomeFragmentPresenter;
import com.ido.life.module.sport.bean.LocationMessage;
import com.ido.life.module.sport.util.BigDecimalUtil;
import com.ido.life.util.CalendarUtils;
import com.ido.life.util.CoroutinesUtils;
import com.ido.life.util.ListUtils;
import com.ido.life.util.PinyinUtils;
import com.ido.life.util.ResourceUtils;
import com.ido.life.util.SPHelper;
import com.ido.life.util.SunRiseSetUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.channels.ProducerScope;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: WorldTimeCityManager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008b\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r*\u0001\u0010\u0018\u0000 O2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002OPB\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0016\u0010\u0019\u001a\u00020\u001a2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005H\u0002J\"\u0010\u001b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u001c\u001a\u00020\u001d2\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0005H\u0007J\u0010\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0005H\u0007J\u0018\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\u0006\u0010 \u001a\u00020\fH\u0007J\u001c\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005H\u0002J\u0006\u0010#\u001a\u00020\fJ\b\u0010$\u001a\u0004\u0018\u00010\u0002J\u001e\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005H\u0002J\u0010\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0005H\u0007J\u0010\u0010'\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010\u0005H\u0007J*\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020.J*\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020.J\u000e\u00100\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0010\u00101\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u00102\u001a\u00020\u001aJ\b\u00103\u001a\u00020\fH\u0002J\b\u00104\u001a\u00020\u0016H\u0016J\b\u00105\u001a\u00020\u0016H\u0016J\u0016\u00106\u001a\u00020\u00162\f\u00107\u001a\b\u0012\u0004\u0012\u00020\"08H\u0007J\u0012\u00109\u001a\u00020\u00162\b\u0010:\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010;\u001a\u00020\u0016H\u0002J\u0010\u0010<\u001a\u00020\u00162\u0006\u0010:\u001a\u00020\fH\u0016J.\u0010=\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\u000e\u0010>\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010?2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005H\u0002J\u0006\u0010@\u001a\u00020\u0016J\u0018\u0010A\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\u0006\u0010B\u001a\u00020\fH\u0007J,\u0010A\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\u0006\u0010B\u001a\u00020\f2\u0012\u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u001a0DH\u0007J\u0010\u0010E\u001a\u00020\u00162\u0006\u0010F\u001a\u00020\"H\u0002J\u000e\u0010G\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010H\u001a\u00020\u0016H\u0002J \u0010I\u001a\u00020\u001a2\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\u0006\u0010J\u001a\u00020\u001aH\u0002J\u001c\u0010K\u001a\u00020\u001a2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\"0\u00052\u0006\u0010J\u001a\u00020\u001aJ\u0016\u0010M\u001a\u00020\u00162\f\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005H\u0002R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Q"}, d2 = {"Lcom/ido/life/data/cache/WorldTimeCityManager;", "Lcom/ido/life/data/cache/AbsDataCacheManager;", "Lcom/ido/life/bean/WorldTimeCity;", "()V", "cities", "", "citiesForDevice", "citiesInDevice", "citiesInSync", "hasSet2Device", "Ljava/util/concurrent/atomic/AtomicBoolean;", "mCurrentLanguage", "", "mLocationMessage", "Lcom/ido/life/module/sport/bean/LocationMessage;", "mSettingCallBack", "com/ido/life/data/cache/WorldTimeCityManager$mSettingCallBack$1", "Lcom/ido/life/data/cache/WorldTimeCityManager$mSettingCallBack$1;", "onWorldTimeRefreshListener", "Lcom/ido/life/data/cache/listener/OnWorldTimeRefreshListener;", "sync", "addDebounce", "", "timeInMill", "", "checkDataChanged", "", "findCityByTimeZone", "timeZone", "Ljava/util/TimeZone;", "getCities", "getCitiesByDeviceLanguage", "language", "getDefaultIds", "", "getDefaultTimeZoneAbb", "getDefaultTimeZoneCityInfo", "getDefaultWorldTimeCities", "getDeviceWorldTimeCities", "getDeviceWorldTimeCityIdsByLanguage", "getSunRiseTime", "Lkotlin/Pair;", "longitude", "", "latitude", "dateTime", "Ljava/util/Calendar;", "getSunSetTime", "getTimeZoneAbb", "getTimeZoneCityInfo", "isSyncing", "keyCitiesInDevice", "onClear", "onConnectBreak", "onDeviceLanguageChanged", "event", "Lcom/ido/life/base/BaseMessage;", "onSdkInitComplete", "macAddress", "onSyncComplete", "onUnBind", "queryCitiesByIds", "cityIds", "", "refreshDeviceWorldTime", "searchCities", "key", "filter", "Lkotlin/Function1;", "setDataByDeviceLanguage", "useLang", "setLocationMessage", "showError", "syncWorldTimeToDevice", "forceSync", "syncWorldTimeToDeviceById", "ids", "updateDeviceWorldTimeCities", "list", "Companion", "SingleInstanceHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WorldTimeCityManager extends AbsDataCacheManager<WorldTimeCity> {
    private static final ArrayList<Integer> DEFAULT_CITY_IDS;
    public static final String KEY_CITIES_IN_DEVICE = "KEY_CITIES_IN_DEVICE";
    private static final boolean ifSortNecessary;
    private volatile List<WorldTimeCity> cities;
    private volatile List<WorldTimeCity> citiesForDevice;
    private volatile List<WorldTimeCity> citiesInDevice;
    private LocationMessage mLocationMessage;
    private final WorldTimeCityManager$mSettingCallBack$1 mSettingCallBack;
    private OnWorldTimeRefreshListener onWorldTimeRefreshListener;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final WorldTimeCityManager instance = SingleInstanceHolder.INSTANCE.getINSTANCE();
    private static final boolean isZH = LanguageUtil.isZh(IdoApp.getAppContext());
    private volatile List<WorldTimeCity> citiesInSync = new ArrayList();
    private AtomicBoolean hasSet2Device = new AtomicBoolean(false);
    private AtomicBoolean sync = new AtomicBoolean(false);
    private String mCurrentLanguage = "";

    public static final WorldTimeCityManager getInstance() {
        Companion companion = INSTANCE;
        return instance;
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.ido.life.data.cache.WorldTimeCityManager$mSettingCallBack$1] */
    public WorldTimeCityManager() {
        addDebounce(1000L);
        this.mSettingCallBack = new SettingCallBack.ICallBack() { // from class: com.ido.life.data.cache.WorldTimeCityManager$mSettingCallBack$1
            @Override // com.ido.ble.callback.SettingCallBack.ICallBack
            public void onSuccess(SettingCallBack.SettingType p0, Object p1) {
                if (p0 == SettingCallBack.SettingType.WORLD_TIME) {
                    WorldTimeCityManager worldTimeCityManager = this.this$0;
                    StringBuilder sb = new StringBuilder();
                    sb.append("世界时钟同步成功：");
                    List list = this.this$0.citiesInSync;
                    sb.append((list != null ? Integer.valueOf(list.size()) : null).intValue());
                    worldTimeCityManager.logP(sb.toString());
                    this.this$0.onSyncComplete();
                    List list2 = this.this$0.citiesInSync;
                    if (list2 != null) {
                        this.this$0.updateDeviceWorldTimeCities(list2);
                    }
                }
            }

            @Override // com.ido.ble.callback.SettingCallBack.ICallBack
            public void onFailed(SettingCallBack.SettingType p0) {
                if (p0 == SettingCallBack.SettingType.WORLD_TIME) {
                    this.this$0.logP("世界时钟同步失败...");
                    this.this$0.showError();
                    this.this$0.onSyncComplete();
                }
            }
        };
    }

    /* JADX INFO: compiled from: WorldTimeCityManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u00020\u00108\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0002\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000e¨\u0006\u0015"}, d2 = {"Lcom/ido/life/data/cache/WorldTimeCityManager$Companion;", "", "()V", "DEFAULT_CITY_IDS", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getDEFAULT_CITY_IDS", "()Ljava/util/ArrayList;", WorldTimeCityManager.KEY_CITIES_IN_DEVICE, "", "ifSortNecessary", "", "getIfSortNecessary", "()Z", "instance", "Lcom/ido/life/data/cache/WorldTimeCityManager;", "instance$annotations", "getInstance", "()Lcom/ido/life/data/cache/WorldTimeCityManager;", "isZH", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void instance$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final WorldTimeCityManager getInstance() {
            return WorldTimeCityManager.instance;
        }

        public final boolean isZH() {
            return WorldTimeCityManager.isZH;
        }

        public final boolean getIfSortNecessary() {
            return WorldTimeCityManager.ifSortNecessary;
        }

        public final ArrayList<Integer> getDEFAULT_CITY_IDS() {
            return WorldTimeCityManager.DEFAULT_CITY_IDS;
        }
    }

    static {
        ifSortNecessary = isZH || LanguageUtil.isEn(IdoApp.getAppContext());
        DEFAULT_CITY_IDS = CollectionsKt.arrayListOf(Integer.valueOf(Opcodes.LCMP), 197, 295);
    }

    /* JADX INFO: compiled from: WorldTimeCityManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/ido/life/data/cache/WorldTimeCityManager$SingleInstanceHolder;", "", "()V", "INSTANCE", "Lcom/ido/life/data/cache/WorldTimeCityManager;", "getINSTANCE", "()Lcom/ido/life/data/cache/WorldTimeCityManager;", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final class SingleInstanceHolder {
        public static final SingleInstanceHolder INSTANCE = new SingleInstanceHolder();
        private static final WorldTimeCityManager INSTANCE = new WorldTimeCityManager();

        private SingleInstanceHolder() {
        }

        public final WorldTimeCityManager getINSTANCE() {
            return INSTANCE;
        }
    }

    public final void setLocationMessage(LocationMessage mLocationMessage) {
        Intrinsics.checkParameterIsNotNull(mLocationMessage, "mLocationMessage");
        this.mLocationMessage = mLocationMessage;
    }

    private final void addDebounce(long timeInMill) {
        CoroutinesUtils.INSTANCE.debounce(timeInMill, new Function1<ProducerScope<? super List<Integer>>, Unit>() { // from class: com.ido.life.data.cache.WorldTimeCityManager.addDebounce.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ProducerScope<? super List<Integer>> producerScope) {
                invoke2(producerScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final ProducerScope<? super List<Integer>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                WorldTimeCityManager.this.onWorldTimeRefreshListener = new OnWorldTimeRefreshListener() { // from class: com.ido.life.data.cache.WorldTimeCityManager.addDebounce.1.1
                    @Override // com.ido.life.data.cache.listener.OnWorldTimeRefreshListener
                    public void onWorldTimeRefresh(List<Integer> list) {
                        Intrinsics.checkParameterIsNotNull(list, "list");
                        WorldTimeCityManager.this.logP("onWorldTimeRefresh ids = " + list);
                        it.offer(list);
                    }
                };
            }
        }, new Function0<Unit>() { // from class: com.ido.life.data.cache.WorldTimeCityManager.addDebounce.2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                WorldTimeCityManager.this.onWorldTimeRefreshListener = (OnWorldTimeRefreshListener) null;
            }
        }, new Function1<Throwable, Unit>() { // from class: com.ido.life.data.cache.WorldTimeCityManager.addDebounce.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                WorldTimeCityManager.this.logP("onWorldTimeRefresh error：" + it);
            }
        }, new Function1<List<Integer>, Unit>() { // from class: com.ido.life.data.cache.WorldTimeCityManager.addDebounce.4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<Integer> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<Integer> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                WorldTimeCityManager.this.logP("notify world time refresh：" + it);
                WorldTimeCityManager.this.refreshDeviceWorldTime();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onSyncComplete() {
        this.sync.set(false);
        BLEManager.unregisterSettingCallBack(this.mSettingCallBack);
    }

    public final boolean isSyncing() {
        return this.sync.get();
    }

    /* JADX INFO: renamed from: com.ido.life.data.cache.WorldTimeCityManager$showError$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: WorldTimeCityManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.data.cache.WorldTimeCityManager$showError$1", f = "WorldTimeCityManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02211 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C02211(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02211 c02211 = new C02211(completion);
            c02211.p$ = (CoroutineScope) obj;
            return c02211;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02211) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            NormalToast.showToast(LanguageUtil.getLanguageText(R.string.public_set_failed));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showError() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C02211(null), 2, null);
    }

    public final void refreshDeviceWorldTime() {
        logP("refreshDeviceWorldTime");
        ArrayList deviceWorldTimeCityIdsByLanguage = getDeviceWorldTimeCityIdsByLanguage();
        if (deviceWorldTimeCityIdsByLanguage == null) {
            deviceWorldTimeCityIdsByLanguage = new ArrayList();
        }
        syncWorldTimeToDeviceById(deviceWorldTimeCityIdsByLanguage, true);
    }

    public final boolean syncWorldTimeToDeviceById(List<Integer> ids, boolean forceSync) {
        Intrinsics.checkParameterIsNotNull(ids, "ids");
        if (ListUtils.INSTANCE.isNullOrEmpty(this.citiesForDevice)) {
            logP("syncWorldTimeToDevice 对应语言的时区列表为空！！！");
            return false;
        }
        List<WorldTimeCity> list = this.citiesForDevice;
        if (list == null) {
            Intrinsics.throwNpe();
        }
        return syncWorldTimeToDevice(queryCitiesByIds(ids, list), forceSync);
    }

    private final boolean syncWorldTimeToDevice(final List<WorldTimeCity> cities, boolean forceSync) {
        if (ListUtils.INSTANCE.isNullOrEmpty(cities)) {
            logP("没有有效数据，不同步");
            return false;
        }
        if (!forceSync) {
            if (this.hasSet2Device.get()) {
                if (cities == null) {
                    Intrinsics.throwNpe();
                }
                if (!checkDataChanged(cities)) {
                    logP("数据未发生变化，不同步");
                    return false;
                }
            }
        } else {
            logP("syncWorldTimeToDevice -> 强制同步");
        }
        if (this.sync.get()) {
            logP("正在同步中...");
            return true;
        }
        logP("开始同步世界时钟，forceSync = " + forceSync);
        this.sync.set(true);
        this.citiesInSync.clear();
        List<WorldTimeCity> list = this.citiesInSync;
        if (cities == null) {
            Intrinsics.throwNpe();
        }
        list.addAll(cities);
        ExecutorDispatcher.getInstance().dispatch(new Runnable() { // from class: com.ido.life.data.cache.WorldTimeCityManager.syncWorldTimeToDevice.1
            @Override // java.lang.Runnable
            public final void run() {
                List<WorldTimeCity> list2 = cities;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                for (WorldTimeCity worldTimeCity : list2) {
                    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(worldTimeCity.getTimeZoneName()));
                    WorldTimeCityManager worldTimeCityManager = WorldTimeCityManager.this;
                    double longitude = worldTimeCity.getLongitude();
                    double latitude = worldTimeCity.getLatitude();
                    Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                    Pair<String, String> sunRiseTime = worldTimeCityManager.getSunRiseTime(longitude, latitude, calendar);
                    Pair<String, String> sunSetTime = WorldTimeCityManager.this.getSunSetTime(worldTimeCity.getLongitude(), worldTimeCity.getLatitude(), calendar);
                    WorldTime.Item item = new WorldTime.Item();
                    item.id = worldTimeCity.getId();
                    item.city_name = worldTimeCity.getName();
                    double d2 = 100;
                    item.latitude = Math.abs(MathKt.roundToInt(BigDecimalUtil.round(worldTimeCity.getLatitude(), 2) * d2));
                    item.longitude = Math.abs(MathKt.roundToInt(BigDecimalUtil.round(worldTimeCity.getLongitude(), 2) * d2));
                    double d3 = 0;
                    int i = 1;
                    item.latitude_flag = worldTimeCity.getLatitude() >= d3 ? 1 : 2;
                    if (worldTimeCity.getLongitude() < d3) {
                        i = 2;
                    }
                    item.longitude_flag = i;
                    item.sunrise_hour = Integer.parseInt(sunRiseTime.getFirst());
                    item.sunrise_min = Integer.parseInt(sunRiseTime.getSecond());
                    item.sunset_hour = Integer.parseInt(sunSetTime.getFirst());
                    item.sunset_min = Integer.parseInt(sunSetTime.getSecond());
                    TimeZone timeZone = TimeZone.getTimeZone(worldTimeCity.getTimeZoneName());
                    Intrinsics.checkExpressionValueIsNotNull(timeZone, "TimeZone.getTimeZone(it.timeZoneName)");
                    item.min_offset = CalendarUtils.getTimezoneOffsetInMin(timeZone);
                    arrayList.add(item);
                }
                ArrayList arrayList2 = arrayList;
                WorldTimeCityManager.this.logP("同步世界时钟到设备：" + GsonUtil.toJson(arrayList2));
                BLEManager.registerSettingCallBack(WorldTimeCityManager.this.mSettingCallBack);
                BLEManager.setWorldTime(arrayList2);
            }
        });
        return true;
    }

    private final boolean checkDataChanged(List<WorldTimeCity> cities) {
        return ListUtils.INSTANCE.isNotEmpty(cities) && (Intrinsics.areEqual(this.citiesInDevice, cities) ^ true);
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onClear() {
        List<WorldTimeCity> list;
        if (ListUtils.INSTANCE.isNotEmpty(this.citiesInDevice)) {
            this.citiesInDevice = new ArrayList();
        }
        if (!ListUtils.INSTANCE.isNotEmpty(this.citiesInSync) || (list = this.citiesInSync) == null) {
            return;
        }
        list.clear();
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onConnectBreak() {
        super.onConnectBreak();
        this.sync.set(false);
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onSdkInitComplete(String macAddress) {
        super.onSdkInitComplete(macAddress);
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onUnBind(String macAddress) {
        Intrinsics.checkParameterIsNotNull(macAddress, "macAddress");
        super.onUnBind(macAddress);
        logP("onUnBind,     removeDeviceWorldTimeCity, macAddress = " + macAddress);
        SPHelper.removeDeviceWorldTimeCity(macAddress);
        SPHelper.removeDeviceWorldTimeCityIds(macAddress);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onDeviceLanguageChanged(BaseMessage<Integer> event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        boolean zIsConnected = BLEManager.isConnected();
        if (!zIsConnected) {
            logP("onDeviceLanguageChanged：" + event.getType() + "，设备未连接！");
            return;
        }
        if (event.getType() == 901) {
            logP("onDeviceLanguageChanged，language = " + event.getData() + "，设备是否已连接：" + zIsConnected);
            Integer data = event.getData();
            Intrinsics.checkExpressionValueIsNotNull(data, "event.data");
            setDataByDeviceLanguage(data.intValue());
            return;
        }
        if (event.getType() == 916) {
            logP("跟随天气，刷新世界时钟，设备是否已连接：" + zIsConnected);
            if (HomeFragmentPresenter.mIsSyncing || HomeFragmentPresenter.mDeviceUpgrading) {
                logP("正在同步或在升级中..., mIsSyncing = " + HomeFragmentPresenter.mIsSyncing + ", mDeviceUpgrading = " + HomeFragmentPresenter.mDeviceUpgrading);
                return;
            }
            OnWorldTimeRefreshListener onWorldTimeRefreshListener = this.onWorldTimeRefreshListener;
            if (onWorldTimeRefreshListener != null) {
                onWorldTimeRefreshListener.onWorldTimeRefresh(new ArrayList());
            }
        }
    }

    private final void setDataByDeviceLanguage(int useLang) {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo != null && supportFunctionInfo.V3_support_set_v3_world_time) {
            logP("It's time to syncWorldTimeToDevice");
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C02201(useLang, null), 2, null);
        } else {
            logP("The device connected is not support set world time!!!");
        }
    }

    /* JADX INFO: renamed from: com.ido.life.data.cache.WorldTimeCityManager$setDataByDeviceLanguage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: WorldTimeCityManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.data.cache.WorldTimeCityManager$setDataByDeviceLanguage$1", f = "WorldTimeCityManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02201 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $useLang;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02201(int i, Continuation continuation) {
            super(2, continuation);
            this.$useLang = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02201 c02201 = WorldTimeCityManager.this.new C02201(this.$useLang, completion);
            c02201.p$ = (CoroutineScope) obj;
            return c02201;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02201) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                Locale locale = Locale.getDefault();
                Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
                String language = locale.getLanguage();
                Intrinsics.checkExpressionValueIsNotNull(language, "Locale.getDefault().language");
                Locale locale2 = Locale.getDefault();
                Intrinsics.checkExpressionValueIsNotNull(locale2, "Locale.getDefault()");
                if (language == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String lowerCase = language.toLowerCase(locale2);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
                WorldTimeCityManager.this.logP("initDataByDeviceLanguage language = " + this.$useLang + "，sysLan = " + lowerCase);
                WorldTimeCityManager.this.getCities();
                WorldTimeCityManager.this.getCitiesByDeviceLanguage(ResourceUtils.INSTANCE.getLanguageAbb(this.$useLang));
                ResourceUtils resourceUtils = ResourceUtils.INSTANCE;
                VeryFitApp app = VeryFitApp.getApp();
                Intrinsics.checkExpressionValueIsNotNull(app, "VeryFitApp.getApp()");
                resourceUtils.restoreSystemResource(app, lowerCase);
                ArrayList deviceWorldTimeCityIdsByLanguage = WorldTimeCityManager.this.getDeviceWorldTimeCityIdsByLanguage();
                if (deviceWorldTimeCityIdsByLanguage == null) {
                    deviceWorldTimeCityIdsByLanguage = new ArrayList();
                }
                WorldTimeCityManager.this.syncWorldTimeToDeviceById(deviceWorldTimeCityIdsByLanguage, true);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final synchronized WorldTimeCity findCityByTimeZone(TimeZone timeZone, List<WorldTimeCity> cities) {
        WorldTimeCity worldTimeCity;
        Intrinsics.checkParameterIsNotNull(timeZone, "timeZone");
        String id = timeZone.getID();
        logP("timezone = " + timeZone.getDisplayName());
        worldTimeCity = null;
        Object obj = null;
        if (cities != null) {
            Iterator<T> it = cities.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (id.equals(((WorldTimeCity) next).getTimeZoneName())) {
                    obj = next;
                    break;
                }
            }
            worldTimeCity = (WorldTimeCity) obj;
        }
        return worldTimeCity;
    }

    public final synchronized List<WorldTimeCity> searchCities(String key, Function1<? super WorldTimeCity, Boolean> filter) {
        String strConvertChinese2Pinyin;
        String strConvertChinese2Pinyin2;
        boolean zBooleanValue;
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(filter, "filter");
        if (TextUtils.isEmpty(key)) {
            return new ArrayList();
        }
        if (ListUtils.INSTANCE.isNullOrEmpty(this.cities)) {
            getCities();
        }
        ArrayList arrayList = new ArrayList();
        if (ListUtils.INSTANCE.isNotEmpty(this.cities)) {
            List<WorldTimeCity> list = this.cities;
            if (list == null) {
                Intrinsics.throwNpe();
            }
            for (WorldTimeCity worldTimeCity : list) {
                try {
                    strConvertChinese2Pinyin = "";
                    strConvertChinese2Pinyin2 = "";
                    if (isZH) {
                        strConvertChinese2Pinyin = TextUtils.isEmpty(worldTimeCity.getName()) ? "" : PinyinUtils.convertChinese2Pinyin(worldTimeCity.getName());
                        if (!TextUtils.isEmpty(worldTimeCity.getCountry())) {
                            strConvertChinese2Pinyin2 = PinyinUtils.convertChinese2Pinyin(worldTimeCity.getCountry());
                        }
                    }
                    zBooleanValue = filter.invoke(worldTimeCity).booleanValue();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (!StringsKt.contains((CharSequence) worldTimeCity.getName(), (CharSequence) key, true)) {
                    String country = worldTimeCity.getCountry();
                    if ((country != null ? Boolean.valueOf(StringsKt.contains((CharSequence) country, (CharSequence) key, true)) : null).booleanValue() || (isZH && (StringsKt.contains((CharSequence) strConvertChinese2Pinyin, (CharSequence) key, true) || StringsKt.contains((CharSequence) strConvertChinese2Pinyin2, (CharSequence) key, true)))) {
                    }
                }
                if (!zBooleanValue) {
                    arrayList.add(worldTimeCity);
                }
            }
        }
        logP("list = " + arrayList);
        return arrayList;
    }

    public final synchronized List<WorldTimeCity> searchCities(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return searchCities(key, new Function1<WorldTimeCity, Boolean>() { // from class: com.ido.life.data.cache.WorldTimeCityManager.searchCities.1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(WorldTimeCity it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return false;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(WorldTimeCity worldTimeCity) {
                return Boolean.valueOf(invoke2(worldTimeCity));
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00a1 A[Catch: all -> 0x00c4, TryCatch #3 {, blocks: (B:3:0x0001, B:12:0x0073, B:13:0x0076, B:31:0x0093, B:33:0x00a1, B:34:0x00a9, B:40:0x00bb, B:42:0x00c0, B:43:0x00c3, B:28:0x008d), top: B:49:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00bb A[Catch: all -> 0x00c4, TRY_ENTER, TryCatch #3 {, blocks: (B:3:0x0001, B:12:0x0073, B:13:0x0076, B:31:0x0093, B:33:0x00a1, B:34:0x00a9, B:40:0x00bb, B:42:0x00c0, B:43:0x00c3, B:28:0x008d), top: B:49:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c0 A[Catch: all -> 0x00c4, TryCatch #3 {, blocks: (B:3:0x0001, B:12:0x0073, B:13:0x0076, B:31:0x0093, B:33:0x00a1, B:34:0x00a9, B:40:0x00bb, B:42:0x00c0, B:43:0x00c3, B:28:0x008d), top: B:49:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized java.util.List<com.ido.life.bean.WorldTimeCity> getCitiesByDeviceLanguage(java.lang.String r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            java.lang.String r0 = "language"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)     // Catch: java.lang.Throwable -> Lc4
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc4
            r0.<init>()     // Catch: java.lang.Throwable -> Lc4
            java.lang.String r1 = "getCitiesByDeviceLanguage language = "
            r0.append(r1)     // Catch: java.lang.Throwable -> Lc4
            r0.append(r8)     // Catch: java.lang.Throwable -> Lc4
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Lc4
            r7.logP(r0)     // Catch: java.lang.Throwable -> Lc4
            r0 = 0
            r1 = r0
            java.io.InputStreamReader r1 = (java.io.InputStreamReader) r1     // Catch: java.lang.Throwable -> Lc4
            r2 = r0
            java.io.BufferedReader r2 = (java.io.BufferedReader) r2     // Catch: java.lang.Throwable -> Lc4
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L85
            com.ido.life.util.ResourceUtils r4 = com.ido.life.util.ResourceUtils.INSTANCE     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L85
            com.ido.life.VeryFitApp r5 = com.ido.life.VeryFitApp.getApp()     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L85
            java.lang.String r6 = "VeryFitApp.getApp()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L85
            android.content.Context r5 = (android.content.Context) r5     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L85
            android.content.res.Resources r8 = r4.getResourceByLanguage(r5, r8)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L85
            r4 = 2131755008(0x7f100000, float:1.9140883E38)
            java.io.InputStream r8 = r8.openRawResource(r4)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L85
            r3.<init>(r8)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L85
            java.io.BufferedReader r8 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L7f java.lang.Throwable -> Lb7
            r1 = r3
            java.io.Reader r1 = (java.io.Reader) r1     // Catch: java.lang.Exception -> L7f java.lang.Throwable -> Lb7
            r8.<init>(r1)     // Catch: java.lang.Exception -> L7f java.lang.Throwable -> Lb7
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7c
            r1.<init>()     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7c
        L4a:
            java.lang.String r2 = r8.readLine()     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7c
            if (r2 == 0) goto L53
            r1.append(r2)     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7c
        L53:
            if (r2 != 0) goto L4a
            com.google.gson.GsonBuilder r2 = new com.google.gson.GsonBuilder     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7c
            r2.<init>()     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7c
            com.google.gson.Gson r2 = r2.create()     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7c
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7c
            com.ido.life.data.cache.WorldTimeCityManager$getCitiesByDeviceLanguage$1 r4 = new com.ido.life.data.cache.WorldTimeCityManager$getCitiesByDeviceLanguage$1     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7c
            r4.<init>()     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7c
            java.lang.reflect.Type r4 = r4.getType()     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7c
            java.lang.Object r1 = r2.fromJson(r1, r4)     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7c
            java.util.List r1 = (java.util.List) r1     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7c
            r7.citiesForDevice = r1     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7c
            r8.close()     // Catch: java.lang.Throwable -> Lc4
        L76:
            r3.close()     // Catch: java.lang.Throwable -> Lc4
            goto L93
        L7a:
            r0 = move-exception
            goto Lb9
        L7c:
            r1 = move-exception
            r2 = r8
            goto L88
        L7f:
            r1 = move-exception
            goto L88
        L81:
            r0 = move-exception
            r3 = r1
        L83:
            r8 = r2
            goto Lb9
        L85:
            r8 = move-exception
            r3 = r1
            r1 = r8
        L88:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> Lb7
            if (r2 == 0) goto L90
            r2.close()     // Catch: java.lang.Throwable -> Lc4
        L90:
            if (r3 == 0) goto L93
            goto L76
        L93:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc4
            r8.<init>()     // Catch: java.lang.Throwable -> Lc4
            java.lang.String r1 = "citiesForDevice: "
            r8.append(r1)     // Catch: java.lang.Throwable -> Lc4
            java.util.List<com.ido.life.bean.WorldTimeCity> r1 = r7.citiesForDevice     // Catch: java.lang.Throwable -> Lc4
            if (r1 == 0) goto La9
            int r0 = r1.size()     // Catch: java.lang.Throwable -> Lc4
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> Lc4
        La9:
            r8.append(r0)     // Catch: java.lang.Throwable -> Lc4
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> Lc4
            r7.logP(r8)     // Catch: java.lang.Throwable -> Lc4
            java.util.List<com.ido.life.bean.WorldTimeCity> r8 = r7.citiesForDevice     // Catch: java.lang.Throwable -> Lc4
            monitor-exit(r7)
            return r8
        Lb7:
            r0 = move-exception
            goto L83
        Lb9:
            if (r8 == 0) goto Lbe
            r8.close()     // Catch: java.lang.Throwable -> Lc4
        Lbe:
            if (r3 == 0) goto Lc3
            r3.close()     // Catch: java.lang.Throwable -> Lc4
        Lc3:
            throw r0     // Catch: java.lang.Throwable -> Lc4
        Lc4:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.data.cache.WorldTimeCityManager.getCitiesByDeviceLanguage(java.lang.String):java.util.List");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.ido.life.util.ListUtils] */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.List, java.util.List<com.ido.life.bean.WorldTimeCity>] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v8 */
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
    public final synchronized List<WorldTimeCity> getCities() {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        Exception e2;
        String line;
        List<WorldTimeCity> list;
        logP("getCities");
        ?? r0 = ListUtils.INSTANCE;
        ?? r1 = this.cities;
        if (r0.isNullOrEmpty(r1)) {
            try {
                InputStreamReader inputStreamReader2 = (InputStreamReader) null;
                BufferedReader bufferedReader2 = (BufferedReader) null;
                try {
                    VeryFitApp app = VeryFitApp.getApp();
                    Intrinsics.checkExpressionValueIsNotNull(app, "VeryFitApp.getApp()");
                    inputStreamReader = new InputStreamReader(app.getResources().openRawResource(R.raw.cities_and_time_zones));
                } catch (Exception e3) {
                    bufferedReader = bufferedReader2;
                    e2 = e3;
                    inputStreamReader = inputStreamReader2;
                } catch (Throwable th) {
                    r1 = bufferedReader2;
                    th = th;
                    inputStreamReader = inputStreamReader2;
                }
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                } catch (Exception e4) {
                    bufferedReader = bufferedReader2;
                    e2 = e4;
                } catch (Throwable th2) {
                    r1 = bufferedReader2;
                    th = th2;
                    if (r1 != 0) {
                        r1.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    throw th;
                }
                try {
                    StringBuilder sb = new StringBuilder();
                    do {
                        line = bufferedReader.readLine();
                        if (line != null) {
                            sb.append(line);
                        }
                    } while (line != null);
                    this.cities = (List) new GsonBuilder().create().fromJson(sb.toString(), new TypeToken<List<WorldTimeCity>>() { // from class: com.ido.life.data.cache.WorldTimeCityManager.getCities.1
                    }.getType());
                    if (ListUtils.INSTANCE.isNotEmpty(this.cities) && ifSortNecessary && (list = this.cities) != null && list.size() > 1) {
                        CollectionsKt.sortWith(list, new Comparator<T>() { // from class: com.ido.life.data.cache.WorldTimeCityManager$getCities$$inlined$sortBy$1
                            /* JADX WARN: Multi-variable type inference failed */
                            @Override // java.util.Comparator
                            public final int compare(T t, T t2) throws BadHanyuPinyinOutputFormatCombination {
                                String name;
                                String name2;
                                WorldTimeCity worldTimeCity = (WorldTimeCity) t;
                                if (WorldTimeCityManager.INSTANCE.isZH()) {
                                    name = PinyinUtils.convertChinese2Pinyin(worldTimeCity.getName());
                                } else {
                                    name = worldTimeCity.getName();
                                }
                                String str = name;
                                WorldTimeCity worldTimeCity2 = (WorldTimeCity) t2;
                                if (WorldTimeCityManager.INSTANCE.isZH()) {
                                    name2 = PinyinUtils.convertChinese2Pinyin(worldTimeCity2.getName());
                                } else {
                                    name2 = worldTimeCity2.getName();
                                }
                                return ComparisonsKt.compareValues(str, name2);
                            }
                        });
                    }
                    bufferedReader.close();
                } catch (Exception e5) {
                    e2 = e5;
                    e2.printStackTrace();
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStreamReader != null) {
                    }
                    return this.cities;
                }
                inputStreamReader.close();
            } catch (Throwable th3) {
                th = th3;
            }
        }
        return this.cities;
    }

    public final synchronized List<Integer> getDeviceWorldTimeCityIdsByLanguage() {
        List<Integer> deviceWorldTimeCityIds;
        try {
            deviceWorldTimeCityIds = SPHelper.getDeviceWorldTimeCityIds();
            if (ListUtils.INSTANCE.isNullOrEmpty(deviceWorldTimeCityIds) && this.citiesForDevice != null) {
                List<WorldTimeCity> list = this.citiesForDevice;
                if (list == null) {
                    Intrinsics.throwNpe();
                }
                deviceWorldTimeCityIds = getDefaultIds(list);
            }
            logP("getDeviceWorldTimeCityIdsByLanguage by ids：" + deviceWorldTimeCityIds);
        } catch (Exception e2) {
            e2.printStackTrace();
            deviceWorldTimeCityIds = null;
        }
        return deviceWorldTimeCityIds;
    }

    public final synchronized List<WorldTimeCity> getDeviceWorldTimeCities() {
        logP("getDeviceWorldTimeCities");
        if (ListUtils.INSTANCE.isNullOrEmpty(this.citiesInDevice)) {
            try {
                List<WorldTimeCity> cities = getCities();
                if (ListUtils.INSTANCE.isNullOrEmpty(cities)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("getDeviceWorldTimeCities cities  = ");
                    sb.append(cities != null ? Integer.valueOf(cities.size()) : null);
                    logP(sb.toString());
                    return null;
                }
                List<Integer> deviceWorldTimeCityIds = SPHelper.getDeviceWorldTimeCityIds();
                logP("getDeviceWorldTimeCities cityIds = " + deviceWorldTimeCityIds);
                if (cities == null) {
                    Intrinsics.throwNpe();
                }
                this.citiesInDevice = queryCitiesByIds(deviceWorldTimeCityIds, cities);
                this.hasSet2Device.set(ListUtils.INSTANCE.isNotEmpty(deviceWorldTimeCityIds));
                if (!this.hasSet2Device.get()) {
                    this.citiesInDevice = getDefaultWorldTimeCities(cities);
                } else {
                    logP("获取已设置的世界时钟...");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return this.citiesInDevice;
    }

    public final String getDefaultTimeZoneAbb() {
        TimeZone timeZone = TimeZone.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(timeZone, "TimeZone.getDefault()");
        return getTimeZoneAbb(timeZone);
    }

    public final String getTimeZoneAbb(TimeZone timeZone) {
        String abbreviation;
        Intrinsics.checkParameterIsNotNull(timeZone, "timeZone");
        WorldTimeCity worldTimeCityFindCityByTimeZone = findCityByTimeZone(timeZone, getCities());
        return (worldTimeCityFindCityByTimeZone == null || (abbreviation = worldTimeCityFindCityByTimeZone.getAbbreviation()) == null) ? "" : abbreviation;
    }

    public final WorldTimeCity getDefaultTimeZoneCityInfo() {
        TimeZone timeZone = TimeZone.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(timeZone, "TimeZone.getDefault()");
        return getTimeZoneCityInfo(timeZone);
    }

    public final WorldTimeCity getTimeZoneCityInfo(TimeZone timeZone) {
        Intrinsics.checkParameterIsNotNull(timeZone, "timeZone");
        return findCityByTimeZone(timeZone, getCities());
    }

    private final List<WorldTimeCity> getDefaultWorldTimeCities(List<WorldTimeCity> cities) {
        logP("获取默认的世界时钟...");
        ArrayList arrayListQueryCitiesByIds = queryCitiesByIds(DEFAULT_CITY_IDS, cities);
        TimeZone timeZone = TimeZone.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(timeZone, "TimeZone.getDefault()");
        WorldTimeCity worldTimeCityFindCityByTimeZone = findCityByTimeZone(timeZone, cities);
        if (worldTimeCityFindCityByTimeZone != null) {
            if (arrayListQueryCitiesByIds == null) {
                arrayListQueryCitiesByIds = new ArrayList();
            }
            if (arrayListQueryCitiesByIds == null) {
                Intrinsics.throwNpe();
            }
            if (!arrayListQueryCitiesByIds.contains(worldTimeCityFindCityByTimeZone)) {
                if (arrayListQueryCitiesByIds == null) {
                    Intrinsics.throwNpe();
                }
                arrayListQueryCitiesByIds.add(0, worldTimeCityFindCityByTimeZone);
            }
        }
        return arrayListQueryCitiesByIds;
    }

    private final List<Integer> getDefaultIds(List<WorldTimeCity> cities) {
        logP("获取默认的世界时钟id...");
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DEFAULT_CITY_IDS);
        TimeZone timeZone = TimeZone.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(timeZone, "TimeZone.getDefault()");
        WorldTimeCity worldTimeCityFindCityByTimeZone = findCityByTimeZone(timeZone, cities);
        if (worldTimeCityFindCityByTimeZone != null && !arrayList.contains(Integer.valueOf(worldTimeCityFindCityByTimeZone.getId()))) {
            arrayList.add(0, Integer.valueOf(worldTimeCityFindCityByTimeZone.getId()));
        }
        return arrayList;
    }

    private final List<WorldTimeCity> queryCitiesByIds(List<Integer> cityIds, List<WorldTimeCity> cities) {
        logP("queryCitiesByIds：" + cityIds + ", cities.size = " + cities.size());
        if (ListUtils.INSTANCE.isNullOrEmpty(cityIds)) {
            return null;
        }
        HashMap map = new HashMap();
        if (cityIds == null) {
            Intrinsics.throwNpe();
        }
        int i = 0;
        for (Object obj : cityIds) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            map.put(Integer.valueOf(((Number) obj).intValue()), Integer.valueOf(i));
            i = i2;
        }
        WorldTimeCity[] worldTimeCityArr = new WorldTimeCity[cityIds.size()];
        int length = worldTimeCityArr.length;
        logP("queryCitiesByIds indexMap = " + map + ", count = " + length);
        Iterator<WorldTimeCity> it = cities.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            WorldTimeCity next = it.next();
            if (cityIds.contains(Integer.valueOf(next.getId()))) {
                Integer num = (Integer) map.get(Integer.valueOf(next.getId()));
                if (num == null) {
                    num = -1;
                }
                Intrinsics.checkExpressionValueIsNotNull(num, "indexMap[city.id] ?: -1");
                int iIntValue = num.intValue();
                logP("queryCitiesByIds index = " + iIntValue + ", result = " + worldTimeCityArr.length + " city = " + next);
                if (iIntValue < 0) {
                    continue;
                } else {
                    worldTimeCityArr[iIntValue] = next;
                    length--;
                }
            }
            if (length == 0) {
                logP("取够了 没必要继续循环了");
                break;
            }
        }
        List<WorldTimeCity> mutableList = CollectionsKt.toMutableList((Collection) ArraysKt.filterNotNull(worldTimeCityArr));
        logP("queryCitiesByIds list = " + mutableList);
        return mutableList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateDeviceWorldTimeCities(List<WorldTimeCity> list) {
        List<WorldTimeCity> list2;
        try {
            logP("世界时钟同步成功，同步的数据：" + list);
            this.citiesInDevice = new ArrayList();
            if (ListUtils.INSTANCE.isNotEmpty(this.cities) && (list2 = this.citiesInDevice) != null) {
                List<WorldTimeCity> list3 = list;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
                Iterator<T> it = list3.iterator();
                while (it.hasNext()) {
                    arrayList.add(Integer.valueOf(((WorldTimeCity) it.next()).getId()));
                }
                ArrayList arrayList2 = arrayList;
                List<WorldTimeCity> list4 = this.cities;
                if (list4 == null) {
                    Intrinsics.throwNpe();
                }
                ArrayList arrayListQueryCitiesByIds = queryCitiesByIds(arrayList2, list4);
                if (arrayListQueryCitiesByIds == null) {
                    arrayListQueryCitiesByIds = new ArrayList();
                }
                list2.addAll(arrayListQueryCitiesByIds);
            }
            this.hasSet2Device.set(true);
            logP("世界时钟同步成功，更新缓存数据：" + this.citiesInDevice);
            List<WorldTimeCity> list5 = list;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list5, 10));
            Iterator<T> it2 = list5.iterator();
            while (it2.hasNext()) {
                arrayList3.add(Integer.valueOf(((WorldTimeCity) it2.next()).getId()));
            }
            SPHelper.setDeviceWorldTimeCityIds(arrayList3);
        } catch (Exception e2) {
            logP("世界时钟同步成功，更新缓存数据异常：" + e2);
            e2.printStackTrace();
        }
    }

    public final Pair<String, String> getSunRiseTime(double longitude, double latitude, Calendar dateTime) {
        Intrinsics.checkParameterIsNotNull(dateTime, "dateTime");
        String sunRise = SunRiseSetUtils.getSunrise(new BigDecimal(longitude), new BigDecimal(latitude), dateTime.getTime(), dateTime.getTimeZone());
        Intrinsics.checkExpressionValueIsNotNull(sunRise, "sunRise");
        List listSplit$default = StringsKt.split$default((CharSequence) sunRise, new String[]{":"}, false, 0, 6, (Object) null);
        return new Pair<>(listSplit$default.get(0), listSplit$default.get(1));
    }

    public final Pair<String, String> getSunSetTime(double longitude, double latitude, Calendar dateTime) {
        Intrinsics.checkParameterIsNotNull(dateTime, "dateTime");
        String sunSet = SunRiseSetUtils.getSunset(new BigDecimal(longitude), new BigDecimal(latitude), dateTime.getTime(), dateTime.getTimeZone());
        Intrinsics.checkExpressionValueIsNotNull(sunSet, "sunSet");
        List listSplit$default = StringsKt.split$default((CharSequence) sunSet, new String[]{":"}, false, 0, 6, (Object) null);
        return new Pair<>(listSplit$default.get(0), listSplit$default.get(1));
    }

    private final String keyCitiesInDevice() {
        return KEY_CITIES_IN_DEVICE + '_' + LocalDataManager.getCurrentDeviceInfo().mDeviceAddress;
    }
}