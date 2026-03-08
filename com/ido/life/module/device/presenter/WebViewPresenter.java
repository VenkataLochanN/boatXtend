package com.ido.life.module.device.presenter;

import android.os.Handler;
import android.text.TextUtils;
import com.ido.common.constant.LanguageRegion;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.NumUtil;
import com.ido.life.VeryFitApp;
import com.ido.life.base.BasePresenter;
import com.ido.life.ble.DeviceConfigHelper;
import com.ido.life.data.DownloadManager;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.device.view.ICommWebView;
import com.ido.life.net.BaseUrl;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class WebViewPresenter extends BasePresenter<ICommWebView> implements DownloadManager.DownloadListener {
    private static final String TAG = "WebViewPresenter";
    private Handler mHandler;
    private List<String> mStringList = new ArrayList();
    private String questionHelpUrl = "";
    private String questionHelpSearchUrl = "";

    @Override // com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        this.mHandler = new Handler();
        getStringList();
    }

    public String getUrlByType(int i, String str) {
        String lowerCase;
        Locale locale = Locale.getDefault();
        if (locale == null || TextUtils.isEmpty(locale.getLanguage())) {
            lowerCase = LanguageRegion.ZH;
        } else {
            lowerCase = locale.getLanguage().toLowerCase();
            if (lowerCase.contentEquals(LanguageRegion.ZH) || lowerCase.contentEquals("cn")) {
                lowerCase = "cn";
            }
        }
        if (!this.mStringList.contains(lowerCase)) {
            lowerCase = "en";
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        String str2 = "";
        switch (i) {
            case 2:
                String str3 = String.format(BaseUrl.URL_USER_AGREEMENT, lowerCase, "boAt Wave");
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "用户协议的h5 网址：" + str3);
                return str3;
            case 3:
                String str4 = String.format(BaseUrl.URL_PRIVACY_POLICY, lowerCase, "boAt Wave");
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "隐私政策的h5 网址：" + str4);
                return str4;
            case 4:
                return String.format(BaseUrl.URL_APP_HELP, lowerCase, "boAt Wave");
            case 5:
                return String.format(BaseUrl.URL_LINK_HELP, lowerCase, "boAt Wave");
            case 6:
                String strConcat = BaseUrl.HOST_USER_THIRD.concat("userapi/faq/home/boAt%20Wave/").concat(lowerCase).concat("?status=0");
                this.questionHelpUrl = strConcat;
                this.questionHelpSearchUrl = BaseUrl.HOST_USER_THIRD.concat("userapi/faq/home/search/boAt%20Wave/").concat(lowerCase).concat("/0?keyword");
                return strConcat;
            case 7:
                String str5 = String.format(BaseUrl.URL_LINK_BACKGROUD_HELP, lowerCase);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "后台运行保护帮助的h5 网址：" + str5);
                return str5;
            case 8:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            default:
                return "";
            case 9:
                return String.format(BaseUrl.URL_FITNESS, lowerCase);
            case 10:
                if (!"cn".equals(lowerCase)) {
                    lowerCase = "en";
                }
                if (DeviceConfigHelper.getSupportFunctionInfo().V3_alexa_set_jump_ui) {
                    return String.format(BaseUrl.URL_LINK_ALEXA_HELP2, lowerCase);
                }
                return String.format(BaseUrl.URL_LINK_ALEXA_HELP, lowerCase);
            case 11:
                return String.format(BaseUrl.URL_LINK_ALEXA_APP, LanguageUtil.getSystemLanguage());
            case 18:
                UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
                int currentYear = 18;
                if (userInfoQueryUserInfo != null) {
                    if (!TextUtils.isEmpty(userInfoQueryUserInfo.getBirthday())) {
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "关于训练效果==== " + userInfoQueryUserInfo.getBirthday());
                        if (userInfoQueryUserInfo.getBirthday().contains("-")) {
                            currentYear = DateUtil.getCurrentYear() - NumUtil.parseInt(userInfoQueryUserInfo.getBirthday().split("-")[0]).intValue();
                        }
                    }
                    Object[] objArr = new Object[4];
                    objArr[0] = lowerCase;
                    objArr[1] = "" + currentYear;
                    StringBuilder sb = new StringBuilder();
                    sb.append("");
                    sb.append(userInfoQueryUserInfo.getGender() != 1 ? 0 : 1);
                    objArr[2] = sb.toString();
                    objArr[3] = lowerCase;
                    str2 = String.format(BaseUrl.SPORT_TRAINING, objArr);
                }
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "关于训练效果====url= " + str2);
                return str2;
            case 19:
                String str6 = String.format(BaseUrl.SPORT_NAME_EXPLAIN, lowerCase);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "运动名词解释====url= " + str6);
                return str6;
            case 20:
                String str7 = String.format(BaseUrl.SPORT_TYPE_EXPLAIN, lowerCase);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "运动方式说明====url= " + str7);
                return str7;
        }
    }

    public String getQuestionHelpUrl() {
        return this.questionHelpUrl;
    }

    public String getQuestionHelpSearchUrl() {
        return this.questionHelpSearchUrl;
    }

    public void downloadH5(int i) {
        String strInitFilePath = initFilePath(i, LanguageUtil.getSystemLanguage());
        if (NetworkUtil.isConnected(VeryFitApp.getApp())) {
            DownloadManager.download(getUrlByType(i, ""), strInitFilePath, this);
        } else if (isAttachView()) {
            getView().onGetH5(strInitFilePath);
        }
    }

    private String initFilePath(int i, String str) {
        File file = new File(LogPathImpl.getInstance().getHtmlFilePath());
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        return new File(LogPathImpl.getInstance().getHtmlFilePath(), createFileName(i, str)).getAbsolutePath();
    }

    private String createFileName(int i, String str) {
        return String.format("%1$s_%2$s.html", i != 1 ? "" : BaseUrl.HELP_EXPLAIN, str);
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadStart() {
        CommonLogUtil.d("-----下载onStart------");
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadProgress(int i) {
        CommonLogUtil.d("-----下载onProgress------" + i);
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadFinish(final String str) {
        CommonLogUtil.d("-----下载onFinish------" + str);
        if (isAttachView()) {
            this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$WebViewPresenter$F5hVyzVuNblbXA-mSmha_rN5JNE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDownloadFinish$0$WebViewPresenter(str);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onDownloadFinish$0$WebViewPresenter(String str) {
        getView().onGetH5(str);
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadFailed(int i, String str) {
        CommonLogUtil.d("-----下载onFail------" + str);
        if (isAttachView()) {
            this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$WebViewPresenter$js58k2SCkZCQzSRk_4BmysE0lqA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDownloadFailed$1$WebViewPresenter();
                }
            });
        }
    }

    public /* synthetic */ void lambda$onDownloadFailed$1$WebViewPresenter() {
        getView().onGetH5(null);
    }

    private void getStringList() {
        this.mStringList.add("en");
        this.mStringList.add("de");
        this.mStringList.add("es");
        this.mStringList.add("fr");
        this.mStringList.add("it");
        this.mStringList.add(LanguageRegion.JA);
        this.mStringList.add("ko");
        this.mStringList.add(LanguageRegion.PT);
        this.mStringList.add(LanguageRegion.RU);
        this.mStringList.add("cn");
    }
}