package com.ido.life.util;

import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.data.manage.database.HealthActivity;
import com.ido.ble.data.manage.database.HealthActivityV3;
import com.ido.ble.data.manage.database.HealthGpsItemV3;
import com.ido.ble.data.manage.database.HealthGpsV3;
import com.ido.ble.data.manage.database.HealthHeartRate;
import com.ido.ble.data.manage.database.HealthHeartRateItem;
import com.ido.ble.data.manage.database.HealthHeartRateSecond;
import com.ido.ble.data.manage.database.HealthHeartRateSecondItem;
import com.ido.ble.data.manage.database.HealthNoise;
import com.ido.ble.data.manage.database.HealthNoiseItem;
import com.ido.ble.data.manage.database.HealthPressure;
import com.ido.ble.data.manage.database.HealthPressureItem;
import com.ido.ble.data.manage.database.HealthSleep;
import com.ido.ble.data.manage.database.HealthSleepItem;
import com.ido.ble.data.manage.database.HealthSleepV3;
import com.ido.ble.data.manage.database.HealthSleepV3Item;
import com.ido.ble.data.manage.database.HealthSpO2;
import com.ido.ble.data.manage.database.HealthSpO2Item;
import com.ido.ble.data.manage.database.HealthSport;
import com.ido.ble.data.manage.database.HealthSportItem;
import com.ido.ble.data.manage.database.HealthSportV3;
import com.ido.ble.data.manage.database.HealthSportV3Item;
import com.ido.ble.data.manage.database.HealthSwimming;
import com.ido.ble.data.manage.database.HealthSwimmingDetail;
import com.ido.ble.gps.database.HealthGps;
import com.ido.ble.gps.database.HealthGpsItem;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import com.ido.ble.protocol.model.Goal;
import com.ido.ble.protocol.model.HeartRateMeasureMode;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.WalkReminder;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.NumUtil;
import com.ido.life.bean.ValueRangePair;
import com.ido.life.constants.Constants;
import com.ido.life.database.LocalHealthDataManager;
import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.HealthVolumeData;
import com.ido.life.database.model.MultiDaysWalkTotalData;
import com.ido.life.database.model.ServerBloodOxyDayData;
import com.ido.life.database.model.ServerBloodOxyMonthData;
import com.ido.life.database.model.ServerDaysBloodOxyData;
import com.ido.life.database.model.ServerDaysSleepData;
import com.ido.life.database.model.ServerHeartRateDayData;
import com.ido.life.database.model.ServerMultiMonthBloodOxyTotalData;
import com.ido.life.database.model.ServerMultiMonthSleepTotalData;
import com.ido.life.database.model.ServerSleepDayData;
import com.ido.life.database.model.ServerSleepMonthData;
import com.ido.life.database.model.SleepDetailModel;
import com.ido.life.database.model.SportDistanceBean;
import com.ido.life.database.model.SportGps;
import com.ido.life.database.model.SportGpsData;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.SportItemPace;
import com.ido.life.database.model.SportSwimItem;
import com.ido.life.database.model.SportSwimSwolf;
import com.ido.life.database.model.StepDayData;
import com.ido.life.database.model.WalkDayData;
import com.ido.life.database.model.WalkMonthTotalData;
import com.ido.life.database.model.WalkYearTotalData;
import com.ido.life.enums.PressureEnum;
import com.ido.life.location.GpsCoordinateUtils;
import com.ido.life.log.SportLogHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes3.dex */
public class HealthDataUtil {
    private static final String TAG = "HealthDataUtil";

    public static int formatTypeByActivityType(int i) {
        if (i == 1) {
            return 2;
        }
        if (i != 2) {
            if (i != 3) {
                if (i == 4) {
                    return 2;
                }
                if (i != 5) {
                    if (i != 7 && i != 26) {
                        switch (i) {
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                                return 5;
                            default:
                                switch (i) {
                                    case 48:
                                    case 49:
                                        break;
                                    case 50:
                                    case 51:
                                        break;
                                    case 52:
                                    case 53:
                                        return 2;
                                    case 54:
                                    case 55:
                                        break;
                                    default:
                                        return 6;
                                }
                                break;
                        }
                    } else {
                        return 5;
                    }
                }
                return 4;
            }
            return 3;
        }
        return 1;
    }

    public static void saveGpsData(HealthGps healthGps, List<HealthGpsItem> list) {
    }

    public static float[] formatItems2Array(String str, int i) {
        List listAnalysisJsonArrayToList;
        float[] fArr = new float[i];
        if (!TextUtils.isEmpty(str) && (listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList(str, Integer[].class)) != null) {
            int i2 = 0;
            for (int i3 = 0; i3 < listAnalysisJsonArrayToList.size(); i3++) {
                int iIntValue = ((Integer) listAnalysisJsonArrayToList.get(i3)).intValue();
                int i4 = i3 % 4;
                i2 = i4 == 0 ? iIntValue : i2 + iIntValue;
                if (i4 == 3) {
                    int i5 = i3 / 4;
                    if (i5 > fArr.length - 1) {
                        break;
                    }
                    fArr[i5] = i2;
                }
            }
        }
        return fArr;
    }

    public static float[] formatActiveTimeItems2Array(String str, int i) {
        List listAnalysisJsonArrayToList;
        float[] fArr = new float[i];
        if (!TextUtils.isEmpty(str) && (listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList(str, Integer[].class)) != null) {
            int i2 = 0;
            for (int i3 = 0; i3 < listAnalysisJsonArrayToList.size(); i3++) {
                int iIntValue = ((Integer) listAnalysisJsonArrayToList.get(i3)).intValue();
                int i4 = i3 % 4;
                i2 = i4 == 0 ? iIntValue : i2 + iIntValue;
                if (i4 == 3) {
                    int i5 = i3 / 4;
                    if (i5 > fArr.length - 1) {
                        break;
                    }
                    fArr[i5] = Math.round(i2 / 60.0f);
                }
            }
        }
        return fArr;
    }

    public static int[] formatStepItems2Array(String str, int i) {
        int[] iArr = new int[i];
        if (str != null) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i2 = 0; i2 < Math.min(jSONArray.length(), i); i2++) {
                    iArr[i2] = jSONArray.getInt(i2);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return iArr;
    }

    public static List<ValueRangePair> formatItems2ValuePairList(String str, int i) {
        ValueRangePair[] valueRangePairArr = new ValueRangePair[i];
        if (str != null) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                if (jSONArray.length() > 0) {
                    for (int i2 = 0; i2 < Math.min(valueRangePairArr.length, jSONArray.length()); i2++) {
                        JSONArray jSONArray2 = jSONArray.getJSONArray(i2);
                        if (jSONArray2 != null && jSONArray2.length() == 3) {
                            valueRangePairArr[i2] = new ValueRangePair(jSONArray2.getInt(2), jSONArray2.getInt(1));
                        } else {
                            valueRangePairArr[i2] = new ValueRangePair();
                        }
                    }
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return new ArrayList(Arrays.asList(valueRangePairArr));
    }

    public static SleepDetailModel formatSleepData(ServerSleepDayData serverSleepDayData) {
        if (serverSleepDayData == null) {
            return null;
        }
        int[] iArrYearMonthDay = DateUtil.yearMonthDay(serverSleepDayData.getDate());
        SleepDetailModel sleepDetailModel = new SleepDetailModel();
        if (iArrYearMonthDay != null && iArrYearMonthDay.length == 3) {
            sleepDetailModel.year = iArrYearMonthDay[0];
            sleepDetailModel.month = iArrYearMonthDay[1];
            sleepDetailModel.day = iArrYearMonthDay[2];
        }
        Date dateString2Date = DateUtil.string2Date(serverSleepDayData.getEndTime(), "yyyy-MM-dd HH:mm:ss");
        sleepDetailModel.sleepEndedTimeH = dateString2Date.getHours();
        sleepDetailModel.sleepEndedTimeM = dateString2Date.getMinutes();
        sleepDetailModel.totalSleepMinutes = serverSleepDayData.getTotalSeconds() / 60;
        sleepDetailModel.lightSleepMinutes = serverSleepDayData.getLightlySeconds() / 60;
        sleepDetailModel.deepSleepMinutes = serverSleepDayData.getDeeplySeconds() / 60;
        sleepDetailModel.awakeSleepMinutes = serverSleepDayData.getAwakeSeconds() / 60;
        sleepDetailModel.eyeMovementSleepMinutes = serverSleepDayData.getEyeMovementSeconds() / 60;
        return sleepDetailModel;
    }

    public static List<HealthSleepItem> formatSleepItems(String str) {
        List<int[]> listAnalysisJsonArrayToList;
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str) && (listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList(str, int[][].class)) != null) {
            for (int[] iArr : listAnalysisJsonArrayToList) {
                if (iArr != null && iArr.length == 2) {
                    HealthSleepItem healthSleepItem = new HealthSleepItem();
                    healthSleepItem.offsetMinute = iArr[0];
                    healthSleepItem.sleepStatus = iArr[1];
                    arrayList.add(healthSleepItem);
                }
            }
        }
        return arrayList;
    }

    public static void saveSportData(HealthSport healthSport, List<HealthSportItem> list) {
        int[] currentDate = DateUtil.getCurrentDate();
        if (healthSport == null || healthSport.year < currentDate[0] - 100) {
            return;
        }
        CommonLogUtil.d(TAG, "saveSportData date=" + DateUtil.format(healthSport.getDate(), "yyyy-MM-dd HH:mm:ss"));
        saveStepData(healthSport, list);
        saveCalorieData(healthSport, list);
        saveActiveTimeData(healthSport, list);
        saveDistanceData(healthSport, list);
        saveWalkData(healthSport, list);
    }

    private static void saveWalkData(HealthSport healthSport, List<HealthSportItem> list) {
        saveDayWalkData(healthSport, list);
    }

    private static void saveWalkDataV3(HealthSportV3 healthSportV3, List<HealthSportV3Item> list) {
        saveDayWalkDataV3(healthSportV3, list);
    }

    public static void saveSportV3Data(HealthSportV3 healthSportV3, List<HealthSportV3Item> list) {
        int[] currentDate = DateUtil.getCurrentDate();
        if (healthSportV3 == null || healthSportV3.year < currentDate[0] - 100) {
            return;
        }
        saveStepDataV3(healthSportV3, list);
        saveCalorieDataV3(healthSportV3, list);
        saveActiveTimeDataV3(healthSportV3, list);
        saveDistanceDataV3(healthSportV3, list);
        saveWalkDataV3(healthSportV3, list);
    }

    private static void saveDayWalkData(HealthSport healthSport, List<HealthSportItem> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        WalkDayData walkDayData = new WalkDayData();
        walkDayData.setDate(DateUtil.format(healthSport.year, healthSport.month, healthSport.day));
        WalkReminder walkReminder = LocalDataManager.getWalkReminder();
        if (walkReminder == null) {
            walkReminder = new WalkReminder();
            walkReminder.setOnOff(0);
            walkReminder.setWeeks(new boolean[]{true, true, true, true, true, true, true});
            walkReminder.setStartHour(9);
            walkReminder.setStartMinute(0);
            walkReminder.setEndHour(21);
            walkReminder.setEndMinute(0);
            walkReminder.setGoalStep(100);
        }
        walkDayData.setTargetSteps(walkReminder.getGoalStep());
        walkDayData.setStartTime(DateUtil.format(healthSport.year, healthSport.month, healthSport.day, walkReminder.getStartHour(), walkReminder.getStartMinute(), 0));
        walkDayData.setEndTime(DateUtil.format(healthSport.year, healthSport.month, healthSport.day, walkReminder.getEndHour(), walkReminder.getEndMinute(), 0));
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            walkDayData.setSourceMac(currentDeviceInfo.mDeviceAddress);
            walkDayData.setDeviceName(currentDeviceInfo.mDeviceName);
        }
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTime(healthSport.getDate());
        calendar.set(11, calendar.getActualMinimum(11));
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(12, healthSport.getTimeSpace() * list.size());
        walkDayData.setTimestamp(calendar.getTimeInMillis());
        int[] iArr = new int[24];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            HealthSportItem healthSportItem = list.get(i3);
            if (healthSportItem != null) {
                int stepCount = healthSportItem.getStepCount();
                int i4 = i3 % 4;
                if (i4 != 0) {
                    stepCount += i;
                }
                if (i4 == 3 || i3 == list.size() - 1) {
                    int i5 = i3 / 4;
                    if (i5 > iArr.length - 1) {
                        break;
                    }
                    iArr[i5] = stepCount;
                    if (stepCount >= walkReminder.getGoalStep()) {
                        i2++;
                    }
                }
                i = stepCount;
            }
        }
        walkDayData.setItems(GsonUtil.toJson(iArr));
        walkDayData.setReachSeconds(i2 * 60 * 60);
        LocalHealthDataManager.getInstance().saveWalkDayData(walkDayData);
    }

    private static void saveDayWalkDataV3(HealthSportV3 healthSportV3, List<HealthSportV3Item> list) {
        WalkDayData walkDayData = new WalkDayData();
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(1, healthSportV3.year);
        calendar.set(2, healthSportV3.month);
        calendar.set(5, 1);
        calendar.set(11, healthSportV3.hour);
        calendar.set(12, healthSportV3.minute);
        calendar.set(13, healthSportV3.second);
        calendar.add(2, -1);
        calendar.set(5, healthSportV3.day);
        if (list != null) {
            calendar.add(12, healthSportV3.per_minute * list.size());
        }
        walkDayData.setTimestamp(calendar.getTimeInMillis());
        walkDayData.setDate(DateUtil.format(healthSportV3.year, healthSportV3.month, healthSportV3.day));
        WalkReminder walkReminder = LocalDataManager.getWalkReminder();
        if (walkReminder == null) {
            walkReminder = new WalkReminder();
            walkReminder.setOnOff(0);
            walkReminder.setWeeks(new boolean[]{true, true, true, true, true, true, true});
            walkReminder.setStartHour(9);
            walkReminder.setStartMinute(0);
            walkReminder.setEndHour(21);
            walkReminder.setEndMinute(0);
            walkReminder.setGoalStep(100);
        }
        walkDayData.setTargetSteps(walkReminder.getGoalStep());
        walkDayData.setStartTime(DateUtil.format(healthSportV3.year, healthSportV3.month, healthSportV3.day, walkReminder.getStartHour(), walkReminder.getStartMinute(), 0));
        walkDayData.setEndTime(DateUtil.format(healthSportV3.year, healthSportV3.month, healthSportV3.day, walkReminder.getEndHour(), walkReminder.getEndMinute(), 0));
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            walkDayData.setSourceMac(currentDeviceInfo.mDeviceAddress);
            walkDayData.setDeviceName(currentDeviceInfo.mDeviceName);
        }
        int[] iArr = new int[24];
        if (list != null) {
            int i = 0;
            for (int i2 = 0; i2 < list.size(); i2++) {
                HealthSportV3Item healthSportV3Item = list.get(i2);
                if (healthSportV3Item != null) {
                    int i3 = healthSportV3Item.step_count;
                    int i4 = i2 % 4;
                    if (i4 != 0) {
                        i3 += i;
                    }
                    if (i4 == 3 || i2 == list.size() - 1) {
                        int i5 = i2 / 4;
                        if (i5 > iArr.length - 1) {
                            break;
                        } else {
                            iArr[i5] = i3;
                        }
                    }
                    i = i3;
                }
            }
        }
        walkDayData.setItems(GsonUtil.toJson(iArr));
        walkDayData.setReachSeconds(healthSportV3.walk_goal_time * 60 * 60);
        LocalHealthDataManager.getInstance().saveWalkDayData(walkDayData);
    }

    public static void calculateWalkData(Date date) {
        calculateMultiDaysWalkData(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getMondayOfWeek(date)), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getSundayOfNextWeek(date)), false);
        Date sundayOfWeek = DateUtil.getSundayOfWeek(date);
        calculateMultiDaysWalkData(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, sundayOfWeek), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getSpecifiedDayBefore(sundayOfWeek, -6)), false);
        Date saturdayOfLastWeek = DateUtil.getSaturdayOfLastWeek(date);
        calculateMultiDaysWalkData(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, saturdayOfLastWeek), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getSpecifiedDayBefore(saturdayOfLastWeek, -6)), false);
        calculateMultiDaysWalkData(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getFirstDayOfMonth(date)), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getLastDayOfMonth(date)), true);
        calculateYearWalkData(DateUtil.yearMonthDay(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, date))[0]);
    }

    public static void calculateMultiDaysWalkData(String str, String str2, boolean z) {
        MultiDaysWalkTotalData multiDaysWalkTotalData = LocalHealthDataManager.getInstance().getMultiDaysWalkTotalData(str, str2);
        if (multiDaysWalkTotalData == null) {
            multiDaysWalkTotalData = new MultiDaysWalkTotalData();
            multiDaysWalkTotalData.setStartDate(str);
            multiDaysWalkTotalData.setEndDate(str2);
        }
        List<WalkDayData> walkDayDataList = LocalHealthDataManager.getInstance().getWalkDayDataList(str, str2, z);
        if (walkDayDataList == null || walkDayDataList.isEmpty()) {
            return;
        }
        int reachSeconds = 0;
        int i = 0;
        for (WalkDayData walkDayData : walkDayDataList) {
            if (walkDayData != null) {
                multiDaysWalkTotalData.setUserId(walkDayData.getUserId());
                reachSeconds += walkDayData.getReachSeconds();
                i++;
            }
        }
        int i2 = (reachSeconds / 60) / 60;
        multiDaysWalkTotalData.setTotalHour(i2);
        float f2 = i == 0 ? 0.0f : (i2 * 1.0f) / i;
        multiDaysWalkTotalData.setAvgHour(f2);
        LocalHealthDataManager.getInstance().saveMultiDaysWalkTotalData(multiDaysWalkTotalData);
        if (z) {
            int[] iArrYearMonthDay = DateUtil.yearMonthDay(str);
            String str3 = iArrYearMonthDay[0] + "-" + NumUtil.numberFormat(iArrYearMonthDay[1]);
            WalkMonthTotalData walkMonthTotalData = LocalHealthDataManager.getInstance().getWalkMonthTotalData(str3);
            if (walkMonthTotalData == null) {
                walkMonthTotalData = new WalkMonthTotalData();
                walkMonthTotalData.setMonth(str3);
                walkMonthTotalData.setUserId(multiDaysWalkTotalData.getUserId());
            }
            walkMonthTotalData.setAvgDayHour(f2);
            LocalHealthDataManager.getInstance().saveWalkMonthTotalData(walkMonthTotalData);
        }
    }

    public static void calculateYearWalkData(int i) {
        List<MultiDaysWalkTotalData> yearAllMonthWalkTotalDataList = LocalHealthDataManager.getInstance().getYearAllMonthWalkTotalDataList(i);
        WalkYearTotalData walkYearTotalData = LocalHealthDataManager.getInstance().getWalkYearTotalData(i);
        if (walkYearTotalData == null) {
            walkYearTotalData = new WalkYearTotalData();
            walkYearTotalData.setStartDate(i + LocalHealthDataManager.YEAR_START_DATE);
            walkYearTotalData.setEndDate(i + LocalHealthDataManager.YEAR_END_DATE);
        }
        int i2 = 0;
        float avgHour = 0.0f;
        int totalHour = 0;
        for (MultiDaysWalkTotalData multiDaysWalkTotalData : yearAllMonthWalkTotalDataList) {
            if (multiDaysWalkTotalData != null) {
                walkYearTotalData.setUserId(multiDaysWalkTotalData.getUserId());
                totalHour += multiDaysWalkTotalData.getTotalHour();
                avgHour += multiDaysWalkTotalData.getAvgHour();
                if (multiDaysWalkTotalData.getAvgHour() != 0.0f) {
                    i2++;
                }
            }
        }
        if (i2 > 0) {
            walkYearTotalData.setTotalHour(totalHour);
            walkYearTotalData.setAvgDayHour(avgHour / i2);
        }
        LocalHealthDataManager.getInstance().saveWalkYearTotalData(walkYearTotalData);
    }

    public static void calculateWalkYearData(int i) {
        for (int i2 = 1; i2 <= 12; i2++) {
            Date date = DateUtil.getDate(i, i2, 1);
            calculateMultiDaysWalkData(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, date), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getLastDayOfMonth(date)), true);
        }
        calculateYearWalkData(i);
    }

    private static void saveDistanceData(HealthSport healthSport, List<HealthSportItem> list) {
        if (healthSport == null || list == null || list.size() == 0 || healthSport.getTimeSpace() <= 0) {
            return;
        }
        long userId = RunTimeUtil.getInstance().getUserId();
        String str = DateUtil.format(healthSport.getDate(), DateUtil.DATE_FORMAT_YMD);
        SportDistanceBean sportDistanceBeanQuerySportDistanceByDate = GreenDaoUtil.querySportDistanceByDate(userId, str);
        if (sportDistanceBeanQuerySportDistanceByDate != null) {
            sportDistanceBeanQuerySportDistanceByDate.delete();
        }
        SportDistanceBean sportDistanceBean = new SportDistanceBean();
        sportDistanceBean.setUserId(userId);
        sportDistanceBean.setDate(str);
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTime(healthSport.getDate());
        calendar.set(11, calendar.getActualMinimum(11));
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(12, healthSport.getTimeSpace() * list.size());
        sportDistanceBean.setTimeStamp(calendar.getTimeInMillis());
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            sportDistanceBean.setSourceMac(currentDeviceInfo.mDeviceAddress);
            sportDistanceBean.setDeviceName(currentDeviceInfo.mDeviceName);
        }
        int[] iArr = new int[96];
        int size = list.size();
        for (int i = 0; i < size; i++) {
            HealthSportItem healthSportItem = list.get(i);
            if (healthSportItem != null) {
                iArr[i] = healthSportItem.distance;
            }
        }
        sportDistanceBean.setItems(GsonUtil.toJson(convertDistanceToHourItem(iArr, healthSport.getTimeSpace())));
        sportDistanceBean.setOrignalItems(GsonUtil.toJson(iArr));
        sportDistanceBean.setTotalDistance(healthSport.totalDistance);
        sportDistanceBean.setUploadSuccess(false);
        GreenDaoUtil.addSportDistance(sportDistanceBean);
    }

    public static List<int[]> convertCalorieToHourItem(List<Integer> list, int i) {
        if (list == null || list.size() == 0) {
            return null;
        }
        List<int[]> listGenedefaultCalorieOffeset = CalorieDayData.genedefaultCalorieOffeset();
        Iterator<Integer> it = list.iterator();
        int i2 = 0;
        int iIntValue = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            i2 += i;
            iIntValue += it.next().intValue();
            if (i2 > 1440) {
                iIntValue = 0;
                break;
            }
            if (i2 % 60 == 0 && iIntValue > 0) {
                int i3 = (i2 / 60) - 1;
                listGenedefaultCalorieOffeset.set(i3, new int[]{i3, listGenedefaultCalorieOffeset.get(i3)[1] + iIntValue});
                iIntValue = 0;
            }
        }
        if (iIntValue > 0) {
            int i4 = i2 / 60;
            listGenedefaultCalorieOffeset.set(i4, new int[]{i4, listGenedefaultCalorieOffeset.get(i4)[1] + iIntValue});
        }
        return listGenedefaultCalorieOffeset;
    }

    public static List<int[]> convertDistanceToHourItem(int[] iArr, int i) {
        if (iArr == null || iArr.length == 0) {
            return null;
        }
        List<int[]> listGenedefaultDistanceOffeset = SportDistanceBean.genedefaultDistanceOffeset();
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            i3 += i;
            i4 += iArr[i2];
            if (i3 > 1440) {
                i4 = 0;
                break;
            }
            if (i3 % 60 == 0 && i4 > 0) {
                int i5 = (i3 / 60) - 1;
                listGenedefaultDistanceOffeset.set(i5, new int[]{i5, listGenedefaultDistanceOffeset.get(i5)[1] + i4});
                i4 = 0;
            }
            i2++;
        }
        if (i4 > 0) {
            int i6 = i3 / 60;
            listGenedefaultDistanceOffeset.set(i6, new int[]{i6, listGenedefaultDistanceOffeset.get(i6)[1] + i4});
        }
        return listGenedefaultDistanceOffeset;
    }

    private static void saveDistanceDataV2(HealthActivity healthActivity) {
        if (healthActivity == null || healthActivity.getItems() == null || healthActivity.getItems().size() == 0 || healthActivity.type == 3) {
            return;
        }
        long userId = RunTimeUtil.getInstance().getUserId();
        String str = DateUtil.format(healthActivity.getDate(), DateUtil.DATE_FORMAT_YMD);
        SportDistanceBean sportDistanceBeanQuerySportDistanceByDate = GreenDaoUtil.querySportDistanceByDate(userId, str);
        List<int[]> listAnalysisJsonArrayToList = null;
        if (sportDistanceBeanQuerySportDistanceByDate == null) {
            sportDistanceBeanQuerySportDistanceByDate = new SportDistanceBean();
            sportDistanceBeanQuerySportDistanceByDate.setUserId(userId);
            sportDistanceBeanQuerySportDistanceByDate.setDate(str);
        } else if (!TextUtils.isEmpty(sportDistanceBeanQuerySportDistanceByDate.getItems())) {
            listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList(sportDistanceBeanQuerySportDistanceByDate.getItems(), int[][].class);
        }
        sportDistanceBeanQuerySportDistanceByDate.setTimeStamp(healthActivity.getDate().getTime());
        if (listAnalysisJsonArrayToList == null || listAnalysisJsonArrayToList.size() != 24) {
            listAnalysisJsonArrayToList = SportDistanceBean.genedefaultDistanceOffeset();
        }
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            sportDistanceBeanQuerySportDistanceByDate.setSourceMac(currentDeviceInfo.mDeviceAddress);
            sportDistanceBeanQuerySportDistanceByDate.setDeviceName(currentDeviceInfo.mDeviceName);
        }
        Calendar.getInstance(Locale.getDefault()).setTime(healthActivity.getDate());
        int i = (healthActivity.hour * 60 * 60) + (healthActivity.minute * 60) + healthActivity.second;
        int i2 = 0;
        int i3 = 0;
        for (HealthActivity.Item item : healthActivity.getItems()) {
            i += Constants.EventConstants.EVENT_SWITCH_LANGUAGE;
            if (i2 > 23) {
                break;
            }
            if (item.distance > 0) {
                i3 += item.distance;
                i2 = (i / 60) / 60;
                listAnalysisJsonArrayToList.set(i2, new int[]{i2, listAnalysisJsonArrayToList.get(i2)[1] + item.distance});
            }
        }
        sportDistanceBeanQuerySportDistanceByDate.setItems(GsonUtil.toJson(listAnalysisJsonArrayToList));
        sportDistanceBeanQuerySportDistanceByDate.setTotalDistance(i3);
        sportDistanceBeanQuerySportDistanceByDate.setUploadSuccess(false);
        GreenDaoUtil.addSportDistance(sportDistanceBeanQuerySportDistanceByDate);
    }

    private static void saveDistanceDataV3(HealthSportV3 healthSportV3, List<HealthSportV3Item> list) {
        if (healthSportV3 == null) {
            return;
        }
        long userId = RunTimeUtil.getInstance().getUserId();
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(1, healthSportV3.year);
        calendar.set(2, healthSportV3.month);
        calendar.set(5, 1);
        calendar.set(11, healthSportV3.hour);
        calendar.set(12, healthSportV3.minute);
        calendar.set(13, healthSportV3.second);
        calendar.add(2, -1);
        if (list != null) {
            calendar.add(12, healthSportV3.per_minute * list.size());
        }
        calendar.set(5, healthSportV3.day);
        String str = DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD);
        SportDistanceBean sportDistanceBeanQuerySportDistanceByDate = GreenDaoUtil.querySportDistanceByDate(userId, str);
        if (sportDistanceBeanQuerySportDistanceByDate != null) {
            sportDistanceBeanQuerySportDistanceByDate.delete();
        }
        SportDistanceBean sportDistanceBean = new SportDistanceBean();
        sportDistanceBean.setUserId(userId);
        sportDistanceBean.setDate(str);
        sportDistanceBean.setTimeStamp(calendar.getTimeInMillis());
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            sportDistanceBean.setSourceMac(currentDeviceInfo.mDeviceAddress);
            sportDistanceBean.setDeviceName(currentDeviceInfo.mDeviceName);
        }
        int[] iArr = new int[96];
        if (list != null && list.size() > 0) {
            int size = list.size();
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                HealthSportV3Item healthSportV3Item = list.get(i2);
                if (healthSportV3Item != null) {
                    i += healthSportV3Item.distance;
                    if (((i2 + 1) * healthSportV3.per_minute) % 15 == 0) {
                        iArr[(healthSportV3.per_minute * i2) / 15] = i;
                        i = 0;
                    }
                }
            }
        }
        sportDistanceBean.setItems(GsonUtil.toJson(convertDistanceToHourItem(iArr, healthSportV3.per_minute)));
        sportDistanceBean.setOrignalItems(GsonUtil.toJson(iArr));
        sportDistanceBean.setTotalDistance(healthSportV3.total_distances);
        sportDistanceBean.setUploadSuccess(false);
        GreenDaoUtil.addSportDistance(sportDistanceBean);
    }

    private static void saveActiveTimeData(HealthSport healthSport, List<HealthSportItem> list) {
        ActiveTimeDayData activeTimeDailyDataByDate = LocalHealthDataManager.getInstance().getActiveTimeDailyDataByDate(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, healthSport.getDate()));
        if (activeTimeDailyDataByDate != null && activeTimeDailyDataByDate.getTotalSeconds() == healthSport.getTotalActiveTime() && activeTimeDailyDataByDate.getTotalDistance() == healthSport.getTotalDistance()) {
            return;
        }
        saveDayActiveTimeData(healthSport, list);
    }

    private static void saveActiveTimeDataV3(HealthSportV3 healthSportV3, List<HealthSportV3Item> list) {
        if (healthSportV3 == null) {
            return;
        }
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(1, healthSportV3.year);
        calendar.set(2, healthSportV3.month);
        calendar.set(5, 1);
        calendar.set(11, healthSportV3.hour);
        calendar.set(12, healthSportV3.minute);
        calendar.set(13, healthSportV3.second);
        calendar.add(2, -1);
        calendar.set(5, healthSportV3.day);
        saveDayActiveTimeDataV3(healthSportV3, list);
    }

    private static void saveCalorieData(HealthSport healthSport, List<HealthSportItem> list) {
        CalorieDayData calorieDailyDataByDate = LocalHealthDataManager.getInstance().getCalorieDailyDataByDate(RunTimeUtil.getInstance().getUserId(), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, healthSport.getDate()));
        if (calorieDailyDataByDate == null || calorieDailyDataByDate.getTotalCalorie() != healthSport.getTotalCalory()) {
            saveDayCalorieData(healthSport, list);
        }
    }

    private static void saveCalorieDataV3(HealthSportV3 healthSportV3, List<HealthSportV3Item> list) {
        if (healthSportV3 == null) {
            return;
        }
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(1, healthSportV3.year);
        calendar.set(2, healthSportV3.month);
        calendar.set(5, 1);
        calendar.set(11, healthSportV3.hour);
        calendar.set(12, healthSportV3.minute);
        calendar.set(13, healthSportV3.second);
        calendar.add(2, -1);
        calendar.set(5, healthSportV3.day);
        saveDayCalorieDataV3(healthSportV3, list);
    }

    private static void saveStepData(HealthSport healthSport, List<HealthSportItem> list) {
        StepDayData stepDailyDataByDate = LocalHealthDataManager.getInstance().getStepDailyDataByDate(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, healthSport.getDate()));
        if (stepDailyDataByDate == null || stepDailyDataByDate.getNumSteps() != healthSport.getTotalStepCount()) {
            saveDayStepData(healthSport, list);
        }
    }

    private static void saveStepDataV3(HealthSportV3 healthSportV3, List<HealthSportV3Item> list) {
        if (healthSportV3 == null) {
            return;
        }
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(1, healthSportV3.year);
        calendar.set(2, healthSportV3.month);
        calendar.set(5, 1);
        calendar.set(11, healthSportV3.hour);
        calendar.set(12, healthSportV3.minute);
        calendar.set(13, healthSportV3.second);
        calendar.add(2, -1);
        calendar.set(5, healthSportV3.day);
        CommonLogUtil.d(TAG, "saveStepDataV3 date=" + DateUtil.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
        saveDayStepDataV3(healthSportV3, list);
    }

    private static void saveDayActiveTimeDataV3(HealthSportV3 healthSportV3, List<HealthSportV3Item> list) {
        try {
            ActiveTimeDayData activeTimeDayData = new ActiveTimeDayData();
            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            calendar.set(1, healthSportV3.year);
            calendar.set(2, healthSportV3.month);
            calendar.set(5, 1);
            calendar.set(11, healthSportV3.hour);
            calendar.set(12, healthSportV3.minute);
            calendar.set(13, healthSportV3.second);
            calendar.add(2, -1);
            calendar.set(5, healthSportV3.day);
            if (list != null) {
                calendar.add(12, healthSportV3.per_minute * list.size());
            }
            CommonLogUtil.d(TAG, "中高强度数据产生时间:" + DateUtil.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
            activeTimeDayData.setTimestamp(calendar.getTimeInMillis());
            activeTimeDayData.setDate(DateUtil.format(healthSportV3.year, healthSportV3.month, healthSportV3.day));
            BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
            if (currentDeviceInfo != null) {
                activeTimeDayData.setSourceMac(currentDeviceInfo.mDeviceAddress);
                activeTimeDayData.setDeviceName(currentDeviceInfo.mDeviceName);
            }
            activeTimeDayData.setTotalSeconds(healthSportV3.total_active_time * 60);
            activeTimeDayData.setTotalDistance(healthSportV3.total_distances);
            activeTimeDayData.setMediumOrHigherSeconds(healthSportV3.total_active_time * 60);
            int[] iArr = new int[96];
            if (list != null && list.size() > 0) {
                for (int i = 0; i < Math.min(iArr.length, list.size()); i++) {
                    HealthSportV3Item healthSportV3Item = list.get(i);
                    if (healthSportV3Item != null) {
                        iArr[i] = healthSportV3Item.active_time * 60;
                    }
                }
            }
            activeTimeDayData.setItems(GsonUtil.toJson(iArr));
            activeTimeDayData.setUploaded(false);
            LocalHealthDataManager.getInstance().saveActiveTimeDayData(activeTimeDayData);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void saveDayActiveTimeData(HealthSport healthSport, List<HealthSportItem> list) {
        try {
            ActiveTimeDayData activeTimeDayData = new ActiveTimeDayData();
            activeTimeDayData.setDate(DateUtil.format(healthSport.year, healthSport.month, healthSport.day));
            BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
            if (currentDeviceInfo != null) {
                activeTimeDayData.setSourceMac(currentDeviceInfo.mDeviceAddress);
                activeTimeDayData.setDeviceName(currentDeviceInfo.mDeviceName);
            }
            activeTimeDayData.setTotalSeconds(healthSport.totalActiveTime * 60);
            activeTimeDayData.setTotalDistance(healthSport.totalDistance);
            activeTimeDayData.setMediumOrHigherSeconds(healthSport.totalActiveTime * 60);
            activeTimeDayData.setTimestamp(healthSport.getDate().getTime());
            int[] iArr = new int[96];
            if (list != null && list.size() > 0) {
                int iMin = Math.min(iArr.length, list.size());
                Calendar calendar = Calendar.getInstance(Locale.getDefault());
                calendar.set(11, calendar.getActualMinimum(11));
                calendar.set(12, 0);
                calendar.set(13, 0);
                calendar.set(14, 0);
                calendar.add(12, healthSport.getTimeSpace() * iMin);
                CommonLogUtil.d(TAG, "中高强度数据产生时间:" + DateUtil.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
                activeTimeDayData.setTimestamp(calendar.getTimeInMillis());
                for (int i = 0; i < iMin; i++) {
                    HealthSportItem healthSportItem = list.get(i);
                    if (healthSportItem != null) {
                        iArr[i] = healthSportItem.activeTime * 60;
                    }
                }
            }
            activeTimeDayData.setItems(GsonUtil.toJson(iArr));
            activeTimeDayData.setUploaded(false);
            LocalHealthDataManager.getInstance().saveActiveTimeDayData(activeTimeDayData);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void saveDayCalorieData(HealthSport healthSport, List<HealthSportItem> list) {
        int i;
        try {
            CalorieDayData calorieDayData = new CalorieDayData();
            calorieDayData.setDate(DateUtil.format(healthSport.year, healthSport.month, healthSport.day));
            BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
            if (currentDeviceInfo != null) {
                calorieDayData.setSourceMac(currentDeviceInfo.mDeviceAddress);
                calorieDayData.setDeviceName(currentDeviceInfo.mDeviceName);
            }
            CalorieAndDistanceGoal calorieAndDistanceGoal = LocalDataManager.getCalorieAndDistanceGoal();
            calorieDayData.setTargetCalorie(calorieAndDistanceGoal == null ? 500 : calorieAndDistanceGoal.calorie);
            int[] iArr = new int[96];
            if (list == null || list.size() <= 0) {
                i = 0;
            } else {
                int iMin = Math.min(iArr.length, list.size());
                Calendar calendar = Calendar.getInstance(Locale.getDefault());
                calendar.setTime(healthSport.getDate());
                calendar.set(11, calendar.getActualMinimum(11));
                calendar.set(12, 0);
                calendar.set(13, 0);
                calendar.set(14, 0);
                calendar.add(12, healthSport.getTimeSpace() * iMin);
                CommonLogUtil.d(TAG, "卡路里数据产生时间:" + DateUtil.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
                calorieDayData.setTimestamp(calendar.getTimeInMillis());
                i = 0;
                for (int i2 = 0; i2 < iMin; i2++) {
                    HealthSportItem healthSportItem = list.get(i2);
                    if (healthSportItem != null) {
                        iArr[i2] = healthSportItem.calory;
                        i += healthSportItem.calory;
                    }
                }
            }
            calorieDayData.setTotalCalorie(i);
            calorieDayData.setItems(GsonUtil.toJson(iArr));
            calorieDayData.setUploaded(false);
            LocalHealthDataManager.getInstance().saveCalorieDayData(calorieDayData);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void saveDayCalorieDataV3(HealthSportV3 healthSportV3, List<HealthSportV3Item> list) {
        try {
            CalorieDayData calorieDayData = new CalorieDayData();
            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            calendar.set(1, healthSportV3.year);
            calendar.set(2, healthSportV3.month);
            calendar.set(5, 1);
            calendar.set(11, healthSportV3.hour);
            calendar.set(12, healthSportV3.minute);
            calendar.set(13, healthSportV3.second);
            calendar.add(2, -1);
            calendar.set(5, healthSportV3.day);
            if (list != null) {
                calendar.add(12, healthSportV3.per_minute * list.size());
            }
            CommonLogUtil.d(TAG, "卡路里数据产生时间:" + DateUtil.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
            calorieDayData.setTimestamp(calendar.getTimeInMillis());
            calorieDayData.setDate(DateUtil.format(healthSportV3.year, healthSportV3.month, healthSportV3.day));
            BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
            if (currentDeviceInfo != null) {
                calorieDayData.setSourceMac(currentDeviceInfo.mDeviceAddress);
                calorieDayData.setDeviceName(currentDeviceInfo.mDeviceName);
            }
            calorieDayData.setTotalCalorie(healthSportV3.total_rest_activity_calories);
            CalorieAndDistanceGoal calorieAndDistanceGoal = LocalDataManager.getCalorieAndDistanceGoal();
            calorieDayData.setTargetCalorie(calorieAndDistanceGoal == null ? 500 : calorieAndDistanceGoal.calorie);
            int[] iArr = new int[96];
            int[] iArr2 = new int[96];
            if (list != null && list.size() > 0) {
                for (int i = 0; i < Math.min(iArr.length, list.size()); i++) {
                    HealthSportV3Item healthSportV3Item = list.get(i);
                    if (healthSportV3Item != null) {
                        iArr[i] = healthSportV3Item.rest_activity_calories;
                        iArr2[i] = healthSportV3Item.activity_calories;
                    }
                }
            }
            calorieDayData.setItems(GsonUtil.toJson(iArr));
            calorieDayData.setActivityItems(GsonUtil.toJson(iArr2));
            calorieDayData.setActivityCalorie(healthSportV3.total_activity_calories);
            calorieDayData.setUploaded(false);
            LocalHealthDataManager.getInstance().saveCalorieDayData(calorieDayData);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void saveDayStepData(HealthSport healthSport, List<HealthSportItem> list) {
        int i;
        try {
            StepDayData stepDayData = new StepDayData();
            stepDayData.setDate(DateUtil.format(healthSport.year, healthSport.month, healthSport.day));
            stepDayData.setDistances(healthSport.totalDistance);
            stepDayData.setCalories(healthSport.totalCalory);
            stepDayData.setTimeOfSeconds(healthSport.getTotalActiveTime());
            int[] iArr = new int[96];
            if (healthSport.totalStepCount <= 0 || list == null || list.size() <= 0) {
                i = 0;
            } else {
                int iMin = Math.min(list.size(), iArr.length);
                Calendar calendar = Calendar.getInstance(Locale.getDefault());
                calendar.setTime(healthSport.getDate());
                calendar.set(11, calendar.getActualMinimum(11));
                calendar.set(12, 0);
                calendar.set(13, 0);
                calendar.set(14, 0);
                calendar.add(12, healthSport.getTimeSpace() * iMin);
                CommonLogUtil.d(TAG, "步数数据产生时间:" + DateUtil.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
                stepDayData.setTimeStamp(calendar.getTimeInMillis());
                i = 0;
                int i2 = 0;
                for (int i3 = 0; i3 < iMin; i3++) {
                    HealthSportItem healthSportItem = list.get(i3);
                    if (healthSportItem != null) {
                        int stepCount = healthSportItem.getStepCount();
                        iArr[i3] = stepCount;
                        i2 = i3 % 4 == 0 ? stepCount : i2 + stepCount;
                        if ((i3 % 4 == 3 || i3 == iMin - 1) && i2 > i) {
                            i = i2;
                        }
                    }
                }
            }
            stepDayData.setItems(GsonUtil.toJson(iArr));
            BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
            if (currentDeviceInfo != null) {
                stepDayData.setSourceMac(currentDeviceInfo.mDeviceAddress);
                stepDayData.setDeviceName(currentDeviceInfo.mDeviceName);
            }
            stepDayData.setNumSteps(healthSport.totalStepCount);
            stepDayData.setEffectiveSteps(healthSport.totalStepCount);
            Goal goal = LocalDataManager.getGoal();
            stepDayData.setTargetSteps((goal == null || goal.sport_step == 0) ? 10000 : goal.sport_step);
            stepDayData.setMaxNumSteps(i);
            stepDayData.setUploaded(false);
            LocalHealthDataManager.getInstance().saveStepDayData(stepDayData);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void saveDayStepDataV3(HealthSportV3 healthSportV3, List<HealthSportV3Item> list) {
        int i;
        try {
            StepDayData stepDayData = new StepDayData();
            stepDayData.setDate(DateUtil.format(healthSportV3.year, healthSportV3.month, healthSportV3.day));
            stepDayData.setDistances(healthSportV3.total_distances);
            stepDayData.setNumSteps(healthSportV3.total_step);
            stepDayData.setCalories(healthSportV3.total_rest_activity_calories);
            stepDayData.setTimeOfSeconds(healthSportV3.total_active_time);
            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            calendar.set(1, healthSportV3.year);
            calendar.set(2, healthSportV3.month);
            calendar.set(5, 1);
            calendar.set(11, healthSportV3.hour);
            calendar.set(12, healthSportV3.minute);
            calendar.set(13, healthSportV3.second);
            calendar.add(2, -1);
            calendar.set(5, healthSportV3.day);
            if (list != null) {
                calendar.add(12, healthSportV3.per_minute * list.size());
            }
            CommonLogUtil.d(TAG, "距离数据产生时间:" + DateUtil.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
            stepDayData.setTimeStamp(calendar.getTimeInMillis());
            int[] iArr = new int[96];
            if (healthSportV3.total_step < 0 || list == null || list.size() <= 0) {
                i = 0;
            } else {
                int iMin = Math.min(list.size(), iArr.length);
                i = 0;
                int i2 = 0;
                for (int i3 = 0; i3 < iMin; i3++) {
                    HealthSportV3Item healthSportV3Item = list.get(i3);
                    if (healthSportV3Item != null) {
                        int i4 = healthSportV3Item.step_count;
                        iArr[i3] = i4;
                        i2 = i3 % 4 == 0 ? i4 : i2 + i4;
                        if ((i3 % 4 == 3 || i3 == iMin - 1) && i2 > i) {
                            i = i2;
                        }
                    }
                }
            }
            stepDayData.setItems(GsonUtil.toJson(iArr));
            BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
            if (currentDeviceInfo != null) {
                stepDayData.setSourceMac(currentDeviceInfo.mDeviceAddress);
                stepDayData.setDeviceName(currentDeviceInfo.mDeviceName);
            }
            stepDayData.setEffectiveSteps(healthSportV3.total_step);
            Goal goal = LocalDataManager.getGoal();
            stepDayData.setTargetSteps((goal == null || goal.sport_step == 0) ? 10000 : goal.sport_step);
            stepDayData.setMaxNumSteps(i);
            stepDayData.setUploaded(false);
            LocalHealthDataManager.getInstance().saveStepDayData(stepDayData);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void saveHeartRateData(HealthHeartRate healthHeartRate, List<HealthHeartRateItem> list) {
        int[] currentDate = DateUtil.getCurrentDate();
        if (healthHeartRate == null || healthHeartRate.year < currentDate[0] - 100) {
            return;
        }
        CommonLogUtil.d(TAG, "saveHeartRateData date=" + DateUtil.format(healthHeartRate.getDate(), "yyyy-MM-dd HH:mm:ss"));
        saveDayHeartRateData(healthHeartRate, list);
    }

    public static void saveHeartRateV3Data(HealthHeartRateSecond healthHeartRateSecond) {
        int[] currentDate = DateUtil.getCurrentDate();
        if (healthHeartRateSecond == null || healthHeartRateSecond.year < currentDate[0] - 100) {
            return;
        }
        CommonLogUtil.d(TAG, "saveHeartRateV3Data date=" + DateUtil.format(healthHeartRateSecond.getDate(), "yyyy-MM-dd HH:mm:ss"));
        saveDayHeartRateV3Data(healthHeartRateSecond);
    }

    private static void saveDayHeartRateV3Data(HealthHeartRateSecond healthHeartRateSecond) {
        long userId = RunTimeUtil.getInstance().getUserId();
        String str = DateUtil.format(healthHeartRateSecond.year, healthHeartRateSecond.month, healthHeartRateSecond.day);
        CommonLogUtil.d(TAG, "心率数据同步成功date=" + str);
        ServerHeartRateDayData heartRateDailyDataByDate = LocalHealthDataManager.getInstance().getHeartRateDailyDataByDate(userId, str);
        if (heartRateDailyDataByDate != null) {
            heartRateDailyDataByDate.delete();
        }
        List<HealthHeartRateSecondItem> list = healthHeartRateSecond.items;
        if (list == null || list.size() <= 0) {
            return;
        }
        int size = list.size();
        ArrayList arrayList = new ArrayList();
        int iMax = 20;
        int iMin = 220;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            HealthHeartRateSecondItem healthHeartRateSecondItem = list.get(i5);
            if (healthHeartRateSecondItem != null) {
                if (healthHeartRateSecondItem.heartRateVal > 0) {
                    int i6 = healthHeartRateSecondItem.heartRateVal;
                    iMax = Math.max(iMax, healthHeartRateSecondItem.heartRateVal);
                    iMin = Math.min(iMin, healthHeartRateSecondItem.heartRateVal);
                    i++;
                    i2 += healthHeartRateSecondItem.heartRateVal;
                    i4 = i6;
                }
                if (i5 == 0) {
                    i3 += healthHeartRateSecondItem.offset + healthHeartRateSecond.startTime;
                    arrayList.add(new int[]{healthHeartRateSecondItem.heartRateVal, healthHeartRateSecondItem.offset + healthHeartRateSecond.startTime});
                } else {
                    arrayList.add(new int[]{healthHeartRateSecondItem.heartRateVal, healthHeartRateSecondItem.offset});
                    i3 += healthHeartRateSecondItem.offset;
                }
            }
        }
        if (iMax > 220 || iMax < 20 || iMin > 220 || iMin < 20 || i2 <= 0) {
            return;
        }
        ServerHeartRateDayData serverHeartRateDayData = new ServerHeartRateDayData();
        serverHeartRateDayData.setDate(str);
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            serverHeartRateDayData.setSourceMac(currentDeviceInfo.mDeviceAddress);
            serverHeartRateDayData.setDeviceName(currentDeviceInfo.mDeviceName);
        }
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTime(healthHeartRateSecond.getDate());
        calendar.set(11, calendar.getActualMinimum(11));
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(13, i3);
        CommonLogUtil.printAndSave("更新心率数据，原始日期 originalDate=" + healthHeartRateSecond.getDate().getTime() + ",新的数据日期 calendar=" + calendar.getTimeInMillis() + ",totalSecond=" + i3);
        serverHeartRateDayData.setTimestamp(calendar.getTimeInMillis());
        serverHeartRateDayData.setUserId(userId);
        serverHeartRateDayData.setItems(GsonUtil.toJson(arrayList));
        serverHeartRateDayData.setSilentValue(healthHeartRateSecond.getSilentHR());
        serverHeartRateDayData.setLatestValue(i4);
        serverHeartRateDayData.setMaxValue(iMax);
        serverHeartRateDayData.setMinValue(iMin);
        serverHeartRateDayData.setAvgValue(i2 / i);
        HeartRateMeasureMode heartRateMode = LocalDataManager.getHeartRateMode();
        serverHeartRateDayData.setHeartMonitorType((heartRateMode == null || heartRateMode.mode == 170) ? 3 : 2);
        serverHeartRateDayData.setUploadSuccess(false);
        LocalHealthDataManager.getInstance().saveHeartRateDayData(serverHeartRateDayData);
    }

    private static void saveDayHeartRateData(HealthHeartRate healthHeartRate, List<HealthHeartRateItem> list) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        ServerHeartRateDayData serverHeartRateDayData = new ServerHeartRateDayData();
        serverHeartRateDayData.setDate(DateUtil.format(healthHeartRate.year, healthHeartRate.month, healthHeartRate.day));
        serverHeartRateDayData.setTimestamp(healthHeartRate.getDate().getTime());
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            serverHeartRateDayData.setSourceMac(currentDeviceInfo.mDeviceAddress);
            serverHeartRateDayData.setDeviceName(currentDeviceInfo.mDeviceName);
        }
        ArrayList arrayList = new ArrayList();
        if (list == null || list.size() <= 0) {
            i = 0;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
        } else {
            i = 0;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
            for (int i7 = 0; i7 < list.size(); i7++) {
                HealthHeartRateItem healthHeartRateItem = list.get(i7);
                if (healthHeartRateItem != null) {
                    if (i7 == 0) {
                        i6 = healthHeartRateItem.offsetMinute + healthHeartRate.startTime;
                    } else {
                        i6 = healthHeartRateItem.offsetMinute;
                    }
                    int i8 = healthHeartRateItem.HeartRaveValue;
                    if (i8 >= 20 && i8 <= 220) {
                        arrayList.add(new int[]{i6, i8});
                        if (i < i8) {
                            i = i8;
                        }
                        if (i2 == 0 || i2 > i8) {
                            i2 = i8;
                        }
                        i5 += i8;
                        i4++;
                        i3 = i8;
                    }
                }
            }
        }
        if (i > 220 || i < 20 || i2 > 220 || i2 < 20) {
            return;
        }
        serverHeartRateDayData.setItems(GsonUtil.toJson(arrayList));
        serverHeartRateDayData.setMaxValue(i);
        serverHeartRateDayData.setMinValue(i2);
        serverHeartRateDayData.setSilentValue(healthHeartRate.getSilentHeart());
        serverHeartRateDayData.setLatestValue(i3);
        serverHeartRateDayData.setAvgValue(i4 == 0 ? 0 : i5 / i4);
        HeartRateMeasureMode heartRateMode = LocalDataManager.getHeartRateMode();
        serverHeartRateDayData.setHeartMonitorType((heartRateMode == null || heartRateMode.mode == 170) ? 3 : 2);
        serverHeartRateDayData.setUploadSuccess(false);
        LocalHealthDataManager.getInstance().saveHeartRateDayData(serverHeartRateDayData);
    }

    public static void saveSleepData(HealthSleep healthSleep, List<HealthSleepItem> list) {
        int[] currentDate = DateUtil.getCurrentDate();
        if (healthSleep == null || healthSleep.year < currentDate[0] - 100) {
            return;
        }
        CommonLogUtil.d(TAG, "saveSleepData date=" + DateUtil.format(healthSleep.getDate(), "yyyy-MM-dd HH:mm:ss"));
        saveDaySleepData(healthSleep, list);
    }

    public static void saveSleepV3Data(HealthSleepV3 healthSleepV3) {
        int[] currentDate = DateUtil.getCurrentDate();
        if (healthSleepV3 == null || healthSleepV3.get_up_year < Math.abs(currentDate[0] - 100)) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSleepDataLogPath(), "数据异常，不保存");
        } else {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSleepDataLogPath(), healthSleepV3.toString());
            saveDaySleepV3Data(healthSleepV3);
        }
    }

    private static void saveDaySleepData(HealthSleep healthSleep, List<HealthSleepItem> list) {
        ServerSleepDayData serverSleepDayData = new ServerSleepDayData();
        serverSleepDayData.setDate(DateUtil.format(healthSleep.year, healthSleep.month, healthSleep.day));
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            serverSleepDayData.setSourceMac(currentDeviceInfo.mDeviceAddress);
            serverSleepDayData.setDeviceName(currentDeviceInfo.mDeviceName);
        }
        String sleepStartTime = getSleepStartTime(healthSleep);
        serverSleepDayData.setStartTime(sleepStartTime);
        Date dateString2Date = DateUtil.string2Date(sleepStartTime, "yyyy-MM-dd HH:mm:ss");
        serverSleepDayData.setStartTimeMinuteOffset((dateString2Date.getHours() * 60) + dateString2Date.getMinutes());
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(1, healthSleep.year);
        calendar.set(2, healthSleep.month);
        calendar.set(5, 1);
        calendar.set(11, healthSleep.sleepEndedTimeH);
        calendar.set(12, healthSleep.sleepEndedTimeM);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(2, -1);
        calendar.set(5, healthSleep.day);
        CommonLogUtil.d(TAG, "睡眠数据产生时间:" + DateUtil.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
        serverSleepDayData.setTimestamp(calendar.getTimeInMillis());
        serverSleepDayData.setEndTime(DateUtil.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
        serverSleepDayData.setEndTimeMinuteOffset((healthSleep.sleepEndedTimeH * 60) + healthSleep.sleepEndedTimeM);
        serverSleepDayData.setScore(healthSleep.sleepScore);
        int i = healthSleep.totalSleepMinutes;
        if (i > 0) {
            serverSleepDayData.setTotalSeconds(i * 60);
            serverSleepDayData.setDeeplySeconds(healthSleep.deepSleepMinutes * 60);
            serverSleepDayData.setLightlySeconds(healthSleep.lightSleepMinutes * 60);
            serverSleepDayData.setAwakeSeconds(((i - healthSleep.deepSleepMinutes) - healthSleep.lightSleepMinutes) * 60);
            float f2 = i;
            serverSleepDayData.setDeeplyRatio(Math.round((healthSleep.deepSleepMinutes * 100.0f) / f2));
            serverSleepDayData.setLightlyRatio(Math.round((healthSleep.lightSleepMinutes * 100.0f) / f2));
            int deeplyRatio = (100 - serverSleepDayData.getDeeplyRatio()) - serverSleepDayData.getLightlyRatio();
            if (deeplyRatio < 0) {
                if (serverSleepDayData.getDeeplyRatio() > serverSleepDayData.getLightlyRatio()) {
                    serverSleepDayData.setDeeplyRatio(serverSleepDayData.getDeeplyRatio() + deeplyRatio);
                } else {
                    serverSleepDayData.setLightlyRatio(serverSleepDayData.getLightlyRatio() + deeplyRatio);
                }
                deeplyRatio = 0;
            }
            serverSleepDayData.setAwakeRatio(deeplyRatio);
        }
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                HealthSleepItem healthSleepItem = list.get(i2);
                if (healthSleepItem != null) {
                    arrayList.add(new int[]{healthSleepItem.offsetMinute, healthSleepItem.sleepStatus});
                }
            }
        }
        serverSleepDayData.setItems(GsonUtil.toJson(arrayList));
        serverSleepDayData.setUploaded(false);
        mergeAndSaveSleepData(serverSleepDayData);
    }

    private static void saveDaySleepV3Data(HealthSleepV3 healthSleepV3) {
        ServerSleepDayData serverSleepDayData = new ServerSleepDayData();
        serverSleepDayData.setDate(DateUtil.format(healthSleepV3.get_up_year, healthSleepV3.get_up_month, healthSleepV3.get_up_day));
        serverSleepDayData.setType(healthSleepV3.data_type);
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            serverSleepDayData.setSourceMac(currentDeviceInfo.mDeviceAddress);
            serverSleepDayData.setDeviceName(currentDeviceInfo.mDeviceName);
        }
        serverSleepDayData.setStartTime(DateUtil.format(healthSleepV3.fall_asleep_year, healthSleepV3.fall_asleep_month, healthSleepV3.fall_asleep_day, healthSleepV3.fall_asleep_hour, healthSleepV3.fall_asleep_minte, 0));
        serverSleepDayData.setStartTimeMinuteOffset((healthSleepV3.fall_asleep_hour * 60) + healthSleepV3.fall_asleep_minte);
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(1, healthSleepV3.get_up_year);
        calendar.set(2, healthSleepV3.get_up_month);
        calendar.set(5, 1);
        calendar.set(11, healthSleepV3.get_up_hour);
        calendar.set(12, healthSleepV3.get_up_minte);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(2, -1);
        calendar.set(5, healthSleepV3.get_up_day);
        CommonLogUtil.d(TAG, "睡眠数据产生时间:" + DateUtil.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
        serverSleepDayData.setTimestamp(calendar.getTimeInMillis());
        CommonLogUtil.d(TAG, "saveSleepV3Data date=" + DateUtil.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
        serverSleepDayData.setEndTime(DateUtil.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
        serverSleepDayData.setEndTimeMinuteOffset((healthSleepV3.get_up_hour * 60) + healthSleepV3.get_up_minte);
        serverSleepDayData.setScore(healthSleepV3.sleep_score);
        serverSleepDayData.setBreathRate(healthSleepV3.breath_quality);
        int i = healthSleepV3.total_sleep_time_mins;
        if (i > 0) {
            serverSleepDayData.setTotalSeconds(i * 60);
            serverSleepDayData.setDeeplySeconds(healthSleepV3.deep_mins * 60);
            serverSleepDayData.setLightlySeconds(healthSleepV3.light_mins * 60);
            serverSleepDayData.setAwakeSeconds(healthSleepV3.wake_mins * 60);
            serverSleepDayData.setEyeMovementSeconds(healthSleepV3.rem_mins * 60);
            float f2 = i;
            serverSleepDayData.setDeeplyRatio(Math.round((healthSleepV3.deep_mins * 100.0f) / f2));
            serverSleepDayData.setLightlyRatio(Math.round((healthSleepV3.light_mins * 100.0f) / f2));
            serverSleepDayData.setEyeMovementRatio(Math.round((healthSleepV3.rem_mins * 100.0f) / f2));
            int deeplyRatio = ((100 - serverSleepDayData.getDeeplyRatio()) - serverSleepDayData.getLightlyRatio()) - serverSleepDayData.getEyeMovementRatio();
            if (deeplyRatio < 0) {
                int iMax = Math.max(Math.max(serverSleepDayData.getDeeplyRatio(), serverSleepDayData.getLightlyRatio()), serverSleepDayData.getEyeMovementRatio());
                if (iMax == serverSleepDayData.getDeeplyRatio()) {
                    serverSleepDayData.setDeeplyRatio(serverSleepDayData.getDeeplyRatio() + deeplyRatio);
                } else if (iMax == serverSleepDayData.getLightlyRatio()) {
                    serverSleepDayData.setLightlyRatio(serverSleepDayData.getLightlyRatio() + deeplyRatio);
                } else {
                    serverSleepDayData.setEyeMovementRatio(serverSleepDayData.getEyeMovementRatio() + deeplyRatio);
                }
                deeplyRatio = 0;
            }
            serverSleepDayData.setAwakeRatio(deeplyRatio);
        }
        List<HealthSleepV3Item> list = healthSleepV3.items;
        if (list != null && list.size() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < list.size(); i2++) {
                HealthSleepV3Item healthSleepV3Item = list.get(i2);
                if (healthSleepV3Item != null) {
                    arrayList.add(new int[]{healthSleepV3Item.duration, healthSleepV3Item.stage});
                }
            }
            serverSleepDayData.setItems(GsonUtil.toJson(arrayList));
        }
        serverSleepDayData.setUploaded(false);
        mergeAndSaveSleepData(serverSleepDayData);
    }

    private static void mergeAndSaveSleepData(ServerSleepDayData serverSleepDayData) {
        List<ServerSleepDayData> deviceSleepDailyDataByDate = LocalHealthDataManager.getInstance().getDeviceSleepDailyDataByDate(serverSleepDayData.getDate(), serverSleepDayData.getSourceMac());
        if (deviceSleepDailyDataByDate == null || deviceSleepDailyDataByDate.isEmpty()) {
            LocalHealthDataManager.getInstance().saveSleepDayData(serverSleepDayData);
            return;
        }
        int startTimeMinuteOffset = serverSleepDayData.getStartTimeMinuteOffset();
        int endTimeMinuteOffset = serverSleepDayData.getEndTimeMinuteOffset();
        for (ServerSleepDayData serverSleepDayData2 : deviceSleepDailyDataByDate) {
            if (serverSleepDayData2 != null) {
                if ((startTimeMinuteOffset <= serverSleepDayData2.getStartTimeMinuteOffset() && endTimeMinuteOffset >= serverSleepDayData2.getStartTimeMinuteOffset()) || (startTimeMinuteOffset <= serverSleepDayData2.getEndTimeMinuteOffset() && endTimeMinuteOffset >= serverSleepDayData2.getEndTimeMinuteOffset())) {
                    if (endTimeMinuteOffset < serverSleepDayData2.getEndTimeMinuteOffset()) {
                        return;
                    } else {
                        serverSleepDayData2.delete();
                    }
                } else if (startTimeMinuteOffset >= serverSleepDayData2.getEndTimeMinuteOffset() && startTimeMinuteOffset - serverSleepDayData2.getEndTimeMinuteOffset() <= 60) {
                    serverSleepDayData2.delete();
                    mergeAndSaveSleepData(mergeSleepData(serverSleepDayData2, serverSleepDayData));
                    return;
                } else if (serverSleepDayData2.getStartTimeMinuteOffset() >= endTimeMinuteOffset && serverSleepDayData2.getStartTimeMinuteOffset() - endTimeMinuteOffset <= 60) {
                    serverSleepDayData2.delete();
                    mergeAndSaveSleepData(mergeSleepData(serverSleepDayData, serverSleepDayData2));
                    return;
                }
            }
        }
        LocalHealthDataManager.getInstance().saveSleepDayData(serverSleepDayData);
    }

    private static ServerSleepDayData mergeSleepData(ServerSleepDayData serverSleepDayData, ServerSleepDayData serverSleepDayData2) {
        ServerSleepDayData serverSleepDayData3 = new ServerSleepDayData();
        serverSleepDayData3.setSourceMac(serverSleepDayData.getSourceMac());
        serverSleepDayData3.setDeviceName(serverSleepDayData.getDeviceName());
        serverSleepDayData3.setDate(serverSleepDayData.getDate());
        serverSleepDayData3.setStartTime(serverSleepDayData.getStartTime());
        serverSleepDayData3.setStartTimeMinuteOffset(serverSleepDayData.getStartTimeMinuteOffset());
        serverSleepDayData3.setEndTime(serverSleepDayData2.getEndTime());
        serverSleepDayData3.setEndTimeMinuteOffset(serverSleepDayData2.getEndTimeMinuteOffset());
        serverSleepDayData3.setScore((serverSleepDayData2.getScore() + serverSleepDayData.getScore()) / 2);
        serverSleepDayData3.setBreathRate((serverSleepDayData2.getBreathRate() + serverSleepDayData.getBreathRate()) / 2);
        int startTimeMinuteOffset = (serverSleepDayData2.getStartTimeMinuteOffset() - serverSleepDayData.getEndTimeMinuteOffset()) * 60;
        int totalSeconds = serverSleepDayData2.getTotalSeconds() + serverSleepDayData.getTotalSeconds() + startTimeMinuteOffset;
        if (totalSeconds > 0) {
            serverSleepDayData3.setTotalSeconds(totalSeconds);
            serverSleepDayData3.setDeeplySeconds(serverSleepDayData2.getDeeplySeconds() + serverSleepDayData.getDeeplySeconds());
            serverSleepDayData3.setLightlySeconds(serverSleepDayData2.getLightlySeconds() + serverSleepDayData.getLightlySeconds());
            serverSleepDayData3.setAwakeSeconds(serverSleepDayData2.getAwakeSeconds() + serverSleepDayData.getAwakeSeconds() + startTimeMinuteOffset);
            serverSleepDayData3.setEyeMovementSeconds(serverSleepDayData2.getEyeMovementSeconds() + serverSleepDayData.getEyeMovementSeconds());
            float f2 = totalSeconds;
            serverSleepDayData3.setDeeplyRatio(Math.round((serverSleepDayData3.getDeeplySeconds() * 100.0f) / f2));
            serverSleepDayData3.setLightlyRatio(Math.round((serverSleepDayData3.getLightlySeconds() * 100.0f) / f2));
            serverSleepDayData3.setEyeMovementRatio(Math.round((serverSleepDayData3.getEyeMovementSeconds() * 100.0f) / f2));
            int deeplyRatio = ((100 - serverSleepDayData3.getDeeplyRatio()) - serverSleepDayData3.getLightlyRatio()) - serverSleepDayData3.getEyeMovementRatio();
            if (deeplyRatio < 0) {
                int iMax = Math.max(Math.max(serverSleepDayData3.getDeeplyRatio(), serverSleepDayData3.getLightlyRatio()), serverSleepDayData3.getEyeMovementRatio());
                if (iMax == serverSleepDayData3.getDeeplyRatio()) {
                    serverSleepDayData3.setDeeplyRatio(serverSleepDayData3.getDeeplyRatio() + deeplyRatio);
                } else if (iMax == serverSleepDayData3.getLightlyRatio()) {
                    serverSleepDayData3.setLightlyRatio(serverSleepDayData3.getLightlyRatio() + deeplyRatio);
                } else {
                    serverSleepDayData3.setEyeMovementRatio(serverSleepDayData3.getEyeMovementRatio() + deeplyRatio);
                }
                deeplyRatio = 0;
            }
            serverSleepDayData3.setAwakeRatio(deeplyRatio);
        }
        ArrayList arrayList = new ArrayList();
        List listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList(serverSleepDayData.getItems(), int[][].class);
        if (listAnalysisJsonArrayToList != null && !listAnalysisJsonArrayToList.isEmpty()) {
            arrayList.addAll(listAnalysisJsonArrayToList);
        }
        arrayList.add(new int[]{startTimeMinuteOffset / 60, 1});
        List listAnalysisJsonArrayToList2 = GsonUtil.analysisJsonArrayToList(serverSleepDayData2.getItems(), int[][].class);
        if (listAnalysisJsonArrayToList2 != null && !listAnalysisJsonArrayToList2.isEmpty()) {
            arrayList.addAll(listAnalysisJsonArrayToList2);
        }
        serverSleepDayData3.setItems(GsonUtil.toJson(arrayList));
        serverSleepDayData3.setUploaded(false);
        return serverSleepDayData3;
    }

    public static void calculateSleepData(Date date) {
        Date mondayOfWeek = DateUtil.getMondayOfWeek(date);
        calculateMultiDaysSleepData(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, mondayOfWeek), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getSpecifiedDayBefore(mondayOfWeek, -6)), false);
        Date sundayOfWeek = DateUtil.getSundayOfWeek(date);
        calculateMultiDaysSleepData(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, sundayOfWeek), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getSpecifiedDayBefore(sundayOfWeek, -6)), false);
        Date saturdayOfLastWeek = DateUtil.getSaturdayOfLastWeek(date);
        calculateMultiDaysSleepData(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, saturdayOfLastWeek), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getSpecifiedDayBefore(saturdayOfLastWeek, -6)), false);
        calculateMultiDaysSleepData(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getFirstDayOfMonth(date)), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getLastDayOfMonth(date)), false);
        calculateYearSleepData(DateUtil.yearMonthDay(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, date))[0]);
    }

    public static void calculateYearSleepData(int i) {
        List<ServerSleepMonthData> monthSleepAnnual = LocalHealthDataManager.getInstance().getMonthSleepAnnual(i);
        ServerMultiMonthSleepTotalData yearSleep = LocalHealthDataManager.getInstance().getYearSleep(i);
        if (yearSleep == null) {
            yearSleep = new ServerMultiMonthSleepTotalData();
            yearSleep.setStartDate(i + LocalHealthDataManager.YEAR_START_DATE);
            yearSleep.setEndDate(i + LocalHealthDataManager.YEAR_END_DATE);
        }
        int i2 = 0;
        int avgTotalSeconds = 0;
        int avgDeeplySeconds = 0;
        int avgLightlySeconds = 0;
        int avgEyeMovementSeconds = 0;
        for (ServerSleepMonthData serverSleepMonthData : monthSleepAnnual) {
            if (serverSleepMonthData != null && serverSleepMonthData.getAvgTotalSeconds() > 0) {
                avgTotalSeconds += serverSleepMonthData.getAvgTotalSeconds();
                avgDeeplySeconds += serverSleepMonthData.getAvgDeeplySeconds();
                avgLightlySeconds += serverSleepMonthData.getAvgLightlySeconds();
                avgEyeMovementSeconds += serverSleepMonthData.getAvgEyeMovementSeconds();
                i2++;
            }
        }
        if (i2 > 0) {
            yearSleep.setAvgTotalSeconds(avgTotalSeconds / i2);
            yearSleep.setAvgDeeplySeconds(avgDeeplySeconds / i2);
            float f2 = avgTotalSeconds;
            yearSleep.setDeeplyRatio(Math.round((avgDeeplySeconds * 100.0f) / f2));
            yearSleep.setAvgLightlySeconds(avgLightlySeconds / i2);
            yearSleep.setLightlyRatio(Math.round((avgLightlySeconds * 100.0f) / f2));
            yearSleep.setAvgEyeMovementSeconds(avgEyeMovementSeconds / i2);
            yearSleep.setEyeMovementRatio(Math.round((avgEyeMovementSeconds * 100.0f) / f2));
            int i3 = ((avgTotalSeconds - avgDeeplySeconds) - avgLightlySeconds) - avgEyeMovementSeconds;
            yearSleep.setAvgAwakeSeconds(i3 > 0 ? i3 / i2 : 0);
            int deeplyRatio = ((100 - yearSleep.getDeeplyRatio()) - yearSleep.getLightlyRatio()) - yearSleep.getEyeMovementRatio();
            if (deeplyRatio < 0) {
                int iMax = Math.max(Math.max(yearSleep.getDeeplyRatio(), yearSleep.getLightlyRatio()), yearSleep.getEyeMovementRatio());
                if (iMax == yearSleep.getDeeplyRatio()) {
                    yearSleep.setDeeplyRatio(yearSleep.getDeeplyRatio() + deeplyRatio);
                } else if (iMax == yearSleep.getLightlyRatio()) {
                    yearSleep.setLightlyRatio(yearSleep.getLightlyRatio() + deeplyRatio);
                } else {
                    yearSleep.setEyeMovementRatio(yearSleep.getEyeMovementRatio() + deeplyRatio);
                }
                deeplyRatio = 0;
            }
            yearSleep.setAwakeRatio(deeplyRatio);
        }
        LocalHealthDataManager.getInstance().saveYearSleep(yearSleep);
    }

    public static void calculateSleepYearData(int i) {
        for (int i2 = 1; i2 <= 12; i2++) {
            Date date = DateUtil.getDate(i, i2, 1);
            calculateMultiDaysSleepData(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, date), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getLastDayOfMonth(date)), true);
        }
        calculateYearSleepData(i);
    }

    public static void calculateMultiDaysSleepData(String str, String str2, boolean z) {
        int i;
        ServerDaysSleepData serverDaysSleepData = new ServerDaysSleepData();
        serverDaysSleepData.setStartDate(str);
        serverDaysSleepData.setEndDate(str2);
        List<ServerSleepDayData> sleepDailyDataList = LocalHealthDataManager.getInstance().getSleepDailyDataList(str, str2, z);
        if (sleepDailyDataList == null || sleepDailyDataList.isEmpty()) {
            return;
        }
        String date = "";
        int i2 = 0;
        int i3 = 0;
        int totalSeconds = 0;
        int lightlySeconds = 0;
        int deeplySeconds = 0;
        int eyeMovementSeconds = 0;
        int i4 = -1;
        int i5 = -1;
        int score = 0;
        int breathRate = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < sleepDailyDataList.size(); i11++) {
            ServerSleepDayData serverSleepDayData = sleepDailyDataList.get(i11);
            if (serverSleepDayData == null) {
                if (i11 == sleepDailyDataList.size() - 1 && i4 >= 0 && i5 >= 0) {
                    if (i4 <= 720) {
                        i8 += i4;
                        i6++;
                    } else {
                        i9 += i4;
                        i7++;
                    }
                    i10 += i5;
                }
            } else if (serverSleepDayData.getTotalSeconds() > 0) {
                if (!serverSleepDayData.getDate().equals(date) && i4 >= 0 && i5 >= 0) {
                    if (i4 <= 720) {
                        i8 += i4;
                        i6++;
                    } else {
                        i9 += i4;
                        i7++;
                    }
                    i10 += i5;
                    i4 = -1;
                    i5 = -1;
                }
                totalSeconds += serverSleepDayData.getTotalSeconds();
                lightlySeconds += serverSleepDayData.getLightlySeconds();
                deeplySeconds += serverSleepDayData.getDeeplySeconds();
                eyeMovementSeconds += serverSleepDayData.getEyeMovementSeconds();
                score += serverSleepDayData.getScore();
                breathRate += serverSleepDayData.getBreathRate();
                int startTimeMinuteOffset = serverSleepDayData.getStartTimeMinuteOffset();
                if (i4 >= 0) {
                    startTimeMinuteOffset = Math.min(i4, startTimeMinuteOffset);
                }
                int endTimeMinuteOffset = serverSleepDayData.getEndTimeMinuteOffset();
                if (i5 >= 0) {
                    endTimeMinuteOffset = Math.max(i5, endTimeMinuteOffset);
                }
                i3++;
                if (!serverSleepDayData.getDate().equals(date)) {
                    i2++;
                    date = serverSleepDayData.getDate();
                }
                if (i11 == sleepDailyDataList.size() - 1 && startTimeMinuteOffset >= 0 && endTimeMinuteOffset >= 0) {
                    if (startTimeMinuteOffset <= 720) {
                        i8 += startTimeMinuteOffset;
                        i6++;
                    } else {
                        i9 += startTimeMinuteOffset;
                        i7++;
                    }
                    i10 += endTimeMinuteOffset;
                }
                i5 = endTimeMinuteOffset;
                i4 = startTimeMinuteOffset;
            }
        }
        int iMax = Math.max(i2, 1);
        if (i3 > 0) {
            serverDaysSleepData.setAvgTotalSeconds(totalSeconds / iMax);
            serverDaysSleepData.setAvgLightlySeconds(lightlySeconds / iMax);
            serverDaysSleepData.setAvgDeeplySeconds(deeplySeconds / iMax);
            serverDaysSleepData.setAvgEyeMovementSeconds(eyeMovementSeconds / iMax);
            serverDaysSleepData.setAvgAwakeSeconds((((totalSeconds - lightlySeconds) - deeplySeconds) - eyeMovementSeconds) / iMax);
            float f2 = totalSeconds;
            serverDaysSleepData.setLightlyRatio(Math.round((lightlySeconds * 100.0f) / f2));
            serverDaysSleepData.setDeeplyRatio(Math.round((deeplySeconds * 100.0f) / f2));
            serverDaysSleepData.setEyeMovementRatio(Math.round((eyeMovementSeconds * 100.0f) / f2));
            int lightlyRatio = ((100 - serverDaysSleepData.getLightlyRatio()) - serverDaysSleepData.getDeeplyRatio()) - serverDaysSleepData.getEyeMovementRatio();
            if (lightlyRatio < 0) {
                int iMax2 = Math.max(Math.max(serverDaysSleepData.getDeeplyRatio(), serverDaysSleepData.getLightlyRatio()), serverDaysSleepData.getEyeMovementRatio());
                if (iMax2 == serverDaysSleepData.getDeeplyRatio()) {
                    serverDaysSleepData.setDeeplyRatio(serverDaysSleepData.getDeeplyRatio() + lightlyRatio);
                } else if (iMax2 == serverDaysSleepData.getLightlyRatio()) {
                    serverDaysSleepData.setLightlyRatio(serverDaysSleepData.getLightlyRatio() + lightlyRatio);
                } else {
                    serverDaysSleepData.setEyeMovementRatio(serverDaysSleepData.getEyeMovementRatio() + lightlyRatio);
                }
                lightlyRatio = 0;
            }
            serverDaysSleepData.setAwakeRatio(lightlyRatio);
            serverDaysSleepData.setAvgScore(score / i3);
            serverDaysSleepData.setAvgBreathRate(breathRate / i3);
            if (i6 > 0 || i7 > 0) {
                if (i6 > 0 && i7 > 0) {
                    i = (((i8 / i6) + (i9 / i7)) / 2) + 720;
                } else if (i6 > 0) {
                    i = i8 / i6;
                } else {
                    i = i9 / i7;
                }
                while (i >= 1440) {
                    i -= 1440;
                }
            } else {
                i = 0;
            }
            serverDaysSleepData.setAvgStartTimeMinute(i);
            int i12 = i10 / iMax;
            while (i12 >= 1440) {
                i12 -= 1440;
            }
            serverDaysSleepData.setAvgEndTimeMinute(i12);
        }
        LocalHealthDataManager.getInstance().saveMultiDaysSleepTotalData(serverDaysSleepData);
        if (z) {
            int[] iArrYearMonthDay = DateUtil.yearMonthDay(str);
            StringBuilder sb = new StringBuilder();
            int i13 = 0;
            sb.append(iArrYearMonthDay[0]);
            sb.append("-");
            sb.append(NumUtil.numberFormat(iArrYearMonthDay[1]));
            String string = sb.toString();
            ServerSleepMonthData monthSleep = LocalHealthDataManager.getInstance().getMonthSleep(string);
            if (monthSleep == null) {
                monthSleep = new ServerSleepMonthData();
                monthSleep.setMonth(string);
            }
            if (i3 > 0) {
                monthSleep.setAvgTotalSeconds(totalSeconds / iMax);
                monthSleep.setAvgLightlySeconds(lightlySeconds / iMax);
                monthSleep.setAvgDeeplySeconds(deeplySeconds / iMax);
                monthSleep.setAvgEyeMovementSeconds(eyeMovementSeconds / iMax);
                monthSleep.setAvgAwakeSeconds((((totalSeconds - lightlySeconds) - deeplySeconds) - eyeMovementSeconds) / iMax);
                float f3 = totalSeconds;
                monthSleep.setLightlyRatio(Math.round((lightlySeconds * 100.0f) / f3));
                monthSleep.setDeeplyRatio(Math.round((deeplySeconds * 100.0f) / f3));
                monthSleep.setEyeMovementRatio(Math.round((eyeMovementSeconds * 100.0f) / f3));
                int lightlyRatio2 = ((100 - monthSleep.getLightlyRatio()) - monthSleep.getDeeplyRatio()) - monthSleep.getEyeMovementRatio();
                if (lightlyRatio2 < 0) {
                    int iMax3 = Math.max(Math.max(monthSleep.getDeeplyRatio(), monthSleep.getLightlyRatio()), monthSleep.getEyeMovementRatio());
                    if (iMax3 == monthSleep.getDeeplyRatio()) {
                        monthSleep.setDeeplyRatio(monthSleep.getDeeplyRatio() + lightlyRatio2);
                    } else if (iMax3 == monthSleep.getLightlyRatio()) {
                        monthSleep.setLightlyRatio(monthSleep.getLightlyRatio() + lightlyRatio2);
                    } else {
                        monthSleep.setEyeMovementRatio(monthSleep.getEyeMovementRatio() + lightlyRatio2);
                    }
                } else {
                    i13 = lightlyRatio2;
                }
                monthSleep.setAwakeRatio(i13);
            }
            LocalHealthDataManager.getInstance().saveMonthSleep(monthSleep);
        }
    }

    public static String getSleepStartTime(HealthSleep healthSleep) {
        int sleepEndedTimeH = (healthSleep.getSleepEndedTimeH() * 60) + healthSleep.getSleepEndedTimeM();
        int totalSleepMinutes = healthSleep.getTotalSleepMinutes();
        int i = 0;
        while (sleepEndedTimeH <= totalSleepMinutes) {
            sleepEndedTimeH += 1440;
            i++;
        }
        int i2 = sleepEndedTimeH - totalSleepMinutes;
        int[] yearMonthDay = DateUtil.getYearMonthDay(DateUtil.getSpecifiedDayBefore(DateUtil.getDate(healthSleep.year, healthSleep.month, healthSleep.day), i));
        return DateUtil.format(yearMonthDay[0], yearMonthDay[1], yearMonthDay[2], i2 / 60, i2 % 60, 0);
    }

    public static String getSleepTime(HealthSleep healthSleep) {
        int sleepEndedTimeH = (healthSleep.getSleepEndedTimeH() * 60) + healthSleep.getSleepEndedTimeM();
        int totalSleepMinutes = healthSleep.getTotalSleepMinutes();
        while (sleepEndedTimeH <= totalSleepMinutes) {
            sleepEndedTimeH += 1440;
        }
        int i = sleepEndedTimeH - totalSleepMinutes;
        return NumUtil.numberFormat(i / 60) + ":" + NumUtil.numberFormat(i % 60);
    }

    public static void saveActivityData(HealthActivity healthActivity) {
        int[] currentDate = DateUtil.getCurrentDate();
        if (healthActivity == null || healthActivity.year < currentDate[0] - 100) {
            return;
        }
        CommonLogUtil.d("saveActivityData date=" + DateUtil.format(healthActivity.getDate(), "yyyy-MM-dd HH:mm:ss"));
        SportHealth sportHealth = new SportHealth();
        sportHealth.setDateTime(DateUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", healthActivity.getDate()));
        sportHealth.setType(healthActivity.type);
        sportHealth.setTotalSeconds(healthActivity.durations);
        sportHealth.setNumCalories(healthActivity.calories);
        sportHealth.setNumSteps(healthActivity.step);
        sportHealth.setDistance(healthActivity.distance);
        sportHealth.setStartTime(DateUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", healthActivity.getDate()));
        Date timeAfterSeconds = DateUtil.getTimeAfterSeconds(healthActivity.getDate(), healthActivity.durations);
        sportHealth.setEndTime(DateUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", timeAfterSeconds));
        sportHealth.setTimestamp(timeAfterSeconds.getTime());
        sportHealth.setMaxHrValue(healthActivity.max_hr_value);
        sportHealth.setAvgHrValue(healthActivity.avg_hr_value);
        int[] hr_data_vlaue = healthActivity.getHr_data_vlaue();
        int i = healthActivity.min_hr_value;
        if (hr_data_vlaue != null) {
            int i2 = i;
            for (int i3 : hr_data_vlaue) {
                if (i3 >= 20 && i3 < i2 && i3 <= 220) {
                    i2 = i3;
                }
            }
            i = i2;
        }
        sportHealth.setMinHrValue(i);
        sportHealth.setWarmupSeconds(healthActivity.warmUpMins * 60);
        sportHealth.setBurnFatSeconds(healthActivity.burn_fat_mins * 60);
        sportHealth.setAerobicSeconds(healthActivity.aerobic_mins * 60);
        sportHealth.setAnaerobicSecond(healthActivity.anaerobicMins * 60);
        sportHealth.setExtremeSecond(healthActivity.limit_mins * 60);
        sportHealth.setIsLocus(LocalHealthDataManager.getInstance().getSportGpsData(RunTimeUtil.getInstance().getUserId(), healthActivity.getDate().getTime()) != null ? 1 : 0);
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            sportHealth.setSourceMac(currentDeviceInfo.mDeviceAddress);
        }
        sportHealth.setSourceType(2);
        sportHealth.setHr_data_vlaue_json(GsonUtil.toJson(hr_data_vlaue));
        sportHealth.setIsUploaded(false);
        sportHealth.setUserId(RunTimeUtil.getInstance().getUserId());
        LocalHealthDataManager.getInstance().saveActivityData(sportHealth);
    }

    private static String getLength2Str(int i) {
        StringBuilder sb;
        String str;
        if (i >= 10) {
            sb = new StringBuilder();
            str = "";
        } else {
            sb = new StringBuilder();
            str = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
        }
        sb.append(str);
        sb.append(i);
        return sb.toString();
    }

    public static void saveActivityV3Data(HealthActivityV3 healthActivityV3) {
        List<Integer> list;
        CommonLogUtil.d(TAG, "saveActivityV3Data: " + healthActivityV3.toString());
        SportLogHelper.saveSportLog(TAG, "saveActivityV3Data: " + healthActivityV3.toString());
        int[] currentDate = DateUtil.getCurrentDate();
        if (healthActivityV3 != null) {
            if (healthActivityV3.year < currentDate[0] - 100) {
                return;
            }
            SportHealth sportHealth = new SportHealth();
            sportHealth.setUserId(RunTimeUtil.getInstance().getUserId());
            String str = healthActivityV3.year + "-" + getLength2Str(healthActivityV3.month) + "-" + getLength2Str(healthActivityV3.day) + " " + getLength2Str(healthActivityV3.hour) + ":" + getLength2Str(healthActivityV3.minute) + ":" + getLength2Str(healthActivityV3.second);
            Date dateString2Date = DateUtil.string2Date(str, "yyyy-MM-dd HH:mm:ss");
            CommonLogUtil.d(TAG, "saveActivityV3Data: " + str);
            sportHealth.setDateTime(str);
            sportHealth.setType(healthActivityV3.type);
            sportHealth.setTotalSeconds(healthActivityV3.durations);
            sportHealth.setHrDataIntervalMinute(healthActivityV3.hr_data_interval_minute);
            sportHealth.setNumCalories(healthActivityV3.calories);
            sportHealth.setNumSteps(healthActivityV3.step);
            sportHealth.setDistance(healthActivityV3.distance);
            sportHealth.setStartTime(str);
            if (healthActivityV3.end_month > 0) {
                sportHealth.setEndTime(healthActivityV3.year + "-" + getLength2Str(healthActivityV3.end_month) + "-" + getLength2Str(healthActivityV3.end_day) + " " + getLength2Str(healthActivityV3.end_hour) + ":" + getLength2Str(healthActivityV3.end_minute) + ":30");
            } else {
                sportHealth.setEndTime(DateUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", DateUtil.getTimeAfterSeconds(dateString2Date, healthActivityV3.durations)));
            }
            sportHealth.setTimestamp(dateString2Date.getTime());
            CommonLogUtil.d(TAG, "saveActivityV3Data: " + sportHealth.getStartTime() + AppInfo.DELIM + sportHealth.getEndTime());
            sportHealth.setIntervalSecond(healthActivityV3.hr_data_interval_minute);
            sportHealth.setMaxHrValue(healthActivityV3.max_hr_value);
            sportHealth.setAvgHrValue(healthActivityV3.avg_hr_value);
            int[] iArr = healthActivityV3.hr_data_vlaue;
            int i = healthActivityV3.min_hr_value;
            if (iArr != null) {
                int i2 = i;
                for (int i3 : iArr) {
                    if (i3 >= 20 && i3 < i2 && i3 <= 220) {
                        i2 = i3;
                    }
                }
                i = i2;
            }
            sportHealth.setMinHrValue(i);
            sportHealth.setWarmupSeconds((healthActivityV3.warmUpMins * 60) + healthActivityV3.warm_up_time);
            sportHealth.setBurnFatSeconds((healthActivityV3.burn_fat_mins * 60) + healthActivityV3.fat_burning_time);
            sportHealth.setAerobicSeconds((healthActivityV3.aerobic_mins * 60) + healthActivityV3.aerobic_exercise_time);
            sportHealth.setAnaerobicSecond((healthActivityV3.anaerobicMins * 60) + healthActivityV3.anaerobic_exercise_time);
            sportHealth.setExtremeSecond((healthActivityV3.limit_mins * 60) + healthActivityV3.extreme_exercise_time);
            sportHealth.setIsLocus(GreenDaoUtil.getSportGpsData(RunTimeUtil.getInstance().getUserId(), dateString2Date.getTime()) != null ? 1 : 0);
            BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
            if (currentDeviceInfo != null) {
                sportHealth.setSourceMac(currentDeviceInfo.mDeviceAddress);
            }
            sportHealth.setSourceType(2);
            sportHealth.setSourceOs(2);
            sportHealth.setHr_data_vlaue_json(GsonUtil.toJson(iArr));
            sportHealth.setIsUploaded(false);
            sportHealth.setAvgPace(healthActivityV3.km_speed);
            sportHealth.setMinPace(healthActivityV3.fast_km_speed);
            sportHealth.setAvgSpeed(healthActivityV3.avg_speed);
            sportHealth.setMaxSpeed(healthActivityV3.max_speed);
            sportHealth.setStepRate(healthActivityV3.avg_step_frequency);
            sportHealth.setStepRateMax(healthActivityV3.max_step_frequency);
            sportHealth.setStepRange(healthActivityV3.avg_step_stride);
            sportHealth.setStepRangeMax(healthActivityV3.max_step_stride);
            sportHealth.setLoadDetail(true);
            if (healthActivityV3.frequency_items != null && (list = healthActivityV3.frequency_items) != null && list.size() > 0) {
                int[] iArr2 = new int[list.size()];
                for (int i4 = 0; i4 < list.size(); i4++) {
                    iArr2[i4] = list.get(i4).intValue();
                }
                sportHealth.setStepItem(GsonUtil.toJson(iArr2));
            }
            List<HealthActivityV3.ItemKMSpeed> list2 = healthActivityV3.items_km_speed;
            if (list2 != null && list2.size() > 0) {
                int[] iArr3 = new int[list2.size()];
                for (int i5 = 0; i5 < list2.size(); i5++) {
                    iArr3[i5] = list2.get(i5).second;
                }
                SportItemPace pace = sportHealth.getPace();
                if (pace == null) {
                    pace = new SportItemPace();
                }
                pace.setMetricItems(GsonUtil.toJson(iArr3));
                sportHealth.setPace(pace);
            }
            if (healthActivityV3.items_mi_speed != null && healthActivityV3.items_mi_speed.size() > 0) {
                SportItemPace pace2 = sportHealth.getPace();
                if (pace2 == null) {
                    pace2 = new SportItemPace();
                }
                pace2.setBritishItems(GsonUtil.toJson(healthActivityV3.items_mi_speed));
                sportHealth.setPace(pace2);
            }
            sportHealth.setDiscoverDateTime(healthActivityV3.recovery_time_year + "-" + healthActivityV3.recovery_time_mon + "-" + healthActivityV3.recovery_time_day + " " + healthActivityV3.recovery_time_hour + ":" + healthActivityV3.recovery_time_min + ":" + healthActivityV3.recovery_time_s);
            sportHealth.setVo2max(Math.round(((float) healthActivityV3.vO2max) / 100.0f));
            if (healthActivityV3.training_effect != 0) {
                sportHealth.setTrainingEffectScore(healthActivityV3.training_effect / 10.0f);
            }
            SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
            if (supportFunctionInfo != null) {
                sportHealth.setIsSupportTrainingEffect(supportFunctionInfo.V3_sync_v3_activity_add_param ? 1 : 0);
            }
            GreenDaoUtil.saveActivityData(sportHealth);
            String str2 = (String) SPUtils.get(Constants.UPLOAD_STRAVA_LIST, "");
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getStravaLogPath(), TAG, "saveActivityV3Data: " + str2);
            List listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList(str2, Long[].class);
            ArrayList arrayList = new ArrayList();
            if (listAnalysisJsonArrayToList != null && listAnalysisJsonArrayToList.size() >= 0) {
                arrayList = new ArrayList(listAnalysisJsonArrayToList);
            }
            arrayList.add(Long.valueOf(dateString2Date.getTime()));
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getStravaLogPath(), TAG, "saveActivityV3Data: " + GsonUtil.toJson(arrayList));
            SPUtils.put(Constants.UPLOAD_STRAVA_LIST, GsonUtil.toJson(arrayList));
        }
    }

    public static void saveHealthSpO2Data(HealthSpO2 healthSpO2, List<HealthSpO2Item> list) {
        int[] currentDate = DateUtil.getCurrentDate();
        if (healthSpO2 == null || healthSpO2.year < currentDate[0] - 100) {
            return;
        }
        CommonLogUtil.d(TAG, "saveHealthSpO2Data date=" + DateUtil.format(healthSpO2.getDate(), "yyyy-MM-dd HH:mm:ss"));
        saveDayBloodOxyData(healthSpO2, list);
    }

    public static void calculateBloodOxyData(Date date) {
        calculateMultiDaysBloodOxyData(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getMondayOfWeek(date)), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getSundayOfNextWeek(date)), false);
        Date sundayOfWeek = DateUtil.getSundayOfWeek(date);
        calculateMultiDaysBloodOxyData(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, sundayOfWeek), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getSpecifiedDayBefore(sundayOfWeek, -6)), false);
        Date saturdayOfLastWeek = DateUtil.getSaturdayOfLastWeek(date);
        calculateMultiDaysBloodOxyData(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, saturdayOfLastWeek), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getSpecifiedDayBefore(saturdayOfLastWeek, -6)), false);
        calculateMultiDaysBloodOxyData(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getFirstDayOfMonth(date)), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getLastDayOfMonth(date)), false);
        calculateYearBloodOxyData(DateUtil.yearMonthDay(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, date))[0]);
    }

    public static void calculateYearBloodOxyData(int i) {
        List<ServerDaysBloodOxyData> yearAllMonthTotalBloodOxyDataList = LocalHealthDataManager.getInstance().getYearAllMonthTotalBloodOxyDataList(i);
        ServerMultiMonthBloodOxyTotalData yearBloodOxy = LocalHealthDataManager.getInstance().getYearBloodOxy(i);
        if (yearBloodOxy == null) {
            yearBloodOxy = new ServerMultiMonthBloodOxyTotalData();
            yearBloodOxy.setStartDate(i + LocalHealthDataManager.YEAR_START_DATE);
            yearBloodOxy.setEndDate(i + LocalHealthDataManager.YEAR_END_DATE);
        }
        int iMin = 0;
        int iMax = 0;
        int i2 = 0;
        int i3 = 0;
        int totalMeasurementTimes = 0;
        for (ServerDaysBloodOxyData serverDaysBloodOxyData : yearAllMonthTotalBloodOxyDataList) {
            if (serverDaysBloodOxyData != null && (serverDaysBloodOxyData.getMaxValue() != 0 || serverDaysBloodOxyData.getMinValue() != 0)) {
                yearBloodOxy.setUserId(serverDaysBloodOxyData.getUserId());
                int minValue = serverDaysBloodOxyData.getMinValue();
                if (iMin == 0) {
                    iMin = minValue;
                } else if (minValue != 0) {
                    iMin = Math.min(iMin, minValue);
                }
                iMax = Math.max(iMax, serverDaysBloodOxyData.getMaxValue());
                totalMeasurementTimes += serverDaysBloodOxyData.getTotalMeasurementTimes();
                int avgValue = serverDaysBloodOxyData.getAvgValue();
                if (avgValue >= 60 && avgValue <= 100) {
                    i3 += avgValue;
                    i2++;
                }
            }
        }
        if (iMin == 0) {
            iMin = iMax;
        }
        if (iMin == 0) {
            return;
        }
        yearBloodOxy.setMinValue(iMin);
        yearBloodOxy.setMaxValue(iMax);
        yearBloodOxy.setAvgValue(i2 != 0 ? i3 / i2 : 0);
        yearBloodOxy.setTotalMeasurementTimes(totalMeasurementTimes);
        LocalHealthDataManager.getInstance().saveYearBloodOxy(yearBloodOxy);
    }

    public static void calculateBloodOxyYearData(int i) {
        for (int i2 = 1; i2 <= 12; i2++) {
            Date date = DateUtil.getDate(i, i2, 1);
            calculateMultiDaysBloodOxyData(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, date), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getLastDayOfMonth(date)), true);
        }
        calculateYearBloodOxyData(i);
    }

    public static void calculateMultiDaysBloodOxyData(String str, String str2, boolean z) {
        ServerDaysBloodOxyData serverDaysBloodOxyData = new ServerDaysBloodOxyData();
        serverDaysBloodOxyData.setStartDate(str);
        serverDaysBloodOxyData.setEndDate(str2);
        List<ServerBloodOxyDayData> bloodOxyDailyDataList = LocalHealthDataManager.getInstance().getBloodOxyDailyDataList(str, str2, z);
        if (bloodOxyDailyDataList == null || bloodOxyDailyDataList.isEmpty()) {
            return;
        }
        int minValue = 0;
        int iMax = 0;
        int i = 0;
        int i2 = 0;
        int measurementTimes = 0;
        for (ServerBloodOxyDayData serverBloodOxyDayData : bloodOxyDailyDataList) {
            if (serverBloodOxyDayData != null && (serverBloodOxyDayData.getMaxValue() != 0 || serverBloodOxyDayData.getMinValue() != 0)) {
                serverDaysBloodOxyData.setUserId(serverBloodOxyDayData.getUserId());
                if (serverBloodOxyDayData.getMinValue() > 0) {
                    minValue = minValue == 0 ? serverBloodOxyDayData.getMinValue() : Math.min(minValue, serverBloodOxyDayData.getMinValue());
                }
                iMax = Math.max(iMax, serverBloodOxyDayData.getMaxValue());
                measurementTimes += serverBloodOxyDayData.getMeasurementTimes();
                int avgValue = serverBloodOxyDayData.getAvgValue();
                if (avgValue >= 60 && avgValue <= 100) {
                    i2 += avgValue;
                    i++;
                }
            }
        }
        if (minValue == 0) {
            minValue = iMax;
        }
        if (minValue == 0) {
            return;
        }
        serverDaysBloodOxyData.setMinValue(minValue);
        serverDaysBloodOxyData.setMaxValue(iMax);
        serverDaysBloodOxyData.setAvgValue(i == 0 ? 0 : i2 / i);
        serverDaysBloodOxyData.setTotalMeasurementTimes(measurementTimes);
        LocalHealthDataManager.getInstance().saveMultiDaysBloodOxyTotalData(serverDaysBloodOxyData);
        if (z) {
            int[] iArrYearMonthDay = DateUtil.yearMonthDay(str);
            String str3 = iArrYearMonthDay[0] + "-" + NumUtil.numberFormat(iArrYearMonthDay[1]);
            ServerBloodOxyMonthData monthBloodOxy = LocalHealthDataManager.getInstance().getMonthBloodOxy(str3);
            if (monthBloodOxy == null) {
                monthBloodOxy = new ServerBloodOxyMonthData();
                monthBloodOxy.setMonth(str3);
            }
            monthBloodOxy.setMinValue(minValue);
            monthBloodOxy.setMaxValue(iMax);
            monthBloodOxy.setUserId(serverDaysBloodOxyData.getUserId());
            monthBloodOxy.setAvgValue(i != 0 ? i2 / i : 0);
            monthBloodOxy.setTotalMeasurementTimes(measurementTimes);
            LocalHealthDataManager.getInstance().saveMonthBloodOxy(monthBloodOxy);
        }
    }

    private static void saveDayBloodOxyData(HealthSpO2 healthSpO2, List<HealthSpO2Item> list) {
        ArrayList<int[]> arrayList;
        if (list == null) {
            list = new ArrayList<>();
        }
        ServerBloodOxyDayData bloodOxyDailyDataByDate = LocalHealthDataManager.getInstance().getBloodOxyDailyDataByDate(DateUtil.format(healthSpO2.year, healthSpO2.month, healthSpO2.day));
        if (bloodOxyDailyDataByDate == null) {
            bloodOxyDailyDataByDate = new ServerBloodOxyDayData();
            bloodOxyDailyDataByDate.setDate(DateUtil.format(healthSpO2.year, healthSpO2.month, healthSpO2.day));
        }
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            bloodOxyDailyDataByDate.setSourceMac(currentDeviceInfo.mDeviceAddress);
        }
        bloodOxyDailyDataByDate.setMeasurementTimes(bloodOxyDailyDataByDate.getMeasurementTimes() + list.size());
        List listAnalysisJsonArrayToList = TextUtils.isEmpty(bloodOxyDailyDataByDate.getItems()) ? null : GsonUtil.analysisJsonArrayToList(bloodOxyDailyDataByDate.getItems(), int[][].class);
        if (listAnalysisJsonArrayToList == null) {
            arrayList = new ArrayList();
        } else {
            arrayList = new ArrayList(listAnalysisJsonArrayToList);
        }
        int i = healthSpO2.startTime;
        for (HealthSpO2Item healthSpO2Item : list) {
            if (healthSpO2Item != null) {
                i += healthSpO2Item.offset;
                if (healthSpO2Item.value <= 100 && healthSpO2Item.value >= 60) {
                    arrayList.add(new int[]{i, healthSpO2Item.value});
                }
            }
        }
        Collections.sort(arrayList, new Comparator() { // from class: com.ido.life.util.-$$Lambda$HealthDataUtil$QO8ik6LEHU2Vg6wF4FRtLcb_aP8
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return HealthDataUtil.lambda$saveDayBloodOxyData$0((int[]) obj, (int[]) obj2);
            }
        });
        bloodOxyDailyDataByDate.setItems(GsonUtil.toJson(arrayList));
        int i2 = 0;
        int iMax = 0;
        int iMin = 0;
        int i3 = 0;
        for (int[] iArr : arrayList) {
            if (iArr != null && iArr.length == 2 && iArr[1] != 0) {
                i3 += iArr[1];
                iMax = Math.max(iMax, iArr[1]);
                iMin = iMin == 0 ? iArr[1] : Math.min(iMin, iArr[1]);
                i2++;
            }
        }
        if (i2 > 0) {
            if (iMax > 100 || iMax < 60 || iMin > 100 || iMin < 60) {
                return;
            }
            bloodOxyDailyDataByDate.setMaxValue(iMax);
            bloodOxyDailyDataByDate.setMinValue(iMin);
            bloodOxyDailyDataByDate.setAvgValue(Math.round((i3 * 1.0f) / i2));
            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            calendar.setTime(healthSpO2.getDate());
            calendar.set(11, calendar.getActualMinimum(11));
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            int[] iArr2 = (int[]) arrayList.get(i2 - 1);
            calendar.add(12, iArr2[0]);
            CommonLogUtil.d(TAG, "血氧数据产生时间:" + DateUtil.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
            bloodOxyDailyDataByDate.setTimestamp(calendar.getTimeInMillis());
            bloodOxyDailyDataByDate.setLatestValue(iArr2[1]);
        } else {
            bloodOxyDailyDataByDate.setTimestamp(System.currentTimeMillis());
        }
        bloodOxyDailyDataByDate.setUploaded(false);
        LocalHealthDataManager.getInstance().saveBloodOxyDayData(bloodOxyDailyDataByDate);
    }

    static /* synthetic */ int lambda$saveDayBloodOxyData$0(int[] iArr, int[] iArr2) {
        if (iArr == null || iArr2 == null || iArr.length < 2 || iArr2.length < 2) {
            return 0;
        }
        return iArr[0] - iArr2[0];
    }

    public static List<ValueRangePair> formatBloodOxyItems(String str) {
        List listAnalysisJsonArrayToList;
        int iMin;
        ValueRangePair[] valueRangePairArr = new ValueRangePair[24];
        if (!TextUtils.isEmpty(str) && (listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList(str, int[][].class)) != null && listAnalysisJsonArrayToList.size() > 0) {
            int i = 0;
            int iMax = 0;
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < listAnalysisJsonArrayToList.size(); i4++) {
                int[] iArr = (int[]) listAnalysisJsonArrayToList.get(i4);
                if (iArr != null && iArr.length == 2 && (iMin = iArr[1]) >= 60 && iMin <= 100) {
                    int i5 = iArr[0] / 60;
                    if (i != i5) {
                        if (i3 > 0) {
                            valueRangePairArr[i] = new ValueRangePair(iMax, i2);
                        }
                        i3 = 0;
                        iMax = iMin;
                    } else {
                        iMax = Math.max(iMin, iMax);
                        iMin = i2 == 0 ? iMax : Math.min(iMin, i2);
                    }
                    if (i5 < 24) {
                        i = i5;
                    }
                    i3++;
                    if (i4 == listAnalysisJsonArrayToList.size() - 1 && i3 > 0) {
                        valueRangePairArr[i] = new ValueRangePair(iMax, iMin);
                    }
                    i2 = iMin;
                }
            }
        }
        return new ArrayList(Arrays.asList(valueRangePairArr));
    }

    public static float[] formatWalkItems(WalkDayData walkDayData, boolean z) {
        int i;
        int i2 = 0;
        if (z) {
            int[] currentHM = DateUtil.getCurrentHM();
            i = (currentHM[1] > 0 ? 1 : 0) + currentHM[0];
        } else {
            i = 24;
        }
        float[] defaultValues = getDefaultValues(24);
        if (walkDayData == null) {
            int length = defaultValues.length;
            if (z) {
                length = i;
            }
            while (i2 < length) {
                defaultValues[i2] = 1.0f;
                i2++;
            }
            return defaultValues;
        }
        String items = walkDayData.getItems();
        if (!TextUtils.isEmpty(items)) {
            List listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList(items, Integer[].class);
            if (listAnalysisJsonArrayToList != null && listAnalysisJsonArrayToList.size() > 0) {
                while (i2 < Math.min(i, defaultValues.length)) {
                    defaultValues[i2] = (i2 >= listAnalysisJsonArrayToList.size() || ((Integer) listAnalysisJsonArrayToList.get(i2)).intValue() < walkDayData.getTargetSteps()) ? 1 : 2;
                    i2++;
                }
            }
        } else {
            while (i2 < defaultValues.length) {
                defaultValues[i2] = 1.0f;
                i2++;
            }
        }
        return defaultValues;
    }

    public static void savePressure(HealthPressure healthPressure, List<HealthPressureItem> list) {
        int i;
        HealthPressure healthPressure2 = healthPressure;
        List<HealthPressureItem> list2 = list;
        if (healthPressure2 == null || list2 == null || list.size() == 0) {
            return;
        }
        long userId = RunTimeUtil.getInstance().getUserId();
        ArrayList arrayList = new ArrayList();
        String str = DateUtil.format(healthPressure.getDate(), DateUtil.DATE_FORMAT_YMD);
        CommonLogUtil.printAndSave("开始同步压力数据date=" + str);
        com.ido.life.database.model.HealthPressure healthPressureDetailByDay = GreenDaoUtil.getHealthPressureDetailByDay(userId, str);
        if (healthPressureDetailByDay != null) {
            healthPressureDetailByDay.delete();
        }
        int i2 = Integer.MAX_VALUE;
        int i3 = healthPressure2.startTime;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = Integer.MIN_VALUE;
        int i12 = 0;
        for (int size = list.size(); i12 < size; size = i) {
            HealthPressureItem healthPressureItem = list2.get(i12);
            if (healthPressureItem != null) {
                if (healthPressureItem.value != 0) {
                    i = size;
                    if (healthPressureItem.value < Constants.PRESSURE_MIN || healthPressureItem.value > Constants.PRESSURE_MAX) {
                    }
                    i12++;
                    healthPressure2 = healthPressure;
                    list2 = list;
                } else {
                    i = size;
                }
                if (i12 == 0) {
                    arrayList.add(new int[]{healthPressure2.startTime + healthPressureItem.offset, healthPressureItem.value});
                } else {
                    arrayList.add(new int[]{healthPressureItem.offset, healthPressureItem.value});
                }
                i3 += healthPressureItem.offset;
                if (healthPressureItem.value > 0) {
                    int i13 = healthPressureItem.value;
                    int iMax = Math.max(i11, healthPressureItem.value);
                    int iMin = Math.min(i2, healthPressureItem.value);
                    i5 += healthPressureItem.value;
                    i6++;
                    int i14 = AnonymousClass1.$SwitchMap$com$ido$life$enums$PressureEnum[PressureEnum.getPressureEnumByValue(healthPressureItem.value).ordinal()];
                    if (i14 == 1) {
                        i7++;
                        i2 = iMin;
                        i4 = i13;
                        i11 = iMax;
                    } else if (i14 != 2) {
                        if (i14 == 3) {
                            i9++;
                        } else if (i14 == 4) {
                            i10++;
                        }
                        i4 = i13;
                        i11 = iMax;
                        i2 = iMin;
                    } else {
                        i8++;
                        i11 = iMax;
                        i2 = iMin;
                        i4 = i13;
                    }
                }
                i12++;
                healthPressure2 = healthPressure;
                list2 = list;
            } else {
                i = size;
            }
            i8 = i8;
            i9 = i9;
            i10 = i10;
            i12++;
            healthPressure2 = healthPressure;
            list2 = list;
        }
        int i15 = i8;
        int i16 = i9;
        int i17 = i10;
        if (arrayList.size() <= 0 || i4 <= 0 || i11 > PressureEnum.HIGH.Max || i11 < PressureEnum.RELAX.Min || i2 > PressureEnum.HIGH.Max || i2 < PressureEnum.RELAX.Min) {
            return;
        }
        com.ido.life.database.model.HealthPressure healthPressure3 = new com.ido.life.database.model.HealthPressure();
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        int i18 = i7;
        calendar.setTime(healthPressure.getDate());
        calendar.set(11, calendar.getActualMinimum(11));
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(12, i3);
        healthPressure3.setTimeStamp(calendar.getTimeInMillis());
        StringBuilder sb = new StringBuilder();
        sb.append("更新压力数据，原始日期 originalDate=");
        int i19 = i5;
        int i20 = i6;
        sb.append(healthPressure.getDate().getTime());
        sb.append(",新的数据日期 calendar=");
        sb.append(calendar.getTimeInMillis());
        sb.append(",totalMinute=");
        sb.append(i3);
        CommonLogUtil.printAndSave(sb.toString());
        healthPressure3.setDate(str);
        healthPressure3.setUserId(userId);
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            healthPressure3.setSourceMac(currentDeviceInfo.mDeviceAddress);
            healthPressure3.setDeviceName(currentDeviceInfo.mDeviceName);
        }
        healthPressure3.setLastestPressure(i4);
        healthPressure3.setUploadSuccess(false);
        healthPressure3.setMaxPressure(i11);
        healthPressure3.setMinPressure(i2);
        healthPressure3.setItems(GsonUtil.toJson(arrayList));
        if (i20 > 0) {
            healthPressure3.setAvgPressure(i19 / i20);
        }
        healthPressure3.setNormalCount(i15);
        healthPressure3.setHigherCount(i17);
        healthPressure3.setMediumCount(i16);
        healthPressure3.setRelaxCount(i18);
        int i21 = i18 + i15 + i16 + i17;
        if (i21 > 0) {
            healthPressure3.setHigherRatio((i17 * 100) / i21);
            healthPressure3.setMediumRatio((i16 * 100) / i21);
            healthPressure3.setNormalRatio((i15 * 100) / i21);
            healthPressure3.setRelaxRatio(((100 - healthPressure3.getHigherRatio()) - healthPressure3.getMediumRatio()) - healthPressure3.getNormalRatio());
        }
        GreenDaoUtil.addPressureDayData(healthPressure3);
    }

    /* JADX INFO: renamed from: com.ido.life.util.HealthDataUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ido$life$enums$PressureEnum = new int[PressureEnum.values().length];

        static {
            try {
                $SwitchMap$com$ido$life$enums$PressureEnum[PressureEnum.RELAX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ido$life$enums$PressureEnum[PressureEnum.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ido$life$enums$PressureEnum[PressureEnum.MEDIUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ido$life$enums$PressureEnum[PressureEnum.HIGH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static void saveSwimData(HealthSwimming healthSwimming) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        if (healthSwimming == null) {
            return;
        }
        if (healthSwimming.year < DateUtil.getCurrentDate()[0] - 100) {
            return;
        }
        SportHealth sportHealth = new SportHealth();
        sportHealth.setUserId(RunTimeUtil.getInstance().getUserId());
        sportHealth.setDateTime(getStartTime(healthSwimming.year, healthSwimming.month, healthSwimming.day, healthSwimming.hour, healthSwimming.minute, healthSwimming.second));
        sportHealth.setStartTime(sportHealth.getDateTime());
        sportHealth.setType(0);
        if (healthSwimming.type == 1) {
            sportHealth.setType(54);
        } else {
            sportHealth.setType(55);
        }
        sportHealth.setSubType(healthSwimming.type);
        sportHealth.setNumCalories(healthSwimming.calories);
        sportHealth.setDistance(healthSwimming.distance);
        sportHealth.setAverageSWOLF(healthSwimming.averageSWOLF);
        sportHealth.setSwimmingPosture(healthSwimming.swimmingPosture);
        sportHealth.setPoolDistance(healthSwimming.poolDistance);
        sportHealth.setTrips(healthSwimming.trips);
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            sportHealth.setSourceMac(currentDeviceInfo.mDeviceAddress);
        }
        sportHealth.setAvgFrequency(healthSwimming.avg_frequency);
        sportHealth.setLoadDetail(true);
        sportHealth.setSourceType(2);
        if (healthSwimming.items == null || healthSwimming.items.size() <= 0) {
            i = 10000;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
        } else {
            SportSwimSwolf sportSwimSwolf = new SportSwimSwolf();
            ArrayList arrayList = new ArrayList();
            i = 10000;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
            for (HealthSwimmingDetail healthSwimmingDetail : healthSwimming.items) {
                i2 = i2 + healthSwimmingDetail.difference_time + healthSwimmingDetail.stop_time + healthSwimmingDetail.duration;
                if (healthSwimmingDetail.speed > i4) {
                    i4 = healthSwimmingDetail.speed;
                }
                if (healthSwimmingDetail.frequency > i5) {
                    i5 = healthSwimmingDetail.frequency;
                }
                if (healthSwimmingDetail.swolf < i) {
                    i = healthSwimmingDetail.swolf;
                }
                if (healthSwimmingDetail.swolf > i3) {
                    i3 = healthSwimmingDetail.swolf;
                }
                SportSwimItem sportSwimItem = new SportSwimItem();
                sportSwimItem.setDuration(healthSwimmingDetail.duration);
                sportSwimItem.setStrokesNumber(healthSwimmingDetail.strokesNumber);
                sportSwimItem.setSwolf(healthSwimmingDetail.swolf);
                sportSwimItem.setFrequency(healthSwimmingDetail.frequency);
                sportSwimItem.setDifferenceTime(healthSwimmingDetail.difference_time);
                sportSwimItem.setStopTime(healthSwimmingDetail.stop_time);
                sportSwimItem.setSpeed(healthSwimmingDetail.speed);
                arrayList.add(sportSwimItem);
                sportSwimSwolf.setItems(GsonUtil.toJson(arrayList));
                sportHealth.setSwolf(sportSwimSwolf);
            }
        }
        sportHealth.setTotalSeconds(healthSwimming.duration);
        long jConvTimeYmdhmsToLong = com.ido.common.utils.TimeUtil.convTimeYmdhmsToLong(sportHealth.getStartTime()) + ((long) i2);
        sportHealth.setEndTime(TimeUtil.timeStamp2Date(jConvTimeYmdhmsToLong, "yyyy-MM-dd HH:mm:ss"));
        sportHealth.setTimestamp(jConvTimeYmdhmsToLong);
        sportHealth.setBestSWOLF(i);
        sportHealth.setmaxSwolf(i3);
        sportHealth.setAvgPace(healthSwimming.avg_speed);
        sportHealth.setMinPace(i4);
        sportHealth.setMaxFrequency(i5);
        String json = GsonUtil.toJson(healthSwimming.items);
        if (json == null) {
            json = "";
        }
        sportHealth.setSwimmingDetailStr(json);
        sportHealth.setTotalStrokesNumber(healthSwimming.getTotalStrokesNumber());
        sportHealth.setIsUploaded(false);
        SportLogHelper.saveSportLog(TAG, sportHealth.toString());
        GreenDaoUtil.saveActivityData(sportHealth);
    }

    private static String getStartTime(int i, int i2, int i3, int i4, int i5, int i6) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i);
        stringBuffer.append("-");
        if (i2 < 10) {
            stringBuffer.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + i2);
        } else {
            stringBuffer.append(i2);
        }
        stringBuffer.append("-");
        if (i3 < 10) {
            stringBuffer.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + i3);
        } else {
            stringBuffer.append(i3);
        }
        stringBuffer.append(" ");
        if (i4 < 10) {
            stringBuffer.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + i4);
        } else {
            stringBuffer.append(i4);
        }
        stringBuffer.append(":");
        if (i5 < 10) {
            stringBuffer.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + i5);
        } else {
            stringBuffer.append(i5);
        }
        stringBuffer.append(":");
        if (i6 < 10) {
            stringBuffer.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + i6);
        } else {
            stringBuffer.append(i6);
        }
        return stringBuffer.toString();
    }

    public static float[] getDefaultValues(int i) {
        float[] fArr = new float[i];
        for (int i2 = 0; i2 < fArr.length; i2++) {
            fArr[i2] = -2.0f;
        }
        return fArr;
    }

    public static void saveGpsDataV3(HealthGpsV3 healthGpsV3) {
        String str;
        String str2;
        if (healthGpsV3 == null || healthGpsV3.items == null || healthGpsV3.items.size() == 0) {
            return;
        }
        long userId = RunTimeUtil.getInstance().getUserId();
        String strConvTimeDetail = com.ido.common.utils.TimeUtil.convTimeDetail(DateUtil.string2Date(healthGpsV3.year + "-" + healthGpsV3.month + "-" + healthGpsV3.day + " " + healthGpsV3.hour + ":" + healthGpsV3.minute + ":" + healthGpsV3.second, "yyyy-MM-dd HH:mm:ss").getTime());
        String sportLogPath = LogPathImpl.getInstance().getSportLogPath();
        StringBuilder sb = new StringBuilder();
        String str3 = "saveGpsDataV3: ";
        sb.append("saveGpsDataV3: ");
        sb.append(strConvTimeDetail);
        String string = sb.toString();
        String str4 = TAG;
        CommonLogUtil.printAndSave(sportLogPath, TAG, string);
        SportGpsData sportGpsData = new SportGpsData();
        JSONArray jSONArray = new JSONArray();
        SportGps sportGps = new SportGps();
        int i = 0;
        while (i < healthGpsV3.items.size()) {
            HealthGpsItemV3 healthGpsItemV3 = healthGpsV3.items.get(i);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), str4, str3 + healthGpsItemV3.latitude + AppInfo.DELIM + healthGpsItemV3.longitude);
            if (healthGpsItemV3.latitude.doubleValue() == 0.0d || healthGpsItemV3.longitude.doubleValue() == 0.0d) {
                str = str3;
                str2 = str4;
            } else {
                JSONArray jSONArray2 = new JSONArray();
                str = str3;
                str2 = str4;
                if (!GpsCoordinateUtils.isOutOfChina(healthGpsItemV3.latitude.doubleValue(), healthGpsItemV3.longitude.doubleValue(), false)) {
                    double[] dArrCalWGS84toGCJ02 = GpsCoordinateUtils.calWGS84toGCJ02(healthGpsItemV3.latitude.doubleValue(), healthGpsItemV3.longitude.doubleValue());
                    jSONArray2.put(String.valueOf(dArrCalWGS84toGCJ02[0]));
                    jSONArray2.put(String.valueOf(dArrCalWGS84toGCJ02[1]));
                } else {
                    jSONArray2.put(String.valueOf(healthGpsItemV3.latitude));
                    jSONArray2.put(String.valueOf(healthGpsItemV3.longitude));
                }
                jSONArray2.put(String.valueOf(DateUtil.getLongFromDateStr(strConvTimeDetail) + ((long) (i * 2000))));
                jSONArray.put(jSONArray2);
            }
            i++;
            str3 = str;
            str4 = str2;
        }
        String str5 = str3;
        String str6 = str4;
        sportGps.setItems(jSONArray.toString());
        sportGpsData.setUserId(RunTimeUtil.getInstance().getUserId());
        sportGpsData.setTimeMillis(com.ido.common.utils.TimeUtil.convTimeYmdhmsToLong(strConvTimeDetail));
        sportGpsData.setGpsData(sportGps);
        sportGpsData.setUserId(userId);
        LocalHealthDataManager.getInstance().savAppGpsData(sportGpsData);
        SportHealth activityData = LocalHealthDataManager.getInstance().getActivityData(strConvTimeDetail);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), str6, str5 + strConvTimeDetail + "空");
        if (activityData != null) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), str6, str5 + activityData.toString());
            activityData.setIsLocus(1);
            activityData.setGpsSourceType(2);
            GreenDaoUtil.getDaoSession().update(activityData);
        }
    }

    public static boolean saveHealthNoiseData(HealthNoise healthNoise) {
        int i;
        if (healthNoise == null || healthNoise.items == null || healthNoise.items.size() == 0) {
            return false;
        }
        String dateYMDTime = TimeUtil.getDateYMDTime(healthNoise.year, healthNoise.month, healthNoise.day);
        Date dateString2Date = DateUtil.string2Date(dateYMDTime, DateUtil.DATE_FORMAT_YMD);
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(dateString2Date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        SportLogHelper.saveSportLog(TAG, "saveHealthNoiseData: " + dateYMDTime);
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i2 < healthNoise.items.size()) {
            HealthNoiseItem healthNoiseItem = healthNoise.items.get(i2);
            int i9 = i7 + healthNoiseItem.offset;
            if (healthNoiseItem.offset == 0) {
                i = i9;
            } else {
                int iMin = Math.min(healthNoiseItem.value, 120);
                if (iMin > 0) {
                    i8 = iMin;
                }
                i = i9;
                arrayList.add(new int[]{healthNoiseItem.offset, iMin});
                if (iMin >= 90) {
                    i3 += healthNoiseItem.offset;
                } else if (iMin >= 80) {
                    i4 += healthNoiseItem.offset;
                } else if (iMin >= 75) {
                    i5 += healthNoiseItem.offset;
                } else if (iMin > 0) {
                    i6 += healthNoiseItem.offset;
                }
            }
            i2++;
            i7 = i;
        }
        int i10 = i3 + i4 + i5 + i6;
        calendar.add(13, i7);
        HealthVolumeData healthVolumeDate = GreenDaoUtil.getHealthVolumeDate(RunTimeUtil.getInstance().getUserId(), dateYMDTime);
        if (healthVolumeDate == null) {
            healthVolumeDate = new HealthVolumeData();
        }
        healthVolumeDate.setUserId(RunTimeUtil.getInstance().getUserId());
        healthVolumeDate.setHasUpdate(false);
        healthVolumeDate.setTotalSeconds(i10);
        healthVolumeDate.setSuperHighLevelSeconds(i3);
        healthVolumeDate.setHighLevelSeconds(i4);
        healthVolumeDate.setNormalLevelSeconds(i5);
        healthVolumeDate.setLowLevelSeconds(i6);
        healthVolumeDate.setAvgValue(healthNoise.avg_noise);
        healthVolumeDate.setMaxValue(healthNoise.max_noise);
        healthVolumeDate.setMinValue(healthNoise.min_noise);
        healthVolumeDate.setInterval(healthNoise.interval_mode);
        healthVolumeDate.setLatestValue(i8);
        healthVolumeDate.setTimestamp(calendar.getTimeInMillis());
        healthVolumeDate.setItems(GsonUtil.toJson(arrayList));
        healthVolumeDate.setDate(dateYMDTime);
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            healthVolumeDate.setSourceMac(currentDeviceInfo.mDeviceAddress);
            healthVolumeDate.setDeviceName(currentDeviceInfo.mDeviceName);
        }
        SportLogHelper.saveSportLog(TAG, "saveHealthNoiseData: " + healthVolumeDate.toString());
        GreenDaoUtil.saveHealthVolumeData(healthVolumeDate);
        return true;
    }

    public static boolean hasHistoryHealthVolume() {
        return LocalHealthDataManager.getInstance().hasHistoryHealthVolume();
    }
}