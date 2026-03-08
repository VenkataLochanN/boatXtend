package com.ido.life.data.api.entity;

import com.ido.common.net.BaseEntity;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class TopDialPlateEntity extends BaseEntity {
    private List<DialPlate> result;

    public List<DialPlate> getResult() {
        return this.result;
    }

    public void setResult(List<DialPlate> list) {
        this.result = list;
    }

    public static class DialPlate implements Serializable {
        private String customFaceType;
        private String description;
        private String faceType;
        private int id;
        private String imageUrl;
        public boolean isCurrentDial;
        public boolean isInstalledDial;
        private String linkUrl;
        private String name;
        private String otaFaceName;
        private String sid;

        public String getCustomFaceType() {
            return this.customFaceType;
        }

        public void setCustomFaceType(String str) {
            this.customFaceType = str;
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

        public String getImageUrl() {
            return this.imageUrl;
        }

        public void setImageUrl(String str) {
            this.imageUrl = str;
        }

        public String getOtaFaceName() {
            return this.otaFaceName;
        }

        public void setOtaFaceName(String str) {
            this.otaFaceName = str;
        }

        public String getSid() {
            return this.sid;
        }

        public void setSid(String str) {
            this.sid = str;
        }

        public String getFaceType() {
            return this.faceType;
        }

        public void setFaceType(String str) {
            this.faceType = str;
        }

        public String toString() {
            return "DialPlate{id=" + this.id + ", name='" + this.name + "', description='" + this.description + "', linkUrl='" + this.linkUrl + "', imageUrl='" + this.imageUrl + "', otaFaceName='" + this.otaFaceName + "', faceType='" + this.faceType + "', isCurrentDial=" + this.isCurrentDial + ", isInstalledDial=" + this.isInstalledDial + ", customFaceType='" + this.customFaceType + "', sid='" + this.sid + "'}";
        }
    }
}