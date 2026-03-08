package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationActivity_ViewBinding implements Unbinder {
    private NotificationActivity target;

    public NotificationActivity_ViewBinding(NotificationActivity notificationActivity) {
        this(notificationActivity, notificationActivity.getWindow().getDecorView());
    }

    public NotificationActivity_ViewBinding(NotificationActivity notificationActivity, View view) {
        this.target = notificationActivity;
        notificationActivity.mTvTip = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_tip, "field 'mTvTip'", RegularTextView.class);
        notificationActivity.mItemMasterSwitch = (CustomToggleButton) Utils.findRequiredViewAsType(view, R.id.allToggle, "field 'mItemMasterSwitch'", CustomToggleButton.class);
        notificationActivity.allNotificationTips = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.allNotificationID, "field 'allNotificationTips'", RegularTextView.class);
        notificationActivity.mScrollView = (NestedScrollView) Utils.findRequiredViewAsType(view, R.id.nsv_notification, "field 'mScrollView'", NestedScrollView.class);
        notificationActivity.mLayoutAppList = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_app_list, "field 'mLayoutAppList'", LinearLayout.class);
        notificationActivity.mItemCalendar = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_calendar, "field 'mItemCalendar'", CustomItemLabelView.class);
        notificationActivity.mItemMatter = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_matter, "field 'mItemMatter'", CustomItemLabelView.class);
        notificationActivity.mItemEmail = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_email, "field 'mItemEmail'", CustomItemLabelView.class);
        notificationActivity.mItemYahooEmail = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_yahoo_email, "field 'mItemYahooEmail'", CustomItemLabelView.class);
        notificationActivity.mItemOutlookEmail = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_outlook_email, "field 'mItemOutlookEmail'", CustomItemLabelView.class);
        notificationActivity.mItemGoogleMeet = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_google_meet, "field 'mItemGoogleMeet'", CustomItemLabelView.class);
        notificationActivity.mItemSms = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_sms, "field 'mItemSms'", CustomItemLabelView.class);
        notificationActivity.mItemMissedCall = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_missed_call, "field 'mItemMissedCall'", CustomItemLabelView.class);
        notificationActivity.mItemWechat = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_wechat, "field 'mItemWechat'", CustomItemLabelView.class);
        notificationActivity.mItemQq = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_qq, "field 'mItemQq'", CustomItemLabelView.class);
        notificationActivity.mItemFacebook = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_facebook, "field 'mItemFacebook'", CustomItemLabelView.class);
        notificationActivity.mItemTwitter = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_twitter, "field 'mItemTwitter'", CustomItemLabelView.class);
        notificationActivity.mItemInstagram = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_instagram, "field 'mItemInstagram'", CustomItemLabelView.class);
        notificationActivity.mItemWhatsApp = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_whats_app, "field 'mItemWhatsApp'", CustomItemLabelView.class);
        notificationActivity.mItemWhatsAppBusiness = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_whats_app_business, "field 'mItemWhatsAppBusiness'", CustomItemLabelView.class);
        notificationActivity.mItemMessenger = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_messenger, "field 'mItemMessenger'", CustomItemLabelView.class);
        notificationActivity.mItemLinkedin = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_linkedin, "field 'mItemLinkedin'", CustomItemLabelView.class);
        notificationActivity.mItemGmail = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_gmail, "field 'mItemGmail'", CustomItemLabelView.class);
        notificationActivity.mItemLine = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_line, "field 'mItemLine'", CustomItemLabelView.class);
        notificationActivity.mItemViber = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_viber, "field 'mItemViber'", CustomItemLabelView.class);
        notificationActivity.mItemSkype = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_skype, "field 'mItemSkype'", CustomItemLabelView.class);
        notificationActivity.mItemKakaoTalk = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_kakao_talk, "field 'mItemKakaoTalk'", CustomItemLabelView.class);
        notificationActivity.mItemVkontakte = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_vkontakte, "field 'mItemVkontakte'", CustomItemLabelView.class);
        notificationActivity.mItemTumblr = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_tumblr, "field 'mItemTumblr'", CustomItemLabelView.class);
        notificationActivity.mItemSnapchat = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_snapchat, "field 'mItemSnapchat'", CustomItemLabelView.class);
        notificationActivity.mItemYouTube = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_youTube, "field 'mItemYouTube'", CustomItemLabelView.class);
        notificationActivity.mItemPinterest = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_pinterest, "field 'mItemPinterest'", CustomItemLabelView.class);
        notificationActivity.mItemTelegram = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_telegram, "field 'mItemTelegram'", CustomItemLabelView.class);
        notificationActivity.mItemTikTok = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_tik_tok, "field 'mItemTikTok'", CustomItemLabelView.class);
        notificationActivity.redPointToggle = (CustomToggleButton) Utils.findRequiredViewAsType(view, R.id.redPointToggle, "field 'redPointToggle'", CustomToggleButton.class);
        notificationActivity.tvRedPointTips = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_red_point_tips, "field 'tvRedPointTips'", TextView.class);
        notificationActivity.layRedPoint = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_red_point, "field 'layRedPoint'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        NotificationActivity notificationActivity = this.target;
        if (notificationActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        notificationActivity.mTvTip = null;
        notificationActivity.mItemMasterSwitch = null;
        notificationActivity.allNotificationTips = null;
        notificationActivity.mScrollView = null;
        notificationActivity.mLayoutAppList = null;
        notificationActivity.mItemCalendar = null;
        notificationActivity.mItemMatter = null;
        notificationActivity.mItemEmail = null;
        notificationActivity.mItemYahooEmail = null;
        notificationActivity.mItemOutlookEmail = null;
        notificationActivity.mItemGoogleMeet = null;
        notificationActivity.mItemSms = null;
        notificationActivity.mItemMissedCall = null;
        notificationActivity.mItemWechat = null;
        notificationActivity.mItemQq = null;
        notificationActivity.mItemFacebook = null;
        notificationActivity.mItemTwitter = null;
        notificationActivity.mItemInstagram = null;
        notificationActivity.mItemWhatsApp = null;
        notificationActivity.mItemWhatsAppBusiness = null;
        notificationActivity.mItemMessenger = null;
        notificationActivity.mItemLinkedin = null;
        notificationActivity.mItemGmail = null;
        notificationActivity.mItemLine = null;
        notificationActivity.mItemViber = null;
        notificationActivity.mItemSkype = null;
        notificationActivity.mItemKakaoTalk = null;
        notificationActivity.mItemVkontakte = null;
        notificationActivity.mItemTumblr = null;
        notificationActivity.mItemSnapchat = null;
        notificationActivity.mItemYouTube = null;
        notificationActivity.mItemPinterest = null;
        notificationActivity.mItemTelegram = null;
        notificationActivity.mItemTikTok = null;
        notificationActivity.redPointToggle = null;
        notificationActivity.tvRedPointTips = null;
        notificationActivity.layRedPoint = null;
    }
}