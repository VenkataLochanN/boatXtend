package com.baidu.mapsdkplatform.comapi.util;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SysUpdateObservable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile SysUpdateObservable f3824a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<SysUpdateObserver> f3825b;

    private SysUpdateObservable() {
        this.f3825b = null;
        this.f3825b = new ArrayList();
    }

    public static SysUpdateObservable getInstance() {
        if (f3824a == null) {
            synchronized (SysUpdateObservable.class) {
                if (f3824a == null) {
                    f3824a = new SysUpdateObservable();
                }
            }
        }
        return f3824a;
    }

    public void addObserver(SysUpdateObserver sysUpdateObserver) {
        this.f3825b.add(sysUpdateObserver);
    }

    public void init() {
        for (SysUpdateObserver sysUpdateObserver : this.f3825b) {
            if (sysUpdateObserver != null) {
                sysUpdateObserver.init();
            }
        }
    }

    public void updateNetworkInfo(Context context) {
        for (SysUpdateObserver sysUpdateObserver : this.f3825b) {
            if (sysUpdateObserver != null) {
                sysUpdateObserver.updateNetworkInfo(context);
            }
        }
    }

    public void updateNetworkProxy(Context context) {
        for (SysUpdateObserver sysUpdateObserver : this.f3825b) {
            if (sysUpdateObserver != null) {
                sysUpdateObserver.updateNetworkProxy(context);
            }
        }
    }

    public void updatePhoneInfo() {
        for (SysUpdateObserver sysUpdateObserver : this.f3825b) {
            if (sysUpdateObserver != null) {
                sysUpdateObserver.updatePhoneInfo();
            }
        }
    }
}