package com.ido.life.data.listener;

import android.content.Intent;
import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.BaseEntity;
import com.ido.common.net.ErrorCode;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.VeryFitApp;
import com.ido.life.constants.Constants;
import com.ido.life.module.user.login.PreLoginAndRegisterActivity;
import com.ido.life.syncdownload.DataDownLoadService;
import com.ido.life.util.eventbus.EventBusHelper;
import retrofit2.Call;
import retrofit2.Response;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ApiCallback<T extends BaseEntity> implements retrofit2.Callback<T>, NetCallback<T> {
    private static final String TAG = "ApiCallback";
    private int mCode;

    public static boolean checkTokenError(int i) {
        return i == 201010 || i == 201012 || i == 201000 || i == 201013 || i == 200109;
    }

    @Override // retrofit2.Callback
    public void onResponse(Call<T> call, Response<T> response) {
        T tBody = response.body();
        String string = response.toString();
        if (!TextUtils.isEmpty(string)) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "onResponse responseStr = " + string);
        } else {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "onResponse responseStr 为空");
        }
        int i = -1;
        if (tBody != null) {
            i = tBody.status;
            if (tBody.message != null) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "response.body():BaseEntity -- status " + tBody.status + ",message" + tBody.message);
            }
        }
        this.mCode = i;
        if (i == 200 || i == 100030 || i == 501001 || i == 10404) {
            onSuccess(response.body());
            return;
        }
        if (checkTokenError(i)) {
            VeryFitApp.isFirstTokenInvalid.incrementAndGet();
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "token失效的次数isFirstTokenInvalid = " + VeryFitApp.isFirstTokenInvalid.get());
            if (IdoApp.getAppContext() == null || VeryFitApp.isFirstTokenInvalid.get() != 1) {
                return;
            }
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "checkTokenError:失败的错误码 " + i);
            DataDownLoadService.stop();
            Intent intent = new Intent(IdoApp.getAppContext(), (Class<?>) PreLoginAndRegisterActivity.class);
            intent.addFlags(32768);
            intent.addFlags(268435456);
            intent.putExtra(Constants.LOGIN_OTHER_PHONE, true);
            IdoApp.getAppContext().startActivity(intent);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "token失效，跳转到登录注册页 ");
            VeryFitApp.getApp().quitClearCacheThread(new VeryFitApp.ClearCacheCallBack() { // from class: com.ido.life.data.listener.ApiCallback.1
                @Override // com.ido.life.VeryFitApp.ClearCacheCallBack
                public void clearSuccess() {
                    EventBusHelper.post(305);
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), ApiCallback.TAG, "发送EVENT_TYPE_OTHER_LOGIN_FINISH 事件1");
                }
            });
            return;
        }
        onError(null, i, fromErrCodeToMessage(i));
    }

    @Override // retrofit2.Callback
    public void onFailure(Call<T> call, Throwable th) {
        if (th != null) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "onFailure t = " + th.toString());
        } else {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "onFailure t 为空");
        }
        onError(th, -1, fromErrCodeToMessage(-1));
    }

    public int getCode() {
        return this.mCode;
    }

    public static String fromErrCodeToMessage(int i) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "fromErrCodeToMessage code: " + i);
        switch (i) {
            case 400:
                return LanguageUtil.getLanguageText(R.string.error_request_paramater);
            case 403:
                return LanguageUtil.getLanguageText(R.string.error_request_paramater);
            case 500:
                return LanguageUtil.getLanguageText(R.string.error_request_paramater);
            case 502:
                return LanguageUtil.getLanguageText(R.string.error_request_paramater);
            case 601:
                return LanguageUtil.getLanguageText(R.string.error_date_change);
            case ErrorCode.ACCOUNT_ALREADY_EXISTS /* 100040 */:
            case 200101:
                return LanguageUtil.getLanguageText(R.string.me_error_account_exist);
            case ErrorCode.REGISTER_SYSTEM_NOT_EXIST /* 100060 */:
            case ErrorCode.GET_REGISTER_SYSTEM_ACCOUNT_FAILED /* 100070 */:
            case ErrorCode.REGISTER_SYSTEM_ACCOUNT_WRONG /* 100080 */:
            case ErrorCode.USER_PASSWORD_WRONG /* 201011 */:
                return LanguageUtil.getLanguageText(R.string.login_not_register_wrong);
            case ErrorCode.USER_NOT_EXISTS /* 200103 */:
                return LanguageUtil.getLanguageText(R.string.login_account_not_register);
            case ErrorCode.USER_LOGIN_ERR_ACCPSW /* 200104 */:
                return LanguageUtil.getLanguageText(R.string.login_account_password_wrong);
            case ErrorCode.USER_LOGIN_NOT_MODIFY_ACCPSW /* 200105 */:
                return LanguageUtil.getLanguageText(R.string.me_error_login_not_modify_accpsw);
            case ErrorCode.USER_INFO_NOT_EXISTS /* 200106 */:
                return LanguageUtil.getLanguageText(R.string.me_error_user_info_not_exist);
            case ErrorCode.ACCOUNT_TOKEN_NOT_SAME /* 200109 */:
            case ErrorCode.USER_LOGIN_OTHER_PHONE /* 201000 */:
            case ErrorCode.INVALIDATE_TOKEN /* 201010 */:
            case ErrorCode.TOKEN_EXPIRED /* 201012 */:
            case ErrorCode.NO_ACCESS_VISIT_RESOURCE /* 201013 */:
                return LanguageUtil.getLanguageText(R.string.login_other_phone_tip);
            case ErrorCode.APP_VERSION_NOT_FIND /* 200305 */:
                return LanguageUtil.getLanguageText(R.string.error_app_version_not_find);
            case ErrorCode.APP_INFO_NOT_FIND /* 200306 */:
                return LanguageUtil.getLanguageText(R.string.error_app_info_not_find);
            case ErrorCode.SHORT_MESSAGE_ERROR_REPEAT /* 200401 */:
                return LanguageUtil.getLanguageText(R.string.me_error_short_message_repeat);
            case ErrorCode.USER_CODE_ERROR /* 200402 */:
            case ErrorCode.SHORT_MESSAGE_NOT_EXISTS /* 200404 */:
                return LanguageUtil.getLanguageText(R.string.me_code_error_past);
            case ErrorCode.USER_CODE_NOT_SUPPORT /* 200403 */:
                return LanguageUtil.getLanguageText(R.string.me_password_email_wrong);
            case ErrorCode.VCODE_WRONG_OVER /* 200405 */:
            case ErrorCode.VCODE_WRONG_OVER_USERED /* 200406 */:
                return LanguageUtil.getLanguageText(R.string.me_code_error_over);
            case ErrorCode.USER_CODE_ACCOUNT_NOT_EMPTY /* 200407 */:
                return LanguageUtil.getLanguageText(R.string.me_error_user_code_not_empty);
            case ErrorCode.WHITE_LIST_NOT_CONFIGURE /* 501011 */:
                return LanguageUtil.getLanguageText(R.string.error_white_list_not_configure);
            case ErrorCode.WHITE_LIST_NOT_EXIST /* 501012 */:
                return LanguageUtil.getLanguageText(R.string.error_white_list_not_exist);
            case ErrorCode.OTA_VERSION_NOT_EXIST /* 502001 */:
                return LanguageUtil.getLanguageText(R.string.error_ota_version_not_exist);
            case ErrorCode.DEVICE_NOT_EXIST /* 502003 */:
                return LanguageUtil.getLanguageText(R.string.error_device_not_exist);
            case ErrorCode.DEVICE_IS_DISMOUNT /* 502004 */:
                return LanguageUtil.getLanguageText(R.string.error_device_is_dismount);
            case ErrorCode.DEVICE_UNBIND_ERROR /* 503001 */:
                return LanguageUtil.getLanguageText(R.string.error_device_device_unbind);
            default:
                return LanguageUtil.getLanguageText(R.string.me_request_error);
        }
    }
}