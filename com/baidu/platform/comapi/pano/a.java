package com.baidu.platform.comapi.pano;

import android.net.Uri;
import android.text.TextUtils;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.amazon.identity.auth.map.device.token.Token;
import com.baidu.mapapi.http.AsyncHttpClient;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapsdkplatform.comjni.util.AppMD5;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.alexa.AlexaCustomSkillConstant;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    AsyncHttpClient f3912a = new AsyncHttpClient();

    /* JADX INFO: renamed from: com.baidu.platform.comapi.pano.a$a, reason: collision with other inner class name */
    public interface InterfaceC0037a<T> {
        void a(HttpClient.HttpStateError httpStateError);

        void a(T t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public c a(String str) {
        if (str == null || str.equals("")) {
            return new c(PanoStateError.PANO_NOT_FOUND);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("result");
            if (jSONObjectOptJSONObject == null) {
                return new c(PanoStateError.PANO_NOT_FOUND);
            }
            if (jSONObjectOptJSONObject.optInt(AuthorizationResponseParser.ERROR) != 0) {
                return new c(PanoStateError.PANO_UID_ERROR);
            }
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(FirebaseAnalytics.Param.CONTENT);
            if (jSONArrayOptJSONArray == null) {
                return new c(PanoStateError.PANO_NOT_FOUND);
            }
            c cVar = null;
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(i).optJSONObject("poiinfo");
                if (jSONObjectOptJSONObject2 != null) {
                    cVar = new c(PanoStateError.PANO_NO_ERROR);
                    cVar.a(jSONObjectOptJSONObject2.optString("PID"));
                    cVar.a(jSONObjectOptJSONObject2.optInt("hasstreet"));
                }
            }
            return cVar;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return new c(PanoStateError.PANO_NOT_FOUND);
        }
    }

    private String a(Uri.Builder builder) {
        Uri.Builder builderBuildUpon = Uri.parse(builder.build().toString() + HttpClient.getPhoneInfo()).buildUpon();
        builderBuildUpon.appendQueryParameter("sign", AppMD5.getSignMD5String(builderBuildUpon.build().getEncodedQuery()));
        return builderBuildUpon.build().toString();
    }

    private void a(Uri.Builder builder, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        builder.appendQueryParameter(str, str2);
    }

    public void a(String str, InterfaceC0037a<c> interfaceC0037a) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(HttpClient.isHttpsEnable ? "https" : "http");
        builder.encodedAuthority("api.map.baidu.com");
        builder.path("/sdkproxy/lbs_androidsdk/pano/v1/");
        a(builder, "qt", "poi");
        a(builder, "uid", str);
        a(builder, "action", AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        String authToken = HttpClient.getAuthToken();
        if (authToken == null) {
            interfaceC0037a.a(new c(PanoStateError.PANO_NO_TOKEN));
        } else {
            a(builder, Token.KEY_TOKEN, authToken);
            this.f3912a.get(a(builder), new b(this, interfaceC0037a));
        }
    }
}