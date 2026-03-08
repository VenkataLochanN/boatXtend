package com.baidu.mapapi.cloud;

/* JADX INFO: loaded from: classes.dex */
public interface CloudListener {
    void onGetCloudRgcResult(CloudRgcResult cloudRgcResult, int i);

    void onGetDetailSearchResult(DetailSearchResult detailSearchResult, int i);

    void onGetSearchResult(CloudSearchResult cloudSearchResult, int i);
}