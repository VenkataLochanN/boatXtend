package com.ido.life.module.user.set;

import com.ido.life.base.IBaseView;
import kotlin.Metadata;

/* JADX INFO: compiled from: ISettingNewView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\bH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\bH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\bH&J\b\u0010\u000f\u001a\u00020\u0003H&J\b\u0010\u0010\u001a\u00020\u0003H&J\b\u0010\u0011\u001a\u00020\u0003H&¨\u0006\u0012"}, d2 = {"Lcom/ido/life/module/user/set/ISettingNewView;", "Lcom/ido/life/base/IBaseView;", "onGetCacleData", "", "cacheData", "", "onGetMapEngineSuccess", "mapEngine", "", "onGetTimeFormatSuccess", "timeFormat", "onGetUnitSuccess", "unit", "onGetWeekStartSuccess", "weekStart", "onHistoryDataDownloadFailed", "onHistoryDataDownloadSuccess", "onHistoryDataDownloading", "app_release"}, k = 1, mv = {1, 1, 16})
public interface ISettingNewView extends IBaseView {
    void onGetCacleData(float cacheData);

    void onGetMapEngineSuccess(int mapEngine);

    void onGetTimeFormatSuccess(int timeFormat);

    void onGetUnitSuccess(int unit);

    void onGetWeekStartSuccess(int weekStart);

    void onHistoryDataDownloadFailed();

    void onHistoryDataDownloadSuccess();

    void onHistoryDataDownloading();
}