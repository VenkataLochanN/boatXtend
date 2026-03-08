package com.ido.ble.protocol.model;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class QuickReplyInfo {
    public List<QuickMsg> fast_items;
    public int num;
    public int version;

    public static class QuickMsg {
        public String msg_data;
        public int msg_id;
        public int on_off;

        public String toString() {
            return "QuickMsg{on_off=" + this.on_off + ", mesg_id=" + this.msg_id + ", msg_data='" + this.msg_data + "'}";
        }
    }

    public String toString() {
        return "QuickReplyInfo{version=" + this.version + ", num=" + this.num + ", fast_items=" + this.fast_items + '}';
    }
}