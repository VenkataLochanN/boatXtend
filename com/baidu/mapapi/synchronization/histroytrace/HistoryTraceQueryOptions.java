package com.baidu.mapapi.synchronization.histroytrace;

/* JADX INFO: loaded from: classes.dex */
public class HistoryTraceQueryOptions {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f3351a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3352b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3353c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3354d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3355e = 4;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3356f = 5;

    public int getCurrentOrderState() {
        return this.f3356f;
    }

    public String getDriverId() {
        return this.f3354d;
    }

    public String getOrderId() {
        return this.f3351a;
    }

    public int getQueryOrderState() {
        return this.f3355e;
    }

    public int getRoleType() {
        return this.f3352b;
    }

    public String getUserId() {
        return this.f3353c;
    }

    public HistoryTraceQueryOptions setCurrentOrderState(int i) {
        this.f3356f = i;
        return this;
    }

    public HistoryTraceQueryOptions setDriverId(String str) {
        this.f3354d = str;
        return this;
    }

    public HistoryTraceQueryOptions setOrderId(String str) {
        this.f3351a = str;
        return this;
    }

    public HistoryTraceQueryOptions setQueryOrderState(int i) {
        this.f3355e = i;
        return this;
    }

    public HistoryTraceQueryOptions setRoleType(int i) {
        this.f3352b = i;
        return this;
    }

    public HistoryTraceQueryOptions setUserId(String str) {
        this.f3353c = str;
        return this;
    }
}