package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AllPhoneContacts implements Serializable {
    private static final long serialVersionUID = 1;
    public int contact_item_num;
    public int day;
    public int hour;
    public List<PhoneContactItem> items;
    public int minute;
    public int month;
    public int second;
    public int year;

    public static class PhoneContactItem {
        public String name;
        public String phone;

        public String toString() {
            return "PhoneContactItem{name='" + this.name + "', phone='" + this.phone + "'}";
        }
    }

    public static class Response {
        public String path;

        public String toString() {
            return "Response{path='" + this.path + "'}";
        }
    }

    public String toString() {
        return "AllPhoneContacts{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", items=" + this.items + ", contact_item_num=" + this.contact_item_num + '}';
    }
}