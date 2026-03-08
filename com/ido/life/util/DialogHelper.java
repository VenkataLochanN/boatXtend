package com.ido.life.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.VeryFitLifecycleCallbacks;
import com.ido.life.data.api.entity.AppInfoEntity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DialogHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/ido/life/util/DialogHelper;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class DialogHelper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int TYPE_APP_UPDATE = 1;
    private static final int TYPE_EXIT_LOGIN_CONFIRM = 2;
    private static final int TYPE_EXIT_LOGIN_NO_NET_CONFIRM = 3;
    private static final int TYPE_EXIT_LOGIN_CANCEL_SYNC = 4;
    private static final int TYPE_EXIT_LOGIN_SYNC_PROGRESS = 5;
    private static final int TYPE_NOTIFICATION_PERMISSION = 6;
    private static final int TYPE_GPS_SETTING = 7;
    private static final int TYPE_FITNESS_CALORIE_SUMMARY_DIALOG = 8;
    private static final int TYPE_FITNESS_TIME_SUMMARY_DIALOG = 8;
    private static final int TYPE_FITNESS_WALK_SUMMARY_DIALOG = 8;
    private static final int TYPE_DEVICE_QUICK_SETTING_DIALOG = 9;
    private static final int TYPE_NOTIFICATION_EXCEPTION = 10;
    private static final int TYPE_UI_UPDATE_DIALOG = 11;
    private static final Map<Integer, Object> mDialogMap = new LinkedHashMap();
    private static final int TYPE_APP_NORMAL_UPDATE = 2;

    public static final int TYPE_APP_NORMAL_UPDATE() {
        Companion companion = INSTANCE;
        return TYPE_APP_NORMAL_UPDATE;
    }

    public static final int TYPE_APP_UPDATE() {
        Companion companion = INSTANCE;
        return TYPE_APP_UPDATE;
    }

    public static final int TYPE_DEVICE_QUICK_SETTING_DIALOG() {
        Companion companion = INSTANCE;
        return TYPE_DEVICE_QUICK_SETTING_DIALOG;
    }

    public static final int TYPE_EXIT_LOGIN_CANCEL_SYNC() {
        Companion companion = INSTANCE;
        return TYPE_EXIT_LOGIN_CANCEL_SYNC;
    }

    public static final int TYPE_EXIT_LOGIN_CONFIRM() {
        Companion companion = INSTANCE;
        return TYPE_EXIT_LOGIN_CONFIRM;
    }

    public static final int TYPE_EXIT_LOGIN_NO_NET_CONFIRM() {
        Companion companion = INSTANCE;
        return TYPE_EXIT_LOGIN_NO_NET_CONFIRM;
    }

    public static final int TYPE_EXIT_LOGIN_SYNC_PROGRESS() {
        Companion companion = INSTANCE;
        return TYPE_EXIT_LOGIN_SYNC_PROGRESS;
    }

    public static final int TYPE_FITNESS_CALORIE_SUMMARY_DIALOG() {
        Companion companion = INSTANCE;
        return TYPE_FITNESS_CALORIE_SUMMARY_DIALOG;
    }

    public static final int TYPE_FITNESS_TIME_SUMMARY_DIALOG() {
        Companion companion = INSTANCE;
        return TYPE_FITNESS_TIME_SUMMARY_DIALOG;
    }

    public static final int TYPE_FITNESS_WALK_SUMMARY_DIALOG() {
        Companion companion = INSTANCE;
        return TYPE_FITNESS_WALK_SUMMARY_DIALOG;
    }

    public static final int TYPE_GPS_SETTING() {
        Companion companion = INSTANCE;
        return TYPE_GPS_SETTING;
    }

    public static final int TYPE_NOTIFICATION_EXCEPTION() {
        Companion companion = INSTANCE;
        return TYPE_NOTIFICATION_EXCEPTION;
    }

    public static final int TYPE_NOTIFICATION_PERMISSION() {
        Companion companion = INSTANCE;
        return TYPE_NOTIFICATION_PERMISSION;
    }

    public static final int TYPE_UI_UPDATE_DIALOG() {
        Companion companion = INSTANCE;
        return TYPE_UI_UPDATE_DIALOG;
    }

    @JvmStatic
    public static final boolean appUpdateDialogShowing() {
        return INSTANCE.appUpdateDialogShowing();
    }

    @JvmStatic
    public static final void dismissDialog(int i) {
        INSTANCE.dismissDialog(i);
    }

    @JvmStatic
    public static final void dismissDialog(Dialog dialog) {
        INSTANCE.dismissDialog(dialog);
    }

    @JvmStatic
    public static final void dismissDialogFragment(int i) {
        INSTANCE.dismissDialogFragment(i);
    }

    @JvmStatic
    public static final void dismissDialogFragment(DialogFragment dialogFragment) {
        INSTANCE.dismissDialogFragment(dialogFragment);
    }

    @JvmStatic
    public static final Dialog getDialog(int i) {
        return INSTANCE.getDialog(i);
    }

    @JvmStatic
    public static final BaseDialogFragment getDialogFragment(int i) {
        return INSTANCE.getDialogFragment(i);
    }

    @JvmStatic
    public static final Dialog showAppForceUpdateDialog(Activity activity) {
        return INSTANCE.showAppForceUpdateDialog(activity);
    }

    @JvmStatic
    public static final Dialog showAppNormalUpdateDialog(Activity activity, AppInfoEntity.AppInfo appInfo) {
        return INSTANCE.showAppNormalUpdateDialog(activity, appInfo);
    }

    @JvmStatic
    private static final DialogFragment showDialogFragmentInternal(FragmentManager fragmentManager, BaseDialogFragment baseDialogFragment, int i) {
        return INSTANCE.showDialogFragmentInternal(fragmentManager, baseDialogFragment, i);
    }

    @JvmStatic
    private static final Dialog showDialogInternal(Dialog dialog, int i) {
        return INSTANCE.showDialogInternal(dialog, i);
    }

    @JvmStatic
    public static final Dialog showFitnessCalorieSummaryDialog(Activity activity) {
        return INSTANCE.showFitnessCalorieSummaryDialog(activity);
    }

    @JvmStatic
    public static final Dialog showFitnessTimeSummaryDialog(Activity activity) {
        return INSTANCE.showFitnessTimeSummaryDialog(activity);
    }

    @JvmStatic
    public static final Dialog showFitnessWalkSummaryDialog(Activity activity) {
        return INSTANCE.showFitnessWalkSummaryDialog(activity);
    }

    @JvmStatic
    public static final DialogFragment showNotificationExceptionDialog(FragmentActivity fragmentActivity) {
        return INSTANCE.showNotificationExceptionDialog(fragmentActivity);
    }

    @JvmStatic
    public static final DialogFragment showNotificationPermissionDialog(FragmentActivity fragmentActivity) {
        return INSTANCE.showNotificationPermissionDialog(fragmentActivity);
    }

    /* JADX INFO: compiled from: DialogHelper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001d\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010%\u001a\u00020&H\u0007J\u0012\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0007J\u0010\u0010'\u001a\u00020(2\u0006\u0010+\u001a\u00020\u0004H\u0007J\u0012\u0010,\u001a\u00020(2\b\u0010-\u001a\u0004\u0018\u00010.H\u0007J\u0010\u0010,\u001a\u00020(2\u0006\u0010+\u001a\u00020\u0004H\u0007J\u0012\u0010/\u001a\u0004\u0018\u00010*2\u0006\u0010+\u001a\u00020\u0004H\u0007J\u0012\u00100\u001a\u0004\u0018\u0001012\u0006\u0010+\u001a\u00020\u0004H\u0007J\u0016\u00102\u001a\u0004\u0018\u00010*2\n\b\u0002\u00103\u001a\u0004\u0018\u000104H\u0007J\u001e\u00105\u001a\u0004\u0018\u00010*2\n\b\u0002\u00103\u001a\u0004\u0018\u0001042\u0006\u00106\u001a\u000207H\u0007J\"\u00108\u001a\u0004\u0018\u00010.2\u0006\u00109\u001a\u00020:2\u0006\u0010-\u001a\u0002012\u0006\u0010+\u001a\u00020\u0004H\u0003J\u0018\u0010;\u001a\u00020*2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0004H\u0003J\u0016\u0010<\u001a\u0004\u0018\u00010*2\n\b\u0002\u00103\u001a\u0004\u0018\u000104H\u0007J\u0016\u0010=\u001a\u0004\u0018\u00010*2\n\b\u0002\u00103\u001a\u0004\u0018\u000104H\u0007J\u0016\u0010>\u001a\u0004\u0018\u00010*2\n\b\u0002\u00103\u001a\u0004\u0018\u000104H\u0007J\u0016\u0010?\u001a\u0004\u0018\u00010.2\n\b\u0002\u00103\u001a\u0004\u0018\u00010@H\u0007J\u0016\u0010A\u001a\u0004\u0018\u00010.2\n\b\u0002\u00103\u001a\u0004\u0018\u00010@H\u0007R\u001c\u0010\u0003\u001a\u00020\u00048GX\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0003\u0010\u0006R\u001c\u0010\u0007\u001a\u00020\u00048GX\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\u0002\u001a\u0004\b\u0007\u0010\u0006R\u001c\u0010\t\u001a\u00020\u00048GX\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\t\u0010\u0006R\u001c\u0010\u000b\u001a\u00020\u00048GX\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\u0002\u001a\u0004\b\u000b\u0010\u0006R\u001c\u0010\r\u001a\u00020\u00048GX\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u0002\u001a\u0004\b\r\u0010\u0006R\u001c\u0010\u000f\u001a\u00020\u00048GX\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0002\u001a\u0004\b\u000f\u0010\u0006R\u001c\u0010\u0011\u001a\u00020\u00048GX\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0002\u001a\u0004\b\u0011\u0010\u0006R\u001c\u0010\u0013\u001a\u00020\u00048GX\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0002\u001a\u0004\b\u0013\u0010\u0006R\u001c\u0010\u0015\u001a\u00020\u00048GX\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0002\u001a\u0004\b\u0015\u0010\u0006R\u001c\u0010\u0017\u001a\u00020\u00048GX\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0002\u001a\u0004\b\u0017\u0010\u0006R\u001c\u0010\u0019\u001a\u00020\u00048GX\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0002\u001a\u0004\b\u0019\u0010\u0006R\u001c\u0010\u001b\u001a\u00020\u00048GX\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u0002\u001a\u0004\b\u001b\u0010\u0006R\u001c\u0010\u001d\u001a\u00020\u00048GX\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u0002\u001a\u0004\b\u001d\u0010\u0006R\u001c\u0010\u001f\u001a\u00020\u00048GX\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u0002\u001a\u0004\b\u001f\u0010\u0006R\u001d\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\"¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u0006B"}, d2 = {"Lcom/ido/life/util/DialogHelper$Companion;", "", "()V", "TYPE_APP_NORMAL_UPDATE", "", "TYPE_APP_NORMAL_UPDATE$annotations", "()I", "TYPE_APP_UPDATE", "TYPE_APP_UPDATE$annotations", "TYPE_DEVICE_QUICK_SETTING_DIALOG", "TYPE_DEVICE_QUICK_SETTING_DIALOG$annotations", "TYPE_EXIT_LOGIN_CANCEL_SYNC", "TYPE_EXIT_LOGIN_CANCEL_SYNC$annotations", "TYPE_EXIT_LOGIN_CONFIRM", "TYPE_EXIT_LOGIN_CONFIRM$annotations", "TYPE_EXIT_LOGIN_NO_NET_CONFIRM", "TYPE_EXIT_LOGIN_NO_NET_CONFIRM$annotations", "TYPE_EXIT_LOGIN_SYNC_PROGRESS", "TYPE_EXIT_LOGIN_SYNC_PROGRESS$annotations", "TYPE_FITNESS_CALORIE_SUMMARY_DIALOG", "TYPE_FITNESS_CALORIE_SUMMARY_DIALOG$annotations", "TYPE_FITNESS_TIME_SUMMARY_DIALOG", "TYPE_FITNESS_TIME_SUMMARY_DIALOG$annotations", "TYPE_FITNESS_WALK_SUMMARY_DIALOG", "TYPE_FITNESS_WALK_SUMMARY_DIALOG$annotations", "TYPE_GPS_SETTING", "TYPE_GPS_SETTING$annotations", "TYPE_NOTIFICATION_EXCEPTION", "TYPE_NOTIFICATION_EXCEPTION$annotations", "TYPE_NOTIFICATION_PERMISSION", "TYPE_NOTIFICATION_PERMISSION$annotations", "TYPE_UI_UPDATE_DIALOG", "TYPE_UI_UPDATE_DIALOG$annotations", "mDialogMap", "", "getMDialogMap", "()Ljava/util/Map;", "appUpdateDialogShowing", "", "dismissDialog", "", "dialog", "Landroid/app/Dialog;", "dialogType", "dismissDialogFragment", "dialogFragment", "Landroidx/fragment/app/DialogFragment;", "getDialog", "getDialogFragment", "Lcom/ido/common/base/BaseDialogFragment;", "showAppForceUpdateDialog", "activity", "Landroid/app/Activity;", "showAppNormalUpdateDialog", "appInfo", "Lcom/ido/life/data/api/entity/AppInfoEntity$AppInfo;", "showDialogFragmentInternal", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "showDialogInternal", "showFitnessCalorieSummaryDialog", "showFitnessTimeSummaryDialog", "showFitnessWalkSummaryDialog", "showNotificationExceptionDialog", "Landroidx/fragment/app/FragmentActivity;", "showNotificationPermissionDialog", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void TYPE_APP_NORMAL_UPDATE$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_APP_UPDATE$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_DEVICE_QUICK_SETTING_DIALOG$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_EXIT_LOGIN_CANCEL_SYNC$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_EXIT_LOGIN_CONFIRM$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_EXIT_LOGIN_NO_NET_CONFIRM$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_EXIT_LOGIN_SYNC_PROGRESS$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_FITNESS_CALORIE_SUMMARY_DIALOG$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_FITNESS_TIME_SUMMARY_DIALOG$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_FITNESS_WALK_SUMMARY_DIALOG$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_GPS_SETTING$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_NOTIFICATION_EXCEPTION$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_NOTIFICATION_PERMISSION$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_UI_UPDATE_DIALOG$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int TYPE_APP_UPDATE() {
            return DialogHelper.TYPE_APP_UPDATE;
        }

        public final int TYPE_EXIT_LOGIN_CONFIRM() {
            return DialogHelper.TYPE_EXIT_LOGIN_CONFIRM;
        }

        public final int TYPE_EXIT_LOGIN_NO_NET_CONFIRM() {
            return DialogHelper.TYPE_EXIT_LOGIN_NO_NET_CONFIRM;
        }

        public final int TYPE_EXIT_LOGIN_CANCEL_SYNC() {
            return DialogHelper.TYPE_EXIT_LOGIN_CANCEL_SYNC;
        }

        public final int TYPE_EXIT_LOGIN_SYNC_PROGRESS() {
            return DialogHelper.TYPE_EXIT_LOGIN_SYNC_PROGRESS;
        }

        public final int TYPE_NOTIFICATION_PERMISSION() {
            return DialogHelper.TYPE_NOTIFICATION_PERMISSION;
        }

        public final int TYPE_GPS_SETTING() {
            return DialogHelper.TYPE_GPS_SETTING;
        }

        public final int TYPE_FITNESS_CALORIE_SUMMARY_DIALOG() {
            return DialogHelper.TYPE_FITNESS_CALORIE_SUMMARY_DIALOG;
        }

        public final int TYPE_FITNESS_TIME_SUMMARY_DIALOG() {
            return DialogHelper.TYPE_FITNESS_TIME_SUMMARY_DIALOG;
        }

        public final int TYPE_FITNESS_WALK_SUMMARY_DIALOG() {
            return DialogHelper.TYPE_FITNESS_WALK_SUMMARY_DIALOG;
        }

        public final int TYPE_DEVICE_QUICK_SETTING_DIALOG() {
            return DialogHelper.TYPE_DEVICE_QUICK_SETTING_DIALOG;
        }

        public final int TYPE_NOTIFICATION_EXCEPTION() {
            return DialogHelper.TYPE_NOTIFICATION_EXCEPTION;
        }

        public final int TYPE_UI_UPDATE_DIALOG() {
            return DialogHelper.TYPE_UI_UPDATE_DIALOG;
        }

        public final Map<Integer, Object> getMDialogMap() {
            return DialogHelper.mDialogMap;
        }

        public final int TYPE_APP_NORMAL_UPDATE() {
            return DialogHelper.TYPE_APP_NORMAL_UPDATE;
        }

        public static /* synthetic */ Dialog showAppForceUpdateDialog$default(Companion companion, Activity activity, int i, Object obj) {
            if ((i & 1) != 0) {
                activity = (Activity) null;
            }
            return companion.showAppForceUpdateDialog(activity);
        }

        @JvmStatic
        public final Dialog showAppForceUpdateDialog(Activity activity) {
            if (activity == null) {
                VeryFitLifecycleCallbacks veryFitLifecycleCallbacks = VeryFitLifecycleCallbacks.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(veryFitLifecycleCallbacks, "VeryFitLifecycleCallbacks.getInstance()");
                Activity topActivity = veryFitLifecycleCallbacks.getTopActivity();
                if (topActivity == null) {
                    return null;
                }
                Companion companion = this;
                return companion.showDialogInternal(DialogFactoryKt.getAppForceUpdateDialog(topActivity), companion.TYPE_APP_UPDATE());
            }
            Companion companion2 = this;
            return companion2.showDialogInternal(DialogFactoryKt.getAppForceUpdateDialog(activity), companion2.TYPE_APP_UPDATE());
        }

        @JvmStatic
        public final boolean appUpdateDialogShowing() {
            Companion companion = this;
            Dialog dialog = companion.getDialog(companion.TYPE_APP_UPDATE());
            return dialog != null && dialog.isShowing();
        }

        @JvmStatic
        public final Dialog getDialog(int dialogType) {
            Object obj = getMDialogMap().get(Integer.valueOf(dialogType));
            if (obj instanceof Dialog) {
                return (Dialog) obj;
            }
            return null;
        }

        public static /* synthetic */ Dialog showAppNormalUpdateDialog$default(Companion companion, Activity activity, AppInfoEntity.AppInfo appInfo, int i, Object obj) {
            if ((i & 1) != 0) {
                activity = (Activity) null;
            }
            return companion.showAppNormalUpdateDialog(activity, appInfo);
        }

        @JvmStatic
        public final Dialog showAppNormalUpdateDialog(Activity activity, AppInfoEntity.AppInfo appInfo) {
            Intrinsics.checkParameterIsNotNull(appInfo, "appInfo");
            if (activity == null) {
                VeryFitLifecycleCallbacks veryFitLifecycleCallbacks = VeryFitLifecycleCallbacks.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(veryFitLifecycleCallbacks, "VeryFitLifecycleCallbacks.getInstance()");
                Activity topActivity = veryFitLifecycleCallbacks.getTopActivity();
                if (topActivity == null) {
                    return null;
                }
                AlertDialog appNormalUpdateDialog = DialogFactoryKt.getAppNormalUpdateDialog(topActivity, appInfo);
                Companion companion = this;
                return companion.showDialogInternal(appNormalUpdateDialog, companion.TYPE_APP_NORMAL_UPDATE());
            }
            AlertDialog appNormalUpdateDialog2 = DialogFactoryKt.getAppNormalUpdateDialog(activity, appInfo);
            Companion companion2 = this;
            return companion2.showDialogInternal(appNormalUpdateDialog2, companion2.TYPE_APP_NORMAL_UPDATE());
        }

        public static /* synthetic */ DialogFragment showNotificationExceptionDialog$default(Companion companion, FragmentActivity fragmentActivity, int i, Object obj) {
            if ((i & 1) != 0) {
                fragmentActivity = (FragmentActivity) null;
            }
            return companion.showNotificationExceptionDialog(fragmentActivity);
        }

        @JvmStatic
        public final DialogFragment showNotificationExceptionDialog(FragmentActivity activity) {
            Companion companion = this;
            if (companion.appUpdateDialogShowing()) {
                CommonLogUtil.printAndSave("已经显示了APP强制升级弹框，不显示通知异常提示弹框");
                return null;
            }
            if (activity == null) {
                VeryFitLifecycleCallbacks veryFitLifecycleCallbacks = VeryFitLifecycleCallbacks.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(veryFitLifecycleCallbacks, "VeryFitLifecycleCallbacks.getInstance()");
                Activity topActivity = veryFitLifecycleCallbacks.getTopActivity();
                if (topActivity == null || !(topActivity instanceof FragmentActivity)) {
                    return null;
                }
                BaseDialogFragment notificationExceptionDialog = DialogFactoryKt.getNotificationExceptionDialog();
                FragmentManager supportFragmentManager = ((FragmentActivity) topActivity).getSupportFragmentManager();
                Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "topActivity.supportFragmentManager");
                return companion.showDialogFragmentInternal(supportFragmentManager, notificationExceptionDialog, companion.TYPE_NOTIFICATION_PERMISSION());
            }
            BaseDialogFragment notificationExceptionDialog2 = DialogFactoryKt.getNotificationExceptionDialog();
            FragmentManager supportFragmentManager2 = activity.getSupportFragmentManager();
            Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager2, "activity.supportFragmentManager");
            return companion.showDialogFragmentInternal(supportFragmentManager2, notificationExceptionDialog2, companion.TYPE_NOTIFICATION_PERMISSION());
        }

        public static /* synthetic */ Dialog showFitnessCalorieSummaryDialog$default(Companion companion, Activity activity, int i, Object obj) {
            if ((i & 1) != 0) {
                activity = (Activity) null;
            }
            return companion.showFitnessCalorieSummaryDialog(activity);
        }

        @JvmStatic
        public final Dialog showFitnessCalorieSummaryDialog(Activity activity) {
            if (activity == null) {
                VeryFitLifecycleCallbacks veryFitLifecycleCallbacks = VeryFitLifecycleCallbacks.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(veryFitLifecycleCallbacks, "VeryFitLifecycleCallbacks.getInstance()");
                Activity topActivity = veryFitLifecycleCallbacks.getTopActivity();
                if (topActivity == null) {
                    return null;
                }
                Companion companion = this;
                return companion.showDialogInternal(DialogFactoryKt.getFitnessCalorieSummaryDialog(topActivity), companion.TYPE_FITNESS_CALORIE_SUMMARY_DIALOG());
            }
            Companion companion2 = this;
            return companion2.showDialogInternal(DialogFactoryKt.getFitnessCalorieSummaryDialog(activity), companion2.TYPE_FITNESS_CALORIE_SUMMARY_DIALOG());
        }

        public static /* synthetic */ Dialog showFitnessTimeSummaryDialog$default(Companion companion, Activity activity, int i, Object obj) {
            if ((i & 1) != 0) {
                activity = (Activity) null;
            }
            return companion.showFitnessTimeSummaryDialog(activity);
        }

        @JvmStatic
        public final Dialog showFitnessTimeSummaryDialog(Activity activity) {
            if (activity == null) {
                VeryFitLifecycleCallbacks veryFitLifecycleCallbacks = VeryFitLifecycleCallbacks.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(veryFitLifecycleCallbacks, "VeryFitLifecycleCallbacks.getInstance()");
                Activity topActivity = veryFitLifecycleCallbacks.getTopActivity();
                if (topActivity == null) {
                    return null;
                }
                Companion companion = this;
                return companion.showDialogInternal(DialogFactoryKt.getFitnessTimeSummaryDialog(topActivity), companion.TYPE_FITNESS_TIME_SUMMARY_DIALOG());
            }
            Companion companion2 = this;
            return companion2.showDialogInternal(DialogFactoryKt.getFitnessTimeSummaryDialog(activity), companion2.TYPE_FITNESS_TIME_SUMMARY_DIALOG());
        }

        public static /* synthetic */ Dialog showFitnessWalkSummaryDialog$default(Companion companion, Activity activity, int i, Object obj) {
            if ((i & 1) != 0) {
                activity = (Activity) null;
            }
            return companion.showFitnessWalkSummaryDialog(activity);
        }

        @JvmStatic
        public final Dialog showFitnessWalkSummaryDialog(Activity activity) {
            if (activity == null) {
                VeryFitLifecycleCallbacks veryFitLifecycleCallbacks = VeryFitLifecycleCallbacks.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(veryFitLifecycleCallbacks, "VeryFitLifecycleCallbacks.getInstance()");
                Activity topActivity = veryFitLifecycleCallbacks.getTopActivity();
                if (topActivity == null) {
                    return null;
                }
                Companion companion = this;
                return companion.showDialogInternal(DialogFactoryKt.getFitnessWalkSummaryDialog(topActivity), companion.TYPE_FITNESS_WALK_SUMMARY_DIALOG());
            }
            Companion companion2 = this;
            return companion2.showDialogInternal(DialogFactoryKt.getFitnessWalkSummaryDialog(activity), companion2.TYPE_FITNESS_WALK_SUMMARY_DIALOG());
        }

        public static /* synthetic */ DialogFragment showNotificationPermissionDialog$default(Companion companion, FragmentActivity fragmentActivity, int i, Object obj) {
            if ((i & 1) != 0) {
                fragmentActivity = (FragmentActivity) null;
            }
            return companion.showNotificationPermissionDialog(fragmentActivity);
        }

        @JvmStatic
        public final DialogFragment showNotificationPermissionDialog(FragmentActivity activity) {
            Companion companion = this;
            if (companion.appUpdateDialogShowing()) {
                CommonLogUtil.printAndSave("已经显示了APP强制升级弹框，不能再弹出消息提醒弹框。");
                return null;
            }
            if (activity == null) {
                VeryFitLifecycleCallbacks veryFitLifecycleCallbacks = VeryFitLifecycleCallbacks.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(veryFitLifecycleCallbacks, "VeryFitLifecycleCallbacks.getInstance()");
                Activity topActivity = veryFitLifecycleCallbacks.getTopActivity();
                if (topActivity == null || !(topActivity instanceof FragmentActivity)) {
                    return null;
                }
                BaseDialogFragment notificationTipDialog = DialogFactoryKt.getNotificationTipDialog();
                FragmentManager supportFragmentManager = ((FragmentActivity) topActivity).getSupportFragmentManager();
                Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "topActivity.supportFragmentManager");
                return companion.showDialogFragmentInternal(supportFragmentManager, notificationTipDialog, companion.TYPE_NOTIFICATION_PERMISSION());
            }
            BaseDialogFragment notificationTipDialog2 = DialogFactoryKt.getNotificationTipDialog();
            FragmentManager supportFragmentManager2 = activity.getSupportFragmentManager();
            Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager2, "activity.supportFragmentManager");
            return companion.showDialogFragmentInternal(supportFragmentManager2, notificationTipDialog2, companion.TYPE_NOTIFICATION_PERMISSION());
        }

        @JvmStatic
        public final BaseDialogFragment getDialogFragment(int dialogType) {
            Object obj = getMDialogMap().get(Integer.valueOf(dialogType));
            if (obj instanceof BaseDialogFragment) {
                return (BaseDialogFragment) obj;
            }
            return null;
        }

        @JvmStatic
        public final void dismissDialog(Dialog dialog) {
            if (dialog == null || !dialog.isShowing()) {
                return;
            }
            try {
                dialog.dismiss();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @JvmStatic
        public final void dismissDialog(int dialogType) {
            Companion companion = this;
            companion.dismissDialog(companion.getDialog(dialogType));
        }

        @JvmStatic
        public final void dismissDialogFragment(int dialogType) {
            Companion companion = this;
            companion.dismissDialogFragment(companion.getDialogFragment(dialogType));
        }

        @JvmStatic
        public final void dismissDialogFragment(DialogFragment dialogFragment) {
            if (dialogFragment == null || !dialogFragment.getShowsDialog()) {
                return;
            }
            try {
                dialogFragment.dismissAllowingStateLoss();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final Dialog showDialogInternal(Dialog dialog, final int dialogType) {
            Companion companion = this;
            companion.dismissDialog(companion.getDialog(dialogType));
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.ido.life.util.DialogHelper$Companion$showDialogInternal$1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    DialogHelper.INSTANCE.getMDialogMap().remove(Integer.valueOf(dialogType));
                }
            });
            try {
                dialog.show();
                getMDialogMap().put(Integer.valueOf(dialogType), dialog);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return dialog;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final DialogFragment showDialogFragmentInternal(FragmentManager fragmentManager, BaseDialogFragment dialogFragment, final int dialogType) {
            Companion companion = this;
            companion.dismissDialogFragment(companion.getDialogFragment(dialogType));
            dialogFragment.setOnDismissionListener(new BaseDialogFragment.OnDismissionListener() { // from class: com.ido.life.util.DialogHelper$Companion$showDialogFragmentInternal$1
                @Override // com.ido.common.base.BaseDialogFragment.OnDismissionListener
                public final void onDismission() {
                    DialogHelper.INSTANCE.getMDialogMap().remove(Integer.valueOf(dialogType));
                }
            });
            try {
                dialogFragment.show(fragmentManager);
                getMDialogMap().put(Integer.valueOf(dialogType), dialogFragment);
                return dialogFragment;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }
}