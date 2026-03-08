package com.ido.life.module.device.activity;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.DeviceUnreadReminder;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.PermissionUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.bean.SwitchStatus;
import com.ido.life.boatservice.DeviceAssistService;
import com.ido.life.boatservice.NBoatService;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.dialog.NotificationPermissionSettingDialog;
import com.ido.life.module.device.presenter.NotificationPresenter;
import com.ido.life.module.device.view.INotificationView;
import com.ido.life.util.MsgNotificationHelper;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationActivity extends BaseActivity<NotificationPresenter> implements INotificationView {

    @BindView(R.id.allNotificationID)
    RegularTextView allNotificationTips;

    @BindView(R.id.lay_red_point)
    LinearLayout layRedPoint;
    private DeviceUnreadReminder mDeviceUnreadReminder = null;

    @BindView(R.id.item_calendar)
    CustomItemLabelView mItemCalendar;

    @BindView(R.id.item_email)
    CustomItemLabelView mItemEmail;

    @BindView(R.id.item_facebook)
    CustomItemLabelView mItemFacebook;

    @BindView(R.id.item_gmail)
    CustomItemLabelView mItemGmail;

    @BindView(R.id.item_google_meet)
    CustomItemLabelView mItemGoogleMeet;

    @BindView(R.id.item_instagram)
    CustomItemLabelView mItemInstagram;

    @BindView(R.id.item_kakao_talk)
    CustomItemLabelView mItemKakaoTalk;

    @BindView(R.id.item_line)
    CustomItemLabelView mItemLine;

    @BindView(R.id.item_linkedin)
    CustomItemLabelView mItemLinkedin;

    @BindView(R.id.allToggle)
    CustomToggleButton mItemMasterSwitch;

    @BindView(R.id.item_matter)
    CustomItemLabelView mItemMatter;

    @BindView(R.id.item_messenger)
    CustomItemLabelView mItemMessenger;

    @BindView(R.id.item_missed_call)
    CustomItemLabelView mItemMissedCall;

    @BindView(R.id.item_outlook_email)
    CustomItemLabelView mItemOutlookEmail;

    @BindView(R.id.item_pinterest)
    CustomItemLabelView mItemPinterest;

    @BindView(R.id.item_qq)
    CustomItemLabelView mItemQq;

    @BindView(R.id.item_skype)
    CustomItemLabelView mItemSkype;

    @BindView(R.id.item_sms)
    CustomItemLabelView mItemSms;

    @BindView(R.id.item_snapchat)
    CustomItemLabelView mItemSnapchat;

    @BindView(R.id.item_telegram)
    CustomItemLabelView mItemTelegram;

    @BindView(R.id.item_tik_tok)
    CustomItemLabelView mItemTikTok;

    @BindView(R.id.item_tumblr)
    CustomItemLabelView mItemTumblr;

    @BindView(R.id.item_twitter)
    CustomItemLabelView mItemTwitter;

    @BindView(R.id.item_viber)
    CustomItemLabelView mItemViber;

    @BindView(R.id.item_vkontakte)
    CustomItemLabelView mItemVkontakte;

    @BindView(R.id.item_wechat)
    CustomItemLabelView mItemWechat;

    @BindView(R.id.item_whats_app)
    CustomItemLabelView mItemWhatsApp;

    @BindView(R.id.item_whats_app_business)
    CustomItemLabelView mItemWhatsAppBusiness;

    @BindView(R.id.item_yahoo_email)
    CustomItemLabelView mItemYahooEmail;

    @BindView(R.id.item_youTube)
    CustomItemLabelView mItemYouTube;

    @BindView(R.id.layout_app_list)
    LinearLayout mLayoutAppList;
    private SwitchStatus.NotificationSwitch mNotificationStatus;

    @BindView(R.id.nsv_notification)
    NestedScrollView mScrollView;

    @BindView(R.id.tv_tip)
    RegularTextView mTvTip;

    @BindView(R.id.redPointToggle)
    CustomToggleButton redPointToggle;

    @BindView(R.id.tv_red_point_tips)
    TextView tvRedPointTips;

    @Override // com.ido.common.base.BaseCoreActivity
    protected boolean canDoubleClick() {
        return true;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_msg_notification;
    }

    @Override // com.ido.life.module.device.view.INotificationView
    public void onGetDeviceUnreadReminderFailed() {
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        MsgNotificationHelper.saveLog("NotificationActivity initData，mNotificationStatus ：" + this.mNotificationStatus);
        this.mNotificationStatus = ((NotificationPresenter) this.mPresenter).getNotificationStatus();
        initSwitchStatus();
        if (((NotificationPresenter) this.mPresenter).isSupportRedPoint()) {
            ((NotificationPresenter) this.mPresenter).getDeviceUnreadReminder();
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_msg_notification)).setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$g7yFvfkeoiB3DaAK4JSCNk6aeto
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$NotificationActivity(view);
            }
        });
        this.allNotificationTips.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$LDPTbgVdWlJRXXnp9Y-eRplJkDM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$1$NotificationActivity(view);
            }
        });
        initSwitchEvent();
        if (((NotificationPresenter) this.mPresenter).isSupportRedPoint()) {
            this.layRedPoint.setVisibility(0);
            this.tvRedPointTips.setVisibility(0);
        } else {
            this.layRedPoint.setVisibility(8);
            this.tvRedPointTips.setVisibility(8);
        }
        this.redPointToggle.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.NotificationActivity.1
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public void onSwitched(View view, boolean z) {
                if (NotificationActivity.this.mDeviceUnreadReminder != null) {
                    NotificationActivity.this.mDeviceUnreadReminder.on_off = z ? 170 : 85;
                    NotificationActivity.this.showSettingLoading(false);
                    ((NotificationPresenter) NotificationActivity.this.mPresenter).setDeviceUnreadReminder(NotificationActivity.this.mDeviceUnreadReminder);
                }
            }
        });
    }

    public /* synthetic */ void lambda$initEvent$0$NotificationActivity(View view) {
        onBackPressed();
    }

    public /* synthetic */ void lambda$initEvent$1$NotificationActivity(View view) {
        startActivity(new SingleTopIntent(this, (Class<?>) NotificationTipsActivity.class));
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        initTipText();
        this.allNotificationTips.setText(getLanguageText(R.string.device_allow_notifications));
        this.mItemCalendar.setTitle(getLanguageText(R.string.device_calendar));
        this.mItemEmail.setTitle(getLanguageText(R.string.device_email));
        this.mItemSms.setTitle(getLanguageText(R.string.device_sms));
        this.mItemMissedCall.setTitle(getLanguageText(R.string.device_missed_call));
        this.mItemWechat.setTitle(getLanguageText(R.string.device_wechat));
    }

    @Override // com.ido.life.module.device.view.INotificationView
    public void onGetAllInstalledApp() {
        initItemDisplayState();
        this.mScrollView.setVisibility(0);
    }

    @Override // com.ido.life.module.device.view.INotificationView
    public void onGetDeviceUnreadReminderSuccess(DeviceUnreadReminder deviceUnreadReminder) {
        this.mDeviceUnreadReminder = deviceUnreadReminder;
        DeviceUnreadReminder deviceUnreadReminder2 = this.mDeviceUnreadReminder;
        if (deviceUnreadReminder2 != null) {
            this.redPointToggle.setSwitchStatus(deviceUnreadReminder2.on_off == 170);
        }
    }

    @Override // com.ido.life.module.device.view.INotificationView
    public void onSetDeviceUnreadReminderSuccess() {
        dismissLoadingDialog();
    }

    @Override // com.ido.life.module.device.view.INotificationView
    public void onSetDeviceUnreadReminderFailed() {
        dismissLoadingDialog();
        showToast(getLanguageText(R.string.public_set_failed));
        this.redPointToggle.setSwitchStatus(!r0.getSwitchStatus());
        DeviceUnreadReminder deviceUnreadReminder = this.mDeviceUnreadReminder;
        if (deviceUnreadReminder != null) {
            this.redPointToggle.getSwitchStatus();
            deviceUnreadReminder.on_off = 170;
        }
    }

    private void initTipText() {
        String string = getString(isNotificationEnabled() ? R.string.device_notification_permission_reset_tip : R.string.device_notification_permission_tip);
        String string2 = getString(R.string.sport_setting_manager_to_set);
        String strConcat = string.concat("\t\t").concat(string2);
        int iIndexOf = strConcat.indexOf(string2);
        SpannableString spannableString = new SpannableString(strConcat);
        spannableString.setSpan(new ClickableSpan() { // from class: com.ido.life.module.device.activity.NotificationActivity.2
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                MsgNotificationHelper.saveLog("click top tips");
                NotificationActivity.this.jump2SettingActivity();
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
                textPaint.setColor(NotificationActivity.this.getColor(R.color.color_027AFF));
            }
        }, iIndexOf, string2.length() + iIndexOf, 33);
        this.mTvTip.setText(spannableString);
        this.mTvTip.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initItemDisplayState() {
        ArrayList arrayList = new ArrayList();
        this.mItemGoogleMeet.setVisibility(((NotificationPresenter) this.mPresenter).hasGoogleMeet() ? 0 : 8);
        this.mItemYahooEmail.setVisibility(((NotificationPresenter) this.mPresenter).hasYahoo() ? 0 : 8);
        this.mItemOutlookEmail.setVisibility(((NotificationPresenter) this.mPresenter).hasOutlook() ? 0 : 8);
        boolean zHasWechat = ((NotificationPresenter) this.mPresenter).hasWechat();
        this.mItemWechat.setVisibility(zHasWechat ? 0 : 8);
        if (zHasWechat) {
            arrayList.add(this.mItemWechat);
        }
        boolean zHasQQ = ((NotificationPresenter) this.mPresenter).hasQQ();
        this.mItemQq.setVisibility(zHasQQ ? 0 : 8);
        if (zHasQQ) {
            arrayList.add(this.mItemQq);
        }
        boolean zHasFacebook = ((NotificationPresenter) this.mPresenter).hasFacebook();
        this.mItemFacebook.setVisibility(zHasFacebook ? 0 : 8);
        if (zHasFacebook) {
            arrayList.add(this.mItemFacebook);
        }
        boolean zHasTwitter = ((NotificationPresenter) this.mPresenter).hasTwitter();
        this.mItemTwitter.setVisibility(zHasTwitter ? 0 : 8);
        if (zHasTwitter) {
            arrayList.add(this.mItemTwitter);
        }
        if (arrayList.size() > 0) {
            ((CustomItemLabelView) arrayList.get(arrayList.size() - 1)).setHasBottomDivider(true);
            arrayList.clear();
        }
        boolean zHasInstagram = ((NotificationPresenter) this.mPresenter).hasInstagram();
        this.mItemInstagram.setVisibility(zHasInstagram ? 0 : 8);
        if (zHasInstagram) {
            arrayList.add(this.mItemInstagram);
        }
        boolean zHasWhatsApp = ((NotificationPresenter) this.mPresenter).hasWhatsApp();
        this.mItemWhatsApp.setVisibility(zHasWhatsApp ? 0 : 8);
        if (zHasWhatsApp) {
            arrayList.add(this.mItemWhatsApp);
        }
        boolean zHasWhatsAppBusiness = ((NotificationPresenter) this.mPresenter).hasWhatsAppBusiness();
        this.mItemWhatsAppBusiness.setVisibility(zHasWhatsAppBusiness ? 0 : 8);
        if (zHasWhatsAppBusiness) {
            arrayList.add(this.mItemWhatsAppBusiness);
        }
        boolean zHasMessengre = ((NotificationPresenter) this.mPresenter).hasMessengre();
        this.mItemMessenger.setVisibility(zHasMessengre ? 0 : 8);
        if (zHasMessengre) {
            arrayList.add(this.mItemMessenger);
        }
        if (arrayList.size() > 0) {
            ((CustomItemLabelView) arrayList.get(arrayList.size() - 1)).setHasBottomDivider(true);
            arrayList.clear();
        }
        boolean zHasLinkedin = ((NotificationPresenter) this.mPresenter).hasLinkedin();
        this.mItemLinkedin.setVisibility(zHasLinkedin ? 0 : 8);
        if (zHasLinkedin) {
            arrayList.add(this.mItemLinkedin);
        }
        boolean zHasLine = ((NotificationPresenter) this.mPresenter).hasLine();
        this.mItemLine.setVisibility(zHasLine ? 0 : 8);
        if (zHasLine) {
            arrayList.add(this.mItemLine);
        }
        boolean zHasViber = ((NotificationPresenter) this.mPresenter).hasViber();
        this.mItemViber.setVisibility(zHasViber ? 0 : 8);
        if (zHasViber) {
            arrayList.add(this.mItemViber);
        }
        if (arrayList.size() > 0) {
            ((CustomItemLabelView) arrayList.get(arrayList.size() - 1)).setHasBottomDivider(true);
            arrayList.clear();
        }
        boolean zHasSkype = ((NotificationPresenter) this.mPresenter).hasSkype();
        this.mItemSkype.setVisibility(zHasSkype ? 0 : 8);
        if (zHasSkype) {
            arrayList.add(this.mItemSkype);
        }
        boolean zHasKakaoTalk = ((NotificationPresenter) this.mPresenter).hasKakaoTalk();
        this.mItemKakaoTalk.setVisibility(zHasKakaoTalk ? 0 : 8);
        if (zHasKakaoTalk) {
            arrayList.add(this.mItemKakaoTalk);
        }
        boolean zHasVKontakte = ((NotificationPresenter) this.mPresenter).hasVKontakte();
        this.mItemVkontakte.setVisibility(zHasVKontakte ? 0 : 8);
        if (zHasVKontakte) {
            arrayList.add(this.mItemVkontakte);
        }
        boolean zHasTumblr = ((NotificationPresenter) this.mPresenter).hasTumblr();
        this.mItemTumblr.setVisibility(zHasTumblr ? 0 : 8);
        if (zHasTumblr) {
            arrayList.add(this.mItemTumblr);
        }
        boolean zHasSnapchat = ((NotificationPresenter) this.mPresenter).hasSnapchat();
        this.mItemSnapchat.setVisibility(zHasSnapchat ? 0 : 8);
        if (zHasSnapchat) {
            arrayList.add(this.mItemSnapchat);
        }
        if (arrayList.size() > 0) {
            ((CustomItemLabelView) arrayList.get(arrayList.size() - 1)).setHasBottomDivider(true);
            arrayList.clear();
        }
        boolean zHasTelegram = ((NotificationPresenter) this.mPresenter).hasTelegram();
        this.mItemTelegram.setVisibility(zHasTelegram ? 0 : 8);
        if (zHasTelegram) {
            arrayList.add(this.mItemTelegram);
        }
        boolean zHasTikTok = ((NotificationPresenter) this.mPresenter).hasTikTok();
        this.mItemTikTok.setVisibility(zHasTikTok ? 0 : 8);
        if (zHasTikTok) {
            arrayList.add(this.mItemTikTok);
        }
        boolean zHasYoutube = ((NotificationPresenter) this.mPresenter).hasYoutube();
        this.mItemYouTube.setVisibility(zHasYoutube ? 0 : 8);
        if (zHasYoutube) {
            arrayList.add(this.mItemYouTube);
        }
        boolean zHasPinterest = ((NotificationPresenter) this.mPresenter).hasPinterest();
        this.mItemPinterest.setVisibility(zHasPinterest ? 0 : 8);
        if (zHasPinterest) {
            arrayList.add(this.mItemPinterest);
        }
        if (arrayList.size() > 0) {
            ((CustomItemLabelView) arrayList.get(arrayList.size() - 1)).setHasBottomDivider(true);
            arrayList.clear();
        }
    }

    private void initSwitchStatus() {
        MsgNotificationHelper.saveLog("NotificationActivity initSwitchStatus，isNotificationEnabled ：" + isNotificationEnabled());
        this.mItemMasterSwitch.setSwitchStatus(this.mNotificationStatus.masterSwitched && isNotificationEnabled());
        setAllItemSwitchEnable();
        this.mItemCalendar.setSwitchStatus(this.mNotificationStatus.calendarSwitched);
        this.mItemMatter.setSwitchStatus(this.mNotificationStatus.matterSwitched);
        this.mItemEmail.setSwitchStatus(this.mNotificationStatus.mailSwitched);
        this.mItemYahooEmail.setSwitchStatus(this.mNotificationStatus.mailYahooSwitched);
        this.mItemOutlookEmail.setSwitchStatus(this.mNotificationStatus.mailOutlookSwitched);
        this.mItemGoogleMeet.setSwitchStatus(this.mNotificationStatus.googleMeetSwitched);
        this.mItemSms.setSwitchStatus(this.mNotificationStatus.smsSwitched);
        this.mItemMissedCall.setSwitchStatus(this.mNotificationStatus.missedCall);
        this.mItemWechat.setSwitchStatus(this.mNotificationStatus.wechatSwitched);
        this.mItemQq.setSwitchStatus(this.mNotificationStatus.qqSwitched);
        this.mItemFacebook.setSwitchStatus(this.mNotificationStatus.facebookSwitched);
        this.mItemTwitter.setSwitchStatus(this.mNotificationStatus.twitterSwitched);
        this.mItemInstagram.setSwitchStatus(this.mNotificationStatus.instagramSwitched);
        this.mItemWhatsApp.setSwitchStatus(this.mNotificationStatus.whatsAppSwitched);
        this.mItemWhatsAppBusiness.setSwitchStatus(this.mNotificationStatus.whatsAppBusinessSwitched);
        this.mItemMessenger.setSwitchStatus(this.mNotificationStatus.messengerSwitched);
        this.mItemLinkedin.setSwitchStatus(this.mNotificationStatus.linkedinSwitched);
        this.mItemLine.setSwitchStatus(this.mNotificationStatus.lineSwitched);
        this.mItemViber.setSwitchStatus(this.mNotificationStatus.viberSwitched);
        this.mItemSkype.setSwitchStatus(this.mNotificationStatus.skypeSwitched);
        this.mItemKakaoTalk.setSwitchStatus(this.mNotificationStatus.kakaoTalkSwitched);
        this.mItemVkontakte.setSwitchStatus(this.mNotificationStatus.vkSwitched);
        this.mItemTumblr.setSwitchStatus(this.mNotificationStatus.tumblrSwitched);
        this.mItemSnapchat.setSwitchStatus(this.mNotificationStatus.snapchatSwitched);
        this.mItemYouTube.setSwitchStatus(this.mNotificationStatus.youTubeSwitched);
        this.mItemPinterest.setSwitchStatus(this.mNotificationStatus.pinterestSwitched);
        this.mItemTelegram.setSwitchStatus(this.mNotificationStatus.telegramSwitched);
        this.mItemTikTok.setSwitchStatus(this.mNotificationStatus.tikTokSwitched);
    }

    private void initSwitchEvent() {
        this.mItemMasterSwitch.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$uGChXNaHCOtWGJo2Pe9i6rC2Mo4
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$2$NotificationActivity(view, z);
            }
        });
        this.mItemCalendar.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$_-qEx1BSY15cDOax0a5smBTRkN0
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$3$NotificationActivity(view, z);
            }
        });
        this.mItemMatter.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$TRSxeQNOZxc_HrzoZtfPyYWvw-0
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$4$NotificationActivity(view, z);
            }
        });
        this.mItemEmail.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$pKKJEt2XO9aF2y7d-4MFOA2TXwA
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$5$NotificationActivity(view, z);
            }
        });
        this.mItemYahooEmail.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$j-3MJWLKgN6qIUB5e9ZuDOjsCK4
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$6$NotificationActivity(view, z);
            }
        });
        this.mItemOutlookEmail.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$nYA4zVrmEd5sW0noN_YZfhF69ug
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$7$NotificationActivity(view, z);
            }
        });
        this.mItemGoogleMeet.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$3KVQKRgFzlugAu61puWFT8LwRT0
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$8$NotificationActivity(view, z);
            }
        });
        this.mItemSms.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$wFhH9vjHVhZe5Cr3Krv6NC1NWQc
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$9$NotificationActivity(view, z);
            }
        });
        this.mItemMissedCall.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$OCVCfdYv75GrPuQy8hPAJhuzGS4
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$10$NotificationActivity(view, z);
            }
        });
        this.mItemWechat.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$m8HjE6Pc3Wf7M2_cpxCelNvbLkA
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$11$NotificationActivity(view, z);
            }
        });
        this.mItemQq.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$vodSA9Hsq9703DWkrAEWSw7OiYo
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$12$NotificationActivity(view, z);
            }
        });
        this.mItemFacebook.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$2-SMf0hkYGfOhKrVQ6ynu0QLjSU
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$13$NotificationActivity(view, z);
            }
        });
        this.mItemTwitter.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$IAgilvHyb_ohTVkWeEhzKkTX2NE
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$14$NotificationActivity(view, z);
            }
        });
        this.mItemInstagram.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$GxQid19OWiQAaS2mzYurvcb_XXc
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$15$NotificationActivity(view, z);
            }
        });
        this.mItemWhatsApp.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$0vcbTgLEn1dvXps0JNjizd4aynA
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$16$NotificationActivity(view, z);
            }
        });
        this.mItemWhatsAppBusiness.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$CEk1oet7xEH4gI8LuyD5O-5iwyU
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$17$NotificationActivity(view, z);
            }
        });
        this.mItemMessenger.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$_87byzpLihUwMlxqizNH3ri1j6Q
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$18$NotificationActivity(view, z);
            }
        });
        this.mItemLinkedin.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$Dz2zDNzcaiY_IXyIg9oUMq5GiOU
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$19$NotificationActivity(view, z);
            }
        });
        this.mItemLine.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$dvFibnzEejsjry420G68PP58mYo
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$20$NotificationActivity(view, z);
            }
        });
        this.mItemViber.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$XqZteVxA4Q-Eg6kqdnQzsen34BM
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$21$NotificationActivity(view, z);
            }
        });
        this.mItemSkype.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$LRobRw8GJLxJVXVQiCE2YHsiSu4
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$22$NotificationActivity(view, z);
            }
        });
        this.mItemKakaoTalk.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$tf8mGBBYB4sh5amKRnvMfgsWtvY
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$23$NotificationActivity(view, z);
            }
        });
        this.mItemVkontakte.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$QfsdS3QPorvSBbC1jaqJRLYvyr8
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$24$NotificationActivity(view, z);
            }
        });
        this.mItemTumblr.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$C_WglJERBiWoGPL6Zhbb9hXE1w0
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$25$NotificationActivity(view, z);
            }
        });
        this.mItemSnapchat.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$CYixoRIxKPoQljZrTz5VA_F97c0
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$26$NotificationActivity(view, z);
            }
        });
        this.mItemYouTube.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$8gm-0_aUnEM6pHOA2RlWQojuiQA
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$27$NotificationActivity(view, z);
            }
        });
        this.mItemPinterest.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$C7WZOithoktwY4t2ymM7Mu8nmf8
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$28$NotificationActivity(view, z);
            }
        });
        this.mItemTelegram.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$tRTmEjm_2lt4qsdROQzdVfVvjkY
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$29$NotificationActivity(view, z);
            }
        });
        this.mItemTikTok.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$NotificationActivity$4hO5k4aB4Vc8iwxdXPsALvXQWOc
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$30$NotificationActivity(view, z);
            }
        });
    }

    public /* synthetic */ void lambda$initSwitchEvent$2$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemMasterSwitch isSwitchOn ：" + z + "\nisNotificationEnabled : " + isNotificationEnabled());
        if (z && !isNotificationEnabled()) {
            showPermissionSettingDialog();
            this.mItemMasterSwitch.setSwitchStatus(false);
        } else {
            this.mNotificationStatus.masterSwitched = z;
            setAllItemSwitchEnable();
        }
    }

    public /* synthetic */ void lambda$initSwitchEvent$3$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemCalendar isSwitchOn ：" + z);
        this.mNotificationStatus.calendarSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$4$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemMatter isSwitchOn ：" + z);
        this.mNotificationStatus.matterSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$5$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemEmail isSwitchOn ：" + z);
        this.mNotificationStatus.mailSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$6$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mailYahooSwitched isSwitchOn ：" + z);
        this.mNotificationStatus.mailYahooSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$7$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mailOutlookSwitched isSwitchOn ：" + z);
        this.mNotificationStatus.mailOutlookSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$8$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("googleMeetSwitched isSwitchOn ：" + z);
        this.mNotificationStatus.googleMeetSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$9$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemSms isSwitchOn ：" + z);
        this.mNotificationStatus.smsSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$10$NotificationActivity(View view, boolean z) {
        if (!PermissionUtil.checkSelfPermission(this, PermissionUtil.getPhonePermission())) {
            requestPermissions(502, PermissionUtil.getPhonePermission());
        } else {
            this.mNotificationStatus.missedCall = z;
        }
    }

    public /* synthetic */ void lambda$initSwitchEvent$11$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemWechat isSwitchOn ：" + z);
        this.mNotificationStatus.wechatSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$12$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemQq isSwitchOn ：" + z);
        this.mNotificationStatus.qqSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$13$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemFacebook isSwitchOn ：" + z);
        this.mNotificationStatus.facebookSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$14$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemTwitter isSwitchOn ：" + z);
        this.mNotificationStatus.twitterSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$15$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemInstagram isSwitchOn ：" + z);
        this.mNotificationStatus.instagramSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$16$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemWhatsApp isSwitchOn ：" + z);
        this.mNotificationStatus.whatsAppSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$17$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemWhatsAppBusiness isSwitchOn ：" + z);
        this.mNotificationStatus.whatsAppBusinessSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$18$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemMessenger isSwitchOn ：" + z);
        this.mNotificationStatus.messengerSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$19$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemLinkedin isSwitchOn ：" + z);
        this.mNotificationStatus.linkedinSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$20$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemLine isSwitchOn ：" + z);
        this.mNotificationStatus.lineSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$21$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemViber isSwitchOn ：" + z);
        this.mNotificationStatus.viberSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$22$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemSkype isSwitchOn ：" + z);
        this.mNotificationStatus.skypeSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$23$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemKakaoTalk isSwitchOn ：" + z);
        this.mNotificationStatus.kakaoTalkSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$24$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemVkontakte isSwitchOn ：" + z);
        this.mNotificationStatus.vkSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$25$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemTumblr isSwitchOn ：" + z);
        this.mNotificationStatus.tumblrSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$26$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemSnapchat isSwitchOn ：" + z);
        this.mNotificationStatus.snapchatSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$27$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemYouTube isSwitchOn ：" + z);
        this.mNotificationStatus.youTubeSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$28$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemPinterest isSwitchOn ：" + z);
        this.mNotificationStatus.pinterestSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$29$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemTelegram isSwitchOn ：" + z);
        this.mNotificationStatus.telegramSwitched = z;
    }

    public /* synthetic */ void lambda$initSwitchEvent$30$NotificationActivity(View view, boolean z) {
        MsgNotificationHelper.saveLog("mItemTikTok isSwitchOn ：" + z);
        this.mNotificationStatus.tikTokSwitched = z;
    }

    private void showPermissionSettingDialog() {
        MsgNotificationHelper.saveLog("NotificationActivity，showPermissionSettingDialog");
        NotificationPermissionSettingDialog.getInstance().setOnClickListener(new NotificationPermissionSettingDialog.OnClickListener() { // from class: com.ido.life.module.device.activity.NotificationActivity.3
            @Override // com.ido.life.dialog.NotificationPermissionSettingDialog.OnClickListener
            public void onConfirmClicked() {
                MsgNotificationHelper.saveLog("PermissionSettingDialog，onConfirmClicked");
                NotificationActivity.this.jump2SettingActivity();
            }

            @Override // com.ido.life.dialog.NotificationPermissionSettingDialog.OnClickListener
            public void onCancelClicked() {
                MsgNotificationHelper.saveLog("PermissionSettingDialog，onCancelClicked");
            }
        }).show(getSupportFragmentManager());
    }

    private void setAllItemSwitchEnable() {
        this.mScrollView.setAlpha((this.mNotificationStatus.masterSwitched && isNotificationEnabled()) ? 1.0f : 0.3f);
        for (int i = 0; i < this.mLayoutAppList.getChildCount(); i++) {
            ((CustomItemLabelView) this.mLayoutAppList.getChildAt(i)).setSwitchEnable(this.mNotificationStatus.masterSwitched);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jump2SettingActivity() {
        MsgNotificationHelper.saveLog("NotificationActivity，jump2SettingActivity");
        try {
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            intent.addFlags(268435456);
            startActivityForResult(intent, 500);
        } catch (ActivityNotFoundException e2) {
            MsgNotificationHelper.saveLog("ActivityNotFoundException ：" + e2.toString());
            try {
                Intent intent2 = new Intent();
                intent2.addFlags(268435456);
                intent2.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$NotificationAccessSettingsActivity"));
                intent2.putExtra(":settings:show_fragment", "NotificationAccessSettings");
                startActivityForResult(intent2, 500);
            } catch (Exception e3) {
                MsgNotificationHelper.saveLog("Exception ：" + e3.toString());
                e3.printStackTrace();
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getNotificationLogPath(), "--------------对不起，您的手机暂不支持------------->>");
            }
            e2.printStackTrace();
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity, com.ido.common.utils.PermissionUtil.RequestResult
    public void requestPermissionsSuccess(int i) {
        if (i == 501) {
            this.mNotificationStatus.smsSwitched = true;
        } else {
            if (i != 502) {
                return;
            }
            this.mNotificationStatus.missedCall = true;
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity, com.ido.common.utils.PermissionUtil.RequestResult
    public void requestPermissionsFail(int i) {
        if (i == 501) {
            this.mNotificationStatus.smsSwitched = false;
            this.mItemSms.setSwitchStatus(false);
        } else {
            if (i != 502) {
                return;
            }
            this.mNotificationStatus.missedCall = false;
            this.mItemMissedCall.setSwitchStatus(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ido.life.base.BaseActivity
    public void saveData() {
        if (((NotificationPresenter) this.mPresenter).isStatusChanged(this.mNotificationStatus)) {
            MsgNotificationHelper.saveLog("NotificationActivity statusChanged，mNotificationStatus ：" + this.mNotificationStatus);
            ((NotificationPresenter) this.mPresenter).saveNotificationStatus(this.mNotificationStatus);
        }
        startService(new Intent(this, (Class<?>) NBoatService.class));
        if (this.mNotificationStatus.smsSwitched) {
            startService(new Intent(this, (Class<?>) DeviceAssistService.class));
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        saveData();
    }
}