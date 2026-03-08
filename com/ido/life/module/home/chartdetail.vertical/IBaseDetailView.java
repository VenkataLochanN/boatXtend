package com.ido.life.module.home.chartdetail.vertical;

import com.ido.life.base.IBaseView;

/* JADX INFO: loaded from: classes2.dex */
public interface IBaseDetailView<Day, Week, Month, Year> extends IBaseView {
    void clearCache();

    void dismissLoading();

    int getPageType();

    void onBottomViewRefresh();

    void onDetailLoadFailed();

    void onLoadSuccessByDay(boolean z, Day day);

    void onLoadSuccessByMonth(boolean z, Month month);

    void onLoadSuccessByWeek(boolean z, Week week);

    void onLoadSuccessByYear(boolean z, Year year);

    void onTopViewRefresh();

    void setXMaxValue(int i);

    void setXMinValue(int i);

    void showLoadFailedView();

    void showLoadSuccessView();

    void showLoading();

    void showLoadingView();
}