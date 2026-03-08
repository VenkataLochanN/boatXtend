package com.ido.life.module.device.presenter;

import android.text.TextUtils;
import android.util.SparseArray;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.base.BasePresenter;
import com.ido.life.bean.WorldTimeCity;
import com.ido.life.data.Func;
import com.ido.life.data.Func1;
import com.ido.life.data.cache.WorldTimeCityManager;
import com.ido.life.data.listener.Callback;
import com.ido.life.module.device.view.IWorldTimeCityChooseView;
import com.ido.life.util.ListUtils;
import com.ido.life.util.PinyinUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: WorldTimeCityChoosePresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tJ\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u0005J\"\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\fJ\u0016\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u0015\u001a\u00020\u000eJ\u0006\u0010\r\u001a\u00020\u000eJ\u001c\u0010\u0016\u001a\u00020\u00052\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\u00182\u0006\u0010\r\u001a\u00020\u000eJ#\u0010\u0019\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u001a*\b\u0012\u0004\u0012\u0002H\u001a0\u001b2\u0006\u0010\u001c\u001a\u0002H\u001a¢\u0006\u0002\u0010\u001d¨\u0006\u001f"}, d2 = {"Lcom/ido/life/module/device/presenter/WorldTimeCityChoosePresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/device/view/IWorldTimeCityChooseView;", "()V", "doSearch", "", "key", "", "mSelectedCities", "Ljava/util/ArrayList;", "Lcom/ido/life/bean/WorldTimeCity;", "generateFunc", "Lcom/ido/life/data/Func1;", "isZH", "", "getCities", "getFirstCharacter", "s", "func1", "getFirstCharacterUpperCase", AppMeasurementSdk.ConditionalUserProperty.NAME, "ifSideBarNecessary", "takeCharacter", "cities", "", "hasValue", "T", "Landroid/util/SparseArray;", "value", "(Landroid/util/SparseArray;Ljava/lang/Object;)Z", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WorldTimeCityChoosePresenter extends BasePresenter<IWorldTimeCityChooseView> {
    public static final String TAG = "WorldTimeCityChoosePresenter";
    private static final boolean ifSideBarNecessary;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final boolean isZH = LanguageUtil.isZh(IdoApp.getAppContext());

    /* JADX INFO: compiled from: WorldTimeCityChoosePresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Lcom/ido/life/module/device/presenter/WorldTimeCityChoosePresenter$Companion;", "", "()V", "TAG", "", "ifSideBarNecessary", "", "getIfSideBarNecessary", "()Z", "isZH", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isZH() {
            return WorldTimeCityChoosePresenter.isZH;
        }

        public final boolean getIfSideBarNecessary() {
            return WorldTimeCityChoosePresenter.ifSideBarNecessary;
        }
    }

    public static final /* synthetic */ IWorldTimeCityChooseView access$getView(WorldTimeCityChoosePresenter worldTimeCityChoosePresenter) {
        return worldTimeCityChoosePresenter.getView();
    }

    static {
        ifSideBarNecessary = isZH || LanguageUtil.isEn(IdoApp.getAppContext());
    }

    public final boolean ifSideBarNecessary() {
        return ifSideBarNecessary;
    }

    public final boolean isZH() {
        return isZH;
    }

    public final void getCities() {
        addAutoCancelSubscriber(new Func<List<WorldTimeCity>>() { // from class: com.ido.life.module.device.presenter.WorldTimeCityChoosePresenter.getCities.1
            @Override // com.ido.life.data.Func
            public List<WorldTimeCity> call() {
                return WorldTimeCityManager.INSTANCE.getInstance().getCities();
            }
        }, new Callback<List<WorldTimeCity>>() { // from class: com.ido.life.module.device.presenter.WorldTimeCityChoosePresenter.getCities.2
            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(List<WorldTimeCity> data) {
                if (data != null) {
                    IWorldTimeCityChooseView iWorldTimeCityChooseViewAccess$getView = WorldTimeCityChoosePresenter.access$getView(WorldTimeCityChoosePresenter.this);
                    if (iWorldTimeCityChooseViewAccess$getView != null) {
                        iWorldTimeCityChooseViewAccess$getView.onGetCitySuccess(data);
                        return;
                    }
                    return;
                }
                IWorldTimeCityChooseView iWorldTimeCityChooseViewAccess$getView2 = WorldTimeCityChoosePresenter.access$getView(WorldTimeCityChoosePresenter.this);
                if (iWorldTimeCityChooseViewAccess$getView2 != null) {
                    iWorldTimeCityChooseViewAccess$getView2.onGetCityFailed();
                }
            }

            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String errMsg) {
                Intrinsics.checkParameterIsNotNull(errMsg, "errMsg");
                IWorldTimeCityChooseView iWorldTimeCityChooseViewAccess$getView = WorldTimeCityChoosePresenter.access$getView(WorldTimeCityChoosePresenter.this);
                if (iWorldTimeCityChooseViewAccess$getView != null) {
                    iWorldTimeCityChooseViewAccess$getView.onGetCityFailed();
                }
            }
        });
    }

    public final void takeCharacter(final List<WorldTimeCity> cities, final boolean isZH2) {
        Intrinsics.checkParameterIsNotNull(cities, "cities");
        addAutoCancelSubscriber(new Func<HashMap<String, Integer>>() { // from class: com.ido.life.module.device.presenter.WorldTimeCityChoosePresenter.takeCharacter.1
            @Override // com.ido.life.data.Func
            public HashMap<String, Integer> call() {
                try {
                    HashMap<String, Integer> map = new HashMap<>();
                    int size = cities.size();
                    for (int i = 0; i < size; i++) {
                        String firstCharacterUpperCase = WorldTimeCityChoosePresenter.this.getFirstCharacterUpperCase(((WorldTimeCity) cities.get(i)).getName(), isZH2);
                        if (!map.containsKey(firstCharacterUpperCase)) {
                            map.put(firstCharacterUpperCase, Integer.valueOf(i));
                        }
                    }
                    return map;
                } catch (Exception unused) {
                    return null;
                }
            }
        }, new Callback<HashMap<String, Integer>>() { // from class: com.ido.life.module.device.presenter.WorldTimeCityChoosePresenter.takeCharacter.2
            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(HashMap<String, Integer> data) {
                if (data != null) {
                    IWorldTimeCityChooseView iWorldTimeCityChooseViewAccess$getView = WorldTimeCityChoosePresenter.access$getView(WorldTimeCityChoosePresenter.this);
                    if (iWorldTimeCityChooseViewAccess$getView != null) {
                        iWorldTimeCityChooseViewAccess$getView.onTakeCharacterSuccess(data);
                        return;
                    }
                    return;
                }
                IWorldTimeCityChooseView iWorldTimeCityChooseViewAccess$getView2 = WorldTimeCityChoosePresenter.access$getView(WorldTimeCityChoosePresenter.this);
                if (iWorldTimeCityChooseViewAccess$getView2 != null) {
                    iWorldTimeCityChooseViewAccess$getView2.onTakeCharacterFailed();
                }
            }

            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String errMsg) {
                Intrinsics.checkParameterIsNotNull(errMsg, "errMsg");
                IWorldTimeCityChooseView iWorldTimeCityChooseViewAccess$getView = WorldTimeCityChoosePresenter.access$getView(WorldTimeCityChoosePresenter.this);
                if (iWorldTimeCityChooseViewAccess$getView != null) {
                    iWorldTimeCityChooseViewAccess$getView.onTakeCharacterFailed();
                }
            }
        });
    }

    public final String getFirstCharacterUpperCase(String name, boolean isZH2) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        String firstCharacter = getFirstCharacter(name, generateFunc(isZH2));
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
        if (firstCharacter == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase = firstCharacter.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.String).toUpperCase(locale)");
        return upperCase;
    }

    public final Func1<String, String> generateFunc(final boolean isZH2) {
        return new Func1<String, String>() { // from class: com.ido.life.module.device.presenter.WorldTimeCityChoosePresenter.generateFunc.1
            @Override // com.ido.life.data.Func1
            public String call(String data) {
                Intrinsics.checkParameterIsNotNull(data, "data");
                if (isZH2 && !TextUtils.isEmpty(data)) {
                    return String.valueOf(StringsKt.first(PinyinUtils.convertChinese2Pinyin(data)));
                }
                return String.valueOf(StringsKt.first(data));
            }
        };
    }

    public final <T> boolean hasValue(SparseArray<T> hasValue, T t) {
        Intrinsics.checkParameterIsNotNull(hasValue, "$this$hasValue");
        if (t != null) {
            int size = hasValue.size();
            for (int i = 0; i < size; i++) {
                if (Intrinsics.areEqual(t, hasValue.valueAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public final String getFirstCharacter(String s, Func1<String, String> func1) {
        Intrinsics.checkParameterIsNotNull(s, "s");
        Intrinsics.checkParameterIsNotNull(func1, "func1");
        return String.valueOf(StringsKt.first(func1.call(s)));
    }

    /* JADX INFO: renamed from: com.ido.life.module.device.presenter.WorldTimeCityChoosePresenter$doSearch$1, reason: invalid class name */
    /* JADX INFO: compiled from: WorldTimeCityChoosePresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0001J\u0010\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016¨\u0006\u0005"}, d2 = {"com/ido/life/module/device/presenter/WorldTimeCityChoosePresenter$doSearch$1", "Lcom/ido/life/data/Func;", "", "Lcom/ido/life/bean/WorldTimeCity;", NotificationCompat.CATEGORY_CALL, "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class AnonymousClass1 implements Func<List<WorldTimeCity>> {
        final /* synthetic */ String $key;
        final /* synthetic */ ArrayList $mSelectedCities;
        final /* synthetic */ boolean $notEmpty;

        AnonymousClass1(String str, boolean z, ArrayList arrayList) {
            this.$key = str;
            this.$notEmpty = z;
            this.$mSelectedCities = arrayList;
        }

        @Override // com.ido.life.data.Func
        public List<WorldTimeCity> call() {
            List<WorldTimeCity> listSearchCities = WorldTimeCityManager.INSTANCE.getInstance().searchCities(this.$key, new Function1<WorldTimeCity, Boolean>() { // from class: com.ido.life.module.device.presenter.WorldTimeCityChoosePresenter$doSearch$1$call$list$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(WorldTimeCity worldTimeCity) {
                    return Boolean.valueOf(invoke2(worldTimeCity));
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(WorldTimeCity worldTimeCity) {
                    Intrinsics.checkParameterIsNotNull(worldTimeCity, "worldTimeCity");
                    if (this.this$0.$notEmpty) {
                        ArrayList arrayList = this.this$0.$mSelectedCities;
                        if (arrayList == null) {
                            Intrinsics.throwNpe();
                        }
                        if (arrayList.contains(worldTimeCity)) {
                            return true;
                        }
                    }
                    return false;
                }
            });
            CommonLogUtil.d("list：" + listSearchCities);
            return listSearchCities;
        }
    }

    public final void doSearch(String key, ArrayList<WorldTimeCity> mSelectedCities) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        addAutoCancelSubscriber(new AnonymousClass1(key, ListUtils.INSTANCE.isNotEmpty(mSelectedCities), mSelectedCities), new Callback<List<WorldTimeCity>>() { // from class: com.ido.life.module.device.presenter.WorldTimeCityChoosePresenter.doSearch.2
            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(List<WorldTimeCity> data) {
                CommonLogUtil.d(WorldTimeCityChoosePresenter.TAG, "onSuccess：" + data);
                if (data != null) {
                    IWorldTimeCityChooseView iWorldTimeCityChooseViewAccess$getView = WorldTimeCityChoosePresenter.access$getView(WorldTimeCityChoosePresenter.this);
                    if (iWorldTimeCityChooseViewAccess$getView != null) {
                        iWorldTimeCityChooseViewAccess$getView.onSearchCitySuccess(data);
                        return;
                    }
                    return;
                }
                IWorldTimeCityChooseView iWorldTimeCityChooseViewAccess$getView2 = WorldTimeCityChoosePresenter.access$getView(WorldTimeCityChoosePresenter.this);
                if (iWorldTimeCityChooseViewAccess$getView2 != null) {
                    iWorldTimeCityChooseViewAccess$getView2.onSearchCityFailed();
                }
            }

            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String errMsg) {
                Intrinsics.checkParameterIsNotNull(errMsg, "errMsg");
                IWorldTimeCityChooseView iWorldTimeCityChooseViewAccess$getView = WorldTimeCityChoosePresenter.access$getView(WorldTimeCityChoosePresenter.this);
                if (iWorldTimeCityChooseViewAccess$getView != null) {
                    iWorldTimeCityChooseViewAccess$getView.onSearchCityFailed();
                }
            }
        });
    }
}