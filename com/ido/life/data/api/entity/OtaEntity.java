package com.ido.life.data.api.entity;

import com.ido.common.net.BaseEntity;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class OtaEntity extends BaseEntity implements Serializable {
    private OtaInfo result;

    public OtaInfo getResult() {
        return this.result;
    }

    public void setResult(OtaInfo otaInfo) {
        this.result = otaInfo;
    }

    public static class OtaInfo implements Serializable {
        private String description;
        private String deviceId;
        private String deviceModel;
        private SystemConstituentInfo extensionFirmware;
        private String fileName;
        private String fileUrl;
        private String firmwareVersion;
        private boolean forced;
        private int id;
        private String md5;
        private FlashInfo otaFont;
        private List<LanguageInfo> otaLangs;
        private String sha256;
        private String sid;
        private int type;
        private String version;

        public boolean isForced() {
            return this.forced;
        }

        public void setForced(boolean z) {
            this.forced = z;
        }

        public String getFirmwareVersion() {
            return this.firmwareVersion;
        }

        public void setFirmwareVersion(String str) {
            this.firmwareVersion = str;
        }

        public SystemConstituentInfo getSystemConstituentInfo() {
            return this.extensionFirmware;
        }

        public void setSystemConstituentInfo(SystemConstituentInfo systemConstituentInfo) {
            this.extensionFirmware = systemConstituentInfo;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public String getDeviceId() {
            return this.deviceId;
        }

        public void setDeviceId(String str) {
            this.deviceId = str;
        }

        public String getDeviceModel() {
            return this.deviceModel;
        }

        public void setDeviceModel(String str) {
            this.deviceModel = str;
        }

        public String getFileName() {
            return this.fileName;
        }

        public void setFileName(String str) {
            this.fileName = str;
        }

        public String getFileUrl() {
            return this.fileUrl;
        }

        public void setFileUrl(String str) {
            this.fileUrl = str;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public String getMd5() {
            return this.md5;
        }

        public void setMd5(String str) {
            this.md5 = str;
        }

        public FlashInfo getFlashInfo() {
            return this.otaFont;
        }

        public void setFlashInfo(FlashInfo flashInfo) {
            this.otaFont = flashInfo;
        }

        public String getSha256() {
            return this.sha256;
        }

        public void setSha256(String str) {
            this.sha256 = str;
        }

        public String getSid() {
            return this.sid;
        }

        public void setSid(String str) {
            this.sid = str;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public String getVersion() {
            return this.version;
        }

        public void setVersion(String str) {
            this.version = str;
        }

        public List<LanguageInfo> getLanguageInfoList() {
            return this.otaLangs;
        }

        public void setLanguageInfoList(List<LanguageInfo> list) {
            this.otaLangs = list;
        }

        public String toString() {
            return "OtaInfo{description='" + this.description + "', deviceId='" + this.deviceId + "', deviceModel='" + this.deviceModel + "', fileName='" + this.fileName + "', fileUrl='" + this.fileUrl + "', id=" + this.id + ", md5='" + this.md5 + "', otaFont=" + this.otaFont + ", sha256='" + this.sha256 + "', sid='" + this.sid + "', type=" + this.type + ", version='" + this.version + "', otaLangs=" + this.otaLangs + '}';
        }

        public static class FlashInfo implements Serializable {
            private String fileName;
            private String fileUrl;
            private String fontVersion;
            private boolean forced;
            private int id;
            private String otaVersion;
            private int type;

            public String getFileName() {
                return this.fileName;
            }

            public void setFileName(String str) {
                this.fileName = str;
            }

            public String getFileUrl() {
                return this.fileUrl;
            }

            public void setFileUrl(String str) {
                this.fileUrl = str;
            }

            public String getFontVersion() {
                return this.fontVersion;
            }

            public void setFontVersion(String str) {
                this.fontVersion = str;
            }

            public boolean isForced() {
                return this.forced;
            }

            public void setForced(boolean z) {
                this.forced = z;
            }

            public int getId() {
                return this.id;
            }

            public void setId(int i) {
                this.id = i;
            }

            public String getOtaVersion() {
                return this.otaVersion;
            }

            public void setOtaVersion(String str) {
                this.otaVersion = str;
            }

            public int getType() {
                return this.type;
            }

            public void setType(int i) {
                this.type = i;
            }

            public String toString() {
                return "FlashInfo{fileName='" + this.fileName + "', fileUrl='" + this.fileUrl + "', fontVersion='" + this.fontVersion + "', forced=" + this.forced + ", id=" + this.id + ", otaVersion='" + this.otaVersion + "', type=" + this.type + '}';
            }
        }

        public static class LanguageInfo implements Serializable {
            private String code;
            private int codeId;
            private String fileName;
            private String fileUrl;
            private int id;
            private int langVersion;
            private String name;
            private String otaVersion;

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

            public String getFileName() {
                return this.fileName;
            }

            public void setFileName(String str) {
                this.fileName = str;
            }

            public String getFileUrl() {
                return this.fileUrl;
            }

            public void setFileUrl(String str) {
                this.fileUrl = str;
            }

            public int getId() {
                return this.id;
            }

            public void setId(int i) {
                this.id = i;
            }

            public int getLangVersion() {
                return this.langVersion;
            }

            public void setLangVersion(int i) {
                this.langVersion = i;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public String getOtaVersion() {
                return this.otaVersion;
            }

            public void setOtaVersion(String str) {
                this.otaVersion = str;
            }

            public String toString() {
                return "LanguageInfo{code='" + this.code + "', codeId=" + this.codeId + ", fileName='" + this.fileName + "', fileUrl='" + this.fileUrl + "', id=" + this.id + ", langVersion=" + this.langVersion + ", name='" + this.name + "', otaVersion='" + this.otaVersion + "'}";
            }
        }

        public static class SystemConstituentInfo implements Serializable {
            private String fileName;
            private String fileUrl;
            private String firmwareVersion;
            private int id;
            private String otaVersion;

            public int getId() {
                return this.id;
            }

            public void setId(int i) {
                this.id = i;
            }

            public String getOtaVersion() {
                return this.otaVersion;
            }

            public void setOtaVersion(String str) {
                this.otaVersion = str;
            }

            public String getFirmwareVersion() {
                return this.firmwareVersion;
            }

            public void setFirmwareVersion(String str) {
                this.firmwareVersion = str;
            }

            public String getFileUrl() {
                return this.fileUrl;
            }

            public void setFileUrl(String str) {
                this.fileUrl = str;
            }

            public String getFileName() {
                return this.fileName;
            }

            public void setFileName(String str) {
                this.fileName = str;
            }
        }
    }
}