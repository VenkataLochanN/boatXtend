package com.ido.life.database.model;

import com.ido.common.net.BaseEntity;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class WeatherEntity extends BaseEntity implements Serializable {
    public ServerWeather result;

    public ServerWeather getResult() {
        return this.result;
    }

    public void setResult(ServerWeather serverWeather) {
        this.result = serverWeather;
    }

    public String toString() {
        return "WeatherEntity{result=" + this.result + '}';
    }

    public static class ServerWeather implements Serializable {
        private List<FutureWeatherInfo> futureWeatherInfo;
        private List<Hour48WeatherInfos> hour48WeatherInfos;
        private int humidity;
        private int maxTemperature;
        private int minTemperature;
        private int precipChance;
        private String sunriseTimeLocal;
        private String sunsetTimeLocal;
        private int temperature;
        private int type;
        private String uvDescription;
        private int uvIndex;
        private WeatherQualityInfo weatherQualityInfo;
        private int windSpeed;

        public WeatherQualityInfo getWeatherQualityInfo() {
            return this.weatherQualityInfo;
        }

        public void setWeatherQualityInfo(WeatherQualityInfo weatherQualityInfo) {
            this.weatherQualityInfo = weatherQualityInfo;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public int getTemperature() {
            return this.temperature;
        }

        public void setTemperature(int i) {
            this.temperature = i;
        }

        public int getMaxTemperature() {
            return this.maxTemperature;
        }

        public void setMaxTemperature(int i) {
            this.maxTemperature = i;
        }

        public int getMinTemperature() {
            return this.minTemperature;
        }

        public void setMinTemperature(int i) {
            this.minTemperature = i;
        }

        public int getHumidity() {
            return this.humidity;
        }

        public void setHumidity(int i) {
            this.humidity = i;
        }

        public String getSunriseTimeLocal() {
            return this.sunriseTimeLocal;
        }

        public void setSunriseTimeLocal(String str) {
            this.sunriseTimeLocal = str;
        }

        public String getSunsetTimeLocal() {
            return this.sunsetTimeLocal;
        }

        public void setSunsetTimeLocal(String str) {
            this.sunsetTimeLocal = str;
        }

        public String getUvDescription() {
            return this.uvDescription;
        }

        public void setUvDescription(String str) {
            this.uvDescription = str;
        }

        public int getUvIndex() {
            return this.uvIndex;
        }

        public void setUvIndex(int i) {
            this.uvIndex = i;
        }

        public int getWindSpeed() {
            return this.windSpeed;
        }

        public void setWindSpeed(int i) {
            this.windSpeed = i;
        }

        public int getPrecipChance() {
            return this.precipChance;
        }

        public void setPrecipChance(int i) {
            this.precipChance = i;
        }

        public List<FutureWeatherInfo> getFutureWeatherInfo() {
            return this.futureWeatherInfo;
        }

        public void setFutureWeatherInfo(List<FutureWeatherInfo> list) {
            this.futureWeatherInfo = list;
        }

        public List<Hour48WeatherInfos> getHour48WeatherInfos() {
            return this.hour48WeatherInfos;
        }

        public void setHour48WeatherInfos(List<Hour48WeatherInfos> list) {
            this.hour48WeatherInfos = list;
        }

        public String toString() {
            return "ServerWeather{type=" + this.type + ", temperature=" + this.temperature + ", maxTemperature=" + this.maxTemperature + ", minTemperature=" + this.minTemperature + ", humidity=" + this.humidity + ", sunriseTimeLocal='" + this.sunriseTimeLocal + "', sunsetTimeLocal='" + this.sunsetTimeLocal + "', uvDescription='" + this.uvDescription + "', uvIndex=" + this.uvIndex + ", windSpeed=" + this.windSpeed + ", precipChance=" + this.precipChance + ", futureWeatherInfo=" + this.futureWeatherInfo + ", hour48WeatherInfos=" + this.hour48WeatherInfos + ", weatherQualityInfo=" + this.weatherQualityInfo.toString() + '}';
        }

        public static class FutureWeatherInfo implements Serializable {
            private int maxTemperature;
            private int minTemperature;
            private String sunriseTimeLocal;
            private String sunsetTimeLocal;
            private int type;
            private String uvDescription;
            private int uvIndex;

            public int getType() {
                return this.type;
            }

            public void setType(int i) {
                this.type = i;
            }

            public int getMaxTemperature() {
                return this.maxTemperature;
            }

            public void setMaxTemperature(int i) {
                this.maxTemperature = i;
            }

            public int getMinTemperature() {
                return this.minTemperature;
            }

            public void setMinTemperature(int i) {
                this.minTemperature = i;
            }

            public String getSunriseTimeLocal() {
                return this.sunriseTimeLocal;
            }

            public void setSunriseTimeLocal(String str) {
                this.sunriseTimeLocal = str;
            }

            public String getSunsetTimeLocal() {
                return this.sunsetTimeLocal;
            }

            public void setSunsetTimeLocal(String str) {
                this.sunsetTimeLocal = str;
            }

            public String getUvDescription() {
                return this.uvDescription;
            }

            public void setUvDescription(String str) {
                this.uvDescription = str;
            }

            public int getUvIndex() {
                return this.uvIndex;
            }

            public void setUvIndex(int i) {
                this.uvIndex = i;
            }

            public String toString() {
                return "FutureWeatherInfo{type=" + this.type + ", maxTemperature=" + this.maxTemperature + ", minTemperature=" + this.minTemperature + ", sunriseTimeLocal=" + this.sunriseTimeLocal + ", sunsetTimeLocal=" + this.sunsetTimeLocal + ", uvDescription=" + this.uvDescription + ", uvIndex=" + this.uvIndex + '}';
            }
        }

        public static class WeatherQualityInfo implements Serializable {
            private String airQualityCategory;
            private String airQualityIndex;
            private String code;
            private String color;

            public String getAirQualityCategory() {
                return this.airQualityCategory;
            }

            public void setAirQualityCategory(String str) {
                this.airQualityCategory = str;
            }

            public String getAirQualityIndex() {
                return this.airQualityIndex;
            }

            public void setAirQualityIndex(String str) {
                this.airQualityIndex = str;
            }

            public String getColor() {
                return this.color;
            }

            public void setColor(String str) {
                this.color = str;
            }

            public String getCode() {
                return this.code;
            }

            public void setCode(String str) {
                this.code = str;
            }

            public String toString() {
                return "WeatherQualityInfo{airQualityCategory='" + this.airQualityCategory + "', airQualityIndex='" + this.airQualityIndex + "', color='" + this.color + "', code='" + this.code + "'}";
            }
        }

        public static class Hour48WeatherInfos implements Serializable {
            private String hour;
            private int precipChance;
            private int temperature;
            private int type;

            public String getHour() {
                return this.hour;
            }

            public void setHour(String str) {
                this.hour = str;
            }

            public int getType() {
                return this.type;
            }

            public void setType(int i) {
                this.type = i;
            }

            public int getTemperature() {
                return this.temperature;
            }

            public void setTemperature(int i) {
                this.temperature = i;
            }

            public int getPrecipChance() {
                return this.precipChance;
            }

            public void setPrecipChance(int i) {
                this.precipChance = i;
            }
        }
    }
}