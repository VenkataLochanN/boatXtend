package com.ido.life.module.device.presenter;

import android.util.Log;
import com.ido.life.base.BasePresenter;
import com.ido.life.data.Func;
import com.ido.life.data.cache.WorldTimeCityManager;
import com.ido.life.data.listener.Callback;
import com.ido.life.module.device.view.IWorldTimeDetailView;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WorldTimeDetailPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0003J*\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bJ\u001e\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b¨\u0006\u000f"}, d2 = {"Lcom/ido/life/module/device/presenter/WorldTimeDetailPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/device/view/IWorldTimeDetailView;", "()V", "calc", "Lkotlin/Pair;", "", "longitude", "", "latitude", "dateTime", "Ljava/util/Calendar;", "getSunRiseSetTime", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WorldTimeDetailPresenter extends BasePresenter<IWorldTimeDetailView> {
    public static final String TAG = "WorldTimeDetail";

    public static final /* synthetic */ IWorldTimeDetailView access$getView(WorldTimeDetailPresenter worldTimeDetailPresenter) {
        return worldTimeDetailPresenter.getView();
    }

    public final void getSunRiseSetTime(final double longitude, final double latitude, final Calendar dateTime) {
        Intrinsics.checkParameterIsNotNull(dateTime, "dateTime");
        addAutoCancelSubscriber(new Func<Pair<? extends String, ? extends String>>() { // from class: com.ido.life.module.device.presenter.WorldTimeDetailPresenter.getSunRiseSetTime.1
            @Override // com.ido.life.data.Func
            public Pair<? extends String, ? extends String> call() {
                try {
                    return WorldTimeDetailPresenter.this.calc(longitude, latitude, dateTime);
                } catch (Exception unused) {
                    return new Pair<>("", "");
                }
            }
        }, new Callback<Pair<? extends String, ? extends String>>() { // from class: com.ido.life.module.device.presenter.WorldTimeDetailPresenter.getSunRiseSetTime.2
            @Override // com.ido.life.data.listener.Callback
            public /* bridge */ /* synthetic */ void onSuccess(Pair<? extends String, ? extends String> pair) {
                onSuccess2((Pair<String, String>) pair);
            }

            /* JADX INFO: renamed from: onSuccess, reason: avoid collision after fix types in other method */
            public void onSuccess2(Pair<String, String> data) {
                Intrinsics.checkParameterIsNotNull(data, "data");
                IWorldTimeDetailView iWorldTimeDetailViewAccess$getView = WorldTimeDetailPresenter.access$getView(WorldTimeDetailPresenter.this);
                if (iWorldTimeDetailViewAccess$getView != null) {
                    iWorldTimeDetailViewAccess$getView.onGetSunRiseSetTimeSuccess(data);
                }
            }

            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String errMsg) {
                Intrinsics.checkParameterIsNotNull(errMsg, "errMsg");
                IWorldTimeDetailView iWorldTimeDetailViewAccess$getView = WorldTimeDetailPresenter.access$getView(WorldTimeDetailPresenter.this);
                if (iWorldTimeDetailViewAccess$getView != null) {
                    iWorldTimeDetailViewAccess$getView.onGetSunRiseSetTimeFailed();
                }
            }
        });
    }

    public final Pair<String, String> calc(double longitude, double latitude, Calendar dateTime) {
        Intrinsics.checkParameterIsNotNull(dateTime, "dateTime");
        Pair<String, String> sunRiseTime = WorldTimeCityManager.INSTANCE.getInstance().getSunRiseTime(longitude, latitude, dateTime);
        Pair<String, String> sunSetTime = WorldTimeCityManager.INSTANCE.getInstance().getSunSetTime(longitude, latitude, dateTime);
        Log.e(TAG, "sunRise = " + sunRiseTime + "，sunSet = " + sunSetTime);
        StringBuilder sb = new StringBuilder();
        sb.append(sunRiseTime.getFirst());
        sb.append(':');
        sb.append(sunRiseTime.getSecond());
        return new Pair<>(sb.toString(), sunSetTime.getFirst() + ':' + sunSetTime.getSecond());
    }
}