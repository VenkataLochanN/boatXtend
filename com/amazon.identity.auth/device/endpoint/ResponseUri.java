package com.amazon.identity.auth.device.endpoint;

import android.net.Uri;
import android.text.TextUtils;
import com.amazon.identity.auth.device.AuthError;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ResponseUri {
    public static final String STATE_PARAMETER_NAME = "state";
    private final Uri uri;

    public ResponseUri(Uri uri) {
        this.uri = uri;
    }

    public Map<String, String> getStateValues() throws AuthError {
        String queryParameter = this.uri.getQueryParameter("state");
        if (queryParameter == null) {
            throw new AuthError(String.format("Response does not have a state parameter: %s", this.uri.toString()), AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
        }
        HashMap map = new HashMap();
        for (String str : TextUtils.split(queryParameter, "&")) {
            String[] strArrSplit = TextUtils.split(str, "=");
            if (strArrSplit != null && strArrSplit.length == 2) {
                map.put(strArrSplit[0], strArrSplit[1]);
            }
        }
        return map;
    }
}