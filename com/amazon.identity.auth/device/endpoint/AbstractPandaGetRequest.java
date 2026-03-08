package com.amazon.identity.auth.device.endpoint;

import android.content.Context;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.endpoint.Response;
import java.net.ProtocolException;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractPandaGetRequest<T extends Response> extends AbstractPandaRequest<T> {
    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest, com.amazon.identity.auth.device.endpoint.AbstractHTTPSRequest
    protected void writeBody(HttpsURLConnection httpsURLConnection) {
    }

    public AbstractPandaGetRequest(Context context, AppInfo appInfo) {
        super(context, appInfo);
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractPandaRequest, com.amazon.identity.auth.device.endpoint.AbstractHTTPSRequest
    protected void setHttpMethod(HttpsURLConnection httpsURLConnection) throws ProtocolException {
        httpsURLConnection.setRequestMethod("GET");
    }
}