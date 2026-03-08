package com.ido.ble.protocol.model;

/* JADX INFO: loaded from: classes2.dex */
public class IconPara {

    public static class Get {
        public int evt_type;
        public int sport_type;
        public int type;

        public String toString() {
            return "Get{type=" + this.type + ", evt_type=" + this.evt_type + ", sport_type=" + this.sport_type + '}';
        }
    }

    public static class Response {
        public int block_size;
        public int err_code;
        public int evt_type;
        public int format;
        public int icon_height;
        public int icon_width;
        public int sport_type;
        public int type;

        public String toString() {
            return "Response{block_size=" + this.block_size + ", err_code=" + this.err_code + ", evt_type=" + this.evt_type + ", format=" + this.format + ", icon_height=" + this.icon_height + ", icon_width=" + this.icon_width + ", sport_type=" + this.sport_type + ", type=" + this.type + '}';
        }
    }
}