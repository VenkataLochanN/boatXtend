package com.ido.life.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ScreenUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CommonSyncFailedDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\rH\u0014J\b\u0010\u001a\u001a\u00020\u0014H\u0014J\u0012\u0010\u001b\u001a\u00020\u00142\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u0012\u0010\u001e\u001a\u00020\u00142\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u0016\u0010\u001f\u001a\u00020\u00142\u000e\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013J\u0016\u0010!\u001a\u00020\u00142\u000e\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\b¨\u0006#"}, d2 = {"Lcom/ido/life/dialog/CommonSyncFailedDialog;", "Lcom/ido/common/base/BaseDialogFragment;", "()V", "cancel_text", "", "getCancel_text", "()Ljava/lang/String;", "setCancel_text", "(Ljava/lang/String;)V", FirebaseAnalytics.Param.CONTENT, "getContent", "setContent", "iconResId", "", "getIconResId", "()I", "setIconResId", "(I)V", "mCancelListener", "Lkotlin/Function0;", "", "mRetryListener", "retry_text", "getRetry_text", "setRetry_text", "getLayoutResId", "initData", "initListener", "view", "Landroid/view/View;", "initUI", "setOnCancelClickListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setOnRetryClickListener", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class CommonSyncFailedDialog extends BaseDialogFragment {
    private static final String BUTTON_CANCEL_TEXT_PARAMS = "button_cancel_text_params";
    private static final String BUTTON_RETRY_TEXT_PARAMS = "button_retry_text_params";
    private static final String CONTENT_PARAMS = "content_params";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String ICON_RESOURCE_ID_PARAMS = "icon_resource_id_params";
    private HashMap _$_findViewCache;
    private int iconResId;
    private Function0<Unit> mCancelListener;
    private Function0<Unit> mRetryListener;
    private String content = "";
    private String retry_text = "";
    private String cancel_text = "";

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View viewFindViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_common_sync_failed;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final int getIconResId() {
        return this.iconResId;
    }

    public final void setIconResId(int i) {
        this.iconResId = i;
    }

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.content = str;
    }

    public final String getRetry_text() {
        return this.retry_text;
    }

    public final void setRetry_text(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.retry_text = str;
    }

    public final String getCancel_text() {
        return this.cancel_text;
    }

    public final void setCancel_text(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.cancel_text = str;
    }

    public final void setOnRetryClickListener(Function0<Unit> listener) {
        this.mRetryListener = listener;
    }

    public final void setOnCancelClickListener(Function0<Unit> listener) {
        this.mCancelListener = listener;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        super.initData();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.iconResId = arguments.getInt(ICON_RESOURCE_ID_PARAMS);
            String string = arguments.getString(CONTENT_PARAMS, "");
            Intrinsics.checkExpressionValueIsNotNull(string, "it.getString(CONTENT_PARAMS, \"\")");
            this.content = string;
            String string2 = arguments.getString(BUTTON_CANCEL_TEXT_PARAMS, "");
            Intrinsics.checkExpressionValueIsNotNull(string2, "it.getString(BUTTON_CANCEL_TEXT_PARAMS, \"\")");
            this.cancel_text = string2;
            String string3 = arguments.getString(BUTTON_RETRY_TEXT_PARAMS, "");
            Intrinsics.checkExpressionValueIsNotNull(string3, "it.getString(BUTTON_RETRY_TEXT_PARAMS, \"\")");
            this.retry_text = string3;
        }
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        Window it;
        Dialog dialog = getDialog();
        if (dialog != null && (it = dialog.getWindow()) != null) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            it.getDecorView().setPadding(DipPixelUtil.dip2px(10.0f), 0, DipPixelUtil.dip2px(10.0f), DipPixelUtil.dip2px(15.0f));
            WindowManager.LayoutParams attributes = it.getAttributes();
            attributes.gravity = 17;
            attributes.width = (int) (ScreenUtil.getScreenW() * 0.8f);
            it.setAttributes(attributes);
        }
        TextView tvContent = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvContent);
        Intrinsics.checkExpressionValueIsNotNull(tvContent, "tvContent");
        tvContent.setText(this.content);
        ((ImageView) _$_findCachedViewById(com.ido.life.R.id.ivTipIcon)).setImageResource(this.iconResId);
        TextView tvRetry = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvRetry);
        Intrinsics.checkExpressionValueIsNotNull(tvRetry, "tvRetry");
        tvRetry.setText(this.retry_text);
        TextView tv_cancel = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_cancel);
        Intrinsics.checkExpressionValueIsNotNull(tv_cancel, "tv_cancel");
        tv_cancel.setText(this.cancel_text);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initListener(View view) {
        super.initListener(view);
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tvRetry)).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.dialog.CommonSyncFailedDialog.initListener.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                Function0 function0 = CommonSyncFailedDialog.this.mRetryListener;
                if (function0 != null) {
                    function0.invoke();
                }
            }
        });
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.dialog.CommonSyncFailedDialog.initListener.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                Function0 function0 = CommonSyncFailedDialog.this.mCancelListener;
                if (function0 != null) {
                    function0.invoke();
                }
            }
        });
    }

    /* JADX INFO: compiled from: CommonSyncFailedDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/ido/life/dialog/CommonSyncFailedDialog$Companion;", "", "()V", "BUTTON_CANCEL_TEXT_PARAMS", "", "BUTTON_RETRY_TEXT_PARAMS", "CONTENT_PARAMS", "ICON_RESOURCE_ID_PARAMS", "newInstance", "Lcom/ido/life/dialog/CommonSyncFailedDialog;", "iconResId", "", FirebaseAnalytics.Param.CONTENT, "cancel_text", "retry_text", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ CommonSyncFailedDialog newInstance$default(Companion companion, int i, String str, String str2, String str3, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = R.mipmap.icon_connect_caveat;
            }
            if ((i2 & 2) != 0) {
                str = LanguageUtil.getLanguageText(R.string.public_sync_failed);
                Intrinsics.checkExpressionValueIsNotNull(str, "LanguageUtil.getLanguage…tring.public_sync_failed)");
            }
            if ((i2 & 4) != 0) {
                str2 = LanguageUtil.getLanguageText(R.string.public_cancel);
                Intrinsics.checkExpressionValueIsNotNull(str2, "LanguageUtil.getLanguage…t(R.string.public_cancel)");
            }
            if ((i2 & 8) != 0) {
                str3 = LanguageUtil.getLanguageText(R.string.public_retry);
                Intrinsics.checkExpressionValueIsNotNull(str3, "LanguageUtil.getLanguage…xt(R.string.public_retry)");
            }
            return companion.newInstance(i, str, str2, str3);
        }

        public final CommonSyncFailedDialog newInstance(int iconResId, String content, String cancel_text, String retry_text) {
            Intrinsics.checkParameterIsNotNull(content, "content");
            Intrinsics.checkParameterIsNotNull(cancel_text, "cancel_text");
            Intrinsics.checkParameterIsNotNull(retry_text, "retry_text");
            Bundle bundle = new Bundle();
            bundle.putInt(CommonSyncFailedDialog.ICON_RESOURCE_ID_PARAMS, iconResId);
            bundle.putString(CommonSyncFailedDialog.CONTENT_PARAMS, content);
            bundle.putString(CommonSyncFailedDialog.BUTTON_CANCEL_TEXT_PARAMS, cancel_text);
            bundle.putString(CommonSyncFailedDialog.BUTTON_RETRY_TEXT_PARAMS, retry_text);
            CommonSyncFailedDialog commonSyncFailedDialog = new CommonSyncFailedDialog();
            commonSyncFailedDialog.setArguments(bundle);
            commonSyncFailedDialog.setCancelable(false);
            commonSyncFailedDialog.setStyle(1, 2131886083);
            return commonSyncFailedDialog;
        }
    }
}