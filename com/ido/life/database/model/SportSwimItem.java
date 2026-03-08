package com.ido.life.database.model;

/* JADX INFO: loaded from: classes2.dex */
public class SportSwimItem {
    public int differenceTime;
    public int duration;
    public int frequency;
    public int speed;
    public int stopTime;
    public int strokesNumber;
    public int swolf;

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public int getStrokesNumber() {
        return this.strokesNumber;
    }

    public void setStrokesNumber(int i) {
        this.strokesNumber = i;
    }

    public int getSwolf() {
        return this.swolf;
    }

    public void setSwolf(int i) {
        this.swolf = i;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int i) {
        this.speed = i;
    }

    public int getDifferenceTime() {
        return this.differenceTime;
    }

    public void setDifferenceTime(int i) {
        this.differenceTime = i;
    }

    public int getStopTime() {
        return this.stopTime;
    }

    public void setStopTime(int i) {
        this.stopTime = i;
    }

    public String toString() {
        return "SportSwimItem{duration=" + this.duration + ", strokesNumber=" + this.strokesNumber + ", swolf=" + this.swolf + ", frequency=" + this.frequency + ", speed=" + this.speed + ", stopTime=" + this.stopTime + ", differenceTime=" + this.differenceTime + '}';
    }
}