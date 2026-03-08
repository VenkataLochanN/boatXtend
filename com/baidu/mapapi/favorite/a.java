package com.baidu.mapapi.favorite;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.mapsdkplatform.comapi.favrite.FavSyncPoi;
import com.ido.common.constant.LanguageRegion;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class a {
    static FavoritePoiInfo a(FavSyncPoi favSyncPoi) {
        if (favSyncPoi == null || favSyncPoi.f3485c == null || favSyncPoi.f3484b.equals("")) {
            return null;
        }
        FavoritePoiInfo favoritePoiInfo = new FavoritePoiInfo();
        favoritePoiInfo.f2736a = favSyncPoi.f3483a;
        favoritePoiInfo.f2737b = favSyncPoi.f3484b;
        favoritePoiInfo.f2738c = new LatLng(((double) favSyncPoi.f3485c.y) / 1000000.0d, ((double) favSyncPoi.f3485c.x) / 1000000.0d);
        favoritePoiInfo.f2740e = favSyncPoi.f3487e;
        favoritePoiInfo.f2741f = favSyncPoi.f3488f;
        favoritePoiInfo.f2739d = favSyncPoi.f3486d;
        favoritePoiInfo.f2742g = Long.parseLong(favSyncPoi.f3490h);
        return favoritePoiInfo;
    }

    static FavoritePoiInfo a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        FavoritePoiInfo favoritePoiInfo = new FavoritePoiInfo();
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(LanguageRegion.PT);
        if (jSONObjectOptJSONObject != null) {
            favoritePoiInfo.f2738c = new LatLng(((double) jSONObjectOptJSONObject.optInt("y")) / 1000000.0d, ((double) jSONObjectOptJSONObject.optInt("x")) / 1000000.0d);
        }
        favoritePoiInfo.f2737b = jSONObject.optString("uspoiname");
        favoritePoiInfo.f2742g = Long.parseLong(jSONObject.optString("addtimesec"));
        favoritePoiInfo.f2739d = jSONObject.optString("addr");
        favoritePoiInfo.f2741f = jSONObject.optString("uspoiuid");
        favoritePoiInfo.f2740e = jSONObject.optString("ncityid");
        favoritePoiInfo.f2736a = jSONObject.optString("key");
        return favoritePoiInfo;
    }

    static FavSyncPoi a(FavoritePoiInfo favoritePoiInfo) {
        if (favoritePoiInfo == null || favoritePoiInfo.f2738c == null || favoritePoiInfo.f2737b == null || favoritePoiInfo.f2737b.equals("")) {
            return null;
        }
        FavSyncPoi favSyncPoi = new FavSyncPoi();
        favSyncPoi.f3484b = favoritePoiInfo.f2737b;
        favSyncPoi.f3485c = new Point((int) (favoritePoiInfo.f2738c.longitude * 1000000.0d), (int) (favoritePoiInfo.f2738c.latitude * 1000000.0d));
        favSyncPoi.f3486d = favoritePoiInfo.f2739d;
        favSyncPoi.f3487e = favoritePoiInfo.f2740e;
        favSyncPoi.f3488f = favoritePoiInfo.f2741f;
        favSyncPoi.i = false;
        return favSyncPoi;
    }
}