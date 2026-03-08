package com.ido.alexa.manager;

import com.ido.alexa.bean.EndpointIDBean;
import com.ido.alexa.callbacks.AlexaSettingCallBack;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaEndpointIdDefine {
    public static final String BRIGHTNESS = "brightness";
    private static final String CALORIE_STATISTICS = "Calorie Statistics Sensor";
    private static final String CURRENT_HR = "Heart Rate Sensor";
    private static final String HEALTHDATA = "Health Data";
    private static final String KILOMETER_STATISTICST = "Kilometer Statistics Sensor";
    public static final String MONTHLY_AVG_HR = "Monthly Heartrate Sensor";
    private static final String PEDOMETER = "Pedometer";
    private static final String RUNNING_COUNT = "Running Count Sensor";
    private static final String SLEEP_SCORE_SENSOR = "Sleep Score Sensor";
    private static final String SPO2_SENSOR = "SpO2 Sensor";
    public static final String SPORT = "Sport";
    private static final String SWIMMING_COUNT = "Swimming Count Sensor";
    public static final String SWIMMING_STATISTICS = "Swimming Statistics Sensor";
    public static final String WEEKLY_AVG_HR = "Weekly Heartrate Sensor";
    private static final String WORKOUT_COUNT = "Workout Count Sensor";
    public static final String WORKOUT_HISTORY = "Workout History";
    public static final String YEARLY_AVG_HR = "Yearly Heartrate Sensor";

    public static List<EndpointIDBean> getEndpoints() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new EndpointIDBean(BRIGHTNESS, AlexaSettingCallBack.AlexaSettingType.BRIGHTNESS));
        arrayList.add(new EndpointIDBean(CURRENT_HR, AlexaSettingCallBack.AlexaSettingType.HEARTRATE));
        arrayList.add(new EndpointIDBean(SPO2_SENSOR, AlexaSettingCallBack.AlexaSettingType.BLOOD_OXYGEN));
        arrayList.add(new EndpointIDBean(SLEEP_SCORE_SENSOR, AlexaSettingCallBack.AlexaSettingType.SLEEP_SCORE));
        arrayList.add(new EndpointIDBean(PEDOMETER, AlexaSettingCallBack.AlexaSettingType.STEP_COUNT));
        arrayList.add(new EndpointIDBean(WEEKLY_AVG_HR, AlexaSettingCallBack.AlexaSettingType.LAST_WEEKLY_AVG_HR));
        arrayList.add(new EndpointIDBean(MONTHLY_AVG_HR, AlexaSettingCallBack.AlexaSettingType.LAST_MONTHLY_AVG_HR));
        arrayList.add(new EndpointIDBean(YEARLY_AVG_HR, AlexaSettingCallBack.AlexaSettingType.LAST_YEARLY_AVG_HR));
        arrayList.add(new EndpointIDBean(WORKOUT_COUNT, AlexaSettingCallBack.AlexaSettingType.TODAY_WORKOUT_COUNT));
        arrayList.add(new EndpointIDBean(SWIMMING_COUNT, AlexaSettingCallBack.AlexaSettingType.TODAY_SWIMMING_COUNT));
        arrayList.add(new EndpointIDBean(RUNNING_COUNT, AlexaSettingCallBack.AlexaSettingType.TODAY_RUNNING_COUNT));
        arrayList.add(new EndpointIDBean(CALORIE_STATISTICS, AlexaSettingCallBack.AlexaSettingType.TODAY_CALORIE_STATISTICS));
        arrayList.add(new EndpointIDBean(KILOMETER_STATISTICST, AlexaSettingCallBack.AlexaSettingType.TODAY_KILOMETER_STATISTICST));
        arrayList.add(new EndpointIDBean(SWIMMING_STATISTICS, AlexaSettingCallBack.AlexaSettingType.TODAY_SWIMMING_STATISTICS));
        arrayList.add(new EndpointIDBean(HEALTHDATA, AlexaSettingCallBack.AlexaSettingType.TODAY_CALORIE_STATISTICS));
        arrayList.add(new EndpointIDBean(WORKOUT_HISTORY, AlexaSettingCallBack.AlexaSettingType.WORKOUT_HISTORY));
        return arrayList;
    }
}