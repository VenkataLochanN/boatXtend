package com.ido.life.module.device.presenter;

import android.os.Handler;
import android.os.Looper;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.QuickReplyInfo;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.module.device.view.IQuickMsgReplyView;
import com.ido.life.util.SPHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class QuickMsgReplyPresenter extends BaseCmdPresenter<IQuickMsgReplyView> {
    private static final long QUICK_MSG_SET_TIMEOUT = 10000;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Runnable mTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$QuickMsgReplyPresenter$2BNKMWPJfuy9qBNmgb5WUxidk1g
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$QuickMsgReplyPresenter();
        }
    };

    public /* synthetic */ void lambda$new$0$QuickMsgReplyPresenter() {
        if (isAttachView()) {
            ((IQuickMsgReplyView) getView()).onSetQuickReplyInfoFailed();
        }
    }

    public List<String> getQuickMsgList() {
        List<String> quickMsgList = SPHelper.getQuickMsgList();
        if (quickMsgList.isEmpty()) {
            quickMsgList.add(LanguageUtil.getLanguageText(R.string.device_quick_msg_default));
        }
        return quickMsgList;
    }

    public void saveQuickMsgList(List<String> list) {
        SPHelper.saveQuickMsgList(list);
    }

    public void sendQuickReplyInfo2Device(List<String> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        QuickReplyInfo quickReplyInfo = new QuickReplyInfo();
        quickReplyInfo.num = list.size();
        quickReplyInfo.fast_items = new ArrayList();
        int i = 0;
        while (i < list.size()) {
            QuickReplyInfo.QuickMsg quickMsg = new QuickReplyInfo.QuickMsg();
            int i2 = i + 1;
            quickMsg.msg_id = i2;
            quickMsg.on_off = 1;
            quickMsg.msg_data = list.get(i);
            quickReplyInfo.fast_items.add(quickMsg);
            i = i2;
        }
        this.mHandler.removeCallbacks(this.mTimeoutRunnable);
        this.mHandler.postDelayed(this.mTimeoutRunnable, 10000L);
        BLEManager.setQuickReplyInfo(quickReplyInfo);
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (isAttachView() && settingType == SettingCallBack.SettingType.QUICK_REPLY_INFO) {
            this.mHandler.removeCallbacks(this.mTimeoutRunnable);
            ((IQuickMsgReplyView) getView()).onSetQuickReplyInfoFailed();
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        super.onSetCmdSuccess(settingType, obj);
        if (isAttachView() && settingType == SettingCallBack.SettingType.QUICK_REPLY_INFO) {
            this.mHandler.removeCallbacks(this.mTimeoutRunnable);
            ((IQuickMsgReplyView) getView()).onSetQuickReplyInfoSuccess();
        }
    }
}