package com.ido.alexa.bean;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaTimerItem {
    public static final int FLAG_ADD = 0;
    public static final int FLAG_DEL = 1;
    public static final int FLAG_DEL_ALL = 255;
    private long delay_time;
    private int index;
    private int operate_timer_flag;

    public long getDelay_time() {
        return this.delay_time;
    }

    public void setDelay_time(long j) {
        this.delay_time = j;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public int getOperate_timer_flag() {
        return this.operate_timer_flag;
    }

    public void setOperate_timer_flag(int i) {
        this.operate_timer_flag = i;
    }

    public String toString() {
        return "AlexaTimerItem{delay_time=" + this.delay_time + ", index=" + this.index + ", operate_timer_flag=" + this.operate_timer_flag + '}';
    }
}