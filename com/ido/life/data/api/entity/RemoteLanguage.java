package com.ido.life.data.api.entity;

import com.ido.common.net.BaseEntity;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class RemoteLanguage extends BaseEntity {
    private List<LanguageInfo> result;

    public List<LanguageInfo> getLanguageList() {
        return this.result;
    }

    public void setLanguageList(List<LanguageInfo> list) {
        this.result = list;
    }

    public static class LanguageInfo {
        private String code;
        private int codeId;
        private String description;
        public boolean hasNewVersion;
        private int id;
        public boolean installed;
        private String langVersion;
        private String md5;
        private String name;
        private String sha128;
        private String sha256;
        private String sha512;
        private String sid;
        private int size;
        private String url;

        public LanguageInfo() {
        }

        public LanguageInfo(int i, String str, boolean z) {
            this.codeId = i;
            this.name = str;
            this.installed = z;
        }

        public LanguageInfo(int i, String str, String str2, boolean z) {
            this.codeId = i;
            this.name = str;
            this.code = str2;
            this.installed = z;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public int getSize() {
            return this.size;
        }

        public void setSize(int i) {
            this.size = i;
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String str) {
            this.code = str;
        }

        public int getCodeId() {
            return this.codeId;
        }

        public void setCodeId(int i) {
            this.codeId = i;
        }

        public String getMd5() {
            return this.md5;
        }

        public void setMd5(String str) {
            this.md5 = str;
        }

        public String getSha128() {
            return this.sha128;
        }

        public void setSha128(String str) {
            this.sha128 = str;
        }

        public String getSha256() {
            return this.sha256;
        }

        public void setSha256(String str) {
            this.sha256 = str;
        }

        public String getSha512() {
            return this.sha512;
        }

        public void setSha512(String str) {
            this.sha512 = str;
        }

        public String getLangVersion() {
            return this.langVersion;
        }

        public void setLangVersion(String str) {
            this.langVersion = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public String getSid() {
            return this.sid;
        }

        public void setSid(String str) {
            this.sid = str;
        }

        public String toString() {
            return "LanguageInfo{id=" + this.id + ", name='" + this.name + "', url='" + this.url + "', size=" + this.size + ", code='" + this.code + "', codeId=" + this.codeId + ", md5='" + this.md5 + "', sha128='" + this.sha128 + "', sha256='" + this.sha256 + "', sha512='" + this.sha512 + "', langVersion='" + this.langVersion + "', description='" + this.description + "', installed=" + this.installed + ", hasNewVersion=" + this.hasNewVersion + ", sid='" + this.sid + "'}";
        }
    }
}