package com.ido.life.bean;

import com.ido.ble.protocol.model.AlarmV3;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmBean extends AlarmV3 {
    public static final int TYPE_ALEXA = 1;
    public static final int TYPE_APP = 0;
    public int alarmType;
    public int day;
    public int month;
    public int year;

    public boolean isAlexa() {
        return this.alarmType == 1;
    }

    @Override // com.ido.ble.protocol.model.AlarmV3
    public String toString() {
        return "AlarmBean{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", type=" + this.type + ", alarm_id=" + this.alarm_id + ", status=" + this.status + ", alarmType=" + this.alarmType + ", type=" + this.type + ", hour=" + this.hour + ", minute=" + this.minute + ", tsnooze_duration=" + this.tsnooze_duration + ", delay_min=" + this.delay_min + ", name='" + this.name + "', shock_on_off=" + this.shock_on_off + ", repeat_times=" + this.repeat_times + '}';
    }
}