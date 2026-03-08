package com.baidu.mapapi.synchronization;

import android.content.Context;
import android.view.View;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.Marker;

/* JADX INFO: loaded from: classes.dex */
public class SynchronizationDisplayManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3328a = SynchronizationDisplayManager.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.baidu.mapsdkplatform.comapi.synchronization.a.a f3329b;

    public SynchronizationDisplayManager(Context context, BaiduMap baiduMap, RoleOptions roleOptions, DisplayOptions displayOptions) {
        this.f3329b = new com.baidu.mapsdkplatform.comapi.synchronization.a.a(context, baiduMap, roleOptions, displayOptions);
    }

    public void adjustVisibleSpanByUser() {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        } else {
            aVar.g();
        }
    }

    public void adjustVisibleSpanByUser(int i, int i2, int i3, int i4) {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        } else {
            aVar.a(i, i2, i3, i4);
        }
    }

    public Marker getCarMarker() {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar != null) {
            return aVar.f();
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        return null;
    }

    public Marker getEndPositionMarker() {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar != null) {
            return aVar.e();
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        return null;
    }

    public Marker getStartPositionMarker() {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar != null) {
            return aVar.d();
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        return null;
    }

    public boolean isHttpsEnable() {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar != null) {
            return aVar.h();
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        return true;
    }

    public void onPause() {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        } else {
            aVar.b();
        }
    }

    public void onResume() {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        } else {
            aVar.a();
        }
    }

    public void registerSynchronizationDisplayListener(SynchronizationDisplayListener synchronizationDisplayListener) {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        } else {
            aVar.a(synchronizationDisplayListener);
        }
    }

    public void release() {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        } else {
            aVar.c();
        }
    }

    public void setDriverPositionFreshFrequency(int i) {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        } else {
            aVar.b(i);
        }
    }

    public void setHttpsEnable(boolean z) {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        } else {
            aVar.a(z);
        }
    }

    public void setOperatedMapFrozenInterval(int i) {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        } else {
            aVar.c(i);
        }
    }

    public void setUnOperatedMapFrozenInterval(int i) {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        } else {
            aVar.d(i);
        }
    }

    public void unRegisterSynchronizationDisplayListener(SynchronizationDisplayListener synchronizationDisplayListener) {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        } else {
            aVar.b(synchronizationDisplayListener);
        }
    }

    public void updateCarPositionInfoWindowView(View view) {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        } else {
            aVar.c(view);
        }
    }

    public void updateDisplayOptions(DisplayOptions displayOptions) {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        } else {
            aVar.a(displayOptions);
        }
    }

    public void updateEndPositionInfoWindowView(View view) {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        } else {
            aVar.b(view);
        }
    }

    public void updateOrderState(int i) {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        } else {
            aVar.a(i);
        }
    }

    public void updateRoleOptions(RoleOptions roleOptions) {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        } else {
            aVar.a(roleOptions);
        }
    }

    public void updateStartPositionInfoWindowView(View view) {
        com.baidu.mapsdkplatform.comapi.synchronization.a.a aVar = this.f3329b;
        if (aVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3328a, "The implement instance is null");
        } else {
            aVar.a(view);
        }
    }
}