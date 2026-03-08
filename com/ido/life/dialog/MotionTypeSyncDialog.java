package com.ido.life.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.life.customview.OnSyncChangeListener;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: MotionTypeSyncDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u001d2\u00020\u00012\u00020\u0002:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\u0012\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u0012\u0010\u0014\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u0018\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000eH\u0016J\u0010\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0012\u0010\u001b\u001a\u00020\u00102\b\u0010\u001c\u001a\u0004\u0018\u00010\u0005H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\t¨\u0006\u001e"}, d2 = {"Lcom/ido/life/dialog/MotionTypeSyncDialog;", "Lcom/ido/common/base/BaseDialogFragment;", "Lcom/ido/life/customview/OnSyncChangeListener;", "()V", "textOnComplete", "", "getTextOnComplete", "()Ljava/lang/String;", "setTextOnComplete", "(Ljava/lang/String;)V", "textOnProgress", "getTextOnProgress", "setTextOnProgress", "getLayoutResId", "", "initData", "", "initListener", "view", "Landroid/view/View;", "initUI", "onSync", "value", "max", "onSyncComplete", "success", "", "onSyncFailed", "msg", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MotionTypeSyncDialog extends BaseDialogFragment implements OnSyncChangeListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String MAX_VALUE_PARAMS = "max_value_params";
    private static final String TXT_ON_COMPLETE_PARAMS = "txt_on_complete_params";
    private static final String TXT_ON_PROGRESS_PARAMS = "txt_on_progress_params";
    private HashMap _$_findViewCache;
    private String textOnProgress = "";
    private String textOnComplete = "";

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
        return R.layout.dialog_motion_type_sync;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.ido.life.customview.OnSyncChangeListener
    public void onSyncFailed(String msg) {
    }

    public final String getTextOnProgress() {
        return this.textOnProgress;
    }

    public final void setTextOnProgress(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.textOnProgress = str;
    }

    public final String getTextOnComplete() {
        return this.textOnComplete;
    }

    public final void setTextOnComplete(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.textOnComplete = str;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        super.initData();
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString(TXT_ON_PROGRESS_PARAMS, "");
            Intrinsics.checkExpressionValueIsNotNull(string, "it.getString(TXT_ON_PROGRESS_PARAMS, \"\")");
            this.textOnProgress = string;
            String string2 = arguments.getString(TXT_ON_COMPLETE_PARAMS, "");
            Intrinsics.checkExpressionValueIsNotNull(string2, "it.getString(TXT_ON_COMPLETE_PARAMS, \"\")");
            this.textOnComplete = string2;
        }
        onSync(0, 100);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        Window it;
        Dialog dialog = getDialog();
        if (dialog == null || (it = dialog.getWindow()) == null) {
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(it, "it");
        it.getDecorView().setPadding(DipPixelUtil.dip2px(10.0f), 0, DipPixelUtil.dip2px(10.0f), DipPixelUtil.dip2px(15.0f));
        WindowManager.LayoutParams attributes = it.getAttributes();
        attributes.gravity = 17;
        attributes.width = (int) (ScreenUtil.getScreenW() * 0.8f);
        it.setAttributes(attributes);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initListener(View view) {
        super.initListener(view);
    }

    /* JADX INFO: compiled from: MotionTypeSyncDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/ido/life/dialog/MotionTypeSyncDialog$Companion;", "", "()V", "MAX_VALUE_PARAMS", "", "TXT_ON_COMPLETE_PARAMS", "TXT_ON_PROGRESS_PARAMS", "newInstance", "Lcom/ido/life/dialog/MotionTypeSyncDialog;", "textOnProgress", "textOnComplete", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final MotionTypeSyncDialog newInstance(String textOnProgress, String textOnComplete) {
            Intrinsics.checkParameterIsNotNull(textOnProgress, "textOnProgress");
            Intrinsics.checkParameterIsNotNull(textOnComplete, "textOnComplete");
            Bundle bundle = new Bundle();
            bundle.putString(MotionTypeSyncDialog.TXT_ON_PROGRESS_PARAMS, textOnProgress);
            bundle.putString(MotionTypeSyncDialog.TXT_ON_COMPLETE_PARAMS, textOnComplete);
            MotionTypeSyncDialog motionTypeSyncDialog = new MotionTypeSyncDialog();
            motionTypeSyncDialog.setArguments(bundle);
            motionTypeSyncDialog.setCancelable(false);
            motionTypeSyncDialog.setStyle(1, 2131886083);
            return motionTypeSyncDialog;
        }
    }

    @Override // com.ido.life.customview.OnSyncChangeListener
    public void onSync(int value, int max) {
        if (max <= 0 || ((TextView) _$_findCachedViewById(com.ido.life.R.id.tvTitle)) == null) {
            return;
        }
        if (value >= max) {
            TextView tvTitle = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvTitle);
            Intrinsics.checkExpressionValueIsNotNull(tvTitle, "tvTitle");
            tvTitle.setText(this.textOnComplete);
        } else {
            TextView tvTitle2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvTitle);
            Intrinsics.checkExpressionValueIsNotNull(tvTitle2, "tvTitle");
            tvTitle2.setText(this.textOnProgress);
        }
        ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(com.ido.life.R.id.progressBar);
        Intrinsics.checkExpressionValueIsNotNull(progressBar, "progressBar");
        progressBar.setMax(max);
        ProgressBar progressBar2 = (ProgressBar) _$_findCachedViewById(com.ido.life.R.id.progressBar);
        Intrinsics.checkExpressionValueIsNotNull(progressBar2, "progressBar");
        progressBar2.setProgress(value);
        TextView tvProgress = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvProgress);
        Intrinsics.checkExpressionValueIsNotNull(tvProgress, "tvProgress");
        StringBuilder sb = new StringBuilder();
        sb.append(MathKt.roundToInt((value / max) * 100));
        sb.append('%');
        tvProgress.setText(sb.toString());
    }

    @Override // com.ido.life.customview.OnSyncChangeListener
    public void onSyncComplete(boolean success) {
        TextView tvTitle = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvTitle);
        Intrinsics.checkExpressionValueIsNotNull(tvTitle, "tvTitle");
        tvTitle.setText(this.textOnComplete);
        TextView tvProgress = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvProgress);
        Intrinsics.checkExpressionValueIsNotNull(tvProgress, "tvProgress");
        tvProgress.setText("100%");
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvTitle);
        if (textView != null) {
            textView.postDelayed(new Runnable() { // from class: com.ido.life.dialog.MotionTypeSyncDialog.onSyncComplete.1
                @Override // java.lang.Runnable
                public final void run() {
                    MotionTypeSyncDialog.this.dismissAllowingStateLoss();
                }
            }, 200L);
        }
    }
}