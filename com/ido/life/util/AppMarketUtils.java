package com.ido.life.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppMarketUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00040\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020!2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fJ\u0018\u0010\"\u001a\u00020#2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010$\u001a\u0004\u0018\u00010%J\u0018\u0010&\u001a\u00020#2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010'\u001a\u0004\u0018\u00010\u0004J\u000e\u0010(\u001a\u00020!2\u0006\u0010\u001e\u001a\u00020\u001fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/ido/life/util/AppMarketUtils;", "", "()V", "ALI_MARKET_PAGE", "", "COOL_MARKET_PAGE", "HUAWEI_MARKET_PAGE", "MEIZU_MARKET_PAGE", "MI_MARKET_PAGE", "OPPO_MARKET_PAGE", "PACKAGE_360_MARKET", "PACKAGE_360_PAGE", "PACKAGE_ALI_MARKET", "PACKAGE_COOL_MARKET", "PACKAGE_HUAWEI_MARKET", "PACKAGE_MEIZU_MARKET", "PACKAGE_MI_MARKET", "PACKAGE_OPPO_MARKET", "PACKAGE_TENCENT_MARKET", "PACKAGE_UCWEB_MARKET", "PACKAGE_VIVO_MARKET", "PACKAGE_WANDOUJIA_MARKET", "PACKAGE_ZTE_MARKET", "TENCENT_MARKET_PAGE", "UCWEB_MARKET_PAGE", "VIVO_MARKET_PAGE", "WANDOUJIA_MARKET_PAGE", "ZTE_MARKET_PAGE", "getKeys", "", "context", "Landroid/content/Context;", "gotoMarket", "", "isIntentAvailable", "", "intent", "Landroid/content/Intent;", "isPackageExist", "packageName", "toAPPMarket", "app_release"}, k = 1, mv = {1, 1, 16})
public final class AppMarketUtils {
    public static final String ALI_MARKET_PAGE = "com.pp.assistant.activity.MainActivity";
    public static final String COOL_MARKET_PAGE = "com.coolapk.market.activity.AppViewActivity";
    public static final String HUAWEI_MARKET_PAGE = "com.huawei.appmarket.service.externalapi.view.ThirdApiActivity";
    public static final AppMarketUtils INSTANCE = new AppMarketUtils();
    public static final String MEIZU_MARKET_PAGE = "com.meizu.flyme.appcenter.activitys.AppMainActivity";
    public static final String MI_MARKET_PAGE = "com.xiaomi.market.ui.AppDetailActivity";
    public static final String OPPO_MARKET_PAGE = "a.a.a.aoz";
    public static final String PACKAGE_360_MARKET = "com.qihoo.appstore";
    public static final String PACKAGE_360_PAGE = "com.qihoo.appstore.distribute.SearchDistributionActivity";
    public static final String PACKAGE_ALI_MARKET = "com.pp.assistant";
    public static final String PACKAGE_COOL_MARKET = "com.coolapk.market";
    public static final String PACKAGE_HUAWEI_MARKET = "com.huawei.appmarket";
    public static final String PACKAGE_MEIZU_MARKET = "com.meizu.mstore";
    public static final String PACKAGE_MI_MARKET = "com.xiaomi.market";
    public static final String PACKAGE_OPPO_MARKET = "com.oppo.market";
    public static final String PACKAGE_TENCENT_MARKET = "com.tencent.android.qqdownloader";
    public static final String PACKAGE_UCWEB_MARKET = "com.UCMobile";
    public static final String PACKAGE_VIVO_MARKET = "com.bbk.appstore";
    public static final String PACKAGE_WANDOUJIA_MARKET = "com.wandoujia.phoenix2";
    public static final String PACKAGE_ZTE_MARKET = "zte.com.market";
    public static final String TENCENT_MARKET_PAGE = "com.tencent.pangu.link.LinkProxyActivity";
    public static final String UCWEB_MARKET_PAGE = "com.pp.assistant.activity.PPMainActivity";
    public static final String VIVO_MARKET_PAGE = "com.bbk.appstore.ui.AppStoreTabActivity";
    public static final String WANDOUJIA_MARKET_PAGE = "com.pp.assistant.activity.PPMainActivity";
    public static final String ZTE_MARKET_PAGE = "zte.com.market.view.zte.drain.ZtDrainTrafficActivity";

    private AppMarketUtils() {
    }

    public final void gotoMarket(Context context) {
        if (context == null) {
            return;
        }
        new Intent("android.intent.action.VIEW").setData(Uri.parse("market://details?id=" + context.getPackageName()));
        toAPPMarket(context);
    }

    public final void toAPPMarket(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.setData(Uri.parse("market://details?id=" + context.getPackageName()));
            intent.setPackage(context.getPackageName());
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            } else {
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setData(Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName()));
                intent2.addFlags(268435456);
                if (intent2.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent2);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private final List<String> getKeys(Context context) {
        ArrayList arrayList = new ArrayList();
        if (isPackageExist(context, PACKAGE_MI_MARKET)) {
            arrayList.add(PACKAGE_MI_MARKET);
            arrayList.add(MI_MARKET_PAGE);
        } else if (isPackageExist(context, PACKAGE_VIVO_MARKET)) {
            arrayList.add(PACKAGE_VIVO_MARKET);
            arrayList.add(VIVO_MARKET_PAGE);
        } else if (isPackageExist(context, PACKAGE_OPPO_MARKET)) {
            arrayList.add(PACKAGE_OPPO_MARKET);
            arrayList.add(OPPO_MARKET_PAGE);
        } else if (isPackageExist(context, PACKAGE_HUAWEI_MARKET)) {
            arrayList.add(PACKAGE_HUAWEI_MARKET);
            arrayList.add(HUAWEI_MARKET_PAGE);
        } else if (isPackageExist(context, PACKAGE_ZTE_MARKET)) {
            arrayList.add(PACKAGE_ZTE_MARKET);
            arrayList.add(ZTE_MARKET_PAGE);
        } else if (isPackageExist(context, PACKAGE_COOL_MARKET)) {
            arrayList.add(PACKAGE_COOL_MARKET);
            arrayList.add(COOL_MARKET_PAGE);
        } else if (isPackageExist(context, PACKAGE_360_MARKET)) {
            arrayList.add(PACKAGE_360_MARKET);
            arrayList.add(PACKAGE_360_PAGE);
        } else if (isPackageExist(context, PACKAGE_MEIZU_MARKET)) {
            arrayList.add(PACKAGE_MEIZU_MARKET);
            arrayList.add(MEIZU_MARKET_PAGE);
        } else if (isPackageExist(context, PACKAGE_TENCENT_MARKET)) {
            arrayList.add(PACKAGE_TENCENT_MARKET);
            arrayList.add(TENCENT_MARKET_PAGE);
        } else if (isPackageExist(context, PACKAGE_ALI_MARKET)) {
            arrayList.add(PACKAGE_ALI_MARKET);
            arrayList.add(ALI_MARKET_PAGE);
        } else if (isPackageExist(context, PACKAGE_WANDOUJIA_MARKET)) {
            arrayList.add(PACKAGE_WANDOUJIA_MARKET);
            arrayList.add("com.pp.assistant.activity.PPMainActivity");
        } else if (isPackageExist(context, PACKAGE_UCWEB_MARKET)) {
            arrayList.add(PACKAGE_UCWEB_MARKET);
            arrayList.add("com.pp.assistant.activity.PPMainActivity");
        }
        return arrayList;
    }

    public final boolean isPackageExist(Context context, String packageName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent().setPackage(packageName);
        Intrinsics.checkExpressionValueIsNotNull(intent, "Intent().setPackage(packageName)");
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 128);
        Intrinsics.checkExpressionValueIsNotNull(listQueryIntentActivities, "manager.queryIntentActiv…ageManager.GET_META_DATA)");
        return listQueryIntentActivities != null && listQueryIntentActivities.size() >= 1;
    }

    public final boolean isIntentAvailable(Context context, Intent intent) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        PackageManager packageManager = context.getPackageManager();
        if (intent == null) {
            return false;
        }
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 128);
        Intrinsics.checkExpressionValueIsNotNull(listQueryIntentActivities, "packageManager.queryInte…ageManager.GET_META_DATA)");
        return listQueryIntentActivities.size() > 0;
    }
}