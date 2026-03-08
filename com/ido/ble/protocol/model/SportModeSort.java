package com.ido.ble.protocol.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class SportModeSort {
    public SportModeSortItem[] items;

    public static class SportModeSortItem {
        public int index;
        public int type;

        public String toString() {
            return "SportModeSortItem{index=" + this.index + ", type=" + this.type + '}';
        }
    }

    public String toString() {
        return "SportModeSort{items=" + Arrays.toString(this.items) + '}';
    }
}