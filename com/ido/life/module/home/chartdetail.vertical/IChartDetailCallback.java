package com.ido.life.module.home.chartdetail.vertical;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;

/* JADX INFO: compiled from: IChartDetailCallback.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\t\u001a\u00020\u0003H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003H&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\u000f\u001a\u00020\u0003H&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\u0012\u001a\u00020\u0003H&J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\u0016\u001a\u00020\u0017H&J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0003H&J\u0018\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0003H&J\u0018\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u0003H&J\u001a\u0010 \u001a\u00020\u00172\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\"H&¨\u0006#"}, d2 = {"Lcom/ido/life/module/home/chartdetail/vertical/IChartDetailCallback;", "", "getBottomViewHeight", "", "getBottomViewHolder", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailViewHolder;", "fragment", "Landroidx/fragment/app/Fragment;", "getCalorieType", "getContentHeight", "getDateType", "getPageData", "Landroid/os/Bundle;", "offset", "getPageOffset", "getPagerTop", "getRateType", "getTipViewHolder", "getTopViewHeight", "getTopViewHolder", "getUserId", "", "hideTipUI", "", "isShow", "", "updateDateSelect", "dateType", "updatePagerHeight", "chartHeight", "updateRateType", "rateType", "updateSelectDate", "selectDate", "", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IChartDetailCallback {
    int getBottomViewHeight();

    BaseDetailViewHolder getBottomViewHolder(Fragment fragment);

    int getCalorieType(Fragment fragment);

    int getContentHeight();

    int getDateType(Fragment fragment);

    Bundle getPageData(int offset);

    int getPageOffset(Fragment fragment);

    int getPagerTop();

    int getRateType(Fragment fragment);

    BaseDetailViewHolder getTipViewHolder(Fragment fragment);

    int getTopViewHeight();

    BaseDetailViewHolder getTopViewHolder(Fragment fragment);

    long getUserId(Fragment fragment);

    void hideTipUI();

    boolean isShow(Fragment fragment);

    void updateDateSelect(Fragment fragment, int dateType);

    void updatePagerHeight(Fragment fragment, int chartHeight);

    void updateRateType(Fragment fragment, int rateType);

    void updateSelectDate(Fragment fragment, String selectDate);
}