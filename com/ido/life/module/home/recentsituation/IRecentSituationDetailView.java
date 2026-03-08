package com.ido.life.module.home.recentsituation;

import androidx.core.app.NotificationCompat;
import com.ido.life.base.IBaseView;
import com.ido.life.bean.GradientBarPoint;
import com.ido.life.customview.charter.RecentSituationWeekChart;
import com.ido.life.dialog.CommonDialog;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: IRecentSituationDetailView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\u000b\u001a\u00020\u0003H&J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u000eH&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0012H&J\u0018\u0010\u0017\u001a\u00020\u00032\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019H&J\u0016\u0010\u001b\u001a\u00020\u00032\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00120\u0019H&J\u0018\u0010\u001d\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH&J\u0016\u0010\u001e\u001a\u00020\u00032\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00120\u0019H&J\u0018\u0010\u001f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH&J\u0018\u0010 \u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u000eH&J\u0018\u0010#\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH&J\u0010\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&H&J\u0018\u0010'\u001a\u00020\u00032\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\u0019H&J\u0012\u0010)\u001a\u00020\u00032\b\u0010*\u001a\u0004\u0018\u00010\u0012H&J\u0010\u0010+\u001a\u00020\u00032\u0006\u0010,\u001a\u00020\u000eH&J\u0018\u0010-\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH&J\u0010\u0010.\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&H&J\u0018\u0010/\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH&J\u0010\u00100\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&H&J\u0018\u00101\u001a\u00020\u00032\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\u0019H&J\u0012\u00102\u001a\u00020\u00032\b\u0010*\u001a\u0004\u0018\u00010\u0012H&J\u0018\u00103\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u000eH&J\u0010\u00104\u001a\u00020\u00032\u0006\u00105\u001a\u00020\u000eH&¨\u00066"}, d2 = {"Lcom/ido/life/module/home/recentsituation/IRecentSituationDetailView;", "Lcom/ido/life/base/IBaseView;", "dismissDialog", "", "onTargetSettingFailed", "onTargetSettingSuccess", "refreshLineChart", "showAnimator", "", "refreshPastChart", "refreshRecentChart", "showDialog", "updateBarYmaxmin", "max", "", "min", "updateCompareStateDesc", "compareDesc", "", "updateCompareStateIcon", "iconResid", "updateCompareStateTitle", CommonDialog.EXTRA_TITLE, "updateLineChartData", "dataList", "", "Lcom/ido/life/customview/charter/RecentSituationWeekChart$ChartBean;", "updateLineXLabel", "labelList", "updateLineXMaxmin", "updateLineYLabel", "updateLineYMaxmin", "updatePassDayCount", "totalCount", "validCount", "updatePastBarXMaxmin", "updatePastChartAvg", "avg", "", "updatePastChartData", "Lcom/ido/life/bean/GradientBarPoint;", "updatePastChartDesc", "desc", "updateProgress", NotificationCompat.CATEGORY_PROGRESS, "updateProgressMaxmin", "updateRecentAvg", "updateRecentBarXMaxmin", "updateRecentChartAvg", "updateRecentChartData", "updateRecentChartDesc", "updateRecentDayCount", "updateTarget", "target", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IRecentSituationDetailView extends IBaseView {
    void dismissDialog();

    void onTargetSettingFailed();

    void onTargetSettingSuccess();

    void refreshLineChart(boolean showAnimator);

    void refreshPastChart(boolean showAnimator);

    void refreshRecentChart(boolean showAnimator);

    void showDialog();

    void updateBarYmaxmin(int max, int min);

    void updateCompareStateDesc(String compareDesc);

    void updateCompareStateIcon(int iconResid);

    void updateCompareStateTitle(String title);

    void updateLineChartData(List<RecentSituationWeekChart.ChartBean> dataList);

    void updateLineXLabel(List<String> labelList);

    void updateLineXMaxmin(int max, int min);

    void updateLineYLabel(List<String> labelList);

    void updateLineYMaxmin(int max, int min);

    void updatePassDayCount(int totalCount, int validCount);

    void updatePastBarXMaxmin(int max, int min);

    void updatePastChartAvg(float avg);

    void updatePastChartData(List<? extends GradientBarPoint> dataList);

    void updatePastChartDesc(String desc);

    void updateProgress(int progress);

    void updateProgressMaxmin(int max, int min);

    void updateRecentAvg(float avg);

    void updateRecentBarXMaxmin(int max, int min);

    void updateRecentChartAvg(float avg);

    void updateRecentChartData(List<? extends GradientBarPoint> dataList);

    void updateRecentChartDesc(String desc);

    void updateRecentDayCount(int totalCount, int validCount);

    void updateTarget(int target);
}