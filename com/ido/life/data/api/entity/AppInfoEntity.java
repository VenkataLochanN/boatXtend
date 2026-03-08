package com.ido.life.data.api.entity;

import com.ido.common.net.BaseEntity;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class AppInfoEntity extends BaseEntity implements Serializable {
    public AppInfo result;

    public AppInfo getResult() {
        return this.result;
    }

    public void setResult(AppInfo appInfo) {
        this.result = appInfo;
    }

    public String toString() {
        return "AppInfoEntity{result=" + this.result + '}';
    }

    public static class AppInfo {
        private int appId;
        private boolean forceUpdate;
        private int id;
        private String linkUrl;
        private String updateDescription;
        private String version;

        public int getAppId() {
            return this.appId;
        }

        public void setAppId(int i) {
            this.appId = i;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public String getUpdateDescription() {
            return this.updateDescription;
        }

        public void setUpdateDescription(String str) {
            this.updateDescription = str;
        }

        public boolean isForceUpdate() {
            return this.forceUpdate;
        }

        public void setForceUpdate(boolean z) {
            this.forceUpdate = z;
        }

        public String getLinkUrl() {
            return this.linkUrl;
        }

        public void setLinkUrl(String str) {
            this.linkUrl = str;
        }

        public String getVersion() {
            return this.version;
        }

        public void setVersion(String str) {
            this.version = str;
        }

        public String toString() {
            return "AppInfo{appId=" + this.appId + ", id=" + this.id + ", updateDescription='" + this.updateDescription + "', forceUpdate=" + this.forceUpdate + ", linkUrl='" + this.linkUrl + "', version='" + this.version + "'}";
        }
    }
}