package com.ido.life.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.Telephony;
import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.ble.LocalDataManager;
import com.ido.ble.event.stat.one.d;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.FileDialDefinedUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.bean.AppNameBean;
import com.ido.life.bean.TranIconBean;
import com.ido.life.constants.Constants;
import com.ido.life.data.cache.AppNameLanguageManager;
import com.ido.life.data.cache.RemindDataManager;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: NoticeAppUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001GB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010'\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u0005H\u0002J\u0010\u0010*\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u0005H\u0002J\u0012\u0010+\u001a\u0004\u0018\u00010\u00052\u0006\u0010,\u001a\u00020\u000bH\u0007J\b\u0010-\u001a\u00020.H\u0002J\u0014\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u001aH\u0007J\u0018\u00100\u001a\u00020\b2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000204H\u0002J\u001a\u00100\u001a\u0004\u0018\u00010\b2\u0006\u0010)\u001a\u00020\u00052\u0006\u00103\u001a\u000204H\u0002J\"\u00105\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010 2\u0006\u0010)\u001a\u00020\u00052\b\u00106\u001a\u0004\u0018\u00010\u0005H\u0007J\u001c\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u00072\u0006\u00103\u001a\u000204H\u0002J\u000e\u00108\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007J\u0012\u00109\u001a\u0004\u0018\u00010\b2\u0006\u0010,\u001a\u00020\u000bH\u0007J\u0010\u0010:\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u0005H\u0007J\b\u0010;\u001a\u00020.H\u0007J\b\u0010<\u001a\u00020.H\u0002J\u0010\u0010=\u001a\u00020\u00132\u0006\u0010>\u001a\u00020\u0005H\u0007J\u0010\u0010?\u001a\u00020\u00132\u0006\u0010>\u001a\u00020\u0005H\u0007J\u0010\u0010@\u001a\u00020\u00132\u0006\u0010>\u001a\u00020\u0005H\u0007J\u0010\u0010A\u001a\u00020.2\u0006\u0010B\u001a\u00020CH\u0002J\u0012\u0010D\u001a\u00020.2\b\u0010E\u001a\u0004\u0018\u00010\u0005H\u0007J\u0014\u0010F\u001a\u0004\u0018\u00010\b2\b\u0010>\u001a\u0004\u0018\u00010\u0005H\u0007R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\r8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0012\u001a\u00020\u00138FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0014\u0010\u0002\u001a\u0004\b\u0012\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0019\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\u001aj\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b`\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u001e\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0004\u0012\u00020!\u0018\u00010 0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010#\u001a\u0004\u0018\u00010\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\b$\u0010\u0002\u001a\u0004\b%\u0010&¨\u0006H"}, d2 = {"Lcom/ido/life/util/NoticeAppUtil;", "", "()V", "COMPOSE_APP", "", "", "allNoticeAppBeans", "Ljava/util/LinkedHashMap;", "Lcom/ido/life/bean/TranIconBean;", "allNoticeAppBeansWithoutCompose", "allNoticeAppTypeBeans", "", "calendarPkgNames", "Ljava/util/ArrayList;", "getCalendarPkgNames", "()Ljava/util/ArrayList;", "emailPkgNames", "getEmailPkgNames", "isSupportV3Notify", "", "isSupportV3Notify$annotations", "()Z", "lastPkg", "lastValue", "mAllPresetApps", "mAlphabet", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "mAppLoading", "Ljava/util/concurrent/atomic/AtomicBoolean;", "mAppNameMap", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/ido/life/bean/AppNameBean;", "mDefaultPresetApps", "smsPkgName", "smsPkgName$annotations", "getSmsPkgName", "()Ljava/lang/String;", "checkRepeatValue", "value", "pkg", "convertPkg2Type", "convertType2Pkg", "type", "filterPresetApps", "", "getAllNoticeAppBeans", "getAppInfo", FileDialDefinedUtil.APP_FILE, "Landroid/content/pm/ApplicationInfo;", "pm", "Landroid/content/pm/PackageManager;", "getAppNames", "defaultName", "getDefaultApp", "getDefaultPresetApps", "getNoticeApp", "getTypeByPkg", d.m, "initAlphabet", "isCalendarApp", "pkgName", "isEmailApp", "isSmsApp", "loadInstallApp", "mContext", "Landroid/content/Context;", "removeAllAppBeans", "mac", "updateNoticeApp", "IPushCallBack", "app_release"}, k = 1, mv = {1, 1, 16})
public final class NoticeAppUtil {
    private static final List<String> COMPOSE_APP;
    public static final NoticeAppUtil INSTANCE;
    public static LinkedHashMap<String, TranIconBean> allNoticeAppBeans;
    private static final LinkedHashMap<String, TranIconBean> allNoticeAppBeansWithoutCompose;
    public static LinkedHashMap<Integer, String> allNoticeAppTypeBeans;
    private static String lastPkg;
    private static int lastValue;
    private static List<String> mAllPresetApps;
    private static final HashMap<String, Integer> mAlphabet;
    private static final AtomicBoolean mAppLoading;
    private static final ConcurrentHashMap<String, List<AppNameBean>> mAppNameMap;
    private static List<String> mDefaultPresetApps;

    /* JADX INFO: compiled from: NoticeAppUtil.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&¨\u0006\u0006"}, d2 = {"Lcom/ido/life/util/NoticeAppUtil$IPushCallBack;", "", "onFailed", "", "onFinish", "onSuccess", "app_release"}, k = 1, mv = {1, 1, 16})
    public interface IPushCallBack {
        void onFailed();

        void onFinish();

        void onSuccess();
    }

    @JvmStatic
    public static /* synthetic */ void isSupportV3Notify$annotations() {
    }

    @JvmStatic
    public static final void removeAllAppBeans(String mac) {
    }

    @JvmStatic
    public static /* synthetic */ void smsPkgName$annotations() {
    }

    static {
        NoticeAppUtil noticeAppUtil = new NoticeAppUtil();
        INSTANCE = noticeAppUtil;
        mAlphabet = new HashMap<>();
        mAllPresetApps = new ArrayList();
        mDefaultPresetApps = new ArrayList();
        COMPOSE_APP = new ArrayList();
        mAppNameMap = new ConcurrentHashMap<>();
        allNoticeAppBeans = new LinkedHashMap<>();
        allNoticeAppTypeBeans = new LinkedHashMap<>();
        allNoticeAppBeansWithoutCompose = new LinkedHashMap<>();
        mAppLoading = new AtomicBoolean(false);
        lastPkg = "";
        COMPOSE_APP.add(Constants.AppPackage.SMS);
        COMPOSE_APP.add("email");
        COMPOSE_APP.add(Constants.AppPackage.CALENDAR);
        COMPOSE_APP.add(Constants.AppPackage.MISS_CALL);
        mAllPresetApps.add("com.tencent.mm");
        mAllPresetApps.add("com.tencent.mobileqq");
        mAllPresetApps.add("com.tencent.qqlite");
        mAllPresetApps.add("com.google.android.youtube");
        mAllPresetApps.add("com.facebook.katana");
        mAllPresetApps.add("com.whatsapp");
        mAllPresetApps.add("com.whatsapp.w4b");
        mAllPresetApps.add("com.twitter.android");
        mAllPresetApps.add("com.tumblr");
        mAllPresetApps.add("com.instagram.android");
        mAllPresetApps.add("com.linkedin.android");
        mAllPresetApps.add("com.facebook.orca");
        mAllPresetApps.add("com.snapchat.android");
        mAllPresetApps.add("jp.naver.line.android");
        mAllPresetApps.add("line.android");
        mAllPresetApps.add("com.vkontakte.android");
        mAllPresetApps.add("com.viber.voip");
        mAllPresetApps.add("com.skype");
        mAllPresetApps.add("com.skype.insiders");
        mAllPresetApps.add("com.skype.raider");
        mAllPresetApps.add("com.kakao.talk");
        mAllPresetApps.add("com.pinterest");
        mAllPresetApps.add("org.telegram.messenger");
        mAllPresetApps.add("com.zhiliaoapp.musically");
        mAllPresetApps.add("com.ss.android.ugc.trill");
        mAllPresetApps.add(Constants.AppPackage.EBAY);
        mAllPresetApps.add(Constants.AppPackage.NETFLIX);
        mAllPresetApps.add(Constants.AppPackage.AMAZON_SHOPPING);
        mAllPresetApps.add(Constants.AppPackage.ZOOM);
        mAllPresetApps.add(Constants.AppPackage.DISNEY_PLUS);
        mAllPresetApps.add(Constants.AppPackage.SHAZAM);
        mAllPresetApps.add(Constants.AppPackage.GOOGLE_MEET);
        mAllPresetApps.add(Constants.AppPackage.HOUSEPARTY);
        mAllPresetApps.add(Constants.AppPackage.MX_TAKATAK);
        mAllPresetApps.add(Constants.AppPackage.MOJ);
        mAllPresetApps.add(Constants.AppPackage.DISCORD);
        mAllPresetApps.addAll(COMPOSE_APP);
        noticeAppUtil.initAlphabet();
    }

    private NoticeAppUtil() {
    }

    public static final /* synthetic */ LinkedHashMap access$getAllNoticeAppBeansWithoutCompose$p(NoticeAppUtil noticeAppUtil) {
        return allNoticeAppBeansWithoutCompose;
    }

    public static final /* synthetic */ AtomicBoolean access$getMAppLoading$p(NoticeAppUtil noticeAppUtil) {
        return mAppLoading;
    }

    @JvmStatic
    public static final void init() {
        NoticeAppUtil noticeAppUtil = INSTANCE;
        Context appContext = IdoApp.getAppContext();
        Intrinsics.checkExpressionValueIsNotNull(appContext, "IdoApp.getAppContext()");
        noticeAppUtil.loadInstallApp(appContext);
        RemindDataManager.INSTANCE.getInstance();
    }

    @JvmStatic
    public static final synchronized List<AppNameBean> getAppNames(String pkg, String defaultName) {
        Intrinsics.checkParameterIsNotNull(pkg, "pkg");
        if (mAppNameMap.containsKey(pkg)) {
            return mAppNameMap.get(pkg);
        }
        ArrayList arrayList = new ArrayList();
        String[] stringArray = ResourceUtils.INSTANCE.getStringArray(pkg);
        List<Integer> deviceLanguages = AppNameLanguageManager.INSTANCE.getInstance().getDeviceLanguages();
        if (ListUtils.INSTANCE.isNullOrEmpty(deviceLanguages)) {
            return null;
        }
        int size = deviceLanguages.size();
        for (int i = 0; i < size; i++) {
            int iIntValue = deviceLanguages.get(i).intValue();
            String str = (stringArray.length != 31 || iIntValue <= 0) ? defaultName : stringArray[iIntValue - 1];
            if (iIntValue > 0) {
                if (str == null) {
                    Intrinsics.throwNpe();
                }
                arrayList.add(new AppNameBean(iIntValue, str));
            }
        }
        if (arrayList.size() > 0) {
            mAppNameMap.put(pkg, arrayList);
        }
        CommonLogUtil.d("getAppNames list = " + arrayList);
        return arrayList;
    }

    @JvmStatic
    public static final List<String> getDefaultPresetApps() {
        return mAllPresetApps;
    }

    @JvmStatic
    public static final HashMap<String, TranIconBean> getAllNoticeAppBeans() {
        return allNoticeAppBeans;
    }

    public static final boolean isSupportV3Notify() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo == null) {
            supportFunctionInfo = new SupportFunctionInfo();
        }
        return supportFunctionInfo.V3_support_set_v3_notify_add_app_name;
    }

    @JvmStatic
    public static final boolean isEmailApp(String pkgName) {
        Intrinsics.checkParameterIsNotNull(pkgName, "pkgName");
        return INSTANCE.getEmailPkgNames().contains(pkgName) || StringsKt.contains$default((CharSequence) pkgName, (CharSequence) "email", false, 2, (Object) null);
    }

    @JvmStatic
    public static final boolean isCalendarApp(String pkgName) {
        Intrinsics.checkParameterIsNotNull(pkgName, "pkgName");
        return StringsKt.contains$default((CharSequence) pkgName, (CharSequence) Constants.AppPackage.CALENDAR, false, 2, (Object) null) || INSTANCE.getCalendarPkgNames().contains(pkgName);
    }

    @JvmStatic
    public static final boolean isSmsApp(String pkgName) {
        Intrinsics.checkParameterIsNotNull(pkgName, "pkgName");
        String str = pkgName;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String smsPkgName = getSmsPkgName();
        if (smsPkgName == null) {
            Intrinsics.throwNpe();
        }
        return StringsKt.contains$default((CharSequence) str, (CharSequence) smsPkgName, false, 2, (Object) null);
    }

    @JvmStatic
    public static final TranIconBean updateNoticeApp(String pkgName) {
        if (TextUtils.isEmpty(pkgName)) {
            return null;
        }
        return allNoticeAppBeans.get(pkgName);
    }

    @JvmStatic
    public static final TranIconBean getNoticeApp(int type) {
        return updateNoticeApp(convertType2Pkg(type));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LinkedHashMap<String, TranIconBean> getDefaultApp(PackageManager pm) {
        LinkedHashMap<String, TranIconBean> linkedHashMap = new LinkedHashMap<>();
        for (String str : mAllPresetApps) {
            TranIconBean tranIconBean = new TranIconBean();
            tranIconBean.setGroup(1);
            if (Intrinsics.areEqual(str, "email")) {
                tranIconBean.setIcon(ResourceUtil.getDrawable(R.mipmap.icon_email));
                tranIconBean.setAppName(LanguageUtil.getLanguageText(R.string.device_email));
            } else if (Intrinsics.areEqual(str, Constants.AppPackage.MISS_CALL)) {
                tranIconBean.setIcon(ResourceUtil.getDrawable(R.mipmap.icon_missed_call));
                tranIconBean.setAppName(LanguageUtil.getLanguageText(R.string.device_missed_call));
            } else if (Intrinsics.areEqual(str, Constants.AppPackage.CALENDAR)) {
                tranIconBean.setIcon(ResourceUtil.getDrawable(R.mipmap.icon_calendar));
                tranIconBean.setAppName(LanguageUtil.getLanguageText(R.string.device_calendar));
            } else if (Intrinsics.areEqual(str, Constants.AppPackage.SMS)) {
                tranIconBean.setIcon(ResourceUtil.getDrawable(R.mipmap.icon_sms));
                tranIconBean.setAppName(LanguageUtil.getLanguageText(R.string.device_sms));
            } else {
                tranIconBean.setGroup(3);
                tranIconBean.setInstalled(false);
            }
            tranIconBean.setPkgName(str);
            String pkgName = tranIconBean.getPkgName();
            Intrinsics.checkExpressionValueIsNotNull(pkgName, "bean.pkgName");
            tranIconBean.setType(convertPkg2Type(pkgName));
            if (!linkedHashMap.containsKey(tranIconBean.getPkgName()) && !allNoticeAppBeansWithoutCompose.containsKey(tranIconBean.getPkgName())) {
                String pkgName2 = tranIconBean.getPkgName();
                Intrinsics.checkExpressionValueIsNotNull(pkgName2, "bean.pkgName");
                linkedHashMap.put(pkgName2, tranIconBean);
            }
        }
        return linkedHashMap;
    }

    private final void loadInstallApp(final Context mContext) {
        if (mAppLoading.get()) {
            return;
        }
        mAppLoading.set(true);
        new Thread(new Runnable() { // from class: com.ido.life.util.NoticeAppUtil.loadInstallApp.1
            @Override // java.lang.Runnable
            public final void run() {
                MsgNotificationHelper.saveLog("loadInstallApp start");
                NoticeAppUtil.allNoticeAppTypeBeans.clear();
                PackageManager pm = mContext.getPackageManager();
                List<ApplicationInfo> installedApplications = pm.getInstalledApplications(8192);
                Intrinsics.checkExpressionValueIsNotNull(installedApplications, "pm.getInstalledApplicati…GET_UNINSTALLED_PACKAGES)");
                Collections.sort(installedApplications, new ApplicationInfo.DisplayNameComparator(pm));
                for (ApplicationInfo app : installedApplications) {
                    if ((app.flags & 1) <= 0 || (app.flags & 128) != 0) {
                        NoticeAppUtil noticeAppUtil = NoticeAppUtil.INSTANCE;
                        Intrinsics.checkExpressionValueIsNotNull(app, "app");
                        Intrinsics.checkExpressionValueIsNotNull(pm, "pm");
                        TranIconBean appInfo = noticeAppUtil.getAppInfo(app, pm);
                        NoticeAppUtil noticeAppUtil2 = NoticeAppUtil.INSTANCE;
                        String pkgName = appInfo.getPkgName();
                        Intrinsics.checkExpressionValueIsNotNull(pkgName, "bean.pkgName");
                        appInfo.setType(noticeAppUtil2.convertPkg2Type(pkgName));
                        appInfo.setGroup(3);
                        LinkedHashMap linkedHashMapAccess$getAllNoticeAppBeansWithoutCompose$p = NoticeAppUtil.access$getAllNoticeAppBeansWithoutCompose$p(NoticeAppUtil.INSTANCE);
                        String pkgName2 = appInfo.getPkgName();
                        Intrinsics.checkExpressionValueIsNotNull(pkgName2, "bean.pkgName");
                        linkedHashMapAccess$getAllNoticeAppBeansWithoutCompose$p.put(pkgName2, appInfo);
                    }
                }
                NoticeAppUtil.allNoticeAppBeans.clear();
                LinkedHashMap<String, TranIconBean> linkedHashMap = NoticeAppUtil.allNoticeAppBeans;
                NoticeAppUtil noticeAppUtil3 = NoticeAppUtil.INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(pm, "pm");
                linkedHashMap.putAll(noticeAppUtil3.getDefaultApp(pm));
                NoticeAppUtil.allNoticeAppBeans.putAll(NoticeAppUtil.access$getAllNoticeAppBeansWithoutCompose$p(NoticeAppUtil.INSTANCE));
                CommonLogUtil.d("loadInstallApp allNoticeAppBeans = " + NoticeAppUtil.allNoticeAppBeans.size());
                CommonLogUtil.d("loadInstallApp allNoticeAppTypeBeans = " + NoticeAppUtil.allNoticeAppTypeBeans.size());
                EventBusHelper.postSticky(Constants.EventConstants.EVENT_PRESET_APPS_LOADED);
                NoticeAppUtil.access$getMAppLoading$p(NoticeAppUtil.INSTANCE).set(false);
                MsgNotificationHelper.saveLog("loadInstallApp end");
            }
        }).start();
    }

    private final void filterPresetApps() {
        Collection<TranIconBean> collectionValues = allNoticeAppBeans.values();
        Intrinsics.checkExpressionValueIsNotNull(collectionValues, "allNoticeAppBeans.values");
        ArrayList arrayList = new ArrayList();
        for (Object obj : collectionValues) {
            TranIconBean it = (TranIconBean) obj;
            List<String> list = mAllPresetApps;
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            if (list.contains(it.getPkgName())) {
                arrayList.add(obj);
            }
        }
        ArrayList<TranIconBean> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        for (TranIconBean it2 : arrayList2) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            arrayList3.add(it2.getPkgName());
        }
        mDefaultPresetApps.clear();
        mDefaultPresetApps.addAll(arrayList3);
        CommonLogUtil.printAndSave("需要预置状态的应用列表：" + mDefaultPresetApps);
    }

    private final TranIconBean getAppInfo(String pkg, PackageManager pm) {
        try {
            TranIconBean tranIconBean = new TranIconBean();
            ApplicationInfo applicationInfo = pm.getApplicationInfo(pkg, 128);
            tranIconBean.setAppName(pm.getApplicationLabel(applicationInfo).toString());
            tranIconBean.setIcon(applicationInfo.loadIcon(pm));
            tranIconBean.setPkgName(pkg);
            tranIconBean.setAppUpdateTime(pm.getPackageInfo(applicationInfo.packageName, 0).lastUpdateTime);
            return tranIconBean;
        } catch (Exception unused) {
            CommonLogUtil.printAndSave("the app " + pkg + " has not installed!!");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TranIconBean getAppInfo(ApplicationInfo app, PackageManager pm) {
        TranIconBean tranIconBean = new TranIconBean();
        try {
            tranIconBean.setAppName(pm.getApplicationLabel(app).toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            tranIconBean.setIcon(app.loadIcon(pm));
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        tranIconBean.setPkgName(app.packageName);
        try {
            tranIconBean.setAppUpdateTime(pm.getPackageInfo(app.packageName, 0).lastUpdateTime);
        } catch (PackageManager.NameNotFoundException e4) {
            e4.printStackTrace();
        }
        return tranIconBean;
    }

    public static final String getSmsPkgName() {
        String defaultSmsPackage = (String) null;
        try {
            defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(IdoApp.getAppContext());
        } catch (Exception e2) {
            MsgNotificationHelper.saveLog("getSmsPkgName异常：" + e2);
            Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:"));
            Context appContext = IdoApp.getAppContext();
            Intrinsics.checkExpressionValueIsNotNull(appContext, "IdoApp.getAppContext()");
            ResolveInfo resolveInfoResolveActivity = appContext.getPackageManager().resolveActivity(intent, 65536);
            if (resolveInfoResolveActivity != null && resolveInfoResolveActivity.activityInfo != null) {
                defaultSmsPackage = resolveInfoResolveActivity.activityInfo.packageName;
            }
        }
        if (TextUtils.isEmpty(defaultSmsPackage)) {
            defaultSmsPackage = "com.android.mms";
        }
        MsgNotificationHelper.saveLog("getSmsPkgName为：" + defaultSmsPackage);
        return defaultSmsPackage;
    }

    public final ArrayList<String> getCalendarPkgNames() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("com.android.calendar");
        arrayList.add("com.htc.calendar");
        arrayList.add(Constants.AppPackage.CALENDAR);
        return arrayList;
    }

    public final ArrayList<String> getEmailPkgNames() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("com.tencent.androidqqmail");
        arrayList.add("com.netease.mobimail");
        arrayList.add("com.netease.mail");
        arrayList.add("com.google.android.gm");
        arrayList.add("com.my.mail");
        arrayList.add("com.microsoft.office.outlook");
        arrayList.add("com.trtf.blue");
        arrayList.add("me.bluemail.mail");
        arrayList.add("com.motorola.email");
        arrayList.add("com.htc.android.mail");
        arrayList.add("com.google.android.apps.inbox");
        arrayList.add("com.asus.email");
        arrayList.add("jp.co.yahoo.android.ymail");
        arrayList.add("com.fuzixx.dokidokipostbox");
        arrayList.add("ru.mail.mailapp");
        arrayList.add("air.kukulive.mailnow");
        arrayList.add("com.mail.emails");
        arrayList.add("com.nhn.android.mail");
        arrayList.add("com.zoho.mail");
        arrayList.add("com.syntomo.email");
        arrayList.add("com.corp21cn.mail189");
        arrayList.add("com.email.email");
        arrayList.add("com.motorola.blur.email");
        arrayList.add("com.jdex.gmail");
        arrayList.add("hotmail.hotmail.hotmail");
        return arrayList;
    }

    @JvmStatic
    public static final int getTypeByPkg(String pkg) {
        Intrinsics.checkParameterIsNotNull(pkg, "pkg");
        for (Map.Entry<Integer, String> entry : allNoticeAppTypeBeans.entrySet()) {
            if (Intrinsics.areEqual(entry.getValue(), pkg)) {
                return entry.getKey().intValue();
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int convertPkg2Type(String pkg) {
        try {
            Collection<String> collectionValues = allNoticeAppTypeBeans.values();
            Intrinsics.checkExpressionValueIsNotNull(collectionValues, "allNoticeAppTypeBeans.values");
            CollectionsKt.indexOf(collectionValues, pkg);
            if (allNoticeAppTypeBeans.values().contains(pkg)) {
                for (Map.Entry<Integer, String> entry : allNoticeAppTypeBeans.entrySet()) {
                    int iIntValue = entry.getKey().intValue();
                    if (Intrinsics.areEqual(pkg, entry.getValue())) {
                        MsgNotificationHelper.saveLog("重复转换：" + pkg + ", value = " + iIntValue);
                        return iIntValue;
                    }
                }
            }
        } catch (Exception e2) {
            MsgNotificationHelper.saveLog("convertPkg2Type(" + pkg + ") error: " + e2);
        }
        String str = pkg;
        int i = 0;
        int iIntValue2 = 0;
        int i2 = 0;
        while (i < str.length()) {
            int i3 = i2 + 1;
            Integer num = mAlphabet.get(String.valueOf(str.charAt(i)));
            if (num == null) {
                num = 0;
            }
            iIntValue2 += num.intValue() * i2;
            i++;
            i2 = i3;
        }
        if (20000 <= iIntValue2 && 21000 >= iIntValue2) {
            MsgNotificationHelper.saveLog("类型值处于设备预留翻译【20000,21000】，pkg = " + pkg + ",  value = " + iIntValue2);
            iIntValue2 *= 2;
        }
        int iCheckRepeatValue = checkRepeatValue(iIntValue2, pkg);
        MsgNotificationHelper.saveLog("generateTypeByPkg,   pkg = " + pkg + ", value = " + iCheckRepeatValue);
        allNoticeAppTypeBeans.put(Integer.valueOf(iCheckRepeatValue), pkg);
        return iCheckRepeatValue;
    }

    private final int checkRepeatValue(int value, String pkg) {
        if (!allNoticeAppTypeBeans.keySet().contains(Integer.valueOf(value))) {
            return value;
        }
        MsgNotificationHelper.saveLog("产生重复类型值" + value + ",  pkg = " + pkg + ",  lastPkg = " + allNoticeAppTypeBeans.get(Integer.valueOf(value)));
        return checkRepeatValue(value * 2, pkg);
    }

    @JvmStatic
    public static final String convertType2Pkg(int type) {
        return allNoticeAppTypeBeans.get(Integer.valueOf(type));
    }

    private final void initAlphabet() {
        Iterator<Integer> it = new IntRange(97, 122).iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            mAlphabet.put(String.valueOf((char) ((IntIterator) it).nextInt()), Integer.valueOf(i2));
            i2++;
        }
        int size = mAlphabet.size();
        Iterator<Integer> it2 = new IntRange(65, 90).iterator();
        while (it2.hasNext()) {
            mAlphabet.put(String.valueOf((char) ((IntIterator) it2).nextInt()), Integer.valueOf(i + size));
            i++;
        }
        HashMap<String, Integer> map = mAlphabet;
        map.put(".", Integer.valueOf(map.size()));
        HashMap<String, Integer> map2 = mAlphabet;
        map2.put("_", Integer.valueOf(map2.size()));
    }
}