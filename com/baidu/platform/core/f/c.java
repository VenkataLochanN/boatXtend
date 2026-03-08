package com.baidu.platform.core.f;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiChildrenInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c extends com.baidu.platform.base.d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3950b = c.class.getSimpleName();

    private LatLng a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        double dOptDouble = jSONObject.optDouble("lat");
        double dOptDouble2 = jSONObject.optDouble("lng");
        return SDKInitializer.getCoordType() == CoordType.GCJ02 ? CoordTrans.baiduToGcj(new LatLng(dOptDouble, dOptDouble2)) : new LatLng(dOptDouble, dOptDouble2);
    }

    private List<PoiChildrenInfo> a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.length() != 0) {
                PoiChildrenInfo poiChildrenInfo = new PoiChildrenInfo();
                poiChildrenInfo.setUid(jSONObjectOptJSONObject.optString("uid"));
                poiChildrenInfo.setName(jSONObjectOptJSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME));
                poiChildrenInfo.setShowName(jSONObjectOptJSONObject.optString("show_name"));
                poiChildrenInfo.setTag(jSONObjectOptJSONObject.optString("tag"));
                poiChildrenInfo.setAddress(jSONObjectOptJSONObject.optString("address"));
                arrayList.add(poiChildrenInfo);
            }
        }
        return arrayList;
    }

    private boolean a(String str, SuggestionResult suggestionResult) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.length() != 0) {
                int iOptInt = jSONObject.optInt(NotificationCompat.CATEGORY_STATUS);
                if (iOptInt == 0) {
                    return a(jSONObject, suggestionResult);
                }
                suggestionResult.error = iOptInt != 1 ? iOptInt != 2 ? SearchResult.ERRORNO.RESULT_NOT_FOUND : SearchResult.ERRORNO.SEARCH_OPTION_ERROR : SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                return false;
            }
        } catch (JSONException e2) {
            Log.e(f3950b, "Parse sug search error", e2);
        }
        suggestionResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
        return false;
    }

    private boolean a(JSONObject jSONObject, SuggestionResult suggestionResult) {
        if (jSONObject != null && jSONObject.length() != 0) {
            suggestionResult.error = SearchResult.ERRORNO.NO_ERROR;
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("result");
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() != 0) {
                ArrayList<SuggestionResult.SuggestionInfo> arrayList = new ArrayList<>();
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObject2 = (JSONObject) jSONArrayOptJSONArray.opt(i);
                    if (jSONObject2 != null && jSONObject2.length() != 0) {
                        SuggestionResult.SuggestionInfo suggestionInfo = new SuggestionResult.SuggestionInfo();
                        suggestionInfo.setKey(jSONObject2.optString(AppMeasurementSdk.ConditionalUserProperty.NAME));
                        suggestionInfo.setCity(jSONObject2.optString("city"));
                        suggestionInfo.setDistrict(jSONObject2.optString("district"));
                        suggestionInfo.setUid(jSONObject2.optString("uid"));
                        suggestionInfo.setTag(jSONObject2.optString("tag"));
                        suggestionInfo.setAddress(jSONObject2.optString("address"));
                        suggestionInfo.setPt(a(jSONObject2.optJSONObject(FirebaseAnalytics.Param.LOCATION)));
                        JSONArray jSONArrayOptJSONArray2 = jSONObject2.optJSONArray("children");
                        if (jSONArrayOptJSONArray2 != null && jSONArrayOptJSONArray2.length() != 0) {
                            suggestionInfo.setPoiChildrenInfoList(a(jSONArrayOptJSONArray2));
                        }
                        arrayList.add(suggestionInfo);
                    }
                }
                suggestionResult.setSuggestionInfo(arrayList);
                return true;
            }
            suggestionResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0066  */
    @Override // com.baidu.platform.base.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.baidu.mapapi.search.core.SearchResult a(java.lang.String r6) {
        /*
            r5 = this;
            com.baidu.mapapi.search.sug.SuggestionResult r0 = new com.baidu.mapapi.search.sug.SuggestionResult
            r0.<init>()
            if (r6 == 0) goto L19
            boolean r1 = r6.isEmpty()
            if (r1 == 0) goto Le
            goto L19
        Le:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: org.json.JSONException -> L81
            r1.<init>(r6)     // Catch: org.json.JSONException -> L81
            int r2 = r1.length()
            if (r2 != 0) goto L1e
        L19:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.RESULT_NOT_FOUND
        L1b:
            r0.error = r6
            return r0
        L1e:
            java.lang.String r2 = "SDK_InnerError"
            boolean r3 = r1.has(r2)
            r4 = 1
            if (r3 == 0) goto L77
            org.json.JSONObject r1 = r1.optJSONObject(r2)
            java.lang.String r2 = "PermissionCheckError"
            boolean r2 = r1.has(r2)
            if (r2 == 0) goto L36
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.PERMISSION_UNFINISHED
            goto L1b
        L36:
            java.lang.String r2 = "httpStateError"
            boolean r3 = r1.has(r2)
            if (r3 == 0) goto L77
            java.lang.String r6 = r1.optString(r2)
            r1 = -1
            int r2 = r6.hashCode()
            r3 = -879828873(0xffffffffcb8ee077, float:-1.872715E7)
            if (r2 == r3) goto L5c
            r3 = 1470557208(0x57a6ec18, float:3.670659E14)
            if (r2 == r3) goto L52
            goto L66
        L52:
            java.lang.String r2 = "REQUEST_ERROR"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L66
            r6 = r4
            goto L67
        L5c:
            java.lang.String r2 = "NETWORK_ERROR"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L66
            r6 = 0
            goto L67
        L66:
            r6 = r1
        L67:
            if (r6 == 0) goto L73
            if (r6 == r4) goto L70
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR
        L6d:
            r0.error = r6
            goto L76
        L70:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.REQUEST_ERROR
            goto L6d
        L73:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.NETWORK_ERROR
            goto L6d
        L76:
            return r0
        L77:
            boolean r1 = r5.a(r6, r0, r4)
            if (r1 != 0) goto L80
            r5.a(r6, r0)
        L80:
            return r0
        L81:
            r6 = move-exception
            java.lang.String r1 = com.baidu.platform.core.f.c.f3950b
            java.lang.String r2 = "Parse suggestion search result error"
            android.util.Log.e(r1, r2, r6)
            goto L19
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.core.f.c.a(java.lang.String):com.baidu.mapapi.search.core.SearchResult");
    }

    @Override // com.baidu.platform.base.d
    public void a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetSuggestionResultListener)) {
            return;
        }
        ((OnGetSuggestionResultListener) obj).onGetSuggestionResult((SuggestionResult) searchResult);
    }
}