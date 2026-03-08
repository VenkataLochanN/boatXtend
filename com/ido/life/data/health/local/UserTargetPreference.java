package com.ido.life.data.health.local;

import android.content.Context;
import com.ido.common.IdoApp;
import com.ido.common.base.BasePreference;
import com.ido.life.database.model.UserTargetNew;

/* JADX INFO: loaded from: classes2.dex */
public class UserTargetPreference extends BasePreference {
    private static final String NAME = "user_target_preference";
    private static final String TAG_CALORIES = "calories";
    private static final String TAG_DATE = "date";
    private static final String TAG_DISTANCES = "distances";
    private static final String TAG_ID = "id";
    private static final String TAG_NUM_STEPS = "numSteps";
    private static final String TAG_SPORT_TIMES = "sportTimes";
    private static final String TAG_WEIGHT = "weight";

    private static Context getContext() {
        return IdoApp.getAppContext();
    }

    public static UserTargetNew getUserTarget() {
        UserTargetNew userTargetNew = new UserTargetNew();
        userTargetNew.setUserId(getLong(getContext(), NAME, TAG_ID, -1L));
        userTargetNew.setStep(getInt(getContext(), NAME, TAG_NUM_STEPS, -1));
        userTargetNew.setWeight(getInt(getContext(), NAME, TAG_WEIGHT, -1));
        return userTargetNew;
    }

    public static void savaUserTarget(UserTargetNew userTargetNew) {
        if (userTargetNew == null) {
            userTargetNew = new UserTargetNew();
        }
        saveLong(getContext(), NAME, TAG_ID, userTargetNew.getUserId());
        saveLong(getContext(), NAME, TAG_DATE, userTargetNew.getCreateTime());
        saveInt(getContext(), NAME, TAG_NUM_STEPS, userTargetNew.getStep());
        saveInt(getContext(), NAME, TAG_WEIGHT, userTargetNew.getWeightInt());
    }

    public static void clear() {
        clear(IdoApp.getAppContext(), NAME);
    }
}