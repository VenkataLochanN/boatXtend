package com.ido.life.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.VeryFitLifecycleCallbacks;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.dialog.CommonDialog;
import com.ido.life.dialog.CommonSyncFailedDialog;
import com.ido.life.dialog.WallPaperDialFunctionSetDialog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: DialogUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\nJJ\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010J$\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0012\u001a\u00020\fJ¾\u0001\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00062\u0016\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0016j\b\u0012\u0004\u0012\u00020\n`\u00172\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\b\b\u0002\u0010$\u001a\u00020\n2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010#2\b\b\u0002\u0010&\u001a\u00020\n2\u0006\u0010'\u001a\u00020\n2\u0006\u0010(\u001a\u00020\n2\b\b\u0002\u0010)\u001a\u00020\u001a2\b\b\u0002\u0010*\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020,J2\u0010-\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\b\b\u0002\u0010.\u001a\u00020\f2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010/Jr\u00101\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u00102\u001a\u00020\u001a2\b\b\u0002\u00103\u001a\u00020\u001a2\b\b\u0002\u00104\u001a\u00020\u001a2\b\b\u0002\u00105\u001a\u00020\u001a2\b\b\u0002\u00106\u001a\u00020\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010JB\u00107\u001a\u0002002\u0006\u00108\u001a\u0002092\u0006\u0010\u000b\u001a\u00020\f2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\f0;2\u0006\u0010<\u001a\u00020\n2\u0014\u0010=\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u000200\u0018\u00010>J:\u0010?\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\b\b\u0002\u0010.\u001a\u00020\f2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010/JF\u0010@\u001a\u00020A2\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010B\u001a\u00020\n2\b\b\u0002\u0010C\u001a\u00020\f2\u0010\b\u0002\u0010D\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010/2\u0010\b\u0002\u0010E\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010/Jz\u0010F\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u00102\u001a\u00020\u001a2\b\b\u0002\u00103\u001a\u00020\u001a2\b\b\u0002\u00104\u001a\u00020\u001a2\b\b\u0002\u00105\u001a\u00020\u001a2\b\b\u0002\u00106\u001a\u00020\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¨\u0006G"}, d2 = {"Lcom/ido/life/util/DialogUtils;", "", "()V", "showCustomDialog", "Lcom/ido/life/dialog/CommonDialog;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "view", "Landroid/view/View;", CommonDialog.EXTRA_GRAVITY, "", CommonDialog.EXTRA_TITLE, "", "cancelTxt", "confirmTxt", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/ido/life/dialog/CommonDialog$OnDialogEventListener;", "showDeleteConfirmDialog", "tips", "showDialFunctionSetDialog", "Lcom/ido/life/dialog/WallPaperDialFunctionSetDialog;", "functionList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "selectedFunction", "functionStatus", "", FirebaseAnalytics.Param.LOCATION, "dialWidth", "dialHeight", "dialBgWidth", "dialBgHeight", "dialImageAspectRatio", "", "bgBitmap", "Landroid/graphics/Bitmap;", "bgResId", "dialBitmap", "dialResId", "timeColor", "functionColor", "isCircle", "isBracelet", "onClickListener", "Lcom/ido/life/dialog/WallPaperDialFunctionSetDialog$OnClickListener;", "showGlobalOneBtnTipsDialog", "txt", "Lkotlin/Function0;", "", "showGlobalTipsDialog", "showTips", "showTitle", "showCancel", "showConfirm", "tipsGravity", "showHeartRateThresholdSelectDialog", "activity", "Landroidx/fragment/app/FragmentActivity;", "values", "", "selPosition", "callback", "Lkotlin/Function1;", "showOneBtnTipsDialog", "showSyncFailedDialog", "Lcom/ido/life/dialog/CommonSyncFailedDialog;", "iconResId", FirebaseAnalytics.Param.CONTENT, "onCancel", "onRetry", "showTipsDialog", "app_release"}, k = 1, mv = {1, 1, 16})
public final class DialogUtils {
    public static final DialogUtils INSTANCE = new DialogUtils();

    private DialogUtils() {
    }

    public static /* synthetic */ CommonDialog showDeleteConfirmDialog$default(DialogUtils dialogUtils, FragmentManager fragmentManager, CommonDialog.OnDialogEventListener onDialogEventListener, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            onDialogEventListener = (CommonDialog.OnDialogEventListener) null;
        }
        if ((i & 4) != 0) {
            str = LanguageUtil.getLanguageText(R.string.public_delete_confirm_content);
            Intrinsics.checkExpressionValueIsNotNull(str, "LanguageUtil.getLanguage…c_delete_confirm_content)");
        }
        return dialogUtils.showDeleteConfirmDialog(fragmentManager, onDialogEventListener, str);
    }

    public final CommonDialog showDeleteConfirmDialog(FragmentManager fragmentManager, CommonDialog.OnDialogEventListener listener, String tips) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
        Intrinsics.checkParameterIsNotNull(tips, "tips");
        CommonDialog.Companion companion = CommonDialog.INSTANCE;
        String languageText = LanguageUtil.getLanguageText(R.string.public_delete);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…t(R.string.public_delete)");
        String languageText2 = LanguageUtil.getLanguageText(R.string.public_cancel);
        Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…t(R.string.public_cancel)");
        String languageText3 = LanguageUtil.getLanguageText(R.string.public_confirm);
        Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…(R.string.public_confirm)");
        CommonDialog commonDialogNewInstance = companion.newInstance(languageText, languageText2, languageText3, 17, 0.8f);
        commonDialogNewInstance.setOnConfirmClickListener(listener);
        commonDialogNewInstance.show(fragmentManager);
        commonDialogNewInstance.setContentView(null);
        commonDialogNewInstance.setCancelable(true);
        commonDialogNewInstance.showButton();
        Drawable drawable = ResourceUtil.getDrawable(R.drawable.bg_white_20_corner);
        Intrinsics.checkExpressionValueIsNotNull(drawable, "ResourceUtil.getDrawable…wable.bg_white_20_corner)");
        commonDialogNewInstance.setBackground(drawable);
        commonDialogNewInstance.setTips(tips);
        commonDialogNewInstance.showTips(true);
        return commonDialogNewInstance;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CommonSyncFailedDialog showSyncFailedDialog$default(DialogUtils dialogUtils, FragmentManager fragmentManager, int i, String str, Function0 function0, Function0 function02, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = R.mipmap.icon_connect_caveat;
        }
        int i3 = i;
        if ((i2 & 4) != 0) {
            str = LanguageUtil.getLanguageText(R.string.public_sync_failed);
            Intrinsics.checkExpressionValueIsNotNull(str, "LanguageUtil.getLanguage…tring.public_sync_failed)");
        }
        String str2 = str;
        if ((i2 & 8) != 0) {
            function0 = (Function0) null;
        }
        Function0 function03 = function0;
        if ((i2 & 16) != 0) {
            function02 = (Function0) null;
        }
        return dialogUtils.showSyncFailedDialog(fragmentManager, i3, str2, function03, function02);
    }

    public final CommonSyncFailedDialog showSyncFailedDialog(FragmentManager fragmentManager, int iconResId, String content, final Function0<Unit> onCancel, final Function0<Unit> onRetry) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
        Intrinsics.checkParameterIsNotNull(content, "content");
        final CommonSyncFailedDialog commonSyncFailedDialogNewInstance$default = CommonSyncFailedDialog.Companion.newInstance$default(CommonSyncFailedDialog.INSTANCE, iconResId, content, null, null, 12, null);
        commonSyncFailedDialogNewInstance$default.show(fragmentManager);
        commonSyncFailedDialogNewInstance$default.setOnRetryClickListener(new Function0<Unit>() { // from class: com.ido.life.util.DialogUtils.showSyncFailedDialog.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                commonSyncFailedDialogNewInstance$default.dismiss();
                Function0 function0 = onRetry;
                if (function0 != null) {
                    function0.invoke();
                }
            }
        });
        commonSyncFailedDialogNewInstance$default.setOnCancelClickListener(new Function0<Unit>() { // from class: com.ido.life.util.DialogUtils.showSyncFailedDialog.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                commonSyncFailedDialogNewInstance$default.dismiss();
                Function0 function0 = onCancel;
                if (function0 != null) {
                    function0.invoke();
                }
            }
        });
        return commonSyncFailedDialogNewInstance$default;
    }

    public static /* synthetic */ CommonDialog showCustomDialog$default(DialogUtils dialogUtils, FragmentManager fragmentManager, String str, View view, String str2, String str3, int i, CommonDialog.OnDialogEventListener onDialogEventListener, int i2, Object obj) {
        String str4;
        String str5;
        if ((i2 & 8) != 0) {
            String languageText = LanguageUtil.getLanguageText(R.string.public_cancel);
            Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…t(R.string.public_cancel)");
            str4 = languageText;
        } else {
            str4 = str2;
        }
        if ((i2 & 16) != 0) {
            String languageText2 = LanguageUtil.getLanguageText(R.string.public_confirm);
            Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…(R.string.public_confirm)");
            str5 = languageText2;
        } else {
            str5 = str3;
        }
        return dialogUtils.showCustomDialog(fragmentManager, str, view, str4, str5, (i2 & 32) != 0 ? 81 : i, (i2 & 64) != 0 ? (CommonDialog.OnDialogEventListener) null : onDialogEventListener);
    }

    public final CommonDialog showCustomDialog(FragmentManager fragmentManager, String title, View view, String cancelTxt, String confirmTxt, int gravity, CommonDialog.OnDialogEventListener listener) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(cancelTxt, "cancelTxt");
        Intrinsics.checkParameterIsNotNull(confirmTxt, "confirmTxt");
        CommonDialog commonDialogNewInstance$default = CommonDialog.Companion.newInstance$default(CommonDialog.INSTANCE, title, cancelTxt, confirmTxt, gravity, 0.0f, 16, null);
        commonDialogNewInstance$default.setOnConfirmClickListener(listener);
        commonDialogNewInstance$default.show(fragmentManager);
        commonDialogNewInstance$default.setContentView(view);
        commonDialogNewInstance$default.showButton();
        commonDialogNewInstance$default.noTips();
        return commonDialogNewInstance$default;
    }

    public static /* synthetic */ CommonDialog showCustomDialog$default(DialogUtils dialogUtils, FragmentManager fragmentManager, View view, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 81;
        }
        return dialogUtils.showCustomDialog(fragmentManager, view, i);
    }

    public final CommonDialog showCustomDialog(FragmentManager fragmentManager, View view, int gravity) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
        CommonDialog commonDialogNewInstance$default = CommonDialog.Companion.newInstance$default(CommonDialog.INSTANCE, "", "", "", gravity, 0.0f, 16, null);
        commonDialogNewInstance$default.show(fragmentManager);
        commonDialogNewInstance$default.setContentView(view);
        return commonDialogNewInstance$default;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CommonDialog showGlobalOneBtnTipsDialog$default(DialogUtils dialogUtils, String str, String str2, String str3, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = LanguageUtil.getLanguageText(R.string.public_confirm);
            Intrinsics.checkExpressionValueIsNotNull(str3, "LanguageUtil.getLanguage…(R.string.public_confirm)");
        }
        if ((i & 8) != 0) {
            function0 = (Function0) null;
        }
        return dialogUtils.showGlobalOneBtnTipsDialog(str, str2, str3, function0);
    }

    public final CommonDialog showGlobalOneBtnTipsDialog(String title, String tips, String txt, final Function0<Unit> listener) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(tips, "tips");
        Intrinsics.checkParameterIsNotNull(txt, "txt");
        return showGlobalTipsDialog$default(this, title, tips, txt, txt, 0, false, false, false, false, 17, new CommonDialog.SampleDialogEventListener() { // from class: com.ido.life.util.DialogUtils.showGlobalOneBtnTipsDialog.1
            @Override // com.ido.life.dialog.CommonDialog.SampleDialogEventListener, com.ido.life.dialog.CommonDialog.OnDialogEventListener
            public void onConfirmClick(CommonDialog dialog) {
                super.onConfirmClick(dialog);
                Function0 function0 = listener;
                if (function0 != null) {
                    function0.invoke();
                }
            }
        }, 368, null);
    }

    public static /* synthetic */ CommonDialog showGlobalTipsDialog$default(DialogUtils dialogUtils, String str, String str2, String str3, String str4, int i, boolean z, boolean z2, boolean z3, boolean z4, int i2, CommonDialog.OnDialogEventListener onDialogEventListener, int i3, Object obj) {
        String str5;
        String str6;
        if ((i3 & 4) != 0) {
            String languageText = LanguageUtil.getLanguageText(R.string.public_cancel);
            Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…t(R.string.public_cancel)");
            str5 = languageText;
        } else {
            str5 = str3;
        }
        if ((i3 & 8) != 0) {
            String languageText2 = LanguageUtil.getLanguageText(R.string.public_confirm);
            Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…(R.string.public_confirm)");
            str6 = languageText2;
        } else {
            str6 = str4;
        }
        return dialogUtils.showGlobalTipsDialog(str, str2, str5, str6, (i3 & 16) != 0 ? 17 : i, (i3 & 32) != 0 ? true : z, (i3 & 64) != 0 ? true : z2, (i3 & 128) != 0 ? true : z3, (i3 & 256) != 0 ? true : z4, (i3 & 512) != 0 ? 8388611 : i2, (i3 & 1024) != 0 ? (CommonDialog.OnDialogEventListener) null : onDialogEventListener);
    }

    public final CommonDialog showGlobalTipsDialog(String title, String tips, String cancelTxt, String confirmTxt, int gravity, boolean showTips, boolean showTitle, boolean showCancel, boolean showConfirm, int tipsGravity, CommonDialog.OnDialogEventListener listener) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(tips, "tips");
        Intrinsics.checkParameterIsNotNull(cancelTxt, "cancelTxt");
        Intrinsics.checkParameterIsNotNull(confirmTxt, "confirmTxt");
        CommonDialog commonDialogNewInstance = CommonDialog.INSTANCE.newInstance(title, cancelTxt, confirmTxt, gravity, 0.8f);
        VeryFitLifecycleCallbacks veryFitLifecycleCallbacks = VeryFitLifecycleCallbacks.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(veryFitLifecycleCallbacks, "VeryFitLifecycleCallbacks.getInstance()");
        Activity topActivity = veryFitLifecycleCallbacks.getTopActivity();
        if (topActivity != null && (topActivity instanceof FragmentActivity)) {
            try {
                commonDialogNewInstance.setOnConfirmClickListener(listener);
                commonDialogNewInstance.show(((FragmentActivity) topActivity).getSupportFragmentManager());
                commonDialogNewInstance.setContentView(null);
                commonDialogNewInstance.setCancelable(false);
                Drawable drawable = ResourceUtil.getDrawable(R.drawable.bg_white_20_corner);
                Intrinsics.checkExpressionValueIsNotNull(drawable, "ResourceUtil.getDrawable…wable.bg_white_20_corner)");
                commonDialogNewInstance.setBackground(drawable);
                commonDialogNewInstance.setTips(tips);
                commonDialogNewInstance.showTips(showTips);
                commonDialogNewInstance.showTitle(showTitle);
                if (!showCancel) {
                    commonDialogNewInstance.hideCancel();
                }
                if (!showConfirm) {
                    commonDialogNewInstance.hideConfirm();
                }
                commonDialogNewInstance.setTipsGravity(tipsGravity);
            } catch (Exception unused) {
            }
        } else {
            CommonLogUtil.printAndSave("the top activity is null or is not FragmentActivity");
        }
        return commonDialogNewInstance;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CommonDialog showOneBtnTipsDialog$default(DialogUtils dialogUtils, FragmentManager fragmentManager, String str, String str2, String str3, Function0 function0, int i, Object obj) {
        if ((i & 8) != 0) {
            str3 = LanguageUtil.getLanguageText(R.string.public_confirm);
            Intrinsics.checkExpressionValueIsNotNull(str3, "LanguageUtil.getLanguage…(R.string.public_confirm)");
        }
        String str4 = str3;
        if ((i & 16) != 0) {
            function0 = (Function0) null;
        }
        return dialogUtils.showOneBtnTipsDialog(fragmentManager, str, str2, str4, function0);
    }

    public final CommonDialog showOneBtnTipsDialog(FragmentManager fragmentManager, String title, String tips, String txt, final Function0<Unit> listener) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(tips, "tips");
        Intrinsics.checkParameterIsNotNull(txt, "txt");
        return showTipsDialog$default(this, fragmentManager, title, tips, txt, txt, 0, false, false, false, false, 17, new CommonDialog.SampleDialogEventListener() { // from class: com.ido.life.util.DialogUtils.showOneBtnTipsDialog.1
            @Override // com.ido.life.dialog.CommonDialog.SampleDialogEventListener, com.ido.life.dialog.CommonDialog.OnDialogEventListener
            public void onConfirmClick(CommonDialog dialog) {
                super.onConfirmClick(dialog);
                Function0 function0 = listener;
                if (function0 != null) {
                    function0.invoke();
                }
            }
        }, 736, null);
    }

    public static /* synthetic */ CommonDialog showTipsDialog$default(DialogUtils dialogUtils, FragmentManager fragmentManager, String str, String str2, String str3, String str4, int i, boolean z, boolean z2, boolean z3, boolean z4, int i2, CommonDialog.OnDialogEventListener onDialogEventListener, int i3, Object obj) {
        String str5;
        String str6;
        if ((i3 & 8) != 0) {
            String languageText = LanguageUtil.getLanguageText(R.string.public_cancel);
            Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…t(R.string.public_cancel)");
            str5 = languageText;
        } else {
            str5 = str3;
        }
        if ((i3 & 16) != 0) {
            String languageText2 = LanguageUtil.getLanguageText(R.string.public_confirm);
            Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…(R.string.public_confirm)");
            str6 = languageText2;
        } else {
            str6 = str4;
        }
        return dialogUtils.showTipsDialog(fragmentManager, str, str2, str5, str6, (i3 & 32) != 0 ? 17 : i, (i3 & 64) != 0 ? true : z, (i3 & 128) != 0 ? true : z2, (i3 & 256) != 0 ? true : z3, (i3 & 512) != 0 ? true : z4, (i3 & 1024) != 0 ? 8388611 : i2, (i3 & 2048) != 0 ? (CommonDialog.OnDialogEventListener) null : onDialogEventListener);
    }

    public final CommonDialog showTipsDialog(FragmentManager fragmentManager, String title, String tips, String cancelTxt, String confirmTxt, int gravity, boolean showTips, boolean showTitle, boolean showCancel, boolean showConfirm, int tipsGravity, CommonDialog.OnDialogEventListener listener) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(tips, "tips");
        Intrinsics.checkParameterIsNotNull(cancelTxt, "cancelTxt");
        Intrinsics.checkParameterIsNotNull(confirmTxt, "confirmTxt");
        CommonDialog commonDialogNewInstance = CommonDialog.INSTANCE.newInstance(title, cancelTxt, confirmTxt, gravity, 0.8f);
        try {
            commonDialogNewInstance.setOnConfirmClickListener(listener);
            commonDialogNewInstance.show(fragmentManager);
            commonDialogNewInstance.setContentView(null);
            commonDialogNewInstance.setCancelable(false);
            Drawable drawable = ResourceUtil.getDrawable(R.drawable.bg_white_20_corner);
            Intrinsics.checkExpressionValueIsNotNull(drawable, "ResourceUtil.getDrawable…wable.bg_white_20_corner)");
            commonDialogNewInstance.setBackground(drawable);
            commonDialogNewInstance.setTips(tips);
            commonDialogNewInstance.showTips(showTips);
            commonDialogNewInstance.showTitle(showTitle);
            if (!showCancel) {
                commonDialogNewInstance.hideCancel();
            }
            if (!showConfirm) {
                commonDialogNewInstance.hideConfirm();
            }
            commonDialogNewInstance.setTipsGravity(tipsGravity);
        } catch (Exception unused) {
        }
        return commonDialogNewInstance;
    }

    public final WallPaperDialFunctionSetDialog showDialFunctionSetDialog(FragmentManager fragmentManager, ArrayList<Integer> functionList, int selectedFunction, boolean functionStatus, int location, int dialWidth, int dialHeight, int dialBgWidth, int dialBgHeight, float dialImageAspectRatio, Bitmap bgBitmap, int bgResId, Bitmap dialBitmap, int dialResId, int timeColor, int functionColor, boolean isCircle, boolean isBracelet, WallPaperDialFunctionSetDialog.OnClickListener onClickListener) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
        Intrinsics.checkParameterIsNotNull(functionList, "functionList");
        Intrinsics.checkParameterIsNotNull(onClickListener, "onClickListener");
        WallPaperDialFunctionSetDialog wallPaperDialFunctionSetDialogNewInstance = WallPaperDialFunctionSetDialog.INSTANCE.newInstance((1 & 1) != 0 ? R.mipmap.icon_connect_caveat : 0, functionStatus, functionList, selectedFunction, location, (1 & 32) != 0 ? false : isCircle, (1 & 64) != 0 ? false : isBracelet, dialImageAspectRatio);
        wallPaperDialFunctionSetDialogNewInstance.show(fragmentManager);
        wallPaperDialFunctionSetDialogNewInstance.setOnClickListener(onClickListener);
        wallPaperDialFunctionSetDialogNewInstance.setTimeColor(timeColor);
        wallPaperDialFunctionSetDialogNewInstance.setFunctionColor(functionColor);
        wallPaperDialFunctionSetDialogNewInstance.setDialSize(dialWidth, dialHeight);
        wallPaperDialFunctionSetDialogNewInstance.setDialBgSize(dialBgWidth, dialBgHeight);
        if (bgBitmap != null) {
            wallPaperDialFunctionSetDialogNewInstance.setBgBitmap(bgBitmap);
        } else if (bgResId > 0) {
            wallPaperDialFunctionSetDialogNewInstance.setBgResId(bgResId);
        }
        if (dialBitmap != null) {
            wallPaperDialFunctionSetDialogNewInstance.setDialBitmap(dialBitmap);
        } else if (dialResId > 0) {
            wallPaperDialFunctionSetDialogNewInstance.setDialResId(dialResId);
        }
        return wallPaperDialFunctionSetDialogNewInstance;
    }

    /* JADX WARN: Type inference failed for: r12v0, types: [com.ido.life.util.DialogUtils$showHeartRateThresholdSelectDialog$adapter$1] */
    public final void showHeartRateThresholdSelectDialog(FragmentActivity activity, String title, final List<String> values, int selPosition, final Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(values, "values");
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = selPosition;
        View viewInflate = LayoutInflater.from(activity).inflate(R.layout.dialog_heart_rate_remind, (ViewGroup) null);
        View viewFindViewById = viewInflate.findViewById(R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "view.findViewById(R.id.recyclerview)");
        final Context appContext = IdoApp.getAppContext();
        final int i = R.layout.item_heart_rate_remind_set;
        final ?? r12 = new CommonRecyclerViewAdapter<String>(appContext, i, values) { // from class: com.ido.life.util.DialogUtils$showHeartRateThresholdSelectDialog$adapter$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder holder, String t, int position) {
                String str;
                TextView textView;
                TextPaint paint;
                if (position > 0) {
                    String languageText = LanguageUtil.getLanguageText(R.string.health_heart_rate_unit_with_value);
                    Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…art_rate_unit_with_value)");
                    Object[] objArr = {values.get(position)};
                    str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
                    Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
                } else {
                    str = (String) values.get(position);
                }
                if (holder != null) {
                    holder.setText(R.id.tvTitle, str);
                }
                if (holder != null) {
                    holder.setChecked(R.id.cbRemind, position == intRef.element);
                }
                if (holder != null) {
                    holder.setTextColorRes(R.id.tvTitle, position == intRef.element ? R.color.color_131825 : R.color.color_82868F);
                }
                if (holder == null || (textView = (TextView) holder.getView(R.id.tvTitle)) == null || (paint = textView.getPaint()) == null) {
                    return;
                }
                paint.setFakeBoldText(position == intRef.element);
            }
        };
        ((RecyclerView) viewFindViewById).setAdapter((RecyclerView.Adapter) r12);
        r12.setOnItemClickListener(new MultiItemTypeAdapterForRV.OnItemClickListener() { // from class: com.ido.life.util.DialogUtils.showHeartRateThresholdSelectDialog.1
            @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }

            @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                intRef.element = position;
                notifyDataSetChanged();
            }
        });
        FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "activity.supportFragmentManager");
        showCustomDialog$default(this, supportFragmentManager, title, viewInflate, null, null, 81, new CommonDialog.SampleDialogEventListener() { // from class: com.ido.life.util.DialogUtils.showHeartRateThresholdSelectDialog.2
            @Override // com.ido.life.dialog.CommonDialog.SampleDialogEventListener, com.ido.life.dialog.CommonDialog.OnDialogEventListener
            public void onConfirmClick(CommonDialog dialog) {
                super.onConfirmClick(dialog);
                Function1 function1 = callback;
                if (function1 != null) {
                    function1.invoke(Integer.valueOf(intRef.element));
                }
            }
        }, 24, null);
    }
}