package com.baidu.mapapi.cloud;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.common.BaiduMapSDKException;
import com.baidu.platform.comjni.map.cloud.ICloudCenter;
import com.baidu.platform.comjni.map.cloud.a;

/* JADX INFO: loaded from: classes.dex */
public class CloudManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f2722a = CloudManager.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static CloudManager f2723b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ICloudCenter f2724c;

    private CloudManager() {
    }

    private boolean a(BaseCloudSearchInfo baseCloudSearchInfo) {
        String strA;
        if (baseCloudSearchInfo == null || (strA = baseCloudSearchInfo.a()) == null || strA.equals("")) {
            return false;
        }
        return this.f2724c.a(strA);
    }

    public static CloudManager getInstance() {
        if (f2723b == null) {
            f2723b = new CloudManager();
        }
        return f2723b;
    }

    public boolean boundSearch(BoundSearchInfo boundSearchInfo) {
        return a(boundSearchInfo);
    }

    public void destroy() {
        if (this.f2724c != null) {
            this.f2724c = null;
            BMapManager.destroy();
        }
    }

    public boolean detailSearch(DetailSearchInfo detailSearchInfo) {
        String strA;
        if (detailSearchInfo == null || (strA = detailSearchInfo.a()) == null || strA.equals("")) {
            return false;
        }
        return this.f2724c.b(strA);
    }

    public void init() {
        if (this.f2724c == null) {
            BMapManager.init();
            this.f2724c = new a();
        }
    }

    public void init(CloudListener cloudListener) {
        if (this.f2724c == null) {
            BMapManager.init();
            this.f2724c = new a();
            this.f2724c.a(cloudListener);
        }
    }

    public boolean localSearch(LocalSearchInfo localSearchInfo) {
        return a(localSearchInfo);
    }

    public boolean nearbySearch(NearbySearchInfo nearbySearchInfo) {
        return a(nearbySearchInfo);
    }

    public void registerListener(CloudListener cloudListener) {
        ICloudCenter iCloudCenter = this.f2724c;
        if (iCloudCenter == null) {
            return;
        }
        if (cloudListener == null) {
            throw new BaiduMapSDKException("the CloudListener should not be null.");
        }
        iCloudCenter.a(cloudListener);
    }

    public boolean rgcSearch(CloudRgcInfo cloudRgcInfo) {
        String strA;
        if (cloudRgcInfo == null || (strA = cloudRgcInfo.a()) == null || strA.equals("")) {
            return false;
        }
        return this.f2724c.c(strA);
    }

    public void unregisterListener() {
        ICloudCenter iCloudCenter = this.f2724c;
        if (iCloudCenter == null) {
            return;
        }
        iCloudCenter.a((CloudListener) null);
    }
}