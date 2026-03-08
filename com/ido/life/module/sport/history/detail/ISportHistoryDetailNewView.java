package com.ido.life.module.sport.history.detail;

import com.ido.life.base.IBaseView;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.bean.LatLngBean;
import com.ido.life.database.model.SportHealth;
import com.ido.life.module.sport.history.KmProgress;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: ISportHistoryDetailNewView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001c\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\bE\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J2\u0010\u0002\u001a\u00020\u00032\u0010\u0010\u0004\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH&J\b\u0010\u000b\u001a\u00020\u0003H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u0018\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u0012\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010\u0018\u001a\u00020\u00032\b\u0010\u0019\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010\u001a\u001a\u00020\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010\u001c\u001a\u00020\u00032\b\u0010\u001d\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010\u001e\u001a\u00020\u00032\b\u0010\u001f\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010 \u001a\u00020\u00032\b\u0010!\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010\"\u001a\u00020\u00032\b\u0010#\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010$\u001a\u00020\u00032\b\u0010%\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010&\u001a\u00020\u00032\b\u0010'\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010(\u001a\u00020\u00032\b\u0010)\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010*\u001a\u00020\u00032\b\u0010+\u001a\u0004\u0018\u00010\u0011H&J\u001e\u0010,\u001a\u00020\u00032\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.2\u0006\u00100\u001a\u00020\u0011H&J\u001e\u00101\u001a\u00020\u00032\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.2\u0006\u00100\u001a\u00020\u0011H&J\u0010\u00102\u001a\u00020\u00032\u0006\u00103\u001a\u00020\u000eH&J\u0012\u00104\u001a\u00020\u00032\b\u00105\u001a\u0004\u0018\u00010\u0011H&J\u0012\u00106\u001a\u00020\u00032\b\u00107\u001a\u0004\u0018\u00010\u0011H&J\u0010\u00108\u001a\u00020\u00032\u0006\u00109\u001a\u00020\u000eH&J\u0010\u0010:\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u0010\u0010;\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u0018\u0010<\u001a\u00020\u00032\u0006\u0010=\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH&J\u0010\u0010>\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u0012\u0010?\u001a\u00020\u00032\b\u0010@\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010A\u001a\u00020\u00032\b\u0010B\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010C\u001a\u00020\u00032\b\u0010D\u001a\u0004\u0018\u00010\u0011H&J\u0018\u0010E\u001a\u00020\u00032\u0006\u0010F\u001a\u00020\b2\u0006\u0010G\u001a\u00020\bH&J\u0012\u0010H\u001a\u00020\u00032\b\u0010I\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010J\u001a\u00020\u00032\b\u0010K\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010L\u001a\u00020\u00032\b\u0010M\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010N\u001a\u00020\u00032\b\u0010O\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010P\u001a\u00020\u00032\b\u0010Q\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010R\u001a\u00020\u00032\b\u0010S\u001a\u0004\u0018\u00010\u0011H&J\u0010\u0010T\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u0018\u0010U\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010V\u001a\u00020\u0011H&J\u001c\u0010W\u001a\u00020\u00032\b\u0010X\u001a\u0004\u0018\u00010\u00112\b\u0010Y\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010Z\u001a\u00020\u00032\b\u0010[\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010\\\u001a\u00020\u00032\b\u0010]\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010^\u001a\u00020\u00032\b\u0010_\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010`\u001a\u00020\u00032\b\u0010a\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010b\u001a\u00020\u00032\b\u0010c\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010d\u001a\u00020\u00032\b\u0010e\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010f\u001a\u00020\u00032\b\u0010g\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010h\u001a\u00020\u00032\b\u0010i\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010j\u001a\u00020\u00032\b\u0010k\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010l\u001a\u00020\u00032\b\u0010m\u001a\u0004\u0018\u00010\u0011H&J\u0010\u0010n\u001a\u00020\u00032\u0006\u0010o\u001a\u00020\u0011H&J\u0010\u0010p\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\bH&J\u0012\u0010q\u001a\u00020\u00032\b\u0010r\u001a\u0004\u0018\u00010\u0011H&J\u0018\u0010s\u001a\u00020\u00032\u000e\u0010t\u001a\n\u0012\u0004\u0012\u00020u\u0018\u00010\u0005H&J\u0012\u0010v\u001a\u00020\u00032\b\u0010w\u001a\u0004\u0018\u00010\u0011H&J\u0010\u0010x\u001a\u00020\u00032\u0006\u0010y\u001a\u00020\bH&J\u0010\u0010z\u001a\u00020\u00032\u0006\u0010{\u001a\u00020\bH&J\u0010\u0010|\u001a\u00020\u00032\u0006\u0010}\u001a\u00020\bH&J\u0010\u0010~\u001a\u00020\u00032\u0006\u0010\u007f\u001a\u00020\bH&J\u0014\u0010\u0080\u0001\u001a\u00020\u00032\t\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u0011H&J\u0014\u0010\u0082\u0001\u001a\u00020\u00032\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0011H&J\u0014\u0010\u0084\u0001\u001a\u00020\u00032\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u0011H&J\u0014\u0010\u0086\u0001\u001a\u00020\u00032\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u0011H&J\u001a\u0010\u0088\u0001\u001a\u00020\u00032\u0007\u0010\u0089\u0001\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH&J\u0011\u0010\u008a\u0001\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u001a\u0010\u008b\u0001\u001a\u00020\u00032\u000f\u0010\u008c\u0001\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0005H&J\u001a\u0010\u008d\u0001\u001a\u00020\u00032\u000f\u0010\u008e\u0001\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0005H&J\u0012\u0010\u008f\u0001\u001a\u00020\u00032\u0007\u0010\u0090\u0001\u001a\u00020\u000eH&J\t\u0010\u0091\u0001\u001a\u00020\u0003H&J\u0014\u0010\u0092\u0001\u001a\u00020\u00032\t\u0010\u0093\u0001\u001a\u0004\u0018\u00010\u0011H&J\u0011\u0010\u0094\u0001\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u0012\u0010\u0095\u0001\u001a\u00020\u00032\u0007\u0010\u0090\u0001\u001a\u00020\u000eH&J\u0011\u0010\u0096\u0001\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u0011\u0010\u0097\u0001\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u0011\u0010\u0098\u0001\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u0011\u0010\u0099\u0001\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u0012\u0010\u009a\u0001\u001a\u00020\u00032\u0007\u0010\u009b\u0001\u001a\u00020\u000eH&J\u0012\u0010\u009c\u0001\u001a\u00020\u00032\u0007\u0010\u0090\u0001\u001a\u00020\u000eH&J\u0012\u0010\u009d\u0001\u001a\u00020\u00032\u0007\u0010\u0090\u0001\u001a\u00020\u000eH&J\u0012\u0010\u009e\u0001\u001a\u00020\u00032\u0007\u0010\u0090\u0001\u001a\u00020\u000eH&J\u0012\u0010\u009f\u0001\u001a\u00020\u00032\u0007\u0010 \u0001\u001a\u00020\u000eH&J\u0015\u0010¡\u0001\u001a\u00020\u00032\n\u0010¢\u0001\u001a\u0005\u0018\u00010£\u0001H&J\u0012\u0010¤\u0001\u001a\u00020\u00032\u0007\u0010¥\u0001\u001a\u00020\u000eH&J\u0015\u0010¦\u0001\u001a\u00020\u00032\n\u0010¢\u0001\u001a\u0005\u0018\u00010£\u0001H&J\u0012\u0010§\u0001\u001a\u00020\u00032\u0007\u0010\u0090\u0001\u001a\u00020\u000eH&J\t\u0010¨\u0001\u001a\u00020\u0003H&¨\u0006©\u0001"}, d2 = {"Lcom/ido/life/module/sport/history/detail/ISportHistoryDetailNewView;", "Lcom/ido/life/base/IBaseView;", "addPolylineAndMove", "", "mLatLngBeanList", "", "Lcom/ido/life/bean/LatLngBean;", "type", "", "durations", "distance", "hideLoading", "setBasicDataViewVisible", "visible", "", "setCalorie", "calorie", "", "drawableId", "setCalorieVisible", "setEightItemDesc", "eightDesc", "setEightItemUnit", "eightUnit", "setEightItemValue", "eightValue", "setFirstItemDesc", "firstItemDesc", "setFirstItemUnit", "firstItemUnit", "setFirstItemValue", "firstItemValue", "setFiveItemDesc", "fiveItemDesc", "setFiveItemUnit", "fiveItemUnit", "setFiveItemValue", "fiveItemValue", "setFourItemDesc", "fourItemDesc", "setFourItemUnit", "fourItemUnit", "setFourItemValue", "fourItemValue", "setKmSpace", "kmProgress", "", "Lcom/ido/life/module/sport/history/KmProgress;", "unit", "setKmSpeed", "setLoadLoadTitleShow", "loadLoadTitleShow", "setLoadTitleText", "titleText", "setNoDistanceRemind", "noDistanceRemind", "setNoDistanceRemindVisible", "noDistanceRemindVisible", "setOutDoorLayoutVisible", "setOutLocusShareLayout", "setRate", "rate", "setRateVisible", "setSecondItemDesc", "secondItemDesc", "setSecondItemUnit", "secondItemUnit", "setSecondItemValue", "secondItemValue", "setSeekBarProcess", "process", "max", "setSevenItemDesc", "sevenDesc", "setSevenItemUnit", "sevenUnit", "setSevenItemValue", "sevenValue", "setSixItemDesc", "sixItemDesc", "setSixItemUnit", "sixItemUnit", "setSixItemValue", "sixItemValue", "setSixItemVisible", "setSportCycleNoDistance", "tips", "setSportDistance", "sportDistance", "sportDistanceUnit", "setSportItemDistanceLabel", "sportItemDistanceLabel", "setSportItemPaceAverage", "sportPaceAverage", "setSportItemPaceAverageDesc", "sportItemPaceAverageDesc", "setSportItemPaceFaster", "sportItemPaceFaster", "setSportItemPaceTitleUnit", "sportItemPaceTitleUnit", "setSportItemSpeedAverage", "sportSpeedAverage", "setSportItemSpeedTitleUnit", "sportItemSpeedTitleUnit", "setSportName", "sportName", "setSportSpeedItemKm", "sportSpeedItemKm", "setSportStartTime", "startTime", "setSportTarget", "target", "setSportType", "setStepFrequencyAvg", "stepFrequencyAvg", "setStepFrequencyList", "baseCharBeans", "Lcom/ido/life/bean/BaseCharBean;", "setStepFrequencyMax", "stepFrequencyMax", "setStepXMaxValue", "xMaxValue", "setStepXMinValue", "stepXMinValue", "setStepYMaxValue", "rateYMaxValue", "setStepYMinValue", "stepYMinValue", "setTargetDiff", "targetDiff", "setThreeItemDesc", "threeItemDesc", "setThreeItemUnit", "threeItemUnit", "setThreeItemValue", "threeItemValue", "setTotalSecond", "time", "setTotalSecondVisible", "setXStepLabelList", "xLabelList", "setYStepLabelList", "yLabelList", "showFourItem", "isVisible", "showLoading", "showMessage", "message", "showRightBtn", "showSeekBarStepNum", "showSportDataItemFour", "showSportDataItemOneRight", "showSportDataItemThree", "showSportDataItemTwo", "showSportDataView", "sportDataViw", "showSportItemFrequency", "showSportItemPace", "showSportItemSpeed", "showSportNoNet", "isNet", "showSportRate", "sportHealth", "Lcom/ido/life/database/model/SportHealth;", "showSportRetryView", "retryView", "showSportTrain", "showUserTargetDiff", "toBack", "app_release"}, k = 1, mv = {1, 1, 16})
public interface ISportHistoryDetailNewView extends IBaseView {
    void addPolylineAndMove(List<? extends LatLngBean> mLatLngBeanList, int type, int durations, int distance);

    void hideLoading();

    void setBasicDataViewVisible(boolean visible);

    void setCalorie(String calorie, int drawableId);

    void setCalorieVisible(boolean visible);

    void setEightItemDesc(String eightDesc);

    void setEightItemUnit(String eightUnit);

    void setEightItemValue(String eightValue);

    void setFirstItemDesc(String firstItemDesc);

    void setFirstItemUnit(String firstItemUnit);

    void setFirstItemValue(String firstItemValue);

    void setFiveItemDesc(String fiveItemDesc);

    void setFiveItemUnit(String fiveItemUnit);

    void setFiveItemValue(String fiveItemValue);

    void setFourItemDesc(String fourItemDesc);

    void setFourItemUnit(String fourItemUnit);

    void setFourItemValue(String fourItemValue);

    void setKmSpace(List<KmProgress> kmProgress, String unit);

    void setKmSpeed(List<KmProgress> kmProgress, String unit);

    void setLoadLoadTitleShow(boolean loadLoadTitleShow);

    void setLoadTitleText(String titleText);

    void setNoDistanceRemind(String noDistanceRemind);

    void setNoDistanceRemindVisible(boolean noDistanceRemindVisible);

    void setOutDoorLayoutVisible(boolean visible);

    void setOutLocusShareLayout(boolean visible);

    void setRate(String rate, int drawableId);

    void setRateVisible(boolean visible);

    void setSecondItemDesc(String secondItemDesc);

    void setSecondItemUnit(String secondItemUnit);

    void setSecondItemValue(String secondItemValue);

    void setSeekBarProcess(int process, int max);

    void setSevenItemDesc(String sevenDesc);

    void setSevenItemUnit(String sevenUnit);

    void setSevenItemValue(String sevenValue);

    void setSixItemDesc(String sixItemDesc);

    void setSixItemUnit(String sixItemUnit);

    void setSixItemValue(String sixItemValue);

    void setSixItemVisible(boolean visible);

    void setSportCycleNoDistance(boolean visible, String tips);

    void setSportDistance(String sportDistance, String sportDistanceUnit);

    void setSportItemDistanceLabel(String sportItemDistanceLabel);

    void setSportItemPaceAverage(String sportPaceAverage);

    void setSportItemPaceAverageDesc(String sportItemPaceAverageDesc);

    void setSportItemPaceFaster(String sportItemPaceFaster);

    void setSportItemPaceTitleUnit(String sportItemPaceTitleUnit);

    void setSportItemSpeedAverage(String sportSpeedAverage);

    void setSportItemSpeedTitleUnit(String sportItemSpeedTitleUnit);

    void setSportName(String sportName);

    void setSportSpeedItemKm(String sportSpeedItemKm);

    void setSportStartTime(String startTime);

    void setSportTarget(String target);

    void setSportType(int drawableId);

    void setStepFrequencyAvg(String stepFrequencyAvg);

    void setStepFrequencyList(List<? extends BaseCharBean> baseCharBeans);

    void setStepFrequencyMax(String stepFrequencyMax);

    void setStepXMaxValue(int xMaxValue);

    void setStepXMinValue(int stepXMinValue);

    void setStepYMaxValue(int rateYMaxValue);

    void setStepYMinValue(int stepYMinValue);

    void setTargetDiff(String targetDiff);

    void setThreeItemDesc(String threeItemDesc);

    void setThreeItemUnit(String threeItemUnit);

    void setThreeItemValue(String threeItemValue);

    void setTotalSecond(String time, int drawableId);

    void setTotalSecondVisible(boolean visible);

    void setXStepLabelList(List<String> xLabelList);

    void setYStepLabelList(List<String> yLabelList);

    void showFourItem(boolean isVisible);

    void showLoading();

    void showMessage(String message);

    void showRightBtn(boolean visible);

    void showSeekBarStepNum(boolean isVisible);

    void showSportDataItemFour(boolean visible);

    void showSportDataItemOneRight(boolean visible);

    void showSportDataItemThree(boolean visible);

    void showSportDataItemTwo(boolean visible);

    void showSportDataView(boolean sportDataViw);

    void showSportItemFrequency(boolean isVisible);

    void showSportItemPace(boolean isVisible);

    void showSportItemSpeed(boolean isVisible);

    void showSportNoNet(boolean isNet);

    void showSportRate(SportHealth sportHealth);

    void showSportRetryView(boolean retryView);

    void showSportTrain(SportHealth sportHealth);

    void showUserTargetDiff(boolean isVisible);

    void toBack();
}