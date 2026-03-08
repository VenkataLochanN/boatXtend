package com.ido.life.net;

import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.constants.Constants;
import com.ido.life.enums.ServerEnum;
import com.ido.life.util.SPUtils;

/* JADX INFO: loaded from: classes3.dex */
public class BaseUrl {
    public static final String APP_HELP = "app_help";
    public static final String BASE_URL_BACKGROUND_H5 = "https://backstage.idoocloud.com/boatwave/";
    public static final String BASE_URL_H5 = "https://life-content.idoocloud.com/page/";
    public static final String DEFAULT_HOST = "http://cloud.user.veryfitplus.com/";
    public static final String FAQ_URL = "http://cloud.user.veryfitplus.com:90/faq/home/%s/%s";
    public static final String HELP_EXPLAIN = "help_explain";
    public static String HOST_APP_LOG = null;
    public static String HOST_DEVICE = null;
    public static final String HOST_GOOGLE_MAP = "http://maps.google.cn/maps/api/";
    public static String HOST_HEALTH = null;
    public static final String HOST_LOGSER = "http://cloud.console.veryfitplus.com:90/";
    public static final String HOST_STRAVA = "https://www.strava.com";
    public static final String HOST_USER = "http://cloud.user.veryfitplus.com:90/";
    public static String HOST_USER_THIRD = null;
    public static final String LINK_HELP = "link_help";
    public static final int PORT = 90;
    public static final String PRIVACY_POLICY = "privacy_policy";
    public static final String SPORT_NAME_EXPLAIN = "https://life-content.idoocloud.com/page/help_explain/%s/boatydmc.html";
    public static final String SPORT_TRAINING = "https://life-content.idoocloud.com/page/h5/Training_%s.html?age=%s&gender=%s&code=%s&status=0";
    public static final String SPORT_TYPE_EXPLAIN = "https://life-content.idoocloud.com/page/help_explain/%s/boatydfs.html";
    public static final String URL_APP_HELP = "https://life-content.idoocloud.com/page/app_help/%s/%s?status=0";
    public static String URL_DIAL_DETAIL = null;
    public static String URL_DIAL_MARKET = null;
    public static final String URL_FITNESS = "https://h5.idoocloud.com/boatwave/Tricyclic/index_%s.html";
    public static final String URL_HELP_EXPLAIN = "http://cloud.user.veryfitplus.com:90/userapi/faq/helper?language=%s";
    public static final String URL_LINK_ALEXA_APP = "https://play.google.com/store/apps/details?id=com.amazon.dee.app&hl=/%s&gl=US";
    public static final String URL_LINK_ALEXA_HELP = "https://life-content.idoocloud.com/page/help_explain/%s/bwBlack1.html";
    public static final String URL_LINK_ALEXA_HELP2 = "https://life-content.idoocloud.com/page/help_explain/%s/bwBlack2.html";
    public static final String URL_LINK_BACKGROUD_HELP = "https://backstage.idoocloud.com/boatwave/%s/index.html?status=0";
    public static final String URL_LINK_HELP = "https://life-content.idoocloud.com/page/link_help/%s/%s?status=0";
    public static final String URL_NAME = "url_name";
    public static final String URL_NAME_APP_LOG = "url_name:app_log";
    public static final String URL_NAME_DEVICE = "device";
    public static final String URL_NAME_DEVICE_HEADER = "url_name:device";
    public static final String URL_NAME_GOOGLE_MAP = "url_name:googleMap";
    public static final String URL_NAME_HEALTH = "health";
    public static final String URL_NAME_HEALTH_HEADER = "url_name:health";
    public static final String URL_NAME_LOGSER = "logser";
    public static final String URL_NAME_LOGSER_HEADER = "url_name:logser";
    public static final String URL_NAME_STRAVA_HEADER = "url_name:strava";
    public static final String URL_NAME_USER = "user";
    public static final String URL_NAME_USER_HEADER = "url_name:user";
    public static final String URL_PRIVACY_POLICY = "https://life-content.idoocloud.com/page/privacy_policy/%s/%s.html?status=0";
    public static final String URL_USER_AGREEMENT = "https://life-content.idoocloud.com/page/user_agreement/%s/%s.html?status=0";
    public static final String USER_AGREEMENT = "user_agreement";

    static {
        switchServer();
    }

    public static void switchServer() {
        ServerEnum serverEnumByCode = ServerEnum.getServerEnumByCode(((Integer) SPUtils.get(Constants.SERVER_CODE, Integer.valueOf(ServerEnum.Europe.Code))).intValue());
        if (serverEnumByCode == null) {
            serverEnumByCode = ServerEnum.Europe;
        }
        HOST_USER_THIRD = serverEnumByCode.UserUrl;
        HOST_DEVICE = serverEnumByCode.DeviceUrl;
        HOST_HEALTH = serverEnumByCode.HealthUrl;
        HOST_APP_LOG = serverEnumByCode.AppLogUrl;
        URL_DIAL_MARKET = HOST_DEVICE + "device/face/home/%1$s/%2$s";
        URL_DIAL_DETAIL = HOST_DEVICE + "device/face/item/%1$d/%2$s";
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getServerLogPath(), "服务器切换为:HOST_USER_THIRD=" + HOST_USER_THIRD + ",HOST_DEVICE=" + HOST_DEVICE + ",HOST_HEALTH=" + HOST_HEALTH + ",HOST_APP_LOG=" + HOST_APP_LOG + "URL_DIAL_MARKET=" + URL_DIAL_MARKET + ",URL_DIAL_DETAIL=" + URL_DIAL_DETAIL);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getServerLogPath(), "服务器切换为:HOST_USER_THIRD=" + HOST_USER_THIRD + ",HOST_DEVICE=" + HOST_DEVICE + ",HOST_HEALTH=" + HOST_HEALTH + ",HOST_APP_LOG=" + HOST_APP_LOG + "URL_DIAL_MARKET=" + URL_DIAL_MARKET + ",URL_DIAL_DETAIL=" + URL_DIAL_DETAIL);
    }
}