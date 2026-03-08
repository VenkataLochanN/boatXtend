package com.ido.life;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.util.ConnectLogHelper;
import com.ido.life.util.DateUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.util.WeatherHelper;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class VeryFitLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    private static int count;
    private static VeryFitLifecycleCallbacks sLifecycleCallbacks;
    private List<Activity> activities = new ArrayList();

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    private VeryFitLifecycleCallbacks() {
    }

    public List<Activity> getActivities() {
        return this.activities;
    }

    public boolean isForeground() {
        return count > 0;
    }

    public static VeryFitLifecycleCallbacks getInstance() {
        if (sLifecycleCallbacks == null) {
            sLifecycleCallbacks = new VeryFitLifecycleCallbacks();
        }
        return sLifecycleCallbacks;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (this.activities.contains(activity)) {
            return;
        }
        this.activities.add(activity);
    }

    public Activity getTopActivity() {
        List<Activity> list = this.activities;
        if (list == null || list.size() == 0) {
            return null;
        }
        return this.activities.get(r0.size() - 1);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        if (count == 0) {
            CommonLogUtil.d(">>>>>>>>>>>>>>>>>>>切到前台  lifecycle");
            ConnectLogHelper.saveLog("VeryFitLifecycleCallbacks", "APP enters the front desk");
            long timeOfApp2Background = SPHelper.getTimeOfApp2Background();
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (!DateUtil.format(timeOfApp2Background, DateUtil.DATE_FORMAT_YMD).equals(DateUtil.format(jCurrentTimeMillis, DateUtil.DATE_FORMAT_YMD))) {
                EventBusHelper.post(105);
            }
            if (jCurrentTimeMillis - timeOfApp2Background > 600000) {
                WeatherHelper.requestWeatherFromServer();
                SPHelper.saveTimeOfApp2Background(jCurrentTimeMillis);
            }
        }
        count++;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        Runtime.getRuntime().gc();
        System.gc();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        count--;
        if (count == 0) {
            CommonLogUtil.d(">>>>>>>>>>>>>>>>>>>切到后台  lifecycle" + activity);
            ConnectLogHelper.saveLog("VeryFitLifecycleCallbacks", "APP enters the background");
            SPHelper.saveTimeOfApp2Background(System.currentTimeMillis());
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        this.activities.remove(activity);
    }
}