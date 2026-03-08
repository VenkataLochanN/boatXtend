package com.baidu.mapsdkplatform.comjni.map.basemap;

import android.os.Bundle;
import com.baidu.mapapi.map.MapBaseIndoorMapInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3860a = a.class.getSimpleName();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Set<Integer> f3861d = new HashSet();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static List<JNIBaseMap> f3862e = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f3863b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private JNIBaseMap f3864c;

    public a() {
        this.f3864c = null;
        this.f3864c = new JNIBaseMap();
    }

    public static int a(long j, int i, int i2, int i3) {
        return JNIBaseMap.MapProc(j, i, i2, i3);
    }

    public static List<JNIBaseMap> b() {
        return f3862e;
    }

    public static void b(long j, boolean z) {
        JNIBaseMap.SetMapCustomEnable(j, z);
    }

    public long a() {
        return this.f3863b;
    }

    public long a(int i, int i2, String str) {
        return this.f3864c.AddLayer(this.f3863b, i, i2, str);
    }

    public String a(int i, int i2) {
        return this.f3864c.ScrPtToGeoPoint(this.f3863b, i, i2);
    }

    public String a(int i, int i2, int i3, int i4) {
        return this.f3864c.GetNearlyObjID(this.f3863b, i, i2, i3, i4);
    }

    public String a(String str) {
        return this.f3864c.OnSchcityGet(this.f3863b, str);
    }

    public void a(long j, long j2, long j3, long j4, boolean z) {
        this.f3864c.setCustomTrafficColor(this.f3863b, j, j2, j3, j4, z);
    }

    public void a(long j, boolean z) {
        this.f3864c.ShowLayers(this.f3863b, j, z);
    }

    public void a(Bundle bundle) {
        this.f3864c.SetMapStatus(this.f3863b, bundle);
    }

    public void a(String str, Bundle bundle) {
        this.f3864c.SaveScreenToLocal(this.f3863b, str, bundle);
    }

    public void a(boolean z) {
        this.f3864c.ShowSatelliteMap(this.f3863b, z);
    }

    public void a(Bundle[] bundleArr) {
        this.f3864c.addOverlayItems(this.f3863b, bundleArr, bundleArr.length);
    }

    public boolean a(int i) {
        this.f3863b = f3862e.size() == 0 ? this.f3864c.Create() : this.f3864c.CreateDuplicate(f3862e.get(0).f3859a);
        JNIBaseMap jNIBaseMap = this.f3864c;
        jNIBaseMap.f3859a = this.f3863b;
        f3862e.add(jNIBaseMap);
        f3861d.add(Integer.valueOf(i));
        this.f3864c.SetCallback(this.f3863b, null);
        return true;
    }

    public boolean a(int i, boolean z) {
        return this.f3864c.OnRecordReload(this.f3863b, i, z);
    }

    public boolean a(int i, boolean z, int i2) {
        return this.f3864c.OnRecordStart(this.f3863b, i, z, i2);
    }

    public boolean a(long j) {
        return this.f3864c.LayersIsShow(this.f3863b, j);
    }

    public boolean a(long j, long j2) {
        return this.f3864c.SwitchLayer(this.f3863b, j, j2);
    }

    public boolean a(String str, String str2) {
        return this.f3864c.SwitchBaseIndoorMapFloor(this.f3863b, str, str2);
    }

    public boolean a(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, String str8, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return this.f3864c.Init(this.f3863b, str, str2, str3, str4, str5, str6, str7, i, str8, i2, i3, i4, i5, i6, i7, i8);
    }

    public boolean a(boolean z, boolean z2) {
        return this.f3864c.OnRecordImport(this.f3863b, z, z2);
    }

    public int[] a(int[] iArr, int i, int i2) {
        return this.f3864c.GetScreenBuf(this.f3863b, iArr, i, i2);
    }

    public String b(int i, int i2) {
        return this.f3864c.GeoPtToScrPoint(this.f3863b, i, i2);
    }

    public void b(long j) {
        this.f3864c.UpdateLayers(this.f3863b, j);
    }

    public void b(Bundle bundle) {
        this.f3864c.setMapStatusLimits(this.f3863b, bundle);
    }

    public void b(boolean z) {
        this.f3864c.ShowHotMap(this.f3863b, z);
    }

    public boolean b(int i) {
        this.f3864c.Release(this.f3863b);
        f3862e.remove(this.f3864c);
        f3861d.remove(Integer.valueOf(i));
        this.f3863b = 0L;
        return true;
    }

    public boolean b(int i, boolean z) {
        return this.f3864c.OnRecordRemove(this.f3863b, i, z);
    }

    public boolean b(int i, boolean z, int i2) {
        return this.f3864c.OnRecordSuspend(this.f3863b, i, z, i2);
    }

    public float c(Bundle bundle) {
        return this.f3864c.GetZoomToBound(this.f3863b, bundle);
    }

    public int c(int i) {
        return this.f3864c.SetMapControlMode(this.f3863b, i);
    }

    public void c() {
        this.f3864c.OnPause(this.f3863b);
    }

    public void c(boolean z) {
        this.f3864c.ShowTrafficMap(this.f3863b, z);
    }

    public boolean c(long j) {
        return this.f3864c.cleanSDKTileDataCache(this.f3863b, j);
    }

    public void d() {
        this.f3864c.OnResume(this.f3863b);
    }

    public void d(long j) {
        this.f3864c.ClearLayer(this.f3863b, j);
    }

    public void d(boolean z) {
        this.f3864c.enableDrawHouseHeight(this.f3863b, z);
    }

    public boolean d(int i) {
        return this.f3864c.OnRecordAdd(this.f3863b, i);
    }

    public boolean d(Bundle bundle) {
        return this.f3864c.updateSDKTile(this.f3863b, bundle);
    }

    public String e(int i) {
        return this.f3864c.OnRecordGetAt(this.f3863b, i);
    }

    public String e(long j) {
        return this.f3864c.getCompassPosition(this.f3863b, j);
    }

    public void e() {
        this.f3864c.OnBackground(this.f3863b);
    }

    public void e(boolean z) {
        this.f3864c.ShowBaseIndoorMap(this.f3863b, z);
    }

    public boolean e(Bundle bundle) {
        return this.f3864c.addtileOverlay(this.f3863b, bundle);
    }

    public void f() {
        this.f3864c.OnForeground(this.f3863b);
    }

    public void f(Bundle bundle) {
        this.f3864c.addOneOverlayItem(this.f3863b, bundle);
    }

    public void g() {
        this.f3864c.ResetImageRes(this.f3863b);
    }

    public void g(Bundle bundle) {
        this.f3864c.updateOneOverlayItem(this.f3863b, bundle);
    }

    public Bundle h() {
        return this.f3864c.GetMapStatus(this.f3863b);
    }

    public void h(Bundle bundle) {
        this.f3864c.removeOneOverlayItem(this.f3863b, bundle);
    }

    public Bundle i() {
        return this.f3864c.getMapStatusLimits(this.f3863b);
    }

    public Bundle j() {
        return this.f3864c.getDrawingMapStatus(this.f3863b);
    }

    public boolean k() {
        return this.f3864c.GetBaiduHotMapCityInfo(this.f3863b);
    }

    public String l() {
        return this.f3864c.OnRecordGetAll(this.f3863b);
    }

    public String m() {
        return this.f3864c.OnHotcityGet(this.f3863b);
    }

    public void n() {
        this.f3864c.PostStatInfo(this.f3863b);
    }

    public boolean o() {
        return this.f3864c.isDrawHouseHeightEnable(this.f3863b);
    }

    public void p() {
        this.f3864c.clearHeatMapLayerCache(this.f3863b);
    }

    public MapBaseIndoorMapInfo q() {
        String str = this.f3864c.getfocusedBaseIndoorMapInfo(this.f3863b);
        if (str == null) {
            return null;
        }
        String strOptString = "";
        String str2 = new String();
        ArrayList arrayList = new ArrayList(1);
        try {
            JSONObject jSONObject = new JSONObject(str);
            strOptString = jSONObject.optString("focusindoorid");
            str2 = jSONObject.optString("curfloor");
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("floorlist");
            if (jSONArrayOptJSONArray == null) {
                return null;
            }
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(jSONArrayOptJSONArray.get(i).toString());
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return new MapBaseIndoorMapInfo(strOptString, str2, arrayList);
    }

    public boolean r() {
        return this.f3864c.IsBaseIndoorMapMode(this.f3863b);
    }

    public void s() {
        this.f3864c.setBackgroundTransparent(this.f3863b);
    }

    public void t() {
        this.f3864c.resetBackgroundTransparent(this.f3863b);
    }

    public float[] u() {
        JNIBaseMap jNIBaseMap = this.f3864c;
        if (jNIBaseMap == null) {
            return null;
        }
        float[] fArr = new float[16];
        jNIBaseMap.getProjectionMatrix(this.f3863b, fArr, 16);
        return fArr;
    }

    public float[] v() {
        JNIBaseMap jNIBaseMap = this.f3864c;
        if (jNIBaseMap == null) {
            return null;
        }
        float[] fArr = new float[16];
        jNIBaseMap.getViewMatrix(this.f3863b, fArr, 16);
        return fArr;
    }
}