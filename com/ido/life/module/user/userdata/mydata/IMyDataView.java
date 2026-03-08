package com.ido.life.module.user.userdata.mydata;

import com.ido.life.base.IBaseView;
import kotlin.Metadata;

/* JADX INFO: compiled from: IMyDataView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\n\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0005H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0005H&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0005H&J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0013H&J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0005H&J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0005H&J\u0010\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0005H&J\u0018\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u0005H&¨\u0006\u001d"}, d2 = {"Lcom/ido/life/module/user/userdata/mydata/IMyDataView;", "Lcom/ido/life/base/IBaseView;", "onGetCagories", "", "cagory", "", "onGetDistance", "distance", "", "onGetHeartRate", "heartRate", "onGetOxy", "oxy", "onGetPressure", "pressure", "onGetSleepDuration", "sleepDuration", "onGetSportRecord", "sportCount", "", "onGetStep", "step", "onGetStronger", "stronger", "onGetWalkDuration", "walkDuration", "onGetWeight", "weight", "weightUnit", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IMyDataView extends IBaseView {
    void onGetCagories(int cagory);

    void onGetDistance(float distance);

    void onGetHeartRate(int heartRate);

    void onGetOxy(int oxy);

    void onGetPressure(int pressure);

    void onGetSleepDuration(int sleepDuration);

    void onGetSportRecord(long sportCount);

    void onGetStep(int step);

    void onGetStronger(int stronger);

    void onGetWalkDuration(int walkDuration);

    void onGetWeight(float weight, int weightUnit);
}