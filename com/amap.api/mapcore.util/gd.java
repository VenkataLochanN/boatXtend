package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Handler;
import com.amap.api.maps.model.LatLng;
import com.amap.api.trace.TraceLocation;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: TraceHandlerAbstract.java */
/* JADX INFO: loaded from: classes.dex */
public class gd extends gb<List<TraceLocation>, List<LatLng>> implements Runnable {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private List<TraceLocation> f1011h;
    private Handler i;
    private int j;
    private int k;
    private String l;

    @Override // com.amap.api.mapcore.util.iq
    public boolean isSupportIPV6() {
        return true;
    }

    public gd(Context context, Handler handler, List<TraceLocation> list, int i, String str, int i2, int i3) {
        super(context, list);
        this.i = null;
        this.j = 0;
        this.k = 0;
        this.f1011h = list;
        this.i = handler;
        this.k = i2;
        this.j = i3;
        this.l = str;
    }

    @Override // com.amap.api.mapcore.util.gb, com.amap.api.mapcore.util.ga
    protected String a() {
        long time;
        JSONArray jSONArray = new JSONArray();
        long j = 0;
        for (int i = 0; i < this.f1011h.size(); i++) {
            TraceLocation traceLocation = this.f1011h.get(i);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("x", traceLocation.getLongitude());
                jSONObject.put("y", traceLocation.getLatitude());
                jSONObject.put("ag", (int) traceLocation.getBearing());
                time = traceLocation.getTime();
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            if (i == 0) {
                if (time == 0) {
                    time = (System.currentTimeMillis() - DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT) / 1000;
                }
                jSONObject.put("tm", time / 1000);
            } else {
                if (time != 0) {
                    long j2 = time - j;
                    if (j2 < 1000) {
                        jSONObject.put("tm", 1);
                    } else {
                        jSONObject.put("tm", j2 / 1000);
                    }
                } else {
                    jSONObject.put("tm", 1);
                }
                jSONArray.put(jSONObject);
            }
            j = time;
            jSONObject.put("sp", (int) traceLocation.getSpeed());
            jSONArray.put(jSONObject);
        }
        this.f1009g = getURL() + "&" + jSONArray.toString();
        return jSONArray.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amap.api.mapcore.util.gb, com.amap.api.mapcore.util.ga
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public List<LatLng> b(String str) throws fz {
        JSONObject jSONObject;
        JSONArray jSONArrayOptJSONArray;
        ArrayList arrayList = new ArrayList();
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (jSONObject.has(AeUtil.ROOT_DATA_PATH_OLD_NAME) && (jSONArrayOptJSONArray = jSONObject.optJSONObject(AeUtil.ROOT_DATA_PATH_OLD_NAME).optJSONArray("points")) != null && jSONArrayOptJSONArray.length() != 0) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                arrayList.add(new LatLng(Double.parseDouble(jSONObjectOptJSONObject.optString("y")), Double.parseDouble(jSONObjectOptJSONObject.optString("x"))));
            }
            return arrayList;
        }
        return arrayList;
    }

    @Override // java.lang.Runnable
    public void run() {
        new ArrayList();
        try {
            try {
                gf.a().a(this.l, this.j, e());
                gf.a().a(this.l).a(this.i);
            } catch (fz e2) {
                gf.a().a(this.i, this.k, e2.a());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.mapcore.util.iq
    public String getURL() {
        String str = "key=" + gi.f(this.f1008f);
        String strA = gl.a();
        return "http://restsdk.amap.com/v4/grasproad/driving?" + str + ("&ts=" + strA) + ("&scode=" + gl.a(this.f1008f, strA, str));
    }

    @Override // com.amap.api.mapcore.util.iq
    public String getIPV6URL() {
        return er.a(getURL());
    }
}