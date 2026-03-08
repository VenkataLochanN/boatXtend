package com.ido.life.data.api.entity;

import com.ido.common.net.BaseEntity;
import com.ido.life.data.api.entity.DialMarket;
import com.ido.life.data.api.entity.TopDialPlateEntity;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MyDialListEntity extends BaseEntity implements Serializable {
    public List<DialInfo> result;

    public List<DialInfo> getResult() {
        return this.result;
    }

    public void setResult(List<DialInfo> list) {
        this.result = list;
    }

    public String toString() {
        return "MyDialListEntity{result=" + this.result + '}';
    }

    public static class DialInfo implements Serializable {
        public static final int TYPE_CLOUD_DIAL = 1;
        public static final int TYPE_WALL_PAPER = 2;
        public boolean collected;
        private String customFaceType;
        private String description;
        private String downloadCount;
        private String downloadedCount;
        private boolean enabled;
        private int faceId;
        private String faceType;
        private String faceTypeName;
        public boolean hasNewVersion;
        private int id;
        private int imageResId;
        private String imageUrl;
        private String index;
        public boolean isCurrentDial;
        public boolean isInstalledDial;
        public boolean isLocal;
        private String linkUrl;
        private String name;
        private int nameResId;
        private String otaFaceName;
        private OtaFaceVersion otaFaceVersion;
        private String sid;
        private long size;
        public int type;

        public int getFaceId() {
            return this.faceId;
        }

        public void setFaceId(int i) {
            this.faceId = i;
        }

        public boolean isLocal() {
            return this.isLocal;
        }

        public void setLocal(boolean z) {
            this.isLocal = z;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean z) {
            this.enabled = z;
        }

        public boolean isCollected() {
            return this.collected;
        }

        public void setCollected(boolean z) {
            this.collected = z;
        }

        public String getCustomFaceType() {
            return this.customFaceType;
        }

        public void setCustomFaceType(String str) {
            this.customFaceType = str;
        }

        public DialInfo() {
        }

        public DialInfo(int i) {
            this.id = i;
        }

        public DialInfo(TopDialPlateEntity.DialPlate dialPlate) {
            this.name = dialPlate.getName();
            this.otaFaceName = dialPlate.getOtaFaceName();
            this.imageUrl = dialPlate.getImageUrl();
            this.id = dialPlate.getId();
            this.description = dialPlate.getDescription();
            this.linkUrl = dialPlate.getLinkUrl();
            this.isCurrentDial = dialPlate.isCurrentDial;
            this.isInstalledDial = dialPlate.isInstalledDial;
            this.sid = dialPlate.getSid();
            this.customFaceType = dialPlate.getCustomFaceType();
        }

        public DialInfo(DialMarket.DialType.Dial dial) {
            this.name = dial.getName();
            this.otaFaceName = dial.getOtaFaceName();
            this.imageUrl = dial.getImageUrl();
            this.id = dial.getId();
            this.description = dial.getDescription();
            this.isCurrentDial = dial.isCurrentDial;
            this.isInstalledDial = dial.isInstalledDial;
            this.sid = dial.getSid();
            this.customFaceType = dial.getCustomFaceType();
        }

        public DialInfo(String str, String str2) {
            this.otaFaceName = str;
            this.imageUrl = str2;
        }

        public DialInfo(String str, int i) {
            this.otaFaceName = str;
            this.imageResId = i;
        }

        public DialInfo(String str, int i, int i2, String str2) {
            this.otaFaceName = str;
            this.imageResId = i;
            this.nameResId = i2;
            this.description = str2;
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

        public String getOtaFaceName() {
            return this.otaFaceName;
        }

        public void setOtaFaceName(String str) {
            this.otaFaceName = str;
        }

        public String getFaceTypeName() {
            return this.faceTypeName;
        }

        public void setFaceTypeName(String str) {
            this.faceTypeName = str;
        }

        public String getFaceType() {
            return this.faceType;
        }

        public void setFaceType(String str) {
            this.faceType = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public String getLinkUrl() {
            return this.linkUrl;
        }

        public void setLinkUrl(String str) {
            this.linkUrl = str;
        }

        public long getSize() {
            return this.size;
        }

        public void setSize(long j) {
            this.size = j;
        }

        public String getImageUrl() {
            return this.imageUrl;
        }

        public void setImageUrl(String str) {
            this.imageUrl = str;
        }

        public String getDownloadCount() {
            return this.downloadCount;
        }

        public void setDownloadCount(String str) {
            this.downloadCount = str;
        }

        public String getDownloadedCount() {
            return this.downloadedCount;
        }

        public void setDownloadedCount(String str) {
            this.downloadedCount = str;
        }

        public String getIndex() {
            return this.index;
        }

        public void setIndex(String str) {
            this.index = str;
        }

        public boolean isCurrentDial() {
            return this.isCurrentDial;
        }

        public void setCurrentDial(boolean z) {
            this.isCurrentDial = z;
        }

        public boolean isInstalledDial() {
            return this.isInstalledDial;
        }

        public void setInstalledDial(boolean z) {
            this.isInstalledDial = z;
        }

        public int getImageResId() {
            return this.imageResId;
        }

        public void setImageResId(int i) {
            this.imageResId = i;
        }

        public String getSid() {
            return this.sid;
        }

        public void setSid(String str) {
            this.sid = str;
        }

        public int getNameResId() {
            return this.nameResId;
        }

        public void setNameResId(int i) {
            this.nameResId = i;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public OtaFaceVersion getOtaFaceVersion() {
            return this.otaFaceVersion;
        }

        public void setOtaFaceVersion(OtaFaceVersion otaFaceVersion) {
            this.otaFaceVersion = otaFaceVersion;
        }

        public String toString() {
            return "DialInfo{id=" + this.id + ", name='" + this.name + "', otaFaceName='" + this.otaFaceName + "', faceTypeName='" + this.faceTypeName + "', faceType='" + this.faceType + "', description='" + this.description + "', linkUrl='" + this.linkUrl + "', size=" + this.size + ", imageUrl='" + this.imageUrl + "', downloadCount='" + this.downloadCount + "', downloadedCount='" + this.downloadedCount + "', index='" + this.index + "', isCurrentDial=" + this.isCurrentDial + ", isInstalledDial=" + this.isInstalledDial + ", imageResId=" + this.imageResId + ", sid='" + this.sid + "', nameResId=" + this.nameResId + ", type=" + this.type + ", hasNewVersion=" + this.hasNewVersion + ", otaFaceVersion=" + this.otaFaceVersion + '}';
        }

        public static class OtaFaceVersion implements Serializable {
            private String appId;
            private String createdAt;
            private String createdBy;
            private boolean enabled;
            private int faceId;
            private int id;
            private String linkUrl;
            private String md5;
            private String orgId;
            private String otaFace;
            private String sha128;
            private String sha256;
            private String sha512;
            private int size;
            private String supportFaceVersion;
            private String type;
            private String updatedAt;
            private String updatedBy;
            private String version;

            public String getCreatedBy() {
                return this.createdBy;
            }

            public void setCreatedBy(String str) {
                this.createdBy = str;
            }

            public String getUpdatedBy() {
                return this.updatedBy;
            }

            public void setUpdatedBy(String str) {
                this.updatedBy = str;
            }

            public String getCreatedAt() {
                return this.createdAt;
            }

            public void setCreatedAt(String str) {
                this.createdAt = str;
            }

            public String getUpdatedAt() {
                return this.updatedAt;
            }

            public void setUpdatedAt(String str) {
                this.updatedAt = str;
            }

            public Object getAppId() {
                return this.appId;
            }

            public void setAppId(String str) {
                this.appId = str;
            }

            public Object getOrgId() {
                return this.orgId;
            }

            public void setOrgId(String str) {
                this.orgId = str;
            }

            public int getId() {
                return this.id;
            }

            public void setId(int i) {
                this.id = i;
            }

            public int getFaceId() {
                return this.faceId;
            }

            public void setFaceId(int i) {
                this.faceId = i;
            }

            public boolean isEnabled() {
                return this.enabled;
            }

            public void setEnabled(boolean z) {
                this.enabled = z;
            }

            public String getLinkUrl() {
                return this.linkUrl;
            }

            public void setLinkUrl(String str) {
                this.linkUrl = str;
            }

            public int getSize() {
                return this.size;
            }

            public void setSize(int i) {
                this.size = i;
            }

            public String getMd5() {
                return this.md5;
            }

            public void setMd5(String str) {
                this.md5 = str;
            }

            public String getSha256() {
                return this.sha256;
            }

            public void setSha256(String str) {
                this.sha256 = str;
            }

            public Object getSha512() {
                return this.sha512;
            }

            public void setSha512(String str) {
                this.sha512 = str;
            }

            public Object getSha128() {
                return this.sha128;
            }

            public void setSha128(String str) {
                this.sha128 = str;
            }

            public String getSupportFaceVersion() {
                return this.supportFaceVersion;
            }

            public void setSupportFaceVersion(String str) {
                this.supportFaceVersion = str;
            }

            public String getVersion() {
                return this.version;
            }

            public void setVersion(String str) {
                this.version = str;
            }

            public String getType() {
                return this.type;
            }

            public void setType(String str) {
                this.type = str;
            }

            public Object getOtaFace() {
                return this.otaFace;
            }

            public void setOtaFace(String str) {
                this.otaFace = str;
            }

            public String toString() {
                return "OtaFaceVersion{createdBy='" + this.createdBy + "', updatedBy='" + this.updatedBy + "', createdAt='" + this.createdAt + "', updatedAt='" + this.updatedAt + "', appId='" + this.appId + "', orgId='" + this.orgId + "', id=" + this.id + ", faceId=" + this.faceId + ", enabled=" + this.enabled + ", linkUrl='" + this.linkUrl + "', size=" + this.size + ", md5='" + this.md5 + "', sha256='" + this.sha256 + "', sha512='" + this.sha512 + "', sha128='" + this.sha128 + "', supportFaceVersion='" + this.supportFaceVersion + "', version='" + this.version + "', type='" + this.type + "', otaFace='" + this.otaFace + "'}";
            }
        }
    }
}