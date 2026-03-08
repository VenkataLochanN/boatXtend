package com.realsil.sdk.core.utility;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class DependenceManager {
    public static DependenceManager mInstance;
    public Map<String, DependenceLib> dc;

    public static class DependenceLib {
        public String artifactId;
        public String groupId;
        public String version;

        public DependenceLib(String str, String str2, String str3) {
            this.groupId = str;
            this.artifactId = str2;
            this.version = str3;
        }
    }

    public DependenceManager() {
        this.dc = new HashMap();
        Map<String, DependenceLib> map = this.dc;
        if (map == null) {
            this.dc = new HashMap();
        } else {
            map.clear();
        }
    }

    public static DependenceManager getInstance() {
        if (mInstance == null) {
            synchronized (DependenceManager.class) {
                if (mInstance == null) {
                    mInstance = new DependenceManager();
                }
            }
        }
        return mInstance;
    }

    public Map<String, DependenceLib> getLibMap() {
        return this.dc;
    }

    public void register(DependenceLib dependenceLib) {
        if (dependenceLib == null) {
            return;
        }
        if (this.dc == null) {
            this.dc = new HashMap();
        }
        if (this.dc.containsKey(dependenceLib.artifactId)) {
            return;
        }
        this.dc.put(dependenceLib.artifactId, dependenceLib);
    }
}