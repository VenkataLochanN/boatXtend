package com.baidu.mapapi.cloud;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class DetailSearchResult extends BaseSearchResult {
    public CloudPoiInfo poiInfo;

    @Override // com.baidu.mapapi.cloud.BaseSearchResult
    public void parseFromJSON(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArrayOptJSONArray;
        JSONObject jSONObjectOptJSONObject;
        super.parseFromJSON(jSONObject);
        if (this.status != 0 || (jSONArrayOptJSONArray = jSONObject.optJSONArray("contents")) == null || (jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(0)) == null) {
            return;
        }
        this.poiInfo = new CloudPoiInfo();
        this.poiInfo.a(jSONObjectOptJSONObject);
    }
}