package com.baidu.platform.base;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.baidu.mapapi.http.AsyncHttpClient;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.district.DistrictResult;
import com.baidu.mapsdkplatform.comapi.util.PermissionCheck;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private AsyncHttpClient f3891b = new AsyncHttpClient();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f3892c = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final Lock f3890a = new ReentrantLock();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f3893d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private DistrictResult f3894e = null;

    private void a(AsyncHttpClient asyncHttpClient, HttpClient.ProtoResultCallback protoResultCallback, SearchResult searchResult) {
        asyncHttpClient.get(new com.baidu.platform.core.a.c(((DistrictResult) searchResult).getCityName()).a(), protoResultCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HttpClient.HttpStateError httpStateError, d dVar, Object obj) {
        a(dVar.a("{SDK_InnerError:{httpStateError:" + httpStateError + "}}"), obj, dVar);
    }

    private void a(SearchResult searchResult, Object obj, d dVar) {
        this.f3892c.post(new c(this, dVar, searchResult, obj));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (b(str)) {
            return;
        }
        Log.e("BaseSearch", "Permission check unfinished, try again");
        int iPermissionCheck = PermissionCheck.permissionCheck();
        if (iPermissionCheck != 0) {
            Log.e("BaseSearch", "The authorized result is: " + iPermissionCheck);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, d dVar, Object obj, AsyncHttpClient asyncHttpClient, HttpClient.ProtoResultCallback protoResultCallback) {
        SearchResult searchResultA = dVar.a(str);
        searchResultA.status = c(str);
        if (a(dVar, searchResultA)) {
            a(asyncHttpClient, protoResultCallback, searchResultA);
            return;
        }
        if (!(dVar instanceof com.baidu.platform.core.a.b)) {
            a(searchResultA, obj, dVar);
            return;
        }
        DistrictResult districtResult = this.f3894e;
        if (districtResult != null) {
            DistrictResult districtResult2 = (DistrictResult) searchResultA;
            districtResult2.setCityCode(districtResult.getCityCode());
            districtResult2.setCenterPt(this.f3894e.getCenterPt());
        }
        a(searchResultA, obj, dVar);
        this.f3893d = true;
        this.f3894e = null;
        ((com.baidu.platform.core.a.b) dVar).a(false);
    }

    private boolean a(d dVar, SearchResult searchResult) {
        if (!(dVar instanceof com.baidu.platform.core.a.b)) {
            return false;
        }
        DistrictResult districtResult = (DistrictResult) searchResult;
        if (SearchResult.ERRORNO.RESULT_NOT_FOUND != districtResult.error || districtResult.getCityName() == null || !this.f3893d) {
            return false;
        }
        this.f3893d = false;
        this.f3894e = districtResult;
        ((com.baidu.platform.core.a.b) dVar).a(true);
        return true;
    }

    private boolean b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("SDK_InnerError") || !jSONObject.optJSONObject("SDK_InnerError").has("PermissionCheckError")) {
                return true;
            }
            Log.e("BaseSearch", "Permission check unfinished");
            return false;
        } catch (JSONException unused) {
            Log.e("BaseSearch", "Create JSONObject failed");
            return false;
        }
    }

    private int c(String str) {
        JSONObject jSONObjectOptJSONObject;
        if (str != null && !str.equals("")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(NotificationCompat.CATEGORY_STATUS)) {
                    return jSONObject.getInt(NotificationCompat.CATEGORY_STATUS);
                }
                if (jSONObject.has("status_sp")) {
                    return jSONObject.getInt("status_sp");
                }
                if (!jSONObject.has("result") || (jSONObjectOptJSONObject = jSONObject.optJSONObject("result")) == null) {
                    return 10204;
                }
                return jSONObjectOptJSONObject.optInt(AuthorizationResponseParser.ERROR);
            } catch (JSONException unused) {
                Log.e("BaseSearch", "Create JSONObject failed when get response result status");
            }
        }
        return 10204;
    }

    protected boolean a(e eVar, Object obj, d dVar) {
        if (dVar == null) {
            Log.e(a.class.getSimpleName(), "The SearchParser is null, must be applied.");
            return false;
        }
        String strA = eVar.a();
        if (strA != null) {
            this.f3891b.get(strA, new b(this, dVar, obj));
            return true;
        }
        Log.e("BaseSearch", "The sendurl is: " + strA);
        a(dVar.a("{SDK_InnerError:{PermissionCheckError:Error}}"), obj, dVar);
        return false;
    }
}