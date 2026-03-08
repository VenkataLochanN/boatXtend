package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.ido.life.dialog.CommonDialog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: UpdateItem.java */
/* JADX INFO: loaded from: classes.dex */
@hs(a = "update_item", b = true)
public class bk extends bn {
    private String n = "";
    private Context o;

    public bk() {
    }

    public bk(OfflineMapCity offlineMapCity, Context context) {
        this.o = context;
        this.f279a = offlineMapCity.getCity();
        this.f281c = offlineMapCity.getAdcode();
        this.f280b = offlineMapCity.getUrl();
        this.f285g = offlineMapCity.getSize();
        this.f283e = offlineMapCity.getVersion();
        this.k = offlineMapCity.getCode();
        this.i = 0;
        this.l = offlineMapCity.getState();
        this.j = offlineMapCity.getcompleteCode();
        this.m = offlineMapCity.getPinyin();
        a();
    }

    public bk(OfflineMapProvince offlineMapProvince, Context context) {
        this.o = context;
        this.f279a = offlineMapProvince.getProvinceName();
        this.f281c = offlineMapProvince.getProvinceCode();
        this.f280b = offlineMapProvince.getUrl();
        this.f285g = offlineMapProvince.getSize();
        this.f283e = offlineMapProvince.getVersion();
        this.i = 1;
        this.l = offlineMapProvince.getState();
        this.j = offlineMapProvince.getcompleteCode();
        this.m = offlineMapProvince.getPinyin();
        a();
    }

    protected void a() {
        this.f282d = er.c(this.o) + this.m + ".zip.tmp";
    }

    public String b() {
        return this.n;
    }

    public void a(String str) {
        this.n = str;
    }

    public void b(String str) {
        JSONObject jSONObject;
        if (str != null) {
            try {
                if ("".equals(str) || (jSONObject = new JSONObject(str).getJSONObject("file")) == null) {
                    return;
                }
                this.f279a = jSONObject.optString(CommonDialog.EXTRA_TITLE);
                this.f281c = jSONObject.optString(AuthorizationResponseParser.CODE);
                this.f280b = jSONObject.optString("url");
                this.f282d = jSONObject.optString("fileName");
                this.f284f = jSONObject.optLong("lLocalLength");
                this.f285g = jSONObject.optLong("lRemoteLength");
                this.l = jSONObject.optInt("mState");
                this.f283e = jSONObject.optString("version");
                this.f286h = jSONObject.optString("localPath");
                this.n = jSONObject.optString("vMapFileNames");
                this.i = jSONObject.optInt("isSheng");
                this.j = jSONObject.optInt("mCompleteCode");
                this.k = jSONObject.optString("mCityCode");
                this.m = a(jSONObject, "pinyin");
                if ("".equals(this.m)) {
                    String strSubstring = this.f280b.substring(this.f280b.lastIndexOf("/") + 1);
                    this.m = strSubstring.substring(0, strSubstring.lastIndexOf("."));
                }
            } catch (Throwable th) {
                hn.c(th, "UpdateItem", "readFileToJSONObject");
                th.printStackTrace();
            }
        }
    }

    public void c() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(CommonDialog.EXTRA_TITLE, this.f279a);
            jSONObject2.put(AuthorizationResponseParser.CODE, this.f281c);
            jSONObject2.put("url", this.f280b);
            jSONObject2.put("fileName", this.f282d);
            jSONObject2.put("lLocalLength", this.f284f);
            jSONObject2.put("lRemoteLength", this.f285g);
            jSONObject2.put("mState", this.l);
            jSONObject2.put("version", this.f283e);
            jSONObject2.put("localPath", this.f286h);
            if (this.n != null) {
                jSONObject2.put("vMapFileNames", this.n);
            }
            jSONObject2.put("isSheng", this.i);
            jSONObject2.put("mCompleteCode", this.j);
            jSONObject2.put("mCityCode", this.k);
            jSONObject2.put("pinyin", this.m);
            jSONObject.put("file", jSONObject2);
            File file = new File(this.f282d + ".dt");
            file.delete();
            OutputStreamWriter outputStreamWriter = null;
            try {
                try {
                    OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream(file, true), "utf-8");
                    try {
                        outputStreamWriter2.write(jSONObject.toString());
                        try {
                            outputStreamWriter2.close();
                        } catch (IOException e2) {
                            e = e2;
                            e.printStackTrace();
                        }
                    } catch (IOException e3) {
                        e = e3;
                        outputStreamWriter = outputStreamWriter2;
                        hn.c(e, "UpdateItem", "saveJSONObjectToFile");
                        e.printStackTrace();
                        if (outputStreamWriter != null) {
                            try {
                                outputStreamWriter.close();
                            } catch (IOException e4) {
                                e = e4;
                                e.printStackTrace();
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        outputStreamWriter = outputStreamWriter2;
                        if (outputStreamWriter != null) {
                            try {
                                outputStreamWriter.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e6) {
                e = e6;
            }
        } catch (Throwable th3) {
            hn.c(th3, "UpdateItem", "saveJSONObjectToFile parseJson");
            th3.printStackTrace();
        }
    }

    public static String a(JSONObject jSONObject, String str) throws JSONException {
        return (jSONObject == null || !jSONObject.has(str) || "[]".equals(jSONObject.getString(str))) ? "" : jSONObject.optString(str).trim();
    }
}