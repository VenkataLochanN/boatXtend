package com.ido.life.data.api.entity;

import com.ido.common.net.BaseEntity;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialMarket extends BaseEntity implements Serializable {
    private List<DialType> result;

    public List<DialType> getResult() {
        return this.result;
    }

    public void setResult(List<DialType> list) {
        this.result = list;
    }

    public String toString() {
        return "DialMarket{result=" + this.result + '}';
    }

    public static class DialType implements Serializable {
        private int count;
        private List<Dial> faceList;
        private int id;
        private String language;
        private String name;
        private int type;

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public String getLanguage() {
            return this.language;
        }

        public void setLanguage(String str) {
            this.language = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public int getCount() {
            return this.count;
        }

        public void setCount(int i) {
            this.count = i;
        }

        public List<Dial> getFaceList() {
            return this.faceList;
        }

        public void setFaceList(List<Dial> list) {
            this.faceList = list;
        }

        public String toString() {
            return "DialType{id=" + this.id + ", language='" + this.language + "', name='" + this.name + "', count=" + this.count + ", faceList=" + this.faceList + '}';
        }

        public static class Dial implements Serializable {
            private String customFaceType;
            private String description;
            private int faceId;
            private String faceType;
            private String faceTypeName;
            private int id;
            private String imageUrl;
            private String index;
            public boolean isCurrentDial;
            public boolean isInstalledDial;
            private String name;
            private String otaFaceName;
            private String sid;

            public int getFaceId() {
                return this.faceId;
            }

            public void setFaceId(int i) {
                this.faceId = i;
            }

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

            public String getOtaFaceName() {
                return this.otaFaceName;
            }

            public void setOtaFaceName(String str) {
                this.otaFaceName = str;
            }

            public String getDescription() {
                return this.description;
            }

            public void setDescription(String str) {
                this.description = str;
            }

            public String getImageUrl() {
                return this.imageUrl;
            }

            public void setImageUrl(String str) {
                this.imageUrl = str;
            }

            public String getIndex() {
                return this.index;
            }

            public void setIndex(String str) {
                this.index = str;
            }

            public String getSid() {
                return this.sid;
            }

            public void setSid(String str) {
                this.sid = str;
            }

            public String toString() {
                return "Dial{id=" + this.id + ", name='" + this.name + "', faceTypeName='" + this.faceTypeName + "', faceType='" + this.faceType + "', faceId=" + this.faceId + ", otaFaceName='" + this.otaFaceName + "', description='" + this.description + "', imageUrl='" + this.imageUrl + "', index='" + this.index + "', sid='" + this.sid + "', customFaceType='" + this.customFaceType + "', isCurrentDial=" + this.isCurrentDial + ", isInstalledDial=" + this.isInstalledDial + '}';
            }
        }
    }
}