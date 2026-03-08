package com.ido.life.data.cache;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.DeviceParaChangedCallBack;
import com.ido.ble.callback.OperateCallBack;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.DeviceChangedPara;
import com.ido.ble.protocol.model.MessageNotifyState;
import com.ido.ble.protocol.model.MessageNotifyStateCmdParaWrapper;
import com.ido.ble.protocol.model.QuickReplyInfo;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.AppUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.life.VeryFitApp;
import com.ido.life.base.BaseMessage;
import com.ido.life.bean.NotificationApp;
import com.ido.life.bean.QuickMsgBean;
import com.ido.life.bean.SwitchStatus;
import com.ido.life.bean.TranIconBean;
import com.ido.life.ble.BaseOperateCallback;
import com.ido.life.constants.Constants;
import com.ido.life.data.cache.RemindDataManager;
import com.ido.life.transmitter.FileTransmitter;
import com.ido.life.transmitter.task.IconTransferTask;
import com.ido.life.transmitter.task.TransferTask;
import com.ido.life.util.CoroutinesUtils;
import com.ido.life.util.ListUtils;
import com.ido.life.util.MsgNotificationHelper;
import com.ido.life.util.NoticeAppUtil;
import com.ido.life.util.ResourceUtils;
import com.ido.life.util.SPHelper;
import com.ido.life.util.eventbus.EventBusHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ProducerScope;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: RemindDataManager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\b*\u0002\u0011\u001a\u0018\u0000 L2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003LMNB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\"\u001a\u00020#H\u0002J\u000e\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020\u0017J\u000e\u0010$\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u0005J\"\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u00072\u0006\u0010)\u001a\u00020\u00172\f\u0010*\u001a\b\u0012\u0004\u0012\u00020(0\u0007J\u001e\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u00072\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0007J\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007J\u0006\u00102\u001a\u00020\u001dJ\u0016\u00103\u001a\b\u0012\u0004\u0012\u00020(0\u00072\u0006\u0010)\u001a\u00020\u0017H\u0002J\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00150\u0007J\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u00170\u0007J\u0010\u00106\u001a\u00020#2\u0006\u00107\u001a\u00020\u0005H\u0014J\u0012\u00108\u001a\u00020#2\b\u00109\u001a\u0004\u0018\u00010\u0005H\u0016J\b\u0010:\u001a\u00020#H\u0016J\u0016\u0010;\u001a\u00020#2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00170=H\u0007J\u0012\u0010>\u001a\u00020#2\b\u00109\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010?\u001a\u00020#2\u0006\u00109\u001a\u00020\u0005H\u0016J\u0018\u0010@\u001a\u00020#2\u000e\u0010A\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0007H\u0002J\b\u0010B\u001a\u00020#H\u0002J\b\u0010C\u001a\u00020#H\u0002J\b\u0010D\u001a\u00020#H\u0002J\u0016\u0010E\u001a\u00020#2\f\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00150GH\u0002J\u000e\u0010H\u001a\u00020#2\u0006\u0010I\u001a\u00020\u0017J\u0018\u0010J\u001a\u00020\u001d*\b\u0012\u0004\u0012\u00020\u00150\u00072\u0006\u0010K\u001a\u00020\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006O"}, d2 = {"Lcom/ido/life/data/cache/RemindDataManager;", "Lcom/ido/life/data/cache/AbsDataCacheManager;", "", "()V", "mCurrentLanguage", "", "mInstalledThirdPartyAppPackages", "", "mJob", "Lkotlinx/coroutines/Job;", "mMaxDefaultNotificationSetTimes", "Ljava/util/concurrent/atomic/AtomicInteger;", "mNotificationIconNotifyCallback", "Lcom/ido/ble/callback/DeviceParaChangedCallBack$ICallBack;", "mOnNotificationStatusChangedCallback", "Lcom/ido/life/data/cache/RemindDataManager$OnNotificationStatusChangedCallback;", "mOperateCallback", "com/ido/life/data/cache/RemindDataManager$mOperateCallback$1", "Lcom/ido/life/data/cache/RemindDataManager$mOperateCallback$1;", "mQueryJob", "mReminderStatusInDevice", "Lcom/ido/ble/protocol/model/MessageNotifyState;", "mReminderTypesInDevice", "", "mRetryCount", "mSettingCallBack", "com/ido/life/data/cache/RemindDataManager$mSettingCallBack$1", "Lcom/ido/life/data/cache/RemindDataManager$mSettingCallBack$1;", "openQuickMsgReply", "", "getOpenQuickMsgReply", "()Z", "setOpenQuickMsgReply", "(Z)V", "addDebounce", "", "canNotify", "type", "pkg", "convertQuickMsgBeanByLanguage", "Lcom/ido/life/bean/QuickMsgBean;", "useLang", "list", "getInstalledApps", "Lcom/ido/life/bean/NotificationApp;", "functionInfo", "Lcom/ido/ble/protocol/model/SupportFunctionInfo;", "notificationStatus", "Lcom/ido/life/bean/SwitchStatus$NotificationSwitch;", "getInstalledPackages", "getMissCallDefaultStatus", "getQuickReplyMsgByLanguage", "getReminderStatusInDevice", "getReminderTypesInDevice", "logP", "msg", "onBind", "macAddress", "onClear", "onDeviceLanguageChanged", "event", "Lcom/ido/life/base/BaseMessage;", "onSdkInitComplete", "onUnBind", "processNotifyState", "items", "queryMessageNotifyState", "queryMessageNotifyStateDelay", "sendDefaultNotificationState2Device", "sendNotificationIcons", "stateList", "", "sendQuickReplyInfo2Device", "language", "hasIcon", "state", "Companion", "OnNotificationStatusChangedCallback", "SingleInstanceHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class RemindDataManager extends AbsDataCacheManager<Object> {
    public static final int HAS_PIC = 1;
    public static final int MAX_RETRY_COUNT = 10;
    public static final int NO_PIC = 2;
    private Job mJob;
    private OnNotificationStatusChangedCallback mOnNotificationStatusChangedCallback;
    private Job mQueryJob;
    private int mRetryCount;
    private boolean openQuickMsgReply;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final RemindDataManager instance = SingleInstanceHolder.INSTANCE.getINSTANCE();
    private static final List<NotificationApp> WHITE_APP_LIST = CollectionsKt.mutableListOf(new NotificationApp(null, "com.skype", 0, 0, null, "skype", "skypeNotifyFlag", "skypeSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.skype.insiders", 0, 0, null, "skype", "skypeNotifyFlag", "skypeSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.skype.raider", 0, 0, null, "skype", "skypeNotifyFlag", "skypeSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.facebook.katana", 0, 0, null, "noticeFacebook", "facebookNotifyFlag", "facebookSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.instagram.android", 0, 0, null, "instagram", "instagramNotifyFlag", "instagramSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.tencent.mm", 0, 0, null, "noticeWeixin", "wechatNotifyFlag", "wechatSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.linkedin.android", 0, 0, null, "linked_in", "linkedinNotifyFlag", "linkedinSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.twitter.android", 0, 0, null, "noticeTwitter", "twitterNotifyFlag", "twitterSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.whatsapp", 0, 0, null, "whatsapp", "whatsAppNotifyFlag", "whatsAppSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.tencent.mobileqq", 0, 0, null, "noticeQQ", "qqNotifyFlag", "qqSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.tencent.qqlite", 0, 0, null, "noticeQQ", "qqNotifyFlag", "qqSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.kakao.talk", 0, 0, null, "KakaoTalk", "kakaoTalkNotifyFlag", "kakaoTalkSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.viber.voip", 0, 0, null, "Viber", "viberNotifyFlag", "viberSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "jp.naver.line.android", 0, 0, null, "Line", "lineNotifyFlag", "lineSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "line.android", 0, 0, null, "Line", "lineNotifyFlag", "lineSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.vkontakte.android", 0, 0, null, "VKontakte", "vkNotifyFlag", "vkSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.google.android.gm", 0, 0, null, "Gmail", "gmailNotifyFlag", "gmailSwitch", 0, 0, 0, 1821, null), new NotificationApp(null, "com.snapchat.android", 0, 0, null, "Snapchat", "snapchatNotifyFlag", "snapchatSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "org.telegram.messenger", 0, 0, null, "Telegram", "telegramNotifyFlag", "telegramSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.google.android.youtube", 0, 0, null, "notice_youtube", "youTubeNotifyFlag", "youTubeSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.pinterest", 0, 0, null, "notice_pinterest_yahoo", "pinterestNotifyFlag", "pinterestSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.tumblr", 0, 0, null, "notice_tumblr", "tumblrNotifyFlag", "tumblrSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.zhiliaoapp.musically", 0, 0, null, "notice_TikTok", "tikTokNotifyFlag", "tikTokSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.ss.android.ugc.trill", 0, 0, null, "notice_TikTok", "tikTokNotifyFlag", "tikTokSwitched", 0, 0, 0, 1821, null), new NotificationApp(null, "com.whatsapp.w4b", 0, 0, null, "V3_support_WhatsApp_Business", "whatsAppBusinessNotifyFlag", "whatsAppBusinessSwitched", 0, 0, 0, 1821, null));
    private String mCurrentLanguage = "";
    private List<String> mInstalledThirdPartyAppPackages = new ArrayList();
    private List<MessageNotifyState> mReminderStatusInDevice = new ArrayList();
    private List<Integer> mReminderTypesInDevice = new ArrayList();
    private AtomicInteger mMaxDefaultNotificationSetTimes = new AtomicInteger(10);
    private final RemindDataManager$mSettingCallBack$1 mSettingCallBack = new SettingCallBack.ICallBack() { // from class: com.ido.life.data.cache.RemindDataManager$mSettingCallBack$1
        @Override // com.ido.ble.callback.SettingCallBack.ICallBack
        public void onSuccess(SettingCallBack.SettingType p0, Object p1) {
            if (p0 == SettingCallBack.SettingType.QUICK_REPLY_INFO) {
                this.this$0.logP("默认快捷回复设置成功!!!");
                BLEManager.unregisterSettingCallBack(this);
            }
        }

        @Override // com.ido.ble.callback.SettingCallBack.ICallBack
        public void onFailed(SettingCallBack.SettingType p0) {
            if (p0 == SettingCallBack.SettingType.QUICK_REPLY_INFO) {
                this.this$0.logP("默认快捷回复设置失败!!!");
                BLEManager.unregisterSettingCallBack(this);
            }
        }
    };
    private final DeviceParaChangedCallBack.ICallBack mNotificationIconNotifyCallback = new DeviceParaChangedCallBack.ICallBack() { // from class: com.ido.life.data.cache.RemindDataManager$mNotificationIconNotifyCallback$1
        @Override // com.ido.ble.callback.DeviceParaChangedCallBack.ICallBack
        public final void onChanged(DeviceChangedPara deviceChangedPara) {
            if (deviceChangedPara.dataType == 13) {
                this.this$0.logP("图标需要更新");
                RemindDataManager.OnNotificationStatusChangedCallback onNotificationStatusChangedCallback = this.this$0.mOnNotificationStatusChangedCallback;
                if (onNotificationStatusChangedCallback != null) {
                    onNotificationStatusChangedCallback.onNotificationStatusChanged();
                    return;
                }
                return;
            }
            if (deviceChangedPara.dataType == 20) {
                this.this$0.logP("通知状态发生变化");
                EventBusHelper.post(Constants.EventConstants.EVENT_HEALTH_REMINDER_STATUS_CHANGED);
            }
        }
    };
    private final RemindDataManager$mOperateCallback$1 mOperateCallback = new BaseOperateCallback() { // from class: com.ido.life.data.cache.RemindDataManager$mOperateCallback$1
        @Override // com.ido.life.ble.BaseOperateCallback, com.ido.ble.callback.OperateCallBack.ICallBack
        public void onQueryResult(OperateCallBack.OperateType operateType, Object o) {
            if (operateType == OperateCallBack.OperateType.MESSAGE_NOTIFY_STATE) {
                if (o != null ? o instanceof MessageNotifyStateCmdParaWrapper.Response : true) {
                    this.this$0.logP("onQueryResult o = " + o);
                    MessageNotifyStateCmdParaWrapper.Response response = (MessageNotifyStateCmdParaWrapper.Response) o;
                    if (response != null && response.err_code == 0) {
                        this.this$0.processNotifyState(response != null ? response.items : null);
                    } else {
                        this.this$0.logP("获取应用通知状态列表失败");
                    }
                }
            }
        }

        @Override // com.ido.life.ble.BaseOperateCallback, com.ido.ble.callback.OperateCallBack.ICallBack
        public void onModifyResult(OperateCallBack.OperateType operateType, boolean b2) {
            if (operateType == OperateCallBack.OperateType.MESSAGE_NOTIFY_STATE) {
                this.this$0.logP("RemindDataManager: onModifyResult o = " + b2);
                RemindDataManager.OnNotificationStatusChangedCallback onNotificationStatusChangedCallback = this.this$0.mOnNotificationStatusChangedCallback;
                if (onNotificationStatusChangedCallback != null) {
                    onNotificationStatusChangedCallback.onNotificationStatusChanged();
                }
            }
        }

        @Override // com.ido.life.ble.BaseOperateCallback, com.ido.ble.callback.OperateCallBack.ICallBack
        public void onAddResult(OperateCallBack.OperateType operateType, boolean b2) {
            if (operateType == OperateCallBack.OperateType.MESSAGE_NOTIFY_STATE) {
                this.this$0.logP("RemindDataManager: onAddResult o = " + b2);
                RemindDataManager.OnNotificationStatusChangedCallback onNotificationStatusChangedCallback = this.this$0.mOnNotificationStatusChangedCallback;
                if (onNotificationStatusChangedCallback != null) {
                    onNotificationStatusChangedCallback.onNotificationStatusChanged();
                }
            }
        }
    };

    /* JADX INFO: compiled from: RemindDataManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/ido/life/data/cache/RemindDataManager$OnNotificationStatusChangedCallback;", "", "onNotificationStatusChanged", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public interface OnNotificationStatusChangedCallback {
        void onNotificationStatusChanged();
    }

    /* JADX WARN: Type inference failed for: r0v11, types: [com.ido.life.data.cache.RemindDataManager$mOperateCallback$1] */
    /* JADX WARN: Type inference failed for: r0v8, types: [com.ido.life.data.cache.RemindDataManager$mSettingCallBack$1] */
    public RemindDataManager() {
        BLEManager.registerOperateCallBack(this.mOperateCallback);
        BLEManager.registerDeviceParaChangedCallBack(this.mNotificationIconNotifyCallback);
        addDebounce();
    }

    public final boolean getOpenQuickMsgReply() {
        return this.openQuickMsgReply;
    }

    public final void setOpenQuickMsgReply(boolean z) {
        this.openQuickMsgReply = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void logP(String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        MsgNotificationHelper.saveLog(msg);
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onClear() {
        this.mReminderStatusInDevice.clear();
        this.mReminderTypesInDevice.clear();
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onUnBind(String macAddress) {
        Intrinsics.checkParameterIsNotNull(macAddress, "macAddress");
        super.onUnBind(macAddress);
        SPHelper.removeQuickMsgList(macAddress);
        SPHelper.removeQuickMsgBeanList(macAddress);
        SPHelper.removeDeviceUnreadReminder(macAddress);
        SPHelper.removeScheduleReminderSwitch(macAddress);
        MsgNotificationHelper.saveLog("onUnBind -> removeAllAppBeans");
        NoticeAppUtil.removeAllAppBeans(macAddress);
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onBind(String macAddress) {
        super.onBind(macAddress);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public final void onDeviceLanguageChanged(final BaseMessage<Integer> event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (event.getType() == 901) {
            logP("onDeviceLanguageChanged，language = " + event.getData() + ", openQuickMsgReply = " + this.openQuickMsgReply);
            if (this.openQuickMsgReply) {
                CoroutinesUtils.INSTANCE.delay(2000L, new Function0<Unit>() { // from class: com.ido.life.data.cache.RemindDataManager.onDeviceLanguageChanged.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        RemindDataManager remindDataManager = RemindDataManager.this;
                        Object data = event.getData();
                        Intrinsics.checkExpressionValueIsNotNull(data, "event.data");
                        remindDataManager.sendQuickReplyInfo2Device(((Number) data).intValue());
                    }
                });
                return;
            }
            return;
        }
        if (event.getType() == 903) {
            logP("onNotificationStatusChanged");
            OnNotificationStatusChangedCallback onNotificationStatusChangedCallback = this.mOnNotificationStatusChangedCallback;
            if (onNotificationStatusChangedCallback != null) {
                onNotificationStatusChangedCallback.onNotificationStatusChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processNotifyState(List<MessageNotifyState> items) {
        if (ListUtils.INSTANCE.isNotEmpty(items)) {
            this.mReminderStatusInDevice.clear();
            this.mReminderTypesInDevice.clear();
            List<MessageNotifyState> list = this.mReminderStatusInDevice;
            if (items == null) {
                Intrinsics.throwNpe();
            }
            list.addAll(items);
            ArrayList arrayList = new ArrayList();
            for (MessageNotifyState messageNotifyState : items) {
                this.mReminderTypesInDevice.add(Integer.valueOf(messageNotifyState.evt_type));
                if (messageNotifyState.pic_flag == 2) {
                    arrayList.add(messageNotifyState);
                }
            }
            EventBusHelper.post(Constants.EventConstants.EVENT_THIRD_PARTY_APP_NOTIFICATION_CHANGED);
            sendNotificationIcons(arrayList);
            return;
        }
        sendDefaultNotificationState2Device();
    }

    public final boolean getMissCallDefaultStatus() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        Boolean boolValueOf = supportFunctionInfo != null ? Boolean.valueOf(supportFunctionInfo.V3_support_missed_calls) : null;
        Context appContext = IdoApp.getAppContext();
        String[] onlyPhonePermission = PermissionUtil.getOnlyPhonePermission();
        boolean zCheckSelfPermission = PermissionUtil.checkSelfPermission(appContext, (String[]) Arrays.copyOf(onlyPhonePermission, onlyPhonePermission.length));
        logP("isSupportMissCall, V3_support_missed_calls = " + boolValueOf + ", permit = " + zCheckSelfPermission);
        return supportFunctionInfo != null && supportFunctionInfo.V3_support_missed_calls && zCheckSelfPermission;
    }

    private final synchronized void sendDefaultNotificationState2Device() {
        if (this.mMaxDefaultNotificationSetTimes.get() <= 0) {
            logP("sendDefaultNotificationState2Device out of max 10 times!");
            this.mMaxDefaultNotificationSetTimes.set(10);
        }
        this.mMaxDefaultNotificationSetTimes.decrementAndGet();
        Job job = this.mJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.mJob = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C02161(null), 2, null);
    }

    /* JADX INFO: renamed from: com.ido.life.data.cache.RemindDataManager$sendDefaultNotificationState2Device$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RemindDataManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.data.cache.RemindDataManager$sendDefaultNotificationState2Device$1", f = "RemindDataManager.kt", i = {0, 0}, l = {242}, m = "invokeSuspend", n = {"$this$launch", "defaultPkg"}, s = {"L$0", "L$1"})
    static final class C02161 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;
        private CoroutineScope p$;

        C02161(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02161 c02161 = RemindDataManager.this.new C02161(completion);
            c02161.p$ = (CoroutineScope) obj;
            return c02161;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02161) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x003e  */
        /* JADX WARN: Removed duplicated region for block: B:19:0x0072  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x007b  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00b9  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00f4  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x014a  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0052 -> B:16:0x0055). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 341
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ido.life.data.cache.RemindDataManager.C02161.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private final void sendNotificationIcons(List<? extends MessageNotifyState> stateList) {
        if (ListUtils.INSTANCE.isNullOrEmpty(stateList)) {
            return;
        }
        logP("sendNotificationIcons：" + stateList);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = stateList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            TranIconBean noticeApp = NoticeAppUtil.getNoticeApp(((MessageNotifyState) next).evt_type);
            if ((noticeApp != null ? noticeApp.getIcon() : null) != null) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            arrayList3.add(new IconTransferTask(1, ((MessageNotifyState) it2.next()).evt_type, 1));
        }
        List<TransferTask> mutableList = CollectionsKt.toMutableList((Collection) arrayList3);
        StringBuilder sb = new StringBuilder();
        sb.append("sendNotificationIcons,    There has ");
        sb.append((mutableList != null ? Integer.valueOf(mutableList.size()) : null).intValue());
        sb.append(" Apps which has icon to trans! ");
        logP(sb.toString());
        FileTransmitter.INSTANCE.getInstance().addTask(mutableList);
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onSdkInitComplete(String macAddress) {
        logP("onConnected macAddress = " + macAddress);
        queryMessageNotifyStateDelay();
    }

    private final void queryMessageNotifyStateDelay() {
        if (BLEManager.isConnected() && BLEManager.isBind()) {
            logP("delay 2000 to queryMessageNotifyStateDelay");
            Job job = this.mQueryJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.mQueryJob = CoroutinesUtils.INSTANCE.delay(2000L, new Function0<Unit>() { // from class: com.ido.life.data.cache.RemindDataManager.queryMessageNotifyStateDelay.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    RemindDataManager.this.logP("It's time to queryMessageNotifyStateDelay");
                    OnNotificationStatusChangedCallback onNotificationStatusChangedCallback = RemindDataManager.this.mOnNotificationStatusChangedCallback;
                    if (onNotificationStatusChangedCallback != null) {
                        onNotificationStatusChangedCallback.onNotificationStatusChanged();
                    }
                }
            });
            return;
        }
        logP("queryMessageNotifyStateDelay, BLE is not connected(" + BLEManager.isConnected() + ") or bind(" + BLEManager.isBind() + ')');
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void queryMessageNotifyState() {
        boolean zNotificationEnabled = AppUtil.notificationEnabled(VeryFitApp.getApp());
        logP("queryMessageNotifyState isSupportV3Notify = " + NoticeAppUtil.isSupportV3Notify() + ", notificationEnabled = " + zNotificationEnabled);
        if (NoticeAppUtil.isSupportV3Notify() && zNotificationEnabled) {
            BLEManager.queryMessageNotifyState();
        }
    }

    public final void sendQuickReplyInfo2Device(int language) {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        StringBuilder sb = new StringBuilder();
        sb.append("sendQuickReplyInfo2Device v3_fast_msg_data = ");
        sb.append(supportFunctionInfo != null ? Boolean.valueOf(supportFunctionInfo.v3_fast_msg_data) : null);
        logP(sb.toString());
        if (supportFunctionInfo == null || !supportFunctionInfo.v3_fast_msg_data) {
            return;
        }
        List<QuickMsgBean> quickReplyMsgByLanguage = getQuickReplyMsgByLanguage(language);
        if (ListUtils.INSTANCE.isNotEmpty(quickReplyMsgByLanguage)) {
            QuickReplyInfo quickReplyInfo = new QuickReplyInfo();
            quickReplyInfo.num = quickReplyMsgByLanguage.size();
            quickReplyInfo.fast_items = new ArrayList();
            int size = quickReplyMsgByLanguage.size();
            for (int i = 0; i < size; i++) {
                QuickReplyInfo.QuickMsg quickMsg = new QuickReplyInfo.QuickMsg();
                QuickMsgBean quickMsgBean = quickReplyMsgByLanguage.get(i);
                quickMsg.msg_id = quickMsgBean.getIndex();
                quickMsg.on_off = 1;
                quickMsg.msg_data = quickMsgBean.getMsg();
                quickReplyInfo.fast_items.add(quickMsg);
            }
            BLEManager.registerSettingCallBack(this.mSettingCallBack);
            BLEManager.setQuickReplyInfo(quickReplyInfo);
        }
    }

    private final List<QuickMsgBean> getQuickReplyMsgByLanguage(int useLang) {
        logP("getQuickReplyMsgByLanguage useLang = " + useLang);
        List<QuickMsgBean> list = SPHelper.getQuickMsgBeanList();
        Intrinsics.checkExpressionValueIsNotNull(list, "list");
        List<QuickMsgBean> listConvertQuickMsgBeanByLanguage = convertQuickMsgBeanByLanguage(useLang, list);
        logP("getQuickReplyMsgByLanguage useLang = " + useLang + ", newList = " + listConvertQuickMsgBeanByLanguage);
        return listConvertQuickMsgBeanByLanguage;
    }

    public final List<QuickMsgBean> convertQuickMsgBeanByLanguage(int useLang, List<QuickMsgBean> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        if (ListUtils.INSTANCE.isNullOrEmpty(list)) {
            return new ArrayList();
        }
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
        String language = locale.getLanguage();
        Intrinsics.checkExpressionValueIsNotNull(language, "Locale.getDefault().language");
        Locale locale2 = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale2, "Locale.getDefault()");
        if (language == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = language.toLowerCase(locale2);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
        String languageAbb = ResourceUtils.INSTANCE.getLanguageAbb(useLang);
        logP("convertQuickMsgBeanByLanguage：" + languageAbb + ", sysLan = " + lowerCase);
        ResourceUtils resourceUtils = ResourceUtils.INSTANCE;
        VeryFitApp app = VeryFitApp.getApp();
        Intrinsics.checkExpressionValueIsNotNull(app, "VeryFitApp.getApp()");
        String[] stringArray = resourceUtils.getResourceByLanguage(app, languageAbb).getStringArray(R.array.quick_reply_default_msg);
        Intrinsics.checkExpressionValueIsNotNull(stringArray, "ResourceUtils.getResourc….quick_reply_default_msg)");
        ResourceUtils resourceUtils2 = ResourceUtils.INSTANCE;
        VeryFitApp app2 = VeryFitApp.getApp();
        Intrinsics.checkExpressionValueIsNotNull(app2, "VeryFitApp.getApp()");
        resourceUtils2.restoreSystemResource(app2, lowerCase);
        ArrayList arrayList = new ArrayList();
        for (QuickMsgBean quickMsgBean : list) {
            if (quickMsgBean.getIndex() <= stringArray.length) {
                int index = quickMsgBean.getIndex();
                String str = stringArray[quickMsgBean.getIndex() - 1];
                Intrinsics.checkExpressionValueIsNotNull(str, "msgListByForLanguage[it.index - 1]");
                arrayList.add(new QuickMsgBean(index, str));
            } else {
                arrayList.add(quickMsgBean);
            }
        }
        return arrayList;
    }

    public final List<MessageNotifyState> getReminderStatusInDevice() {
        return this.mReminderStatusInDevice;
    }

    public final List<Integer> getReminderTypesInDevice() {
        return this.mReminderTypesInDevice;
    }

    public final boolean canNotify(String pkg) {
        Object next;
        Intrinsics.checkParameterIsNotNull(pkg, "pkg");
        int typeByPkg = NoticeAppUtil.getTypeByPkg(pkg);
        if (typeByPkg < 0) {
            return false;
        }
        Iterator<T> it = this.mReminderStatusInDevice.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((MessageNotifyState) next).evt_type == typeByPkg) {
                break;
            }
        }
        MessageNotifyState messageNotifyState = (MessageNotifyState) next;
        if (messageNotifyState != null) {
            return messageNotifyState.notify_state == 1 || messageNotifyState.notify_state == 2;
        }
        return false;
    }

    public final boolean canNotify(int type) {
        Object next;
        Iterator<T> it = this.mReminderStatusInDevice.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((MessageNotifyState) next).evt_type == type) {
                break;
            }
        }
        MessageNotifyState messageNotifyState = (MessageNotifyState) next;
        if (messageNotifyState != null) {
            return messageNotifyState.notify_state == 1 || messageNotifyState.notify_state == 2;
        }
        return false;
    }

    public final boolean hasIcon(List<MessageNotifyState> hasIcon, MessageNotifyState state) {
        Object next;
        Intrinsics.checkParameterIsNotNull(hasIcon, "$this$hasIcon");
        Intrinsics.checkParameterIsNotNull(state, "state");
        Iterator<T> it = hasIcon.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((MessageNotifyState) next).evt_type == state.evt_type) {
                break;
            }
        }
        MessageNotifyState messageNotifyState = (MessageNotifyState) next;
        return messageNotifyState != null && messageNotifyState.pic_flag == 1;
    }

    private final void addDebounce() {
        CoroutinesUtils.INSTANCE.debounce(500L, new Function1<ProducerScope<? super Long>, Unit>() { // from class: com.ido.life.data.cache.RemindDataManager.addDebounce.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ProducerScope<? super Long> producerScope) {
                invoke2(producerScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final ProducerScope<? super Long> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                RemindDataManager.this.mOnNotificationStatusChangedCallback = new OnNotificationStatusChangedCallback() { // from class: com.ido.life.data.cache.RemindDataManager.addDebounce.1.1
                    @Override // com.ido.life.data.cache.RemindDataManager.OnNotificationStatusChangedCallback
                    public void onNotificationStatusChanged() {
                        it.offer(1L);
                    }
                };
            }
        }, (4 & 4) != 0 ? (Function0) null : null, (4 & 8) != 0 ? (Function1) null : new Function1<Throwable, Unit>() { // from class: com.ido.life.data.cache.RemindDataManager.addDebounce.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                RemindDataManager.this.logP("queryMessageNotifyState error：" + it);
            }
        }, new Function1<Long, Unit>() { // from class: com.ido.life.data.cache.RemindDataManager.addDebounce.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                invoke(l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(long j) {
                RemindDataManager.this.queryMessageNotifyState();
            }
        });
    }

    /* JADX INFO: compiled from: RemindDataManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/ido/life/data/cache/RemindDataManager$SingleInstanceHolder;", "", "()V", "INSTANCE", "Lcom/ido/life/data/cache/RemindDataManager;", "getINSTANCE", "()Lcom/ido/life/data/cache/RemindDataManager;", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final class SingleInstanceHolder {
        public static final SingleInstanceHolder INSTANCE = new SingleInstanceHolder();
        private static final RemindDataManager INSTANCE = new RemindDataManager();

        private SingleInstanceHolder() {
        }

        public final RemindDataManager getINSTANCE() {
            return INSTANCE;
        }
    }

    /* JADX INFO: compiled from: RemindDataManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0001J\u0016\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u0013J\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u0004J\u001e\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0017J&\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006$"}, d2 = {"Lcom/ido/life/data/cache/RemindDataManager$Companion;", "", "()V", "HAS_PIC", "", "MAX_RETRY_COUNT", "NO_PIC", "WHITE_APP_LIST", "", "Lcom/ido/life/bean/NotificationApp;", "getWHITE_APP_LIST", "()Ljava/util/List;", "instance", "Lcom/ido/life/data/cache/RemindDataManager;", "getInstance", "()Lcom/ido/life/data/cache/RemindDataManager;", "getFieldValueInFunctionInfo", "", "fieldName", "", "obj", "getFieldValueInNotificationStatus", "notification", "Lcom/ido/life/bean/SwitchStatus$NotificationSwitch;", "getNotificationState", "Lcom/ido/ble/protocol/model/MessageNotifyState;", "pkg", NotificationCompat.CATEGORY_STATUS, "setFieldValueInNotificationStatus", "value", "setNotificationStatusValue", "", "mNotificationStatus", "reminderStatus", "fieldIntInNotification", "fieldBoolInNotification", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final RemindDataManager getInstance() {
            return RemindDataManager.instance;
        }

        public final List<NotificationApp> getWHITE_APP_LIST() {
            return RemindDataManager.WHITE_APP_LIST;
        }

        public final MessageNotifyState getNotificationState(String pkg, int status) {
            Intrinsics.checkParameterIsNotNull(pkg, "pkg");
            CommonLogUtil.d("getNotificationState pkg = " + pkg + "，status = " + status);
            MessageNotifyState notificationState = getNotificationState(pkg);
            if (notificationState == null) {
                return null;
            }
            notificationState.notify_state = status;
            return notificationState;
        }

        public final MessageNotifyState getNotificationState(String pkg) {
            Intrinsics.checkParameterIsNotNull(pkg, "pkg");
            TranIconBean tranIconBeanUpdateNoticeApp = NoticeAppUtil.updateNoticeApp(pkg);
            CommonLogUtil.d("getNotificationState pkg = " + pkg + "，app = " + tranIconBeanUpdateNoticeApp);
            if (tranIconBeanUpdateNoticeApp == null) {
                return null;
            }
            MessageNotifyState messageNotifyState = new MessageNotifyState();
            messageNotifyState.evt_type = tranIconBeanUpdateNoticeApp.getType();
            return messageNotifyState;
        }

        public final void setNotificationStatusValue(SwitchStatus.NotificationSwitch mNotificationStatus, int reminderStatus, String fieldIntInNotification, String fieldBoolInNotification) {
            Intrinsics.checkParameterIsNotNull(mNotificationStatus, "mNotificationStatus");
            Intrinsics.checkParameterIsNotNull(fieldIntInNotification, "fieldIntInNotification");
            Intrinsics.checkParameterIsNotNull(fieldBoolInNotification, "fieldBoolInNotification");
            Companion companion = this;
            companion.setFieldValueInNotificationStatus(fieldIntInNotification, reminderStatus, mNotificationStatus);
            companion.setFieldValueInNotificationStatus(fieldBoolInNotification, (reminderStatus == 0 || reminderStatus == 3) ? false : true, mNotificationStatus);
        }

        public final int getFieldValueInNotificationStatus(String fieldName, SwitchStatus.NotificationSwitch notification) {
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            Intrinsics.checkParameterIsNotNull(notification, "notification");
            try {
                return notification.getClass().getField(fieldName).getInt(notification);
            } catch (Exception unused) {
                return 3;
            }
        }

        public final boolean setFieldValueInNotificationStatus(String fieldName, int value, SwitchStatus.NotificationSwitch notification) {
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            Intrinsics.checkParameterIsNotNull(notification, "notification");
            try {
                notification.getClass().getField(fieldName).setInt(notification, value);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }

        public final boolean setFieldValueInNotificationStatus(String fieldName, boolean value, SwitchStatus.NotificationSwitch notification) {
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            Intrinsics.checkParameterIsNotNull(notification, "notification");
            try {
                notification.getClass().getField(fieldName).setBoolean(notification, value);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }

        public final boolean getFieldValueInFunctionInfo(String fieldName, Object obj) {
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            Intrinsics.checkParameterIsNotNull(obj, "obj");
            try {
                return obj.getClass().getField(fieldName).getBoolean(obj);
            } catch (Exception unused) {
                return false;
            }
        }
    }

    public final List<NotificationApp> getInstalledApps(SupportFunctionInfo functionInfo, SwitchStatus.NotificationSwitch notificationStatus) {
        String str;
        Intrinsics.checkParameterIsNotNull(functionInfo, "functionInfo");
        Intrinsics.checkParameterIsNotNull(notificationStatus, "notificationStatus");
        List<String> installedPackages = getInstalledPackages();
        Context appContext = IdoApp.getAppContext();
        Intrinsics.checkExpressionValueIsNotNull(appContext, "IdoApp.getAppContext()");
        PackageManager packageManager = appContext.getPackageManager();
        ArrayList arrayList = new ArrayList();
        for (String str2 : installedPackages) {
            try {
                if (!TextUtils.isEmpty(str2)) {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str2, 128);
                    Intrinsics.checkExpressionValueIsNotNull(applicationInfo, "pm.getApplicationInfo(pk…ageManager.GET_META_DATA)");
                    CharSequence charSequenceLoadLabel = applicationInfo.loadLabel(packageManager);
                    Intrinsics.checkExpressionValueIsNotNull(charSequenceLoadLabel, "applicationInfo.loadLabel(pm)");
                    Drawable drawableLoadIcon = applicationInfo.loadIcon(packageManager);
                    str = str2;
                    try {
                        int iIndexOf = WHITE_APP_LIST.indexOf(new NotificationApp(null, str2, 0, 0, null, null, null, null, 0, 0, 0, 2045, null));
                        if (iIndexOf >= 0) {
                            NotificationApp notificationApp = WHITE_APP_LIST.get(iIndexOf);
                            String fieldInFunctionList = notificationApp.getFieldInFunctionList();
                            String fieldIntInNotification = notificationApp.getFieldIntInNotification();
                            boolean fieldValueInFunctionInfo = INSTANCE.getFieldValueInFunctionInfo(fieldInFunctionList, functionInfo);
                            int fieldValueInNotificationStatus = INSTANCE.getFieldValueInNotificationStatus(fieldIntInNotification, notificationStatus);
                            if (!fieldValueInFunctionInfo) {
                                continue;
                            } else if (charSequenceLoadLabel != null) {
                                notificationApp.setName((String) charSequenceLoadLabel);
                                notificationApp.setIcon(drawableLoadIcon);
                                notificationApp.setStatus(fieldValueInNotificationStatus);
                                if (!RemindDataManagerKt.has(arrayList, notificationApp)) {
                                    arrayList.add(notificationApp);
                                }
                            } else {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        CommonLogUtil.printAndSave("Get the installed app " + str + " error：" + e.getMessage());
                    }
                }
            } catch (Exception e3) {
                e = e3;
                str = str2;
            }
        }
        logP("all installed APP count : " + arrayList.size());
        return arrayList;
    }

    public final List<String> getInstalledPackages() {
        String line;
        if (ListUtils.INSTANCE.isNullOrEmpty(this.mInstalledThirdPartyAppPackages)) {
            try {
                Process process = Runtime.getRuntime().exec("pm list package -3");
                Intrinsics.checkExpressionValueIsNotNull(process, "process");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                do {
                    line = bufferedReader.readLine();
                    if (!TextUtils.isEmpty(line)) {
                        Intrinsics.checkExpressionValueIsNotNull(line, "line");
                        if (StringsKt.contains$default((CharSequence) line, (CharSequence) ":", false, 2, (Object) null)) {
                            this.mInstalledThirdPartyAppPackages.add((String) StringsKt.split$default((CharSequence) line, new String[]{":"}, false, 0, 6, (Object) null).get(1));
                        }
                    }
                } while (!TextUtils.isEmpty(line));
            } catch (IOException unused) {
            }
            CommonLogUtil.d("list = " + this.mInstalledThirdPartyAppPackages);
        }
        return this.mInstalledThirdPartyAppPackages;
    }
}