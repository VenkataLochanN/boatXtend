package com.amazon.identity.auth.device.endpoint;

import android.content.Context;
import android.util.Pair;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class OneTimeCodeRequest extends AbstractJsonPandaRequest<OneTimeCodeResponse> {
    private static final String ACCESS_TOKEN_PARAMETER = "accessToken";
    private static final String LOG_TAG = OneTimeCodeRequest.class.getName();
    private static final String ONE_TIME_CODE_ENDPOINT = "/auth/create/oneTimeCode";
    private static final String WORKFLOW_CLIENT_ID_PARAMETER = "workflowClientId";
    private String accessToken;
    private String workflowClientId;

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    public String getEndPoint() {
        return ONE_TIME_CODE_ENDPOINT;
    }

    public OneTimeCodeRequest(Context context, String str, String str2, AppInfo appInfo) {
        super(context, appInfo);
        this.workflowClientId = str;
        this.accessToken = str2;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest
    protected List<Pair<String, String>> getExtraParameters() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair(WORKFLOW_CLIENT_ID_PARAMETER, this.workflowClientId));
        arrayList.add(new Pair(ACCESS_TOKEN_PARAMETER, this.accessToken));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.endpoint.AbstractHTTPSRequest
    public OneTimeCodeResponse generateResponse(HttpResponse httpResponse) {
        return new OneTimeCodeResponse(httpResponse);
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractHTTPSRequest
    protected void logRequest() {
        MAPLog.pii(LOG_TAG, "Executing create one time code request. workflowClientId=" + this.workflowClientId, "accessToken=" + this.accessToken);
    }

    public int hashCode() {
        String str = this.accessToken;
        int iHashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.workflowClientId;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OneTimeCodeRequest oneTimeCodeRequest = (OneTimeCodeRequest) obj;
        if ((this.accessToken != null || oneTimeCodeRequest.accessToken == null) && this.accessToken.equals(oneTimeCodeRequest.accessToken)) {
            return (this.workflowClientId != null || oneTimeCodeRequest.workflowClientId == null) && this.workflowClientId.equals(oneTimeCodeRequest.workflowClientId);
        }
        return false;
    }
}