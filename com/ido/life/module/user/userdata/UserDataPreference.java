package com.ido.life.module.user.userdata;

import com.ido.common.IdoApp;
import com.ido.common.base.BasePreference;

/* JADX INFO: loaded from: classes3.dex */
public class UserDataPreference extends BasePreference {
    private static final String NAME = "user_info_preference";
    private static final String USER_DAY = "user_day";
    private static final String USER_HEIGHT = "user_height";
    private static final String USER_HEIGHT_UNIT = "user_height_unit";
    private static final String USER_MONTH = "user_month";
    private static final String USER_SEX = "user_sex";
    private static final String USER_WEIGHT = "user_weight";
    private static final String USER_WEIGHT_UNIT = "user_weight_unit";
    private static final String USER_YEAR = "user_year";

    public static void saveUserData(UserData userData) {
        if (userData == null) {
            userData = new UserData();
        }
        saveInt(IdoApp.getAppContext(), NAME, USER_SEX, userData.getSex());
        saveInt(IdoApp.getAppContext(), NAME, USER_YEAR, userData.getYear());
        saveInt(IdoApp.getAppContext(), NAME, USER_MONTH, userData.getMonth());
        saveInt(IdoApp.getAppContext(), NAME, USER_DAY, userData.getDay());
        saveInt(IdoApp.getAppContext(), NAME, USER_HEIGHT, userData.getHegiht());
        saveInt(IdoApp.getAppContext(), NAME, USER_HEIGHT_UNIT, userData.getHeightUnit());
        saveInt(IdoApp.getAppContext(), NAME, USER_WEIGHT, userData.getWeight());
        saveInt(IdoApp.getAppContext(), NAME, USER_WEIGHT_UNIT, userData.getWeightUnit());
    }

    public static UserData getmUserData() {
        UserData userData = new UserData();
        userData.setSex(getInt(IdoApp.getAppContext(), NAME, USER_SEX));
        userData.setYear(getInt(IdoApp.getAppContext(), NAME, USER_YEAR));
        userData.setMonth(getInt(IdoApp.getAppContext(), NAME, USER_MONTH));
        userData.setDay(getInt(IdoApp.getAppContext(), NAME, USER_DAY));
        userData.setHegiht(getInt(IdoApp.getAppContext(), NAME, USER_HEIGHT));
        userData.setHeightUnit(getInt(IdoApp.getAppContext(), NAME, USER_HEIGHT_UNIT));
        userData.setWeight(getInt(IdoApp.getAppContext(), NAME, USER_WEIGHT));
        userData.setWeightUnit(getInt(IdoApp.getAppContext(), NAME, USER_WEIGHT_UNIT));
        return userData;
    }

    public static void clear() {
        clear(IdoApp.getAppContext(), NAME);
    }
}