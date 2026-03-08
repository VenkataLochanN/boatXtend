package com.ido.life.module.device.presenter;

import android.os.Handler;
import android.os.Looper;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.QuickReplyInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.bean.QuickMsgBean;
import com.ido.life.data.cache.AppNameLanguageManager;
import com.ido.life.data.cache.RemindDataManager;
import com.ido.life.module.device.view.IQuickReplyMsgEditView;
import com.ido.life.util.SPHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class QuickReplyMsgEditPresenter extends BaseCmdPresenter<IQuickReplyMsgEditView> {
    private static final long QUICK_MSG_SET_TIMEOUT = 10000;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Runnable mTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$QuickReplyMsgEditPresenter$xLzrVKBD1eDJ434U7Pms0D_a9Hw
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$QuickReplyMsgEditPresenter();
        }
    };

    public /* synthetic */ void lambda$new$0$QuickReplyMsgEditPresenter() {
        if (isAttachView()) {
            ((IQuickReplyMsgEditView) getView()).onSetQuickReplyInfoFailed();
        }
    }

    public int getMaxId(List<QuickMsgBean> list) {
        Iterator<QuickMsgBean> it = list.iterator();
        int iMax = 0;
        while (it.hasNext()) {
            iMax = Math.max(iMax, it.next().getIndex());
        }
        return Math.max(iMax, ResourceUtil.getStringArray(R.array.quick_reply_default_msg).length);
    }

    public List<QuickMsgBean> getQuickMsgList() {
        return SPHelper.getQuickMsgBeanList();
    }

    public void saveQuickMsgList(List<QuickMsgBean> list) {
        SPHelper.saveQuickMsgBeanList(list);
    }

    public void sendQuickReplyInfo2Device(List<QuickMsgBean> list) {
        List<QuickMsgBean> listConvertQuickMsgBeanByLanguage = RemindDataManager.INSTANCE.getInstance().convertQuickMsgBeanByLanguage(AppNameLanguageManager.INSTANCE.getInstance().getMCurrentLanguage(), list);
        QuickReplyInfo quickReplyInfo = new QuickReplyInfo();
        quickReplyInfo.num = listConvertQuickMsgBeanByLanguage.size();
        quickReplyInfo.fast_items = new ArrayList();
        for (int i = 0; i < listConvertQuickMsgBeanByLanguage.size(); i++) {
            QuickReplyInfo.QuickMsg quickMsg = new QuickReplyInfo.QuickMsg();
            QuickMsgBean quickMsgBean = listConvertQuickMsgBeanByLanguage.get(i);
            quickMsg.msg_id = quickMsgBean.getIndex();
            quickMsg.on_off = 1;
            quickMsg.msg_data = quickMsgBean.getMsg();
            quickReplyInfo.fast_items.add(quickMsg);
        }
        this.mHandler.removeCallbacks(this.mTimeoutRunnable);
        this.mHandler.postDelayed(this.mTimeoutRunnable, 10000L);
        BLEManager.setQuickReplyInfo(quickReplyInfo);
    }

    public void sendQuickReplyMsg2Device(List<QuickMsgBean> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        sendQuickReplyInfo2Device(list);
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (settingType == SettingCallBack.SettingType.QUICK_REPLY_INFO) {
            CommonLogUtil.printAndSave("sendQuickReplyMsg2Device onSetCmdFailed");
            this.mHandler.removeCallbacks(this.mTimeoutRunnable);
            if (isAttachView()) {
                ((IQuickReplyMsgEditView) getView()).onSetQuickReplyInfoFailed();
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        super.onSetCmdSuccess(settingType, obj);
        if (settingType == SettingCallBack.SettingType.QUICK_REPLY_INFO) {
            CommonLogUtil.printAndSave("sendQuickReplyMsg2Device onSetCmdSuccess");
            this.mHandler.removeCallbacks(this.mTimeoutRunnable);
            if (isAttachView()) {
                ((IQuickReplyMsgEditView) getView()).onSetQuickReplyInfoSuccess();
            }
        }
    }
}