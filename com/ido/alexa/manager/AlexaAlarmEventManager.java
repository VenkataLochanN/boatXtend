package com.ido.alexa.manager;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.ido.alexa.AlexaConstant;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.alexa.callbacks.AsyncCallback;
import com.ido.alexa.callbacks.ImplAsyncCallback;
import com.ido.alexa.data.AvsAlarmItem;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.alexa.util.AsyncTaskUtil;
import com.ido.alexa.util.ClientUtil;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaAlarmEventManager {
    private static AlexaAlarmEventManager instance;
    private String mEventUrl = AlexaConstant.ALARM_NA_URL;
    private Gson mGson = new Gson();
    private String mToken;

    private AlexaAlarmEventManager() {
    }

    public static AlexaAlarmEventManager getInstance() {
        if (instance == null) {
            instance = new AlexaAlarmEventManager();
        }
        return instance;
    }

    public void setAlarmEventUrl(String str) {
        if (str.contains("na.")) {
            this.mEventUrl = AlexaConstant.ALARM_NA_URL;
        } else if (str.contains("fe.")) {
            this.mEventUrl = AlexaConstant.ALARM_FE_URL;
        } else {
            this.mEventUrl = AlexaConstant.ALARM_EU_URL;
        }
    }

    public void setToken(String str) {
        this.mToken = str;
    }

    private boolean checkToken() {
        boolean z;
        if (TextUtils.isEmpty(this.mToken)) {
            AlexaLogUtil.d("token is null");
            z = true;
        } else {
            z = false;
        }
        return !z;
    }

    public void getAlarmByToken(String str, final AsyncCallback<AvsAlarmItem.AlexaAlarmsBean, Throwable> asyncCallback) {
        AlexaLogUtil.d("---------getAlarmByToken");
        if (!checkToken()) {
            if (asyncCallback != null) {
                asyncCallback.failure(new Throwable("token is null"));
                return;
            }
            return;
        }
        Request.Builder builder = new Request.Builder();
        builder.url(this.mEventUrl + "/v1/alerts/alarms/" + str);
        builder.addHeader("Authorization", this.mToken);
        builder.get();
        sendAlarmEvent(builder, new ImplAsyncCallback<String, Throwable>() { // from class: com.ido.alexa.manager.AlexaAlarmEventManager.1
            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void success(String str2) {
                if (asyncCallback != null) {
                    if (!TextUtils.isEmpty(str2)) {
                        asyncCallback.success(AlexaAlarmEventManager.this.mGson.fromJson(str2, AvsAlarmItem.AlexaAlarmsBean.class));
                    } else {
                        asyncCallback.failure(new Throwable("result is empty"));
                    }
                }
            }

            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void failure(Throwable th) {
                AsyncCallback asyncCallback2 = asyncCallback;
                if (asyncCallback2 != null) {
                    asyncCallback2.failure(th);
                }
            }
        });
    }

    public void getAllAlarms(final AsyncCallback<AvsAlarmItem, Throwable> asyncCallback) {
        AlexaLogUtil.d("---------getAllAlarms");
        if (!checkToken()) {
            if (asyncCallback != null) {
                asyncCallback.failure(new Throwable("token is null"));
                return;
            }
            return;
        }
        Request.Builder builder = new Request.Builder();
        builder.url(this.mEventUrl + "/v1/alerts/alarms?endpointId=@self");
        builder.addHeader("Authorization", this.mToken);
        builder.get();
        sendAlarmEvent(builder, new ImplAsyncCallback<String, Throwable>() { // from class: com.ido.alexa.manager.AlexaAlarmEventManager.2
            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void success(String str) {
                if (asyncCallback != null) {
                    if (!TextUtils.isEmpty(str)) {
                        asyncCallback.success(AlexaAlarmEventManager.this.mGson.fromJson(str, AvsAlarmItem.class));
                    } else {
                        asyncCallback.failure(new Throwable("result is empty"));
                    }
                }
            }

            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void failure(Throwable th) {
                AsyncCallback asyncCallback2 = asyncCallback;
                if (asyncCallback2 != null) {
                    asyncCallback2.failure(th);
                }
            }
        });
    }

    public void deleteAllAlarms(AsyncCallback<String, Throwable> asyncCallback) {
        AlexaLogUtil.d("----------deleteAllAlarms");
        if (!checkToken()) {
            if (asyncCallback != null) {
                asyncCallback.failure(new Throwable("token is null"));
                return;
            }
            return;
        }
        Request.Builder builder = new Request.Builder();
        builder.url(this.mEventUrl + "/v1/alerts/alarms?endpointId=@self");
        builder.addHeader("Authorization", this.mToken);
        builder.delete();
        sendAlarmEvent(builder, asyncCallback);
    }

    public void deleteAlarmByToken(String str, AsyncCallback<String, Throwable> asyncCallback) {
        AlexaLogUtil.d("--------deleteAlarmByToken=" + str);
        if (!checkToken()) {
            if (asyncCallback != null) {
                asyncCallback.failure(new Throwable("token is null"));
                return;
            }
            return;
        }
        Request.Builder builder = new Request.Builder();
        builder.url(this.mEventUrl + "/v1/alerts/alarms/" + str);
        builder.addHeader("Authorization", this.mToken);
        builder.delete();
        sendAlarmEvent(builder, asyncCallback);
    }

    public void closeAlarm(String str, final AsyncCallback<AvsAlarmItem.AlexaAlarmsBean, Throwable> asyncCallback) {
        if (!checkToken()) {
            if (asyncCallback != null) {
                asyncCallback.failure(new Throwable("token is null"));
                return;
            }
            return;
        }
        Request.Builder builder = new Request.Builder();
        builder.url(this.mEventUrl + "/v1/alerts/alarms/" + str + "/cancel");
        builder.addHeader("Authorization", this.mToken);
        builder.put(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), ""));
        sendAlarmEvent(builder, new ImplAsyncCallback<String, Throwable>() { // from class: com.ido.alexa.manager.AlexaAlarmEventManager.3
            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void success(String str2) {
                if (asyncCallback != null) {
                    if (!TextUtils.isEmpty(str2)) {
                        asyncCallback.success(AlexaAlarmEventManager.this.mGson.fromJson(str2, AvsAlarmItem.AlexaAlarmsBean.class));
                    } else {
                        asyncCallback.failure(new Throwable("result is empty"));
                    }
                }
            }

            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void failure(Throwable th) {
                AsyncCallback asyncCallback2 = asyncCallback;
                if (asyncCallback2 != null) {
                    asyncCallback2.failure(th);
                }
            }
        });
    }

    public void openAlarm(String str, final AsyncCallback<AvsAlarmItem.AlexaAlarmsBean, Throwable> asyncCallback) {
        AlexaLogUtil.d("----------openAlarm alarmToken=" + str);
        if (!checkToken()) {
            if (asyncCallback != null) {
                asyncCallback.failure(new Throwable("token is null"));
                return;
            }
            return;
        }
        Request.Builder builder = new Request.Builder();
        builder.url(this.mEventUrl + "/v1/alerts/alarms/" + str + "/activate");
        builder.addHeader("Authorization", this.mToken);
        builder.put(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), ""));
        sendAlarmEvent(builder, new ImplAsyncCallback<String, Throwable>() { // from class: com.ido.alexa.manager.AlexaAlarmEventManager.4
            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void success(String str2) {
                if (asyncCallback != null) {
                    if (!TextUtils.isEmpty(str2)) {
                        asyncCallback.success(AlexaAlarmEventManager.this.mGson.fromJson(str2, AvsAlarmItem.AlexaAlarmsBean.class));
                    } else {
                        asyncCallback.failure(new Throwable("result is empty"));
                    }
                }
            }

            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void failure(Throwable th) {
                AsyncCallback asyncCallback2 = asyncCallback;
                if (asyncCallback2 != null) {
                    asyncCallback2.failure(th);
                }
            }
        });
    }

    public void createAlarm(Date date, final AsyncCallback<AvsAlarmItem.AlexaAlarmsBean, Throwable> asyncCallback) {
        AlexaLogUtil.d("---------createAlarm=" + date.toString());
        if (!checkToken()) {
            if (asyncCallback != null) {
                asyncCallback.failure(new Throwable("token is null"));
                return;
            }
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        String str = simpleDateFormat.format(date);
        Request.Builder builder = new Request.Builder();
        builder.url(this.mEventUrl + "/v1/alerts/alarms");
        builder.addHeader("Authorization", this.mToken);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        String str2 = "{\"endpointId\": \"@self\",\"trigger\": {\"scheduledTime\":\"" + str + "\"}}";
        AlexaLogUtil.d("createAlarm json=" + str2);
        builder.post(RequestBody.create(mediaType, str2));
        sendAlarmEvent(builder, new ImplAsyncCallback<String, Throwable>() { // from class: com.ido.alexa.manager.AlexaAlarmEventManager.5
            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void success(String str3) {
                if (asyncCallback != null) {
                    if (!TextUtils.isEmpty(str3)) {
                        asyncCallback.success(AlexaAlarmEventManager.this.mGson.fromJson(str3, AvsAlarmItem.AlexaAlarmsBean.class));
                    } else {
                        asyncCallback.failure(new Throwable("result is empty"));
                    }
                }
            }

            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void failure(Throwable th) {
                AsyncCallback asyncCallback2 = asyncCallback;
                if (asyncCallback2 != null) {
                    asyncCallback2.failure(th);
                }
            }
        });
    }

    public void createRepeatAlarm(Date date, AlexaCustomSkillConstant.Frequency frequency, List<String> list, final AsyncCallback<AvsAlarmItem.AlexaAlarmsBean, Throwable> asyncCallback) {
        AlexaLogUtil.d("-------createRepeatAlarm=" + date.toString());
        if (!checkToken()) {
            if (asyncCallback != null) {
                asyncCallback.failure(new Throwable("token is null"));
                return;
            }
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        String str = simpleDateFormat.format(date);
        Request.Builder builder = new Request.Builder();
        builder.url(this.mEventUrl + "/v1/alerts/alarms");
        builder.addHeader("Authorization", this.mToken);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        String str2 = "{\"endpointId\": \"@self\",\"trigger\": {\"scheduledTime\": \"" + str + "\",\"recurrence\" : {\"freq\" : \"" + frequency.name() + "\",\"byDay\": " + this.mGson.toJson(list) + ",\"interval\": 1}}}";
        AlexaLogUtil.d("createRepeatAlarm json=" + str2);
        builder.post(RequestBody.create(mediaType, str2));
        sendAlarmEvent(builder, new ImplAsyncCallback<String, Throwable>() { // from class: com.ido.alexa.manager.AlexaAlarmEventManager.6
            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void success(String str3) {
                if (asyncCallback != null) {
                    if (!TextUtils.isEmpty(str3)) {
                        asyncCallback.success(AlexaAlarmEventManager.this.mGson.fromJson(str3, AvsAlarmItem.AlexaAlarmsBean.class));
                    } else {
                        asyncCallback.failure(new Throwable("result is empty"));
                    }
                }
            }

            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void failure(Throwable th) {
                AsyncCallback asyncCallback2 = asyncCallback;
                if (asyncCallback2 != null) {
                    asyncCallback2.failure(th);
                }
            }
        });
    }

    private void sendAlarmEvent(final Request.Builder builder, final AsyncCallback<String, Throwable> asyncCallback) {
        new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.alexa.manager.AlexaAlarmEventManager.7
            @Override // com.ido.alexa.util.AsyncTaskUtil.IAsyncTaskCallBack
            public void onPostExecute(Object obj) {
            }

            @Override // com.ido.alexa.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... strArr) throws Exception {
                Response responseExecute;
                ResponseBody responseBodyBody;
                Request requestBuild = builder.build();
                try {
                    responseExecute = ClientUtil.getTLS12OkHttpClient().newCall(requestBuild).execute();
                    AlexaLogUtil.printAndSave(requestBuild.url().toString() + " response.code()==" + responseExecute.code());
                    responseBodyBody = responseExecute.body();
                } catch (IOException e2) {
                    e2.printStackTrace();
                    AsyncCallback asyncCallback2 = asyncCallback;
                    if (asyncCallback2 != null) {
                        asyncCallback2.failure(e2);
                    }
                }
                if (responseBodyBody == null) {
                    if (asyncCallback != null) {
                        if (responseExecute.code() == 204) {
                            asyncCallback.success("");
                        } else {
                            asyncCallback.failure(new Throwable(responseExecute.code() + ""));
                        }
                    }
                    return null;
                }
                String strString = responseBodyBody.string();
                if (asyncCallback != null) {
                    asyncCallback.success(strString);
                }
                AlexaLogUtil.printAndSave("body==" + strString);
                return null;
            }
        }).execute("");
    }
}