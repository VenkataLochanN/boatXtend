package com.ido.life.module.home.ambientvolume;

import com.ido.ble.event.stat.one.d;
import com.ido.life.customview.AmbientVolumePassedChartView;
import com.ido.life.customview.charter.FloatLineChartBar;
import com.ido.life.module.home.chartdetail.vertical.IBaseDetailView;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: IAmbientVolumeDetailView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u000e\bf\u0018\u000022\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0016\u0010\b\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0002H&J\u0016\u0010\u000b\u001a\u00020\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\u0002H&J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\nH&J\u0018\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H&J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0011H&J\u0018\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0011H&J\u0010\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0011H&J\u001e\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00112\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0002H&J\u0010\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0011H&J \u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0011H&J\u0016\u0010#\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0$H&J\u0018\u0010%\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u0011H&J\"\u0010'\u001a\u00020\u00052\b\u0010(\u001a\u0004\u0018\u00010\n2\u0006\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u0011H&J\u0010\u0010+\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\"\u0010,\u001a\u00020\u00052\b\u0010(\u001a\u0004\u0018\u00010\n2\u0006\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u0011H&J\"\u0010-\u001a\u00020\u00052\b\u0010(\u001a\u0004\u0018\u00010\n2\u0006\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u0011H&J\u0018\u0010.\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u0011H&J\u0010\u00101\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u00062"}, d2 = {"Lcom/ido/life/module/home/ambientvolume/IAmbientVolumeDetailView;", "Lcom/ido/life/module/home/chartdetail/vertical/IBaseDetailView;", "", "Lcom/ido/life/customview/charter/FloatLineChartBar$ChartBean;", "setAboutAmbientVolumeUIVisibility", "", "visibility", "", "setChartXLabelList", "xLabelList", "", "setChartYLabelList", "yLabelList", "setDate", "dateDesc", "setDayVolumeCompare", "todayVolume", "", "yesterdayVolume", "setDayVolumeCompareUIVisibility", "setExposureAvg", "avgVolume", "setExposureDuration", "hour", "min", "setExposureState", "exposureState", "setPassedSevenDaysChartList", "passedChartList", "Lcom/ido/life/customview/AmbientVolumePassedChartView$ChartBean;", "setPassedSevenDaysChartVisibility", "setPassedSevenDaysVolumeState", "volumeState", "setPassedSevenDaysVolumeValue", "volumeValue", "setPassedSevenDaysXLabelList", "", "setPerHourVolumeMaxmin", "max", "setVolumeHighLevelExposureInfo", "desc", d.C, "maxDuration", "setVolumeLevelExposureVisibility", "setVolumeMediumLevelExposureInfo", "setVolumeNormalLevelExposureInfo", "setWeekVolumeCompare", "currentWeekVolume", "previousWeekVolume", "setWeekVolumeCompareUIVisibility", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IAmbientVolumeDetailView extends IBaseDetailView<List<? extends FloatLineChartBar.ChartBean>, List<? extends FloatLineChartBar.ChartBean>, List<? extends FloatLineChartBar.ChartBean>, List<? extends FloatLineChartBar.ChartBean>> {
    void setAboutAmbientVolumeUIVisibility(boolean visibility);

    void setChartXLabelList(List<String> xLabelList);

    void setChartYLabelList(List<String> yLabelList);

    void setDate(String dateDesc);

    void setDayVolumeCompare(int todayVolume, int yesterdayVolume);

    void setDayVolumeCompareUIVisibility(boolean visibility);

    void setExposureAvg(int avgVolume);

    void setExposureDuration(int hour, int min);

    void setExposureState(int exposureState);

    void setPassedSevenDaysChartList(int avgVolume, List<AmbientVolumePassedChartView.ChartBean> passedChartList);

    void setPassedSevenDaysChartVisibility(boolean visibility);

    void setPassedSevenDaysVolumeState(int volumeState);

    void setPassedSevenDaysVolumeValue(int volumeValue, int hour, int min);

    void setPassedSevenDaysXLabelList(List<String> xLabelList);

    void setPerHourVolumeMaxmin(int min, int max);

    void setVolumeHighLevelExposureInfo(String desc, int duration, int maxDuration);

    void setVolumeLevelExposureVisibility(boolean visibility);

    void setVolumeMediumLevelExposureInfo(String desc, int duration, int maxDuration);

    void setVolumeNormalLevelExposureInfo(String desc, int duration, int maxDuration);

    void setWeekVolumeCompare(int currentWeekVolume, int previousWeekVolume);

    void setWeekVolumeCompareUIVisibility(boolean visibility);
}