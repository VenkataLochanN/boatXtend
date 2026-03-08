package com.amazon.identity.auth.device.endpoint;

import com.amazon.identity.auth.device.AuthError;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class LogoutResponse extends AbstractJSONTokenResponse {
    private JSONObject logoutResponse;

    public LogoutResponse(HttpResponse httpResponse) {
        super(httpResponse);
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse
    protected void doParse(JSONObject jSONObject) throws JSONException, AuthError, IOException {
        this.logoutResponse = jSONObject;
    }

    public JSONObject getLogoutResponse() {
        return this.logoutResponse;
    }
}