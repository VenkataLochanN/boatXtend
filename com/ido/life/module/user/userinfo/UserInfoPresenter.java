package com.ido.life.module.user.userinfo;

import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.http.Result;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.StringUtil;
import com.ido.common.utils.TimeUtil;
import com.ido.life.base.BasePresenter;
import com.ido.life.constants.Constants;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.database.model.PrivateSafeSetting;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.WeightItemBean;
import com.ido.life.enums.WeightBmiEnum;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPUtils;
import com.ido.life.util.UnitUtil;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class UserInfoPresenter extends BasePresenter<IUserInfoView> {
    private static final String TAG = "UserInfoPresenter";
    private String mNickName;
    private float mOrignalWeight = 0.0f;
    private UserInfo mUserInfo;

    public void initUserData() {
        String[] strArrSplit;
        this.mUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if (this.mUserInfo == null) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "initUserData: 我的信息界面数据库拿到的userInfo --" + this.mUserInfo.toString());
        getView().setGender(this.mUserInfo.getGender());
        if (!TextUtils.isEmpty(this.mUserInfo.getAvatarUrl())) {
            getView().setAvatarUrl(this.mUserInfo.getAvatarUrl());
        }
        if (!TextUtils.isEmpty(this.mUserInfo.getDisplayName())) {
            this.mNickName = this.mUserInfo.getDisplayName();
            getView().setUserName(this.mUserInfo.getDisplayName());
        }
        if (!TextUtils.isEmpty(this.mUserInfo.getEmail())) {
            getView().setEmail(this.mUserInfo.getEmail());
        }
        if (!TextUtils.isEmpty(this.mUserInfo.getBirthday())) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "initUserData: 我的信息界面userInfo 的生日 -- " + this.mUserInfo.getBirthday());
            getView().setBirthday(this.mUserInfo.getBirthday().replaceAll("-", "/"));
        } else {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "initUserData: 我的信息界面userInfo 的生日为空 -- ");
            getView().setBirthday(TimeUtil.getCurrentDayDate().replaceAll("-", "/"));
        }
        if (!TextUtils.isEmpty(this.mUserInfo.getAreaCode())) {
            getView().setArea(this.mUserInfo.getAreaCode());
        } else if (!TextUtils.isEmpty(this.mUserInfo.getCountry()) && (strArrSplit = this.mUserInfo.getCountry().split("_")) != null) {
            getView().setArea(strArrSplit[strArrSplit.length - 1]);
        }
        int unitSet = RunTimeUtil.getInstance().getUnitSet();
        if (unitSet == 1) {
            if (this.mUserInfo.getHeightUnit() == unitSet) {
                if (this.mUserInfo.getHeightInt() > 0) {
                    getView().setHeight(this.mUserInfo.getHeightInt() + LanguageUtil.getLanguageText(R.string.height_km_short));
                } else {
                    getView().setHeight(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + LanguageUtil.getLanguageText(R.string.height_km_short));
                }
            } else if (this.mUserInfo.getHeight() > 0.0f) {
                int iMin = Math.min(UnitUtil.inch2Cm(this.mUserInfo.getHeight()), 250);
                getView().setHeight(iMin + LanguageUtil.getLanguageText(R.string.height_km_short));
            } else {
                getView().setHeight(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + LanguageUtil.getLanguageText(R.string.height_km_short));
            }
            if (this.mUserInfo.getWeightUnit() == unitSet) {
                this.mOrignalWeight = this.mUserInfo.getWeight();
                if (this.mUserInfo.getWeight() > 0.0f) {
                    getView().setWeight(String.format("%.1f%s", Float.valueOf(this.mOrignalWeight), LanguageUtil.getLanguageText(R.string.weight_kg_unit_short)));
                    return;
                } else {
                    getView().setWeight(String.format("%.1f%s", Float.valueOf(0.0f), LanguageUtil.getLanguageText(R.string.weight_kg_unit_short)));
                    return;
                }
            }
            this.mOrignalWeight = Math.min(UnitUtil.getPound2Kg(this.mUserInfo.getWeight()), 250.0f);
            if (this.mUserInfo.getWeight() > 0.0f) {
                getView().setWeight(String.format("%.1f%s", Float.valueOf(this.mOrignalWeight), LanguageUtil.getLanguageText(R.string.weight_kg_unit_short)));
                return;
            } else {
                getView().setWeight(String.format("%.1f%s", Float.valueOf(0.0f), LanguageUtil.getLanguageText(R.string.weight_kg_unit_short)));
                return;
            }
        }
        if (unitSet == this.mUserInfo.getHeightUnit()) {
            if (this.mUserInfo.getHeight() > 0.0f) {
                getView().setHeight(String.format("%.2f", Float.valueOf(this.mUserInfo.getHeight())) + LanguageUtil.getLanguageText(R.string.height_foot_short));
            } else {
                getView().setHeight("0.00" + LanguageUtil.getLanguageText(R.string.height_foot_short));
            }
        } else if (this.mUserInfo.getHeightInt() > 0) {
            float fMin = Math.min(UnitUtil.cm2Inch(this.mUserInfo.getHeightInt()), 8.2f);
            getView().setHeight(String.format("%.2f", Float.valueOf(fMin)) + LanguageUtil.getLanguageText(R.string.height_foot_short));
        } else {
            getView().setHeight(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + LanguageUtil.getLanguageText(R.string.height_foot_short));
        }
        if (unitSet == this.mUserInfo.getWeightUnit()) {
            this.mOrignalWeight = this.mUserInfo.getWeight();
            if (this.mUserInfo.getWeightInt() > 0) {
                getView().setWeight(String.format("%d%s", Integer.valueOf(Math.round(this.mOrignalWeight)), LanguageUtil.getLanguageText(R.string.weight_pound_unit_short)));
                return;
            } else {
                getView().setWeight(String.format("%d%s", 0, LanguageUtil.getLanguageText(R.string.weight_pound_unit_short)));
                return;
            }
        }
        this.mOrignalWeight = Math.min(Math.round(UnitUtil.getKg2Pound(this.mUserInfo.getWeight())), 551);
        if (this.mUserInfo.getWeightInt() > 0) {
            getView().setWeight(String.format("%d%s", Integer.valueOf(Math.round(this.mOrignalWeight)), LanguageUtil.getLanguageText(R.string.weight_pound_unit_short)));
        } else {
            getView().setWeight(String.format("%d%s", 0, LanguageUtil.getLanguageText(R.string.weight_pound_unit_short)));
        }
    }

    public void setDisplayName(String str) {
        this.mNickName = str;
        if (TextUtils.isEmpty(str)) {
            getView().onEmptyName();
            return;
        }
        UserInfo userInfo = this.mUserInfo;
        if (userInfo != null) {
            userInfo.setDisplayName(str);
            this.mUserInfo.setUploadSuccess(false);
            this.mUserInfo.update();
        }
    }

    public void doChooseSex(int i) {
        if (i == 1) {
            updateGender(1);
        } else {
            updateGender(2);
        }
    }

    public void setBirthday(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "setBirthday: 拿到的生日 +++ " + str);
        String strReplaceAll = str.replaceAll("/", "-");
        if (TextUtils.isEmpty(strReplaceAll)) {
            return;
        }
        UserInfo userInfo = this.mUserInfo;
        if (userInfo != null) {
            userInfo.setBirthday(strReplaceAll);
            this.mUserInfo.setUploadSuccess(false);
            this.mUserInfo.update();
        }
        String strReplaceAll2 = strReplaceAll.replaceAll("-", "/");
        getView().setBirthday(strReplaceAll2);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "setBirthday: 拿到的生日 +++ " + strReplaceAll2);
    }

    public void showCurrentBirthday(String str) {
        String[] strArrSplitDate;
        CommonLogUtil.d(TAG, "showCurrentBirthday: " + str);
        int[] iArr = new int[3];
        int[] iArr2 = new int[3];
        int[] iArr3 = new int[3];
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        if (LanguageUtil.hasLowIntervalCountry((String) SPUtils.get(Constants.CHOOSE_COUNTRY_CODE, ""))) {
            calendar.add(1, -16);
            strArrSplitDate = TimeUtil.splitDate(calendar);
            calendar.add(1, -84);
        } else {
            strArrSplitDate = TimeUtil.splitDate(calendar);
            calendar.add(1, -100);
        }
        iArr2[0] = Integer.parseInt(strArrSplitDate[0]);
        iArr2[1] = Integer.parseInt(strArrSplitDate[1]);
        iArr2[2] = Integer.parseInt(strArrSplitDate[2]);
        String[] strArrSplitDate2 = TimeUtil.splitDate(calendar);
        iArr[0] = Integer.parseInt(strArrSplitDate2[0]);
        iArr[1] = Integer.parseInt(strArrSplitDate2[1]);
        iArr[2] = Integer.parseInt(strArrSplitDate2[2]);
        String[] strArrSplit = str.split("/");
        iArr3[0] = Integer.parseInt(strArrSplit[0]);
        iArr3[1] = Integer.parseInt(strArrSplit[1]);
        iArr3[2] = Integer.parseInt(strArrSplit[2]);
        getView().setBirthday(iArr, iArr2, iArr3);
    }

    private void updateGender(int i) {
        UserInfo userInfo = this.mUserInfo;
        if (userInfo != null) {
            userInfo.setGender(i);
            this.mUserInfo.setUploadSuccess(false);
            this.mUserInfo.update();
        }
        getView().setGender(i);
    }

    public void setHeight(String str) {
        String string;
        String stringNoEmpty = StringUtil.getStringNoEmpty(str);
        int i = 1;
        if (stringNoEmpty.contains(ResourceUtil.getString(R.string.height_km_short))) {
            string = ResourceUtil.getString(R.string.height_km_short);
        } else if (stringNoEmpty.contains(ResourceUtil.getString(R.string.height_foot_short))) {
            string = ResourceUtil.getString(R.string.height_foot_short);
            i = 2;
        } else {
            string = "";
        }
        getView().showPickDialog(i, stringNoEmpty.substring(0, stringNoEmpty.indexOf(string)));
    }

    public void setWeight(String str) {
        String languageText;
        String stringNoEmpty = StringUtil.getStringNoEmpty(str);
        int i = 3;
        if (stringNoEmpty.contains(LanguageUtil.getLanguageText(R.string.weight_kg_unit_short))) {
            languageText = LanguageUtil.getLanguageText(R.string.weight_kg_unit_short);
        } else if (stringNoEmpty.contains(LanguageUtil.getLanguageText(R.string.weight_pound_unit_short))) {
            i = 4;
            languageText = LanguageUtil.getLanguageText(R.string.weight_pound_unit_short);
        } else {
            languageText = "";
        }
        getView().showPickDialog(i, stringNoEmpty.substring(0, stringNoEmpty.indexOf(languageText)));
    }

    public void updateProfile(String str) {
        UserInfo userInfo = this.mUserInfo;
        if (userInfo == null) {
            return;
        }
        if (userInfo.getUserId() != -1) {
            if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
                getView().showLoading();
                AccountRepository.getInstance().updateFile(str, new OnResultCallback() { // from class: com.ido.life.module.user.userinfo.UserInfoPresenter.1
                    @Override // com.ido.life.data.listener.OnResultCallback
                    public void onSuccess(Result result) {
                        if (UserInfoPresenter.this.getView() == null) {
                            return;
                        }
                        ((IUserInfoView) UserInfoPresenter.this.getView()).dismissLoading();
                        ((IUserInfoView) UserInfoPresenter.this.getView()).setAvatarUrlSuccess(result.getData().toString());
                        if (UserInfoPresenter.this.mUserInfo != null) {
                            UserInfoPresenter.this.mUserInfo.setAvatarUrl(result.getData().toString());
                            UserInfoPresenter.this.mUserInfo.setUploadSuccess(false);
                            UserInfoPresenter.this.mUserInfo.setServerImageUrl(true);
                            UserInfoPresenter.this.mUserInfo.update();
                        }
                    }

                    @Override // com.ido.life.data.listener.OnResultCallback
                    public void onFailed(String str2) {
                        if (UserInfoPresenter.this.getView() == null) {
                            return;
                        }
                        ((IUserInfoView) UserInfoPresenter.this.getView()).dismissLoading();
                        ((IUserInfoView) UserInfoPresenter.this.getView()).setAvatarUrlFailed(str2);
                        CommonLogUtil.d(UserInfoPresenter.TAG, "onFailed: " + str2);
                    }
                });
                return;
            }
            UserInfo userInfo2 = this.mUserInfo;
            if (userInfo2 != null) {
                userInfo2.setAvatarUrl(str);
                this.mUserInfo.setUploadSuccess(false);
                this.mUserInfo.setServerImageUrl(false);
                this.mUserInfo.update();
            }
            getView().setAvatarUrlSuccess(str);
            return;
        }
        this.mUserInfo.setAvatarUrl(str);
        this.mUserInfo.setUploadSuccess(false);
        this.mUserInfo.setServerImageUrl(false);
        this.mUserInfo.update();
        if (getView() != null) {
            getView().setAvatarUrlSuccess(str);
        }
    }

    public void updateHeight(String str, int i) {
        float f2;
        if (this.mUserInfo == null || TextUtils.isEmpty(str)) {
            return;
        }
        String languageText = "";
        if (i == 1) {
            try {
                f2 = Integer.parseInt(str);
                try {
                    languageText = LanguageUtil.getLanguageText(R.string.sport_detail_stride_avg_unit);
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                }
            } catch (Exception e3) {
                e = e3;
                f2 = -1.0f;
            }
        } else if (i != 2) {
            f2 = -1.0f;
        } else {
            try {
                f2 = Float.parseFloat(str);
                try {
                    languageText = LanguageUtil.getLanguageText(R.string.height_foot_short);
                } catch (Exception e4) {
                    e = e4;
                    e.printStackTrace();
                }
            } catch (Exception e5) {
                e = e5;
                f2 = -1.0f;
            }
        }
        if (f2 > -1.0f) {
            this.mUserInfo.setHeight(f2);
            this.mUserInfo.setHeightUnit(i);
            this.mUserInfo.setUploadSuccess(false);
            this.mUserInfo.update();
            getView().setHeight(str + languageText);
        }
    }

    public void updateWeight(String str, int i) {
        String languageText;
        if (this.mUserInfo == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if (i == 1) {
                this.mUserInfo.setWeight(Float.parseFloat(str));
            } else if (i == 2) {
                this.mUserInfo.setWeight(Integer.parseInt(str));
            }
            this.mUserInfo.setWeightUnit(i);
            this.mUserInfo.setUploadSuccess(false);
            this.mUserInfo.update();
            if (i == 1) {
                languageText = LanguageUtil.getLanguageText(R.string.weight_kg_unit_short);
            } else {
                languageText = LanguageUtil.getLanguageText(R.string.weight_pound_unit_short);
            }
            getView().setWeight(str + languageText);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void saveUserInfo() {
        if (this.mUserInfo == null || TextUtils.isEmpty(this.mNickName)) {
            return;
        }
        if (this.mOrignalWeight != this.mUserInfo.getWeight()) {
            WeightItemBean weightItemBean = new WeightItemBean();
            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            weightItemBean.setDate(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD));
            if (this.mUserInfo.getWeightUnit() == 1) {
                weightItemBean.setTotalWeight(this.mUserInfo.getWeight());
            } else {
                weightItemBean.setTotalWeight(Math.round(UnitUtil.getPound2Kg(this.mUserInfo.getWeight())));
            }
            weightItemBean.setUploadSuccess(false);
            weightItemBean.setTimeStamp(calendar.getTimeInMillis());
            weightItemBean.setUserId(this.mUserInfo.getUserId());
            weightItemBean.setBmi(WeightBmiEnum.caluteBmi(weightItemBean.getTotalWeight(), this.mUserInfo.getHeightCm()));
            weightItemBean.setUserId(RunTimeUtil.getInstance().getUserId());
            GreenDaoUtil.addWeight(weightItemBean);
        }
        if (RunTimeUtil.getInstance().getUserId() == -1) {
            EventBusHelper.post(Constants.EventConstants.EVENT_SYNC_USER_INFO_TO_DEVICE);
            return;
        }
        EventBusHelper.post(Constants.EventConstants.EVENT_SYNC_USER_INFO_TO_DEVICE);
        PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(RunTimeUtil.getInstance().getUserId());
        if (privateSafeSettingQueryPrivateSafeSetting == null) {
            return;
        }
        boolean savePrivateData = privateSafeSettingQueryPrivateSafeSetting.getSavePrivateData();
        if (NetworkUtil.isConnected(IdoApp.getAppContext()) && this.mUserInfo.getUserId() != -1 && this.mUserInfo.isServerImageUrl() && savePrivateData) {
            EventBusHelper.post(Constants.EventConstants.EVENT_UPLOAD_USER_INFO);
        }
    }
}