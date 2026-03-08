package com.ido.life.module.home.menstrualcycle;

import android.text.SpannableStringBuilder;
import com.ido.life.base.IBaseView;
import com.ido.life.module.home.menstrualcycle.MenstrualCycleDetailPresenter;
import com.ido.life.module.home.menstrualcycle.dialog.MenstrulationSettingPresenter;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: IMenstrualCycleDetailView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0016\u0010\n\u001a\u00020\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH&J\u0016\u0010\u000e\u001a\u00020\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H&J\b\u0010\u0012\u001a\u00020\u0003H&J\b\u0010\u0013\u001a\u00020\u0003H&J\b\u0010\u0014\u001a\u00020\u0003H&J\b\u0010\u0015\u001a\u00020\u0003H&J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018H&J\u001a\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH&¨\u0006\u001d"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/IMenstrualCycleDetailView;", "Lcom/ido/life/base/IBaseView;", "dataLoadFailed", "", "dataLoadSuccess", "dataLoading", "menstruationAvgLength", "length", "", "menstruationCycleAvg", "onGetDataSuccess", "dataList", "", "Lcom/ido/life/module/home/menstrualcycle/dialog/MenstrulationSettingPresenter$SettingBean;", "setHistoryMenstruation", "historyList", "", "Lcom/ido/life/module/home/menstrualcycle/MenstrualCycleDetailPresenter$HistoryMenstruationItemBean;", "startSyncToDevice", "syncToDeviceDisconnect", "syncToDeviceFailed", "syncToDeviceSuccess", "updateDeviceConnectState", "connected", "", "updateMensDesc", "color", "desc", "Landroid/text/SpannableStringBuilder;", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IMenstrualCycleDetailView extends IBaseView {
    void dataLoadFailed();

    void dataLoadSuccess();

    void dataLoading();

    void menstruationAvgLength(int length);

    void menstruationCycleAvg(int length);

    void onGetDataSuccess(List<MenstrulationSettingPresenter.SettingBean> dataList);

    void setHistoryMenstruation(List<MenstrualCycleDetailPresenter.HistoryMenstruationItemBean> historyList);

    void startSyncToDevice();

    void syncToDeviceDisconnect();

    void syncToDeviceFailed();

    void syncToDeviceSuccess();

    void updateDeviceConnectState(boolean connected);

    void updateMensDesc(int color, SpannableStringBuilder desc);
}