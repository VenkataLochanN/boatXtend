package com.ido.alexa.bean;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaWeatherItem {
    private String date;
    private String min_max_weather;
    private String weather_state;

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getWeather_state() {
        return this.weather_state;
    }

    public void setWeather_state(String str) {
        this.weather_state = str;
    }

    public String getMin_max_weather() {
        return this.min_max_weather;
    }

    public void setMin_max_weather(String str) {
        this.min_max_weather = str;
    }

    public String toString() {
        return "AlexaWeatherItem{date='" + this.date + "', weather_state='" + this.weather_state + "', min_max_weather='" + this.min_max_weather + "'}";
    }
}