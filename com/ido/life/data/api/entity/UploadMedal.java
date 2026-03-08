package com.ido.life.data.api.entity;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class UploadMedal {
    private List<UploadMedalItem> datas;

    public UploadMedal(List<UploadMedalItem> list) {
        this.datas = list;
    }

    public List<UploadMedalItem> getDatas() {
        return this.datas;
    }

    public static class UploadMedalItem {
        private int medalId;
        private String time;

        public UploadMedalItem(int i, String str) {
            this.medalId = i;
            this.time = str;
        }

        public int getMedalId() {
            return this.medalId;
        }

        public String getTime() {
            return this.time;
        }
    }
}