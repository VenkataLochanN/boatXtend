package com.ido.life.module.user.userdata.weight;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.FragmentManager;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.Units;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.BaseEntity;
import com.ido.common.net.http.Result;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.data.AuthorizationPreference;
import com.ido.life.data.api.entity.UploadWeightBean;
import com.ido.life.data.health.remote.HealthDataManager;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.database.model.PrivateSafeSetting;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.WeightItemBean;
import com.ido.life.dialog.CloudSyncConfirmDialogFragment;
import com.ido.life.enums.WeightBmiEnum;
import com.ido.life.module.user.userdata.weight.WeightContract;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPUtils;
import com.ido.life.util.UnitUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class WeightPresenter implements WeightContract.Presenter {
    private static final String TAG = "WeightPresenter";
    private WeightContract.View mView;
    private float weightKg = 80.0f;
    private float weightBan = 176.0f;
    private int mWeightType = 1;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public WeightPresenter(WeightContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.userdata.weight.WeightContract.Presenter
    public void initWeight() {
        UserInfo userInfo = AccountRepository.getInstance().getUserInfo();
        if (userInfo != null) {
            float weight = userInfo.getWeight();
            int heightUnit = userInfo.getHeightUnit();
            if (weight <= 0.0f) {
                if (userInfo.getGender() == 1) {
                    this.weightKg = 80.0f;
                    this.weightBan = 176.0f;
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "设置男士默认值80kg/176");
                } else {
                    this.weightKg = 50.0f;
                    this.weightBan = 110.0f;
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "设置女士默认值50kg/110");
                }
                this.mView.setRulerView(heightUnit);
                return;
            }
            if (heightUnit == 1) {
                this.weightKg = weight;
            } else {
                this.weightBan = (int) UnitUtil.getKg2Pound(weight);
            }
            this.mView.setRulerView(heightUnit);
        }
    }

    @Override // com.ido.life.module.user.userdata.weight.WeightContract.Presenter
    public void saveTempWeight(int i) {
        int heightUnit;
        UserInfo userInfo = AccountRepository.getInstance().getUserInfo();
        if (userInfo != null) {
            heightUnit = userInfo.getHeightUnit();
            if (heightUnit == 1) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "公制的体重：saveWeight = nowWeight " + i);
            } else {
                i = (int) UnitUtil.getPound2Kg(i);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "英制的体重：saveWeight = nowWeight " + i);
            }
        } else {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "**********发生异常，体重界面拿到空的用户数据");
            i = 0;
            heightUnit = 1;
        }
        AccountRepository.getInstance().updateWeight(i, heightUnit);
    }

    @Override // com.ido.life.module.user.userdata.weight.WeightContract.Presenter
    public float getWeightKg() {
        return this.weightKg;
    }

    @Override // com.ido.life.module.user.userdata.weight.WeightContract.Presenter
    public int getWeightBan() {
        return (int) this.weightBan;
    }

    @Override // com.ido.life.module.user.userdata.weight.WeightContract.Presenter
    public void getWeight(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mView.changeForwardEnable(true);
        } else {
            this.mView.changeForwardEnable(false);
        }
    }

    @Override // com.ido.life.module.user.userdata.weight.WeightContract.Presenter
    public void checkWeight(Context context, float f2) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "卡尺上读到的weight = " + f2);
        UserInfo userInfo = AccountRepository.getInstance().getUserInfo();
        if (userInfo != null) {
            int heightUnit = userInfo.getHeightUnit();
            AccountRepository.getInstance().updateWeight(f2, heightUnit);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "本地保存的weight = " + f2 + ",heightUnit = " + heightUnit);
            String token = AuthorizationPreference.getToken(context);
            if (heightUnit != 1) {
                f2 = Math.round(UnitUtil.getPound2Kg(f2));
            }
            if (!TextUtils.isEmpty(token) && userInfo.getUserId() != -1) {
                if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
                    saveWeightItemAndNext(f2, userInfo.getHeightCm());
                    return;
                }
                PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(userInfo.getUserId());
                if (privateSafeSettingQueryPrivateSafeSetting != null && privateSafeSettingQueryPrivateSafeSetting.getSavePrivateData()) {
                    this.mView.showLoading();
                    userInfo.setWeight(f2);
                    userInfo.setWeightUnit(heightUnit);
                    userInfo.setUploadSuccess(false);
                    userInfo.update();
                    updateUserInfoToServer(f2, heightUnit, token, context);
                    updateUserInfoFirstLogin(f2, userInfo.getHeightCm());
                    return;
                }
                this.mView.setForward();
                return;
            }
            saveWeightItemAndNext(f2, userInfo.getHeightCm());
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "**********发生异常，体重界面拿到空的用户数据");
    }

    private void saveWeightItemAndNext(float f2, float f3) {
        WeightItemBean weightItemBean = new WeightItemBean();
        weightItemBean.setTotalWeight(f2);
        weightItemBean.setUserId(RunTimeUtil.getInstance().getUserId());
        weightItemBean.setBmi(WeightBmiEnum.caluteBmi(f2, f3));
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        weightItemBean.setDate(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD));
        weightItemBean.setTimeStamp(calendar.getTimeInMillis());
        GreenDaoUtil.addWeight(weightItemBean);
        this.mView.setForward();
    }

    @Override // com.ido.life.module.user.userdata.weight.WeightContract.Presenter
    public void toCloudSyncDialog(FragmentManager fragmentManager) {
        if (RunTimeUtil.getInstance().getUserId() == -1) {
            this.mView.toSetGoal();
            return;
        }
        UserInfo userInfoQueryLatestUserInfo = GreenDaoUtil.queryLatestUserInfo();
        String str = (String) SPUtils.get(Constants.LAST_ACCOUNT_KEY, "");
        if (userInfoQueryLatestUserInfo != null) {
            if (str.equals(userInfoQueryLatestUserInfo.getEmail())) {
                if (((Boolean) SPUtils.get(Constants.FIRST_CLOUD_SYNC, true)).booleanValue()) {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "mUserInfo != null,当前邮箱账号与上一次邮箱账号一样，是第一次注册，弹框 ");
                    toastCloudDialog(fragmentManager);
                    return;
                } else {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "mUserInfo != null,当前邮箱账号与上一次邮箱账号一样，不是第一次注册，不再弹框 ");
                    this.mView.toSetGoal();
                    return;
                }
            }
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "mUserInfo != null,当前邮箱账号与上一次邮箱账号不一样，弹框 ");
            toastCloudDialog(fragmentManager);
            return;
        }
        if (((Boolean) SPUtils.get(Constants.FIRST_CLOUD_SYNC, true)).booleanValue()) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "mUserInfo = null,当前邮箱账号与上一次邮箱账号一样，是第一次注册，弹框 ");
            toastCloudDialog(fragmentManager);
        } else {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "mUserInfo = null,当前邮箱账号与上一次邮箱账号一样，不是第一次注册，不再弹框 ");
            this.mView.toSetGoal();
        }
    }

    private void toastCloudDialog(FragmentManager fragmentManager) {
        final CloudSyncConfirmDialogFragment cloudSyncConfirmDialogFragmentNewInstance = CloudSyncConfirmDialogFragment.newInstance(LanguageUtil.getLanguageText(R.string.register_cloud_sync_title), LanguageUtil.getLanguageText(R.string.register_cloud_sync_context), LanguageUtil.getLanguageText(R.string.login_agree_continue), LanguageUtil.getLanguageText(R.string.register_cloud_sync_disallow), true);
        cloudSyncConfirmDialogFragmentNewInstance.show(fragmentManager);
        cloudSyncConfirmDialogFragmentNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.user.userdata.weight.WeightPresenter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                cloudSyncConfirmDialogFragmentNewInstance.dismissAllowingStateLoss();
                SPUtils.put(Constants.FIRST_CLOUD_SYNC, false);
                GreenDaoUtil.saveConfig(false, true, true, null);
                WeightPresenter.this.mView.toSetGoal();
            }
        });
        cloudSyncConfirmDialogFragmentNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.userdata.weight.WeightPresenter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                cloudSyncConfirmDialogFragmentNewInstance.dismissAllowingStateLoss();
                SPUtils.put(Constants.FIRST_CLOUD_SYNC, false);
                GreenDaoUtil.saveConfig(true, true, true, null);
                WeightPresenter.this.mView.toSetGoal();
            }
        });
    }

    private void updateUserInfoToServer(float f2, int i, String str, Context context) {
        int iInch2Cm;
        final UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "注册填写个人资料完成后体重界面点下一步，从本地数据库拿到的userinfo -- " + userInfoQueryUserInfo.toString());
        if (userInfoQueryUserInfo == null || TextUtils.isEmpty(str)) {
            return;
        }
        if (userInfoQueryUserInfo.getHeightUnit() == 1) {
            iInch2Cm = Math.round(userInfoQueryUserInfo.getHeight());
        } else {
            iInch2Cm = UnitUtil.inch2Cm(userInfoQueryUserInfo.getHeight());
        }
        AccountRepository.getInstance().updateUserInfoFirstLogin(userInfoQueryUserInfo.getAvatarUrl(), userInfoQueryUserInfo.getDisplayName(), userInfoQueryUserInfo.getGender(), iInch2Cm < 50 ? 50 : iInch2Cm > 250 ? 250 : iInch2Cm, 1, f2, 1, userInfoQueryUserInfo.getBirthday(), new OnResultCallback() { // from class: com.ido.life.module.user.userdata.weight.WeightPresenter.3
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result result) {
                if (result != null) {
                    UserInfo userInfo = (UserInfo) result.getData();
                    userInfoQueryUserInfo.setUserId(userInfo.getUserId());
                    userInfoQueryUserInfo.setUploadSuccess(true);
                    if (userInfoQueryUserInfo.getHeightUnit() == 2) {
                        userInfoQueryUserInfo.setHeight(UnitUtil.cm2Inch(userInfo.getHeightInt()));
                    }
                    if (userInfoQueryUserInfo.getWeightUnit() == 2) {
                        userInfoQueryUserInfo.setWeight(UnitUtil.getKg2Pound(userInfo.getWeightInt()));
                    }
                    userInfoQueryUserInfo.update();
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), WeightPresenter.TAG, "注册填写个人资料完成后第一次上传服务器返回成功，更新本地数据库的userinfo -- " + userInfoQueryUserInfo.toString());
                    RunTimeUtil.getInstance().setUserId(userInfo.getUserId());
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), WeightPresenter.TAG, "updateUserInfoFirstLogin -- onSuccess" + ((UserInfo) result.getData()).toString());
                }
                Units units = LocalDataManager.getUnits();
                if (units == null) {
                    units = new Units();
                }
                if (userInfoQueryUserInfo.getHeightUnit() == 1) {
                    units.dist = 1;
                    units.weight = 1;
                } else {
                    units.dist = 2;
                    units.weight = 2;
                }
                if (BLEManager.isConnected()) {
                    BLEManager.setUnitPending(units);
                    BLEManager.setUnit(units);
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), WeightPresenter.TAG, "BLEManager.isConnected()--" + units.toString());
                } else {
                    BLEManager.setUnitPending(units);
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), WeightPresenter.TAG, "else--" + units.toString());
                }
                WeightPresenter.this.mView.setForward();
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String str2) {
                WeightPresenter.this.mView.hideLoading();
                NormalToast.showToast(str2);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), WeightPresenter.TAG, "updateUserInfoFirstLogin -- onFailed:" + str2);
            }
        });
    }

    private void updateUserInfoFirstLogin(float f2, float f3) {
        final WeightItemBean weightItemBean = new WeightItemBean();
        weightItemBean.setTotalWeight(f2);
        weightItemBean.setUserId(RunTimeUtil.getInstance().getUserId());
        weightItemBean.setBmi(WeightBmiEnum.caluteBmi(f2, f3));
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        weightItemBean.setDate(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD));
        weightItemBean.setTimeStamp(calendar.getTimeInMillis());
        GreenDaoUtil.addWeight(weightItemBean);
        if (RunTimeUtil.getInstance().enableUploadPrivateData()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(weightItemBean);
            HealthDataManager.uploadWeight(new UploadWeightBean(arrayList), new HealthDataManager.OnHealthDataCallback<BaseEntity>() { // from class: com.ido.life.module.user.userdata.weight.WeightPresenter.4
                @Override // com.ido.life.data.health.remote.HealthDataManager.OnHealthDataCallback
                public void onSuccess(BaseEntity baseEntity) {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), WeightPresenter.TAG, "uploadWeight 上传体重修改记录到服务器成功");
                    WeightItemBean weightItemBeanQueryWeightByDate = GreenDaoUtil.queryWeightByDate(RunTimeUtil.getInstance().getUserId(), weightItemBean.getDate());
                    if (weightItemBeanQueryWeightByDate != null) {
                        weightItemBeanQueryWeightByDate.setUploadSuccess(true);
                        weightItemBeanQueryWeightByDate.update();
                    }
                }

                @Override // com.ido.life.data.health.remote.HealthDataManager.OnHealthDataCallback
                public void onFailed(int i, String str) {
                    WeightPresenter.this.mView.hideLoading();
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), WeightPresenter.TAG, "uploadWeight 上传体重修改纪录到服务器失败");
                }
            });
        }
    }
}