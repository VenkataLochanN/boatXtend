package com.amazon.identity.auth.device.dataobject;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class Scope {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final String mScopeData;
    private final String mScopeName;
    private String scopeDescription;

    public static String getDescription(String str, String str2) {
        return str;
    }

    public Scope(String str) {
        this(str, null);
    }

    public Scope(String str, String str2) {
        this.scopeDescription = null;
        this.mScopeName = str;
        this.mScopeData = str2;
    }

    public String getScopeName() {
        return this.mScopeName;
    }

    public String getScopeData() {
        return this.mScopeData;
    }

    public boolean isLocal() {
        return isLocal(this.mScopeName);
    }

    public synchronized String getScopeDescription() {
        if (this.scopeDescription == null) {
            this.scopeDescription = getDescription(this.mScopeName, Locale.getDefault().getLanguage());
        }
        return this.scopeDescription;
    }

    public void setScopeDescription(String str) {
        this.scopeDescription = str;
    }

    public static boolean isLocal(String str) {
        return str.startsWith("device");
    }
}