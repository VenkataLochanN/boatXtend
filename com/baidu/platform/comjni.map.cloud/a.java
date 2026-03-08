package com.baidu.platform.comjni.map.cloud;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.baidu.mapapi.cloud.CloudListener;
import com.baidu.mapapi.cloud.CloudRgcResult;
import com.baidu.mapapi.cloud.CloudSearchResult;
import com.baidu.mapapi.cloud.DetailSearchResult;
import com.baidu.mapapi.http.AsyncHttpClient;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapsdkplatform.comjni.util.AppMD5;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a implements ICloudCenter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3919b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private CloudListener f3920c;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f3925h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final Lock f3918a = new ReentrantLock();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f3921d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f3922e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private AsyncHttpClient f3923f = new AsyncHttpClient();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Handler f3924g = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        switch (this.f3919b) {
            case 10001:
                CloudSearchResult cloudSearchResult = new CloudSearchResult();
                this.f3918a.lock();
                try {
                    this.f3920c.onGetSearchResult(cloudSearchResult, i);
                } finally {
                }
                break;
            case 10002:
                DetailSearchResult detailSearchResult = new DetailSearchResult();
                this.f3918a.lock();
                try {
                    this.f3920c.onGetDetailSearchResult(detailSearchResult, i);
                } finally {
                }
                break;
            case 10003:
                CloudRgcResult cloudRgcResult = new CloudRgcResult();
                this.f3918a.lock();
                try {
                    this.f3920c.onGetCloudRgcResult(cloudRgcResult, i);
                } finally {
                }
                break;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    private boolean d(String str) {
        if (str == null) {
            return false;
        }
        this.f3923f.get(str, new b(this));
        return true;
    }

    private String e(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String authToken = HttpClient.getAuthToken();
        if (authToken == null) {
            a(-4);
            return null;
        }
        if (this.f3921d) {
            str = str + "&token=" + AppMD5.encodeUrlParamsValue(authToken);
        }
        String str2 = str + HttpClient.getPhoneInfo();
        if (!this.f3922e) {
            return str2;
        }
        return str2 + "&sign=" + AppMD5.getSignMD5String(Uri.parse(str2).buildUpon().build().getEncodedQuery());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            switch (this.f3919b) {
                case 10001:
                    CloudSearchResult cloudSearchResult = new CloudSearchResult();
                    try {
                        cloudSearchResult.parseFromJSON(jSONObject);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    this.f3918a.lock();
                    try {
                        this.f3920c.onGetSearchResult(cloudSearchResult, cloudSearchResult.status);
                    } finally {
                    }
                    break;
                case 10002:
                    DetailSearchResult detailSearchResult = new DetailSearchResult();
                    try {
                        detailSearchResult.parseFromJSON(jSONObject);
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                    this.f3918a.lock();
                    try {
                        this.f3920c.onGetDetailSearchResult(detailSearchResult, detailSearchResult.status);
                    } finally {
                    }
                    break;
                case 10003:
                    CloudRgcResult cloudRgcResult = new CloudRgcResult();
                    try {
                        cloudRgcResult.parseFromJSON(jSONObject);
                    } catch (JSONException e4) {
                        e4.printStackTrace();
                    }
                    this.f3918a.lock();
                    try {
                        this.f3920c.onGetCloudRgcResult(cloudRgcResult, cloudRgcResult.status);
                    } finally {
                    }
                    break;
                default:
                    return;
            }
        } catch (JSONException e5) {
            e5.printStackTrace();
        }
    }

    @Override // com.baidu.platform.comjni.map.cloud.ICloudCenter
    public void a(CloudListener cloudListener) {
        this.f3918a.lock();
        this.f3920c = cloudListener;
        this.f3918a.unlock();
    }

    @Override // com.baidu.platform.comjni.map.cloud.ICloudCenter
    public boolean a(String str) {
        this.f3919b = 10001;
        this.f3921d = false;
        return d(e(str));
    }

    @Override // com.baidu.platform.comjni.map.cloud.ICloudCenter
    public boolean b(String str) {
        this.f3919b = 10002;
        this.f3921d = false;
        return d(e(str));
    }

    @Override // com.baidu.platform.comjni.map.cloud.ICloudCenter
    public boolean c(String str) {
        this.f3919b = 10003;
        this.f3921d = true;
        return d(e(str));
    }
}