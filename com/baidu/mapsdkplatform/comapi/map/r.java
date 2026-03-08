package com.baidu.mapsdkplatform.comapi.map;

import android.content.Context;
import android.os.Handler;
import androidx.core.app.NotificationCompat;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3612a = r.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static r f3613c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.baidu.mapsdkplatform.comjni.map.basemap.a f3614b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private w f3615d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Handler f3616e;

    private r() {
    }

    public static r a() throws Throwable {
        if (f3613c == null) {
            f3613c = new r();
            f3613c.g();
        }
        return f3613c;
    }

    private void g() throws Throwable {
        h();
        this.f3615d = new w();
        this.f3616e = new s(this);
        MessageCenter.registMessage(65289, this.f3616e);
    }

    private void h() throws Throwable {
        Context context = BMapManager.getContext();
        EnvironmentUtilities.initAppDirectory(context);
        this.f3614b = new com.baidu.mapsdkplatform.comjni.map.basemap.a();
        this.f3614b.a(context.hashCode());
        String moduleFileName = SysOSUtil.getModuleFileName();
        String appSDCardPath = EnvironmentUtilities.getAppSDCardPath();
        String appCachePath = EnvironmentUtilities.getAppCachePath();
        String appSecondCachePath = EnvironmentUtilities.getAppSecondCachePath();
        int mapTmpStgMax = EnvironmentUtilities.getMapTmpStgMax();
        int domTmpStgMax = EnvironmentUtilities.getDomTmpStgMax();
        int itsTmpStgMax = EnvironmentUtilities.getItsTmpStgMax();
        String str = SysOSUtil.getDensityDpi() >= 180 ? "/h/" : "/l/";
        String str2 = moduleFileName + "/cfg";
        String str3 = appSDCardPath + "/vmp";
        String str4 = str3 + str;
        String str5 = str3 + str;
        String str6 = appCachePath + "/tmp/";
        this.f3614b.a(str2 + "/a/", str4, str6, appSecondCachePath + "/tmp/", str5, str2 + "/a/", null, 0, str2 + "/idrres/", SysOSUtil.getScreenSizeX(), SysOSUtil.getScreenSizeY(), SysOSUtil.getDensityDpi(), mapTmpStgMax, domTmpStgMax, itsTmpStgMax, 0);
        this.f3614b.d();
    }

    public ArrayList<q> a(String str) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar;
        JSONArray jSONArrayOptJSONArray;
        if (!str.equals("") && (aVar = this.f3614b) != null) {
            String strA = aVar.a(str);
            if (strA == null || strA.equals("")) {
                return null;
            }
            ArrayList<q> arrayList = new ArrayList<>();
            try {
                JSONObject jSONObject = new JSONObject(strA);
                if (jSONObject.length() == 0 || (jSONArrayOptJSONArray = jSONObject.optJSONArray("dataset")) == null) {
                    return null;
                }
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    q qVar = new q();
                    JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                    int iOptInt = jSONObject2.optInt("id");
                    if (iOptInt <= 2000 || iOptInt == 2912 || iOptInt == 2911 || iOptInt == 9000) {
                        qVar.f3607a = iOptInt;
                        qVar.f3608b = jSONObject2.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                        qVar.f3609c = jSONObject2.optInt("mapsize");
                        qVar.f3610d = jSONObject2.optInt("cty");
                        if (jSONObject2.has("child")) {
                            JSONArray jSONArrayOptJSONArray2 = jSONObject2.optJSONArray("child");
                            ArrayList<q> arrayList2 = new ArrayList<>();
                            for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                                q qVar2 = new q();
                                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray2.optJSONObject(i2);
                                qVar2.f3607a = jSONObjectOptJSONObject.optInt("id");
                                qVar2.f3608b = jSONObjectOptJSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                                qVar2.f3609c = jSONObjectOptJSONObject.optInt("mapsize");
                                qVar2.f3610d = jSONObjectOptJSONObject.optInt("cty");
                                arrayList2.add(qVar2);
                            }
                            qVar.a(arrayList2);
                        }
                        arrayList.add(qVar);
                    }
                }
                return arrayList;
            } catch (JSONException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public void a(v vVar) {
        w wVar = this.f3615d;
        if (wVar != null) {
            wVar.a(vVar);
        }
    }

    public boolean a(int i) {
        if (this.f3614b == null || i < 0) {
            return false;
        }
        if (i <= 2000 || i == 2912 || i == 2911 || i == 9000) {
            return this.f3614b.d(i);
        }
        return false;
    }

    public boolean a(boolean z, boolean z2) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.f3614b;
        if (aVar == null) {
            return false;
        }
        return aVar.a(z, z2);
    }

    public void b() {
        MessageCenter.unregistMessage(65289, this.f3616e);
        this.f3614b.b(BMapManager.getContext().hashCode());
        f3613c = null;
    }

    public void b(v vVar) {
        w wVar = this.f3615d;
        if (wVar != null) {
            wVar.b(vVar);
        }
    }

    public boolean b(int i) {
        if (this.f3614b == null || i < 0) {
            return false;
        }
        if (i <= 2000 || i == 2912 || i == 2911 || i == 9000) {
            return this.f3614b.a(i, false, 0);
        }
        return false;
    }

    public ArrayList<q> c() {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.f3614b;
        if (aVar == null) {
            return null;
        }
        String strM = aVar.m();
        ArrayList<q> arrayList = new ArrayList<>();
        try {
            JSONArray jSONArrayOptJSONArray = new JSONObject(strM).optJSONArray("dataset");
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                q qVar = new q();
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                qVar.f3607a = jSONObjectOptJSONObject.optInt("id");
                qVar.f3608b = jSONObjectOptJSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                qVar.f3609c = jSONObjectOptJSONObject.optInt("mapsize");
                qVar.f3610d = jSONObjectOptJSONObject.optInt("cty");
                if (jSONObjectOptJSONObject.has("child")) {
                    JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("child");
                    ArrayList<q> arrayList2 = new ArrayList<>();
                    for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                        q qVar2 = new q();
                        JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray2.optJSONObject(i2);
                        qVar2.f3607a = jSONObjectOptJSONObject2.optInt("id");
                        qVar2.f3608b = jSONObjectOptJSONObject2.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                        qVar2.f3609c = jSONObjectOptJSONObject2.optInt("mapsize");
                        qVar2.f3610d = jSONObjectOptJSONObject2.optInt("cty");
                        arrayList2.add(qVar2);
                    }
                    qVar.a(arrayList2);
                }
                arrayList.add(qVar);
            }
            return arrayList;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean c(int i) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.f3614b;
        if (aVar == null || i < 0) {
            return false;
        }
        return aVar.b(i, false, 0);
    }

    public ArrayList<q> d() {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.f3614b;
        ArrayList<q> arrayList = null;
        if (aVar == null) {
            return null;
        }
        String strA = aVar.a("");
        ArrayList<q> arrayList2 = new ArrayList<>();
        try {
            JSONArray jSONArrayOptJSONArray = new JSONObject(strA).optJSONArray("dataset");
            int i = 0;
            while (i < jSONArrayOptJSONArray.length()) {
                q qVar = new q();
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                int iOptInt = jSONObjectOptJSONObject.optInt("id");
                if (iOptInt <= 2000 || iOptInt == 2912 || iOptInt == 2911 || iOptInt == 9000) {
                    qVar.f3607a = iOptInt;
                    qVar.f3608b = jSONObjectOptJSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                    qVar.f3609c = jSONObjectOptJSONObject.optInt("mapsize");
                    qVar.f3610d = jSONObjectOptJSONObject.optInt("cty");
                    if (jSONObjectOptJSONObject.has("child")) {
                        JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("child");
                        ArrayList<q> arrayList3 = new ArrayList<>();
                        for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                            q qVar2 = new q();
                            JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray2.optJSONObject(i2);
                            try {
                                qVar2.f3607a = jSONObjectOptJSONObject2.optInt("id");
                                qVar2.f3608b = jSONObjectOptJSONObject2.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                                qVar2.f3609c = jSONObjectOptJSONObject2.optInt("mapsize");
                                qVar2.f3610d = jSONObjectOptJSONObject2.optInt("cty");
                                arrayList3.add(qVar2);
                            } catch (JSONException unused) {
                                return null;
                            } catch (Exception unused2) {
                                return null;
                            }
                        }
                        qVar.a(arrayList3);
                    }
                    arrayList2.add(qVar);
                }
                i++;
                arrayList = null;
            }
            return arrayList2;
        } catch (JSONException unused3) {
            return arrayList;
        } catch (Exception unused4) {
            return arrayList;
        }
    }

    public boolean d(int i) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.f3614b;
        if (aVar == null) {
            return false;
        }
        return aVar.b(0, true, i);
    }

    public ArrayList<u> e() {
        String strL;
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.f3614b;
        if (aVar != null && (strL = aVar.l()) != null && !strL.equals("")) {
            ArrayList<u> arrayList = new ArrayList<>();
            try {
                JSONObject jSONObject = new JSONObject(strL);
                if (jSONObject.length() == 0) {
                    return null;
                }
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("dataset");
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    u uVar = new u();
                    t tVar = new t();
                    JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                    tVar.f3618a = jSONObjectOptJSONObject.optInt("id");
                    tVar.f3619b = jSONObjectOptJSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                    tVar.f3620c = jSONObjectOptJSONObject.optString("pinyin");
                    tVar.f3625h = jSONObjectOptJSONObject.optInt("mapoldsize");
                    tVar.i = jSONObjectOptJSONObject.optInt("ratio");
                    tVar.l = jSONObjectOptJSONObject.optInt(NotificationCompat.CATEGORY_STATUS);
                    tVar.f3624g = new GeoPoint(jSONObjectOptJSONObject.optInt("y"), jSONObjectOptJSONObject.optInt("x"));
                    boolean z = true;
                    if (jSONObjectOptJSONObject.optInt("up") != 1) {
                        z = false;
                    }
                    tVar.j = z;
                    tVar.f3622e = jSONObjectOptJSONObject.optInt("lev");
                    if (tVar.j) {
                        tVar.k = jSONObjectOptJSONObject.optInt("mapsize");
                    } else {
                        tVar.k = 0;
                    }
                    uVar.a(tVar);
                    arrayList.add(uVar);
                }
                return arrayList;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public boolean e(int i) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.f3614b;
        if (aVar == null || i < 0) {
            return false;
        }
        return aVar.b(i, false);
    }

    public boolean f(int i) {
        if (this.f3614b == null || i < 0) {
            return false;
        }
        if (i <= 2000 || i == 2912 || i == 2911 || i == 9000) {
            return this.f3614b.a(i, false);
        }
        return false;
    }

    public u g(int i) {
        String strE;
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.f3614b;
        if (aVar != null && i >= 0 && (strE = aVar.e(i)) != null && !strE.equals("")) {
            u uVar = new u();
            t tVar = new t();
            try {
                JSONObject jSONObject = new JSONObject(strE);
                if (jSONObject.length() == 0) {
                    return null;
                }
                int iOptInt = jSONObject.optInt("id");
                if (iOptInt > 2000 && iOptInt != 2912 && iOptInt != 2911 && iOptInt != 9000) {
                    return null;
                }
                tVar.f3618a = iOptInt;
                tVar.f3619b = jSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                tVar.f3620c = jSONObject.optString("pinyin");
                tVar.f3621d = jSONObject.optString("headchar");
                tVar.f3625h = jSONObject.optInt("mapoldsize");
                tVar.i = jSONObject.optInt("ratio");
                tVar.l = jSONObject.optInt(NotificationCompat.CATEGORY_STATUS);
                tVar.f3624g = new GeoPoint(jSONObject.optInt("y"), jSONObject.optInt("x"));
                boolean z = true;
                if (jSONObject.optInt("up") != 1) {
                    z = false;
                }
                tVar.j = z;
                tVar.f3622e = jSONObject.optInt("lev");
                if (tVar.j) {
                    tVar.k = jSONObject.optInt("mapsize");
                } else {
                    tVar.k = 0;
                }
                tVar.f3623f = jSONObject.optInt("ver");
                uVar.a(tVar);
                return uVar;
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}