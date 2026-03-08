package com.amazon.identity.auth.device.endpoint;

import android.content.Context;
import android.util.Pair;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class OauthTokenForCodePairRequest extends AbstractOauthTokenRequest<OauthTokenResponse> {
    private static final String DEVICE_CODE_GRANT = "device_code";
    private static final String LOG_TAG = OauthTokenForCodePairRequest.class.getName();
    private static final String USER_CODE_PARAM = "user_code";
    private final String mDeviceCode;
    private final String mUserCode;

    @Override // com.amazon.identity.auth.device.endpoint.AbstractOauthTokenRequest
    public String getGrantType() {
        return DEVICE_CODE_GRANT;
    }

    OauthTokenForCodePairRequest(String str, String str2, AppInfo appInfo, Context context) throws AuthError {
        super(context, appInfo);
        this.mUserCode = str;
        this.mDeviceCode = str2;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractOauthTokenRequest
    protected List<Pair<String, String>> getExtraOauthTokenRequestParameters() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair(DEVICE_CODE_GRANT, this.mDeviceCode));
        arrayList.add(new Pair(USER_CODE_PARAM, this.mUserCode));
        return arrayList;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractHTTPSRequest
    protected void logRequest() {
        MAPLog.i(LOG_TAG, "Executing OAuth access token exchange. user code=" + this.mUserCode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.endpoint.AbstractHTTPSRequest
    public OauthTokenResponse generateResponse(HttpResponse httpResponse) {
        return new OauthTokenResponse(httpResponse, getAppFamilyId(), null);
    }
}