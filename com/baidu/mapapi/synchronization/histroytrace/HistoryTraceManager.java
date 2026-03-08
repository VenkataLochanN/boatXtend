package com.baidu.mapapi.synchronization.histroytrace;

import com.baidu.mapapi.map.BaiduMap;

/* JADX INFO: loaded from: classes.dex */
public class HistoryTraceManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.baidu.mapsdkplatform.comapi.synchronization.b.b f3350a = new com.baidu.mapsdkplatform.comapi.synchronization.b.b();

    public boolean isHttpsEnable() {
        com.baidu.mapsdkplatform.comapi.synchronization.b.b bVar = this.f3350a;
        if (bVar != null) {
            return bVar.b();
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b("HistoryTraceManager", "The implement instance is null");
        return true;
    }

    public void queryHistoryTraceData(HistoryTraceQueryOptions historyTraceQueryOptions) {
        com.baidu.mapsdkplatform.comapi.synchronization.b.b bVar = this.f3350a;
        if (bVar != null) {
            bVar.a(historyTraceQueryOptions);
        }
    }

    public void release() {
        com.baidu.mapsdkplatform.comapi.synchronization.b.b bVar = this.f3350a;
        if (bVar != null) {
            bVar.a();
        }
    }

    public void renderHistoryTrace(BaiduMap baiduMap, HistoryTraceData historyTraceData, HistoryTraceDisplayOptions historyTraceDisplayOptions, int i) {
        com.baidu.mapsdkplatform.comapi.synchronization.b.b bVar = this.f3350a;
        if (bVar != null) {
            bVar.a(baiduMap, historyTraceData, historyTraceDisplayOptions, i);
        }
    }

    public void setHttpsEnable(boolean z) {
        com.baidu.mapsdkplatform.comapi.synchronization.b.b bVar = this.f3350a;
        if (bVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b("HistoryTraceManager", "The implement instance is null");
        } else {
            bVar.a(z);
        }
    }

    public void setOnHistoryTraceListener(OnHistoryTraceListener onHistoryTraceListener) {
        if (onHistoryTraceListener == null) {
            throw new IllegalArgumentException("HistoryTraceManager-- OnHistoryTraceListener must not be null.");
        }
        com.baidu.mapsdkplatform.comapi.synchronization.b.b bVar = this.f3350a;
        if (bVar != null) {
            bVar.a(onHistoryTraceListener);
        }
    }
}