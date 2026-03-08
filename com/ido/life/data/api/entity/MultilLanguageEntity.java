package com.ido.life.data.api.entity;

import com.ido.common.net.BaseEntity;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class MultilLanguageEntity extends BaseEntity {
    private MultilLangResult result;

    public MultilLangResult getResult() {
        return this.result;
    }

    public void setResult(MultilLangResult multilLangResult) {
        this.result = multilLangResult;
    }

    public static class MultilLangResult implements Serializable {
        protected int codeId;
        private String description;
        protected int id;
        private LangCode langCode;
        private String langFileMd5;
        private String langFileSha128;
        private String langFileSha256;
        private String langFileSha512;
        protected int langFileSize;
        protected String langFileUrl;
        private String langFileVersion;
        protected String name;

        public int getCodeId() {
            return this.codeId;
        }

        public void setCodeId(int i) {
            this.codeId = i;
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

        public String getLangFileUrl() {
            return this.langFileUrl;
        }

        public void setLangFileUrl(String str) {
            this.langFileUrl = str;
        }

        public int getLangFileSize() {
            return this.langFileSize;
        }

        public void setLangFileSize(int i) {
            this.langFileSize = i;
        }

        public String getLangFileMd5() {
            return this.langFileMd5;
        }

        public void setLangFileMd5(String str) {
            this.langFileMd5 = str;
        }

        public String getLangFileSha256() {
            return this.langFileSha256;
        }

        public void setLangFileSha256(String str) {
            this.langFileSha256 = str;
        }

        public String getLangFileSha128() {
            return this.langFileSha128;
        }

        public void setLangFileSha128(String str) {
            this.langFileSha128 = str;
        }

        public String getLangFileSha512() {
            return this.langFileSha512;
        }

        public void setLangFileSha512(String str) {
            this.langFileSha512 = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public String getLangFileVersion() {
            return this.langFileVersion;
        }

        public void setLangFileVersion(String str) {
            this.langFileVersion = str;
        }

        public LangCode getLangCode() {
            return this.langCode;
        }

        public void setLangCode(LangCode langCode) {
            this.langCode = langCode;
        }
    }
}