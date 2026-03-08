package com.ido.ble.protocol.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class V3MessageNotice implements Serializable {
    public static final int TYPE_ALARM = 8206;
    public static final int TYPE_AMAZON = 8236;
    public static final int TYPE_CALENDAR = 8204;
    public static final int TYPE_CALL = 4096;
    public static final int TYPE_CHATWORK = 8224;
    public static final int TYPE_DAILYHUNT = 8232;
    public static final int TYPE_EMAIL = 8194;
    public static final int TYPE_FACEBOOK = 8198;
    public static final int TYPE_FLIPKART = 8237;
    public static final int TYPE_GMAIL = 8212;
    public static final int TYPE_GPAP = 8251;
    public static final int TYPE_GPAY = 8240;
    public static final int TYPE_HOTSTAR = 8233;
    public static final int TYPE_INSHORTS = 8234;
    public static final int TYPE_INSTAGRAM = 8202;
    public static final int TYPE_JIOTV = 8245;
    public static final int TYPE_KAKAO_TALK = 8211;
    public static final int TYPE_KEEP = 8246;
    public static final int TYPE_LINE = 8209;
    public static final int TYPE_LINKEDIN = 8203;
    public static final int TYPE_MAIL_YAHOO = 8226;
    public static final int TYPE_MAKEMYTRIP = 8244;
    public static final int TYPE_MATTER = 8255;
    public static final int TYPE_MESSENGER = 8201;
    public static final int TYPE_MICROSOFT = 8247;
    public static final int TYPE_MISSED_CALL = 8250;
    private static final int TYPE_MSG_BASE = 8192;
    public static final int TYPE_NETFLIX = 8239;
    public static final int TYPE_OLA = 8254;
    public static final int TYPE_OTHER = 8216;
    public static final int TYPE_OUTLOOK = 8213;
    public static final int TYPE_PAYTM = 8235;
    public static final int TYPE_PHONPE = 8241;
    public static final int TYPE_PINTEREST_YAHOO = 8229;
    public static final int TYPE_PRIME = 8238;
    public static final int TYPE_QQ = 8196;
    public static final int TYPE_REDBUS = 8231;
    public static final int TYPE_SKYPE = 8205;
    public static final int TYPE_SLACK = 8225;
    public static final int TYPE_SMS = 8193;
    public static final int TYPE_SNAPCHAT = 8214;
    public static final int TYPE_SWIGGY = 8242;
    public static final int TYPE_TELEGRAM = 8215;
    public static final int TYPE_TIKTOK = 8230;
    public static final int TYPE_TUMBLR = 8227;
    public static final int TYPE_TWITTER = 8199;
    public static final int TYPE_UBER = 8253;
    public static final int TYPE_VIBER = 8210;
    public static final int TYPE_VKONTAKTE = 8208;
    public static final int TYPE_WEIBO = 8197;
    public static final int TYPE_WHATSAPP = 8200;
    public static final int TYPE_WHATSAPP_BUSINESS = 8248;
    public static final int TYPE_WX = 8195;
    public static final int TYPE_YOUTUBE = 8228;
    public static final int TYPE_YT_MUSIC = 8252;
    public static final int TYPE_ZOMATO = 8243;
    public String contact;
    public String dataText;
    public int evtType;
    public int msgID;
    public String phoneNumber;
    public boolean supportAnswering;
    public boolean supportHangUp;
    public boolean supportMute;

    public String toString() {
        return "V3MessageNotice{evtType=" + this.evtType + ", supportAnswering=" + this.supportAnswering + ", supportMute=" + this.supportMute + ", supportHangUp=" + this.supportHangUp + ", contact='" + this.contact + "', phoneNumber='" + this.phoneNumber + "', dataText='" + this.dataText + "', msgID=" + this.msgID + '}';
    }
}