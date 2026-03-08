package com.ido.life.module.user.set.privacyandsecurity;

import android.text.TextUtils;
import com.google.gson.JsonObject;
import com.ido.common.net.BaseEntity;
import com.ido.common.net.BaseEntityNew;
import com.ido.life.base.BasePresenter;
import com.ido.life.bean.SavePrivateSafeSettingBean;
import com.ido.life.ble.PrivacyandSecurityCallBack;
import com.ido.life.data.health.remote.HealthDataManager;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.database.model.PrivateSafeSetting;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/* JADX INFO: loaded from: classes3.dex */
public class PrivacyAndSecurityPresenter extends BasePresenter<IPrivacyAndSecurityView> {
    public void getConfig() {
        PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(RunTimeUtil.getInstance().getUserId());
        if (privateSafeSettingQueryPrivateSafeSetting != null) {
            getView().getConfigSuccess(privateSafeSettingQueryPrivateSafeSetting);
        } else {
            getView().showLoading();
            AccountManager.getPriveteSafeSetting(new AccountManager.OnCommCallback<BaseEntityNew<List<SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem>>>() { // from class: com.ido.life.module.user.set.privacyandsecurity.PrivacyAndSecurityPresenter.1
                @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
                public void onSuccess(BaseEntityNew<List<SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem>> baseEntityNew) {
                    if (PrivacyAndSecurityPresenter.this.getView() == null) {
                        return;
                    }
                    ((IPrivacyAndSecurityView) PrivacyAndSecurityPresenter.this.getView()).dismissLoading();
                    if (baseEntityNew == null || baseEntityNew.getResult() == null || baseEntityNew.getResult().size() <= 0) {
                        ((IPrivacyAndSecurityView) PrivacyAndSecurityPresenter.this.getView()).getConfigFailed();
                        return;
                    }
                    List<SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem> result = baseEntityNew.getResult();
                    PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting2 = GreenDaoUtil.queryPrivateSafeSetting(RunTimeUtil.getInstance().getUserId());
                    if (privateSafeSettingQueryPrivateSafeSetting2 != null) {
                        for (SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem savePrivateSafeSettingBeanItem : result) {
                            if (TextUtils.equals(SavePrivateSafeSettingBean.TYPE_PRIVATE, savePrivateSafeSettingBeanItem.getAttrName())) {
                                privateSafeSettingQueryPrivateSafeSetting2.setSavePrivateData(TextUtils.equals(SavePrivateSafeSettingBean.ON, savePrivateSafeSettingBeanItem.getAttrValue()));
                            } else if (TextUtils.equals(SavePrivateSafeSettingBean.TYPE_HEALTH, savePrivateSafeSettingBeanItem.getAttrName())) {
                                privateSafeSettingQueryPrivateSafeSetting2.setSaveHealthData(TextUtils.equals(SavePrivateSafeSettingBean.ON, savePrivateSafeSettingBeanItem.getAttrValue()));
                            } else if (TextUtils.equals(SavePrivateSafeSettingBean.TYPE_SPORT, savePrivateSafeSettingBeanItem.getAttrName())) {
                                privateSafeSettingQueryPrivateSafeSetting2.setSaveSportData(TextUtils.equals(SavePrivateSafeSettingBean.ON, savePrivateSafeSettingBeanItem.getAttrValue()));
                            }
                        }
                        privateSafeSettingQueryPrivateSafeSetting2.update();
                    } else {
                        privateSafeSettingQueryPrivateSafeSetting2 = new PrivateSafeSetting();
                        for (SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem savePrivateSafeSettingBeanItem2 : result) {
                            if (TextUtils.equals(SavePrivateSafeSettingBean.TYPE_PRIVATE, savePrivateSafeSettingBeanItem2.getAttrName())) {
                                privateSafeSettingQueryPrivateSafeSetting2.setSavePrivateData(TextUtils.equals(SavePrivateSafeSettingBean.ON, savePrivateSafeSettingBeanItem2.getAttrValue()));
                            } else if (TextUtils.equals(SavePrivateSafeSettingBean.TYPE_HEALTH, savePrivateSafeSettingBeanItem2.getAttrName())) {
                                privateSafeSettingQueryPrivateSafeSetting2.setSaveHealthData(TextUtils.equals(SavePrivateSafeSettingBean.ON, savePrivateSafeSettingBeanItem2.getAttrValue()));
                            } else if (TextUtils.equals(SavePrivateSafeSettingBean.TYPE_SPORT, savePrivateSafeSettingBeanItem2.getAttrName())) {
                                privateSafeSettingQueryPrivateSafeSetting2.setSaveSportData(TextUtils.equals(SavePrivateSafeSettingBean.ON, savePrivateSafeSettingBeanItem2.getAttrValue()));
                            }
                        }
                        GreenDaoUtil.addPrivateSafeSetting(privateSafeSettingQueryPrivateSafeSetting2);
                    }
                    ((IPrivacyAndSecurityView) PrivacyAndSecurityPresenter.this.getView()).getConfigSuccess(privateSafeSettingQueryPrivateSafeSetting2);
                }

                @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
                public void onFailed(String str) {
                    ((IPrivacyAndSecurityView) PrivacyAndSecurityPresenter.this.getView()).dismissLoading();
                    ((IPrivacyAndSecurityView) PrivacyAndSecurityPresenter.this.getView()).getConfigFailed();
                }
            });
        }
    }

    public void saveConfig(boolean z, boolean z2, boolean z3) {
        GreenDaoUtil.saveConfig(z, z2, z3, new PrivacyandSecurityCallBack() { // from class: com.ido.life.module.user.set.privacyandsecurity.PrivacyAndSecurityPresenter.2
            @Override // com.ido.life.ble.PrivacyandSecurityCallBack
            public void onSuccess(BaseEntity baseEntity) {
                if (PrivacyAndSecurityPresenter.this.getView() != null) {
                    ((IPrivacyAndSecurityView) PrivacyAndSecurityPresenter.this.getView()).onConfigSuccess();
                }
            }

            @Override // com.ido.life.ble.PrivacyandSecurityCallBack
            public void onFailed(String str) {
                if (PrivacyAndSecurityPresenter.this.getView() != null) {
                    ((IPrivacyAndSecurityView) PrivacyAndSecurityPresenter.this.getView()).getConfigFailed();
                }
            }
        });
    }

    public void clearServerData() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("start", "2019-01-01");
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.add(2, 1);
        jsonObject.addProperty("end", DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD));
        RequestBody requestBodyCreate = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        getView().showLoading();
        HealthDataManager.clearHealthData(requestBodyCreate, new HealthDataManager.OnHealthDataCallback<BaseEntity>() { // from class: com.ido.life.module.user.set.privacyandsecurity.PrivacyAndSecurityPresenter.3
            @Override // com.ido.life.data.health.remote.HealthDataManager.OnHealthDataCallback
            public void onSuccess(BaseEntity baseEntity) {
                PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(RunTimeUtil.getInstance().getUserId());
                if (privateSafeSettingQueryPrivateSafeSetting != null) {
                    privateSafeSettingQueryPrivateSafeSetting.setSaveSportData(false);
                    privateSafeSettingQueryPrivateSafeSetting.setSaveHealthData(false);
                    privateSafeSettingQueryPrivateSafeSetting.update();
                } else {
                    PrivateSafeSetting privateSafeSetting = new PrivateSafeSetting();
                    privateSafeSetting.setSavePrivateData(true);
                    privateSafeSetting.setSaveHealthData(false);
                    privateSafeSetting.setSaveSportData(false);
                    privateSafeSetting.setUserId(RunTimeUtil.getInstance().getUserId());
                    GreenDaoUtil.addPrivateSafeSetting(privateSafeSetting);
                }
                if (PrivacyAndSecurityPresenter.this.getView() != null) {
                    ((IPrivacyAndSecurityView) PrivacyAndSecurityPresenter.this.getView()).dismissLoading();
                    ((IPrivacyAndSecurityView) PrivacyAndSecurityPresenter.this.getView()).deleteServerDataSuccess();
                }
            }

            @Override // com.ido.life.data.health.remote.HealthDataManager.OnHealthDataCallback
            public void onFailed(int i, String str) {
                if (PrivacyAndSecurityPresenter.this.getView() != null) {
                    ((IPrivacyAndSecurityView) PrivacyAndSecurityPresenter.this.getView()).dismissLoading();
                    ((IPrivacyAndSecurityView) PrivacyAndSecurityPresenter.this.getView()).deleteServerDataFailed();
                }
            }
        });
    }
}