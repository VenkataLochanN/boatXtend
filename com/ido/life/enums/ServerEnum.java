package com.ido.life.enums;

import android.text.TextUtils;
import com.ido.life.net.BaseUrl;

/* JADX INFO: loaded from: classes2.dex */
public enum ServerEnum {
    Inner(0, "内部服务器", BaseUrl.HOST_USER, "http://cloud.device.veryfitplus.com:90/", "http://cloud.health.veryfitplus.com:90/", "http://cloud.log.veryfitplus.com:90/"),
    Ali(1, "阿里云服务器", "https://ali-user.idoocloud.com/", "https://ali-device.idoocloud.com/", "https://ali-health.idoocloud.com/", "https://ali-log.idoocloud.com/"),
    China(2, "中国服务器", "https://cn-user.idoocloud.com/", "https://cn-device.idoocloud.com/", "https://cn-health.idoocloud.com/", "https://cn-log.idoocloud.com/"),
    Europe(3, "欧洲服务器", "https://user.idoocloud.com/", "https://device.idoocloud.com/", "https://health.idoocloud.com/", "https://eu-log.idoocloud.com/"),
    USA(4, "美国服务器", "https://us.user.idoocloud.com/", "https://us.device.idoocloud.com/", "https://us.health.idoocloud.com/", "https://us-log.idoocloud.com/"),
    India(5, "印度服务器", "https://in-user.idoocloud.com/", "https://in-device.idoocloud.com/", "https://in-health.idoocloud.com/", "https://in-log.idoocloud.com/");

    public String AppLogUrl;
    public int Code;
    public String Desc;
    public String DeviceUrl;
    public String HealthUrl;
    public String UserUrl;

    ServerEnum(int i, String str, String str2, String str3, String str4, String str5) {
        this.Code = i;
        this.Desc = str;
        this.UserUrl = str2;
        this.DeviceUrl = str3;
        this.HealthUrl = str4;
        this.AppLogUrl = str5;
    }

    public static ServerEnum getServerEnumByCode(int i) {
        ServerEnum serverEnum = Inner;
        if (i == serverEnum.Code) {
            return serverEnum;
        }
        ServerEnum serverEnum2 = Ali;
        if (i == serverEnum2.Code) {
            return serverEnum2;
        }
        ServerEnum serverEnum3 = China;
        if (i == serverEnum3.Code) {
            return serverEnum3;
        }
        ServerEnum serverEnum4 = Europe;
        if (i == serverEnum4.Code) {
            return serverEnum4;
        }
        ServerEnum serverEnum5 = USA;
        if (i == serverEnum5.Code) {
            return serverEnum5;
        }
        ServerEnum serverEnum6 = India;
        return i == serverEnum6.Code ? serverEnum6 : serverEnum4;
    }

    public static ServerEnum getServerEnumByDesc(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String strTrim = str.trim();
        if (TextUtils.equals(strTrim, Inner.Desc)) {
            return Inner;
        }
        if (TextUtils.equals(strTrim, Ali.Desc)) {
            return Ali;
        }
        if (TextUtils.equals(strTrim, Europe.Desc)) {
            return Europe;
        }
        if (TextUtils.equals(strTrim, USA.Desc)) {
            return USA;
        }
        if (TextUtils.equals(strTrim, India.Desc)) {
            return India;
        }
        if (TextUtils.equals(strTrim, China.Desc)) {
            return China;
        }
        return null;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "ServerEnum{Code=" + this.Code + ", Desc='" + this.Desc + "', UserUrl='" + this.UserUrl + "', DeviceUrl='" + this.DeviceUrl + "', HealthUrl='" + this.HealthUrl + "', AppLogUrl='" + this.AppLogUrl + "'}";
    }
}