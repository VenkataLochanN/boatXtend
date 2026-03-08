package com.ido.life.data.api.entity;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import com.ido.common.net.BaseEntity;

/* JADX INFO: loaded from: classes2.dex */
public class DialState extends BaseEntity {
    private State result;

    public State getResult() {
        return this.result;
    }

    public void setResult(State state) {
        this.result = state;
    }

    public static class State {
        private int deviceId;
        private int faceId;

        @SerializedName(NotificationCompat.CATEGORY_STATUS)
        private int statusX;

        public int getFaceId() {
            return this.faceId;
        }

        public void setFaceId(int i) {
            this.faceId = i;
        }

        public int getDeviceId() {
            return this.deviceId;
        }

        public void setDeviceId(int i) {
            this.deviceId = i;
        }

        public int getStatusX() {
            return this.statusX;
        }

        public void setStatusX(int i) {
            this.statusX = i;
        }

        public String toString() {
            return "State{faceId=" + this.faceId + ", deviceId=" + this.deviceId + ", statusX=" + this.statusX + '}';
        }
    }
}