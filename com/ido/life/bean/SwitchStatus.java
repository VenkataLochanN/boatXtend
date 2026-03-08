package com.ido.life.bean;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class SwitchStatus implements Serializable, Cloneable {
    public boolean callDelayReminderSwitched;
    public boolean callReminderSwitched;
    public boolean goalReminderSwitched;
    public boolean musicNameSwitched;
    public NotificationSwitch notificationSwitch = new NotificationSwitch();

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString() {
        return "SwitchStatus{, callReminderSwitched=" + this.callReminderSwitched + ", callDelayReminderSwitched=" + this.callDelayReminderSwitched + ", goalReminderSwitched=" + this.goalReminderSwitched + ", notificationSwitch=" + this.notificationSwitch + '}';
    }

    public static class NotificationSwitch implements Serializable, Cloneable {
        public boolean masterSwitched = true;
        public boolean calendarSwitched = true;
        public boolean mailSwitched = true;
        public boolean mailYahooSwitched = true;
        public boolean mailOutlookSwitched = true;
        public boolean googleMeetSwitched = true;
        public boolean smsSwitched = true;
        public boolean missedCall = true;
        public boolean wechatSwitched = true;
        public boolean matterSwitched = true;
        public boolean qqSwitched = true;
        public boolean facebookSwitched = true;
        public boolean twitterSwitched = true;
        public boolean instagramSwitched = true;
        public boolean whatsAppSwitched = true;
        public boolean whatsAppBusinessSwitched = true;
        public boolean messengerSwitched = true;
        public boolean linkedinSwitched = true;
        public boolean lineSwitched = true;
        public boolean viberSwitched = true;
        public boolean skypeSwitched = true;
        public boolean kakaoTalkSwitched = true;
        public boolean vkSwitched = true;
        public boolean tumblrSwitched = true;
        public boolean snapchatSwitched = true;
        public boolean youTubeSwitched = true;
        public boolean pinterestSwitched = true;
        public boolean telegramSwitched = true;
        public boolean tikTokSwitched = true;

        public String toString() {
            return "NotificationSwitch{masterSwitched=" + this.masterSwitched + ", calendarSwitched=" + this.calendarSwitched + ", mailSwitched=" + this.mailSwitched + ", mailYahooSwitched=" + this.mailYahooSwitched + ", mailOutlookSwitched=" + this.mailOutlookSwitched + ", googleMeetSwitched=" + this.googleMeetSwitched + ", smsSwitched=" + this.smsSwitched + ", missedCall=" + this.missedCall + ", wechatSwitched=" + this.wechatSwitched + ", matterSwitched=" + this.matterSwitched + ", qqSwitched=" + this.qqSwitched + ", facebookSwitched=" + this.facebookSwitched + ", twitterSwitched=" + this.twitterSwitched + ", instagramSwitched=" + this.instagramSwitched + ", whatsAppSwitched=" + this.whatsAppSwitched + ", whatsAppBusinessSwitched=" + this.whatsAppBusinessSwitched + ", messengerSwitched=" + this.messengerSwitched + ", linkedinSwitched=" + this.linkedinSwitched + ", lineSwitched=" + this.lineSwitched + ", viberSwitched=" + this.viberSwitched + ", skypeSwitched=" + this.skypeSwitched + ", kakaoTalkSwitched=" + this.kakaoTalkSwitched + ", vkSwitched=" + this.vkSwitched + ", tumblrSwitched=" + this.tumblrSwitched + ", snapchatSwitched=" + this.snapchatSwitched + ", youTubeSwitched=" + this.youTubeSwitched + ", pinterestSwitched=" + this.pinterestSwitched + ", telegramSwitched=" + this.telegramSwitched + ", tikTokSwitched=" + this.tikTokSwitched + '}';
        }

        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}