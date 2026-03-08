package com.baidu.mapapi.cloud;

import androidx.core.app.NotificationCompat;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseSearchResult {
    public static final int STATUS_CODE_NETWORK_ERROR = -3;
    public static final int STATUS_CODE_NETWORK_TIME_OUT = -2;
    public static final int STATUS_CODE_PARAM_ERROR = 2;
    public static final int STATUS_CODE_PERMISSION_UNFINISHED = -4;
    public static final int STATUS_CODE_RESULT_NOTFOUND = -1;
    public static final int STATUS_CODE_SEARVER_ERROR = 1;
    public static final int STATUS_CODE_SUCCEED = 0;
    public int size;
    public int status = -1;
    public int total;

    public void parseFromJSON(JSONObject jSONObject) throws JSONException {
        this.status = jSONObject.optInt(NotificationCompat.CATEGORY_STATUS);
        int i = this.status;
        if (i == 1233 || i == 2) {
            this.status = 2;
        }
        this.size = jSONObject.optInt("size");
        this.total = jSONObject.optInt("total");
    }
}