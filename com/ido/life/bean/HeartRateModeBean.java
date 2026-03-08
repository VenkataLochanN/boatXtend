package com.ido.life.bean;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class HeartRateModeBean implements Serializable {
    private int highHeartMode;
    private int highHeartValue;
    private int lowHeartMode;
    private int lowHeartValue;
    private int notifyFlag;
    private List<HeartRateMode> rateModes;
    private int startHour = 9;
    private int startMinute = 0;
    private int endHour = 18;
    private int endMinute = 0;

    public int getStartHour() {
        return this.startHour;
    }

    public void setStartHour(int i) {
        this.startHour = i;
    }

    public int getStartMinute() {
        return this.startMinute;
    }

    public void setStartMinute(int i) {
        this.startMinute = i;
    }

    public int getEndHour() {
        return this.endHour;
    }

    public void setEndHour(int i) {
        this.endHour = i;
    }

    public int getEndMinute() {
        return this.endMinute;
    }

    public void setEndMinute(int i) {
        this.endMinute = i;
    }

    public int getNotifyFlag() {
        return this.notifyFlag;
    }

    public void setNotifyFlag(int i) {
        this.notifyFlag = i;
    }

    public int getHighHeartMode() {
        return this.highHeartMode;
    }

    public void setHighHeartMode(int i) {
        this.highHeartMode = i;
    }

    public int getLowHeartMode() {
        return this.lowHeartMode;
    }

    public void setLowHeartMode(int i) {
        this.lowHeartMode = i;
    }

    public int getHighHeartValue() {
        return this.highHeartValue;
    }

    public void setHighHeartValue(int i) {
        this.highHeartValue = i;
    }

    public int getLowHeartValue() {
        return this.lowHeartValue;
    }

    public void setLowHeartValue(int i) {
        this.lowHeartValue = i;
    }

    public List<HeartRateMode> getRateModes() {
        return this.rateModes;
    }

    public void setRateModes(List<HeartRateMode> list) {
        this.rateModes = list;
    }

    public String toString() {
        return "HeartRateModeBean{startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", rateModes=" + this.rateModes + ", notifyFlag=" + this.notifyFlag + ", highHeartMode=" + this.highHeartMode + ", lowHeartMode=" + this.lowHeartMode + ", highHeartValue=" + this.highHeartValue + ", lowHeartValue=" + this.lowHeartValue + '}';
    }
}