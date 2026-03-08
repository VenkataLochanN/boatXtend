package com.ido.life.database.model.middleModel;

import com.ido.common.net.BaseEntity;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportUploadResult extends BaseEntity {
    public List<Result> result;

    public List<Result> getResult() {
        return this.result;
    }

    public void setResult(List<Result> list) {
        this.result = list;
    }

    public class Result {
        private String sid;
        private long timestamp;

        public Result() {
        }

        public String getSid() {
            return this.sid;
        }

        public void setSid(String str) {
            this.sid = str;
        }

        public long getTimestamp() {
            return this.timestamp;
        }

        public void setTimestamp(long j) {
            this.timestamp = j;
        }

        public String toString() {
            return "SportUploadResult{sid='" + this.sid + "', timestamp=" + this.timestamp + '}';
        }
    }
}