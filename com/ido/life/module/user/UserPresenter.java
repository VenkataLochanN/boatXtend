package com.ido.life.module.user;

import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.firmware.log.flash.ICollectFlashLogListener;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.base.BasePresenter;
import com.ido.life.data.api.entity.AppInfoEntity;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserMedalInfo;
import com.ido.life.enums.UserModelEnum;
import com.ido.life.module.user.UserFragment;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class UserPresenter extends BasePresenter<IUserView> {
    private static final String TAG = UserPresenter.class.getSimpleName();

    public void getUserInfo() {
        if (isLogin()) {
            getView().showLoginPage();
        } else {
            getView().showUnLoginPage();
        }
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if (userInfoQueryUserInfo != null) {
            getView().setAvatarUrl(userInfoQueryUserInfo.getAvatarUrl());
            if (TextUtils.isEmpty(userInfoQueryUserInfo.getDisplayName())) {
                getView().setUserName(LanguageUtil.getLanguageText(R.string.home_user_setname));
            } else {
                getView().setUserName(userInfoQueryUserInfo.getDisplayName());
            }
            getView().setEmailAddress(userInfoQueryUserInfo.getEmail());
        } else {
            getView().setAvatarUrl(null);
            getView().setUserName("");
            getView().setEmailAddress("");
        }
        getUserMedal();
    }

    public boolean isLogin() {
        return RunTimeUtil.getInstance().hasLogin();
    }

    public void getUserMedal() {
        ArrayList arrayList = new ArrayList();
        List<UserMedalInfo> listQueryAllUserMedalInfo = GreenDaoUtil.queryAllUserMedalInfo(RunTimeUtil.getInstance().getUserId());
        List<UserModelEnum> allUserModel = UserModelEnum.getAllUserModel();
        if (listQueryAllUserMedalInfo != null && listQueryAllUserMedalInfo.size() > 0) {
            Iterator<UserMedalInfo> it = listQueryAllUserMedalInfo.iterator();
            while (it.hasNext()) {
                UserModelEnum userModelEnumById = UserModelEnum.getUserModelEnumById(it.next().getMedalId());
                arrayList.add(new UserFragment.UserModelnfo(userModelEnumById, true));
                allUserModel.remove(userModelEnumById);
            }
        }
        if (arrayList.size() < 5) {
            int size = 5 - arrayList.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(new UserFragment.UserModelnfo(allUserModel.get(i), false));
            }
        }
        if (getView() != null) {
            getView().onGetUserMedalSuccess(arrayList);
        }
    }

    public void requestInstruction() {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        CommonLogUtil.printAndSave("requestInstructionInfo deviceInfo = " + currentDeviceInfo);
        if (currentDeviceInfo == null) {
            if (getView() != null) {
                getView().onGetDeviceInstruction("");
                return;
            }
            return;
        }
        DeviceManager.requestInstructionInfo(currentDeviceInfo.mDeviceId, new DeviceManager.OnDeviceCallback<String>() { // from class: com.ido.life.module.user.UserPresenter.1
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(String str) {
                CommonLogUtil.printAndSave("requestInstructionInfo onSuccess，url = " + str);
                if (UserPresenter.this.getView() != null) {
                    ((IUserView) UserPresenter.this.getView()).onGetDeviceInstruction(str);
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                CommonLogUtil.printAndSave("requestInstructionInfo onFailed : " + str);
                if (UserPresenter.this.getView() != null) {
                    ((IUserView) UserPresenter.this.getView()).onGetDeviceInstruction("");
                }
            }
        });
    }

    public void checkVersion() {
        AccountManager.requestAppVersionInfo(new AccountManager.OnCommCallback<AppInfoEntity.AppInfo>() { // from class: com.ido.life.module.user.UserPresenter.2
            @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
            public void onSuccess(AppInfoEntity.AppInfo appInfo) {
                if (UserPresenter.this.getView() == null || appInfo == null) {
                    return;
                }
                ((IUserView) UserPresenter.this.getView()).onGetNewVersionSuccess(appInfo);
            }

            @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
            public void onFailed(String str) {
                if (UserPresenter.this.getView() != null) {
                    ((IUserView) UserPresenter.this.getView()).onGetNewVersionFailed(str);
                }
            }
        });
    }

    public void getUnReadMessageCount() {
        if (getView() != null) {
            getView().onUnReadMessageCount(GreenDaoUtil.queryUnReadReportCount(RunTimeUtil.getInstance().getUserId(), RunTimeUtil.getInstance().getWeekStart()));
        }
    }

    public void collectionFlashLog() {
        if (BLEManager.isBind() && BLEManager.isConnected()) {
            CommonLogUtil.printAndSave("设备已经连接，开始获取设备的Flash日志。");
            File file = new File(LogPathImpl.getInstance().getFlashPath());
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(LogPathImpl.getInstance().getFlashPath() + "flash.log");
            if (file2.exists()) {
                file2.delete();
            }
            try {
                if (file2.createNewFile()) {
                    BLEManager.collectDeviceFlashLog(file2.getAbsolutePath(), 60, new ICollectFlashLogListener() { // from class: com.ido.life.module.user.UserPresenter.3
                        @Override // com.ido.ble.firmware.log.flash.ICollectFlashLogListener
                        public void onStart() {
                            CommonLogUtil.printAndSave("开始搜集Flash日志");
                        }

                        @Override // com.ido.ble.firmware.log.flash.ICollectFlashLogListener
                        public void onFinish() {
                            CommonLogUtil.printAndSave("Flash日志收集成功.");
                            if (UserPresenter.this.isAttachView()) {
                                ((IUserView) UserPresenter.this.getView()).flashLogCollectionSuccess();
                            }
                        }
                    });
                    return;
                }
                return;
            } catch (IOException e2) {
                e2.printStackTrace();
                CommonLogUtil.printAndSave("收集Flash日志创建文件失败,error=" + e2.getMessage());
                if (isAttachView()) {
                    getView().flashLogCollectionFailed();
                    return;
                }
                return;
            }
        }
        CommonLogUtil.printAndSave("设备未连接，不搜集Flash日志，开始压缩本地日志。");
        if (isAttachView()) {
            getView().flashLogCollectionFailed();
        }
    }
}