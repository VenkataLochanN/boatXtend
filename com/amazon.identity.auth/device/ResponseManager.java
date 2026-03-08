package com.amazon.identity.auth.device;

import android.net.Uri;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ResponseManager {
    static final int MAX_RESPONSES = 10;
    private final Map<String, Uri> pendingResponses = new LinkedHashMap();
    private static final String LOG_TAG = ResponseManager.class.getName();
    private static ResponseManager INSTANCE = null;

    public static synchronized ResponseManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ResponseManager();
        }
        return INSTANCE;
    }

    public int size() {
        return this.pendingResponses.size();
    }

    public synchronized boolean hasPendingResponseForRequest(String str) {
        return this.pendingResponses.containsKey(str);
    }

    public synchronized void putPendingResponse(String str, Uri uri) {
        if (str == null) {
            throw new IllegalArgumentException("requestId must be non-null");
        }
        if (uri == null) {
            throw new IllegalArgumentException("responseUri must be non-null");
        }
        while (this.pendingResponses.size() >= 10) {
            String next = this.pendingResponses.keySet().iterator().next();
            MAPLog.d(LOG_TAG, "Purging pending response for request ID " + next);
            this.pendingResponses.remove(next);
        }
        MAPLog.d(LOG_TAG, "Recording pending response for request ID " + str);
        this.pendingResponses.put(str, uri);
    }

    public synchronized Uri removePendingResponse(String str) {
        MAPLog.d(LOG_TAG, "Dequeuing pending response for request ID " + str);
        return this.pendingResponses.remove(str);
    }
}