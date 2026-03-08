package com.baidu.mapapi.cloud;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CloudSearchResult extends BaseSearchResult {
    public List<CloudPoiInfo> poiList;

    @Override // com.baidu.mapapi.cloud.BaseSearchResult
    public void parseFromJSON(JSONObject jSONObject) throws JSONException {
        super.parseFromJSON(jSONObject);
        if (this.status != 0) {
            return;
        }
        this.poiList = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("contents");
        if (jSONArrayOptJSONArray == null) {
            return;
        }
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                CloudPoiInfo cloudPoiInfo = new CloudPoiInfo();
                cloudPoiInfo.a(jSONObjectOptJSONObject);
                this.poiList.add(cloudPoiInfo);
            }
        }
    }
}