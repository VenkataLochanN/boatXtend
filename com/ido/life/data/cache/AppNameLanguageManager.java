package com.ido.life.data.cache;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.CanDownLangInfoV3;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.Units;
import com.ido.life.base.BaseMessage;
import com.ido.life.bean.Language;
import com.ido.life.ble.BaseDeviceInfoCallback;
import com.ido.life.constants.Constants;
import com.ido.life.constants.LanguageCodeHelper;
import com.ido.life.data.cache.listener.OnLanguageChangedListener;
import com.ido.life.util.CoroutinesUtils;
import com.ido.life.util.LanguageManager;
import com.ido.life.util.SPHelper;
import com.ido.life.util.WeatherHelper;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProducerScope;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: AppNameLanguageManager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \"2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\"#B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0006\u0010\u0010\u001a\u00020\u0006J\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\u0006\u0010\u0012\u001a\u00020\u0006J\u0012\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u0014H\u0002J\u0006\u0010\u0016\u001a\u00020\u0006J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\rH\u0016J\u0016\u0010\u001a\u001a\u00020\r2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u001cH\u0007J\u0012\u0010\u001d\u001a\u00020\r2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u0010\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\u0006H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/ido/life/data/cache/AppNameLanguageManager;", "Lcom/ido/life/data/cache/AbsDataCacheManager;", "", "()V", "languages", "", "", "mCurrentLanguage", "mICallBack", "Lcom/ido/life/ble/BaseDeviceInfoCallback;", "onLanguageChangedListener", "Lcom/ido/life/data/cache/listener/OnLanguageChangedListener;", "addDebounce", "", "timeInMill", "", "getDeviceLanguage", "getDeviceLanguages", "getSelectedLocalLanguage", "getSupportLanguageList", "", "Lcom/ido/life/bean/Language;", "getSysLanguage", "isCloudLanguage", "", "onClear", "onDeviceLanguageChanged", "event", "Lcom/ido/life/base/BaseMessage;", "onSdkInitComplete", "macAddress", "", "updateLanguage", "use_lang", "Companion", "SingleInstanceHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class AppNameLanguageManager extends AbsDataCacheManager<Object> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AppNameLanguageManager instance = SingleInstanceHolder.INSTANCE.getINSTANCE();
    private List<Integer> languages = new ArrayList();
    private int mCurrentLanguage = -1;
    private final BaseDeviceInfoCallback mICallBack = new BaseDeviceInfoCallback() { // from class: com.ido.life.data.cache.AppNameLanguageManager$mICallBack$1
        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetCanDownloadLangInfoV3(CanDownLangInfoV3 canDownLangInfoV3) {
            ArrayList arrayList;
            this.this$0.logP("AppNameLanguageManager onGetCanDownloadLangInfoV3 ：" + canDownLangInfoV3 + "，mCurrentLanguage = " + this.this$0.mCurrentLanguage);
            if (canDownLangInfoV3 != null) {
                this.this$0.languages.clear();
                List list = this.this$0.languages;
                List<CanDownLangInfoV3.Item> list2 = canDownLangInfoV3.items_user;
                if (list2 == null) {
                    arrayList = new ArrayList();
                } else {
                    List<CanDownLangInfoV3.Item> list3 = list2;
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
                    Iterator<T> it = list3.iterator();
                    while (it.hasNext()) {
                        arrayList2.add(Integer.valueOf(((CanDownLangInfoV3.Item) it.next()).language_type));
                    }
                    arrayList = arrayList2;
                }
                list.addAll(arrayList);
                this.this$0.updateLanguage(canDownLangInfoV3.use_lang);
                return;
            }
            this.this$0.updateLanguage(2);
        }
    };
    private OnLanguageChangedListener onLanguageChangedListener;

    @JvmStatic
    public static final String convertChinese2Pinyin(String str) {
        return INSTANCE.convertChinese2Pinyin(str);
    }

    @JvmStatic
    public static final boolean isSupportChinese() {
        return INSTANCE.isSupportChinese();
    }

    public AppNameLanguageManager() {
        BLEManager.registerGetDeviceInfoCallBack(this.mICallBack);
        addDebounce(1000L);
    }

    /* JADX INFO: compiled from: AppNameLanguageManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0007J\b\u0010\n\u001a\u00020\u000bH\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lcom/ido/life/data/cache/AppNameLanguageManager$Companion;", "", "()V", "instance", "Lcom/ido/life/data/cache/AppNameLanguageManager;", "getInstance", "()Lcom/ido/life/data/cache/AppNameLanguageManager;", "convertChinese2Pinyin", "", FirebaseAnalytics.Param.CONTENT, "isSupportChinese", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        public final String convertChinese2Pinyin(String content) {
            return content;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AppNameLanguageManager getInstance() {
            return AppNameLanguageManager.instance;
        }

        @JvmStatic
        public final boolean isSupportChinese() {
            SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
            return supportFunctionInfo != null && supportFunctionInfo.lang_ch;
        }
    }

    /* JADX INFO: compiled from: AppNameLanguageManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/ido/life/data/cache/AppNameLanguageManager$SingleInstanceHolder;", "", "()V", "INSTANCE", "Lcom/ido/life/data/cache/AppNameLanguageManager;", "getINSTANCE", "()Lcom/ido/life/data/cache/AppNameLanguageManager;", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final class SingleInstanceHolder {
        public static final SingleInstanceHolder INSTANCE = new SingleInstanceHolder();
        private static final AppNameLanguageManager INSTANCE = new AppNameLanguageManager();

        private SingleInstanceHolder() {
        }

        public final AppNameLanguageManager getINSTANCE() {
            return INSTANCE;
        }
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onClear() {
        this.languages.clear();
        this.mCurrentLanguage = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Language> getSupportLanguageList() {
        return LanguageManager.getSupportLanguageList(LocalDataManager.getSupportFunctionInfo());
    }

    private final boolean isCloudLanguage() {
        SupportFunctionInfo supportFunctionInfo = WeatherHelper.getSupportFunctionInfo();
        if (supportFunctionInfo == null || !supportFunctionInfo.downloadLanguage) {
            return supportFunctionInfo != null && supportFunctionInfo.ex_table_main10_v3_get_lang_library;
        }
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onDeviceLanguageChanged(BaseMessage<Integer> event) {
        ArrayList arrayList;
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (event.getType() == 900) {
            logP("onDeviceLanguageChanged，language = " + event.getData() + "，mCurrentLanguage = " + this.mCurrentLanguage);
            Integer newLanguage = event.getData();
            boolean z = true;
            if (isCloudLanguage()) {
                logP("云端语言");
                if ((!this.languages.isEmpty()) && !this.languages.contains(newLanguage) && this.mCurrentLanguage != -1) {
                    logP("无效语言：" + newLanguage + ",    使用设备当前语言:" + this.mCurrentLanguage);
                    newLanguage = Integer.valueOf(this.mCurrentLanguage);
                }
            } else {
                logP("内置语言");
                List<Language> supportLanguageList = getSupportLanguageList();
                if (supportLanguageList != null) {
                    List<Language> list = supportLanguageList;
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    for (Language language : list) {
                        arrayList2.add(Integer.valueOf(language != null ? language.getCode() : -1));
                    }
                    arrayList = arrayList2;
                } else {
                    arrayList = null;
                }
                ArrayList arrayList3 = arrayList;
                if (arrayList3 != null && !arrayList3.isEmpty()) {
                    z = false;
                }
                if (z || !arrayList.contains(newLanguage)) {
                    newLanguage = 2;
                }
            }
            Intrinsics.checkExpressionValueIsNotNull(newLanguage, "newLanguage");
            updateLanguage(newLanguage.intValue());
        }
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onSdkInitComplete(String macAddress) {
        logP("delay 4000s to getCanDownloadLangInfoV3");
        CoroutinesUtils.INSTANCE.delay(4000L, new Function0<Unit>() { // from class: com.ido.life.data.cache.AppNameLanguageManager.onSdkInitComplete.1
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
                ArrayList arrayList;
                boolean z = WeatherHelper.getSupportFunctionInfo().ex_table_main10_v3_get_lang_library;
                AppNameLanguageManager.this.logP("It's time to syncWorldTimeToDevice, support = " + z);
                if (z) {
                    BLEManager.getCanDownloadLangInfoV3();
                    return;
                }
                AppNameLanguageManager.this.logP("内置语言");
                int selectedLocalLanguage = AppNameLanguageManager.this.getSelectedLocalLanguage();
                List supportLanguageList = AppNameLanguageManager.this.getSupportLanguageList();
                if (supportLanguageList != null) {
                    List<Language> list = supportLanguageList;
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    for (Language language : list) {
                        arrayList2.add(Integer.valueOf(language != null ? language.getCode() : -1));
                    }
                    arrayList = arrayList2;
                } else {
                    arrayList = null;
                }
                ArrayList arrayList3 = arrayList;
                if ((arrayList3 == null || arrayList3.isEmpty()) || !arrayList.contains(Integer.valueOf(selectedLocalLanguage))) {
                    AppNameLanguageManager.this.logP("使用默认语言：2");
                    selectedLocalLanguage = 2;
                }
                AppNameLanguageManager.this.updateLanguage(selectedLocalLanguage);
            }
        });
    }

    public final int getSelectedLocalLanguage() {
        if (SPHelper.isLanguageFollowSys()) {
            return getSysLanguage();
        }
        Units units = LocalDataManager.getUnits();
        return units != null ? units.language : getSysLanguage();
    }

    public final int getSysLanguage() {
        return LanguageCodeHelper.getDeviceLanguageCode();
    }

    /* JADX INFO: renamed from: getDeviceLanguage, reason: from getter */
    public final int getMCurrentLanguage() {
        return this.mCurrentLanguage;
    }

    public final List<Integer> getDeviceLanguages() {
        return this.languages;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateLanguage(int use_lang) {
        if (this.mCurrentLanguage != use_lang) {
            this.mCurrentLanguage = use_lang;
            OnLanguageChangedListener onLanguageChangedListener = this.onLanguageChangedListener;
            if (onLanguageChangedListener != null) {
                onLanguageChangedListener.onLanguageChanged(use_lang);
                return;
            }
            return;
        }
        logP("语言未变化:" + this.mCurrentLanguage);
    }

    private final void addDebounce(long timeInMill) {
        CoroutinesUtils.INSTANCE.debounce(timeInMill, new Function1<ProducerScope<? super Integer>, Unit>() { // from class: com.ido.life.data.cache.AppNameLanguageManager.addDebounce.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ProducerScope<? super Integer> producerScope) {
                invoke2(producerScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final ProducerScope<? super Integer> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                AppNameLanguageManager.this.onLanguageChangedListener = new OnLanguageChangedListener() { // from class: com.ido.life.data.cache.AppNameLanguageManager.addDebounce.1.1
                    @Override // com.ido.life.data.cache.listener.OnLanguageChangedListener
                    public void onLanguageChanged(int language) {
                        AppNameLanguageManager.this.logP("onLanguageChanged language = " + language);
                        it.offer(Integer.valueOf(language));
                    }
                };
            }
        }, new Function0<Unit>() { // from class: com.ido.life.data.cache.AppNameLanguageManager.addDebounce.2
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
                AppNameLanguageManager.this.onLanguageChangedListener = (OnLanguageChangedListener) null;
            }
        }, new Function1<Throwable, Unit>() { // from class: com.ido.life.data.cache.AppNameLanguageManager.addDebounce.3
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
                AppNameLanguageManager.this.logP("onLanguageChanged error：" + it);
            }
        }, new Function1<Integer, Unit>() { // from class: com.ido.life.data.cache.AppNameLanguageManager.addDebounce.4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                AppNameLanguageManager.this.logP("notify language changed：" + i);
                EventBusHelper.postSticky(new BaseMessage(Constants.EventConstants.EVENT_DEVICE_LANGUAGE_CHANGED, Integer.valueOf(i)));
            }
        });
    }
}