package com.ido.life.module.user.set.data;

import android.content.Context;
import com.ido.common.IdoApp;
import com.ido.common.base.BasePreference;

/* JADX INFO: loaded from: classes3.dex */
public class StravaPreference extends BasePreference {
    private static final String NAME = "google_fit_preference";
    private static final String TAG_CLIENT_ID = "client_id";
    private static final String TAG_CLIENT_SECRET = "client_secret";
    private static final String TAG_GRANT_TYPE = "grant_type";
    private static final String TAG_REFRESH_TOKEN = "refresh_token";
    private static volatile StravaPreference instance;

    private StravaPreference() {
    }

    private static Context getContext() {
        return IdoApp.getAppContext();
    }

    public static StravaPreference getInstance() {
        if (instance == null) {
            synchronized (StravaPreference.class) {
                if (instance == null) {
                    instance = new StravaPreference();
                }
            }
        }
        return instance;
    }

    public StravaData getStravaDataUpload() {
        StravaData stravaData = new StravaData();
        stravaData.setClientId(getString(getContext(), NAME, "client_id"));
        stravaData.setClientSecret(getString(getContext(), NAME, TAG_CLIENT_SECRET));
        stravaData.setGrantType(getString(getContext(), NAME, TAG_GRANT_TYPE));
        stravaData.setRefreshToken(getString(getContext(), NAME, "refresh_token"));
        return stravaData;
    }

    public void saveStravaDataUpload(StravaData stravaData) {
        if (stravaData == null) {
            stravaData = new StravaData();
        }
        saveString(getContext(), NAME, "client_id", stravaData.getClientId());
        saveString(getContext(), NAME, TAG_CLIENT_SECRET, stravaData.getClientSecret());
        saveString(getContext(), NAME, TAG_GRANT_TYPE, stravaData.getGrantType());
        saveString(getContext(), NAME, "refresh_token", stravaData.getRefreshToken());
    }

    public static void clear() {
        clear(IdoApp.getAppContext(), NAME);
    }
}