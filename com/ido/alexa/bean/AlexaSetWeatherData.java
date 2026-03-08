package com.ido.alexa.bean;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaSetWeatherData {
    private String cur_weather;
    private ArrayList<AlexaWeatherItem> future_weather;
    private int future_weather_len;
    private String location;
    private String today_min_max_weather;
    private String today_weather_state;
    private int version;

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public String getCur_weather() {
        return this.cur_weather;
    }

    public void setCur_weather(String str) {
        this.cur_weather = str;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public String getToday_weather_state() {
        return this.today_weather_state;
    }

    public void setToday_weather_state(String str) {
        this.today_weather_state = str;
    }

    public String getToday_min_max_weather() {
        return this.today_min_max_weather;
    }

    public void setToday_min_max_weather(String str) {
        this.today_min_max_weather = str;
    }

    public int getFuture_weather_len() {
        return this.future_weather_len;
    }

    public void setFuture_weather_len(int i) {
        this.future_weather_len = i;
    }

    public ArrayList<AlexaWeatherItem> getFuture_weather() {
        return this.future_weather;
    }

    public void setFuture_weather(ArrayList<AlexaWeatherItem> arrayList) {
        this.future_weather = arrayList;
    }

    public String toString() {
        return "AlexaSetWeatherData{version=" + this.version + ", cur_weather='" + this.cur_weather + "', location='" + this.location + "', today_weather_state='" + this.today_weather_state + "', today_min_max_weather='" + this.today_min_max_weather + "', future_weather_len=" + this.future_weather_len + ", future_weather=" + this.future_weather + '}';
    }
}