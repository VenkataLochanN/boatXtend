package com.baidu.mapapi.favorite;

import android.util.Log;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapsdkplatform.comapi.favrite.FavSyncPoi;
import com.baidu.mapsdkplatform.comapi.map.i;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class FavoriteManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static FavoriteManager f2734a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static com.baidu.mapsdkplatform.comapi.favrite.a f2735b;

    private FavoriteManager() {
    }

    public static FavoriteManager getInstance() {
        if (f2734a == null) {
            f2734a = new FavoriteManager();
        }
        return f2734a;
    }

    public int add(FavoritePoiInfo favoritePoiInfo) {
        String str;
        if (f2735b == null) {
            str = "you may have not call init method!";
        } else {
            if (favoritePoiInfo != null && favoritePoiInfo.f2738c != null) {
                if (favoritePoiInfo.f2737b == null || favoritePoiInfo.f2737b.equals("")) {
                    Log.e("baidumapsdk", "poiName can not be null or empty!");
                    return -1;
                }
                FavSyncPoi favSyncPoiA = a.a(favoritePoiInfo);
                int iA = f2735b.a(favSyncPoiA.f3484b, favSyncPoiA);
                if (iA == 1) {
                    favoritePoiInfo.f2736a = favSyncPoiA.f3483a;
                    favoritePoiInfo.f2742g = Long.parseLong(favSyncPoiA.f3490h);
                }
                return iA;
            }
            str = "object or pt can not be null!";
        }
        Log.e("baidumapsdk", str);
        return 0;
    }

    public boolean clearAllFavPois() {
        com.baidu.mapsdkplatform.comapi.favrite.a aVar = f2735b;
        if (aVar != null) {
            return aVar.c();
        }
        Log.e("baidumapsdk", "you may have not call init method!");
        return false;
    }

    public boolean deleteFavPoi(String str) {
        if (f2735b == null) {
            Log.e("baidumapsdk", "you may have not call init method!");
            return false;
        }
        if (str == null || str.equals("")) {
            return false;
        }
        return f2735b.a(str);
    }

    public void destroy() {
        com.baidu.mapsdkplatform.comapi.favrite.a aVar = f2735b;
        if (aVar != null) {
            aVar.b();
            f2735b = null;
            BMapManager.destroy();
            i.b();
        }
    }

    public List<FavoritePoiInfo> getAllFavPois() {
        JSONArray jSONArrayOptJSONArray;
        com.baidu.mapsdkplatform.comapi.favrite.a aVar = f2735b;
        if (aVar == null) {
            Log.e("baidumapsdk", "you may have not call init method!");
            return null;
        }
        String strF = aVar.f();
        if (strF != null && !strF.equals("")) {
            try {
                JSONObject jSONObject = new JSONObject(strF);
                if (jSONObject.optInt("favpoinum") != 0 && (jSONArrayOptJSONArray = jSONObject.optJSONArray("favcontents")) != null && jSONArrayOptJSONArray.length() > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                        if (jSONObject2 != null) {
                            arrayList.add(a.a(jSONObject2));
                        }
                    }
                    return arrayList;
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public FavoritePoiInfo getFavPoi(String str) {
        FavSyncPoi favSyncPoiB;
        if (f2735b == null) {
            Log.e("baidumapsdk", "you may have not call init method!");
            return null;
        }
        if (str == null || str.equals("") || (favSyncPoiB = f2735b.b(str)) == null) {
            return null;
        }
        return a.a(favSyncPoiB);
    }

    public void init() {
        if (f2735b == null) {
            i.a();
            BMapManager.init();
            f2735b = com.baidu.mapsdkplatform.comapi.favrite.a.a();
        }
    }

    public boolean updateFavPoi(String str, FavoritePoiInfo favoritePoiInfo) {
        String str2;
        if (f2735b == null) {
            str2 = "you may have not call init method!";
        } else {
            if (str == null || str.equals("") || favoritePoiInfo == null) {
                return false;
            }
            if (favoritePoiInfo == null || favoritePoiInfo.f2738c == null) {
                str2 = "object or pt can not be null!";
            } else {
                if (favoritePoiInfo.f2737b != null && !favoritePoiInfo.f2737b.equals("")) {
                    favoritePoiInfo.f2736a = str;
                    return f2735b.b(str, a.a(favoritePoiInfo));
                }
                str2 = "poiName can not be null or empty!";
            }
        }
        Log.e("baidumapsdk", str2);
        return false;
    }
}