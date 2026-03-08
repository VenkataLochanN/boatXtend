package com.ido.life.boatservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.constants.Constants;
import com.ido.life.util.AlarmManagerUtils;
import com.ido.life.util.DateUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.util.WeatherHelper;
import com.ido.life.util.eventbus.EventBusHelper;

/* JADX INFO: loaded from: classes2.dex */
public class WeatherSendCheckService extends IntentService {
    private static final String TAG = "WeatherSendCheckService";

    public WeatherSendCheckService() {
        super(TAG);
    }

    public WeatherSendCheckService(String str) {
        super(str);
    }

    @Override // android.app.IntentService, android.app.Service
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        try {
            if (System.currentTimeMillis() - SPHelper.getTimeOfWeatherSend() <= 7200000 || DateUtil.getTodayHour() < 6 || DateUtil.getTodayHour() > 23) {
                return;
            }
            saveLog("超过2个小时，同步天气");
            WeatherHelper.requestWeatherFromServer();
            saveLog("同步天气时，同步世界时钟");
            EventBusHelper.post(Constants.EventConstants.EVENT_REFRESH_WORLD_TIME);
        } catch (Exception unused) {
        }
    }

    @Override // android.app.IntentService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        saveLog("oncommond");
        AlarmManagerUtils.getInstance(getApplicationContext()).getUpAlarmManagerWorkOnOthers();
        return super.onStartCommand(intent, i, i2);
    }

    private void saveLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getWeatherLogPath(), TAG, str);
    }
}