package com.amazon.identity.auth.device.endpoint;

import android.text.TextUtils;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.InvalidGrantAuthError;
import com.amazon.identity.auth.device.InvalidTokenAuthError;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.amazon.identity.auth.device.dataobject.AuthorizationToken;
import com.amazon.identity.auth.device.token.AccessAtzToken;
import com.amazon.identity.auth.device.token.RefreshAtzToken;
import com.amazon.identity.auth.map.device.token.AbstractToken;
import com.amazon.identity.auth.map.device.token.Token;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class OauthTokenResponse extends AbstractJSONTokenResponse {
    static final String AUTHZ_ACCESS_TOKEN = "access_token";
    static final String AUTHZ_ERROR_DESCIRPTION = "error_description";
    static final String AUTHZ_INSUFFICIENT_SCOPE = "insufficient_scope";
    static final String AUTHZ_INVALID_CLIENT = "invalid_client";
    static final String AUTHZ_INVALID_GRANT = "invalid_grant";
    static final String AUTHZ_INVALID_REQUEST = "invalid_request";
    static final String AUTHZ_INVALID_SCOPE = "invalid_scope";
    static final String AUTHZ_INVALID_TOKEN = "invalid_token";
    static final String AUTHZ_UNAUTHORIZED_CLIENT = "unauthorized_client";
    static final String AUTHZ_UNSUPPORTED_GRANT_TYPE = "unsupported_grant_type";
    private static final String LOG_TAG = OauthTokenResponse.class.getName();
    private static final String REQUEST_ID_HEADER = "x-amzn-RequestId";
    protected AccessAtzToken mAccessToken;
    private final String mAppFamilyId;
    private String mDirectedId;
    private RefreshAtzToken mRefreshToken;

    @Override // com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse
    public String getVersion() {
        return "3.0.4";
    }

    OauthTokenResponse(HttpResponse httpResponse, String str, String str2) {
        super(httpResponse);
        this.mRefreshToken = null;
        this.mAppFamilyId = str;
        this.mDirectedId = str2;
    }

    public void testParse(JSONObject jSONObject) throws JSONException, AuthError, IOException {
        doParse(jSONObject);
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse
    protected void doParse(JSONObject jSONObject) throws JSONException, AuthError, IOException {
        this.mAccessToken = extractAccessAtzToken(jSONObject);
        this.mRefreshToken = extractRefreshAtzToken(jSONObject);
    }

    public Token createPrimaryToken(String str, long j) {
        return new AccessAtzToken(this.mAppFamilyId, this.mDirectedId, str, j, null);
    }

    public AccessAtzToken extractAccessAtzToken(JSONObject jSONObject) throws AuthError {
        try {
            if (jSONObject.has("access_token")) {
                return (AccessAtzToken) createPrimaryToken(jSONObject.getString("access_token"), AbstractToken.secsToMillis(getExpiresIn(jSONObject)));
            }
            MAPLog.e(LOG_TAG, "Unable to find AccessAtzToken in JSON response, throwing AuthError");
            throw new AuthError("JSON response did not contain an AccessAtzToken", AuthError.ERROR_TYPE.ERROR_JSON);
        } catch (JSONException unused) {
            MAPLog.e(LOG_TAG, "Error reading JSON response, throwing AuthError");
            throw new AuthError("Error reading JSON response", AuthError.ERROR_TYPE.ERROR_JSON);
        }
    }

    public RefreshAtzToken extractRefreshAtzToken(JSONObject jSONObject) throws AuthError {
        MAPLog.i(LOG_TAG, "Extracting RefreshToken");
        try {
            if (jSONObject.has("refresh_token")) {
                return new RefreshAtzToken(getAppFamilyId(), this.mDirectedId, jSONObject.getString("refresh_token"), null);
            }
            MAPLog.e(LOG_TAG, "Unable to find RefreshAtzToken in JSON response");
            return null;
        } catch (JSONException unused) {
            MAPLog.e(LOG_TAG, "Error reading JSON response, throwing AuthError");
            throw new AuthError("Error reading JSON response", AuthError.ERROR_TYPE.ERROR_JSON);
        }
    }

    public String getAppFamilyId() {
        return this.mAppFamilyId;
    }

    public AccessAtzToken getAccessAtzToken() {
        return this.mAccessToken;
    }

    public RefreshAtzToken getRefreshAtzToken() {
        return this.mRefreshToken;
    }

    public AuthorizationToken[] getAtzTokens() {
        return new AuthorizationToken[]{this.mAccessToken, this.mRefreshToken};
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse
    protected JSONObject extractResponseJSONObject(JSONObject jSONObject) throws JSONException {
        try {
            return super.extractResponseJSONObject(jSONObject);
        } catch (JSONException unused) {
            MAPLog.w(LOG_TAG, "No Response type in the response");
            return jSONObject;
        }
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse
    protected void handleJSONError(JSONObject jSONObject) throws AuthError {
        try {
            String string = jSONObject.getString(AuthorizationResponseParser.ERROR);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            String string2 = jSONObject.getString("error_description");
            if (isInvalidGrant(string, string2)) {
                MAPLog.pii(LOG_TAG, "Invalid source authorization in exchange.", "info=" + jSONObject);
                throw new InvalidGrantAuthError("Invalid source authorization in exchange." + jSONObject);
            }
            if (isInvalidToken(string, string2)) {
                handleInvalidToken(jSONObject);
                return;
            }
            if (isInvalidClient(string, string2)) {
                MAPLog.pii(LOG_TAG, "Invalid Client. ApiKey is invalid ", "info=" + jSONObject);
                throw new AuthError("Invalid Client. ApiKey is invalid " + jSONObject, AuthError.ERROR_TYPE.ERROR_INVALID_CLIENT);
            }
            if (isInvalidScope(string, string2) || isInsufficientScope(string, string2)) {
                MAPLog.pii(LOG_TAG, "Invalid Scope. Authorization not valid for the requested scopes ", "info=" + jSONObject);
                throw new AuthError("Invalid Scope. Authorization not valid for the requested scopes " + jSONObject, AuthError.ERROR_TYPE.ERROR_INVALID_SCOPE);
            }
            if (isUnauthorizedClient(string, string2)) {
                MAPLog.pii(LOG_TAG, "Unauthorized Client.  The authenticated client is not authorized to use this authorization grant type. ", "info=" + jSONObject);
                throw new AuthError("Unauthorized Client.  The authenticated client is not authorized to use this authorization grant type. " + jSONObject, AuthError.ERROR_TYPE.ERROR_UNAUTHORIZED_CLIENT);
            }
            MAPLog.pii(LOG_TAG, "Server error doing authorization exchange. ", "info=" + jSONObject);
            throw new AuthError("Server error doing authorization exchange. " + jSONObject, AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
        } catch (JSONException unused) {
            if (TextUtils.isEmpty(null)) {
                return;
            }
            throw new AuthError("Server Error : " + ((String) null), AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
        }
    }

    boolean isUnauthorizedClient(String str, String str2) {
        return AUTHZ_UNAUTHORIZED_CLIENT.equals(str);
    }

    boolean isInvalidScope(String str, String str2) {
        return AUTHZ_INVALID_SCOPE.equals(str);
    }

    boolean isInvalidClient(String str, String str2) {
        return AUTHZ_INVALID_CLIENT.equals(str);
    }

    boolean isInvalidGrant(String str, String str2) {
        return AUTHZ_INVALID_GRANT.equals(str) || AUTHZ_UNSUPPORTED_GRANT_TYPE.equals(str);
    }

    boolean isInsufficientScope(String str, String str2) {
        return AUTHZ_INSUFFICIENT_SCOPE.equals(str);
    }

    boolean isInvalidToken(String str, String str2) {
        return AUTHZ_INVALID_TOKEN.equals(str) || (AUTHZ_INVALID_REQUEST.equals(str) && !TextUtils.isEmpty(str2) && str2.contains("access_token"));
    }

    void handleInvalidToken(JSONObject jSONObject) throws InvalidTokenAuthError {
        MAPLog.pii(LOG_TAG, "Invalid Token in exchange.", "info=" + jSONObject);
        throw new InvalidTokenAuthError("Invalid Token in exchange." + jSONObject);
    }
}