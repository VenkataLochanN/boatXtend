package com.amazon.identity.auth.map.device.token;

import android.text.TextUtils;
import android.text.format.Time;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.map.device.AccountManagerConstants;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractToken implements Token {
    public static final long ALWAYS_EXPIRE = -1;
    public static final String KEY_CREATION_TIME = "creation_time";
    public static final String KEY_EXPIRES_IN = "expires_in";
    private static final String LOG_TAG = AbstractToken.class.getName();
    public static final long NEVER_EXPIRE = 0;
    private final String _token;
    protected final Time localCreationTimestamp;
    protected Map<String, String> tokenData;

    public static long secsToMillis(long j) {
        return j * 1000;
    }

    private AbstractToken() {
        this.localCreationTimestamp = new Time();
        this._token = null;
    }

    public AbstractToken(AbstractToken abstractToken) {
        this.localCreationTimestamp = new Time();
        if (abstractToken == null || TextUtils.isEmpty(abstractToken.getToken())) {
            throw new IllegalArgumentException("Token string may not be null for an AbstractToken");
        }
        this._token = abstractToken.getToken();
        this.localCreationTimestamp.set(abstractToken.localCreationTimestamp);
        this.tokenData = new HashMap(abstractToken.tokenData);
    }

    public AbstractToken(String str) {
        this.localCreationTimestamp = new Time();
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Token string may not be null for an AbstractToken");
        }
        this._token = str;
        this.localCreationTimestamp.setToNow();
        this.tokenData = new HashMap();
        initTokenData();
    }

    public AbstractToken(Map<String, String> map) throws AuthError {
        this.localCreationTimestamp = new Time();
        this._token = map.get(Token.KEY_TOKEN);
        if (TextUtils.isEmpty(this._token)) {
            throw new IllegalArgumentException("Token string may not be null for an AbstractToken");
        }
        this.tokenData = map;
        String str = map.get(KEY_CREATION_TIME);
        if (str == null) {
            MAPLog.i(LOG_TAG, "creation_time not found in token data when creating Token, setting creation time to now");
            this.localCreationTimestamp.setToNow();
            map.put(KEY_CREATION_TIME, String.valueOf(this.localCreationTimestamp.toMillis(false)));
        } else {
            try {
                this.localCreationTimestamp.set(Long.parseLong(str));
            } catch (NumberFormatException unused) {
                MAPLog.e(LOG_TAG, "Unable to parse creation_time from token data when creating Token, setting creation time to now");
                this.localCreationTimestamp.setToNow();
                map.put(KEY_CREATION_TIME, String.valueOf(this.localCreationTimestamp.toMillis(false)));
            }
        }
    }

    @Override // com.amazon.identity.auth.map.device.token.Token
    public Time getLocalTimestamp() {
        return this.localCreationTimestamp;
    }

    protected final String getToken() {
        return this._token;
    }

    @Override // com.amazon.identity.auth.map.device.token.Token
    public final Map<String, String> getData() {
        return this.tokenData;
    }

    public static AccountManagerConstants.REGION_HINT convertStringToRegionHint(String str) {
        if (AccountManagerConstants.REGION_HINT.EU.toString().equalsIgnoreCase(str)) {
            return AccountManagerConstants.REGION_HINT.EU;
        }
        if (AccountManagerConstants.REGION_HINT.FE.toString().equalsIgnoreCase(str)) {
            return AccountManagerConstants.REGION_HINT.FE;
        }
        if (AccountManagerConstants.REGION_HINT.CN.toString().equalsIgnoreCase(str)) {
            return AccountManagerConstants.REGION_HINT.CN;
        }
        return AccountManagerConstants.REGION_HINT.NA;
    }

    private void initTokenData() {
        this.tokenData.put(Token.KEY_TOKEN, this._token);
        this.tokenData.put(KEY_CREATION_TIME, String.valueOf(this.localCreationTimestamp.toMillis(false)));
    }

    protected static long millisToSecs(long j) {
        return j / 1000;
    }

    @Override // com.amazon.identity.auth.map.device.token.Token
    public String getDirectedId() {
        return this.tokenData.get("directedid");
    }
}