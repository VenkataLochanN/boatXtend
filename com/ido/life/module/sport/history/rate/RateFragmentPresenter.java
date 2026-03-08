package com.ido.life.module.sport.history.rate;

import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.base.BasePresenter;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.SportItem;
import com.ido.life.module.sport.bean.PieChartBean;
import com.ido.life.util.DateUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RateFragmentPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00162\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bJ \u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00052\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\bH\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00052\u0006\u0010\f\u001a\u00020\bJ\b\u0010\u0014\u001a\u00020\u000eH\u0002J\u000e\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011¨\u0006\u0017"}, d2 = {"Lcom/ido/life/module/sport/history/rate/RateFragmentPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/sport/history/rate/IRateView;", "()V", "getBottomLabelList", "", "", "totalSecond", "", "getChartRate", "Lcom/ido/life/bean/BaseCharBean;", "hrData", "rateMax", "getHeartRate", "", "hrStr", "sportHealth", "Lcom/ido/life/database/model/SportHealth;", "getXLableUnit", "getYLabelList", "setDefaultPieChart", "showRate", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class RateFragmentPresenter extends BasePresenter<IRateView> {
    private static final int DEFAULT_MINUTE = 60;
    private static final String TAG = "HeartFragmentPresenter";

    public final void showRate(SportHealth sportHealth) {
        Intrinsics.checkParameterIsNotNull(sportHealth, "sportHealth");
        int avgHrValue = sportHealth.getAvgHrValue();
        if (sportHealth.getHeartrate() != null) {
            SportItem heartrate = sportHealth.getHeartrate();
            Intrinsics.checkExpressionValueIsNotNull(heartrate, "sportHealth.heartrate");
            if (!TextUtils.isEmpty(heartrate.getItmes())) {
                SportItem heartrate2 = sportHealth.getHeartrate();
                Intrinsics.checkExpressionValueIsNotNull(heartrate2, "sportHealth.heartrate");
                if (heartrate2.getItmes().length() > 2) {
                    IRateView view = getView();
                    if (view == null) {
                        Intrinsics.throwNpe();
                    }
                    view.showSportItemRate(true);
                    IRateView view2 = getView();
                    if (view2 == null) {
                        Intrinsics.throwNpe();
                    }
                    view2.setXLabelList(getBottomLabelList(sportHealth.getTotalSeconds()));
                    IRateView view3 = getView();
                    if (view3 == null) {
                        Intrinsics.throwNpe();
                    }
                    view3.setXlabelUnit(getXLableUnit(sportHealth.getTotalSeconds()));
                    IRateView view4 = getView();
                    if (view4 == null) {
                        Intrinsics.throwNpe();
                    }
                    view4.setYLabelList(getYLabelList(sportHealth.getMaxHrValue()));
                    if (sportHealth.getHeartrate() != null) {
                        SportItem heartrate3 = sportHealth.getHeartrate();
                        Intrinsics.checkExpressionValueIsNotNull(heartrate3, "sportHealth.heartrate");
                        if (TextUtils.isEmpty(heartrate3.getItmes())) {
                            return;
                        }
                        SportItem heartrate4 = sportHealth.getHeartrate();
                        Intrinsics.checkExpressionValueIsNotNull(heartrate4, "sportHealth.heartrate");
                        if (heartrate4.getItmes().equals("null")) {
                            return;
                        }
                        IRateView view5 = getView();
                        if (view5 == null) {
                            Intrinsics.throwNpe();
                        }
                        SportItem heartrate5 = sportHealth.getHeartrate();
                        Intrinsics.checkExpressionValueIsNotNull(heartrate5, "sportHealth.heartrate");
                        String itmes = heartrate5.getItmes();
                        Intrinsics.checkExpressionValueIsNotNull(itmes, "sportHealth.heartrate.itmes");
                        view5.setRateList(getChartRate(itmes, sportHealth.getMaxHrValue()));
                        IRateView view6 = getView();
                        if (view6 == null) {
                            Intrinsics.throwNpe();
                        }
                        view6.setSportRateMax(String.valueOf(sportHealth.getMaxHrValue()));
                        IRateView view7 = getView();
                        if (view7 == null) {
                            Intrinsics.throwNpe();
                        }
                        view7.setSportChartRateAvg(String.valueOf(avgHrValue));
                        SportItem heartrate6 = sportHealth.getHeartrate();
                        Intrinsics.checkExpressionValueIsNotNull(heartrate6, "sportHealth.heartrate");
                        String itmes2 = heartrate6.getItmes();
                        Intrinsics.checkExpressionValueIsNotNull(itmes2, "sportHealth.heartrate.itmes");
                        getHeartRate(itmes2, sportHealth);
                        return;
                    }
                    return;
                }
            }
        }
        IRateView view8 = getView();
        if (view8 == null) {
            Intrinsics.throwNpe();
        }
        view8.showSportItemRate(false);
    }

    public final List<String> getBottomLabelList(int totalSecond) {
        String strComputeTimeMS;
        ArrayList arrayList = new ArrayList();
        for (int i = 5; 1 <= i && 5 >= i; i--) {
            int i2 = totalSecond / i;
            if (totalSecond > 3600) {
                strComputeTimeMS = DateUtil.computeTimeHM(i2);
                Intrinsics.checkExpressionValueIsNotNull(strComputeTimeMS, "DateUtil.computeTimeHM(temp.toLong())");
            } else {
                strComputeTimeMS = DateUtil.computeTimeMS(i2);
                Intrinsics.checkExpressionValueIsNotNull(strComputeTimeMS, "DateUtil.computeTimeMS(temp)");
            }
            arrayList.add(strComputeTimeMS);
        }
        return arrayList;
    }

    public final String getXLableUnit(int totalSecond) {
        if (totalSecond > 3600) {
            return LanguageUtil.getLanguageText(R.string.sport_record_chart_time_unit);
        }
        return LanguageUtil.getLanguageText(R.string.sport_record_chart_m_s_unit);
    }

    public final List<String> getYLabelList(int rateMax) {
        int i = rateMax / 3;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 <= 2; i2++) {
            if (i2 == 2) {
                arrayList.add(String.valueOf(rateMax));
            } else {
                arrayList.add(String.valueOf((i2 * i) + i));
            }
        }
        return arrayList;
    }

    private final List<BaseCharBean> getChartRate(String hrData, int rateMax) {
        int[] iArr = (int[]) GsonUtil.fromJson(hrData, int[].class);
        int length = iArr.length;
        int i = rateMax / 3;
        IRateView view = getView();
        if (view == null) {
            Intrinsics.throwNpe();
        }
        view.setRateXMinValue(0);
        IRateView view2 = getView();
        if (view2 == null) {
            Intrinsics.throwNpe();
        }
        view2.setRateXMaxValue(length);
        IRateView view3 = getView();
        if (view3 == null) {
            Intrinsics.throwNpe();
        }
        view3.setRateYMinValue(i);
        IRateView view4 = getView();
        if (view4 == null) {
            Intrinsics.throwNpe();
        }
        view4.setRateYMaxValue(rateMax);
        ArrayList arrayList = new ArrayList();
        int length2 = iArr.length;
        for (int i2 = 0; i2 < length2; i2++) {
            float f2 = iArr[i2];
            float f3 = i;
            if (f2 < f3) {
                f2 = f3;
            }
            arrayList.add(new BaseCharBean(0, i2, f2));
        }
        return arrayList;
    }

    private final void getHeartRate(String hrStr, SportHealth sportHealth) {
        int[] iArr = (int[]) GsonUtil.fromJson(hrStr, int[].class);
        if (iArr != null) {
            if (!(iArr.length == 0)) {
                int extremeSecond = sportHealth.getExtremeSecond();
                int anaerobicSecond = sportHealth.getAnaerobicSecond();
                int aerobicSeconds = sportHealth.getAerobicSeconds();
                int burnFatSeconds = sportHealth.getBurnFatSeconds();
                int warmupSeconds = sportHealth.getWarmupSeconds();
                int[] iArr2 = {ResourceUtil.getColor(R.color.color_sport_red), ResourceUtil.getColor(R.color.color_sport_orange), ResourceUtil.getColor(R.color.color_sport_yellow), ResourceUtil.getColor(R.color.color_sport_green), ResourceUtil.getColor(R.color.color_sport_cyan)};
                ArrayList arrayList = new ArrayList();
                int i = extremeSecond + 0 + anaerobicSecond + aerobicSeconds + burnFatSeconds + warmupSeconds;
                if (i > 0) {
                    float f2 = i;
                    float f3 = (extremeSecond * 100.0f) / f2;
                    float f4 = (anaerobicSecond * 100.0f) / f2;
                    float f5 = (aerobicSeconds * 100.0f) / f2;
                    float f6 = (burnFatSeconds * 100.0f) / f2;
                    float f7 = (warmupSeconds * 100.0f) / f2;
                    float f8 = 0;
                    if (f3 > f8) {
                        PieChartBean pieChartBean = new PieChartBean();
                        pieChartBean.setColor(iArr2[0]);
                        pieChartBean.setValue(f3);
                        arrayList.add(pieChartBean);
                    }
                    if (f4 > f8) {
                        PieChartBean pieChartBean2 = new PieChartBean();
                        pieChartBean2.setColor(iArr2[1]);
                        pieChartBean2.setValue(f4);
                        arrayList.add(pieChartBean2);
                    }
                    if (f5 > f8) {
                        PieChartBean pieChartBean3 = new PieChartBean();
                        pieChartBean3.setColor(iArr2[2]);
                        pieChartBean3.setValue(f5);
                        arrayList.add(pieChartBean3);
                    }
                    if (f6 > f8) {
                        PieChartBean pieChartBean4 = new PieChartBean();
                        pieChartBean4.setColor(iArr2[3]);
                        pieChartBean4.setValue(f6);
                        arrayList.add(pieChartBean4);
                    }
                    if (f7 > f8) {
                        PieChartBean pieChartBean5 = new PieChartBean();
                        pieChartBean5.setColor(iArr2[4]);
                        pieChartBean5.setValue(f7);
                        arrayList.add(pieChartBean5);
                    }
                    IRateView view = getView();
                    if (view == null) {
                        Intrinsics.throwNpe();
                    }
                    view.setHeartPieChart(arrayList);
                } else {
                    setDefaultPieChart();
                }
                CommonLogUtil.d(TAG, "getTodayLastHeartRate: " + extremeSecond + AppInfo.DELIM + anaerobicSecond + AppInfo.DELIM + aerobicSeconds + AppInfo.DELIM + burnFatSeconds + AppInfo.DELIM + warmupSeconds);
                if (extremeSecond == 0) {
                    IRateView view2 = getView();
                    if (view2 == null) {
                        Intrinsics.throwNpe();
                    }
                    view2.setMaxExerciseTime(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                } else if (1 <= extremeSecond && 60 > extremeSecond) {
                    IRateView view3 = getView();
                    if (view3 == null) {
                        Intrinsics.throwNpe();
                    }
                    view3.setMaxExerciseTime("<1");
                } else {
                    IRateView view4 = getView();
                    if (view4 == null) {
                        Intrinsics.throwNpe();
                    }
                    view4.setMaxExerciseTime(String.valueOf(extremeSecond / 60));
                }
                if (anaerobicSecond == 0) {
                    IRateView view5 = getView();
                    if (view5 == null) {
                        Intrinsics.throwNpe();
                    }
                    view5.setNoEnduranceTime(String.valueOf(anaerobicSecond / 60));
                } else if (1 <= anaerobicSecond && 60 > anaerobicSecond) {
                    IRateView view6 = getView();
                    if (view6 == null) {
                        Intrinsics.throwNpe();
                    }
                    view6.setNoEnduranceTime("<1");
                } else {
                    IRateView view7 = getView();
                    if (view7 == null) {
                        Intrinsics.throwNpe();
                    }
                    view7.setNoEnduranceTime(String.valueOf(anaerobicSecond / 60));
                }
                if (aerobicSeconds == 0) {
                    IRateView view8 = getView();
                    if (view8 == null) {
                        Intrinsics.throwNpe();
                    }
                    view8.setAerobicEnduranceTime(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                } else if (1 <= aerobicSeconds && 60 > aerobicSeconds) {
                    IRateView view9 = getView();
                    if (view9 == null) {
                        Intrinsics.throwNpe();
                    }
                    view9.setAerobicEnduranceTime("<1");
                } else {
                    IRateView view10 = getView();
                    if (view10 == null) {
                        Intrinsics.throwNpe();
                    }
                    view10.setAerobicEnduranceTime(String.valueOf(aerobicSeconds / 60));
                }
                if (burnFatSeconds == 0) {
                    IRateView view11 = getView();
                    if (view11 == null) {
                        Intrinsics.throwNpe();
                    }
                    view11.setBurningHeartRateTime(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                } else if (1 <= burnFatSeconds && 60 > burnFatSeconds) {
                    IRateView view12 = getView();
                    if (view12 == null) {
                        Intrinsics.throwNpe();
                    }
                    view12.setBurningHeartRateTime("<1");
                } else {
                    IRateView view13 = getView();
                    if (view13 == null) {
                        Intrinsics.throwNpe();
                    }
                    view13.setBurningHeartRateTime(String.valueOf(burnFatSeconds / 60));
                }
                if (warmupSeconds == 0) {
                    IRateView view14 = getView();
                    if (view14 == null) {
                        Intrinsics.throwNpe();
                    }
                    view14.setWarmUpHeartTime(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                    return;
                }
                if (1 <= warmupSeconds && 60 > warmupSeconds) {
                    IRateView view15 = getView();
                    if (view15 == null) {
                        Intrinsics.throwNpe();
                    }
                    view15.setWarmUpHeartTime("<1");
                    return;
                }
                IRateView view16 = getView();
                if (view16 == null) {
                    Intrinsics.throwNpe();
                }
                view16.setWarmUpHeartTime(String.valueOf(warmupSeconds / 60));
                return;
            }
        }
        setDefaultPieChart();
    }

    private final void setDefaultPieChart() {
        int[] iArr = {ResourceUtil.getColor(R.color.color_sport_gray)};
        ArrayList arrayList = new ArrayList();
        PieChartBean pieChartBean = new PieChartBean();
        pieChartBean.setColor(iArr[0]);
        pieChartBean.setValue(100.0f);
        arrayList.add(pieChartBean);
        IRateView view = getView();
        if (view == null) {
            Intrinsics.throwNpe();
        }
        view.setHeartPieChart(arrayList);
        IRateView view2 = getView();
        if (view2 == null) {
            Intrinsics.throwNpe();
        }
        view2.setMaxExerciseTime(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        IRateView view3 = getView();
        if (view3 == null) {
            Intrinsics.throwNpe();
        }
        view3.setNoEnduranceTime(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        IRateView view4 = getView();
        if (view4 == null) {
            Intrinsics.throwNpe();
        }
        view4.setAerobicEnduranceTime(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        IRateView view5 = getView();
        if (view5 == null) {
            Intrinsics.throwNpe();
        }
        view5.setBurningHeartRateTime(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        IRateView view6 = getView();
        if (view6 == null) {
            Intrinsics.throwNpe();
        }
        view6.setWarmUpHeartTime(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
    }
}