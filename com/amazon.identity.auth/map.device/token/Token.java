package com.amazon.identity.auth.map.device.token;

import android.text.format.Time;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface Token {
    public static final String KEY_TOKEN = "token";

    Map<String, String> getData();

    String getDirectedId();

    Time getLocalTimestamp();

    String getType();

    String toString();
}