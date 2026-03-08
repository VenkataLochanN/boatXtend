package com.ido.ntf.phone;

import com.ido.ntf.Utils.CommUtils;
import com.ido.ntf.Utils.NotificationUtil;
import com.ido.ntf.callback.ICallPhoneInfoBack;
import com.ido.ntf.log.LogHandle;

/* JADX INFO: loaded from: classes3.dex */
public class PhoneStateHandle {
    private static final String TAG = " NotificationSDK  PhoneStateHandle";
    public ICallPhoneInfoBack phoneStateBack;

    public ICallPhoneInfoBack getPhoneStateBack() {
        return this.phoneStateBack;
    }

    private static class SingletonHolder {
        private static final PhoneStateHandle INSTANCE = new PhoneStateHandle();

        private SingletonHolder() {
        }
    }

    private PhoneStateHandle() {
    }

    public static final PhoneStateHandle getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void initPhoneStateListener(int i, String str, ICallPhoneInfoBack iCallPhoneInfoBack) {
        this.phoneStateBack = iCallPhoneInfoBack;
        if (i == 0) {
            NotificationUtil.CALL_STATE_IDLE();
            return;
        }
        if (i != 1) {
            if (i != 2) {
                return;
            }
            NotificationUtil.CALL_STATE_OFFHOOK();
            return;
        }
        String contactNameFromPhoneBook = CommUtils.getContactNameFromPhoneBook(CommUtils.mContext, str);
        LogHandle.getInstance().printLog(" NotificationSDK  PhoneStateHandle  contactName---" + contactNameFromPhoneBook + "  incomingNumber---" + str);
        NotificationUtil.CALL_STATE_RINGING(str, contactNameFromPhoneBook);
    }
}